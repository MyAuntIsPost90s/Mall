package mall.service;

import java.util.List;

import mall.base.model.Goods;
import mall.base.model.dto.GoodsDto;
import mall.uimodel.EUIPageList;

public interface GoodsService {
	/**
	 * 获取单条数据
	 * 
	 * @param userId
	 * @return
	 */
	GoodsDto single(String goodsid);

	/**
	 * 获取集合列表
	 * 
	 * @param userinfo
	 * @param page
	 * @param rows
	 * @return
	 */
	EUIPageList<GoodsDto> list(Goods goods, int page, int rows);

	/**
	 * 获取搜索集合
	 * 
	 * @param goodsname
	 * @param orderType
	 * @param page
	 * @param rows
	 * @return
	 */
	EUIPageList<GoodsDto> list4search(String goodsname, String goodskindid, int orderType, int page, int rows);

	/**
	 * 添加
	 * 
	 * @param userinfo
	 * @param stringBuilder
	 */
	void add(Goods goods, String urls);

	/**
	 * 修改
	 * 
	 * @param userinfo
	 */
	void update(Goods goods, String urls);

	/**
	 * 修改数量（商品入库使用）
	 * 
	 * @param goods
	 */
	void update4count(Goods goods, String userid) throws Exception;

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	void batchDelete(List<String> ids);
}
