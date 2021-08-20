package com.atguigu.bean;

public class MlbackAreafreight {
	
    private Integer areafreightId;

    private String areafreightCountryCode;

    private String areafreightCountry;

    private Integer areafreightPrice;

    private Integer areafreightStatus;
    
    private Integer areafreightTelPrefix;//areafreight_tel_prefix

    public Integer getAreafreightId() {
        return areafreightId;
    }

    public void setAreafreightId(Integer areafreightId) {
        this.areafreightId = areafreightId;
    }

    public String getAreafreightCountryCode() {
        return areafreightCountryCode;
    }

    public void setAreafreightCountryCode(String areafreightCountryCode) {
        this.areafreightCountryCode = areafreightCountryCode == null ? null : areafreightCountryCode.trim();
    }

    public String getAreafreightCountry() {
        return areafreightCountry;
    }

    public void setAreafreightCountry(String areafreightCountry) {
        this.areafreightCountry = areafreightCountry == null ? null : areafreightCountry.trim();
    }

    public Integer getAreafreightPrice() {
        return areafreightPrice;
    }

    public void setAreafreightPrice(Integer areafreightPrice) {
        this.areafreightPrice = areafreightPrice;
    }

    public Integer getAreafreightStatus() {
        return areafreightStatus;
    }

    public void setAreafreightStatus(Integer areafreightStatus) {
        this.areafreightStatus = areafreightStatus;
    }
    
	public Integer getAreafreightTelPrefix() {
		return areafreightTelPrefix;
	}

	public void setAreafreightTelPrefix(Integer areafreightTelPrefix) {
		this.areafreightTelPrefix = areafreightTelPrefix;
	}

	public MlbackAreafreight() {
		super();
	}

	public MlbackAreafreight(Integer areafreightId, String areafreightCountryCode, String areafreightCountry,
			Integer areafreightPrice, Integer areafreightStatus, Integer areafreightTelPrefix) {
		super();
		this.areafreightId = areafreightId;
		this.areafreightCountryCode = areafreightCountryCode;
		this.areafreightCountry = areafreightCountry;
		this.areafreightPrice = areafreightPrice;
		this.areafreightStatus = areafreightStatus;
		this.areafreightTelPrefix = areafreightTelPrefix;
	}

	@Override
	public String toString() {
		return "MlbackAreafreight [areafreightId=" + areafreightId + ", areafreightCountryCode="
				+ areafreightCountryCode + ", areafreightCountry=" + areafreightCountry + ", areafreightPrice="
				+ areafreightPrice + ", areafreightStatus=" + areafreightStatus + ", areafreightTelPrefix=" + areafreightTelPrefix + "]";
	}
    
}