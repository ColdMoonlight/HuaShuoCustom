package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlbackProductSku;
import com.atguigu.dao.MlbackProductSkuMapper;

@Service
public class MlbackProductSkuService {
	
	@Autowired
	MlbackProductSkuMapper mlbackProductSkuMapper;
	
	/**
	 * @author Shinelon
	 * @param MlbackProductSku
	 * @exception add方法MlbackProductSku
	 * 
	 * */
	public int insertSelective(MlbackProductSku mlbackProductSku) {
		int intReslut = mlbackProductSkuMapper.insertSelective(mlbackProductSku);
		return intReslut;
	}

	/**
	 * @author Shinelon
	 * @param MlbackProductSku
	 * @exception 删除本条MlbackProductSku
	 * 
	 * */
	public int deleteByPrimaryKey(int productattrvalueId) {
		int  intReslut = mlbackProductSkuMapper.deleteByPrimaryKey(productattrvalueId);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条MlbackProductSku
	 * 
	 * */
	public int updateByPrimaryKeySelective(MlbackProductSku mlbackProductSku) {
		int  intReslut = mlbackProductSkuMapper.updateByPrimaryKeySelective(mlbackProductSku);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部MlbackProductSku信息
	 * 
	 * */
	public List<MlbackProductSku> selectMlbackProductSkuListByPId(MlbackProductSku mlbackProductSku) {
		List<MlbackProductSku>  MlbackProductSkuList = mlbackProductSkuMapper.selectMlbackProductSkuListByPId(mlbackProductSku);
		return MlbackProductSkuList;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部MlbackProductSku信息
	 * 
	 * */
	public List<MlbackProductSku> selectMlbackProductSkuListByPIdTogether(MlbackProductSku mlbackProductSku) {
		List<MlbackProductSku>  MlbackProductSkuList = mlbackProductSkuMapper.selectMlbackProductSkuListByPIdTogether(mlbackProductSku);
		return MlbackProductSkuList;
	}
	
	public List<MlbackProductSku> selectMlProductSkuListByPskuCode(MlbackProductSku mlbackProductSkuReq) {
		List<MlbackProductSku>  mlbackProductSkuList = mlbackProductSkuMapper.selectMlProductSkuListByPskuCode(mlbackProductSkuReq);
		return mlbackProductSkuList;
	}

}
