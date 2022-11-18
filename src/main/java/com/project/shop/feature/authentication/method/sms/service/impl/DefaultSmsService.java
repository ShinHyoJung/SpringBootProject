package com.project.shop.feature.authentication.method.sms.service.impl;

import com.project.shop.feature.authentication.method.sms.service.SmsService;
import com.project.shop.feature.authentication.method.sms.vo.Sms;
import com.project.shop.feature.authentication.method.sms.vo.SmsMeta;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class DefaultSmsService implements SmsService {

    private final SmsMeta smsMeta;

    @Override
    public void send(Sms sms) throws CoolsmsException {
        String apiKey = smsMeta.getApiKey();
        String apiSecret = smsMeta.getApiSecret();

        Message coolsms = new Message(apiKey, apiSecret);

        HashMap<String, String> params = new HashMap<>();
        params.put("to", sms.getTo());
        params.put("from", smsMeta.getFrom());
        params.put("type", sms.getType());
        params.put("text", sms.getText());

        JSONObject obj = (JSONObject) coolsms.send(params);
        System.out.println(obj.toString());
    }
}
