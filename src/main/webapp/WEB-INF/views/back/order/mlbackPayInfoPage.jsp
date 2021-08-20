<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Order List</title>
	<jsp:include page="../common/backheader.jsp" flush="true"></jsp:include>
	<link rel="stylesheet" href="${APP_PATH}/static/back/lib/datetimepicker/daterangepicker.css">
	<style>
		.pre-code-modal {
			position: fixed;
			top: 0;
			right: 0;
			bottom: 0;
			left: 0;
			z-index: 9999999;
			background-color: rgba(0, 0, 0, .6);
		}
		.pre-code-modal .pre-code-body {
			position: absolute;
			top: 50%;
			left: 50%;
			width: 80%;
			height: 80%;
			padding: 2rem;
			background-color: #fff;
			border-radius: .25rem;
			-webkit-transform: translate(-50%, -50%);
			transform: translate(-50%, -50%);
		}
		.pre-code-content {
			width: 100%;
			height: 100%;
			overflow: auto;
		}
		.pre-code-modal .pre-code-body pre {
			margin: 0;
		}
		.pre-code-head {
			position: relative;
			width: 80%;
			height: 10%;
			margin: 0 auto;
		}
		.pre-code-close {
			position: absolute;
			bottom: -1rem;
			right: 0;
			z-index: 1;
			width: 2rem;
			height: 2rem;
			text-align: center;
			line-height: 1.8rem;
			font-size: 1.2rem;
			color: #fff;
			background-color: #000;
			border-radius: 50%;
			cursor: pointer;
		}
		.table-view {
			cursor: pointer;
		}
	</style>
</head>
<body class="c-app">
	<jsp:include page="../layout/backheader.jsp" flush="true"></jsp:include>
	<jsp:include page="../layout/backsidebar.jsp" flush="true"></jsp:include>
	<div class="c-wrapper">
		<div class="c-body">
			<div class="c-main">
				<div class="c-init">
					<div class="c-option">
						<span class="c-option-title">Order list</span>
						<!-- <button class="btn btn-primary btn-create">Create Order</button> -->
					</div>
					<div class="ecpp-sync row">
						<div class="form-group col-md-4">
							<div class="controls">
								<input hidden id="order-create-time" />
								<input hidden id="order-confirm-time" />
								<input class="form-control daterangetimepicker" id="order-time" type="text" />
							</div>
						</div>
						<div class="order-btn-group col-md-8">
							<button class="btn btn-secondary download-audit" id="download-audit">
								<svg class="c-icon">
									<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-export"></use>
								</svg>
								<span>Export</span>
							</button>
							<div class="btn btn-dark hide" id="send-sms"> send SMS </div>
							<div class="btn btn-secondary hide" id="send-email"> send Email </div>
							<button class="btn btn-info ecpp-verify-sync">checkEcppIfVerify</button>						
							<button class="btn btn-primary ecpp-data-sync">checkEcppIfSend</button>
						</div>
					</div>
					<div class="c-table">
						<div class="c-table-tab">
							<div class="c-table-tab-item" data-idx="0">All</div>
							<div class="c-table-tab-list"></div>
							<div class="c-table-tab-tempory"></div>
						</div>
						<div class="c-table-content">
							<div class="input-group c-search">
								<svg class="c-icon">
									<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-magnifying-glass"></use>
								</svg>
								<div class="form-control">
									<input id="payinfoPlateNum" type="text" placeholder="Search Ordders">						
									<select id="payinfoStatus">
										<option value="999">Please select order-status</option>
										<option value="0">unpaid</option>
										<option value="1">paid</option>
										<option value="2">audited</option>
										<option value="3">delivered</option>
										<option value="4">refunded</option>
										<option value="5">abandon-purchase</option>
										<option value="6">closed</option>
									</select>
								</div>
								<a class="btn btn-primary input-group-addon btn-save-search">Save search</a>
							</div>
							<div class="c-table-table table-responsive-sm">
								<table class="table">
									<thead>
										<tr>
											<th>payId</th>
											<th>orderid</th>
											<th>plate-num</th>
											<!--<th>payinfoCreatetime</th>-->
											<th>pay-time</th>
											<th>pay-status</th>
											<th>price</th>
											<th>customer</th>
											<!-- <th>customer-email</th> -->
											<th>pay-method</th>
											<th class="table-sms hide">SMS-status</th>
											<th class="table-email hide">Email-status</th>
											<th class="table-unpaid">pay-num</th>
											<th class="table-unpaid">money-status</th>
											<th class="table-unpaid">ecpp-num</th>
											<th class="table-unpaid">ecpp-status</th>
											<th class="table-unpaid">tracking-num</th>
											<th>operate</th>
										</tr>
									</thead>
									<tbody></tbody>
								</table>
							</div>
							<div id="table-pagination"></div>
						</div>
					</div>
				</div>
				<!-- edit or create -->
				<div class="c-view hide view-order">
					<div class="c-option">
						<span class="c-option-title">View Order</span>
						<div class="group">
							<button class="btn btn-secondary btn-back">Back</button>
						</div>
					</div>
					<div class="c-form row">
						<!-- left panel -->
						<div class="left-panel col-lg-8 col-md-12">
							<div class="card">
								<div class="card-title" style="display: flex; justify-content: space-between; align-items: center;">
									<div class="card-title-name">Order Info</div>
									<div style="display: flex;">
										<div class="input-group hide" id="unpaid-link">
											<span class="form-control">This link to recover customer cart</span>
											<div class="input-group-addon btn">
												<svg class="c-icon">
													<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-copy"></use>
												</svg>
												<span>copy</span>
											</div>										
										</div>
										<button class="btn btn-dark hide" style="margin-left: 1rem;" id="send-email-one">send Email</button>
										<button class="btn btn-secondary hide" style="margin-left: 1rem;" id="send-sms-one">send SMS</button>
										<button class="btn btn-primary hide" style="margin-left: 1rem;" id="checkout-view">Checkout View</button>
									</div>
								</div>
								<div class="payinfo card-body">
									<div class="order-list">
										<p>no product for order...</p>
									</div>
									<div class="pay-list">
										<div class="pay-item">
											<div class="pay-item-title">Product Total</div>
											<div class="pay-prototal">
												<div class="name"></div>
												<div class="value">$0.00</div>
											</div>
										</div>
										<div class="pay-item">
											<div class="pay-item-title">Discount</div>
											<div class="pay-discount">
												<div class="name"></div>
												<div class="value">-$0.00</div>
											</div>
										</div>
										<div class="pay-item">
											<div class="pay-item-title">Shipping</div>
											<div class="pay-shipping">
												<div class="name"></div>
												<div class="value">$0.00</div>
											</div>
										</div>
										<div class="pay-item">
											<div class="pay-item-title">Total</div>
											<div class="pay-total">
												<div class="name"></div>
												<div class="value">$0.00</div>
											</div>
										</div>									
									</div>
									<div class="pay-final sum-item">
										<div class="name">Paid by customer</div>
										<div class="value">$0.00</div>
									</div>
									<div class="customer-note">
										<div class="name">Customer Note</div>
										<div class="value" style="color: #f00;">No notes from customer...</div>
									</div>
									<div class="payinfo-group">
										<button class="btn btn-warning hide btn-audit" id="ecpp-verify" style="margin-right: 30%;">Aduited</button>
										<!-- <button class="btn btn-danger hide btn-abandon-purchase">Abandon Purchase</button> -->
										<button class="btn btn-primary hide btn-refund">Refund</button>
										<button class="btn btn-dark hide btn-close">Close</button>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="card">
										<div class="card-title">
											<div class="card-title-name">Old Shipping Address</div>
										</div>
										<div class="card-body shipping-list old-shipping">
											<div class="shipping-item order-shipping firstname">
												<div class="name">First Name</div>
												<div class="value"></div>
											</div>
											<div class="shipping-item order-shipping lastname">
												<div class="name">Last Name</div>
												<div class="value"></div>
											</div>
											<div class="shipping-item order-shipping address">
												<div class="name">Address Details</div>
												<div class="value"></div>
											</div>
											<div class="shipping-item order-shipping city">
												<div class="name">city</div>
												<div class="value"></div>
											</div>
											<div class="shipping-item order-shipping province">
												<div class="name">Province/State</div>
												<div class="value"></div>
											</div>
											<div class="shipping-item order-shipping postcode">
												<div class="name">Post Code</div>
												<div class="value"></div>
											</div>
											<div class="shipping-item order-shipping country">
												<div class="name">Country</div>
												<div class="value"></div>
											</div>
											<div class="shipping-item order-shipping phone">
												<div class="name">Phone</div>
												<div class="value"></div>
											</div>
											<div class="shipping-item order-shipping email">
												<div class="name">E-mail</div>
												<div class="value"></div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="card">
										<div class="card-title" style="display: flex; justify-content: space-between; align-items: center;">
											<div class="card-title-name">Ecpp(Billing) Address</div>
											<button class="btn btn-info" id="pay-track">paypal track</button>
										</div>
										<div class="card-body billing-list">
											<div class="billing-item paymentid">
												<div class="name">Payment ID</div>
												<div class="value"></div>
											</div>
											<div class="billing-item username">
												<div class="name">User Name</div>
												<div class="value"></div>
											</div>
											<div class="billing-item line1">
												<div class="name">line 1</div>
												<div class="value"></div>
											</div>
											<div class="billing-item line2">
												<div class="name">line 2</div>
												<div class="value"></div>
											</div>
											<div class="billing-item city">
												<div class="name">city</div>
												<div class="value"></div>
											</div>
											<div class="billing-item province">
												<div class="name">Province/State</div>
												<div class="value"></div>
											</div>
											<div class="billing-item postcode">
												<div class="name">Post Code</div>
												<div class="value"></div>
											</div>
											<div class="billing-item country">
												<div class="name">Country Code</div>
												<div class="value"></div>
											</div>
											<div class="billing-item phone">
												<div class="name">Phone</div>
												<div class="value"></div>
											</div>
											<div class="billing-item email">
												<div class="name">E-mail</div>
												<div class="value"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- right panel -->
						<div class="right-panel col-lg-4 col-md-12">
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">
										<span>Tracking</span>
										<button class="btn btn-info hide" id="btn-manual-delivery">manual delivery</button>
									</div>
								</div>
								<div class="card-body">
									<div class="track-number track-item">
										<div class="name">Tracking Number</div>
										<div class="value"></div>
									</div>
									<div class="ecpp-number track-item">
										<div class="name">ECPP Number</div>
										<div class="value"></div>
									</div>
								</div>
							</div>
							
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Pay Info</div>
								</div>
								<div class="card-body">
									<div class="pay-status sum-item">
										<div class="name">Pay Status</div>
										<div class="value">Unpaid</div>
									</div>
									<div class="pay-number sum-item">
										<div class="name">Pay Number</div>
										<div class="value">MLxxxxxxx</div>
									</div>
									<div class="pay-method sum-item">
										<div class="name">Pay Method</div>
										<div class="value">Other</div>
									</div>
									<div class="pay-paypal-id pay-paypal sum-item">
										<div class="name"><span class="pay-pay-method">...</span> ID</div>
										<div class="value"></div>
									</div>
									<div class="pay-paypal-status pay-paypal sum-item">
										<div class="name"><span class="pay-pay-method">...</span> Status</div>
										<div class="value"></div>
									</div>
									<div class="pay-paypal-number pay-paypal sum-item">
										<div class="name"><span class="pay-pay-method">...</span> number</div>
										<div class="value"></div>
									</div>
									<div class="pay-purchase-time sum-item">
										<div class="name">Purchase Time</div>
										<div class="value"></div>
									</div>
									<div class="pay-create-time sum-item">
										<div class="name">Pay Create Time</div>
										<div class="value"></div>
									</div>
									<div class="pay-end-time sum-item">
										<div class="name">Pay End Time</div>
										<div class="value"></div>
									</div>									
								</div>	
							</div>
							
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Customer Info</div>
								</div>
								<div class="card-body customer-info">Non-registered user!</div>
							</div>
						</div>
					</div>
				</div>
				<!-- mask -->
				<div class="c-mask">
					<div class="spinner-border"></div>
				</div>
			</div>
		</div>
		<jsp:include page="../layout/backfooter.jsp" flush="true"></jsp:include>
	</div>
	<!-- paypal track info -->
	<div class="pre-code-modal hide">
		<div class="pre-code-head">
			<div class="pre-code-close">x</div>
		</div>
		<div class="pre-code-body">
			<div class="pre-code-content"></div>
		</div>
	</div>
	<jsp:include page="../common/backfooter.jsp" flush="true"></jsp:include>
	<jsp:include page="../common/deleteModal.jsp" flush="true"></jsp:include>
	<jsp:include page="../common/checkoutViewModal.jsp" flush="true"></jsp:include>
	
	<script type="text/javascript" src="${APP_PATH}/static/back/lib/datetimepicker/moment.min.js"></script>
	<script type="text/javascript" src="${APP_PATH}/static/back/lib/datetimepicker/daterangepicker.js"></script>
	<!-- custom script -->
	<script>
		var isCreate = false, oldTime = (new Date()).getTime(), timer = null, storageName = "orders";
		// init
		var date = new Date();
		var ymd = date.getFullYear() + '-' + (date.getMonth() + 1 > 9 ? date.getMonth() + 1 : '0' + (date.getMonth() + 1)) + '-' + (date.getDate() > 9 ? date.getDate() : '0' + date.getDate());
		$('#order-create-time').val(ymd + ' 00:00:00');
		$('#order-confirm-time').val(ymd + ' 23:59:59');
		bindDateRangeEvent(function(startTime, endTime, self) {
			$('#order-create-time').val(startTime);
			$('#order-confirm-time').val(endTime);		
		});
		renderTabItems();
		// pagination a-click
		$(document.body).on('click', '#table-pagination li', function (e) {
			getTabSearchData($('.c-table-tab-item.active'));
		});
		// create order
		$('.btn-create').on('click', function () {
			$('.c-view c-option-title').text('Create Order');
			showViewBlock();
			getOrderId();
			isCreate = true;
		});
		$('.btn-back').on('click', function () {
			$('.c-view c-option-title').text('Order List');
			showInitBlock();
		});
		// checkEcppIfSend
		$('.ecpp-data-sync').on('click', function() {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlfrontPayInfo/checkEcppIfSend",
				type: "post",
				data: JSON.stringify({
					"payinfoCreatetime": $('#order-create-time').val(),
				    "payinfoMotifytime": $('#order-confirm-time').val()
				}),
				dataType: "json",
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						toastr.success(data.extend.resMsg);
						updateSearchData();
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function (err) {
					toastr.error('Failed to refresh ecpp-sync-data, please refresh the page to get again!');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		});
		//checkEcppIfVerify
		$('.ecpp-verify-sync').on('click', function() {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlfrontPayInfo/checkEcppIfVerify",
				type: "post",
				data: JSON.stringify({
					"payinfoCreatetime": $('#order-create-time').val(),
				    "payinfoMotifytime": $('#order-confirm-time').val()
				}),
				dataType: "json",
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						toastr.success(data.extend.resMsg);
						updateSearchData();
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function (err) {
					toastr.error('Failed to refresh ecpp-verify-data, please refresh the page to get again!');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		});
		// single order ecpp verify
		$('#ecpp-verify').on('click', function() {
			var ecppData = $(this).data('ecpp');
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlfrontPayInfo/checkEcppOneIfVerify",
				type: "post",
				data: JSON.stringify(ecppData),
				dataType: "json",
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						toastr.success(data.extend.resMsg);
						getOneOrderData({
							payinfoId: ecppData.payinfoId
						}, function(resData) {
							renderOrderDetails(resData);
						});
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function (err) {
					toastr.error('Failed to refresh ecpp-verify-data, please refresh the page to get again!');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		});
		// View  Order
		$(document.body).on('click', '.btn-view, .c-table-table tbody .table-view', function (e) {
			var payinfoId = parseInt($(this).data('id') || $(this).parent().find('.btn-view').data('id'));
			getOneOrderData({
				payinfoId: payinfoId
			}, function(resData) {
				$('.c-view c-option-title').text('View Order');
				renderOrderDetails(resData);
				showViewBlock();
			});
		});
		// tab-item click
		$(document.body).on('click', '.c-table-tab-item', function (e) {
			$('.c-table-tab-item').removeClass('active');
			$(this).addClass('active');
			// inital pagination num
			setPageNum(1);
			setActiveItemNum($(this).data('idx'));
			getTabSearchData($(this));
		});
		// tab delete
		$(document.body).on('click', '.delete-table-tab-item', deleteTableTabItem);
		// save search
		$('.btn-save-search').on('click', function () {
			var searchOrderVal = {
				payinfostatusval: $('#payinfoStatus').find('option:selected').text(),
				payinfostatus: $('#payinfoStatus').val(),
				payinfonum: $('#payinfoPlateNum').val()
			};
			// cancel repeat add save-search
			if (checkNewItem(searchOrderVal)) return;
			if (parseInt(searchOrderVal.payinfostatus) == 999) searchOrderVal.payinfostatusval = "";
			if (searchOrderVal.payinfostatus || searchOrderVal.payinfonum) {
				addStorageItem(searchOrderVal);
				$('.c-table-tab-tempory').html('');
				createTableTabItem(searchOrderVal);
				addTableTabItem(searchOrderVal, $('.c-table-tab-item').length);
			}
		});
		// search it
		$('#payinfoStatus').on('change', function() {
			$(this).attr('data-val', $(this).val());
			updateSearchData();
		});
		$('#payinfoPlateNum').on('keyup', function() {
			var distanceTime = 1000,
				newTime =  (new Date()).getTime();
			if (newTime - oldTime < 1000) clearTimeout(timer);
			oldTime = newTime;
			timer = setTimeout(function() {
				updateSearchData();
			}, distanceTime);
		});
		// order data
		$('#download-audit').on('click', function() {
			var payinfoStatus =$("#payinfoStatus").val();
			var payinfoCreatetime =$('#order-create-time').val()
			var payinfoMotifytime  = $('#order-confirm-time').val()
			// console.log("payinfoStatus:"+payinfoStatus+"payinfoCreatetime:"+payinfoCreatetime+"payinfoMotifytime"+payinfoMotifytime);
			window.location.href = "${APP_PATH}/ExcleDownload/exportPayInfoIF?payinfoStatus="+payinfoStatus+"&payinfoCreatetime="+payinfoCreatetime+"&payinfoMotifytime="+payinfoMotifytime;
		});
		// send sms
		$('#send-sms').on('click', function() {
			var payinfoCreatetime =$('#order-create-time').val();
			var payinfoMotifytime  = $('#order-confirm-time').val();
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlfrontOrderList/toSendUnpaySMS",
				type: "post",
				data: JSON.stringify({
					'searchCreatetime': payinfoCreatetime,
					'searchMotifytime': payinfoMotifytime,
				}),
				dataType: "json",
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function (err) {
					toastr.error('网络异常，请重新返送SMS');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		});
		// send-email
		$('#send-email').on('click', function() {
			var payinfoCreatetime =$('#order-create-time').val();
			var payinfoMotifytime  = $('#order-confirm-time').val();
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlfrontPayOrderQuazList/selectOrderlistBySearch",
				type: "post",
				data: JSON.stringify({
					'searchCreatetime': payinfoCreatetime,
					'searchMotifytime': payinfoMotifytime,
				}),
				dataType: "json",
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function (err) {
					toastr.error('网络异常，请重新返送email');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		});
		// send-email-one
		$('#send-email-one').on('click', function() {
			function getOrderProductInfo() {
				var orderProductStr = '';
				$('.payinfo .order-item').each(function(idx, item) {
					var $item = $(item);
					orderProductStr += '<tr style="font-size: 16px;">' +
						'<td align="left" valign="top" style="padding-bottom: 10px;">' +
							'<img width="100" height="100" src="'+ $item.find('img').attr('src') +'" />' +
						'</td>' +
						'<td align="left" valign="top" style="padding-left: 10px; padding-bottom: 10px; max-width: 302px;">'+ $item.find('.order-product-link').html() +'</td>' +
						'<td align="left" valign="top" style="padding-left: 10px; padding-bottom: 10px;">'+ $item.find('.order-product-cal .order-product-money').html() +'</td>' +
					'</tr>';
				});
				return orderProductStr;
			}

			function getRecoverLink(payOrderId) {
				var cRecoverLink = '';
				$.ajax({
					url: "${APP_PATH}/MlfrontOrderList/getOlderIdSecretCode",
					type: "post",
					data: JSON.stringify({ 'searchId': payOrderId}),
					dataType: "json",
					contentType: 'application/json',
					async: false,
					success: function (data) {
						if (data.code == 100) {
							cRecoverLink = data.extend.ReturnPayUrl;
						}
					}
				});
				return cRecoverLink;
			}

			function getEmailTemplateIfno() {
				var emailTemplateInfo = '';

				$.ajax({
					url: "${APP_PATH}/MlbackHtmlEmail/getOneMlbackHtmlEmailOneAllDetailByName",
					type: "post",
					data: JSON.stringify({ "htmlemailName": "checkEmail" }),
					dataType: "json",
					contentType: 'application/json',
					async: false,
					success: function (data) {
						if (data.code == 100) {
							emailTemplateInfo = data.extend.mlbackHtmlEmailOne;
						}
					}
				});
				return emailTemplateInfo;
			}

			$('.c-mask').show();
			var htmlProductStr = getOrderProductInfo();
			var recoverLink = getRecoverLink($(this).data('id'));
			var etIfno = getEmailTemplateIfno();
			var htmlEmailStr = '<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width: 666px !important;border-collapse: collapse;mso-table-lspace: 0pt;mso-table-rspace: 0pt;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%;border: 0;background-color: #fafafa;"><tbody><tr><td style="padding: 16px;">'+
				'<table border="0" cellpadding="0" cellspacing="0" width="100%">' +
					'<tbody>' +
						'<tr>' +
							'<td style="padding-left: 16px; padding-right: 16px; border-top: 0; border-bottom: 2px solid #EAEAEA; background-color: #FFFFFF;">' +
								'<table border="0" cellpadding="0" cellspacing="0" width="100%">' +
									'<tbody>' +
										'<tr><td align="center" style="padding-top: 40px; padding-bottom: 20px;"><img src="'+ (etIfno && etIfno.htmlemailHeadimgurl) +'" alt="" width="196" style="display: inline-block;"></td></tr>' +
										'<tr><td align="center" style="padding: 6px 0; font-size: 22px; font-weight: bold;">'+ (etIfno && etIfno.htmlemailTitle) +'</td></tr>' +
										'<tr><td style="padding: 8px 0; font-size: 18px;">'+ (etIfno && etIfno.htmlemailRetrieve) +'</td></tr>' +
										'<tr><td style="padding: 8px 0; font-size: 16px; font-weight: bold; color: red;">'+ (etIfno && etIfno.htmlemailRetrievecode && etIfno.htmlemailRetrievecode.replace(/\n/g, '<br>')) +'</td></tr>' +
										'<tr>' +
											'<td style="padding: 20px 0;">' +
												'<table border="0" cellpadding="0" cellspacing="0" width="100%">' + htmlProductStr + '</table>' +
											'</td>' +
										'</tr>' +
										'<tr>' +
											'<td align="center" style="padding-bottom: 36px;">' +
												'<a href="'+ recoverLink +'" style="display: inline-block; padding: 8px 16px; font-weight:bold; letter-spacing:normal; text-decoration:none; color:#FFFFFF; border-radius: 3px; background-color: #2BAADF;" title="Return to Checkout">Return to Checkout</a>' +
											'</td>' +
										'</tr>' +
									'</tbody>' +
								'</table>' +
							'</td>' +
						'</tr>' +
						'<tr>' +
							'<td style="padding-top: 16px; padding-bottom: 8px;">Copyright © 2021 Megalook Hair, All rights reserved.<br>You are receiving this email because you opted in via our website.</td>' +
						'</tr>' +
						'<tr><td style="padding-top: 16px; font-size: 12px; font-weight: bold;">Our mailing address is:</td></tr>' +
						'<tr><td style="padding: 6px 0; font-size: 12px;">Megalook Hair<br>Huashuo Hair<br>jin rong zhong xin<br>xu chang, He Nan 461000<br>China</td></tr>' +
					'</tbody>' +
				'</table>' +
			'</td></tr></tbody><table>';

			var customerEmail = $('.shipping-list .email .value').html();

			$.ajax({
				url: "${APP_PATH}/MlbackHtmlEmail/tosendMlbackHtmlEmailOneContent",
				type: "post",
				data: JSON.stringify({
					'htmlemailId': $('#send-sms-one').data('id'),
					'htmlemailSix': customerEmail,
					'htmlemailSeven': htmlEmailStr
				}),
				dataType: "json",
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						toastr.success('send-email success...');
					} else {
						toastr.error('send-email fail...');
					}
				},
				error: function() {
					toastr.error('send-email fail...');
				},
				complete: function() {
					$('.c-mask').hide();
				}
			});
		});
		// send-sms one
		$('#send-sms-one').on('click', function() {
			var payInfoId = $(this).data('id');
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlfrontOrderList/toSendUnpaySMSByOne",
				type: "post",
				data: JSON.stringify({ 'searchId': payInfoId }),
				dataType: "json",
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						toastr.success('send-sms success...');
					} else {
						toastr.error('send-sms fail...');
					}
				},
				error: function() {
					toastr.error('send-sms fail...');
				},
				complete: function() {
					$('.c-mask').hide();
				}
			});
		});
		// checkout-view
		$('#checkout-view').on('click', function() {
			$('#checkoutViewModal').modal('show');
		});
		// copy unpaid link
		$('#unpaid-link .btn').on('click', function() {
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

			var searchId = $('#unpaid-link').data('id');
			
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlfrontOrderList/getOlderIdSecretCode",
				type: "post",
				data: JSON.stringify({ 'searchId': searchId }),
				dataType: "json",
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						copyToClipboard(data.extend.ReturnPayUrl);
						toastr.success('拷贝复购链接成功...');
					} else {
						toastr.error('拷贝复购链接失败...');
					}
				},
				error: function (err) {
					toastr.error('拷贝复购链接失败...');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
			
		});
		// pay track log
		$('#pay-track').on('click', function() {
			if ($('.pre-code-content').data('flag')) {
				$('.pre-code-modal').removeClass('hide');
				$(document.body).css('overflow', 'hidden');
			} else {
				toastr.error('无法获取paypal记录，请稍后重试...');
			}
		});
		// pre-code modal
		$('.pre-code-modal').on('click', function(e) {
			if (e.target == this) {
				$(this).addClass('hide');
				$(document.body).css('overflow', 'auto');
			}
		});
		$('.pre-code-close').on('click', function(e) {
			$('.pre-code-modal').addClass('hide');
			$(document.body).css('overflow', 'auto');
		});
		$('.btn-refund').on('click', function() {
			var refundData = $(this).data('refundm');
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlfrontPayStatus/refundPayOrder",
				type: "post",
				data: JSON.stringify(refundData),
				dataType: "json",
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						toastr.success(data.extend.resMsg);
						getOneOrderData({
							payinfoId: refundData.payinfoId
						}, function(resData) {
							renderOrderDetails(resData);
						});
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function (err) {
					toastr.error('');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
			
		});
		$('#btn-manual-delivery').on('click', function() {
			var deliveryData = $(this).data('delivery');
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlfrontPayInfo/manualUpdateSendSucceed",
				type: "post",
				data: JSON.stringify(deliveryData),
				dataType: "json",
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						toastr.success(data.extend.resMsg);
						getOneOrderData({
							payinfoId: deliveryData.payinfoId
						}, function(resData) {
							renderOrderDetails(resData);
						});
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function (err) {
					toastr.error('');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
			
		});
		$('.btn-close').on('click', function() {
			var closeData = $(this).data('colsem');
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlfrontPayStatus/closePayOrder",
				type: "post",
				data: JSON.stringify(closeData),
				dataType: "json",
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
					  console.log(data)
						toastr.success(data.extend.resMsg);
						getOneOrderData({
							payinfoId: closeData.payinfoId
						}, function(resData) {
							renderOrderDetails(resData);
						});
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function (err) {
					toastr.error('');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
			
			
		});
		// search status change
		function updateSearchData() {
			var searchOrderVal = {
				payinfostatusval: $('#payinfoStatus').find('option:selected').text(),
				payinfostatus: $('#payinfoStatus').val(),
				payinfonum: $('#payinfoPlateNum').val()
			};
			// inital pagination num
			setPageNum(1);
			// check searchOrder
			if (parseInt(searchOrderVal.payinfostatus) == 999) searchOrderVal.payinfostatusval = "";

			$('.c-table-tab-item.active').removeClass('active');
			$('.c-table-tab-tempory').html(createTableTabItem(searchOrderVal).addClass('active'));
			getTabSearchData($('.c-table-tab-tempory .c-table-tab-item'));
		}
		// tab view/init
		function showViewBlock() {
			$('.c-init').addClass('hide');
			$('.c-view').removeClass('hide');
		}
		function showInitBlock() {
			$('.c-init').removeClass('hide');
			$('.c-view').addClass('hide');
		}
		// get Data for table
		function getTabSearchData($this) {
			function fnSendSms() {
				if ('' + $('#payinfoStatus').val() == '0') {
					$('.table-unpaid').addClass('hide');
					$('.table-sms,.table-email').removeClass('hide');
					$('#send-email,#send-sms,#send-email-one,#send-sms-one').removeClass('hide');
				} else {
					$('.table-unpaid').removeClass('hide');
					$('#send-email,#send-sms,#send-email-one,#send-sms-one').addClass('hide');
					$('.table-sms,.table-email').addClass('hide');
				}
			}
			var dataVal = $this.data('val');
			if (dataVal && (dataVal.payinfostatusval || dataVal.payinfonum)) {
				$('#payinfoPlateNum').val(dataVal.payinfonum || '');
				$('#payinfoStatus').attr('data-val', dataVal.payinfostatus || '-1');
				$('#payinfoStatus').val(dataVal.payinfostatus || '-1');
				getSearchOrdersData(fnSendSms);
			} else {
				$('#payinfoPlateNum').val('');
				$('#payinfoStatus').val('999');
				initActiveItemNum();
				getOrdersData(fnSendSms);
			}
		}
		// callback init order details
		function getNewPayinfoSendNum (str){
			var nStr = '';
			if (str) {
				if (str.endsWith('intofail')) {
					nStr = str.replace('intofail', '<i style="color: red; font-weight: bold">intofail</i>');
					$('#btn-manual-delivery').removeClass('hide');
				} else if (str.endsWith('succeed')) {
					nStr = str.replace('succeed', '<i style="color: green; font-weight: bold">succeed</i>');
					$('#btn-manual-delivery').addClass('hide');
				} else {
					nStr = str;
				}
			}
			return nStr;
		}
		
		function renderOrderDetails(data) {
			// order list
			renderOrderList(data.mlfrontOrderItemList);
			// sum info
			$('.pay-status .value').html(getPayStatus(data.mlfrontPayInfoOne.payinfoStatus));
			$('.pay-number .value').html(data.mlfrontPayInfoOne.payinfoPlatenum || '');
			$('.pay-method .value').html(data.mlfrontPayInfoOne.payinfoPlatform || '');
			var payStatus = '<a class="badge '+ ((data.mlfrontPayInfoOne.payinfoTransStatus == 'completed' || data.mlfrontPayInfoOne.payinfoTransStatus == 'succeeded') ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data.mlfrontPayInfoOne.payinfoTransStatus || '') +'</a>';

			$('.pay-paypal-id .value').html(data.mlfrontPayInfoOne.payinfoTransidnum || '');
			$('.pay-paypal-number .value').html(data.mlfrontPayInfoOne.payinfoPlatformserialcode || '');
			$('.pay-paypal-status .value').html(payStatus);
		
			if (/pm\_/.test(data.mlfrontPayInfoOne.payinfoTransidnum)) {
				$('.pay-pay-method').text('Stripe ');
				$('.pay-paypal-number').addClass('hide');
			} else {
				$('.pay-pay-method').text('Paypal ');
				$('.pay-paypal-number').removeClass('hide');
			}
			
			$('.pay-purchase-time .value').html(data.mlfrontOrderPayOneRes.orderCreatetime || '');
			$('.pay-create-time .value').html(data.mlfrontPayInfoOne.payinfoCreatetime || '');
			$('.pay-end-time .value').html(data.mlfrontPayInfoOne.payinfoReturntime || '');
			
			/* order operate */
			$('.payinfo-group .btn, #checkout-view, #unpaid-link').addClass('hide');
			if (data.mlfrontPayInfoOne.payinfoStatus == 0) {
				$('.btn-abandon-purchase,.btn-close,#checkout-view,#unpaid-link').removeClass('hide');
				$('#unpaid-link,#send-email-one').data('id', data.mlfrontOrderPayOneRes.orderId);
				$('#send-sms-one').data('id', data.mlfrontPayInfoOne.payinfoId);
			} else if (data.mlfrontPayInfoOne.payinfoStatus == 1) {
				$('.btn-audit,.btn-refund').removeClass('hide');
			} else if (data.mlfrontPayInfoOne.payinfoStatus == 2 || data.mlfrontPayInfoOne.payinfoStatus == 3) {
				$('.btn-refund').removeClass('hide');
			}

			$('.payinfo .pay-prototal .value').html('$' + (((data.mlfrontPayInfoOne.payinfoMoney + (data.mlfrontOrderPayOneRes.orderCouponPrice || 0) - data.areafreightMoney)) || 0).toFixed(2));
			$('.payinfo .pay-discount .name').html(data.mlfrontOrderPayOneRes.orderCouponCode || '');
			$('.payinfo .pay-discount .value').html('-$' + (data.mlfrontOrderPayOneRes.orderCouponPrice || 0).toFixed(2));
			$('.payinfo .pay-shipping .value').html('$' + (data.areafreightMoney || 0).toFixed(2));
			$('.payinfo .pay-total .value').html('$' + (data.mlfrontPayInfoOne.payinfoMoney || 0).toFixed(2));
			$('.payinfo .pay-final .value').html('$' + (data.mlfrontPayInfoOne.payinfoMoney || 0).toFixed(2));
			
			// customer note
			$('.customer-note .value').html(data.mlfrontOrderPayOneRes.orderBuyMess || 'No notes from customer...');
			// track
			
			$('.track-number .value').html('('+ (data.mlfrontOrderPayOneRes.orderLogisticsname || '') +')' + (getNewPayinfoSendNum(data.mlfrontPayInfoOne.payinfoSendnum)));
			$('.ecpp-number .value').html(data.mlfrontPayInfoOne.payinfoEcpphsnum || '');
			// order shipping
			$('.old-shipping .order-shipping.firstname .value').html(data.mlfrontAddressOne.addressUserfirstname || '');
			$('.old-shipping .order-shipping.lastname .value').html(data.mlfrontAddressOne.addressUserlastname || '');
			$('.old-shipping .order-shipping.email .value').html(data.mlfrontAddressOne.addressEmail || '');
			$('.old-shipping .order-shipping.phone .value').html(data.mlfrontAddressOne.addressTelephone || '');
			$('.old-shipping .order-shipping.country .value').html(data.mlfrontAddressOne.addressCountry + ' ('+ data.mlfrontAddressOne.addressCountryCode +')');
			$('.old-shipping .order-shipping.province .value').html(data.mlfrontAddressOne.addressProvince + ' ('+ data.mlfrontAddressOne.addressProvincecode +')');
			$('.old-shipping .order-shipping.city .value').html(data.mlfrontAddressOne.addressCity || '');
			$('.old-shipping .order-shipping.postcode .value').html(data.mlfrontAddressOne.addressPost || '');
			$('.old-shipping .order-shipping.address .value').html(data.mlfrontAddressOne.addressDetail || '');
			// billing/ecpp
			$('.billing-item.paymentid .value').html(data.mlPaypalShipAddressOne.shippingaddressPaymentid || '');
			$('.billing-item.username .value').html(data.mlPaypalShipAddressOne.shippingaddressRecipientName || '');
			$('.billing-item.phone .value').html(data.mlPaypalShipAddressOne.shippingaddressTelNumber || '--');
			$('.billing-item.email .value').html(data.mlPaypalShipAddressOne.shippingaddressEmail || '');
			$('.billing-item.country .value').html((data.mlPaypalShipAddressOne.shippingaddressCountryName || '--') + ' ('+ (data.mlPaypalShipAddressOne.shippingaddressCountryCode || '--') +')');
			$('.billing-item.city .value').html(data.mlPaypalShipAddressOne.shippingaddressCity || '');
			$('.billing-item.province .value').html((data.mlPaypalShipAddressOne.shippingaddressStateProvinceName || '--') + ' ('+ (data.mlPaypalShipAddressOne.shippingaddressState || '--') +')');
			$('.billing-item.postcode .value').html(data.mlPaypalShipAddressOne.shippingaddressPostalCode || '');
			$('.billing-item.line1 .value').html(data.mlPaypalShipAddressOne.shippingaddressLine1 || '');
			$('.billing-item.line2 .value').html(data.mlPaypalShipAddressOne.shippingaddressLine2 || '');

			// checkout view order-list
			renderCheckoutView(data.mlfrontCheckoutViewOne);

			var paypaylAddressInfo = data.mlPaypalShipAddressOne.shippingaddressPaymentStr;
			if (paypaylAddressInfo) {
				var opaypal = JSON.parse(paypaylAddressInfo);
				var npaypal = {};
				var transactions = opaypal['transactions'];

				if (transactions && transactions.length) {
					transactions.forEach(function(trans) {
						if (trans['related_resources'] && (trans['related_resources']).length) {
							trans['related_resources'].forEach(function(item, idx) {
								delete item.sale['links'];
							});
						}
					});
				}

				npaypal['id'] = opaypal['id'];
				npaypal['payer'] = opaypal['payer'];
				npaypal['transactions'] = transactions;

				$('.pre-code-content').html('<pre><code>'+ JSON.stringify(npaypal, null, 2) +'</code></pre>').data('flag', true);
			} else {
				$('.pre-code-content').html('').data('flag', false);
			}
			// customer info
			if (data.mlfrontUserOne) {
				var html = '<div class="customer-item"><div class="name">E-mail:</div><div class="value">'+ data.mlfrontUserOne.userEmail +'</div></div>' +
					'<div class="customer-item"><div class="name">Customer Name:</div><div class="value">'+ (data.userLastname && (data.userLastname + ' ' + data.mlfrontUserOne.userFirstname) || '') +'</div></div>' +
					'<div class="customer-item"><div class="name">Telephone Number:</div><div class="value">'+ (data.mlfrontUserOne.userTelephone || '') +'</div></div>' +
					'<div class="customer-item"><div class="name">Purchase times:</div><div class="value">'+ data.mlfrontUserOne.userTimes +'</div></div>';
				$('.customer-info').html(html);
			} else {
				$('.customer-info').html('Non-registered user!');
			}
			// ecpp-verfiy prop data
			$('#ecpp-verify').data('ecpp', {
				"payinfoId": data.mlfrontPayInfoOne.payinfoId,
			    "payinfoOid": data.mlfrontPayInfoOne.payinfoOid,
			    "payinfoPlatenum": data.mlfrontPayInfoOne.payinfoPlatenum,
			    "payinfoUemail": (data.mlfrontAddressOne.addressEmail || data.mlPaypalShipAddressOne.shippingaddressEmail),
			    "payinfoEcpphsnum": data.mlfrontPayInfoOne.payinfoEcpphsnum,
			    "payinfoReturntime": data.mlPaypalShipAddressOne.shippingaddressEmail
			});
			
			
			$('.btn-refund').data('refundm',{
				"payinfoId": data.mlfrontPayInfoOne.payinfoId,
				"payinfoOid": data.mlfrontPayInfoOne.payinfoOid,
			});
			$('.btn-close').data('colsem',{
				"payinfoId": data.mlfrontPayInfoOne.payinfoId,
				"payinfoOid": data.mlfrontPayInfoOne.payinfoOid,
			});
			$('#btn-manual-delivery').data('delivery',{
				"payinfoId": data.mlfrontPayInfoOne.payinfoId,
				"payinfoSendnum": data.mlfrontPayInfoOne.payinfoSendnum,
			});
		}
		// callback order list
		function renderOrderList(data) {
			var htmlStr = '';
			function genSkus(item) {
				var skuHtml = '';
				var optionNameArr = item.orderitemPskuIdnamestr.split(',');
				var optionValueArr = item.orderitemPskuName.split(',');
				optionNameArr.forEach(function(item, idx) {
					skuHtml += '<div class="order-poption-item">' +
							'<div class="name">'+ item +': </div>' +
							'<div class="value">'+ optionValueArr[idx] +'</div>' +
						'</div>';
				});
				return skuHtml;
			}
			data.forEach(function(item, idx) {
				var singlePrice = accuracyCal(parseFloat(item.orderitemProductOriginalprice) + parseFloat(item.orderitemPskuMoneystr), parseFloat(item.orderitemProductAccoff));
				htmlStr += '<div class="order-item">' +
						'<img src="'+ item.orderitemProductMainimgurl +'">' +
						'<div class="order-product-base">' +
							'<a class="order-product-link" href="${APP_PATH}/'+ item.orderitemPseo +'.html" target="_blank">'+ item.orderitemPname +'</a>' +
							'<div class="order-product-option">'+ genSkus(item) +'</div>' +
							'<div class="order-product-sku"><span>SKU: '+ item.orderitemPskuCode +'</span>------------- <span style="color:#6b6464;">('+ item.orderitemProductOriginalprice +'+'+ item.orderitemPskuMoneystr +')*'+ (parseFloat(item.orderitemProductAccoff)/100).toFixed(2) + '</span></div>' +
						'</div>' +
						'<div class="order-product-cal">' +
							'<div class="order-product-money">$'+ singlePrice +'</div>' +
							'<div class="order-product-divider">x</div>' +
							'<div class="order-product-num">'+ item.orderitemPskuNumber +'</div>' +
							'<div class="order-product-total">$'+ (item.orderitemPskuNumber * parseFloat(singlePrice)).toFixed(2) +'</div>' +
						'</div>' +
					'</div>';
			});
			$('.payinfo .order-list').html(htmlStr || 'no product for order...');
		}
		// render checkout view order-list
		function renderCheckoutView(data) {
			var htmlStr = '';
			var checkoutViewAllPrice = 0;
			if (data) {
				var productSeoArr = data.checkoutviewOrderProseoStr && data.checkoutviewOrderProseoStr.split(',') || [],
					productNumArr = data.checkoutviewOrderPronumStr && data.checkoutviewOrderPronumStr.split(',') || [],
					productPriceArr = data.checkoutviewOrderitempskumoneystr && data.checkoutviewOrderitempskumoneystr.split(',') || [],
					skuCodeArr = data.checkoutviewOrderOrderitempskucode && data.checkoutviewOrderOrderitempskucode.split(',') || [],
					skuIdNameArr = data.checkoutviewOrderitempskuidnamestr && data.checkoutviewOrderitempskuidnamestr.split('.') || [],
					skuNameArr = data.checkoutviewOrderitempskuname && data.checkoutviewOrderitempskuname.split('.') || [];
				function genSkus(id) {
					var skuHtml = '';
					var optionNameArr = skuIdNameArr[id].split(',');
					var optionValueArr = skuNameArr[id].split(',');
					optionNameArr.forEach(function(item, idx) {
						skuHtml += '<div class="order-poption-item">' +
								'<div class="name">'+ item +': </div>' +
								'<div class="value">'+ optionValueArr[idx] +'</div>' +
							'</div>';
					});
					return skuHtml;
				}
				productSeoArr.forEach(function(item, idx) {
					checkoutViewAllPrice += parseInt(productNumArr[idx], 10) * parseFloat(productPriceArr[idx]);
					htmlStr += '<div class="order-item">' +
							'<div class="order-product-base">' +
								'<a class="order-product-link" href="${APP_PATH}/'+ productSeoArr[idx] +'.html" target="_blank">'+ productSeoArr[idx] +'</a>' +
								'<div class="order-product-option">'+ genSkus(idx) +'</div>' +
								'<div class="order-product-sku">SKU: '+ skuCodeArr[idx] +'</div>' +
							'</div>' +
							'<div class="order-product-cal">' +
								'<div class="order-product-money">$'+ parseFloat(productPriceArr[idx]).toFixed(2) +'</div>' +
								'<div class="order-product-divider">x</div>' +
								'<div class="order-product-num">'+ productNumArr[idx] +'</div>' +
								'<div class="order-product-total">$'+ (parseInt(productNumArr[idx], 10) * parseFloat(productPriceArr[idx])).toFixed(2) +'</div>' +
							'</div>' +
						'</div>';
				});
			} else {
				htmlStr = 'no product for order...';
			}

			$('.checkout-view-box .order-list').html(htmlStr);

			// checkout view order-info
			$('.checkout-view-box .pay-prototal .value').html('$' + (checkoutViewAllPrice).toFixed(2));
			$('.checkout-view-box .pay-discount .name').html(data.checkoutviewCouponCode || '--');
			$('.checkout-view-box .pay-discount .value').html('-$' + (data.checkoutviewCouponPrice || '--'));
			$('.checkout-view-box .pay-shipping .value').html('$' + data.checkoutviewAddressPrice);
			$('.checkout-view-box .pay-final .value, .checkout-view-box  .pay-total .value').html('$' + (checkoutViewAllPrice + parseFloat(data.checkoutviewAddressPrice || 0) - (parseFloat(data.checkoutviewCouponPrice || 0))).toFixed(2));

			// checkout view shipping
			$('.checkout-view-shipping.firstname .value').html(data.checkoutviewUserfirstname || '--');
			$('.checkout-view-shipping.lastname .value').html(data.checkoutviewUserlastname || '--');
			$('.checkout-view-shipping.email .value').html(data.checkoutviewEmail || '--');
			$('.checkout-view-shipping.phone .value').html(data.checkoutviewTelephone || '--');
			$('.checkout-view-shipping.country .value').html((data.checkoutviewCountry || '--') + ' ('+ (data.checkoutviewCountryCode || '--') +')');
			$('.checkout-view-shipping.province .value').html((data.checkoutviewProvince || '--') + ' ('+ (data.checkoutviewProvincecode || '--') +')');
			$('.checkout-view-shipping.city .value').html(data.checkoutviewCity || '--');
			$('.checkout-view-shipping.postcode .value').html(data.checkoutviewPost || '--');
			$('.checkout-view-shipping.address .value').html(data.checkoutviewAddressdetail || '--');
		}
		//  callback get all
		function getOrdersData(callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlfrontPayInfo/getMlfrontPayInfoByPage",
				type: "post",
				data: "pn=" + getPageNum(),
				success: function (data) {
					if (data.code == 100) {
						renderTable(data.extend.pageInfo.list);
						renderTablePagination(data.extend.pageInfo);
						toastr.success(data.extend.resMsg);
						callback && callback();
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function (err) {
					toastr.error('Failed to get Super-Categeory, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback get search data
		function getSearchOrdersData(callback) {
			$('.c-mask').show();

			var formData = new FormData();
			formData.append('payinfoPlateNum', $('#payinfoPlateNum').val());
			formData.append('payinfoStatus', ($('#payinfoStatus').attr('data-val') || '999'));
			formData.append('pn', getPageNum());

			$.ajax({
				url: "${APP_PATH}/MlfrontPayInfo/selectHighPayInfoListBySearch",
				type: "post",
				data: formData,
				processData: false,
				contentType: false,
				cache: false,
				success: function (data) {
					if (data.code == 100) {
						renderTable(data.extend.pageInfo.list);
						renderTablePagination(data.extend.pageInfo);
						toastr.success(data.extend.resMsg);
						callback && callback();
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function () {
					toastr.error('Failed to get Orders, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback get one order data
		function getOneOrderData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlfrontPayInfo/backGetOnePayInfoDetail",
				type: "post",
				data: JSON.stringify(reqData),
				dataType: "json",
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend);
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function (err) {
					toastr.error('Failed to get Super-Categeory, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		function getPayStatus(num) {
			var statusText;
			switch(num) {
				case 0:
					statusText = '<a class="badge badge-danger">unpaid</a>'; // 未支付 red
				    break;
				case 1:
					statusText = '<a class="badge badge-warning">paid</a>'; // 已支付  yellow
					break;
				case 2:
					statusText = '<a class="badge badge-success">audited</a>'; // 已审核  green
					break;
				case 3:
					statusText = '<a class="badge badge-info">delivered</a>'; // 已发货  purple
				    break;
				case 4:
					statusText = '<a class="badge badge-primary">refunded</a>'; // 已退款  blue
					break;
				case 5:
					statusText = '<a class="badge badge-light">abandon-purchase</a>'; // 已通知弃购  light
					break;
				case 6:
					statusText = '<a class="badge badge-dark">closed</a>'; // 已关闭  dark
					break;
				default:
					statusText = '<a class="badge badge-danger">unpaid</a>'; // 未支付 red
			}
			return statusText;
		}
		// init table-list
		function renderTable(data) {
			function getSendEmailStatus(status) {
				var statusText;
				switch(status) {
					case 0:
						statusText = '<a class="badge badge-danger">not send</a>'; // 未发送 red
					    break;
					case 1:
						statusText = '<a class="badge badge-success">send</a>'; // 发送  green
						break;
					case 2:
						statusText = '<a class="badge badge-primary">repeat</a>'; // 重复 blue
					    break;
					case 3:
						statusText = '<a class="badge badge-info">Deal</a>'; // 成交  purple
						break;
					case 9:
			            statusText = '<a class="badge badge-dark">Deal</a>'; // 成交  purple
			            break;
					default:
						statusText = '<a class="badge badge-danger">not send</a>'; // 未发送
				}
				return statusText;
			}
			var htmlStr = '';
			for (var i = 0, len = data.length; i < len; i += 1) {
				htmlStr += '<tr><td class="table-view">' + data[i].payinfoId + '</td>' +
					'<td>' + (data[i].payinfoOid || '') + '</td>' +
					'<td>' + (data[i].payinfoPlatenum || '') + '</td>' +
					/*'<td>' + data[i].payinfoCreatetime + '</td>' +*/
					'<td>' + data[i].payinfoReturntime + '</td>' +
					'<td>' + getPayStatus(data[i].payinfoStatus) + '</td>' +
					'<td>' + (data[i].payinfoMoney || 0).toFixed(2) + '</td>' +
					'<td>' + (data[i].payinfoUname || '') + '</td>' +
					/* '<td>' + (data[i].payinfoUemail || '') + '</td>' +  */
					'<td><img style="width: 60px;" src="${APP_PATH}/static/pc/img/' + ((data[i].payinfoPlatform).toLowerCase() == 'bank_card' ? 'paypal-2.png' : 'paypal-1.png') + '"/></td>' +
					'<td class="table-sms hide">'+ getSendEmailStatus(data[i].payinfoIfSMS) +'</td>' +
					'<td class="table-email hide">'+ getSendEmailStatus(data[i].payinfoIfEmail) +'</td>' +
					'<td class="table-unpaid">' + (data[i].payinfoTransidnum || '') + '</td>' +
					'<td class="table-unpaid"><a class="badge '+ ((data[i].payinfoTransStatus == 'completed' || data[i].payinfoTransStatus == 'succeeded') ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].payinfoTransStatus || '') + '</td>' +
					'<td class="table-unpaid">' + (data[i].payinfoEcpphsnum || '') + '</td>' +
					'<td class="table-unpaid">' + (data[i].payinfoEcpphsnumStatus || '') + '</td>' +
					'<td class="table-unpaid">' + (getNewPayinfoSendNum(data[i].payinfoSendnum)) + '</td>' +
					'<td>' +
						'<button class="btn btn-primary btn-view" data-id="' + data[i].payinfoId + '">' +
							'<svg class="c-icon">' +
								'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-eye"></use>' +
							'</svg>' +
						'</button>' +
						/* '<button class="btn btn-danger btn-delete" data-id="' + data[i].payinfoId + '">' +
							'<svg class="c-icon">' +
								'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-trash"></use>' +
							'</svg>' +
						'</button>' + */
					'</td></tr>';
			}
			$('.c-table-table tbody').html(htmlStr);
		}
	</script>
</body>

</html>