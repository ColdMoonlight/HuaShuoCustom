package com.atguigu.controller.back;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.MlbackCategory;
import com.atguigu.bean.MlbackProduct;
import com.atguigu.bean.MlbackProductImg;
import com.atguigu.bean.MlbackSearch;
import com.atguigu.common.Const;
import com.atguigu.common.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackCategoryService;
import com.atguigu.service.MlbackProductImgService;
import com.atguigu.service.MlbackProductService;
import com.atguigu.service.MlbackSearchService;
import com.atguigu.service.MlbackSuperCateService;
import com.atguigu.utils.DateUtil;

@Controller
@RequestMapping("/MlbackProduct")
public class MlbackProductController {
	
	@Autowired
	MlbackSuperCateService mlbackSuperCateService;
	
	@Autowired
	MlbackProductService mlbackProductService;
	
	@Autowired
	MlbackCategoryService mlbackCategoryService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	@Autowired
	MlbackProductImgService mlbackProductImgService;
	
	@Autowired
	MlbackSearchService mlbackSearchService;
	
	/**
	 * 1.0	20200608
	 * to后台分类MlbackProduct列表页面
	 * @return jsp
	 * */
	@RequestMapping("/toMlbackProductPage")
	public String tologin(HttpSession session) throws Exception{
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
		if(mlbackAdmin==null){
			//SysUsers对象为空
			return "back/mlbackAdminLogin";
		}else{
			return "back/product/mlbackProductPage";
		}
	}
	
	/**2.0	20200608
	 * 后台MlbackProduct列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlbackProductByPage")
	@ResponseBody
	public Msg getMlbackProductByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {

		int PagNum = 30;
		PageHelper.startPage(pn, PagNum);
		List<MlbackProduct> MlbackProductList = mlbackProductService.selectMlbackProductGetAll();
		PageInfo page = new PageInfo(MlbackProductList, PagNum);
		return Msg.success().add("pageInfo", page);
	}
	
	/**3.0	20200608
	 * MlbackProduct	initializaCategory
	 * @param MlbackProduct
	 * @return
	 */
	@RequestMapping(value="/initializaProduct",method=RequestMethod.POST)
	@ResponseBody
	public Msg initializaCategory(HttpServletResponse rep,HttpServletRequest res){
		
		MlbackProduct mlbackProduct = new MlbackProduct();
		//取出id
		String nowTime = DateUtil.strTime14s();
		mlbackProduct.setProductCreatetime(nowTime);
		mlbackProduct.setProductMotifytime(nowTime);
		mlbackProduct.setProductReviewnum(99);
		//无id,insert
		//System.out.println("插入前"+mlbackProduct.toString());
		mlbackProductService.insertSelective(mlbackProduct);
		//System.out.println("插入后"+mlbackProduct.toString());
		return Msg.success().add("resMsg", "Product初始化成功").add("mlbackProduct", mlbackProduct);
	}
	
	/**4.0	20200608
	 * MlbackProduct	update
	 * @param MlbackProduct
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackProduct mlbackProduct){
		//接受参数信息
		Integer productId =  mlbackProduct.getProductId();
		String productName = mlbackProduct.getProductName();
		String nowTime = DateUtil.strTime14s();
		mlbackProduct.setProductMotifytime(nowTime);
		
		//先对这个产品选择的一些类,进行productIdStr的清理
		//有id,update
		String categoryIdsStr = mlbackProduct.getProductCategoryIdsstr();
		Integer productSupercateid = mlbackProduct.getProductSupercateid();
		//4.0.1从中读取categoryIdsStr,切割得到每一个categoryId,遍历,把productId,填充再每个查回来的categort中的proidStr拼上
		UpdateCategoryProductIdStr(categoryIdsStr,productId,productName,productSupercateid);
		mlbackProductService.updateByPrimaryKeySelective(mlbackProduct);
		return Msg.success().add("resMsg", "category保存成功");
	}
	
	/**
	 * 4.0.1
	 * 从中读取categoryIdsStr,切割得到每一个categoryId,
	 * 遍历categoryId查询,把productId,填充再每个查回来的category中的proidStr拼上
	 * */
	private void UpdateCategoryProductIdStr(String categoryIdsStr, Integer productId,String productName, Integer productSupercateid) {
		
		String inproductIdStr=productId+"";
		
		if(categoryIdsStr==""){
			System.out.println("categoryIdsStr为空");
			//把该productId下的
			ProductCategoryIdsStrUpdateOld(productId,categoryIdsStr);
			//清理掉,该产品下的类
			MlbackProduct mlbackProductReq = new MlbackProduct();
			mlbackProductReq.setProductId(productId);
			mlbackProductReq.setProductCategoryIdsstr("");
			mlbackProductReq.setProductCategoryNamesstr("");
			mlbackProductService.updateByPrimaryKeySelective(mlbackProductReq);
		}else{
			//先清理老的数据
			ProductCategoryIdsStrUpdateOld(productId,categoryIdsStr);
			//从中读取categoryIdsStr,切割得到每一个categoryId,
			String categoryIdsStrArr [] = categoryIdsStr.split(",");
			String categoryIdStr = "";
			Integer categoryIdInt = 0;
			MlbackCategory mlbackCategoryReq = new MlbackCategory();
			MlbackCategory mlbackCategoryRes = new MlbackCategory();
			
			MlbackCategory mlbackCategoryReqUpdate = new MlbackCategory();
			List<MlbackCategory> mlbackCategoryList = new ArrayList<MlbackCategory>();
			String categoryProductIdsStr = "";
			String categoryproductNamesStr = "";
			
			for(int i=0;i<categoryIdsStrArr.length;i++){
				categoryIdStr=categoryIdsStrArr[i];
				categoryIdInt = Integer.parseInt(categoryIdStr);
				//遍历categoryId查询
				mlbackCategoryReq.setCategoryId(categoryIdInt);
				mlbackCategoryList = mlbackCategoryService.selectMlbackCategory(mlbackCategoryReq);
				mlbackCategoryRes = mlbackCategoryList.get(0);
 				categoryProductIdsStr = mlbackCategoryRes.getCategoryProductIds();
				categoryproductNamesStr = mlbackCategoryRes.getCategoryProductNames();
				//把productId,填充再每个查回来的categort中的proidStr拼上
				if(categoryProductIdsStr==null||categoryProductIdsStr.length()==0){
					//如果当前没有,属于第一次填充,直接替代就好
					categoryProductIdsStr =inproductIdStr;
					categoryproductNamesStr=productName;
				}else{
					//如果产品类下已经有数据
					int ifHave = cheakifHave(categoryProductIdsStr,inproductIdStr);
					//先判断是否包含本次
					if(ifHave>0){
						//只要test.indexOf('This')返回的值不是-1说明test字符串中包含字符串'This',相反如果包含返回的值必定是-1"
						//如果包含,跳过
						continue;
					}else{
						//如果不包含,拼接
						categoryProductIdsStr = categoryProductIdsStr +","+ inproductIdStr;
						categoryproductNamesStr = categoryproductNamesStr+","+productName;
					}
				}
				//操作完,执行更新
				mlbackCategoryReqUpdate.setCategoryId(categoryIdInt);
				mlbackCategoryReqUpdate.setCategoryProductIds(categoryProductIdsStr);
				mlbackCategoryReqUpdate.setCategoryProductNames(categoryproductNamesStr);
				mlbackCategoryService.updateByPrimaryKeySelective(mlbackCategoryReqUpdate);
			}
		}
	}
	//* 4.0.2
	private int cheakifHave(String categoryProductIdsStr, String inproductIdStr) {
		
		int num = 0;
		String arrStr [] = categoryProductIdsStr.split(",");
		String nowPidStr = "";
		for(int i=0;i<arrStr.length;i++){
			nowPidStr =arrStr[i];
			if(nowPidStr.equals(inproductIdStr)){
				//有相同的,所以num增加
				num++;
				break;//找到,跳出
			}
		}
		return num;
	}
	
	
	//* 4.0.3
	private int cheakPNewCusStrifHavePid(String categoryNewIdsStr, String categoryOldIdStr) {
		
		int num = 0;
		String arrStr [] = categoryNewIdsStr.split(",");
		String nowCidStr = "";
		for(int i=0;i<arrStr.length;i++){
			nowCidStr =arrStr[i];
			if(nowCidStr.equals(categoryOldIdStr)){
				//有相同的,所以num增加
				num++;
				break;//找到,跳出
			}
		}
		return num;
	}

	//* 4.0.3清理每条的新产品信息
	private void ProductCategoryIdsStrUpdateOld(Integer productId,String categoryIdsStrNew) {
		MlbackProduct mlbackProductReq = new MlbackProduct();
		MlbackProduct mlbackProductRes = new MlbackProduct();
		mlbackProductReq.setProductId(productId);
		List<MlbackProduct> mlbackProductListOld =  mlbackProductService.selectMlbackProductByParam(mlbackProductReq);
		mlbackProductRes = mlbackProductListOld.get(0);
		String productCategoryIdsStr = mlbackProductRes.getProductCategoryIdsstr();
		MlbackCategory mlbackCategoryReq = new MlbackCategory();
		MlbackCategory mlbackCategoryRes = new MlbackCategory();
		List<MlbackCategory> MlbackCategoryList = new ArrayList<MlbackCategory>();
		if(productCategoryIdsStr==null||productCategoryIdsStr.length()==0){
			//没有被类管理绑定过
			System.out.println("产品的这个字段下,没有被类管理绑定过,不需要进行移除操作");
		}else{
			//被类管理绑定过
			String productCategoryIdsStrArr [] =  productCategoryIdsStr.split(",");
			for(int i=0;i<productCategoryIdsStrArr.length;i++){
				//遍历categoryIdsStr
				String  categoryIdsStr = productCategoryIdsStrArr[i];
				Integer categoryIdsInt = Integer.parseInt(categoryIdsStr);
				mlbackCategoryReq.setCategoryId(categoryIdsInt);
				MlbackCategoryList = mlbackCategoryService.selectMlbackCategory(mlbackCategoryReq);
				if(!(MlbackCategoryList.size()>0)){
					continue;
				}
				mlbackCategoryRes = MlbackCategoryList.get(0);
				
				//从该中取的老的产品串串
				String categoryProductIds = "";
				String categoryProductNames = "";
				categoryProductIds = mlbackCategoryRes.getCategoryProductIds();
				categoryProductNames = mlbackCategoryRes.getCategoryProductNames();
				
				String categoryProductIdsArr [] = categoryProductIds.split(",");
				String categoryProductNamesArr [] = categoryProductNames.split(",");
				
				List<String> categoryProductIdsStrNewList = new ArrayList<String>();
				List<String> categoryProductNamesStrNewList = new ArrayList<String>();
				
				String categoryProductIdsStrNew="";
				String categoryProductNamesStrNew="";
				//对来的产品串进行遍历
				String productIdnow = productId+"";
				//该类的产品串进行
				for(int j =0;j<categoryProductIdsArr.length;j++){
					//
					String productOldIdOneStr = categoryProductIdsArr[j];
					String productOldNamesStr = categoryProductNamesArr[j];
					
					if(productOldIdOneStr.equals(productIdnow)){
						//当前新类id串里面有当前不变的Cid,则不清理,则便利类下产品的时候,不跳过该产品
						//如果产品类下已经有数据
						int ifHave = cheakPNewCusStrifHavePid(categoryIdsStrNew,categoryIdsStr);
						//先判断是否包含本次
						if(ifHave>0){
							//只要test.indexOf('This')返回的值不是-1说明test字符串中包含字符串'This',相反如果包含返回的值必定是-1"
							//如果包含,加上,顺序不变
							categoryProductIdsStrNewList.add(productOldIdOneStr);
							categoryProductIdsStrNew+=productOldIdOneStr+",";
							
							categoryProductNamesStrNewList.add(productOldNamesStr);
							categoryProductNamesStrNew+=productOldNamesStr+",";
						}else{
							//如果不包含,过滤掉
							System.out.println("一致就不存了");
							//在的话,继续加上,不在的话,一致就不存了
						}
					}else{
						//移除本个后的该类下全部产品
						categoryProductIdsStrNewList.add(productOldIdOneStr);
						categoryProductIdsStrNew+=productOldIdOneStr+",";
						
						categoryProductNamesStrNewList.add(productOldNamesStr);
						categoryProductNamesStrNew+=productOldNamesStr+",";
					}
				}
				//判断是不是空了,空了的话,不移除最后一个字符","
				if(categoryProductIdsStrNew.length()>0){
					categoryProductIdsStrNew=categoryProductIdsStrNew.substring(0,categoryProductIdsStrNew.length()-1);
					categoryProductNamesStrNew=categoryProductNamesStrNew.substring(0,categoryProductNamesStrNew.length()-1);
				}
				//封装参数
				MlbackCategory mlbackCategoryRemoveOld = new MlbackCategory();
				mlbackCategoryRemoveOld.setCategoryId(categoryIdsInt);
				mlbackCategoryRemoveOld.setCategoryProductIds(categoryProductIdsStrNew);
				mlbackCategoryRemoveOld.setCategoryProductNames(categoryProductNamesStrNew);
				//执行更新
				mlbackCategoryService.updateByPrimaryKeySelective(mlbackCategoryRemoveOld);
			}
		}
	}
	
	/**5.0	20200608
	 * MlbackProduct	delete
	 * @param MlbackProduct-categoryId
	 * @return 
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlbackProduct mlbackProduct){
		//接收id信息
		int productIdInt = mlbackProduct.getProductId();
		mlbackProductService.deleteByPrimaryKey(productIdInt);
		return Msg.success().add("resMsg", "category delete  success");
	}
	
	/**
	 * 6.0	20200608
	 * 查看单个产品的详情
	 * @param productId
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackProductDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackProductDetail(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackProduct mlbackProduct){
		//接受信息
		Integer productId = mlbackProduct.getProductId();
		Integer productSupercateid = mlbackProduct.getProductSupercateid();
		
		MlbackProduct mlbackProductReq = new MlbackProduct();
		mlbackProductReq.setProductId(productId);
		mlbackProductReq.setProductSupercateid(productSupercateid);
		List<MlbackProduct> mlbackProductResList =mlbackProductService.selectMlbackProductByParam(mlbackProductReq);
		MlbackProduct mlbackProductOne = new MlbackProduct();
		if(mlbackProductResList.size()>0){
			//如果用这个id查到,就拿出来.
			mlbackProductOne = mlbackProductResList.get(0);
		}else{
			//如果用这个id没查到,就取出当前所有产品最新上的那款.
			mlbackProductResList = mlbackProductService.selectMlbackProductGetAll();
			mlbackProductOne = mlbackProductResList.get(0);
		}
		//System.out.println("操作说明：查询-mlbackProductOne:"+mlbackProductOne);
		return Msg.success().add("resMsg", "查看单个产品详情完毕").add("mlbackProductOne", mlbackProductOne);
	}
	
	/**
	 * 7.0	20200608
	 * 后端获取backSearchByProduct产品list
	 * @return 
	 * */
	@RequestMapping(value="/backSearchByProduct",method=RequestMethod.POST)
	@ResponseBody
	public Msg backSearchByProduct(HttpServletResponse rep,HttpServletRequest res,HttpSession session,
			@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			@RequestParam(value = "productName") String productName,
			@RequestParam(value = "productSupercateid", defaultValue = "1") Integer productSupercateid) throws Exception{
		
		//接收传递进来的参数
		int PagNum = 30;
		PageHelper.startPage(pn, PagNum);
		
		MlbackProduct mlbackProductReq = new MlbackProduct();
		mlbackProductReq.setProductSupercateid(productSupercateid);
		mlbackProductReq.setProductName(productName);
		List<MlbackProduct> mlbackProductResList = mlbackProductService.selectMlbackProductBackSearch(mlbackProductReq);
		PageInfo page = new PageInfo(mlbackProductResList, PagNum);
		return Msg.success().add("pageInfo", page);
	}
	
	/**
	 * 8.0	onuse	200103
	 * 前台详情页面wap/pc的productDetails
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/tofbProductDetailPageByhtml",method=RequestMethod.GET)
	 public ModelAndView tomfbProductDetailPageByhtml(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestParam(value = "productSeo") String productSeo) throws Exception{
	
		//准备封装参数
		MlbackProduct mlbackProductrepBySeo = new MlbackProduct();
		mlbackProductrepBySeo.setProductSeo(productSeo);
		String nowTime = DateUtil.strTime14s();
		System.out.println("nowTime:"+nowTime+"客户点击ProSeo链接进入: "+productSeo);
	
		ModelAndView modelAndView = new ModelAndView();
		
		List<MlbackProduct> mlbackProductResList = mlbackProductService.selectMlbackProductByParam(mlbackProductrepBySeo);
	  
		if(!(mlbackProductResList.size()>0)){
			//return "redirect:/";
			modelAndView.setViewName("redirect:/");
			
			return modelAndView;
		}else{
			MlbackProduct mlbackProductRes = mlbackProductResList.get(0);
			
			Integer proStatus = mlbackProductRes.getProductStatus();
			if(proStatus==1){
				
				Integer productIdReq = mlbackProductRes.getProductId();
				
				//接受信息
				List<MlbackProductImg> mbackProductImgResList =mlbackProductImgService.selectMlbackProductImgByProductId(productIdReq);
				modelAndView.addObject("mbackProductImgResList", mbackProductImgResList);
				//放回响应域中
				res.setAttribute("productId", productIdReq);
				//放回session域中
				session.setAttribute("productDetailId", productIdReq);
				session.setAttribute("mlbackProductMetaTitle", mlbackProductRes.getProductMetaTitle());
				session.setAttribute("mlbackProductMetaKeywords", mlbackProductRes.getProductMetaKeywords());
				session.setAttribute("mlbackProductMeteDesc", mlbackProductRes.getProductMetaDesc());
				//返回视图
				//return "portal/product/productDetails";
				modelAndView.setViewName("portal/product/productDetails");
				return modelAndView;
			}else{
				System.out.println("通过链接点进来的productSeo:"+productSeo+",但是此产品已下架,跳回首页");
				//return "redirect:/";
				modelAndView.setViewName("redirect:/");
				return modelAndView;
			}
		}
	 }
	
	/**
	 * 8.0	onuse	200103
	 * 下拉配置lownLoadProduct
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/lownLoadProduct",method=RequestMethod.POST)
	@ResponseBody
	public Msg lownLoadProduct(HttpServletResponse rep,HttpServletRequest res,HttpSession session) throws Exception{
		
		MlbackProduct mlbackProductReq = new MlbackProduct();
//		List<MlbackProduct> mlbackProductResList = mlbackProductService.selectMlbackProductSimpleByParam(mlbackProductReq);
		List<MlbackProduct> mlbackProductResList = mlbackProductService.selectMlbackProductDownLoadByParam(mlbackProductReq);
		return Msg.success().add("mlbackProductResList", mlbackProductResList);
	}
	
	/**
	 * 9.	zsh200716
	 * 查询单个产品信息(无富文本描述)
	 * @param productId
	 * @return 
	 */
	@RequestMapping(value="/getOneProductSimple",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneProductSimple(@RequestBody MlbackProduct mlbackProduct){
		
		Integer productId = mlbackProduct.getProductId();
		//接受信息
		MlbackProduct mlbackProductReq = new MlbackProduct();
		mlbackProductReq.setProductId(productId);
		List<MlbackProduct> mlbackProductResList =mlbackProductService.selectMlbackProductSimple(mlbackProductReq);
		MlbackProduct mlbackProductOne = new MlbackProduct();
		if(mlbackProductResList.size()>0){
			//如果用这个id查到,就拿出来.
			mlbackProductOne = mlbackProductResList.get(0);
		}else{
			//如果用这个id没查到,就取出当前所有产品最新上的那款.
			mlbackProductResList = mlbackProductService.selectMlbackProductGetAll();
			mlbackProductOne = mlbackProductResList.get(0);
		}
		return Msg.success().add("resMsg", "查看单个产品详情完毕").add("mlbackProductOne", mlbackProductOne);
	}
	
	/**
	 * 10	zsh200721
	 * 前台移动端获取详情页面mfront/productDetails
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/toSearchPage",method=RequestMethod.GET)
	public String toSearchPage(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestParam(value = "searchProductName") String searchProductName) throws Exception{
		//接收传递进来的参数
		String seaProductNameReq = searchProductName;
		//放回响应域中
		res.setAttribute("productName", seaProductNameReq);
		//放回session域中
		session.setAttribute("productName", seaProductNameReq);
		
		return "portal/searchproductlist";
	}
	
	/**
	  * 11.0	zsh200721
	  * 通过产品名模糊搜索
	  * @param productId
	  * @return 
	  */
	@RequestMapping(value="/searchProductLike",method=RequestMethod.POST)
	@ResponseBody
	public Msg searchProductLike(@RequestParam(value = "productName") String productName){
		//接受信息
		//客户搜索记录
		MlbackSearch mlbackSearchReq = new MlbackSearch();
		mlbackSearchReq.setSearchContent(productName);
		String nowTime = DateUtil.strTime14s();
		mlbackSearchReq.setSearchCreatetime(nowTime);
		mlbackSearchReq.setSearchMotifytime(nowTime);
		mlbackSearchService.insertSelective(mlbackSearchReq);
		//执行搜索
		MlbackProduct mlbackProductReq = new MlbackProduct();
		mlbackProductReq.setProductName(productName);
		System.out.println("操作说明:客户搜索的产品名字productName:"+productName);
		List<MlbackProduct> mlbackProductResList =mlbackProductService.selectMlbackProductLike(mlbackProductReq);
		List<MlbackProduct> mlbackProductResListnum =mlbackProductService.selectMlbackProductLikeNum(mlbackProductReq);
		Integer num = mlbackProductResListnum.size();
		
		if(num>0){
			System.out.println("操作说明:客户搜索的产品名,查询结果mlbackProductResListnum:"+num);
			return Msg.success().add("resMsg", "产品名模糊搜索完毕").add("mlbackProductResList", mlbackProductResList).add("mlbackProductResListnum", num).add("productName", productName);
		}else{
			//1查询全部的上架产品信息,2便利匹配搜索字段
			
			MlbackProduct mlbackProductReqIn = new MlbackProduct();
			mlbackProductReqIn.setProductStatus(1);
			List<MlbackProduct> mlbackProductALLResList = mlbackProductService.selectMlbackProductSimpleByParamOnlyProDateilUsed(mlbackProductReqIn);
			List<MlbackProduct> mlbackProductReturnList = new ArrayList<MlbackProduct>();
			
			String searchProductName = productName;
			String searchProductNameItemArr[] =searchProductName.split(" ");
			
			for(MlbackProduct mlbackProductALLOne:mlbackProductALLResList){
				String pName = mlbackProductALLOne.getProductName();
				
				String pNameLess = pName.toLowerCase();
				Integer flag = 1;
				for(int i =0;i<searchProductNameItemArr.length;i++){
					String sraechProductNameOne = searchProductNameItemArr[i];
					String sraechProductNameOneLess = sraechProductNameOne;
					if(pNameLess.contains(sraechProductNameOneLess)){
						flag = 1;
					}else{
						flag = 0;
						break;
					}
				}
				if(flag==1){
					mlbackProductReturnList.add(mlbackProductALLOne);
				}
			}
			Integer finallyNum = mlbackProductReturnList.size();
			System.out.println("操作说明:客户搜索的产品名,查询结果mlbackProductResListnum:"+num);
			return Msg.success().add("resMsg", "产品名模糊搜索完毕").add("mlbackProductResList", mlbackProductReturnList).add("mlbackProductResListnum", finallyNum).add("productName", productName);
		}
	}
	 
	 /**12.0	20200914
	 * MlbackProduct	copyProByPid
	 * @param MlbackProduct
	 * @return
	 */
	@RequestMapping(value="/copyProByPid",method=RequestMethod.POST)
	@ResponseBody
	public Msg copyProByPid(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackProduct mlbackProduct){
		//接受参数信息
		Integer productId =  mlbackProduct.getProductId();
		
		MlbackProduct mlbackProductReq = new MlbackProduct();
		mlbackProductReq.setProductId(productId);
		List<MlbackProduct> mlbackProductResList =mlbackProductService.selectMlbackProductByParam(mlbackProductReq);
		
		MlbackProduct mlbackProductRes = new MlbackProduct();
		mlbackProductRes = mlbackProductResList.get(0);
		String productName = mlbackProductRes.getProductName();
		String proSeo = mlbackProductRes.getProductSeo();
		
		MlbackProduct mlbackProductIn =  mlbackProductRes;
		mlbackProductIn.setProductId(null);
		mlbackProductIn.setProductName(productName+"COPY");
		mlbackProductIn.setProductSeo(proSeo+"COPY");
		mlbackProductIn.setProductStatus(0);//0,不上架1,上架	刚复制完
		mlbackProductIn.setProductNeedProTogetherId(999);//	组合产品的id	刚复制完	无绑定
		
		String nowTime = DateUtil.strTime14s();
		mlbackProductIn.setProductCreatetime(nowTime);
		mlbackProductIn.setProductMotifytime(nowTime);
		
		mlbackProductService.insertSelective(mlbackProductIn);
		return Msg.success().add("resMsg", "copy产品成功").add("productOne", mlbackProductIn);
	}
}
