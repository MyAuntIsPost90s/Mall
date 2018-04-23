package mall.base.model;

import java.util.Date;

public class Syslog {
    private String syslogid;

    private String syslogcontent;

    private Date syslogtime;

    private String userid;

    public String getSyslogid() {
        return syslogid;
    }

    public void setSyslogid(String syslogid) {
        this.syslogid = syslogid;
    }

    public String getSyslogcontent() {
        return syslogcontent;
    }

    public void setSyslogcontent(String syslogcontent) {
        this.syslogcontent = syslogcontent;
    }

    public Date getSyslogtime() {
        return syslogtime;
    }

    public void setSyslogtime(Date syslogtime) {
        this.syslogtime = syslogtime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}