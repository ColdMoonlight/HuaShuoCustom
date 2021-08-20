package com.atguigu.bean;

public class MlbackCaclPay {
    private Integer tbMlbackCaclpayId;

    private String tbMlbackCaclpayPaltenum;

    private Integer tbMlbackCaclpayOrderid;

    private String tbMlbackCaclpayCreatetime;

    private String tbMlbackCaclpayMotifytime;

    public Integer getTbMlbackCaclpayId() {
        return tbMlbackCaclpayId;
    }

    public void setTbMlbackCaclpayId(Integer tbMlbackCaclpayId) {
        this.tbMlbackCaclpayId = tbMlbackCaclpayId;
    }

    public String getTbMlbackCaclpayPaltenum() {
        return tbMlbackCaclpayPaltenum;
    }

    public void setTbMlbackCaclpayPaltenum(String tbMlbackCaclpayPaltenum) {
        this.tbMlbackCaclpayPaltenum = tbMlbackCaclpayPaltenum == null ? null : tbMlbackCaclpayPaltenum.trim();
    }

    public Integer getTbMlbackCaclpayOrderid() {
        return tbMlbackCaclpayOrderid;
    }

    public void setTbMlbackCaclpayOrderid(Integer tbMlbackCaclpayOrderid) {
        this.tbMlbackCaclpayOrderid = tbMlbackCaclpayOrderid;
    }

    public String getTbMlbackCaclpayCreatetime() {
        return tbMlbackCaclpayCreatetime;
    }

    public void setTbMlbackCaclpayCreatetime(String tbMlbackCaclpayCreatetime) {
        this.tbMlbackCaclpayCreatetime = tbMlbackCaclpayCreatetime == null ? null : tbMlbackCaclpayCreatetime.trim();
    }

    public String getTbMlbackCaclpayMotifytime() {
        return tbMlbackCaclpayMotifytime;
    }

    public void setTbMlbackCaclpayMotifytime(String tbMlbackCaclpayMotifytime) {
        this.tbMlbackCaclpayMotifytime = tbMlbackCaclpayMotifytime == null ? null : tbMlbackCaclpayMotifytime.trim();
    }

	public MlbackCaclPay() {
		super();
	}

	public MlbackCaclPay(Integer tbMlbackCaclpayId, String tbMlbackCaclpayPaltenum, Integer tbMlbackCaclpayOrderid,
			String tbMlbackCaclpayCreatetime, String tbMlbackCaclpayMotifytime) {
		super();
		this.tbMlbackCaclpayId = tbMlbackCaclpayId;
		this.tbMlbackCaclpayPaltenum = tbMlbackCaclpayPaltenum;
		this.tbMlbackCaclpayOrderid = tbMlbackCaclpayOrderid;
		this.tbMlbackCaclpayCreatetime = tbMlbackCaclpayCreatetime;
		this.tbMlbackCaclpayMotifytime = tbMlbackCaclpayMotifytime;
	}

	@Override
	public String toString() {
		return "MlbackCaclPay [tbMlbackCaclpayId=" + tbMlbackCaclpayId + ", tbMlbackCaclpayPaltenum="
				+ tbMlbackCaclpayPaltenum + ", tbMlbackCaclpayOrderid=" + tbMlbackCaclpayOrderid
				+ ", tbMlbackCaclpayCreatetime=" + tbMlbackCaclpayCreatetime + ", tbMlbackCaclpayMotifytime="
				+ tbMlbackCaclpayMotifytime + "]";
	}
    
}