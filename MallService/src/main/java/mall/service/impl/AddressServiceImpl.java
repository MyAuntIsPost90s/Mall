package mall.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import mall.base.dao.AddressMapper;
import mall.base.model.Address;
import mall.service.AddressService;
import mall.uimodel.EUIPageList;
import mall.util.RandomNum;

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
		PageList<Address> pageList = addressMapper.getListWithPage(address, new PageBounds(page, rows));
		return new EUIPageList<Address>(pageList.getPaginator().getTotalCount(), pageList);
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
