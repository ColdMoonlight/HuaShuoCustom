package com.atguigu.dao;

import java.util.List;
import com.atguigu.bean.MlbackPageArea;

public interface MlbackPageAreaMapper {

    int insert(MlbackPageArea record);

    MlbackPageArea selectByPrimaryKey(Integer pageareaId);

    int updateByPrimaryKey(MlbackPageArea record);
    
    //-------------------------------
    int insertSelective(MlbackPageArea record);
    
    int deleteByPrimaryKey(Integer pageareaId);
    
    int updateByPrimaryKeySelective(MlbackPageArea record);
    
	MlbackPageArea selectMlbackPageAreaById(MlbackPageArea mlbackPageArea);

	List<MlbackPageArea> selectMlbackPageAreaGetAll();

	List<MlbackPageArea> selectHomepageByStatus(MlbackPageArea mlbackPageAreaReq);
	
	List<MlbackPageArea> selectHomepageByPcStatus(MlbackPageArea mlbackPageAreaReq);

	List<MlbackPageArea> selectMlbackPageAreaBackSearch(MlbackPageArea mlbackPageAreaReq);
}