package filter;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.FrameTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 链接提取器
 *
 * @author panda
 * @date 2017/10/28
 */
public class LinkExtractor {

    private static final Logger logger = LoggerFactory.getLogger(LinkExtractor.class);

    public static List<String> extractLinks(String body, LinkFilter filter) {

        List<String> linkList = new ArrayList<String>();

        try {

            Parser parser = new Parser(body);

            OrFilter linkFilter = new OrFilter(
                    new NodeClassFilter[]{
                            new NodeClassFilter(LinkTag.class),
                            new NodeClassFilter(FrameTag.class)
                    }
            );
            NodeList nodeList = parser.extractAllNodesThatMatch(linkFilter);

            if (nodeList != null) {
                logger.info("发现链接个数：" + nodeList.size());
            }

            for (int i = 0; i < nodeList.size(); i++) {

                Node node = nodeList.elementAt(i);
                String linkUrl;

                if (node instanceof LinkTag) {
                    LinkTag link = (LinkTag) node;
                    linkUrl = link.getAttribute("HREF");
                } else {
                    FrameTag frame = (FrameTag) node;
                    linkUrl = frame.getFrameLocation();
                }

                // 如果有自定义过滤器，则增加自定义过滤条件
                if (filter != null && linkUrl != null) {
                    if (!filter.accept(linkUrl)) {
                        linkUrl = null;
                    }
                }

                if (linkUrl == null || "".equals(linkUrl) || "#".equals(linkUrl) || linkUrl.startsWith("javascript")) {
                    continue;
                }

                // 防止链接重复
                if (!linkList.contains(linkUrl)) {
                    linkList.add(linkUrl);
                }
            }

            if (linkList != null) {
                logger.info("提取链接个数：" + linkList.size());
            }

        } catch (Exception e) {
            logger.error("提取链接异常：", e);
        }

        return linkList;
    }

}
