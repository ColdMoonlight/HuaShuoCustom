package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlbackAddCheakoutViewDetail;
import com.atguigu.dao.MlbackAddCheakoutViewDetailMapper;

@Service
public class MlbackAddCheakoutViewDetailService {
	
	@Autowired
	MlbackAddCheakoutViewDetailMapper mlbackAddCheakoutViewDetailMapper;
	
	/**
	 * @author Shinelon
	 * @param mlbackAddCheakoutViewDetail
	 * @exception add方法用户信息是否存在
	 * */
	public int insertSelective(MlbackAddCheakoutViewDetail mlbackAddCheakoutViewDetail) {
		int intReslut = mlbackAddCheakoutViewDetailMapper.insertSelective(mlbackAddCheakoutViewDetail);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 删除本条信息
	 * */
	public int deleteByPrimaryKey(int addcartviewdetailId) {
		int  intReslut = mlbackAddCheakoutViewDetailMapper.deleteByPrimaryKey(addcartviewdetailId);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看Cheakout信息
	 * */
	public List<MlbackAddCheakoutViewDetail> selectMlbackAddCheakoutViewDetailByTimeAndActnum(MlbackAddCheakoutViewDetail mlbackAddCheakoutViewDetail) {
		List<MlbackAddCheakoutViewDetail>  mlbackAddCheakoutViewDetailList = mlbackAddCheakoutViewDetailMapper.selectMlbackAddCheakoutViewDetailByTimeAndActnum(mlbackAddCheakoutViewDetail);
		return mlbackAddCheakoutViewDetailList;
	}

}
