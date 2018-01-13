package dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.List;

/**
 * 基础 dao
 *
 * @author panda
 * @date 2018/1/5
 */
public class BaseDao {

    private static final Logger logger = LoggerFactory.getLogger(BaseDao.class);

    private static final String resource = "mybatis-config.xml";

    private static SqlSessionFactory sqlSessionFactory;

    static {
        // 加载配置文件
        InputStream in = BaseDao.class.getClassLoader().getResourceAsStream(resource);

        // 创建SqlSessionFactory实例
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
    }

    public static <T> T selectOne(String statement, Object parameter) {
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession(true);
            return sqlSession.selectOne(statement, parameter);
        } finally {
            sqlSession.close();
        }
    }

    public static <E> List<E> selectList(String statement, Object parameter) {
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession(true);
            return sqlSession.selectList(statement, parameter);
        } finally {
            sqlSession.close();
        }
    }

}
