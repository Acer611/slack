package com.style.slack.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 邮件校验工具类
 * @author Gaofei
 * @date 2018-07-27
 */
public class EmailUtil {

    public static boolean emailFormat(String email) {
        boolean tag = true;
        final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(email);
        if (!mat.find()) {
            tag = false;
        }
        return tag;
    }
}
