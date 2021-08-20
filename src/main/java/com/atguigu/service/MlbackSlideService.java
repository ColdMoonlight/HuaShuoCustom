package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.MlbackSlide;
import com.atguigu.dao.MlbackSlideMapper;


@Service
public class MlbackSlideService {
	
	@Autowired
	MlbackSlideMapper mlbackSlideMapper;
	
	/**
	 * @author Shinelon
	 * @param MlbackSlide
	 * @exception add方法用户信息是否存在
	 * 
	 * */
	public int insertSelective(MlbackSlide mlbackSlide) {
		int intReslut = mlbackSlideMapper.insertSelective(mlbackSlide);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 删除本条信息
	 * 
	 * */
	public int deleteByPrimaryKey(int slideId) {
		// TODO Auto-generated method stub
		int  intReslut = mlbackSlideMapper.deleteByPrimaryKey(slideId);
		return intReslut;
	}
	
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条信息
	 * 
	 * */
	public int updateByPrimaryKeySelective(MlbackSlide mlbackSlide) {
		int  intReslut = mlbackSlideMapper.updateByPrimaryKeySelective(mlbackSlide);
		return intReslut;
	}

	/**
	 * @author Shinelon
	 * @param MlbackSlide
	 * @exception 查看用户信息是否存在
	 * 
	 * */
	public MlbackSlide selectMlbackSlideById(MlbackSlide mlbackSlide) {
		MlbackSlide  MlbackSlideOne = mlbackSlideMapper.selectMlbackSlideById(mlbackSlide);
		return MlbackSlideOne;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 
	 * */
	public List<MlbackSlide> selectMlbackSlideGetAll() {
		List<MlbackSlide>  MlbackSlideList = mlbackSlideMapper.selectMlbackSlideGetAll();
		return MlbackSlideList;
	}

	public List<MlbackSlide> selectMlbackSlidewapListByArea(MlbackSlide mlbackSlide) {
		List<MlbackSlide>  MlbackSlideList = mlbackSlideMapper.selectMlbackSlidewapListByArea(mlbackSlide);
		return MlbackSlideList;
	}
	
	public List<MlbackSlide> selectMlbackSlidepcListByArea(MlbackSlide mlbackSlide) {
		List<MlbackSlide>  MlbackSlideList = mlbackSlideMapper.selectMlbackSlidepcListByArea(mlbackSlide);
		return MlbackSlideList;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * */
/*	public List<MlbackSlide> selectMlbackSlideByActnum(MlbackSlide mlbackSlide) {
		List<MlbackSlide>  MlbackSlideList = mlbackSlideMapper.selectMlbackSlideByActnum(mlbackSlide);
		return MlbackSlideList;
	}*/

}
