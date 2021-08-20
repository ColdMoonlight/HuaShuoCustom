<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Display Area</title>
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
						<span class="c-option-title">Display Area</span>
						<button class="btn btn-primary btn-create">Create Display Area</button>
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
											<th>wap-image</th>
											<th>pc-image</th>
											<th>status</th>
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
						<span class="c-option-title">Edit Display Area</span>
						<div class="group">
							<button class="btn btn-secondary btn-cancel">Cancel</button>
							<button class="btn btn-primary btn-save">Save Display Area</button>
						</div>
					</div>
					<div class="c-form row">
						<input id="showareaId" hidden>
						<!-- left panel  -->
						<div class="left-panel col-lg-12 col-md-12">
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">General</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="showareaName">Display Area Name</label>
										<div class="controls">
											<input class="form-control" id="showareaName" type="text" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="showareaNumth">Display Area</label>
										<div class="controls">
											<select class="form-control" id="showareaNumth" />
												<option value="-1">Please select the location of the Display Area</option>
												<option value="1">first display-area</option>
												<option value="2">second display-area</option>
												<option value="3">three display-area</option>
												<option value="4">four display-area</option>
												<option value="5">five display-area</option>
											</select>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-md-3 col-form-label" for="showareaStatus">Status</label>
										<div class="controls col-md-3">
											<label class="c-switch c-switch-primary">
												<input class="c-switch-input" id="showareaStatus" type="checkbox">
												<span class="c-switch-slider"></span>
											</label>
										</div>
									</div>
								</div>
							</div>
							<!-- product media -->
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Display Area Image</div>
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
												<input id="showareaImgurl" type="file" accept="image/png, image/jpeg, image/gif" />										
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
												<input id="showareaImgpcurl" type="file" accept="image/png, image/jpeg, image/gif" />										
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
	<!-- custom script -->
	<script>
		var isCreate = false;

		// init
		getDisplayAreasData()
		// create collection
		$('.btn-create').on('click', function () {
			$('.c-create .c-option-title').text('Create Display Area');

			getDisplayAreaId(function(data) {
				// init formData
				resetFormData();
				$('#showareaId').val(data.showareaId);
				showCreateBlock();
				isCreate = true;
			});
		});
		// edit collection
		$(document.body).on('click', '.btn-edit', function (e) {
			var showareaId = $(this).data('id');
			getOneDisplayAreaData({
				showareaId: showareaId
			}, function(resData) {
			 	$('.c-create .c-option-title').text('Edit DisplayArea');
				showCreateBlock();
				initFormData(resData);
			});			
		});
		// delete collection
		$(document.body).on('click', '.btn-delete', function (e) {
			var showareaId = parseInt($(this).data('id'));
			$('#deleteModal').find('.modal-title').html('Delete Display Area!');
			$('#deleteModal').modal('show');
			$('#deleteModal .btn-ok').one('click', function () {
				deleteDisplayAreaData({
					showareaId: showareaId,
				}, function() {
					getDisplayAreasData();
				});
			});
		});
		$(window).on('beforeunload', function() {
			var showareaId = $('#showareaId').val();
			isCreate && showareaId && deleteDisplayAreaData({
				showareaId: showareaId,
			});
		});
		// upload picture
		$('#showareaImgpcurl').on('change', function(e) {
			var $this = $(this);
			var file = $this[0].files[0];
			var formData = new FormData();

			if (!file) return false;

			$this.parent().find('.spinner').show();
			$this.val('');

			formData.append('type', 'showareaPc');
			formData.append('image', file);
			formData.append('showareaId', parseInt($('#showareaId').val()));

			$.ajax({
				url: "${APP_PATH}/ImageUpload/showAreaPc",
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
		$('#showareaImgurl').on('change', function(e) {
			var $this = $(this);
			var file = $this[0].files[0];
			var formData = new FormData();

			if (!file) return false;

			$this.parent().find('.spinner').show();
			$this.val('');

			formData.append('type', 'showareaWap');
			formData.append('image', file);
			formData.append('showareaId', parseInt($('#showareaId').val()));

			$.ajax({
				url: "${APP_PATH}/ImageUpload/showAreaWap",
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
			saveDisplayAreaData(getFormData(), function() {
				// redirect tab-active & then search-data
				if (isCreate) isCreate = false;

				getDisplayAreasData();
				showInitBlock();
				$('#showareaId').val('');
			});
		});
		// cancel collection save
		$('.c-create .btn-cancel').on('click', function () {
			if (isCreate) {
				isCreate = false;
				// delete null DisplayArea
				deleteDisplayAreaData({
					showareaId: $('#showareaId').val(),
				}, function() {
					console.log("cancel create DisplayArea");
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
			$('#showareaId').val('');
			$('#showareaName').val('');
			$('#showareaNumth').val('-1');
			$('#showareaStatus').prop('checked', false);

			resetPicture($('#showareaImgurl'));
			resetPicture($('#showareaImgpcurl'));
		}
		// getFormdData
		function getFormData() {
			var data = {};
			data.showareaId = parseInt($('#showareaId').val());
			data.showareaName = $('#showareaName').val();
			data.showareaNumth = $('#showareaNumth').val();
			data.showareaStatus = $('#showareaStatus').prop('checked') ? 1 : 0;

			data.showareaImgurl = $('#showareaImgurl').attr('data-val') && JSON.parse($('#showareaImgurl').attr('data-val')).imageUrl;
			data.showareaImgpcurl = $('#showareaImgpcurl').attr('data-val') && JSON.parse($('#showareaImgpcurl').attr('data-val')).imageUrl;

			return data;
		}
		// initFormData
		function initFormData(data) {
			// init
			$('#showareaId').val(data.showareaId);
			$('#showareaName').val(data.showareaName);
			$('#showareaNumth').val(data.showareaNumth ? data.showareaNumth : '-1');
			$('#showareaStatus').prop('checked', data.showareaStatus);

			if (data.showareaImgurl) {
				addPicture($('#showareaImgurl'), {
					imageUrl: data.showareaImgurl
				});	
			} else {
				resetPicture($('#showareaImgurl'));
			}

			if (data.showareaImgpcurl) {
				addPicture($('#showareaImgpcurl'), {
					imageUrl: data.showareaImgpcurl
				});				
			} else {
				resetPicture($('#showareaImgpcurl'));
			}

		}
		// callback get id
		function getDisplayAreaId(callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/MlbackShowArea/initializaShowArea",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				success: function (data) {
					if (data.code == 100) {
						callback && callback(data.extend.mlbackShowArea)
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
		function getDisplayAreasData() {
			$('.c-mask').show();

			var formData = new FormData();
			formData.append('pn', getPageNum());

			$.ajax({
				url: "${APP_PATH}/MlbackShowArea/getMlbackShowAreaListByPage",
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
					toastr.error('Failed to get DisplayArea, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback get one data
		function getOneDisplayAreaData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackShowArea/getOneMlbackShowAreaDetail",
				type: "post",
				data: JSON.stringify(reqData),
				dataType: 'json',
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend.mlbackShowAreaOne);
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function () {
					toastr.error('Failed to get DisplayArea, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback save
		function saveDisplayAreaData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackShowArea/save",
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
		function deleteDisplayAreaData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackShowArea/delete",
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
				htmlStr += '<tr><td>' + data[i].showareaId + '</td>' +
					'<td>' + data[i].showareaName + '</td>' +
					'<td>' + data[i].showareaNumth + '</td>' +
					'<td>' + (data[i].showareaImgurl ?
							'<div class="c-table-img"><img src="'+ encodeUrl(data[i].showareaImgurl) +'" /></div>'
							: '<div class="c-table-icon"><svg class="c-icon"><use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image1"></use></svg></div>') +
					'</td>' +
					'<td>' + (data[i].showareaImgpcurl ?
							'<div class="c-table-img"><img src="'+ encodeUrl(data[i].showareaImgpcurl) +'" /></div>'
							: '<div class="c-table-icon"><svg class="c-icon"><use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image1"></use></svg></div>') +
					'</td>' +
					'<td><a class="badge '+ (data[i].showareaStatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].showareaStatus ? 'enable' : 'disable') + '</a></td>' +
					'<td>' + data[i].showareaCreatetime + '</td>' +
					'<td>' + data[i].showareaMotifytime + '</td>' +
					'<td>' +
						'<button class="btn btn-primary btn-edit" data-id="' + data[i].showareaId + '">' +
							'<svg class="c-icon">' +
								'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-pencil"></use>' +
							'</svg>' +
						'</button>' +
						'<button class="btn btn-danger btn-delete" data-id="' + data[i].showareaId + '">' +
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