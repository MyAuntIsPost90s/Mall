package mall.service.impl;

import mall.base.dao.GoodsMapper;
import mall.base.model.Goods;
import mall.base.model.Ordergoods;
import mall.base.model.dto.GoodsDto;
import mall.common.page.PageList;
import mall.common.page.QueryPageListProxy;
import mall.service.GoodsImgService;
import mall.service.GoodsKindService;
import mall.service.GoodsService;
import mall.service.OrderFormService;
import mall.uimodel.EUIPageList;
import mall.util.RandomNum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private GoodsKindService goodsKindService;
    @Resource
    private GoodsImgService goodsImgService;
    @Resource
    private OrderFormService orderFormService;

    @Override
    public GoodsDto single(String goodsid) {
        Goods goods = goodsMapper.getSingle(goodsid);
        GoodsDto goodsDto = new GoodsDto(goods);
        goodsDto.setGoodskind(goodsKindService.single(goods.getGoodskindid()));
        goodsDto.setGoodsimgs(goodsImgService.list(goods.getGoodsid()));
        return goodsDto;
    }

    @Override
    public EUIPageList<GoodsDto> list(Goods goods, int page, int rows) {
        PageList<Goods> pageList = QueryPageListProxy.query(() -> goodsMapper.getList(goods), page, rows);
        List<GoodsDto> result = new ArrayList<GoodsDto>();
        for (Goods item : pageList.getRows()) {
            GoodsDto goodsDto = new GoodsDto(item);
            goodsDto.setGoodskind(goodsKindService.single(item.getGoodskindid()));
            goodsDto.setGoodsimgs(goodsImgService.list(item.getGoodsid()));
            result.add(goodsDto);
        }
        return new EUIPageList<>(pageList.getTotal(), result);
    }

    @Override
    public EUIPageList<GoodsDto> list4search(String goodsname, String goodskindid, int orderType, int page, int rows) {
        PageList<Goods> pageList = QueryPageListProxy.query(() -> goodsMapper.searchWithPage(goodsname, goodskindid, orderType), page, rows);
        List<GoodsDto> result = new ArrayList<GoodsDto>();
        for (Goods item : pageList.getRows()) {
            GoodsDto goodsDto = new GoodsDto(item);
            goodsDto.setGoodskind(goodsKindService.single(item.getGoodskindid()));
            goodsDto.setGoodsimgs(goodsImgService.list(item.getGoodsid()));
            result.add(goodsDto);
        }
        return new EUIPageList<>(pageList.getTotal(), result);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void add(Goods goods, String urls) {
        goods.setGoodsid(RandomNum.getLGID());
        goods.setGoodstime(new Date());
        goodsMapper.insert(goods);

        if (urls != null && !urls.isEmpty()) { // 当图片存在时先保存图片到数据库
            String[] urlList = urls.split(",");
            goodsImgService.update(Arrays.asList(urlList), goods.getGoodsid());
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void update(Goods goods, String urls) {
        if (urls != null && !urls.isEmpty()) { // 当图片存在时先保存图片到数据库
            String[] urlList = urls.split(",");
            goodsImgService.update(Arrays.asList(urlList), goods.getGoodsid());
        }
        goodsMapper.update(goods);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void update4count(Goods goods, String userid) throws Exception {
        // 修改数量
        Goods updateGoods = goodsMapper.getSingle(goods.getGoodsid());
        updateGoods.setGoodscount(updateGoods.getGoodscount() + goods.getGoodscount());
        goodsMapper.update(updateGoods);
        // 加入订单
        List<Ordergoods> list = new ArrayList<Ordergoods>();
        Ordergoods ordergoods = new Ordergoods();
        ordergoods.setOrdergoodsid(RandomNum.getLGID());
        ordergoods.setGoodsid(updateGoods.getGoodsid());
        ordergoods.setOrdergoodscost(updateGoods.getGoodscost());
        ordergoods.setOrdergoodscount(goods.getGoodscount());
        ordergoods.setOrdergoodsimgurl(updateGoods.getGoodsimgurl());
        ordergoods.setOrdergoodsname(updateGoods.getGoodsname());
        ordergoods.setOrdergoodspercentage(updateGoods.getGoodspercentage());
        ordergoods.setOrdergoodsprice(updateGoods.getGoodsprice());
        list.add(ordergoods);
        orderFormService.addBuyLogOrder(list, userid);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void batchDelete(List<String> ids) {
        for (String id : ids) {
            goodsImgService.deleteByGoodsId(id);
        }
        goodsMapper.batchDelete(ids);
    }
}
