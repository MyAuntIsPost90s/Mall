package mall.controller;

import com.ch.web.gateway.session.SessionHolder;
import mall.base.model.Feedback;
import mall.base.model.Userinfo;
import mall.base.model.dto.FeedbackUserDto;
import mall.service.FeedbackService;
import mall.uimodel.EUIPageList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("feedback")
public class FeedbackController {

	@Resource
	private FeedbackService feedbackService;

	/**
	 * 获取集合接口
	 *
	 * @param feedback
	 * @param page
	 * @param rows
	 */
	@ResponseBody
	@RequestMapping("list")
	public void list(Feedback feedback, int page, int rows) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		EUIPageList<FeedbackUserDto> list = feedbackService.list(feedback, page, rows);
		sessionHolder.success(list);
	}

	/**
	 * 添加
	 *
	 * @param feedback
	 */
	@ResponseBody
	@RequestMapping("/add")
	public void add(Feedback feedback) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		Userinfo userinfo = (Userinfo) sessionHolder.getUserSession().getData();
		feedbackService.add(feedback, userinfo.getUserid());
		sessionHolder.success("操作成功");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 */
	@ResponseBody
	@RequestMapping("batchDelete")
	public void batchDelete(@RequestBody List<String> ids) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		feedbackService.batchDelete(ids);
		sessionHolder.success("操作成功");
	}
}
