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
public class PostPayGetToken {
    private String imp_key;
    private String imp_secret;
}
