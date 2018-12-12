package com.panda.service;

import com.panda.config.Constants;
import org.apache.activemq.broker.jmx.BrokerViewMBean;
import org.apache.activemq.broker.jmx.QueueViewMBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * MQ 监控服务
 *
 * @author panda
 * @date 2018/2/25
 */
public class ActiveJmxService {

    private static final Logger logger = LoggerFactory.getLogger(ActiveJmxService.class);

    private volatile static ActiveJmxService service;

    private static final String JMX_URL = Constants.JMX_URL;

    private static final String BROKER_NAME = Constants.BROKER_NAME;

    private Map<String, QueueViewMBean> queueMap = new HashMap<String, QueueViewMBean>(16);

    private ActiveJmxService() {
        initQueueMap();
    }

    public static ActiveJmxService getInstance() {
        if (service == null) {
            synchronized (ActiveJmxService.class) {
                if (service == null) {
                    service = new ActiveJmxService();
                }
            }
        }
        return service;
    }

    private void initQueueMap() {
        try {
            JMXServiceURL url = new JMXServiceURL(JMX_URL);
            JMXConnector connector = JMXConnectorFactory.connect(url, null);
            connector.connect();

            MBeanServerConnection connection = connector.getMBeanServerConnection();
            ObjectName objectName = new ObjectName(BROKER_NAME);
            BrokerViewMBean mBean = MBeanServerInvocationHandler
                    .newProxyInstance(connection, objectName, BrokerViewMBean.class, true);

            if (mBean != null) {
                for (ObjectName queueName : mBean.getQueues()) {
                    QueueViewMBean queueMBean = MBeanServerInvocationHandler
                            .newProxyInstance(connection, queueName, QueueViewMBean.class, true);
                    queueMap.put(queueMBean.getName(), queueMBean);
                }
            }
        } catch (Exception e) {
            logger.error("初始化 queueMap 异常:", e);
        }
    }

    public Long getQueueSize(String queueName) {
        Long queueSize = null;
        if (queueMap != null) {
            Iterator iterator = queueMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, QueueViewMBean> element = (Map.Entry<String, QueueViewMBean>) iterator.next();
                if (element.getKey().equals(queueName)) {
                    queueSize = element.getValue().getQueueSize();
                    break;
                }
            }
        }
        return queueSize;
    }
}
