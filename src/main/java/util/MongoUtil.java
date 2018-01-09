package util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * mongodb
 *
 * @author panda
 * @date 2018/1/9
 */
public class MongoUtil {

    private static final Logger logger = LoggerFactory.getLogger(MongoUtil.class);

    private static final String CONFIG_PATH = "mongodb.properties";

    private static MongoDatabase database;

    static {
        Properties prop = new Properties();
        try {
            prop.load(MongoUtil.class.getClassLoader().getResourceAsStream(CONFIG_PATH));
            MongoClient mongoClient = new MongoClient(prop.getProperty("mongodb_host"), Integer.valueOf(prop.getProperty("mongodb_port")));
            database = mongoClient.getDatabase(prop.getProperty("database_name"));
        } catch (IOException e) {
            logger.error("加载配置文件异常:", e);
        }
    }

    public static MongoCollection<Document> getCollection(String collectionName) {
        return database.getCollection(collectionName);
    }

}
