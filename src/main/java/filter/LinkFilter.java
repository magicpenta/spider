package filter;

/**
 * 链接过滤接口
 *
 * @author panda
 * @date 2017/11/28
 */
public interface LinkFilter {

    boolean accept(String link);

}
