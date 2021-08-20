package com.atguigu.dao;

import java.util.List;
import com.atguigu.bean.MlbackSmstype;

public interface MlbackSmstypeMapper {
    int deleteByPrimaryKey(Integer smstypeId);

    int insert(MlbackSmstype record);

    int insertSelective(MlbackSmstype record);

    MlbackSmstype selectByPrimaryKey(Integer smstypeId);

    int updateByPrimaryKeySelective(MlbackSmstype record);

    int updateByPrimaryKey(MlbackSmstype record);
    
    List<MlbackSmstype> selectMlbackSmstypeById(MlbackSmstype record);

	List<MlbackSmstype> selectMlbackSmstypeGetAll();

	List<MlbackSmstype> selectMlbackSmstypeByName(MlbackSmstype mlbackSmstype);
}