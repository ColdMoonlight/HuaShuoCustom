package com.atguigu.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import net.coobird.thumbnailator.Thumbnails;

@Service
public class ThumbnailService {
	
	public static final int WIDTH = 200;
	public static final int HEIGH = 200;
	
	public static final int DisWIDTH = 50;
	public static final int DisHEIGH = 50;
	
	/**
	 * @author Shinelon
	 * @param MlbackAdmin
	 * @exception 查看用户信息是否存在
	 * 
	 * */
//	public String Thumbnail(CommonsMultipartFile file,String uploadPath,String realUploadUrl,String imgName) {
//		
//		try {
//			String des = realUploadUrl+"/"+"thumb_"+imgName;//文件最终的路径
////			String des = realUploadUrl+"/"+"thumb_"+file.getOriginalFilename();
//			Thumbnails.of(file.getInputStream()).scale(0.4f).toFile(des);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return uploadPath+"/"+"thumb_"+imgName;
//		
//	}
	
}
