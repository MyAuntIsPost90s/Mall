package mall.base.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Orderform {
	private String orderid;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date ordertime;

	private Float orderprice;

	private String userid;

	private Integer ordertype;

	private Integer orderstatus;

	private String orderdesc;

	private String orderflow;

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public Float getOrderprice() {
		return orderprice;
	}

	public void setOrderprice(Float orderprice) {
		this.orderprice = orderprice;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Integer getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(Integer ordertype) {
		this.ordertype = ordertype;
	}

	public Integer getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(Integer orderstatus) {
		this.orderstatus = orderstatus;
	}

	public String getOrderdesc() {
		return orderdesc;
	}

	public void setOrderdesc(String orderdesc) {
		this.orderdesc = orderdesc;
	}

	public String getOrderflow() {
		return orderflow;
	}

	public void setOrderflow(String orderflow) {
		this.orderflow = orderflow;
	}
}