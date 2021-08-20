package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MlbackVideoShowArea;

public interface MlbackVideoShowAreaMapper {

    int insert(MlbackVideoShowArea record);

    MlbackVideoShowArea selectByPrimaryKey(Integer videoshowareaId);

    int updateByPrimaryKey(MlbackVideoShowArea record);
    
    
    //插入新类目
    int insertSelective(MlbackVideoShowArea record);
    //删除本条
    int deleteByPrimaryKey(Integer videoshowareaId);
    //更新本条
    int updateByPrimaryKeySelective(MlbackVideoShowArea record);
    //通过id查询本条
    MlbackVideoShowArea selectMlbackVideoShowAreaById(MlbackVideoShowArea example);
    //查询全部
	List<MlbackVideoShowArea> selectMlbackVideoShowAreaGetAll();

	List<MlbackVideoShowArea> selectMlbackVideoShowAreawapListByArea(MlbackVideoShowArea mlbackVideoShowArea);
	
	List<MlbackVideoShowArea> selectMlbackVideoShowAreapcListByArea(MlbackVideoShowArea mlbackVideoShowArea);
}