package com.project.shop.feature.member.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * Company: NANDSOFT
 * User: sljh1020
 * Date: 2023-02-21
 * Comments:
 * </pre>
 */
@Data
public class PostCheckLoggedInResponse {
    private String code;
    private String message;
}
