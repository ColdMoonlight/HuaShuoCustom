package com.atguigu.bean;

public class UrlCount {
	
	private String UrlString;
	private String UrlStringNum;
	private String searchStartTime;
	private String searchEndTime;

	public String getUrlString() {
		return UrlString;
	}

	public void setUrlString(String urlString) {
		UrlString = urlString;
	}

	public String getUrlStringNum() {
		return UrlStringNum;
	}

	public void setUrlStringNum(String urlStringNum) {
		UrlStringNum = urlStringNum;
	}

	public String getSearchStartTime() {
		return searchStartTime;
	}

	public void setSearchStartTime(String searchStartTime) {
		this.searchStartTime = searchStartTime;
	}

	public String getSearchEndTime() {
		return searchEndTime;
	}

	public void setSearchEndTime(String searchEndTime) {
		this.searchEndTime = searchEndTime;
	}

	public UrlCount() {
		super();
	}

	public UrlCount(String urlString, String urlStringNum, Integer urlStringType, String searchStartTime,
			String searchEndTime) {
		super();
		UrlString = urlString;
		UrlStringNum = urlStringNum;
		this.searchStartTime = searchStartTime;
		this.searchEndTime = searchEndTime;
	}

	@Override
	public String toString() {
		return "UrlCount [UrlString=" + UrlString + ", UrlStringNum=" + UrlStringNum + ", searchStartTime="
				+ searchStartTime + ", searchEndTime=" + searchEndTime + "]";
	}

}
