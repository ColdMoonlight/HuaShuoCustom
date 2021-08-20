package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.CustomerWholesale;
import com.atguigu.bean.MlfrontProductQa;

public interface MlfrontProductQaMapper {
    int deleteByPrimaryKey(Integer productqaId);

    int insert(MlfrontProductQa record);

    int insertSelective(MlfrontProductQa record);

    MlfrontProductQa selectByPrimaryKey(Integer productqaId);

    int updateByPrimaryKeySelective(MlfrontProductQa mlfrontProductQa);

    int updateByPrimaryKey(MlfrontProductQa record);
    
    List<MlfrontProductQa> selectMlfrontProductQaGetAll();
    
    List<MlfrontProductQa> selectMlfrontProductQaById(MlfrontProductQa mlfrontProductQa);
}