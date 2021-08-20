<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Email Template</title>
	<jsp:include page="../common/backheader.jsp" flush="true"></jsp:include>
	<style>
		.email-notification {
			margin-top: 1rem;
		}
		.email-notification-item {
			display: flex;
			padding: .5rem;
		}
		.email-notification-item+.email-notification-item {
			border-top: 1px solid #ccc;
		}
		.email-notification-name {
			width: 180px;
			color: #006fbb;
			font-weight: 500;
			font-size: .875rem;
			cursor: pointer;
		}
		.email-notification-info {
			margin-left: 1rem;
			font-size: .75rem;
			color: #333;
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
						<span class="c-option-title">Email Notifications</span>
						<button class="btn btn-primary btn-create">Create Email-Notification</button>
					</div>
					<div class="c-table">
						<div class="c-table-content">
							<div class="c-table-table table-responsive-sm">
								<table class="table">
									<thead>
										<tr>
											<th>id</th>
											<th>Email Subject</th>
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
						<span class="c-option-title">Edit Email-Notification</span>
						<div class="group">
							<button class="btn btn-secondary btn-cancel">Cancel</button>
							<button class="btn btn-primary btn-save">Save Email-Notification</button>
						</div>
					</div>
					<div class="c-form row">
						<input id="emailrichtextId" hidden>
						<!-- left panel  -->
						<div class="left-panel col-lg-8 col-md-12">
							<div class="card">
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="emailrichtextTitle">Email Subject</label>
										<div class="controls">
											<input class="form-control" id="emailrichtextTitle" type="text" />
										</div>
									</div>
									<div class="row">
										<div class="form-group col-lg-6 col-md-12">
											<label class="col-form-label" for="emailrichtextSeoname">Email SEO</label>
											<div class="controls">
												<input class="form-control" id="emailrichtextSeoname" type="text" />
											</div>
										</div>
										<div class="form-group col-lg-6 col-md-12">
											<label class="col-form-label" for="emailrichtextSendfrom">Email</label>
											<div class="controls">
												<input class="form-control" id="emailrichtextSendfrom" type="text" />
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="emailrichtextTemplate">Email Body</label>
										<div class="controls">
											<textarea class="form-control" rows="36" id="emailrichtextTemplate"></textarea>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- right panel  -->
						<div class="right-panel col-lg-4 col-md-12">
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">key & value</div>
								</div>
								<div class="card-body">
									<button class="btn btn-primary" onclick="notifyKvAdd()">add</button>
									<div class="rich-value-box">
										<h3>key-values</h3>
										<table class="table">
											<thead>
												<tr>
													<th>key</th>
													<th>value</th>
													<th></th>
												</tr>
											</thead>
											<tbody id="notify-kv-list"></tbody>
										</table>
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
	<!-- custom script -->
	<script>
		var isCreate = false;

		// init
		getEmailNotificationsData();
		// pagination a-click
		$(document.body).on('click', '#table-pagination li', function (e) {
			getEmailNotificationsData();
		});
		// create Email-Notification
		$('.btn-create').on('click', function () {
			$('.c-create .c-option-title').text('Create Email-Notification');
			showCreateBlock();
			// init formData
			resetFormData();
			getEmailNotificationId();
			isCreate = true;
		});
		// edit Email-Notification
		$(document.body).on('click', '.btn-edit', function (e) {
			var emailrichtextId = $(this).data('id');
			getOneEmailNotificationData({
				emailrichtextId: emailrichtextId
			}, function(resData) {
			 	$('.c-create .c-option-title').text('Edit Email-Notification');
				showCreateBlock();
				initFormData(resData);
			});			
		});
		// delete Email-Notification
		$(document.body).on('click', '.btn-delete', function (e) {
			var emailrichtextId = parseInt($(this).data('id'));
			$('#deleteModal').find('.modal-title').html('Delete Email-Notification!');
			$('#deleteModal').modal('show');
			$('#deleteModal .btn-ok').one('click', function () {
				deleteEmailNotificationData({
					emailrichtextId: emailrichtextId,
				}, function() {
					getEmailNotificationsData();
				});
			});
		});
		$(window).on('beforeunload', function() {
			var emailrichtextId = $('#emailrichtextId').val();
			isCreate && emailrichtextId && deleteEmailNotificationData({
				emailrichtextId: emailrichtextId,
			});
		});
		// save Email-Notification
		$('.c-create .btn-save').on('click', function () {
			var reqData = getFormData();
			if (!reqData.emailrichtextKeynamestr.replace(/,/g, '') && !reqData.emailrichtextValuenamestr.replace(/,/g, '')) {
				toastr.error('Email-Notification key-value ')
				return false;
			}
			saveEmailNotificationData(reqData, function() {
				// redirect tab-active & then search-data
				getEmailNotificationsData();
				showInitBlock();
				$('#emailrichtextId').val('');
			});
		});
		// cancel Email-Notification save
		$('.c-create .btn-cancel').on('click', function () {
			if (isCreate) {
				isCreate = false;
				deleteEmailNotificationData({
					emailrichtextId: $('#emailrichtextId').val(),
				}, function() {
					console.log("keys & values invalid!!!");
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
		// notify kv list
		function renderNotifyKvData(keys, values) {
			var keyArr = keys && keys.split(',') || [];
			var valueArr = values && values.split(',') || [];
			var kvHtml = '';
			keyArr.forEach(function(item, idx) {
				kvHtml += '<tr class="notify-kv-item">' +
						'<td><input class="form-control key" type="text" value="'+ (item || '') +'"></td>' +
						'<td><input class="form-control value" type="text" value="'+ (valueArr[idx] || '') +'"></td>' +
						'<td><button class="btn btn-danger" onclick="notifyKvDelete(event)">delete</button></td>' +
					'</tr>';
			});
			$('#notify-kv-list').html(kvHtml);
		}
		function clearNotifyData() {
			$('#notify-kv-list').html('');
		}
		function getNotifyKvData() {
			var keyArr = [], valueArr = [];
			$('.notify-kv-item').each(function(idx, item) {
				keyArr.push($(item).find('.key').val());
				valueArr.push($(item).find('.value').val());
			});
			return {
				key: keyArr.join(','),
				value: valueArr.join(',')
			};
		}
		function notifyKvDelete(e) {
			$(e.target).parents('.notify-kv-item').remove();
		}
		function notifyKvAdd() {
			var kvItemHtml = '<tr class="notify-kv-item">' +
				'<td><input class="form-control key" type="text"></td>' +
				'<td><input class="form-control value" type="text"></td>' +
				'<td><button class="btn btn-danger" onclick="notifyKvDelete(event)">delete</button></td>' +
			'</tr>';
			$('#notify-kv-list').append($(kvItemHtml));
		}
		// reset data
		function resetFormData() {
			$('#emailrichtextId').val('');
			$('#emailrichtextSendfrom').val('');
			$('#emailrichtextSeoname').val('');
			$('#emailrichtextTitle').val('');
			$('#emailrichtextTemplate').val('');

			clearNotifyData();
		}
		// getFormdData
		function getFormData() {
			var data = {};
			data.emailrichtextId = parseInt($('#emailrichtextId').val());
			data.emailrichtextSendfrom = $('#emailrichtextSendfrom').val();
			data.emailrichtextSeoname = $('#emailrichtextSeoname').val();
			data.emailrichtextTitle = $('#emailrichtextTitle').val();
			data.emailrichtextTemplate = $('#emailrichtextTemplate').val();

			var notifyKvData = getNotifyKvData();
			data.emailrichtextKeynamestr = notifyKvData && notifyKvData.key;
			data.emailrichtextValuenamestr = notifyKvData && notifyKvData.value;

			return data;
		}
		// initFormData
		function initFormData(data) {
			$('#emailrichtextId').val(data.emailrichtextId);
			$('#emailrichtextSendfrom').val(data.emailrichtextSendfrom);
			$('#emailrichtextSeoname').val(data.emailrichtextSeoname);
			$('#emailrichtextTitle').val(data.emailrichtextTitle);
			$('#emailrichtextTemplate').val(data.emailrichtextTemplate);

			renderNotifyKvData(data.emailrichtextKeynamestr, data.emailrichtextValuenamestr);
		}
		// callback get id
		function getEmailNotificationId() {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/MlbackEmailRichText/initializaEmailRichText",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				success: function (data) {
					if (data.code == 100) {
						var emailrichtextId = data.extend&& data.extend.MlbackEmailRichText && data.extend.MlbackEmailRichText.emailrichtextId;
						if (emailrichtextId) {
							$('#emailrichtextId').val(data.extend.MlbackEmailRichText.emailrichtextId);
							toastr.success(data.extend.resMsg);
						} else {
							toastr.error('create Email-Notification fail! Please try again.');
						}
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
		function getEmailNotificationsData() {
			$('.c-mask').show();

			var formData = new FormData();
			formData.append('pn', getPageNum());

			$.ajax({
				url: "${APP_PATH }/MlbackEmailRichText/getMlbackEmailRichTextByPage",
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
					toastr.error('Failed to get Email-Notification, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback get one data
		function getOneEmailNotificationData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/MlbackEmailRichText/getOneMlbackEmailRichTextOneAllDetail",
				type: "post",
				data: JSON.stringify(reqData),
				dataType: 'json',
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend.MlbackEmailRichTextOne);
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function () {
					toastr.error('Failed to get Email-Notification, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback save
		function saveEmailNotificationData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackEmailRichText/save",
				type: "post",
				cache: false,
				dataType: "json",
				contentType: 'application/json',
				data: JSON.stringify(reqData),
				success: function (data) {
					if (data.code == 100) {
						toastr.success(data.extend.resMsg);
						callback && callback();
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
		function deleteEmailNotificationData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackEmailRichText/delete",
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
				htmlStr += '<tr><td>' + data[i].emailrichtextId + '</td>' +
					'<td>' + data[i].emailrichtextTitle + '</td>' +
					'<td>' +
						'<button class="btn btn-primary btn-edit" data-id="' + data[i].emailrichtextId + '">' +
							'<svg class="c-icon">' +
								'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-pencil"></use>' +
							'</svg>' +
						'</button>' +
						'<button class="btn btn-danger btn-delete" data-id="' + data[i].emailrichtextId + '">' +
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