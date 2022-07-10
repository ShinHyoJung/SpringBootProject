package com.project.springboot.practice.controller;


import com.project.springboot.practice.config.CodeConfig;
import com.project.springboot.practice.dto.ValidateCode;
import com.project.springboot.practice.dto.ValidateCodeResponse;
import com.project.springboot.practice.service.EmailService;
import com.project.springboot.practice.service.SmsService;
import com.project.springboot.practice.util.QrCodeUtil;
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
            return new ValidateCodeResponse("SUCCESS","인증 성공했습니다.");
        } else {
            return new ValidateCodeResponse("FAIL", "인증 실패했습니다.");
        }
    }

    @ResponseBody
    @RequestMapping("/qrCode")
    public String QrCode(Model model) {
        String codeInformation = "http://www.naver.com";
        String img = QrCodeUtil.getQrCodeImage(codeInformation, 200, 200);
        model.addAttribute("img", img);
        return "/skill/qrCode";
    }
}
