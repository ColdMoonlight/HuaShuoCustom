<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<!DOCTYPE html>
<html>

<head>
    <title>My Order Details</title>
	<jsp:include page="../common/processor.jsp" flush="true"></jsp:include>
	<jsp:include page="../common/header.jsp" flush="true"></jsp:include>
	<style>
		@media only screen and (min-width: 576px) { main { margin-top: 1rem; } }
	</style>
</head>

<body>
    <jsp:include page="../layout/header/header.jsp" flush="true"></jsp:include>
	<!-- main start -->
		<div class="container">
			<div class="usercenter-body">
				<jsp:include page="usercenter-nav.jsp" flush="true"></jsp:include>
				<div class="usercenter-content">
					<div class="usercenter-back"><a href="javascript:goToUserCenerOrder();"><span class="icon left"></span>Back</a></div>
	
					<div class="usercenter-order-details">
						<div class="user-order-address"></div>
						<div class="user-order-order"></div>
						<div class="user-order-time"></div>
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
		function getOrderList(orderId) {
			$.ajax({
				url: "${APP_PATH}/MlfrontOrder/getOneMlfrontOrderDetail",
				data: JSON.stringify({ orderId: orderId }),
				type: "post",
				dataType: 'json',
				contentType: 'application/json',
				async: false,
				success: function (data) {
					if(data.code == 100) {
						shippingMoney = data.extend.areafreightMoney;
						renderOrderAddress(data.extend.addressInfo);
						renderOrderOrder(data.extend.mlfrontPayInfoOne, data.extend.mlfrontOrderOne, data.extend.mlfrontOrderItemList);
						renderOrderTime(data.extend.mlfrontOrderOne);
					} else {
						sysModalTip();
						setTimeout(goToUserCenter, 2000);
					}
				},
				error: function(err) {
					sysModalTip();
					setTimeout(goToUserCenter, 2000);
				}
			})
		}
		function renderOrderAddress(data) {
			var html = '';
			html += '<span class="icon address"></span>' +
					'<div class="order-address-data">' +
						'<div class="order-address-info">' +
							'<div class="order-address-username">Consignee: ' + (data.addressUserlastname + ' ' + data.addressUserfirstname) + '</div>' +
							'<div class="order-address-phone">Phone: ' + data.addressTelephone + '</div>' +
						'</div>' +
						'<div class="order-address-address">' +
							'<div class="name">Address: </div>' +
							'<div class="value">' + data.addressDetail + ' ' + data.addressCity + ' ' + data.addressProvince + ' ' + data.addressCountry + '</div>' +
						'</div>' +
					'</div>' +
				'</div>';
			$('.user-order-address').html(html);
		}
		function renderOrderOrder(payinfoData, orderData, orderItemList) {
			function generateSkus(item) {
				var html = '';
				var optionName = item.orderitemPskuIdnamestr.split(',');
				var optionValue = item.orderitemPskuName.split(',');
				optionName.forEach(function(name, idx) {
					html += '<div class="user-orderitem-skuitem">' +
							'<div class="name">'+ name +':&nbsp;</div>' +
							'<div class="value">'+ optionValue[idx] +'</div>' +
						'</div>';
				});
				return html;
			}
			var htmlStr = '';
			if (orderData) {
				htmlStr += '<div class="user-order-item" data-id="' + orderData.orderId + '">' +
					'<div class="user-orderitem-title">' +
						'<div class="user-order-id">Order Id: ' + payinfoData.payinfoPlatenum + '</div>' +
					'</div>' +
					'<div class="user-orderitem-body">';
				var len = orderItemList.length;
				for (var i = 0, len; i < len; i+=1) {
					var item = orderItemList[i];
					var itemLink = item.orderitemPseo ? ('${APP_PATH}/products/'+ item.orderitemPseo) : 'javascript:;';
					htmlStr += '<div class="user-orderitem-item">' +
							'<div class="lazyload" data-src="' + item.orderitemProductMainimgurl + '"></div>' +
							'<div class="user-orderitem-text">' +
								'<a class="user-orderitem-name" href="'+ itemLink +'">' + item.orderitemPname + '</a>' +
								'<div class="user-orderitem-sku">' + generateSkus(item) + '</div>' +
							'</div>' +
							'<div class="user-orderitem-data">' +
								'<div class="user-orderitem-price">$' + (item.orderitemPskuReamoney / item.orderitemPskuNumber).toFixed(2) + '</div>' +
								'<div class="user-orderitem-num">X' + item.orderitemPskuNumber + '</div>' +
							'</div>' +
						'</div>';
				}
				htmlStr += '</div>' +
					'<div class="user-orderitem-footer">' +
						'<div class="user-orderitem-cal">' +
							'<span class="name">Total Amount: </span>' +
							'<span class="value">$' + (orderData.orderMoney + orderData.orderCouponPrice - shippingMoney).toFixed(2) + '<span>' +
						'</div>' +
						'<div class="user-orderitem-cal">' +
							'<span class="name">Coupon: </span>' +
							'<span class="value">-$' + (orderData.orderCouponPrice).toFixed(2) + '<span>' +
						'</div>' +
						'<div class="user-orderitem-cal">' +
							'<span class="name">shipping: </span>' +
							'<span class="value">$' + shippingMoney.toFixed(2) + '<span>' +
						'</div>' +
						'<div class="user-orderitem-cal">' +
							'<span class="name">Actual payment: </span>' +
							'<span class="value">$' + (orderData.orderMoney).toFixed(2) + '<span>' +
						'</div>' +
					'</div>' +
				'</div>';

				$('.user-order-order').html(htmlStr);
				new LazyLoad($('.user-order-order').find('.lazyload'), {
					root: null,
					rootMargin: "10px",
					threshold: 0
				});
			} else {
				$('.user-order-order').html('<p class="user-order-empty">No order Info...</p>');
			}
		}
		function renderOrderTime(data) {
			var html = '';
			data && (html = '<div class="user-order-time-title">Order Time</div>' +
				'<div class="user-order-time-body">' +
					'<div class="user-order-time-item">' +
						'<span class="name">Payment Method:</span>' +
						'<span class="value">' + (data.orderPayPlate ? 'paypal card' : 'paypal') + '<span>' +
					'</div>' +
					'<div class="user-order-time-item">' +
						'<span class="name">Create Time:</span>' +
						'<span class="value">' + data.orderCreatetime + '<span>' +
					'</div>' +
					'<div class="user-order-time-item">' +
						'<span class="name">Pay End Time:</span>' +
						'<span class="value">' +  data.orderMotifytime + '<span>' +
					'</div>' +
				'</div>'
				);
			$('.user-order-time').html(html);
		}
		var  orderId= window.location.href.split('?')[1].split('=')[1],
			shippingMoney = 0,
			loginStatus = checkUserLoginOrNot();
		
		!loginStatus && loginNotTip();
		loginStatus && getOrderList(orderId);
	</script>
	<!-- footer script -->
	<jsp:include page="../layout/footer/footer-script.min.jsp" flush="true"></jsp:include>
</body>
</html>