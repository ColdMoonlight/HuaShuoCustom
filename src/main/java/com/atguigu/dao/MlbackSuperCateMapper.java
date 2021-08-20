package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MlbackSuperCate;

public interface MlbackSuperCateMapper {
	
	
	int insert(MlbackSuperCate record);
	
	//插入新类目
	int insertSelective(MlbackSuperCate record);
    //删除本条
	int deleteByPrimaryKey(Integer supercateId);
    //更新本条
    int updateByPrimaryKeySelective(MlbackSuperCate record);

    int updateByPrimaryKey(MlbackSuperCate record);
    
    MlbackSuperCate selectByPrimaryKey(Integer supercateId);
    
    //查询后台登陆用户
    List<MlbackSuperCate> selectMlbackSuperCateById(MlbackSuperCate example);
    //查询全部登陆用户
	List<MlbackSuperCate> selectMlbackSuperCateGetAll();
	//通过MlbackSuperCate查找该类的详情
	List<MlbackSuperCate> selectmlbackSuperCateBySeo(MlbackSuperCate mlbackCategory);
//	//查询全部Category的parentid信息
//	List<MlbackCategory> selectMlbackCategoryGetAllByParentId();
//
//	List<MlbackCategory> selectMenuMlbackCategoryGetAll();
//	//selectCategorylistByParentId	onuse	20191226	检查
//	List<MlbackCategory> selectCategorylistByParam(MlbackSuperCate mlbackCategory);
    
}