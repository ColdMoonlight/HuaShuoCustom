<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<!DOCTYPE html>
<!--[if IE 8]><html class="no-js lt-ie9" lang="en" xmlns:og="http://ogp.me/ns#" xmlns:fb="http://www.facebook.com/2008/fbml"> <![endif]-->
<!--[if IE 9 ]><html class="ie9 no-js" xmlns:og="http://ogp.me/ns#" xmlns:fb="http://www.facebook.com/2008/fbml"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html class="no-js" xmlns:og="http://ogp.me/ns#" xmlns:fb="http://www.facebook.com/2008/fbml"> <!--<![endif]-->
	<head>
		<jsp:include page="WEB-INF/views/portal/common/config-front.jsp" flush="true"></jsp:include>
		<title>${ map.index_title }</title>
		<meta name="keywords" content="${ map.index_keywords }" />
		<meta name="description" content="${ map.index_description }">
	    <!-- google rule -->
		<meta name="robots" content="INDEX,FOLLOW">
	    <link rel="alternate" href="${ map.website_domain }/" hreflang="en-us" />
		<link rel="canonical" href="${ map.website_domain }/" />
		<!-- socail meta -->
		<meta name="twitter:site" content="@${ map.website_name }">
		<meta name="twitter:card" content="summary">
		<meta name="twitter:title" content="${ map.index_title }">
		<meta name="twitter:description" content="${ map.index_description }">
		<meta property="og:type" content="website">
		<meta property="og:title" content="${ map.index_title }">
		<meta property="og:url" content="${ map.website_domain }/">
		<meta property="og:site_name" content="${ map.website_name }">
		<meta property="og:image" content="${ map.website_domain }/static/apple/micon1.png" />
		<jsp:include page="WEB-INF/views/portal/common/processor.jsp" flush="true"></jsp:include>
		<jsp:include page="WEB-INF/views/portal/common/header.jsp" flush="true"></jsp:include>
		<jsp:include page="WEB-INF/views/portal/common/swiper.jsp" flush="true"></jsp:include>
		<style> main { margin: 0; background-color: #f5f5f5;}</style>
	</head>
	<body>
		<jsp:include page="WEB-INF/views/portal/layout/header/header.jsp" flush="true"></jsp:include>
		<!-- main start -->
			<div id="main-body" class="main-body"><div id="init-loading"></div></div>
			<div id="showAreaThree" class="showarea showAreaThree"></div>
		<!-- main end -->
		<!-- footer nav -->
		<jsp:include page="WEB-INF/views/portal/layout/footer/footer.jsp" flush="true"></jsp:include>

		<!-- common script -->
		<script src="${APP_PATH}/static/common/jquery.min.js"></script>
		<script>function getCarouselData(area,async,callback){$.ajax({url:"${APP_PATH}/MlbackSlides/getMlbackSlidListByArea",data:JSON.stringify({slideArea:area}),dataType:"json",contentType:"application/json",type:"post",async:async,success:function(data){100==data.code&&callback&&callback(data.extend.mlbackSlideList)}})}function debounce(callback,delay){var timeEnd;delay=delay||100,Date.now()-timeStart>delay&&clearTimeout(timer),timer=setTimeout((function(){callback&&callback()}),delay)}</script>
		<!-- lazy -->
		<script>!function(t,e){"object"==typeof exports?module.exports=e(t):"function"==typeof define&&define.amd?define([],e):t.LazyLoad=e(t)}("undefined"!=typeof global?global:this.window||this.global,function(t){"use strict";function e(t,e){this.settings=o(r,e||{}),this.images=t||document.querySelectorAll(this.settings.selector),this.observer=null,this.init()}"function"==typeof define&&define.amd&&(t=window);const r={src:"data-src",srcset:"data-srcset",selector:".lazyload",root:null,rootMargin:"0px",threshold:0},o=function(){let t={},e=!1,r=0,s=arguments.length;for("[object Boolean]"===Object.prototype.toString.call(arguments[0])&&(e=arguments[0],r++);r<s;r++)!function(r){for(let s in r)Object.prototype.hasOwnProperty.call(r,s)&&(e&&"[object Object]"===Object.prototype.toString.call(r[s])?t[s]=o(!0,t[s],r[s]):t[s]=r[s])}(arguments[r]);return t};if(e.prototype={init:function(){if(!t.IntersectionObserver)return void this.loadImages();let e=this,r={root:this.settings.root,rootMargin:this.settings.rootMargin,threshold:[this.settings.threshold]};this.observer=new IntersectionObserver(function(t){Array.prototype.forEach.call(t,function(t){if(t.isIntersecting){e.observer.unobserve(t.target);let r=t.target.getAttribute(e.settings.src),o=t.target.getAttribute(e.settings.srcset),s=new Image;s.src=r,s.onload=function(e){var s=e.target.width,i=e.target.height;"img"===t.target.tagName.toLowerCase()?(r&&(t.target.src=r),o&&(t.target.srcset=o)):t.target.style.backgroundImage="url("+r+")",t.target.width=s,t.target.height=i,t.target.classList.add("loaded")},s.onerror=function(){}}})},r),Array.prototype.forEach.call(this.images,function(t){e.observer.observe(t)})},loadAndDestroy:function(){this.settings&&(this.loadImages(),this.destroy())},loadImages:function(){if(!this.settings)return;let t=this;Array.prototype.forEach.call(this.images,function(e){let r=e.getAttribute(t.settings.src),o=e.getAttribute(t.settings.srcset);"img"===e.tagName.toLowerCase()?(r&&(e.src=r),o&&(e.srcset=o)):e.style.backgroundImage="url('"+r+"')"})},destroy:function(){this.settings&&(this.observer.disconnect(),this.settings=null)}},t.lazyload=function(t,r){return new e(t,r)},t.jQuery){const r=t.jQuery;r.fn.lazyload=function(t){return(t=t||{}).attribute=t.attribute||"data-src",new e(r.makeArray(this),t),this}}return e});</script>
		<!-- swiper script -->
		<script src="${APP_PATH}/static/common/swiper/swiper.min.js"></script>
		<!-- common footer script -->
		<jsp:include page="WEB-INF/views/portal/common/footer.jsp" flush="true"></jsp:include>
		<!-- header script  -->
		<jsp:include page="WEB-INF/views/portal/layout/header/header-script.min.jsp" flush="true"></jsp:include>
		<!-- index script -->
		<jsp:include page="index-script.jsp" flush="true"></jsp:include>
		<!-- footer script -->
		<jsp:include page="WEB-INF/views/portal/layout/footer/footer-script.min.jsp" flush="true"></jsp:include>
		<!-- lottery -->
		<jsp:include page="WEB-INF/views/portal/layout/lottery.jsp" flush="true"></jsp:include>
		<script> addTidio(); </script>
	</body>
</html>