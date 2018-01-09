package util;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.junit.Test;

/**
 * mongodb 测试类
 *
 * @author panda
 * @date 2018/1/9
 */
public class MongoUtilTest {

    @Test
    public void testInsertOne() {
        MongoCollection<Document> mongoCollection = MongoUtil.getCollection("col");
        Document document = new Document("name", "panda");
        mongoCollection.insertOne(document);
        document = mongoCollection.find(Filters.eq("name", "panda")).first();
        assert document != null;
        assert document.getString("name").equals("panda");
    }

}
