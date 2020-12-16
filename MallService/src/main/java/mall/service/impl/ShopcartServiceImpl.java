package mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import mall.common.page.PageList;
import mall.common.page.QueryPageListProxy;

import mall.base.dao.ShopcartMapper;
import mall.base.model.Shopcart;
import mall.base.model.dto.ShopcartGoodsDto;
import mall.service.GoodsService;
import mall.service.ShopcartService;
import mall.uimodel.EUIPageList;
import mall.util.RandomNum;

@Service
public class ShopcartServiceImpl implements ShopcartService {

	@Resource
	private ShopcartMapper shopcartMapper;
	@Resource
	private GoodsService goodsService;

	@Override
	public EUIPageList<ShopcartGoodsDto> list(Shopcart shopcart, int page, int rows) {
		PageList<Shopcart> pageList = QueryPageListProxy.query(()-> shopcartMapper.getList(shopcart), page, rows);
		List<ShopcartGoodsDto> result = new ArrayList<ShopcartGoodsDto>();
		for (Shopcart item : pageList.getRows()) {
			ShopcartGoodsDto shopcartGoodsDto = new ShopcartGoodsDto(item);
			shopcartGoodsDto.setGoods(goodsService.single(item.getGoodsid()));
			result.add(shopcartGoodsDto);
		}
		return new EUIPageList<>(pageList.getTotal(), result);
	}

	@Override
	public ShopcartGoodsDto single(String shopcartid) {
		Shopcart shopcart = shopcartMapper.getSingle(shopcartid);
		ShopcartGoodsDto shopcartGoodsDto = new ShopcartGoodsDto(shopcart);
		shopcartGoodsDto.setGoods(goodsService.single(shopcart.getGoodsid()));
		return shopcartGoodsDto;
	}

	@Override
	public void add(Shopcart shopcart) {
		// 判断是否是添加
		Shopcart condition = new Shopcart();
		condition.setGoodsid(shopcart.getGoodsid());
		condition.setUserid(shopcart.getUserid());
		List<Shopcart> list = shopcartMapper.getList(condition);
		if (list == null || list.isEmpty()) { // 判定为添加
			shopcart.setShopcartid(RandomNum.getSID());
			shopcartMapper.insert(shopcart);
			return;
		}
		list.get(0).setShopcartcount(list.get(0).getShopcartcount() + shopcart.getShopcartcount());
		update(list.get(0));
	}

	@Override
	public void update(Shopcart shopcart) {
		shopcartMapper.update(shopcart);
	}

	@Override
	public void batchDelete(List<String> ids) {
		shopcartMapper.batchDelete(ids);
	}

	@Override
	public void batchUpdate(List<Shopcart> shopcarts, String userid) {
		for (Shopcart shopcart : shopcarts) {
			shopcart.setUserid(userid);
			shopcartMapper.update(shopcart);
		}
	}
}
