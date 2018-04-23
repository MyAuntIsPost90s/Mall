package mall.base.model;

import java.util.Date;

public class Addresslist {
    private String addresslistid;

    private String addresslistname;

    private String addresslistphone;

    private Date addresslisttime;

    public String getAddresslistid() {
        return addresslistid;
    }

    public void setAddresslistid(String addresslistid) {
        this.addresslistid = addresslistid;
    }

    public String getAddresslistname() {
        return addresslistname;
    }

    public void setAddresslistname(String addresslistname) {
        this.addresslistname = addresslistname;
    }

    public String getAddresslistphone() {
        return addresslistphone;
    }

    public void setAddresslistphone(String addresslistphone) {
        this.addresslistphone = addresslistphone;
    }

    public Date getAddresslisttime() {
        return addresslisttime;
    }

    public void setAddresslisttime(Date addresslisttime) {
        this.addresslisttime = addresslisttime;
    }
}