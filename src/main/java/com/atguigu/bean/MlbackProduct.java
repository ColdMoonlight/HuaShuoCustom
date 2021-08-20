package com.atguigu.bean;

import java.math.BigDecimal;

public class MlbackProduct {
    private Integer productId;

    private String productName;

    private String productSeo;

    private Integer productSupercateid;

    private String productSupercatename;

    private String productMainimgurl;

    private String productMainfuimgurl;

    private String productMainsmallimgurl;

    private String productMainfumallimgurl;
    
    private String productDiscoutimgurl;
    
    private Integer productDiscoutimgShow;
    
    private BigDecimal productOriginalprice;

    private Integer productHavesalenum;

    private Integer productStatus;

    private String productCreatetime;

    private String productMotifytime;

    private Integer productLable;

    private Integer productFirsth;

    private Integer productActoffoff;

    private Integer productReviewnum;

    private String productCategoryIdsstr;

    private String productCategoryNamesstr;

    private String productMetaDesc;

    private String productMetaTitle;

    private String productMetaKeywords;

    private String productDesc;
    
    private String productVideoUrl;
    
    private String productVideoImgUrl;
    
    private Integer productNeedProTogetherId;//0-无推荐;999-默认组合

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductSeo() {
        return productSeo;
    }

    public void setProductSeo(String productSeo) {
        this.productSeo = productSeo == null ? null : productSeo.trim();
    }

    public Integer getProductSupercateid() {
        return productSupercateid;
    }

    public void setProductSupercateid(Integer productSupercateid) {
        this.productSupercateid = productSupercateid;
    }

    public String getProductSupercatename() {
        return productSupercatename;
    }

    public void setProductSupercatename(String productSupercatename) {
        this.productSupercatename = productSupercatename == null ? null : productSupercatename.trim();
    }

    public String getProductMainimgurl() {
        return productMainimgurl;
    }

    public void setProductMainimgurl(String productMainimgurl) {
        this.productMainimgurl = productMainimgurl == null ? null : productMainimgurl.trim();
    }

    public String getProductMainfuimgurl() {
        return productMainfuimgurl;
    }

    public void setProductMainfuimgurl(String productMainfuimgurl) {
        this.productMainfuimgurl = productMainfuimgurl == null ? null : productMainfuimgurl.trim();
    }

    public String getProductMainsmallimgurl() {
        return productMainsmallimgurl;
    }

    public void setProductMainsmallimgurl(String productMainsmallimgurl) {
        this.productMainsmallimgurl = productMainsmallimgurl == null ? null : productMainsmallimgurl.trim();
    }

    public String getProductMainfumallimgurl() {
        return productMainfumallimgurl;
    }

    public void setProductMainfumallimgurl(String productMainfumallimgurl) {
        this.productMainfumallimgurl = productMainfumallimgurl == null ? null : productMainfumallimgurl.trim();
    }
    
    public String getProductDiscoutimgurl() {
		return productDiscoutimgurl;
	}

	public void setProductDiscoutimgurl(String productDiscoutimgurl) {
		this.productDiscoutimgurl = productDiscoutimgurl == null ? null : productDiscoutimgurl.trim();
	}
	
	public Integer getProductDiscoutimgShow() {
		return productDiscoutimgShow;
	}

	public void setProductDiscoutimgShow(Integer productDiscoutimgShow) {
		this.productDiscoutimgShow = productDiscoutimgShow;
	}

	public BigDecimal getProductOriginalprice() {
        return productOriginalprice;
    }

    public void setProductOriginalprice(BigDecimal productOriginalprice) {
        this.productOriginalprice = productOriginalprice;
    }

    public Integer getProductHavesalenum() {
        return productHavesalenum;
    }

    public void setProductHavesalenum(Integer productHavesalenum) {
        this.productHavesalenum = productHavesalenum;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductCreatetime() {
        return productCreatetime;
    }

    public void setProductCreatetime(String productCreatetime) {
        this.productCreatetime = productCreatetime == null ? null : productCreatetime.trim();
    }

    public String getProductMotifytime() {
        return productMotifytime;
    }

    public void setProductMotifytime(String productMotifytime) {
        this.productMotifytime = productMotifytime == null ? null : productMotifytime.trim();
    }

    public Integer getProductLable() {
        return productLable;
    }

    public void setProductLable(Integer productLable) {
        this.productLable = productLable;
    }

    public Integer getProductFirsth() {
        return productFirsth;
    }

    public void setProductFirsth(Integer productFirsth) {
        this.productFirsth = productFirsth;
    }

    public Integer getProductActoffoff() {
        return productActoffoff;
    }

    public void setProductActoffoff(Integer productActoffoff) {
        this.productActoffoff = productActoffoff;
    }

    public Integer getProductReviewnum() {
        return productReviewnum;
    }

    public void setProductReviewnum(Integer productReviewnum) {
        this.productReviewnum = productReviewnum;
    }

    public String getProductCategoryIdsstr() {
        return productCategoryIdsstr;
    }

    public void setProductCategoryIdsstr(String productCategoryIdsstr) {
        this.productCategoryIdsstr = productCategoryIdsstr == null ? null : productCategoryIdsstr.trim();
    }

    public String getProductCategoryNamesstr() {
        return productCategoryNamesstr;
    }

    public void setProductCategoryNamesstr(String productCategoryNamesstr) {
        this.productCategoryNamesstr = productCategoryNamesstr == null ? null : productCategoryNamesstr.trim();
    }

    public String getProductMetaDesc() {
        return productMetaDesc;
    }

    public void setProductMetaDesc(String productMetaDesc) {
        this.productMetaDesc = productMetaDesc == null ? null : productMetaDesc.trim();
    }

    public String getProductMetaTitle() {
        return productMetaTitle;
    }

    public void setProductMetaTitle(String productMetaTitle) {
        this.productMetaTitle = productMetaTitle == null ? null : productMetaTitle.trim();
    }

    public String getProductMetaKeywords() {
        return productMetaKeywords;
    }

    public void setProductMetaKeywords(String productMetaKeywords) {
        this.productMetaKeywords = productMetaKeywords == null ? null : productMetaKeywords.trim();
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc == null ? null : productDesc.trim();
    }
    
	public String getProductVideoUrl() {
		return productVideoUrl;
	}

	public void setProductVideoUrl(String productVideoUrl) {
		this.productVideoUrl = productVideoUrl == null ? null : productVideoUrl.trim();
	}
	
	public String getProductVideoImgUrl() {
		return productVideoImgUrl;
	}

	public void setProductVideoImgUrl(String productVideoImgUrl) {
		this.productVideoImgUrl = productVideoImgUrl == null ? null : productVideoImgUrl.trim();
	}

	public Integer getProductNeedProTogetherId() {
		return productNeedProTogetherId;
	}

	public void setProductNeedProTogetherId(Integer productNeedProTogetherId) {
		this.productNeedProTogetherId = productNeedProTogetherId;
	}

	public MlbackProduct() {
		super();
	}

	public MlbackProduct(Integer productId, String productName, String productSeo, Integer productSupercateid,
			String productSupercatename, String productMainimgurl, String productMainfuimgurl,
			String productMainsmallimgurl, String productMainfumallimgurl, String productDiscoutimgurl,
			Integer productDiscoutimgShow, BigDecimal productOriginalprice, Integer productHavesalenum,
			Integer productStatus, String productCreatetime, String productMotifytime, Integer productLable,
			Integer productFirsth, Integer productActoffoff, Integer productReviewnum, String productCategoryIdsstr,
			String productCategoryNamesstr, String productMetaDesc, String productMetaTitle, String productMetaKeywords,
			String productDesc, String productVideoUrl, String productVideoImgUrl,Integer productNeedProTogetherId) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productSeo = productSeo;
		this.productSupercateid = productSupercateid;
		this.productSupercatename = productSupercatename;
		this.productMainimgurl = productMainimgurl;
		this.productMainfuimgurl = productMainfuimgurl;
		this.productMainsmallimgurl = productMainsmallimgurl;
		this.productMainfumallimgurl = productMainfumallimgurl;
		this.productDiscoutimgurl = productDiscoutimgurl;
		this.productDiscoutimgShow = productDiscoutimgShow;
		this.productOriginalprice = productOriginalprice;
		this.productHavesalenum = productHavesalenum;
		this.productStatus = productStatus;
		this.productCreatetime = productCreatetime;
		this.productMotifytime = productMotifytime;
		this.productLable = productLable;
		this.productFirsth = productFirsth;
		this.productActoffoff = productActoffoff;
		this.productReviewnum = productReviewnum;
		this.productCategoryIdsstr = productCategoryIdsstr;
		this.productCategoryNamesstr = productCategoryNamesstr;
		this.productMetaDesc = productMetaDesc;
		this.productMetaTitle = productMetaTitle;
		this.productMetaKeywords = productMetaKeywords;
		this.productDesc = productDesc;
		this.productVideoUrl = productVideoUrl;
		this.productVideoImgUrl = productVideoImgUrl;
		this.productNeedProTogetherId = productNeedProTogetherId;
	}

	@Override
	public String toString() {
		return "MlbackProduct [productId=" + productId + ", productName=" + productName + ", productSeo=" + productSeo
				+ ", productSupercateid=" + productSupercateid + ", productSupercatename=" + productSupercatename
				+ ", productMainimgurl=" + productMainimgurl + ", productMainfuimgurl=" + productMainfuimgurl
				+ ", productMainsmallimgurl=" + productMainsmallimgurl + ", productMainfumallimgurl="
				+ productMainfumallimgurl + ", productDiscoutimgurl=" + productDiscoutimgurl
				+ ", productDiscoutimgShow=" + productDiscoutimgShow + ", productOriginalprice=" + productOriginalprice
				+ ", productHavesalenum=" + productHavesalenum + ", productStatus=" + productStatus
				+ ", productCreatetime=" + productCreatetime + ", productMotifytime=" + productMotifytime
				+ ", productLable=" + productLable + ", productFirsth=" + productFirsth + ", productActoffoff="
				+ productActoffoff + ", productReviewnum=" + productReviewnum + ", productCategoryIdsstr="
				+ productCategoryIdsstr + ", productCategoryNamesstr=" + productCategoryNamesstr + ", productMetaDesc="
				+ productMetaDesc + ", productMetaTitle=" + productMetaTitle + ", productMetaKeywords="
				+ productMetaKeywords + ", productDesc=" + productDesc + ", productVideoUrl=" + productVideoUrl
				+ ", productVideoImgUrl=" + productVideoImgUrl + ", productNeedProTogetherId=" + productNeedProTogetherId + "]";
	}
	
}