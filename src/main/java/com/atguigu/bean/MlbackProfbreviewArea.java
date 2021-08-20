package com.atguigu.bean;

public class MlbackProfbreviewArea {
    private Integer profbreviewAreaId;

    private Integer profbreviewAreaPid;

    private String profbreviewAreaPseo;

    private Integer profbreviewSupercateId;

    private String profbreviewSupercateName;

    private Integer profbreviewAreaStatus;

    private String profbreviewAreaCreatetime;

    private String profbreviewAreaMotifytime;

    private String profbreviewAreaDesc;

    public Integer getProfbreviewAreaId() {
        return profbreviewAreaId;
    }

    public void setProfbreviewAreaId(Integer profbreviewAreaId) {
        this.profbreviewAreaId = profbreviewAreaId;
    }

    public Integer getProfbreviewAreaPid() {
        return profbreviewAreaPid;
    }

    public void setProfbreviewAreaPid(Integer profbreviewAreaPid) {
        this.profbreviewAreaPid = profbreviewAreaPid;
    }

    public String getProfbreviewAreaPseo() {
        return profbreviewAreaPseo;
    }

    public void setProfbreviewAreaPseo(String profbreviewAreaPseo) {
        this.profbreviewAreaPseo = profbreviewAreaPseo == null ? null : profbreviewAreaPseo.trim();
    }

    public Integer getProfbreviewSupercateId() {
		return profbreviewSupercateId;
	}

	public void setProfbreviewSupercateId(Integer profbreviewSupercateId) {
		this.profbreviewSupercateId = profbreviewSupercateId;
	}

	public String getProfbreviewSupercateName() {
		return profbreviewSupercateName;
	}

	public void setProfbreviewSupercateName(String profbreviewSupercateName) {
		this.profbreviewSupercateName = profbreviewSupercateName == null ? null : profbreviewSupercateName.trim();
	}

	public Integer getProfbreviewAreaStatus() {
        return profbreviewAreaStatus;
    }

    public void setProfbreviewAreaStatus(Integer profbreviewAreaStatus) {
        this.profbreviewAreaStatus = profbreviewAreaStatus;
    }

    public String getProfbreviewAreaCreatetime() {
        return profbreviewAreaCreatetime;
    }

    public void setProfbreviewAreaCreatetime(String profbreviewAreaCreatetime) {
        this.profbreviewAreaCreatetime = profbreviewAreaCreatetime == null ? null : profbreviewAreaCreatetime.trim();
    }

    public String getProfbreviewAreaMotifytime() {
        return profbreviewAreaMotifytime;
    }

    public void setProfbreviewAreaMotifytime(String profbreviewAreaMotifytime) {
        this.profbreviewAreaMotifytime = profbreviewAreaMotifytime == null ? null : profbreviewAreaMotifytime.trim();
    }

    public String getProfbreviewAreaDesc() {
        return profbreviewAreaDesc;
    }

    public void setProfbreviewAreaDesc(String profbreviewAreaDesc) {
        this.profbreviewAreaDesc = profbreviewAreaDesc == null ? null : profbreviewAreaDesc.trim();
    }

	public MlbackProfbreviewArea() {
		super();
	}

	public MlbackProfbreviewArea(Integer profbreviewAreaId, Integer profbreviewAreaPid, String profbreviewAreaPseo,
			Integer profbreviewSupercateId, String profbreviewSupercateName, Integer profbreviewAreaStatus,
			String profbreviewAreaCreatetime, String profbreviewAreaMotifytime, String profbreviewAreaDesc) {
		super();
		this.profbreviewAreaId = profbreviewAreaId;
		this.profbreviewAreaPid = profbreviewAreaPid;
		this.profbreviewAreaPseo = profbreviewAreaPseo;
		this.profbreviewSupercateId = profbreviewSupercateId;
		this.profbreviewSupercateName = profbreviewSupercateName;
		this.profbreviewAreaStatus = profbreviewAreaStatus;
		this.profbreviewAreaCreatetime = profbreviewAreaCreatetime;
		this.profbreviewAreaMotifytime = profbreviewAreaMotifytime;
		this.profbreviewAreaDesc = profbreviewAreaDesc;
	}

	@Override
	public String toString() {
		return "MlbackProfbreviewArea [profbreviewAreaId=" + profbreviewAreaId + ", profbreviewAreaPid="
				+ profbreviewAreaPid + ", profbreviewAreaPseo=" + profbreviewAreaPseo + ", profbreviewSupercateId="
				+ profbreviewSupercateId + ", profbreviewSupercateName=" + profbreviewSupercateName
				+ ", profbreviewAreaStatus=" + profbreviewAreaStatus + ", profbreviewAreaCreatetime="
				+ profbreviewAreaCreatetime + ", profbreviewAreaMotifytime=" + profbreviewAreaMotifytime
				+ ", profbreviewAreaDesc=" + profbreviewAreaDesc + "]";
	}

}