package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlfrontCart;
import com.atguigu.dao.MlfrontCartMapper;

@Service
public class MlfrontCartService {
	
	
	@Autowired
	MlfrontCartMapper mlfrontCartMapper;
	
	/**
	 * @author shaohua200617
	 * @param MlfrontCart
	 * @exception add方法用户信息是否存在
	 * */
	public int insertSelective(MlfrontCart mlfrontCart) {
		int intReslut = mlfrontCartMapper.insertSelective(mlfrontCart);
		return intReslut;
	}

	/**
	 * @author shaohua200617
	 * @param 
	 * @exception 删除本条信息
	 * */
	public int deleteByPrimaryKey(int cartId) {
		int  intReslut = mlfrontCartMapper.deleteByPrimaryKey(cartId);
		return intReslut;
	}
	
	/**
	 * @author shaohua200617
	 * @param 
	 * @exception 更新本条信息
	 * */
	public int updateByPrimaryKeySelective(MlfrontCart mlfrontCart) {
		int  intReslut = mlfrontCartMapper.updateByPrimaryKeySelective(mlfrontCart);
		return intReslut;
	}
	/**
	 * @author shaohua200617
	 * @param 
	 * @exception selectMlfrontCartByDate
	 * */
	public List<MlfrontCart> selectMlfrontCartByDate(MlfrontCart mlfrontCart) {
		List<MlfrontCart>  mlfrontCartList = mlfrontCartMapper.selectMlfrontCartByDate(mlfrontCart);
		return mlfrontCartList;
	}

	/**
	 * @author shaohua200617
	 * @param 
	 * @exception selectMlfrontCartGetAllHundred
	 * */
	public List<MlfrontCart> selectMlfrontCartGetAllHundred() {
		List<MlfrontCart>  mlfrontCartList = mlfrontCartMapper.selectMlfrontCartGetAllHundred();
		return mlfrontCartList;
	}
	
	/**
	 * @author shaohua200617
	 * @param 
	 * @exception selectMlfrontCartByIp
	 * */
	public List<MlfrontCart> selectMlfrontCartByIp(MlfrontCart mlfrontCart) {
		List<MlfrontCart>  mlfrontCartList = mlfrontCartMapper.selectMlfrontCartByIp(mlfrontCart);
		return mlfrontCartList;
	}
	
	/**
	 * @author shaohua0617
	 * @param 
	 * @exception 查看全部用户信息
	 * */
	public List<MlfrontCart> selectMlfrontCartByUidAndStatus(MlfrontCart mlfrontCart) {
		List<MlfrontCart>  mlfrontCartList = mlfrontCartMapper.selectMlfrontCartByUidAndStatus(mlfrontCart);
		return mlfrontCartList;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 
	 * */
	public MlfrontCart selectMlfrontCartByCartId(Integer cartId) {
		MlfrontCart  mlfrontCartOne = mlfrontCartMapper.selectMlfrontCartByCartId(cartId);
		return mlfrontCartOne;
	}
	
}
