package com.atguigu.dao;

import java.util.List;
import com.atguigu.bean.MlbackProductTogether;

public interface MlbackProductTogetherMapper {

    int insert(MlbackProductTogether record);

    MlbackProductTogether selectByPrimaryKey(Integer producttogetherId);

    int updateByPrimaryKey(MlbackProductTogether record);
    
	//-----------------------------------------------------------/
    int insertSelective(MlbackProductTogether record);
    
    int deleteByPrimaryKey(Integer producttogetherId);
    
    int updateByPrimaryKeySelective(MlbackProductTogether record);
    
	List<MlbackProductTogether> selectMlbackProductTogetherGetAll();

	List<MlbackProductTogether> selectMlbackProductTogetherById(MlbackProductTogether MlbackProductTogether);

	List<MlbackProductTogether> selectMlbackProductTogetherBackSearch(MlbackProductTogether MlbackProductTogether);

	List<MlbackProductTogether> selectMlbackProTogetherDownLoadByParam(MlbackProductTogether mlbackProductTogetherReq);
}