package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlbackSmstype;
import com.atguigu.dao.MlbackSmstypeMapper;

@Service
public class MlbackSmstypeService {
	
	@Autowired
	MlbackSmstypeMapper mlbackSmstypeMapper;
	
	/**
	 * @author Shinelon
	 * @param mlbackSmstype
	 * @exception add方法用户信息是否存在
	 * 
	 * */
	public int insertSelective(MlbackSmstype mlbackSmstype) {
		int intReslut = mlbackSmstypeMapper.insertSelective(mlbackSmstype);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 删除本条信息
	 * 
	 * */
	public int deleteByPrimaryKey(int smstypeId) {
		int  intReslut = mlbackSmstypeMapper.deleteByPrimaryKey(smstypeId);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条信息
	 * 
	 * */
	public int updateByPrimaryKeySelective(MlbackSmstype mlbackSmstype) {
		int  intReslut = mlbackSmstypeMapper.updateByPrimaryKeySelective(mlbackSmstype);
		return intReslut;
	}

	/**
	 * @author Shinelon
	 * @param MlbackSmstype
	 * @exception 查看用户信息是否存在
	 * 
	 * */
	public List<MlbackSmstype> selectMlbackSmstypeById(MlbackSmstype mlbackSmstype) {
		List<MlbackSmstype>  MlbackSmstypeOneList = mlbackSmstypeMapper.selectMlbackSmstypeById(mlbackSmstype);
		return MlbackSmstypeOneList;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 
	 * */
	public List<MlbackSmstype> selectMlbackSmstypeGetAll() {
		List<MlbackSmstype>  MlbackSmstypeList = mlbackSmstypeMapper.selectMlbackSmstypeGetAll();
		return MlbackSmstypeList;
	}

	public List<MlbackSmstype> selectMlbackSmstypeByName(MlbackSmstype mlbackSmstype) {
		List<MlbackSmstype>  MlbackSmstypeList = mlbackSmstypeMapper.selectMlbackSmstypeByName(mlbackSmstype);
		return MlbackSmstypeList;
	}

}
