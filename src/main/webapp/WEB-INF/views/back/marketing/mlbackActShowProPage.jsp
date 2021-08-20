<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Activity Product</title>
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
						<span class="c-option-title">Activity Product</span>
						<button class="btn btn-primary btn-create">Create Activity Product</button>
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
											<th>area</th>
											<th>order</th>
											<th>product/subject/colleciton</th>
											<th>seo</th>
											<!-- <th>ifInto</th> -->
											<th>wap-image</th>
											<th>pc-image</th>
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
						<span class="c-option-title">Edit Activity Product</span>
						<div class="group">
							<button class="btn btn-secondary btn-cancel">Cancel</button>
							<button class="btn btn-primary btn-save">Save Activity-Product</button>
						</div>
					</div>
					<div class="c-form row">
						<input id="actshowproId" hidden>
						<!-- left panel  -->
						<div class="left-panel col-lg-7 col-md-12">
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">General</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="actshowproName">Activity-Product Name</label>
										<div class="controls">
											<input class="form-control" id="actshowproName" type="text" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="actshowproActnum">Area</label>
										<div class="controls">
											<select class="form-control" id="actshowproActnum" />
												<option value="-1">Please select the location of the Activity-Product</option>
												<option value="1">首页主推</option>
												<option value="2">第2活动</option>
												<option value="3">第3活动</option>
												<option value="4">第4活动</option>
												<option value="5">第5活动</option>
												<option value="6">第6-首页中间adv_two</option>
												<option value="7">第7-首adv_four</option>
												<option value="8">第8-首页分类导航下边2个图</option>
												<option value="9">第9-首页banner下边分类导航5个图</option>
												<option value="10">第10-自由配置总归属</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="actshowproOrderth">Activity-Product Sort</label>
										<div class="controls">
											<select class="form-control" id="actshowproOrderth" />
												<option value="-1">Please select Activity-Product sort-order</option>
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
										<label class="col-md-3 col-form-label" for="actshowproStatus">Status</label>
										<div class="controls col-md-3">
											<label class="c-switch c-switch-primary">
												<input class="c-switch-input" id="actshowproStatus" type="checkbox">
												<span class="c-switch-slider"></span>
											</label>
										</div>
									</div>
								</div>
							</div>
							<!-- product media -->
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Activity-Product Image</div>
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
												<input id="actWap" type="file" accept="image/png, image/jpeg, image/gif" />										
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
												<input id="actPc" type="file" accept="image/png, image/jpeg, image/gif" />										
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
										<label class="col-form-label" for="actshowproIfproorcate">Product Or Subject Or Collection</label>
										<div class="controls">
											<select class="form-control" id="actshowproIfproorcate" />
												<option value="0" data-class="ml-product">product</option>
												<option value="1" data-class="ml-category">collection</option>
												<option value="2" data-class="ml-subject">subject</option>
											</select>
										</div>
									</div>
									<div>
										<div class="form-group ml-product">
											<label class="col-form-label" for="actshowproProid">Product</label>
											<div class="controls">
												<select class="form-control product-list" id="actshowproProid" /></select>
											</div>
										</div>
										<div class="form-group ml-category hide">
											<label class="col-form-label" for="actshowproCateid">Category</label>
											<div class="controls">
												<select class="form-control collection-list" id="actshowproCateid" /></select>
											</div>
										</div>	
										<div class="form-group ml-subject hide">
											<label class="col-form-label" for="actshowproPageseoname">Subject</label>
											<div class="controls">
												<select class="form-control" id="actshowproPageseoname">
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
		var isCreate = false;

		$('#actshowproIfproorcate').on('change', function() {
			$('.' + $(this).find('option:checked').data('class')).removeClass('hide').siblings().addClass('hide').find('select').val('-1');
		});

		// init
		getActivityProductsData()
		// create collection
		$('.btn-create').on('click', function () {
			$('.c-create .c-option-title').text('Create Activity-Product');

			getActivityProductId(function(data) {
				// init formData
				resetFormData();
				$('#actshowproId').val(data.actshowproId);
				showCreateBlock();
				isCreate = true;
			});
		});
		// edit collection
		$(document.body).on('click', '.btn-edit', function (e) {
			var actshowproId = $(this).data('id');
			getOneActivityProductData({
				actshowproId: actshowproId
			}, function(resData) {
			 	$('.c-create .c-option-title').text('Edit Activity-Product');
				showCreateBlock();
				initFormData(resData);
			});			
		});
		// delete collection
		$(document.body).on('click', '.btn-delete', function (e) {
			var actshowproId = parseInt($(this).data('id'));
			$('#deleteModal').find('.modal-title').html('Delete Activity-Product!');
			$('#deleteModal').modal('show');
			$('#deleteModal .btn-ok').one('click', function () {
				deleteActivityProductData({
					actshowproId: actshowproId,
				}, function() {
					getActivityProductsData();
				});
			});
		});
		$(window).on('beforeunload', function() {
			var actshowproId = $('#actshowproId').val();
			isCreate && actshowproId && deleteActivityProductData({
				actshowproId: actshowproId,
			});
		});
		// upload picture
		$('#actPc').on('change', function(e) {
			var $this = $(this);
			var file = $this[0].files[0];
			var formData = new FormData();

			if (!file) return false;

			$this.parent().find('.spinner').show();
			$this.val('');

			formData.append('type', 'actShowProPc');
			formData.append('image', file);
			formData.append('actshowproId', parseInt($('#actshowproId').val()));

			$.ajax({
				url: "${APP_PATH}/ImageUpload/actShowProPc",
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
		$('#actWap').on('change', function(e) {
			var $this = $(this);
			var file = $this[0].files[0];
			var formData = new FormData();

			if (!file) return false;

			$this.parent().find('.spinner').show();
			$this.val('');

			formData.append('type', 'actShowProWap');
			formData.append('image', file);
			formData.append('actshowproId', parseInt($('#actshowproId').val()));

			$.ajax({
				url: "${APP_PATH}/ImageUpload/actShowProWap",
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
			saveActivityProductData(getFormData(), function() {
				// redirect tab-active & then search-data
				if (isCreate) isCreate = false;

				getActivityProductsData();
				showInitBlock();
				$('#actshowproId').val('');
			});
		});
		// cancel collection save
		$('.c-create .btn-cancel').on('click', function () {
			if (isCreate) {
				isCreate = false;
				// delete null Activity-Product
				deleteActivityProductData({
					actshowproId: $('#actshowproId').val(),
				}, function() {
					console.log("cancel create Activity-Product");
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
			$('#actshowproId').val('');
			$('#actshowproName').val('');
			$('#actshowproActnum').val('-1');
			$('#actshowproOrderth').val('-1');
			$('#actshowproStatus').prop('checked', false);

			resetPicture($('#actWap'));
			resetPicture($('#actPc'));
			
			$('#actshowproIfproorcate').val('0');
			$('.ml-product').removeClass('hide').siblings().addClass('hide');
			$('#actshowproProid').val('-1');
			$('#actshowproCateid').val('-1');
			$('#actshowproPageseoname').val('-1');
			/* $('#slideIfinto').val('0'); */
		}
		// getFormdData
		function getFormData() {
			var data = {};
			data.actshowproId = parseInt($('#actshowproId').val());
			data.actshowproName = $('#actshowproName').val();
			data.actshowproActnum = parseInt($('#actshowproActnum').val());
			data.actshowproOrderth = $('#actshowproOrderth').val();
			data.actshowproStatus = $('#actshowproStatus').prop('checked') ? 1 : 0;

			data.actshowproImgwapurl = $('#actWap').attr('data-val') && JSON.parse($('#actWap').attr('data-val')).imageUrl;
			data.actshowproImgpcurl = $('#actPc').attr('data-val') && JSON.parse($('#actPc').attr('data-val')).imageUrl;

			var actshowproIfproorcate = $('#actshowproIfproorcate').val();
			data.actshowproIfproorcate = actshowproIfproorcate;
			if (actshowproIfproorcate == 0) {
				data.actshowproProid = $('#actshowproProid').val();
				data.actshowproProname = $('#actshowproProid').find('option:checked').data('name') || '';
				data.actshowproSeoname = $('#actshowproProid').find('option:checked').data('seo') || '';
				data.actshowproCateid = '';
				data.actshowproCatename = '';
				data.actshowproCateseoname = '';
				data.actshowproPageseoname = '';
			} else if (actshowproIfproorcate == 1) {
				data.actshowproProid = '';
				data.actshowproProname = '';
				data.actshowproSeoname = '';
				data.actshowproCateid = $('#actshowproCateid').val();
				data.actshowproCatename = $('#actshowproCateid').find('option:checked').data('name') || '';
				data.actshowproCateseoname = $('#actshowproCateid').find('option:checked').data('seo') || '';
				data.actshowproPageseoname = '';
			} else if (actshowproIfproorcate == 2) {
				data.actshowproProid = '';
				data.actshowproProname = '';
				data.actshowproSeoname = '';
				data.actshowproCateid ='';
				data.actshowproCatename = '';
				data.actshowproCateseoname = '';
				data.actshowproPageseoname = $('#actshowproPageseoname').val();
			}
			/* data.slideIfinto = $('#slideIfinto').val(); */

			return data;
		}
		// initFormData
		function initFormData(data) {
			// initial
			$('#actshowproProid').val('-1');
			$('#actshowproCateid').val('-1');
			$('#actshowproPageseoname').val('-1');
			// init
			$('#actshowproId').val(data.actshowproId);
			$('#actshowproName').val(data.actshowproName);
			$('#actshowproActnum').val(data.actshowproActnum || '-1');
			$('#actshowproOrderth').val(data.actshowproOrderth ? data.actshowproOrderth : '-1');
			$('#actshowproStatus').prop('checked', data.actshowproStatus);
			
			if (data.actshowproImgwapurl) {
				addPicture($('#actWap'), {
					imageUrl: data.actshowproImgwapurl
				});				
			} else {
				resetPicture($('#actWap'));
			}

			if (data.actshowproImgpcurl) {
				addPicture($('#actPc'), {
					imageUrl: data.actshowproImgpcurl
				});				
			} else {
				resetPicture($('#actPc'));
			}

			var actshowproIfproorcate = data.actshowproIfproorcate;
			$('#actshowproIfproorcate').val(actshowproIfproorcate || 0);
			if (actshowproIfproorcate == 0) {
				$('#actshowproProid').val(data.actshowproProid || '-1');
				$('.ml-product').removeClass('hide').siblings().addClass('hide');
			} else if (actshowproIfproorcate == 1) {
				$('#actshowproCateid').val(data.actshowproCateid || '-1');
				$('.ml-category').removeClass('hide').siblings().addClass('hide');
			} else if (actshowproIfproorcate == 2) {
				$('#actshowproPageseoname').val(data.actshowproPageseoname || '-1');
				$('.ml-subject').removeClass('hide').siblings().addClass('hide');
			}
			
			/* $('#slideIfinto').val(data.slideIfinto); */

		}
		// callback get id
		function getActivityProductId(callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/MlbackActShowPro/initializaActShowPro",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				success: function (data) {
					if (data.code == 100) {
						callback && callback(data.extend.mlbackActShowPro)
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
		function getActivityProductsData() {
			$('.c-mask').show();

			var formData = new FormData();
			formData.append('pn', getPageNum());

			$.ajax({
				url: "${APP_PATH}/MlbackActShowPro/getMlbackActShowProByPage",
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
					toastr.error('Failed to get ActivityProduct, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback get one data
		function getOneActivityProductData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackActShowPro/getOneMlbackActShowProDetail",
				type: "post",
				data: JSON.stringify(reqData),
				dataType: 'json',
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend.mlbackActShowProOne);
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function () {
					toastr.error('Failed to get ActivityProduct, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback save
		function saveActivityProductData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackActShowPro/save",
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
		function deleteActivityProductData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackActShowPro/delete",
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
				if (data[i].actshowproIfproorcate == 0) {
					logName = '<b>product</b> ' + data[i].actshowproProid;
					logSeo = data[i].actshowproSeoname;
				} else if (data[i].actshowproIfproorcate == 1) {
					logName = '<b>collection</b> ' + data[i].actshowproCateid;
					logSeo = data[i].actshowproCateseoname;
				} else if (data[i].actshowproIfproorcate == 2) {
					logName = '<b>subject</b> ' + data[i].actshowproPageseoname;
					logSeo = data[i].actshowproPageseoname;
				}
				htmlStr += '<tr><td>' + data[i].actshowproId + '</td>' +
					'<td>' + data[i].actshowproName + '</td>' +
					'<td>' + data[i].actshowproActnum + '</td>' +
					'<td>' + data[i].actshowproOrderth + '</td>' +
					'<td>' + (logName || '') + '</td>' +
					'<td>' + (logSeo || '') + '</td>' +
					/* '<td>' + (data[i].slideIfinto ? 'YES' : 'NO') + '</td>' + */
					'<td>' + (data[i].actshowproImgwapurl ?
							'<div class="c-table-img"><img src="'+ encodeUrl(data[i].actshowproImgwapurl) +'" /></div>'
							: '<div class="c-table-icon"><svg class="c-icon"><use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image1"></use></svg></div>') +
					'</td>' +
					'<td>' + (data[i].actshowproImgpcurl ?
							'<div class="c-table-img"><img src="'+ encodeUrl(data[i].actshowproImgpcurl) +'" /></div>'
							: '<div class="c-table-icon"><svg class="c-icon"><use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image1"></use></svg></div>') +
					'</td>' +
					'<td><a class="badge '+ (data[i].actshowproStatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].actshowproStatus ? 'enable' : 'disable') + '</a></td>' +
					'<td>' +
						'<button class="btn btn-primary btn-edit" data-id="' + data[i].actshowproId + '">' +
							'<svg class="c-icon">' +
								'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-pencil"></use>' +
							'</svg>' +
						'</button>' +
						'<button class="btn btn-danger btn-delete" data-id="' + data[i].actshowproId + '">' +
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