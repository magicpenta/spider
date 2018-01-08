package dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * 基础 dao
 *
 * @author panda
 * @date 2018/1/5
 */
public class BaseDao {

    private static final String resource = "mybatis-config.xml";

    private static SqlSession sqlSession;

    static {
        // 加载配置文件
        InputStream in = BaseDao.class.getClassLoader().getResourceAsStream(resource);

        // 创建SqlSessionFactory实例
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);

        // 获取session
        sqlSession = sessionFactory.openSession(true);
    }

    public static SqlSession getSqlSession() {
        return sqlSession;
    }

}
