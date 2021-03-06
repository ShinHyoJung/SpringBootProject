package com.project.springboot.practice.springbootproject.controller;


import com.project.springboot.practice.springbootproject.config.CodeConfig;
import com.project.springboot.practice.springbootproject.dto.ValidateCode;
import com.project.springboot.practice.springbootproject.dto.ValidateCodeResponse;
import com.project.springboot.practice.springbootproject.service.EmailService;
import com.project.springboot.practice.springbootproject.service.SmsService;
import com.project.springboot.practice.springbootproject.util.QrCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class ValidateController {

    @Autowired
    SmsService smsService;
    @Autowired
    EmailService emailService;

    @ResponseBody
    @RequestMapping("/sms")
    public void postSms(HttpSession httpSession, String phoneNum) {
        String code = CodeConfig.makeCode();
        smsService.sendNumber(phoneNum, code);
        httpSession.setAttribute("code", code);
        httpSession.setMaxInactiveInterval(180);
    }

    @RequestMapping("/smsPage")
    public String getSms() {
        return "/verification/sms";
    }

    @ResponseBody
    @RequestMapping("/email")
    public void postEmail(HttpSession httpSession, String email) {
        String code = CodeConfig.makeCode();
        httpSession.setAttribute("code", code);
        emailService.sendMail(email, code);
        httpSession.setMaxInactiveInterval(180);
    }

    @RequestMapping("/emailPage")
    public String getEmail() {
        return "/verification/email";
    }

    @ResponseBody
    @RequestMapping("/validate")
    public ValidateCodeResponse validateCode(@RequestBody ValidateCode validateCode, HttpSession httpSession) {
        String code = httpSession.getAttribute("code").toString();
        String validate = validateCode.getCode();

        if(code.equals(validate)) {
            return new ValidateCodeResponse("success","?????? ??????????????????.");
        } else {
            return new ValidateCodeResponse("fail", "?????? ??????????????????.");
        }
    }

    @RequestMapping("/qrCode")
    public String QrCode(Model model) {
        String codeInformation = "http://www.naver.com";
        String img = QrCodeUtil.getQrCodeImage(codeInformation, 200, 200);
        model.addAttribute("img", img);
        return "/verification/qrCode";
    }
}
