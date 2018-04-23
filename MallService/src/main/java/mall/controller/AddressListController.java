package mall.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lingshi.web.model.RequestHolder;
import mall.base.model.Addresslist;
import mall.service.AddressListService;
import mall.uimodel.EUIPageList;
import mall.util.ExcelUtil;

@Controller
@RequestMapping("addressList")
public class AddressListController {

	@Resource
	private AddressListService addressListService;

	/**
	 * 获取集合列表
	 * 
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/list")
	public void list(HttpServletRequest request, HttpServletResponse response, Addresslist addresslist, int page,
			int rows) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			EUIPageList<Addresslist> list = addressListService.list(addresslist, page, rows);
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
	public void single(HttpServletRequest request, HttpServletResponse response, String addresslistid) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Addresslist addresslist = addressListService.single(addresslistid);
			requestHolder.success(addresslist);
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
	public void update(HttpServletRequest request, HttpServletResponse response, Addresslist addresslist) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			addressListService.update(addresslist);
			requestHolder.success("操作成功", addresslist);
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
	public void add(HttpServletRequest request, HttpServletResponse response, Addresslist addresslist) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			addressListService.add(addresslist);
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
			addressListService.batchDelete(ids);
			requestHolder.success("操作成功");
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/export")
	public void export(HttpServletRequest request, HttpServletResponse response) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			List<Map<String, String>> list = addressListService.list4excelmap();
			String[] cols = addressListService.excelcols();
			String sheetname = "通讯录";
			response.reset();
			response.setContentType("application/vnd.ms-excel; charset=utf-8");
			response.setHeader("Content-Disposition",
					"attachment; filename=" + java.net.URLEncoder.encode(sheetname+".xls", "UTF8"));
			ExcelUtil.exportExcel(response.getOutputStream(),list, sheetname, cols);
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}
}
