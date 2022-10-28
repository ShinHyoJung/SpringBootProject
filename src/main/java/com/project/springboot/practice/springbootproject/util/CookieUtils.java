package com.project.springboot.practice.springbootproject.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CookieUtils {
    public static Map<String, String> toNameValueMap(Cookie[] cookies) {
        Map<String, String> map = new HashMap<>();

        if (cookies != null) {
            for (Cookie c : cookies) {
                map.put(c.getName(), c.getValue());
            }
        }

        return map;
    }

    public static void setSessionCookie(HttpServletResponse res, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        res.addCookie(cookie);
    }

    public static void setCookie(HttpServletResponse res, String name, String value, int expiredSeconds) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(expiredSeconds);
        cookie.setPath("/");
        res.addCookie(cookie);
    }

    public static String getCookie(HttpServletRequest req, String cookieName) {
        Cookie[] cookies=req.getCookies();
        if (cookies != null){
            for (Cookie c : cookies) {
                String name = c.getName();
                String value = c.getValue();
                if (name.equals(cookieName)) {
                    return value;
                }
            }
        }
        return null;
    }

}
