package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MlfrontCouponLuckyDraw;

public interface MlfrontCouponLuckyDrawMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MlfrontCouponLuckyDraw mlfrontCouponLuckyDraw);

    int insertSelective(MlfrontCouponLuckyDraw mlfrontCouponLuckyDraw);

    MlfrontCouponLuckyDraw selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MlfrontCouponLuckyDraw mlfrontCouponLuckyDraw);

    int updateByPrimaryKey(MlfrontCouponLuckyDraw mlfrontCouponLuckyDraw);
    
    //获取当前session下是否有抽奖优惠券记录
    List<MlfrontCouponLuckyDraw> selectMlfrontCouponLuckyDrawBySessionId(MlfrontCouponLuckyDraw mlfrontCouponLuckyDraw);
}