package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MlfrontPayInfo;

public interface MlfrontPayInfoMapper {

    int insert(MlfrontPayInfo record);

    MlfrontPayInfo selectByPrimaryKey(Integer payinfoId);

    int updateByPrimaryKey(MlfrontPayInfo record);
    
    //插入新类目00
    int insertSelective(MlfrontPayInfo record);
    //删除本条00
    int deleteByPrimaryKey(Integer payinfoId);
    //更新本条00
    int updateByPrimaryKeySelective(MlfrontPayInfo record);
    //查询后台登陆用户00
    List<MlfrontPayInfo> selectMlfrontPayInfoById(MlfrontPayInfo record);
	//通过时间查询全部登陆用户00
	List<MlfrontPayInfo> selectMlfrontPayInfoByDateAndStatus(MlfrontPayInfo mlfrontPayInfo);
	//高级查询,多条件筛选
	List<MlfrontPayInfo> selectHighPayInfoListBySearch(MlfrontPayInfo mlfrontPayInfoReq);
	//高级按照时间查询
	List<MlfrontPayInfo> selectMlfrontPayInfoByDate(MlfrontPayInfo mlfrontPayInfoReq);
	
	//高级按照时间查询
	List<MlfrontPayInfo> selectMlfrontPayInfoByDateAndIfEmail(MlfrontPayInfo mlfrontPayInfoReq);

	List<MlfrontPayInfo> selectMlfrontPayInfoSuccessAll();

	List<MlfrontPayInfo> selectUnpayToSMSByDate(MlfrontPayInfo mlfrontPayInfoReq);
    
}