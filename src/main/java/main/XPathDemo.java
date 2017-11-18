package main;

import org.apache.xpath.XPathAPI;
import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import util.FileUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

/**
 * XPath demo
 *
 * @author panda
 * @date 2017/11/18
 */
public class XPathDemo {

    public static void main(String[] args) {
        String htmlBody = FileUtils.readFile("test.html", "UTF-8");
        System.out.println(htmlBody);

        try {
            DOMParser parser = new DOMParser();
            parser.setFeature("http://xml.org/sax/features/namespaces", false);
            parser.setProperty(
                    "http://cyberneko.org/html/properties/default-encoding",
                    "UTF-8");

            ByteArrayInputStream in = new ByteArrayInputStream(htmlBody.getBytes());
            InputStreamReader reader = new InputStreamReader(in);
            InputSource source = new InputSource(reader);
            parser.parse(source);

            Document doc = parser.getDocument();
            Node node = XPathAPI.selectSingleNode(doc, ".//P");
            System.out.println(node.getTextContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
