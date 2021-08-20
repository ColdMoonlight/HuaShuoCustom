package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlbackEmailRichText;
import com.atguigu.dao.MlbackEmailRichTextMapper;

@Service
public class MlbackEmailRichTextService {
	
	@Autowired
	MlbackEmailRichTextMapper mlbackEmailRichTextMapper;

	/**1
	 * @author Shinelon
	 * @param MlbackEmailRichText
	 * @exception add方法用户信息是否存在
	 * 
	 * */
	public int insertSelective(MlbackEmailRichText MlbackEmailRichText) {
		int intReslut = mlbackEmailRichTextMapper.insertSelective(MlbackEmailRichText);
		return intReslut;
	}
	
	/**2
	 * @author Shinelon
	 * @param MlbackEmailRichText
	 * @exception 删除本条信息
	 * 
	 * */
	public int deleteByPrimaryKey(int footnavId) {
		int  intReslut = mlbackEmailRichTextMapper.deleteByPrimaryKey(footnavId);
		return intReslut;
	}

	/**3
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条信息
	 * 
	 * */
	public int updateByPrimaryKeySelective(MlbackEmailRichText MlbackEmailRichText) {
		int  intReslut = mlbackEmailRichTextMapper.updateByPrimaryKeySelective(MlbackEmailRichText);
		return intReslut;
	}
	
	
	public MlbackEmailRichText selectByPrimaryKey(int footnavId) {
		MlbackEmailRichText  MlbackEmailRichText = mlbackEmailRichTextMapper.selectByPrimaryKey(footnavId);
		return MlbackEmailRichText;
	}
	
	/**6
	 * @author Shinelon
	 * @param MlbackProduct
	 * @exception 查看全部的地址信息
	 * */
	public List<MlbackEmailRichText> selectMlbackEmailRichTextAll() {
		List<MlbackEmailRichText>  mlbackAreafreightList = mlbackEmailRichTextMapper.selectMlbackEmailRichTextGetAll();
		return mlbackAreafreightList;
	}

	public List<MlbackEmailRichText> selectMlbackEmailRichTextListByName(MlbackEmailRichText MlbackEmailRichTextReq) {
		List<MlbackEmailRichText>  MlbackEmailRichTextList = mlbackEmailRichTextMapper.selectMlbackEmailRichTextListByName(MlbackEmailRichTextReq);
		return MlbackEmailRichTextList;
	}
	
}
