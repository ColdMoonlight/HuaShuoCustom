package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlbackProfbreviewArea;
import com.atguigu.dao.MlbackProfbreviewAreaMapper;

@Service
public class MlbackProfbreviewAreaService {
	
	@Autowired
	MlbackProfbreviewAreaMapper mlbackProfbreviewAreaMapper;
	
	/**
	 * @author Shinelon
	 * @param MlbackProfbreviewArea
	 * @exception add方法用户信息是否存在
	 * */
	public int insertSelective(MlbackProfbreviewArea MlbackProfbreviewArea) {
		int intReslut = mlbackProfbreviewAreaMapper.insertSelective(MlbackProfbreviewArea);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 删除本条信息
	 * */
	public int deleteByPrimaryKey(int productId) {
		int  intReslut = mlbackProfbreviewAreaMapper.deleteByPrimaryKey(productId);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条信息
	 * */
	public int updateByPrimaryKeySelective(MlbackProfbreviewArea MlbackProfbreviewArea) {
		int  intReslut = mlbackProfbreviewAreaMapper.updateByPrimaryKeySelective(MlbackProfbreviewArea);
		return intReslut;
	}
	
	public List<MlbackProfbreviewArea> selectMlbackProfbreviewAreabyPid(MlbackProfbreviewArea MlbackProfbreviewAreaReq) {
		List<MlbackProfbreviewArea>  MlbackProfbreviewAreaList = mlbackProfbreviewAreaMapper.selectMlbackProfbreviewAreabyPid(MlbackProfbreviewAreaReq);
		return MlbackProfbreviewAreaList;
	}
	
	public List<MlbackProfbreviewArea> selectMlbackProfbreviewAreaGetAllSimple() {
		List<MlbackProfbreviewArea>  MlbackProfbreviewAreaList = mlbackProfbreviewAreaMapper.selectMlbackProfbreviewAreaGetAllSimple();
		return MlbackProfbreviewAreaList;
	}

	public List<MlbackProfbreviewArea> selectMlbackProfbreviewAreabyParam(MlbackProfbreviewArea mlbackProfbreviewAreaReq) {
		List<MlbackProfbreviewArea>  MlbackProfbreviewAreaList = mlbackProfbreviewAreaMapper.selectMlbackProfbreviewAreabyParam(mlbackProfbreviewAreaReq);
		return MlbackProfbreviewAreaList;
	}

	public List<MlbackProfbreviewArea> selectMlbackProductBackLike(MlbackProfbreviewArea mlbackProfbreviewAreaReq) {
		List<MlbackProfbreviewArea>  MlbackProfbreviewAreaList = mlbackProfbreviewAreaMapper.selectMlbackProductBackLike(mlbackProfbreviewAreaReq);
		return MlbackProfbreviewAreaList;
	}

}
