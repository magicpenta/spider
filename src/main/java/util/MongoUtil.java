package util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.BsonDocument;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
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

    public static void insertOne(BsonDocument document) {
        MongoUtil.insertOne(DEFAULT_COLLECTION, document);
    }

    public static void insertOne(String collectionName, BsonDocument document) {
        try {
            MongoCollection<BsonDocument> mongoCollection = database.getCollection(collectionName, BsonDocument.class);
            mongoCollection.insertOne(document);
        } catch (Exception e) {
            logger.error("插入单条数据异常:", e);
        }
    }

    public static void insertList(List<BsonDocument> documents) {
        MongoUtil.insertList(DEFAULT_COLLECTION, documents);
    }

    public static void insertList(String collectionName, List<BsonDocument> documents) {
        try {
            MongoCollection<BsonDocument> mongoCollection = database.getCollection(collectionName, BsonDocument.class);
            mongoCollection.insertMany(documents);
        } catch (Exception e) {
            logger.error("批量插入数据异常:", e);
        }
    }

    public static BsonDocument findFirst(String fieldName, String value) {
        return database.getCollection(DEFAULT_COLLECTION, BsonDocument.class).find(Filters.eq(fieldName, value)).first();
    }

    public static BsonDocument findFirst(String collectionName, String fieldName, String value) {
        return database.getCollection(collectionName, BsonDocument.class).find(Filters.eq(fieldName, value)).first();
    }

}
