package mall.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import mall.base.dao.AddresslistMapper;
import mall.base.model.Addresslist;
import mall.service.AddressListService;
import mall.uimodel.EUIPageList;
import mall.util.RandomNum;

@Service
public class AddressListServiceImpl implements AddressListService {
	@Resource
	private AddresslistMapper addresslistMapper;

	@Override
	public EUIPageList<Addresslist> list(Addresslist addresslist, int page, int rows) {
		PageList<Addresslist> pageList = addresslistMapper.getListWithPage(addresslist, new PageBounds(page, rows));
		return new EUIPageList<Addresslist>(pageList.getPaginator().getTotalCount(), pageList);
	}

	@Override
	public List<Map<String, String>> list4excelmap() {
		List<Addresslist> list = addresslistMapper.getList(new Addresslist());
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		int i = 1;
		for (Addresslist addresslist : list) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("序号", String.valueOf(i++));
			map.put("编号", addresslist.getAddresslistid());
			map.put("姓名", addresslist.getAddresslistname());
			map.put("联系方式", addresslist.getAddresslistphone());
			map.put("创建时间", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(addresslist.getAddresslisttime()));
			result.add(map);
		}
		return result;
	}

	@Override
	public String[] excelcols() {
		return new String[] { "序号", "编号", "姓名", "联系方式", "创建时间" };
	}

	@Override
	public Addresslist single(String addresslistid) {
		return addresslistMapper.getSingle(addresslistid);
	}

	@Override
	public void add(Addresslist addresslist) {
		addresslist.setAddresslistid(RandomNum.getAID());
		addresslist.setAddresslisttime(new Date());
		addresslistMapper.insert(addresslist);
	}

	@Override
	public void update(Addresslist addresslist) {
		addresslistMapper.update(addresslist);
	}

	@Override
	public void batchDelete(List<String> ids) {
		addresslistMapper.batchDelete(ids);
	}
}
