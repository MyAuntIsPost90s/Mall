package mall.service;

import java.util.List;

import mall.base.model.Shopcart;
import mall.base.model.dto.ShopcartGoodsDto;
import mall.uimodel.EUIPageList;

public interface ShopcartService {

	/**
	 * 获取购物车列表
	 * 
	 * @param shopcart
	 * @param page
	 * @param rows
	 * @return
	 */
	EUIPageList<ShopcartGoodsDto> list(Shopcart shopcart, int page, int rows);

	/**
	 * 获取单条购物车信息
	 * 
	 * @param shopcartid
	 * @return
	 */
	ShopcartGoodsDto single(String shopcartid);

	/**
	 * 添加购物车信息
	 * 
	 * @param shopcart
	 */
	void add(Shopcart shopcart);

	/**
	 * 修改购物车信息
	 * 
	 * @param shopcart
	 */
	void update(Shopcart shopcart);

	/**
	 * 批量修改购物车信息
	 * 
	 * @param shopcarts
	 * @param userid
	 */
	void batchUpdate(List<Shopcart> shopcarts, String userid);

	/**
	 * 通过id批量删除
	 * 
	 * @param ids
	 */
	void batchDelete(List<String> ids);
}
