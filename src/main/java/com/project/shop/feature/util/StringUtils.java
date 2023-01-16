package com.project.shop.feature.util;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * Company: NANDSOFT
 * User: sljh1020
 * Date: 2023-01-16
 * Comments:
 * </pre>
 */
public class StringUtils {

    public static boolean isNullOrEmpty(String s) {
        return (s == null || s.length() == 0);
    }

    public static boolean isNotEmpty(String s) {
        return (s != null && s.length() > 0);
    }
}
