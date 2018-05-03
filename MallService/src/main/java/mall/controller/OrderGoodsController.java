package mall.controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lingshi.web.model.RequestHolder;
import mall.base.model.Ordergoods;
import mall.common.OrderEnums.OrderFormType;
import mall.service.OrderGoodsService;
import mall.uimodel.EUIPageList;

@Controller
@RequestMapping("/orderGoods")
public class OrderGoodsController {

	@Resource
	private OrderGoodsService orderGoodsService;

	/**
	 * 获取财务报表
	 * 
	 * @param request
	 * @param response
	 * @param date
	 * @param type
	 */
	@ResponseBody
	@RequestMapping("/orderForm")
	public void orderForm(HttpServletRequest request, HttpServletResponse response,
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date date, String type) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Map<String, String> map = orderGoodsService.getOrderForm(date, OrderFormType.valueOf(type));
			if (map == null) {
				requestHolder.fail("当前无数据");
			}
			requestHolder.success(map);
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 获取订单详情集合
	 * 
	 * @param request
	 * @param response
	 * @param ordergoods
	 * @param page
	 * @param rows
	 */
	@ResponseBody
	@RequestMapping("/list")
	public void list(HttpServletRequest request, HttpServletResponse response, Ordergoods ordergoods, int page,
			int rows) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			EUIPageList<Ordergoods> list = orderGoodsService.list(ordergoods, page, rows);
			requestHolder.success(list);
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 获取订单详情集合
	 * 
	 * @param request
	 * @param response
	 * @param ordergoods
	 * @param page
	 * @param rows
	 */
	@ResponseBody
	@RequestMapping("list4count")
	public void list4count(HttpServletRequest request, HttpServletResponse response, String userid, int page,
			int rows) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			EUIPageList<Ordergoods> list = orderGoodsService.list4count(userid, page, rows);
			requestHolder.success(list);
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}
}
