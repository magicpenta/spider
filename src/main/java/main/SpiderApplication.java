package main;

import entity.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.DownloadService;
import service.ParseService;

/**
 * 应用入口
 *
 * @author panda
 * @date 2017/10/28
 */
public class SpiderApplication {

    public static void main(String[] args) {
        DownloadService downloadService = new DownloadService();
        ParseService parseService = new ParseService();

        Task task = new Task();
        task.setUrl("http://news.xinhuanet.com/world/2017-11/12/c_1121941191.htm");

        String responseBody = downloadService.getResponseBody(task);
        if (responseBody != null) {
            parseService.parse(responseBody);
        }
    }

}
