<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<!DOCTYPE html>
<html>

<head>
	<jsp:include page="../common/config-front.jsp"></jsp:include>
    <title>My Order List</title>
    <!-- google rule -->
	<meta name="robots" content="INDEX,FOLLOW">
    <link rel="alternate" href="${ map.website_domain }/MlfrontUser/tomyOrderPage" hreflang="en-us" />
	<link rel="canonical" href="${ map.website_domain }/MlfrontUser/tomyOrderPage" />
	<jsp:include page="../common/processor.jsp" flush="true"></jsp:include>
	<jsp:include page="../common/header.jsp" flush="true"></jsp:include>
	<style>
		@media only screen and (max-width: 575px) { main { margin: 0; } }
	</style>
</head>

<body>
    <jsp:include page="../layout/header/header.jsp" flush="true"></jsp:include>
	<!-- main start -->
		<div class="container">
			<div class="usercenter-back"><a href="javascript:goToUserCenter();"><span class="icon left"></span>Back</a></div>
			<div class="usercenter-order-box">
				<div class="order-tab">
					<div class="order-tab-item active" data-id="999">All orders</div>
					<div class="order-tab-item" data-id="1">Paid</div>
					<div class="order-tab-item" data-id="3">Processing</div>
					<div class="order-tab-item" data-id="4">Delivered</div>
					<div class="order-tab-item" data-id="5">Refund</div>
				</div>
				<div class="user-order-list"></div>
				<div id="table-pagination" class="hide"></div>
				<div class="loader-box">
					<div class="loader"></div>
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
		function getOrderList() {
			$.ajax({
				url: "${APP_PATH}/MlfrontOrderList/selectOrderlistBySearch",
				data: {
					orderStatus: orderStatus,
					pn: getPageNum()
				},
				type: "post",
				async: false,
				success: function (data) {
					if(data.code == 100) {
						rednerOrderList(data.extend.mlfrontPayInfoReturnList, data.extend.pageInfo.list, data.extend.mlfrontOrderItemReturnList, data.extend.sizeList);
						renderTablePagination(data.extend.pageInfo);
						$('.loader-box').hide();
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
		function orderMap(data) {
			var orderMap = {};
			var orderItemMap = {}
			for (var i = 0, len = data.length; i < len; i += 1) {
				if (orderMap[data[i].orderId]) {
					orderMap[data[i].orderId].push(data[i].orderitemId);
				} else {
					orderMap[data[i].orderId] = [data[i].orderitemId]
				}
				orderItemMap[data[i].orderitemId] = data[i];
			}
			return {
				orderMap: orderMap,
				orderItemMap: orderItemMap
			}
		}
		function rednerOrderList(payinfoList, orderList, orderItemList, orderItemSize) {
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
			var map = orderMap(orderItemList);
			var htmlStr = '';
			if (orderList.length) {
				for (var key in orderList) {
					htmlStr += '<div class="user-order-item" data-id="' + orderList[key].orderId + '">' +
						'<div class="user-orderitem-title">' +
							'<div class="user-order-id">Order Id: ' + payinfoList[key].payinfoPlatenum + '</div>' +
							'<div class="right-box">' +
								'<div class="user-order-status">' + (statusMap[orderList[key].orderStatus]) + '</div>' +
							'</div>' +
						'</div>' +
						'<div class="user-orderitem-body">';
					for (var i = 0; i < orderItemSize[key]; i+=1) {
						var item = map.orderItemMap[map.orderMap[orderList[key].orderId][i]];
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
						'<div class="user-orderitem-footer">Total ' + orderItemSize[key] + ' goods, money $ ' + orderList[key].orderMoney + '</div>' +
						(orderStatus == 4 ? '<div class="user-order-track-details">' +
							'<div class="user-order-track-text">' +
								'<div class="name">'+ orderList[key].orderLogisticsname +':</div>' +
								'<div class="value">'+ orderList[key].orderLogisticsnumber +'</div>' +
							'</div>' +
							'<button class="btn btn-default user-order-track" data-slug="'+ orderList[key].orderLogisticsname +'" data-tracknumber="'+ orderList[key].orderLogisticsnumber +'">Tracking Details</button>' +
						'</div>' : '' ) +
					'</div>';
				}

				$userOrderList.html(htmlStr);
				new LazyLoad($userOrderList.find('.lazyload'), {
					root: null,
					rootMargin: "10px",
					threshold: 0
				});
				$tablePagination.removeClass('hide');
			} else {
				$userOrderList.html('<p class="user-order-empty">No orders...</p>');
				!$tablePagination.hasClass('hide') && $tablePagination.addClass('hide');
			}
		}
		// get track details
		function getTrackDetails(reqData, callback) {
			payLoading();
			$.ajax({
				url: "${APP_PATH}/MlfrontOrderList/getCheckpointByTrackingNumber",
				data: reqData,
				type: "post",
				success: function (data) {
					if(data.code == 100) {
						callback && callback(data.extend.trackingRes);
					} else {
						mlModalTip('Failed to obtain logistics information. Please try again later !');
					}
				},
				error: function(err) {
					mlModalTip('Failed to obtain logistics information. Please try again later !');
				},
				complete: function() {
					hidePayLoading();
				}
			});
		}
		// render tracking details
		function renderTrackDetails(data) {
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
			var trackingTitleHtml = '<div clsass="tracking-name"><span class="name">Tracking Name: </span><span class="value">'+ data.slug +'</span></div>' +
				'<div clsass="tracking-number"><span class="name">Tracking Number: </span><span class="value">'+ data.trackingNumber +'</span></div>' +
				'<div clsass="tracking-adress"><span class="name">Tracking Adress: </span><span class="value">'+ data.originCountryISO3 + ' → ' + data.destinationCountryISO3 +'</span></div>';
			var trackingBodyHtml = '<div class="time-line">' + generateTimelineHtml(data.checkpoints) + '</div>';
			var trackModal = createModal({
				header: {
					html: trackingTitleHtml
				},
    			body: {
    				html: trackingBodyHtml
    			}
    		});
			trackModal.addClass('track-modal');
		}
		var $userOrderList = $('.user-order-list'),
			$tablePagination = $('#table-pagination'),
			orderStatus = 999,
			statusMap = {
				1: 'Paid',
				3: 'Processing',
				4: 'Delviered',
				5:'refund'
			},
			loginStatus = checkUserLoginOrNot();
		
		!loginStatus && loginNotTip();
		loginStatus && getOrderList();
		// tab-order-item tab
		$('.order-tab-item').on('click', function() {
			if (!$(this).hasClass('active')) {
				$(this).addClass('active').siblings().removeClass('active');
				orderStatus = $(this).data('id');
				setPageNum(1);
				getOrderList();
			}
		});
		// pagination a-click
		$(document.body).on('click', '#table-pagination li', function (e) {
			getOrderList();
		});
		$(document.body).on('click', '.user-order-item', function() {
			var orderId = $(this).data('id');
			orderId && (window.location.href = '${APP_PATH}/MlfrontUser/tomyOrderDetailPage?orderId=' + orderId);
		});
		$(document.body).on('click', '.user-order-track', function(e) {
			e.stopPropagation();
			var slug = $(this).data('slug'),
				trackNumber = $(this).data('tracknumber');
			getTrackDetails({
				Slug: slug,
				trackingNumber: trackNumber
			}, renderTrackDetails);
		});
		$(window).on('beforeunload', function() {
			setPageNum(1);
		});
	</script>
	<!-- footer script -->
	<jsp:include page="../layout/footer/footer-script.min.jsp" flush="true"></jsp:include>
</body>
</html>