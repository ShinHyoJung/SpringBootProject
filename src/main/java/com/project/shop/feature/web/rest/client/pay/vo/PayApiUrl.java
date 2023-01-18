package com.project.shop.feature.web.rest.client.pay.vo;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * Company: NANDSOFT
 * User: sljh1020
 * Date: 2023-01-18
 * Comments:
 * </pre>
 */
@Component
@Getter
public class PayApiUrl {
    @Value("${spring.pay.url.getToken}")
    private String getTokenUrl;

    @Value("${spring.pay.url.paymentInfo}")
    private String paymentInfoUrl;

    @Value("${spring.pay.url.paymentCancel}")
    private String paymentCancelUrl;
}
