package mall.base.dao;

import com.ch.common.mybatis.BaseMapper;
import mall.base.model.Userinfo;

import java.util.List;

public interface UserinfoMapper extends BaseMapper<Userinfo> {
	
	/**
	 * 获取员工列表
	 * 
	 * @param userinfo
	 * @return
	 */
	List<Userinfo> getStaffWithPage(Userinfo userinfo);

	/**
	 * 获取员工工资
	 * 
	 * @return
	 */
	Integer getStaffWages();
}