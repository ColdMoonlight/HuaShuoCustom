package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlbackReviewImg;
import com.atguigu.dao.MlbackReviewImgMapper;

@Service
public class MlbackReviewImgService {
	
	@Autowired
	MlbackReviewImgMapper mlbackReviewImgMapper;
	
	/**
	 * @author Shinelon
	 * @param MlbackReviewImg
	 * @exception add方法MlbackReviewImg
	 * */
	public int insertSelective(MlbackReviewImg mlbackReviewImg) {
		int intReslut = mlbackReviewImgMapper.insertSelective(mlbackReviewImg);
		return intReslut;
	}

	/**
	 * @author Shinelon
	 * @param MlbackReviewImg
	 * @exception 删除本条MlbackReviewImg
	 * */
	public int deleteByPrimaryKey(int productimgId) {
		int  intReslut = mlbackReviewImgMapper.deleteByPrimaryKey(productimgId);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条MlbackReviewImg
	 * */
	public int updateByPrimaryKeySelective(MlbackReviewImg mlbackReviewImg) {
		int  intReslut = mlbackReviewImgMapper.updateByPrimaryKeySelective(mlbackReviewImg);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部MlbackReviewImg信息
	 * */
	public List<MlbackReviewImg> selectMlbackReviewImgByReviewId(Integer reviewimgId) {
		List<MlbackReviewImg>  MlbackReviewImgList = mlbackReviewImgMapper.selectMlbackReviewImgByReviewId(reviewimgId);
		return MlbackReviewImgList;
	}

	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部MlbackReviewImg信息
	 * */
	public List<MlbackReviewImg> selectMlbackReviewImgByRIdAndImgSort(MlbackReviewImg mlbackReviewImg) {
		List<MlbackReviewImg>  MlbackReviewImgList = mlbackReviewImgMapper.selectMlbackReviewImgByRIdAndImgSort(mlbackReviewImg);
		return MlbackReviewImgList;
	}
	
	/**
	 * @author Shinelon
	 * @param MlbackReviewImg
	 * @exception 删除本条MlbackReviewImg
	 * */
	public int deleteByreviewId(int reviewId) {
		int  intReslut = mlbackReviewImgMapper.deleteByreviewId(reviewId);
		return intReslut;
	}
	
}
