package com.atguigu.bean;

public class MlbackCouponDescDetail {
    private Integer coupondescdetailId;

    private String coupondescdetailName;

    private String coupondescdetailStrengthpre;

    private String coupondescdetailStrength;

    private String coupondescdetailCodepre;

    private String coupondescdetailCode;

    private Integer coupondescdetailStatus;

    private String coupondescdetailCreatetime;

    private String coupondescdetailMotifytime;

    public Integer getCoupondescdetailId() {
        return coupondescdetailId;
    }

    public void setCoupondescdetailId(Integer coupondescdetailId) {
        this.coupondescdetailId = coupondescdetailId;
    }

    public String getCoupondescdetailName() {
        return coupondescdetailName;
    }

    public void setCoupondescdetailName(String coupondescdetailName) {
        this.coupondescdetailName = coupondescdetailName == null ? null : coupondescdetailName.trim();
    }

    public String getCoupondescdetailStrengthpre() {
        return coupondescdetailStrengthpre;
    }

    public void setCoupondescdetailStrengthpre(String coupondescdetailStrengthpre) {
        this.coupondescdetailStrengthpre = coupondescdetailStrengthpre == null ? null : coupondescdetailStrengthpre.trim();
    }

    public String getCoupondescdetailStrength() {
        return coupondescdetailStrength;
    }

    public void setCoupondescdetailStrength(String coupondescdetailStrength) {
        this.coupondescdetailStrength = coupondescdetailStrength == null ? null : coupondescdetailStrength.trim();
    }

    public String getCoupondescdetailCodepre() {
        return coupondescdetailCodepre;
    }

    public void setCoupondescdetailCodepre(String coupondescdetailCodepre) {
        this.coupondescdetailCodepre = coupondescdetailCodepre == null ? null : coupondescdetailCodepre.trim();
    }

    public String getCoupondescdetailCode() {
        return coupondescdetailCode;
    }

    public void setCoupondescdetailCode(String coupondescdetailCode) {
        this.coupondescdetailCode = coupondescdetailCode == null ? null : coupondescdetailCode.trim();
    }

    public Integer getCoupondescdetailStatus() {
        return coupondescdetailStatus;
    }

    public void setCoupondescdetailStatus(Integer coupondescdetailStatus) {
        this.coupondescdetailStatus = coupondescdetailStatus;
    }

    public String getCoupondescdetailCreatetime() {
        return coupondescdetailCreatetime;
    }

    public void setCoupondescdetailCreatetime(String coupondescdetailCreatetime) {
        this.coupondescdetailCreatetime = coupondescdetailCreatetime == null ? null : coupondescdetailCreatetime.trim();
    }

    public String getCoupondescdetailMotifytime() {
        return coupondescdetailMotifytime;
    }

    public void setCoupondescdetailMotifytime(String coupondescdetailMotifytime) {
        this.coupondescdetailMotifytime = coupondescdetailMotifytime == null ? null : coupondescdetailMotifytime.trim();
    }

	public MlbackCouponDescDetail() {
		super();
	}

	public MlbackCouponDescDetail(Integer coupondescdetailId, String coupondescdetailName,
			String coupondescdetailStrengthpre, String coupondescdetailStrength, String coupondescdetailCodepre,
			String coupondescdetailCode, Integer coupondescdetailStatus, String coupondescdetailCreatetime,
			String coupondescdetailMotifytime) {
		super();
		this.coupondescdetailId = coupondescdetailId;
		this.coupondescdetailName = coupondescdetailName;
		this.coupondescdetailStrengthpre = coupondescdetailStrengthpre;
		this.coupondescdetailStrength = coupondescdetailStrength;
		this.coupondescdetailCodepre = coupondescdetailCodepre;
		this.coupondescdetailCode = coupondescdetailCode;
		this.coupondescdetailStatus = coupondescdetailStatus;
		this.coupondescdetailCreatetime = coupondescdetailCreatetime;
		this.coupondescdetailMotifytime = coupondescdetailMotifytime;
	}

	@Override
	public String toString() {
		return "MlbackCouponDescDetail [coupondescdetailId=" + coupondescdetailId + ", coupondescdetailName="
				+ coupondescdetailName + ", coupondescdetailStrengthpre=" + coupondescdetailStrengthpre
				+ ", coupondescdetailStrength=" + coupondescdetailStrength + ", coupondescdetailCodepre="
				+ coupondescdetailCodepre + ", coupondescdetailCode=" + coupondescdetailCode
				+ ", coupondescdetailStatus=" + coupondescdetailStatus + ", coupondescdetailCreatetime="
				+ coupondescdetailCreatetime + ", coupondescdetailMotifytime=" + coupondescdetailMotifytime + "]";
	}
    
}