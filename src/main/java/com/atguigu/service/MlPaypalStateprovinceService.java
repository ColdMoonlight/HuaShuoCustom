package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlPaypalStateprovince;
import com.atguigu.dao.MlPaypalStateprovinceMapper;


@Service
public class MlPaypalStateprovinceService {
	
	
	@Autowired
	MlPaypalStateprovinceMapper mlPaypalStateprovinceMapper;
	
	
	
	/**
	 * @author Shinelon
	 * @param MlPaypalStateprovince
	 * @exception add方法用户信息是否存在
	 * 
	 * */
	public int insertSelective(MlPaypalStateprovince mlPaypalStateprovince) {
		int intReslut = mlPaypalStateprovinceMapper.insertSelective(mlPaypalStateprovince);
		return intReslut;
	}

	/**
	 * @author Shinelon
	 * @param 
	 * @exception 删除本条信息
	 * 
	 * */
	public int deleteByPrimaryKey(int shippingaddressId) {
		int  intReslut = mlPaypalStateprovinceMapper.deleteByPrimaryKey(shippingaddressId);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条信息
	 * 
	 * */
	public int updateByPrimaryKeySelective(MlPaypalStateprovince mlPaypalStateprovince) {
		int  intReslut = mlPaypalStateprovinceMapper.updateByPrimaryKeySelective(mlPaypalStateprovince);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param MlPaypalStateprovince
	 * @exception 查看用户信息是否存在
	 * 
	 * */
	public List<MlPaypalStateprovince> selectMlPaypalStateprovinceByCountryCode(MlPaypalStateprovince mlPaypalStateprovince) {
		List<MlPaypalStateprovince>  mlPaypalStateprovinceRes = mlPaypalStateprovinceMapper.selectMlPaypalStateprovinceByCountryCode(mlPaypalStateprovince);
		return mlPaypalStateprovinceRes;
	}

	public List<MlPaypalStateprovince> selectMlPaypalStateprovinceByCountryCodeAndProvince(MlPaypalStateprovince mlPaypalStateprovinceReq) {
		List<MlPaypalStateprovince>  mlPaypalStateprovinceRes = mlPaypalStateprovinceMapper.selectMlPaypalStateprovinceByCountryCodeAndProvince(mlPaypalStateprovinceReq);
		return mlPaypalStateprovinceRes;
	}
	public List<MlPaypalStateprovince> selectMlPaypalStateprovinceByCountryCodeAndProvinceCode(MlPaypalStateprovince mlPaypalStateprovinceReq) {
		List<MlPaypalStateprovince>  mlPaypalStateprovinceRes = mlPaypalStateprovinceMapper.selectMlPaypalStateprovinceByCountryCodeAndProvinceCode(mlPaypalStateprovinceReq);
		return mlPaypalStateprovinceRes;
	}
	
	

}
