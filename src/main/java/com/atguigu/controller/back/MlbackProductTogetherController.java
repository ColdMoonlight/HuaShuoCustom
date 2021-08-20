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
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.MlbackProduct;
import com.atguigu.bean.MlbackProductAttributeName;
import com.atguigu.bean.MlbackProductSku;
import com.atguigu.bean.MlbackProductTogether;
import com.atguigu.common.Const;
import com.atguigu.common.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackProductAttributeNameService;
import com.atguigu.service.MlbackProductService;
import com.atguigu.service.MlbackProductSkuService;
import com.atguigu.service.MlbackProductTogetherService;
import com.atguigu.utils.DateUtil;

@Controller
@RequestMapping("/productTogether")
public class MlbackProductTogetherController {
	
	@Autowired
	MlbackProductService mlbackProductService;
	
	@Autowired
	MlbackProductTogetherService mlbackProductTogetherService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	@Autowired
	MlbackProductSkuService mlbackProductSkuService;
	
	@Autowired
	MlbackProductAttributeNameService mlbackProductAttributeNameService;
	
	/**
	 * 1.0	20201217
	 * to后台分类ProductTogether列表页面
	 * @return jsp
	 * */
	@RequestMapping("/toProductTogetherPage")
	public String toProductTogetherPage(HttpSession session) throws Exception{
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
		if(mlbackAdmin==null){
			//SysUsers对象为空
			return "back/mlbackAdminLogin";
		}else{
			return "back/product/productTogetherPage";
		}
	}
	
	/**2.0	20200703
	 * 后台MlbackCatalog列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getProductTogetherByPage")
	@ResponseBody
	public Msg getProductTogetherByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {

		int PagNum = Const.PAGE_NUM_CATEGORY;
		PageHelper.startPage(pn, PagNum);
		List<MlbackProductTogether> productTogetherList = mlbackProductTogetherService.selectMlbackProductTogetherGetAll();
		PageInfo page = new PageInfo(productTogetherList, PagNum);
		return Msg.success().add("pageInfo", page);
	}
	
	/**3.0	20200703
	 * MlbackProductTogether	initializaProductTogether
	 * @param MlbackProductTogether
	 * @return
	 */
	@RequestMapping(value="/initializaProductTogether",method=RequestMethod.POST)
	@ResponseBody
	public Msg initializaProductTogether(HttpServletResponse rep,HttpServletRequest res){
		
		MlbackProductTogether mlbackProductTogether = new MlbackProductTogether();
		//取出id
		String nowTime = DateUtil.strTime14s();
		mlbackProductTogether.setProducttogetherCreatetime(nowTime);
		mlbackProductTogether.setProducttogetherStatus(0);//0未上架1上架中
		//无id,insert
		System.out.println("插入前"+mlbackProductTogether.toString());
		mlbackProductTogetherService.insertSelective(mlbackProductTogether);
		System.out.println("插入后"+mlbackProductTogether.toString());
		return Msg.success().add("resMsg", "Catalog初始化成功").add("mlbackProductTogether", mlbackProductTogether);
	}
	
	/**3.0	20200703
	 * mlbackProductTogether	update
	 * @param mlbackProductTogether
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackProductTogether mlbackProductTogether){
		//接受参数信息
		System.out.println("mlbackProductTogether:"+mlbackProductTogether);
		//取出id
		Integer proTogetherId = mlbackProductTogether.getProducttogetherId();
		String nowTime = DateUtil.strTime14s();
		mlbackProductTogether.setProducttogetherMotifytime(nowTime);
		if(proTogetherId==null){
			//无id,insert
			mlbackProductTogether.setProducttogetherCreatetime(nowTime);
			mlbackProductTogetherService.insertSelective(mlbackProductTogether);
			System.out.println("mlbackProductTogether--insertSuccess--:"+mlbackProductTogether);
			return Msg.success().add("resMsg", "插入成功");
		}else{
			//有id,update
			mlbackProductTogetherService.updateByPrimaryKeySelective(mlbackProductTogether);
			//更新产品表
			//读出一起买里面绑定的产品id串串,遍历修改(查出该产品,修改里面的组合绑定字段)
			String belongProIdsStr = mlbackProductTogether.getProducttogetherBelongProIdStr();
			
			if(belongProIdsStr==null){
				System.out.println("并没有配置产品,belongProIdsStr字段为null");
			}else{
				
				//先清理到组合id之前归属的产品串串
				//对遍历这些产品,把这些产品下面的proTogetHerId字段都回到默认值999
				updateProductDeatilByproTogetherId(proTogetherId);
				
				System.out.println("配置的归属产品为,belongProIdsStr字段为:"+belongProIdsStr);
				String productIdsStrArr [] = belongProIdsStr.split(",");
				String productIdStr = "";
				Integer productIdInt = 0;
				for(int i=0;i<productIdsStrArr.length;i++){
					productIdStr=productIdsStrArr[i];
					productIdInt = Integer.parseInt(productIdStr);
					MlbackProduct mlbackProductReq = new MlbackProduct();
					mlbackProductReq.setProductId(productIdInt);
					mlbackProductReq.setProductNeedProTogetherId(proTogetherId);
					mlbackProductService.updateByPrimaryKeySelective(mlbackProductReq);
				}
			}
			return Msg.success().add("resMsg", "更新成功").add("mlbackProductTogether", mlbackProductTogether);
		}
	}
	//更新掉产品里面的proTogetHerId字段ByproTogetherId
	private void updateProductDeatilByproTogetherId(Integer proTogetherId) {
		MlbackProductTogether mlbackProductTogetherOld = new MlbackProductTogether();
		mlbackProductTogetherOld.setProducttogetherId(proTogetherId);
		List<MlbackProductTogether> mlbackProductTogetherList =mlbackProductTogetherService.selectMlbackProductTogetherById(mlbackProductTogetherOld);
		if(mlbackProductTogetherList.size()>0){
			mlbackProductTogetherOld = mlbackProductTogetherList.get(0);
			String belongProIdStr = mlbackProductTogetherOld.getProducttogetherBelongProIdStr();
			String productIdsStrArr [] = belongProIdStr.split(",");
			String productIdStr = "";
			Integer productIdInt = 0;
			for(int i=0;i<productIdsStrArr.length;i++){
				productIdStr=productIdsStrArr[i];
				productIdInt = Integer.parseInt(productIdStr);
				MlbackProduct mlbackProductReq = new MlbackProduct();
				mlbackProductReq.setProductId(productIdInt);
				mlbackProductReq.setProductNeedProTogetherId(999);//返回默认值
				mlbackProductService.updateByPrimaryKeySelective(mlbackProductReq);
			}
			
		}
		
	}

	/**4.0	20201218
	 * mlbackProductTogether	delete
	 * @param mlbackProductTogether-producttogetherId
	 * @return 
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlbackProductTogether mlbackProductTogether){
		//接收id信息
		Integer producttogetherId = mlbackProductTogether.getProducttogetherId();
		mlbackProductTogetherService.deleteByPrimaryKey(producttogetherId);
		return Msg.success().add("resMsg", "Catalog delete  success");
	}
	
	/**
	 * 6.0	20200703
	 * 查单条mlbackProductTogether详情
	 * @param mlbackProductTogether-producttogetherId
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackProductTogetherDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackProductTogetherDetail(@RequestBody MlbackProductTogether mlbackProductTogether){
		
		//接受wholesaleId
		Integer producttogetherId = mlbackProductTogether.getProducttogetherId();
		MlbackProductTogether mlbackProductTogetherReq = new MlbackProductTogether();
		mlbackProductTogetherReq.setProducttogetherId(producttogetherId);
		//查询本条
		List<MlbackProductTogether> mlbackProductTogetherResList =mlbackProductTogetherService.selectMlbackProductTogetherById(mlbackProductTogetherReq);
		MlbackProductTogether mlbackProductTogetherOne =mlbackProductTogetherResList.get(0);
		return Msg.success().add("resMsg", "查CatalogOne完毕").add("mlbackProductTogetherOne", mlbackProductTogetherOne);
	}
	
	/**
	 * 6.1	20201218
	 * 查单条mlbackProductTogether详情
	 * @param mlbackProductTogether-producttogetherId
	 * @return 
	 */
	@RequestMapping(value="/getProtalOneMlbackProductTogetherDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getProtalOneMlbackProductTogetherDetail(@RequestBody MlbackProductTogether mlbackProductTogether){
		
		//临时存储的,此字段中放置的是当前产品pid
		Integer ProId= mlbackProductTogether.getProducttogetherSupercateId();
		//有组合,查不到-走;能查到-显示;组合是,,,,,,
		//接受wholesaleId
		Integer producttogetherId = mlbackProductTogether.getProducttogetherId();
		MlbackProductTogether mlbackProductTogetherReq = new MlbackProductTogether();
		mlbackProductTogetherReq.setProducttogetherId(producttogetherId);
		//查询本条
		List<MlbackProductTogether> mlbackProductTogetherResList =mlbackProductTogetherService.selectMlbackProductTogetherById(mlbackProductTogetherReq);
		MlbackProductTogether mlbackProductTogetherRes = null;
		
		if(mlbackProductTogetherResList.size()>0){
			//取出结果
			mlbackProductTogetherRes =mlbackProductTogetherResList.get(0);
			
			Integer producttogetherStatus = mlbackProductTogetherRes.getProducttogetherStatus();
			if(producttogetherStatus==0){
				return Msg.success().add("resMsg", "查CatalogOne完毕").add("mlbackProductTogetherRes", mlbackProductTogetherRes);
			}
			//拿到值
			String toGetHerIdsStr = mlbackProductTogetherRes.getProducttogetherProsidStr();
			//抽出下面的几个id
			String toGetHerIdsStrArr [] = toGetHerIdsStr.split(",");
			//先遍历一遍,有没有当前的产品pid
			String orginalPidStr = ProId+"";
			Integer insite = 999;
			for(int k =0;k<toGetHerIdsStrArr.length;k++){
				if(orginalPidStr.equals(toGetHerIdsStrArr[k])){
					//查到了
					insite = k;
					break;
				}
			}
			List<String> aaaList = new ArrayList<String>();
			if(insite==999){//999是初始值,没有第任何位置的值跟pid一致
				aaaList.add(orginalPidStr);
				for(int k =0;k<toGetHerIdsStrArr.length;k++){
					aaaList.add(toGetHerIdsStrArr[k]);
				}
			}else{//位置值,第几任何位置的值跟pid一致
				//存在
				aaaList.add(orginalPidStr);
				for(int k =0;k<toGetHerIdsStrArr.length;k++){
					if(orginalPidStr.equals(toGetHerIdsStrArr[k])){
						//查到了
						//这个重复了,不添加了
					}else{
						aaaList.add(toGetHerIdsStrArr[k]);
					}
				}
			}
			
			//遍历ids,准备拿下面的sku属性列表
			List<MlbackProduct> mlbackProductList =new ArrayList<MlbackProduct>();
			List<List<MlbackProductAttributeName>> propAttributeNameListList = new ArrayList<List<MlbackProductAttributeName>>(); 
			List<List<MlbackProductSku>> mlbackProductSkuTogetherList =new ArrayList<List<MlbackProductSku>>();
			for(int i=0;i<aaaList.size();i++){
				String proIdstr=aaaList.get(i);
				Integer proIdInt = Integer.parseInt(proIdstr);
				//准备封装产品id,查询该id下面的pro明细
				MlbackProduct mlbackProductOne = getProListByPid(proIdInt);
				mlbackProductList.add(mlbackProductOne);
				//准备封装产品id,查询该id下面的proAttr明细
				List<MlbackProductAttributeName> proAttributeNameOneList = getProAttributeNameListByPid(proIdInt);
				propAttributeNameListList.add(proAttributeNameOneList);
				//准备封装产品id,查询该id下面的prosku明细
				List<MlbackProductSku> mlbackProductSkuResListOne = getPSkuListByPid(proIdInt);
				mlbackProductSkuTogetherList.add(mlbackProductSkuResListOne);
			}
			//组合,查到-显示;
			return Msg.success().add("resMsg", "查CatalogOne完毕").add("mlbackProductTogetherRes", mlbackProductTogetherRes)
					.add("mlbackProductList", mlbackProductList).add("propAttributeNameListList", propAttributeNameListList)
					.add("mlbackProductSkuTogetherList", mlbackProductSkuTogetherList);
		}else{
			//组合,查不到-走;
			return Msg.success().add("resMsg", "查CatalogOne完毕").add("mlbackProductTogetherRes", mlbackProductTogetherRes);
		}
	}
	
	private MlbackProduct getProListByPid(Integer proIdInt) {
		MlbackProduct mlbackProductReq = new MlbackProduct();
		mlbackProductReq.setProductId(proIdInt);
		List<MlbackProduct> mlbackProductResList =mlbackProductService.selectMlbackProductbyPid(mlbackProductReq);
		MlbackProduct mlbackProductOne = new MlbackProduct();
		if(mlbackProductResList.size()>0){
			//如果用这个id查到,就拿出来.
			mlbackProductOne = mlbackProductResList.get(0);
		}
		return mlbackProductOne;
	}
	
	private List<MlbackProductAttributeName> getProAttributeNameListByPid(Integer productId) {
		
		List<MlbackProductAttributeName> mbackProductAttributeNameResList =mlbackProductAttributeNameService.selectMlbackProductAttributeNameByProductId(productId);
		return mbackProductAttributeNameResList;
	}

	private List<MlbackProductSku> getPSkuListByPid(Integer productskuPid) {
		
		MlbackProductSku mlbackProductSkuReq = new MlbackProductSku();
		mlbackProductSkuReq.setProductskuPid(productskuPid);
		mlbackProductSkuReq.setProductskuStatus(1);//
		
		//接受信息
		List<MlbackProductSku> mlbackProductSkuResList =mlbackProductSkuService.selectMlbackProductSkuListByPIdTogether(mlbackProductSkuReq);
		return mlbackProductSkuResList;
		
	}

	/**
	 * 7.0	zsh	20200608
	 * 后端获取backSearchByWholesale产品list
	 * @return 
	 * */
	@RequestMapping(value="/backSearchByProductTogether",method=RequestMethod.POST)
	@ResponseBody
	public Msg backSearchByProductTogether(HttpServletResponse rep,HttpServletRequest res,HttpSession session,
			@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			@RequestParam(value = "producttogetherName") String producttogetherName,
			@RequestParam(value = "producttogetherSupercateId", defaultValue = "1") Integer producttogetherSupercateId) throws Exception{
		
		//接收传递进来的参数
		int PagNum = 30;
		PageHelper.startPage(pn, PagNum);
		
		MlbackProductTogether mlbackProductTogetherReq = new MlbackProductTogether();
		mlbackProductTogetherReq.setProducttogetherName(producttogetherName);
		mlbackProductTogetherReq.setProducttogetherSupercateId(producttogetherSupercateId);
		List<MlbackProductTogether> mlbackProductTogetherResList = mlbackProductTogetherService.selectMlbackProductTogetherBackSearch(mlbackProductTogetherReq);
		PageInfo page = new PageInfo(mlbackProductTogetherResList, PagNum);
		return Msg.success().add("pageInfo", page);
	}
	
	/**
	 * 8.0	zsh	201222
	 * 组合下拉列表lownLoadProTogether
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/lownLoadProTogether",method=RequestMethod.POST)
	@ResponseBody
	public Msg lownLoadProTogether(HttpServletResponse rep,HttpServletRequest res,HttpSession session) throws Exception{
		
		MlbackProductTogether mlbackProductTogetherReq = new MlbackProductTogether();
		List<MlbackProductTogether> mlbackProductTogetherResList = mlbackProductTogetherService.selectMlbackProTogetherDownLoadByParam(mlbackProductTogetherReq);
		return Msg.success().add("mlbackProductTogetherResList", mlbackProductTogetherResList);
	}
	
	
}
