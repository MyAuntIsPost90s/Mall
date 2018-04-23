package mall.service;

import java.util.List;

import mall.base.model.Feedback;
import mall.base.model.dto.FeedbackUserDto;
import mall.uimodel.EUIPageList;

public interface FeedbackService {

	/**
	 * 分页获取集合
	 * 
	 * @param feedback
	 * @param page
	 * @param rows
	 * @return
	 */
	EUIPageList<FeedbackUserDto> list(Feedback feedback, int page, int rows);

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	void batchDelete(List<String> ids);

	/**
	 * 添加
	 * 
	 * @param feedback
	 */
	void add(Feedback feedback, String userid);

}
