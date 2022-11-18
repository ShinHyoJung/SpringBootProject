package com.project.shop.feature.authentication.controller;

import com.project.shop.feature.authentication.dto.PostCheckAuth;
import com.project.shop.feature.authentication.dto.PostCheckAuthResponse;
import com.project.shop.feature.authentication.method.mail.dto.PostSendMail;
import com.project.shop.feature.authentication.method.mail.dto.PostSendMailResponse;
import com.project.shop.feature.authentication.method.mail.service.MailService;
import com.project.shop.feature.authentication.method.mail.vo.Mail;
import com.project.shop.feature.authentication.method.sms.dto.PostSendSms;
import com.project.shop.feature.authentication.method.sms.dto.PostSendSmsResponse;
import com.project.shop.feature.authentication.method.sms.service.SmsService;
import com.project.shop.feature.authentication.method.sms.vo.Sms;
import com.project.shop.feature.authentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/authentication")
public class AuthenticationController {

    private final MailService mailService;
    private final SmsService smsService;
    private final AuthenticationService authenticationService;

    @ResponseBody
    @PostMapping("/send-mail")
    public PostSendMailResponse sendMail(@RequestBody PostSendMail postSendMail, HttpSession session) throws MessagingException {
        PostSendMailResponse pageResponse = new PostSendMailResponse();
        String authCode = authenticationService.createAuthCode();
        session.setAttribute("authCode", authCode);

        Map<String, Object> variables = new HashMap<>();
        variables.put("authCode", authCode);

        Mail mail = new Mail();
        mail.setSubject(postSendMail.getSubject());
        mail.setTemplateName(postSendMail.getTemplateName());
        mail.setText(postSendMail.getText());
        mail.setTo(postSendMail.getMail());
        mail.setVariables(variables);

        try {
            mailService.send(mail);
            pageResponse.setCode("0000");
            pageResponse.setMessage("인증번호가 전송되었습니다.");
        } catch (Exception e) {
            pageResponse.setCode("0001");
            pageResponse.setMessage("인증번호 전송을 실패하였습니다.");
        }
        return pageResponse;
    }

    @ResponseBody
    @PostMapping("/send-sms")
    public PostSendSmsResponse sendSms(@RequestBody PostSendSms postSendSms, HttpSession session) throws CoolsmsException {
        PostSendSmsResponse pageResponse = new PostSendSmsResponse();
        String mobile = postSendSms.getMobile();

        String authCode = authenticationService.createAuthCode();
        session.setAttribute("authCode", authCode);

        Sms sms = new Sms();
        sms.setTo(mobile);
        sms.setType(postSendSms.getType());
        sms.setText("인증번호:" + authCode);

        try {
            smsService.send(sms);
            pageResponse.setCode("0000");
            pageResponse.setMessage("인증번호가 전송되었습니다.");
        } catch (Exception e) {
            pageResponse.setCode("0001");
            pageResponse.setMessage("인증번호 전송을 실패하였습니다.");
        }
        return pageResponse;
    }

    @ResponseBody
    @PostMapping("/check-auth")
    public PostCheckAuthResponse checkAuth(@RequestBody PostCheckAuth postCheckAuth, HttpSession session) {
        PostCheckAuthResponse pageResponse = new PostCheckAuthResponse();

        String authCode = session.getAttribute("authCode").toString();
        String code = postCheckAuth.getCode();

        if(code.equals(authCode)) {
            session.removeAttribute("authCode");
            pageResponse.setCode("0000");
            pageResponse.setMessage("인증 성공했습니다.");
        } else {
            pageResponse.setCode("0001");
            pageResponse.setMessage("인증 실패했습니다 다시 시도해주세요.");
        }
        return pageResponse;
    }
}
