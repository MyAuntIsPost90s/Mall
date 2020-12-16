package mall.controller;

import com.ch.web.gateway.session.SessionHolder;
import mall.base.enums.UserInfoEnum.UserType;
import mall.base.model.Orderform;
import mall.base.model.Ordergoods;
import mall.base.model.Userinfo;
import mall.base.model.dto.OrderWithGoodsDto;
import mall.service.OrderFormService;
import mall.uimodel.EUIPageList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/orderForm")
public class OrderFormController {

	@Resource
	private OrderFormService orderFormService;

	/**
	 * 获取集合
	 *
	 * @param orderform
	 * @param page
	 * @param rows
	 */
	@ResponseBody
	@RequestMapping("/list")
	public void list(Orderform orderform, int page, int rows) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		Userinfo nowUser = (Userinfo) sessionHolder.getUserSession().getData();
		if (nowUser.getUsertype() == UserType.SALES.value) {
			orderform.setUserid(nowUser.getUserid());
		}
		EUIPageList<Orderform> list = orderFormService.list(orderform, page, rows);
		sessionHolder.success(list);
	}

	/**
	 * 获取单条信息记录
	 *
	 * @param orderformid
	 */
	@ResponseBody
	@RequestMapping("/single")
	public void single(String orderformid) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		Orderform orderform = orderFormService.single(orderformid);
		sessionHolder.success(orderform);
	}

	/**
	 * 获取订单列表（包含订单物品详情）
	 *
	 * @param orderform
	 * @param page
	 * @param rows
	 */
	@ResponseBody
	@RequestMapping("/orderWithGoods")
	public void orderWithGoods(Orderform orderform, int page, int rows) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		Userinfo nowUser = (Userinfo) sessionHolder.getUserSession().getData();
		if (nowUser.getUsertype() == UserType.SALES.value || nowUser.getUsertype() == UserType.USER.value) {
			orderform.setUserid(nowUser.getUserid());
		}
		EUIPageList<OrderWithGoodsDto> list = orderFormService.orderWithGoods(orderform, page, rows);
		sessionHolder.success(list);
	}

	/**
	 * 添加订单（记录单）
	 *
	 * @param ordergoods
	 */
	@ResponseBody
	@RequestMapping("/addLogOrder")
	public void addLogOrder(@RequestBody List<Ordergoods> ordergoods) throws Exception {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		Userinfo userinfo = (Userinfo) sessionHolder.getUserSession().getData();
		orderFormService.addLogOrder(ordergoods, userinfo.getUserid());
		sessionHolder.success("操作成功，请在订单记录中查看");
	}

	/**
	 * 添加订单（用户单）
	 *
	 * @param addressid
	 */
	@ResponseBody
	@RequestMapping("/addUserOrder")
	public void addUserOrder(String addressid) throws Exception {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		Userinfo userinfo = (Userinfo) sessionHolder.getUserSession().getData();
		orderFormService.addUserOrder(userinfo.getUserid(), addressid);
		sessionHolder.success("操作成功，请在订单记录中查看");
	}

	/**
	 * 修改订单
	 *
	 * @param orderform
	 */
	@ResponseBody
	@RequestMapping("/update")
	public void update(Orderform orderform) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		orderFormService.update(orderform);
		sessionHolder.success("操作成功");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 */
	@ResponseBody
	@RequestMapping("/batchDelete")
	public void batchDelete(@RequestBody List<String> ids) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		orderFormService.batchDelete(ids);
		sessionHolder.success("操作成功");
	}
}
