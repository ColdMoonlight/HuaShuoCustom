package com.atguigu.dao;

import java.util.List;
import com.atguigu.bean.MlbackCoupon;

public interface MlbackCouponMapper {

    int insert(MlbackCoupon record);

    MlbackCoupon selectByPrimaryKey(Integer couponId);

    int updateByPrimaryKey(MlbackCoupon record);
    
    //插入新类目00
    int insertSelective(MlbackCoupon record);
    //删除本条00
    int deleteByPrimaryKey(Integer couponId);
    //更新本条00
    int updateByPrimaryKeySelective(MlbackCoupon record);
    //查询后台登陆用户00
    List<MlbackCoupon> selectMlbackCouponByConditionS(MlbackCoupon example);
    //查询后台登陆用户00
    List<MlbackCoupon> selectMlbackCouponBYCode(MlbackCoupon example);
    //查询全部登陆用户00
	List<MlbackCoupon> selectMlbackCouponGetAll();
	//查询全部生效中的可抽奖优惠券
	List<MlbackCoupon> selectMlbackCouponByLuckDrawType(MlbackCoupon mlbackCoupon);

	List<MlbackCoupon> selectMlbackCouponBackSearch(MlbackCoupon mlbackCouponReq);
	
	//查看全部mlbackCoupon信息,并按状态排序
	List<MlbackCoupon> selectMlbackCouponOrderByStatus();
}