<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%
Map<String, String> map = new HashMap<String, String>();
/* website domain */
map.put("website_domain", "https://www.megalook.com");
map.put("website_name", "megalookhair"); // 小写

/* tidio */
map.put("tidio", "//code.tidio.co/sjcpaqy3xxtkt935ucnyf2gxv1zuh9us.js");

/* facebook */
map.put("fb_verify", "nzkbta5il40gcwp82e2cbzn6452qja");
map.put("facebook_id", "221174955835797");

/* microsoft */
map.put("ms_verify", "5490B8CBC84E3C9D7F95D2FE93A263BC");

/* google */
map.put("google_verfiy", "SAL4KVmok7Gucsll1buOMRYO2RQVPPNdF163maxOVSU");
map.put("google_id", "UA-148324397-1");

/* pinterest */
map.put("pin_verfiy", "3e915d33b453f7bbe46ef399876e0406");
map.put("pin_id", "2613999865733");
map.put("pin_email", "lvzhenbang@outlook.com");
map.put("pin_hash_email", "a6243724a31979a66daa0edc9aa348e44146a59cbe981e0c1139f990d12cd64d");

/* home area block */
map.put("area_one", "top-selling");
map.put("area_two", "Highly-Recommend");

/* SEO Index */
map.put("index_title", "Megalook Hair, Virgin Remy Human Hair Wigs, Lace Front Wigs, Best Wigs | megalook.com");
map.put("index_keywords", "Lace Front Wigs, Human Hair Extensions,Brazilian Virgin Hair,Affordable Human Hair");
map.put("index_description", "Megalook Hair Official Website Sells Best Virgin Hair, Full Lace Wig, Transparent Lace Wig, 360 Lace Wig, Frontal Lace Wig, Closure Lace Wig, Bundles with Closure, Bundles with Frontal, Free Wig Making Service. Hottest Products, Free Gifts, Free & Fast Shipping. No Shedding, No Tangles, Save Big! Lowest Price For U.");

/* SEO cart list */
map.put("cart_title", "Megalook Hair Cart List | megalook.com");
map.put("cart_keywords", "Lace Front Wigs, Human Hair Extensions,Brazilian Virgin Hair,Affordable Human Hair");
map.put("cart_description", "Megalook Hair Official Website Sells Best Virgin Hair, Full Lace Wig, Transparent Lace Wig, 360 Lace Wig, Frontal Lace Wig, Closure Lace Wig, Bundles with Closure, Bundles with Frontal, Free Wig Making Service. Hottest Products, Free Gifts, Free & Fast Shipping. No Shedding, No Tangles, Save Big! Lowest Price For U.");

/* SEO checkout */
map.put("checkout_title", "Checkout | megalook.com");
map.put("checkout_keywords", "Lace Front Wigs, Human Hair Extensions,Brazilian Virgin Hair,Affordable Human Hair");
map.put("checkout_description", "Megalook Hair Official Website Sells Best Virgin Hair, Full Lace Wig, Transparent Lace Wig, 360 Lace Wig, Frontal Lace Wig, Closure Lace Wig, Bundles with Closure, Bundles with Frontal, Free Wig Making Service. Hottest Products, Free Gifts, Free & Fast Shipping. No Shedding, No Tangles, Save Big! Lowest Price For U.");

/* SEO pay-success */
map.put("pay_title", "Payment Success | megalook.com");
map.put("pay_keywords", "Lace Front Wigs, Human Hair Extensions,Brazilian Virgin Hair,Affordable Human Hair");
map.put("pay_description", "Megalook Hair Official Website Sells Best Virgin Hair, Full Lace Wig, Transparent Lace Wig, 360 Lace Wig, Frontal Lace Wig, Closure Lace Wig, Bundles with Closure, Bundles with Frontal, Free Wig Making Service. Hottest Products, Free Gifts, Free & Fast Shipping. No Shedding, No Tangles, Save Big! Lowest Price For U.");

/* SEO search-product result */
map.put("search_product_title", "Search Product List | megalook.com");
map.put("search_product_keywords", "Lace Front Wigs, Human Hair Extensions,Brazilian Virgin Hair,Affordable Human Hair");
map.put("search_product_description", "Megalook Hair Official Website Sells Best Virgin Hair, Full Lace Wig, Transparent Lace Wig, 360 Lace Wig, Frontal Lace Wig, Closure Lace Wig, Bundles with Closure, Bundles with Frontal, Free Wig Making Service. Hottest Products, Free Gifts, Free & Fast Shipping. No Shedding, No Tangles, Save Big! Lowest Price For U.");

/* follow us */
map.put("fl_instagram", "https://www.instagram.com/megalookhair/");
map.put("fl_youtube", "https://www.youtube.com/channel/UCbbrYL1KabTMlXFmQhFWtmw?view_as=subscriber");
map.put("fl_facebook", "https://www.facebook.com/MegalookHairCompany/");
map.put("fl_pinterest", "https://www.pinterest.com/megalookhair1");
map.put("fl_snapchat", "https://www.snapchat.com/add/megalook-hair");

/* product share */
map.put("sh_youtube", "https://www.youtube.com/channel/UCbbrYL1KabTMlXFmQhFWtmw?view_as=subscriber");
map.put("sh_instagram", "https://www.instagram.com/megalookhair/");
map.put("sh_facebook", "https://www.facebook.com/sharer/sharer.php?u=");
map.put("sh_pinterest", "https://www.pinterest.com/pin/create/button/?url=");
map.put("sh_wahtsapp", "https://api.whatsapp.com/send?text=");

request.setAttribute("map",map);
%>