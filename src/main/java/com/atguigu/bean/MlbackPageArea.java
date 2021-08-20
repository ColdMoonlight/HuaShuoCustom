package com.atguigu.bean;

public class MlbackPageArea {
    private Integer pageareaId;

    private String pageareaSeo;

    private String pageareaName;

    private String pageareaAscription;

    private Integer pageareaSupercateid;

    private String pageareaSupercatename;

    private Integer pageareaStatus;

    private Integer pageareaSort;

    private Integer pageareaPcstatus;

    private Integer pageareaPcsort;

    private Integer pageareaType;

    private String pageareaTypedetail;

    private String pageareaTypedetailIdstr;

    private String pageareaCreatetime;

    private String pageareaMotifytime;

    public Integer getPageareaId() {
        return pageareaId;
    }

    public void setPageareaId(Integer pageareaId) {
        this.pageareaId = pageareaId;
    }

    public String getPageareaSeo() {
        return pageareaSeo;
    }

    public void setPageareaSeo(String pageareaSeo) {
        this.pageareaSeo = pageareaSeo == null ? null : pageareaSeo.trim();
    }

    public String getPageareaName() {
        return pageareaName;
    }

    public void setPageareaName(String pageareaName) {
        this.pageareaName = pageareaName == null ? null : pageareaName.trim();
    }

    public String getPageareaAscription() {
        return pageareaAscription;
    }

    public void setPageareaAscription(String pageareaAscription) {
        this.pageareaAscription = pageareaAscription == null ? null : pageareaAscription.trim();
    }

    public Integer getPageareaSupercateid() {
        return pageareaSupercateid;
    }

    public void setPageareaSupercateid(Integer pageareaSupercateid) {
        this.pageareaSupercateid = pageareaSupercateid;
    }

    public String getPageareaSupercatename() {
        return pageareaSupercatename;
    }

    public void setPageareaSupercatename(String pageareaSupercatename) {
        this.pageareaSupercatename = pageareaSupercatename == null ? null : pageareaSupercatename.trim();
    }

    public Integer getPageareaStatus() {
        return pageareaStatus;
    }

    public void setPageareaStatus(Integer pageareaStatus) {
        this.pageareaStatus = pageareaStatus;
    }

    public Integer getPageareaSort() {
        return pageareaSort;
    }

    public void setPageareaSort(Integer pageareaSort) {
        this.pageareaSort = pageareaSort;
    }

    public Integer getPageareaPcstatus() {
        return pageareaPcstatus;
    }

    public void setPageareaPcstatus(Integer pageareaPcstatus) {
        this.pageareaPcstatus = pageareaPcstatus;
    }

    public Integer getPageareaPcsort() {
        return pageareaPcsort;
    }

    public void setPageareaPcsort(Integer pageareaPcsort) {
        this.pageareaPcsort = pageareaPcsort;
    }

    public Integer getPageareaType() {
        return pageareaType;
    }

    public void setPageareaType(Integer pageareaType) {
        this.pageareaType = pageareaType;
    }

    public String getPageareaTypedetail() {
        return pageareaTypedetail;
    }

    public void setPageareaTypedetail(String pageareaTypedetail) {
        this.pageareaTypedetail = pageareaTypedetail == null ? null : pageareaTypedetail.trim();
    }

    public String getPageareaTypedetailIdstr() {
        return pageareaTypedetailIdstr;
    }

    public void setPageareaTypedetailIdstr(String pageareaTypedetailIdstr) {
        this.pageareaTypedetailIdstr = pageareaTypedetailIdstr == null ? null : pageareaTypedetailIdstr.trim();
    }

    public String getPageareaCreatetime() {
        return pageareaCreatetime;
    }

    public void setPageareaCreatetime(String pageareaCreatetime) {
        this.pageareaCreatetime = pageareaCreatetime == null ? null : pageareaCreatetime.trim();
    }

    public String getPageareaMotifytime() {
        return pageareaMotifytime;
    }

    public void setPageareaMotifytime(String pageareaMotifytime) {
        this.pageareaMotifytime = pageareaMotifytime == null ? null : pageareaMotifytime.trim();
    }

	public MlbackPageArea() {
		super();
	}

	public MlbackPageArea(Integer pageareaId, String pageareaSeo, String pageareaName, String pageareaAscription,
			Integer pageareaSupercateid, String pageareaSupercatename, Integer pageareaStatus, Integer pageareaSort,
			Integer pageareaPcstatus, Integer pageareaPcsort, Integer pageareaType, String pageareaTypedetail,
			String pageareaTypedetailIdstr, String pageareaCreatetime, String pageareaMotifytime) {
		super();
		this.pageareaId = pageareaId;
		this.pageareaSeo = pageareaSeo;
		this.pageareaName = pageareaName;
		this.pageareaAscription = pageareaAscription;
		this.pageareaSupercateid = pageareaSupercateid;
		this.pageareaSupercatename = pageareaSupercatename;
		this.pageareaStatus = pageareaStatus;
		this.pageareaSort = pageareaSort;
		this.pageareaPcstatus = pageareaPcstatus;
		this.pageareaPcsort = pageareaPcsort;
		this.pageareaType = pageareaType;
		this.pageareaTypedetail = pageareaTypedetail;
		this.pageareaTypedetailIdstr = pageareaTypedetailIdstr;
		this.pageareaCreatetime = pageareaCreatetime;
		this.pageareaMotifytime = pageareaMotifytime;
	}

	@Override
	public String toString() {
		return "MlbackPageArea [pageareaId=" + pageareaId + ", pageareaSeo=" + pageareaSeo + ", pageareaName="
				+ pageareaName + ", pageareaAscription=" + pageareaAscription + ", pageareaSupercateid="
				+ pageareaSupercateid + ", pageareaSupercatename=" + pageareaSupercatename + ", pageareaStatus="
				+ pageareaStatus + ", pageareaSort=" + pageareaSort + ", pageareaPcstatus=" + pageareaPcstatus
				+ ", pageareaPcsort=" + pageareaPcsort + ", pageareaType=" + pageareaType + ", pageareaTypedetail="
				+ pageareaTypedetail + ", pageareaTypedetailIdstr=" + pageareaTypedetailIdstr + ", pageareaCreatetime="
				+ pageareaCreatetime + ", pageareaMotifytime=" + pageareaMotifytime + "]";
	}
    
}