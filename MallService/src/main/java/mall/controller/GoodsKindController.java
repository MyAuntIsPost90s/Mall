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
import mall.base.model.Goodskind;
import mall.service.GoodsKindService;
import mall.uimodel.EUIPageList;
import mall.uimodel.EUITree;

@Controller
@RequestMapping("/goodsKind")
public class GoodsKindController {

	@Resource
	private GoodsKindService goodsKindService;

	/**
	 * 获取集合列表
	 * 
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/list")
	public void list(HttpServletRequest request, HttpServletResponse response, Goodskind goodskind, int page,
			int rows) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			EUIPageList<Goodskind> list = goodsKindService.list(goodskind, page, rows);
			requestHolder.success(list);
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 获取树形集合列表
	 * 
	 * @param request
	 * @param response
	 * @param goodskind
	 */
	@ResponseBody
	@RequestMapping("/tree")
	public void tree(HttpServletRequest request, HttpServletResponse response, Goodskind goodskind) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			List<EUITree> list = goodsKindService.tree(goodskind);
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
	public void single(HttpServletRequest request, HttpServletResponse response, String goodskindid) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Goodskind goodskind = goodsKindService.single(goodskindid);
			requestHolder.success(goodskind);
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
	public void update(HttpServletRequest request, HttpServletResponse response, Goodskind goodskind) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			goodsKindService.update(goodskind);
			requestHolder.success("操作成功", goodskind);
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
	public void add(HttpServletRequest request, HttpServletResponse response, Goodskind goodskind) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			goodsKindService.add(goodskind);
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
			goodsKindService.batchDelete(ids);
			requestHolder.success("操作成功");
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}
}
