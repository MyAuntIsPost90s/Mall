package mall.controller;

import com.ch.web.gateway.session.SessionHolder;
import mall.base.model.Addresslist;
import mall.service.AddressListService;
import mall.uimodel.EUIPageList;
import mall.util.ExcelUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/addressList")
public class AddressListController {

	@Resource
	private AddressListService addressListService;

	/**
	 * 获取集合列表
	 *
	 */
	@ResponseBody
	@RequestMapping("/list")
	public void list(Addresslist addresslist, int page, int rows) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		EUIPageList<Addresslist> list = addressListService.list(addresslist, page, rows);
		sessionHolder.success(list);
	}

	/**
	 * 获取单条数据
	 *
	 * @param addresslistid
	 */
	@ResponseBody
	@RequestMapping("/single")
	public void single(String addresslistid) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		Addresslist addresslist = addressListService.single(addresslistid);
		sessionHolder.success(addresslist);
	}

	/**
	 * 修改
	 *
	 * @param addresslist
	 */
	@ResponseBody
	@RequestMapping("/update")
	public void update(Addresslist addresslist) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		addressListService.update(addresslist);
		sessionHolder.success("操作成功", addresslist);
	}

	/**
	 * 添加
	 *
	 * @param addresslist
	 */
	@ResponseBody
	@RequestMapping("/add")
	public void add(Addresslist addresslist) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		addressListService.add(addresslist);
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
		addressListService.batchDelete(ids);
		sessionHolder.success("操作成功");
	}

	/**
	 * 导出excel
	 *
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/export")
	public void export(HttpServletResponse response) throws Exception {
		List<Map<String, String>> list = addressListService.list4excelmap();
		String[] cols = addressListService.excelcols();
		String sheetname = "通讯录";
		response.reset();
		response.setContentType("application/vnd.ms-excel; charset=utf-8");
		response.setHeader("Content-Disposition",
				"attachment; filename=" + java.net.URLEncoder.encode(sheetname+".xls", "UTF8"));
		ExcelUtil.exportExcel(response.getOutputStream(),list, sheetname, cols);
	}
}
