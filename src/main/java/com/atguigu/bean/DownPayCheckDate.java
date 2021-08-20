package com.atguigu.bean;

import java.math.BigDecimal;

public class DownPayCheckDate {
	
	/**
	 * SELECT 
tbpayinfo.payInfo_id,tbpayinfo.payInfo_oid,tbpayinfo.payInfo_money,tbpayinfo.payInfo_status,tbpayinfo.payInfo_createTime, 
tborder.order_id ,tborder.order_orderItemIdStr,
tbaddre.address_email,tbaddre.address_telephone,tbaddre.address_userFirstName,tbaddre.address_userLastName,
tbordItem.orderItem_id,tbordItem.order_id,tbordItem.orderItem_pSeo,tbordItem.orderItem_pSku_name,tbordItem.orderItem_product_originalPrice, tbordItem.orderItem_pSku_moneyStr, tbordItem.orderItem_product_accoff
FROM tb_mlfront_payinfo as tbpayinfo,tb_mlfront_order as tborder,tb_mlfront_orderitem as tbordItem ,tb_mlfront_address as tbaddre  
WHERE tbpayinfo.payInfo_status = 0 AND tbpayinfo.payInfo_oid = tborder.order_id AND tborder.order_id = tbordItem.order_id AND tborder.order_addressinfo_id=tbaddre.address_id
ORDER BY tbpayinfo.payInfo_id
	 * 
	 * */
	
	private Integer payinfoId;
	private Integer payinfoOid;
	private BigDecimal payinfoMoney;
	private Integer payinfoStatus;
	private String payinfoCreatetime;
	private String payinfoMotifytime;
	private MlfrontOrder mlfrontOrder;
	private MlfrontOrderItem mlfrontOrderItem;
	private MlfrontAddress mlfrontAddress;
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
	public MlfrontOrder getMlfrontOrder() {
		return mlfrontOrder;
	}
	public void setMlfrontOrder(MlfrontOrder mlfrontOrder) {
		this.mlfrontOrder = mlfrontOrder;
	}
	public MlfrontOrderItem getMlfrontOrderItem() {
		return mlfrontOrderItem;
	}
	public void setMlfrontOrderItem(MlfrontOrderItem mlfrontOrderItem) {
		this.mlfrontOrderItem = mlfrontOrderItem;
	}
	public MlfrontAddress getMlfrontAddress() {
		return mlfrontAddress;
	}
	public void setMlfrontAddress(MlfrontAddress mlfrontAddress) {
		this.mlfrontAddress = mlfrontAddress;
	}
	public DownPayCheckDate() {
		super();
	}
	public DownPayCheckDate(Integer payinfoId, Integer payinfoOid, BigDecimal payinfoMoney, Integer payinfoStatus,
			String payinfoCreatetime, String payinfoMotifytime, MlfrontOrder mlfrontOrder,
			MlfrontOrderItem mlfrontOrderItem, MlfrontAddress mlfrontAddress) {
		super();
		this.payinfoId = payinfoId;
		this.payinfoOid = payinfoOid;
		this.payinfoMoney = payinfoMoney;
		this.payinfoStatus = payinfoStatus;
		this.payinfoCreatetime = payinfoCreatetime;
		this.payinfoMotifytime = payinfoMotifytime;
		this.mlfrontOrder = mlfrontOrder;
		this.mlfrontOrderItem = mlfrontOrderItem;
		this.mlfrontAddress = mlfrontAddress;
	}
	@Override
	public String toString() {
		return "DownPayIFDate [payinfoId=" + payinfoId + ", payinfoOid=" + payinfoOid + ", payinfoMoney=" + payinfoMoney
				+ ", payinfoStatus=" + payinfoStatus + ", payinfoCreatetime=" + payinfoCreatetime
				+ ", payinfoMotifytime=" + payinfoMotifytime + ", mlfrontOrder=" + mlfrontOrder + ", mlfrontOrderItem="
				+ mlfrontOrderItem + ", mlfrontAddress=" + mlfrontAddress + "]";
	}
	
}
