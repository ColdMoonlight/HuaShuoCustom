<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Collection</title>
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
						<span class="c-option-title">SuperCate</span>
						<button class="btn btn-primary btn-create">Create SuperCate</button>
					</div>
					<div class="c-table">
						<div class="c-table-tab">
							<div class="c-table-tab-item active">All</div>
						</div>
						<div class="c-table-content">
							<div class="input-group c-search">
								<svg class="c-icon">
									<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-magnifying-glass"></use>
								</svg>
								<div  class="form-control">
									<input name="searchCollection" type="text" disabled placeholder="Search Collections">
								</div>
								<a class="btn btn-primary input-group-addon btn-save-search" disabled>Save search</a>
							</div>							
							<div class="c-table-table table-responsive-sm">
								<table class="table">
									<thead>
										<tr>
											<th>supercate-id</th>
											<th>iamge</th>
											<th>supercate-name</th>
											<th>status</th>
											<th>order</th>
											<th>operation</th>
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
						<span class="c-option-title">Edit SuperCate</span>
						<div class="group">
							<button class="btn btn-secondary btn-cancel">Cancel</button>
							<button class="btn btn-primary btn-save">Save SuperCate</button>
						</div>
					</div>
					<div class="c-form row">
						<input id="supercateId" hidden>
						<!-- left panel -->
						<div class="left-panel col-lg-7 col-md-12">
							<div class="card">
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="supercateName">SuperCate Name</label>
										<div class="controls">
											<input class="form-control" id="supercateName" type="text" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="supercateSeo">Supercate SEO</label>
										<div class="controls">
											 <input class="form-control" id="supercateSeo" type="text">
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="supercateSortOrder">Sort</label>
										<!-- <div class="controls">
												 <input class="form-control" id="supercateSortOrder" type="number" />
											</div> -->
										<div class="controls">
											<select class="form-control" id="supercateSortOrder" />
											<option value="0">Please super-category sort-order</option>
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
											</select>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-md-3 col-form-label" for="supercateStatus">Status</label>
										<div class="controls col-md-3">
											<label class="c-switch c-switch-primary">
												<input class="c-switch-input" id="supercateStatus" type="checkbox">
												<span class="c-switch-slider"></span>
											</label>
										</div>
									</div>
								</div>
							</div>
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">SuperCate Image</div>
								</div>
								<div class="card-body">
									<div id="uploadImg" class="c-upload-img">
										<svg class="c-icon">
											<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image-plus"></use>
										</svg>
										<div class="c-backshow"></div>	
										<input type="file" />
										<!-- spinner -->
										<div class="spinner">
											<div class="spinner-border" role="status" aria-hidden="true"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- right panel -->
						<div class="right-panel col-lg-5 col-md-12">
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Search engine listing preview</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="supercateMetatitle">Page title</label>
										<div class="controls">
											<input class="form-control" id="supercateMetatitle" type="text" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="supercateMetakeywords">Page keywords</label>
										<div class="controls">
											<textarea class="form-control" rows="5" id="supercateMetakeywords"></textarea>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="supercateMetadesc">Page Description</label>
										<div class="controls">
											<textarea class="form-control" rows="5" id="supercateMetadesc"></textarea>
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
		var inputSearchEl = $('input[name="searchCollection"]');
		var isCreate = false;
		// init
		renderTabItems();
		getCollectionsData();
		// search
		$('.btn-save-search').on('click', function () {
			var searchCollectionVal = inputSearchEl.val().trim();
			if (searchCollectionVal) {
				addCollectionItem(searchCollectionVal);
				addTabItemEl(searchCollectionVal);
			}
		});
		// tab-item click
		$(document.body).on('click', '.c-table-tab-item', function () {
			$('.c-table-tab-item').removeClass('active');
			$(this).addClass('active');
			setPageNum(1);
			getCollectionsData($(this).text());
		});
		// tab delete
		$(document.body).on('click', '.delete-table-tab-item', deleteCollectionEl);
		// pagination
		$(document.body).on('click', '#table-pagination li', function (e) {
			getCollectionsData($(this).text());
		});
		// create collection
		$('.btn-create').on('click', function () {
			$('.c-create c-option-title').text('Create Collection');
			showCreateBlock();
			// init formData
			resetFormData();
			getCollectionId();
			isCreate = true;
		});
		// edit collection
		$(document.body).on('click', '.btn-edit', function (e) {
			var cId = $(this).data('id');
			getOneCollectionData({
				supercateId: cId
			}, function(resData) {
				$('.c-create c-option-title').text('Edit Collection');
				showCreateBlock();
				initFormData(resData);
			});
		});
		// delete collection
		$(document.body).on('click', '.btn-delete', function (e) {
			var supercateId = parseInt($(this).data('id'));
			$('#deleteModal').find('.modal-title').html('Delete SuperCategory!');
			$('#deleteModal').modal('show');
			$('#deleteModal .btn-ok').one('click', function () {
				deleteCollectionData({
					supercateId: supercateId,
				}, getCollectionsData);

			});
		});
		$(window).on('beforeunload', function() {
			var supercateId = $('#supercateId').val();
			isCreate && supercateId && deleteCollectionData({
				supercateId: supercateId,
			});
		});
		// save collection
		$('.c-create .btn-save').on('click', function () {
			saveCollectionData(getFormData(), function() {
				getCollectionsData();
				showInitBlock();
				isCreate = false;
				$('#supercateId').val('');
			});
		});
		// cancel collection save
		$('.c-create .btn-cancel').on('click', function () {
			if (isCreate) {
				deleteCollectionData({
					supercateId: $('#supercateId').val(),
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
			$('#supercateId').val('');
			$('#supercateName').val('');
			$('#supercateSortOrder').val('0');
			$('#supercateStatus').prop('checked', false);

			$('#supercateImgurl').val('');

			$('#supercateSeo').prop('checked', false);
			$('#supercateMetatitle').val('');
			$('#supercateMetakeywords').val('');
			$('#supercateMetadesc').val('');
		}
		// getFormdData
		function getFormData() {
			var data = {};
			data.supercateId = parseInt($('#supercateId').val());
			data.supercateName = $('#supercateName').val();
			data.supercateSortOrder = $('#supercateSortOrder').val();
			data.supercateStatus = $('#supercateStatus').prop('checked') ? 1 : 0;

			data.supercateImgurl = $('#supercateImgurl').val();

			data.supercateSeo = String($('#supercateSeo').prop('checked'));
			data.supercateMetatitle = $('#supercateMetatitle').val();
			data.supercateMetakeywords = $('#supercateMetakeywords').val();
			data.supercateMetadesc = $('#supercateMetadesc').val();

			return data;
		}
		// initFormData
		function initFormData(data) {
			$('#supercateId').val(data.supercateId);
			$('#supercateName').val(data.supercateName);
			$('#supercateSortOrder').val(data.supercateSortOrder ? data.supercateSortOrder : '0');
			$('#supercateStatus').prop('checked', data.supercateStatus);

			$('#supercateImgurl').val(data.supercateImgurl);

			$('#supercateSeo').prop('checked', data.supercateSeo);
			$('#supercateMetatitle').val(data.supercateMetatitle);
			$('#supercateMetakeywords').val(data.supercateMetakeywords);
			$('#supercateMetadesc').val(data.supercateMetadesc);
		}
		// callback id
		function getCollectionId() {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/MlbackSuperCate/initializaSuperCate",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				success: function (data) {
					if (data.code == 100) {
						$('#supercateId').val(data.extend.mlbackSuperCate.supercateId);
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
		function getCollectionsData(val) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackSuperCate/getMlbackSuperCateByPage",
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
					toastr.error('Failed to get Super-Categeory, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		//  callback get one
		function getOneCollectionData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackSuperCate/getOneMlbackSuperCateDetail",
				type: "post",
				data: JSON.stringify(reqData),
				dataType: 'json',
				contentType: 'application/json', 
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend.mlbackSuperCate);
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
		// callback save
		function saveCollectionData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackSuperCate/save",
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
		function deleteCollectionData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackSuperCate/delete",
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
				htmlStr += '<tr><td>' + data[i].supercateId + '</td>' +
					'<td>' +
						(data[i].supercateImgurl ?
							'<div class="c-table-img" style="background-image: url(' + data[i].supercateImgurl + ');"></div>'
							: '<div class="c-table-icon"><svg class="c-icon"><use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image1"></use></svg></div>') +
					'</td>' +
					'<td>' + data[i].supercateName + '</td>' +
					'<td>' + (data[i].supercateStatus ? 'enable' : 'disable') + '</td>' +
					'<td>' + data[i].supercateSortOrder + '</td>' +
					'<td>' +
					'<button class="btn btn-primary btn-edit" data-id="' + data[i].supercateId + '">' +
					'<svg class="c-icon">' +
					'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-pencil"></use>' +
					'</svg>' +
					'</button>' +
					'<button class="btn btn-danger btn-delete" data-id="' + data[i].supercateId + '">' +
					'<svg class="c-icon">' +
					'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-trash"></use>' +
					'</svg>' +
					'</button>' +
					'</td></tr>';
			}
			$('.c-table-table tbody').html(htmlStr);
		}
		function renderTabItems() {
			var collections = getCollectionList(),
				len = collections.length,
				htmlStr = '';
			if (len > 0) {
				for (var i = 0; i < len; i += 1) {
					htmlStr += createCollectionItem(collections[i])[0].outerHTML;
				}
				$('.c-table-tab').append(htmlStr);
				$('.c-table-tab-item').removeClass('active').eq(0).addClass('active');
			}
		}
		function addTabItemEl(val) {
			$('.c-table-tab-item').removeClass('active');
			$('.c-table-tab').append(createCollectionItem(val));
		}
		function createCollectionItem(val) {
			return $('<div class="c-table-tab-item active">' + val +
				'<div class="delete-table-tab-item c-icon" data-item="' + val + '">x</div></div>');
		}
		function deleteCollectionEl(e) {
			e.stopPropagation();
			var targetEl = $(e.target),
				itemVal = targetEl.data('item'),
				parentEl = targetEl.parent('.c-table-tab-item');
			deleteCollectionItem(itemVal);
			$(parentEl).remove();
			$('.c-table-tab-item').eq(0).addClass('active');
			getCollectionsData();
		}
		function getCollectionList() {
			return JSON.parse(storage.getItem('supercates')) || [];
		}
		function deleteCollectionItem(name) {
			var oldCollections = getCollectionList();
			var newCollections = oldCollections.filter(function (item) {
				if (item != name) return item;
			});
			storage.setItem('supercates', JSON.stringify(newCollections));
		}
		function addCollectionItem(name) {
			var collections = getCollectionList();
			collections.unshift(name);
			storage.setItem('supercates', JSON.stringify(collections));
		}
	</script>
</body>

</html>