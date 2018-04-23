package mall.base.model;

import java.util.Date;

public class Goods {
    private String goodsid;

    private String goodsname;

    private Float goodscost;

    private Float goodsprice;

    private Integer goodscount;

    private Date goodstime;

    private Float goodspercentage;

    private String goodskindid;

    private String goodsimgurl;

    private String goodscontents;

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public Float getGoodscost() {
        return goodscost;
    }

    public void setGoodscost(Float goodscost) {
        this.goodscost = goodscost;
    }

    public Float getGoodsprice() {
        return goodsprice;
    }

    public void setGoodsprice(Float goodsprice) {
        this.goodsprice = goodsprice;
    }

    public Integer getGoodscount() {
        return goodscount;
    }

    public void setGoodscount(Integer goodscount) {
        this.goodscount = goodscount;
    }

    public Date getGoodstime() {
        return goodstime;
    }

    public void setGoodstime(Date goodstime) {
        this.goodstime = goodstime;
    }

    public Float getGoodspercentage() {
        return goodspercentage;
    }

    public void setGoodspercentage(Float goodspercentage) {
        this.goodspercentage = goodspercentage;
    }

    public String getGoodskindid() {
        return goodskindid;
    }

    public void setGoodskindid(String goodskindid) {
        this.goodskindid = goodskindid;
    }

    public String getGoodsimgurl() {
        return goodsimgurl;
    }

    public void setGoodsimgurl(String goodsimgurl) {
        this.goodsimgurl = goodsimgurl;
    }

    public String getGoodscontents() {
        return goodscontents;
    }

    public void setGoodscontents(String goodscontents) {
        this.goodscontents = goodscontents;
    }
}