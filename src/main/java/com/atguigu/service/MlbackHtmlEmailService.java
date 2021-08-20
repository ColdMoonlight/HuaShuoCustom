package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlbackHtmlEmail;
import com.atguigu.dao.MlbackHtmlEmailMapper;

@Service
public class MlbackHtmlEmailService {
	
	@Autowired
	MlbackHtmlEmailMapper mlbackHtmlEmailMapper;

	/**1
	 * @author Shinelon
	 * @param MlbackHtmlEmail
	 * @exception add方法用户信息是否存在
	 * 
	 * */
	public int insertSelective(MlbackHtmlEmail MlbackHtmlEmail) {
		int intReslut = mlbackHtmlEmailMapper.insertSelective(MlbackHtmlEmail);
		return intReslut;
	}
	
	/**2
	 * @author Shinelon
	 * @param MlbackHtmlEmail
	 * @exception 删除本条信息
	 * 
	 * */
	public int deleteByPrimaryKey(int footnavId) {
		int  intReslut = mlbackHtmlEmailMapper.deleteByPrimaryKey(footnavId);
		return intReslut;
	}

	/**3
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条信息
	 * 
	 * */
	public int updateByPrimaryKeySelective(MlbackHtmlEmail MlbackHtmlEmail) {
		int  intReslut = mlbackHtmlEmailMapper.updateByPrimaryKeySelective(MlbackHtmlEmail);
		return intReslut;
	}
	
	
	public MlbackHtmlEmail selectByPrimaryKey(int footnavId) {
		MlbackHtmlEmail  mlbackHtmlEmail = mlbackHtmlEmailMapper.selectByPrimaryKey(footnavId);
		return mlbackHtmlEmail;
	}
	
	/**6
	 * @author Shinelon
	 * @param MlbackProduct
	 * @exception 查看全部的地址信息
	 * 
	 * */
	public List<MlbackHtmlEmail> selectMlbackHtmlEmailAll() {
		List<MlbackHtmlEmail>  mlbackAreafreightList = mlbackHtmlEmailMapper.selectMlbackHtmlEmailGetAll();
		return mlbackAreafreightList;
	}

	public List<MlbackHtmlEmail> selectMlbackHtmlEmailListByName(MlbackHtmlEmail MlbackHtmlEmailReq) {
		List<MlbackHtmlEmail>  MlbackHtmlEmailList = mlbackHtmlEmailMapper.selectMlbackHtmlEmailListByName(MlbackHtmlEmailReq);
		return MlbackHtmlEmailList;
	}
	
}
