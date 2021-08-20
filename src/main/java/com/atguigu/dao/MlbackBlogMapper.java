package com.atguigu.dao;

import java.util.List;
import com.atguigu.bean.MlbackBlog;

public interface MlbackBlogMapper {

    int insert(MlbackBlog record);

    int updateByPrimaryKeySelective(MlbackBlog record);

    int updateByPrimaryKey(MlbackBlog record);
    
    int insertSelective(MlbackBlog record);
    
    int deleteByPrimaryKey(Integer blogId);
    
    int updateByPrimaryKeyWithBLOBs(MlbackBlog record);
    
    MlbackBlog selectByPrimaryKey(Integer blogId);

	List<MlbackBlog> selectMlbackBlogGetAll();

	List<MlbackBlog> selectMlbackBlogByParam(MlbackBlog mlbackBlog);
	
	List<MlbackBlog> selectMlbackBlogSimpleByParam(MlbackBlog mlbackBlog);
	
	List<MlbackBlog> selectMlbackBlogBack(MlbackBlog mlbackBlogReq);
}