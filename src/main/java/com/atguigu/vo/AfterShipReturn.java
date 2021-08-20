package com.atguigu.vo;

public class AfterShipReturn {
	
	private String afterOperateStatus;//写入afterShip状态

    private String afterShipSlugName;//afterShip返回的物流名称
    
    private String afterShipDetailUrl;//写入后返回的查看物流链接
    
	public String getAfterOperateStatus() {
		return afterOperateStatus;
	}

	public void setAfterOperateStatus(String afterOperateStatus) {
		this.afterOperateStatus = afterOperateStatus;
	}

	public String getAfterShipSlugName() {
		return afterShipSlugName;
	}

	public void setAfterShipSlugName(String afterShipSlugName) {
		this.afterShipSlugName = afterShipSlugName;
	}

	public String getAfterShipDetailUrl() {
		return afterShipDetailUrl;
	}

	public void setAfterShipDetailUrl(String afterShipDetailUrl) {
		this.afterShipDetailUrl = afterShipDetailUrl;
	}
	
	public AfterShipReturn() {
		super();
	}

	public AfterShipReturn(String afterOperateStatus, String afterShipSlugName, String afterShipDetailUrl) {
		super();
		this.afterOperateStatus = afterOperateStatus;
		this.afterShipSlugName = afterShipSlugName;
		this.afterShipDetailUrl = afterShipDetailUrl;
	}

	@Override
	public String toString() {
		return "AfterShipReturn [afterOperateStatus=" + afterOperateStatus + ", afterShipSlugName=" + afterShipSlugName
				+ ", afterShipDetailUrl=" + afterShipDetailUrl + "]";
	}
	
}
