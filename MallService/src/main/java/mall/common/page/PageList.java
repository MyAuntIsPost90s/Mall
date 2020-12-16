package mall.common.page;

import java.io.Serializable;
import java.util.List;

/**
 * 分页集合对象
 *
 * @author caich
 **/
public class PageList<T> implements Serializable {

    private Long total;

    private List<T> rows;

    public PageList(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
