package mall.service.impl;

import mall.base.dao.BannerMapper;
import mall.base.model.Banner;
import mall.common.page.PageList;
import mall.common.page.QueryPageListProxy;
import mall.service.BannerService;
import mall.uimodel.EUIPageList;
import mall.util.RandomNum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    @Resource
    private BannerMapper bannerMapper;

    @Override
    public EUIPageList<Banner> list(Banner banner, int page, int rows) {
        PageList<Banner> pageList = QueryPageListProxy.query(() -> bannerMapper.getList(banner), page, rows);
        return new EUIPageList<Banner>(pageList.getTotal(), pageList.getRows());
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
