package com.atguigu.bean;

public class MlbackCountdown {
    private Integer countdownId;

    private String countdownTitle;
    
    private Integer countdownStatus;

    private String countdownDescription;

    private String countdownStarttime;

    private String countdownEndtime;

    private String countdownCreatetime;

    private String countdownMotifytime;

    public Integer getCountdownId() {
        return countdownId;
    }

    public void setCountdownId(Integer countdownId) {
        this.countdownId = countdownId;
    }

    public String getCountdownTitle() {
        return countdownTitle;
    }

    public void setCountdownTitle(String countdownTitle) {
        this.countdownTitle = countdownTitle == null ? null : countdownTitle.trim();
    }
    
    public Integer getCountdownStatus() {
		return countdownStatus;
	}

	public void setCountdownStatus(Integer countdownStatus) {
		this.countdownStatus = countdownStatus;
	}

	public String getCountdownDescription() {
        return countdownDescription;
    }

    public void setCountdownDescription(String countdownDescription) {
        this.countdownDescription = countdownDescription == null ? null : countdownDescription.trim();
    }

    public String getCountdownStarttime() {
        return countdownStarttime;
    }

    public void setCountdownStarttime(String countdownStarttime) {
        this.countdownStarttime = countdownStarttime == null ? null : countdownStarttime.trim();
    }

    public String getCountdownEndtime() {
        return countdownEndtime;
    }

    public void setCountdownEndtime(String countdownEndtime) {
        this.countdownEndtime = countdownEndtime == null ? null : countdownEndtime.trim();
    }

    public String getCountdownCreatetime() {
        return countdownCreatetime;
    }

    public void setCountdownCreatetime(String countdownCreatetime) {
        this.countdownCreatetime = countdownCreatetime == null ? null : countdownCreatetime.trim();
    }

    public String getCountdownMotifytime() {
        return countdownMotifytime;
    }

    public void setCountdownMotifytime(String countdownMotifytime) {
        this.countdownMotifytime = countdownMotifytime == null ? null : countdownMotifytime.trim();
    }
    
	public MlbackCountdown() {
		super();
	}

	public MlbackCountdown(Integer countdownId, String countdownTitle, Integer countdownStatus,
			String countdownDescription, String countdownStarttime, String countdownEndtime, String countdownCreatetime,
			String countdownMotifytime) {
		super();
		this.countdownId = countdownId;
		this.countdownTitle = countdownTitle;
		this.countdownStatus = countdownStatus;
		this.countdownDescription = countdownDescription;
		this.countdownStarttime = countdownStarttime;
		this.countdownEndtime = countdownEndtime;
		this.countdownCreatetime = countdownCreatetime;
		this.countdownMotifytime = countdownMotifytime;
	}

	@Override
	public String toString() {
		return "MlbackCountdown [countdownId=" + countdownId + ", countdownTitle=" + countdownTitle
				+ ", countdownStatus=" + countdownStatus + ", countdownDescription=" + countdownDescription
				+ ", countdownStarttime=" + countdownStarttime + ", countdownEndtime=" + countdownEndtime
				+ ", countdownCreatetime=" + countdownCreatetime + ", countdownMotifytime=" + countdownMotifytime + "]";
	}
	
}