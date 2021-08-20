<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Video | megalook.com</title>
	<meta name="keywords" content="Lace Front Wigs, Human Hair Extensions,Brazilian Virgin Hair,Affordable Human Hair" />
	<meta name="description" content="Megalook Hair Official Website Sells Best Virgin Hair, Full Lace Wig, Transparent Lace Wig, 360 Lace Wig, Frontal Lace Wig, Closure Lace Wig, Bundles with Closure, Bundles with Frontal, Free Wig Making Service. Hottest Products, Free Gifts, Free & Fast Shipping. No Shedding, No Tangles, Save Big! Lowest Price For U.">
	<!-- google rule -->
	<meta name="robots" content="INDEX,FOLLOW">
    <link rel="alternate" href="https://megalook.com/MlbackVideoShowArea/toVideoListPage.html" hreflang="en-us" />
	<link rel="canonical" href="https://megalook.com/MlbackVideoShowArea/toVideoListPage.html" />
	<!-- socail meta -->
	<meta name="twitter:site" content="@megalookhair">
	<meta name="twitter:card" content="summary">
	<meta name="twitter:title" content="Megalook Hair, Virgin Remy Human Hair Wigs, Lace Front Wigs, Best Wigs | megalook.com">
	<meta name="twitter:description" content="Megalook Hair Official Website Sells Best Virgin Hair, Full Lace Wig, Transparent Lace Wig, 360 Lace Wig, Frontal Lace Wig, Closure Lace Wig, Bundles with Closure, Bundles with Frontal, Free Wig Making Service. Hottest Products, Free Gifts, Free & Fast Shipping. No Shedding, No Tangles, Save Big! Lowest Price For U.">
	<meta property="og:type" content="website">
	<meta property="og:title" content="Megalook Hair, Virgin Remy Human Hair Wigs, Lace Front Wigs, Best Wigs | megalook.com">
	<meta property="og:url" content="https://megalook.com/MlbackVideoShowArea/toVideoListPage.html">
	<meta property="og:site_name" content="Megalook Hair">
	<meta property="og:image" content="https://www.megalook.com/static/apple/micon1.png" />
	<jsp:include page="../common/processor.jsp" flush="true"></jsp:include>
	<jsp:include page="../common/header.jsp" flush="true"></jsp:include>
	<style>
		@media only screen and (max-width: 1023px) { main { margin:  0; } }
		
		@media only screen and (min-width: 576px){
			.video-banner2 .pc {
			    padding-top: 26.1%;
			    /* max-height: 500px; */
				display: block;
				opacity: 1;
			}
			.video-banner2 .wap {
				display: none;
				opacity: 0;
			}
		}
		@media only screen and (max-width: 575px){
			.video-banner2 .wap {
			    padding-top: 53.33%;
				display: block;
				opacity: 1;
			}
			.video-banner2 .pc {
				display: none;
				opacity: 0;
			}
		}		
	</style>
</head>
<body>
    <jsp:include page="../layout/header/header.jsp" flush="true"></jsp:include>
	<!-- main start -->
		<!-- video banner -->
		<div class="video-banner">
			<div class="wap lazyload" data-src="${APP_PATH }/static/pc/img/video/sj1.jpg"></div>
			<div class="pc lazyload" data-src="${APP_PATH }/static/pc/img/video/c01.jpg"></div>
		</div>

		<div class="container">
			<!-- video discount -->
			<div class="video-discount">			
				<div class="video-discount-title wap lazyload" data-src="${APP_PATH }/static/pc/img/video/sjbt1.jpg"></div>
				<div class="video-discount-title pc lazyload" data-src="${APP_PATH }/static/pc/img/video/pcbt.jpg"></div>
				<div class="video-discount-body wap lazyload" data-src="${APP_PATH }/static/pc/img/video/sj2.jpg"></div>
				<div class="video-discount-body pc lazyload" data-src="${APP_PATH }/static/pc/img/video/c02.jpg"></div>				
			</div>
			<!-- video recommend -->
			<div class="video-banner2">
				<div class="wap lazyload" data-src="${APP_PATH }/static/pc/img/video/sj3.jpg"></div>
				<div class="pc lazyload" data-src="${APP_PATH }/static/pc/img/video/c03.jpg"></div>
			</div>	
			<div class="video-recommend-box">
				<div class="video-recommend-title">
					<div class="wap lazyload" data-src="${APP_PATH }/static/pc/img/video/wap-recommend.jpg"></div>
					<div class="pc lazyload" data-src="${APP_PATH }/static/pc/img/video/pc-recommend.jpg"></div>
				</div>	
				<div class="video-recommend-body">
					<div class="video-recommend-item shadow-radius lazyload" data-id="157" data-video="https://www.youtube.com/embed/zD5dgYYDb08?enablejsapi=1&version=3&playerapiid=ytplayer&loop=1&playlist=zD5dgYYDb08&modestbranding=1&playsinline=1&rel=0&controls=1&showinfo=0" data-src="${APP_PATH }/static/pc/img/video/pro/vd1.jpg"></div>
					<div class="video-recommend-item shadow-radius lazyload" data-id="109" data-video="https://www.youtube.com/embed/qp91g1hg9nM?enablejsapi=1&version=3&playerapiid=ytplayer&loop=1&playlist=qp91g1hg9nM&modestbranding=1&playsinline=1&rel=0&controls=1&showinfo=0" data-src="${APP_PATH }/static/pc/img/video/pro/vd2.jpg"></div>
					<div class="video-recommend-item shadow-radius lazyload" data-id="193" data-video="https://www.youtube.com/embed/u9M_pFxuLOA?enablejsapi=1&version=3&playerapiid=ytplayer&loop=1&playlist=u9M_pFxuLOA&modestbranding=1&playsinline=1&rel=0&controls=1&showinfo=0" data-src="${APP_PATH }/static/pc/img/video/pro/vd3.jpg"></div>
					<div class="video-recommend-item shadow-radius lazyload" data-id="84" data-video="https://www.youtube.com/embed/2cJvNAYpI6Y?enablejsapi=1&version=3&playerapiid=ytplayer&loop=1&playlist=2cJvNAYpI6Y&modestbranding=1&playsinline=1&rel=0&controls=1&showinfo=0" data-src="${APP_PATH }/static/pc/img/video/pro/vd4.jpg"></div>
					<div class="video-recommend-item shadow-radius lazyload" data-id="110" data-video="https://www.youtube.com/embed/BYgVHjc6P3k?enablejsapi=1&version=3&playerapiid=ytplayer&loop=1&playlist=BYgVHjc6P3k&modestbranding=1&playsinline=1&rel=0&controls=1&showinfo=0" data-src="${APP_PATH }/static/pc/img/video/pro/vd5.jpg"></div>
					<div class="video-recommend-item shadow-radius lazyload" data-id="80" data-video="https://www.youtube.com/embed/TD4mGC5xNcU?enablejsapi=1&version=3&playerapiid=ytplayer&loop=1&playlist=TD4mGC5xNcU&modestbranding=1&playsinline=1&rel=0&controls=1&showinfo=0" data-src="${APP_PATH }/static/pc/img/video/pro/vd6.jpg"></div>
					<div class="video-recommend-item shadow-radius lazyload" data-id="140" data-video="https://www.youtube.com/embed/TD4mGC5xNcU?enablejsapi=1&version=3&playerapiid=ytplayer&loop=1&playlist=TD4mGC5xNcU&modestbranding=1&playsinline=1&rel=0&controls=1&showinfo=0" data-src="${APP_PATH }/static/pc/img/video/pro/vd7.jpg"></div>
					<div class="video-recommend-item shadow-radius lazyload" data-id="251" data-video="https://www.youtube.com/embed/zoZm8cPDLKM?enablejsapi=1&version=3&playerapiid=ytplayer&loop=1&playlist=zoZm8cPDLKM&modestbranding=1&playsinline=1&rel=0&controls=1&showinfo=0" data-src="${APP_PATH }/static/pc/img/video/pro/vd8.jpg"></div>
				</div>
			</div>
			<!-- video collection -->
			<div class="video-collection">
				<div class="video-collection-title">
					<div class="wap lazyload" data-src="${APP_PATH }/static/pc/img/video/wap-collection.jpg"></div>
					<div class="pc lazyload" data-src="${APP_PATH }/static/pc/img/video/pc-collection.jpg"></div>
				</div>
				<div class="video-collection-body"></div>
			</div>
			<!-- video top sale -->
			<div class="video-topsale">
				<div class="video-topsale-title">
					<div class="wap lazyload" data-src="${APP_PATH }/static/pc/img/video/wap-top-sale.jpg"></div>
					<div class="pc lazyload" data-src="${APP_PATH }/static/pc/img/video/pc-top-sale.jpg"></div>
				</div>
				<div class="video-topsale-body">
					<div class="video-topsale-style one">
						<div class="video-topsale-style-item">
							<a class="lazyload" href="${APP_PATH }/B-Piano-Color-13x4-Straight-Hair-Wig.html" data-src="${APP_PATH }/static/pc/img/video/pro/c4.jpg"></a>
							<div class="video-topsale-style-name">Piano Color Lace Frontal Wigs</div>
						</div>
						<div class="video-topsale-style-item">
							<a class="lazyload" href="${APP_PATH }/B-360-Body-Wave-Wig.html" data-src="${APP_PATH }/static/pc/img/video/pro/c2.jpg"></a>
							<div class="video-topsale-style-name">360 Lace Frontal Wigs Body Wave</div>					
						</div>
						<div class="video-topsale-style-item">
							<a class="lazyload" href="${APP_PATH }/B-13x4-Straight-Wave-Wig.html" data-src="${APP_PATH }/static/pc/img/video/pro/c1.jpg"></a>
							<div class="video-topsale-style-name">13x4 Lace Frontal Wig Natural Color</div>					
						</div>
						<div class="video-topsale-style-item">
							<a class="lazyload" href="${APP_PATH }/B-13x4-Water-Wave-Wig.html" data-src="${APP_PATH }/static/pc/img/video/pro/c3.jpg"></a>
							<div class="video-topsale-style-name">13x4 Lace Frontal Wig Water Wave Hair</div>					
						</div>
					</div>
					<div class="video-topsale-style two">
						<div class="video-topsale-style-item">
							<a class="lazyload" href="${APP_PATH }/13x4-Bob-Wig-1B-30-Color-Straight-Lace-Wig.html" data-src="${APP_PATH }/static/pc/img/video/pro/cp6.jpg"></a>
							<div class="video-topsale-style-name">13x4 1b/30 Bob Lace Frontal Straight Wig</div>					
						</div>
						<div class="video-topsale-style-item">
							<a class="lazyload" href="${APP_PATH }/All-Color-4x4-Bob-Wig.html" data-src="${APP_PATH }/static/pc/img/video/pro/cp5.jpg"></a>
							<div class="video-topsale-style-name">4x4 Bob Wigs All Color Straight Hair</div>					
						</div>
						<div class="video-topsale-style-item">
							<a class="lazyload" href="${APP_PATH }/2x6-Straight-Wave-Bob-Wig.html" data-src="${APP_PATH }/static/pc/img/video/pro/cp7.jpg"></a>
							<div class="video-topsale-style-name">Kim K 2x6 Straight Lace Closure Bob Wig</div>					
						</div>
					</div>
				</div>
			</div>
		</div>
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
		function getVideoCollectionData(num, callback) {
			$.ajax({
				url: '${APP_PATH}/MlbackVideoShowArea/getMlbackVideoShowAreawapListByArea',
				data: JSON.stringify({
					"videoshowareaAreanum": num
				}),
				type: "post",
				dataType: 'json',
				contentType: 'application/json',
				success: function(data) {
					if (data.code == 100) {
						callback && callback(data.extend.mlbackVideoShowAreaList, data.extend.videoNumByAreaListList);
					} else {
						sysModalTip();
					}
				},
				error: function() {
					sysModalTip();
				}
			});
		}
		function renderVideoArea($el, videoData, numData) {
			var htmlStr = '';
			videoData.forEach(function(item, idx) {
				var itemLink = '${APP_PATH}/MlbackVideo/toVideoAreaPage?videoArea='+ item.videoshowareaId;
				htmlStr += '<div class="video-area-item">' +
						'<a class="lazyload wap" href="'+ itemLink +'" data-src="'+ item.videoshowareaWapimgurl +'"></a>' +
						'<a class="lazyload pc" href="'+ itemLink +'" data-src="'+ item.videoshowareaPcimgurl +'"></a>' +
						'<div class="video-areaitem-content">' +
							'<a class="video-areaitem-name" href="'+ itemLink +'">'+ item.videoshowareaName +'</a>' +
							'<div class="video-areaitem">('+ numData[idx] +')</div>' +
						'</div>' +
					'</div>';
			});
			$el.html(htmlStr);
		}
		new LazyLoad($('main').find('.lazyload'), {
			root: null,
			rootMargin: "10px",
			threshold: 0
		});
		getVideoCollectionData(2, function(videoData, numData) {
			var $el = $('.video-collection-body');
			videoData.length && renderVideoArea($el, videoData, numData);
			new LazyLoad($el.find('.lazyload'), {
				root: null,
				rootMargin: "10px",
				threshold: 0
			});
		});
		$(document.body).on('click', '.video-recommend-item', function() {
			var productId = $(this).data('id');
			var videoLink = $(this).data('video');
			productId && getOneProductData({ "productId": productId }, function(data) {
				renderVideoData(data, videoLink);
			});
		});
	</script>
	<!-- footer script -->
	<jsp:include page="../layout/footer/footer-script.min.jsp" flush="true"></jsp:include>
	<script> addTidio(); </script>
</body>
</html>