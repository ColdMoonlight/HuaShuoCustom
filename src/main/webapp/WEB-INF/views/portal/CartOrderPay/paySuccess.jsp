<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<!DOCTYPE html>
<!--[if IE 8]><html class="no-js lt-ie9" lang="en" xmlns:og="http://ogp.me/ns#" xmlns:fb="http://www.facebook.com/2008/fbml"> <![endif]-->
<!--[if IE 9 ]><html class="ie9 no-js" xmlns:og="http://ogp.me/ns#" xmlns:fb="http://www.facebook.com/2008/fbml"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html class="no-js" xmlns:og="http://ogp.me/ns#" xmlns:fb="http://www.facebook.com/2008/fbml"> <!--<![endif]-->
<head>
	<jsp:include page="../common/config-front.jsp"></jsp:include>
	<title>${ map.pay_title }</title>
	<meta name="keywords" content="${ map.pay_keywords }" />
	<meta name="description" content="${ map.description }">
	<jsp:include page="../common/processor.jsp" flush="true"></jsp:include>
	<!-- google rule -->
	<meta name="robots" content="INDEX,FOLLOW">
    <link rel="alternate" href="${ map.website_domain }/success.html" hreflang="en-us" />
	<link rel="canonical" href="${ map.website_domain }/success.html" />
	<!-- socail media -->
	<meta property="og:type" content="website">
	<meta property="og:title" content="${ map.pay_title }">
	<meta property="og:url" content="${ map.website_domain }/success.html">
	<meta property="og:site_name" content="${ map.website_name }">
	<meta property="og:image" content="${ map.website_domain }/static/apple/micon1.png" />
	<meta name="twitter:site" content="@${ map.website_name }">
	<meta name="twitter:card" content="summary_large_image">
	<jsp:include page="../common/header.jsp" flush="true"></jsp:include>
	<style> main { margin: 0; } </style>
</head>

<body>
	<jsp:include page="../layout/header/header.jsp" flush="true"></jsp:include>
	<!-- main start -->
		<div class="container">
			<div class="payment-header">
				<div class="ml-breadcrumb">
					<div class="ml-breadcrumb-item">Cart</div>
					<div class="ml-breadcrumb-item">•&nbsp;•&nbsp;•</div>
					<div class="ml-breadcrumb-item">Checkout</div>
					<div class="ml-breadcrumb-item">•&nbsp;•&nbsp;•</div>
					<div class="ml-breadcrumb-item active">Payment</div>
				</div>
			</div>
			<div class="payment-box payment-order">
				<div class="payment-box-title">
					<div class="payment-box-left">
						<span class="icon cart"></span>
						<span class="value">Show order summary</span>
						<span class="icon arrow bottom"></span>					
					</div>
					<span class="total-money"></span>
				</div>
				<div class="payment-box-body" style="display:none">
					<div class="payment-product"></div>
					<div class="payment-cal"></div>
				</div>
			</div>
			<div class="payment-box payment-order-info">				
				<div class="payment-box-title">
					<span class="icon wallet"></span>
					<div class="revceiver-info"></div>
				</div>
				<div class="payment-box-body">
					<div class="payment-tip">We do send an Email to your email address <b id="payment-user-email" style="color: #000;"></b> whenever your order is done (Maybe it's in your Junk or Spam Email),you can check the detailed information about your order. In order let you get the update information about your order, pls add <b style="color: #000;">team@megalookemail.com</b> be your whitelist .</div>
					<div class="payment-contact-us">Contact Us</div>
				</div>
			</div>			
			<div class="payment-box payment-buyer">
				<div class="payment-box-body">
					<div class="payment-address payment-shipping-address"></div>
					<div class="payment-address payment-billing-address hide"></div>
				</div>
			</div>
			<div class="payment-btn" style="margin-top: 1rem;">
				<a href="javascript:goToIndex();" class="btn btn-black">Continue Shopping</a>
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
	<jsp:include page="./add-free-gift.jsp" flush="true"></jsp:include>
	<script type="text/javascript">
		function getPayInfo(reqData, callback) {
			$.ajax({
				url: "${APP_PATH}/MlfrontPayInfo/successPageGetPaymentInfo",
				data: JSON.stringify(reqData),
				dataType: 'json',
				contentType: 'application/json',
				type: "post",
				success: function (data) {
					if (data.code == 100) {
						callback && callback(data.extend);
					} else {
						refreshPageModal();
					}
				},
				error: function(err) {
					sysModalTip();
				}
			});
		}
		
		function updatePayInfo(payId, couponCode) {
			$.ajax({
				url: "${APP_PATH}/MlfrontPayInfo/updateSuccessInfoIfMoreTimesBuy",
				data: JSON.stringify({'payinfoId': payId}),
				dataType: 'json',
				contentType: 'application/json',
				type: "post",
				success: function (data) {},
				error: function(err) {}
			});
		}

		function renerPaymentInfo(data) {
			$('.payment-order .total-money').html('$' + data.payinfoMoney);

			var paymentProductHtml = '';
			data.list.forEach(function (item, idx) {
				var productLink = item.orderitemPseo ? ('${APP_PATH}/products/' + item.orderitemPseo) : 'javascript:;';
				payinfoProductArr.push({
					'id': item.orderitemPid,
					'quantity': item.orderitemPskuNumber,
					'item_price': item.orderitemPskuReamoney
				}); // for facebook
				payinfoOrderArr.push({
					'id': item.orderitemPid,
					'name': item.orderitemPname,
					'quantity': item.orderitemPskuNumber,
					'price': (item.orderitemPskuReamoney / item.orderitemPskuNumber).toFixed(2),
					'brand': 'MLH',
					'list_name': 'Search Results'
				}); // for google
				paymentProductHtml += '<div class="payment-product-item">' +
						'<a href="' + productLink + '"><img class="order-img" src="' + item.orderitemProductMainimgurl + '"></a>' +
						'<div class="payment-product-details">' +
							'<a class="payment-product-name" href="' + productLink + '">' + item.orderitemPname + '</a>' +
							'<div class="payment-sku-list">' + item.orderitemPskuName + '</div>' +
						'</div>' +
						'<div class="payment-product-num">' +
							'<span class="product-num">x' + item.orderitemPskuNumber + '</span>' +
							'<span class="product-price">$' + (item.orderitemProductOriginalprice && item.orderitemProductAccoff ? accuracyCal((item.orderitemProductOriginalprice + parseFloat(item.orderitemPskuMoneystr)), item.orderitemProductAccoff) : 0.00) + '</span>' +
						'</div>' +
					'</div>';
			});
			$('.payment-order .payment-product').html(paymentProductHtml);

			renderFreeGift();
			showFreeGift(parseFloat(data.payinfoMoney) + parseFloat(data.orderCouponPrice) - data.shipping);
			
			var paymentCalHtml = '';
			paymentCalHtml = '<div class="payment-cal-item">' +
					'<span class="name">prototal: </span>' +
					'<span class="value">$' + (parseFloat(data.payinfoMoney) + parseFloat(data.orderCouponPrice) - data.shipping).toFixed(2) + '</span>' +
				'</div>' +
				'<div class="payment-cal-item">' +
					'<span class="name">coupon: </span>' +
					'<span class="value">-$' + (data.orderCouponPrice).toFixed(2) + '</span>' +
				'</div>' +
				'<div class="payment-cal-item">' +
					'<span class="name">shipping: </span>' +
					'<span class="value">$' + (data.shipping).toFixed(2) + '</span>' +
				'</div>' +
				'<div class="payment-cal-item">' +
					'<span class="name">subtotal: </span>' +
					'<span class="value">$' + (data.payinfoMoney).toFixed(2) + '</span>' +
				'</div>';
			$('.payment-order .payment-cal').html(paymentCalHtml);

		}

		$(".payment-order .payment-box-title").on('click', function () {
			if ($(".payment-order .payment-box-body").hasClass('active')) {
				$(".payment-order .payment-box-body").removeClass('active').slideUp();
				$(".payment-order .icon.arrow").removeClass('top').addClass('bottom');
			} else {
				$(".payment-order .payment-box-body").addClass('active').slideDown();
				$(".payment-order .icon.arrow").removeClass('bottom').addClass('top');
			}
		});

		function renderReceiverinfo(addressData, BillingData) {
			$('.revceiver-info').html('<div class="payment-orderid" data-id="'+ addressData.payinfoPlatenum +'"><span class="name">Order ID: </span><span class="value">' + addressData.payinfoPlatenum + '</span><span class="payment-orderid-copy">copy it</span></div>' +
					'<div class="payment-buyer-name"><span class="name">Thank You </span><span class="value">' + BillingData.shippingaddressRecipientName + '</span></div>');
			$('#payment-user-email').text(BillingData.shippingaddressEmail)
			$(".payment-shipping-address").html('<div>Thanks again for your order, we will arrange the package to the address you left.</div><h4 style="margin: 1rem 0;">Customer information</h4>' +
					'<div class="payment-address-item"><span class="name">Contact Email: </span><span class="value">' + BillingData.shippingaddressEmail + '</span></div>' +
					'<h5 style="margin: .75rem 0;">Shipping Address</h5>' +
					'<div class="payment-address-item"><span class="name">Phone: </span><span class="value">' + addressData.addressTelephone + '</span></div>' +
					'<div class="payment-address-item"><span class="name">Customer Name: </span><span class="value">' + BillingData.shippingaddressRecipientName + '</span></div>' +
					'<div class="payment-address-item"><span class="name">Address: </span><span class="value">'+ BillingData.shippingaddressLine1 + ', ' + BillingData.shippingaddressCity + ', ' + BillingData.shippingaddressStateProvinceName + ', ' + BillingData.shippingaddressCountryName  + '</span></div>' +
					'<div class="payment-address-item"><span class="name">Postcode: </span><span class="value">' + BillingData.shippingaddressPostalCode + '</span></div>' +
				'</div');
		}

		function copyToClipboard(str) {
			var el = document.createElement('textarea');
			el.value = str;
			el.setAttribute('readonly', '');
			el.style.position = 'absolute';
			el.style.left = '-9999px';
			document.body.appendChild(el);
			var selected =
			  document.getSelection().rangeCount > 0
			    ? document.getSelection().getRangeAt(0)
			    : false;
			el.select();
			document.execCommand('copy');
			document.body.removeChild(el);
			if (selected) {
				document.getSelection().removeAllRanges();
				document.getSelection().addRange(selected);
			}
		};

		var payinfoId = '${sessionScope.payinfoId}';
		var payinfoProductArr = [], payinfoOrderArr = [];
		
		if (!payinfoId) {
			mlModalTip('Please contact customer service for abnormal orders !');
			$('main').html('');
			setTimeout(goToIndex, 3000);
		} else {			
			getPayInfo({ "payinfoId": payinfoId}, function (data) {
				var resDataPayInfoOne = data.mlfrontPayInfoOne;
				var resDataOrderItemList = data.mlfrontOrderItemList;
				var resDataOrderPayOne = data.mlfrontOrderPayOneRes;
				var resDataAddressOne = data.mlfrontAddressOne;
				var mlPaypalShipAddressOne = data.mlPaypalShipAddressOne;

				if (data.mlfrontPayInfoOne) {
					var isFirst = data.ifFirst;
					var orderData = resDataOrderPayOne;
					orderData.shipping = data.areafreightMoney;
					orderData.list = resDataOrderItemList;
					orderData.payinfoMoney = resDataPayInfoOne.payinfoMoney;
		
					resDataAddressOne.payinfoPlatenum = resDataPayInfoOne.payinfoPlatenum;
		
					renerPaymentInfo(orderData);
					renderReceiverinfo(resDataAddressOne, mlPaypalShipAddressOne);
	
					!isFirst && fbq('track', 'Purchase', {
						'content_ids': payinfoProductArr,
						'content_type': 'product',
						'value': orderData.payinfoMoney,
						'currency': 'USD'
					}, {"eventID": payinfoId});
	
					setTimeout(function() {
						!isFirst && gtag('event', 'purchase', {
							'transaction_id': resDataPayInfoOne.payinfoPlatenum,
							'affiliation': 'MegaLookHair',
							'value': String(orderData.payinfoMoney),
							'currency': 'USD',
							'tax': 0,
							'shipping': 0,
							'items': payinfoOrderArr
						});

						!isFirst && snaptr('track', 'PURCHASE', {
							'currency': 'USD',
							'price': String(orderData.payinfoMoney),
							'transaction_id': resDataPayInfoOne.payinfoPlatenum
						});
					}, 0);
				}
			});
		}

		updatePayInfo(payinfoId);
		$(document.body).on('click', '.payment-orderid-copy', function() {
			copyToClipboard($(this).parent('.payment-orderid').data('id'));
			createModal({
				body: {
					html: '<p>The order id has been copied! You can also visit the <a href="${APP_PATH}/MlfrontOrderList/searchTrackPage">search-track-page</a> to track logistics information.</p>'
				}
			})
		});
		$(document.body).on('click', '.payment-contact-us', function() {
			function onTidioChatApiReady() {
				window.tidioChatApi.open();
			}
			if (window.tidioChatApi) {
				window.tidioChatApi.on("ready", onTidioChatApiReady);
			} else {
				document.addEventListener("tidioChat-ready", onTidioChatApiReady);
			}
		});
	</script>
	<!-- footer script -->
	<jsp:include page="../layout/footer/footer-script.min.jsp" flush="true"></jsp:include>
	<script> addTidio(); </script>
</body>

</html>