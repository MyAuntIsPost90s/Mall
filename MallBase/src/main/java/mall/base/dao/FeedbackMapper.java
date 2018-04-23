package mall.base.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import lingshi.mybaties.mapperextend.BaseMapper;
import mall.base.model.Feedback;
import mall.base.model.dto.FeedbackUserDto;

public interface FeedbackMapper extends BaseMapper<Feedback> {

	PageList<FeedbackUserDto> getFeedbackUserWithPage(Feedback feedback, PageBounds pageBounds);
	
}