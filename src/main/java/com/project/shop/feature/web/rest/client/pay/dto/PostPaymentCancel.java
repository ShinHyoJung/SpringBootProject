package com.project.shop.feature.web.rest.client.pay.dto;

import lombok.Data;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * Company: NANDSOFT
 * User: sljh1020
 * Date: 2023-01-18
 * Comments:
 * </pre>
 */
@Data
public class PostPaymentCancel {
    private String reason;
    private String imp_uid;
    private int amount;
    private int checksum;
}
