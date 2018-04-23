package mall.base.model;

import java.util.Date;

public class Userinfo {
	private String userid;

	private String username;

	private String password;

	private String realname;

	private Date createtime;

	private Float wages;

	private Integer usertype;

	private String userheadimgurl;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Float getWages() {
		return wages;
	}

	public void setWages(Float wages) {
		this.wages = wages;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public String getUserheadimgurl() {
		return userheadimgurl;
	}

	public void setUserheadimgurl(String userheadimgurl) {
		this.userheadimgurl = userheadimgurl;
	}

	@Override
    public boolean equals(Object obj) {
    	if(obj instanceof Userinfo){
    		Userinfo userinfo=(Userinfo)obj;
    		if(userinfo.getUserid().equals(this.getUserid())){
    			return true;
    		}
    	}
    	return false;
    }
}