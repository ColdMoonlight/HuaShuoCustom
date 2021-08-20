package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlbackBlog;
import com.atguigu.dao.MlbackBlogMapper;

@Service
public class MlbackBlogService {
	
	@Autowired
	MlbackBlogMapper mlbackBlogMapper;
	
	/**
	 * @author Shinelon
	 * @param MlbackBlog
	 * @exception add方法用户信息是否存在
	 * */
	public int insertSelective(MlbackBlog mlbackBlog) {
		int intReslut = mlbackBlogMapper.insertSelective(mlbackBlog);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 删除本条信息
	 * */
	public int deleteByPrimaryKey(int productId) {
		int  intReslut = mlbackBlogMapper.deleteByPrimaryKey(productId);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条信息
	 * */
	public int updateByPrimaryKeySelective(MlbackBlog mlbackBlog) {
		int  intReslut = mlbackBlogMapper.updateByPrimaryKeySelective(mlbackBlog);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * */
	public List<MlbackBlog> selectMlbackBlogGetAll() {
		List<MlbackBlog>  mlbackBlogList = mlbackBlogMapper.selectMlbackBlogGetAll();
		return mlbackBlogList;
	}

	/**
	 * @author Shinelon
	 * @param MlbackBlog
	 * @exception 查看用户信息是否存在
	 * */
	public List<MlbackBlog> selectMlbackBlogByParam(MlbackBlog mlbackBlog) {
		List<MlbackBlog>  mlbackBlogList = mlbackBlogMapper.selectMlbackBlogByParam(mlbackBlog);
		return mlbackBlogList;
	}

	public List<MlbackBlog> selectMlbackBlogBack(MlbackBlog mlbackBlogReq) {
		List<MlbackBlog>  mlbackBlogList = mlbackBlogMapper.selectMlbackBlogBack(mlbackBlogReq);
		return mlbackBlogList;
	}

	public List<MlbackBlog> selectMlbackBlogSimpleByParam(MlbackBlog mlbackBlogReq) {
		List<MlbackBlog>  mlbackBlogList = mlbackBlogMapper.selectMlbackBlogSimpleByParam(mlbackBlogReq);
		return mlbackBlogList;
	}

}
