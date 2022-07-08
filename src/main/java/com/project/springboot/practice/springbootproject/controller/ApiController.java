package com.project.springboot.practice.springbootproject.controller;


import com.project.springboot.practice.springbootproject.dto.CheckUser;
import com.project.springboot.practice.springbootproject.dto.CheckUserResponse;
import com.project.springboot.practice.springbootproject.util.QrCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class ApiController {

    @RequestMapping("/apiPage")
    public String getApi() {

        return "check/api";
    }

    @ResponseBody
    @RequestMapping("/api")
    public CheckUserResponse postApi(@RequestBody CheckUser checkUser) {

            String idx = String.valueOf(checkUser.getIdx());
            String url = "http://localhost:8080/api/checkUser";
            RestTemplate restTemplate = new RestTemplate();
            /*
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<String> request = new HttpEntity<>(idx, httpHeaders);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
             System.out.println(response.getBody());
            */
            String username = restTemplate.postForObject(url, idx, String.class);
            System.out.println(username);
            return new CheckUserResponse(username);
        /*
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://localhost:8080/api/checkUser");
        httpPost.setEntity(new StringEntity(idx, HTTP.UTF_8));
        try {
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResponseHandler<String> handler = new BasicResponseHandler();
                String body = handler.handleResponse(response);
                System.out.println("[RESPONSE] requestHttpJson() " + body);
            } else {
                System.out.println("response is error : " + response.getStatusLine().getStatusCode());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    */
        }

    @ResponseBody
    @RequestMapping("/postQrCode")
    public String postQrCode() {
        String codeInformation = "http://www.naver.com";
        String img = QrCodeUtil.getQrCodeImage(codeInformation, 200, 200);
        return img;
    }
}
