package com.atguigu.bean;

public class MlbackSmstype {
    private Integer smstypeId;

    private String smstypeName;

    private Integer smstypeFronttime;

    private String smstypeContent;

    private Integer smstypeStatus;

    private String smstypeCreatetime;

    private String smstypeMotifytime;

    public Integer getSmstypeId() {
        return smstypeId;
    }

    public void setSmstypeId(Integer smstypeId) {
        this.smstypeId = smstypeId;
    }

    public String getSmstypeName() {
        return smstypeName;
    }

    public void setSmstypeName(String smstypeName) {
        this.smstypeName = smstypeName == null ? null : smstypeName.trim();
    }

    public Integer getSmstypeFronttime() {
        return smstypeFronttime;
    }

    public void setSmstypeFronttime(Integer smstypeFronttime) {
        this.smstypeFronttime = smstypeFronttime;
    }

    public String getSmstypeContent() {
        return smstypeContent;
    }

    public void setSmstypeContent(String smstypeContent) {
        this.smstypeContent = smstypeContent == null ? null : smstypeContent.trim();
    }

    public Integer getSmstypeStatus() {
        return smstypeStatus;
    }

    public void setSmstypeStatus(Integer smstypeStatus) {
        this.smstypeStatus = smstypeStatus;
    }

    public String getSmstypeCreatetime() {
        return smstypeCreatetime;
    }

    public void setSmstypeCreatetime(String smstypeCreatetime) {
        this.smstypeCreatetime = smstypeCreatetime == null ? null : smstypeCreatetime.trim();
    }

    public String getSmstypeMotifytime() {
        return smstypeMotifytime;
    }

    public void setSmstypeMotifytime(String smstypeMotifytime) {
        this.smstypeMotifytime = smstypeMotifytime == null ? null : smstypeMotifytime.trim();
    }

	public MlbackSmstype() {
		super();
	}

	public MlbackSmstype(Integer smstypeId, String smstypeName, Integer smstypeFronttime, String smstypeContent,
			Integer smstypeStatus, String smstypeCreatetime, String smstypeMotifytime) {
		super();
		this.smstypeId = smstypeId;
		this.smstypeName = smstypeName;
		this.smstypeFronttime = smstypeFronttime;
		this.smstypeContent = smstypeContent;
		this.smstypeStatus = smstypeStatus;
		this.smstypeCreatetime = smstypeCreatetime;
		this.smstypeMotifytime = smstypeMotifytime;
	}

	@Override
	public String toString() {
		return "MlbackSmstype [smstypeId=" + smstypeId + ", smstypeName=" + smstypeName + ", smstypeFronttime="
				+ smstypeFronttime + ", smstypeContent=" + smstypeContent + ", smstypeStatus=" + smstypeStatus
				+ ", smstypeCreatetime=" + smstypeCreatetime + ", smstypeMotifytime=" + smstypeMotifytime + "]";
	}
    
}