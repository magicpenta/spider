package filter;

import org.junit.Test;
import service.DownloadService;
import java.util.List;

/**
 * htmlParser单元测试
 *
 * @author panda
 * @date 2017/11/23
 */
public class LinkExtractorTest {

    @Test
    public void testExtractLinksWithoutFilter() {
        String url = "http://www.importnew.com/";
        String body = new DownloadService().getResponseBody(url);
        List<String> linkList = LinkExtractor.extractLinks(url, body, null);
        for (int i = 0; i < linkList.size(); i++) {
            System.out.println("linkUrl:" + linkList.get(i));
        }
    }

    @Test
    public void testExtractLinksWithFilter() {
        String url = "http://sm.xmu.edu.cn/";
        String body = new DownloadService().getResponseBody(url);
        LinkFilter filter = new LinkFilter() {
            public boolean accept(String link) {
                return link.contains("http://sm.xmu.edu.cn/");
            }
        };
        List<String> linkList = LinkExtractor.extractLinks(url, body, filter);
        for (int i = 0; i < linkList.size(); i++) {
            System.out.println("linkUrl:" + linkList.get(i));
        }
    }

}
