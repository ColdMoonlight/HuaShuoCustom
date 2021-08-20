package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlfrontUser;
import com.atguigu.dao.MlfrontUserMapper;

@Service
public class MlfrontUserService {
	
	@Autowired
	MlfrontUserMapper mlfrontUsertMapper;
	
	/**
	 * @author Shinelon
	 * @param MlfrontUser
	 * @exception add方法用户信息是否存在
	 * */
	public int insertSelective(MlfrontUser mlfrontUser) {
		int intReslut = mlfrontUsertMapper.insertSelective(mlfrontUser);
		return intReslut;
	}

	/**
	 * @author Shinelon
	 * @param 
	 * @exception 删除本条信息
	 * */
	public int deleteByPrimaryKey(int productId) {
		int  intReslut = mlfrontUsertMapper.deleteByPrimaryKey(productId);
		return intReslut;
	}

	/**
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条信息
	 * */
	public int updateByPrimaryKeySelective(MlfrontUser mlfrontUser) {
		int  intReslut = mlfrontUsertMapper.updateByPrimaryKeySelective(mlfrontUser);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条信息
	 * */
	public int updatePwdByEmail(MlfrontUser mlfrontUser) {
		int  intReslut = mlfrontUsertMapper.updatePwdByEmail(mlfrontUser);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * */
	public List<MlfrontUser> selectMlfrontUserWhenFirst(MlfrontUser mlfrontUser) {
		List<MlfrontUser>  mlfrontUserList = mlfrontUsertMapper.selectMlfrontUserWhenFirst(mlfrontUser);
		return mlfrontUserList;
	}
	
	/**
	 * @author Shinelon
	 * @param MlfrontUser
	 * @exception 查看用户信息是否存在
	 * */
	public List<MlfrontUser> selectMlfrontUserByConditionS(MlfrontUser mlfrontUser) {
		List<MlfrontUser>  mlfrontUserList = mlfrontUsertMapper.selectMlfrontUserByConditionS(mlfrontUser);
		return mlfrontUserList;
	}

	public List<MlfrontUser> selectMlfrontUserByDate(MlfrontUser mlfrontUser) {
		List<MlfrontUser>  mlfrontUserList = mlfrontUsertMapper.selectMlfrontUserByDate(mlfrontUser);
		return mlfrontUserList;
	}
	
	public List<MlfrontUser> selectMlfrontUserSimpleByDate(MlfrontUser mlfrontUser) {
		List<MlfrontUser>  mlfrontUserList = mlfrontUsertMapper.selectMlfrontUserSimpleByDate(mlfrontUser);
		return mlfrontUserList;
	}
	
	public List<MlfrontUser> selectMlfrontUserBackHomeByDate(MlfrontUser mlfrontUser) {
		List<MlfrontUser>  mlfrontUserList = mlfrontUsertMapper.selectMlfrontUserBackHomeByDate(mlfrontUser);
		return mlfrontUserList;
	}
	
}
