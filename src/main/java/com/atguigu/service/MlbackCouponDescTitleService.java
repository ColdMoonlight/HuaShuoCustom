package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.MlbackCouponDescTitle;
import com.atguigu.dao.MlbackCouponDescTitleMapper;


@Service
public class MlbackCouponDescTitleService {
	
	
	@Autowired
	MlbackCouponDescTitleMapper mlbackCouponDescTitleMapper;
	
	/**
	 * @author Shinelon
	 * @param MlbackCouponDescTitle
	 * @exception add方法用户信息是否存在
	 * 
	 * */
	public int insertSelective(MlbackCouponDescTitle mlbackCouponDescTitle) {
		int intReslut = mlbackCouponDescTitleMapper.insertSelective(mlbackCouponDescTitle);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 删除本条信息
	 * 
	 * */
	public int deleteByPrimaryKey(int coupondesctieleId) {
		int  intReslut = mlbackCouponDescTitleMapper.deleteByPrimaryKey(coupondesctieleId);
		return intReslut;
	}
	
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条信息
	 * 
	 * */
	public int updateByPrimaryKeySelective(MlbackCouponDescTitle MlbackCouponDescTitle) {
		int  intReslut = mlbackCouponDescTitleMapper.updateByPrimaryKeySelective(MlbackCouponDescTitle);
		return intReslut;
	}

	/**
	 * @author Shinelon
	 * @param MlbackCouponDescTitle
	 * @exception 查看用户信息是否存在
	 * 
	 * */
	public MlbackCouponDescTitle selectMlbackCouponDescTitleById(MlbackCouponDescTitle MlbackCouponDescTitle) {
		MlbackCouponDescTitle  MlbackCouponDescTitleOne = mlbackCouponDescTitleMapper.selectMlbackCouponDescTitleById(MlbackCouponDescTitle);
		return MlbackCouponDescTitleOne;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 
	 * */
	public List<MlbackCouponDescTitle> selectMlbackCouponDescTitleGetAll() {
		List<MlbackCouponDescTitle>  mlbackCouponDescTitleList = mlbackCouponDescTitleMapper.selectMlbackCouponDescTitleGetAll();
		return mlbackCouponDescTitleList;
	}

	public List<MlbackCouponDescTitle> selectMlbackCouponDescTitleListByStatus(MlbackCouponDescTitle MlbackCouponDescTitle) {
		List<MlbackCouponDescTitle>  mlbackCouponDescTitleList = mlbackCouponDescTitleMapper.selectMlbackCouponDescTitleListByStatus(MlbackCouponDescTitle);
		return mlbackCouponDescTitleList;
	}

}
