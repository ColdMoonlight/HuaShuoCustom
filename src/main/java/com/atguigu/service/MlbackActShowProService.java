package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.MlbackActShowPro;
import com.atguigu.dao.MlbackActShowProMapper;

@Service
public class MlbackActShowProService {
	
	
	@Autowired
	MlbackActShowProMapper mlbackActShowProMapper;
	
	/**
	 * @author Shinelon
	 * @param MlbackActShowPro
	 * @exception add方法用户信息是否存在
	 * 
	 * */
	public int insertSelective(MlbackActShowPro MlbackActShowPro) {
		int intReslut = mlbackActShowProMapper.insertSelective(MlbackActShowPro);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 删除本条信息
	 * 
	 * */
	public int deleteByPrimaryKey(int actshowproId) {
		int  intReslut = mlbackActShowProMapper.deleteByPrimaryKey(actshowproId);
		return intReslut;
	}
	
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条信息
	 * 
	 * */
	public int updateByPrimaryKeySelective(MlbackActShowPro mlbackActShowPro) {
		int  intReslut = mlbackActShowProMapper.updateByPrimaryKeySelective(mlbackActShowPro);
		return intReslut;
	}

	/**
	 * @author Shinelon
	 * @param MlbackActShowPro
	 * @exception 查看用户信息是否存在
	 * 
	 * */
	public MlbackActShowPro selectMlbackActShowProById(MlbackActShowPro mlbackActShowPro) {
		MlbackActShowPro  MlbackActShowProOne = mlbackActShowProMapper.selectMlbackActShowProById(mlbackActShowPro);
		return MlbackActShowProOne;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 
	 * */
	public List<MlbackActShowPro> selectMlbackActShowProGetAll() {
		List<MlbackActShowPro>  MlbackActShowProList = mlbackActShowProMapper.selectMlbackActShowProGetAll();
		return MlbackActShowProList;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 
	 * */
	public List<MlbackActShowPro> selectMlbackActShowProByActnum(MlbackActShowPro mlbackActShowProReq) {
		List<MlbackActShowPro>  MlbackActShowProList = mlbackActShowProMapper.selectMlbackActShowProByActnum(mlbackActShowProReq);
		return MlbackActShowProList;
	}

}
