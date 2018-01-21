package util;

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
        Document document = new Document("name", "panda");
        MongoUtil.insertOne(document);
        document = MongoUtil.findFirst("name", "panda");
        assert document != null;
        assert document.getString("name").equals("panda");
    }

}
