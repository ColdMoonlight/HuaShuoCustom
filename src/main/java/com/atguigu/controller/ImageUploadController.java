package com.atguigu.controller;

import java.util.List;
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
import com.atguigu.bean.MlbackActShowPro;
import com.atguigu.bean.MlbackBlog;
import com.atguigu.bean.MlbackCategory;
import com.atguigu.bean.MlbackCoupon;
import com.atguigu.bean.MlbackCouponDescTitle;
import com.atguigu.bean.MlbackProduct;
import com.atguigu.bean.MlbackProductImg;
import com.atguigu.bean.MlbackProductSku;
import com.atguigu.bean.MlbackReviewImg;
import com.atguigu.bean.MlbackShowArea;
import com.atguigu.bean.MlbackSlide;
import com.atguigu.bean.MlfrontReview;
import com.atguigu.common.Msg;
import com.atguigu.service.MlbackActShowProService;
import com.atguigu.service.MlbackBlogService;
import com.atguigu.service.MlbackCategoryService;
import com.atguigu.service.MlbackCouponDescTitleService;
import com.atguigu.service.MlbackCouponService;
import com.atguigu.service.MlbackProductImgService;
import com.atguigu.service.MlbackProductService;
import com.atguigu.service.MlbackProductSkuService;
import com.atguigu.service.MlbackReviewImgService;
import com.atguigu.service.MlbackShowAreaService;
import com.atguigu.service.MlbackSlideService;
import com.atguigu.service.MlfrontReviewService;
import com.atguigu.service.ThumbnailService;
import com.atguigu.service.UploadService;
import com.atguigu.utils.DateUtil;
import com.atguigu.utils.ImageNameUtil;
import com.atguigu.utils.URLLocationUtils;

/**
 * ImageUploadPage
 * @author	zsh
 */
@Controller
@RequestMapping("/ImageUpload")
public class ImageUploadController {
	
	@Autowired
	UploadService uploadService;
	
	@Autowired
	ThumbnailService thumbnailService;
	
	@Autowired
	MlbackCategoryService mlbackCategoryService;
	
	@Autowired
	MlbackProductService mlbackProductService;
	
	@Autowired
	MlbackProductImgService mlbackProductImgService;
	
	@Autowired
	MlbackReviewImgService mlbackReviewImgService;
	
	@Autowired
	MlbackSlideService mlbackSlideService;
	
	@Autowired
	MlbackShowAreaService mlbackShowAreaService;
	
	@Autowired
	MlbackActShowProService mlbackActShowProService;
	
	@Autowired
	MlbackCouponDescTitleService mlbackCouponDescTitleService;
	
	@Autowired
	MlbackCouponService mlbackCouponService;
	
	@Autowired
	MlbackProductSkuService mlbackProductSkuService;
	
	@Autowired
	MlbackBlogService mlbackBlogService;
	
	@Autowired
	MlfrontReviewService mlfrontReviewService;
	
	/**
	 * 	zsh	20201010
	 * */
	@RequestMapping(value="/thumImageCategory",method=RequestMethod.POST)
	@ResponseBody
	public Msg thumImageCategory(@RequestParam("image")CommonsMultipartFile file,@RequestParam("categorySeo")String categorySeo,
			@RequestParam("categoryId")Integer categoryId,@RequestParam("type")String type,
			HttpSession session,HttpServletResponse rep,HttpServletRequest res){
		
		//判断参数,确定信息
		String typeName=ImageNameUtil.gettypeName(type);
		
		String categoryIdStr = categoryId+"";
		String imgName = ImageNameUtil.getfilename(typeName,categoryIdStr);
		
		String uploadPath = "static/upload/img/category";
		String realUploadPath = session.getServletContext().getRealPath(uploadPath);
		
		//当前服务器路径
		String basePathStr = URLLocationUtils.getbasePathStr(rep,res);
        System.out.println("basePathStr:"+basePathStr);
		
		String imageUrl ="";
		String thumImageUrl ="";
		String sqlimageUrl="";
		String sqlthumImageUrl="";
		try {
			
			imageUrl = uploadService.uploadImage(file, uploadPath, realUploadPath,imgName);//图片原图路径
			sqlimageUrl=basePathStr+imageUrl;
			System.out.println("sqlimageUrl:"+sqlimageUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		MlbackCategory mlbackCategory = new MlbackCategory();
		mlbackCategory.setCategoryId(categoryId);
		mlbackCategory.setCategoryImgurl(sqlimageUrl);	
		mlbackCategoryService.updateByPrimaryKeySelective(mlbackCategory);
		
		return Msg.success().add("resMsg", "登陆成功").add("imageUrl", imageUrl).add("thumImageUrl", thumImageUrl)
				.add("sqlimageUrl", sqlimageUrl).add("sqlthumImageUrl", sqlthumImageUrl);
	}
	
	/**
	 * 	zsh	20201012
	 * */
	@RequestMapping(value="/thumImageCategoryPc",method=RequestMethod.POST)
	@ResponseBody
	public Msg thumImageCategoryPc(@RequestParam("image")CommonsMultipartFile file,@RequestParam("categorySeo")String categorySeo,
			@RequestParam("categoryId")Integer categoryId,@RequestParam("type")String type,
			HttpSession session,HttpServletResponse rep,HttpServletRequest res){
		
		//判断参数,确定信息
		String typeName=ImageNameUtil.gettypeName(type);
		
		String categoryIdStr = categoryId+"";
		String imgName = ImageNameUtil.getfilename(typeName,categoryIdStr);
		
		String uploadPath = "static/upload/img/category";
		String realUploadPath = session.getServletContext().getRealPath(uploadPath);
		
		//当前服务器路径
		String basePathStr = URLLocationUtils.getbasePathStr(rep,res);
        System.out.println("basePathStr:"+basePathStr);
		
		String imageUrl ="";
		String thumImageUrl ="";
		String sqlimageUrl="";
		String sqlthumImageUrl="";
		try {
			
			imageUrl = uploadService.uploadImage(file, uploadPath, realUploadPath,imgName);//图片原图路径
			sqlimageUrl=basePathStr+imageUrl;
			System.out.println("sqlimageUrl:"+sqlimageUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		MlbackCategory mlbackCategory = new MlbackCategory();
		mlbackCategory.setCategoryId(categoryId);
		mlbackCategory.setCategoryImgpcurl(sqlimageUrl);	
		mlbackCategoryService.updateByPrimaryKeySelective(mlbackCategory);
		
		return Msg.success().add("resMsg", "登陆成功").add("imageUrl", imageUrl).add("thumImageUrl", thumImageUrl)
				.add("sqlimageUrl", sqlimageUrl).add("sqlthumImageUrl", sqlthumImageUrl);
	}
	/**
	 * 	onuse	20200103	检查
	 * */
	@RequestMapping(value="/thumImageProduct",method=RequestMethod.POST)
	@ResponseBody
	public Msg thumImageProduct(@RequestParam("image")CommonsMultipartFile file,@RequestParam("productSeo")String productSeo,
			@RequestParam("productId")Integer productId,@RequestParam("type")String type,
			HttpSession session,HttpServletResponse rep,HttpServletRequest res){
		
		//判断参数,确定信息
		String typeName=ImageNameUtil.gettypeName(type);
		
		String productIdStr = productId+"";
		String imgName = ImageNameUtil.getfilename(typeName,productIdStr);
		
		String uploadPath = "static/upload/img/product";
		String realUploadPath = session.getServletContext().getRealPath(uploadPath);
		
		//当前服务器路径
		String basePathStr = URLLocationUtils.getbasePathStr(rep,res);
        System.out.println("basePathStr:"+basePathStr);
		
		String imageUrl ="";
		String thumImageUrl ="";
		String sqlimageUrl="";
		String sqlthumImageUrl="";
		try {
			
			imageUrl = uploadService.uploadImage(file, uploadPath, realUploadPath,imgName);//图片原图路径
			sqlimageUrl=basePathStr+imageUrl;
			System.out.println("sqlimageUrl:"+sqlimageUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		String uploadPathcompress = "static/upload/imagecompress/product";
//		String realUploadPathcompress = session.getServletContext().getRealPath(uploadPathcompress);
//		
//		try {
//			
//			thumImageUrl = thumbnailService.Thumbnail(file, uploadPathcompress, realUploadPathcompress,imgName);
//			sqlthumImageUrl=basePathStr+thumImageUrl;
//			System.out.println("sqlthumImageUrl:"+sqlthumImageUrl);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		MlbackProduct mlbackProduct = new MlbackProduct();
		mlbackProduct.setProductId(productId);
		mlbackProduct.setProductMainimgurl(sqlimageUrl);
		mlbackProduct.setProductMainsmallimgurl(sqlthumImageUrl);
		
		mlbackProductService.updateByPrimaryKeySelective(mlbackProduct);
		
		return Msg.success().add("resMsg", "登陆成功").add("imageUrl", imageUrl).add("thumImageUrl", thumImageUrl)
				.add("sqlimageUrl", sqlimageUrl).add("sqlthumImageUrl", sqlthumImageUrl);
	}
	
	/**
	 * 	onuse	20200103	检查
	 * */
	@RequestMapping(value="/thumImageProductAll",method=RequestMethod.POST)
	@ResponseBody
	public Msg thumImageProductAll(@RequestParam("imageAll")CommonsMultipartFile file,@RequestParam("productId")Integer productId,
			@RequestParam("productimgSortOrder")Integer productimgSortOrder,
			HttpSession session,HttpServletResponse rep,HttpServletRequest res){
		
		//判断参数,确定信息
		String productIdStr = productId+"";
		String productimgSortIdstr = productimgSortOrder+"";
		String imgName = ImageNameUtil.getImageAllfilename(productIdStr,productimgSortIdstr);
		
		String uploadPath = "static/upload/img/productAll";
		String realUploadPath = session.getServletContext().getRealPath(uploadPath);
		
		//当前服务器路径
		String basePathStr = URLLocationUtils.getbasePathStr(rep,res);
        System.out.println("basePathStr:"+basePathStr);
		
		String imageUrl ="";
		String thumImageUrl ="";
		String sqlimageUrl="";
		String sqlthumImageUrl="";
		try {
			
			imageUrl = uploadService.uploadImage(file, uploadPath, realUploadPath,imgName);//图片原图路径
			sqlimageUrl=basePathStr+imageUrl;
			System.out.println("sqlimageUrl:"+sqlimageUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		String uploadPathcompress = "static/upload/imagecompress/productAll";
//		String realUploadPathcompress = session.getServletContext().getRealPath(uploadPathcompress);
//		
//		try {
//			
//			thumImageUrl = thumbnailService.Thumbnail(file, uploadPathcompress, realUploadPathcompress,imgName);
//			sqlthumImageUrl=basePathStr+thumImageUrl;
//			System.out.println("sqlthumImageUrl:"+sqlthumImageUrl);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		//查询文件是否在
		MlbackProductImg mlbackProductImgIf = new MlbackProductImg();
		
		mlbackProductImgIf.setProductId(productId);
		mlbackProductImgIf.setProductimgSortOrder(productimgSortOrder);
		
		List<MlbackProductImg> mlbackProductImgIfList = mlbackProductImgService.selectMlbackProductImgByPIdAndImgSort(mlbackProductImgIf);
		MlbackProductImg mlbackProductImg = new MlbackProductImg();
		
		mlbackProductImg.setProductId(productId);
		
		mlbackProductImg.setProductimgUrl(sqlimageUrl);
		
		mlbackProductImg.setProductimgSmallturl(sqlthumImageUrl);//详情页轮播筛选所用
		
		mlbackProductImg.setProductimgSortOrder(productimgSortOrder);
		
		mlbackProductImg.setProductimgName(imgName);
		
		String nowTime = DateUtil.strTime14s();
		mlbackProductImg.setProductimgMotifytime(nowTime);
		
		if(mlbackProductImgIfList.size()>0){
			//取出id
			int pImgId = mlbackProductImgIfList.get(0).getProductimgId();
			mlbackProductImg.setProductimgId(pImgId);
			mlbackProductImgService.updateByPrimaryKeySelective(mlbackProductImg);
			
			//存在update
		}else{
			mlbackProductImg.setProductimgCreatetime(nowTime);
			//存在insert
			mlbackProductImgService.insertSelective(mlbackProductImg);
		}
		
		return Msg.success().add("resMsg", "登陆成功").add("imageUrl", imageUrl).add("thumImageUrl", thumImageUrl)
				.add("sqlimageUrl", sqlimageUrl).add("sqlthumImageUrl", sqlthumImageUrl).add("mlbackProductImg", mlbackProductImg);
	}
	/**
	 * 	zsh	20200103	检查
	 * */
	@RequestMapping(value="/productDiscount",method=RequestMethod.POST)
	@ResponseBody
	public Msg productDiscount(@RequestParam("image")CommonsMultipartFile file,
			@RequestParam("productId")Integer productId,@RequestParam("type")String type,
			HttpSession session,HttpServletResponse rep,HttpServletRequest res){
		
		//判断参数,确定信息
		String typeName=ImageNameUtil.gettypeName(type);//proidDiscout
		
		String productIdStr = productId+"";
		String imgName = ImageNameUtil.getfilename(typeName,productIdStr);
		
		//当前服务器路径
		String basePathStr = URLLocationUtils.getbasePathStr(rep,res);
        System.out.println("basePathStr:"+basePathStr);
		
		String uploadPath = "static/upload/img/productDiscount";
		String realUploadPath = session.getServletContext().getRealPath(uploadPath);
				
		String imageUrl ="";
		String thumImageUrl ="";
		String sqlimageUrl="";
		String sqlthumImageUrl="";
		try {
			
			imageUrl = uploadService.uploadImage(file, uploadPath, realUploadPath,imgName);//图片原图路径
			sqlimageUrl=basePathStr+imageUrl;
			System.out.println("sqlimageUrl:"+sqlimageUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		String uploadPathcompress = "static/upload/imagecompress/productDiscount";
//		String realUploadPathcompress = session.getServletContext().getRealPath(uploadPathcompress);
//		
//		try {
//			
//			thumImageUrl = thumbnailService.Thumbnail(file, uploadPathcompress, realUploadPathcompress,imgName);
//			sqlthumImageUrl=basePathStr+thumImageUrl;
//			System.out.println("sqlthumImageUrl:"+sqlthumImageUrl);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		MlbackProduct mlbackProduct = new MlbackProduct();
		mlbackProduct.setProductId(productId);
		mlbackProduct.setProductDiscoutimgurl(sqlimageUrl);		
		mlbackProductService.updateByPrimaryKeySelective(mlbackProduct);
		
		return Msg.success().add("resMsg", "登陆成功").add("imageUrl", imageUrl).add("thumImageUrl", thumImageUrl)
				.add("sqlimageUrl", sqlimageUrl).add("sqlthumImageUrl", sqlthumImageUrl);
	}
	
	/**
	 * 	onuse	20200103	检查
	 * */
	@RequestMapping(value="/thumProVideoImage",method=RequestMethod.POST)
	@ResponseBody
	public Msg thumProVideoImage(@RequestParam("image")CommonsMultipartFile file,
			@RequestParam("productId")Integer productId,@RequestParam("type")String type,
			HttpSession session,HttpServletResponse rep,HttpServletRequest res){
		
		//判断参数,确定信息
		String typeName=ImageNameUtil.gettypeName(type);
		
		String productIdStr = productId+"";
		String imgName = ImageNameUtil.getfilename(typeName,productIdStr);
		
		//当前服务器路径
		String basePathStr = URLLocationUtils.getbasePathStr(rep,res);
        System.out.println("basePathStr:"+basePathStr);
		
		String uploadPath = "static/upload/video/productVideoImage";
		String realUploadPath = session.getServletContext().getRealPath(uploadPath);
				
		String imageUrl ="";
		String thumImageUrl ="";
		String sqlimageUrl="";
		String sqlthumImageUrl="";
		try {
			
			imageUrl = uploadService.uploadImage(file, uploadPath, realUploadPath,imgName);//图片原图路径
			sqlimageUrl=basePathStr+imageUrl;
			System.out.println("sqlimageUrl:"+sqlimageUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		
//		String uploadPathcompress = "static/video/productVideoImage";
//		String realUploadPathcompress = session.getServletContext().getRealPath(uploadPathcompress);
//		
//		try {
//			
//			thumImageUrl = thumbnailService.Thumbnail(file, uploadPathcompress, realUploadPathcompress,imgName);
//			sqlthumImageUrl=basePathStr+thumImageUrl;
//			System.out.println("sqlthumImageUrl:"+sqlthumImageUrl);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		MlbackProduct mlbackProduct = new MlbackProduct();
		mlbackProduct.setProductId(productId);
		mlbackProduct.setProductVideoImgUrl(sqlimageUrl);
		
		mlbackProductService.updateByPrimaryKeySelective(mlbackProduct);
		
		return Msg.success().add("resMsg", "登陆成功").add("imageUrl", imageUrl).add("thumImageUrl", thumImageUrl)
				.add("sqlimageUrl", sqlimageUrl).add("sqlthumImageUrl", sqlthumImageUrl);
	}
	
	/**
	 * 	onuse	20200103	检查
	 * */
	@RequestMapping(value="/thumImageReviewAll",method=RequestMethod.POST)
	@ResponseBody
	public Msg thumImageReview(@RequestParam("image")CommonsMultipartFile file,@RequestParam("productSeo")String productSeo,
			@RequestParam("reviewId")Integer reviewId,@RequestParam("reviewimgSortOrder")Integer reviewimgSortOrder,@RequestParam("type")String type,
			HttpSession session,HttpServletResponse rep,HttpServletRequest res){
		
		//判断参数,确定信息
		String typeName=ImageNameUtil.gettypeName(type);
		
		String reviewIdStr = reviewId+"";
		
		String reviewimgSortOrderStr = reviewimgSortOrder+"";
		
		String imgName = ImageNameUtil.getReviewFilename(typeName,productSeo,reviewIdStr,reviewimgSortOrderStr);
		
		String uploadPath = "static/upload/img/reviewAllImg";
		String realUploadPath = session.getServletContext().getRealPath(uploadPath);
		
		//当前服务器路径
		String basePathStr = URLLocationUtils.getbasePathStr(rep,res);
        System.out.println("basePathStr:"+basePathStr);
		
		String imageUrl ="";
		String thumImageUrl ="";
		String sqlimageUrl="";
		String sqlthumImageUrl="";
		try {
			
			imageUrl = uploadService.uploadImage(file, uploadPath, realUploadPath,imgName);//图片原图路径
			sqlimageUrl=basePathStr+imageUrl;
			System.out.println("sqlimageUrl:"+sqlimageUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		String uploadPathcompress = "static/upload/imagecompress/reviewAllImg";
//		String realUploadPathcompress = session.getServletContext().getRealPath(uploadPathcompress);
//		
//		try {
//			
//			thumImageUrl = thumbnailService.Thumbnail(file, uploadPathcompress, realUploadPathcompress,imgName);
//			sqlthumImageUrl=basePathStr+thumImageUrl;
//			System.out.println("sqlthumImageUrl:"+sqlthumImageUrl);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		//查询文件是否在
		MlbackReviewImg mlbackReviewImgIf = new MlbackReviewImg();
		
		mlbackReviewImgIf.setReviewId(reviewId);
		mlbackReviewImgIf.setReviewimgSortOrder(reviewimgSortOrder);
		List<MlbackReviewImg> mlbackReviewImgIfList = mlbackReviewImgService.selectMlbackReviewImgByRIdAndImgSort(mlbackReviewImgIf);
		
		MlbackReviewImg mlbackReviewImg = new MlbackReviewImg();
		mlbackReviewImg.setReviewId(reviewId);
		mlbackReviewImg.setReviewimgName(imgName);
		mlbackReviewImg.setReviewimgSortOrder(reviewimgSortOrder);
		mlbackReviewImg.setReviewimgUrl(sqlimageUrl);
		mlbackReviewImg.setReviewsmallimgUrl(sqlimageUrl);
		String nowTime = DateUtil.strTime14s();
		if(mlbackReviewImgIfList.size()>0){
			//取出id
			int rImgId = mlbackReviewImgIfList.get(0).getReviewimgId();
			mlbackReviewImg.setReviewimgId(rImgId);
			//存在update
			mlbackReviewImgService.updateByPrimaryKeySelective(mlbackReviewImg);
		}else{
			mlbackReviewImg.setReviewimgCreatetime(nowTime);
			//存在insert
			mlbackReviewImgService.insertSelective(mlbackReviewImg);
		}
		
		mlbackReviewImgService.updateByPrimaryKeySelective(mlbackReviewImg);
		
		//设置该review 有图片
		MlfrontReview mlfrontReview = new MlfrontReview();
		mlfrontReview.setReviewId(reviewId);
		List<MlfrontReview> mlfrontReviewList = mlfrontReviewService.selectMlfrontReviewById(mlfrontReview);
		if(mlfrontReviewList.size()>0){
			if(mlfrontReviewList.get(0).getReviewIsPics() != null &&  1 == mlfrontReviewList.get(0).getReviewIsPics()){
			}else{
				mlfrontReview.setReviewIsPics(1);
				mlfrontReviewService.updateByPrimaryKeySelective(mlfrontReview);
			}
		}
		 
		return Msg.success().add("resMsg", "登陆成功").add("imageUrl", imageUrl).add("thumImageUrl", thumImageUrl)
				.add("sqlimageUrl", sqlimageUrl).add("sqlthumImageUrl", sqlthumImageUrl);
	}
	
	
	/**
	 * 	onuse	20200103	检查
	 * */
	@RequestMapping(value="/slideWap",method=RequestMethod.POST)
	@ResponseBody
	public Msg slideWap(@RequestParam("image")CommonsMultipartFile file,
			@RequestParam("slideId")Integer slideId,@RequestParam("type")String type,
			HttpSession session,HttpServletResponse rep,HttpServletRequest res){
		
		//判断参数,确定信息
		String typeName=ImageNameUtil.gettypeName(type);//proidDiscout
		
		String slideIdStr = slideId+"";
		String imgName = ImageNameUtil.getfilename(typeName,slideIdStr);
		
		//当前服务器路径
		String basePathStr = URLLocationUtils.getbasePathStr(rep,res);
        System.out.println("basePathStr:"+basePathStr);
		
		String uploadPath = "static/upload/img/Slide";
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

		MlbackSlide mlbackSlide = new MlbackSlide();
		mlbackSlide.setSlideId(slideId);
		mlbackSlide.setSlideWapimgurl(sqlimageUrl);		
		mlbackSlideService.updateByPrimaryKeySelective(mlbackSlide);
		
		return Msg.success().add("resMsg", "登陆成功").add("imageUrl", imageUrl).add("sqlimageUrl", sqlimageUrl);
	}
	
	/**
	 * 	onuse	20200103	检查
	 * */
	@RequestMapping(value="/slidePc",method=RequestMethod.POST)
	@ResponseBody
	public Msg slidePc(@RequestParam("image")CommonsMultipartFile file,
			@RequestParam("slideId")Integer slideId,@RequestParam("type")String type,
			HttpSession session,HttpServletResponse rep,HttpServletRequest res){
		
		//判断参数,确定信息
		String typeName=ImageNameUtil.gettypeName(type);//proidDiscout
		
		String slideIdStr = slideId+"";
		String imgName = ImageNameUtil.getfilename(typeName,slideIdStr);
		
		//当前服务器路径
		String basePathStr = URLLocationUtils.getbasePathStr(rep,res);
        System.out.println("basePathStr:"+basePathStr);
		
		String uploadPath = "static/upload/img/Slide";
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

		MlbackSlide mlbackSlide = new MlbackSlide();
		mlbackSlide.setSlideId(slideId);
		mlbackSlide.setSlidePcimgurl(sqlimageUrl);		
		mlbackSlideService.updateByPrimaryKeySelective(mlbackSlide);
		
		return Msg.success().add("resMsg", "登陆成功").add("imageUrl", imageUrl).add("sqlimageUrl", sqlimageUrl);
	}
	
	/**
	 * 	onuse	20200103	检查
	 * */
	@RequestMapping(value="/showAreaWap",method=RequestMethod.POST)
	@ResponseBody
	public Msg showAreaWap(@RequestParam("image")CommonsMultipartFile file,
			@RequestParam("showareaId")Integer showareaId,@RequestParam("type")String type,
			HttpSession session,HttpServletResponse rep,HttpServletRequest res){
		
		//判断参数,确定信息
		String typeName=ImageNameUtil.gettypeName(type);//proidDiscout
		
		String showareaIdStr = showareaId+"";
		String imgName = ImageNameUtil.getfilename(typeName,showareaIdStr);
		
		//当前服务器路径
		String basePathStr = URLLocationUtils.getbasePathStr(rep,res);
        System.out.println("basePathStr:"+basePathStr);
		
		String uploadPath = "static/upload/img/showArea";
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

		MlbackShowArea mlbackShowArea = new MlbackShowArea();
		mlbackShowArea.setShowareaId(showareaId);
		mlbackShowArea.setShowareaImgurl(sqlimageUrl);
		mlbackShowAreaService.updateByPrimaryKeySelective(mlbackShowArea);
		
		return Msg.success().add("resMsg", "登陆成功").add("imageUrl", imageUrl).add("sqlimageUrl", sqlimageUrl);
	}
	
	/**
	 * 	onuse	20200103	检查
	 * */
	@RequestMapping(value="/showAreaPc",method=RequestMethod.POST)
	@ResponseBody
	public Msg showAreaPc(@RequestParam("image")CommonsMultipartFile file,
			@RequestParam("showareaId")Integer showareaId,@RequestParam("type")String type,
			HttpSession session,HttpServletResponse rep,HttpServletRequest res){
		
		//判断参数,确定信息
		String typeName=ImageNameUtil.gettypeName(type);//proidDiscout
		
		String showareaIdStr = showareaId+"";
		String imgName = ImageNameUtil.getfilename(typeName,showareaIdStr);
		
		//当前服务器路径
		String basePathStr = URLLocationUtils.getbasePathStr(rep,res);
        System.out.println("basePathStr:"+basePathStr);
		
		String uploadPath = "static/upload/img/showArea";
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

		MlbackShowArea mlbackShowArea = new MlbackShowArea();
		mlbackShowArea.setShowareaId(showareaId);
		mlbackShowArea.setShowareaImgpcurl(sqlimageUrl);
		mlbackShowAreaService.updateByPrimaryKeySelective(mlbackShowArea);
		
		return Msg.success().add("resMsg", "登陆成功").add("imageUrl", imageUrl).add("sqlimageUrl", sqlimageUrl);
	}
	
	/**
	 * 	zsh 200727
	 * */
	@RequestMapping(value="/actShowProWap",method=RequestMethod.POST)
	@ResponseBody
	public Msg actShowProWap(@RequestParam("image")CommonsMultipartFile file,
			@RequestParam("actshowproId")Integer actshowproId,@RequestParam("type")String type,
			HttpSession session,HttpServletResponse rep,HttpServletRequest res){
		
		//判断参数,确定信息
		String typeName=ImageNameUtil.gettypeName(type);//proidDiscout
		
		String actshowproIdStr = actshowproId+"";
		String imgName = ImageNameUtil.getfilename(typeName,actshowproIdStr);
		
		//当前服务器路径
		String basePathStr = URLLocationUtils.getbasePathStr(rep,res);
        System.out.println("basePathStr:"+basePathStr);
		
		String uploadPath = "static/upload/img/actShowPro";
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

		MlbackActShowPro mlbackActShowPro = new MlbackActShowPro();
		mlbackActShowPro.setActshowproId(actshowproId);
		mlbackActShowPro.setActshowproImgwapurl(sqlimageUrl);
		mlbackActShowProService.updateByPrimaryKeySelective(mlbackActShowPro);
		
		return Msg.success().add("resMsg", "登陆成功").add("imageUrl", imageUrl).add("sqlimageUrl", sqlimageUrl);
	}
	
	/**
	 * 	zsh 200727
	 * */
	@RequestMapping(value="/actShowProPc",method=RequestMethod.POST)
	@ResponseBody
	public Msg actShowProPc(@RequestParam("image")CommonsMultipartFile file,
			@RequestParam("actshowproId")Integer actshowproId,@RequestParam("type")String type,
			HttpSession session,HttpServletResponse rep,HttpServletRequest res){
		
		//判断参数,确定信息
		String typeName=ImageNameUtil.gettypeName(type);//actShowPro
		
		String actshowproIdStr = actshowproId+"";
		String imgName = ImageNameUtil.getfilename(typeName,actshowproIdStr);
		
		//当前服务器路径
		String basePathStr = URLLocationUtils.getbasePathStr(rep,res);
        System.out.println("basePathStr:"+basePathStr);
		
		String uploadPath = "static/upload/img/actShowPro";
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

		MlbackActShowPro mlbackActShowPro = new MlbackActShowPro();
		mlbackActShowPro.setActshowproId(actshowproId);
		mlbackActShowPro.setActshowproImgpcurl(sqlimageUrl);
		mlbackActShowProService.updateByPrimaryKeySelective(mlbackActShowPro);
		
		return Msg.success().add("resMsg", "登陆成功").add("imageUrl", imageUrl).add("sqlimageUrl", sqlimageUrl);
	}
	
	/**
	 * 	zsh 200727
	 * */
	@RequestMapping(value="/couponDescTitle",method=RequestMethod.POST)
	@ResponseBody
	public Msg couponDescTitle(@RequestParam("image")CommonsMultipartFile file,
			@RequestParam("coupondesctieleId")Integer coupondesctieleId,@RequestParam("type")String type,
			HttpSession session,HttpServletResponse rep,HttpServletRequest res){
		
		//判断参数,确定信息
		String typeName=ImageNameUtil.gettypeName(type);//couponDescTitle
		
		String coupondesctieleIdIdStr = coupondesctieleId+"";
		String imgName = ImageNameUtil.getfilename(typeName,coupondesctieleIdIdStr);
		
		//当前服务器路径
		String basePathStr = URLLocationUtils.getbasePathStr(rep,res);
        System.out.println("basePathStr:"+basePathStr);
		
		String uploadPath = "static/upload/img/CouponDescTitle";
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

		MlbackCouponDescTitle mlbackCouponDescTitle = new MlbackCouponDescTitle();
		mlbackCouponDescTitle.setCoupondesctieleId(coupondesctieleId);
		mlbackCouponDescTitle.setCoupondesctieleImgurl(sqlimageUrl);
		mlbackCouponDescTitleService.updateByPrimaryKeySelective(mlbackCouponDescTitle);
		
		return Msg.success().add("resMsg", "登陆成功").add("imageUrl", imageUrl).add("sqlimageUrl", sqlimageUrl);
	}
	
	/**
	 * 	zsh 200727
	 * */
	@RequestMapping(value="/uploadCouponImg",method=RequestMethod.POST)
	@ResponseBody
	public Msg uploadCouponImg(@RequestParam("image")CommonsMultipartFile file,
			@RequestParam("couponId")Integer couponId,@RequestParam("type")String type,
			HttpSession session,HttpServletResponse rep,HttpServletRequest res){
		
		//判断参数,确定信息
		String typeName=ImageNameUtil.gettypeName(type);//couponDescTitle
		
		String couponIdStr = couponId+"";
		String imgName = ImageNameUtil.getfilename(typeName,couponIdStr);
		
		//当前服务器路径
		String basePathStr = URLLocationUtils.getbasePathStr(rep,res);
        System.out.println("basePathStr:"+basePathStr);
		
		String uploadPath = "static/upload/img/Coupon";
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

		MlbackCoupon mlbackCoupon = new MlbackCoupon();
		mlbackCoupon.setCouponId(couponId);
		mlbackCoupon.setCouponImgUrl(sqlimageUrl);
		mlbackCouponService.updateByPrimaryKeySelective(mlbackCoupon);
		
		return Msg.success().add("resMsg", "登陆成功").add("imageUrl", imageUrl).add("sqlimageUrl", sqlimageUrl);
	}
	
	/**
	 * 	zsh 200727
	 * */
	@RequestMapping(value="/uploadProSkuImg",method=RequestMethod.POST)
	@ResponseBody
	public Msg uploadProSkuImg(@RequestParam("image")CommonsMultipartFile file,
			@RequestParam("productskuId")Integer productskuId,
			@RequestParam("productskuPid")Integer productskuPid,@RequestParam("type")String type,
			HttpSession session,HttpServletResponse rep,HttpServletRequest res){
		
		//判断参数,确定信息
		String typeName=ImageNameUtil.gettypeName(type);//proSku
		
		String productskuPidStr = productskuPid+"";
		
		String proIdStr = productskuId+"";
		
		String imgName = ImageNameUtil.getfilenamePsku(typeName,productskuPidStr,proIdStr);
		
		//当前服务器路径
		String basePathStr = URLLocationUtils.getbasePathStr(rep,res);
        System.out.println("basePathStr:"+basePathStr);
		
		String uploadPath = "static/upload/img/Prosku";
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

		MlbackProductSku mlbackProductSku = new MlbackProductSku();
		mlbackProductSku.setProductskuId(productskuId);
		mlbackProductSku.setProductskuOneImg(sqlimageUrl);
		mlbackProductSkuService.updateByPrimaryKeySelective(mlbackProductSku);
		
		return Msg.success().add("resMsg", "上传成功").add("imageUrl", imageUrl).add("sqlimageUrl", sqlimageUrl);
	}
	
	/**
	 * 	zsh 200727
	 * */
	@RequestMapping(value="/uploadBlogImg",method=RequestMethod.POST)
	@ResponseBody
	public Msg uploadBlogImg(@RequestParam("image")CommonsMultipartFile file,
			@RequestParam("blogId")Integer blogId,
			@RequestParam("blogSeoname")String blogSeoname,@RequestParam("type")String type,
			HttpSession session,HttpServletResponse rep,HttpServletRequest res){
		
		//判断参数,确定信息
		String typeName=ImageNameUtil.gettypeName(type);//proSku
		
		String blogSeonameStr = blogSeoname+"";
		
		String imgName = ImageNameUtil.getfilenameBlog(typeName,blogSeonameStr);
		
		//当前服务器路径
		String basePathStr = URLLocationUtils.getbasePathStr(rep,res);
        System.out.println("basePathStr:"+basePathStr);
		
		String uploadPath = "static/upload/img/Blog";
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

		MlbackBlog mlbackBlogReq = new MlbackBlog();
		mlbackBlogReq.setBlogId(blogId);
		mlbackBlogReq.setBlogBannerimg(sqlimageUrl);//blogBannerimg
		mlbackBlogService.updateByPrimaryKeySelective(mlbackBlogReq);
		
		return Msg.success().add("resMsg", "上传成功").add("imageUrl", imageUrl).add("sqlimageUrl", sqlimageUrl);
	}


}
