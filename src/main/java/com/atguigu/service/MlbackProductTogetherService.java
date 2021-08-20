package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlbackProductTogether;
import com.atguigu.dao.MlbackProductTogetherMapper;

@Service
public class MlbackProductTogetherService {
	
	@Autowired
	MlbackProductTogetherMapper mlbackProductTogetherMapper;
	
	/**1.0
	 * @author Shinelon
	 * @param MlbackProductTogether
	 * @exception add方法用户信息是否存在
	 * */
	public int insertSelective(MlbackProductTogether MlbackProductTogether) {
		int intReslut = mlbackProductTogetherMapper.insertSelective(MlbackProductTogether);
		return intReslut;
	}
	
	/**2.0
	 * @author
	 * @param 
	 * @exception 删除本条信息
	 * */
	public int deleteByPrimaryKey(int CatalogId) {
		int  intReslut = mlbackProductTogetherMapper.deleteByPrimaryKey(CatalogId);
		return intReslut;
	}
	
	/**3.0
	 * @author
	 * @param 
	 * @exception 更新本条信息
	 * */
	public int updateByPrimaryKeySelective(MlbackProductTogether MlbackProductTogether) {
		int  intReslut = mlbackProductTogetherMapper.updateByPrimaryKeySelective(MlbackProductTogether);
		return intReslut;
	}
	
	/**4.0
	 * @author
	 * @param 
	 * @exception 查看全部用户信息
	 * */
	public List<MlbackProductTogether> selectMlbackProductTogetherGetAll() {
		List<MlbackProductTogether>  MlbackProductTogetherList = mlbackProductTogetherMapper.selectMlbackProductTogetherGetAll();
		return MlbackProductTogetherList;
	}
	
	/**5.0
	 * @author Shinelon
	 * @param MlbackProductTogether
	 * @exception 查看用户信息是否存在
	 * */
	public List<MlbackProductTogether> selectMlbackProductTogetherById(MlbackProductTogether MlbackProductTogether) {
		List<MlbackProductTogether>  MlbackProductTogetherList = mlbackProductTogetherMapper.selectMlbackProductTogetherById(MlbackProductTogether);
		return MlbackProductTogetherList;
	}

	public List<MlbackProductTogether> selectMlbackProductTogetherBackSearch(MlbackProductTogether MlbackProductTogether) {
		List<MlbackProductTogether>  MlbackProductTogetherList = mlbackProductTogetherMapper.selectMlbackProductTogetherBackSearch(MlbackProductTogether);
		return MlbackProductTogetherList;
	}

	public List<MlbackProductTogether> selectMlbackProTogetherDownLoadByParam(MlbackProductTogether mlbackProductTogetherReq) {
		List<MlbackProductTogether>  mlbackProductTogetherList = mlbackProductTogetherMapper.selectMlbackProTogetherDownLoadByParam(mlbackProductTogetherReq);
		return mlbackProductTogetherList;
	}

}
