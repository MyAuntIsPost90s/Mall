package mall.controller;

import com.ch.web.gateway.session.SessionHolder;
import com.ch.web.gateway.session.UserSession;
import mall.base.model.Goodskind;
import mall.base.model.Userinfo;
import mall.base.model.dto.GoodsDto;
import mall.service.WebAppService;
import mall.uimodel.EUIPageList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 专门为webapp提供接口，不需要登陆即可获取数据
 *
 */
@Controller
@RequestMapping("webapp")
public class WebAppController {

	@Resource
	private WebAppService webAppService;

	/**
	 * 获取home页面参数
	 *
	 */
	@ResponseBody
	@RequestMapping("/home")
	public void home() {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		Map<String, Object> map = webAppService.home();
		sessionHolder.success(map);
	}

	/**
	 * 商品详情页信息
	 *
	 * @param goodsid
	 */
	@ResponseBody
	@RequestMapping("introduction")
	public void introduction(String goodsid) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		Map<String, Object> map = webAppService.introduction(goodsid);
		UserSession session = sessionHolder.getUserSession();
		if (session == null) {
			map.put("logined", false);
		} else {
			map.put("logined", true);
		}
		sessionHolder.success(map);
	}

	/**
	 * 支付页面
	 *
	 */
	@ResponseBody
	@RequestMapping("pay")
	public void pay() {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		Userinfo userinfo = (Userinfo) sessionHolder.getUserSession().getData();
		Map<String, Object> map = webAppService.pay(userinfo.getUserid());
		sessionHolder.success(map);
	}

	/**
	 * 获取物品分类
	 *
	 */
	@ResponseBody
	@RequestMapping("goodskinds")
	public void goodskinds() {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		List<Goodskind> list = webAppService.goodskinds();
		sessionHolder.success(list);
	}

	/**
	 * 获取物品列表
	 *
	 * @param goodsname
	 * @param goodskindid
	 * @param orderType
	 * @param page
	 * @param rows
	 */
	@ResponseBody
	@RequestMapping("list4goods")
	public void list4goods(String goodsname,
			String goodskindid, int orderType, int page, int rows) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		EUIPageList<GoodsDto> list = webAppService.list4goods(goodsname, goodskindid, orderType, page, rows);
		sessionHolder.success(list);
	}
}
