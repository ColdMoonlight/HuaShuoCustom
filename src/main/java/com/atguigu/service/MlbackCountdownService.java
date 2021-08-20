package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlbackCountdown;
import com.atguigu.dao.MlbackCountdownMapper;

@Service
public class MlbackCountdownService {
	
	@Autowired
	MlbackCountdownMapper mlbackCountdownMapper;

	/**
	 * @param MlbackCountdown
	 * @exception add方法用户信息是否存在
	 * */
	public int insertSelective(MlbackCountdown MlbackCountdown) {
		int intReslut = mlbackCountdownMapper.insertSelective(MlbackCountdown);
		return intReslut;
	}

	/**
	 * @param 
	 * @exception 删除本条信息
	 * */
	public int deleteByPrimaryKey(int CatalogId) {
		int  intReslut = mlbackCountdownMapper.deleteByPrimaryKey(CatalogId);
		return intReslut;
	}

	/**
	 * @param 
	 * @exception 更新本条信息
	 * */
	public int updateByPrimaryKeySelective(MlbackCountdown MlbackCountdown) {
		int  intReslut = mlbackCountdownMapper.updateByPrimaryKeySelective(MlbackCountdown);
		return intReslut;
	}
	
	/**
	 * @param 
	 * @exception 查看全部用户信息
	 * */
	public List<MlbackCountdown> selectMlbackCountdownGetAll() {
		List<MlbackCountdown>  MlbackCountdownList = mlbackCountdownMapper.selectMlbackCountdownGetAll();
		return MlbackCountdownList;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * */
	public List<MlbackCountdown> selectMlbackCountdownById(MlbackCountdown MlbackCountdown) {
		List<MlbackCountdown>  MlbackCountdownList = mlbackCountdownMapper.selectMlbackCountdownById(MlbackCountdown);
		return MlbackCountdownList;
	}

}
