package mall.base.model.dto;

import mall.base.model.Feedback;
import mall.base.model.Userinfo;

public class FeedbackUserDto extends Feedback {

	private Userinfo userinfo;

	public Userinfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

}
