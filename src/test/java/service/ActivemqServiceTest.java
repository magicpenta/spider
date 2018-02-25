package service;

import org.junit.Test;

/**
 * MQ 服务单元测试
 *
 * @author panda
 * @date 2018/2/24
 */
public class ActivemqServiceTest {

    @Test
    public void testActivemqService() {
        ActivemqService service = ActivemqService.getInstance();
        String s1 = "test";
        service.sendMessage("test", s1);
        String s2 = service.getMessage("test");
        service.close();

        assert s1.equals(s2);
    }

}
