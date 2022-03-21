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

    public static String randomString(){
        log.info(">>>> otp");
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    public void sendMail(String emailTo, String emailFrom, String otp) {
        MailRequest mail = new MailRequest();
        mail.setMailFrom(emailFrom);
        mail.setMailTo(emailTo);
        mail.setMailSubject("Spring Boot - Email Example");
        mail.setMailContent("Kode OTP : " + otp + " \n\n Thanks \n admin");
        mailService.sendEmail(mail);
    }
}
