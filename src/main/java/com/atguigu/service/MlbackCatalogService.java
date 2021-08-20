package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlbackCatalog;
import com.atguigu.dao.MlbackCatalogMapper;

@Service
public class MlbackCatalogService {
	
	@Autowired
	MlbackCatalogMapper mlbackCatalogMapper;

	/**
	 * @author Shinelon
	 * @param MlbackCatalog
	 * @exception 查看用户信息是否存在
	 * 	3.0
	 * */
	public List<MlbackCatalog> selectMlbackCatalog(MlbackCatalog mlbackCatalog) {
		List<MlbackCatalog>  mlbackCatalogList = mlbackCatalogMapper.selectMlbackCatalog(mlbackCatalog);
		return mlbackCatalogList;
	}
	/**
	 * @author Shinelon
	 * @param MlbackCatalog
	 * @exception 查看用户信息是否存在
	 * 	3.0
	 * */
	public MlbackCatalog selectMlbackCatalogById(MlbackCatalog mlbackCatalog) {
		MlbackCatalog  mlbackCatalogOne = mlbackCatalogMapper.selectMlbackCatalogById(mlbackCatalog);
		return mlbackCatalogOne;
	}
	
	/**
	 * @author Shinelon
	 * @param MlbackCatalog
	 * @exception add方法用户信息是否存在
	 * 	3.0
	 * */
	public int insertSelective(MlbackCatalog mlbackCatalog) {
		int intReslut = mlbackCatalogMapper.insertSelective(mlbackCatalog);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 	3.0
	 * */
	public List<MlbackCatalog> selectMlbackCatalogGetAll() {
		List<MlbackCatalog>  mlbackCatalogList = mlbackCatalogMapper.selectMlbackCatalogGetAll();
		return mlbackCatalogList;
	}
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 
	 * */
	public List<MlbackCatalog> selectMlbackCatalogGetAllByParentId() {
		List<MlbackCatalog>  mlbackCatalogList = mlbackCatalogMapper.selectMlbackCatalogGetAllByParentId();
		return mlbackCatalogList;
	}
	
	/**
	 * @author
	 * @param 
	 * @exception 查看全部用户信息
	 * 	3.0
	 * */
	public List<MlbackCatalog> selectMenuMlbackCatalogGetAll() {
		List<MlbackCatalog>  mlbackCatalogList = mlbackCatalogMapper.selectMenuMlbackCatalogGetAll();
		return mlbackCatalogList;
	}
	/**
	 * @author
	 * @param 
	 * @exception 删除本条信息
	 * 	3.0
	 * */
	public int deleteByPrimaryKey(int CatalogId) {
		int  intReslut = mlbackCatalogMapper.deleteByPrimaryKey(CatalogId);
		return intReslut;
	}

	/**
	 * @author	3.0
	 * @param 
	 * @exception 更新本条信息
	 * */
	public int updateByPrimaryKeySelective(MlbackCatalog mlbackCatalog) {
		int  intReslut = mlbackCatalogMapper.updateByPrimaryKeySelective(mlbackCatalog);
		return intReslut;
	}
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 通过CatalogBySeo查找该类的详情
	 * 	3.0
	 * */
	public List<MlbackCatalog> selectMlbackCatalogBySeo(MlbackCatalog mlbackCatalog) {
		List<MlbackCatalog>  mlbackCatalogList = mlbackCatalogMapper.selectMlbackCatalogBySeo(mlbackCatalog);
		return mlbackCatalogList;
	}
	/**
	 * @autho	onuse	20191226	检查
	 * @param MlbackCatalog
	 * @exception	
	 * 	3.0
	 * */
	public List<MlbackCatalog> selectCataloglistByParam(MlbackCatalog mlbackCatalog) {
		List<MlbackCatalog>  mlbackCatalogList = mlbackCatalogMapper.selectCataloglistByParam(mlbackCatalog);
		return mlbackCatalogList;
	}
	
	/**
	 * @autho	3.0
	 * @param MlbackCatalog
	 * @exception
	 * 	3.0	
	 * */
	public List<MlbackCatalog> selectMlbackCatalogBackSearch(MlbackCatalog mlbackCatalogReq) {
		List<MlbackCatalog>  mlbackCatalogList = mlbackCatalogMapper.selectMlbackCatalogBackSearch(mlbackCatalogReq);
		return mlbackCatalogList;
	}
	/**
	 * @autho	3.0
	 * @param MlbackCatalog
	 * @exception
	 * 	3.0	
	 * */
	public List<MlbackCatalog> selectMenuMlbackCatalogGetAllOnlyFrontUsed() {
		List<MlbackCatalog>  mlbackCatalogList = mlbackCatalogMapper.selectMenuMlbackCatalogGetAllOnlyFrontUsed();
		return mlbackCatalogList;
	}
	/**
	 * @autho	3.0
	 * @param MlbackCatalog
	 * @exception
	 * 	3.0	
	 * */
	public List<MlbackCatalog> selectCataloglistByParamOnlyFrontUsed(MlbackCatalog mlbackCatalogReq) {
		List<MlbackCatalog>  mlbackCatalogList = mlbackCatalogMapper.selectCataloglistByParamOnlyFrontUsed(mlbackCatalogReq);
		return mlbackCatalogList;
	}
	
}
