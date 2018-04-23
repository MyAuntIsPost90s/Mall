package mall.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import mall.base.model.Address;
import mall.base.model.Banner;
import mall.base.model.Goods;
import mall.base.model.Goodskind;
import mall.base.model.Shopcart;
import mall.base.model.dto.GoodsDto;
import mall.base.model.dto.ShopcartGoodsDto;
import mall.service.AddressService;
import mall.service.BannerService;
import mall.service.GoodsKindService;
import mall.service.GoodsService;
import mall.service.ShopcartService;
import mall.service.WebAppService;
import mall.uimodel.EUIPageList;

@Service
public class WebAppServiceImpl implements WebAppService {

	@Resource
	private GoodsService goodsService;
	@Resource
	private GoodsKindService goodsKindService;
	@Resource
	private BannerService bannerService;
	@Resource
	private ShopcartService shopcartService;
	@Resource
	private AddressService addressService;

	@Override
	public Map<String, Object> home() {
		Map<String, Object> result = new HashMap<String, Object>();
		// 获取轮播图
		EUIPageList<Banner> euibanners = bannerService.list(null, 1, 6);
		List<Banner> banners = euibanners == null ? new ArrayList<Banner>() : euibanners.getRows();
		// 获取物品种类 最多20
		EUIPageList<Goodskind> euigoodskind = goodsKindService.list(null, 1, 20);
		List<Goodskind> goodskinds = euigoodskind == null ? new ArrayList<Goodskind>() : euigoodskind.getRows();
		// 获取物品集合
		List<Object> goods = new ArrayList<Object>();
		Goods condition = new Goods();
		for (int i = 0; i < 6 && i < goodskinds.size(); i++) {
			Map<String, Object> item = new HashMap<String, Object>();
			condition.setGoodskindid(goodskinds.get(i).getGoodskindid());
			Goodskind goodskind = new Goodskind();
			goodskind.setGoodskindid(goodskinds.get(i).getGoodskindid());
			goodskind.setGoodskindname(goodskinds.get(i).getGoodskindname());
			goodskind.setGoodskindnote(goodskinds.get(i).getGoodskindnote());
			item.put("goodskind", goodskind);
			item.put("goods", goodsService.list(condition, 1, 7).getRows());
			goods.add(item);
		}
		result.put("banners", banners);
		result.put("goodskinds", goodskinds);
		result.put("goods", goods);
		return result;
	}

	@Override
	public Map<String, Object> introduction(String goodsid) {
		Map<String, Object> result = new HashMap<String, Object>();
		GoodsDto goodsDto = goodsService.single(goodsid); // 获取物品详情
		if (goodsDto == null) {
			return result;
		}
		EUIPageList<GoodsDto> newGoods = goodsService.list4search(null, null, 2, 1, 5); // 新上市物品
		EUIPageList<GoodsDto> relateGoods = goodsService.list4search(null, goodsDto.getGoodskindid(), 2, 1, 12); // 相关物品
		result.put("goods", goodsDto);
		result.put("newGoods", newGoods);
		result.put("relateGoods", relateGoods);
		return result;
	}

	@Override
	public EUIPageList<GoodsDto> list4goods(String goodsname, String goodskindid, int orderType, int page, int rows) {
		return goodsService.list4search(goodsname, goodskindid, orderType, page, rows);
	}

	@Override
	public List<Goodskind> goodskinds() {
		return goodsKindService.list(null, 1, 20).getRows();
	}

	@Override
	public Map<String, Object> pay(String userId) {
		Map<String, Object> result = new HashMap<String, Object>();
		Shopcart shopcartQ = new Shopcart();
		shopcartQ.setUserid(userId);
		List<ShopcartGoodsDto> shopcarts = shopcartService.list(shopcartQ, 1, 20).getRows();
		Address addressQ = new Address();
		addressQ.setUserid(userId);
		List<Address> addresses = addressService.list(addressQ, 1, 50).getRows();
		result.put("shopcarts", shopcarts);
		result.put("addresses", addresses);
		return result;
	}
}
