package mall.base.dao;

import com.ch.common.mybatis.BaseMapper;
import mall.base.model.Feedback;
import mall.base.model.dto.FeedbackUserDto;

import java.util.List;

public interface FeedbackMapper extends BaseMapper<Feedback> {

	List<FeedbackUserDto> getFeedbackUserWithPage(Feedback feedback);
	
}