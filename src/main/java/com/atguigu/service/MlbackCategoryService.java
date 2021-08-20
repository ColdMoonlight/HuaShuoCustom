package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlbackCategory;
import com.atguigu.dao.MlbackCategoryMapper;

@Service
public class MlbackCategoryService {
	
	@Autowired
	MlbackCategoryMapper mlbackCategoryMapper;

	/**
	 * @author Shinelon
	 * @param MlbackCategory
	 * @exception 查看用户信息是否存在
	 * */
	public List<MlbackCategory> selectMlbackCategory(MlbackCategory mlbackCategory) {
		List<MlbackCategory>  mlbackCategoryList = mlbackCategoryMapper.selectMlbackCategory(mlbackCategory);
		return mlbackCategoryList;
	}
	/**
	 * @author Shinelon
	 * @param MlbackCategory
	 * @exception 查看用户信息是否存在
	 * */
	public MlbackCategory selectMlbackCategoryById(MlbackCategory mlbackCategory) {
		MlbackCategory  mlbackCategoryOne = mlbackCategoryMapper.selectMlbackCategoryById(mlbackCategory);
		return mlbackCategoryOne;
	}
	
	/**
	 * @author Shinelon
	 * @param MlbackCategory
	 * @exception add方法用户信息是否存在
	 * */
	public int insertSelective(MlbackCategory mlbackCategory) {
		int intReslut = mlbackCategoryMapper.insertSelective(mlbackCategory);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * */
	public List<MlbackCategory> selectMlbackCategoryGetAll() {
		List<MlbackCategory>  mlbackCategoryList = mlbackCategoryMapper.selectMlbackCategoryGetAll();
		return mlbackCategoryList;
	}
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * */
	public List<MlbackCategory> selectMlbackCategoryGetAllByParentId() {
		List<MlbackCategory>  mlbackCategoryList = mlbackCategoryMapper.selectMlbackCategoryGetAllByParentId();
		return mlbackCategoryList;
	}
	
	public List<MlbackCategory> selectMlbackCategoryGetAllByParentIdFront() {
		List<MlbackCategory>  mlbackCategoryList = mlbackCategoryMapper.selectMlbackCategoryGetAllByParentIdFront();
		return mlbackCategoryList;
	}
	
	/**
	 * @author
	 * @param 
	 * @exception 查看全部用户信息
	 * */
	public List<MlbackCategory> selectMenuMlbackCategoryGetAll() {
		List<MlbackCategory>  mlbackCategoryList = mlbackCategoryMapper.selectMenuMlbackCategoryGetAll();
		return mlbackCategoryList;
	}
	/**
	 * @author
	 * @param 
	 * @exception 删除本条信息
	 * */
	public int deleteByPrimaryKey(int categoryId) {
		int  intReslut = mlbackCategoryMapper.deleteByPrimaryKey(categoryId);
		return intReslut;
	}

	/**
	 * @author	3.0
	 * @param 
	 * @exception 更新本条信息
	 * */
	public int updateByPrimaryKeySelective(MlbackCategory mlbackCategory) {
		int  intReslut = mlbackCategoryMapper.updateByPrimaryKeySelective(mlbackCategory);
		return intReslut;
	}
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 通过CategoryBySeo查找该类的详情
	 * */
	public List<MlbackCategory> selectMlbackCategoryBySeo(MlbackCategory mlbackCategory) {
		List<MlbackCategory>  mlbackCategoryList = mlbackCategoryMapper.selectMlbackCategoryBySeo(mlbackCategory);
		return mlbackCategoryList;
	}
	/**
	 * @autho	onuse	20191226	检查
	 * @param MlbackCategory
	 * @exception	
	 * */
	public List<MlbackCategory> selectCategorylistByParam(MlbackCategory mlbackCategory) {
		List<MlbackCategory>  mlbackCategoryList = mlbackCategoryMapper.selectCategorylistByParam(mlbackCategory);
		return mlbackCategoryList;
	}
	
	/**
	 * @autho	3.0
	 * @param MlbackCategory
	 * @exception
	 * */
	public List<MlbackCategory> selectMlbackCategoryBackSearch(MlbackCategory mlbackCategoryReq) {
		List<MlbackCategory>  mlbackCategoryList = mlbackCategoryMapper.selectMlbackCategoryBackSearch(mlbackCategoryReq);
		return mlbackCategoryList;
	}

}
