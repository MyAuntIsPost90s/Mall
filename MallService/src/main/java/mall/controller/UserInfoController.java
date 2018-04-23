package mall.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lingshi.web.model.RequestHolder;
import mall.base.enums.UserInfoEnum.UserType;
import mall.base.model.Userinfo;
import mall.base.model.dto.UserSalaryDto;
import mall.service.UserInfoService;
import mall.uimodel.EUIPageList;

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
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Userinfo userinfo = (Userinfo) requestHolder.getClientUser();
			requestHolder.success(userinfo);
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 获取集合列表
	 * 
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/list")
	public void list(HttpServletRequest request, HttpServletResponse response, Userinfo userinfo, int page, int rows) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			EUIPageList<Userinfo> list = userInfoService.list(userinfo, page, rows);
			requestHolder.success(list);
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 获取员工薪资水平列表
	 * 
	 * @param request
	 * @param response
	 * @param userinfo
	 * @param date
	 * @param page
	 * @param rows
	 */
	@ResponseBody
	@RequestMapping("/list4salary")
	public void list4salary(HttpServletRequest request, HttpServletResponse response, Userinfo userinfo,
			@DateTimeFormat(pattern = "yyyy-MM") Date date, int page, int rows) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Userinfo nowUser = (Userinfo) requestHolder.getClientUser();
			if(nowUser.getUsertype()!=UserType.ADMIN.value){
				userinfo.setUserid(nowUser.getUserid());
			}
			EUIPageList<UserSalaryDto> list = userInfoService.list4salary(userinfo, date, page, rows);
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
	public void single(HttpServletRequest request, HttpServletResponse response, String userId) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Userinfo userinfo = userInfoService.single(userId);
			requestHolder.success(userinfo);
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
	public void update(HttpServletRequest request, HttpServletResponse response, Userinfo userinfo) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			userInfoService.update(userinfo);
			Userinfo nowUser = (Userinfo) requestHolder.getClientUser();
			if (userinfo.getUserid().equals(nowUser.getUserid())) { // 如果是当前用户需要刷新Token中的对象
				nowUser = userInfoService.single(nowUser.getUserid());
				requestHolder.updateClientUser(nowUser);
			}
			requestHolder.success("操作成功", nowUser);
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
	public void add(HttpServletRequest request, HttpServletResponse response, Userinfo userinfo) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			StringBuilder stringBuilder = new StringBuilder();
			boolean successed = userInfoService.add(userinfo, stringBuilder);
			if (!successed) {
				requestHolder.fail(stringBuilder.toString());
				return;
			}
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
			userInfoService.batchDelete(ids);
			requestHolder.success("操作成功");
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}
}
