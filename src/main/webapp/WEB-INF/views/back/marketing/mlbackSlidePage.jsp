<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Carousel</title>
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
						<span class="c-option-title">Carousel</span>
						<button class="btn btn-primary btn-create">Create Carousel</button>
					</div>
					<div class="c-table">
						<div class="c-table-tab">
							<div class="c-table-tab-item" data-idx="0">All</div>
							<div class="c-table-tab-list"></div>
							<div class="c-table-tab-tempory"></div>
						</div>
						<div class="c-table-content">
							<%-- <div class="input-group c-search">
								<svg class="c-icon">
									<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-magnifying-glass"></use>
								</svg>
								<div class="form-control">
									<input id="searchCarousel" type="text" placeholder="Search Carousel">						
									<select class="supercate-list" id="searchSupercate"></select>
								</div>
								<a class="btn btn-primary input-group-addon btn-save-search">Save search</a>
							</div> --%>
							<div class="c-table-table table-responsive-sm">
								<table class="table">
									<thead>
										<tr>
											<th>id</th>
											<th>name</th>
											<th>area</th>
											<th>order</th>
											<th>product/subject/colleciton</th>
											<th>seo</th>
											<th>ifInto</th>
											<th>wap-image</th>
											<th>wap-status</th>
											<th>pc-image</th>
											<th>pc-status</th>
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
						<span class="c-option-title">Edit Carousel</span>
						<div class="group">
							<button class="btn btn-secondary btn-cancel">Cancel</button>
							<button class="btn btn-primary btn-save">Save Carousel</button>
						</div>
					</div>
					<div class="c-form row">
						<input id="slideId" hidden>
						<!-- left panel  -->
						<div class="left-panel col-lg-7 col-md-12">
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">General</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="slideName">Carousel Name</label>
										<div class="controls">
											<input class="form-control" id="slideName" type="text" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="slideArea">Area</label>
										<div class="controls">
											<select class="form-control" id="slideArea" />
												<option value="-1">Please select the location of the Carousel</option>
												<option value="1">首页轮播</option>
												<option value="2">2优惠券位置</option>
												<option value="3">3You MIGHT ALSO Like</option>
												<option value="4">4首页广告</option>
												<option value="5">5HeaderTopImg</option>
												<option value="6">6ProDetailCouponDesc</option>
												<option value="7">7cheakoutCouponDesc</option>
												<option value="10">10首页PageArea</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="slideFirthNum">Carousel Sort</label>
										<div class="controls">
											<select class="form-control" id="slideFirthNum" />
												<option value="-1">Please select Carousel sort-order</option>
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
										<label class="col-md-3 col-form-label" for="slideWapstatus">Wap Status</label>
										<div class="controls col-md-3">
											<label class="c-switch c-switch-primary">
												<input class="c-switch-input" id="slideWapstatus" type="checkbox">
												<span class="c-switch-slider"></span>
											</label>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-md-3 col-form-label" for="slidePcstatus">Pc Status</label>
										<div class="controls col-md-3">
											<label class="c-switch c-switch-primary">
												<input class="c-switch-input" id="slidePcstatus" type="checkbox">
												<span class="c-switch-slider"></span>
											</label>
										</div>
									</div>
								</div>
							</div>
							<!-- product media -->
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Carousel Image</div>
								</div>
								<div class="card-body">
									<div class="row">
										<div class="col-md-6">
											<h3>Wap Image</h3>
											<div class="c-upload-img">
												<svg class="c-icon">
													<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image-plus"></use>
												</svg>
												<div class="c-backshow"></div>						
												<input id="slideWap" type="file" accept="image/png, image/jpeg, image/gif" />										
												<!-- spinner -->
												<div class="spinner">
													<div class="spinner-border" role="status" aria-hidden="true"></div>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<h3>Pc Image</h3>
											<div class="c-upload-img">
												<svg class="c-icon">
													<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image-plus"></use>
												</svg>
												<div class="c-backshow"></div>						
												<input id="slidePc" type="file" accept="image/png, image/jpeg, image/gif" />										
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
							<!-- product or subject -->
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Product & Subject & Collection</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="slideIfproorcateorpage">Product Or Subject Or Collection</label>
										<div class="controls">
											<select class="form-control" id="slideIfproorcateorpage" />
												<option value="0" data-class="ml-product">product</option>
												<option value="1" data-class="ml-category">collection</option>
												<option value="2" data-class="ml-subject">subject</option>
											</select>
										</div>
									</div>
									<div>
										<div class="form-group ml-product">
											<label class="col-form-label" for="slideProid">Product</label>
											<div class="controls">
												<select class="form-control product-list" id="slideProid" /></select>
											</div>
										</div>
										<div class="form-group ml-category hide">
											<label class="col-form-label" for="slideCateid">Category</label>
											<div class="controls">
												<select class="form-control collection-list" id="slideCateid" /></select>
											</div>
										</div>	
										<div class="form-group ml-subject hide">
											<label class="col-form-label" for="slidePageseoname">Subject</label>
											<div class="controls">
												<select class="form-control" id="slidePageseoname">
													<jsp:include page="../layout/subject-option.jsp" flush="true"></jsp:include>
												</select>
											</div>
										</div>								
									</div>
									<div class="form-group">
										<label class="col-form-label" for="slideIfinto">click entry or not</label>
										<div class="controls">
											<select class="form-control" id="slideIfinto" />
												<option value="0" selected="selected">disable</option>
					  							<option value="1">enalbe</option>
											</select>
										</div>
									</div>
								</div>
							</div>
							<!-- <div class="card">
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
							</div> -->
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
		var hasCollectionList = false;
		var hasProductList = false;
		var isCreate = false;

		$('#slideIfproorcateorpage').on('change', function() {
			$('.' + $(this).find('option:checked').data('class')).removeClass('hide').siblings().addClass('hide').find('select').val('-1');
		});

		// init
		getCarouselsData()
		// create collection
		$('.btn-create').on('click', function () {
			$('.c-create .c-option-title').text('Create Carousel');

			getCarouselId(function(data) {
				// init formData
				resetFormData();
				$('#slideId').val(data.slideId);
				showCreateBlock();
				isCreate = true;
			});
		});
		// edit collection
		$(document.body).on('click', '.btn-edit', function (e) {
			var slideId = $(this).data('id');
			getOneCarouselData({
				slideId: slideId
			}, function(resData) {
			 	$('.c-create .c-option-title').text('Edit Carousel');
				showCreateBlock();
				initFormData(resData);
			});			
		});
		// delete collection
		$(document.body).on('click', '.btn-delete', function (e) {
			var slideId = parseInt($(this).data('id'));
			$('#deleteModal').find('.modal-title').html('Delete Carousel!');
			$('#deleteModal').modal('show');
			$('#deleteModal .btn-ok').one('click', function () {
				deleteCarouselData({
					slideId: slideId,
				}, function() {
					getCarouselsData();
				});
			});
		});
		$(window).on('beforeunload', function() {
			var slideId = $('#slideId').val();
			isCreate && slideId && deleteCarouselData({
				slideId: slideId,
			});
		});
		// upload picture
		$('#slidePc').on('change', function(e) {
			var $this = $(this);
			var file = $this[0].files[0];
			var formData = new FormData();

			if (!file) return false;

			$this.parent().find('.spinner').show();
			$this.val('');

			formData.append('type', 'slidePc');
			formData.append('image', file);
			formData.append('slideId', parseInt($('#slideId').val()));

			$.ajax({
				url: "${APP_PATH}/ImageUpload/slidePc",
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
		$('#slideWap').on('change', function(e) {
			var $this = $(this);
			var file = $this[0].files[0];
			var formData = new FormData();

			if (!file) return false;

			$this.parent().find('.spinner').show();
			$this.val('');

			formData.append('type', 'slideWap');
			formData.append('image', file);
			formData.append('slideId', parseInt($('#slideId').val()));

			$.ajax({
				url: "${APP_PATH}/ImageUpload/slideWap",
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
			saveCarouselData(getFormData(), function() {
				// redirect tab-active & then search-data
				if (isCreate) isCreate = false;

				getCarouselsData();
				showInitBlock();
				$('#slideId').val('');
			});
		});
		// cancel collection save
		$('.c-create .btn-cancel').on('click', function () {
			if (isCreate) {
				isCreate = false;
				// delete null carousel
				deleteCarouselData({
					slideId: $('#slideId').val(),
				}, function() {
					console.log("cancel create Carousel");
				});
			}

			showInitBlock();
		});
		function showCreateBlock() {
			$('.c-init').addClass('hide');
			$('.c-create').removeClass('hide');

			if (!hasCollectionList) getAllCollectionData(renderAllCollection);
			if (!hasProductList) getAllProductData(renderAllProduct);
			
		}
		function showInitBlock() {
			$('.c-init').removeClass('hide');
			$('.c-create').addClass('hide');
		}
		// handle formData
		// reset data
		function resetFormData() {
			$('#slideId').val('');
			$('#slideName').val('');
			$('#slideArea').val('-1');
			$('#slideFirthNum').val('-1');
			$('#slideWapstatus').prop('checked', false);
			$('#slidePcstatus').prop('checked', false);

			resetPicture($('#slideWap'));
			resetPicture($('#slidePc'));
			
			$('#slideIfproorcateorpage').val('0');
			$('#slideProid').val('-1');
			$('#slideCateid').val('-1');
			$('#slidePageseoname').val('-1');
			$('#slideIfinto').val('0');
		}
		// getFormdData
		function getFormData() {
			var data = {};
			data.slideId = parseInt($('#slideId').val());
			data.slideName = $('#slideName').val();
			data.slideWapstatus = $('#slideWapstatus').prop('checked') ? 1 : 0;
			data.slidePcstatus = $('#slidePcstatus').prop('checked') ? 1 : 0;
			data.slideFirthNum = $('#slideFirthNum').val();
			data.slideArea = parseInt($('#slideArea').val());

			data.slideWapimgurl = $('#slideWap').attr('data-val') && JSON.parse($('#slideWap').attr('data-val')).imageUrl;
			data.slidePcimgurl = $('#slidePc').attr('data-val') && JSON.parse($('#slidePc').attr('data-val')).imageUrl;

			var slideIfproorcateorpage = $('#slideIfproorcateorpage').val();
			data.slideIfproorcateorpage = slideIfproorcateorpage;
			if (slideIfproorcateorpage == 0) {
				data.slideProid = $('#slideProid').val();
				data.slideProname = $('#slideProid').find('option:checked').data('name') || '';
				data.slideSeoname = $('#slideProid').find('option:checked').data('seo') || '';
				data.slideCateid = '';
				data.slideCatename = '';
				data.slideCateseoname = '';
				data.slidePageseoname = '';
			} else if (slideIfproorcateorpage == 1) {
				data.slideProid = '';
				data.slideProname = '';
				data.slideSeoname = '';
				data.slideCateid = $('#slideCateid').val();
				data.slideCatename = $('#slideCateid').find('option:checked').data('name') || '';
				data.slideCateseoname = $('#slideCateid').find('option:checked').data('seo') || '';
				data.slidePageseoname = '';
			} else if (slideIfproorcateorpage == 2) {
				data.slideProid = '';
				data.slideProname = '';
				data.slideSeoname = '';
				data.slideCateid ='';
				data.slideCatename = '';
				data.slideCateseoname = '';
				data.slidePageseoname = $('#slidePageseoname').val();
			}
			data.slideIfinto = $('#slideIfinto').val();

			return data;
		}
		// initFormData
		function initFormData(data) {
			// initial
			$('#slideProid').val('-1');
			$('#slideCateid').val('-1');
			$('#slidePageseoname').val('-1');
			// init
			$('#slideId').val(data.slideId);
			$('#slideName').val(data.slideName);
			$('#slideArea').val(data.slideArea || '-1');
			$('#slideFirthNum').val(data.slideFirthNum ? data.slideFirthNum : '-1');
			$('#slideWapstatus').prop('checked', data.slideWapstatus);
			$('#slidePcstatus').prop('checked', data.slidePcstatus);
			
			if (data.slideWapimgurl) {
				addPicture($('#slideWap'), {
					imageUrl: data.slideWapimgurl
				});				
			} else {
				resetPicture($('#slideWap'));
			}

			if (data.slidePcimgurl) {
				addPicture($('#slidePc'), {
					imageUrl: data.slidePcimgurl
				});				
			} else {
				resetPicture($('#slidePc'));
			}

			var slideIfproorcateorpage = data.slideIfproorcateorpage;
			$('#slideIfproorcateorpage').val(slideIfproorcateorpage || 0);
			if (slideIfproorcateorpage == 0) {
				$('#slideProid').val(data.slideProid || '-1');
				$('.ml-product').removeClass('hide').siblings().addClass('hide');
			} else if (slideIfproorcateorpage == 1) {
				$('#slideCateid').val(data.slideCateid || '-1');
				$('.ml-category').removeClass('hide').siblings().addClass('hide');
			} else if (slideIfproorcateorpage == 2) {
				$('#slidePageseoname').val(data.slidePageseoname || '-1');
				$('.ml-subject').removeClass('hide').siblings().addClass('hide');
			}
			
			$('#slideIfinto').val(data.slideIfinto);

		}
		// callback get id
		function getCarouselId(callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/MlbackSlides/initializaSlide",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				success: function (data) {
					if (data.code == 100) {
						callback && callback(data.extend.mlbackSlide)
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
		function getCarouselsData() {
			$('.c-mask').show();

			var formData = new FormData();
			formData.append('pn', getPageNum());

			$.ajax({
				url: "${APP_PATH}/MlbackSlides/getMlbackSlideByPage",
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
					toastr.error('Failed to get Carousel, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback get one data
		function getOneCarouselData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackSlides/getOneMlbackSlideDetail",
				type: "post",
				data: JSON.stringify(reqData),
				dataType: 'json',
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend.mlbackSlideOne);
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function () {
					toastr.error('Failed to get Carousel, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback save
		function saveCarouselData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackSlides/save",
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
		function deleteCarouselData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackSlides/delete",
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
				var logName, logSeo;
				if (data[i].slideIfproorcateorpage == 0) {
					logName = '<b>product</b> ' + data[i].slideProid;
					logSeo = data[i].slideSeoname;
				} else if (data[i].slideIfproorcateorpage == 1) {
					logName = '<b>collection</b> ' + data[i].slideCateid;
					logSeo = data[i].slideCateseoname;
				} else if (data[i].slideIfproorcateorpage == 2) {
					logName = '<b>subject</b> ' + data[i].slidePageseoname;
					logSeo = data[i].slidePageseoname;
				}
				htmlStr += '<tr><td>' + data[i].slideId + '</td>' +
					'<td>' + data[i].slideName + '</td>' +
					'<td>' + data[i].slideArea + '</td>' +
					'<td>' + data[i].slideFirthNum + '</td>' +
					'<td>' + (logName || '') + '</td>' +
					'<td>' + (logSeo || '') + '</td>' +
					'<td>' + (data[i].slideIfinto ? 'YES' : 'NO') + '</td>' +
					'<td>' + (data[i].slideWapimgurl ?
							'<div class="c-table-img"><img src="'+ encodeUrl(data[i].slideWapimgurl) +'" /></div>'
							: '<div class="c-table-icon"><svg class="c-icon"><use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image1"></use></svg></div>') +
					'</td>' +
					'<td><a class="badge '+ (data[i].slideWapstatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].slideWapstatus ? 'enable' : 'disable') + '</a></td>' +
					'<td>' + (data[i].slidePcimgurl ?
							'<div class="c-table-img"><img src="'+ encodeUrl(data[i].slidePcimgurl) +'" /></div>'
							: '<div class="c-table-icon"><svg class="c-icon"><use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image1"></use></svg></div>') +
					'</td>' +
					'<td><a class="badge '+ (data[i].slidePcstatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].slidePcstatus ? 'enable' : 'disable') + '</a></td>' +
					'<td>' +
						'<button class="btn btn-primary btn-edit" data-id="' + data[i].slideId + '">' +
							'<svg class="c-icon">' +
								'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-pencil"></use>' +
							'</svg>' +
						'</button>' +
						'<button class="btn btn-danger btn-delete" data-id="' + data[i].slideId + '">' +
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