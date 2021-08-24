<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common/config-front.jsp"></jsp:include>
	<title>${ sessionScope.mlbackProductMetaTitle }</title>
    <meta name="keyword" content="${sessionScope.mlbackProductMetaKeyWords}">
    <meta id="ml-des" name="description" content="${sessionScope.mlbackProductMeteDesc}">
	<jsp:include page="../common/processor.jsp" flush="true"></jsp:include>
	<script> var productId = '${sessionScope.productDetailId}'; var productSeo; var productName; </script>
	<jsp:include page="../common/header.jsp" flush="true"></jsp:include>
	<jsp:include page="../common/swiper.jsp" flush="true"></jsp:include>
	<style>
		main {margin-top: 1rem;}
		@media only screen and (max-width: 575px) {
			main { margin: 0 }
			.product-details, .product-body, .product-footer { margin-left: 1rem; margin-right: 1rem; }
			.product-share .instagram {background-position: 0 -2rem;} .product-share .youtube {background-position: 0 -8rem;}
		}
		@media only screen and (min-width: 576px) and (max-width: 1023px) {.product-share .instagram {background-position: 0 -2.5rem;} .product-share .youtube {background-position: 0 -10rem;} }		
		@media only screen and (min-width: 1023px) {.product-share .instagram {background-position: 0 -3rem;} .product-share .youtube {background-position: 0 -12rem;} }
		.product-fb-reviews iframe, .fb-reviews-box iframe { width: 100% !important; }
	</style>
</head>
<body>
    <jsp:include page="../layout/header/header.jsp" flush="true"></jsp:include>
	<!-- main start -->
		<div class="container">
			<div class="product-header">
				<div class="product-media">
					<div class="swiper-container product-slide product-zoom">
						<div class="swiper-wrapper">
							<c:forEach items="${ mbackProductImgResList }" var="pro"><div class="swiper-slide"><div class="lazyload img" data-src="${ pro.productimgUrl }" rel="${ pro.productimgUrl }"></div><noscript><img src="${ pro.productimgUrl }" alt="${ sessionScope.mlbackProductMetaTitle }" /></noscript></div></c:forEach>
						</div>
			   			<div class="swiper-pagination"></div>
					    <div class="swiper-btn swiper-button-next"></div>
			            <div class="swiper-btn swiper-button-prev"></div>
					</div>
					<div class="swiper-container product-thumb-slide">
						<div class="swiper-wrapper"></div>
					    <div class="swiper-button-next"></div>
			            <div class="swiper-button-prev"></div>
					</div>
					<div class="product-share">
						<c:if test="${ map.sh_youtube != null }"><a class="share-item youtube" style="background-image: url(${APP_PATH}/static/pc/img/follow-us.png);" href="${ map.sh_youtube }" title="${ map.website_name } youtube"></a></c:if>
						<c:if test="${ map.sh_instagram != null }"><a class="share-item instagram" style="background-image: url(${APP_PATH}/static/pc/img/follow-us.png);" href="${ map.sh_instagram }" title="${ map.website_name } instagram"></a></c:if>
						<c:if test="${ map.sh_facebook != null }"><div class="share-item share-click facebook" data-url="${ map.sh_facebook }" title="share on facebook"></div></c:if>
						<c:if test="${ map.sh_pinterest != null }"><div class="share-item share-click pinterest" data-url="${ map.sh_pinterest }" title="share on pinterest"></div></c:if>
						<c:if test="${ map.sh_whatsapp != null }"><div class="share-item share-click whatsapp" data-url="${ map.sh_whatsapp }" title="share on whatsapp"></div></c:if>
					</div>
				</div>
				<div class="product-details">
					<div class="product-name"></div>
					<div class="product-coupons hide"></div>
					<div class="product-options"></div>
					<div class="product-price"></div>
					<div class="product-operate">
						<div class="name">Qty:</div>
						<div class="product-qty">
							<span class="group-addon" id="product-num-sub"><i class="icon sub"></i></span>
							<input type="text" class="product-num" value="1" data-count="1" disabled>					 
							<span class="group-addon" id="product-num-add"><i class="icon plus"></i></span>					
						</div>
					</div>
					<div class="product-pay paypal-button-container">
						<div class="btn paypal-button btn-primary" id="add-to-cart">Add To Cart</div>
		   				<div class="btn paypal-button btn-black buy-now">Buy Now</div>
					</div>
					<div id="countdown-area"></div>
				</div>
			</div>
			<div class="product-body">
				<div class="product-service">
					<div class="product-service-item">
						<div class="icon car"></div>
						<p>Free Shipping</p>
					</div>
					<div class="product-service-item">
						<div class="icon back"></div>
						<p>15 Days Returns</p>
					</div>
					<div class="product-service-item">
						<div class="icon lock"></div>
						<p>Secure Payment</p>
					</div>
				</div>
				<div class="product-tab">
					<div class="product-tab-item btn btn-primary" data-name="desc">Description</div>
					<div class="product-tab-item btn btn-black" data-name="review">Reviews</div>
				</div>
				<div class="product-tab-body">
					<div class="product-tab-container active" data-name="desc">
						<div class="product-description"></div>
						<div class="product-fb-reviews"></div>
					</div>
					<div class="product-tab-container" data-name="review">
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
							</div>
						</div>
						<div class="product-review-body">
							<div class="product-review-list"></div>
							<div id="table-pagination"></div>
						</div>
						<div class="loader-box" id="loader-box">
							<div class="loader"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="product-footer"></div>
		</div>
	<!-- main end -->
	<!-- footer nav -->
	<jsp:include page="../layout/footer/footer.jsp" flush="true"></jsp:include>

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
	<script> addTidio(); </script>
</body>
</html>