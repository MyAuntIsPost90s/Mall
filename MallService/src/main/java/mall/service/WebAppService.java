package mall.service;

import java.util.List;
import java.util.Map;

import mall.base.model.Goodskind;
import mall.base.model.dto.GoodsDto;
import mall.uimodel.EUIPageList;

public interface WebAppService {

	/**
	 * 获取首页数据集合
	 * 
	 * @return
	 */
	Map<String, Object> home();
	
	/**
	 * 物品详情页
	 * 
	 * @return
	 */
	Map<String, Object> introduction(String goodsid);
	
	/**
	 * 付款页面
	 * 
	 * @return
	 */
	Map<String, Object> pay(String userId);

	/**
	 * 获取分类集合
	 * 
	 * @return
	 */
	List<Goodskind> goodskinds();

	/**
	 * 获取物品列表
	 * 
	 * @param goodsname
	 * @param goodskindid
	 * @param orderType
	 * @param page
	 * @param rows
	 * @return
	 */
	EUIPageList<GoodsDto> list4goods(String goodsname, String goodskindid, int orderType, int page, int rows);

}
