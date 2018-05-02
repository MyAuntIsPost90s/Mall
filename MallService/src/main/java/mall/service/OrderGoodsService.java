package mall.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import mall.base.model.Ordergoods;
import mall.common.OrderEnums.OrderFormType;
import mall.uimodel.EUIPageList;

public interface OrderGoodsService {

	/**
	 * 获取统计数据
	 * 
	 * @param userid
	 * @param date
	 * @return
	 */
	Ordergoods getItemSum(String userid, Date date, Integer orderType);

	/**
	 * 获取财务报表
	 * 
	 * @return
	 */
	Map<String, String> getOrderForm(Date date, OrderFormType orderFormType);

	/**
	 * 获取单条数据
	 * 
	 * @param userId
	 * @return
	 */
	Ordergoods single(String ordergoodsid);

	/**
	 * 获取集合列表
	 * 
	 * @param userinfo
	 * @param page
	 * @param rows
	 * @return
	 */
	EUIPageList<Ordergoods> list(Ordergoods ordergoods, int page, int rows);

	/**
	 * 添加
	 * 
	 * @param userinfo
	 * @param stringBuilder
	 */
	void add(Ordergoods ordergoods) throws Exception;

	/**
	 * 添加入库订单商品
	 * 
	 * @param ordergoods
	 * @throws Exception
	 */
	void addBuy(Ordergoods ordergoods) throws Exception;

	/**
	 * 修改
	 * 
	 * @param userinfo
	 */
	void update(Ordergoods ordergoods);

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	void batchDelete(List<String> ids);
}
