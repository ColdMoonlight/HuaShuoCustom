package com.atguigu.bean;

import java.math.BigDecimal;

public class CouponAnalysisDate {
	
	/**
	 * SELECT 
	tbpay.payInfo_id,tbpay.payInfo_oid,tbpay.payInfo_money,tbpay.payInfo_status,tbpay.payInfo_createTime, 
	tborder.order_id ,tborder.order_addressinfo_id,
	tborder.order_coupon_code,
	tbpayadd.shippingAddress_id,tbpayadd.shippingAddress_email,tbpayadd.shippingAddress_recipient_name 
	FROM 
	tb_mlfront_payinfo as tbpay,
	tb_mlfront_order as tborder,
	tb_paypal_shippingaddress as tbpayadd 
	WHERE tbpay.payInfo_createTime BETWEEN #{payinfoCreatetime,jdbcType=VARCHAR} and #{payinfoMotifytime,jdbcType=VARCHAR} 
	AND (tbpay.payInfo_status !=0)
	<if test="SearchCouponCode != null">
     AND tborder.order_coupon_code = #{SearchCouponCode,jdbcType=VARCHAR} 
    </if>
	 AND tbpay.payInfo_oid = tborder.order_id AND tbpay.payInfo_id = tbpayadd.shippingAddress_payInfoid 
	 ORDER BY tbpay.payInfo_id DESC
	 * */
	private Integer payinfoId;
	private Integer payinfoOid;
	private BigDecimal payinfoMoney;
	private Integer payinfoStatus;
	private String payinfoCreatetime;
	private String payinfoMotifytime;
	private String SearchCouponCode;
	private MlfrontOrder mlfrontOrder;
	private MlPaypalShipAddress mlPaypalShipAddress;
	public Integer getPayinfoId() {
		return payinfoId;
	}
	public void setPayinfoId(Integer payinfoId) {
		this.payinfoId = payinfoId;
	}
	public Integer getPayinfoOid() {
		return payinfoOid;
	}
	public void setPayinfoOid(Integer payinfoOid) {
		this.payinfoOid = payinfoOid;
	}
	public BigDecimal getPayinfoMoney() {
		return payinfoMoney;
	}
	public void setPayinfoMoney(BigDecimal payinfoMoney) {
		this.payinfoMoney = payinfoMoney;
	}
	public Integer getPayinfoStatus() {
		return payinfoStatus;
	}
	public void setPayinfoStatus(Integer payinfoStatus) {
		this.payinfoStatus = payinfoStatus;
	}
	public String getPayinfoCreatetime() {
		return payinfoCreatetime;
	}
	public void setPayinfoCreatetime(String payinfoCreatetime) {
		this.payinfoCreatetime = payinfoCreatetime;
	}
	public String getPayinfoMotifytime() {
		return payinfoMotifytime;
	}
	public void setPayinfoMotifytime(String payinfoMotifytime) {
		this.payinfoMotifytime = payinfoMotifytime;
	}
	public String getSearchCouponCode() {
		return SearchCouponCode;
	}
	public void setSearchCouponCode(String searchCouponCode) {
		SearchCouponCode = searchCouponCode;
	}
	public MlfrontOrder getMlfrontOrder() {
		return mlfrontOrder;
	}
	public void setMlfrontOrder(MlfrontOrder mlfrontOrder) {
		this.mlfrontOrder = mlfrontOrder;
	}
	public MlPaypalShipAddress getMlPaypalShipAddress() {
		return mlPaypalShipAddress;
	}
	public void setMlPaypalShipAddress(MlPaypalShipAddress mlPaypalShipAddress) {
		this.mlPaypalShipAddress = mlPaypalShipAddress;
	}
	
	public CouponAnalysisDate() {
		super();
	}
	public CouponAnalysisDate(Integer payinfoId, Integer payinfoOid, BigDecimal payinfoMoney, Integer payinfoStatus,
			String payinfoCreatetime, String payinfoMotifytime, String searchCouponCode, MlfrontOrder mlfrontOrder,
			MlPaypalShipAddress mlPaypalShipAddress) {
		super();
		this.payinfoId = payinfoId;
		this.payinfoOid = payinfoOid;
		this.payinfoMoney = payinfoMoney;
		this.payinfoStatus = payinfoStatus;
		this.payinfoCreatetime = payinfoCreatetime;
		this.payinfoMotifytime = payinfoMotifytime;
		SearchCouponCode = searchCouponCode;
		this.mlfrontOrder = mlfrontOrder;
		this.mlPaypalShipAddress = mlPaypalShipAddress;
	}
	@Override
	public String toString() {
		return "CouponAnalysisDate [payinfoId=" + payinfoId + ", payinfoOid=" + payinfoOid + ", payinfoMoney="
				+ payinfoMoney + ", payinfoStatus=" + payinfoStatus + ", payinfoCreatetime=" + payinfoCreatetime
				+ ", payinfoMotifytime=" + payinfoMotifytime + ", SearchCouponCode=" + SearchCouponCode
				+ ", mlfrontOrder=" + mlfrontOrder + ", mlPaypalShipAddress=" + mlPaypalShipAddress + "]";
	}
	
}
