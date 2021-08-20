package com.atguigu.bean;

public class MlbackAddCartViewDetail {
    private Integer addcartviewdetailId;

    private String addcartviewdetailSessionid;

    private Integer addcartviewdetailProid;

    private String addcartviewdetailProname;

    private String addcartviewdetailProseo;

    private Integer addcartviewdetailActnum;

    private String addcartviewdetailCreatetime;

    private String addcartviewdetailMotifytime;

    public Integer getAddcartviewdetailId() {
        return addcartviewdetailId;
    }

    public void setAddcartviewdetailId(Integer addcartviewdetailId) {
        this.addcartviewdetailId = addcartviewdetailId;
    }

    public String getAddcartviewdetailSessionid() {
        return addcartviewdetailSessionid;
    }

    public void setAddcartviewdetailSessionid(String addcartviewdetailSessionid) {
        this.addcartviewdetailSessionid = addcartviewdetailSessionid == null ? null : addcartviewdetailSessionid.trim();
    }

    public Integer getAddcartviewdetailProid() {
        return addcartviewdetailProid;
    }

    public void setAddcartviewdetailProid(Integer addcartviewdetailProid) {
        this.addcartviewdetailProid = addcartviewdetailProid;
    }

    public String getAddcartviewdetailProname() {
        return addcartviewdetailProname;
    }

    public void setAddcartviewdetailProname(String addcartviewdetailProname) {
        this.addcartviewdetailProname = addcartviewdetailProname == null ? null : addcartviewdetailProname.trim();
    }

    public String getAddcartviewdetailProseo() {
        return addcartviewdetailProseo;
    }

    public void setAddcartviewdetailProseo(String addcartviewdetailProseo) {
        this.addcartviewdetailProseo = addcartviewdetailProseo == null ? null : addcartviewdetailProseo.trim();
    }

    public Integer getAddcartviewdetailActnum() {
        return addcartviewdetailActnum;
    }

    public void setAddcartviewdetailActnum(Integer addcartviewdetailActnum) {
        this.addcartviewdetailActnum = addcartviewdetailActnum;
    }

    public String getAddcartviewdetailCreatetime() {
        return addcartviewdetailCreatetime;
    }

    public void setAddcartviewdetailCreatetime(String addcartviewdetailCreatetime) {
        this.addcartviewdetailCreatetime = addcartviewdetailCreatetime == null ? null : addcartviewdetailCreatetime.trim();
    }

    public String getAddcartviewdetailMotifytime() {
        return addcartviewdetailMotifytime;
    }

    public void setAddcartviewdetailMotifytime(String addcartviewdetailMotifytime) {
        this.addcartviewdetailMotifytime = addcartviewdetailMotifytime == null ? null : addcartviewdetailMotifytime.trim();
    }

	public MlbackAddCartViewDetail() {
		super();
	}

	public MlbackAddCartViewDetail(Integer addcartviewdetailId, String addcartviewdetailSessionid,
			Integer addcartviewdetailProid, String addcartviewdetailProname, String addcartviewdetailProseo,
			Integer addcartviewdetailActnum, String addcartviewdetailCreatetime, String addcartviewdetailMotifytime) {
		super();
		this.addcartviewdetailId = addcartviewdetailId;
		this.addcartviewdetailSessionid = addcartviewdetailSessionid;
		this.addcartviewdetailProid = addcartviewdetailProid;
		this.addcartviewdetailProname = addcartviewdetailProname;
		this.addcartviewdetailProseo = addcartviewdetailProseo;
		this.addcartviewdetailActnum = addcartviewdetailActnum;
		this.addcartviewdetailCreatetime = addcartviewdetailCreatetime;
		this.addcartviewdetailMotifytime = addcartviewdetailMotifytime;
	}
    
}