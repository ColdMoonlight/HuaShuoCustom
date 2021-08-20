package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MlbackShowArea;

public interface MlbackShowAreaMapper {

    int insert(MlbackShowArea record);

    MlbackShowArea selectByPrimaryKey(Integer showareaId);

    int updateByPrimaryKey(MlbackShowArea record);
    
    
    //插入新地区价格模板
    int insertSelective(MlbackShowArea record);
    //删除本条地区价格模板
    int deleteByPrimaryKey(Integer showareaId);
    //更新本条地区价格模板
    int updateByPrimaryKeySelective(MlbackShowArea record);
    //查询后台登陆用户00
    List<MlbackShowArea> selectMlbackShowAreaById(MlbackShowArea example);
    //查询全部登陆用户00
	List<MlbackShowArea> selectMlbackShowAreaGetAll();

	List<MlbackShowArea> selectMlbackShowAreaByNumthAndStatus(MlbackShowArea mlbackShowArea);
    
}