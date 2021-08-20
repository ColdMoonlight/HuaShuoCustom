package com.atguigu.bean;

import java.math.BigDecimal;

public class MlfrontOrder {
    private Integer orderId;

    private Integer orderUid;

    private String orderIp;

    private String orderOrderitemidstr;

    private BigDecimal orderMoney;

    private Integer orderCouponId;

    private String orderCouponCode;

    private BigDecimal orderCouponPrice;

    private Integer orderAddressinfoId;

    private String orderAddressinfoUname;

    private String orderAddressinfoUemail;

    private String orderPronumStr;

    private Integer orderPayPlate;

    private String orderPayPlatestr;

    private String orderBuyMess;

    private Integer orderStatus;

    private String orderCreatetime;

    private String orderMotifytime;

    private Integer orderLogisticsid;

    private String orderLogisticsname;

    private String orderLogisticsnumber;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderUid() {
        return orderUid;
    }

    public void setOrderUid(Integer orderUid) {
        this.orderUid = orderUid;
    }

    public String getOrderIp() {
        return orderIp;
    }

    public void setOrderIp(String orderIp) {
        this.orderIp = orderIp == null ? null : orderIp.trim();
    }

    public String getOrderOrderitemidstr() {
        return orderOrderitemidstr;
    }

    public void setOrderOrderitemidstr(String orderOrderitemidstr) {
        this.orderOrderitemidstr = orderOrderitemidstr == null ? null : orderOrderitemidstr.trim();
    }

    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Integer getOrderCouponId() {
        return orderCouponId;
    }

    public void setOrderCouponId(Integer orderCouponId) {
        this.orderCouponId = orderCouponId;
    }

    public String getOrderCouponCode() {
        return orderCouponCode;
    }

    public void setOrderCouponCode(String orderCouponCode) {
        this.orderCouponCode = orderCouponCode == null ? null : orderCouponCode.trim();
    }

    public BigDecimal getOrderCouponPrice() {
        return orderCouponPrice;
    }

    public void setOrderCouponPrice(BigDecimal orderCouponPrice) {
        this.orderCouponPrice = orderCouponPrice;
    }

    public Integer getOrderAddressinfoId() {
        return orderAddressinfoId;
    }

    public void setOrderAddressinfoId(Integer orderAddressinfoId) {
        this.orderAddressinfoId = orderAddressinfoId;
    }

    public String getOrderAddressinfoUname() {
        return orderAddressinfoUname;
    }

    public void setOrderAddressinfoUname(String orderAddressinfoUname) {
        this.orderAddressinfoUname = orderAddressinfoUname == null ? null : orderAddressinfoUname.trim();
    }

    public String getOrderAddressinfoUemail() {
        return orderAddressinfoUemail;
    }

    public void setOrderAddressinfoUemail(String orderAddressinfoUemail) {
        this.orderAddressinfoUemail = orderAddressinfoUemail == null ? null : orderAddressinfoUemail.trim();
    }

    public String getOrderPronumStr() {
        return orderPronumStr;
    }

    public void setOrderPronumStr(String orderPronumStr) {
        this.orderPronumStr = orderPronumStr == null ? null : orderPronumStr.trim();
    }

    public Integer getOrderPayPlate() {
        return orderPayPlate;
    }

    public void setOrderPayPlate(Integer orderPayPlate) {
        this.orderPayPlate = orderPayPlate;
    }

    public String getOrderPayPlatestr() {
        return orderPayPlatestr;
    }

    public void setOrderPayPlatestr(String orderPayPlatestr) {
        this.orderPayPlatestr = orderPayPlatestr == null ? null : orderPayPlatestr.trim();
    }

    public String getOrderBuyMess() {
        return orderBuyMess;
    }

    public void setOrderBuyMess(String orderBuyMess) {
        this.orderBuyMess = orderBuyMess == null ? null : orderBuyMess.trim();
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderCreatetime() {
        return orderCreatetime;
    }

    public void setOrderCreatetime(String orderCreatetime) {
        this.orderCreatetime = orderCreatetime == null ? null : orderCreatetime.trim();
    }

    public String getOrderMotifytime() {
        return orderMotifytime;
    }

    public void setOrderMotifytime(String orderMotifytime) {
        this.orderMotifytime = orderMotifytime == null ? null : orderMotifytime.trim();
    }

    public Integer getOrderLogisticsid() {
        return orderLogisticsid;
    }

    public void setOrderLogisticsid(Integer orderLogisticsid) {
        this.orderLogisticsid = orderLogisticsid;
    }

    public String getOrderLogisticsname() {
        return orderLogisticsname;
    }

    public void setOrderLogisticsname(String orderLogisticsname) {
        this.orderLogisticsname = orderLogisticsname == null ? null : orderLogisticsname.trim();
    }

    public String getOrderLogisticsnumber() {
        return orderLogisticsnumber;
    }

    public void setOrderLogisticsnumber(String orderLogisticsnumber) {
        this.orderLogisticsnumber = orderLogisticsnumber == null ? null : orderLogisticsnumber.trim();
    }

	public MlfrontOrder() {
		super();
	}

	public MlfrontOrder(Integer orderId, Integer orderUid, String orderIp, String orderOrderitemidstr,
			BigDecimal orderMoney, Integer orderCouponId, String orderCouponCode, BigDecimal orderCouponPrice,
			Integer orderAddressinfoId, String orderAddressinfoUname, String orderAddressinfoUemail,
			String orderPronumStr, Integer orderPayPlate, String orderPayPlatestr, String orderBuyMess,
			Integer orderStatus, String orderCreatetime, String orderMotifytime, Integer orderLogisticsid,
			String orderLogisticsname, String orderLogisticsnumber) {
		super();
		this.orderId = orderId;
		this.orderUid = orderUid;
		this.orderIp = orderIp;
		this.orderOrderitemidstr = orderOrderitemidstr;
		this.orderMoney = orderMoney;
		this.orderCouponId = orderCouponId;
		this.orderCouponCode = orderCouponCode;
		this.orderCouponPrice = orderCouponPrice;
		this.orderAddressinfoId = orderAddressinfoId;
		this.orderAddressinfoUname = orderAddressinfoUname;
		this.orderAddressinfoUemail = orderAddressinfoUemail;
		this.orderPronumStr = orderPronumStr;
		this.orderPayPlate = orderPayPlate;
		this.orderPayPlatestr = orderPayPlatestr;
		this.orderBuyMess = orderBuyMess;
		this.orderStatus = orderStatus;
		this.orderCreatetime = orderCreatetime;
		this.orderMotifytime = orderMotifytime;
		this.orderLogisticsid = orderLogisticsid;
		this.orderLogisticsname = orderLogisticsname;
		this.orderLogisticsnumber = orderLogisticsnumber;
	}

	@Override
	public String toString() {
		return "MlfrontOrder [orderId=" + orderId + ", orderUid=" + orderUid + ", orderIp=" + orderIp
				+ ", orderOrderitemidstr=" + orderOrderitemidstr + ", orderMoney=" + orderMoney + ", orderCouponId="
				+ orderCouponId + ", orderCouponCode=" + orderCouponCode + ", orderCouponPrice=" + orderCouponPrice
				+ ", orderAddressinfoId=" + orderAddressinfoId + ", orderAddressinfoUname=" + orderAddressinfoUname
				+ ", orderAddressinfoUemail=" + orderAddressinfoUemail + ", orderPronumStr=" + orderPronumStr
				+ ", orderPayPlate=" + orderPayPlate + ", orderPayPlatestr=" + orderPayPlatestr + ", orderBuyMess="
				+ orderBuyMess + ", orderStatus=" + orderStatus + ", orderCreatetime=" + orderCreatetime
				+ ", orderMotifytime=" + orderMotifytime + ", orderLogisticsid=" + orderLogisticsid
				+ ", orderLogisticsname=" + orderLogisticsname + ", orderLogisticsnumber=" + orderLogisticsnumber + "]";
	}
    
}