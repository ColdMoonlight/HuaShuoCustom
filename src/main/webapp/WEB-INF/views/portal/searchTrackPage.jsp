<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<!DOCTYPE html>
<!--[if IE 8]><html class="no-js lt-ie9" lang="en" xmlns:og="http://ogp.me/ns#" xmlns:fb="http://www.facebook.com/2008/fbml"> <![endif]-->
<!--[if IE 9 ]><html class="ie9 no-js" xmlns:og="http://ogp.me/ns#" xmlns:fb="http://www.facebook.com/2008/fbml"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html class="no-js" xmlns:og="http://ogp.me/ns#" xmlns:fb="http://www.facebook.com/2008/fbml"> <!--<![endif]-->
<head>
    <title>Search Track Info | megalook.com</title>
	<jsp:include page="common/processor.jsp" flush="true"></jsp:include>
	<meta name="keywords" content="Lace Front Wigs, Human Hair Extensions,Brazilian Virgin Hair,Affordable Human Hair" />
	<meta name="description" content="Megalook Hair Official Website Sells Best Virgin Hair, Full Lace Wig, Transparent Lace Wig, 360 Lace Wig, Frontal Lace Wig, Closure Lace Wig, Bundles with Closure, Bundles with Frontal, Free Wig Making Service. Hottest Products, Free Gifts, Free & Fast Shipping. No Shedding, No Tangles, Save Big! Lowest Price For U.">
	<!-- google rule -->
	<meta name="robots" content="INDEX,FOLLOW">
    <link rel="alternate" href="https://www.megalook.com/MlfrontOrderList/searchTrackPage" hreflang="en-us" />
	<link rel="canonical" href="https://www.megalook.com/MlfrontOrderList/searchTrackPage" />
	<!-- socail media -->
	<meta property="og:type" content="website">
	<meta property="og:title" content="Search Track Info">
	<meta property="og:url" content="https://www.megalook.com/MlfrontOrderList/searchTrackPage">
	<meta property="og:site_name" content="Megalook Hair">
	<meta property="og:image" content="https://www.megalook.com/static/apple/micon1.png" />
	<meta name="twitter:site" content="@megalookhair">
	<meta name="twitter:card" content="summary">
	<jsp:include page="common/header.jsp" flush="true"></jsp:include>
	<style>
		.search-track-title { padding: 1rem 0; font-size: 20px; font-weight: bold; }
		#search-track-input { min-width: 18rem; max-width: 100%; font-size: 18px; color: #96a6a7; border-width: 2px; border-color: #96a6a7; }
		.btn-track { padding: 10px; background-color: #f9a31a; color: #fff; font-size: 20px; border-color: #f9a31a; border-radius: .25rem; }
		@media only screen and (min-width: 876px) {
			.search-track-img { display: flex; }
			.search-track-img>img { width: 50%; }
		}
		@media only screen and (max-width: 875px) {
			.search-track-img>img { width: 100%; }
		}
	</style>
</head>
<body>
    <jsp:include page="layout/header/header.jsp" flush="true"></jsp:include>
	<!-- main start -->
		<div class="container" style="max-width: 900px;">
			<div class="search-track-title" style="text-align: left;">Track Your Order</div>
			<div class="search-input">
				<input type="text" id="search-track-input" class="form-control" placeholder="Tracking number or order ID">
				<button class="btn btn-track" id="search-track-btn">Track</button>
			</div>
			<div class="search-track-note">
				<p><strong>Note:</strong></p>
				<p>1.One day in a week won't process orders-- U.S&nbsp;<strong>Saturday</strong>&nbsp;. Shipping companies&nbsp;all close on Sunday. If you have special emergency,plz email us.</p>
				<p>2.&nbsp;Orders will only be remained for&nbsp;5 working days&nbsp;after placement when<strong>&nbsp;phone number missing</strong>&nbsp;or&nbsp;<strong>unclear address</strong>. Longer than that, order will be canceled&nbsp;and money will be returned accordingly. So pls make sure that you&nbsp;<strong>leave correct shipping address&nbsp;and&nbsp;telephone number</strong>&nbsp;when place order.</p>
				<p>3. All the order will be fulfilled&nbsp;<strong>&nbsp;Within&nbsp;48&nbsp;hours,&nbsp;in&nbsp;24&nbsp;hours&nbsp;most&nbsp;of&nbsp;the&nbsp;time</strong>, so you could try to&nbsp;<strong>track your order after 24 Hours later</strong></p>
				<p><strong>Contact Us:</strong></p>
				<p>&nbsp;team@megalookemail.com<br>Call Us: (501)7226336<br>Whatsapp:+86 18903740682</p>
				<div class="search-track-img">
					<img src="${APP_PATH}/static/pc/img/track_01.png" />
					<img src="${APP_PATH}/static/pc/img/track_02.png" />
				</div>
			</div>
		</div>	
	<!-- main end -->
	<!-- footer nav -->
	<jsp:include page="layout/footer/footer.jsp" flush="true"></jsp:include>

	<!-- common script -->
	<script src="${APP_PATH}/static/common/jquery.min.js"></script>
	<script>function getCarouselData(area,async,callback){$.ajax({url:"${APP_PATH}/MlbackSlides/getMlbackSlidListByArea",data:JSON.stringify({slideArea:area}),dataType:"json",contentType:"application/json",type:"post",async:async,success:function(data){100==data.code&&callback&&callback(data.extend.mlbackSlideList)}})}function debounce(callback,delay){var timeEnd;delay=delay||100,Date.now()-timeStart>delay&&clearTimeout(timer),timer=setTimeout((function(){callback&&callback()}),delay)}</script>
	<!-- lazy -->
	<script>!function(t,e){"object"==typeof exports?module.exports=e(t):"function"==typeof define&&define.amd?define([],e):t.LazyLoad=e(t)}("undefined"!=typeof global?global:this.window||this.global,function(t){"use strict";function e(t,e){this.settings=o(r,e||{}),this.images=t||document.querySelectorAll(this.settings.selector),this.observer=null,this.init()}"function"==typeof define&&define.amd&&(t=window);const r={src:"data-src",srcset:"data-srcset",selector:".lazyload",root:null,rootMargin:"0px",threshold:0},o=function(){let t={},e=!1,r=0,s=arguments.length;for("[object Boolean]"===Object.prototype.toString.call(arguments[0])&&(e=arguments[0],r++);r<s;r++)!function(r){for(let s in r)Object.prototype.hasOwnProperty.call(r,s)&&(e&&"[object Object]"===Object.prototype.toString.call(r[s])?t[s]=o(!0,t[s],r[s]):t[s]=r[s])}(arguments[r]);return t};if(e.prototype={init:function(){if(!t.IntersectionObserver)return void this.loadImages();let e=this,r={root:this.settings.root,rootMargin:this.settings.rootMargin,threshold:[this.settings.threshold]};this.observer=new IntersectionObserver(function(t){Array.prototype.forEach.call(t,function(t){if(t.isIntersecting){e.observer.unobserve(t.target);let r=t.target.getAttribute(e.settings.src),o=t.target.getAttribute(e.settings.srcset),s=new Image;s.src=r,s.onload=function(e){var s=e.target.width,i=e.target.height;"img"===t.target.tagName.toLowerCase()?(r&&(t.target.src=r),o&&(t.target.srcset=o)):t.target.style.backgroundImage="url("+r+")",t.target.width=s,t.target.height=i,t.target.classList.add("loaded")},s.onerror=function(){}}})},r),Array.prototype.forEach.call(this.images,function(t){e.observer.observe(t)})},loadAndDestroy:function(){this.settings&&(this.loadImages(),this.destroy())},loadImages:function(){if(!this.settings)return;let t=this;Array.prototype.forEach.call(this.images,function(e){let r=e.getAttribute(t.settings.src),o=e.getAttribute(t.settings.srcset);"img"===e.tagName.toLowerCase()?(r&&(e.src=r),o&&(e.srcset=o)):e.style.backgroundImage="url('"+r+"')"})},destroy:function(){this.settings&&(this.observer.disconnect(),this.settings=null)}},t.lazyload=function(t,r){return new e(t,r)},t.jQuery){const r=t.jQuery;r.fn.lazyload=function(t){return(t=t||{}).attribute=t.attribute||"data-src",new e(r.makeArray(this),t),this}}return e});</script>
	<!-- common footer script -->
	<jsp:include page="common/footer.jsp" flush="true"></jsp:include>
	<!-- header script  -->
	<jsp:include page="layout/header/header-script.min.jsp" flush="true"></jsp:include>
	<script>
		function getTrackInfo(callback) {
			payLoading();
			var searchVal = $('#search-track-input').val().trim();
			if (!searchVal) {
				mlModalTip('Logistics order number or order ID cannot be empty!');
				hidePayLoading();
			} else {
				if ('' + searchVal.indexOf('ML20') == '0') {
					$.ajax({
						url: "${APP_PATH}/MlfrontOrderList/getTrackDetailByPayinfoPlatenum",
						type: "post",
						data: {"payinfoPlatenum": searchVal},
						success: function (data) {
							if (data.code == 100) {
								callback && callback(data.extend.trackingRes);
							} else {
								mlModalTip(data.extend.resMsg);
							}
						},
						error: function () {
							refreshPageModal();
						},
						complete: function() {
							hidePayLoading();
						}
					});
				} else {

					$.ajax({
						url: "${APP_PATH}/MlfrontOrderList/getTrackDetailByTrackingNumber",
						type: "post",
						data: {"trackingNumber": searchVal},
						success: function (data) {
							if (data.code == 100) {
								callback && callback(data.extend.trackingRes);
							} else {
								mlModalTip('The tracking number is invalid !');
							}
						},
						error: function () {
							refreshPageModal();
						},
						complete: function() {
							hidePayLoading();
						}
					});
				}
			}
		}
		function renderTrackInfo(data) {
			function generateTimelineHtml(data) {
				var html = '';
				var len = data.length - 1;
				for(var i = len; i >= 0; i -= 1) {
					var item = data[i];
					html += '<div class="time-line-item'+ (i == len ? ' active' : '') +'">' +
							'<div class="time-line-date">'+ item.checkpointTime +'</div>' +
							'<div class="time-line-tag"><span class="name">tag: </span><span class="value">'+ (item.tag || '') +'</span></div>' +
							'<div class="time-line-location"><span class="name">location: </span><span class="value">'+ (item.location || '') +'</span></div>' +
							'<div class="time-line-msg"><span class="name">msg: </span><span class="value">'+ (item.message || '') +'</span></div>' +
						'</div>';
				}
				return html;
			}
			var trackOrderHtml;
			var trackingModal;
			if (data.checkpoints && $.isArray(data.checkpoints) && data.checkpoints.length) {
				trackOrderHtml= '<div class="track-result-header">' +
						'<div clsass="tracking-name"><span class="name">Tracking Name: </span><span class="value">'+ data.slug +'</span></div>' +
						'<div clsass="tracking-number"><span class="name">Tracking Number: </span><span class="value">'+ data.trackingNumber +'</span></div>' +
						'<div clsass="tracking-adress"><span class="name">Tracking Adress: </span><span class="value">'+ data.originCountryISO3 + ' → ' + data.destinationCountryISO3 +'</span></div>' +
					'</div>' +
					'<div class="track-result-body">' +
						'<div class="time-line">' + generateTimelineHtml(data.checkpoints) + '</div>' +
					'</div>';
			} else {
				mlModalTip('The product you purchased has been sent out and is waiting for the courier. If you have other questions, please consult customer service.');
				return;
			}
			
			trackingModal = createModal({
				header: {
					html: '<p>Tracking Info...</p>'
				},
	   			body: {
	   				html: trackOrderHtml,
	   			}
	   		});
		}
		$('#search-track-btn').on('click', function() {
			getTrackInfo(renderTrackInfo);
		});
	</script>
	<!-- footer script -->
	<jsp:include page="layout/footer/footer-script.min.jsp" flush="true"></jsp:include>
	<script> addTidio(); </script>
</body>
</html>