package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlbackOrderStateEmail;
import com.atguigu.dao.MlbackOrderStateEmailMapper;

@Service
public class MlbackOrderStateEmailService {
	
	@Autowired
	MlbackOrderStateEmailMapper mlbackOrderStateEmailMapper;
	
	/**
	 * @author Shinelon
	 * @param MlbackOrderStateEmail
	 * @exception add方法用户信息是否存在
	 * */
	public int insertSelective(MlbackOrderStateEmail MlbackOrderStateEmail) {
		int intReslut = mlbackOrderStateEmailMapper.insertSelective(MlbackOrderStateEmail);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 删除本条信息
	 * */
	public int deleteByPrimaryKey(int slideId) {
		int  intReslut = mlbackOrderStateEmailMapper.deleteByPrimaryKey(slideId);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条信息
	 * */
	public int updateByPrimaryKeySelective(MlbackOrderStateEmail MlbackOrderStateEmail) {
		int  intReslut = mlbackOrderStateEmailMapper.updateByPrimaryKeySelective(MlbackOrderStateEmail);
		return intReslut;
	}

	/**
	 * @author Shinelon
	 * @param MlbackOrderStateEmail
	 * @exception 查看用户信息是否存在
	 * */
	public List<MlbackOrderStateEmail> selectMlbackOrderStateEmailById(MlbackOrderStateEmail MlbackOrderStateEmail) {
		List<MlbackOrderStateEmail>  MlbackOrderStateEmailList = mlbackOrderStateEmailMapper.selectMlbackOrderStateEmailById(MlbackOrderStateEmail);
		return MlbackOrderStateEmailList;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部
	 * */
	public List<MlbackOrderStateEmail> selectMlbackOrderStateEmailGetAll() {
		List<MlbackOrderStateEmail>  MlbackOrderStateEmailList = mlbackOrderStateEmailMapper.selectMlbackOrderStateEmailGetAll();
		return MlbackOrderStateEmailList;
	}

	public List<MlbackOrderStateEmail> selectMlbackOrderStateEmailByName(MlbackOrderStateEmail MlbackOrderStateEmail) {
		List<MlbackOrderStateEmail>  MlbackOrderStateEmailList = mlbackOrderStateEmailMapper.selectMlbackOrderStateEmailByName(MlbackOrderStateEmail);
		return MlbackOrderStateEmailList;
	}
	
}
