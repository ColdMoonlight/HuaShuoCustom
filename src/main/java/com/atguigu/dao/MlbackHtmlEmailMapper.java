package com.atguigu.dao;

import java.util.List;
import com.atguigu.bean.MlbackHtmlEmail;

public interface MlbackHtmlEmailMapper {
    int deleteByPrimaryKey(Integer htmlemailId);

    int insert(MlbackHtmlEmail record);

    int updateByPrimaryKey(MlbackHtmlEmail record);
    
    int insertSelective(MlbackHtmlEmail record);
    
    int updateByPrimaryKeySelective(MlbackHtmlEmail record);

    MlbackHtmlEmail selectByPrimaryKey(Integer htmlemailId);

    List<MlbackHtmlEmail> selectMlbackHtmlEmailListByName(MlbackHtmlEmail record);

	List<MlbackHtmlEmail> selectMlbackHtmlEmailGetAll();
    
}