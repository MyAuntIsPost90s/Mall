package mall.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import mall.base.dao.OrderformMapper;
import mall.base.model.Address;
import mall.base.model.Goods;
import mall.base.model.Orderform;
import mall.base.model.Ordergoods;
import mall.base.model.Shopcart;
import mall.base.model.dto.OrderWithGoodsDto;
import mall.base.model.dto.ShopcartGoodsDto;
import mall.common.OrderEnums.OrderStatus;
import mall.common.OrderEnums.OrderType;
import mall.service.AddressService;
import mall.service.GoodsService;
import mall.service.OrderFormService;
import mall.service.OrderGoodsService;
import mall.service.ShopcartService;
import mall.uimodel.EUIPageList;
import mall.util.RandomNum;

@Service
public class OrderFormServiceImpl implements OrderFormService {

	@Resource
	private OrderformMapper orderformMapper;
	@Resource
	private OrderGoodsService orderGoodsService;
	@Resource
	private ShopcartService shopcartService;
	@Resource
	private AddressService addressService;
	@Resource
	private GoodsService goodsService;

	@Override
	public Orderform single(String orderformid) {
		return orderformMapper.getSingle(orderformid);
	}

	@Override
	public EUIPageList<Orderform> list(Orderform orderform, int page, int rows) {
		PageList<Orderform> pageList = orderformMapper.getListWithPage(orderform, new PageBounds(page, rows));
		return new EUIPageList<Orderform>(pageList.getPaginator().getTotalCount(), pageList);
	}

	@Override
	public EUIPageList<OrderWithGoodsDto> orderWithGoods(Orderform orderform, int page, int rows) {
		PageList<Orderform> pageList = orderformMapper.getListWithPage(orderform, new PageBounds(page, rows));
		List<OrderWithGoodsDto> list = new ArrayList<OrderWithGoodsDto>();
		for (Orderform item : pageList) {
			Ordergoods condition = new Ordergoods();
			condition.setOrderid(item.getOrderid());
			List<Ordergoods> ordergoods = orderGoodsService.list(condition, 1, 999).getRows();
			OrderWithGoodsDto orderWithGoodsDto = new OrderWithGoodsDto();
			orderWithGoodsDto.setOrderdesc(item.getOrderdesc());
			orderWithGoodsDto.setOrdergoods(ordergoods);
			orderWithGoodsDto.setOrderid(item.getOrderid());
			orderWithGoodsDto.setOrderprice(item.getOrderprice());
			orderWithGoodsDto.setOrderstatus(item.getOrderstatus());
			orderWithGoodsDto.setOrdertime(item.getOrdertime());
			orderWithGoodsDto.setOrdertype(item.getOrdertype());
			list.add(orderWithGoodsDto);
		}
		return new EUIPageList<OrderWithGoodsDto>(pageList.getPaginator().getTotalCount(), list);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void addLogOrder(List<Ordergoods> ordergoods, String userid) throws Exception {
		Orderform orderform = new Orderform();
		orderform.setOrderid(RandomNum.getLGID());
		// 生成订单详情
		float sum = 0f;
		for (Ordergoods item : ordergoods) {
			Goods goods = goodsService.single(item.getGoodsid());
			item.setOrdergoodsimgurl(goods.getGoodsimgurl());
			item.setOrderid(orderform.getOrderid());
			orderGoodsService.add(item);
			sum += item.getOrdergoodscount() * item.getOrdergoodsprice();
		}
		// 生成订单
		orderform.setOrdertime(new Date());
		orderform.setOrderprice(sum);
		orderform.setUserid(userid);
		orderform.setOrderdesc(OrderType.LOG.valueZh);
		orderform.setOrdertype(OrderType.LOG.value);
		orderform.setOrderstatus(OrderStatus.SEND.value);
		orderformMapper.insert(orderform);
	}

	@Override
	public void addBuyLogOrder(List<Ordergoods> ordergoods, String userid) throws Exception {
		Orderform orderform = new Orderform();
		orderform.setOrderid(RandomNum.getLGID());
		// 生成订单详情
		float sum = 0f;
		for (Ordergoods item : ordergoods) {
			Goods goods = goodsService.single(item.getGoodsid());
			item.setOrdergoodsimgurl(goods.getGoodsimgurl());
			item.setOrderid(orderform.getOrderid());
			orderGoodsService.addBuy(item);
			sum += item.getOrdergoodscount() * item.getOrdergoodsprice();
		}
		// 生成订单
		orderform.setOrdertime(new Date());
		orderform.setOrderprice(sum);
		orderform.setUserid(userid);
		orderform.setOrderdesc(OrderType.LOG.valueZh);
		orderform.setOrdertype(OrderType.LOG.value);
		orderform.setOrderstatus(OrderStatus.SEND.value);
		orderformMapper.insert(orderform);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void addUserOrder(String userid, String addressid) throws Exception {
		Shopcart condition = new Shopcart();
		condition.setUserid(userid);
		List<ShopcartGoodsDto> shopcartGoodsDtos = shopcartService.list(condition, 1, 9999).getRows();
		Orderform orderform = new Orderform();
		orderform.setOrderid(RandomNum.getLGID());
		// 生成订单详情
		float sum = 0f;
		for (ShopcartGoodsDto item : shopcartGoodsDtos) {
			Ordergoods ordergoods = new Ordergoods();
			Goods goods = goodsService.single(item.getGoodsid());
			ordergoods.setOrdergoodsimgurl(goods.getGoodsimgurl());
			ordergoods.setGoodsid(item.getGoodsid());
			ordergoods.setOrdergoodscount(item.getShopcartcount());
			ordergoods.setOrdergoodscost(item.getGoods().getGoodscost());
			ordergoods.setOrdergoodsname(item.getGoods().getGoodsname());
			ordergoods.setOrdergoodspercentage(item.getGoods().getGoodspercentage());
			ordergoods.setOrdergoodsprice(item.getGoods().getGoodsprice());
			ordergoods.setOrderid(orderform.getOrderid());
			ordergoods.setOrdergoodsid(RandomNum.getLGID());
			orderGoodsService.add(ordergoods);
			sum += ordergoods.getOrdergoodscount() * ordergoods.getOrdergoodsprice();
		}
		// 生成订单
		Address address = addressService.single(addressid);
		if (address == null) {
			throw new Exception("地址不存在");
		}
		orderform.setOrdertime(new Date());
		orderform.setOrderprice(sum);
		orderform.setUserid(userid);
		orderform.setOrderdesc(String.format("收件人：%s，联系方式：%s，收货地址：%s", address.getAddressname(),
				address.getAddressphone(), address.getAddresscontent()));
		orderform.setOrdertype(OrderType.USER.value);
		orderform.setOrderstatus(OrderStatus.UNSEND.value);
		orderformMapper.insert(orderform);

		// 移除订单
		List<String> ids = new ArrayList<String>();
		for (ShopcartGoodsDto item : shopcartGoodsDtos) {
			ids.add(item.getShopcartid());
		}
		shopcartService.batchDelete(ids);
	}

	@Override
	public void update(Orderform orderform) {
		orderformMapper.update(orderform);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void batchDelete(List<String> ids) {
		List<String> orderids = new ArrayList<String>();
		Ordergoods condition = new Ordergoods();
		for (String id : ids) {
			condition.setOrderid(id);
			EUIPageList<Ordergoods> list = orderGoodsService.list(condition, 1, 9999);
			if (list.getTotal() < 1) {
				continue;
			}
			for (Ordergoods ordergoods : list.getRows()) {
				orderids.add(ordergoods.getOrdergoodsid());
			}
		}
		if (orderids != null && orderids.size() > 0) {
			orderGoodsService.batchDelete(orderids);
		}
		orderformMapper.batchDelete(ids);
	}
}
