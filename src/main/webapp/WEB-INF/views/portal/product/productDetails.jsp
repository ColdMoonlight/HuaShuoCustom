<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!--[if IE 8]><html class="no-js lt-ie9" lang="en" xmlns:og="http://ogp.me/ns#" xmlns:fb="http://www.facebook.com/2008/fbml"> <![endif]-->
<!--[if IE 9 ]><html class="ie9 no-js" xmlns:og="http://ogp.me/ns#" xmlns:fb="http://www.facebook.com/2008/fbml"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html class="no-js" xmlns:og="http://ogp.me/ns#" xmlns:fb="http://www.facebook.com/2008/fbml"> <!--<![endif]-->
<head>
	<jsp:include page="../common/config-front.jsp"></jsp:include>
	<title>${ sessionScope.mlbackProductMetaTitle }</title>
    <meta name="keyword" content="${sessionScope.mlbackProductMetaKeywords}">
    <meta id="ml-des" name="description" content="${sessionScope.mlbackProductMeteDesc}">
	<jsp:include page="../common/processor.jsp" flush="true"></jsp:include>
    <!-- google rule -->
	<meta name="robots" content="INDEX,FOLLOW">
    <link rel="alternate" href="${ map.website_domain }/products/<%=request.getParameter("productSeo") %>" hreflang="en-us" />
	<link rel="canonical" href="${ map.website_domain }/products/<%=request.getParameter("productSeo") %>" />
	<!-- socail media -->
	<meta name="twitter:site" content="@${ map.website_name }">
	<meta name="twitter:card" content="summary">
	<meta name="twitter:title" content="${ sessionScope.mlbackProductMetaTitle }">
	<meta name="twitter:description" content="${sessionScope.mlbackProductMeteDesc}">
	<meta name="twitter:image" content="${mbackProductImgResList[0].productimgUrl}">
	<meta name="twitter:image:width" content="600">
	<meta name="twitter:image:height" content="600">
	<script type="application/ld+json">{
		"@context": "http://schema.org/",
		"@type": "Product",
		"name": "${ sessionScope.mlbackProductMetaTitle }",
		"url": "${ map.website_domain}/products/<%=request.getParameter("productSeo") %>",
		"sku": "${sessionScope.productDetailId}",
		"image": [ <c:forEach items="${ mbackProductImgResList }" var="pro" varStatus="status"><c:if test="${status.count < 2}">"${ pro.productimgUrl }"</c:if><c:if test="${status.count > 2}">,"${ pro.productimgUrl }"</c:if></c:forEach> ],
		"description": "${sessionScope.mlbackProductMeteDesc}",
		"brand": { "@type": "Thing", "name": "Megalook Hair" },
		"offers": {
			"@type": "AggregateOffer",
          	"offerCount": "100",
          	"lowPrice": "89.99",
          	"highPrice": "599.99",
          	"priceCurrency": "USD"
        }
	}</script>
	<script> var productId='${sessionScope.productDetailId}', productSeo, productName; </script>
	<jsp:include page="../common/header.jsp" flush="true"></jsp:include>
	<jsp:include page="../common/swiper.jsp" flush="true"></jsp:include>
	<style>
		@media only screen and (max-width: 575px) { .product-share .instagram {background-position: 0 -2rem;} .product-share .youtube {background-position: 0 -8rem;} }
		@media only screen and (min-width: 576px) and (max-width: 1023px) {.product-share .instagram {background-position: 0 -2.5rem;} .product-share .youtube {background-position: 0 -10rem;} }		
		@media only screen and (min-width: 1023px) {.product-share .instagram {background-position: 0 -3rem;} .product-share .youtube {background-position: 0 -12rem;} }
		#countdown-area .body { padding-top: 0; }
		#countdown-area .desc { padding: 0; }
		.product-slide-box .swiper-btn { z-index: 1; }
		.product-slide .swiper-slide>.img { border: 1px solid #ededed; }
		.product-slide .swiper-slide>.img.loaded { background-size: cover; }
		.product-fb-reviews iframe, .fb-reviews-box iframe { width: 100% !important; }
		.product-together-box { padding: 1rem; margin-top: 1rem; border: 1px solid #ededed; border-radius: .5rem; background-color: rgb(255, 192, 203); }
		.product-togher-title { font-size: 1.5rem; font-weight: 500; }
		.product-together-head, .product-together-imgs { margin-bottom: 1rem; }
		.product-together-img-item { display: flex; align-items: center; margin-top: 1rem; }
		.product-together-img+.product-togehter-img-item { margin-left: 1rem; }
		.product-together-plus { font-size: 2rem; }
		.product-together-img-item a { border: 1px solid #ddd; }
		.product-together-cal { margin-bottom: 1rem; } 
		.product-together-info-item { margin-top: .5rem; }
		.proudct-together-name { margin-left: .5rem; font-size: 1.2rem;  color: #333; }
		.product-together-check { width: 18px; height: 18px; }
		.product-together-select { margin-left: 1rem; padding: .25rem .5rem; background-color: #fff; }
		.product-together-oprice { margin-left: .5rem; color: #333; text-decoration: line-through; font-weight: 500; }
		.product-together-dprice { margin-left: .5rem; color: #ff0000; font-weight: 500; }
		.product-tab-item.active { color: #fff; border: 1px solid #000; background-color: #000; }
		.product-reviews,.product-review-orders {display:flex;align-items:center;}
		.product-review-orders {margin-top: 1rem;}
		.product-media-review-num {color: #333;}
		.product-sold-num b {color: #ff186e;}
		.product-price-save {border: 1px solid #ff186e; border-radius: 1em;}
		.product-stock {color: #666666; font-style: italic; font-size: .875rem;}
		.product-media {flex-shrink: 0;}
		.product-service {display: none; opacity: 0; visibility: hidden;}
		.product-service-wap-item,.product-service-pc-item2 {margin-bottom: .75rem; margin-right: 1.2rem; color: #3e3a3a;}
		.product-service-wap-item:before,.product-service-pc-item2:before {content: ""; display: inline-block; vertical-align: middle; margin-right: .5rem; background: url(${APP_PATH}/static/pc/img/product-service.png) no-repeat; background-size: 100%;}
		.product-share-btn {line-height: 1.0; color: #1296db; cursor: pointer;}
		.product-share-btn .icon-share {display: inline-block; vertical-align:middle; width: .875rem; height: .875rem; margin-right: .2rem;}
		.product-review-stars {position: relative;}
		.product-header .product-review-stars .icon.avgstar {position: absolute; top: 0; left: 0; overflow: hidden;}
		@media only screen and (max-width: 575px) {
			.product-together-imgs { width: 100%; }
			.product-together-img-item { display: inline-block; }
			.product-together-img-item a { display: inline-block; vertical-align: middle; width: 135px; height: 135px; }
			.product-together-plus { display: inline-block; vertical-align: middle; margin: 0 .5rem; }
			.product-together-cal { text-align: center; }
			#product-together-sadd { width: 100%; }
			.product-price-qty {display: flex;justify-content: space-between;}
			.product-price-qty .name {display: none;}
			.product-price-qty .product-operate {margin-top: 0!important;}
			.product-price-qty .product-define-price {font-size: 1rem; margin-left: 0;}			
			.product-price-qty .product-now-price {font-size: 1.25rem;}
			.product-price-save {float: left;padding: .1rem .25rem; margin-top: .25rem; font-size: .75rem;}
			.product-options {margin-top: .5rem!important;}
			.product-service.wap {display: block; opacity: 1;  visibility: visible; margin-top: 1rem;}
			.product-service-wap-item {display: inline-block; font-size: .875rem;}
			.product-service-wap-item:before {width: 1rem; height: 1rem;}
			.product-header .product-review-stars .icon {height: 1.2rem; font-size: 1.2rem;}
			.product-sold-num {font-size: .875rem}
			.product-media-review-num {font-size:.875rem; margin-left: .25rem; margin-right: .5rem;}
			.product-share-btn {margin-left: .5rem; font-size: .875rem;}
		}
		@media only screen and (min-width: 576px) {
			.product-together-head, .product-together-imgs { display: flex; flex-wrap: wrap;  align-items: center; }
			.product-together-imgs { margin-right: 1rem; }
			.product-together-plus { margin: 0 1rem; }
			.product-together-img-item a { display: block; justify-content: center; width: 150px; height: 150px; }
			.product-header .product-single-price { margin-left: .5rem; }
			.product-price-qty .product-qty { margin-left: 1rem; }
			.product-price-data {display: flex; align-items: center;}
			.product-price-save {margin-top: .5rem; margin-left: 1rem;padding: .2rem .5rem;}
			.product-service.pc {display: block; opacity: 1;  visibility: visible; margin-top: 1rem; padding: 1rem;}
			.product-service-pc-item {display: inline-block; margin-bottom: .75rem; margin-right: 1.2rem; font-size: 1rem;}
			.product-service-pc-item .text {font-size: 1.25rem;}
			.product-service-pc-item .icon {display: inline-block; font-size: 1.5rem; margin-right: .5rem; width: 1.5rem; height: 1.5rem; color: #ff186e;}
			.product-service-pc-item2:before {width: 1.25rem; height: 1.25rem;}
			.product-service-pc-item2 .color {color: #ff186e;}
			.product-sold-num {display: none;}
			.product-header .product-review-stars .icon {height: 1.4rem; font-size: 1.4rem;}
			.product-media-review-num {font-size: 1rem; margin-left: .5rem; margin-right: 1rem;}
			.product-share-btn {margin-left: 1rem; font-size: 1rem;}
		}
		.product-coupons.wap, .product-coupons.pc { display: none; opacity: 0; visibility: hidden; }
		@media only screen and (max-width: 1023px) { .product-coupons.wap { display: flex; opacity: 1; visibility: visible; } }
		@media only screen and (min-width: 1024px) { .product-coupons.pc { display: flex; opacity: 1; visibility: visible; } }
		@media only screen and (min-width: 1299px) { .product-media-container {position: absolute;} .product-media-container.product-media-fixed {position: fixed;} }
		.product-share-modal { display: flex; align-items: center; z-index: 999999999!important; }
	</style>
</head>
<body>
    <jsp:include page="../layout/header/header.jsp" flush="true"></jsp:include>
	<!-- main start -->
		<div class="container">
			<div class="product-details-name product-name"></div>
			<div class="product-header">
				<div class="product-media">
					<div class="product-media-container">
						<div class="product-slide-box">
							<div class="swiper-container product-slide product-zoom">
								<div class="swiper-wrapper">
									<c:forEach items="${ mbackProductImgResList }" var="pro"><div class="swiper-slide"><div class="lazyload img" data-src="${ pro.productimgUrl }" rel="${ pro.productimgUrl }"></div><noscript><img src="${ pro.productimgUrl }" alt="${ sessionScope.mlbackProductMetaTitle }" /></noscript></div></c:forEach>
								</div>
								<div class="swiper-pagination hide"></div>
							</div>
						    <div class="swiper-btn swiper-button-next"></div>
				            <div class="swiper-btn swiper-button-prev"></div>
						</div>
						<div class="swiper-container product-thumb-slide">
							<div class="swiper-wrapper"></div>
						</div>
					</div>
				</div>
				<div class="product-details">
					<div class="product-coupons pc hide"></div>
					<div class="product-review-orders">
						<div class="product-reviews"></div>
						<div class="product-sold-num"></div>
						<div class="product-share-btn"><svg t="1622182325492" class="icon-share" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1188" width="32" height="32"><path d="M769.14815 670.390403c-44.430932 0-84.182284 19.999496-110.768803 51.471278L389.219117 565.736878c6.597255-16.571421 10.228969-34.653241 10.228969-53.594639 0-17.006326-2.940982-33.332153-8.320503-48.497551l270.88143-157.119457c26.511817 29.069059 64.702628 47.312562 107.138112 47.312562 80.055291 0 144.95337-64.899102 144.95337-144.95337 0-80.055291-64.898079-144.954393-144.95337-144.954393s-144.95337 64.899102-144.95337 144.954393c0 15.991206 2.600221 31.386848 7.382131 45.776579L359.655801 412.377048c-26.417673-27.833929-63.756069-45.181015-105.161085-45.181015-80.055291 0-144.954393 64.890916-144.954393 144.946206 0 80.055291 64.898079 144.967696 144.954393 144.967696 39.409568 0 75.128071-15.741519 101.256148-41.24845l274.172383 159.024853c-3.725858 12.8384-5.729491 26.409486-5.729491 40.457434 0 80.0645 64.898079 144.954393 144.95337 144.954393s144.95337-64.889893 144.95337-144.954393C914.101519 735.297692 849.20344 670.390403 769.14815 670.390403z" p-id="1189" fill="#1296db"></path></svg>Share</div>
					</div>
					<div class="product-options"><div id="init-loading"></div></div>
					<div class="product-stock"></div>
					<div class="product-price-qty">
						<div class="product-price-data">
							<div class="product-price"></div>
							<div class="product-price-save"></div>
						</div>
						<div class="product-operate">
							<div class="name">Qty:</div>
							<div class="product-qty">
								<span class="group-addon" id="product-num-sub"><i class="icon sub"></i></span>
								<input type="text" class="product-num" value="1" data-count="1">					 
								<span class="group-addon" id="product-num-add"><i class="icon plus"></i></span>					
							</div>
						</div>
					</div>
					<div class="product-pay paypal-button-container">
						<div class="btn paypal-button btn-primary" id="add-to-cart">Add To Cart</div>
		   				<div class="btn paypal-button btn-black buy-now">Buy Now</div>
					</div>

					<div id="countdown-area"></div>

					<div class="product-coupons wap hide"></div>

					<div class="product-service pc">
						<div class="product-service-pc-item"><span class="icon car"></span><span class="text">Free Shipping</span></div>
						<div class="product-service-pc-item"><span class="icon back"></span><span class="text">30 Days Free Returns</span></div>
						<div class="product-service-pc-item"><span class="icon lock"></span><span class="text">Secure Payment</span></div>
						<div class="product-service-pc-item2"><span class="color">Free Gift ---</span> Free Gift With Every Order Worth <strong>$50</strong>.</div>
						<div class="product-service-pc-item2"><span class="color">Free & Fast Commerical Shipping ---</span> For Wordwide Order.</div>
						<div class="product-service-pc-item2"><span class="color">Fast Turnaround Time ---</span> All Orders Processed Within- <strong>1-3</strong> Days.</div>
						<div class="product-service-pc-item2"><span class="color">USA Warehouse Shipping ---</span> Express: <strong>1-3 Days.</strong></div>
						<div class="product-service-pc-item2"><span class="color">Drop Shipping Design Label Customized Service Approved ---</span> For Every Customer's Order.</div>
					</div>
				</div>
			</div>
			<div class="product-service wap">
				<div class="product-service-wap-item">Free OEM Service Design Label Drop Shipping</div>
				<div class="product-service-wap-item">$50 Gifts/ Order</div>
				<div class="product-service-wap-item">30 Days Free Returns</div>
				<div class="product-service-wap-item">Secure Payment</div>
				<div class="product-service-wap-item">72 Hours Online</div>
			</div>
			<div class="product-together-box hide">
				<div class="product-togher-title">Frequently Bought Together</div>
				<div class="product-together-head">
					<div class="product-together-imgs"></div>
					<div class="product-together-add">
						<div class="product-together-cal">
							<span class="product-together-des">Total Price: </span>
							<span class="product-together-dprice"></span>
							<span class="product-together-oprice"></span>
						</div>
						<button class="btn btn-black" id="product-together-sadd">Add selected to cart</button>
					</div>
				</div>
				<div class="product-together-other"></div>
			</div>
			<div class="product-body" id="product-reviews">
				<div class="product-tab">
					<div class="product-tab-item btn" data-name="desc">Description</div>
					<div class="product-tab-item btn active" data-name="review">Reviews</div>
					<div class="product-tab-item btn" data-name="faq">FAQ</div>
				</div>
				<div class="product-tab-body">
					<div class="product-tab-container" data-name="desc">
						<div class="product-description"></div>
						<div class="product-fb-reviews"></div>
					</div>
					<div class="product-tab-container active" data-name="review">
						<div class="product-review-title">
							<div class="product-review-cal">
								<div class="product-review-total">Based on <span>0</span> Customer Reviews</div>
								<div class="product-review-avgstar">
									<span class="icon star"></span>
									<span class="icon star"></span>
									<span class="icon star"></span>
									<span class="icon star"></span>
									<span class="icon star"></span>
								</div>
								<div class="product-review-star-list"></div>
							</div>
							<div class="product-review-add">
								<button class="btn btn-black" id="write-review">Write A Review</button>
								<div class="product-tab" id="product-search-review">
									<div class="product-tab-item btn active" data-name="">All stars</div>
									<div class="product-tab-item btn" data-name="1">Photo</div>
								</div>
							</div>
						</div>
						<div class="product-review-body">
							<div class="product-review-list"></div>
							<div id="table-pagination"></div>
						</div>
					</div>
					<div class="product-tab-container" data-name="faq">
						<jsp:include page="../layout/faq.jsp" flush="true"></jsp:include>
					</div>
				</div>
			</div>
			<div class="product-might-like"></div>
		</div>
	<!-- main end -->
	<!-- footer nav -->
	<jsp:include page="../layout/footer/footer.jsp" flush="true"></jsp:include>
	
	<div class="modal product-share-modal hide">
		<div class="modal-close icon close"></div>
		<div class="modal-container">
			<div class="modal-header"><p>Product Share</p></div>
			<div class="modal-body">
				<div class="product-share">
					<c:if test="${ map.sh_youtube != null }"><a class="share-item youtube" style="background-image: url(${APP_PATH}/static/pc/img/follow-us.png);" href="${ map.sh_youtube }" title="${ map.website_name } youtube"></a></c:if>
					<c:if test="${ map.sh_instagram != null }"><a class="share-item instagram" style="background-image: url(${APP_PATH}/static/pc/img/follow-us.png);" href="${ map.sh_instagram }" title="${ map.website_name } instagram"></a></c:if>
					<c:if test="${ map.sh_facebook != null }"><div class="share-item share-click facebook" data-url="${ map.sh_facebook }" title="share on facebook"></div></c:if>
					<c:if test="${ map.sh_pinterest != null }"><div class="share-item share-click pinterest" data-url="${ map.sh_pinterest }" title="share on pinterest"></div></c:if>
					<c:if test="${ map.sh_whatsapp != null }"><div class="share-item share-click whatsapp" data-url="${ map.sh_whatsapp }" title="share on whatsapp"></div></c:if>
				</div>
			</div>
		</div>
	</div>

	<!-- common script -->
	<script src="${APP_PATH}/static/common/jquery.min.js"></script>
	<script>function getCarouselData(area,async,callback){$.ajax({url:"${APP_PATH}/MlbackSlides/getMlbackSlidListByArea",data:JSON.stringify({slideArea:area}),dataType:"json",contentType:"application/json",type:"post",async:async,success:function(data){100==data.code&&callback&&callback(data.extend.mlbackSlideList)}})}function debounce(callback,delay){var timeEnd;delay=delay||100,Date.now()-timeStart>delay&&clearTimeout(timer),timer=setTimeout((function(){callback&&callback()}),delay)}</script>
	<!-- lazy -->
	<script>!function(t,e){"object"==typeof exports?module.exports=e(t):"function"==typeof define&&define.amd?define([],e):t.LazyLoad=e(t)}("undefined"!=typeof global?global:this.window||this.global,function(t){"use strict";function e(t,e){this.settings=o(r,e||{}),this.images=t||document.querySelectorAll(this.settings.selector),this.observer=null,this.init()}"function"==typeof define&&define.amd&&(t=window);const r={src:"data-src",srcset:"data-srcset",selector:".lazyload",root:null,rootMargin:"0px",threshold:0},o=function(){let t={},e=!1,r=0,s=arguments.length;for("[object Boolean]"===Object.prototype.toString.call(arguments[0])&&(e=arguments[0],r++);r<s;r++)!function(r){for(let s in r)Object.prototype.hasOwnProperty.call(r,s)&&(e&&"[object Object]"===Object.prototype.toString.call(r[s])?t[s]=o(!0,t[s],r[s]):t[s]=r[s])}(arguments[r]);return t};if(e.prototype={init:function(){if(!t.IntersectionObserver)return void this.loadImages();let e=this,r={root:this.settings.root,rootMargin:this.settings.rootMargin,threshold:[this.settings.threshold]};this.observer=new IntersectionObserver(function(t){Array.prototype.forEach.call(t,function(t){if(t.isIntersecting){e.observer.unobserve(t.target);let r=t.target.getAttribute(e.settings.src),o=t.target.getAttribute(e.settings.srcset),s=new Image;s.src=r,s.onload=function(e){var s=e.target.width,i=e.target.height;"img"===t.target.tagName.toLowerCase()?(r&&(t.target.src=r),o&&(t.target.srcset=o)):t.target.style.backgroundImage="url("+r+")",t.target.width=s,t.target.height=i,t.target.classList.add("loaded")},s.onerror=function(){}}})},r),Array.prototype.forEach.call(this.images,function(t){e.observer.observe(t)})},loadAndDestroy:function(){this.settings&&(this.loadImages(),this.destroy())},loadImages:function(){if(!this.settings)return;let t=this;Array.prototype.forEach.call(this.images,function(e){let r=e.getAttribute(t.settings.src),o=e.getAttribute(t.settings.srcset);"img"===e.tagName.toLowerCase()?(r&&(e.src=r),o&&(e.srcset=o)):e.style.backgroundImage="url('"+r+"')"})},destroy:function(){this.settings&&(this.observer.disconnect(),this.settings=null)}},t.lazyload=function(t,r){return new e(t,r)},t.jQuery){const r=t.jQuery;r.fn.lazyload=function(t){return(t=t||{}).attribute=t.attribute||"data-src",new e(r.makeArray(this),t),this}}return e});</script>
	<!-- swiper script -->
	<script src="${APP_PATH}/static/common/swiper/swiper.min.js"></script>
	<!-- common footer script -->
	<jsp:include page="../common/footer.jsp" flush="true"></jsp:include>
	<!-- header script  -->
	<jsp:include page="../layout/header/header-script.min.jsp" flush="true"></jsp:include>
	<!-- third lib -->
	<script src="${APP_PATH}/static/pc/js/jqfly/jquery.fly.min.js"></script>
	<!-- product-details script -->
	<jsp:include page="./productdetails-script.min.jsp" flush="true"></jsp:include>
	<!-- footer script -->
	<jsp:include page="../layout/footer/footer-script.min.jsp" flush="true"></jsp:include>
	<!-- lottery -->
	<jsp:include page="../layout/lottery.jsp" flush="true"></jsp:include>
	<script> addTidio(); </script>
</body>
</html>