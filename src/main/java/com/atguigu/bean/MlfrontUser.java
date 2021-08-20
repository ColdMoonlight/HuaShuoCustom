package com.atguigu.bean;

public class MlfrontUser {
    private Integer userId;

    private String userEmail;

    private String userPassword;

    private String userTelephone;

    private String userAddressCountry;

    private String userAddressProvince;

    private String userAddressCity;

    private String userAddressStreetaddress;

    private String userAddressPostalcode;

    private String userFirstname;

    private String userLastname;

    private String userCreatetime;

    private String userMotifytime;

    private String userLastonlinetime;

    private Integer userPoint;

    private String userCouponidstr;

    private Integer userTimes;

    private Integer userViplevel;

    private String userStr;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone == null ? null : userTelephone.trim();
    }

    public String getUserAddressCountry() {
        return userAddressCountry;
    }

    public void setUserAddressCountry(String userAddressCountry) {
        this.userAddressCountry = userAddressCountry == null ? null : userAddressCountry.trim();
    }

    public String getUserAddressProvince() {
        return userAddressProvince;
    }

    public void setUserAddressProvince(String userAddressProvince) {
        this.userAddressProvince = userAddressProvince == null ? null : userAddressProvince.trim();
    }

    public String getUserAddressCity() {
        return userAddressCity;
    }

    public void setUserAddressCity(String userAddressCity) {
        this.userAddressCity = userAddressCity == null ? null : userAddressCity.trim();
    }

    public String getUserAddressStreetaddress() {
        return userAddressStreetaddress;
    }

    public void setUserAddressStreetaddress(String userAddressStreetaddress) {
        this.userAddressStreetaddress = userAddressStreetaddress == null ? null : userAddressStreetaddress.trim();
    }

    public String getUserAddressPostalcode() {
        return userAddressPostalcode;
    }

    public void setUserAddressPostalcode(String userAddressPostalcode) {
        this.userAddressPostalcode = userAddressPostalcode == null ? null : userAddressPostalcode.trim();
    }

    public String getUserFirstname() {
        return userFirstname;
    }

    public void setUserFirstname(String userFirstname) {
        this.userFirstname = userFirstname == null ? null : userFirstname.trim();
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname == null ? null : userLastname.trim();
    }

    public String getUserCreatetime() {
        return userCreatetime;
    }

    public void setUserCreatetime(String userCreatetime) {
        this.userCreatetime = userCreatetime == null ? null : userCreatetime.trim();
    }

    public String getUserMotifytime() {
        return userMotifytime;
    }

    public void setUserMotifytime(String userMotifytime) {
        this.userMotifytime = userMotifytime == null ? null : userMotifytime.trim();
    }

    public String getUserLastonlinetime() {
        return userLastonlinetime;
    }

    public void setUserLastonlinetime(String userLastonlinetime) {
        this.userLastonlinetime = userLastonlinetime == null ? null : userLastonlinetime.trim();
    }

    public Integer getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(Integer userPoint) {
        this.userPoint = userPoint;
    }

    public String getUserCouponidstr() {
        return userCouponidstr;
    }

    public void setUserCouponidstr(String userCouponidstr) {
        this.userCouponidstr = userCouponidstr == null ? null : userCouponidstr.trim();
    }

    public Integer getUserTimes() {
        return userTimes;
    }

    public void setUserTimes(Integer userTimes) {
        this.userTimes = userTimes;
    }

    public Integer getUserViplevel() {
        return userViplevel;
    }

    public void setUserViplevel(Integer userViplevel) {
        this.userViplevel = userViplevel;
    }

	public String getUserStr() {
		return userStr;
	}

	public void setUserStr(String userStr) {
		this.userStr = userStr == null ? null : userStr.trim();
	}

	public MlfrontUser() {
		super();
	}

	public MlfrontUser(Integer userId, String userEmail, String userPassword, String userTelephone,
			String userAddressCountry, String userAddressProvince, String userAddressCity,
			String userAddressStreetaddress, String userAddressPostalcode, String userFirstname, String userLastname,
			String userCreatetime, String userMotifytime, String userLastonlinetime, Integer userPoint,
			String userCouponidstr, Integer userTimes, Integer userViplevel, String userStr) {
		super();
		this.userId = userId;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userTelephone = userTelephone;
		this.userAddressCountry = userAddressCountry;
		this.userAddressProvince = userAddressProvince;
		this.userAddressCity = userAddressCity;
		this.userAddressStreetaddress = userAddressStreetaddress;
		this.userAddressPostalcode = userAddressPostalcode;
		this.userFirstname = userFirstname;
		this.userLastname = userLastname;
		this.userCreatetime = userCreatetime;
		this.userMotifytime = userMotifytime;
		this.userLastonlinetime = userLastonlinetime;
		this.userPoint = userPoint;
		this.userCouponidstr = userCouponidstr;
		this.userTimes = userTimes;
		this.userViplevel = userViplevel;
		this.userStr = userStr;
	}

	@Override
	public String toString() {
		return "MlfrontUser [userId=" + userId + ", userEmail=" + userEmail + ", userPassword=" + userPassword
				+ ", userTelephone=" + userTelephone + ", userAddressCountry=" + userAddressCountry
				+ ", userAddressProvince=" + userAddressProvince + ", userAddressCity=" + userAddressCity
				+ ", userAddressStreetaddress=" + userAddressStreetaddress + ", userAddressPostalcode="
				+ userAddressPostalcode + ", userFirstname=" + userFirstname + ", userLastname=" + userLastname
				+ ", userCreatetime=" + userCreatetime + ", userMotifytime=" + userMotifytime + ", userLastonlinetime="
				+ userLastonlinetime + ", userPoint=" + userPoint + ", userCouponidstr=" + userCouponidstr
				+ ", userTimes=" + userTimes + ", userViplevel=" + userViplevel + ", userStr=" + userStr + "]";
	}
    
}