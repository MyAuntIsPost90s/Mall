package mall.service.impl;

import mall.base.dao.AddressMapper;
import mall.base.model.Address;
import mall.common.page.PageList;
import mall.common.page.QueryPageListProxy;
import mall.service.AddressService;
import mall.uimodel.EUIPageList;
import mall.util.RandomNum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressMapper addressMapper;

    @Override
    public Address single(String addressid) {
        return addressMapper.getSingle(addressid);
    }

    @Override
    public EUIPageList<Address> list(Address address, int page, int rows) {
        PageList<Address> pageList = QueryPageListProxy.query(() -> addressMapper.getList(address), page, rows);
        return new EUIPageList<Address>(pageList.getTotal(), pageList.getRows());
    }

    @Override
    public void add(Address address) {
        address.setAddressid(RandomNum.getAID());
        addressMapper.insert(address);
    }

    @Override
    public void update(Address address) {
        addressMapper.update(address);
    }

    @Override
    public void batchDelete(List<String> ids) {
        addressMapper.batchDelete(ids);
    }

}
