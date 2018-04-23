package mall.base.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Feedback {
    private String feedbackid;

    private String feedbackcontent;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date feedbacktime;

    private String userid;

    public String getFeedbackid() {
        return feedbackid;
    }

    public void setFeedbackid(String feedbackid) {
        this.feedbackid = feedbackid;
    }

    public String getFeedbackcontent() {
        return feedbackcontent;
    }

    public void setFeedbackcontent(String feedbackcontent) {
        this.feedbackcontent = feedbackcontent;
    }

    public Date getFeedbacktime() {
        return feedbacktime;
    }

    public void setFeedbacktime(Date feedbacktime) {
        this.feedbacktime = feedbacktime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}