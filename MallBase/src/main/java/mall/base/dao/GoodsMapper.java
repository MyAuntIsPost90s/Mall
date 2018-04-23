package mall.base.dao;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import lingshi.mybaties.mapperextend.BaseMapper;
import mall.base.model.Goods;

public interface GoodsMapper extends BaseMapper<Goods> {

	PageList<Goods> searchWithPage(@Param("goodsname") String goodsname, @Param("goodskindid") String goodskindid,
			@Param("orderType") int orderType, PageBounds pageBounds);
}