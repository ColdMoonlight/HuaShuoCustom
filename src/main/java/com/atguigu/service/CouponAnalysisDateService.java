package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.CouponAnalysisDate;
import com.atguigu.dao.CouponAnalysisDateMapper;

@Service
public class CouponAnalysisDateService {
	
	@Autowired
	CouponAnalysisDateMapper couponAnalysisDateMapper;
	
	public List<CouponAnalysisDate> selectCouponAnalysisList(CouponAnalysisDate couponAnalysisDateReq) {
		List<CouponAnalysisDate>  couponAnalysisDateList = couponAnalysisDateMapper.selectCouponAnalysisList(couponAnalysisDateReq);
		return couponAnalysisDateList;
	}
	
}
