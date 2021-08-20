<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>coupon area title</title>
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
						<span class="c-option-title">Coupon Area Title</span>
						<button class="btn btn-primary btn-create">Create Coupon-Area-Title</button>
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
											<th>details</th>
											<th>image</th>
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
						<span class="c-option-title">Edit Coupon-Area-Title</span>
						<div class="group">
							<button class="btn btn-secondary btn-cancel">Cancel</button>
							<button class="btn btn-primary btn-save">Save Coupon-Area-Title</button>
						</div>
					</div>
					<div class="c-form row">
						<input id="coupondesctieleId" hidden>
						<!-- left panel  -->
						<div class="left-panel col-lg-12 col-md-12">
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">General</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="coupondesctieleName">Coupon-Area-Title Name</label>
										<div class="controls">
											<input class="form-control" id="coupondesctieleName" type="text" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="coupondesctieleTieledetail">Coupon-Area-Title Details</label>
										<div class="controls">
											<input class="form-control" id="coupondesctieleTieledetail" type="text" />											
										</div>
									</div>
									<div class="form-group row">
										<label class="col-md-3 col-form-label" for="coupondesctieleStatus">Status</label>
										<div class="controls col-md-3">
											<label class="c-switch c-switch-primary">
												<input class="c-switch-input" id="coupondesctieleStatus" type="checkbox">
												<span class="c-switch-slider"></span>
											</label>
										</div>
									</div>
								</div>
							</div>
							<!-- product media -->
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Coupon-Area-Title Image</div>
								</div>
								<div class="card-body">
									<div class="row">
										<div class="col-lg-6 col-md-6">
											<div class="c-upload-img">
												<svg class="c-icon">
													<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image-plus"></use>
												</svg>
												<div class="c-backshow"></div>						
												<input id="coupondesctieleImgurl" type="file" accept="image/png, image/jpeg, image/gif" />										
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
		getCouponAreaTitlesData()
		// create collection
		$('.btn-create').on('click', function () {
			$('.c-create .c-option-title').text('Create Coupon-Area-Title');
	
			getCouponAreaTitleId(function(data) {
				// init formData
				resetFormData();
				$('#coupondesctieleId').val(data.coupondesctieleId);
				showCreateBlock();
				isCreate = true;
			});
		});
		// edit collection
		$(document.body).on('click', '.btn-edit', function (e) {
			var coupondesctieleId = $(this).data('id');
			getOneCouponAreaTitleData({
				coupondesctieleId: coupondesctieleId
			}, function(resData) {
			 	$('.c-create .c-option-title').text('Edit Coupon-Area-Title');
				showCreateBlock();
				initFormData(resData);
			});			
		});
		// delete collection
		$(document.body).on('click', '.btn-delete', function (e) {
			var coupondesctieleId = parseInt($(this).data('id'));
			$('#deleteModal').find('.modal-title').html('Delete Coupon-Area-Title!');
			$('#deleteModal').modal('show');
			$('#deleteModal .btn-ok').one('click', function () {
				deleteCouponAreaTitleData({
					coupondesctieleId: coupondesctieleId,
				}, function() {
					getCouponAreaTitlesData();
				});
			});
		});
		$(window).on('beforeunload', function() {
			var coupondesctieleId = $('#coupondesctieleId').val();
			isCreate && coupondesctieleId && deleteCouponAreaTitleData({
				coupondesctieleId: coupondesctieleId,
			});
		});
		// upload picture
		$('#coupondesctieleImgurl').on('change', function(e) {
			var $this = $(this);
			var file = $this[0].files[0];
			var formData = new FormData();

			if (!file) return false;

			$this.parent().find('.spinner').show();
			$this.val('');

			formData.append('type', 'couponDescTitle');
			formData.append('image', file);
			formData.append('coupondesctieleId', parseInt($('#coupondesctieleId').val()));
	
			$.ajax({
				url: "${APP_PATH}/ImageUpload/couponDescTitle",
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
			saveCouponAreaTitleData(getFormData(), function() {
				// redirect tab-active & then search-data
				if (isCreate) isCreate = false;
	
				getCouponAreaTitlesData();
				showInitBlock();
				$('#coupondesctieleId').val('');
			});
		});
		// cancel collection save
		$('.c-create .btn-cancel').on('click', function () {
			if (isCreate) {
				isCreate = false;
				// delete null CouponAreaTitle
				deleteCouponAreaTitleData({
					coupondesctieleId: $('#coupondesctieleId').val(),
				}, function() {
					console.log("cancel create Coupon-Area-Title");
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
			$('#coupondesctieleId').val('');
			$('#coupondesctieleName').val('');
			$('#coupondesctieleTieledetail').val('');
			$('#coupondesctieleStatus').prop('checked', false);
	
			resetPicture($('#coupondesctieleImgurl'));
		}
		// getFormdData
		function getFormData() {
			var data = {};
			data.coupondesctieleId = parseInt($('#coupondesctieleId').val());
			data.coupondesctieleName = $('#coupondesctieleName').val();
			data.coupondesctieleTieledetail = $('#coupondesctieleTieledetail').val();
			data.coupondesctieleStatus = $('#coupondesctieleStatus').prop('checked') ? 1 : 0;
	
			data.coupondesctieleImgurl = $('#coupondesctieleImgurl').attr('data-val') && JSON.parse($('#coupondesctieleImgurl').attr('data-val')).imageUrl;
	
			return data;
		}
		// initFormData
		function initFormData(data) {
			// init
			$('#coupondesctieleId').val(data.coupondesctieleId || '');
			$('#coupondesctieleName').val(data.coupondesctieleName || '');
			$('#coupondesctieleTieledetail').val(data.coupondesctieleTieledetail || '');
			$('#coupondesctieleStatus').prop('checked', data.coupondesctieleStatus);
	
			if (data.coupondesctieleImgurl) {
				addPicture($('#coupondesctieleImgurl'), {
					imageUrl: data.coupondesctieleImgurl
				});	
			} else {
				resetPicture($('#coupondesctieleImgurl'));
			}
	
		}
		// callback get id
		function getCouponAreaTitleId(callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/MlbackCouponDescTitle/initializaCouponDescTitle",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				success: function (data) {
					if (data.code == 100) {
						callback && callback(data.extend.mlbackCouponDescTitle)
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
		function getCouponAreaTitlesData() {
			$('.c-mask').show();
	
			var formData = new FormData();
			formData.append('pn', getPageNum());
	
			$.ajax({
				url: "${APP_PATH}/MlbackCouponDescTitle/getMlbackCouponDescTitleByPage",
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
					toastr.error('Failed to get Coupon-Area-Title, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback get one data
		function getOneCouponAreaTitleData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackCouponDescTitle/getOneMlbackCouponDescTitleDetail",
				type: "post",
				data: JSON.stringify(reqData),
				dataType: 'json',
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend.mlbackCouponDescTitleOne);
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function () {
					toastr.error('Failed to get Coupon-Area-Title, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback save
		function saveCouponAreaTitleData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackCouponDescTitle/save",
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
		function deleteCouponAreaTitleData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackCouponDescTitle/delete",
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
				htmlStr += '<tr><td>' + data[i].coupondesctieleId + '</td>' +
					'<td>' + data[i].coupondesctieleName + '</td>' +
					'<td>' + data[i].coupondesctieleTieledetail + '</td>' +
					'<td>' + (data[i].coupondesctieleImgurl ?
							'<div class="c-table-img"><img src="'+ encodeUrl(data[i].coupondesctieleImgurl) +'" /></div>'
							: '<div class="c-table-icon"><svg class="c-icon"><use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image1"></use></svg></div>') +
					'</td>' +
					'<td><a class="badge '+ (data[i].coupondesctieleStatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].coupondesctieleStatus ? 'enable' : 'disable') + '</a></td>' +
					'<td>' + data[i].coupondesctieleCreatetime + '</td>' +
					'<td>' + data[i].coupondesctieleMotifytime + '</td>' +
					'<td>' +
						'<button class="btn btn-primary btn-edit" data-id="' + data[i].coupondesctieleId + '">' +
							'<svg class="c-icon">' +
								'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-pencil"></use>' +
							'</svg>' +
						'</button>' +
						'<button class="btn btn-danger btn-delete" data-id="' + data[i].coupondesctieleId + '">' +
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