package plugins;

import entity.Task;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * spider
 *
 * @author panda
 * @date 2018/1/4
 */
@Plugin(value = "www.zhihu.com")
public class ZhihuPlugin extends AbstractPlugin {

    private static final Logger logger = LoggerFactory.getLogger(ZhihuPlugin.class);

    public ZhihuPlugin(Task task) {
        super(task);
    }

    @Override
    public void parseContent(String body) {
        logger.info(body);
    }

    @Override
    public boolean isDetailPage(String url) {
        return true;
    }
}
