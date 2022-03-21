package com.backend.tempatusaha.utils;

import com.backend.tempatusaha.dto.request.MailRequest;
import com.backend.tempatusaha.service.email.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
public class Helper {

    @Autowired
    private MailService mailService;

    public static String randomString() {
        log.info(">>>> otp");
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    public void sendMail(String emailTo, String emailFrom, String otp) {
        MailRequest mail = new MailRequest();
        mail.setMailFrom(emailFrom);
        mail.setMailTo(emailTo);
        mail.setMailSubject("OTP Registration");
        mail.setMailContent("Kode OTP : " + otp);
        mailService.sendEmail(mail);
    }

    public void sendMailForgotPassword(String emailTo, String emailFrom, String content) {
        String konten = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + content + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        MailRequest mail = new MailRequest();
        mail.setMailFrom(emailFrom);
        mail.setMailTo(emailTo);
        mail.setMailSubject("Forgot Password");
        mail.setMailContent(konten);
        mailService.sendEmail(mail);
    }
}
