package mall.controller;

import com.ch.web.gateway.session.SessionHolder;
import mall.base.model.Shopcart;
import mall.base.model.Userinfo;
import mall.base.model.dto.ShopcartGoodsDto;
import mall.service.ShopcartService;
import mall.uimodel.EUIPageList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("shopcart")
public class ShopcartController {

	@Resource
	private ShopcartService shopcartService;

	/**
	 * 获取集合列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	public void list(Shopcart shopcart, int page, int rows) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		Userinfo userinfo = (Userinfo) sessionHolder.getUserSession().getData();
		shopcart.setUserid(userinfo.getUserid());
		EUIPageList<ShopcartGoodsDto> list = shopcartService.list(shopcart, page, rows);
		sessionHolder.success(list);
	}

	/**
	 * 获取单条数据
	 *
	 * @param shopcartid
	 */
	@ResponseBody
	@RequestMapping("/single")
	public void single(String shopcartid) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		ShopcartGoodsDto shopcartGoodsDto = shopcartService.single(shopcartid);
		sessionHolder.success(shopcartGoodsDto);
	}

	/**
	 * 修改
	 *
	 * @param shopcart
	 */
	@ResponseBody
	@RequestMapping("/update")
	public void update(Shopcart shopcart) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		Userinfo userinfo = (Userinfo) sessionHolder.getUserSession().getData();
		shopcart.setUserid(userinfo.getUserid());
		shopcartService.update(shopcart);
		sessionHolder.success("操作成功", shopcart);
	}

	/**
	 * 添加
	 *
	 * @param shopcart
	 */
	@ResponseBody
	@RequestMapping("/add")
	public void add(Shopcart shopcart) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		Userinfo userinfo = (Userinfo) sessionHolder.getUserSession().getData();
		shopcart.setUserid(userinfo.getUserid());
		shopcartService.add(shopcart);
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
		shopcartService.batchDelete(ids);
		sessionHolder.success("操作成功");
	}

	/**
	 * 批量修改数据
	 *
	 */
	@ResponseBody
	@RequestMapping("/batchUpdate")
	public void batchUpdate(@RequestBody List<Shopcart> list) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		Userinfo userinfo = (Userinfo) sessionHolder.getUserSession().getData();
		shopcartService.batchUpdate(list, userinfo.getUserid());
		sessionHolder.success("操作成功");
	}

}
