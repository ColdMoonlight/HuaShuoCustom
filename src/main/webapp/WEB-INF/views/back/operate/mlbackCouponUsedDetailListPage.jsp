<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>coupon - order details</title>
	<jsp:include page="../common/backheader.jsp" flush="true"></jsp:include>
	<link rel="stylesheet" href="${APP_PATH}/static/back/lib/datetimepicker/daterangepicker.css">
</head>
<body class="c-app">
	<jsp:include page="../layout/backheader.jsp" flush="true"></jsp:include>
	<jsp:include page="../layout/backsidebar.jsp" flush="true"></jsp:include>
	<div class="c-wrapper">
		<div class="c-body">
			<div class="c-main">
				<div class="c-init">
					<div class="c-option">
						<span class="c-option-title">Coupon Analysis</span>
					</div>
					<div class="row">
						<div class="form-group col-md-4">
							<div class="controls">
								<input hidden id="create-time" />
								<input hidden id="confirm-time" />
								<input class="form-control daterangetimepicker" id="coupon-time" type="text" />
							</div>
						</div>
						<div class="form-group col-md-4">
							<div class="controls">
								<input class="form-control" id="coupon-name" type="text" placeholder="please enter coupon-name..." />
							</div>
						</div>
						<div class="form-group col-md-4">
							<button class="btn btn-primary" id="search-orders"> Search </button>
						</div>
					</div>
					<div class="c-table">
						<div class="c-table-content">
							<div class="c-table-table table-responsive-sm">
								<table class="table">
									<thead>
										<tr>
											<th>payId</th>
											<th>orderid</th>
											<th>coupon-code</th>
											<th>price</th>
											<th>recipt-name</th>
											<th>customer-email</th>
											<th>status</th>
											<th>pay-time</th>
										</tr>
									</thead>
									<tbody></tbody>
								</table>
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
	<jsp:include page="../common/backfooter.jsp" flush="true"></jsp:include>
	<jsp:include page="../common/deleteModal.jsp" flush="true"></jsp:include>
	<script type="text/javascript" src="${APP_PATH}/static/back/lib/datetimepicker/moment.min.js"></script>
	<script type="text/javascript" src="${APP_PATH}/static/back/lib/datetimepicker/daterangepicker.js"></script>
	<!-- custom script -->
	<script>
		// init
		var date = new Date();
		var ymd = date.getFullYear() + '-' + (date.getMonth() + 1 > 9 ? date.getMonth() + 1 : '0' + (date.getMonth() + 1)) + '-' + (date.getDate() > 9 ? date.getDate() : '0' + date.getDate());
		var urlScheme = new URLSearchParams(window.location.search);
		
		if (urlScheme.get('startTime') && urlScheme.get('endTime')) {
			$('#create-time').val(urlScheme.get('startTime') || ymd + ' 00:00:00');
			$('#confirm-time').val(urlScheme.get('endTime') || ymd + ' 23:59:59');
			$('#coupon-time').val(urlScheme.get('startTime') + ' - ' + urlScheme.get('endTime'));
			bindDateRangeEvent(function(startTime, endTime, self) {
				$('#create-time').val(startTime);
				$('#confirm-time').val(endTime);		
			});
		}

		if (urlScheme.get('coupon')) {
			$('#coupon-name').val(urlScheme.get('coupon'));
			getOrdersByCoupon({
				'searchContent': urlScheme.get('coupon'),
				'searchCreatetime': $('#create-time').val(),
				'searchMotifytime': $('#confirm-time').val()
			});
		} else {
			$('.c-table-table tbody').html('<p class="text-align;">没有任何数据记录...</p>');
		}
		// search orders
		$('#search-orders').on('click', function () {
			var couponName = $('#coupon-name').val().trim();
			if (couponName) {
				getOrdersByCoupon({
					'searchContent': couponName,
					'searchCreatetime': $('#create-time').val(),
					'searchMotifytime': $('#confirm-time').val(),
				});				
			} else {
				toastr.error('优惠券名字不能为空！');
			}
		});
		// init table-list
		function renderTable(data) {
			var htmlStr = '';
			var len = data.length;
			if (len) {
				var couponName = $('#coupon-name').val();
				for (var i = 0, len = data.length; i < len; i += 1) {
					htmlStr += '<tr><td>' + data[i].payinfoId + '</td>' +
					'<td>' + (data[i].payinfoOid || '') + '</td>' +
					'<td>' + couponName + '</td>' +
					'<td>' + (data[i].payinfoMoney || 0).toFixed(2) + '</td>' +
					'<td>' + (data[i].mlPaypalShipAddress && data[i].mlPaypalShipAddress.shippingaddressRecipientName || '') + '</td>' +
					'<td>' + (data[i].mlPaypalShipAddress && data[i].mlPaypalShipAddress.shippingaddressEmail || '') + '</td>' +
					'<td>'+ getPayStatus(data[i].payinfoStatus) +'</td>' +
					'<td>' + data[i].payinfoCreatetime + '</td></tr>';
				}
			} else {
				htmlStr = '<p class="text-align;">没有任何数据记录...</p>';
			}
			$('.c-table-table tbody').html(htmlStr);
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
		//  callback get orders
		function getOrdersByCoupon(data) {
			$('.c-mask').show();

			$.ajax({
				url: "${APP_PATH }/MlbackCoupon/getCouponUsedDetailListByTime",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				data: JSON.stringify(data),
				success: function (data) {
					if (data.code == 100) {
						renderTable(data.extend.CouponAnalysisDateList);
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function () {
					toastr.error('Failed to get Categeory, please refresh the page to get again!');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
	</script>
</body>

</html>