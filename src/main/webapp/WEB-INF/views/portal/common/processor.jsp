<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>

<!-- fb 认证
<meta name="facebook-domain-verification" content="${ map.fb_verify }" />
 -->
<!-- xu 认证
<meta name="msvalidate.01" content="${ map.ms_verify }" /> -->

<!-- google-site-verification 徐验证所需 声明网站
<meta name="google-site-verification" content="${ map.google_verfiy }" /> -->
<!-- pinterest 认证
<meta name="p:domain_verify" content="${ map.pin_verfiy }"/> -->
<!-- icon -->
<link rel="icon" sizes="120x120" href="${APP_PATH }/static/custom/micon1.png">
<link rel="apple-touch-icon" sizes="120x120" href="${APP_PATH }/static/custom/micon1.png">
<link rel="apple-touch-icon" sizes="180x180" href="${APP_PATH }/static/custom/micon2.png">
<script>
//tidio widget
function addTidio(){var script=document.createElement("script");script.async=!0,script.src='${ map.tidio }',document.body.appendChild(script)}
/* varient */
var timer = null, timeStart = Date.now(), mapSet = {}, mapItems = {}, optionObj = {}, optionIdArr = [], emailPattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
var storage = window.localStorage;
</script>