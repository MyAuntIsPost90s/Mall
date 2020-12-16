package mall.controller;

import com.ch.web.gateway.session.SessionHolder;
import mall.base.model.Ordergoods;
import mall.common.OrderEnums.OrderFormType;
import mall.service.OrderGoodsService;
import mall.uimodel.EUIPageList;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/orderGoods")
public class OrderGoodsController {

	@Resource
	private OrderGoodsService orderGoodsService;

	/**
	 * 获取财务报表
	 *
	 * @param date
	 * @param type
	 */
	@ResponseBody
	@RequestMapping("/orderForm")
	public void orderForm(@DateTimeFormat(pattern = "yyyy-MM-dd") Date date, String type) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		Map<String, String> map = orderGoodsService.getOrderForm(date, OrderFormType.valueOf(type));
		if (map == null) {
			sessionHolder.fail("当前无数据");
		}
		sessionHolder.success(map);
	}

	/**
	 * 获取订单详情集合
	 *
	 * @param ordergoods
	 * @param page
	 * @param rows
	 */
	@ResponseBody
	@RequestMapping("/list")
	public void list(Ordergoods ordergoods, int page, int rows) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		EUIPageList<Ordergoods> list = orderGoodsService.list(ordergoods, page, rows);
		sessionHolder.success(list);
	}

	/**
	 * 获取订单详情集合
	 *
	 * @param userid
	 * @param ordergoodsname
	 * @param page
	 * @param rows
	 */
	@ResponseBody
	@RequestMapping("list4count")
	public void list4count(String userid, String ordergoodsname, int page, int rows) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		EUIPageList<Ordergoods> list = orderGoodsService.list4count(userid, ordergoodsname, page, rows);
		sessionHolder.success(list);
	}
}
