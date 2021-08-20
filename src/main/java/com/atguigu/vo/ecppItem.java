package com.atguigu.vo;

public class ecppItem {
	
    private String goods_sn;//产品编码

    private String goods_name;//产品名称

    private Integer goods_qty;//产品数量
    
    private String goods_price;//产品价格

	public String getGoods_sn() {
		return goods_sn;
	}

	public void setGoods_sn(String goods_sn) {
		this.goods_sn = goods_sn;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public Integer getGoods_qty() {
		return goods_qty;
	}

	public void setGoods_qty(Integer goods_qty) {
		this.goods_qty = goods_qty;
	}

	public String getGoods_price() {
		return goods_price;
	}

	public void setGoods_price(String goods_price) {
		this.goods_price = goods_price;
	}

	public ecppItem() {
		super();
	}
	
	public ecppItem(String goods_sn, String goods_name, Integer goods_qty, String goods_price) {
		super();
		this.goods_sn = goods_sn;
		this.goods_name = goods_name;
		this.goods_qty = goods_qty;
		this.goods_price = goods_price;
	}

	@Override
	public String toString() {
		return "ecppItem [goods_sn=" + goods_sn + ", goods_name=" + goods_name + ", goods_qty=" + goods_qty
				+ ", goods_price=" + goods_price + "]";
	}

}
