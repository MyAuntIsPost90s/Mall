package mall.service;

import java.util.List;

import mall.base.model.Banner;
import mall.uimodel.EUIPageList;

public interface BannerService {

	EUIPageList<Banner> list(Banner banner, int page, int rows);

	Banner single(String bannerid);

	void add(Banner banner);

	void update(Banner banner);

	void batchDelete(List<String> ids);
}
