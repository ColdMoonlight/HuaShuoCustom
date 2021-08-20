package com.atguigu.dao;

import java.util.List;
import com.atguigu.bean.MlbackProductSku;

public interface MlbackProductSkuMapper {

    int insert(MlbackProductSku record);

    MlbackProductSku selectByPrimaryKey(Integer productskuId);

    int updateByPrimaryKey(MlbackProductSku record);
    
    //插入新类目
    int insertSelective(MlbackProductSku record);
    //删除本条图片00    
    int deleteByPrimaryKey(Integer productskuId);
    //更新本条图片00
    int updateByPrimaryKeySelective(MlbackProductSku record);
    //selectMlbackProductSkuByPId
    List<MlbackProductSku> selectMlbackProductSkuListByPId(MlbackProductSku record);
	//selectMlbackProductSkuListByPIdTogether
	List<MlbackProductSku> selectMlbackProductSkuListByPIdTogether(MlbackProductSku mlbackProductSku);
	//文件
	List<MlbackProductSku> selectMlProductSkuListByPskuCode(MlbackProductSku mlbackProductSkuReq);
}