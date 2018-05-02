package mall.service;

import java.util.List;

import mall.base.model.Orderform;
import mall.base.model.Ordergoods;
import mall.base.model.dto.OrderWithGoodsDto;
import mall.uimodel.EUIPageList;

public interface OrderFormService {
	/**
	 * 获取单条数据
	 * 
	 * @param userId
	 * @return
	 */
	Orderform single(String orderformid);

	/**
	 * 获取集合列表
	 * 
	 * @param userinfo
	 * @param page
	 * @param rows
	 * @return
	 */
	EUIPageList<Orderform> list(Orderform orderform, int page, int rows);

	/**
	 * 获取订单集合
	 * 
	 * @param orderform
	 * @param page
	 * @param rows
	 * @return
	 */
	EUIPageList<OrderWithGoodsDto> orderWithGoods(Orderform orderform, int page, int rows);

	/**
	 * 添加（记录订单）
	 * 
	 * @param userinfo
	 * @param stringBuilder
	 */
	void addLogOrder(List<Ordergoods> ordergoods, String userid) throws Exception;

	/**
	 * 添加采购记录单
	 * 
	 * @param ordergoods
	 * @param userid
	 * @throws Exception
	 */
	void addBuyLogOrder(List<Ordergoods> ordergoods, String userid) throws Exception;

	/**
	 * 添加（用户订单）
	 * 
	 * @param userinfo
	 * @param stringBuilder
	 */
	void addUserOrder(String userid, String addressid) throws Exception;

	/**
	 * 修改
	 * 
	 * @param userinfo
	 */
	void update(Orderform orderform);

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	void batchDelete(List<String> ids);
}
