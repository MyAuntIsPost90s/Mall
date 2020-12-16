package mall.controller;

import com.ch.web.gateway.session.SessionHolder;
import mall.base.model.Banner;
import mall.service.BannerService;
import mall.uimodel.EUIPageList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("banner")
public class BannerController {

	@Resource
	private BannerService bannerService;

	/**
	 * 获取集合列表
	 *
	 */
	@ResponseBody
	@RequestMapping("/list")
	public void list(Banner banner, int page, int rows) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		EUIPageList<Banner> list = bannerService.list(banner, page, rows);
		sessionHolder.success(list);
	}

	/**
	 * 获取单条数据
	 *
	 * @param bannerid
	 */
	@ResponseBody
	@RequestMapping("/single")
	public void single(String bannerid) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		Banner banner = bannerService.single(bannerid);
		sessionHolder.success(banner);
	}

	/**
	 * 修改
	 *
	 * @param banner
	 */
	@ResponseBody
	@RequestMapping("/update")
	public void update(Banner banner) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		bannerService.update(banner);
		sessionHolder.success("操作成功", banner);
	}

	/**
	 * 添加
	 *
	 * @param banner
	 */
	@ResponseBody
	@RequestMapping("/add")
	public void add(Banner banner) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		bannerService.add(banner);
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
		bannerService.batchDelete(ids);
		sessionHolder.success("操作成功");
	}
}
