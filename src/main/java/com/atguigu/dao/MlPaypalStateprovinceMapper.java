package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MlPaypalStateprovince;

public interface MlPaypalStateprovinceMapper {

    int insert(MlPaypalStateprovince record);

    MlPaypalStateprovince selectByPrimaryKey(Integer stateprovinceId);

    int updateByPrimaryKey(MlPaypalStateprovince record);
    
    //增
    int insertSelective(MlPaypalStateprovince record);
    //删
    int deleteByPrimaryKey(Integer stateprovinceId);
    //改
    int updateByPrimaryKeySelective(MlPaypalStateprovince record);
    //通过CountryCode查询
    List<MlPaypalStateprovince> selectMlPaypalStateprovinceByCountryCode(MlPaypalStateprovince record);
    //通过CountryCode+province全称查询该条信息
	List<MlPaypalStateprovince> selectMlPaypalStateprovinceByCountryCodeAndProvince(MlPaypalStateprovince mlPaypalStateprovinceReq);

	List<MlPaypalStateprovince> selectMlPaypalStateprovinceByCountryCodeAndProvinceCode(
			MlPaypalStateprovince mlPaypalStateprovinceReq);
}