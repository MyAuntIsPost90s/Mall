package mall.base.model;

import java.util.Date;

public class Goodskind {
    private String goodskindid;

    private String goodskindname;

    private Date goodskindtime;

    private String goodskindnote;

    public String getGoodskindid() {
        return goodskindid;
    }

    public void setGoodskindid(String goodskindid) {
        this.goodskindid = goodskindid;
    }

    public String getGoodskindname() {
        return goodskindname;
    }

    public void setGoodskindname(String goodskindname) {
        this.goodskindname = goodskindname;
    }

    public Date getGoodskindtime() {
        return goodskindtime;
    }

    public void setGoodskindtime(Date goodskindtime) {
        this.goodskindtime = goodskindtime;
    }

    public String getGoodskindnote() {
        return goodskindnote;
    }

    public void setGoodskindnote(String goodskindnote) {
        this.goodskindnote = goodskindnote;
    }
}