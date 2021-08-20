package com.atguigu.vo;

import java.math.BigDecimal;
import java.util.List;

public class order {
	
    private String userid;//买家ID
    private String paid_time;//交易时间:Y-m-d H:i:s	默认为当前日期
    private String email;//买家邮箱
    private String paypalid;//交易号/订单号	唯一
    private String sellsrecord;//订单号/流水号
    private BigDecimal order_amount;//订单金额	两位小数
    private String currency;//币种	三字代码，例：USD
    private Integer shipping_id;//发货方式	参见字典查询接口 name为shipping
    private Integer Sales_channels;//销售渠道	参见字典查询接口		name为Sales_channels
    private Integer Sales_account_id;//渠道账号	参见渠道账号查询列表
    private Integer enableStockControl;//库存控制		0 默认 //1锁定扣除OOO//2锁定PPP

    private String consignee;//*收件人
    private String street1;//*地址1
    private String street2;//地址2
    private String city;//*城市
    private String state;//*省
    
    private String country;//*国家
    private String CountryCode;//*国家二字代码
    private String tel;//*电话
    private String zipcode;//*邮编
	
	private List<ecppItem> item;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPaid_time() {
		return paid_time;
	}

	public void setPaid_time(String paid_time) {
		this.paid_time = paid_time;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPaypalid() {
		return paypalid;
	}

	public void setPaypalid(String paypalid) {
		this.paypalid = paypalid;
	}

	public String getSellsrecord() {
		return sellsrecord;
	}

	public void setSellsrecord(String sellsrecord) {
		this.sellsrecord = sellsrecord;
	}

	public BigDecimal getOrder_amount() {
		return order_amount;
	}

	public void setOrder_amount(BigDecimal order_amount) {
		this.order_amount = order_amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getShipping_id() {
		return shipping_id;
	}

	public void setShipping_id(Integer shipping_id) {
		this.shipping_id = shipping_id;
	}

	public Integer getSales_channels() {
		return Sales_channels;
	}

	public void setSales_channels(Integer sales_channels) {
		Sales_channels = sales_channels;
	}

	public Integer getSales_account_id() {
		return Sales_account_id;
	}

	public void setSales_account_id(Integer sales_account_id) {
		Sales_account_id = sales_account_id;
	}

	public Integer getEnableStockControl() {
		return enableStockControl;
	}

	public void setEnableStockControl(Integer enableStockControl) {
		this.enableStockControl = enableStockControl;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryCode() {
		return CountryCode;
	}

	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public List<ecppItem> getItem() {
		return item;
	}

	public void setItem(List<ecppItem> item) {
		this.item = item;
	}

	public order() {
		super();
	}

	public order(String userid, String paid_time, String email, String paypalid, String sellsrecord,
			BigDecimal order_amount, String currency, Integer shipping_id, Integer sales_channels,
			Integer sales_account_id, Integer enableStockControl, String consignee, String street1, String street2,
			String city, String state, String country, String countryCode, String tel, String zipcode,
			List<ecppItem> item) {
		super();
		this.userid = userid;
		this.paid_time = paid_time;
		this.email = email;
		this.paypalid = paypalid;
		this.sellsrecord = sellsrecord;
		this.order_amount = order_amount;
		this.currency = currency;
		this.shipping_id = shipping_id;
		Sales_channels = sales_channels;
		Sales_account_id = sales_account_id;
		this.enableStockControl = enableStockControl;
		this.consignee = consignee;
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.state = state;
		this.country = country;
		CountryCode = countryCode;
		this.tel = tel;
		this.zipcode = zipcode;
		this.item = item;
	}

	@Override
	public String toString() {
		return "order [userid=" + userid + ", paid_time=" + paid_time + ", email=" + email + ", paypalid=" + paypalid
				+ ", sellsrecord=" + sellsrecord + ", order_amount=" + order_amount + ", currency=" + currency
				+ ", shipping_id=" + shipping_id + ", Sales_channels=" + Sales_channels + ", Sales_account_id="
				+ Sales_account_id + ", enableStockControl=" + enableStockControl + ", consignee=" + consignee
				+ ", street1=" + street1 + ", street2=" + street2 + ", city=" + city + ", state=" + state + ", country="
				+ country + ", CountryCode=" + CountryCode + ", tel=" + tel + ", zipcode=" + zipcode + ", item=" + item
				+ "]";
	}

}
