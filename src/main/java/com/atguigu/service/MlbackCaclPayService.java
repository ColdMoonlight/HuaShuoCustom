package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlbackCaclPay;
import com.atguigu.dao.MlbackCaclPayMapper;

@Service
public class MlbackCaclPayService {
	
	@Autowired
	MlbackCaclPayMapper mlbackCaclPayMapper;

	/**1
	 * @author Shinelon
	 * @param MlbackCaclPay
	 * @exception 查看全部的地址信息
	 * */
	public int insertSelective(MlbackCaclPay mlbackCaclPay) {
		int intReslut = mlbackCaclPayMapper.insertSelective(mlbackCaclPay);
		return intReslut;
	}
	
	/**2
	 * @author Shinelon
	 * @param 
	 * @exception 删除本条信息
	 * */
	public int deleteByPrimaryKey(int showareaId) {
		int  intReslut = mlbackCaclPayMapper.deleteByPrimaryKey(showareaId);
		return intReslut;
	}
	
	/**3
	 * @author Shinelon
	 * @param 
	 * @exception updateByPrimaryKeySelective
	 * */
	public int updateByPrimaryKeySelective(MlbackCaclPay mlbackCaclPay) {
		int  intReslut = mlbackCaclPayMapper.updateByPrimaryKeySelective(mlbackCaclPay);
		return intReslut;
	}
	
	/**4
	 * @author Shinelon
	 * @param 
	 * @exception selectMlbackCaclPayByParams
	 * */
	public List<MlbackCaclPay> selectMlbackCaclPayByParams(MlbackCaclPay mlbackCaclPay) {
		List<MlbackCaclPay>  mlbackCaclPayList = mlbackCaclPayMapper.selectMlbackCaclPayByParams(mlbackCaclPay);
		return mlbackCaclPayList;
	}
}
