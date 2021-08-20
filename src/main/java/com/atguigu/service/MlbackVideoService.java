package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlbackVideo;
import com.atguigu.dao.MlbackVideoMapper;


@Service
public class MlbackVideoService {
	
	
	@Autowired
	MlbackVideoMapper mlbackVideoMapper;
	
	/**
	 * @author Shinelon
	 * @param mlbackVideo
	 * @exception add方法用户信息是否存在
	 * 
	 * */
	public int insertSelective(MlbackVideo mlbackVideo) {
		int intReslut = mlbackVideoMapper.insertSelective(mlbackVideo);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 删除本条信息
	 * 
	 * */
	public int deleteByPrimaryKey(int actshowproId) {
		int  intReslut = mlbackVideoMapper.deleteByPrimaryKey(actshowproId);
		return intReslut;
	}
	
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条信息
	 * 
	 * */
	public int updateByPrimaryKeySelective(MlbackVideo mlbackVideo) {
		int  intReslut = mlbackVideoMapper.updateByPrimaryKeySelective(mlbackVideo);
		return intReslut;
	}

	/**
	 * @author Shinelon
	 * @param MlbackVideo
	 * @exception 查看用户信息是否存在
	 * 
	 * */
	public MlbackVideo selectMlbackVideoById(MlbackVideo mlbackVideo) {
		MlbackVideo  MlbackVideoOne = mlbackVideoMapper.selectMlbackVideoById(mlbackVideo);
		return MlbackVideoOne;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 
	 * */
	public List<MlbackVideo> selectMlbackVideoGetAll() {
		List<MlbackVideo>  MlbackVideoList = mlbackVideoMapper.selectMlbackVideoGetAll();
		return MlbackVideoList;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 
	 * */
	public List<MlbackVideo> selectMlbackvideoByVideoAreaCount(MlbackVideo mlbackVideoReq) {
		List<MlbackVideo>  MlbackVideoList = mlbackVideoMapper.selectMlbackvideoByVideoAreaCount(mlbackVideoReq);
		return MlbackVideoList;
		
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 
	 * */
	public List<MlbackVideo> selectMlbackvideoByVideoArea(MlbackVideo mlbackVideoReq) {
		List<MlbackVideo>  MlbackVideoList = mlbackVideoMapper.selectMlbackvideoByVideoArea(mlbackVideoReq);
		return MlbackVideoList;
		
		
	}

}
