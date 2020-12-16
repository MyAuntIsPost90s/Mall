package mall.base.dao;

import org.apache.ibatis.annotations.Param;

import com.ch.common.mybatis.BaseMapper;
import mall.base.model.Goods;

import java.util.List;

public interface GoodsMapper extends BaseMapper<Goods> {

	List<Goods> searchWithPage(@Param("goodsname") String goodsname, @Param("goodskindid") String goodskindid,
							   @Param("orderType") int orderType);
}