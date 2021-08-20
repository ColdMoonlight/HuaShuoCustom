package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.MlbackAdmin;
import com.atguigu.dao.MlbackAdminMapper;


@Service
public class MlbackAdminService {
	
	
	@Autowired
	MlbackAdminMapper mlbackAdminMapper;

	/**
	 * @author Shinelon
	 * @param MlbackAdmin
	 * @exception 查看用户信息是否存在
	 * 
	 * */
	public List<MlbackAdmin> selectMlbackAdmin(MlbackAdmin mlbackAdmin) {
		List<MlbackAdmin>  MlbackAdminList = mlbackAdminMapper.selectMlbackAdmin(mlbackAdmin);
		return MlbackAdminList;
	}
	
	/**
	 * @author Shinelon
	 * @param MlbackAdmin
	 * @exception add方法用户信息是否存在
	 * 
	 * */
	public int insertSelective(MlbackAdmin mlbackAdmin) {
		int intReslut = mlbackAdminMapper.insertSelective(mlbackAdmin);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 
	 * */
	public int updateByAdminAccnameSelective(MlbackAdmin mlbackAdmin) {
		int intReslut = mlbackAdminMapper.updateByAdminAccnameSelective(mlbackAdmin);
		return intReslut;
	}

}
