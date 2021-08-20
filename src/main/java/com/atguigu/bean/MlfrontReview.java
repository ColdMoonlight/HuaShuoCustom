package com.atguigu.bean;

public class MlfrontReview {
    private Integer reviewId;

    private Integer reviewUid;

    private String reviewUname;

    private String reviewUimgurl;

    private Integer reviewPid;//1

    private String reviewPname;

    private String reviewCreatetime;

    private String reviewMotifytime;

    private String reviewConfirmtime;

    private String reviewPseoname;//2

    private String reviewSupercateidstr;

    private Integer reviewStatus;

    private String reviewDetailstr;

    private String reviewImgidstr;

    private Integer reviewProstarnum;//3

    private Integer reviewFrom;
    
    /**
     * 是否有照片
     */
    private Integer reviewIsPics;
    
    /**
     * 查询字段  1：是否有照片，2：待补充
     */
    private Integer searchType;

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getReviewUid() {
        return reviewUid;
    }

    public void setReviewUid(Integer reviewUid) {
        this.reviewUid = reviewUid;
    }

    public String getReviewUname() {
        return reviewUname;
    }

    public void setReviewUname(String reviewUname) {
        this.reviewUname = reviewUname == null ? null : reviewUname.trim();
    }

    public String getReviewUimgurl() {
        return reviewUimgurl;
    }

    public void setReviewUimgurl(String reviewUimgurl) {
        this.reviewUimgurl = reviewUimgurl == null ? null : reviewUimgurl.trim();
    }

    public Integer getReviewPid() {
        return reviewPid;
    }

    public void setReviewPid(Integer reviewPid) {
        this.reviewPid = reviewPid;
    }

    public String getReviewPname() {
        return reviewPname;
    }

    public void setReviewPname(String reviewPname) {
        this.reviewPname = reviewPname == null ? null : reviewPname.trim();
    }

    public String getReviewCreatetime() {
        return reviewCreatetime;
    }

    public void setReviewCreatetime(String reviewCreatetime) {
        this.reviewCreatetime = reviewCreatetime == null ? null : reviewCreatetime.trim();
    }

    public String getReviewMotifytime() {
        return reviewMotifytime;
    }

    public void setReviewMotifytime(String reviewMotifytime) {
        this.reviewMotifytime = reviewMotifytime == null ? null : reviewMotifytime.trim();
    }

    public String getReviewConfirmtime() {
        return reviewConfirmtime;
    }

    public void setReviewConfirmtime(String reviewConfirmtime) {
        this.reviewConfirmtime = reviewConfirmtime == null ? null : reviewConfirmtime.trim();
    }

    public String getReviewPseoname() {
        return reviewPseoname;
    }

    public void setReviewPseoname(String reviewPseoname) {
        this.reviewPseoname = reviewPseoname == null ? null : reviewPseoname.trim();
    }

    public String getReviewSupercateidstr() {
        return reviewSupercateidstr;
    }

    public void setReviewSupercateidstr(String reviewSupercateidstr) {
        this.reviewSupercateidstr = reviewSupercateidstr == null ? null : reviewSupercateidstr.trim();
    }

    public Integer getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Integer reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getReviewDetailstr() {
        return reviewDetailstr;
    }

    public void setReviewDetailstr(String reviewDetailstr) {
        this.reviewDetailstr = reviewDetailstr == null ? null : reviewDetailstr.trim();
    }

    public String getReviewImgidstr() {
        return reviewImgidstr;
    }

    public void setReviewImgidstr(String reviewImgidstr) {
        this.reviewImgidstr = reviewImgidstr == null ? null : reviewImgidstr.trim();
    }

    public Integer getReviewProstarnum() {
        return reviewProstarnum;
    }

    public void setReviewProstarnum(Integer reviewProstarnum) {
        this.reviewProstarnum = reviewProstarnum;
    }

    public Integer getReviewFrom() {
        return reviewFrom;
    }

    public void setReviewFrom(Integer reviewFrom) {
        this.reviewFrom = reviewFrom;
    }

	public Integer getReviewIsPics() {
		return reviewIsPics;
	}

	public Integer getSearchType() {
		return searchType;
	}

	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}

	public void setReviewIsPics(Integer reviewIsPics) {
		this.reviewIsPics = reviewIsPics;
	}

	public MlfrontReview() {
		super();
	}

	public MlfrontReview(Integer reviewId, Integer reviewUid, String reviewUname, String reviewUimgurl,
			Integer reviewPid, String reviewPname, String reviewCreatetime, String reviewMotifytime,
			String reviewConfirmtime, String reviewPseoname, String reviewSupercateidstr, Integer reviewStatus,
			String reviewDetailstr, String reviewImgidstr, Integer reviewProstarnum, Integer reviewFrom, Integer reviewIsPics) {
		super();
		this.reviewId = reviewId;
		this.reviewUid = reviewUid;
		this.reviewUname = reviewUname;
		this.reviewUimgurl = reviewUimgurl;
		this.reviewPid = reviewPid;
		this.reviewPname = reviewPname;
		this.reviewCreatetime = reviewCreatetime;
		this.reviewMotifytime = reviewMotifytime;
		this.reviewConfirmtime = reviewConfirmtime;
		this.reviewPseoname = reviewPseoname;
		this.reviewSupercateidstr = reviewSupercateidstr;
		this.reviewStatus = reviewStatus;
		this.reviewDetailstr = reviewDetailstr;
		this.reviewImgidstr = reviewImgidstr;
		this.reviewProstarnum = reviewProstarnum;
		this.reviewFrom = reviewFrom;
		this.reviewIsPics = reviewIsPics;
	}

	@Override
	public String toString() {
		return "MlfrontReview [reviewId=" + reviewId + ", reviewUid=" + reviewUid + ", reviewUname=" + reviewUname
				+ ", reviewUimgurl=" + reviewUimgurl + ", reviewPid=" + reviewPid + ", reviewPname=" + reviewPname
				+ ", reviewCreatetime=" + reviewCreatetime + ", reviewMotifytime=" + reviewMotifytime
				+ ", reviewConfirmtime=" + reviewConfirmtime + ", reviewPseoname=" + reviewPseoname
				+ ", reviewSupercateidstr=" + reviewSupercateidstr + ", reviewStatus=" + reviewStatus
				+ ", reviewDetailstr=" + reviewDetailstr + ", reviewImgidstr=" + reviewImgidstr + ", reviewProstarnum="
				+ reviewProstarnum + ", reviewFrom=" + reviewFrom + ", reviewIsPics=" + reviewIsPics + "]";
	}
    
}