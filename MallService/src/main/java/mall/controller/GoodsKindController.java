package mall.controller;

import com.ch.web.gateway.session.SessionHolder;
import mall.base.model.Goodskind;
import mall.service.GoodsKindService;
import mall.uimodel.EUIPageList;
import mall.uimodel.EUITree;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/goodsKind")
public class GoodsKindController {

	@Resource
	private GoodsKindService goodsKindService;

	/**
	 * 获取集合列表
	 *
	 */
	@ResponseBody
	@RequestMapping("/list")
	public void list(Goodskind goodskind, int page,
			int rows) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		try {
			EUIPageList<Goodskind> list = goodsKindService.list(goodskind, page, rows);
			sessionHolder.success(list);
		} catch (Exception e) {
			sessionHolder.fail("操作失败", e);
		}
	}

	/**
	 * 获取树形集合列表
	 *
	 * @param goodskind
	 */
	@ResponseBody
	@RequestMapping("/tree")
	public void tree(Goodskind goodskind) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		try {
			List<EUITree> list = goodsKindService.tree(goodskind);
			sessionHolder.success(list);
		} catch (Exception e) {
			sessionHolder.fail("操作失败", e);
		}
	}

	/**
	 * 获取单条数据
	 *
	 * @param goodskindid
	 */
	@ResponseBody
	@RequestMapping("/single")
	public void single(String goodskindid) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		try {
			Goodskind goodskind = goodsKindService.single(goodskindid);
			sessionHolder.success(goodskind);
		} catch (Exception e) {
			sessionHolder.fail("操作失败", e);
		}
	}

	/**
	 * 修改
	 *
	 * @param goodskind
	 */
	@ResponseBody
	@RequestMapping("/update")
	public void update(Goodskind goodskind) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		try {
			goodsKindService.update(goodskind);
			sessionHolder.success("操作成功", goodskind);
		} catch (Exception e) {
			sessionHolder.fail("操作失败", e);
		}
	}

	/**
	 * 添加
	 *
	 * @param goodskind
	 */
	@ResponseBody
	@RequestMapping("/add")
	public void add(Goodskind goodskind) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		try {
			goodsKindService.add(goodskind);
			sessionHolder.success("操作成功");
		} catch (Exception e) {
			sessionHolder.fail("操作失败", e);
		}
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
		try {
			goodsKindService.batchDelete(ids);
			sessionHolder.success("操作成功");
		} catch (Exception e) {
			sessionHolder.fail("操作失败", e);
		}
	}
}
