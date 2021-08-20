package com.atguigu.bean;

import java.math.BigDecimal;

public class MlfrontCouponLuckyDraw {
    private Integer couponluckydrawId;

    private String couponluckydrawSessionid;

    private Integer couponluckydrawUserid;

    private String couponluckydrawUseremail;

    private Integer couponluckydrawCouponid;

    private String couponluckydrawCouponname;

    private BigDecimal couponluckydrawCouponprice;

    private BigDecimal couponluckydrawCouponpriceoff;

    private BigDecimal couponluckydrawCouponpricebaseline;

    private String couponluckydrawCouponcode;

    private String couponluckydrawCoupontype;

    private String couponluckydrawCreatetime;


	public Integer getCouponluckydrawId() {
        return couponluckydrawId;
    }

    public void setCouponluckydrawId(Integer couponluckydrawId) {
        this.couponluckydrawId = couponluckydrawId;
    }

    public String getCouponluckydrawSessionid() {
        return couponluckydrawSessionid;
    }

    public void setCouponluckydrawSessionid(String couponluckydrawSessionid) {
        this.couponluckydrawSessionid = couponluckydrawSessionid;
    }

    public Integer getCouponluckydrawUserid() {
        return couponluckydrawUserid;
    }

    public void setCouponluckydrawUserid(Integer couponluckydrawUserid) {
        this.couponluckydrawUserid = couponluckydrawUserid;
    }

    public String getCouponluckydrawUseremail() {
        return couponluckydrawUseremail;
    }

    public void setCouponluckydrawUseremail(String couponluckydrawUseremail) {
        this.couponluckydrawUseremail = couponluckydrawUseremail == null ? null : couponluckydrawUseremail.trim();
    }

    public Integer getCouponluckydrawCouponid() {
        return couponluckydrawCouponid;
    }

    public void setCouponluckydrawCouponid(Integer couponluckydrawCouponid) {
        this.couponluckydrawCouponid = couponluckydrawCouponid;
    }

    public String getCouponluckydrawCouponname() {
        return couponluckydrawCouponname;
    }

    public void setCouponluckydrawCouponname(String couponluckydrawCouponname) {
        this.couponluckydrawCouponname = couponluckydrawCouponname == null ? null : couponluckydrawCouponname.trim();
    }

    public BigDecimal getCouponluckydrawCouponprice() {
        return couponluckydrawCouponprice;
    }

    public void setCouponluckydrawCouponprice(BigDecimal couponluckydrawCouponprice) {
        this.couponluckydrawCouponprice = couponluckydrawCouponprice;
    }

    public BigDecimal getCouponluckydrawCouponpriceoff() {
        return couponluckydrawCouponpriceoff;
    }

    public void setCouponluckydrawCouponpriceoff(BigDecimal couponluckydrawCouponpriceoff) {
        this.couponluckydrawCouponpriceoff = couponluckydrawCouponpriceoff;
    }

    public BigDecimal getCouponluckydrawCouponpricebaseline() {
        return couponluckydrawCouponpricebaseline;
    }

    public void setCouponluckydrawCouponpricebaseline(BigDecimal couponluckydrawCouponpricebaseline) {
        this.couponluckydrawCouponpricebaseline = couponluckydrawCouponpricebaseline;
    }

    public String getCouponluckydrawCouponcode() {
        return couponluckydrawCouponcode;
    }

    public void setCouponluckydrawCouponcode(String couponluckydrawCouponcode) {
        this.couponluckydrawCouponcode = couponluckydrawCouponcode == null ? null : couponluckydrawCouponcode.trim();
    }

    public String getCouponluckydrawCoupontype() {
        return couponluckydrawCoupontype;
    }

    public void setCouponluckydrawCoupontype(String couponluckydrawCoupontype) {
        this.couponluckydrawCoupontype = couponluckydrawCoupontype == null ? null : couponluckydrawCoupontype.trim();
    }

    public String getCouponluckydrawCreatetime() {
        return couponluckydrawCreatetime;
    }

    public void setCouponluckydrawCreatetime(String couponluckydrawCreatetime) {
        this.couponluckydrawCreatetime = couponluckydrawCreatetime == null ? null : couponluckydrawCreatetime.trim();
    }
    
    
    public MlfrontCouponLuckyDraw() {
		super();
	}

	public MlfrontCouponLuckyDraw(Integer couponluckydrawId, String couponluckydrawSessionid,
			Integer couponluckydrawUserid, String couponluckydrawUseremail, Integer couponluckydrawCouponid,
			String couponluckydrawCouponname, BigDecimal couponluckydrawCouponprice, BigDecimal couponluckydrawCouponpriceoff,
			BigDecimal couponluckydrawCouponpricebaseline, String couponluckydrawCouponcode,
			String couponluckydrawCoupontype, String couponluckydrawCreatetime) {
		super();
		this.couponluckydrawId = couponluckydrawId;
		this.couponluckydrawSessionid = couponluckydrawSessionid;
		this.couponluckydrawUserid = couponluckydrawUserid;
		this.couponluckydrawUseremail = couponluckydrawUseremail;
		this.couponluckydrawCouponid = couponluckydrawCouponid;
		this.couponluckydrawCouponname = couponluckydrawCouponname;
		this.couponluckydrawCouponprice = couponluckydrawCouponprice;
		this.couponluckydrawCouponpriceoff = couponluckydrawCouponpriceoff;
		this.couponluckydrawCouponpricebaseline = couponluckydrawCouponpricebaseline;
		this.couponluckydrawCouponcode = couponluckydrawCouponcode;
		this.couponluckydrawCoupontype = couponluckydrawCoupontype;
		this.couponluckydrawCreatetime = couponluckydrawCreatetime;
	}

	@Override
	public String toString() {
		return "MlfrontCouponLuckyDraw [couponluckydrawId=" + couponluckydrawId + ", couponluckydrawSessionid="
				+ couponluckydrawSessionid + ", couponluckydrawUserid=" + couponluckydrawUserid
				+ ", couponluckydrawUseremail=" + couponluckydrawUseremail + ", couponluckydrawCouponid="
				+ couponluckydrawCouponid + ", couponluckydrawCouponname=" + couponluckydrawCouponname
				+ ", couponluckydrawCouponprice=" + couponluckydrawCouponprice + ", couponluckydrawCouponpriceoff="
				+ couponluckydrawCouponpriceoff + ", couponluckydrawCouponpricebaseline="
				+ couponluckydrawCouponpricebaseline + ", couponluckydrawCouponcode=" + couponluckydrawCouponcode
				+ ", couponluckydrawCoupontype=" + couponluckydrawCoupontype + ", couponluckydrawCreatetime="
				+ couponluckydrawCreatetime + "]";
	}
	
}