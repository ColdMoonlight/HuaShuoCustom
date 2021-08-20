package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MlbackReviewImg;

public interface MlbackReviewImgMapper {

    int insert(MlbackReviewImg record);

    MlbackReviewImg selectByPrimaryKey(Integer reviewimgId);

    int updateByPrimaryKey(MlbackReviewImg record);
    
    //插入新图片00
    int insertSelective(MlbackReviewImg record);
    //删除本条图片00
    int deleteByPrimaryKey(Integer reviewimgId);
    //更新本条图片00
    int updateByPrimaryKeySelective(MlbackReviewImg record);
	//查询本产品下的多张图00
	List<MlbackReviewImg> selectMlbackReviewImgByReviewId(Integer reviewimgId);
	//查询本条图片否存在
	List<MlbackReviewImg> selectMlbackReviewImgByRIdAndImgSort(MlbackReviewImg record);

	int deleteByreviewId(int reviewId);
}