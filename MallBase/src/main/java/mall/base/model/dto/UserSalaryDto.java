package mall.base.model.dto;

import mall.base.model.Userinfo;

public class UserSalaryDto extends Userinfo {

	private float allprice;
	private float allcost;
	private float allcount;
	private float allpercentage;

	public float getAllcost() {
		return allcost;
	}

	public void setAllcost(float allcost) {
		this.allcost = allcost;
	}

	public float getAllcount() {
		return allcount;
	}

	public void setAllcount(float allcount) {
		this.allcount = allcount;
	}

	public float getAllpercentage() {
		return allpercentage;
	}

	public void setAllpercentage(float allpercentage) {
		this.allpercentage = allpercentage;
	}

	public float getAllprice() {
		return allprice;
	}

	public void setAllprice(float allprice) {
		this.allprice = allprice;
	}
}
