package mall.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lingshi.web.model.RequestHolder;
import mall.base.model.Userinfo;
import mall.service.UserInfoService;

@Controller
@RequestMapping("account")
public class AccountController {

	@Resource
	private UserInfoService userInfoService;

	/**
	 * 登陆接口
	 * 
	 * @param request
	 * @param response
	 * @param userinfo
	 */
	@ResponseBody
	@RequestMapping("login")
	public void login(HttpServletRequest request, HttpServletResponse response, Userinfo userinfo) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			StringBuilder stringBuilder = new StringBuilder("");
			userinfo = userInfoService.login(userinfo, stringBuilder);
			if (userinfo == null) {
				requestHolder.fail(stringBuilder.toString());
				return;
			}
			requestHolder.addClientUser(userinfo);
			requestHolder.success(stringBuilder.toString(), userinfo);
		} catch (Exception e) {
			requestHolder.err(e);
		}
	}

	/**
	 * 注册接口
	 * 
	 * @param request
	 * @param response
	 * @param userinfo
	 */
	@ResponseBody
	@RequestMapping("regist")
	public void regist(HttpServletRequest request, HttpServletResponse response, Userinfo userinfo) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			StringBuilder stringBuilder = new StringBuilder("");
			userinfo = userInfoService.regist(userinfo, stringBuilder);
			if (userinfo == null) {
				requestHolder.fail(stringBuilder.toString());
				return;
			}
			requestHolder.addClientUser(userinfo);
			requestHolder.success(stringBuilder.toString(), userinfo);
		} catch (Exception e) {
			requestHolder.err(e);
		}
	}
	
	/**
	 * 登出
	 * 
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("loginOut")
	public void loginOut(HttpServletRequest request, HttpServletResponse response){
		RequestHolder requestHolder = RequestHolder.get(request, response);
		requestHolder.removeClientUser();
	}
}
