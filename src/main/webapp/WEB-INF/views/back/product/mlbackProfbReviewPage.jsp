<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>FB review</title>
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
						<span class="c-option-title">FB-review</span>
						<button class="btn btn-primary btn-create">Create FB-review</button>
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
									<input id="searchFbReview" type="text" placeholder="Search fb-reviews by product-seo">						
									<select class="supercate-list" id="searchSupercate"></select>
								</div>
								<a class="btn btn-primary input-group-addon btn-save-search">Save search</a>
							</div>
							<div class="c-table-table table-responsive-sm">
								<table class="table">
									<thead>
										<tr>
											<th>id</th>
											<th>product-id</th>
											<th>product-seo</th>
											<th>supercate-name</th>
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
						<span class="c-option-title">Edit FB-review</span>
						<div class="group">
							<button class="btn btn-secondary btn-cancel">Cancel</button>
							<button class="btn btn-primary btn-save">Save FB-review</button>
						</div>
					</div>
					<div class="c-form row">
						<input id="profbreviewAreaId" hidden>
						<!-- left panel  -->
						<div class="left-panel col-lg-7 col-md-12">
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">General</div>
								</div>
								<div class="card-body">
									<div class="form-group row">
										<label class="col-md-3 col-form-label" for="profbreviewAreaStatus">Status</label>
										<div class="controls col-md-3">
											<label class="c-switch c-switch-primary">
												<input class="c-switch-input" id="profbreviewAreaStatus" type="checkbox">
												<span class="c-switch-slider"></span>
											</label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="profbreviewAreaDesc">FB-Review desc</label>
										<div class="controls">
											<textarea class="form-control" id="profbreviewAreaDesc" row="6"></textarea>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- right panel  -->
						<div class="right-panel col-lg-5 col-md-12">
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Super Category & Product-list</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="profbreviewSupercateId">Super Category</label>
										<div class="controls">
											<select class="form-control supercate-list" id="profbreviewSupercateId" /></select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="profbreviewAreaPid">Product list</label>
										<div class="controls">
											<select class="form-control product-list" id="profbreviewAreaPid" /></select>
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
	<jsp:include page="../common/imgModal.jsp" flush="true"></jsp:include>

	<script src="${APP_PATH}/static/back/lib/tagsinput/bootstrap-tagsinput.min.js"></script>
	<script src="${APP_PATH}/static/back/lib/summernote/summernote.min.js"></script>
	<!-- custom script -->
	<script>
		var hasSuperCateList = false;
		var hasProductList = false;
		var isCreate = false;
		var storageName = 'fbreviews';

		if (!hasSuperCateList) getSuperCategoryData(renderSuperCategory);
	 	$('#searchSupercate').val($('#searchSupercate').data('val') || -1);

	 	// init summernote editor for description
		$('#profbreviewAreaDesc').summernote({
			height: 300,
	        toolbar: [
				['style', ['style', 'bold', 'italic', 'underline', 'clear']],
				['fontsize', ['fontsize']],
				['height', ['height']],
				['color', ['color']],
				['para', ['ul', 'ol', 'paragraph']],
				['table', ['table']],
				['insert', ['link', 'picture', 'video']],
				['view', ['codeview']]
	        ]
	   	});

		// init
		renderTabItems();
		// save search
		$('.btn-save-search').on('click', function () {
			var searchFbReviewVal = {
				supercate: $('#searchSupercate').find('option:selected').text(),
				supercateId: $('#searchSupercate').val(),
				fbreview: $('#searchFbReview').val()
			};
			// cancel repeat add save-search
			if (checkNewItem(searchFbReviewVal)) return;
			if (parseInt(searchFbReviewVal.supercateId) < 0) searchFbReviewVal.supercate = "";
			if (searchFbReviewVal.supercate || searchFbReviewVal.fbreview) {
				addStorageItem(searchFbReviewVal);
				$('.c-table-tab-tempory').html('');
				createTableTabItem(searchFbReviewVal);
				addTableTabItem(searchFbReviewVal, $('.c-table-tab-item').length);
			}
		});
		// search it
		$('#searchSupercate').on('change', function() {
			$(this).attr('data-val', $(this).val());
			updateSearchData();
		});
		var oldTime = (new Date()).getTime(),
			timer = null;
		$('#searchFbReview').on('keyup', function() {
			var distanceTime = 1000,
				newTime =  (new Date()).getTime();
			if (newTime - oldTime < 1000) clearTimeout(timer);
			oldTime = newTime;
			timer = setTimeout(function() {
				updateSearchData();
			}, distanceTime);
		});
		// search status change
		function updateSearchData() {
			var searchFbReviewVal = {
				supercate: $('#searchSupercate').find('option:selected').text(),
				supercateId: $('#searchSupercate').val(),
				fbreview: $('#searchFbReview').val()
			};
			// inital pagination num
			setPageNum(1);
			// check searchFbReview
			if (parseInt(searchFbReviewVal.supercateId) < 0) searchFbReviewVal.supercate = "";

			$('.c-table-tab-item.active').removeClass('active');
			$('.c-table-tab-tempory').html(createTableTabItem(searchFbReviewVal).addClass('active'));
			getTabSearchData($('.c-table-tab-tempory .c-table-tab-item'));
		}
		// tab-item click
		$(document.body).on('click', '.c-table-tab-item', function (e) {
			$('.c-table-tab-item').removeClass('active');
			$(this).addClass('active');
			// inital pagination num
			setPageNum(1);
			setActiveItemNum($(this).data('idx'));
			getTabSearchData($(this));
		});
		// get Data for table
		function getTabSearchData($this) {
			var dataVal = $this.data('val');
			if (dataVal && (dataVal.fbreview || dataVal.supercate)) {
				$('#searchFbReview').val(dataVal.fbreview || '');
				$('#searchSupercate').attr('data-val', dataVal.supercateId || '-1');
				$('#searchSupercate').val(dataVal.supercateId || '-1');
				getSearchFbReviewsData();
			} else {
				$('#searchSupercate').val('-1');
				$('#searchFbReview').val('');
				initActiveItemNum();
				getFbReviewsData();
			}
		}
		// tab delete
		$(document.body).on('click', '.delete-table-tab-item', deleteTableTabItem);
		// pagination a-click
		$(document.body).on('click', '#table-pagination li', function (e) {
			getTabSearchData($('.c-table-tab-item.active'));
		});
		// create
		$('.btn-create').on('click', function () {
			$('.c-create .c-option-title').text('Create FB-review');
			showCreateBlock();
			// init formData
			resetFormData();
			getFbReviewId();
			isCreate = true;
		});
		// edit
		$(document.body).on('click', '.btn-edit', function (e) {
			var profbreviewAreaId = $(this).data('id');
			getOneFbReviewData({
				profbreviewAreaId: profbreviewAreaId
			}, function(resData) {
			 	$('.c-create .c-option-title').text('Edit FB-review');
				showCreateBlock();
				initFormData(resData);
			});			
		});
		// delete
		$(document.body).on('click', '.btn-delete', function (e) {
			var profbreviewAreaId = parseInt($(this).data('id'));
			$('#deleteModal').find('.modal-title').html('Delete FB-review!');
			$('#deleteModal').modal('show');
			$('#deleteModal .btn-ok').one('click', function () {
				deleteFbReviewData({
					profbreviewAreaId: profbreviewAreaId,
				}, function() {
					getFbReviewsData();
				});
			});
		});
		$(window).on('beforeunload', function() {
			var profbreviewAreaId = $('#profbreviewAreaId').val();
			isCreate && profbreviewAreaId && deleteFbReviewData({
				profbreviewAreaId: profbreviewAreaId,
			});
			$('#img-all-handle').addClass('hide');
		});
		// save
		$('.c-create .btn-save').on('click', function () {
			saveFbReviewData(getFormData(), function() {
				// redirect tab-active & then search-data
				if (isCreate) {
					isCreate = false;
					initActiveItemNum();
					$('.c-table-tab-item').removeClass('active').eq(0).addClass('active');
				}

				getTabSearchData($('.c-table-tab-item.active'));
				showInitBlock();
				$('#profbreviewAreaId').val('');
			});
		});
		// cancel save
		$('.c-create .btn-cancel').on('click', function () {
			if (isCreate) {
				isCreate = false;
				// delete null data
				deleteFbReviewData({
					profbreviewAreaId: $('#profbreviewAreaId').val(),
				}, function() {
					console.log("cancel create FB-review");
				});
			}

			showInitBlock();
		});
		// status combinewith supercate
		$('#profbreviewAreaStatus').on('click', function(e) {
			if (parseInt($('#categorySuperCateId').val()) < 0) {
				toastr.info('Please Select super-category!');
				$('#categorySuperCateId').focus();
				$('#profbreviewAreaStatus').prop('checked', false);
			}
		});
		// supercate & profbreviewAreaStatus combinewith
		$('#categorySuperCateId').on('change', function(e) {
			if (parseInt($(this).val()) < 0) {
				$('#profbreviewAreaStatus').prop('checked', false);
			}
		});
		function showCreateBlock() {
			$('.c-init').addClass('hide');
			$('.c-create').removeClass('hide');

			if (!hasProductList) getAllProductData(renderAllProduct);
		}
		function showInitBlock() {
			$('.c-init').removeClass('hide');
			$('.c-create').addClass('hide');
		}
		// handle formData
		// reset data
		function resetFormData() {
			$('#profbreviewAreaId').val('');
			$('#profbreviewAreaStatus').prop('checked', false);
			$('#profbreviewAreaDesc').summernote('reset'); 

			$('#categorySuperCateId').val('-1');
			$('#profbreviewAreaPid').val('-1');
			$('#profbreviewSupercateId').val('-1');
			$('#profbreviewSupercateName').val('');
		}
		// getFormdData
		function getFormData() {
			var data = {};
			data.profbreviewAreaId = parseInt($('#profbreviewAreaId').val());
			data.profbreviewAreaStatus = $('#profbreviewAreaStatus').prop('checked') ? 1 : 0;
			data.profbreviewAreaDesc = $('#profbreviewAreaDesc').val();

			data.profbreviewSupercateId = $('#profbreviewSupercateId').val();
			data.profbreviewSupercateName = $('#profbreviewSupercateId').find('option:selected').text();

			data.profbreviewAreaPid = $('#profbreviewAreaPid').val();
			data.profbreviewAreaPseo = $('#profbreviewAreaPid').find('option:selected').data('seo');

			return data;
		}
		// initFormData
		function initFormData(data) {
			$('#profbreviewAreaId').val(data.profbreviewAreaId);
			$('#categorySortOrder').val(data.categorySortOrder ? data.categorySortOrder : '0');
			$('#profbreviewAreaStatus').prop('checked', (data.categorySuperCateId > 0 ? data.profbreviewAreaStatus : 0));
			$('#categoryLable').val(data.categoryLable);
			$('#profbreviewAreaDesc').summernote('code', data.profbreviewAreaDesc);

			if (hasSuperCateList && hasProductList) {
				// value
				$('#categorySuperCateId').val(data.categorySuperCateId || '-1');
				$('#profbreviewAreaPid').val(data.profbreviewAreaPid || '-1');
			}
			// attr
			$('#categorySuperCateId').attr('data-val', data.categorySuperCateId || '-1');
			$('#profbreviewAreaPid').attr('data-val', data.profbreviewAreaPid || '-1');

			$('#profbreviewSupercateId').val(data.profbreviewSupercateId);
			$('#profbreviewSupercateName').val(data.profbreviewSupercateName);
			$('#categoryProductList').val(data.profbreviewSupercateName ? ' * ' + data.profbreviewSupercateName.replace(/\,/g, '\n * '): 'No product！').attr('data-val', data.profbreviewSupercateName);

			$('#profbreviewAreaStatus').prop('checked', data.profbreviewAreaStatus);
		}
		// callback get id
		function getFbReviewId() {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/MlbackProfbReview/initializaProfbreviewArea",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				success: function (data) {
					if (data.code == 100) {
						var profbreviewAreaId = data.extend&& data.extend.mlbackProfbreviewArea && data.extend.mlbackProfbreviewArea.profbreviewAreaId;
						if (profbreviewAreaId) {
							$('#profbreviewAreaId').val(data.extend.mlbackProfbreviewArea.profbreviewAreaId);
							toastr.success(data.extend.resMsg);
						} else {
							toastr.error('create collecion fail! Please try again.');
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
		function getFbReviewsData() {
			$('.c-mask').show();

			var formData = new FormData();
			formData.append('pn', getPageNum());

			$.ajax({
				url: "${APP_PATH }/MlbackProfbReview/getMlbackProfbreviewByPage",
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
					toastr.error('Failed to get Categeory, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback get one data
		function getOneFbReviewData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/MlbackProfbReview/getOneMlbackProfbreviewAreaDetail",
				type: "post",
				data: JSON.stringify(reqData),
				dataType: 'json',
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend.mlbackProfbreviewAreaOne);
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function () {
					toastr.error('Failed to get FB-review, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback get search data
		function getSearchFbReviewsData(data) {
			$('.c-mask').show();

			var formData = new FormData();
			formData.append('profbreviewAreaPseo', $('#searchFbReview').val());
			formData.append('profbreviewSupercateId', ($('#searchSupercate').attr('data-val') || '-1'));
			formData.append('pn', getPageNum());

			$.ajax({
				url: "${APP_PATH }/MlbackProfbReview/backSearchByprofbreviewArea",
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
					toastr.error('Failed to get fb-reviews, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback save
		function saveFbReviewData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackProfbReview/save",
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
		function deleteFbReviewData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackProfbReview/delete",
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
				htmlStr += '<tr><td>' + data[i].profbreviewAreaId + '</td>' +
					'<td>' + data[i].profbreviewAreaPid + '</td>' +
					'<td>' + data[i].profbreviewAreaPseo + '</td>' +
					'<td>' + data[i].profbreviewSupercateName + '</td>' +					
					'<td><a class="badge '+ (data[i].profbreviewAreaStatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].profbreviewAreaStatus ? 'enable' : 'disable') + '</a></td>' +
					/* '<td>' + data[i].profbreviewAreaDesc + '</td>' + */
					'<td>' +
						'<button class="btn btn-primary btn-edit" data-id="' + data[i].profbreviewAreaId + '">' +
							'<svg class="c-icon">' +
								'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-pencil"></use>' +
							'</svg>' +
						'</button>' +
						'<button class="btn btn-danger btn-delete" data-id="' + data[i].profbreviewAreaId + '">' +
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