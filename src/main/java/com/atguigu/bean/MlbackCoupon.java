package com.atguigu.bean;

import java.math.BigDecimal;

public class MlbackCoupon {
	
    private Integer couponId;

    /**优惠券Code-Name*/
    private String couponName;//满100减10块   手动输入

    private String couponCode;//优惠码  如  OFF15,OFF15%    手动输入  
    
    /**优惠券类型--半分比/满多少减多少*/
    private Integer couponStatus;//  0不生效,1生效中,下拉选
    
    private String couponType;//优惠码类型  0满减的,1百分比折扣的(手动输入)
    
    /**优惠券类型--半分比折扣/减多少钱*/
    private BigDecimal couponPrice;// 5  手动输入

    private BigDecimal couponPriceoff;// 5  手动输入

    private BigDecimal couponPriceBaseline;//100     使用的底钱,减前满100.  手动输入

    /**是否支持前端抽奖*/
    private Integer couponLuckdrawType;//优惠码是否支持抽奖  0不支持抽奖,1支持抽奖    手动输入
    
    private Integer couponLuckdrawSort;//抽奖券排序  0默认值无排序,123456789

    private Integer couponLuckdrawWeight;//优惠码抽奖权重 0--100%    手动输入
    
    private String couponImgUrl;//抽奖的时候,需要显示的优惠券Img
    
    /**优惠券使用次数,总次数*/
    private Integer couponTimes;//优惠码已经使用次数  如  999,998次

    private Integer couponAlltimes;//优惠码可以使用总次数  如  999,998次
    
    /**优惠券生效的时间*/
    private String couponCreatetime;//后端留存-前不可见

    private String couponMotifytime;//后端留存-前不可见

    private String couponStarttime;//生效时间

    private String couponEndtime;//失效时间
    
    /**使用产品范围0全部,1单品,2某一类产品,3全局(除了xxxxx)*/
    private Integer couponProductonlyType;//优惠码是否支持单品

    private String couponProductonlyPidstr;//优惠码支持单品的时候,所绑定的产品

    private String couponProductseonamesstronlyPid;//优惠码支持单品的时候,所绑定的产品的seo

    private String couponProductpronamesstronlyPid;//优惠码支持单品的时候,所绑定的产品的proname

    private String couponApplyCateidstr;//优惠码支持类组的时候,所类CateIds串

    private String couponApplyCateSeostr;//优惠码支持类组的时候,所类CateseoStr串

    private String couponProsFromApplyCateidstr;//优惠码支持类组的时候,下面的所有产品id集合
    
    private String couponAllExceptPidstr;//优惠码支持全场(例外的产品Pid集合)

    private String couponAllExceptPseostr;//优惠码支持全场(例外的产品Pseo集合)
    
    /**是否客户专属券*/
    private Integer couponCodeUniqueEmailIF;//是否客户专属券0,非专属/1专属
    
    private String couponCodeUniqueEmail;//是否客户专属券

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName == null ? null : couponName.trim();
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    public BigDecimal getCouponPriceoff() {
        return couponPriceoff;
    }

    public void setCouponPriceoff(BigDecimal couponPriceoff) {
        this.couponPriceoff = couponPriceoff;
    }

    public BigDecimal getCouponPriceBaseline() {
        return couponPriceBaseline;
    }

    public void setCouponPriceBaseline(BigDecimal couponPriceBaseline) {
        this.couponPriceBaseline = couponPriceBaseline;
    }

    public Integer getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(Integer couponStatus) {
        this.couponStatus = couponStatus;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode == null ? null : couponCode.trim();
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType == null ? null : couponType.trim();
    }

    public Integer getCouponLuckdrawType() {
        return couponLuckdrawType;
    }

    public void setCouponLuckdrawType(Integer couponLuckdrawType) {
        this.couponLuckdrawType = couponLuckdrawType;
    }
    
    public Integer getCouponLuckdrawSort() {
		return couponLuckdrawSort;
	}

	public void setCouponLuckdrawSort(Integer couponLuckdrawSort) {
		this.couponLuckdrawSort = couponLuckdrawSort;
	}

	public Integer getCouponLuckdrawWeight() {
        return couponLuckdrawWeight;
    }

    public void setCouponLuckdrawWeight(Integer couponLuckdrawWeight) {
        this.couponLuckdrawWeight = couponLuckdrawWeight;
    }

    public Integer getCouponTimes() {
        return couponTimes;
    }

    public void setCouponTimes(Integer couponTimes) {
        this.couponTimes = couponTimes;
    }

    public Integer getCouponAlltimes() {
        return couponAlltimes;
    }

    public void setCouponAlltimes(Integer couponAlltimes) {
        this.couponAlltimes = couponAlltimes;
    }

    public String getCouponCreatetime() {
        return couponCreatetime;
    }

    public void setCouponCreatetime(String couponCreatetime) {
        this.couponCreatetime = couponCreatetime == null ? null : couponCreatetime.trim();
    }

    public String getCouponMotifytime() {
        return couponMotifytime;
    }

    public void setCouponMotifytime(String couponMotifytime) {
        this.couponMotifytime = couponMotifytime == null ? null : couponMotifytime.trim();
    }

    public String getCouponStarttime() {
        return couponStarttime;
    }

    public void setCouponStarttime(String couponStarttime) {
        this.couponStarttime = couponStarttime == null ? null : couponStarttime.trim();
    }

    public String getCouponEndtime() {
        return couponEndtime;
    }

    public void setCouponEndtime(String couponEndtime) {
        this.couponEndtime = couponEndtime == null ? null : couponEndtime.trim();
    }
    
    public String getCouponImgUrl() {
		return couponImgUrl;
	}

	public void setCouponImgUrl(String couponImgUrl) {
		this.couponImgUrl = couponImgUrl == null ? null : couponImgUrl.trim();
	}

	public Integer getCouponProductonlyType() {
        return couponProductonlyType;
    }

    public void setCouponProductonlyType(Integer couponProductonlyType) {
        this.couponProductonlyType = couponProductonlyType;
    }

    public String getCouponProductonlyPidstr() {
        return couponProductonlyPidstr;
    }

    public void setCouponProductonlyPidstr(String couponProductonlyPidstr) {
        this.couponProductonlyPidstr = couponProductonlyPidstr == null ? null : couponProductonlyPidstr.trim();
    }

    public String getCouponProductseonamesstronlyPid() {
        return couponProductseonamesstronlyPid;
    }

    public void setCouponProductseonamesstronlyPid(String couponProductseonamesstronlyPid) {
        this.couponProductseonamesstronlyPid = couponProductseonamesstronlyPid == null ? null : couponProductseonamesstronlyPid.trim();
    }

    public String getCouponProductpronamesstronlyPid() {
        return couponProductpronamesstronlyPid;
    }

    public void setCouponProductpronamesstronlyPid(String couponProductpronamesstronlyPid) {
        this.couponProductpronamesstronlyPid = couponProductpronamesstronlyPid == null ? null : couponProductpronamesstronlyPid.trim();
    }
    
	public String getCouponApplyCateidstr() {
		return couponApplyCateidstr;
	}

	public void setCouponApplyCateidstr(String couponApplyCateidstr) {
		this.couponApplyCateidstr = couponApplyCateidstr == null ? null : couponApplyCateidstr.trim();
	}

	public String getCouponApplyCateSeostr() {
		return couponApplyCateSeostr;
	}

	public void setCouponApplyCateSeostr(String couponApplyCateSeostr) {
		this.couponApplyCateSeostr = couponApplyCateSeostr == null ? null : couponApplyCateSeostr.trim();
	}

	public String getCouponProsFromApplyCateidstr() {
		return couponProsFromApplyCateidstr;
	}

	public void setCouponProsFromApplyCateidstr(String couponProsFromApplyCateidstr) {
		this.couponProsFromApplyCateidstr = couponProsFromApplyCateidstr == null ? null : couponProsFromApplyCateidstr.trim();
	}
	
	public String getCouponAllExceptPidstr() {
		return couponAllExceptPidstr;
	}
	
	public void setCouponAllExceptPidstr(String couponAllExceptPidstr) {
		this.couponAllExceptPidstr = couponAllExceptPidstr == null ? null : couponAllExceptPidstr.trim();
	}
	
	public String getCouponAllExceptPseostr() {
		return couponAllExceptPseostr;
	}
	
	public void setCouponAllExceptPseostr(String couponAllExceptPseostr) {
		this.couponAllExceptPseostr = couponAllExceptPseostr == null ? null : couponAllExceptPseostr.trim();
	}
	
	public Integer getCouponCodeUniqueEmailIF() {
		return couponCodeUniqueEmailIF;
	}
	
	public void setCouponCodeUniqueEmailIF(Integer couponCodeUniqueEmailIF) {
		this.couponCodeUniqueEmailIF = couponCodeUniqueEmailIF;
	}

	public String getCouponCodeUniqueEmail() {
		return couponCodeUniqueEmail;
	}

	public void setCouponCodeUniqueEmail(String couponCodeUniqueEmail) {
		this.couponCodeUniqueEmail = couponCodeUniqueEmail == null ? null : couponCodeUniqueEmail.trim();
	}

	public MlbackCoupon() {
		super();
	}

	public MlbackCoupon(Integer couponId, String couponName, String couponCode, Integer couponStatus, String couponType,
			BigDecimal couponPrice, BigDecimal couponPriceoff, BigDecimal couponPriceBaseline,
			Integer couponLuckdrawType, Integer couponLuckdrawSort, Integer couponLuckdrawWeight, String couponImgUrl,
			Integer couponTimes, Integer couponAlltimes, String couponCreatetime, String couponMotifytime,
			String couponStarttime, String couponEndtime, Integer couponProductonlyType, String couponProductonlyPidstr,
			String couponProductseonamesstronlyPid, String couponProductpronamesstronlyPid, String couponApplyCateidstr,
			String couponApplyCateSeostr, String couponProsFromApplyCateidstr, String couponAllExceptPidstr,
			String couponAllExceptPseostr, Integer couponCodeUniqueEmailIF, String couponCodeUniqueEmail) {
		super();
		this.couponId = couponId;
		this.couponName = couponName;
		this.couponPrice = couponPrice;
		this.couponPriceoff = couponPriceoff;
		this.couponPriceBaseline = couponPriceBaseline;
		this.couponStatus = couponStatus;
		this.couponCode = couponCode;
		this.couponType = couponType;
		this.couponLuckdrawType = couponLuckdrawType;
		this.couponLuckdrawSort = couponLuckdrawSort;
		this.couponLuckdrawWeight = couponLuckdrawWeight;
		this.couponTimes = couponTimes;
		this.couponAlltimes = couponAlltimes;
		this.couponCreatetime = couponCreatetime;
		this.couponMotifytime = couponMotifytime;
		this.couponStarttime = couponStarttime;
		this.couponEndtime = couponEndtime;
		this.couponImgUrl = couponImgUrl;
		this.couponProductonlyType = couponProductonlyType;
		this.couponProductonlyPidstr = couponProductonlyPidstr;
		this.couponProductseonamesstronlyPid = couponProductseonamesstronlyPid;
		this.couponProductpronamesstronlyPid = couponProductpronamesstronlyPid;
		this.couponApplyCateidstr = couponApplyCateidstr;
		this.couponApplyCateSeostr = couponApplyCateSeostr;
		this.couponProsFromApplyCateidstr = couponProsFromApplyCateidstr;
		this.couponAllExceptPidstr = couponAllExceptPidstr;
		this.couponAllExceptPseostr = couponAllExceptPseostr;
		this.couponCodeUniqueEmailIF = couponCodeUniqueEmailIF;
		this.couponCodeUniqueEmail = couponCodeUniqueEmail;
	}

	@Override
	public String toString() {
		return "MlbackCoupon [couponId=" + couponId + ", couponName=" + couponName + ", couponCode=" + couponCode
				+ ", couponStatus=" + couponStatus + ", couponType=" + couponType + ", couponPrice=" + couponPrice
				+ ", couponPriceoff=" + couponPriceoff + ", couponPriceBaseline=" + couponPriceBaseline
				+ ", couponLuckdrawType=" + couponLuckdrawType + ", couponLuckdrawSort=" + couponLuckdrawSort
				+ ", couponLuckdrawWeight=" + couponLuckdrawWeight + ", couponImgUrl=" + couponImgUrl + ", couponTimes="
				+ couponTimes + ", couponAlltimes=" + couponAlltimes + ", couponCreatetime=" + couponCreatetime
				+ ", couponMotifytime=" + couponMotifytime + ", couponStarttime=" + couponStarttime + ", couponEndtime="
				+ couponEndtime + ", couponProductonlyType=" + couponProductonlyType + ", couponProductonlyPidstr="
				+ couponProductonlyPidstr + ", couponProductseonamesstronlyPid=" + couponProductseonamesstronlyPid
				+ ", couponProductpronamesstronlyPid=" + couponProductpronamesstronlyPid + ", couponApplyCateidstr="
				+ couponApplyCateidstr + ", couponApplyCateSeostr=" + couponApplyCateSeostr
				+ ", couponProsFromApplyCateidstr=" + couponProsFromApplyCateidstr + ", couponAllExceptPidstr="
				+ couponAllExceptPidstr + ", couponAllExceptPseostr=" + couponAllExceptPseostr
				+ ", couponCodeUniqueEmailIF=" + couponCodeUniqueEmailIF + ", couponCodeUniqueEmail="
				+ couponCodeUniqueEmail + "]";
	}

}