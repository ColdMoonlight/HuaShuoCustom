package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.UrlCount;
import com.atguigu.dao.UrlCountMapper;

@Service
public class UrlCountService {
	
	@Autowired
	UrlCountMapper urlCountMapper;
	
	/**
	 * @author Shinelon
	 * @param UrlCount
	 * @exception selectUrlCountByTime
	 * */
	public List<UrlCount> selectCartUrlCountByTime(UrlCount urlCountReq) {
		List<UrlCount>  urlCountList = urlCountMapper.selectCartUrlCountByTime(urlCountReq);
		return urlCountList;
	}
	
	public List<UrlCount> selectCheckoutUrlCountByTime(UrlCount urlCountReq) {
		List<UrlCount>  urlCountList = urlCountMapper.selectCheckoutUrlCountByTime(urlCountReq);
		return urlCountList;
	}
	
	public List<UrlCount> selectSearchUrlCountByTime(UrlCount urlCountReq) {
		List<UrlCount>  urlCountList = urlCountMapper.selectSearchUrlCountByTime(urlCountReq);
		return urlCountList;
	}
	
	public List<UrlCount> selectCouponCountByTime(UrlCount urlCountReq) {
		List<UrlCount>  urlCountList = urlCountMapper.selectCouponCountByTime(urlCountReq);
		return urlCountList;
	}
	
	public List<UrlCount> selectMoreBuyCountByTime(UrlCount urlCountReq) {
		List<UrlCount>  urlCountList = urlCountMapper.selectMoreBuyCountByTime(urlCountReq);
		return urlCountList;
	}
	
	public List<UrlCount> selectMoreBuyCountFromAddressByTime(UrlCount urlCountReq) {
		List<UrlCount>  urlCountList = urlCountMapper.selectMoreBuyCountFromAddressByTime(urlCountReq);
		return urlCountList;
	}

}
