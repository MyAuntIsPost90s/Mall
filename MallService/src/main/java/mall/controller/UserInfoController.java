package mall.controller;

import com.ch.web.gateway.session.SessionHolder;
import com.ch.web.gateway.session.UserSession;
import com.ch.web.gateway.session.UserSessionBuilder;
import com.ch.web.gateway.session.UserSessionManager;
import mall.base.enums.UserInfoEnum.UserType;
import mall.base.model.Userinfo;
import mall.base.model.dto.UserSalaryDto;
import mall.service.UserInfoService;
import mall.uimodel.EUIPageList;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

	@Resource
	private UserInfoService userInfoService;

	/**
	 * 获取当前登陆用户
	 * 
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/nowUser")
	public void nowUser(HttpServletRequest request, HttpServletResponse response) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		Userinfo userinfo = (Userinfo) sessionHolder.getUserSession().getData();
		sessionHolder.success(userinfo);
	}

	/**
	 * 获取集合列表
	 *
	 */
	@ResponseBody
	@RequestMapping("/list")
	public void list(Userinfo userinfo, int page, int rows) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		EUIPageList<Userinfo> list = userInfoService.list(userinfo, page, rows);
		sessionHolder.success(list);
	}

	/**
	 * 获取员工薪资水平列表
	 *
	 * @param userinfo
	 * @param date
	 * @param page
	 * @param rows
	 */
	@ResponseBody
	@RequestMapping("/list4salary")
	public void list4salary(Userinfo userinfo, @DateTimeFormat(pattern = "yyyy-MM") Date date, int page, int rows) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		Userinfo nowUser = (Userinfo) sessionHolder.getUserSession().getData();
		if(nowUser.getUsertype()!=UserType.ADMIN.value){
			userinfo.setUserid(nowUser.getUserid());
		}
		EUIPageList<UserSalaryDto> list = userInfoService.list4salary(userinfo, date, page, rows);
		sessionHolder.success(list);
	}

	/**
	 * 获取单条数据
	 *
	 * @param userId
	 */
	@ResponseBody
	@RequestMapping("/single")
	public void single(String userId) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		Userinfo userinfo = userInfoService.single(userId);
		sessionHolder.success(userinfo);
	}

	/**
	 * 修改
	 *
	 * @param userinfo
	 */
	@ResponseBody
	@RequestMapping("/update")
	public void update(Userinfo userinfo) throws Exception {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		userInfoService.update(userinfo);
		Userinfo nowUser = (Userinfo) sessionHolder.getUserSession().getData();
		if (userinfo.getUserid().equals(nowUser.getUserid())) { // 如果是当前用户需要刷新Token中的对象
			nowUser = userInfoService.single(nowUser.getUserid());
			UserSession session = sessionHolder.getUserSession();
			session = UserSessionBuilder.build(nowUser.getUserid(), nowUser, session.getAccessToken());
			UserSessionManager.get().updateSession(session);
		}
		sessionHolder.success("操作成功", nowUser);
	}

	/**
	 * 添加
	 *
	 * @param userinfo
	 */
	@ResponseBody
	@RequestMapping("/add")
	public void add(Userinfo userinfo) {
		SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		StringBuilder stringBuilder = new StringBuilder();
		boolean successed = userInfoService.add(userinfo, stringBuilder);
		if (!successed) {
			sessionHolder.fail(stringBuilder.toString());
			return;
		}
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
		userInfoService.batchDelete(ids);
		sessionHolder.success("操作成功");
	}
}
