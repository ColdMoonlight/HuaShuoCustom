package com.atguigu.bean;

public class MoneyEmailcheckout {
    private Integer emailcheckoutId;

    private Integer emailcheckoutPayid;

    private Integer emailcheckoutOrderid;

    private String emailcheckoutOrdername;

    private String emailcheckoutSendtype;

    private String emailcheckoutCreatetime;

    private String emailcheckoutMotifytime;

    public Integer getEmailcheckoutId() {
        return emailcheckoutId;
    }

    public void setEmailcheckoutId(Integer emailcheckoutId) {
        this.emailcheckoutId = emailcheckoutId;
    }

    public Integer getEmailcheckoutPayid() {
        return emailcheckoutPayid;
    }

    public void setEmailcheckoutPayid(Integer emailcheckoutPayid) {
        this.emailcheckoutPayid = emailcheckoutPayid;
    }

    public Integer getEmailcheckoutOrderid() {
        return emailcheckoutOrderid;
    }

    public void setEmailcheckoutOrderid(Integer emailcheckoutOrderid) {
        this.emailcheckoutOrderid = emailcheckoutOrderid;
    }

    public String getEmailcheckoutOrdername() {
        return emailcheckoutOrdername;
    }

    public void setEmailcheckoutOrdername(String emailcheckoutOrdername) {
        this.emailcheckoutOrdername = emailcheckoutOrdername == null ? null : emailcheckoutOrdername.trim();
    }

    public String getEmailcheckoutSendtype() {
        return emailcheckoutSendtype;
    }

    public void setEmailcheckoutSendtype(String emailcheckoutSendtype) {
        this.emailcheckoutSendtype = emailcheckoutSendtype == null ? null : emailcheckoutSendtype.trim();
    }

    public String getEmailcheckoutCreatetime() {
        return emailcheckoutCreatetime;
    }

    public void setEmailcheckoutCreatetime(String emailcheckoutCreatetime) {
        this.emailcheckoutCreatetime = emailcheckoutCreatetime == null ? null : emailcheckoutCreatetime.trim();
    }

    public String getEmailcheckoutMotifytime() {
        return emailcheckoutMotifytime;
    }

    public void setEmailcheckoutMotifytime(String emailcheckoutMotifytime) {
        this.emailcheckoutMotifytime = emailcheckoutMotifytime == null ? null : emailcheckoutMotifytime.trim();
    }

	public MoneyEmailcheckout() {
		super();
	}

	public MoneyEmailcheckout(Integer emailcheckoutId, Integer emailcheckoutPayid, Integer emailcheckoutOrderid,
			String emailcheckoutOrdername, String emailcheckoutSendtype, String emailcheckoutCreatetime,
			String emailcheckoutMotifytime) {
		super();
		this.emailcheckoutId = emailcheckoutId;
		this.emailcheckoutPayid = emailcheckoutPayid;
		this.emailcheckoutOrderid = emailcheckoutOrderid;
		this.emailcheckoutOrdername = emailcheckoutOrdername;
		this.emailcheckoutSendtype = emailcheckoutSendtype;
		this.emailcheckoutCreatetime = emailcheckoutCreatetime;
		this.emailcheckoutMotifytime = emailcheckoutMotifytime;
	}

	@Override
	public String toString() {
		return "MoneyEmailcheckout [emailcheckoutId=" + emailcheckoutId + ", emailcheckoutPayid=" + emailcheckoutPayid
				+ ", emailcheckoutOrderid=" + emailcheckoutOrderid + ", emailcheckoutOrdername="
				+ emailcheckoutOrdername + ", emailcheckoutSendtype=" + emailcheckoutSendtype
				+ ", emailcheckoutCreatetime=" + emailcheckoutCreatetime + ", emailcheckoutMotifytime="
				+ emailcheckoutMotifytime + "]";
	}
    
}