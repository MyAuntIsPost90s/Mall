package mall.service.impl;

import mall.base.dao.AddresslistMapper;
import mall.base.model.Addresslist;
import mall.common.page.PageList;
import mall.common.page.QueryPageListProxy;
import mall.service.AddressListService;
import mall.uimodel.EUIPageList;
import mall.util.RandomNum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AddressListServiceImpl implements AddressListService {
    @Resource
    private AddresslistMapper addresslistMapper;

    @Override
    public EUIPageList<Addresslist> list(Addresslist addresslist, int page, int rows) {
        PageList<Addresslist> pageList = QueryPageListProxy.query(() -> addresslistMapper.getList(addresslist), page, rows);
        return new EUIPageList<>(pageList.getTotal(), pageList.getRows());
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
        return new String[]{"序号", "编号", "姓名", "联系方式", "创建时间"};
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
