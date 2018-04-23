package mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mall.base.dao.GoodsimgMapper;
import mall.base.model.Goodsimg;
import mall.service.GoodsImgService;
import mall.util.RandomNum;

@Service
public class GoodsImgServiceImpl implements GoodsImgService {

	@Resource
	private GoodsimgMapper goodsimgMapper;

	@Override
	public List<Goodsimg> list(String goodsid) {
		Goodsimg condition = new Goodsimg();
		condition.setGoodsid(goodsid);
		return goodsimgMapper.getList(condition);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void update(List<String> urls, String goodsid) {
		// 先删除原有数据
		goodsimgMapper.deleteByGoodsId(goodsid);

		// 重新批量插入
		List<Goodsimg> list = new ArrayList<Goodsimg>();
		for (String url : urls) {
			Goodsimg goodsimg = new Goodsimg();
			goodsimg.setGoodsimgid(RandomNum.getLGID());
			goodsimg.setGoodsid(goodsid);
			goodsimg.setGoodsimgurl(url);
			list.add(goodsimg);
		}
		goodsimgMapper.batchInsert(list);
	}

	@Override
	public void deleteByGoodsId(String goodsid) {
		goodsimgMapper.deleteByGoodsId(goodsid);
	}

}
