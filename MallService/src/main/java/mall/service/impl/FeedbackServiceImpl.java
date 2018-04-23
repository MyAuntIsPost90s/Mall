package mall.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import mall.base.dao.FeedbackMapper;
import mall.base.model.Feedback;
import mall.base.model.dto.FeedbackUserDto;
import mall.service.FeedbackService;
import mall.uimodel.EUIPageList;
import mall.util.RandomNum;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Resource
	private FeedbackMapper feedbackMapper;

	@Override
	public EUIPageList<FeedbackUserDto> list(Feedback feedback, int page, int rows) {
		PageList<FeedbackUserDto> pageList = feedbackMapper.getFeedbackUserWithPage(feedback,
				new PageBounds(page, rows));
		return new EUIPageList<FeedbackUserDto>(pageList.getPaginator().getTotalCount(), pageList);
	}

	@Override
	public void batchDelete(List<String> ids) {
		feedbackMapper.batchDelete(ids);
	}

	@Override
	public void add(Feedback feedback, String userid) {
		feedback.setFeedbacktime(new Date());
		feedback.setFeedbackid(RandomNum.getLGID());
		feedback.setUserid(userid);
		feedbackMapper.insert(feedback);
	}

}
