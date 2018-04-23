package mall.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lingshi.web.model.RequestHolder;
import mall.base.model.Goodskind;
import mall.base.model.Userinfo;
import mall.base.model.dto.GoodsDto;
import mall.service.WebAppService;
import mall.uimodel.EUIPageList;

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
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("home")
	public void home(HttpServletRequest request, HttpServletResponse response) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Map<String, Object> map = webAppService.home();
			requestHolder.success(map);
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 商品详情页信息
	 * 
	 * @param request
	 * @param response
	 * @param goodsid
	 */
	@ResponseBody
	@RequestMapping("introduction")
	public void introduction(HttpServletRequest request, HttpServletResponse response, String goodsid) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Map<String, Object> map = webAppService.introduction(goodsid);
			Userinfo userinfo = (Userinfo) requestHolder.getClientUser();
			if (userinfo == null) {
				map.put("logined", false);
			} else {
				map.put("logined", true);
			}
			requestHolder.success(map);
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 支付页面
	 * 
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("pay")
	public void pay(HttpServletRequest request, HttpServletResponse response) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Userinfo userinfo = (Userinfo) requestHolder.getClientUser();
			Map<String, Object> map = webAppService.pay(userinfo.getUserid());
			requestHolder.success(map);
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 获取物品分类
	 * 
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("goodskinds")
	public void goodskinds(HttpServletRequest request, HttpServletResponse response) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			List<Goodskind> list = webAppService.goodskinds();
			requestHolder.success(list);
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 获取物品列表
	 * 
	 * @param request
	 * @param response
	 * @param goodsname
	 * @param goodskindid
	 * @param orderType
	 * @param page
	 * @param rows
	 */
	@ResponseBody
	@RequestMapping("list4goods")
	public void list4goods(HttpServletRequest request, HttpServletResponse response, String goodsname,
			String goodskindid, int orderType, int page, int rows) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			EUIPageList<GoodsDto> list = webAppService.list4goods(goodsname, goodskindid, orderType, page, rows);
			requestHolder.success(list);
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}
}
