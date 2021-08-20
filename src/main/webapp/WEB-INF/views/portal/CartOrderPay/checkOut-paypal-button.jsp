<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<!DOCTYPE html>
<!--[if IE 8]><html class="no-js lt-ie9" lang="en" xmlns:og="http://ogp.me/ns#" xmlns:fb="http://www.facebook.com/2008/fbml"> <![endif]-->
<!--[if IE 9 ]><html class="ie9 no-js" xmlns:og="http://ogp.me/ns#" xmlns:fb="http://www.facebook.com/2008/fbml"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html class="no-js" xmlns:og="http://ogp.me/ns#" xmlns:fb="http://www.facebook.com/2008/fbml"> <!--<![endif]-->
<head>
	<title>Checkout | megalook.com</title>
	<meta name="keywords" content="Lace Front Wigs, Human Hair Extensions,Brazilian Virgin Hair,Affordable Human Hair" />
	<meta name="description" content="Megalook Hair Official Website Sells Best Virgin Hair, Full Lace Wig, Transparent Lace Wig, 360 Lace Wig, Frontal Lace Wig, Closure Lace Wig, Bundles with Closure, Bundles with Frontal, Free Wig Making Service. Hottest Products, Free Gifts, Free & Fast Shipping. No Shedding, No Tangles, Save Big! Lowest Price For U.">
	<jsp:include page="../common/processor.jsp" flush="true"></jsp:include>
	<!-- google rule -->
	<meta name="robots" content="INDEX,FOLLOW">
    <link rel="alternate" href="https://www.megalook.com/MlbackCart/toCheakOut" hreflang="en-us" />
	<link rel="canonical" href="https://www.megalook.com/MlbackCart/toCheakOut" />
	<!-- socail media -->
	<meta property="og:type" content="website">
	<meta property="og:title" content="Megalook checkout Info">
	<meta property="og:url" content="https://www.megalook.com/MlbackCart/toCheakOut">
	<meta property="og:site_name" content="Megalook Hair">
	<meta property="og:image" content="https://www.megalook.com/static/apple/micon1.png" />
	<meta name="twitter:site" content="@megalookhair">
	<meta name="twitter:card" content="summary_large_image">
	<jsp:include page="../common/header.jsp" flush="true"></jsp:include>
	<style>main { margin: 0; }</style>
	<style>.spinner,.spinner:after,.spinner:before{border-radius:50%}.spinner{position:relative;margin-right:.75rem;color:#fff;width:20px;height:20px;box-shadow:inset 0 0 0 2px;-webkit-transform:translateZ(0);-ms-transform:translateZ(0);transform:translateZ(0)}.spinner:after,.spinner:before{position:absolute;content:"";background:#5469d4}.spinner:before{width:10.4px;height:20.4px;border-radius:20.4px 0 0 20.4px;top:-.2px;left:-.2px;-webkit-transform-origin:10.4px 10.2px;transform-origin:10.4px 10.2px;-webkit-animation:loading 2s infinite ease 1.5s;animation:loading 2s infinite ease 1.5s}.spinner:after{width:10.4px;height:10.2px;border-radius:0 10.2px 10.2px 0;top:-.1px;left:10.2px;-webkit-transform-origin:0 10.2px;transform-origin:0 10.2px;-webkit-animation:loading 2s infinite ease;animation:loading 2s infinite ease}@-webkit-keyframes loading{0%{-webkit-transform:rotate(0);transform:rotate(0)}100%{-webkit-transform:rotate(360deg);transform:rotate(360deg)}}@keyframes loading{0%{-webkit-transform:rotate(0);transform:rotate(0)}100%{-webkit-transform:rotate(360deg);transform:rotate(360deg)}}.card-element-box{position:relative;padding:1rem;margin-top:.5rem;background-color:#f5f5f5}.card-element-box .spinner:after,.card-element-box .spinner:before{background-color:#f5f5f5}#card-element{width:100%;height:2.75rem;padding:.75rem;border-radius:.25rem .25rem 0 0;border:1px solid rgba(50,50,93,.1);background-color:#fff}#card-error{margin:.25rem 0 0 0;font-style:italic;font-size:.75rem;text-align:left;color:#ff1671}.card-element-box.mask #card-element{background-color:#f5f5f5;border-color:#f5f5f5}.card-element-box.mask .spinner{display:block;color:#333}#payment-form{display:flex;align-items:center;justify-content:center}#payment-form .spinner:after,#payment-form .spinner:before{background-color:#ccc}#payment-form[disabled]{background-color:#ccc;border-color:#ccc}.card-element-box .spinner{position:absolute;top:50%;left:50%;display:none;transform:translate(-50%,-50%)}.order-payment-item~.order-payment-item{margin-top:.5rem;}.form-input{position:relative}.address-box .form-no-label::after{position:absolute;top:.7rem;left:.4rem;content:'*';display:inline-block;font-size:.875rem;color:red;vertical-align:middle}</style>
	<script>var checkoutviewId='',isBackFill=false;</script>
</head>

<body>
	<jsp:include page="../layout/header/header.jsp" flush="true"></jsp:include> 
	<!-- main start -->
		<div class="container">
			<div class="order-header">
				<div class="ml-breadcrumb">
					<div class="ml-breadcrumb-item">Cart</div>
					<div class="ml-breadcrumb-item">•&nbsp;•&nbsp;•</div>
					<div class="ml-breadcrumb-item active">Checkout</div>
					<div class="ml-breadcrumb-item">•&nbsp;•&nbsp;•</div>
					<div class="ml-breadcrumb-item">Payment</div>
				</div>
			</div>
			<div class="order-body">
				<div class="order-left">
					<div class="cart-box">
						<div class="cart-box-title"><span class="order-sort">1</span>SHIPPING ADDRESS</div>
						<div class="cart-box-body">
							<div class="address-box">
								<!-- address id -->
								<input type="hidden" id="addressId" class="form-control" name="addressId">
								<!-- first name -->
								<div class="form-group">
									<div class="form-input form-no-label">
										<label for="addressUserfirstname" class="form-label hide">First Name</label>
										<input type="text" name="addressUserfirstname" id="addressUserfirstname" class="form-control" placeholder="First Name">
									</div>
								</div>
								<!-- last name -->
								<div class="form-group">
									<label for="addressUserlastname" class="form-label hide">Last Name</label>
									<div class="form-input form-no-label">
										<input type="text" name="addressUserlastname" id="addressUserlastname" class="form-control" placeholder="Last Name">
									</div>
								</div>
								<!-- email address -->
								<div class="form-group">
									<label for="addressEmail" class="form-label hide">Email Adress</label>
									<div class="form-input form-no-label">
										<input type="text" name="addressEmail" id="addressEmail" class="form-control" placeholder="Email">
									</div>
								</div>
								<!-- telephone -->
								<div class="form-group">
									<label for="addressTelephone" class="form-label hide">Telephone</label>
									<div class="form-input form-no-label">
										<input type="text" name="addressTelephone" id="addressTelephone" class="form-control" placeholder="Telephone">
									</div>
								</div>
								<!-- address -->
								<div class="form-group" style="width: 100%">
									<label for="addressDetail" class="form-label hide">Address</label>
									<div class="form-input form-no-label">
										<input type="text" name="addressDetail" id="addressDetail" class="form-control" placeholder="Address">
									</div>
								</div>
								<!-- city -->
								<div class="form-group">
									<label for="addressCity" class="form-label hide">City</label>
									<div class="form-input form-no-label">
										<input type="text" name="addressCity" id="addressCity" class="form-control" placeholder="City">
									</div>
								</div>
								<!-- Zip/Postal code -->
								<div class="form-group">
									<label for="addressPost" class="form-label hide">Zip/Postal code</label>
									<div class="form-input form-no-label">
										<input type="text" name="addressPost" id="addressPost" class="form-control" placeholder="Zip/Postal code">
									</div>
								</div>
								<!-- country -->
								<div class="form-group">
									<label for="addressCountry" class="form-label hide">Country</label>
									<div class="form-input">
										<select name="addressCountry" id="addressCountry" class="form-control" value="US"></select>
									</div>
								</div>
								<!-- stateprovinceName -->
								<div class="form-group address-province">
									<label for="addressProvince" class="form-label hide">State/Province</label>
									<div class="form-input">
										<select name="addressProvince" id="addressProvince" class="form-control"></select>
									</div>
								</div>
								<div class="ml-user-policy"><input name="userPolicy" type="checkbox" checked /> I accept&nbsp;<a class="user-policy-item" data-title="Terms of service" href="${APP_PATH}/Policy/TermsConditionsPage.html">Terms of Usage</a>&nbsp;and&nbsp;<a class="user-policy-item" data-title="Privacy Policy" href="${APP_PATH}/Policy/PrivacyPolicyPage.html">Privacy Policy</a>&nbsp;of Megalook.com</div>
							</div>
							<div class="paypal-error-tip hide">
								<span class="icon tip"></span>
								<div class="text">A match of the shipping Address E-mail, Country, State and Postal Code failed.</div>
							</div>
							<div class="order-address-shipping">
								<div class="name">SHIPPING COST:</div>
								<div class="value">$0</div>
							</div>
						</div>
					</div>
					<div class="checkout-order-list"></div>
				</div>

				<div class="order-right">
					<div class="checkout-coupons"><div id="init-loading"></div></div>
					<div class="checkout-pay-method"></div>
					<div class="checkout-buy-msg"></div>
					<div class="checkout-order-cal"></div>
				</div>
			</div>
			<div class="website-policy" style="margin-bottom: 1rem;">
				<a class="website-policy-item" href="${APP_PATH}/Policy/ReturnPolicyPage.html" data-title="Refund policy">Refund policy</a>
				<a class="website-policy-item" href="${APP_PATH}/Policy/ShippingPolicyPage.html" data-title="Shipping policy">Shipping policy</a>
				<a class="website-policy-item" href="${APP_PATH}/Policy/PrivacyPolicyPage.html" data-title="Privacy policy">Privacy policy</a>
				<a class="website-policy-item" href="${APP_PATH}/Policy/TermsConditionsPage.html" data-title="Terms of service">Terms of service</a>
			</div>
		</div>
	</main>
	<!-- main end -->

	<!-- common script -->
	<script src="${APP_PATH}/static/common/jquery.min.js"></script>
	<script>function getCarouselData(area,async,callback){$.ajax({url:"${APP_PATH}/MlbackSlides/getMlbackSlidListByArea",data:JSON.stringify({slideArea:area}),dataType:"json",contentType:"application/json",type:"post",async:async,success:function(data){100==data.code&&callback&&callback(data.extend.mlbackSlideList)}})}function debounce(callback,delay){var timeEnd;delay=delay||100,Date.now()-timeStart>delay&&clearTimeout(timer),timer=setTimeout((function(){callback&&callback()}),delay)}</script>
	<!-- lazy -->
	<script>!function(t,e){"object"==typeof exports?module.exports=e(t):"function"==typeof define&&define.amd?define([],e):t.LazyLoad=e(t)}("undefined"!=typeof global?global:this.window||this.global,function(t){"use strict";function e(t,e){this.settings=o(r,e||{}),this.images=t||document.querySelectorAll(this.settings.selector),this.observer=null,this.init()}"function"==typeof define&&define.amd&&(t=window);const r={src:"data-src",srcset:"data-srcset",selector:".lazyload",root:null,rootMargin:"0px",threshold:0},o=function(){let t={},e=!1,r=0,s=arguments.length;for("[object Boolean]"===Object.prototype.toString.call(arguments[0])&&(e=arguments[0],r++);r<s;r++)!function(r){for(let s in r)Object.prototype.hasOwnProperty.call(r,s)&&(e&&"[object Object]"===Object.prototype.toString.call(r[s])?t[s]=o(!0,t[s],r[s]):t[s]=r[s])}(arguments[r]);return t};if(e.prototype={init:function(){if(!t.IntersectionObserver)return void this.loadImages();let e=this,r={root:this.settings.root,rootMargin:this.settings.rootMargin,threshold:[this.settings.threshold]};this.observer=new IntersectionObserver(function(t){Array.prototype.forEach.call(t,function(t){if(t.isIntersecting){e.observer.unobserve(t.target);let r=t.target.getAttribute(e.settings.src),o=t.target.getAttribute(e.settings.srcset),s=new Image;s.src=r,s.onload=function(e){var s=e.target.width,i=e.target.height;"img"===t.target.tagName.toLowerCase()?(r&&(t.target.src=r),o&&(t.target.srcset=o)):t.target.style.backgroundImage="url("+r+")",t.target.width=s,t.target.height=i,t.target.classList.add("loaded")},s.onerror=function(){}}})},r),Array.prototype.forEach.call(this.images,function(t){e.observer.observe(t)})},loadAndDestroy:function(){this.settings&&(this.loadImages(),this.destroy())},loadImages:function(){if(!this.settings)return;let t=this;Array.prototype.forEach.call(this.images,function(e){let r=e.getAttribute(t.settings.src),o=e.getAttribute(t.settings.srcset);"img"===e.tagName.toLowerCase()?(r&&(e.src=r),o&&(e.srcset=o)):e.style.backgroundImage="url('"+r+"')"})},destroy:function(){this.settings&&(this.observer.disconnect(),this.settings=null)}},t.lazyload=function(t,r){return new e(t,r)},t.jQuery){const r=t.jQuery;r.fn.lazyload=function(t){return(t=t||{}).attribute=t.attribute||"data-src",new e(r.makeArray(this),t),this}}return e});</script>
	<!-- common footer script -->
	<jsp:include page="../common/footer.jsp" flush="true"></jsp:include>
	<!-- header script  -->
	<jsp:include page="../layout/header/header-script.min.jsp" flush="true"></jsp:include>
	<jsp:include page="./add-free-gift.jsp" flush="true"></jsp:include>
	<!-- checkout script -->
	<jsp:include page="collect-shipping-address.jsp" flush="true"></jsp:include>
	<jsp:include page="./checkout-script.min.jsp" flush="true"></jsp:include>
	<script> addTidio(); </script>
</body>
</html>