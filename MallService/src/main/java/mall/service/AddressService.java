package mall.service;

import java.util.List;

import mall.base.model.Address;
import mall.uimodel.EUIPageList;

public interface AddressService {

	/**
	 * 获取单条数据
	 * 
	 * @param userId
	 * @return
	 */
	Address single(String addressid);

	/**
	 * 获取集合列表
	 * 
	 * @param userinfo
	 * @param page
	 * @param rows
	 * @return
	 */
	EUIPageList<Address> list(Address address, int page, int rows);

	/**
	 * 添加
	 * 
	 * @param userinfo
	 * @param stringBuilder
	 */
	void add(Address address);

	/**
	 * 修改
	 * 
	 * @param userinfo
	 */
	void update(Address address);

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	void batchDelete(List<String> ids);
}
