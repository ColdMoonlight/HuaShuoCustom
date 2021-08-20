<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Video List</title>
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
						<span class="c-option-title">Video</span>
						<button class="btn btn-primary btn-create">Create Video</button>
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
											<th>name</th>
											<th>image</th>
											<th>area</th>
											<th>order</th>
											<th>video-url</th>
											<th>product/subject/colleciton</th>
											<th>seo</th>
											<!-- <th>ifInto</th> -->
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
						<span class="c-option-title">Edit Video</span>
						<div class="group">
							<button class="btn btn-secondary btn-cancel">Cancel</button>
							<button class="btn btn-primary btn-save">Save Video</button>
						</div>
					</div>
					<div class="c-form row">
						<input id="videoId" hidden>
						<!-- left panel  -->
						<div class="left-panel col-lg-7 col-md-12">
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">General</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="videoName">Video Name</label>
										<div class="controls">
											<input class="form-control" id="videoName" type="text" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="videoArea">Area</label>
										<div class="controls">
											<select class="form-control ml-videoarea" id="videoArea" /></select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="videoOrderthNum">Video Sort</label>
										<div class="controls">
											<select class="form-control" id="videoOrderthNum" />
												<option value="-1">Please select Video sort-order</option>
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
									<div class="form-group">
										<label class="col-form-label" for="videoUrl">Video URL</label>
										<div class="controls">
											<input class="form-control" id="videoUrl" type="text" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-md-3 col-form-label" for="videoStatus">Status</label>
										<div class="controls col-md-3">
											<label class="c-switch c-switch-primary">
												<input class="c-switch-input" id="videoStatus" type="checkbox">
												<span class="c-switch-slider"></span>
											</label>
										</div>
									</div>
								</div>
							</div>
							<!-- product media -->
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Video Image</div>
								</div>
								<div class="card-body">
									<div class="row">
										<div class="col-md-6">
											<h3>Image</h3>
											<div class="c-upload-img">
												<svg class="c-icon">
													<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image-plus"></use>
												</svg>
												<div class="c-backshow"></div>						
												<input id="videoImgurl" type="file" accept="image/png, image/jpeg, image/gif" />										
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
										<label class="col-form-label" for="videoIfproorcateorpage">Product Or Subject Or Collection</label>
										<div class="controls">
											<select class="form-control" id="videoIfproorcateorpage" />
												<option value="0" data-class="ml-product">product</option>
												<option value="1" data-class="ml-category">collection</option>
												<option value="2" data-class="ml-subject">subject</option>
											</select>
										</div>
									</div>
									<div>
										<div class="form-group ml-product">
											<label class="col-form-label" for="videoProid">Product</label>
											<div class="controls">
												<select class="form-control product-list" id="videoProid" /></select>
											</div>
										</div>
										<div class="form-group ml-category hide">
											<label class="col-form-label" for="videoCateid">Category</label>
											<div class="controls">
												<select class="form-control collection-list" id="videoCateid" /></select>
											</div>
										</div>	
										<div class="form-group ml-subject hide">
											<label class="col-form-label" for="videoPageseoname">Subject</label>
											<div class="controls">
												<select class="form-control" id="videoPageseoname">
													<jsp:include page="../layout/subject-option.jsp" flush="true"></jsp:include>
												</select>
											</div>
										</div>								
									</div>
									<!-- <div class="form-group">
										<label class="col-form-label" for="slideIfinto">click entry or not</label>
										<div class="controls">
											<select class="form-control" id="slideIfinto" />
												<option value="0" selected="selected">disable</option>
					  							<option value="1">enalbe</option>
											</select>
										</div>
									</div> -->
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
		var hasCollectionList = false;
		var hasProductList = false;
		var hasVideoAreaList = false;
		var isCreate = false;

		$('#videoIfproorcateorpage').on('change', function() {
			$('.' + $(this).find('option:checked').data('class')).removeClass('hide').siblings().addClass('hide').find('select').val('-1');
		});

		// init
		getVideosData()
		// create collection
		$('.btn-create').on('click', function () {
			$('.c-create .c-option-title').text('Create Video');

			getVideoId(function(data) {
				// init formData
				resetFormData();
				$('#videoId').val(data.videoId);
				showCreateBlock();
				isCreate = true;
			});
		});
		// edit collection
		$(document.body).on('click', '.btn-edit', function (e) {
			var videoId = $(this).data('id');
			getOneVideoData({
				videoId: videoId
			}, function(resData) {
			 	$('.c-create .c-option-title').text('Edit Video');
				showCreateBlock();
				initFormData(resData);
			});			
		});
		// delete collection
		$(document.body).on('click', '.btn-delete', function (e) {
			var videoId = parseInt($(this).data('id'));
			$('#deleteModal').find('.modal-title').html('Delete Video!');
			$('#deleteModal').modal('show');
			$('#deleteModal .btn-ok').one('click', function () {
				deleteVideoData({
					videoId: videoId,
				}, function() {
					getVideosData();
				});
			});
		});
		$(window).on('beforeunload', function() {
			var videoId = $('#videoId').val();
			isCreate && videoId && deleteVideoData({
				videoId: videoId,
			});
		});
		// upload picture
		$('#videoImgurl').on('change', function(e) {
			var $this = $(this);
			var file = $this[0].files[0];
			var formData = new FormData();

			if (!file) return false;

			$this.parent().find('.spinner').show();
			$this.val('');

			formData.append('type', 'VideoYTB');
			formData.append('image', file);
			formData.append('videoId', parseInt($('#videoId').val()));

			$.ajax({
				url: "${APP_PATH}/ImageYTBUpload/uploadVideoImg",
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
			saveVideoData(getFormData(), function() {
				// redirect tab-active & then search-data
				if (isCreate) isCreate = false;

				getVideosData();
				showInitBlock();
				$('#videoId').val('');
			});
		});
		// cancel collection save
		$('.c-create .btn-cancel').on('click', function () {
			if (isCreate) {
				isCreate = false;
				// delete null Video
				deleteVideoData({
					videoId: $('#videoId').val(),
				}, function() {
					console.log("cancel create Video");
				});
			}

			showInitBlock();
		});
		// render video area-list
		function renderVideoAreaList(data) {
			var htmlStr = '<option value="-1">Please select the location of the Video</option>';
			for (var i = 0, len = data.length; i < len; i += 1) {
				htmlStr += '<option value="' + data[i].videoshowareaId + '">'+ data[i].videoshowareaId + ' ' + data[i].videoshowareaName +'</option>';
			}
			$('.ml-videoarea').html(htmlStr);
			hasVideoAreaList = true;
		}
		function getAllVideoAreaData(callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackVideoShowArea/getMlbackVideoShowAreaAllList",
				type: "get",
				contentType: 'application/json',
				async: false,
				success: function (data) {
					if (data.code == 100) {
						toastr.success(data.extend.resMsg);
						callback(data.extend.mlbackVideoShowAreaList);
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
		function showCreateBlock() {
			$('.c-init').addClass('hide');
			$('.c-create').removeClass('hide');

			if (!hasCollectionList) getAllCollectionData(renderAllCollection);
			if (!hasProductList) getAllProductData(renderAllProduct);
			if (!hasVideoAreaList) getAllVideoAreaData(renderVideoAreaList);
			
		}
		function showInitBlock() {
			$('.c-init').removeClass('hide');
			$('.c-create').addClass('hide');
		}
		// handle formData
		// reset data
		function resetFormData() {
			$('#videoId').val('');
			$('#videoName').val('');
			$('#videoArea').val('-1');
			$('#videoOrderthNum').val('-1');
			$('#videoUrl').val('');
			$('#videoStatus').prop('checked', false);

			resetPicture($('#videoImgurl'));
			
			$('#videoIfproorcateorpage').val('0');
			$('.ml-product').removeClass('hide').siblings().addClass('hide');
			$('#videoProid').val('-1');
			$('#videoCateid').val('-1');
			$('#videoPageseoname').val('-1');
			/* $('#slideIfinto').val('0'); */
		}
		// getFormdData
		function getFormData() {
			var data = {};
			data.videoId = parseInt($('#videoId').val());
			data.videoName = $('#videoName').val();
			data.videoArea = parseInt($('#videoArea').val());
			data.videoOrderthNum = $('#videoOrderthNum').val();
			data.videoUrl = $('#videoUrl').val();
			data.videoStatus = $('#videoStatus').prop('checked') ? 1 : 0;

			data.videoImgurl = $('#videoImgurl').attr('data-val') && JSON.parse($('#videoImgurl').attr('data-val')).imageUrl;

			var videoIfproorcateorpage = $('#videoIfproorcateorpage').val();
			data.videoIfproorcateorpage = videoIfproorcateorpage;
			if (videoIfproorcateorpage == 0) {
				data.videoProid = $('#videoProid').val();
				data.videoProname = $('#videoProid').find('option:checked').data('name') || '';
				data.videoSeoname = $('#videoProid').find('option:checked').data('seo') || '';
				data.videoCateid = '';
				data.videoCatename = '';
				data.videoCateseoname = '';
				data.videoPageseoname = '';
			} else if (videoIfproorcateorpage == 1) {
				data.videoProid = '';
				data.videoProname = '';
				data.videoSeoname = '';
				data.videoCateid = $('#videoCateid').val();
				data.videoCatename = $('#videoCateid').find('option:checked').data('name') || '';
				data.videoCateseoname = $('#videoCateid').find('option:checked').data('seo') || '';
				data.videoPageseoname = '';
			} else if (videoIfproorcateorpage == 2) {
				data.videoProid = '';
				data.videoProname = '';
				data.videoSeoname = '';
				data.videoCateid ='';
				data.videoCatename = '';
				data.videoCateseoname = '';
				data.videoPageseoname = $('#videoPageseoname').val();
			}
			/* data.slideIfinto = $('#slideIfinto').val(); */

			return data;
		}
		// initFormData
		function initFormData(data) {
			// initial
			$('#videoProid').val('-1');
			$('#videoCateid').val('-1');
			$('#videoPageseoname').val('-1');
			// init
			$('#videoId').val(data.videoId);
			$('#videoName').val(data.videoName);
			$('#videoArea').val(data.videoArea || '-1');
			$('#videoOrderthNum').val(data.videoOrderthNum ? data.videoOrderthNum : '-1');
			$('#videoUrl').val(data.videoUrl);
			$('#videoStatus').prop('checked', data.videoStatus);
			
			if (data.videoImgurl) {
				addPicture($('#videoImgurl'), {
					imageUrl: data.videoImgurl
				});				
			} else {
				resetPicture($('#videoImgurl'));
			}

			var videoIfproorcateorpage = data.videoIfproorcateorpage;
			$('#videoIfproorcateorpage').val(videoIfproorcateorpage || 0);
			if (videoIfproorcateorpage == 0) {
				$('#videoProid').val(data.videoProid || '-1');
				$('.ml-product').removeClass('hide').siblings().addClass('hide');
			} else if (videoIfproorcateorpage == 1) {
				$('#videoCateid').val(data.videoCateid || '-1');
				$('.ml-category').removeClass('hide').siblings().addClass('hide');
			} else if (videoIfproorcateorpage == 2) {
				$('#videoPageseoname').val(data.videoPageseoname || '-1');
				$('.ml-subject').removeClass('hide').siblings().addClass('hide');
			}
			
			/* $('#slideIfinto').val(data.slideIfinto); */

		}
		// callback get id
		function getVideoId(callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/MlbackVideo/initializaVideo",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				success: function (data) {
					if (data.code == 100) {
						callback && callback(data.extend.mlbackVideo);
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
		function getVideosData() {
			$('.c-mask').show();

			var formData = new FormData();
			formData.append('pn', getPageNum());

			$.ajax({
				url: "${APP_PATH}/MlbackVideo/getMlbackVideoByPage",
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
					toastr.error('Failed to get Video, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback get one data
		function getOneVideoData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackVideo/getOneMlbackVideoDetail",
				type: "post",
				data: JSON.stringify(reqData),
				dataType: 'json',
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend.mlbackVideoOne);
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function () {
					toastr.error('Failed to get Video, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback save
		function saveVideoData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackVideo/save",
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
		function deleteVideoData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackVideo/delete",
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
				if (data[i].videoIfproorcateorpage == 0) {
					logName = '<b>product</b> ' + data[i].videoProid;
					logSeo = data[i].videoSeoname;
				} else if (data[i].videoIfproorcateorpage == 1) {
					logName = '<b>collection</b> ' + data[i].videoCateid;
					logSeo = data[i].videoCateseoname;
				} else if (data[i].videoIfproorcateorpage == 2) {
					logName = '<b>subject</b> ' + data[i].videoPageseoname;
					logSeo = data[i].videoPageseoname;
				}
				htmlStr += '<tr><td>' + data[i].videoId + '</td>' +
					'<td>' + data[i].videoName + '</td>' +
					'<td>' + (data[i].videoImgurl ?
							'<div class="c-table-img"><img src="'+ encodeUrl(data[i].videoImgurl) +'" /></div>'
							: '<div class="c-table-icon"><svg class="c-icon"><use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image1"></use></svg></div>') +
					'</td>' +
					'<td>' + data[i].videoArea + '</td>' +
					'<td>' + data[i].videoOrderthNum + '</td>' +
					'<td>' + data[i].videoUrl + '</td>' +
					'<td>' + (logName || '') + '</td>' +
					'<td>' + (logSeo || '') + '</td>' +
					/* '<td>' + (data[i].slideIfinto ? 'YES' : 'NO') + '</td>' + */
					'<td><a class="badge '+ (data[i].videoStatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].videoStatus ? 'enable' : 'disable') + '</a></td>' +
					'<td>' +
						'<button class="btn btn-primary btn-edit" data-id="' + data[i].videoId + '">' +
							'<svg class="c-icon">' +
								'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-pencil"></use>' +
							'</svg>' +
						'</button>' +
						'<button class="btn btn-danger btn-delete" data-id="' + data[i].videoId + '">' +
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