package com.atguigu.bean;

public class MlbackProductAttributeName {
	
    private Integer productattrnameId;	//属性名Id

    private String productattrnameName;	//属性名

    private Integer productattrnameSort;	//排序

    private Integer productattrnamePid;	//归属产品Id
    
    private String productattrnameValues;	//属性值串

    private Integer productattrnameStatus;	//状态:0不生效,1生效

    private String productattrnameCreatetime;	//创建时间

    public Integer getProductattrnameId() {
        return productattrnameId;
    }

    public void setProductattrnameId(Integer productattrnameId) {
        this.productattrnameId = productattrnameId;
    }

    public String getProductattrnameName() {
        return productattrnameName;
    }

    public void setProductattrnameName(String productattrnameName) {
        this.productattrnameName = productattrnameName == null ? null : productattrnameName.trim();
    }

    public Integer getProductattrnameSort() {
        return productattrnameSort;
    }

    public void setProductattrnameSort(Integer productattrnameSort) {
        this.productattrnameSort = productattrnameSort;
    }

    public Integer getProductattrnamePid() {
        return productattrnamePid;
    }

    public void setProductattrnamePid(Integer productattrnamePid) {
        this.productattrnamePid = productattrnamePid;
    }
    
    public String getProductattrnameValues() {
		return productattrnameValues;
	}

	public void setProductattrnameValues(String productattrnameValues) {
		this.productattrnameValues = productattrnameValues == null ? null : productattrnameValues.trim();
	}

	public Integer getProductattrnameStatus() {
        return productattrnameStatus;
    }

    public void setProductattrnameStatus(Integer productattrnameStatus) {
        this.productattrnameStatus = productattrnameStatus;
    }

    public String getProductattrnameCreatetime() {
        return productattrnameCreatetime;
    }

    public void setProductattrnameCreatetime(String productattrnameCreatetime) {
        this.productattrnameCreatetime = productattrnameCreatetime == null ? null : productattrnameCreatetime.trim();
    }

	public MlbackProductAttributeName() {
		super();
	}

	public MlbackProductAttributeName(Integer productattrnameId, String productattrnameName,
			Integer productattrnameSort, Integer productattrnamePid, String productattrnameValues,
			Integer productattrnameStatus, String productattrnameCreatetime) {
		super();
		this.productattrnameId = productattrnameId;
		this.productattrnameName = productattrnameName;
		this.productattrnameSort = productattrnameSort;
		this.productattrnamePid = productattrnamePid;
		this.productattrnameValues = productattrnameValues;
		this.productattrnameStatus = productattrnameStatus;
		this.productattrnameCreatetime = productattrnameCreatetime;
	}

	@Override
	public String toString() {
		return "MlbackProductAttributeName [productattrnameId=" + productattrnameId + ", productattrnameName="
				+ productattrnameName + ", productattrnameSort=" + productattrnameSort + ", productattrnamePid="
				+ productattrnamePid + ", productattrnameValues=" + productattrnameValues + ", productattrnameStatus="
				+ productattrnameStatus + ", productattrnameCreatetime=" + productattrnameCreatetime + "]";
	}

}