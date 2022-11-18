package com.project.shop.feature.authentication.method.mail.service.impl;

import com.project.shop.feature.authentication.method.mail.service.MailService;
import com.project.shop.feature.authentication.method.mail.vo.Mail;
import com.project.shop.feature.authentication.method.mail.vo.MailMeta;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DefaultMailService implements MailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;
    private final MailMeta mailMeta;
    @Override
    public void send(Mail mail) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(mailMeta.getFrom());
        helper.setSubject(mail.getSubject());
        helper.setTo(mail.getTo());
        helper.setText(mail.getText());

        Context context = new Context();
        Map<String ,Object> variables = mail.getVariables();
        context.setVariables(variables);
        String html = templateEngine.process(mail.getTemplateName(), context);

        helper.setText(html, true);
        mailSender.send(message);
    }
}
