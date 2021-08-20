package com.atguigu.vo;

public class EcppTrackItem {
	
	private String ecppOrderStatusCode;//ecpp单子状态码

    private String ecppOrderTrackNo;//ecpp物流number
    
    private String shippingName;//ecpp物流name

	public String getEcppOrderStatusCode() {
		return ecppOrderStatusCode;
	}

	public void setEcppOrderStatusCode(String ecppOrderStatusCode) {
		this.ecppOrderStatusCode = ecppOrderStatusCode;
	}

	public String getEcppOrderTrackNo() {
		return ecppOrderTrackNo;
	}

	public void setEcppOrderTrackNo(String ecppOrderTrackNo) {
		this.ecppOrderTrackNo = ecppOrderTrackNo;
	}

	public String getShippingName() {
		return shippingName;
	}

	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}

	public EcppTrackItem() {
		super();
	}

	public EcppTrackItem(String ecppOrderStatusCode, String ecppOrderTrackNo, String shippingName) {
		super();
		this.ecppOrderStatusCode = ecppOrderStatusCode;
		this.ecppOrderTrackNo = ecppOrderTrackNo;
		this.shippingName = shippingName;
	}

	@Override
	public String toString() {
		return "EcppTrackItem [ecppOrderStatusCode=" + ecppOrderStatusCode + ", ecppOrderTrackNo=" + ecppOrderTrackNo
				+ ", shippingName=" + shippingName + "]";
	}
	
}
