package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MoneySmscheckout;
import com.atguigu.dao.MoneySmscheckoutMapper;

@Service
public class MoneySmscheckoutService {
	
	@Autowired
	MoneySmscheckoutMapper moneySmscheckoutMapper;

	/**1
	 * add方法
	 * */
	public int insertSelective(MoneySmscheckout moneySmscheckout) {
		int intReslut = moneySmscheckoutMapper.insertSelective(moneySmscheckout);
		return intReslut;
	}
	
	/**2
	 * 删除本条信息
	 * */
	public int deleteByPrimaryKey(int smscheckoutId) {
		int  intReslut = moneySmscheckoutMapper.deleteByPrimaryKey(smscheckoutId);
		return intReslut;
	}

	/**3
	 * 更新本条信息
	 * */
	public int updateByPrimaryKeySelective(MoneySmscheckout moneySmscheckout) {
		int  intReslut = moneySmscheckoutMapper.updateByPrimaryKeySelective(moneySmscheckout);
		return intReslut;
	}

	/**4
	 * 查单条信息byId
	 * */
	public List<MoneySmscheckout> selectMoneySmscheckoutByPayId(MoneySmscheckout moneySmscheckout) {
		List<MoneySmscheckout>  moneySmscheckoutList = moneySmscheckoutMapper.selectMoneySmscheckoutByPayId(moneySmscheckout);
		return moneySmscheckoutList;
	}
	
	/**5
	 * 查单条信息byId
	 * */
	public List<MoneySmscheckout> selectMoneySmscheckoutByPayOId(MoneySmscheckout moneySmscheckout) {
		List<MoneySmscheckout>  moneySmscheckoutList = moneySmscheckoutMapper.selectMoneySmscheckoutByPayOId(moneySmscheckout);
		return moneySmscheckoutList;
	}
	
	/**6
	 * 查单条信息byId
	 * */
	public List<MoneySmscheckout> selectMoneySmscheckoutByPayOnameAndOid(MoneySmscheckout moneySmscheckout) {
		List<MoneySmscheckout>  moneySmscheckoutList = moneySmscheckoutMapper.selectMoneySmscheckoutByPayOnameAndOid(moneySmscheckout);
		return moneySmscheckoutList;
	}

}
