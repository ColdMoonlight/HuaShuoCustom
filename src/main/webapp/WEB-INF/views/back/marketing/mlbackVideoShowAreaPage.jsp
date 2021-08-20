<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Video Area</title>
	<jsp:include page="../common/backheader.jsp" flush="true"></jsp:include>
	<link href="${APP_PATH}/static/back/lib/summernote/summernote.min.css" rel="stylesheet">
	<link href="${APP_PATH}/static/back/lib/tagsinput/bootstrap-tagsinput.css" rel="stylesheet">
</head>

<body class="c-app">
	<jsp:include page="../layout/backheader.jsp" flush="true"></jsp:include>
	<jsp:include page="../layout/backsidebar.jsp" flush="true"></jsp:include>
	<div class="c-wrapper">
		<div class="c-body">
			<div class="c-main">
				<div class="c-init">
					<div class="c-option">
						<span class="c-option-title">Video Area</span>
						<button class="btn btn-primary btn-create">Create Video Area</button>
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
											<th>area</th>
											<th>order</th>
											<th>wap-image</th>
											<th>wap-status</th>
											<th>pc-image</th>
											<th>pc-status</th>
											<th>create-time</th>
											<th>update-time</th>
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
						<span class="c-option-title">Edit Video Area</span>
						<div class="group">
							<button class="btn btn-secondary btn-cancel">Cancel</button>
							<button class="btn btn-primary btn-save">Save Video Area</button>
						</div>
					</div>
					<div class="c-form row">
						<input id="videoshowareaId" hidden>
						<!-- left panel  -->
						<div class="left-panel col-lg-12 col-md-12">
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">General</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="videoshowareaName">Video Area Name</label>
										<div class="controls">
											<input class="form-control" id="videoshowareaName" type="text" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="videoshowareaAreanum">Video Area</label>
										<div class="controls">
											<select class="form-control" id="videoshowareaAreanum" />
												<option value="-1">Please select the location of the Video Area</option>
												<option value="1">1-首页活动</option>
					  							<option value="2">2-Video页面</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="videoshowareaOrderthNum">Order</label>
										<div class="controls">
											<select class="form-control" id="videoshowareaOrderthNum" />
												<option value="-1">Please select the Video order</option>
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
												<option value="6">6</option>
												<option value="7">7</option>
												<option value="8">8</option>
												<option value="9">9</option>
												<option value="10">10</option>
												<option value="11">11</option>
					  							<option value="12">12</option>
											</select>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-md-3 col-form-label" for="videoshowareaWapstatus">Wap Status</label>
										<div class="controls col-md-3">
											<label class="c-switch c-switch-primary">
												<input class="c-switch-input" id="videoshowareaWapstatus" type="checkbox">
												<span class="c-switch-slider"></span>
											</label>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-md-3 col-form-label" for="videoshowareaPcstatus">Pc Status</label>
										<div class="controls col-md-3">
											<label class="c-switch c-switch-primary">
												<input class="c-switch-input" id="videoshowareaPcstatus" type="checkbox">
												<span class="c-switch-slider"></span>
											</label>
										</div>
									</div>
								</div>
							</div>
							<!-- product media -->
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Video Area Image</div>
								</div>
								<div class="card-body">
									<div class="row">
										<div class="col-lg-6 col-md-6">
											<h3>Wap Image</h3>
											<div class="c-upload-img">
												<svg class="c-icon">
													<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image-plus"></use>
												</svg>
												<div class="c-backshow"></div>						
												<input id="videoshowareaWapimgurl" type="file" accept="image/png, image/jpeg, image/gif" />										
												<!-- spinner -->
												<div class="spinner">
													<div class="spinner-border" role="status" aria-hidden="true"></div>
												</div>
											</div>
										</div>
										<div class="col-lg-6 col-md-6">
											<h3>Pc Image</h3>
											<div class="c-upload-img">
												<svg class="c-icon">
													<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image-plus"></use>
												</svg>
												<div class="c-backshow"></div>						
												<input id="videoshowareaPcimgurl" type="file" accept="image/png, image/jpeg, image/gif" />										
												<!-- spinner -->
												<div class="spinner">
													<div class="spinner-border" role="status" aria-hidden="true"></div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- right panel  -->
						<!-- <div class="right-panel col-lg-5 col-md-12">
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Super Category</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="slideCateid">Super Category</label>
										<div class="controls">
											<select class="form-control supercate-list" id="slideCateid" /></select>
										</div>
									</div>
								</div>
							</div>
						</div> -->
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
		getVideoAreasData()
		// create collection
		$('.btn-create').on('click', function () {
			$('.c-create .c-option-title').text('Create Video Area');

			getVideoAreaId(function(data) {
				// init formData
				resetFormData();
				$('#videoshowareaId').val(data.videoshowareaId);
				showCreateBlock();
				isCreate = true;
			});
		});
		// edit collection
		$(document.body).on('click', '.btn-edit', function (e) {
			var videoshowareaId = $(this).data('id');
			getOneVideoAreaData({
				videoshowareaId: videoshowareaId
			}, function(resData) {
			 	$('.c-create .c-option-title').text('Edit VideoArea');
				showCreateBlock();
				initFormData(resData);
			});			
		});
		// delete collection
		$(document.body).on('click', '.btn-delete', function (e) {
			var videoshowareaId = parseInt($(this).data('id'));
			$('#deleteModal').find('.modal-title').html('Delete Video Area!');
			$('#deleteModal').modal('show');
			$('#deleteModal .btn-ok').one('click', function () {
				deleteVideoAreaData({
					videoshowareaId: videoshowareaId,
				}, function() {
					getVideoAreasData();
				});
			});
		});
		$(window).on('beforeunload', function() {
			var videoshowareaId = $('#videoshowareaId').val();
			isCreate && videoshowareaId && deleteVideoAreaData({
				videoshowareaId: videoshowareaId,
			});
		});
		// upload picture
		$('#videoshowareaPcimgurl').on('change', function(e) {
			var $this = $(this);
			var file = $this[0].files[0];
			var formData = new FormData();

			if (!file) return false;

			$this.parent().find('.spinner').show();
			$this.val('');

			formData.append('type', 'VideoShowAreaPc');
			formData.append('image', file);
			formData.append('videoshowareaId', parseInt($('#videoshowareaId').val()));

			$.ajax({
				url: "${APP_PATH}/ImageYTBUpload/uploadVideoShowAreaPcImg",
				type: "post",
				data: formData,
				processData: false,
				contentType: false,
				cache: false,
				dataType: 'json',
				success: function (data) {
					if (data.code == 100) {
						addPicture($this, {
							imageUrl: data.extend.sqlimageUrl,
							thumImageUrl: data.extend.sqlthumImageUrl
						});
					} else {
						toastr.error('网络错误， 请稍后重试！');	
					}
				},
				error: function (err) {
					toastr.error(err);
				},
				complete: function () {
					$this.parent().find('.spinner').hide();
				}
			});
		});
		$('#videoshowareaWapimgurl').on('change', function(e) {
			var $this = $(this);
			var file = $this[0].files[0];
			var formData = new FormData();

			if (!file) return false;

			$this.parent().find('.spinner').show();
			$this.val('');

			formData.append('type', 'VideoShowAreaWap');
			formData.append('image', file);
			formData.append('videoshowareaId', parseInt($('#videoshowareaId').val()));

			$.ajax({
				url: "${APP_PATH}/ImageYTBUpload/uploadVideoShowAreaWapImg",
				type: "post",
				data: formData,
				processData: false,
				contentType: false,
				cache: false,
				dataType: 'json',
				success: function (data) {
					if (data.code == 100) {
						addPicture($this, {
							imageUrl: data.extend.sqlimageUrl,
							thumImageUrl: data.extend.sqlthumImageUrl
						});
					} else {
						toastr.error('网络错误， 请稍后重试！');	
					}
				},
				error: function (err) {
					toastr.error(err);
				},
				complete: function () {
					 $this.parent().find('.spinner').hide();
				}
			});
		});
		function addPicture(el, data) {
			var parentEl = el.parent();
			el.attr('data-val', JSON.stringify(data));
			parentEl.addClass('active');
			parentEl.find('.c-backshow').html('<img src="'+ encodeUrl(data.imageUrl) + '" />');
		}
		function resetPicture(el) {
			var parentEl = el.parent();
			el.attr('data-val', '');
			parentEl.removeClass('active');
			parentEl.find('.c-backshow').html('');
		}
		// save collection
		$('.c-create .btn-save').on('click', function () {
			saveVideoAreaData(getFormData(), function() {
				// redirect tab-active & then search-data
				if (isCreate) isCreate = false;

				getVideoAreasData();
				showInitBlock();
				$('#videoshowareaId').val('');
			});
		});
		// cancel collection save
		$('.c-create .btn-cancel').on('click', function () {
			if (isCreate) {
				isCreate = false;
				// delete null VideoArea
				deleteVideoAreaData({
					videoshowareaId: $('#videoshowareaId').val(),
				}, function() {
					console.log("cancel create VideoArea");
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
			$('#videoshowareaId').val('');
			$('#videoshowareaName').val('');
			$('#videoshowareaAreanum').val('-1');
			$('#videoshowareaOrderthNum').val('-1');
			$('#videoshowareaWapstatus').prop('checked', false);
			$('#videoshowareaPcstatus').prop('checked', false);

			resetPicture($('#videoshowareaWapimgurl'));
			resetPicture($('#videoshowareaPcimgurl'));
		}
		// getFormdData
		function getFormData() {
			var data = {};
			data.videoshowareaId = parseInt($('#videoshowareaId').val());
			data.videoshowareaName = $('#videoshowareaName').val();
			data.videoshowareaAreanum = $('#videoshowareaAreanum').val();
			data.videoshowareaOrderthNum = $('#videoshowareaOrderthNum').val();
			data.videoshowareaWapstatus = $('#videoshowareaWapstatus').prop('checked') ? 1 : 0;
			data.videoshowareaPcstatus = $('#videoshowareaPcstatus').prop('checked') ? 1 : 0;

			data.videoshowareaWapimgurl = $('#videoshowareaWapimgurl').attr('data-val') && JSON.parse($('#videoshowareaWapimgurl').attr('data-val')).imageUrl;
			data.videoshowareaPcimgurl = $('#videoshowareaPcimgurl').attr('data-val') && JSON.parse($('#videoshowareaPcimgurl').attr('data-val')).imageUrl;

			return data;
		}
		// initFormData
		function initFormData(data) {
			// init
			$('#videoshowareaId').val(data.videoshowareaId);
			$('#videoshowareaName').val(data.videoshowareaName);
			$('#videoshowareaAreanum').val(data.videoshowareaAreanum ? data.videoshowareaAreanum : '-1');
			$('#videoshowareaOrderthNum').val(data.videoshowareaOrderthNum ? data.videoshowareaOrderthNum : '-1');
			$('#videoshowareaWapstatus').prop('checked', data.videoshowareaWapstatus);
			$('#videoshowareaPcstatus').prop('checked', data.videoshowareaPcstatus);

			if (data.videoshowareaWapimgurl) {
				addPicture($('#videoshowareaWapimgurl'), {
					imageUrl: data.videoshowareaWapimgurl
				});	
			} else {
				resetPicture($('#videoshowareaWapimgurl'));
			}

			if (data.videoshowareaPcimgurl) {
				addPicture($('#videoshowareaPcimgurl'), {
					imageUrl: data.videoshowareaPcimgurl
				});				
			} else {
				resetPicture($('#videoshowareaPcimgurl'));
			}

		}
		// callback get id
		function getVideoAreaId(callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/MlbackVideoShowArea/initializaVideoShowArea",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				success: function (data) {
					if (data.code == 100) {
						callback && callback(data.extend.mlbackVideoShowArea)
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
		function getVideoAreasData() {
			$('.c-mask').show();

			var formData = new FormData();
			formData.append('pn', getPageNum());

			$.ajax({
				url: "${APP_PATH}/MlbackVideoShowArea/getMlbackVideoShowAreaByPage",
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
					toastr.error('Failed to get VideoArea, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback get one data
		function getOneVideoAreaData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackVideoShowArea/getOneMlbackVideoShowAreaDetail",
				type: "post",
				data: JSON.stringify(reqData),
				dataType: 'json',
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend.mlbackVideoShowAreaOne);
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function () {
					toastr.error('Failed to get VideoArea, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback save
		function saveVideoAreaData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackVideoShowArea/save",
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
		function deleteVideoAreaData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackVideoShowArea/delete",
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
				htmlStr += '<tr><td>' + data[i].videoshowareaId + '</td>' +
					'<td>' + data[i].videoshowareaName + '</td>' +
					'<td>' + data[i].videoshowareaAreanum + '</td>' +
					'<td>' + (data[i].videoshowareaOrderthNum || '-1') + '</td>' +
					'<td>' + (data[i].videoshowareaWapimgurl ?
							'<div class="c-table-img"><img src="'+ encodeUrl(data[i].videoshowareaWapimgurl) +'" /></div>'
							: '<div class="c-table-icon"><svg class="c-icon"><use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image1"></use></svg></div>') +
					'</td>' +
					'<td><a class="badge '+ (data[i].videoshowareaWapstatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].videoshowareaWapstatus ? 'enable' : 'disable') + '</a></td>' +
					'<td>' + (data[i].videoshowareaPcimgurl ?
							'<div class="c-table-img"><img src="'+ encodeUrl(data[i].videoshowareaPcimgurl) +'" /></div>'
							: '<div class="c-table-icon"><svg class="c-icon"><use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image1"></use></svg></div>') +
					'</td>' +
					'<td><a class="badge '+ (data[i].videoshowareaPcstatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].videoshowareaPcstatus ? 'enable' : 'disable') + '</a></td>' +
					'<td>' + data[i].videoshowareaCreatetime + '</td>' +
					'<td>' + data[i].videoshowareaMotifytime + '</td>' +
					'<td>' +
						'<button class="btn btn-primary btn-edit" data-id="' + data[i].videoshowareaId + '">' +
							'<svg class="c-icon">' +
								'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-pencil"></use>' +
							'</svg>' +
						'</button>' +
						'<button class="btn btn-danger btn-delete" data-id="' + data[i].videoshowareaId + '">' +
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