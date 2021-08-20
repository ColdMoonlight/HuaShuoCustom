package com.atguigu.vo;

public class MoneyRate {
	
	private String MoneyRateCode;

    private String MoneyRate;

	public String getMoneyRateCode() {
		return MoneyRateCode;
	}

	public void setMoneyRateCode(String moneyRateCode) {
		MoneyRateCode = moneyRateCode;
	}

	public String getMoneyRate() {
		return MoneyRate;
	}

	public void setMoneyRate(String moneyRate) {
		MoneyRate = moneyRate;
	}

	public MoneyRate(String moneyRateCode, String moneyRate) {
		super();
		MoneyRateCode = moneyRateCode;
		MoneyRate = moneyRate;
	}

	public MoneyRate() {
		super();
	}

	@Override
	public String toString() {
		return "MoneyRate [MoneyRateCode=" + MoneyRateCode + ", MoneyRate=" + MoneyRate + "]";
	}

}
