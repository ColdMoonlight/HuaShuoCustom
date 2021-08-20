package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MlbackProduct;

public interface MlbackProductMapper {

    int insert(MlbackProduct record);

    MlbackProduct selectByPrimaryKey(Integer productId);

    int updateByPrimaryKeyWithBLOBs(MlbackProduct record);

    int updateByPrimaryKey(MlbackProduct record);
    
    
    //插入新类目
    int insertSelective(MlbackProduct record);
    //删除本条
    int deleteByPrimaryKey(Integer productId);
    //更新本条
    int updateByPrimaryKeySelective(MlbackProduct record);
    //查询全部登陆用户
	List<MlbackProduct> selectMlbackProductGetAll();
    //查询ProductByParam
    List<MlbackProduct> selectMlbackProductByParam(MlbackProduct example);
  //查询ProductByParam
    List<MlbackProduct> selectMlbackProductSimpleByParam(MlbackProduct example);
    //查询BackSearch
    List<MlbackProduct> selectMlbackProductBackSearch(MlbackProduct mlbackProductReq);
    
    List<MlbackProduct> selectMlbackProductbyPid(MlbackProduct mlbackProductReq);
    
    List<MlbackProduct> selectMlbackProductbyCategorySeo(MlbackProduct mlbackProductReq);
//  //查询后台登陆用户
//  MlbackProduct selectMlbackProductById(MlbackProduct example);
//	//查询全部Category的parentid信息
//	List<MlbackProduct> selectMlbackProductGetAllByParentId();
//	//通过CategoryBySeo查找该类的详情
//	List<MlbackProduct> selectMlbackProductBySeo(MlbackProduct MlbackProduct);
//	//selectCategorylistByParentId	onuse	20191226	检查
//	List<MlbackProduct> selectProductlistByParam(MlbackProduct MlbackProduct);
//	//selectMlbackProductBackSearch	服务端需要用查询
//	List<MlbackProduct> selectMlbackProductBackSearch(MlbackProduct MlbackProductReq);
    //查询单个产品信息(无富文本描述)
  	List<MlbackProduct> selectMlbackProductSimple(MlbackProduct mlbackProductReq);

  	//模糊查询
	List<MlbackProduct> selectMlbackProductLike(MlbackProduct mlbackProduct);
	//模糊查询数量
	List<MlbackProduct> selectMlbackProductLikeNum(MlbackProduct mlbackProduct);

	List<MlbackProduct> selectMlbackProductDownLoadByParam(MlbackProduct mlbackProductReq);

	List<MlbackProduct> selectMlbackProductBeforeTime(MlbackProduct mlbackProductReq);

	List<MlbackProduct> selectMlbackProductSimpleByParamOnlyProDateilUsed(MlbackProduct mlbackProductReq);
}