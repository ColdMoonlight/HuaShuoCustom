package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MlbackCatalog;

public interface MlbackCatalogMapper {

    int insert(MlbackCatalog record);

    MlbackCatalog selectByPrimaryKey(Integer catalogId);

    int updateByPrimaryKey(MlbackCatalog record);
    
    
  //插入新类目
    int insertSelective(MlbackCatalog record);
    //删除本条
    int deleteByPrimaryKey(Integer CatalogId);
    //更新本条
    int updateByPrimaryKeySelective(MlbackCatalog record);
    //查询全部登陆用户
    List<MlbackCatalog> selectMlbackCatalogGetAll();
    //查询后台登陆用户
    List<MlbackCatalog> selectMlbackCatalog(MlbackCatalog example);
    //查询后台登陆用户
    MlbackCatalog selectMlbackCatalogById(MlbackCatalog example);
	//查询全部Catalog的parentid信息
	List<MlbackCatalog> selectMlbackCatalogGetAllByParentId();

	List<MlbackCatalog> selectMenuMlbackCatalogGetAll();
	//通过CatalogBySeo查找该类的详情
	List<MlbackCatalog> selectMlbackCatalogBySeo(MlbackCatalog mlbackCatalog);
	//selectCataloglistByParentId	onuse	20191226	检查
	List<MlbackCatalog> selectCataloglistByParam(MlbackCatalog mlbackCatalog);
	//selectMlbackCatalogBackSearch	服务端需要用查询
	List<MlbackCatalog> selectMlbackCatalogBackSearch(MlbackCatalog mlbackCatalogReq);
	//selectMenuMlbackCatalogGetAllOnlyFrontUsed	前段需要的目录查询
	List<MlbackCatalog> selectMenuMlbackCatalogGetAllOnlyFrontUsed();
	//selectCataloglistByParamOnlyFrontUsed	服务端需要用查询
	List<MlbackCatalog> selectCataloglistByParamOnlyFrontUsed(MlbackCatalog mlbackCatalogReq);

}