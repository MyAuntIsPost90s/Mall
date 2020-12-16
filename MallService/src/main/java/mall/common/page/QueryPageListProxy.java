package mall.common.page;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.util.List;

/**
 * 查询分页列表代理
 *
 * @author caich
 **/
public class QueryPageListProxy {

    public interface Callback<T> {
        List<T> execute();
    }

    public static <T> PageList<T> query(Callback<T> callback, int page, int rows) {
        Page<T> tPage = PageHelper.startPage(page, rows);
        callback.execute();
        return new PageList<>(tPage.getTotal(), tPage.getResult());
    }

}
