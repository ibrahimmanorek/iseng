//package com.backend.tempatusaha.scheduler;
//
//import com.backend.tempatusaha.dto.request.MailRequest;
//import com.backend.tempatusaha.entity.Otp;
//import com.backend.tempatusaha.entity.SendEmail;
//import com.backend.tempatusaha.repository.OtpRepository;
//import com.backend.tempatusaha.repository.SendEmailRepository;
//import com.backend.tempatusaha.service.email.MailService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import java.util.List;
//import java.util.Optional;
//
//@Slf4j
//@Configuration
//@EnableScheduling
//public class JobSendMail {
//
//    @Autowired
//    private MailService mailService;
//
//    @Autowired
//    private SendEmailRepository sendEmailRepository;
//
//    @Autowired
//    private OtpRepository otpRepository;
//
//    @Scheduled(fixedRate = 20000)
//    public void findSendEmail() {
//        log.info("run job send mail");
//        List<SendEmail> sendEmailList = sendEmailRepository.findAllByFlag(0);
//        log.info("total send mail : {}", sendEmailList.size());
//        sendEmailList.forEach(s -> {
//            Optional<Otp> optionalOtp = otpRepository.findByAccountIdAndFlag(s.getAccountId().getId(), 0);
//            if(optionalOtp.isPresent()){
//                sendMail(s.getEmailTo(), s.getEmailFrom(), optionalOtp.get().getOtp());
//            } else {
//                log.info("tidak ada otp..");
//            }
//
//        });
//    }
//
//    public void sendMail(String emailTo, String emailFrom, String otp) {
//        MailRequest mail = new MailRequest();
//        mail.setMailFrom(emailFrom);
//        mail.setMailTo(emailTo);
//        mail.setMailSubject("Spring Boot - Email Example");
//        mail.setMailContent("Kode OTP : " + otp + " \n\n Thanks \n admin");
//        mailService.sendEmail(mail);
//    }
//}
