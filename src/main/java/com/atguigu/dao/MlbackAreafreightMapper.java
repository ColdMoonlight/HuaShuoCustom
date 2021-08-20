package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MlbackAreafreight;

public interface MlbackAreafreightMapper {

    int insert(MlbackAreafreight record);

    MlbackAreafreight selectByPrimaryKey(Integer areafreightId);

    int updateByPrimaryKey(MlbackAreafreight record);
    
    //插入新地区价格模板
    int insertSelective(MlbackAreafreight record);
    //删除本条地区价格模板
    int deleteByPrimaryKey(Integer areafreightId);
    //更新本条地区价格模板
    int updateByPrimaryKeySelective(MlbackAreafreight record);
    //查询全部登陆用户00
	List<MlbackAreafreight> selectMlbackAreafreightGetAll();
	//条件查询
	List<MlbackAreafreight> selectMlbackAreafreightByParam(MlbackAreafreight example);
    
}