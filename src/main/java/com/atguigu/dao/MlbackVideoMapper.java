package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MlbackVideo;

public interface MlbackVideoMapper {

    int insert(MlbackVideo record);

    MlbackVideo selectByPrimaryKey(Integer videoId);

    int updateByPrimaryKey(MlbackVideo record);
    
    
    //插入新类目
    int insertSelective(MlbackVideo record);
    //删除本条
    int deleteByPrimaryKey(Integer videoId);
    //更新本条
    int updateByPrimaryKeySelective(MlbackVideo record);
    //查询后台登陆用户
    MlbackVideo selectMlbackVideoById(MlbackVideo example);
    //查询全部登陆用户
	List<MlbackVideo> selectMlbackVideoGetAll();
	
	List<MlbackVideo> selectMlbackvideoByVideoAreaCount(MlbackVideo mlbackVideo);

	List<MlbackVideo> selectMlbackvideoByVideoArea(MlbackVideo mlbackVideo);
}