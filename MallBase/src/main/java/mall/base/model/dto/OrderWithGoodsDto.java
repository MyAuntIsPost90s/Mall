package mall.base.model.dto;

import java.util.List;

import mall.base.model.Orderform;
import mall.base.model.Ordergoods;

public class OrderWithGoodsDto extends Orderform {

	private List<Ordergoods> ordergoods;

	public List<Ordergoods> getOrdergoods() {
		return ordergoods;
	}

	public void setOrdergoods(List<Ordergoods> ordergoods) {
		this.ordergoods = ordergoods;
	}
}
