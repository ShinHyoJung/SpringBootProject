package com.project.springboot.practice.springbootproject.service;

import net.nurigo.java_sdk.api.Message;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service("SmsServiceImpl")
public class SmsServiceImpl implements SmsService {

    @Override
    public void sendNumber(String phoneNum, String cerNum)
    {
        String api_key = "NCSLMYYWCQFCY8DE";
        String api_secret = "TKLI5VCORIL04CTISNAI1ZUDLKFBPK7K";
        Message coolsms = new Message(api_key, api_secret);

        HashMap<String, String> params = new HashMap<>();
        params.put("to", phoneNum);
        params.put("from", "01052386416");
        params.put("type", "SMS");
        params.put("text", "인증번호는 [" + cerNum + "] 입니다.");

        try
        {
            coolsms.send(params);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
