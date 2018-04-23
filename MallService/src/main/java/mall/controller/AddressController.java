package mall.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lingshi.web.model.RequestHolder;
import mall.base.model.Address;
import mall.base.model.Userinfo;
import mall.service.AddressService;
import mall.uimodel.EUIPageList;

@Controller
@RequestMapping("address")
public class AddressController {

	@Resource
	private AddressService addressService;

	/**
	 * 获取集合列表
	 * 
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/list")
	public void list(HttpServletRequest request, HttpServletResponse response, Address address, int page, int rows) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			EUIPageList<Address> list = addressService.list(address, page, rows);
			requestHolder.success(list);
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 获取单条数据
	 * 
	 * @param request
	 * @param response
	 * @param userId
	 */
	@ResponseBody
	@RequestMapping("/single")
	public void single(HttpServletRequest request, HttpServletResponse response, String addressid) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Address address = addressService.single(addressid);
			requestHolder.success(address);
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param response
	 * @param userinfo
	 */
	@ResponseBody
	@RequestMapping("/update")
	public void update(HttpServletRequest request, HttpServletResponse response, Address address) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			addressService.update(address);
			requestHolder.success("操作成功", address);
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 添加
	 * 
	 * @param request
	 * @param response
	 * @param userinfo
	 */
	@ResponseBody
	@RequestMapping("/add")
	public void add(HttpServletRequest request, HttpServletResponse response, Address address) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Userinfo userinfo=(Userinfo)requestHolder.getClientUser();
			address.setUserid(userinfo.getUserid());
			addressService.add(address);
			requestHolder.success("操作成功");
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 批量删除
	 * 
	 * @param request
	 * @param response
	 * @param ids
	 */
	@ResponseBody
	@RequestMapping("/batchDelete")
	public void batchDelete(HttpServletRequest request, HttpServletResponse response, @RequestBody List<String> ids) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			addressService.batchDelete(ids);
			requestHolder.success("操作成功");
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

}
