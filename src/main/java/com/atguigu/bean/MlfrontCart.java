package com.atguigu.bean;

public class MlfrontCart {
    private Integer cartId;

    private Integer cartUid;

    private String cartIp;

    private String cartitemIdstr;

    private Integer cartStatus;

    private String cartCreatetime;

    private String cartMotifytime;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getCartUid() {
        return cartUid;
    }

    public void setCartUid(Integer cartUid) {
        this.cartUid = cartUid;
    }

    public String getCartIp() {
        return cartIp;
    }

    public void setCartIp(String cartIp) {
        this.cartIp = cartIp == null ? null : cartIp.trim();
    }

    public String getCartitemIdstr() {
        return cartitemIdstr;
    }

    public void setCartitemIdstr(String cartitemIdstr) {
        this.cartitemIdstr = cartitemIdstr == null ? null : cartitemIdstr.trim();
    }

    public Integer getCartStatus() {
        return cartStatus;
    }

    public void setCartStatus(Integer cartStatus) {
        this.cartStatus = cartStatus;
    }

    public String getCartCreatetime() {
        return cartCreatetime;
    }

    public void setCartCreatetime(String cartCreatetime) {
        this.cartCreatetime = cartCreatetime == null ? null : cartCreatetime.trim();
    }

    public String getCartMotifytime() {
        return cartMotifytime;
    }

    public void setCartMotifytime(String cartMotifytime) {
        this.cartMotifytime = cartMotifytime == null ? null : cartMotifytime.trim();
    }
}