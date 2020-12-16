package mall.controller;

import com.ch.web.gateway.session.SessionHolder;
import mall.base.model.Address;
import mall.base.model.Userinfo;
import mall.service.AddressService;
import mall.uimodel.EUIPageList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("address")
public class AddressController {

    @Resource
    private AddressService addressService;

    /**
     * 获取集合列表
     */
    @ResponseBody
    @RequestMapping("/list")
    public void list(Address address, int page, int rows) {
        SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
        Userinfo userinfo = (Userinfo) sessionHolder.getUserSession().getData();
        address.setUserid(userinfo.getUserid());
        EUIPageList<Address> list = addressService.list(address, page, rows);
        sessionHolder.success(list);
    }

    /**
     * 获取单条数据
     *
     * @param addressid
     */
    @ResponseBody
    @RequestMapping("/single")
    public void single(String addressid) {
        SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
        Address address = addressService.single(addressid);
        sessionHolder.success(address);
    }

    /**
     * 修改
     *
     * @param address
     */
    @ResponseBody
    @RequestMapping("/update")
    public void update(Address address) {
        SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
        addressService.update(address);
        sessionHolder.success("操作成功", address);
    }

    /**
     * 添加
     *
     * @param address
     */
    @ResponseBody
    @RequestMapping("/add")
    public void add(Address address) {
        SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
        Userinfo userinfo = (Userinfo) sessionHolder.getUserSession().getData();
        address.setUserid(userinfo.getUserid());
        addressService.add(address);
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
        addressService.batchDelete(ids);
        sessionHolder.success("操作成功");
    }

}
