package com.atguigu.bean;

public class MlfrontAddress {
	
    private Integer addressId;

    private Integer addressUid;

    private String addressIp;

    private String addressEmail;

    private String addressTelephone;

    private String addressUserfirstname;

    private String addressUserlastname;

    private String addressCountryCode;

    private String addressCountry;

    private String addressProvince;

    private String addressProvincecode;

    private String addressCity;

    private String addressDetail;

    private String addressPost;

    private String addressCreatetime;

    private String addressMotifytime;
    
    private Integer addressStatus;
    
    private String addressFormatTelephone;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getAddressUid() {
        return addressUid;
    }

    public void setAddressUid(Integer addressUid) {
        this.addressUid = addressUid;
    }

    public String getAddressIp() {
        return addressIp;
    }

    public void setAddressIp(String addressIp) {
        this.addressIp = addressIp == null ? null : addressIp.trim();
    }

    public String getAddressEmail() {
        return addressEmail;
    }

    public void setAddressEmail(String addressEmail) {
        this.addressEmail = addressEmail == null ? null : addressEmail.trim();
    }

    public String getAddressTelephone() {
        return addressTelephone;
    }

    public void setAddressTelephone(String addressTelephone) {
        this.addressTelephone = addressTelephone == null ? null : addressTelephone.trim();
    }

    public String getAddressUserfirstname() {
        return addressUserfirstname;
    }

    public void setAddressUserfirstname(String addressUserfirstname) {
        this.addressUserfirstname = addressUserfirstname == null ? null : addressUserfirstname.trim();
    }

    public String getAddressUserlastname() {
        return addressUserlastname;
    }

    public void setAddressUserlastname(String addressUserlastname) {
        this.addressUserlastname = addressUserlastname == null ? null : addressUserlastname.trim();
    }

    public String getAddressCountryCode() {
        return addressCountryCode;
    }

    public void setAddressCountryCode(String addressCountryCode) {
        this.addressCountryCode = addressCountryCode == null ? null : addressCountryCode.trim();
    }

    public String getAddressCountry() {
        return addressCountry;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry == null ? null : addressCountry.trim();
    }

    public String getAddressProvince() {
        return addressProvince;
    }

    public void setAddressProvince(String addressProvince) {
        this.addressProvince = addressProvince == null ? null : addressProvince.trim();
    }

    public String getAddressProvincecode() {
        return addressProvincecode;
    }

    public void setAddressProvincecode(String addressProvincecode) {
        this.addressProvincecode = addressProvincecode == null ? null : addressProvincecode.trim();
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity == null ? null : addressCity.trim();
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail == null ? null : addressDetail.trim();
    }

    public String getAddressPost() {
        return addressPost;
    }

    public void setAddressPost(String addressPost) {
        this.addressPost = addressPost == null ? null : addressPost.trim();
    }

    public String getAddressCreatetime() {
        return addressCreatetime;
    }

    public void setAddressCreatetime(String addressCreatetime) {
        this.addressCreatetime = addressCreatetime == null ? null : addressCreatetime.trim();
    }

    public String getAddressMotifytime() {
        return addressMotifytime;
    }

    public void setAddressMotifytime(String addressMotifytime) {
        this.addressMotifytime = addressMotifytime == null ? null : addressMotifytime.trim();
    }

	public Integer getAddressStatus() {
		return addressStatus;
	}

	public void setAddressStatus(Integer addressStatus) {
		this.addressStatus = addressStatus;
	}
	
	public String getAddressFormatTelephone() {
		return addressFormatTelephone;
	}

	public void setAddressFormatTelephone(String addressFormatTelephone) {
		this.addressFormatTelephone = addressFormatTelephone == null ? null : addressFormatTelephone.trim();
	}

	public MlfrontAddress() {
		super();
	}

	public MlfrontAddress(Integer addressId, Integer addressUid, String addressIp, String addressEmail,
			String addressTelephone, String addressUserfirstname, String addressUserlastname, String addressCountryCode,
			String addressCountry, String addressProvince, String addressProvincecode, String addressCity,
			String addressDetail, String addressPost, String addressCreatetime, String addressMotifytime,
			Integer addressStatus, String addressFormatTelephone) {
		super();
		this.addressId = addressId;
		this.addressUid = addressUid;
		this.addressIp = addressIp;
		this.addressEmail = addressEmail;
		this.addressTelephone = addressTelephone;
		this.addressUserfirstname = addressUserfirstname;
		this.addressUserlastname = addressUserlastname;
		this.addressCountryCode = addressCountryCode;
		this.addressCountry = addressCountry;
		this.addressProvince = addressProvince;
		this.addressProvincecode = addressProvincecode;
		this.addressCity = addressCity;
		this.addressDetail = addressDetail;
		this.addressPost = addressPost;
		this.addressCreatetime = addressCreatetime;
		this.addressMotifytime = addressMotifytime;
		this.addressStatus = addressStatus;
		this.addressFormatTelephone = addressFormatTelephone;
	}

	@Override
	public String toString() {
		return "MlfrontAddress [addressId=" + addressId + ", addressUid=" + addressUid + ", addressIp=" + addressIp
				+ ", addressEmail=" + addressEmail + ", addressTelephone=" + addressTelephone
				+ ", addressUserfirstname=" + addressUserfirstname + ", addressUserlastname=" + addressUserlastname
				+ ", addressCountryCode=" + addressCountryCode + ", addressCountry=" + addressCountry
				+ ", addressProvince=" + addressProvince + ", addressProvincecode=" + addressProvincecode
				+ ", addressCity=" + addressCity + ", addressDetail=" + addressDetail + ", addressPost=" + addressPost
				+ ", addressCreatetime=" + addressCreatetime + ", addressMotifytime=" + addressMotifytime
				+ ", addressStatus=" + addressStatus + ", addressFormatTelephone=" + addressFormatTelephone + "]";
	}

}