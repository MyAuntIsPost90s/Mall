package mall.controller;

import com.ch.web.gateway.session.SessionHolder;
import mall.base.model.Goods;
import mall.base.model.Userinfo;
import mall.base.model.dto.GoodsDto;
import mall.service.GoodsService;
import mall.uimodel.EUIPageList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

	@Resource
	private GoodsService goodsService;

	/**
	 * 获取集合列表
	 *
	 */
	@ResponseBody
	@RequestMapping("/list")
	public void list(Goods goods, int page, int rows) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		EUIPageList<GoodsDto> list = goodsService.list(goods, page, rows);
		sessionHolder.success(list);
	}

	/**
	 * 获取单条数据
	 *
	 * @param goodsid
	 */
	@ResponseBody
	@RequestMapping("/single")
	public void single(String goodsid) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		Goods goods = goodsService.single(goodsid);
		sessionHolder.success(goods);
	}

	/**
	 * 修改
	 *
	 * @param goods
	 * @param urls
	 */
	@ResponseBody
	@RequestMapping("/update")
	public void update(Goods goods, String urls) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		goodsService.update(goods, urls);
		sessionHolder.success("操作成功", goods);
	}

	/**
	 * 修改数量（入库）
	 *
	 * @param goods
	 */
	@ResponseBody
	@RequestMapping("/update4count")
	public void update4count(Goods goods) throws Exception {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		Userinfo userinfo = (Userinfo) sessionHolder.getUserSession().getData();
		goodsService.update4count(goods, userinfo.getUserid());
		sessionHolder.success("操作成功", goods);
	}

	/**
	 * 添加
	 *
	 * @param goods
	 * @param urls
	 */
	@ResponseBody
	@RequestMapping("/add")
	public void add(Goods goods, String urls) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		goodsService.add(goods, urls);
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
		goodsService.batchDelete(ids);
		sessionHolder.success("操作成功");
	}
}
