package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MlbackProductAttributeName;

public interface MlbackProductAttributeNameMapper {

    int insert(MlbackProductAttributeName record);

    MlbackProductAttributeName selectByPrimaryKey(Integer productattrnameId);
    
    int updateByPrimaryKey(MlbackProductAttributeName record);
    
    //插入新类目
    int insertSelective(MlbackProductAttributeName record);
    //删除本条图片00    
    int deleteByPrimaryKey(Integer productattrnameId);
    //更新本条图片00    
    int updateByPrimaryKeySelective(MlbackProductAttributeName record);
    //selectMlbackProductAttributeNameByProductId
	List<MlbackProductAttributeName> selectMlbackProductAttributeNameByProductId(Integer productId);
}