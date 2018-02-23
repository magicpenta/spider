package service;

import entity.Task;
import org.junit.Test;

/**
 * 下载服务单元测试
 *
 * @author panda
 * @date 2018/2/4
 */
public class DownloadServiceTest {

    @Test
    public void testGetResponseBody() {
        Task task = new Task();
        task.setId(1);
        task.setUrl("https://www.baidu.com");
        task.setStatus(0);
        String responseBody = DownloadService.getInstance().getResponseBody(task);
        assert responseBody != null;
    }
}
