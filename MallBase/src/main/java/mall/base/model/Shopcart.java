package mall.base.model;

public class Shopcart {
    private String shopcartid;

    private String userid;

    private String goodsid;

    private Integer shopcartcount;

    public String getShopcartid() {
        return shopcartid;
    }

    public void setShopcartid(String shopcartid) {
        this.shopcartid = shopcartid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    public Integer getShopcartcount() {
        return shopcartcount;
    }

    public void setShopcartcount(Integer shopcartcount) {
        this.shopcartcount = shopcartcount;
    }
}