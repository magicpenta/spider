package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.FileUtils;

/**
 * jsoup demo
 *
 * @author panda
 * @date 2017/11/12
 */
public class JsoupDemo {

    public static void main(String[] args) {
        String htmlBody = FileUtils.readFile("test.html", "UTF-8");
        System.out.println(htmlBody);

        Document doc;
        try {
            doc = Jsoup.parse(htmlBody);
            Elements elements = doc.getElementsByTag("P");
            for (int i = 0; i < elements.size(); i++) {
                Element element = elements.get(i);
                System.out.println(element.text());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
