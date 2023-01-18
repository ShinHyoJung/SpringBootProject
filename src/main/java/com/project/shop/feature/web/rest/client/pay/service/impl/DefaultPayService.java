package com.project.shop.feature.web.rest.client.pay.service.impl;

import com.google.gson.Gson;
import com.project.shop.feature.web.rest.client.pay.dto.GetPaymentInfo;
import com.project.shop.feature.web.rest.client.pay.dto.PostPayGetToken;
import com.project.shop.feature.web.rest.client.pay.dto.PostPaymentCancel;
import com.project.shop.feature.web.rest.client.pay.service.PayService;
import com.project.shop.feature.web.rest.client.pay.vo.PayApiMeta;
import com.project.shop.feature.web.rest.client.pay.vo.PayApiUrl;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service("PayService")
@RequiredArgsConstructor
public class DefaultPayService implements PayService {
    private final PayApiMeta payApiMeta;
    private final PayApiUrl payApiUrl;
    private final RestTemplate restTemplate;

    @Override
    public String getToken() throws ParseException {
        String apiKey = payApiMeta.getApiKey();
        String apiSecretKey = payApiMeta.getApiSecretKey();

        PostPayGetToken postPayGetToken = new PostPayGetToken();
        postPayGetToken.setImp_key(apiKey);
        postPayGetToken.setImp_secret(apiSecretKey);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<HashMap> responseEntity = restTemplate.exchange(payApiUrl.getGetTokenUrl(), HttpMethod.POST,
                new HttpEntity<>(postPayGetToken, headers), HashMap.class);

        JSONObject jsonObject = new JSONObject(responseEntity.getBody());
        Map response = (Map) jsonObject.get("response");

        String token = response.get("access_token").toString();

        return token;
    }

    @Override
    public int paymentInfo(String impUid, String accessToken) throws IOException, ParseException {
        String url = payApiUrl.getPaymentInfoUrl() + impUid;
        GetPaymentInfo getPaymentInfo = new GetPaymentInfo();
        getPaymentInfo.setAuthorization(accessToken);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", accessToken);

        ResponseEntity<HashMap> responseEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(getPaymentInfo, headers), HashMap.class);
        JSONObject jsonObject = new JSONObject(responseEntity.getBody());
        Map response = (Map)jsonObject.get("response");

        int amount = (int)response.get("amount");

        return amount;
    }

    @Override
    public void paymentCancel(String impUid, String accessToken, int amount, String reason) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", "application/json");
        headers.set("Authorization", accessToken);

        PostPaymentCancel postPaymentCancel = new PostPaymentCancel();
        postPaymentCancel.setReason(reason);
        postPaymentCancel.setImp_uid(impUid);
        postPaymentCancel.setAmount(amount);
        postPaymentCancel.setChecksum(amount);

        ResponseEntity<HashMap> responseEntity = restTemplate.exchange(payApiUrl.getPaymentCancelUrl(), HttpMethod.POST,
                new HttpEntity<>(postPaymentCancel, headers), HashMap.class);
    }
}
