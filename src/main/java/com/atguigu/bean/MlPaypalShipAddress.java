package com.atguigu.bean;

public class MlPaypalShipAddress {
	
    private Integer shippingaddressId;

    private String shippingaddressPayinfoid;

    private String shippingaddressPaymentid;

    private String shippingaddressRecipientName;

    private String shippingaddressCountryCode;
    
    private String shippingaddressCountryName;
    
    private String shippingaddressTelNumber;

    private String shippingaddressCity;

    private String shippingaddressLine1;

    private String shippingaddressLine2;

    private String shippingaddressPostalCode;

    private String shippingaddressEmail;

    private String shippingaddressState;
    
    private String shippingaddressStateProvinceName;

    private String shippingaddressCreatetime;

    private String shippingaddressMotifytime;
    
    private Integer shippingaddressIfFirstBuy;
    
    private String shippingaddressPaymentStr;

    public Integer getShippingaddressId() {
        return shippingaddressId;
    }

    public void setShippingaddressId(Integer shippingaddressId) {
        this.shippingaddressId = shippingaddressId;
    }

    public String getShippingaddressPayinfoid() {
        return shippingaddressPayinfoid;
    }

    public void setShippingaddressPayinfoid(String shippingaddressPayinfoid) {
        this.shippingaddressPayinfoid = shippingaddressPayinfoid == null ? null : shippingaddressPayinfoid.trim();
    }

    public String getShippingaddressPaymentid() {
        return shippingaddressPaymentid;
    }

    public void setShippingaddressPaymentid(String shippingaddressPaymentid) {
        this.shippingaddressPaymentid = shippingaddressPaymentid == null ? null : shippingaddressPaymentid.trim();
    }

    public String getShippingaddressRecipientName() {
        return shippingaddressRecipientName;
    }

    public void setShippingaddressRecipientName(String shippingaddressRecipientName) {
        this.shippingaddressRecipientName = shippingaddressRecipientName == null ? null : shippingaddressRecipientName.trim();
    }

    public String getShippingaddressCountryCode() {
        return shippingaddressCountryCode;
    }

    public void setShippingaddressCountryCode(String shippingaddressCountryCode) {
        this.shippingaddressCountryCode = shippingaddressCountryCode == null ? null : shippingaddressCountryCode.trim();
    }
    
    public String getShippingaddressCountryName() {
		return shippingaddressCountryName;
	}

	public void setShippingaddressCountryName(String shippingaddressCountryName) {
		this.shippingaddressCountryName = shippingaddressCountryName == null ? null : shippingaddressCountryName.trim();
	}
	
	public String getShippingaddressTelNumber() {
		return shippingaddressTelNumber;
	}

	public void setShippingaddressTelNumber(String shippingaddressTelNumber) {
		this.shippingaddressTelNumber = shippingaddressTelNumber == null ? null : shippingaddressTelNumber.trim();
	}

	public String getShippingaddressCity() {
        return shippingaddressCity;
    }

    public void setShippingaddressCity(String shippingaddressCity) {
        this.shippingaddressCity = shippingaddressCity == null ? null : shippingaddressCity.trim();
    }

    public String getShippingaddressLine1() {
        return shippingaddressLine1;
    }

    public void setShippingaddressLine1(String shippingaddressLine1) {
        this.shippingaddressLine1 = shippingaddressLine1 == null ? null : shippingaddressLine1.trim();
    }

    public String getShippingaddressLine2() {
        return shippingaddressLine2;
    }

    public void setShippingaddressLine2(String shippingaddressLine2) {
        this.shippingaddressLine2 = shippingaddressLine2 == null ? null : shippingaddressLine2.trim();
    }

    public String getShippingaddressPostalCode() {
        return shippingaddressPostalCode;
    }

    public void setShippingaddressPostalCode(String shippingaddressPostalCode) {
        this.shippingaddressPostalCode = shippingaddressPostalCode == null ? null : shippingaddressPostalCode.trim();
    }

    public String getShippingaddressEmail() {
        return shippingaddressEmail;
    }

    public void setShippingaddressEmail(String shippingaddressEmail) {
        this.shippingaddressEmail = shippingaddressEmail == null ? null : shippingaddressEmail.trim();
    }

    public String getShippingaddressState() {
        return shippingaddressState;
    }

    public void setShippingaddressState(String shippingaddressState) {
        this.shippingaddressState = shippingaddressState == null ? null : shippingaddressState.trim();
    }

    public String getShippingaddressStateProvinceName() {
		return shippingaddressStateProvinceName;
	}

	public void setShippingaddressStateProvinceName(String shippingaddressStateProvinceName) {
		this.shippingaddressStateProvinceName = shippingaddressStateProvinceName == null ? null : shippingaddressStateProvinceName.trim();
	}

	public String getShippingaddressCreatetime() {
        return shippingaddressCreatetime;
    }

    public void setShippingaddressCreatetime(String shippingaddressCreatetime) {
        this.shippingaddressCreatetime = shippingaddressCreatetime == null ? null : shippingaddressCreatetime.trim();
    }

    public String getShippingaddressMotifytime() {
        return shippingaddressMotifytime;
    }

    public void setShippingaddressMotifytime(String shippingaddressMotifytime) {
        this.shippingaddressMotifytime = shippingaddressMotifytime == null ? null : shippingaddressMotifytime.trim();
    }
    
	public Integer getShippingaddressIfFirstBuy() {
		return shippingaddressIfFirstBuy;
	}

	public void setShippingaddressIfFirstBuy(Integer shippingaddressIfFirstBuy) {
		this.shippingaddressIfFirstBuy = shippingaddressIfFirstBuy;
	}
	
	public String getShippingaddressPaymentStr() {
		return shippingaddressPaymentStr;
	}

	public void setShippingaddressPaymentStr(String shippingaddressPaymentStr) {
		this.shippingaddressPaymentStr = shippingaddressPaymentStr == null ? null : shippingaddressPaymentStr.trim();
	}

	public MlPaypalShipAddress() {
		super();
	}

	public MlPaypalShipAddress(Integer shippingaddressId, String shippingaddressPayinfoid,
			String shippingaddressPaymentid, String shippingaddressRecipientName, String shippingaddressCountryCode,
			String shippingaddressCountryName, String shippingaddressTelNumber, String shippingaddressCity,
			String shippingaddressLine1, String shippingaddressLine2, String shippingaddressPostalCode,
			String shippingaddressEmail, String shippingaddressState, String shippingaddressStateProvinceName,
			String shippingaddressCreatetime, String shippingaddressMotifytime, Integer shippingaddressIfFirstBuy,
			String shippingaddressPaymentStr) {
		super();
		this.shippingaddressId = shippingaddressId;
		this.shippingaddressPayinfoid = shippingaddressPayinfoid;
		this.shippingaddressPaymentid = shippingaddressPaymentid;
		this.shippingaddressRecipientName = shippingaddressRecipientName;
		this.shippingaddressCountryCode = shippingaddressCountryCode;
		this.shippingaddressCountryName = shippingaddressCountryName;
		this.shippingaddressTelNumber = shippingaddressTelNumber;
		this.shippingaddressCity = shippingaddressCity;
		this.shippingaddressLine1 = shippingaddressLine1;
		this.shippingaddressLine2 = shippingaddressLine2;
		this.shippingaddressPostalCode = shippingaddressPostalCode;
		this.shippingaddressEmail = shippingaddressEmail;
		this.shippingaddressState = shippingaddressState;
		this.shippingaddressStateProvinceName = shippingaddressStateProvinceName;
		this.shippingaddressCreatetime = shippingaddressCreatetime;
		this.shippingaddressMotifytime = shippingaddressMotifytime;
		this.shippingaddressIfFirstBuy = shippingaddressIfFirstBuy;
		this.shippingaddressPaymentStr = shippingaddressPaymentStr;
	}

	@Override
	public String toString() {
		return "MlPaypalShipAddress [shippingaddressId=" + shippingaddressId + ", shippingaddressPayinfoid="
				+ shippingaddressPayinfoid + ", shippingaddressPaymentid=" + shippingaddressPaymentid
				+ ", shippingaddressRecipientName=" + shippingaddressRecipientName + ", shippingaddressCountryCode="
				+ shippingaddressCountryCode + ", shippingaddressCountryName=" + shippingaddressCountryName
				+ ", shippingaddressTelNumber=" + shippingaddressTelNumber + ", shippingaddressCity="
				+ shippingaddressCity + ", shippingaddressLine1=" + shippingaddressLine1 + ", shippingaddressLine2="
				+ shippingaddressLine2 + ", shippingaddressPostalCode=" + shippingaddressPostalCode
				+ ", shippingaddressEmail=" + shippingaddressEmail + ", shippingaddressState=" + shippingaddressState
				+ ", shippingaddressStateProvinceName=" + shippingaddressStateProvinceName
				+ ", shippingaddressCreatetime=" + shippingaddressCreatetime + ", shippingaddressMotifytime="
				+ shippingaddressMotifytime + ", shippingaddressIfFirstBuy=" + shippingaddressIfFirstBuy
				+ ", shippingaddressPaymentStr=" + shippingaddressPaymentStr + "]";
	}

}