package filter;

/**
 * 合并过滤
 *
 * @author panda
 * @date 2017/12/04
 */
public class AndFilter implements LinkFilter {

    private LinkFilter[] filters;

    public AndFilter(LinkFilter[] filters) {
        this.filters = filters;
    }

    public boolean accept(String link) {
        for (int i = 0; i < filters.length; i++) {
            if (filters[i].accept(link) == false) {
                return false;
            }
        }
        return true;
    }

}
