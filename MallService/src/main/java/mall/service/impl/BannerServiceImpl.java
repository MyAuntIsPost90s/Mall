package mall.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import mall.base.dao.BannerMapper;
import mall.base.model.Banner;
import mall.service.BannerService;
import mall.uimodel.EUIPageList;
import mall.util.RandomNum;

@Service
public class BannerServiceImpl implements BannerService {

	@Resource
	private BannerMapper bannerMapper;

	@Override
	public EUIPageList<Banner> list(Banner banner, int page, int rows) {
		PageList<Banner> pageList = bannerMapper.getListWithPage(banner, new PageBounds(page, rows));
		return new EUIPageList<Banner>(pageList.getPaginator().getTotalCount(), pageList);
	}

	@Override
	public Banner single(String bannerid) {
		return bannerMapper.getSingle(bannerid);
	}

	@Override
	public void add(Banner banner) {
		banner.setBannerid(RandomNum.getBID());
		bannerMapper.insert(banner);
	}

	@Override
	public void update(Banner banner) {
		bannerMapper.update(banner);
	}

	@Override
	public void batchDelete(List<String> ids) {
		bannerMapper.batchDelete(ids);
	}

}
