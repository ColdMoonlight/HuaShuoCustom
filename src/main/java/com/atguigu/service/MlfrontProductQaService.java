package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.CustomerWholesale;
import com.atguigu.bean.MlfrontProductQa;
import com.atguigu.dao.MlfrontProductQaMapper;

@Service
public class MlfrontProductQaService {
	
	@Autowired
	MlfrontProductQaMapper MlfrontProductQaMapper;
	
	/**1.0
	 * @author Shinelon
	 * @param MlfrontProductQa
	 * @exception 
	 * */
	public int insertSelective(MlfrontProductQa MlfrontProductQa) {
		int intReslut = MlfrontProductQaMapper.insertSelective(MlfrontProductQa);
		return intReslut;
	}
	
	/**2.0
	 * @author
	 * @param 
	 * @exception 删除本条信息
	 * */
	public int deleteByPrimaryKey(int CatalogId) {
		int  intReslut = MlfrontProductQaMapper.deleteByPrimaryKey(CatalogId);
		return intReslut;
	}
	
	/**3.0
	 * @author
	 * @param 
	 * @exception 更新本条信息
	 * */
	public int updateByPrimaryKeySelective(MlfrontProductQa MlfrontProductQa) {
		int  intReslut = MlfrontProductQaMapper.updateByPrimaryKeySelective(MlfrontProductQa);
		return intReslut;
	}
	
	/**4.0
	 * @author
	 * @param 
	 * @exception 查看全部用户信息
	 * */
	public List<MlfrontProductQa> selectMlfrontProductQaGetAll() {
		List<MlfrontProductQa>  MlfrontProductQaList = MlfrontProductQaMapper.selectMlfrontProductQaGetAll();
		return MlfrontProductQaList;
	}
	/**5.0
	 * @author Shinelon
	 * @param MlfrontProductQa
	 * @exception 查看单条信息 
	 * */
	public List<MlfrontProductQa> selectMlfrontProductQaById(MlfrontProductQa MlfrontProductQa) {
		List<MlfrontProductQa>  MlfrontProductQaList = MlfrontProductQaMapper.selectMlfrontProductQaById(MlfrontProductQa);
		return MlfrontProductQaList;
	}
}
