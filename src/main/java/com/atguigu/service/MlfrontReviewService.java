package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.MlfrontReview;
import com.atguigu.dao.MlfrontReviewMapper;


@Service
public class MlfrontReviewService {
	
	
	@Autowired
	MlfrontReviewMapper mlfrontReviewMapper;

	/**1
	 * @author Shinelon
	 * @param MlfrontReview
	 * @exception add方法用户信息是否存在
	 * 
	 * */
	public int insertSelective(MlfrontReview mlfrontReview) {
		int intReslut = mlfrontReviewMapper.insertSelective(mlfrontReview);
		return intReslut;
	}
	
	/**2
	 * @author Shinelon
	 * @param 
	 * @exception 删除本条信息
	 * 
	 * */
	public int deleteByPrimaryKey(int reviewId) {
		int  intReslut = mlfrontReviewMapper.deleteByPrimaryKey(reviewId);
		return intReslut;
	}
	

	/**3
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条信息
	 * 
	 * */
	public int updateByPrimaryKeySelective(MlfrontReview mlfrontReview) {
		int  intReslut = mlfrontReviewMapper.updateByPrimaryKeySelective(mlfrontReview);
		return intReslut;
	}
	
	/**4
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 
	 * */
	public List<MlfrontReview> selectMlfrontReviewById(MlfrontReview mlfrontReview) {
		List<MlfrontReview>  MlfrontReviewList = mlfrontReviewMapper.selectMlfrontReviewById(mlfrontReview);
		return MlfrontReviewList;
	}
	
	/**5
	 * @author Shinelon
	 * @param MlfrontReview
	 * @exception 查看全部的地址信息
	 * 
	 * */
	public List<MlfrontReview> selectMlfrontReviewAll() {
		List<MlfrontReview>  MlfrontReviewList = mlfrontReviewMapper.selectMlfrontReviewAll();
		return MlfrontReviewList;
	}
	/**6
	 * @author Shinelon
	 * @param MlfrontReview
	 * @exception 查看全部的地址信息
	 * 
	 * */
	public List<MlfrontReview> selectMlfrontReviewListByPId(MlfrontReview mlfrontReview) {
		List<MlfrontReview>  MlfrontReviewList = mlfrontReviewMapper.selectMlfrontReviewListByPId(mlfrontReview);
		return MlfrontReviewList;
	}
	/**7
	 * @author Shinelon
	 * @param selectMlfrontReviewListBySearch
	 * @exception 条件搜索
	 * 
	 * */
	public List<MlfrontReview> selectMlbackReviewListBySearch(MlfrontReview mlfrontReview) {
		List<MlfrontReview>  MlfrontReviewList = mlfrontReviewMapper.selectMlbackReviewListBySearch(mlfrontReview);
		return MlfrontReviewList;
	}

	public List<MlfrontReview> selectReviewListFrom(MlfrontReview mlfrontReview) {
		List<MlfrontReview>  MlfrontReviewList = mlfrontReviewMapper.selectReviewListFrom(mlfrontReview);
		return MlfrontReviewList;
	}
	
}
