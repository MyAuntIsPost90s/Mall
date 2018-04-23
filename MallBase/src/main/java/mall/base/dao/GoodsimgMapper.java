package mall.base.dao;

import java.util.List;

import lingshi.mybaties.mapperextend.BaseMapper;
import mall.base.model.Goodsimg;

public interface GoodsimgMapper extends BaseMapper<Goodsimg> {

	void deleteByGoodsId(String goodsid);

	void batchInsert(List<Goodsimg> goodsimgs);
}