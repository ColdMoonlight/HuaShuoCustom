package com.atguigu.bean;

public class MlbackBlog {
	
    private Integer blogId;

    private String blogSeoname;

    private String blogName;

    private String blogMetaTitle;

    private String blogMetaKeyword;

    private String blogMetaDesc;

    private String blogBannerimg;

    private String blogAuthorCreatetime;

    private Integer blogStatus;
    
    private Integer blogSuperCateid;
    
    private String blogSuperCatename;

    private String blogCreatetime;

    private String blogMotifytime;

    private String blogContentrichtext;

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getBlogSeoname() {
        return blogSeoname;
    }

    public void setBlogSeoname(String blogSeoname) {
        this.blogSeoname = blogSeoname == null ? null : blogSeoname.trim();
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName == null ? null : blogName.trim();
    }

    public String getBlogMetaTitle() {
        return blogMetaTitle;
    }

    public void setBlogMetaTitle(String blogMetaTitle) {
        this.blogMetaTitle = blogMetaTitle == null ? null : blogMetaTitle.trim();
    }

    public String getBlogMetaKeyword() {
        return blogMetaKeyword;
    }

    public void setBlogMetaKeyword(String blogMetaKeyword) {
        this.blogMetaKeyword = blogMetaKeyword == null ? null : blogMetaKeyword.trim();
    }

    public String getBlogMetaDesc() {
        return blogMetaDesc;
    }

    public void setBlogMetaDesc(String blogMetaDesc) {
        this.blogMetaDesc = blogMetaDesc == null ? null : blogMetaDesc.trim();
    }

    public String getBlogBannerimg() {
        return blogBannerimg;
    }

    public void setBlogBannerimg(String blogBannerimg) {
        this.blogBannerimg = blogBannerimg == null ? null : blogBannerimg.trim();
    }

    public String getBlogAuthorCreatetime() {
        return blogAuthorCreatetime;
    }

    public void setBlogAuthorCreatetime(String blogAuthorCreatetime) {
        this.blogAuthorCreatetime = blogAuthorCreatetime == null ? null : blogAuthorCreatetime.trim();
    }

    public Integer getBlogStatus() {
        return blogStatus;
    }

    public void setBlogStatus(Integer blogStatus) {
        this.blogStatus = blogStatus;
    }
    
    public Integer getBlogSuperCateid() {
		return blogSuperCateid;
	}

	public void setBlogSuperCateid(Integer blogSuperCateid) {
		this.blogSuperCateid = blogSuperCateid;
	}

	public String getBlogSuperCatename() {
		return blogSuperCatename;
	}

	public void setBlogSuperCatename(String blogSuperCatename) {
		this.blogSuperCatename = blogSuperCatename == null ? null : blogSuperCatename.trim();
	}

	public String getBlogCreatetime() {
        return blogCreatetime;
    }

    public void setBlogCreatetime(String blogCreatetime) {
        this.blogCreatetime = blogCreatetime == null ? null : blogCreatetime.trim();
    }

    public String getBlogMotifytime() {
        return blogMotifytime;
    }

    public void setBlogMotifytime(String blogMotifytime) {
        this.blogMotifytime = blogMotifytime == null ? null : blogMotifytime.trim();
    }

    public String getBlogContentrichtext() {
        return blogContentrichtext;
    }

    public void setBlogContentrichtext(String blogContentrichtext) {
        this.blogContentrichtext = blogContentrichtext == null ? null : blogContentrichtext.trim();
    }

	public MlbackBlog() {
		super();
	}

	public MlbackBlog(Integer blogId, String blogSeoname, String blogName, String blogMetaTitle, String blogMetaKeyword,
			String blogMetaDesc, String blogBannerimg, String blogAuthorCreatetime, Integer blogStatus,
			Integer blogSuperCateid, String blogSuperCatename, String blogCreatetime, String blogMotifytime,
			String blogContentrichtext) {
		super();
		this.blogId = blogId;
		this.blogSeoname = blogSeoname;
		this.blogName = blogName;
		this.blogMetaTitle = blogMetaTitle;
		this.blogMetaKeyword = blogMetaKeyword;
		this.blogMetaDesc = blogMetaDesc;
		this.blogBannerimg = blogBannerimg;
		this.blogAuthorCreatetime = blogAuthorCreatetime;
		this.blogStatus = blogStatus;
		this.blogSuperCateid = blogSuperCateid;
		this.blogSuperCatename = blogSuperCatename;
		this.blogCreatetime = blogCreatetime;
		this.blogMotifytime = blogMotifytime;
		this.blogContentrichtext = blogContentrichtext;
	}

	@Override
	public String toString() {
		return "MlbackBlog [blogId=" + blogId + ", blogSeoname=" + blogSeoname + ", blogName=" + blogName
				+ ", blogMetaTitle=" + blogMetaTitle + ", blogMetaKeyword=" + blogMetaKeyword + ", blogMetaDesc="
				+ blogMetaDesc + ", blogBannerimg=" + blogBannerimg + ", blogAuthorCreatetime=" + blogAuthorCreatetime
				+ ", blogStatus=" + blogStatus + ", blogSuperCateid=" + blogSuperCateid + ", blogSuperCatename="
				+ blogSuperCatename + ", blogCreatetime=" + blogCreatetime + ", blogMotifytime=" + blogMotifytime
				+ ", blogContentrichtext=" + blogContentrichtext + "]";
	}

}