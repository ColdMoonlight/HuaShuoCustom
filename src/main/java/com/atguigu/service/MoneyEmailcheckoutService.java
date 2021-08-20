package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MoneyEmailcheckout;
import com.atguigu.dao.MoneyEmailcheckoutMapper;

@Service
public class MoneyEmailcheckoutService {
	
	@Autowired
	MoneyEmailcheckoutMapper moneyEmailcheckoutMapper;

	/**1
	 * add方法
	 * */
	public int insertSelective(MoneyEmailcheckout moneyEmailcheckout) {
		int intReslut = moneyEmailcheckoutMapper.insertSelective(moneyEmailcheckout);
		return intReslut;
	}
	
	/**2
	 * 删除本条信息
	 * */
	public int deleteByPrimaryKey(int emailcheckoutId) {
		int  intReslut = moneyEmailcheckoutMapper.deleteByPrimaryKey(emailcheckoutId);
		return intReslut;
	}

	/**3
	 * 更新本条信息
	 * */
	public int updateByPrimaryKeySelective(MoneyEmailcheckout moneyEmailcheckout) {
		int  intReslut = moneyEmailcheckoutMapper.updateByPrimaryKeySelective(moneyEmailcheckout);
		return intReslut;
	}

	/**4
	 * 查单条信息byId
	 * */
	public List<MoneyEmailcheckout> selectMoneyEmailcheckoutByPayId(MoneyEmailcheckout moneyEmailcheckout) {
		List<MoneyEmailcheckout>  moneyEmailcheckoutList = moneyEmailcheckoutMapper.selectMoneyEmailcheckoutByPayId(moneyEmailcheckout);
		return moneyEmailcheckoutList;
	}
	
	/**5
	 * 查单条信息byId
	 * */
	public List<MoneyEmailcheckout> selectMoneyEmailcheckoutByPayOId(MoneyEmailcheckout moneyEmailcheckout) {
		List<MoneyEmailcheckout>  moneyEmailcheckoutList = moneyEmailcheckoutMapper.selectMoneyEmailcheckoutByPayOId(moneyEmailcheckout);
		return moneyEmailcheckoutList;
	}
	
	/**6
	 * 查单条信息byId
	 * */
	public List<MoneyEmailcheckout> selectMoneyEmailcheckoutByPayOnameAndOid(MoneyEmailcheckout moneyEmailcheckout) {
		List<MoneyEmailcheckout>  moneyEmailcheckoutList = moneyEmailcheckoutMapper.selectMoneyEmailcheckoutByPayOnameAndOid(moneyEmailcheckout);
		return moneyEmailcheckoutList;
	}
	
}
