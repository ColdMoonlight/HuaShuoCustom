package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.MlbackSuperCate;
import com.atguigu.dao.MlbackSuperCateMapper;


@Service
public class MlbackSuperCateService {
	
	@Autowired
	MlbackSuperCateMapper mlbackSuperCateMapper;

	/**
	 * @author Shinelon
	 * @param MlbackSuperCate
	 * @exception 查看用户信息是否存在
	 * 
	 * */
	public List<MlbackSuperCate> selectMlbackSuperCateById(MlbackSuperCate mlbackSuperCate) {
		List<MlbackSuperCate>  mlbackSuperCateList = mlbackSuperCateMapper.selectMlbackSuperCateById(mlbackSuperCate);
		return mlbackSuperCateList;
	}
	
	/**
	 * @author Shinelon
	 * @param MlbackSuperCate
	 * @exception add方法用户信息是否存在
	 * 
	 * */
	public int insertSelective(MlbackSuperCate mlbackSuperCate) {
		int intReslut = mlbackSuperCateMapper.insertSelective(mlbackSuperCate);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 
	 * */
	public List<MlbackSuperCate> selectMlbackSuperCateGetAll() {
		List<MlbackSuperCate>  mlbackSuperCateList = mlbackSuperCateMapper.selectMlbackSuperCateGetAll();
		return mlbackSuperCateList;
	}

	/**
	 * @author Shinelon
	 * @param 
	 * @exception 删除本条信息
	 * 
	 * */
	public int deleteByPrimaryKey(int categoryId) {
		int  intReslut = mlbackSuperCateMapper.deleteByPrimaryKey(categoryId);
		return intReslut;
	}

	/**
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条信息
	 * 
	 * */
	public int updateByPrimaryKeySelective(MlbackSuperCate mlbackSuperCate) {
		int  intReslut = mlbackSuperCateMapper.updateByPrimaryKeySelective(mlbackSuperCate);
		return intReslut;
	}
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 通过CategoryBySeo查找该类的详情
	 * 
	 * */
	public List<MlbackSuperCate> selectmlbackSuperCateBySeo(MlbackSuperCate mlbackSuperCate) {
		List<MlbackSuperCate>  mlbackSuperCateList = mlbackSuperCateMapper.selectmlbackSuperCateBySeo(mlbackSuperCate);
		return mlbackSuperCateList;
	}

}
