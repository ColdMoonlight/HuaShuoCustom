package com.atguigu.bean;

import java.math.BigDecimal;

public class MlfrontCartItem {
    private Integer cartitemId;

    private Integer cartitemUid;

    private Integer cartitemCartId;

    private Integer cartitemStatus;

    private Integer cartitemProductId;

    private String cartitemProductName;
    
    private String cartitemProductSeoName;

    private BigDecimal cartitemProductOriginalprice;

    private String cartitemProductMainimgurl;

    private Integer cartitemProductActoff;

    private String cartitemProductskuIdstr;			//    "cartitemProductskuIdstr": "123,124,125",

    private String cartitemProductskuIdnamestr;		//    "cartitemProductskuIdnamestr": "length,style,service",

    private Integer cartitemProductskuId;			//	4152
    
    private String cartitemProductskuName;			//	"16inch/body/yes",
    
    private String cartitemProductskuCode;			//	10A32H5414Y

    private String cartitemProductskuMoneystr;

    private Integer cartitemProductNumber;

    private String cartitemCreatetime;

    private String cartitemMotifytime;

    public Integer getCartitemId() {
        return cartitemId;
    }

    public void setCartitemId(Integer cartitemId) {
        this.cartitemId = cartitemId;
    }

    public Integer getCartitemUid() {
        return cartitemUid;
    }

    public void setCartitemUid(Integer cartitemUid) {
        this.cartitemUid = cartitemUid;
    }

    public Integer getCartitemCartId() {
        return cartitemCartId;
    }

    public void setCartitemCartId(Integer cartitemCartId) {
        this.cartitemCartId = cartitemCartId;
    }

    public Integer getCartitemStatus() {
        return cartitemStatus;
    }

    public void setCartitemStatus(Integer cartitemStatus) {
        this.cartitemStatus = cartitemStatus;
    }

    public Integer getCartitemProductId() {
        return cartitemProductId;
    }

    public void setCartitemProductId(Integer cartitemProductId) {
        this.cartitemProductId = cartitemProductId;
    }

    public String getCartitemProductName() {
        return cartitemProductName;
    }

    public void setCartitemProductName(String cartitemProductName) {
        this.cartitemProductName = cartitemProductName == null ? null : cartitemProductName.trim();
    }
    
    public String getCartitemProductSeoName() {
		return cartitemProductSeoName;
	}

	public void setCartitemProductSeoName(String cartitemProductSeoName) {
		this.cartitemProductSeoName = cartitemProductSeoName == null ? null : cartitemProductSeoName.trim();
	}

	public BigDecimal getCartitemProductOriginalprice() {
        return cartitemProductOriginalprice;
    }

    public void setCartitemProductOriginalprice(BigDecimal cartitemProductOriginalprice) {
        this.cartitemProductOriginalprice = cartitemProductOriginalprice;
    }

    public String getCartitemProductMainimgurl() {
        return cartitemProductMainimgurl;
    }

    public void setCartitemProductMainimgurl(String cartitemProductMainimgurl) {
        this.cartitemProductMainimgurl = cartitemProductMainimgurl == null ? null : cartitemProductMainimgurl.trim();
    }

    public Integer getCartitemProductActoff() {
        return cartitemProductActoff;
    }

    public void setCartitemProductActoff(Integer cartitemProductActoff) {
        this.cartitemProductActoff = cartitemProductActoff;
    }

    public String getCartitemProductskuIdstr() {
        return cartitemProductskuIdstr;
    }

    public void setCartitemProductskuIdstr(String cartitemProductskuIdstr) {
        this.cartitemProductskuIdstr = cartitemProductskuIdstr == null ? null : cartitemProductskuIdstr.trim();
    }

    public String getCartitemProductskuIdnamestr() {
        return cartitemProductskuIdnamestr;
    }

    public void setCartitemProductskuIdnamestr(String cartitemProductskuIdnamestr) {
        this.cartitemProductskuIdnamestr = cartitemProductskuIdnamestr == null ? null : cartitemProductskuIdnamestr.trim();
    }
    
    public Integer getCartitemProductskuId() {
		return cartitemProductskuId;
	}

	public void setCartitemProductskuId(Integer cartitemProductskuId) {
		this.cartitemProductskuId = cartitemProductskuId;
	}

	public String getCartitemProductskuName() {
		return cartitemProductskuName;
	}

	public void setCartitemProductskuName(String cartitemProductskuName) {
		this.cartitemProductskuName = cartitemProductskuName == null ? null : cartitemProductskuName.trim();
	}

	public String getCartitemProductskuCode() {
		return cartitemProductskuCode;
	}

	public void setCartitemProductskuCode(String cartitemProductskuCode) {
		this.cartitemProductskuCode = cartitemProductskuCode == null ? null : cartitemProductskuCode.trim();
	}

	public String getCartitemProductskuMoneystr() {
        return cartitemProductskuMoneystr;
    }

    public void setCartitemProductskuMoneystr(String cartitemProductskuMoneystr) {
        this.cartitemProductskuMoneystr = cartitemProductskuMoneystr == null ? null : cartitemProductskuMoneystr.trim();
    }

    public Integer getCartitemProductNumber() {
        return cartitemProductNumber;
    }

    public void setCartitemProductNumber(Integer cartitemProductNumber) {
        this.cartitemProductNumber = cartitemProductNumber;
    }

    public String getCartitemCreatetime() {
        return cartitemCreatetime;
    }

    public void setCartitemCreatetime(String cartitemCreatetime) {
        this.cartitemCreatetime = cartitemCreatetime == null ? null : cartitemCreatetime.trim();
    }

    public String getCartitemMotifytime() {
        return cartitemMotifytime;
    }

    public void setCartitemMotifytime(String cartitemMotifytime) {
        this.cartitemMotifytime = cartitemMotifytime == null ? null : cartitemMotifytime.trim();
    }

	public MlfrontCartItem() {
		super();
	}

	public MlfrontCartItem(Integer cartitemId, Integer cartitemUid, Integer cartitemCartId, Integer cartitemStatus,
			Integer cartitemProductId, String cartitemProductName, String cartitemProductSeoName,
			BigDecimal cartitemProductOriginalprice, String cartitemProductMainimgurl, Integer cartitemProductActoff,
			String cartitemProductskuIdstr, String cartitemProductskuIdnamestr, Integer cartitemProductskuId,
			String cartitemProductskuName, String cartitemProductskuCode, String cartitemProductskuMoneystr,
			Integer cartitemProductNumber, String cartitemCreatetime, String cartitemMotifytime) {
		super();
		this.cartitemId = cartitemId;
		this.cartitemUid = cartitemUid;
		this.cartitemCartId = cartitemCartId;
		this.cartitemStatus = cartitemStatus;
		this.cartitemProductId = cartitemProductId;
		this.cartitemProductName = cartitemProductName;
		this.cartitemProductSeoName = cartitemProductSeoName;
		this.cartitemProductOriginalprice = cartitemProductOriginalprice;
		this.cartitemProductMainimgurl = cartitemProductMainimgurl;
		this.cartitemProductActoff = cartitemProductActoff;
		this.cartitemProductskuIdstr = cartitemProductskuIdstr;
		this.cartitemProductskuIdnamestr = cartitemProductskuIdnamestr;
		this.cartitemProductskuId = cartitemProductskuId;
		this.cartitemProductskuName = cartitemProductskuName;
		this.cartitemProductskuCode = cartitemProductskuCode;
		this.cartitemProductskuMoneystr = cartitemProductskuMoneystr;
		this.cartitemProductNumber = cartitemProductNumber;
		this.cartitemCreatetime = cartitemCreatetime;
		this.cartitemMotifytime = cartitemMotifytime;
	}

	@Override
	public String toString() {
		return "MlfrontCartItem [cartitemId=" + cartitemId + ", cartitemUid=" + cartitemUid + ", cartitemCartId="
				+ cartitemCartId + ", cartitemStatus=" + cartitemStatus + ", cartitemProductId=" + cartitemProductId
				+ ", cartitemProductName=" + cartitemProductName + ", cartitemProductSeoName=" + cartitemProductSeoName
				+ ", cartitemProductOriginalprice=" + cartitemProductOriginalprice + ", cartitemProductMainimgurl="
				+ cartitemProductMainimgurl + ", cartitemProductActoff=" + cartitemProductActoff
				+ ", cartitemProductskuIdstr=" + cartitemProductskuIdstr + ", cartitemProductskuIdnamestr="
				+ cartitemProductskuIdnamestr + ", cartitemProductskuId=" + cartitemProductskuId
				+ ", cartitemProductskuName=" + cartitemProductskuName + ", cartitemProductskuCode="
				+ cartitemProductskuCode + ", cartitemProductskuMoneystr=" + cartitemProductskuMoneystr
				+ ", cartitemProductNumber=" + cartitemProductNumber + ", cartitemCreatetime=" + cartitemCreatetime
				+ ", cartitemMotifytime=" + cartitemMotifytime + "]";
	}
	
}