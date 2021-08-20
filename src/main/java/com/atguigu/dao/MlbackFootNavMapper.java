package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MlbackFootNav;

public interface MlbackFootNavMapper {

    int insert(MlbackFootNav record);

    MlbackFootNav selectByPrimaryKey(Integer footnavId);

    int updateByPrimaryKeyWithBLOBs(MlbackFootNav record);

    int updateByPrimaryKey(MlbackFootNav record);
    
    //插入新类目00
    int insertSelective(MlbackFootNav record);
    //删除本条00
    int deleteByPrimaryKey(Integer footnavId);
    //更新本条00
    int updateByPrimaryKeySelective(MlbackFootNav record);
    //查询后台登陆用户00
    List<MlbackFootNav> selectMlbackFootNavOneAllById(MlbackFootNav example);
    //查询全部登陆用户00
	List<MlbackFootNav> selectMlbackFootNavGetAll();
	//简单查询底部导航
	List<MlbackFootNav> selectMlbackFootNavAllSimpleIfShow(MlbackFootNav mlbackFootNav);
	//类目超级搜索
	List<MlbackFootNav> selectMlbackFootNavBackSearch(MlbackFootNav mlbackFootNavReq);

	List<MlbackFootNav> selectMlbackFootNavBySeo(MlbackFootNav mlbackFootNavReq);
}