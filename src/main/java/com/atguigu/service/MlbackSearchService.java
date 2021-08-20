package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlbackSearch;
import com.atguigu.dao.MlbackSearchMapper;


@Service
public class MlbackSearchService {
	
	
	@Autowired
	MlbackSearchMapper mlbackSearchMapper;

	/**1
	 * @author Shinelon
	 * @param MlbackSearch
	 * @exception 查看全部的地址信息
	 * 
	 * */
	public int insertSelective(MlbackSearch MlbackSearch) {
		int intReslut = mlbackSearchMapper.insertSelective(MlbackSearch);
		return intReslut;
	}
	
	/**2
	 * @author Shinelon
	 * @param 
	 * @exception 删除本条信息
	 * 
	 * */
	public int deleteByPrimaryKey(int showareaId) {
		int  intReslut = mlbackSearchMapper.deleteByPrimaryKey(showareaId);
		return intReslut;
	}
	
	
	/**3
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * */
	public int updateByPrimaryKeySelective(MlbackSearch MlbackSearch) {
		int  intReslut = mlbackSearchMapper.updateByPrimaryKeySelective(MlbackSearch);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看MlbackSearch信息
	 * */
	public List<MlbackSearch> selectSearchListByTime(MlbackSearch MlbackSearch) {
		List<MlbackSearch>  mlbackSearchList = mlbackSearchMapper.selectSearchListByTime(MlbackSearch);
		return mlbackSearchList;
	}

}
