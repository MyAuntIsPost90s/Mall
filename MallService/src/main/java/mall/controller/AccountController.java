package mall.controller;

import com.ch.web.gateway.session.SessionHolder;
import com.ch.web.gateway.session.UserSession;
import com.ch.web.gateway.session.UserSessionBuilder;
import com.ch.web.gateway.session.UserSessionManager;
import com.ch.web.gateway.token.TokenUuidGenerateStrategy;
import mall.base.model.Userinfo;
import mall.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Resource
    private UserInfoService userInfoService;

    /**
     * 登陆接口
     *
     * @param userinfo
     */
    @ResponseBody
    @RequestMapping("/login")
    public void login(Userinfo userinfo) throws Exception {
        SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
        StringBuilder stringBuilder = new StringBuilder("");
        userinfo = userInfoService.login(userinfo, stringBuilder);
        if (userinfo == null) {
            sessionHolder.fail(stringBuilder.toString());
            return;
        }
        UserSession<Userinfo> session = UserSessionBuilder.build(userinfo.getUserid(), userinfo, TokenUuidGenerateStrategy.class);
        UserSessionManager.get().addSession(session);
        sessionHolder.success(stringBuilder.toString(), session);
    }

    /**
     * 注册接口
     *
     * @param userinfo
     */
    @ResponseBody
    @RequestMapping("/regist")
    public void regist(Userinfo userinfo) throws Exception {
        SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
        StringBuilder stringBuilder = new StringBuilder("");
        userinfo = userInfoService.regist(userinfo, stringBuilder);
        if (userinfo == null) {
            sessionHolder.fail(stringBuilder.toString());
            return;
        }
        UserSession<Userinfo> session = UserSessionBuilder.build(userinfo.getUserid(), userinfo, TokenUuidGenerateStrategy.class);
        UserSessionManager.get().addSession(session);
        sessionHolder.success(stringBuilder.toString(), userinfo);
    }

    /**
     * 登出
     *
     */
    @ResponseBody
    @RequestMapping("/loginOut")
    public void loginOut() {
        SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
        UserSession<Userinfo> session = sessionHolder.getUserSession();
        UserSessionManager.get().removeSession(session.getAccessToken());
        sessionHolder.success("退出登录成功");
    }
}
