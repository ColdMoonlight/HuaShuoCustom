package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlbackPageArea;
import com.atguigu.dao.MlbackPageAreaMapper;

@Service
public class MlbackPageAreaService {
	
	@Autowired
	MlbackPageAreaMapper mlbackPageAreaMapper;
	
	/**
	 * @author Shinelon
	 * @param MlbackPageArea
	 * @exception add方法用户信息是否存在
	 * 
	 * */
	public int insertSelective(MlbackPageArea MlbackPageArea) {
		int intReslut = mlbackPageAreaMapper.insertSelective(MlbackPageArea);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 删除本条信息
	 * 
	 * */
	public int deleteByPrimaryKey(int pageareaId) {
		int  intReslut = mlbackPageAreaMapper.deleteByPrimaryKey(pageareaId);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条信息
	 * */
	public int updateByPrimaryKeySelective(MlbackPageArea MlbackPageArea) {
		int  intReslut = mlbackPageAreaMapper.updateByPrimaryKeySelective(MlbackPageArea);
		return intReslut;
	}

	/**
	 * @author Shinelon
	 * @param MlbackPageArea
	 * @exception 查看用户信息是否存在
	 * */
	public MlbackPageArea selectMlbackPageAreaById(MlbackPageArea MlbackPageArea) {
		MlbackPageArea  MlbackPageAreaOne = mlbackPageAreaMapper.selectMlbackPageAreaById(MlbackPageArea);
		return MlbackPageAreaOne;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * */
	public List<MlbackPageArea> selectMlbackPageAreaGetAll() {
		List<MlbackPageArea>  MlbackPageAreaList = mlbackPageAreaMapper.selectMlbackPageAreaGetAll();
		return MlbackPageAreaList;
	}

	public List<MlbackPageArea> selectHomepageByStatus(MlbackPageArea mlbackPageAreaReq) {
		List<MlbackPageArea>  MlbackPageAreaList = mlbackPageAreaMapper.selectHomepageByStatus(mlbackPageAreaReq);
		return MlbackPageAreaList;
	}
	
	public List<MlbackPageArea> selectHomepageByPcStatus(MlbackPageArea mlbackPageAreaReq) {
		List<MlbackPageArea>  MlbackPageAreaList = mlbackPageAreaMapper.selectHomepageByPcStatus(mlbackPageAreaReq);
		return MlbackPageAreaList;
	}

	public List<MlbackPageArea> selectMlbackPageAreaBackSearch(MlbackPageArea mlbackPageAreaReq) {
		List<MlbackPageArea>  MlbackPageAreaList = mlbackPageAreaMapper.selectMlbackPageAreaBackSearch(mlbackPageAreaReq);
		return MlbackPageAreaList;
	}

}
