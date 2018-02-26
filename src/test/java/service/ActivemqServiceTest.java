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
        ActivemqService service = new ActivemqService();
        String s1 = "test";
        service.sendMessage("test", s1);
        service.createConsumer("test");
        String s2 = service.getMessage();
        service.close();

        assert s1.equals(s2);
    }

}
