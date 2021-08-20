package com.atguigu.bean;

public class MlbackHtmlEmail {
    private Integer htmlemailId;

    private String htmlemailName;

    private Integer htmlemailStatus;

    private String htmlemailHeadimgurl;

    private String htmlemailTitle;

    private String htmlemailRetrieve;

    private String htmlemailRetrievecode;

    private String htmlemailFive;

    private String htmlemailSix;

    private String htmlemailSeven;

    public Integer getHtmlemailId() {
        return htmlemailId;
    }

    public void setHtmlemailId(Integer htmlemailId) {
        this.htmlemailId = htmlemailId;
    }

    public String getHtmlemailName() {
        return htmlemailName;
    }

    public void setHtmlemailName(String htmlemailName) {
        this.htmlemailName = htmlemailName == null ? null : htmlemailName.trim();
    }

    public Integer getHtmlemailStatus() {
        return htmlemailStatus;
    }

    public void setHtmlemailStatus(Integer htmlemailStatus) {
        this.htmlemailStatus = htmlemailStatus;
    }

    public String getHtmlemailHeadimgurl() {
        return htmlemailHeadimgurl;
    }

    public void setHtmlemailHeadimgurl(String htmlemailHeadimgurl) {
        this.htmlemailHeadimgurl = htmlemailHeadimgurl == null ? null : htmlemailHeadimgurl.trim();
    }

    public String getHtmlemailTitle() {
        return htmlemailTitle;
    }

    public void setHtmlemailTitle(String htmlemailTitle) {
        this.htmlemailTitle = htmlemailTitle == null ? null : htmlemailTitle.trim();
    }

    public String getHtmlemailRetrieve() {
        return htmlemailRetrieve;
    }

    public void setHtmlemailRetrieve(String htmlemailRetrieve) {
        this.htmlemailRetrieve = htmlemailRetrieve == null ? null : htmlemailRetrieve.trim();
    }

    public String getHtmlemailRetrievecode() {
        return htmlemailRetrievecode;
    }

    public void setHtmlemailRetrievecode(String htmlemailRetrievecode) {
        this.htmlemailRetrievecode = htmlemailRetrievecode == null ? null : htmlemailRetrievecode.trim();
    }

    public String getHtmlemailFive() {
        return htmlemailFive;
    }

    public void setHtmlemailFive(String htmlemailFive) {
        this.htmlemailFive = htmlemailFive == null ? null : htmlemailFive.trim();
    }

    public String getHtmlemailSix() {
        return htmlemailSix;
    }

    public void setHtmlemailSix(String htmlemailSix) {
        this.htmlemailSix = htmlemailSix == null ? null : htmlemailSix.trim();
    }

    public String getHtmlemailSeven() {
        return htmlemailSeven;
    }

    public void setHtmlemailSeven(String htmlemailSeven) {
        this.htmlemailSeven = htmlemailSeven == null ? null : htmlemailSeven.trim();
    }

	public MlbackHtmlEmail() {
		super();
	}

	public MlbackHtmlEmail(Integer htmlemailId, String htmlemailName, Integer htmlemailStatus,
			String htmlemailHeadimgurl, String htmlemailTitle, String htmlemailRetrieve, String htmlemailRetrievecode,
			String htmlemailFive, String htmlemailSix, String htmlemailSeven) {
		super();
		this.htmlemailId = htmlemailId;
		this.htmlemailName = htmlemailName;
		this.htmlemailStatus = htmlemailStatus;
		this.htmlemailHeadimgurl = htmlemailHeadimgurl;
		this.htmlemailTitle = htmlemailTitle;
		this.htmlemailRetrieve = htmlemailRetrieve;
		this.htmlemailRetrievecode = htmlemailRetrievecode;
		this.htmlemailFive = htmlemailFive;
		this.htmlemailSix = htmlemailSix;
		this.htmlemailSeven = htmlemailSeven;
	}

	@Override
	public String toString() {
		return "MlbackhtmlEmail [htmlemailId=" + htmlemailId + ", htmlemailName=" + htmlemailName + ", htmlemailStatus="
				+ htmlemailStatus + ", htmlemailHeadimgurl=" + htmlemailHeadimgurl + ", htmlemailTitle="
				+ htmlemailTitle + ", htmlemailRetrieve=" + htmlemailRetrieve + ", htmlemailRetrievecode="
				+ htmlemailRetrievecode + ", htmlemailFive=" + htmlemailFive + ", htmlemailSix=" + htmlemailSix
				+ ", htmlemailSeven=" + htmlemailSeven + "]";
	}
    
}