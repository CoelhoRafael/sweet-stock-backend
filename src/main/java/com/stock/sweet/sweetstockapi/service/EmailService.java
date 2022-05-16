package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.config.TemplateEngineConfig;
import com.stock.sweet.sweetstockapi.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private TemplateEngineConfig templateEngineConfig;

    @Autowired
    private Environment environment;

    @Autowired
    private TemplateEngine htmlTemplateEngine;


    public void sendEmail(Email emailModel) {
        emailModel.setSendDateEmail(LocalDateTime.now());
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message);
            System.out.println("Email enviado para " + emailModel.getEmailTo());
        } catch (MailException e) {
            System.out.println("Falha ao enviar email" + e);
        }
    }

    public boolean sendHtmlEmail(String to, String subject, String template, Context ctx) {

        final MimeMessage mimeMessage = this.emailSender.createMimeMessage();
        final MimeMessageHelper email;

        try {
            email = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            email.setFrom("sweet.stock.contato@gmail.com");
            email.setTo(to);
            email.setSubject(subject);

            final String htmlContent = this.htmlTemplateEngine.process(template, ctx);

            email.setText(htmlContent, true);

            emailSender.send(mimeMessage);

            return true;
        } catch (MailException | MessagingException e) {
            return false;
        }
    }

}
