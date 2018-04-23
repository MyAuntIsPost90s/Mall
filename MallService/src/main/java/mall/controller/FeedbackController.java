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
import mall.base.model.Feedback;
import mall.base.model.Userinfo;
import mall.base.model.dto.FeedbackUserDto;
import mall.service.FeedbackService;
import mall.uimodel.EUIPageList;

@Controller
@RequestMapping("feedback")
public class FeedbackController {

	@Resource
	private FeedbackService feedbackService;

	/**
	 * 获取集合接口
	 * 
	 * @param request
	 * @param response
	 * @param feedback
	 * @param page
	 * @param rows
	 */
	@ResponseBody
	@RequestMapping("list")
	public void list(HttpServletRequest request, HttpServletResponse response, Feedback feedback, int page, int rows) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			EUIPageList<FeedbackUserDto> list = feedbackService.list(feedback, page, rows);
			requestHolder.success(list);
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
	public void add(HttpServletRequest request, HttpServletResponse response, Feedback feedback) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Userinfo userinfo = (Userinfo) requestHolder.getClientUser();
			feedbackService.add(feedback, userinfo.getUserid());
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
	@RequestMapping("batchDelete")
	public void batchDelete(HttpServletRequest request, HttpServletResponse response, @RequestBody List<String> ids) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			feedbackService.batchDelete(ids);
			requestHolder.success("操作成功");
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}
}
