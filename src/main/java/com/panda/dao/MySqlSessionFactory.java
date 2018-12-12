package com.panda.dao;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * sqlsessionFactory
 *
 * @author panda
 * @date 2018/12/10
 */
public class MySqlSessionFactory {

    private static final Logger logger = LoggerFactory.getLogger(MySqlSessionFactory.class);

    private static final String resource = "mybatis-config.xml";

    private static SqlSessionFactory sqlSessionFactory;

    static {
        // 加载配置文件
        InputStream in = MySqlSessionFactory.class.getClassLoader().getResourceAsStream(resource);
        // 创建SqlSessionFactory实例
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
    }

    public static Configuration getConfiguration() {
        return sqlSessionFactory.getConfiguration();
    }

    public static SqlSession getSqlSession() {
        return (SqlSession) Proxy.newProxyInstance(SqlSessionFactory.class.getClassLoader(),
                new Class[]{SqlSession.class},
                new SqlSessionInterceptor());
    }

    private static class SqlSessionInterceptor implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            Object object = null;
            try {
                object = method.invoke(sqlSession, args);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } finally {
                sqlSession.close();
            }
            return object;
        }
    }
}
