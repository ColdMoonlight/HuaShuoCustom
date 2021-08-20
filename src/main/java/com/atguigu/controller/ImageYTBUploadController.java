package com.atguigu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.atguigu.bean.MlbackVideo;
import com.atguigu.bean.MlbackVideoShowArea;
import com.atguigu.common.Msg;
import com.atguigu.service.MlbackVideoService;
import com.atguigu.service.MlbackVideoShowAreaService;
import com.atguigu.service.ThumbnailService;
import com.atguigu.service.UploadService;
import com.atguigu.utils.ImageNameUtil;
import com.atguigu.utils.URLLocationUtils;

/**
 * ImageUploadPage
 * @author	zsh
 */
@Controller
@RequestMapping("/ImageYTBUpload")
public class ImageYTBUploadController {
	
	@Autowired
	UploadService uploadService;
	
	@Autowired
	ThumbnailService thumbnailService;
	
	@Autowired
	MlbackVideoShowAreaService mlbackVideoShowAreaService;
	
	@Autowired
	MlbackVideoService mlbackVideoService;
	
	/**
	 * 	onuse	20200103	检查
	 * */
	@RequestMapping(value="/uploadVideoShowAreaWapImg",method=RequestMethod.POST)
	@ResponseBody
	public Msg uploadVideoShowAreaWapImg(@RequestParam("image")CommonsMultipartFile file,
			@RequestParam("videoshowareaId")Integer videoshowareaId,@RequestParam("type")String type,
			HttpSession session,HttpServletResponse rep,HttpServletRequest res){
		
		//判断参数,确定信息
		String typeName=ImageNameUtil.gettypeName(type);//proidDiscout
		
		String videoshowareaIdStr = videoshowareaId+"";
		String imgName = ImageNameUtil.getfilename(typeName,videoshowareaIdStr);
		
		//当前服务器路径
		String basePathStr = URLLocationUtils.getbasePathStr(rep,res);
        System.out.println("basePathStr:"+basePathStr);
		
		String uploadPath = "static/upload/img/VideoShowArea";
		String realUploadPath = session.getServletContext().getRealPath(uploadPath);
				
		String imageUrl ="";
		String sqlimageUrl="";
		try {
			
			imageUrl = uploadService.uploadImage(file, uploadPath, realUploadPath,imgName);//图片原图路径
			sqlimageUrl=basePathStr+imageUrl;
			System.out.println("sqlimageUrl:"+sqlimageUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}

		MlbackVideoShowArea mlbackVideoShowAreaReq = new MlbackVideoShowArea();
		mlbackVideoShowAreaReq.setVideoshowareaId(videoshowareaId);
		mlbackVideoShowAreaReq.setVideoshowareaWapimgurl(sqlimageUrl);
		mlbackVideoShowAreaService.updateByPrimaryKeySelective(mlbackVideoShowAreaReq);
		
		return Msg.success().add("resMsg", "登陆成功").add("imageUrl", imageUrl).add("sqlimageUrl", sqlimageUrl);
	}
	
	/**
	 * 	onuse	20200103	检查
	 * */
	@RequestMapping(value="/uploadVideoShowAreaPcImg",method=RequestMethod.POST)
	@ResponseBody
	public Msg uploadVideoShowAreaPcImg(@RequestParam("image")CommonsMultipartFile file,
			@RequestParam("videoshowareaId")Integer videoshowareaId,@RequestParam("type")String type,
			HttpSession session,HttpServletResponse rep,HttpServletRequest res){
		
		//判断参数,确定信息
		String typeName=ImageNameUtil.gettypeName(type);//proidDiscout
		
		String videoshowareaIdStr = videoshowareaId+"";
		String imgName = ImageNameUtil.getfilename(typeName,videoshowareaIdStr);
		
		//当前服务器路径
		String basePathStr = URLLocationUtils.getbasePathStr(rep,res);
        System.out.println("basePathStr:"+basePathStr);
		
		String uploadPath = "static/upload/img/VideoShowArea";
		String realUploadPath = session.getServletContext().getRealPath(uploadPath);
				
		String imageUrl ="";
		String sqlimageUrl="";
		try {
			
			imageUrl = uploadService.uploadImage(file, uploadPath, realUploadPath,imgName);//图片原图路径
			sqlimageUrl=basePathStr+imageUrl;
			System.out.println("sqlimageUrl:"+sqlimageUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}

		MlbackVideoShowArea mlbackVideoShowAreaReq = new MlbackVideoShowArea();
		mlbackVideoShowAreaReq.setVideoshowareaId(videoshowareaId);
		mlbackVideoShowAreaReq.setVideoshowareaPcimgurl(sqlimageUrl);
		mlbackVideoShowAreaService.updateByPrimaryKeySelective(mlbackVideoShowAreaReq);
		
		return Msg.success().add("resMsg", "登陆成功").add("imageUrl", imageUrl).add("sqlimageUrl", sqlimageUrl);
	}
	
	/**
	 * 	onuse	20200103	检查
	 * */
	@RequestMapping(value="/uploadVideoImg",method=RequestMethod.POST)
	@ResponseBody
	public Msg uploadVideoImg(@RequestParam("image")CommonsMultipartFile file,
			@RequestParam("videoId")Integer videoId,@RequestParam("type")String type,
			HttpSession session,HttpServletResponse rep,HttpServletRequest res){
		
		//判断参数,确定信息
		String typeName=ImageNameUtil.gettypeName(type);//proidDiscout
		
		String videoIdStr = videoId+"";
		String imgName = ImageNameUtil.getfilename(typeName,videoIdStr);
		
		//当前服务器路径
		String basePathStr = URLLocationUtils.getbasePathStr(rep,res);
        System.out.println("basePathStr:"+basePathStr);
		
		String uploadPath = "static/upload/img/Video";
		String realUploadPath = session.getServletContext().getRealPath(uploadPath);
				
		String imageUrl ="";
		String sqlimageUrl="";
		try {
			
			imageUrl = uploadService.uploadImage(file, uploadPath, realUploadPath,imgName);//图片原图路径
			sqlimageUrl=basePathStr+imageUrl;
			System.out.println("sqlimageUrl:"+sqlimageUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}

		MlbackVideo mlbackVideoReq = new MlbackVideo();
		mlbackVideoReq.setVideoId(videoId);
		mlbackVideoReq.setVideoUrl(sqlimageUrl);
		mlbackVideoService.updateByPrimaryKeySelective(mlbackVideoReq);
		
		return Msg.success().add("resMsg", "登陆成功").add("imageUrl", imageUrl).add("sqlimageUrl", sqlimageUrl);
	}
}
