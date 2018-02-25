package service;

import org.junit.Test;

/**
 * MQ 监控服务单元测试
 *
 * @author panda
 * @date 2018/2/25
 */
public class ActiveJmxServiceTest {

    @Test
    public void testActiveJmxService() {
        ActiveJmxService service = ActiveJmxService.getInstance();
        Long queueSize = service.getQueueSize("test");
        assert queueSize != null;
    }
}
