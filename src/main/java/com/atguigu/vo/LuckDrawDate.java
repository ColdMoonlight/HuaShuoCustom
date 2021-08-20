package com.atguigu.vo;

public class LuckDrawDate {
	
	private Integer LuckDrawCouponId;

    private String LuckDrawCouponCode;

	public Integer getLuckDrawCouponId() {
		return LuckDrawCouponId;
	}

	public void setLuckDrawCouponId(Integer luckDrawCouponId) {
		LuckDrawCouponId = luckDrawCouponId;
	}

	public String getLuckDrawCouponCode() {
		return LuckDrawCouponCode;
	}

	public void setLuckDrawCouponCode(String luckDrawCouponCode) {
		LuckDrawCouponCode = luckDrawCouponCode;
	}

	public LuckDrawDate() {
		super();
	}

	public LuckDrawDate(Integer luckDrawCouponId, String luckDrawCouponCode) {
		super();
		LuckDrawCouponId = luckDrawCouponId;
		LuckDrawCouponCode = luckDrawCouponCode;
	}

	@Override
	public String toString() {
		return "LuckDrawDate [LuckDrawCouponId=" + LuckDrawCouponId + ", LuckDrawCouponCode=" + LuckDrawCouponCode
				+ "]";
	}

}
