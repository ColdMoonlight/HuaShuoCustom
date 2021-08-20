package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.MlbackAreafreight;
import com.atguigu.dao.MlbackAreafreightMapper;

@Service
public class MlbackAreafreightService {
	
	@Autowired
	MlbackAreafreightMapper mlbackAreafreightMapper;

	/**1
	 * @author Shinelon
	 * @param MlbackProduct
	 * @exception 查看全部的地址信息
	 * */
	public List<MlbackAreafreight> selectMlbackAreafreightAll() {
		List<MlbackAreafreight>  mlbackAreafreightList = mlbackAreafreightMapper.selectMlbackAreafreightGetAll();
		return mlbackAreafreightList;
	}
	
	/**2
	 * @author Shinelon
	 * @param MlbackProduct
	 * @exception add方法用户信息是否存在
	 * */
	public int insertSelective(MlbackAreafreight mlbackAreafreight) {
		int intReslut = mlbackAreafreightMapper.insertSelective(mlbackAreafreight);
		return intReslut;
	}
	
	/**3
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * */
	public List<MlbackAreafreight> selectMlbackAreafreightByParam(MlbackAreafreight mlbackAreafreight) {
		List<MlbackAreafreight>  mlbackAreafreightList = mlbackAreafreightMapper.selectMlbackAreafreightByParam(mlbackAreafreight);
		return mlbackAreafreightList;
	}

	/**4
	 * @author Shinelon
	 * @param 
	 * @exception 删除本条信息
	 * */
	public int deleteByPrimaryKey(int areafreightId) {
		int  intReslut = mlbackAreafreightMapper.deleteByPrimaryKey(areafreightId);
		return intReslut;
	}

	/**5
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条信息
	 * */
	public int updateByPrimaryKeySelective(MlbackAreafreight mlbackAreafreight) {
		int  intReslut = mlbackAreafreightMapper.updateByPrimaryKeySelective(mlbackAreafreight);
		return intReslut;
	}
	
}
