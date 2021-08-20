package com.atguigu.vo;

public class StringTwoVO {
	
	private String KeyString;
	
	private String KeyValue;

	public String getKeyString() {
		return KeyString;
	}

	public void setKeyString(String keyString) {
		KeyString = keyString;
	}

	public String getKeyValue() {
		return KeyValue;
	}

	public void setKeyValue(String keyValue) {
		KeyValue = keyValue;
	}

	public StringTwoVO() {
		super();
	}

	public StringTwoVO(String keyString, String keyValue) {
		super();
		KeyString = keyString;
		KeyValue = keyValue;
	}

	@Override
	public String toString() {
		return "StringTwoVO [KeyString=" + KeyString + ", KeyValue=" + KeyValue + "]";
	}
	
}
