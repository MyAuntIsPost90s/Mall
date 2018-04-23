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
import mall.base.model.Shopcart;
import mall.base.model.Userinfo;
import mall.base.model.dto.ShopcartGoodsDto;
import mall.service.ShopcartService;
import mall.uimodel.EUIPageList;

@Controller
@RequestMapping("shopcart")
public class ShopcartController {

	@Resource
	private ShopcartService shopcartService;

	/**
	 * 获取集合列表
	 * 
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/list")
	public void list(HttpServletRequest request, HttpServletResponse response, Shopcart shopcart, int page, int rows) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Userinfo userinfo = (Userinfo) requestHolder.getClientUser();
			shopcart.setUserid(userinfo.getUserid());
			EUIPageList<ShopcartGoodsDto> list = shopcartService.list(shopcart, page, rows);
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
	public void single(HttpServletRequest request, HttpServletResponse response, String shopcartid) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			ShopcartGoodsDto shopcartGoodsDto = shopcartService.single(shopcartid);
			requestHolder.success(shopcartGoodsDto);
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
	public void update(HttpServletRequest request, HttpServletResponse response, Shopcart shopcart) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Userinfo userinfo = (Userinfo) requestHolder.getClientUser();
			shopcart.setUserid(userinfo.getUserid());
			shopcartService.update(shopcart);
			requestHolder.success("操作成功", shopcart);
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
	public void add(HttpServletRequest request, HttpServletResponse response, Shopcart shopcart) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Userinfo userinfo = (Userinfo) requestHolder.getClientUser();
			shopcart.setUserid(userinfo.getUserid());
			shopcartService.add(shopcart);
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
			shopcartService.batchDelete(ids);
			requestHolder.success("操作成功");
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 批量修改数据
	 * 
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/batchUpdate")
	public void batchUpdate(HttpServletRequest request, HttpServletResponse response,
			@RequestBody List<Shopcart> list) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Userinfo userinfo = (Userinfo) requestHolder.getClientUser();
			shopcartService.batchUpdate(list, userinfo.getUserid());
			requestHolder.success("操作成功");
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

}
