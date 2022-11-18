package com.project.shop.feature.authentication.method.sms.service;

import com.project.shop.feature.authentication.method.sms.vo.Sms;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public interface SmsService {

    void send(Sms sms) throws CoolsmsException;
}
