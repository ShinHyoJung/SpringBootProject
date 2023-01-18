package com.project.shop.feature.web.rest.client.pay.service;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface PayService {

    String getToken() throws ParseException;

    int paymentInfo(String impUid, String accessToken) throws IOException, ParseException;

    void paymentCancel(String impUid, String accessToken, int amount, String reason);
}
