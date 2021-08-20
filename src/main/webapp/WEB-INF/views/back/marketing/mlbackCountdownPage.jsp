<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Countdown Time</title>
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
						<span class="c-option-title">CountDown-time</span>
						<button class="btn btn-primary btn-create">Create CountDown-time</button>
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
											<th>title</th>
											<th>description</th>
											<th>startTime</th>
											<th>endTime</th>
											<th>status</th>
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
						<span class="c-option-title">Edit CountDown-time</span>
						<div class="group">
							<button class="btn btn-secondary btn-cancel">Cancel</button>
							<button class="btn btn-primary btn-save">Save CountDown-time</button>
						</div>
					</div>
					<div class="c-form row">
						<input id="countdownId" hidden>
						<!-- left panel  -->
						<div class="left-panel col-lg-12 col-md-12">
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">General</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="countdownTitle">title</label>
										<div class="controls">
											<input class="form-control" id="countdownTitle" type="text" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-md-3 col-form-label" for="countdownStatus">Status</label>
										<div class="controls col-md-3">
											<label class="c-switch c-switch-primary">
												<input class="c-switch-input" id="countdownStatus" type="checkbox">
												<span class="c-switch-slider"></span>
											</label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="countdownStarttime">startTime</label>
										<div class="controls">
											<input class="form-control datetimepicker" id="countdownStarttime" type="text" placeholder="@exmaple 2020-01-01 00:00:59" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="countdownEndtime">endTime</label>
										<div class="controls">
											<input class="form-control datetimepicker" id="countdownEndtime" type="text" placeholder="@exmaple 2020-01-01 00:00:59" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="countdownDescription">Description</label>
										<div class="controls">
											<input class="form-control" id="countdownDescription" type="text" />
										</div>
									</div>
								</div>
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
		var isCreate = false;

		// init
		bindDateTimepicker();
		getCountdownTimeData()
		// create collection
		$('.btn-create').on('click', function () {
			$('.c-create .c-option-title').text('Create CountDown-time');

			getCountdownId(function(data) {
				// init formData
				resetFormData();
				$('#countdownId').val(data.countdownId);
				showCreateBlock();
				isCreate = true;
			});
		});
		// edit collection
		$(document.body).on('click', '.btn-edit', function (e) {
			var countdownId = $(this).data('id');
			getOneCountdownTimeData({
				countdownId: countdownId
			}, function(resData) {
			 	$('.c-create .c-option-title').text('Edit CountDown-time');
				showCreateBlock();
				initFormData(resData);
			});			
		});
		// delete collection
		$(document.body).on('click', '.btn-delete', function (e) {
			var countdownId = parseInt($(this).data('id'));
			$('#deleteModal').find('.modal-title').html('Delete CountDown-time!');
			$('#deleteModal').modal('show');
			$('#deleteModal .btn-ok').one('click', function () {
				deleteCountdownTimeData({
					countdownId: countdownId,
				}, function() {
					getCountdownTimeData();
				});
			});
		});
		$(window).on('beforeunload', function() {
			var countdownId = $('#countdownId').val();
			isCreate && countdownId && deleteCountdownTimeData({
				countdownId: countdownId,
			});
		});
		// save collection
		$('.c-create .btn-save').on('click', function () {
			saveCountdownTimeData(getFormData(), function() {
				// redirect tab-active & then search-data
				if (isCreate) isCreate = false;

				getCountdownTimeData();
				showInitBlock();
				$('#countdownId').val('');
			});
		});
		// cancel collection save
		$('.c-create .btn-cancel').on('click', function () {
			if (isCreate) {
				isCreate = false;
				// delete null countdown-time
				deleteCountdownTimeData({
					countdownId: $('#countdownId').val(),
				}, function() {
					console.log("cancel create CountDown-time");
				});
			}

			showInitBlock();
		});
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
			$('#countdownId').val('');
			$('#countdownTitle').val('');
			$('#countdownDescription').val('');
			$('#countdownStarttime').val('');
			$('#countdownEndtime').val('');

			$('#countdownStatus').prop('checked', false);
		}
		// getFormdData
		function getFormData() {
			var data = {};
			data.countdownId = parseInt($('#countdownId').val());
			data.countdownTitle = $('#countdownTitle').val();
			data.countdownDescription = $('#countdownDescription').val();
			data.countdownStarttime = $('#countdownStarttime').val();
			data.countdownEndtime = $('#countdownEndtime').val();

			data.countdownStatus = $('#countdownStatus').prop('checked') ? 1 : 0;

			return data;
		}
		// initFormData
		function initFormData(data) {
			// init
			$('#countdownId').val(data.countdownId);
			$('#countdownTitle').val(data.countdownTitle);
			$('#countdownDescription').val(data.countdownDescription);
			$('#countdownStarttime').val(data.countdownStarttime);
			$('#countdownEndtime').val(data.countdownEndtime);

			$('#countdownStatus').prop('checked', data.countdownStatus);
		}
		// callback get id
		function getCountdownId(callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/MlbackCountdown/initializaSlide",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				success: function (data) {
					if (data.code == 100) {
						callback && callback(data.extend.mlbackCountdown);
						toastr.success(data.extend.resMsg);
					} else {
						showInitBlock();
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
		// callback get all data
		function getCountdownTimeData() {
			$('.c-mask').show();

			var formData = new FormData();
			formData.append('pn', getPageNum());

			$.ajax({
				url: "${APP_PATH}/MlbackCountdown/getMlbackCountdownByPage",
				type: "post",
				processData: false,
				contentType: false,
				cache: false,
				data: formData,
				success: function (data) {
					if (data.code == 100) {
						renderTable(data.extend.pageInfo.list);
						renderTablePagination(data.extend.pageInfo);
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function () {
					toastr.error('Failed to get CountDown-time, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback get one data
		function getOneCountdownTimeData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackCountdown/getOneMlbackCountdownDetail",
				type: "post",
				data: JSON.stringify(reqData),
				dataType: 'json',
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend.mlbackCountdownOne);
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function () {
					toastr.error('Failed to get CountDown-time, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback save
		function saveCountdownTimeData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackCountdown/save",
				type: "post",
				cache: false,
				dataType: "json",
				contentType: 'application/json',
				data: JSON.stringify(reqData),
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
		function deleteCountdownTimeData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackCountdown/delete",
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
				htmlStr += '<tr><td>' + data[i].countdownId + '</td>' +
					'<td>' + data[i].countdownTitle + '</td>' +
					'<td>' + data[i].countdownDescription + '</td>' +
					'<td>' + data[i].countdownStarttime + '</td>' +
					'<td>' + data[i].countdownEndtime + '</td>' +
					'<td><a class="badge '+ (data[i].countdownStatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].countdownStatus ? 'enable' : 'disable') + '</a></td>' +
					'<td>' +
						'<button class="btn btn-primary btn-edit" data-id="' + data[i].countdownId + '">' +
							'<svg class="c-icon">' +
								'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-pencil"></use>' +
							'</svg>' +
						'</button>' +
						'<button class="btn btn-danger btn-delete" data-id="' + data[i].countdownId + '" disabled>' +
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