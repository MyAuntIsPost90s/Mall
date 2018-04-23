package mall.base.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import lingshi.mybaties.mapperextend.BaseMapper;
import mall.base.model.Userinfo;

public interface UserinfoMapper extends BaseMapper<Userinfo> {
	
	/**
	 * 获取员工列表
	 * 
	 * @param userinfo
	 * @param pageBounds
	 * @return
	 */
	PageList<Userinfo> getStaffWithPage(Userinfo userinfo,PageBounds pageBounds);

	/**
	 * 获取员工工资
	 * 
	 * @return
	 */
	Integer getStaffWages();
}