<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
<!-- fb 认证
<meta name="facebook-domain-verification" content="nzkbta5il40gcwp82e2cbzn6452qja" />
 -->
<!-- xu 认证
<meta name="msvalidate.01" content="5490B8CBC84E3C9D7F95D2FE93A263BC" /> -->
<!-- google-site-verification 徐验证所需 声明网站
<meta name="google-site-verification" content="SAL4KVmok7Gucsll1buOMRYO2RQVPPNdF163maxOVSU" /> -->
<!-- pinterest 认证
<meta name="p:domain_verify" content="3e915d33b453f7bbe46ef399876e0406"/> -->
<!-- icon -->
<link rel="icon" sizes="120x120" href="${APP_PATH }/static/apple/micon1.png">
<link rel="apple-touch-icon" sizes="120x120" href="${APP_PATH }/static/apple/micon1.png">
<link rel="apple-touch-icon" sizes="180x180" href="${APP_PATH }/static/apple/micon2.png">
<script>
//tidio widget
function addTidio(){var script=document.createElement("script");script.async=!0,script.src=ml.tidio,document.body.appendChild(script)}
/* varient */
var timer = null, timeStart = Date.now(), mapSet = {}, mapItems = {}, optionObj = {}, optionIdArr = [], emailPattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
var storage = window.localStorage;
var ml = {
	//test的fbid googleid tidio
	facebook_id: '221174955835797', // test
	google_id: 'UA-148324397-1', // test
	tidio: '//code.tidio.co/sjcpaqy3xxtkt935ucnyf2gxv1zuh9us.js',
	// pinterest test
	pinterest_id: '2613999865733',
	pinterest_email: 'lvzhenbang@outlook.com',
	pinterest_hash_email: 'a6243724a31979a66daa0edc9aa348e44146a59cbe981e0c1139f990d12cd64d',

	//www.megalook.com的fbid googleid tidio
	//facebook_id: '667403967094866', // megalook
	//google_id: 'UA-145178467-1', // megalook
	//tidio: '//code.tidio.co/sjcpaqy3xxtkt935ucnyf2gxv1zuh9us.js', 
	// pinterest megalook
	//pinterest_id: '2612741055158',
	//pinterest_email: 'phannah0303@gmail.com',
	//pinterest_hash_email: 'dc231ca84370402a003d5fbab542f06bcdf55a056ab1fcbea56eaab2e21dec7a',
	
	//www.huashuohair.com的fbid googleid tidio
	//facebook_id: '246433859565492', // huashuohair
	// google_id: 'UA-128377932-1', // huashuohair
	// "tidio": '//code.tidio.co/folzahtp5vdopiwathysfiyz75dk5vnm.js', 
	area_one: 'top-selling',
	area_two: 'Highly-Recommend',
};
</script>