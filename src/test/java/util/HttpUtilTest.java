package util;

import entity.HttpParams;
import entity.Proxy;
import org.apache.http.Header;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.message.BasicHeader;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * HTTP工具类单元测试
 *
 * @author panda
 * @date 2017/11/23
 */
public class HttpUtilTest {

    @Test
    public void testExecuteGetRequest() {
        HttpParams httpParams = HttpParams.getBuilder()
                .setUrl("http://www.baidu.com")
                .build();
        String responseBody = HttpUtil.executeGetRequest(httpParams);
        assert responseBody != null;
        System.out.println(responseBody);
    }

    @Test
    public void testExecuteGetRequestByCookie() {
        Map<String, String> headerMap = new HashMap<String, String>();
        String cookieStr = "SINAGLOBAL=305612958174.8368.1505465560500; YF-Ugrow-G0=1eba44dbebf62c27ae66e16d40e02964; login_sid_t=86b5dbaf5f6c2bdb719f145fbb49199a; YF-V5-G0=f59276155f879836eb028d7dcd01d03c; _s_tentry=passport.weibo.com; Apache=3158529720063.739.1511424723756; ULV=1511424723767:2:1:1:3158529720063.739.1511424723756:1505465560611; cross_origin_proto=SSL; WBStorage=82ca67f06fa80da0|undefined; UOR=,,www.baidu.com; YF-Page-G0=4c69ce1a525bc6d50f53626826cd2894; wb_cusLike_3655689037=N; SSOLoginState=1511432508; SUB=_2A253EtFvDeThGeRO6FQU8SzMyjiIHXVUZkWnrDV8PUNbmtBeLVKkkW9NHetkT06VwG7igGGgdevPqJ79KTiOqbg_; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WhdNUhWdBzefeF2wLJAWOf95JpX5KzhUgL.Foz7e0qfeKz7eKB2dJLoI0YLxK-L12qL1hnLxKnLBoBLBo2LxK-L12qL1hnLxKnLBK5LBKzLxKML1-2L1hBLxK-LBKBLBKMLxK-L12qL1KBt; SUHB=0jBZB2VhiECR0R; ALF=1542968507; wvr=6; wb_cusLike_2036512014=N";
        headerMap.put("cookie", cookieStr);

        HttpParams httpParams = HttpParams.getBuilder()
                .setUrl("https://weibo.com/u/2036512014")
                .setCharset("gbk")
                .setNeedCookie(true)
                .setHeaderMap(headerMap)
                .build();

        String responseBody = HttpUtil.executeGetRequest(httpParams);
        assert responseBody != null;
        System.out.println(responseBody);
    }

    @Test
    public void testExecuteGetRequestByProxy() {
        Proxy proxy = new Proxy();
        proxy.setProxyIp("127.0.0.1");
        proxy.setProxyPort(443);
        proxy.setProxyUserName("pico");
        proxy.setProxyPassword("pico2009server");

        HttpParams httpParams = HttpParams.getBuilder()
                .setUrl("https://www.google.com/")
                .setNeedProxy(true)
                .setProxy(proxy)
                .build();

        String responseBody = HttpUtil.executeGetRequest(httpParams);
        assert responseBody != null;
        System.out.println(responseBody);
    }

    @Test
    public void testExecutePostRequest() {
        Map<String, String> formParams = new HashMap<String, String>();
        formParams.put("Db", "introduction");
        formParams.put("valuepath", "0%7C1");
        formParams.put("find_count", "0");
        formParams.put("kwd", "test");

        HttpParams httpParams = HttpParams.getBuilder()
                .setUrl("http://www.pkulaw.cn/doCluster.ashx")
                .setFormParams(formParams)
                .build();

        String responseBody = HttpUtil.executePostRequest(httpParams);
        assert responseBody != null;
        System.out.println(responseBody);
    }

}
