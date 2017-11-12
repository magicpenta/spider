package main;

import util.FileUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式 demo
 *
 * @author panda
 * @date 2017/11/12
 */
public class RegexDemo {

    private static Pattern DATE_PATTERN = Pattern.compile("<p>(.*?)</p>");

    public static void main(String[] args) {
        String htmlBody = FileUtils.readFile("test.html", "UTF-8");
        System.out.println(htmlBody);

        Matcher matcher = DATE_PATTERN.matcher(htmlBody);

        if (matcher.find()) {
            String[] s = new String[matcher.groupCount() + 1];
            for (int i = 0; i <= matcher.groupCount(); i++) {
                s[i] = matcher.group(i);
            }
            System.out.println(s[1]);
        }
    }

}
