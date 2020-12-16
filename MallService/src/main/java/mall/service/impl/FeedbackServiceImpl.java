package mall.service.impl;

import mall.base.dao.FeedbackMapper;
import mall.base.model.Feedback;
import mall.base.model.dto.FeedbackUserDto;
import mall.common.page.PageList;
import mall.common.page.QueryPageListProxy;
import mall.service.FeedbackService;
import mall.uimodel.EUIPageList;
import mall.util.RandomNum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Resource
    private FeedbackMapper feedbackMapper;

    @Override
    public EUIPageList<FeedbackUserDto> list(Feedback feedback, int page, int rows) {
        PageList<FeedbackUserDto> pageList = QueryPageListProxy.query(() -> feedbackMapper.getFeedbackUserWithPage(feedback), page, rows);
        return new EUIPageList<>(pageList.getTotal(), pageList.getRows());
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
