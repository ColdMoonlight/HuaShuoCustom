package com.atguigu.bean.portal;

import java.math.BigDecimal;

public class ToPaypalInfo {
	
//    888.00, 
//    "USD", 
//    PaypalPaymentMethod.paypal, 
//    PaypalPaymentIntent.sale,
//    "payment description", 
//    cancelUrl, 
//    successUrl
	private BigDecimal MoneyNum;
	private String MoneyType;
	private String paypalMethod;
	private String saleNumIntent;
	private String paymentDescription;
	private String payCancelUrl;
	private String paySuccessUrl;
	public BigDecimal getMoneyNum() {
		return MoneyNum;
	}
	public void setMoneyNum(BigDecimal moneyNum) {
		MoneyNum = moneyNum;
	}
	public String getMoneyType() {
		return MoneyType;
	}
	public void setMoneyType(String moneyType) {
		MoneyType = moneyType;
	}
	public String getPaypalMethod() {
		return paypalMethod;
	}
	public void setPaypalMethod(String paypalMethod) {
		this.paypalMethod = paypalMethod;
	}
	public String getSaleNumIntent() {
		return saleNumIntent;
	}
	public void setSaleNumIntent(String saleNumIntent) {
		this.saleNumIntent = saleNumIntent;
	}
	public String getPaymentDescription() {
		return paymentDescription;
	}
	public void setPaymentDescription(String paymentDescription) {
		this.paymentDescription = paymentDescription;
	}
	public String getPayCancelUrl() {
		return payCancelUrl;
	}
	public void setPayCancelUrl(String payCancelUrl) {
		this.payCancelUrl = payCancelUrl;
	}
	public String getPaySuccessUrl() {
		return paySuccessUrl;
	}
	public void setPaySuccessUrl(String paySuccessUrl) {
		this.paySuccessUrl = paySuccessUrl;
	}
	public ToPaypalInfo(BigDecimal moneyNum, String moneyType, String paypalMethod, String saleNumIntent,
			String paymentDescription, String payCancelUrl, String paySuccessUrl) {
		super();
		MoneyNum = moneyNum;
		MoneyType = moneyType;
		this.paypalMethod = paypalMethod;
		this.saleNumIntent = saleNumIntent;
		this.paymentDescription = paymentDescription;
		this.payCancelUrl = payCancelUrl;
		this.paySuccessUrl = paySuccessUrl;
	}
	public ToPaypalInfo() {
		super();
	}
	@Override
	public String toString() {
		return "ToPaypalInfo [MoneyNum=" + MoneyNum + ", MoneyType=" + MoneyType + ", paypalMethod=" + paypalMethod
				+ ", saleNumIntent=" + saleNumIntent + ", paymentDescription=" + paymentDescription + ", payCancelUrl="
				+ payCancelUrl + ", paySuccessUrl=" + paySuccessUrl + "]";
	}

}
