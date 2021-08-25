<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Email Template</title>
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
						<span class="c-option-title">Email Template</span>
						<button class="btn btn-primary btn-create">Create Email-template</button>
					</div>
					<div class="c-table">
						<div class="c-table-content">
							<div class="c-table-table table-responsive-sm">
								<table class="table">
									<thead>
										<tr>
											<th>id</th>
											<th>name</th>
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
						<span class="c-option-title">Edit Email-template</span>
						<div class="group">
							<button class="btn btn-secondary btn-cancel">Cancel</button>
							<button class="btn btn-primary btn-save">Save Email-template</button>
						</div>
					</div>
					<div class="c-form row">
						<input id="htmlemailId" hidden>
						<!-- left panel  -->
						<div class="left-panel col-md-12">
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">General</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="htmlemailName">Name</label>
										<div class="controls">
											<input class="form-control" id="htmlemailName" type="text" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-md-3 col-form-label" for="htmlemailStatus">Status</label>
										<div class="controls col-md-3">
											<label class="c-switch c-switch-primary">
												<input class="c-switch-input" id="htmlemailStatus" type="checkbox">
												<span class="c-switch-slider"></span>
											</label>
										</div>
									</div>
								</div>
							</div>
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Template Content</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="htmlemailTitle">Logo</label>
										<div class="controls">
											<img src="/static/custom/dblogo.png" width="200" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="htmlemailTitle">title</label>
										<div class="controls">
											<input class="form-control" id="htmlemailTitle" type="text" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="htmlemailRetrieve">Greetings Info</label>
										<div class="controls">
											<textarea class="form-control" rows="5" id="htmlemailRetrieve"></textarea>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="htmlemailRetrievecode">Coupon Infos</label>
										<div class="controls">
											<textarea class="form-control" rows="5" id="htmlemailRetrievecode"></textarea>
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

	<script src="${APP_PATH}/static/back/lib/tagsinput/bootstrap-tagsinput.min.js"></script>
	<script src="${APP_PATH}/static/back/lib/summernote/summernote.min.js"></script>
	<!-- custom script -->
	<script>
		var isCreate = false;

		// init
		getEmailtemplatesData();
		// pagination a-click
		$(document.body).on('click', '#table-pagination li', function (e) {
			getEmailtemplatesData();
		});
		// create email-template
		$('.btn-create').on('click', function () {
			$('.c-create .c-option-title').text('Create Email-template');
			showCreateBlock();
			// init formData
			resetFormData();
			getEmailtemplateId();
			isCreate = true;
		});
		// edit email-template
		$(document.body).on('click', '.btn-edit', function (e) {
			var htmlemailId = $(this).data('id');
			getOneEmailtemplateData({
				htmlemailId: htmlemailId
			}, function(resData) {
			 	$('.c-create .c-option-title').text('Edit Email-template');
				showCreateBlock();
				initFormData(resData);
			});			
		});
		// delete email-template
		$(document.body).on('click', '.btn-delete', function (e) {
			var htmlemailId = parseInt($(this).data('id'));
			$('#deleteModal').find('.modal-title').html('Delete Email-template!');
			$('#deleteModal').modal('show');
			$('#deleteModal .btn-ok').one('click', function () {
				deleteEmailtemplateData({
					htmlemailId: htmlemailId,
				}, function() {
					getEmailtemplatesData();
				});
			});
		});
		$(window).on('beforeunload', function() {
			var htmlemailId = $('#htmlemailId').val();
			isCreate && htmlemailId && deleteEmailtemplateData({
				htmlemailId: htmlemailId,
			});
		});
		// save email-template
		$('.c-create .btn-save').on('click', function () {
			saveEmailtemplateData(getFormData(), function() {
				// redirect tab-active & then search-data
				getEmailtemplatesData();
				showInitBlock();
				$('#htmlemailId').val('');
			});
		});
		// cancel email-template save
		$('.c-create .btn-cancel').on('click', function () {
			if (isCreate) {
				isCreate = false;
				deleteEmailtemplateData({
					htmlemailId: $('#htmlemailId').val(),
				}, function() {
					console.log("cancel create-email-template");
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
			$('#htmlemailId').val('');
			$('#htmlemailName').val('');
			$('#htmlemailStatus').prop('checked', false);
			$('#htmlemailTitle').val('');
			$('#htmlemailRetrieve').val('');
			$('#htmlemailRetrievecode').val('');
		}
		// getFormdData
		function getFormData() {
			var data = {};
			data.htmlemailId = parseInt($('#htmlemailId').val());
			data.htmlemailName = $('#htmlemailName').val();
			data.htmlemailStatus = $('#htmlemailStatus').prop('checked') ? 1 : 0;
			data.htmlemailHeadimgurl = 'https://megalook.com/static/custom/dblogo.png';
			data.htmlemailTitle = $('#htmlemailTitle').val();
			data.htmlemailRetrieve = $('#htmlemailRetrieve').val();
			data.htmlemailRetrievecode = $('#htmlemailRetrievecode').val();

			return data;
		}
		// initFormData
		function initFormData(data) {
			$('#htmlemailId').val(data.htmlemailId);
			$('#htmlemailName').val(data.htmlemailName);
			$('#htmlemailStatus').prop('checked', data.htmlemailStatus);
	
			$('#htmlemailTitle').val(data.htmlemailTitle);
			$('#htmlemailRetrieve').val(data.htmlemailRetrieve);
			$('#htmlemailRetrievecode').val(data.htmlemailRetrievecode);
		}
		// callback get id
		function getEmailtemplateId() {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/MlbackHtmlEmail/initializaHtmlEmail",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				success: function (data) {
					if (data.code == 100) {
						var htmlemailId = data.extend&& data.extend.mlbackHtmlEmail && data.extend.mlbackHtmlEmail.htmlemailId;
						if (htmlemailId) {
							$('#htmlemailId').val(data.extend.mlbackHtmlEmail.htmlemailId);
							toastr.success(data.extend.resMsg);
						} else {
							toastr.error('create email-template fail! Please try again.');
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
		function getEmailtemplatesData() {
			$('.c-mask').show();

			var formData = new FormData();
			formData.append('pn', getPageNum());

			$.ajax({
				url: "${APP_PATH }/MlbackHtmlEmail/getMlbackHtmlEmailByPage",
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
					toastr.error('Failed to get email-template, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback get one data
		function getOneEmailtemplateData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/MlbackHtmlEmail/getOneMlbackHtmlEmailOneAllDetail",
				type: "post",
				data: JSON.stringify(reqData),
				dataType: 'json',
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend.mlbackHtmlEmailOne);
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function () {
					toastr.error('Failed to get email-template, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback save
		function saveEmailtemplateData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackHtmlEmail/save",
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
		function deleteEmailtemplateData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackHtmlEmail/delete",
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
				htmlStr += '<tr><td>' + data[i].htmlemailId + '</td>' +
					'<td>' + data[i].htmlemailName + '</td>' +
					'<td><a class="badge '+ (data[i].htmlemailStatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].htmlemailStatus ? 'enable' : 'disable') + '</a></td>' +
					'<td>' +
						'<button class="btn btn-primary btn-edit" data-id="' + data[i].htmlemailId + '">' +
							'<svg class="c-icon">' +
								'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-pencil"></use>' +
							'</svg>' +
						'</button>' +
						'<button class="btn btn-danger btn-delete" data-id="' + data[i].htmlemailId + '">' +
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