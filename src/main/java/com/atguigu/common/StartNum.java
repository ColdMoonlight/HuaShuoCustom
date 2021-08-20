package com.atguigu.common;

public class StartNum {
	
	private Integer StartNum;

    private Integer startCount;

	public Integer getStartNum() {
		return StartNum;
	}

	public void setStartNum(Integer startNum) {
		StartNum = startNum;
	}

	public Integer getStartCount() {
		return startCount;
	}

	public void setStartCount(Integer startCount) {
		this.startCount = startCount;
	}

	public StartNum(Integer startNum, Integer startCount) {
		super();
		StartNum = startNum;
		this.startCount = startCount;
	}

	public StartNum() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "StartNum [StartNum=" + StartNum + ", startCount=" + startCount + "]";
	}
    
}
