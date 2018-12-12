package com.panda.util;

import org.apache.commons.lang3.StringUtils;

/**
 * spider
 *
 * @author panda
 * @date 2018/11/10
 */
public class ParserUtil {

    public static String parseEmoji(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        return s.replaceAll("[\\ud83c\\udc00-\\ud83c\\udfff]|[\\ud83d\\udc00-\\ud83d\\udfff]|[\\u2600-\\u27ff]", "");
    }
}
