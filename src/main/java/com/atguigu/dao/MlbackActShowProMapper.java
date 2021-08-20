package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MlbackActShowPro;

public interface MlbackActShowProMapper {

    int insert(MlbackActShowPro record);

    MlbackActShowPro selectByPrimaryKey(Integer actshowproId);

    int updateByPrimaryKey(MlbackActShowPro record);
    
    //插入新类目
    int insertSelective(MlbackActShowPro record);
    //删除本条
    int deleteByPrimaryKey(Integer groupId);
    //更新本条
    int updateByPrimaryKeySelective(MlbackActShowPro record);
    //查询后台登陆用户
    MlbackActShowPro selectMlbackActShowProById(MlbackActShowPro example);
    //查询全部登陆用户
	List<MlbackActShowPro> selectMlbackActShowProGetAll();

	List<MlbackActShowPro> selectMlbackActShowProByActnum(MlbackActShowPro mlbackActShowPro);
}