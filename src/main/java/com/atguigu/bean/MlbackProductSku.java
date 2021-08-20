package com.atguigu.bean;

public class MlbackProductSku {
	
    private Integer productskuId;	//skuId

    private String productskuName;	//skuName

    private String productskuCode;	//skuCode
    
    private String productskuPSeoname;	//PSeoname

    private String productskuMoney;	//价格

    private Integer productskuStock;	//库存

    private Integer productskuPid;	//归属产品Id
    
    private String productskuOneImg;

    private Integer productskuStatus;	//sku状态:0不生效,1生效

    private String productskuCreatetime;	//add时间

    private String productskuMotifytime;	//update时间

    public Integer getProductskuId() {
        return productskuId;
    }

    public void setProductskuId(Integer productskuId) {
        this.productskuId = productskuId;
    }

    public String getProductskuName() {
        return productskuName;
    }

    public void setProductskuName(String productskuName) {
        this.productskuName = productskuName == null ? null : productskuName.trim();
    }

    public String getProductskuCode() {
		return productskuCode;
	}

	public void setProductskuCode(String productskuCode) {
		this.productskuCode = productskuCode == null ? null : productskuCode.trim();
	}
	
	public String getProductskuPSeoname() {
		return productskuPSeoname;
	}

	public void setProductskuPSeoname(String productskuPSeoname) {
		this.productskuPSeoname = productskuPSeoname == null ? null : productskuPSeoname.trim();
	}

	public String getProductskuMoney() {
        return productskuMoney;
    }

    public void setProductskuMoney(String productskuMoney) {
        this.productskuMoney = productskuMoney == null ? null : productskuMoney.trim();
    }

    public Integer getProductskuStock() {
        return productskuStock;
    }

    public void setProductskuStock(Integer productskuStock) {
        this.productskuStock = productskuStock;
    }

    public Integer getProductskuPid() {
        return productskuPid;
    }

    public void setProductskuPid(Integer productskuPid) {
        this.productskuPid = productskuPid;
    }
    
    public String getProductskuOneImg() {
		return productskuOneImg;
	}

	public void setProductskuOneImg(String productskuOneImg) {
		this.productskuOneImg = productskuOneImg == null ? null : productskuOneImg.trim();
	}

	public Integer getProductskuStatus() {
        return productskuStatus;
    }

    public void setProductskuStatus(Integer productskuStatus) {
        this.productskuStatus = productskuStatus;
    }

    public String getProductskuCreatetime() {
        return productskuCreatetime;
    }

    public void setProductskuCreatetime(String productskuCreatetime) {
        this.productskuCreatetime = productskuCreatetime == null ? null : productskuCreatetime.trim();
    }

    public String getProductskuMotifytime() {
        return productskuMotifytime;
    }

    public void setProductskuMotifytime(String productskuMotifytime) {
        this.productskuMotifytime = productskuMotifytime == null ? null : productskuMotifytime.trim();
    }

	public MlbackProductSku() {
		super();
	}

	public MlbackProductSku(Integer productskuId, String productskuName, String productskuCode,
			String productskuPSeoname, String productskuMoney, Integer productskuStock, Integer productskuPid,
			String productskuOneImg, Integer productskuStatus, String productskuCreatetime,
			String productskuMotifytime) {
		super();
		this.productskuId = productskuId;
		this.productskuName = productskuName;
		this.productskuCode = productskuCode;
		this.productskuPSeoname = productskuPSeoname;
		this.productskuMoney = productskuMoney;
		this.productskuStock = productskuStock;
		this.productskuPid = productskuPid;
		this.productskuOneImg = productskuOneImg;
		this.productskuStatus = productskuStatus;
		this.productskuCreatetime = productskuCreatetime;
		this.productskuMotifytime = productskuMotifytime;
	}

	@Override
	public String toString() {
		return "MlbackProductSku [productskuId=" + productskuId + ", productskuName=" + productskuName
				+ ", productskuCode=" + productskuCode + ", productskuPSeoname=" + productskuPSeoname
				+ ", productskuMoney=" + productskuMoney + ", productskuStock=" + productskuStock + ", productskuPid="
				+ productskuPid + ", productskuOneImg=" + productskuOneImg + ", productskuStatus=" + productskuStatus
				+ ", productskuCreatetime=" + productskuCreatetime + ", productskuMotifytime=" + productskuMotifytime
				+ "]";
	}

}