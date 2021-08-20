package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MlbackEmailRichText;

public interface MlbackEmailRichTextMapper {

    int insert(MlbackEmailRichText record);
    //add
    int insertSelective(MlbackEmailRichText record);
	//delete
    int deleteByPrimaryKey(Integer emailrichtextId);
	//update
    int updateByPrimaryKeySelective(MlbackEmailRichText record);
	//select
    MlbackEmailRichText selectByPrimaryKey(Integer emailrichtextId);

    int updateByPrimaryKey(MlbackEmailRichText record);
	//select列表
	List<MlbackEmailRichText> selectMlbackEmailRichTextGetAll();
	//通过产品名查询select列表
	List<MlbackEmailRichText> selectMlbackEmailRichTextListByName(MlbackEmailRichText mlbackEmailRichTextReq);
}