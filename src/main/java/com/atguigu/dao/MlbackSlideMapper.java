package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MlbackSlide;

public interface MlbackSlideMapper {

    int insert(MlbackSlide record);

    MlbackSlide selectByPrimaryKey(Integer slideId);

    int updateByPrimaryKey(MlbackSlide record);
    
    //插入新类目
    int insertSelective(MlbackSlide record);
    //删除本条
    int deleteByPrimaryKey(Integer slideId);
    //更新本条
    int updateByPrimaryKeySelective(MlbackSlide record);
    //通过id查询本条
    MlbackSlide selectMlbackSlideById(MlbackSlide example);
    //查询全部
	List<MlbackSlide> selectMlbackSlideGetAll();

	List<MlbackSlide> selectMlbackSlidewapListByArea(MlbackSlide mlbackSlide);
	
	List<MlbackSlide> selectMlbackSlidepcListByArea(MlbackSlide mlbackSlide);
    
}