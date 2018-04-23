package mall.service;

import java.util.List;
import java.util.Map;

import mall.base.model.Addresslist;
import mall.uimodel.EUIPageList;

public interface AddressListService {

	/**
	 * 获取单条数据
	 * 
	 * @param userId
	 * @return
	 */
	Addresslist single(String addresslistid);

	/**
	 * 获取集合列表
	 * 
	 * @param userinfo
	 * @param page
	 * @param rows
	 * @return
	 */
	EUIPageList<Addresslist> list(Addresslist addresslist, int page, int rows);

	/**
	 * 获取excel列表
	 * 
	 * @return
	 */
	List<Map<String, String>> list4excelmap();
	
	/**
	 * 获取excel表头
	 * 
	 * @return
	 */
	String[] excelcols();

	/**
	 * 添加
	 * 
	 * @param userinfo
	 * @param stringBuilder
	 */
	void add(Addresslist addresslist);

	/**
	 * 修改
	 * 
	 * @param userinfo
	 */
	void update(Addresslist addresslist);

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	void batchDelete(List<String> ids);
}
