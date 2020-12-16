package mall.base.dao;

import com.ch.common.mybatis.BaseMapper;
import mall.base.model.Ordergoods;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OrdergoodsMapper extends BaseMapper<Ordergoods> {

    /**
     * 获取各项值的统计数据
     *
     * @param userid
     * @param ordertime
     * @param type
     * @return
     */
    Ordergoods getItemSum(@Param("userid") String userid, @Param("ordertime") Date ordertime,
                          @Param("type") String type, @Param("orderType") Integer orderType);

    /**
     * 获取每种商品统计
     *
     * @param userid
     * @return
     */
    List<Ordergoods> list4count(@Param("userid") String userid, @Param("ordergoodsname") String ordergoodsname);
}