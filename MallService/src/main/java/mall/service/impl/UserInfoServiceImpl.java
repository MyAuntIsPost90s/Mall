package mall.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import mall.base.dao.UserinfoMapper;
import mall.base.enums.UserInfoEnum.UserType;
import mall.base.model.Ordergoods;
import mall.base.model.Userinfo;
import mall.base.model.dto.UserSalaryDto;
import mall.common.OrderEnums.OrderType;
import mall.service.OrderGoodsService;
import mall.service.UserInfoService;
import mall.uimodel.EUIPageList;
import mall.util.RandomNum;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Resource
	private UserinfoMapper userinfoMapper;
	@Resource
	private OrderGoodsService orderGoodsService;

	@Override
	public Userinfo login(Userinfo userinfo, StringBuilder stringBuilder) {
		Userinfo condition = new Userinfo();
		condition.setUsername(userinfo.getUsername());
		List<Userinfo> list = userinfoMapper.getListWithPage(condition, new PageBounds(1, 1));
		if (list == null || list.size() < 1) {
			stringBuilder.append("该用户不存在");
			return null;
		}
		condition = list.get(0);
		if (!condition.getPassword().equals(userinfo.getPassword())) {
			stringBuilder.append("用户名或密码错误");
			return null;
		}
		stringBuilder.append("登陆成功");
		return condition;
	}

	@Override
	public EUIPageList<Userinfo> list(Userinfo userinfo, int page, int rows) {
		PageList<Userinfo> pageList = userinfoMapper.getListWithPage(userinfo, new PageBounds(page, rows));
		return new EUIPageList<Userinfo>(pageList.getPaginator().getTotalCount(), pageList);
	}

	@Override
	public EUIPageList<UserSalaryDto> list4salary(Userinfo userinfo, Date date, int page, int rows) {
		PageList<Userinfo> pageList = userinfoMapper.getStaffWithPage(userinfo, new PageBounds(page, rows));
		List<UserSalaryDto> list = new ArrayList<UserSalaryDto>();
		for (Userinfo item : pageList) {
			UserSalaryDto userSalaryDto = new UserSalaryDto();
			userSalaryDto.setRealname(item.getRealname());
			userSalaryDto.setUserheadimgurl(item.getUserheadimgurl());
			userSalaryDto.setUsername(item.getUsername());
			userSalaryDto.setWages(item.getWages());
			userSalaryDto.setUserid(item.getUserid());
			userSalaryDto.setUsertype(item.getUsertype());
			Ordergoods ordergoods = orderGoodsService.getItemSum(item.getUserid(), date, OrderType.LOG.value);
			if (ordergoods == null) {
				ordergoods = new Ordergoods();
				ordergoods.setOrdergoodscost(0f);
				ordergoods.setOrdergoodscount(0);
				ordergoods.setOrdergoodspercentage(0f);
				ordergoods.setOrdergoodsprice(0f);
			}
			userSalaryDto.setAllcost(ordergoods.getOrdergoodscost());
			userSalaryDto.setAllcount(ordergoods.getOrdergoodscount());
			userSalaryDto.setAllpercentage(ordergoods.getOrdergoodspercentage());
			userSalaryDto.setAllprice(ordergoods.getOrdergoodsprice());
			list.add(userSalaryDto);
		}
		return new EUIPageList<UserSalaryDto>(pageList.getPaginator().getTotalCount(), list);
	}

	@Override
	public Userinfo single(String userId) {
		return userinfoMapper.getSingle(userId);
	}

	@Override
	public boolean add(Userinfo userinfo, StringBuilder stringBuilder) {
		Userinfo condition = new Userinfo();
		condition.setUsername(userinfo.getUsername());
		long count = userinfoMapper.count(condition);
		if (count > 0) {
			stringBuilder.append("用户名已经存在");
			return false;
		}
		userinfo.setUserid(RandomNum.getAID());
		userinfo.setCreatetime(new Date());
		userinfoMapper.insert(userinfo);
		return true;
	}

	@Override
	public Userinfo regist(Userinfo userinfo, StringBuilder stringBuilder) {
		Userinfo condition = new Userinfo();
		condition.setUsername(userinfo.getUsername());
		long count = userinfoMapper.count(condition);
		if (count > 0) {
			stringBuilder.append("用户名已经存在");
			return null;
		}
		userinfo.setUsertype(UserType.USER.value);
		userinfo.setUserid(RandomNum.getAID());
		userinfo.setCreatetime(new Date());
		userinfoMapper.insert(userinfo);
		return userinfo;
	}

	@Override
	public void update(Userinfo userinfo) {
		userinfoMapper.update(userinfo);
	}

	@Override
	public void batchDelete(List<String> ids) {
		userinfoMapper.batchDelete(ids);
	}

	@Override
	public Integer getStaffWages() {
		return userinfoMapper.getStaffWages();
	}
}
