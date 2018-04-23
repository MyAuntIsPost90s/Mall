package mall.base.model.dto;

import mall.base.model.Goods;
import mall.base.model.Shopcart;

public class ShopcartGoodsDto extends Shopcart {

	private Goods goods;

	public ShopcartGoodsDto() {}

	public ShopcartGoodsDto(Shopcart shopcart) {
		super.setGoodsid(shopcart.getGoodsid());
		super.setShopcartcount(shopcart.getShopcartcount());
		super.setShopcartid(shopcart.getShopcartid());
		super.setUserid(shopcart.getUserid());
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

}
