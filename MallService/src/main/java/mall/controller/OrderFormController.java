package mall.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lingshi.web.model.RequestHolder;
import mall.base.enums.UserInfoEnum.UserType;
import mall.base.model.Orderform;
import mall.base.model.Ordergoods;
import mall.base.model.Userinfo;
import mall.base.model.dto.OrderWithGoodsDto;
import mall.service.OrderFormService;
import mall.uimodel.EUIPageList;

@Controller
@RequestMapping("/orderForm")
public class OrderFormController {

	@Resource
	private OrderFormService orderFormService;

	/**
	 * 获取集合
	 * 
	 * @param request
	 * @param response
	 * @param orderform
	 * @param page
	 * @param rows
	 */
	@ResponseBody
	@RequestMapping("/list")
	public void list(HttpServletRequest request, HttpServletResponse response, Orderform orderform, int page,
			int rows) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Userinfo nowUser = (Userinfo) requestHolder.getClientUser();
			if (nowUser.getUsertype() == UserType.SALES.value) {
				orderform.setUserid(nowUser.getUserid());
			}
			EUIPageList<Orderform> list = orderFormService.list(orderform, page, rows);
			requestHolder.success(list);
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 获取单条信息记录
	 * 
	 * @param request
	 * @param response
	 * @param orderformid
	 */
	@ResponseBody
	@RequestMapping("/single")
	public void single(HttpServletRequest request, HttpServletResponse response, String orderformid) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Orderform orderform = orderFormService.single(orderformid);
			requestHolder.success(orderform);
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 获取订单列表（包含订单物品详情）
	 * 
	 * @param request
	 * @param response
	 * @param orderform
	 * @param page
	 * @param rows
	 */
	@ResponseBody
	@RequestMapping("/orderWithGoods")
	public void orderWithGoods(HttpServletRequest request, HttpServletResponse response, Orderform orderform, int page,
			int rows) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Userinfo nowUser = (Userinfo) requestHolder.getClientUser();
			if (nowUser.getUsertype() == UserType.SALES.value || nowUser.getUsertype() == UserType.USER.value) {
				orderform.setUserid(nowUser.getUserid());
			}
			EUIPageList<OrderWithGoodsDto> list = orderFormService.orderWithGoods(orderform, page, rows);
			requestHolder.success(list);
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 添加订单（记录单）
	 * 
	 * @param request
	 * @param response
	 * @param ordergoods
	 */
	@ResponseBody
	@RequestMapping("/addLogOrder")
	public void addLogOrder(HttpServletRequest request, HttpServletResponse response,
			@RequestBody List<Ordergoods> ordergoods) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Userinfo userinfo = (Userinfo) requestHolder.getClientUser();
			orderFormService.addLogOrder(ordergoods, userinfo.getUserid());
			requestHolder.success("操作成功，请在订单记录中查看");
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 添加订单（用户单）
	 * 
	 * @param request
	 * @param response
	 * @param ordergoods
	 */
	@ResponseBody
	@RequestMapping("/addUserOrder")
	public void addUserOrder(HttpServletRequest request, HttpServletResponse response, String addressid) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Userinfo userinfo = (Userinfo) requestHolder.getClientUser();
			orderFormService.addUserOrder(userinfo.getUserid(), addressid);
			requestHolder.success("操作成功，请在订单记录中查看");
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 修改订单
	 * 
	 * @param request
	 * @param response
	 * @param orderform
	 */
	@ResponseBody
	@RequestMapping("/update")
	public void update(HttpServletRequest request, HttpServletResponse response, Orderform orderform) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			orderFormService.update(orderform);
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
			orderFormService.batchDelete(ids);
			requestHolder.success("操作成功");
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}
}
