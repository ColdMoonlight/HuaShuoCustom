package com.atguigu.bean;

public class MlbackFootNav {
	
    private Integer footnavId;
    
    private String footnavSeo;	//路经查询

    private String footnavName;

    private Integer footnavHang;

    private Integer footnavLie;
    
    private Integer footnavClickType;   //类型 
    
    private String footnavClickUrl;

    private String footnavCreatetime;

    private String footnavMotifytime;

    private Integer footnavIfincome;

    private Integer footnavIfshow;

    private String footnavDesc;
    
    private Integer footnavSuperCateId;
    
    private String footnavSuperCateName;

    public Integer getFootnavId() {
        return footnavId;
    }

    public void setFootnavId(Integer footnavId) {
        this.footnavId = footnavId;
    }

    public String getFootnavSeo() {
		return footnavSeo;
	}

	public void setFootnavSeo(String footnavSeo) {
		this.footnavSeo = footnavSeo;
	}

	public String getFootnavName() {
        return footnavName;
    }

    public void setFootnavName(String footnavName) {
        this.footnavName = footnavName == null ? null : footnavName.trim();
    }

    public Integer getFootnavHang() {
        return footnavHang;
    }

    public void setFootnavHang(Integer footnavHang) {
        this.footnavHang = footnavHang;
    }

    public Integer getFootnavLie() {
        return footnavLie;
    }

    public void setFootnavLie(Integer footnavLie) {
        this.footnavLie = footnavLie;
    }
    
	public Integer getFootnavClickType() {
		return footnavClickType;
	}

	public void setFootnavClickType(Integer footnavClickType) {
		this.footnavClickType = footnavClickType;
	}
	
	public String getFootnavClickUrl() {
		return footnavClickUrl;
	}

	public void setFootnavClickUrl(String footnavClickUrl) {
		this.footnavClickUrl = footnavClickUrl == null ? null : footnavClickUrl.trim();
	}

	public String getFootnavCreatetime() {
        return footnavCreatetime;
    }

    public void setFootnavCreatetime(String footnavCreatetime) {
        this.footnavCreatetime = footnavCreatetime == null ? null : footnavCreatetime.trim();
    }

    public String getFootnavMotifytime() {
        return footnavMotifytime;
    }

    public void setFootnavMotifytime(String footnavMotifytime) {
        this.footnavMotifytime = footnavMotifytime == null ? null : footnavMotifytime.trim();
    }

    public Integer getFootnavIfincome() {
        return footnavIfincome;
    }

    public void setFootnavIfincome(Integer footnavIfincome) {
        this.footnavIfincome = footnavIfincome;
    }

    public Integer getFootnavIfshow() {
        return footnavIfshow;
    }

    public void setFootnavIfshow(Integer footnavIfshow) {
        this.footnavIfshow = footnavIfshow;
    }

    public String getFootnavDesc() {
        return footnavDesc;
    }

    public void setFootnavDesc(String footnavDesc) {
        this.footnavDesc = footnavDesc == null ? null : footnavDesc.trim();
    }
    
	public Integer getFootnavSuperCateId() {
		return footnavSuperCateId;
	}

	public void setFootnavSuperCateId(Integer footnavSuperCateId) {
		this.footnavSuperCateId = footnavSuperCateId;
	}

	public String getFootnavSuperCateName() {
		return footnavSuperCateName;
	}

	public void setFootnavSuperCateName(String footnavSuperCateName) {
		this.footnavSuperCateName = footnavSuperCateName == null ? null : footnavSuperCateName.trim();
	}

	public MlbackFootNav() {
		super();
	}

	public MlbackFootNav(Integer footnavId, String footnavSeo, String footnavName, Integer footnavHang,
			Integer footnavLie, Integer footnavClickType, String footnavClickUrl, String footnavCreatetime,
			String footnavMotifytime, Integer footnavIfincome, Integer footnavIfshow, String footnavDesc,
			Integer footnavSuperCateId, String footnavSuperCateName) {
		super();
		this.footnavId = footnavId;
		this.footnavSeo = footnavSeo;
		this.footnavName = footnavName;
		this.footnavHang = footnavHang;
		this.footnavLie = footnavLie;
		this.footnavClickType = footnavClickType;
		this.footnavClickUrl = footnavClickUrl;
		this.footnavCreatetime = footnavCreatetime;
		this.footnavMotifytime = footnavMotifytime;
		this.footnavIfincome = footnavIfincome;
		this.footnavIfshow = footnavIfshow;
		this.footnavDesc = footnavDesc;
		this.footnavSuperCateId = footnavSuperCateId;
		this.footnavSuperCateName = footnavSuperCateName;
	}

	@Override
	public String toString() {
		return "MlbackFootNav [footnavId=" + footnavId + ", footnavSeo=" + footnavSeo + ", footnavName=" + footnavName
				+ ", footnavHang=" + footnavHang + ", footnavLie=" + footnavLie + ", footnavClickType="
				+ footnavClickType + ", footnavClickUrl=" + footnavClickUrl + ", footnavCreatetime=" + footnavCreatetime
				+ ", footnavMotifytime=" + footnavMotifytime + ", footnavIfincome=" + footnavIfincome
				+ ", footnavIfshow=" + footnavIfshow + ", footnavDesc=" + footnavDesc + ", footnavSuperCateId="
				+ footnavSuperCateId + ", footnavSuperCateName=" + footnavSuperCateName + "]";
	}

}