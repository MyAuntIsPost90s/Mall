package mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import mall.base.dao.GoodskindMapper;
import mall.base.model.Goodskind;
import mall.service.GoodsKindService;
import mall.uimodel.EUIPageList;
import mall.uimodel.EUITree;
import mall.util.RandomNum;

@Service
public class GoodsKindServiceImpl implements GoodsKindService {

	@Resource
	private GoodskindMapper goodskindMapper;

	@Override
	public Goodskind single(String goodskindid) {
		return goodskindMapper.getSingle(goodskindid);
	}

	@Override
	public EUIPageList<Goodskind> list(Goodskind goodskind, int page, int rows) {
		PageList<Goodskind> pageList = goodskindMapper.getListWithPage(goodskind, new PageBounds(page, rows));
		return new EUIPageList<Goodskind>(pageList.getPaginator().getTotalCount(), pageList);
	}

	@Override
	public List<EUITree> tree(Goodskind goodskind) {
		EUITree root = new EUITree();
		root.setId("-1");
		root.setText("分类");
		List<Goodskind> list = goodskindMapper.getList(goodskind);
		List<EUITree> childrens = new ArrayList<EUITree>();
		for (Goodskind item : list) {
			EUITree node = new EUITree();
			node.setId(item.getGoodskindid());
			node.setText(item.getGoodskindname());
			childrens.add(node);
		}
		root.setChildren(childrens);

		List<EUITree> result = new ArrayList<EUITree>();
		result.add(root);
		return result;
	}

	@Override
	public void add(Goodskind goodskind) {
		goodskind.setGoodskindid(RandomNum.getLGID());
		goodskindMapper.insert(goodskind);
	}

	@Override
	public void update(Goodskind goodskind) {
		goodskindMapper.update(goodskind);
	}

	@Override
	public void batchDelete(List<String> ids) {
		goodskindMapper.batchDelete(ids);
	}

}
