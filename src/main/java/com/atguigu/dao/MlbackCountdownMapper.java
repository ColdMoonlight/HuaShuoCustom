package com.atguigu.dao;

import java.util.List;
import com.atguigu.bean.MlbackCountdown;

public interface MlbackCountdownMapper {

    int insert(MlbackCountdown record);

    MlbackCountdown selectByPrimaryKey(Integer countdownId);

    int updateByPrimaryKey(MlbackCountdown record);
    
    //插入新的一条
    int insertSelective(MlbackCountdown record);
    //删除本条
    int deleteByPrimaryKey(Integer countdownId);
    //更新本条
    int updateByPrimaryKeySelective(MlbackCountdown record);
    //查询All
	List<MlbackCountdown> selectMlbackCountdownGetAll();
	//查询OneById
	List<MlbackCountdown> selectMlbackCountdownById(MlbackCountdown example);
}