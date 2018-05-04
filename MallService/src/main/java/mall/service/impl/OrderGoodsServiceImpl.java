package mall.service.impl;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import mall.base.dao.OrdergoodsMapper;
import mall.base.model.Goods;
import mall.base.model.Ordergoods;
import mall.common.OrderEnums.OrderFormType;
import mall.service.GoodsService;
import mall.service.OrderGoodsService;
import mall.service.UserInfoService;
import mall.uimodel.EUIPageList;
import mall.util.RandomNum;

@Service
public class OrderGoodsServiceImpl implements OrderGoodsService {

	@Resource
	private OrdergoodsMapper ordergoodsMapper;
	@Resource
	private UserInfoService userInfoService;
	@Resource
	private GoodsService goodsService;

	@Override
	public Ordergoods getItemSum(String userid, Date date, Integer orderType) {
		return ordergoodsMapper.getItemSum(userid, date, OrderFormType.MONTH.value, orderType);
	}

	@Override
	public Map<String, String> getOrderForm(Date date, OrderFormType orderFormType) {
		Ordergoods ordergoods = ordergoodsMapper.getItemSum(null, date, orderFormType.value, null);
		if (ordergoods == null) {
			return null;
		}
		int stuffWages = userInfoService.getStaffWages();
		DecimalFormat df = new DecimalFormat("0.00");
		Map<String, String> map = new HashMap<String, String>();
		map.put("allcost", df.format(ordergoods.getOrdergoodscost()));
		map.put("allprice", df.format(ordergoods.getOrdergoodsprice()));
		map.put("allpercentage", df.format(ordergoods.getOrdergoodspercentage()));
		if (orderFormType.value == OrderFormType.DAY.value) {
			stuffWages = 0;
		}
		if (orderFormType.value == OrderFormType.YEAR.value) {
			stuffWages *= 12;
		}
		map.put("stuffwages", df.format(stuffWages));
		map.put("allgain", df.format(ordergoods.getOrdergoodsprice() - ordergoods.getOrdergoodscost()
				- ordergoods.getOrdergoodspercentage()));
		map.put("allcount", ordergoods.getOrdergoodscount().toString());
		float income = ordergoods.getOrdergoodsprice() - ordergoods.getOrdergoodscost()
				- ordergoods.getOrdergoodspercentage() - stuffWages;
		map.put("loss", df.format(-income));
		map.put("income", df.format(income));
		return map;
	}

	@Override
	public Ordergoods single(String ordergoodsid) {
		return ordergoodsMapper.getSingle(ordergoodsid);
	}

	@Override
	public EUIPageList<Ordergoods> list(Ordergoods ordergoods, int page, int rows) {
		PageList<Ordergoods> pageList = ordergoodsMapper.getListWithPage(ordergoods, new PageBounds(page, rows));
		return new EUIPageList<Ordergoods>(pageList.getPaginator().getTotalCount(), pageList);
	}

	@Override
	public EUIPageList<Ordergoods> list4count(String userid, String ordergoodsname, int page, int rows) {
		PageList<Ordergoods> pageList = ordergoodsMapper.list4count(userid, ordergoodsname, new PageBounds(page, rows));
		return new EUIPageList<Ordergoods>(pageList.getPaginator().getTotalCount(), pageList);
	}

	@Override
	public void addBuy(Ordergoods ordergoods) throws Exception {
		Goods goods = goodsService.single(ordergoods.getGoodsid());
		ordergoods.setOrdergoodsid(RandomNum.getLGID());
		ordergoods.setOrdergoodscost(goods.getGoodscost());
		ordergoods.setOrdergoodsname(goods.getGoodsname());
		ordergoods.setOrdergoodsprice(goods.getGoodsprice());
		ordergoods.setOrdergoodspercentage(goods.getGoodspercentage());
		ordergoodsMapper.insert(ordergoods);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void add(Ordergoods ordergoods) throws Exception {
		Goods goods = goodsService.single(ordergoods.getGoodsid());
		ordergoods.setOrdergoodsid(RandomNum.getLGID());
		ordergoods.setOrdergoodscost(goods.getGoodscost());
		ordergoods.setOrdergoodsname(goods.getGoodsname());
		ordergoods.setOrdergoodsprice(goods.getGoodsprice());
		ordergoods.setOrdergoodspercentage(goods.getGoodspercentage());
		ordergoodsMapper.insert(ordergoods);

		// 修改goods数量
		int count = goods.getGoodscount() - ordergoods.getOrdergoodscount();
		if (count < 0) {
			throw new Exception(goods.getGoodsname() + "超过库存，创建失败");
		}
		goods.setGoodscount(count);
		goodsService.update(goods, null);
	}

	@Override
	public void update(Ordergoods ordergoods) {
		ordergoodsMapper.update(ordergoods);
	}

	@Override
	public void batchDelete(List<String> ids) {
		ordergoodsMapper.batchDelete(ids);
	}

}
