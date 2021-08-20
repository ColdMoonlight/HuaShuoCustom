<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Statics Add-To-Cart</title>
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
						<span class="c-option-title">Search - List</span>
						<div class="group">
							<a href="${APP_PATH}/BackHome/BackHomePage" class="btn btn-secondary btn-back">Back</a>
						</div>
					</div>
					<div class="dashboard-condition">
						<div class="form-group">
							<label class="col-form-label" for="search-time">
								<svg class="c-icon">
									<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-av-timer"></use>
								</svg>
							</label>
							<div class="controls">
								<input hidden id="search-create-time" />
								<input hidden id="search-confirm-time" />
								<input class="form-control daterangetimepicker" id="search-checkout-time" type="text" />
							</div>
						</div>
					</div>
					<div class="c-table">
						<div class="c-table-content">
							<table class="c-table-table table table-responsive-sm">
								<thead>
									<tr>
										<th>Product Seo</th>
										<th>Num</th>
									</tr>
								</thead>
								<tbody></tbody>
							</table>
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
		bindDateRangeEvent(function(startTime, endTime) {
			$('#search-create-time').val(startTime);
			$('#search-confirm-time').val(endTime);
			getCartsData(renderTable);
			
		});
		getCartsData(renderTable);
		//  callback get all
		function getCartsData(callback) {
			$('.c-mask').show();
			var date = new Date();
			var ymd = date.getFullYear() + '-' + (date.getMonth() + 1 > 9 ? date.getMonth() + 1 : '0' + (date.getMonth() + 1)) + '-' + (date.getDate() > 9 ? date.getDate() : '0' + date.getDate());
			var startTime = $('#search-create-time').val() || (ymd + ' 00:00:00');
			var endTime = $('#search-confirm-time').val() || (ymd + ' 23:59:59');
			$.ajax({
				url: "${APP_PATH}/MlbackSearch/getSearchUrlCountList",
				type: "post",
				data: JSON.stringify({
					'searchCreatetime': startTime,
					'searchMotifytime': endTime
				}),
				dataType: "json",
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						callback && callback(data.extend.urlCountList);
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function (err) {
					toastr.error('Failed to get  search data, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// init table-list
		function renderTable(data) {
			var htmlStr = '';
			for (var i = 0, len = data.length; i < len; i += 1) {
				htmlStr += '<tr>' +
					'<td>' + data[i].urlString + '</td>' +
					'<td>' + (data[i].urlStringNum || '') + '</td>' +
					'</tr>';
			}
			$('.c-table-table tbody').html(htmlStr);
		}
	</script>
</body>

</html>