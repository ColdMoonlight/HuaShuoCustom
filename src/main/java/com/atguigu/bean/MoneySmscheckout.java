package com.atguigu.bean;

public class MoneySmscheckout {
    private Integer smscheckoutId;//

    private Integer smscheckoutPayid;//payid

    private Integer smscheckoutOrderid;//orderid

    private String smscheckoutOrdername;//客户的名字

    private String smscheckoutSendtype;//成功单-3，发送单-1

    private String smscheckoutCreatetime;//创建日期

    private String smscheckoutMotifytime;//更新日期

    public Integer getSmscheckoutId() {
        return smscheckoutId;
    }

    public void setSmscheckoutId(Integer smscheckoutId) {
        this.smscheckoutId = smscheckoutId;
    }

    public Integer getSmscheckoutPayid() {
        return smscheckoutPayid;
    }

    public void setSmscheckoutPayid(Integer smscheckoutPayid) {
        this.smscheckoutPayid = smscheckoutPayid;
    }

    public Integer getSmscheckoutOrderid() {
        return smscheckoutOrderid;
    }

    public void setSmscheckoutOrderid(Integer smscheckoutOrderid) {
        this.smscheckoutOrderid = smscheckoutOrderid;
    }

    public String getSmscheckoutOrdername() {
        return smscheckoutOrdername;
    }

    public void setSmscheckoutOrdername(String smscheckoutOrdername) {
        this.smscheckoutOrdername = smscheckoutOrdername == null ? null : smscheckoutOrdername.trim();
    }

    public String getSmscheckoutSendtype() {
        return smscheckoutSendtype;
    }

    public void setSmscheckoutSendtype(String smscheckoutSendtype) {
        this.smscheckoutSendtype = smscheckoutSendtype == null ? null : smscheckoutSendtype.trim();
    }

    public String getSmscheckoutCreatetime() {
        return smscheckoutCreatetime;
    }

    public void setSmscheckoutCreatetime(String smscheckoutCreatetime) {
        this.smscheckoutCreatetime = smscheckoutCreatetime == null ? null : smscheckoutCreatetime.trim();
    }

    public String getSmscheckoutMotifytime() {
        return smscheckoutMotifytime;
    }

    public void setSmscheckoutMotifytime(String smscheckoutMotifytime) {
        this.smscheckoutMotifytime = smscheckoutMotifytime == null ? null : smscheckoutMotifytime.trim();
    }

	public MoneySmscheckout() {
		super();
	}

	public MoneySmscheckout(Integer smscheckoutId, Integer smscheckoutPayid, Integer smscheckoutOrderid,
			String smscheckoutOrdername, String smscheckoutSendtype, String smscheckoutCreatetime,
			String smscheckoutMotifytime) {
		super();
		this.smscheckoutId = smscheckoutId;
		this.smscheckoutPayid = smscheckoutPayid;
		this.smscheckoutOrderid = smscheckoutOrderid;
		this.smscheckoutOrdername = smscheckoutOrdername;
		this.smscheckoutSendtype = smscheckoutSendtype;
		this.smscheckoutCreatetime = smscheckoutCreatetime;
		this.smscheckoutMotifytime = smscheckoutMotifytime;
	}

	@Override
	public String toString() {
		return "MoneySmscheckout [smscheckoutId=" + smscheckoutId + ", smscheckoutPayid=" + smscheckoutPayid
				+ ", smscheckoutOrderid=" + smscheckoutOrderid + ", smscheckoutOrdername=" + smscheckoutOrdername
				+ ", smscheckoutSendtype=" + smscheckoutSendtype + ", smscheckoutCreatetime=" + smscheckoutCreatetime
				+ ", smscheckoutMotifytime=" + smscheckoutMotifytime + "]";
	}
    
}