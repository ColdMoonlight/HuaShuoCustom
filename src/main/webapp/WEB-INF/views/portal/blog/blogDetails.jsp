<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<!DOCTYPE html>
<html>
<head>
	<title>${sessionScope.blogMetaTitle}</title>	
    <meta name="keyword" content="${sessionScope.blogMetaKeywords}">
    <meta id="ml-des" name="description" content="${sessionScope.blogMeteDesc}">
    <!-- google rule -->
	<meta name="robots" content="INDEX,FOLLOW">
    <link rel="alternate" href="${ map.website_domain }/blog/<%=request.getParameter("blogSeoname") %>.html" hreflang="en-us" />
	<link rel="canonical" href="${ map.website_domain }/blog/<%=request.getParameter("blogSeoname") %>.html" />
	<!-- socail meta -->
	<meta name="twitter:site" content="@${ map.website_name }">
	<meta name="twitter:card" content="summary">
	<meta name="twitter:title" content="${sessionScope.blogMetaTitle}">
	<meta name="twitter:description" content="${sessionScope.blogMeteDesc}">
	<meta property="og:type" content="website">
	<meta property="og:title" content="${sessionScope.blogMetaTitle}">
	<meta property="og:url" content="${ map.website_domain }/blog/<%=request.getParameter("blogSeoname") %>.html">
	<meta property="og:site_name" content="${ map.website_name }">
	<meta property="og:image" content="${ map.website_domain }/static/apple/micon1.png" />
	<jsp:include page="../common/processor.jsp" flush="true"></jsp:include>
	<jsp:include page="../common/header.jsp" flush="true"></jsp:include>
	<style>
		.article { max-width: 1330px; margin: 0 auto; }
		.article-time { display: flex; align-items: center; }
		.article-time .text { margin-left: 1rrem; }
		@media only screen and (min-width: 576px) {
			.article-title { text-align: left; }
			.article-time { padding: 0 1.5rem; font-size: 1rem; }
			.article-time .text { margin-left: 1rem; }
		}

		@media only screen and (max-width: 575px) {
			.article-content iframe, .article-content img{ width: 100%; }
			.article-time { padding: 0 1rem; font-size: .875rem; }
			.article-time .text { margin-left: .5rem; }
		}
	</style>
</head>
<body>
	<jsp:include page="../layout/header/header.jsp" flush="true"></jsp:include>
	<!-- main start -->
		<div class="container"><div id="init-loading"></div></div>
	<!-- main end -->
	<!-- footer nav -->
	<jsp:include page="../layout/footer/footer.jsp" flush="true"></jsp:include>

	<!-- common script -->
	<script src="${APP_PATH}/static/common/jquery.min.js"></script>
	<script>function getCarouselData(area,async,callback){$.ajax({url:"${APP_PATH}/MlbackSlides/getMlbackSlidListByArea",data:JSON.stringify({slideArea:area}),dataType:"json",contentType:"application/json",type:"post",async:async,success:function(data){100==data.code&&callback&&callback(data.extend.mlbackSlideList)}})}function debounce(callback,delay){var timeEnd;delay=delay||100,Date.now()-timeStart>delay&&clearTimeout(timer),timer=setTimeout((function(){callback&&callback()}),delay)}</script>
	<!-- lazy -->
	<script>!function(t,e){"object"==typeof exports?module.exports=e(t):"function"==typeof define&&define.amd?define([],e):t.LazyLoad=e(t)}("undefined"!=typeof global?global:this.window||this.global,function(t){"use strict";function e(t,e){this.settings=o(r,e||{}),this.images=t||document.querySelectorAll(this.settings.selector),this.observer=null,this.init()}"function"==typeof define&&define.amd&&(t=window);const r={src:"data-src",srcset:"data-srcset",selector:".lazyload",root:null,rootMargin:"0px",threshold:0},o=function(){let t={},e=!1,r=0,s=arguments.length;for("[object Boolean]"===Object.prototype.toString.call(arguments[0])&&(e=arguments[0],r++);r<s;r++)!function(r){for(let s in r)Object.prototype.hasOwnProperty.call(r,s)&&(e&&"[object Object]"===Object.prototype.toString.call(r[s])?t[s]=o(!0,t[s],r[s]):t[s]=r[s])}(arguments[r]);return t};if(e.prototype={init:function(){if(!t.IntersectionObserver)return void this.loadImages();let e=this,r={root:this.settings.root,rootMargin:this.settings.rootMargin,threshold:[this.settings.threshold]};this.observer=new IntersectionObserver(function(t){Array.prototype.forEach.call(t,function(t){if(t.isIntersecting){e.observer.unobserve(t.target);let r=t.target.getAttribute(e.settings.src),o=t.target.getAttribute(e.settings.srcset),s=new Image;s.src=r,s.onload=function(e){var s=e.target.width,i=e.target.height;"img"===t.target.tagName.toLowerCase()?(r&&(t.target.src=r),o&&(t.target.srcset=o)):t.target.style.backgroundImage="url("+r+")",t.target.width=s,t.target.height=i,t.target.classList.add("loaded")},s.onerror=function(){}}})},r),Array.prototype.forEach.call(this.images,function(t){e.observer.observe(t)})},loadAndDestroy:function(){this.settings&&(this.loadImages(),this.destroy())},loadImages:function(){if(!this.settings)return;let t=this;Array.prototype.forEach.call(this.images,function(e){let r=e.getAttribute(t.settings.src),o=e.getAttribute(t.settings.srcset);"img"===e.tagName.toLowerCase()?(r&&(e.src=r),o&&(e.srcset=o)):e.style.backgroundImage="url('"+r+"')"})},destroy:function(){this.settings&&(this.observer.disconnect(),this.settings=null)}},t.lazyload=function(t,r){return new e(t,r)},t.jQuery){const r=t.jQuery;r.fn.lazyload=function(t){return(t=t||{}).attribute=t.attribute||"data-src",new e(r.makeArray(this),t),this}}return e});</script>
	<!-- common footer script -->
	<jsp:include page="../common/footer.jsp" flush="true"></jsp:include>
	<!-- header script  -->
	<jsp:include page="../layout/header/header-script.min.jsp" flush="true"></jsp:include>
	<script>
		$.ajax({
			url: "${APP_PATH}/MlbackBlog/getOneMlbackBlogDetail",
			type: "post",
			data: JSON.stringify({"blogId": '${sessionScope.blogDetailId}'}),
			dataType: "json",
			contentType: 'application/json',
			success: function (data) {
				if (data.code == 100) {
					renderBlog(data.extend.MlbackBlogOne);
				} else {
					refreshPageModal();
				}
			},
			error: function () {
				refreshPageModal();
			}
		});
		function getNewFormatTime(time) {
			var monthMap = {0:"JAN",1:"FEB",2:"MAR",3:"APR",4:"MAY",5:"JUN",6:"JUL",7:"AUG",8:"SEP",9:"OCT",10:"NOV",11:"DEC"};
			if (time) {
				var newTime = time.split(' ')[0].split('-');
				return monthMap[newTime[1] - 1] + ' ' + newTime[2] + ', ' + newTime[0];
			} else {
				var newTime = new Date();
				return monthMap[newTime.getMonth()] + ' ' + newTime.getDate() + ', ' + newTime.getFullYear();
			}
		}
		function renderBlog(data) {
			var htmlStr = '';
			htmlStr = '<div class="article">' +
				'<div class="article-title">'+ data.blogName +'</div>' +
				'<div class="article-time"><img src="${APP_PATH}/static/pc/img/blog/date.png" /><span class="text">'+ getNewFormatTime(data.blogAuthorCreatetime) +'</span></div>' +
				'<div class="article-content">'+ data.blogContentrichtext +'</div>' +
			'</div>';
			$('main .container').html(htmlStr);
		}
	</script>
	<!-- footer script -->
	<jsp:include page="../layout/footer/footer-script.min.jsp" flush="true"></jsp:include>
</body>
</html>