<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common/config-front.jsp"></jsp:include>
    <title>Video Area List</title>
	<meta name="keywords" content="Lace Front Wigs, Human Hair Extensions,Brazilian Virgin Hair,Affordable Human Hair" />
	<meta name="description" content="Megalook Hair Official Website Sells Best Virgin Hair, Full Lace Wig, Transparent Lace Wig, 360 Lace Wig, Frontal Lace Wig, Closure Lace Wig, Bundles with Closure, Bundles with Frontal, Free Wig Making Service. Hottest Products, Free Gifts, Free & Fast Shipping. No Shedding, No Tangles, Save Big! Lowest Price For U.">
	
	<jsp:include page="../common/processor.jsp" flush="true"></jsp:include>
	<jsp:include page="../common/header.jsp" flush="true"></jsp:include>
	<style>
		@media only screen and (max-width: 1023px) { main { margin:0; } }
	</style>
</head>
<body>
    <jsp:include page="../layout/header/header.jsp" flush="true"></jsp:include>
	<!-- main start -->
		<div class="container">
			<!-- video list -->
			<div class="video-list"></div>
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
		function renderTwoVideoData(videoLink, urlName, urlLink) {
			var videoHtml = '<div class="video-box">'+
					'<iframe frameborder="0" allowfullscreen="1" allow="autoplay; encrypted-media" title="YouTube video player" src="'+ videoLink +'"></iframe>'+
					'<div class="video-box-data">' +
						'<div class="video-box-name">'+ urlName +'</div>'+
						'<a class="btn btn-pink" href="'+ urlLink +'">See It</a>'+
					'</div>'+
				'</div>';
			var videoHotModal = createModal({
				header: {
					html: '<p>Hot Video...</p>'
				},
				body: {
					html: videoHtml
				}
			});
		}
		function getVideoAreaData(num, callback) {
			$.ajax({
				url: '${APP_PATH}/MlbackVideo/getMlbackVideoListByVideoArea',
				data: JSON.stringify({
					"videoArea": num
				}),
				type: "post",
				dataType: 'json',
				contentType: 'application/json',
				success: function(data) {
					if (data.code == 100) {
						callback && callback(data.extend.mlbackVideoList);
					} else {
						sysModalTip();
					}
				},
				error: function() {
					sysModalTip();
				}
			});
		}
		function renderVideoArea($el, data) {
			var htmlStr = '';
			data.forEach(function(item, idx) {
				var itemName, itemSeo, itemLink;
				if (item.videoIfproorcateorpage == 0) {
					itemSeo = item.videoProname;
					itemName = item.videoSeoname;
				}
				// collection
				if (item.videoIfproorcateorpage == 1) {
					itemSeo = 'search/' + item.videoCateseoname;
					itemName = item.videoCatename;
				}
				// subject
				if (item.videoIfproorcateorpage == 2) {
					itemSeo = item.videoPageseoname;
					itemName = item.videoPagename;
				}
				itemLink = itemSeo ? itemSeo + '.html' : 'javascript:;';
				htmlStr += '<div class="video-list-item shadow-radius" data-tag="'+ item.videoIfproorcateorpage +'" data-id="'+ item.videoProid +'" data-name="'+ itemName +'" data-link="'+ itemLink +'" data-video="'+ item.videoUrl +'">' +
						'<div class="video-list-item-img lazyload" data-src="'+ item.videoImgurl +'"></div>' +
						'<div class="video-list-item-name">'+ item.videoName +'</div>' +
					'</div>';
			});
			$el.html(htmlStr);
		}
		getVideoAreaData('${sessionScope.videoArea}', function(data) {
			var $el = $('.video-list');
			data.length && renderVideoArea($el, data);
			new LazyLoad($el.find('.lazyload'), {
				root: null,
				rootMargin: "10px",
				threshold: 0
			});
		});
		$(document.body).on('click', '.video-list-item', function() {
			var tag = $(this).data('tag');
			var productId = $(this).data('id');
			var videoLink = $(this).data('video');
			var urlLink = $(this).data('link');
			var urlName = $(this).data('name');
			var videoId = matchYoutubeUrl(videoLink);
			videoLink = videoLink && ('https://www.youtube.com/embed/' + videoId + '?enablejsapi=1&version=3&playerapiid=ytplayer&loop=1&playlist='+ videoId +'&modestbranding=1&playsinline=1&rel=0&controls=1&showinfo=0');
			if (tag == 0) {
				productId && getOneProductData({ "productId": productId }, function(data) {
					renderVideoData(data, videoLink);
				});
			} else {
				renderTwoVideoData(videoLink, urlName, urlLink);
			}
		});
	</script>
	<!-- footer script -->
	<jsp:include page="../layout/footer/footer-script.min.jsp" flush="true"></jsp:include>
	<script> addTidio(); </script>
</body>
</html>