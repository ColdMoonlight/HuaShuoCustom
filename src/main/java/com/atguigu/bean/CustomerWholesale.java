package com.atguigu.bean;

public class CustomerWholesale {
	
    private Integer wholesaleId;

    private String wholesaleCustomerName;

    private String wholesaleCustomerEmail;
    
    private String wholesaleCustomerTelephone;//telephone

    private String wholesaleCustomerCountry;//Country

    private String wholesaleCustomerWatsapp;//Watsapp

    private String wholesaleCustomerMessage;
    
    private String wholesaleCustomerStatus;
    
    private String wholesaleOperateDeatil;//业务操作描述

    private String wholesaleCreatetime;

    private String wholesaleMotifytime;
    
    private String wholesaleCustomerTypeName;
    
    public Integer getWholesaleId() {
        return wholesaleId;
    }

    public void setWholesaleId(Integer wholesaleId) {
        this.wholesaleId = wholesaleId;
    }

    public String getWholesaleCustomerName() {
        return wholesaleCustomerName;
    }

    public void setWholesaleCustomerName(String wholesaleCustomerName) {
        this.wholesaleCustomerName = wholesaleCustomerName == null ? null : wholesaleCustomerName.trim();
    }

    public String getWholesaleCustomerEmail() {
        return wholesaleCustomerEmail;
    }

    public void setWholesaleCustomerEmail(String wholesaleCustomerEmail) {
        this.wholesaleCustomerEmail = wholesaleCustomerEmail == null ? null : wholesaleCustomerEmail.trim();
    }

    public String getWholesaleCustomerTelephone() {
		return wholesaleCustomerTelephone;
	}

	public void setWholesaleCustomerTelephone(String wholesaleCustomerTelephone) {
		this.wholesaleCustomerTelephone = wholesaleCustomerTelephone == null ? null : wholesaleCustomerTelephone.trim();
	}

	public String getWholesaleCustomerCountry() {
        return wholesaleCustomerCountry;
    }

    public void setWholesaleCustomerCountry(String wholesaleCustomerCountry) {
        this.wholesaleCustomerCountry = wholesaleCustomerCountry == null ? null : wholesaleCustomerCountry.trim();
    }

    public String getWholesaleCustomerWatsapp() {
        return wholesaleCustomerWatsapp;
    }

    public void setWholesaleCustomerWatsapp(String wholesaleCustomerWatsapp) {
        this.wholesaleCustomerWatsapp = wholesaleCustomerWatsapp == null ? null : wholesaleCustomerWatsapp.trim();
    }

    public String getWholesaleCustomerMessage() {
        return wholesaleCustomerMessage;
    }

    public void setWholesaleCustomerMessage(String wholesaleCustomerMessage) {
        this.wholesaleCustomerMessage = wholesaleCustomerMessage == null ? null : wholesaleCustomerMessage.trim();
    }
    
    public String getWholesaleOperateDeatil() {
		return wholesaleOperateDeatil;
	}

	public void setWholesaleOperateDeatil(String wholesaleOperateDeatil) {
		this.wholesaleOperateDeatil = wholesaleOperateDeatil;
	}

	public String getWholesaleCreatetime() {
        return wholesaleCreatetime;
    }

    public void setWholesaleCreatetime(String wholesaleCreatetime) {
        this.wholesaleCreatetime = wholesaleCreatetime == null ? null : wholesaleCreatetime.trim();
    }

    public String getWholesaleMotifytime() {
        return wholesaleMotifytime;
    }

    public void setWholesaleMotifytime(String wholesaleMotifytime) {
        this.wholesaleMotifytime = wholesaleMotifytime == null ? null : wholesaleMotifytime.trim();
    }
    
	public String getWholesaleCustomerStatus() {
		return wholesaleCustomerStatus;
	}

	public void setWholesaleCustomerStatus(String wholesaleCustomerStatus) {
		this.wholesaleCustomerStatus = wholesaleCustomerStatus == null ? null : wholesaleCustomerStatus.trim();
	}
	
	public String getWholesaleCustomerTypeName() {
		return wholesaleCustomerTypeName;
	}

	public void setWholesaleCustomerTypeName(String wholesaleCustomerTypeName) {
		this.wholesaleCustomerTypeName = wholesaleCustomerTypeName == null ? null : wholesaleCustomerTypeName.trim();
	}

	public CustomerWholesale() {
		super();
	}

	public CustomerWholesale(Integer wholesaleId, String wholesaleCustomerName, String wholesaleCustomerEmail,
			String wholesaleCustomerTelephone, String wholesaleCustomerCountry, String wholesaleCustomerWatsapp,
			String wholesaleCustomerMessage, String wholesaleCustomerStatus, String wholesaleOperateDeatil,
			String wholesaleCreatetime, String wholesaleMotifytime, String wholesaleCustomerTypeName) {
		super();
		this.wholesaleId = wholesaleId;
		this.wholesaleCustomerName = wholesaleCustomerName;
		this.wholesaleCustomerEmail = wholesaleCustomerEmail;
		this.wholesaleCustomerTelephone = wholesaleCustomerTelephone;
		this.wholesaleCustomerCountry = wholesaleCustomerCountry;
		this.wholesaleCustomerWatsapp = wholesaleCustomerWatsapp;
		this.wholesaleCustomerMessage = wholesaleCustomerMessage;
		this.wholesaleCustomerStatus = wholesaleCustomerStatus;
		this.wholesaleOperateDeatil = wholesaleOperateDeatil;
		this.wholesaleCreatetime = wholesaleCreatetime;
		this.wholesaleMotifytime = wholesaleMotifytime;
		this.wholesaleCustomerTypeName = wholesaleCustomerTypeName;
	}

	@Override
	public String toString() {
		return "CustomerWholesale [wholesaleId=" + wholesaleId + ", wholesaleCustomerName=" + wholesaleCustomerName
				+ ", wholesaleCustomerEmail=" + wholesaleCustomerEmail + ", wholesaleCustomerTelephone="
				+ wholesaleCustomerTelephone + ", wholesaleCustomerCountry=" + wholesaleCustomerCountry
				+ ", wholesaleCustomerWatsapp=" + wholesaleCustomerWatsapp + ", wholesaleCustomerMessage="
				+ wholesaleCustomerMessage + ", wholesaleCustomerStatus=" + wholesaleCustomerStatus
				+ ", wholesaleOperateDeatil=" + wholesaleOperateDeatil + ", wholesaleCreatetime=" + wholesaleCreatetime
				+ ", wholesaleMotifytime=" + wholesaleMotifytime + ", wholesaleCustomerTypeName="
				+ wholesaleCustomerTypeName + "]";
	}

}