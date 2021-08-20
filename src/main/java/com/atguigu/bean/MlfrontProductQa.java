package com.atguigu.bean;

public class MlfrontProductQa {
    private Integer productqaId;

    private String productqaProductIds;

    private String productqaQuestionIds;

    private String productqaName;
    
    private String productqaCreatetime;

    private String productqaModifyTime;
    
    public Integer getProductqaId() {
        return productqaId;
    }

    public void setProductqaId(Integer productqaId) {
        this.productqaId = productqaId;
    }

    public String getProductqaProductIds() {
        return productqaProductIds;
    }

    public void setProductqaProductIds(String productqaProductIds) {
        this.productqaProductIds = productqaProductIds == null ? null : productqaProductIds.trim();
    }

    public String getProductqaQuestionIds() {
        return productqaQuestionIds;
    }

    public void setProductqaQuestionIds(String productqaQuestionIds) {
        this.productqaQuestionIds = productqaQuestionIds == null ? null : productqaQuestionIds.trim();
    }

    public String getProductqaName() {
        return productqaName;
    }

    public void setProductqaName(String productqaName) {
        this.productqaName = productqaName == null ? null : productqaName.trim();
    }

    public String getProductqaCreatetime() {
        return productqaCreatetime;
    }

    public void setProductqaCreatetime(String productqaCreatetime) {
        this.productqaCreatetime = productqaCreatetime == null ? null : productqaCreatetime.trim();
    }
    
    public String getProductqaModifyTime() {
        return productqaModifyTime;
    }

    public void setProductqaModifyTime(String productqaModifyTime) {
        this.productqaModifyTime = productqaModifyTime == null ? null : productqaModifyTime.trim();
    }
	public MlfrontProductQa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MlfrontProductQa(Integer productqaId, String productqaProductIds, String productqaQuestionIds,
			String productqaName, String productqaCreatetime, String productqaModifyTime) {
		super();
		this.productqaId = productqaId;
		this.productqaProductIds = productqaProductIds;
		this.productqaQuestionIds = productqaQuestionIds;
		this.productqaName = productqaName;
		this.productqaCreatetime = productqaCreatetime;
		this.productqaModifyTime = productqaModifyTime;
	}

	@Override
	public String toString() {
		return "MlfrontProductQa [productqaId=" + productqaId + ", productqaProductIds=" + productqaProductIds
				+ ", productqaQuestionIds=" + productqaQuestionIds + ", productqaName=" + productqaName
				+ ", productqaCreatetime=" + productqaCreatetime + ", productqaModifyTime=" + productqaModifyTime +"]";
	}
	
}