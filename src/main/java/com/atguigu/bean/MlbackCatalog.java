package com.atguigu.bean;

public class MlbackCatalog {
    private Integer catalogId;

    private String catalogName;

    private Integer catalogParentId;

    private String catalogParentName;

    private String catalogDesc;

    private Integer catalogSupercateId;

    private String catalogSupercateName;

    private Integer catalogStatus;

    private Integer catalogLable;

    private Integer catalogFirthNum;
    
    private Integer catalogIfinto;
    
    private Integer catalogIfproorcateorpage;

    private Integer catalogProid;

    private String catalogProname;

    private String catalogSeoname;

    private Integer catalogCateid;

    private String catalogCatename;

    private String catalogCateseoname;

    private String catalogPagename;

    private String catalogPageseoname;

    private String catalogCreatetime;

    private String catalogMotifytime;

    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName == null ? null : catalogName.trim();
    }

    public Integer getCatalogParentId() {
        return catalogParentId;
    }

    public void setCatalogParentId(Integer catalogParentId) {
        this.catalogParentId = catalogParentId;
    }

    public String getCatalogParentName() {
        return catalogParentName;
    }

    public void setCatalogParentName(String catalogParentName) {
        this.catalogParentName = catalogParentName == null ? null : catalogParentName.trim();
    }

    public String getCatalogDesc() {
        return catalogDesc;
    }

    public void setCatalogDesc(String catalogDesc) {
        this.catalogDesc = catalogDesc == null ? null : catalogDesc.trim();
    }

    public Integer getCatalogSupercateId() {
        return catalogSupercateId;
    }

    public void setCatalogSupercateId(Integer catalogSupercateId) {
        this.catalogSupercateId = catalogSupercateId;
    }

    public String getCatalogSupercateName() {
        return catalogSupercateName;
    }

    public void setCatalogSupercateName(String catalogSupercateName) {
        this.catalogSupercateName = catalogSupercateName == null ? null : catalogSupercateName.trim();
    }

    public Integer getCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(Integer catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    public Integer getCatalogLable() {
        return catalogLable;
    }

    public void setCatalogLable(Integer catalogLable) {
        this.catalogLable = catalogLable;
    }

    public Integer getCatalogFirthNum() {
        return catalogFirthNum;
    }

    public void setCatalogFirthNum(Integer catalogFirthNum) {
        this.catalogFirthNum = catalogFirthNum;
    }
    
    public Integer getCatalogIfinto() {
		return catalogIfinto;
	}

	public void setCatalogIfinto(Integer catalogIfinto) {
		this.catalogIfinto = catalogIfinto;
	}

	public Integer getCatalogIfproorcateorpage() {
        return catalogIfproorcateorpage;
    }

    public void setCatalogIfproorcateorpage(Integer catalogIfproorcateorpage) {
        this.catalogIfproorcateorpage = catalogIfproorcateorpage;
    }

    public Integer getCatalogProid() {
        return catalogProid;
    }

    public void setCatalogProid(Integer catalogProid) {
        this.catalogProid = catalogProid;
    }

    public String getCatalogProname() {
        return catalogProname;
    }

    public void setCatalogProname(String catalogProname) {
        this.catalogProname = catalogProname == null ? null : catalogProname.trim();
    }

    public String getCatalogSeoname() {
        return catalogSeoname;
    }

    public void setCatalogSeoname(String catalogSeoname) {
        this.catalogSeoname = catalogSeoname == null ? null : catalogSeoname.trim();
    }

    public Integer getCatalogCateid() {
        return catalogCateid;
    }

    public void setCatalogCateid(Integer catalogCateid) {
        this.catalogCateid = catalogCateid;
    }

    public String getCatalogCatename() {
        return catalogCatename;
    }

    public void setCatalogCatename(String catalogCatename) {
        this.catalogCatename = catalogCatename == null ? null : catalogCatename.trim();
    }

    public String getCatalogCateseoname() {
        return catalogCateseoname;
    }

    public void setCatalogCateseoname(String catalogCateseoname) {
        this.catalogCateseoname = catalogCateseoname == null ? null : catalogCateseoname.trim();
    }

    public String getCatalogPagename() {
        return catalogPagename;
    }

    public void setCatalogPagename(String catalogPagename) {
        this.catalogPagename = catalogPagename == null ? null : catalogPagename.trim();
    }

    public String getCatalogPageseoname() {
        return catalogPageseoname;
    }

    public void setCatalogPageseoname(String catalogPageseoname) {
        this.catalogPageseoname = catalogPageseoname == null ? null : catalogPageseoname.trim();
    }

    public String getCatalogCreatetime() {
        return catalogCreatetime;
    }

    public void setCatalogCreatetime(String catalogCreatetime) {
        this.catalogCreatetime = catalogCreatetime == null ? null : catalogCreatetime.trim();
    }

    public String getCatalogMotifytime() {
        return catalogMotifytime;
    }

    public void setCatalogMotifytime(String catalogMotifytime) {
        this.catalogMotifytime = catalogMotifytime == null ? null : catalogMotifytime.trim();
    }

	public MlbackCatalog() {
		super();
	}

	public MlbackCatalog(Integer catalogId, String catalogName, Integer catalogParentId, String catalogParentName,
			String catalogDesc, Integer catalogSupercateId, String catalogSupercateName, Integer catalogStatus,
			Integer catalogLable, Integer catalogFirthNum, Integer catalogIfinto, Integer catalogIfproorcateorpage,
			Integer catalogProid, String catalogProname, String catalogSeoname, Integer catalogCateid,
			String catalogCatename, String catalogCateseoname, String catalogPagename, String catalogPageseoname,
			String catalogCreatetime, String catalogMotifytime) {
		super();
		this.catalogId = catalogId;
		this.catalogName = catalogName;
		this.catalogParentId = catalogParentId;
		this.catalogParentName = catalogParentName;
		this.catalogDesc = catalogDesc;
		this.catalogSupercateId = catalogSupercateId;
		this.catalogSupercateName = catalogSupercateName;
		this.catalogStatus = catalogStatus;
		this.catalogLable = catalogLable;
		this.catalogFirthNum = catalogFirthNum;
		this.catalogIfinto = catalogIfinto;
		this.catalogIfproorcateorpage = catalogIfproorcateorpage;
		this.catalogProid = catalogProid;
		this.catalogProname = catalogProname;
		this.catalogSeoname = catalogSeoname;
		this.catalogCateid = catalogCateid;
		this.catalogCatename = catalogCatename;
		this.catalogCateseoname = catalogCateseoname;
		this.catalogPagename = catalogPagename;
		this.catalogPageseoname = catalogPageseoname;
		this.catalogCreatetime = catalogCreatetime;
		this.catalogMotifytime = catalogMotifytime;
	}

	@Override
	public String toString() {
		return "MlbackCatalog [catalogId=" + catalogId + ", catalogName=" + catalogName + ", catalogParentId="
				+ catalogParentId + ", catalogParentName=" + catalogParentName + ", catalogDesc=" + catalogDesc
				+ ", catalogSupercateId=" + catalogSupercateId + ", catalogSupercateName=" + catalogSupercateName
				+ ", catalogStatus=" + catalogStatus + ", catalogLable=" + catalogLable + ", catalogFirthNum="
				+ catalogFirthNum + ", catalogIfinto=" + catalogIfinto + ", catalogIfproorcateorpage="
				+ catalogIfproorcateorpage + ", catalogProid=" + catalogProid + ", catalogProname=" + catalogProname
				+ ", catalogSeoname=" + catalogSeoname + ", catalogCateid=" + catalogCateid + ", catalogCatename="
				+ catalogCatename + ", catalogCateseoname=" + catalogCateseoname + ", catalogPagename="
				+ catalogPagename + ", catalogPageseoname=" + catalogPageseoname + ", catalogCreatetime="
				+ catalogCreatetime + ", catalogMotifytime=" + catalogMotifytime + "]";
	}
    
}