<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<%-- <jsp:include page="intercept.jsp" flush="true"></jsp:include> --%>
<!-- common css -->
<link href="${APP_PATH}/static/pc/css/main.min.css" rel="stylesheet">
<script>
var fbNoscript, gaScript, pinNoscript;
/* Facebook Pixel Code */
!function(f,b,e,v,n,t,s) {if(f.fbq)return;n=f.fbq=function(){n.callMethod? n.callMethod.apply(n,arguments):n.queue.push(arguments)}; if(!f._fbq)f._fbq=n;n.push=n;n.loaded=!0;n.version='2.0'; n.queue=[];t=b.createElement(e);t.async=!0; t.src=v;s=b.getElementsByTagName(e)[0]; s.parentNode.insertBefore(t,s)}(window, document, 'script', 'https://connect.facebook.net/en_US/fbevents.js');
fbq('init', ml.facebook_id);
!window.productId && fbq('track', 'PageView');
!window.productId && fbq('track', 'ViewContent');
fbNoscript = document.createElement('noscript');
fbNoscript.innerHTML = '<img height="1" width="1" style="display:none" src="https://www.facebook.com/tr?id='+ ml.facebook_id +'&ev=PageView&noscript=1" />';
document.head.appendChild(fbNoscript);
/* Global site tag (gtag.js) */
gaScript=document.createElement('script');gaScript.async=true;gaScript.src='https://www.googletagmanager.com/gtag/js?id='+ml.google_id;
document.head.appendChild(gaScript);
window.dataLayer = window.dataLayer || [];
function gtag(){dataLayer.push(arguments);}
gtag('js', new Date());
gtag('config', ml.google_id);
/* Pinterest Tag */
!function(e){if(!window.pintrk){window.pintrk = function () {window.pintrk.queue.push(Array.prototype.slice.call(arguments))};var n=window.pintrk;n.queue=[],n.version="3.0";var t=document.createElement("script");t.async=!0,t.src=e;var r=document.getElementsByTagName("script")[0];r.parentNode.insertBefore(t,r)}}("https://s.pinimg.com/ct/core.js");
pintrk('load', ml.pinterest_id, {em: ml.pinterest_email});
pintrk('page');
!window.productId && pintrk('track', 'pagevisit');
pinNoscript = document.createElement('noscript');
pinNoscript.innerHTML = '<img height="1" width="1" style="display:none;" alt="" src="https://ct.pinterest.com/v3/?event=init&tid='+ ml.pinterest_id +'&pd[em]='+ ml.pinterest_hash_email +'&noscript=1" />';
document.head.appendChild(pinNoscript);
</script>
<!-- bing Tag
<script>(function(w,d,t,r,u){var f,n,i;w[u]=w[u]||[],f=function(){var o={ti:"25140444"};o.q=w[u],w[u]=new UET(o),w[u].push("pageLoad")},n=d.createElement(t),n.src=r,n.async=1,n.onload=n.onreadystatechange=function(){var s=this.readyState;s&&s!=="loaded"&&s!=="complete"||(f(),n.onload=n.onreadystatechange=null)},i=d.getElementsByTagName(t)[0],i.parentNode.insertBefore(n,i)})(window,document,"script","//bat.bing.com/bat.js","uetq");</script>
 -->
<!-- snapchat
<script>(function(e,t,n){if(!e.snaptr){var a=e.snaptr=function(){a.handleRequest?a.handleRequest.apply(a,arguments):a.queue.push(arguments)};a.queue=[];var s="script";r=t.createElement(s),r.async=!0,r.src=n;var c=t.getElementsByTagName(s)[0];c.parentNode.insertBefore(r,c)}})(window,document,"https://sc-static.net/scevent.min.js"),snaptr("init","a8cfe4e2-6e20-4c56-af5d-1d62bde45547",{user_email:"megalookhairreview@gmail.com"}),snaptr("track","PAGE_VIEW");snaptr('track','VIEW_CONTENT');</script>
 -->
 <!-- snapchat
<script type="text/javascript">
            if(!window._pt_sp_2){
                window._pt_lt = new Date().getTime();
                window._pt_sp_2 = [];
                _pt_sp_2.push("setAccount,4209066a");
                var _protocol =(("https:" == document.location.protocol) ? " https://" : " http://");
                (function() {
                    var atag = document.createElement("script");
                    atag.type = "text/javascript";
                    atag.async = true;
                    atag.src = _protocol + "js.ptengine.cn/4209066a.js";
                    var s = document.getElementsByTagName("script")[0];
                    s.parentNode.insertBefore(atag, s);
                })();
            }
 </script>
  -->