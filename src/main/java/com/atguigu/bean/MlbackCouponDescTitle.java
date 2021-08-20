package com.atguigu.bean;

public class MlbackCouponDescTitle {
	
    private Integer coupondesctieleId;

    private String coupondesctieleName;

    private String coupondesctieleTieledetail;

    private Integer coupondesctieleStatus;

    private String coupondesctieleImgurl;

    private String coupondesctieleCreatetime;

    private String coupondesctieleMotifytime;

    public Integer getCoupondesctieleId() {
        return coupondesctieleId;
    }

    public void setCoupondesctieleId(Integer coupondesctieleId) {
        this.coupondesctieleId = coupondesctieleId;
    }

    public String getCoupondesctieleName() {
        return coupondesctieleName;
    }

    public void setCoupondesctieleName(String coupondesctieleName) {
        this.coupondesctieleName = coupondesctieleName == null ? null : coupondesctieleName.trim();
    }

    public String getCoupondesctieleTieledetail() {
        return coupondesctieleTieledetail;
    }

    public void setCoupondesctieleTieledetail(String coupondesctieleTieledetail) {
        this.coupondesctieleTieledetail = coupondesctieleTieledetail == null ? null : coupondesctieleTieledetail.trim();
    }

    public Integer getCoupondesctieleStatus() {
		return coupondesctieleStatus;
	}

	public void setCoupondesctieleStatus(Integer coupondesctieleStatus) {
		this.coupondesctieleStatus = coupondesctieleStatus;
	}

	public String getCoupondesctieleImgurl() {
		return coupondesctieleImgurl;
	}

	public void setCoupondesctieleImgurl(String coupondesctieleImgurl) {
		this.coupondesctieleImgurl = coupondesctieleImgurl == null ? null : coupondesctieleImgurl.trim();
	}

	public String getCoupondesctieleCreatetime() {
        return coupondesctieleCreatetime;
    }

    public void setCoupondesctieleCreatetime(String coupondesctieleCreatetime) {
        this.coupondesctieleCreatetime = coupondesctieleCreatetime == null ? null : coupondesctieleCreatetime.trim();
    }

    public String getCoupondesctieleMotifytime() {
        return coupondesctieleMotifytime;
    }

    public void setCoupondesctieleMotifytime(String coupondesctieleMotifytime) {
        this.coupondesctieleMotifytime = coupondesctieleMotifytime == null ? null : coupondesctieleMotifytime.trim();
    }

	public MlbackCouponDescTitle() {
		super();
	}

	public MlbackCouponDescTitle(Integer coupondesctieleId, String coupondesctieleName,
			String coupondesctieleTieledetail, Integer coupondesctieleStatus, String coupondesctieleImgurl,
			String coupondesctieleCreatetime, String coupondesctieleMotifytime) {
		super();
		this.coupondesctieleId = coupondesctieleId;
		this.coupondesctieleName = coupondesctieleName;
		this.coupondesctieleTieledetail = coupondesctieleTieledetail;
		this.coupondesctieleStatus = coupondesctieleStatus;
		this.coupondesctieleImgurl = coupondesctieleImgurl;
		this.coupondesctieleCreatetime = coupondesctieleCreatetime;
		this.coupondesctieleMotifytime = coupondesctieleMotifytime;
	}

	@Override
	public String toString() {
		return "MlbackCouponDescTitle [coupondesctieleId=" + coupondesctieleId + ", coupondesctieleName="
				+ coupondesctieleName + ", coupondesctieleTieledetail=" + coupondesctieleTieledetail
				+ ", coupondesctieleStatus=" + coupondesctieleStatus + ", coupondesctieleImgurl="
				+ coupondesctieleImgurl + ", coupondesctieleCreatetime=" + coupondesctieleCreatetime
				+ ", coupondesctieleMotifytime=" + coupondesctieleMotifytime + "]";
	}
    
}