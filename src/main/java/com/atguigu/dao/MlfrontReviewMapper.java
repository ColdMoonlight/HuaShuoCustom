package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MlfrontReview;

public interface MlfrontReviewMapper {

    int insert(MlfrontReview record);

    MlfrontReview selectByPrimaryKey(Integer reviewId);

    int updateByPrimaryKey(MlfrontReview record);
    
    //插入新类目00
    int insertSelective(MlfrontReview record);
    //删除本条00
    int deleteByPrimaryKey(Integer reviewId);
    //更新本条00
    int updateByPrimaryKeySelective(MlfrontReview record);
    //通过id查询
    List<MlfrontReview> selectMlfrontReviewById(MlfrontReview example);
    //查询全部
    List<MlfrontReview> selectMlfrontReviewAll();
    //查询一个产品下的全部review
	List<MlfrontReview> selectMlfrontReviewListByPId(MlfrontReview mlfrontReview);
	//条件搜索selectMlfrontReviewListBySearch
	List<MlfrontReview> selectMlbackReviewListBySearch(MlfrontReview mlfrontReview);
	//查询从不同来源的地方selectReviewListFrom
	List<MlfrontReview> selectReviewListFrom(MlfrontReview mlfrontReview);
}