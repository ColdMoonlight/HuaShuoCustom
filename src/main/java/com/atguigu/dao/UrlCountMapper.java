package com.atguigu.dao;

import com.atguigu.bean.UrlCount;
import java.util.List;

public interface UrlCountMapper {

	List<UrlCount> selectCartUrlCountByTime(UrlCount urlCountReq);

	List<UrlCount> selectCheckoutUrlCountByTime(UrlCount urlCountReq);

	List<UrlCount> selectSearchUrlCountByTime(UrlCount urlCountReq);
	
	List<UrlCount> selectCouponCountByTime(UrlCount urlCountReq);
	
	List<UrlCount> selectMoreBuyCountByTime(UrlCount urlCountReq);//复购率查询

	List<UrlCount> selectMoreBuyCountFromAddressByTime(UrlCount urlCountReq);
	
}