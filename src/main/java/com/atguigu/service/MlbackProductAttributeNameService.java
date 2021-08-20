package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlbackProductAttributeName;
import com.atguigu.dao.MlbackProductAttributeNameMapper;


@Service
public class MlbackProductAttributeNameService {
	
	@Autowired
	MlbackProductAttributeNameMapper mlbackProductAttributeNameMapper;
	
	/**
	 * @author Shinelon
	 * @param MlbackProductAttributeName
	 * @exception add方法MlbackProductAttributeName
	 * 
	 * */
	public int insertSelective(MlbackProductAttributeName MlbackProductAttributeName) {
		int intReslut = mlbackProductAttributeNameMapper.insertSelective(MlbackProductAttributeName);
		return intReslut;
	}

	/**
	 * @author Shinelon
	 * @param MlbackProductAttributeName
	 * @exception 删除本条MlbackProductAttributeName
	 * 
	 * */
	public int deleteByPrimaryKey(int productimgId) {
		int  intReslut = mlbackProductAttributeNameMapper.deleteByPrimaryKey(productimgId);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条MlbackProductAttributeName
	 * 
	 * */
	public int updateByPrimaryKeySelective(MlbackProductAttributeName MlbackProductAttributeName) {
		int  intReslut = mlbackProductAttributeNameMapper.updateByPrimaryKeySelective(MlbackProductAttributeName);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部MlbackProductAttributeName信息
	 * 
	 * */
	public List<MlbackProductAttributeName> selectMlbackProductAttributeNameByProductId(Integer productId) {
		List<MlbackProductAttributeName>  MlbackProductAttributeNameList = mlbackProductAttributeNameMapper.selectMlbackProductAttributeNameByProductId(productId);
		return MlbackProductAttributeNameList;
	}
//
//	/**
//	 * @author Shinelon
//	 * @param 
//	 * @exception 查看全部MlbackProductAttributeName信息
//	 * */
//	public List<MlbackProductAttributeName> selectMlbackProductAttributeNameByPIdAndImgSort(MlbackProductAttributeName MlbackProductAttributeName) {
//		List<MlbackProductAttributeName>  MlbackProductAttributeNameList = mlbackProductAttributeNameMapper.selectMlbackProductAttributeNameByPIdAndImgSort(MlbackProductAttributeName);
//		return MlbackProductAttributeNameList;
//	}
//	
//	/**
//	 * @author Shinelon
//	 * @param 
//	 * @exception 用productimgId,查看MlbackProductAttributeName信息
//	 * */
//	public List<MlbackProductAttributeName> selectMlbackProductAttributeNameByProductImgId(Integer productimgId) {
//		List<MlbackProductAttributeName>  MlbackProductAttributeNameList = mlbackProductAttributeNameMapper.selectMlbackProductAttributeNameByProductImgId(productimgId);
//		return MlbackProductAttributeNameList;
//	}
	
}
