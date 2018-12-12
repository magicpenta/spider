package com.panda.temp;

import com.panda.dao.common.TaskDao;
import com.panda.entity.common.TaskDO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import com.panda.service.DownloadService;
import com.panda.util.CommonUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * spider
 *
 * @author panda
 * @date 2018/11/19
 */
public class TaobaoSellerTemp {

    public static void main(String[] args) throws IOException {
        List<TaskDO> taskList = new ArrayList<TaskDO>();
        String text = FileUtils.readFileToString(new File("C:\\Users\\37516\\Desktop\\final\\第二批店铺.txt"), "utf-8");
        String[] urlArray = text.split("\n");
        System.out.println(urlArray.length);
        for (String url : urlArray) {
            Long taskId = Long.valueOf(CommonUtil.match(url, "user_number_id=(\\d+)")[1]);
            String htmlBody = new DownloadService().getResponseBody(url);
            Document doc = Jsoup.parse(htmlBody);
            if (doc == null) {
                System.out.println(url);
                continue;
            }
            Element element = doc.select("span.shop-rank > a").first();
            if (element == null) {
                element = doc.select("input#dsr-ratelink").first();
            }
            if (element == null) {
                System.out.println("这个链接错啦~ " + url);
                System.exit(0);
            }
            String taskUrl = element.attr("href");
            if (StringUtils.isEmpty(taskUrl)) {
                taskUrl = element.attr("value");
            }
            if (StringUtils.isEmpty(taskUrl)) {
                System.out.println("这个链接错啦~ " + url);
                System.exit(0);
            }
            taskUrl = "common:" + taskUrl;
            String sellerName = doc.select("a.shop-name").text();
            TaskDO task = new TaskDO();
            task.setTaskId(taskId);
            task.setTaskName(sellerName);
            task.setUrl(taskUrl);
            task.setTaskStatus(0);
            taskList.add(task);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int result = TaskDao.getInstance().insertList(taskList);
        System.out.println(result);
    }
}
