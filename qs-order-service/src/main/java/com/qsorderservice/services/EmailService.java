package com.qsorderservice.services;

import com.qsorderservice.models.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Service class for the email entity.
 * Contains business logic.
 */
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}") private String sender;

    /**
     * Sends an email to a specified recipient via Google's SMTP server.
     * @param details
     * @return
     */
    public String sendSimpleMail(EmailDetails details)
    {
        try {

            SimpleMailMessage mailMessage = new SimpleMailMessage(); //Instantiate a simple mail message object.

            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            javaMailSender.send(mailMessage);
            return "Email has been sent to the recipient";
        }

        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }

}
