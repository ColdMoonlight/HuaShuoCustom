<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Reviews</title>
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
						<span class="c-option-title">Reviews</span>
						<button class="btn btn-primary btn-create">Create Review</button>
					</div>
					<div class="c-table">
						<div class="c-table-header">
							<div class="row">
								<div class="form-group col-md-5">
									<label class="col-form-label" for="search-supercate">Supercate</label>
									<div class="controls">
										<select class="form-control supercate-list" id="search-supercate"></select>
									</div>
								</div>
								<div class="form-group col-md-5">
									<label class="col-form-label" for="search-product">Product</label>
									<div class="controls">
										<select class="form-control product-list" id="search-product"></select>
									</div>
								</div>
								<div class="form-group col-md-5">
									<label class="col-form-label" for="search-review-time">Range Time</label>
									<div class="controls">
										<input hidden id="search-review-create-time" />
										<input hidden id="search-review-confirm-time" />
										<input class="form-control daterangetimepicker" id="search-review-time" type="text" />
									</div>
								</div>
								<div class="form-group col-md-5">
									<label class="col-form-label" for="search-product">Rating Star</label>
									<div class="controls">
										<select class="form-control" id="search-review-star">
											<option value="-1">All</option>
											<option value="1">1 star</option>
											<option value="2">2 star</option>
											<option value="3">3 star</option>
											<option value="4">4 star</option>
											<option value="5">5 star</option>
										</select>
									</div>
								</div>
								<div class="form-group col-md-5">
									<label class="col-form-label" for="search-product">Review Status</label>
									<div class="controls">
										<select class="form-control" id="search-review-status">
											<option value="-1">All</option>
											<option value="0">disable</option>
											<option value="1">enable</option>
										</select>
									</div>
								</div>
								<div class="form-group col-md-5">
									<label class="col-form-label" for="search-product">Review Source</label>
									<div class="controls">
										<select class="form-control" id="search-review-source">
											<option value="-1">All</option>
											<option value="0">self</option>
											<option value="1">customer</option>
											<option value="2">ins瀑布流</option>
											<option value="3">ins首页</option>
										</select>
									</div>
								</div>
								<div class="form-group col-md-5">
									<!-- search btn -->
									<div class="search-review-search">
										<button id="search-review-search" class="btn btn-primary">SEARCH</button>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-md-8">
									<%-- <div class="col-md-3">
										<h3 style="font-size: 16px;">下载时间模板</h3>
										<div class="c-upload-img" id="download-time" style="width: 4rem; height: 4rem; padding: 1rem;">
										  <svg class="c-icon" style="width: 2rem; height: 2rem;">
										  	<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-downloadwd"></use>
										  </svg>
										</div>
									</div> --%>
									<div class="col-md-3">
										<h3 style="font-size: 16px;">下载自定义时间模板</h3>
										<div class="c-upload-img" id="custom-time" style="width: 4rem; height: 4rem; padding: 1rem;">
										  <svg class="c-icon" style="width: 2rem; height: 2rem;">
										  	<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-downloadwd"></use>
										  </svg>
										</div>
									</div>
									<div class="col-md-3">
										<h3 style="font-size: 16px;">下载模板</h3>
										<div class="c-upload-img" id="download" style="width: 4rem; height: 4rem; padding: 1rem;">
										  <svg class="c-icon" style="width: 2rem; height: 2rem;">
										  	<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-downloadwd"></use>
										  </svg>
										</div>
									</div>
									<div class="col-md-3">
										<h3 style="font-size: 16px;">上传文档</h3>
										<div class="c-upload-img" style="width: 4rem; height: 4rem;padding: 1rem;">
											<svg class="c-icon" style="width: 2rem; height: 2rem;">
												<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-uploadwd"></use>
											</svg>
											<div class="c-backshow"></div>	
												<!-- <input type="file" id="file" name="myfile" style="display: none" />
												<input type="text" id="filename" style="display:none"></span>
												<input type="button" onclick="upload()" value="选择文件上传" /> -->
											<input id="excleImport" type="file" name="file" />										
											<!-- spinner -->
											<div class="spinner">
												<div class="spinner-border" role="status" aria-hidden="true"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							
						</div>
						<div class="c-table-content">
							<div class="c-table-table table-responsive-sm">
								<table class="table">
									<thead>
										<tr>
											<th>id</th>
											<th>Profile Picture</th>
											<th>Review Customer</th>
											<th>Product Id</th>
											<th>Review Time</th>
											<th>Review Status</th>
											<th>Rating Star</th>
											<th>Review Source</th>
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
						<span class="c-option-title">Edit Products</span>
						<div class="group">
							<button class="btn btn-secondary btn-cancel">Cancel</button>
							<button class="btn btn-primary btn-save">Save Review</button>
						</div>
					</div>
					<div class="c-form row">
						<input id="reviewId" hidden>
						<!-- left panel  -->
						<div class="left-panel col-lg-7 col-md-12">
							<!-- general -->
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">General</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="productName">Customer Name</label>
										<div class="controls">
											<input class="form-control" id="reviewUname" type="text" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="reviewProstarnum">Rating Star</label>
										<div class="controls">
											 <select class="form-control" id="reviewProstarnum" />
												<option value="1">1 star</option>
												<option value="2">2 star</option>
												<option value="3">3 star</option>
												<option value="4">4 star</option>
												<option value="5">5 star</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="reviewFrom">Review Source</label>
										<div class="controls">
											<select class="form-control" id="reviewFrom" />
												<option value="0">self</option>
												<option value="1">customer</option>
												<option value="2">ins瀑布流</option>
												<option value="3">ins首页</option>
											</select>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-md-3 col-form-label" for="reviewStatus">Status</label>
										<div class="controls col-md-3">
											<label class="c-switch c-switch-primary">
												<input class="c-switch-input" id="reviewStatus" type="checkbox">
												<span class="c-switch-slider"></span>
											</label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="reviewDetailstr">Review Text</label>
										<div class="controls">
											<textarea class="form-control" rows="5" id="reviewDetailstr" placeholder="pleasse input review content..."></textarea>
										</div>
									</div>
								</div>
							</div>
							<!-- media picture -->
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Review Media</div>
								</div>
								<div class="card-body">
									<div class="pictureDetails">
										<h3>Review Details Picture</h3>
										<i class="text-danger">Upload up to 6 images！</i>
										<div class="product-img-list">
											<div class="product-img-item c-upload-img">
												<svg class="c-icon">
													<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image-plus"></use>
												</svg>
												<div class="c-backshow"></div>						
												<input class="reviewImgurl" data-order="1" type="file" accept="image/png, image/jpeg, image/gif" />										
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
						<div class="right-panel col-lg-5 col-md-12">
							<!-- sell data  -->
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Belong</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="reviewProduct">Product</label>
										<div class="controls">
											<select class="form-control product-list" id="reviewProduct" /></select>
										</div>
									</div>
								</div>
							</div>
							<!-- review time -->
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Review Time</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="reviewTime">Create Time</label>
										<div class="controls">
											<input type="text" class="form-control datetimepicker" id="reviewCreatetime" placeholder="@exmaple 2020-01-01 00:00:59" autocomplete="off"  />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="reviewTime">Confirm Time</label>
										<div class="controls">
											<input type="text" class="form-control datetimepicker" id="reviewConfirmtime" placeholder="@exmaple 2020-01-01 00:00:59" autocomplete="off" />
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
	<jsp:include page="../common/randomTimeModal.jsp" flush="true"></jsp:include>

	<script type="text/javascript" src="${APP_PATH}/static/back/lib/datetimepicker/moment.min.js"></script>
	<script type="text/javascript" src="${APP_PATH}/static/back/lib/datetimepicker/daterangepicker.js"></script>
	<!-- custom script -->
	<script>
		function getSelectReviewsData() {
			function checkSearchTime() {
				if (getMilliseconds($('#search-review-create-time').val()) >= getMilliseconds($('#search-review-confirm-time').val())) {
					toastr.error('The start time must be less than the end time !');
					$('#search-review-time').focus();
					return false;
				}
				return true;
			}
			if (
					($('#search-supercate').val() != "-1")
					|| ($('#search-product').val() != "-1")
					|| ($('#search-review-star').val() != "-1")
					|| ($('#search-review-source').val() != "-1")
					|| ($('#search-review-status').val() != "-1")
			) {				
				checkSearchTime() && getSearchReviewsData();
			} else {
				if (getMilliseconds($('#search-review-create-time').val()) < getMilliseconds($('#search-review-confirm-time').val())) {
					getSearchReviewsData();
				} else {
					getReviewsData();
				}
			}
		}

		var hasSuperCateList = false;
		var hasProductList = false;
		var isCreate = false;

		if (!hasSuperCateList) getSuperCategoryData(renderSuperCategory);
		if (!hasProductList) getAllProductData(renderAllProduct);

		bindDateTimepicker();
		bindDateRangeEvent(function(startTime, endTime) {
			$('#search-review-create-time').val(startTime);
			$('#search-review-confirm-time').val(endTime);
		});
		getSelectReviewsData();
		$('#search-review-search').on('click', getSelectReviewsData);
		// pagination a-click
		$(document.body).on('click', '#table-pagination li', function (e) {
			getSelectReviewsData();
		});
		// create review
		$('.btn-create').on('click', function () {
			$('.c-create .c-option-title').text('Create Review');
			showCreateBlock();
			// init formData
			resetFormData();
			getReviewId();
			isCreate = true;
		});
		// edit review
		$(document.body).on('click', '.btn-edit', function (e) {
			var reqData = { "reviewId": $(this).data('id') };
			getOneReviewData(reqData, function(resData) {
			 	$('.c-create .c-option-title').text('Edit Review');
				showCreateBlock();
				initFormData(resData);
			});
		});
		// delete review
		$(document.body).on('click', '.btn-delete', function (e) {
			var reviewId = parseInt($(this).data('id'));
			$('#deleteModal').find('.modal-title').html('Delete Review!');
			$('#deleteModal').modal('show');
			$('#deleteModal .btn-ok').one('click', function () {
				deleteReviewData({
					"reviewId": reviewId,
				}, function() {
					getSelectReviewsData();
				});
			});
		});
		// save review
		$('.c-create .btn-save').on('click', function () {
			var reqData = getFormData();
			saveReviewData(reqData, function() {
				if (isCreate) isCreate = false;
				getSelectReviewsData();
				showInitBlock();
				$('#reviewId').val('');
			});
		});
		// cancel review save
		$('.c-create .btn-cancel').on('click', function () {
			if (isCreate) {
				// delete null review
				deleteReviewData({
					reviewId: $('#reviewId').val(),
				}, function() {
					console.log("cancel create-review");
				});
			}
			showInitBlock();
		});
		$(window).on('beforeunload', function() {
			if (isCreate) {
				// delete null review
				var reviewId = $('#reviewId').val();
				reviewId && deleteReviewData({
					reviewId: reviewId,
				});
			}
		});
		// upload review details img
		$(document.body).on('change', '.reviewImgurl', function(e) {
			var $this = $(this);
			var file = $this[0].files[0];
			var formData = new FormData();
			var productSeo = $('#reviewProduct').find('option:checked').data('seo');

			if (!productSeo) {
				toastr.error('The product must be selected before uploading the picture !');
				$('#reviewProduct').focus();
				$('html').animate({scrollTop: 0}, 500);
				return false;
			}

			if (!file) return false;

			$this.parent().find('.spinner').show();
			$this.val('');

			formData.append('image', file);
			formData.append('type', 'reviewDetail');
			formData.append('productSeo', productSeo);
			formData.append('reviewId', parseInt($('#reviewId').val()));
			formData.append('reviewimgSortOrder', $this.attr('data-order'));

			$.ajax({
				url: "${APP_PATH}/ImageUpload/thumImageReviewAll",
				type: "post",
				data: formData,
				processData: false,
				contentType: false,
				cache: false,
				dataType: 'json',
				success: function (data) {
					if (data.code == 100) {
						var count = $('.product-img-item').length;
						if (count < 6 && !$this.attr('data-val')) {
							addUploadBlock(count + 1);
						}
						addPicture($this, {
							imageUrl: data.extend.sqlimageUrl,
							thumImageUrl: data.extend.sqlimageUrl
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
			parentEl.find('.c-backshow').html('<img src="'+ encodeUrl(data.thumImageUrl) + '" />');
		}
		function resetPicture(el) {
			var parentEl = el.parent();
			el.attr('data-val', '');
			parentEl.removeClass('active');
			parentEl.find('.c-backshow').html('');
		}
		function addUploadBlock(idx) {
			var htmlStr = '<div class="product-img-item c-upload-img">' +
				'<svg class="c-icon">' +
					'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image-plus"></use>' +
				'</svg>' +
				'<div class="c-backshow"></div>' +
				'<input class="reviewImgurl" data-order="'+ idx +'" type="file" accept="image/png, image/jpeg, image/gif" />' +
				'<div class="spinner">' +
					'<div class="spinner-border" role="status" aria-hidden="true"></div>' +
				'</div>' +
			'</div>';
			$('.product-img-list').append($(htmlStr));
		}
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
			$('#reviewId').val('');
			$('#reviewUname').val('');
			$('#reviewStatus').prop('checked', false);
			$('#reviewProstarnum').val(1);

			$('#reviewDetailstr').val('');

			$('#reviewFrom').val('0');

			var initTime = initDate();
			$('#reviewCreatetime').val('');
			$('#reviewConfirmtime').val('');

			// reset product-img-list
			$('.product-img-list').html('');
			addUploadBlock(1);

			$('#reviewProduct').val('-1');
		}
		// getFormdData
		function getFormData() {
			var data = {};
			data.reviewId = parseInt($('#reviewId').val());
			data.reviewUname = $('#reviewUname').val();
			data.reviewStatus = $('#reviewStatus').prop('checked') ? 1 : 0;
			data.reviewProstarnum = parseInt($('#reviewProstarnum').val());
			data.reviewDetailstr = $('#reviewDetailstr').val();
			
			data.reviewFrom = $('#reviewFrom').val();

			data.reviewCreatetime = $('#reviewCreatetime').val();
			data.reviewConfirmtime = $('#reviewConfirmtime').val();

			var $ReviewProductSelected = $('#reviewProduct').find('option:checked');
			data.reviewPid = $('#reviewProduct').val();
			data.reviewPname = $ReviewProductSelected.data('name');
			data.reviewSupercateidstr = $ReviewProductSelected.data('supercate');
			data.reviewPseoname = $ReviewProductSelected.data('seo');

			return data;
		}
		// initFormData
		function initFormData(data) {
			$('#reviewId').val(data.reviewId);
			$('#reviewUname').val(data.reviewUname);
			$('#reviewStatus').prop('checked', data.reviewStatus);
			$('#reviewProstarnum').val(data.reviewProstarnum || 1);
			$('#reviewDetailstr').val(data.reviewDetailstr);

			$('#reviewFrom').val(data.reviewFrom || 0);

			$('#reviewCreatetime').val(data.reviewCreatetime || '');
			$('#reviewConfirmtime').val(data.reviewConfirmtime || '');
			
			getReviewAllImgData({
				reviewId: data.reviewId
			}, renderReviewAllImgData);

			$('#reviewProduct').val(data.reviewPid || -1);
		}
		// callback get id
		function getReviewId() {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/MlfrontReview/initializaReview",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				success: function (data) {
					if (data.code == 100) {
						var reviewId = data.extend&& data.extend.mlfrontReview && data.extend.mlfrontReview.reviewId;
						if (reviewId) {
							$('#reviewId').val(reviewId);
							toastr.success(data.extend.resMsg);
						} else {
							toastr.error('create review fail! Please try again.');
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
		// callback get search data
		function getSearchReviewsData() {
			$('.c-mask').show();
			var date = new Date();
			var ymd = date.getFullYear() + '-' + (date.getMonth() + 1 > 9 ? date.getMonth() + 1 : '0' + (date.getMonth() + 1)) + '-' + (date.getDate() > 9 ? date.getDate() : '0' + date.getDate());
			$.ajax({
				url: "${APP_PATH}/MlfrontReview/selectMlblackReviewListBySearch",
				type: "post",
				cache: false,
				data: JSON.stringify({
					'reviewUid': parseInt(getPageNum()),
					'reviewSupercateidstr': $('#search-supercate').val() == '-1' ? 0 : $('#search-supercate').val(),
					'reviewPid': $('#search-product').val() == '-1' ? 0 : parseInt($('#search-product').val()),
					'reviewCreatetime': ($('#search-review-create-time').val() || (ymd + ' 00:00:00')),
					'reviewMotifytime': ($('#search-review-confirm-time').val() || (ymd + ' 23:59:59')),
					'reviewStatus': $('#search-review-status').val() == '-1' ? 999 : $('#search-review-status').val(),
					'reviewFrom': $('#search-review-source').val() == '-1' ? 999 : $('#search-review-source').val(),
					'reviewProstarnum': $('#search-review-star').val() == '-1' ? 0 : $('#search-review-star').val(),
					
				}),
				dataType: "json",
				contentType: 'application/json',
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
					toastr.error('Failed to get Reviews, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback get all data
		function getReviewsData() {
			$('.c-mask').show();

			var formData = new FormData();
			formData.append('pn', getPageNum());

			$.ajax({
				url: "${APP_PATH}/MlfrontReview/getMlfrontReviewByPage",
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
					toastr.error('Failed to get Reviews, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback get one data
		function getOneReviewData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlfrontReview/getOneMlfrontReviewDetailById",
				type: "post",
				data: JSON.stringify(reqData),
				dataType: "json",
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend.mlfrontReviewOne);
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function () {
					toastr.error('Failed to get review details, please refresh the page to get again !');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback save
		function saveReviewData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlfrontReview/save",
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
		function deleteReviewData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlfrontReview/delete",
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
		// callback get all img data
		function getReviewAllImgData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackReviewImg/getMlbackReviewImgListByReviewId",
				type: "post",
				cache: false,
				/* dataType: "json",
				contentType: 'application/json',
				data: JSON.stringify(reqData), */
				data: reqData,
				success: function (data) {
					if (data.code == 100) {
						toastr.success(data.extend.resMsg);
						callback(data.extend.mlbackReviewImgResList);
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
			function getReviewFrom(flag) {
				var text = ''
				switch(flag) {
					case 0 :
						text = flag + '-self';
						break;
					case 1 :
						text = flag + '-customer';
						break;
					case 2 :
						text = flag + '-ins瀑布流';
						break;
					case 3 :
						text = flag + '-ins首页';
						break;
					defult:
						text = flag + '-self';
				}
				return text;
			}
			for (var i = 0, len = data.length; i < len; i += 1) {
				htmlStr += '<tr><td>' + data[i].reviewId + '</td>' +
					'<td>' +
						(data[i].reviewUimgurl ?
							'<div class="c-table-img"><img src="'+ encodeUrl(data[i].reviewUimgurl) +'" /></div>'
							: '<div class="c-table-icon"><svg class="c-icon"><use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image1"></use></svg></div>') +
					'</td>' +
					'<td>' + data[i].reviewUname + '</td>' +
					'<td>' + data[i].reviewPid + '</td>' +
					'<td>' + data[i].reviewCreatetime + '</td>' +
					'<td><a class="badge '+ (data[i].reviewStatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].reviewStatus ? 'enable' : 'disable') + '</a></td>' +
					'<td>' + data[i].reviewProstarnum + '</td>' +
					'<td>' + getReviewFrom(data[i].reviewFrom) + '</td>' +
					'<td>' +
						'<button class="btn btn-primary btn-edit" data-id="'+ data[i].reviewId +'">' +
							'<svg class="c-icon">' +
								'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-pencil"></use>' +
							'</svg>' +
						'</button>' +
						'<button class="btn btn-danger btn-delete" data-id="' + data[i].reviewId + '">' +
							'<svg class="c-icon">' +
								'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-trash"></use>' +
							'</svg>' +
						'</button>' +
					'</td></tr>';
			}
			$('.c-table-table tbody').html(htmlStr);
		}
		// render review all img data
		function renderReviewAllImgData(data) {
			var len = data.length;
			$('.product-img-list').html('');

			for (var i = 0; i < len; i+=1) {
				addUploadBlock(data[i].reviewimgSortOrder);
				addPicture($('.product-img-item').last().find('.reviewImgurl'), {
					imageUrl: data[i].reviewimgUrl,
					thumImageUrl: data[i].reviewimgUrl
				});
			}

			if (len < 0) addUploadBlock(1);

			if (len < 6) addUploadBlock(len);
		}
		/***2020-08-25新增*******************/
		$('#download-time').on('click', function() {
			$('#download-time-year').val('-1');
			$('#download-time-month').val('-1');
			$('#download-time-number').val('');
			$('#randTimeModal').modal('show');
			$('.startday').hide();
			$('.endday').hide();
			$('#download-time-custm').hide();
			$('#download-time-template').show();
			
		});
		/***2020-12-15***/
		$('#custom-time').on('click', function() {
			$('#download-time-year').val('-1');
			$('#download-time-month').val('-1');
			$('#download-time-number').val('');
			$('#randTimeModal').modal('show');
			$('.startday').show();
			$('.endday').show();
			$('#download-time-custm').show();
			$('#download-time-template').hide();
		});
		
		$('#download-time-template').on('click', function() {
			var year = parseInt($('#download-time-year').val(), 10);
			var month = parseInt($('#download-time-month').val(), 10);
			var number = parseInt($('#download-time-number').val(), 10);
			if (year < 0) {
				toastr.error('请选择随机生成所需要的年份。。。');
				return;
			}
			if (month < 0) {
				toastr.error('请选择随机生成所需要的月份。。。');
				return;
			}
			if (!number) {
				toastr.error('请选择随机生成所需要的数目。。。');
				return;
			}
			
			window.location.href = '${APP_PATH}/ExcleReviewTimeDownload/exportRandomTime?year='+ year +'&month='+ month +'&number='+ number;
			$('#randTimeModal').modal('hide');
		});
		/*******2020-12-15*******/
		$('#download-time-custm').on('click', function() {
			var year = parseInt($('#download-time-year').val(), 10);
			var month = parseInt($('#download-time-month').val(), 10);
			var number = parseInt($('#download-time-number').val(), 10);
			/****新增***/
			var startday = parseInt($('#download-startday').val(), 10);
			var endday = parseInt($('#download-endday').val(), 10);
			if (year < 0) {
				toastr.error('请选择随机生成所需要的年份。。。');
				return;
			}
			if (month < 0) {
				toastr.error('请选择随机生成所需要的月份。。。');
				return;
			}
			if (!number) {
				toastr.error('请选择随机生成所需要的数目。。。');
				return;
			}
			if (!startday) {
				toastr.error('请输入开始时间。。。');
				return;
			}
			if (!endday) {
				toastr.error('请输入结束时间。。。');
				return;
			}
			window.location.href = '${APP_PATH}/ExcleReviewTimeDownload/exportRandomDIYTime?year='+ year +'&month='+ month +'&number='+ number+'&startday='+startday+'&endday='+endday;
			$('#randTimeModal').modal('hide');
		});
		$('#download').on('click', function() {
			window.location.href = "${APP_PATH}/excleImport/exportReviewsImportDemo";
		});
		$('#excleImport').on('change', function(e) {
			var $this = $(this);
			var excleFile = $this[0].files[0];
			if (!excleFile) return false;
			var name = $this.val('');
			var exclFormData = new FormData();
			exclFormData.append('file', excleFile);
			exclFormData.append('name', name);
			// console.log(exclFormData)
			var file_typename =  excleFile.name.substring(excleFile.name.lastIndexOf('.'));
			if (file_typename === '.xlsx' || file_typename === '.xls') {
				$.ajax({
					url: "${APP_PATH}/excleImport/inportReviews",
					type: "post",
					data: exclFormData,
					processData: false,
					contentType: false,
					cache: false,
					// dataType: 'json',
					success: function (data) {
						// console.log(data);
						alert("Upload successful ！");
						 window.location.reload();
						
					},
					error: function (err) {
						toastr.error(err);
						// console.log("出错了")
					},
					complete: function () {
						$this.parent().find('.spinner').hide();
					}
				});
				
			}else{
				console.log("The file format is incorrect")
				alert("The file format is incorrect")
			}
			
			
			
			
		});
		
		
		
		
		
	</script>
</body>

</html>