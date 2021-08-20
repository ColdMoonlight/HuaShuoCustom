package com.atguigu.dao;

import java.util.List;
import com.atguigu.bean.CouponAnalysisDate;

public interface CouponAnalysisDateMapper {

	List<CouponAnalysisDate> selectCouponAnalysisList(CouponAnalysisDate couponAnalysisDateReq);
    
}