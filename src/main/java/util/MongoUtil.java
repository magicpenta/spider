package util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
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

    private static String DEFAULT_COLLECTION;

    static {
        Properties prop = new Properties();
        try {
            prop.load(MongoUtil.class.getClassLoader().getResourceAsStream(CONFIG_PATH));
            MongoClient mongoClient = new MongoClient(prop.getProperty("mongodb_host"), Integer.valueOf(prop.getProperty("mongodb_port")));
            database = mongoClient.getDatabase(prop.getProperty("database_name"));
            DEFAULT_COLLECTION = prop.getProperty("default_collection", "test");
        } catch (IOException e) {
            logger.error("加载 MongoDB 配置文件异常:", e);
        }
    }

    public static void insertOne(Document document) {
        MongoUtil.insertOne(DEFAULT_COLLECTION, document);
    }

    public static void insertOne(String collectionName, Document document) {
        MongoCollection<Document> mongoCollection = database.getCollection(collectionName);
        mongoCollection.insertOne(document);
    }

    public static Document findFirst(String fieldName, String value) {
        return database.getCollection(DEFAULT_COLLECTION).find(Filters.eq(fieldName, value)).first();
    }

    public static Document findFirst(String collectionName, String fieldName, String value) {
        return database.getCollection(collectionName).find(Filters.eq(fieldName, value)).first();
    }

}
