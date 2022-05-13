package com.stock.sweet.sweetstockapi.service.mail;

import com.stock.sweet.sweetstockapi.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    private static final String TEMPLATE_NAME = "email_codigo_empresa";

    @Autowired
    private Environment environment;

    @Autowired
    private TemplateEngine htmlTemplateEngine;


    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(Email emailModel) throws MessagingException {
        final MimeMessage mimeMessage = this.emailSender.createMimeMessage();
        final MimeMessageHelper email;

        try {
            email = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            email.setFrom(emailModel.getEmailFrom());
            email.setTo(emailModel.getEmailTo());
            email.setSubject(emailModel.getSubject());

            final Context ctx = new Context(LocaleContextHolder.getLocale());
            ctx.setVariable("company", "Pudinho");

            final String htmlContent = this.htmlTemplateEngine.process(TEMPLATE_NAME, ctx);

            email.setText(htmlContent, true);

            emailSender.send(mimeMessage);

        } catch (MessagingException e) {
            System.out.println("Falha ao enviar email" + e);

        }
    }


}
