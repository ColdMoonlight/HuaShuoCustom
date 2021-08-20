package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MlbackCategory;

public interface MlbackCategoryMapper {

    int insert(MlbackCategory record);

    MlbackCategory selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKey(MlbackCategory record);
    
    
    //插入新类目
    int insertSelective(MlbackCategory record);
    //查询后台登陆用户
    List<MlbackCategory> selectMlbackCategory(MlbackCategory example);
    //查询后台登陆用户
    MlbackCategory selectMlbackCategoryById(MlbackCategory example);
    //查询全部登陆用户
	List<MlbackCategory> selectMlbackCategoryGetAll();
	//查询全部Category的parentid信息
	List<MlbackCategory> selectMlbackCategoryGetAllByParentId();
	
	List<MlbackCategory> selectMlbackCategoryGetAllByParentIdFront();
    //更新本条
    int updateByPrimaryKeySelective(MlbackCategory record);
    //删除本条
    int deleteByPrimaryKey(Integer categoryId);

	List<MlbackCategory> selectMenuMlbackCategoryGetAll();
	//通过CategoryBySeo查找该类的详情
	List<MlbackCategory> selectMlbackCategoryBySeo(MlbackCategory mlbackCategory);
	//selectCategorylistByParentId	onuse	20191226	检查
	List<MlbackCategory> selectCategorylistByParam(MlbackCategory mlbackCategory);
	//selectMlbackCategoryBackSearch	服务端需要用查询
	List<MlbackCategory> selectMlbackCategoryBackSearch(MlbackCategory mlbackCategoryReq);
}