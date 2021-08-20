<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>coupon area details</title>
	<jsp:include page="../common/backheader.jsp" flush="true"></jsp:include>
</head>
<body class="c-app">
	<jsp:include page="../layout/backheader.jsp" flush="true"></jsp:include>
	<jsp:include page="../layout/backsidebar.jsp" flush="true"></jsp:include>
	<div class="c-wrapper">
		<div class="c-body">
			<div class="c-main">
				<div class="c-init">
					<div class="c-option">
						<span class="c-option-title">Coupon Area Details</span>
						<button class="btn btn-primary btn-create">Create Coupon-Area-Details</button>
					</div>
					<div class="c-table">
						<div class="c-table-tab">
							<div class="c-table-tab-item" data-idx="0">All</div>
							<div class="c-table-tab-list"></div>
							<div class="c-table-tab-tempory"></div>
						</div>
						<div class="c-table-content">
							<div class="c-table-table table-responsive-sm">
								<table class="table">
									<thead>
										<tr>
											<th>id</th>
											<th>Name</th>
											<th>Discount Before</th>
											<th>Discount</th>
											<th>Code Before</th>
											<th>Code</th>
											<th>state</th>
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
				<div class="c-create hide">
					<div class="c-option">
						<span class="c-option-title">Edit Coupon</span>
						<div class="group">
							<button class="btn btn-secondary btn-cancel">Cancel</button>
							<button class="btn btn-primary btn-save">Save Coupon-Area-Details</button>
						</div>
					</div>
					<div class="c-form row">
						<input id="coupondescdetailId" hidden>
						<!-- left panel -->
						<div class="left-panel col-lg-7 col-md-12">
							<div class="card">
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="coupondescdetailName">Coupon Area-details Name</label>
										<div class="controls">
											<input class="form-control" id="coupondescdetailName" type="text" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="coupondescdetailStrengthpre">Discount Before</label>
										<div class="controls">
											<input class="form-control" id="coupondescdetailStrengthpre" type="text" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="coupondescdetailStrength">Discount</label>
										<div class="controls">
											<input class="form-control" id="coupondescdetailStrength" type="text" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="coupondescdetailCodepre">Code Before</label>
										<div class="controls">
											<input class="form-control" id="coupondescdetailCodepre" type="text" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="coupondescdetailCode">Code</label>
										<div class="controls">
											<input class="form-control" id="coupondescdetailCode" type="text" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-md-3 col-form-label" for="coupondescdetailStatus">Status</label>
										<div class="controls col-md-3">
											<label class="c-switch c-switch-primary">
												<input class="c-switch-input" id="coupondescdetailStatus" type="checkbox">
												<span class="c-switch-slider"></span>
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- right panel -->						
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
	
	<!-- custom script -->
	<script>
		var isCreate = false;
		// init
		getCouponAreaDetailsData();
		// create collection
		$('.btn-create').on('click', function () {
			$('.c-create c-option-title').text('Create Coupon-Area-Details');
			$(".open_product").hide();
			$(".open_draw").hide();
			$(".open_1").hide();
			showCreateBlock();
			resetFormData();
			getCollectionId();
			isCreate = true;
		});
		// edit coupon area details
		$(document.body).on('click', '.btn-edit', function (e) {
			var coupondescdetailId = parseInt($(this).data('id'));
			getOneCouponAreaDetailsData({
				coupondescdetailId: coupondescdetailId
			}, function(resData) {
				$('.c-create c-option-title').text('Edit Coupon-Area-Details');
				showCreateBlock();
				initFormData(resData);
			});
		});
		// delete collection
		$(document.body).on('click', '.btn-delete', function (e) {
			var coupondescdetailId = parseInt($(this).data('id'));
			$('#deleteModal').find('.modal-title').html('Delete Coupon-Area-Details!');
			$('#deleteModal').modal('show');
			$('#deleteModal .btn-ok').one('click', function () {
				deleteCouponAreaDetailsData({
					coupondescdetailId: coupondescdetailId,
				}, function() {
					getCouponAreaDetailsData();
				});
			});
		});
		
		$(window).on('beforeunload', function() {
			var coupondescdetailId = $('#coupondescdetailId').val();
			isCreate && coupondescdetailId && deleteCouponAreaDetailsData({
				coupondescdetailId: coupondescdetailId,
			});
		});
		// save collection
		$('.c-create .btn-save').on('click', function () {
			var reqData = getFormData();
			saveCouponAreaDetailsData(reqData, function() {
				showInitBlock();
				getCouponAreaDetailsData();
				isCreate = false;
				$('#coupondescdetailId').val('');
			});
		});
		// cancel collection save
		$('.c-create .btn-cancel').on('click', function () {
			if (isCreate) {
				deleteCouponAreaDetailsData({
					coupondescdetailId: $('#coupondescdetailId').val(),
				}, function() {
					showInitBlock();
					isCreate = false;					
				});
			}
			showInitBlock();
		});
		// tab create/init
		function showCreateBlock() {
			$('.c-init').addClass('hide');
			$('.c-create').removeClass('hide');
		}
		function showInitBlock() {
			$('.c-init').removeClass('hide');
			$('.c-create').addClass('hide');
		}
		// handle formData
		// reset data
		function resetFormData() {
			$('#coupondescdetailId').val('');
			$('#coupondescdetailName').val('');
			$('#coupondescdetailStrengthpre').val('');
			$('#coupondescdetailStrength').val('');
			$('#coupondescdetailCodepre').val('');
			$('#coupondescdetailCode').val('');
			$('#coupondescdetailStatus').val(0);
		}
		// getFormdData
		function getFormData() {
			var data = {};
			data.coupondescdetailId = parseInt($('#coupondescdetailId').val());
			data.coupondescdetailName = $('#coupondescdetailName').val();
			data.coupondescdetailStrengthpre = $('#coupondescdetailStrengthpre').val();
			data.coupondescdetailStrength = $('#coupondescdetailStrength').val();
			data.coupondescdetailCodepre = $('#coupondescdetailCodepre').val();
			data.coupondescdetailCode = $('#coupondescdetailCode').val();
			data.coupondescdetailStatus = $('#coupondescdetailStatus').prop('checked') ? 1 : 0;
			return data;
		}
		// initFormData
		function initFormData(data) {
			$('#coupondescdetailId').val(data.coupondescdetailId);
			$('#coupondescdetailName').val(data.coupondescdetailName);
			$('#coupondescdetailStrengthpre').val(data.coupondescdetailStrengthpre);
			$('#coupondescdetailStrength').val(data.coupondescdetailStrength);
			$('#coupondescdetailCodepre').val(data.coupondescdetailCodepre);
			$('#coupondescdetailCode').val(data.coupondescdetailCode);
			$('#coupondescdetailStatus').prop('checked', data.coupondescdetailStatus);
		}
		// callback id
		function getCollectionId() {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/MlbackCouponDescDetail/initializaCouponDescDetail",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				success: function (data) {
					if (data.code == 100) {
						$('#coupondescdetailId').val(data.extend.mlbackCouponDescDetail.coupondescdetailId);
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function (err) {
					toastr.error('initial superCate fail：' + err);
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		//  callback get all
		function getCouponAreaDetailsData(val) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackCouponDescDetail/getMlbackCouponDescDetailByPage",
				type: "post",
				data: "pn=" + getPageNum(),
				success: function (data) {
					if (data.code == 100) {
						renderTable(data.extend.pageInfo.list);
						renderTablePagination(data.extend.pageInfo);
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function (err) {
					toastr.error('Failed to get coupon area, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		
		function getOneCouponAreaDetailsData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackCouponDescDetail/getCouponDescDetailById",
				type: "post",
				data: JSON.stringify(reqData),
				dataType: "json",
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend.mlbackCouponDescDetailOne);
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function (err) {
					toastr.error('Failed to get coupon area, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		/**************/
		// callback save
		function saveCouponAreaDetailsData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackCouponDescDetail/save",
				data: JSON.stringify(reqData),
				dataType: "json",
				contentType: 'application/json',
				type: "post",
				success: function (data) {
					if (data.code == 100) {
						toastr.success(data.extend.resMsg);
						callback();
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function (err) {
					toastr.error(err);
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback delete
		function deleteCouponAreaDetailsData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackCouponDescDetail/delete",
				type: "post",
				cache: false,
				dataType: "json",
				contentType: 'application/json',
				data: JSON.stringify(reqData),
				success: function (data) {
					if (data.code == 100) {
						toastr.success(data.extend.resMsg);
						$('#deleteModal').modal('hide');
						callback();
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function (err) {
					toastr.error(err);
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
				htmlStr += '<tr><td>' + data[i].coupondescdetailId + '</td>' +
					'<td>' + data[i].coupondescdetailName + '</td>' +
					'<td>' + data[i].coupondescdetailStrengthpre + '</td>' +
					'<td>' + data[i].coupondescdetailStrength + '</td>' +
					'<td>' + data[i].coupondescdetailCodepre + '</td>' +
					'<td>' + data[i].coupondescdetailCode + '</td>' +
					'<td><a class="badge '+ (data[i].coupondescdetailStatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].coupondescdetailStatus ? 'enable' : 'disable') + '</a></td>' +
					'<td>' +
						'<button class="btn btn-primary btn-edit" data-id="' + data[i].coupondescdetailId + '">' +
							'<svg class="c-icon">' +
								'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-pencil"></use>' +
							'</svg>' +
						'</button>' +
						'<button class="btn btn-danger btn-delete" data-id="' + data[i].coupondescdetailId + '">' +
							'<svg class="c-icon">' +
								'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-trash"></use>' +
							'</svg>' +
						'</button>' +
					'</td></tr>';
			}
			$('.c-table-table tbody').html(htmlStr);
		}
	</script>
</body>

</html>