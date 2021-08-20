package com.atguigu.bean;

public class MlbackSuperCate {
	
    private Integer supercateId;

    private String supercateName;

    private String supercateImgurl;

    private Integer supercateStatus;

    private String supercateSeo;

    private Integer supercateSortOrder;

    private String supercateCreatetime;

    private String supercateMotifytime;

    private String supercateMetatitle;

    private String supercateMetakeywords;

    private String supercateMetadesc;

    public MlbackSuperCate(Integer supercateId, String supercateName, String supercateImgurl, Integer supercateStatus, String supercateSeo, Integer supercateSortOrder, String supercateCreatetime, String supercateMotifytime, String supercateMetatitle, String supercateMetakeywords, String supercateMetadesc) {
        this.supercateId = supercateId;
        this.supercateName = supercateName;
        this.supercateImgurl = supercateImgurl;
        this.supercateStatus = supercateStatus;
        this.supercateSeo = supercateSeo;
        this.supercateSortOrder = supercateSortOrder;
        this.supercateCreatetime = supercateCreatetime;
        this.supercateMotifytime = supercateMotifytime;
        this.supercateMetatitle = supercateMetatitle;
        this.supercateMetakeywords = supercateMetakeywords;
        this.supercateMetadesc = supercateMetadesc;
    }

    public MlbackSuperCate() {
        super();
    }

    public Integer getSupercateId() {
        return supercateId;
    }

    public void setSupercateId(Integer supercateId) {
        this.supercateId = supercateId;
    }

    public String getSupercateName() {
        return supercateName;
    }

    public void setSupercateName(String supercateName) {
        this.supercateName = supercateName == null ? null : supercateName.trim();
    }

    public String getSupercateImgurl() {
        return supercateImgurl;
    }

    public void setSupercateImgurl(String supercateImgurl) {
        this.supercateImgurl = supercateImgurl == null ? null : supercateImgurl.trim();
    }

    public Integer getSupercateStatus() {
        return supercateStatus;
    }

    public void setSupercateStatus(Integer supercateStatus) {
        this.supercateStatus = supercateStatus;
    }

    public String getSupercateSeo() {
        return supercateSeo;
    }

    public void setSupercateSeo(String supercateSeo) {
        this.supercateSeo = supercateSeo == null ? null : supercateSeo.trim();
    }

    public Integer getSupercateSortOrder() {
        return supercateSortOrder;
    }

    public void setSupercateSortOrder(Integer supercateSortOrder) {
        this.supercateSortOrder = supercateSortOrder;
    }

    public String getSupercateCreatetime() {
        return supercateCreatetime;
    }

    public void setSupercateCreatetime(String supercateCreatetime) {
        this.supercateCreatetime = supercateCreatetime == null ? null : supercateCreatetime.trim();
    }

    public String getSupercateMotifytime() {
        return supercateMotifytime;
    }

    public void setSupercateMotifytime(String supercateMotifytime) {
        this.supercateMotifytime = supercateMotifytime == null ? null : supercateMotifytime.trim();
    }

    public String getSupercateMetatitle() {
        return supercateMetatitle;
    }

    public void setSupercateMetatitle(String supercateMetatitle) {
        this.supercateMetatitle = supercateMetatitle == null ? null : supercateMetatitle.trim();
    }

    public String getSupercateMetakeywords() {
        return supercateMetakeywords;
    }

    public void setSupercateMetakeywords(String supercateMetakeywords) {
        this.supercateMetakeywords = supercateMetakeywords == null ? null : supercateMetakeywords.trim();
    }

    public String getSupercateMetadesc() {
        return supercateMetadesc;
    }

    public void setSupercateMetadesc(String supercateMetadesc) {
        this.supercateMetadesc = supercateMetadesc == null ? null : supercateMetadesc.trim();
    }

	@Override
	public String toString() {
		return "MlbackSuperCate [supercateId=" + supercateId + ", supercateName=" + supercateName + ", supercateImgurl="
				+ supercateImgurl + ", supercateStatus=" + supercateStatus + ", supercateSeo=" + supercateSeo
				+ ", supercateSortOrder=" + supercateSortOrder + ", supercateCreatetime=" + supercateCreatetime
				+ ", supercateMotifytime=" + supercateMotifytime + ", supercateMetatitle=" + supercateMetatitle
				+ ", supercateMetakeywords=" + supercateMetakeywords + ", supercateMetadesc=" + supercateMetadesc + "]";
	}
    
    
}