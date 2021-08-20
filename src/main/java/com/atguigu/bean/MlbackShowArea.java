package com.atguigu.bean;

public class MlbackShowArea {
	
    private Integer showareaId;

    private String showareaName;

    private String showareaImgurl;

    private String showareaImgpcurl;

    private Integer showareaStatus;

    private Integer showareaNumth;

    private String showareaCreatetime;

    private String showareaMotifytime;

    public Integer getShowareaId() {
        return showareaId;
    }

    public void setShowareaId(Integer showareaId) {
        this.showareaId = showareaId;
    }

    public String getShowareaName() {
        return showareaName;
    }

    public void setShowareaName(String showareaName) {
        this.showareaName = showareaName == null ? null : showareaName.trim();
    }

    public String getShowareaImgurl() {
        return showareaImgurl;
    }

    public void setShowareaImgurl(String showareaImgurl) {
        this.showareaImgurl = showareaImgurl == null ? null : showareaImgurl.trim();
    }

    public String getShowareaImgpcurl() {
        return showareaImgpcurl;
    }

    public void setShowareaImgpcurl(String showareaImgpcurl) {
        this.showareaImgpcurl = showareaImgpcurl == null ? null : showareaImgpcurl.trim();
    }

    public Integer getShowareaStatus() {
        return showareaStatus;
    }

    public void setShowareaStatus(Integer showareaStatus) {
        this.showareaStatus = showareaStatus;
    }

    public Integer getShowareaNumth() {
        return showareaNumth;
    }

    public void setShowareaNumth(Integer showareaNumth) {
        this.showareaNumth = showareaNumth;
    }

    public String getShowareaCreatetime() {
        return showareaCreatetime;
    }

    public void setShowareaCreatetime(String showareaCreatetime) {
        this.showareaCreatetime = showareaCreatetime == null ? null : showareaCreatetime.trim();
    }

    public String getShowareaMotifytime() {
        return showareaMotifytime;
    }

    public void setShowareaMotifytime(String showareaMotifytime) {
        this.showareaMotifytime = showareaMotifytime == null ? null : showareaMotifytime.trim();
    }

	public MlbackShowArea() {
		super();
	}

	public MlbackShowArea(Integer showareaId, String showareaName, String showareaImgurl, String showareaImgpcurl,
			Integer showareaStatus, Integer showareaNumth, String showareaCreatetime, String showareaMotifytime) {
		super();
		this.showareaId = showareaId;
		this.showareaName = showareaName;
		this.showareaImgurl = showareaImgurl;
		this.showareaImgpcurl = showareaImgpcurl;
		this.showareaStatus = showareaStatus;
		this.showareaNumth = showareaNumth;
		this.showareaCreatetime = showareaCreatetime;
		this.showareaMotifytime = showareaMotifytime;
	}

	@Override
	public String toString() {
		return "MlbackShowArea [showareaId=" + showareaId + ", showareaName=" + showareaName + ", showareaImgurl="
				+ showareaImgurl + ", showareaImgpcurl=" + showareaImgpcurl + ", showareaStatus=" + showareaStatus
				+ ", showareaNumth=" + showareaNumth + ", showareaCreatetime=" + showareaCreatetime
				+ ", showareaMotifytime=" + showareaMotifytime + "]";
	}
    
}