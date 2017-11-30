package demo;

import com.google.gson.Gson;
import util.FileUtils;

/**
 * Gson demo
 *
 * @author panda
 * @date 2017/11/30
 */
public class GsonDemo {

    public static void main(String[] args) {
        String jsonString = FileUtils.readFile("json.txt", "UTF-8");

        Gson gson = new Gson();
        User user = gson.fromJson(jsonString, User.class);
        System.out.println("name:" + user.name);
        System.out.println("age:" + user.age);
    }

    private class User {
        private String name;
        private int age;
    }

}
