package plugins;

import entity.Task;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * XMU 插件
 *
 * @author panda
 * @date 2017/12/01
 */
@Plugin(value = "sm.xmu.edu.cn")
public class XmuPlugin extends AbstractPlugin {

    private static final Logger logger = LoggerFactory.getLogger(XmuPlugin.class);

    public XmuPlugin(Task task) {
        super(task);
    }

    @Override
    public void run() {
        logger.info("我开始跑啦！！！");
        String body = downloadService.getResponseBody(task);
        if (StringUtils.isNotEmpty(body)) {
            if (isDetailPage(task.getUrl())) {
                logger.info("开始解析详情页！！！");
                parseContent(body);
            } else {
                logger.info("开始解析列表页！！！");
                extractPageLinks(body);
            }
        }
    }

    @Override
    public void parseContent(String body) {
        logger.info("嘻嘻嘻！！！");
    }

    @Override
    public boolean isDetailPage(String url) {
        return url.contains("content");
    }

}
