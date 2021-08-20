package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MlbackProductImg;

public interface MlbackProductImgMapper {

    int insert(MlbackProductImg record);

    MlbackProductImg selectByPrimaryKey(Integer productimgId);

    int updateByPrimaryKey(MlbackProductImg record);
    
    
    //插入新图片00
    int insertSelective(MlbackProductImg record);
    //删除本条图片00
    int deleteByPrimaryKey(Integer productimgId);
    //更新本条图片00
    int updateByPrimaryKeySelective(MlbackProductImg record);
    //条件查询图片00
    List<MlbackProductImg> selectMlbackProductImgByConditionS(MlbackProductImg example);
    //查询所有图片00
	List<MlbackProductImg> selectMlbackProductImgGetAll();
	
	//查询本产品下的多张图00
	List<MlbackProductImg> selectMlbackProductImgByProductId(Integer productId);
	
	//查询本条图片否存在
	List<MlbackProductImg> selectMlbackProductImgByPIdAndImgSort(MlbackProductImg example);
	
	List<MlbackProductImg> selectMlbackProductImgByProductImgId(Integer productimgId);
}