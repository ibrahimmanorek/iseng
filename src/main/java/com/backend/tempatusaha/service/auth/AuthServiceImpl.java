package com.backend.tempatusaha.service.auth;

import com.backend.tempatusaha.dto.request.*;
import com.backend.tempatusaha.dto.response.JwtResponse;
import com.backend.tempatusaha.dto.response.Response;
import com.backend.tempatusaha.dto.response.TokenRefreshResponse;
import com.backend.tempatusaha.entity.*;
import com.backend.tempatusaha.exception.ExceptionResponse;
import com.backend.tempatusaha.repository.AccountRepository;
import com.backend.tempatusaha.repository.OtpRepository;
import com.backend.tempatusaha.repository.RoleRepository;
import com.backend.tempatusaha.repository.SendEmailRepository;
import com.backend.tempatusaha.security.jwt.JwtUtils;
import com.backend.tempatusaha.service.email.MailService;
import com.backend.tempatusaha.service.users.RefreshTokenService;
import com.backend.tempatusaha.service.users.UserDetailsImpl;
import com.backend.tempatusaha.utils.Constant;
import com.backend.tempatusaha.utils.Helper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private SendEmailRepository sendEmailRepository;

    @Autowired
    private OtpRepository otpRepository;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Autowired
    private Helper helper;

    @Override
    public Response save(SignUpRequest signUpRequest) {
        if (accountRepository.existsByUsername(signUpRequest.getUsername()))
            throw new ExceptionResponse("username is already taken !!");

        if (accountRepository.existsByEmail(signUpRequest.getEmail()))
            throw new ExceptionResponse("email is already taken !!");

//        Set<String> strRoles = signUpRequest.getRole();
//        if(strRoles == null)
//            throw new ExceptionResponse("Role is required !!");
//
//        Set<Role> roles = new HashSet<>();
//        strRoles.forEach(role -> {
//            switch (role){
//                case "admin" :
//                case "ADMIN" :
//                    Role admin = roleRepository.findByRoleNameAndIsAktif(ERole.ADMIN, 1)
//                            .orElseThrow(() -> new ExceptionResponse("Role Not Found !!"));
//                    roles.add(admin);
//                    break;
//                case "member" :
//                case "MEMBER" :
//                    Role member = roleRepository.findByRoleNameAndIsAktif(ERole.MEMBER, 1)
//                            .orElseThrow(() -> new ExceptionResponse("Error : Role Not Found !!"));
//                    roles.add(member);
//                    break;
//                default:
//                    Role partner = roleRepository.findByRoleNameAndIsAktif(ERole.PARTNER, 1)
//                            .orElseThrow(() -> new ExceptionResponse("Error : Role Not Found !!"));
//                    roles.add(partner);
//                    break;
//            }
//        });

        Role role = roleRepository.findByIdAndIsAktif(signUpRequest.getRoleId(), 1).orElseThrow(() -> new ExceptionResponse("Role tidak aktif"));

        Account account = accountRepository.save(Account.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .phoneNumber(signUpRequest.getPhoneNumber())
                .address(signUpRequest.getAddress())
                .isAktif(0)
                .roleId(role)
                .rekening(signUpRequest.getRekening())
                .bank(signUpRequest.getBank())
                .build());

        Otp otp = otpRepository.save(Otp.builder()
                .accountId(account)
                .otp(Helper.randomString())
                .tipeOtp(Constant.REGISTER)
                .build());

        sendEmailRepository.save(SendEmail.builder()
                .emailTo(signUpRequest.getEmail())
                .emailFrom(emailFrom)
                .tipeEmail(Constant.REGISTER)
                .accountId(account)
                .otpId(otp)
                .build());

        helper.sendMail(signUpRequest.getEmail(), emailFrom, otp.getOtp());

        return Response.builder()
                .success(true)
                .message("successfully")
                .data(account)
                .build();
    }

    @Override
    public Response login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        String jwt = jwtUtils.generateJwtToken(userDetails);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        return Response.builder()
                .success(false)
                .message("successfully")
                .data(JwtResponse.builder()
                        .token(jwt)
                        .refreshToken(refreshToken.getToken())
                        .id(userDetails.getId())
                        .username(userDetails.getUsername())
                        .email(userDetails.getEmail())
                        .role(roles)
                        .type("Bearer")
                        .build())
                .build();
    }

    @Override
    public Response refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String tokenRefresh = refreshTokenService.findByToken(refreshTokenRequest.getRefreshToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getAccountId)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    return token;
                })
                .orElseThrow(() -> new ExceptionResponse("Refresh token is not in database!"));

        return Response.builder()
                .success(true)
                .message("successfully")
                .data(TokenRefreshResponse.builder()
                        .accessToken(refreshTokenRequest.getRefreshToken())
                        .refreshToken(tokenRefresh)
                        .tokenType("Bearer")
                        .build())
                .build();
    }

    @Override
    public Response resendOtp(String email) {
        Account account = accountRepository.findByEmailAndIsAktif(email, 0).orElseThrow(() -> new ExceptionResponse("Email tidak ditemukan"));

        Otp otp = otpRepository.findByAccountIdAndFlagAndTipeOtp(account, 0, "register").orElseThrow(() -> new ExceptionResponse("Otp tidak ditemukan"));
        otp.setOtp(Helper.randomString());
        otp.setUpdatedDate(LocalDateTime.now());
        otp.setCreatedDate(LocalDateTime.now());

        sendEmailRepository.save(SendEmail.builder()
                .emailTo(account.getEmail())
                .emailFrom(emailFrom)
                .tipeEmail(Constant.REGISTER)
                .accountId(account)
                .otpId(otp)
                .build());

        helper.sendMail(email, emailFrom, otp.getOtp());

        return Response.builder()
                .success(true)
                .message("successfully")
                .data(account)
                .build();
    }

    @Override
    public Response forgotPassword(String email, HttpServletRequest servletRequest) {
        Account account = accountRepository.findByEmailAndIsAktif(email, 1).orElseThrow(() -> new ExceptionResponse("Email tidak ditemukan"));

        String url = servletRequest.getRequestURL().toString();
        String emailBody = url + "?email="+ email;
        log.info(">>> {}", emailBody);
        sendEmailRepository.save(SendEmail.builder()
                .emailTo(account.getEmail())
                .emailFrom(emailFrom)
                .tipeEmail(Constant.PASSWORD)
                .accountId(account)
                .build());

        helper.sendMailForgotPassword(email, emailFrom, emailBody);

        return Response.builder()
                .success(true)
                .message("successfully")
                .data(account)
                .build();
    }

    @Override
    public Response getForgotPassword(String email) {
        log.info("masukk >>>");
        return null;
    }

    @Override
    public Response forgotPasswordUpdate(ForgotPasswordRequest request) {
        Account account = accountRepository.findByEmailAndIsAktif(request.getEmail(), 1).orElseThrow(() -> new ExceptionResponse("Email tidak ditemukan"));
        boolean isMatches = passwordEncoder.matches(request.getOldPassword(), account.getPassword());
        if(!isMatches){
            throw new ExceptionResponse("Password lama tidak sesuai");
        }

        account.setPassword(passwordEncoder.encode(request.getNewPassword()));
        account.setUpdatedDate(LocalDateTime.now());
        account = accountRepository.save(account);
        return Response.builder()
                .success(true)
                .message("successfully")
                .data(account)
                .build();
    }
}
