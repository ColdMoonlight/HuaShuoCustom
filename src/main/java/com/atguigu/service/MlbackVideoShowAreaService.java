package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlbackVideoShowArea;
import com.atguigu.dao.MlbackVideoShowAreaMapper;

@Service
public class MlbackVideoShowAreaService {
	
	@Autowired
	MlbackVideoShowAreaMapper mlbackVideoShowAreaMapper;
	
	/**
	 * @author Shinelon
	 * @param MlbackSlide
	 * @exception add方法用户信息是否存在
	 * 
	 * */
	public int insertSelective(MlbackVideoShowArea mlbackSlide) {
		int intReslut = mlbackVideoShowAreaMapper.insertSelective(mlbackSlide);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 删除本条信息
	 * 
	 * */
	public int deleteByPrimaryKey(int slideId) {
		int  intReslut = mlbackVideoShowAreaMapper.deleteByPrimaryKey(slideId);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条信息
	 * 
	 * */
	public int updateByPrimaryKeySelective(MlbackVideoShowArea mlbackSlide) {
		int  intReslut = mlbackVideoShowAreaMapper.updateByPrimaryKeySelective(mlbackSlide);
		return intReslut;
	}

	/**
	 * @author Shinelon
	 * @param MlbackVideoShowArea
	 * @exception 查看用户信息是否存在
	 * 
	 * */
	public MlbackVideoShowArea selectMlbackVideoShowAreaById(MlbackVideoShowArea mlbackSlide) {
		MlbackVideoShowArea  MlbackVideoShowAreaOne = mlbackVideoShowAreaMapper.selectMlbackVideoShowAreaById(mlbackSlide);
		return MlbackVideoShowAreaOne;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 
	 * */
	public List<MlbackVideoShowArea> selectMlbackVideoShowAreaGetAll() {
		List<MlbackVideoShowArea>  MlbackVideoShowAreaList = mlbackVideoShowAreaMapper.selectMlbackVideoShowAreaGetAll();
		return MlbackVideoShowAreaList;
	}

	public List<MlbackVideoShowArea> selectMlbackVideoShowAreawapListByArea(MlbackVideoShowArea mlbackSlide) {
		List<MlbackVideoShowArea>  MlbackVideoShowAreaList = mlbackVideoShowAreaMapper.selectMlbackVideoShowAreawapListByArea(mlbackSlide);
		return MlbackVideoShowAreaList;
	}
	
	public List<MlbackVideoShowArea> selectMlbackVideoShowAreapcListByArea(MlbackVideoShowArea mlbackSlide) {
		List<MlbackVideoShowArea>  MlbackVideoShowAreaList = mlbackVideoShowAreaMapper.selectMlbackVideoShowAreapcListByArea(mlbackSlide);
		return MlbackVideoShowAreaList;
	}

}
