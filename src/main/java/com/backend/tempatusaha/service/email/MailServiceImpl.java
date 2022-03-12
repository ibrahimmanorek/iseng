package com.backend.tempatusaha.service.email;

import com.backend.tempatusaha.dto.request.MailRequest;
import com.backend.tempatusaha.repository.SendEmailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
@Slf4j
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SendEmailRepository sendEmailRepository;

    @Override
    public void sendEmail(MailRequest mail) {
        boolean isSuccess = true;
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom(), "ibrahimmanorek"));
            mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setText(mail.getMailContent());

            mailSender.send(mimeMessageHelper.getMimeMessage());
            sendEmailRepository.updateFlag(mail.getMailTo(), 1, "successfully");
        } catch (Exception e) {
            e.printStackTrace();
            sendEmailRepository.updateFlag(mail.getMailTo(), 9, e.getMessage());
        }
    }
}
