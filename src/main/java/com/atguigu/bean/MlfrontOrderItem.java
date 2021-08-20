package com.atguigu.bean;

import java.math.BigDecimal;

public class MlfrontOrderItem {
	
    private Integer orderitemId;

    private Integer orderId;

    private Integer orderUid;

    private Integer orderCartitemid;

    private Integer orderitemPid;

    private String orderitemPname;

    private String orderitemPseo;

    private BigDecimal orderitemProductOriginalprice;

    private String orderitemProductMainimgurl;

    private Integer orderitemProductAccoff;

    private String orderitemPskuIdstr;

    private String orderitemPskuIdnamestr;

    private Integer orderitemPskuId;

    private String orderitemPskuName;

    private String orderitemPskuCode;

    private String orderitemPskuMoneystr;

    private Integer orderitemPskuNumber;

    private String orderitemPskuReamoney;

    private String orderitemCreatetime;

    private String orderitemMotifytime;

    public Integer getOrderitemId() {
        return orderitemId;
    }

    public void setOrderitemId(Integer orderitemId) {
        this.orderitemId = orderitemId;
    }

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

    public Integer getOrderCartitemid() {
        return orderCartitemid;
    }

    public void setOrderCartitemid(Integer orderCartitemid) {
        this.orderCartitemid = orderCartitemid;
    }

    public Integer getOrderitemPid() {
        return orderitemPid;
    }

    public void setOrderitemPid(Integer orderitemPid) {
        this.orderitemPid = orderitemPid;
    }

    public String getOrderitemPname() {
        return orderitemPname;
    }

    public void setOrderitemPname(String orderitemPname) {
        this.orderitemPname = orderitemPname == null ? null : orderitemPname.trim();
    }

    public String getOrderitemPseo() {
        return orderitemPseo;
    }

    public void setOrderitemPseo(String orderitemPseo) {
        this.orderitemPseo = orderitemPseo == null ? null : orderitemPseo.trim();
    }

    public BigDecimal getOrderitemProductOriginalprice() {
        return orderitemProductOriginalprice;
    }

    public void setOrderitemProductOriginalprice(BigDecimal orderitemProductOriginalprice) {
        this.orderitemProductOriginalprice = orderitemProductOriginalprice;
    }

    public String getOrderitemProductMainimgurl() {
        return orderitemProductMainimgurl;
    }

    public void setOrderitemProductMainimgurl(String orderitemProductMainimgurl) {
        this.orderitemProductMainimgurl = orderitemProductMainimgurl == null ? null : orderitemProductMainimgurl.trim();
    }

    public Integer getOrderitemProductAccoff() {
        return orderitemProductAccoff;
    }

    public void setOrderitemProductAccoff(Integer orderitemProductAccoff) {
        this.orderitemProductAccoff = orderitemProductAccoff;
    }

    public String getOrderitemPskuIdstr() {
        return orderitemPskuIdstr;
    }

    public void setOrderitemPskuIdstr(String orderitemPskuIdstr) {
        this.orderitemPskuIdstr = orderitemPskuIdstr == null ? null : orderitemPskuIdstr.trim();
    }

    public String getOrderitemPskuIdnamestr() {
        return orderitemPskuIdnamestr;
    }

    public void setOrderitemPskuIdnamestr(String orderitemPskuIdnamestr) {
        this.orderitemPskuIdnamestr = orderitemPskuIdnamestr == null ? null : orderitemPskuIdnamestr.trim();
    }

    public Integer getOrderitemPskuId() {
		return orderitemPskuId;
	}

	public void setOrderitemPskuId(Integer orderitemPskuId) {
		this.orderitemPskuId = orderitemPskuId;
	}

	public String getOrderitemPskuName() {
        return orderitemPskuName;
    }

    public void setOrderitemPskuName(String orderitemPskuName) {
        this.orderitemPskuName = orderitemPskuName == null ? null : orderitemPskuName.trim();
    }

    public String getOrderitemPskuCode() {
        return orderitemPskuCode;
    }

    public void setOrderitemPskuCode(String orderitemPskuCode) {
        this.orderitemPskuCode = orderitemPskuCode == null ? null : orderitemPskuCode.trim();
    }

    public String getOrderitemPskuMoneystr() {
        return orderitemPskuMoneystr;
    }

    public void setOrderitemPskuMoneystr(String orderitemPskuMoneystr) {
        this.orderitemPskuMoneystr = orderitemPskuMoneystr == null ? null : orderitemPskuMoneystr.trim();
    }

    public Integer getOrderitemPskuNumber() {
        return orderitemPskuNumber;
    }

    public void setOrderitemPskuNumber(Integer orderitemPskuNumber) {
        this.orderitemPskuNumber = orderitemPskuNumber;
    }

    public String getOrderitemPskuReamoney() {
        return orderitemPskuReamoney;
    }

    public void setOrderitemPskuReamoney(String orderitemPskuReamoney) {
        this.orderitemPskuReamoney = orderitemPskuReamoney == null ? null : orderitemPskuReamoney.trim();
    }

    public String getOrderitemCreatetime() {
        return orderitemCreatetime;
    }

    public void setOrderitemCreatetime(String orderitemCreatetime) {
        this.orderitemCreatetime = orderitemCreatetime == null ? null : orderitemCreatetime.trim();
    }

    public String getOrderitemMotifytime() {
        return orderitemMotifytime;
    }

    public void setOrderitemMotifytime(String orderitemMotifytime) {
        this.orderitemMotifytime = orderitemMotifytime == null ? null : orderitemMotifytime.trim();
    }

	public MlfrontOrderItem() {
		super();
	}

	public MlfrontOrderItem(Integer orderitemId, Integer orderId, Integer orderUid, Integer orderCartitemid,
			Integer orderitemPid, String orderitemPname, String orderitemPseo, BigDecimal orderitemProductOriginalprice,
			String orderitemProductMainimgurl, Integer orderitemProductAccoff, String orderitemPskuIdstr,
			String orderitemPskuIdnamestr, Integer orderitemPskuId, String orderitemPskuName, String orderitemPskuCode,
			String orderitemPskuMoneystr, Integer orderitemPskuNumber, String orderitemPskuReamoney,
			String orderitemCreatetime, String orderitemMotifytime) {
		super();
		this.orderitemId = orderitemId;
		this.orderId = orderId;
		this.orderUid = orderUid;
		this.orderCartitemid = orderCartitemid;
		this.orderitemPid = orderitemPid;
		this.orderitemPname = orderitemPname;
		this.orderitemPseo = orderitemPseo;
		this.orderitemProductOriginalprice = orderitemProductOriginalprice;
		this.orderitemProductMainimgurl = orderitemProductMainimgurl;
		this.orderitemProductAccoff = orderitemProductAccoff;
		this.orderitemPskuIdstr = orderitemPskuIdstr;
		this.orderitemPskuIdnamestr = orderitemPskuIdnamestr;
		this.orderitemPskuId = orderitemPskuId;
		this.orderitemPskuName = orderitemPskuName;
		this.orderitemPskuCode = orderitemPskuCode;
		this.orderitemPskuMoneystr = orderitemPskuMoneystr;
		this.orderitemPskuNumber = orderitemPskuNumber;
		this.orderitemPskuReamoney = orderitemPskuReamoney;
		this.orderitemCreatetime = orderitemCreatetime;
		this.orderitemMotifytime = orderitemMotifytime;
	}

	@Override
	public String toString() {
		return "MlfrontOrderItem [orderitemId=" + orderitemId + ", orderId=" + orderId + ", orderUid=" + orderUid
				+ ", orderCartitemid=" + orderCartitemid + ", orderitemPid=" + orderitemPid + ", orderitemPname="
				+ orderitemPname + ", orderitemPseo=" + orderitemPseo + ", orderitemProductOriginalprice="
				+ orderitemProductOriginalprice + ", orderitemProductMainimgurl=" + orderitemProductMainimgurl
				+ ", orderitemProductAccoff=" + orderitemProductAccoff + ", orderitemPskuIdstr=" + orderitemPskuIdstr
				+ ", orderitemPskuIdnamestr=" + orderitemPskuIdnamestr + ", orderitemPskuId=" + orderitemPskuId
				+ ", orderitemPskuName=" + orderitemPskuName + ", orderitemPskuCode=" + orderitemPskuCode
				+ ", orderitemPskuMoneystr=" + orderitemPskuMoneystr + ", orderitemPskuNumber=" + orderitemPskuNumber
				+ ", orderitemPskuReamoney=" + orderitemPskuReamoney + ", orderitemCreatetime=" + orderitemCreatetime
				+ ", orderitemMotifytime=" + orderitemMotifytime + "]";
	}

}