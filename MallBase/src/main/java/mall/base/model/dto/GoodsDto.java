package mall.base.model.dto;

import java.util.List;

import mall.base.model.Goods;
import mall.base.model.Goodsimg;
import mall.base.model.Goodskind;

public class GoodsDto extends Goods {

	private Goodskind goodskind;
	private List<Goodsimg> goodsimgs;

	public GoodsDto() {
	}

	public GoodsDto(Goods goods) {
		super.setGoodscontents(goods.getGoodscontents());
		super.setGoodscost(goods.getGoodscost());
		super.setGoodscount(goods.getGoodscount());
		super.setGoodsid(goods.getGoodsid());
		super.setGoodsimgurl(goods.getGoodsimgurl());
		super.setGoodskindid(goods.getGoodskindid());
		super.setGoodsname(goods.getGoodsname());
		super.setGoodspercentage(goods.getGoodspercentage());
		super.setGoodsprice(goods.getGoodsprice());
		super.setGoodstime(goods.getGoodstime());
	}

	public List<Goodsimg> getGoodsimgs() {
		return goodsimgs;
	}

	public void setGoodsimgs(List<Goodsimg> goodsimgs) {
		this.goodsimgs = goodsimgs;
	}

	public Goodskind getGoodskind() {
		return goodskind;
	}

	public void setGoodskind(Goodskind goodskind) {
		this.goodskind = goodskind;
	}
}
