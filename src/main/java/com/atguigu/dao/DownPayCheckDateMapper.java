package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.DownPayCheckDate;
public interface DownPayCheckDateMapper {

	List<DownPayCheckDate> selectHighPayCheckList(DownPayCheckDate downPayIFDateReq);
    
}