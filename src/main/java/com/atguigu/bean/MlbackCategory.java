package com.atguigu.bean;

public class MlbackCategory {
    private Integer categoryId;

    private String categoryName;

    private String categoryImgurl;
    
    private Integer categoryImgstatus;

    private Integer categoryParentId;

    private String categoryParentName;

    private Integer categoryStatus;

    private Integer categoryLable;

    private String categorySeo;

    private Integer categorySortOrder;

    private String categoryCreatetime;

    private String categoryMotifytime;

    private String categoryDesc;

    private String categoryProductIds;

    private String categoryProductNames;

    private String categoryImgpcurl;
    
    private Integer categoryImgpcstatus;

    private String categoryMetatitle;

    private String categoryMetakeywords;

    private String categoryMetadesc;
    
    private Integer categorySuperCateId;
    
    private String categorySuperCateName;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public String getCategoryImgurl() {
        return categoryImgurl;
    }

    public void setCategoryImgurl(String categoryImgurl) {
        this.categoryImgurl = categoryImgurl == null ? null : categoryImgurl.trim();
    }
    

    public Integer getCategoryImgstatus() {
		return categoryImgstatus;
	}

	public void setCategoryImgstatus(Integer categoryImgstatus) {
		this.categoryImgstatus = categoryImgstatus;
	}

	public Integer getCategoryParentId() {
        return categoryParentId;
    }

    public void setCategoryParentId(Integer categoryParentId) {
        this.categoryParentId = categoryParentId;
    }

    public String getCategoryParentName() {
        return categoryParentName;
    }

    public void setCategoryParentName(String categoryParentName) {
        this.categoryParentName = categoryParentName == null ? null : categoryParentName.trim();
    }

    public Integer getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(Integer categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    public Integer getCategoryLable() {
        return categoryLable;
    }

    public void setCategoryLable(Integer categoryLable) {
        this.categoryLable = categoryLable;
    }

    public String getCategorySeo() {
        return categorySeo;
    }

    public void setCategorySeo(String categorySeo) {
        this.categorySeo = categorySeo == null ? null : categorySeo.trim();
    }

    public Integer getCategorySortOrder() {
        return categorySortOrder;
    }

    public void setCategorySortOrder(Integer categorySortOrder) {
        this.categorySortOrder = categorySortOrder;
    }

    public String getCategoryCreatetime() {
        return categoryCreatetime;
    }

    public void setCategoryCreatetime(String categoryCreatetime) {
        this.categoryCreatetime = categoryCreatetime == null ? null : categoryCreatetime.trim();
    }

    public String getCategoryMotifytime() {
        return categoryMotifytime;
    }

    public void setCategoryMotifytime(String categoryMotifytime) {
        this.categoryMotifytime = categoryMotifytime == null ? null : categoryMotifytime.trim();
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc == null ? null : categoryDesc.trim();
    }

    public String getCategoryProductIds() {
        return categoryProductIds;
    }

    public void setCategoryProductIds(String categoryProductIds) {
        this.categoryProductIds = categoryProductIds == null ? null : categoryProductIds.trim();
    }

    public String getCategoryProductNames() {
        return categoryProductNames;
    }

    public void setCategoryProductNames(String categoryProductNames) {
        this.categoryProductNames = categoryProductNames == null ? null : categoryProductNames.trim();
    }

    public String getCategoryImgpcurl() {
        return categoryImgpcurl;
    }

    public void setCategoryImgpcurl(String categoryImgpcurl) {
        this.categoryImgpcurl = categoryImgpcurl == null ? null : categoryImgpcurl.trim();
    }

	public Integer getCategoryImgpcstatus() {
		return categoryImgpcstatus;
	}

	public void setCategoryImgpcstatus(Integer categoryImgpcstatus) {
		this.categoryImgpcstatus = categoryImgpcstatus;
	}

    public String getCategoryMetatitle() {
        return categoryMetatitle;
    }

    public void setCategoryMetatitle(String categoryMetatitle) {
        this.categoryMetatitle = categoryMetatitle == null ? null : categoryMetatitle.trim();
    }

    public String getCategoryMetakeywords() {
        return categoryMetakeywords;
    }

    public void setCategoryMetakeywords(String categoryMetakeywords) {
        this.categoryMetakeywords = categoryMetakeywords == null ? null : categoryMetakeywords.trim();
    }

    public String getCategoryMetadesc() {
        return categoryMetadesc;
    }

    public void setCategoryMetadesc(String categoryMetadesc) {
        this.categoryMetadesc = categoryMetadesc == null ? null : categoryMetadesc.trim();
    }

	public Integer getCategorySuperCateId() {
		return categorySuperCateId;
	}

	public void setCategorySuperCateId(Integer categorySuperCateId) {
		this.categorySuperCateId = categorySuperCateId;
	}

	public String getCategorySuperCateName() {
		return categorySuperCateName;
	}

	public void setCategorySuperCateName(String categorySuperCateName) {
		this.categorySuperCateName = categorySuperCateName == null ? null : categorySuperCateName.trim();
	}
	
	public MlbackCategory(Integer categoryId, String categoryName, String categoryImgurl, Integer categoryImgstatus,
			Integer categoryParentId, String categoryParentName, Integer categoryStatus, Integer categoryLable,
			String categorySeo, Integer categorySortOrder, String categoryCreatetime, String categoryMotifytime,
			String categoryDesc, String categoryProductIds, String categoryProductNames, String categoryImgpcurl,
			Integer categoryImgpcstatus, String categoryMetatitle, String categoryMetakeywords, String categoryMetadesc,
			Integer categorySuperCateId, String categorySuperCateName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryImgurl = categoryImgurl;
		this.categoryImgstatus = categoryImgstatus;
		this.categoryParentId = categoryParentId;
		this.categoryParentName = categoryParentName;
		this.categoryStatus = categoryStatus;
		this.categoryLable = categoryLable;
		this.categorySeo = categorySeo;
		this.categorySortOrder = categorySortOrder;
		this.categoryCreatetime = categoryCreatetime;
		this.categoryMotifytime = categoryMotifytime;
		this.categoryDesc = categoryDesc;
		this.categoryProductIds = categoryProductIds;
		this.categoryProductNames = categoryProductNames;
		this.categoryImgpcurl = categoryImgpcurl;
		this.categoryImgpcstatus = categoryImgpcstatus;
		this.categoryMetatitle = categoryMetatitle;
		this.categoryMetakeywords = categoryMetakeywords;
		this.categoryMetadesc = categoryMetadesc;
		this.categorySuperCateId = categorySuperCateId;
		this.categorySuperCateName = categorySuperCateName;
	}

	public MlbackCategory() {
		super();
	}

	@Override
	public String toString() {
		return "MlbackCategory [categoryId=" + categoryId + ", categoryName=" + categoryName + ", categoryImgurl="
				+ categoryImgurl + ", categoryImgstatus=" + categoryImgstatus + ", categoryParentId=" + categoryParentId
				+ ", categoryParentName=" + categoryParentName + ", categoryStatus=" + categoryStatus
				+ ", categoryLable=" + categoryLable + ", categorySeo=" + categorySeo + ", categorySortOrder="
				+ categorySortOrder + ", categoryCreatetime=" + categoryCreatetime + ", categoryMotifytime="
				+ categoryMotifytime + ", categoryDesc=" + categoryDesc + ", categoryProductIds=" + categoryProductIds
				+ ", categoryProductNames=" + categoryProductNames + ", categoryImgpcurl=" + categoryImgpcurl
				+ ", categoryImgpcstatus=" + categoryImgpcstatus + ", categoryMetatitle=" + categoryMetatitle
				+ ", categoryMetakeywords=" + categoryMetakeywords + ", categoryMetadesc=" + categoryMetadesc
				+ ", categorySuperCateId=" + categorySuperCateId + ", categorySuperCateName=" + categorySuperCateName
				+ "]";
	}
    
}