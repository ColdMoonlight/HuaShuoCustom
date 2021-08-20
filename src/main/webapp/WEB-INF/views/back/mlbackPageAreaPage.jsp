<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Page Area</title>
	<jsp:include page="common/backheader.jsp" flush="true"></jsp:include>
</head>

<body class="c-app">
	<jsp:include page="layout/backheader.jsp" flush="true"></jsp:include>
	<jsp:include page="layout/backsidebar.jsp" flush="true"></jsp:include>
	<div class="c-wrapper">
		<div class="c-body">
			<div class="c-main">
				<div class="c-init">
					<div class="c-option">
						<span class="c-option-title">Page Area</span>
						<button class="btn btn-primary btn-create">Create Page Area</button>
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
									<input id="searchPagearea" type="text" placeholder="Search Page Area by name..." />						
									<select class="supercate-list" id="searchSupercate"></select>
								</div>
								<a class="btn btn-primary input-group-addon btn-save-search">Save search</a>
							</div>
							<div class="c-table-table table-responsive-sm">
								<table class="table">
									<thead>
										<tr>
											<th>id</th>
											<th>name</th>
											<th>seo</th>
											<th>type</th>
											<th>wap-status</th>
											<th>wap-sort</th>
											<th>pc-status</th>
											<th>pc-sort</th>
											<th>supercate</th>
											<th>desc</th>
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
						<span class="c-option-title">Edit Page Area</span>
						<div class="group">
							<button class="btn btn-secondary btn-cancel">Cancel</button>
							<button class="btn btn-primary btn-save">Save Page Area</button>
						</div>
					</div>
					<div class="c-form row">
						<input id="pageareaId" hidden />
						<!-- left panel  -->
						<div class="left-panel col-lg-7 col-md-12">
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">General</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="pageareaName">Name</label>
										<div class="controls">
											<input class="form-control" id="pageareaName" type="text" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="pageareaSeo">SEO</label>
										<div class="controls">
											<input class="form-control" id="pageareaSeo" type="text" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-md-3 col-form-label" for="pageareaStatus">Wap Status</label>
										<div class="controls col-md-3">
											<label class="c-switch c-switch-primary">
												<input class="c-switch-input" id="pageareaStatus" type="checkbox">
												<span class="c-switch-slider"></span>
											</label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="pageareaSort">Wap Sort</label>
										<div class="controls">
											<select class="form-control" id="pageareaSort" />
												<option value="99">Please select page-area wap sort...</option>
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
												<option value="13">13</option>
												<option value="14">14</option>
												<option value="15">15</option>
												<option value="16">16</option>
												<option value="17">17</option>
												<option value="18">18</option>
												<option value="19">19</option>
												<option value="20">20</option>
											</select>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-md-3 col-form-label" for="pageareaPcstatus">Pc Status</label>
										<div class="controls col-md-3">
											<label class="c-switch c-switch-primary">
												<input class="c-switch-input" id="pageareaPcstatus" type="checkbox">
												<span class="c-switch-slider"></span>
											</label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="pageareaPcsort">Pc Sort</label>
										<div class="controls">
											<select class="form-control" id="pageareaPcsort" />
												<option value="99">Please select page-area pc sort...</option>
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
												<option value="13">13</option>
												<option value="14">14</option>
												<option value="15">15</option>
												<option value="16">16</option>
												<option value="17">17</option>
												<option value="18">18</option>
												<option value="19">19</option>
												<option value="20">20</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="pageareaAscription">Description</label>
										<div class="controls">
											<input class="form-control" id="pageareaAscription" type="text" disabled />
										</div>
									</div>
								</div>
							</div>
							
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Type Selection</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="pageareaType">Type</label>
										<div class="controls">
											<select class="form-control" id="pageareaType" />
												<option value="-1">Please page area type...</option>
												<option value="0">轮播</option>
												<option value="1">活动</option>
												<option value="2">产品类</option>
											</select>
										</div>
									</div>
									<div class="form-group ml-category hide">
											<label class="col-form-label" for="pagearea-category">Category</label>
											<div class="controls">
												<select class="form-control collection-list" id="pagearea-category" /></select>
											</div>
										</div>
									<div class="form-group">
										<label class="col-form-label" for="pageAreaDetailList">Details(集合详情)</label>
										<input type="hidden" id="pageareaTypedetailIdstr" />
										<input type="hidden" id="pageareaTypedetail" />
										<div class="controls" style="display: flex;">
											<textarea id="pageAreaDetailList" class="form-control" rows="5" disabled></textarea>
											<button id="pageAreaDetailEdit" class="btn btn-info" style="margin-left: 1rem;">edit</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- right panel  -->
						<div class="right-panel col-lg-5 col-md-12">
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Super Category</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="pageareaSupercateid">Super Category</label>
										<div class="controls">
											<select class="form-control supercate-list" id="pageareaSupercateid" /></select>
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
		<jsp:include page="layout/backfooter.jsp" flush="true"></jsp:include>
	</div>

	<jsp:include page="common/backfooter.jsp" flush="true"></jsp:include>

	<jsp:include page="common/deleteModal.jsp" flush="true"></jsp:include>
	<jsp:include page="common/editModal.jsp" flush="true"></jsp:include>

	<!-- custom script -->
	<script>
		var hasSuperCateList = false;
		var hasCollectionList = false;
		var isCreate = false;
		var storageName = 'pagearea';
		var selectedName = [];
		var selectedId = [];

		if (!hasSuperCateList) getSuperCategoryData(renderSuperCategory);
	 	$('#searchSupercate').val($('#searchSupercate').data('val') || '-1');

		// init
		renderTabItems();
		// save search
		$('.btn-save-search').on('click', function () {
			var searchPageareaVal = {
				supercate: $('#searchSupercate').find('option:selected').text(),
				supercateId: $('#searchSupercate').val(),
				pagearea: $('#searchPagearea').val()
			};
			// cancel repeat add save-search
			if (checkNewItem(searchPageareaVal)) return;
			if (parseInt(searchPageareaVal.supercateId) < 0) searchPageareaVal.supercate = "";
			if (searchPageareaVal.supercate || searchPageareaVal.pagearea) {
				addStorageItem(searchPageareaVal);
				$('.c-table-tab-tempory').html('');
				createTableTabItem(searchPageareaVal);
				addTableTabItem(searchPageareaVal, $('.c-table-tab-item').length);
			}
		});
		// search it
		$('#searchSupercate').on('change', function() {
			$(this).attr('data-val', $(this).val());
			updateSearchData();
		});
		var oldTime = (new Date()).getTime(),
			timer = null;
		$('#searchPagearea').on('keyup', function() {
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
			var searchPageareaVal = {
				supercate: $('#searchSupercate').find('option:selected').text(),
				supercateId: $('#searchSupercate').val(),
				pagearea: $('#searchPagearea').val()
			};
			// inital pagination num
			setPageNum(1);
			// check searchPagearea
			if (parseInt(searchPageareaVal.supercateId) < 0) searchPageareaVal.supercate = "";

			$('.c-table-tab-item.active').removeClass('active');
			$('.c-table-tab-tempory').html(createTableTabItem(searchPageareaVal).addClass('active'));
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
			if (dataVal && (dataVal.pagearea || dataVal.supercate)) {
				$('#searchPagearea').val(dataVal.pagearea || '');
				$('#searchSupercate').attr('data-val', dataVal.supercateId || '-1');
				$('#searchSupercate').val(dataVal.supercateId || '-1');
				getSearchPageareaData();
			} else {
				$('#searchSupercate').val('-1');
				$('#searchPagearea').val('');
				initActiveItemNum();
				getPageAreasData();
			}
		}
		// tab delete
		$(document.body).on('click', '.delete-table-tab-item', deleteTableTabItem);
		// pagination a-click
		$(document.body).on('click', '#table-pagination li', function (e) {
			getTabSearchData($('.c-table-tab-item.active'));
		});
		// create page area
		$('.btn-create').on('click', function () {
			$('.c-create .c-option-title').text('Create Page Area');
			showCreateBlock();
			// init formData
			resetFormData();
			getPageAreaId();
			isCreate = true;
		});
		// edit page area
		$(document.body).on('click', '.btn-edit', function (e) {
			var pageareaId = $(this).data('id');
			getOnePageAreaData({
			 pageareaId: pageareaId
			}, function(resData) {
			 	$('.c-create .c-option-title').text('Edit Page Area');
				showCreateBlock();
				initFormData(resData);
			});	
		});
		// delete page area
		$(document.body).on('click', '.btn-delete', function (e) {
			var pageareaId = parseInt($(this).data('id'));
			$('#deleteModal').find('.modal-title').html('Delete Page Area!');
			$('#deleteModal').modal('show');
			$('#deleteModal .btn-ok').one('click', function () {
				deletePageAreaData({
					pageareaId: pageareaId,
				}, function() {
					getPageAreasData();
				});
			});
		});
		$(window).on('beforeunload', function() {
			var pageareaId = $('#pageareaId').val();
			isCreate && pageareaId && deletePageAreaData({
				pageareaId: pageareaId,
			});
			$('#editModal .select-result').addClass('hide');
		});
		// save page area
		$('.c-create .btn-save').on('click', function () {
			savePageAreaData(getFormData(), function() {
				// redirect tab-active & then search-data
				if (isCreate) {
					isCreate = false;
					initActiveItemNum();
					$('.c-table-tab-item').removeClass('active').eq(0).addClass('active');
				}

				getTabSearchData($('.c-table-tab-item.active'));
				showInitBlock();
				$('#pageareaId').val('');
			});
		});
		// cancel page area save
		$('.c-create .btn-cancel').on('click', function () {
			if (isCreate) {
				isCreate = false;
				/* initActiveItemNum(); */
				// delete null pagearea
				deletePageAreaData({
					pageareaId: $('#pageareaId').val(),
				}, function() {
					console.log("cancel create Page Area");
				});
			}

			showInitBlock();
		});
		// slelect content
		$('#pageAreaDetailEdit').on('click', function() {
			var $eidtModal = $('#editModal');
			var $selectResult = $('#editModal .select-result .value');
			var defaultId = $('#pageareaTypedetailIdstr').val();
			var defaultName = $('#pageareaTypedetail').val();
			var type = $('#pageareaType').val();

			$('#editModal .select-result').removeClass('hide');
			if ($('#pageareaType').val() == '-1') {
				toastr.error('Please select page-area type firstly!!!');
				$('#pageareaType').focus();
				return false;
			}
			
			selectedId = [];
			selectedName = [];
			$selectResult.text('');	

			if (defaultId) {
				selectedId = defaultId.split(',');
				selectedName = defaultName.split(',');
				$selectResult.text(defaultId);
			}

			if (type == '0') {
				$eidtModal.find('.modal-title').text('Select Carousel-item block ...');
				getAllCarouselsData(renderCarouselsData);
			} else if (type == '1') {
				$eidtModal.find('.modal-title').text('Select Activity product block...');
				getAllActivityProductData(renderActivityProductData);
			} else if (type == '2') {
				$eidtModal.find('.modal-title').text('Select Display-area block...');
				getAllDsiplayAreaData(renderDisplayAreaData);
			}

			$eidtModal.modal('show');
		});
		$(document.body).on('click', '#editModal .form-check-input', function() {
			var $this = $(this);
			var id = '' + $this.data('id');
			var name = $this.data('name');
			if ($this.prop('checked')) {
				selectedId.push(id);
				selectedName.push(name);
			} else {
				var idx = selectedId.indexOf(id);
				var namex = selectedName.indexOf(name);
				idx > -1  && selectedId.splice(idx, 1);
				namex > -1  && selectedName.splice(namex, 1);
			}
			$('#editModal .select-result .value').text(selectedId.join(', '));
			$('#editModal .btn-ok').one('click', function() {
				if (selectedId.length && selectedName.length && (selectedId.length == selectedName.length)) {
					$('#pageareaTypedetailIdstr').val(selectedId.join(','));
					$('#pageareaTypedetail').val(selectedName.join(','));
					$('#pageAreaDetailList').val(generatePageAreaDetails(selectedId, selectedName));
				} else {
					console.log('数据错误！！！');
				}
				$('#editModal').modal('hide');
			});
		});
		// status combinewith supercate
		$('#pageareaStatus, #pageareaPcstatus').on('change', function(e) {
			if (parseInt($('#pageareaSupercateid').val()) < 0) {
				toastr.info('Please Select super-category!');
				$('#pageareaSupercateid').focus();
				$('#pageareaStatus, #pageareaPcstatus').prop('checked', false);
			}
			if ($('#pageareaType').val() == '-1') {
				toastr.info('Please Select page-area type!');
				$('#pageareaType').focus();
				$('#pageareaStatus, #pageareaPcstatus').prop('checked', false);
			}
		});
		// supercate cahnge
		$('#pageareaSupercateid').on('change', function() {
			if ($(this).val() == '-1') {
				$('#pageareaStatus, #pageareaPcstatus').prop('checked', false);
			}
		});
		// pagearea type change
		$('#pageareaType').on('change', function(e) {
			$('#pageareaTypedetailIdstr').val('');
			$('#pageareaTypedetail').val('');
			$('#pagearea-category').val('-1');
			if ($(this).val() == -1) {
				$('#pageareaStatus, #pageareaPcstatus').prop('checked', false);
			} else if ($(this).val() == 2) {
				$('.ml-category').removeClass('hide');
				$('#pageAreaDetailEdit').addClass('hide');
			} else {
				$('.ml-category').addClass('hide');
				$('#pageAreaDetailEdit').removeClass('hide');
			}
		});
		// pagearea-category change
		$('#pagearea-category').on('change', function(e) {
			var cagtegoryId = $(this).val();
			var cagegoryName = $(this).find('option:checked').data('name');
			$('#pageareaTypedetailIdstr').val(cagtegoryId);
			$('#pageareaTypedetail').val(cagegoryName);
			$('#pageAreaDetailList').val('* ' + cagtegoryId + ' —— ' + cagegoryName + '\n');
		});
		// edit get all carousel data
		function getAllCarouselsData(callback) {
			$.ajax({
				url: "${APP_PATH }/MlbackSlides/getMlbackSlideDownlist",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				success: function (data) {
					if (data.code == 100) {
						callback && callback(data.extend.mlbackSlideList);
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
		// add to dom for eidt-modal eg: carousel
		function renderCarouselsData(data) {
			var htmlStr = '';
			for (var i = 0, len = data.length; i < len; i += 1) {
				var carouselId = data[i].slideId;
				var carouselName = data[i].slideName;
				var logName = '', logSeo = '';
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
				htmlStr += '<div class="page-area-item"><div class="form-check checkbox">' +
						'<input class="form-check-input" id="'+ carouselId +'" type="checkbox"'+ (selectedId.indexOf('' + carouselId) > -1 ? ' checked' : '') +' data-name="'+ carouselName +'" data-id="'+ carouselId +'">' +
						'<label class="form-check-label" for="'+ carouselId +'">'+
							'<span class="table-th">'+ carouselId +'</span>' +
							'<span class="table-th">'+ carouselName +'</span>' +
							'<span class="table-th">'+ data[i].slideArea +'</span>' +
							'<span class="table-th">'+ data[i].slideFirthNum +'</span>' +
							'<span class="table-th">'+ logName +'</span>' +
							'<span class="table-th">'+ logSeo +'</span>' +
							'<span class="table-th">'+ (data[i].slideIfinto ? 'YES' : 'NO') +'</span>' +
							'<span class="table-th">'+ (data[i].slideWapimgurl ?
									'<div class="c-table-img"><img src="'+ encodeUrl(data[i].slideWapimgurl) +'" /></div>'
									: '<div class="c-table-icon"><svg class="c-icon"><use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image1"></use></svg></div>') +
							'</span>' +
							'<span class="table-th"><a class="badge '+ (data[i].slideWapstatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].slideWapstatus ? 'enable' : 'disable') + '</a></span>' +
							'<span class="table-th">'+ (data[i].slidePcimgurl ?
									'<div class="c-table-img"><img src="'+ encodeUrl(data[i].slidePcimgurl) +'" /></div>'
									: '<div class="c-table-icon"><svg class="c-icon"><use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image1"></use></svg></div>') +
							'</span>' +
							'<span class="table-th"><a class="badge '+ (data[i].slidePcstatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].slidePcstatus ? 'enable' : 'disable') + '</a></span>' +
						'</label>' +
					'</div></div>';
			}
			$('#editModal .modal-body-body').html(htmlStr);
			$('#editModal .spinner').hide();
		}
		// get all display-area data
		function getAllActivityProductData(callback) {
			$.ajax({
				url: "${APP_PATH }/MlbackActShowPro/getMlbackActShowProDownlist",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				success: function (data) {
					if (data.code == 100) {
						callback && callback(data.extend.mlbackActShowProList);
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
		// add to dom for edit-modal eg: display-area
		function renderActivityProductData(data) {
			var htmlStr = '';
			for (var i = 0, len = data.length; i < len; i += 1) {
				var actshowproId = data[i].actshowproId;
				var actshowproName = data[i].actshowproName;
				var logName = '', logSeo = '';
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
				htmlStr += '<div class="page-area-item"><div class="form-check checkbox">' +
						'<input class="form-check-input" id="'+ actshowproId +'" type="checkbox"'+ (selectedId.indexOf('' + actshowproId) > -1 ? ' checked' : '') +' value="" data-id="'+ actshowproId +'" data-name="'+ actshowproName +'">' +
						'<label class="form-check-label" for="'+ actshowproId +'">'+
							'<span class="table-th">'+ actshowproId +'</span>' +
							'<span class="table-th">'+ actshowproName +'</span>' +
							'<span class="table-th">'+ data[i].actshowproActnum +'</span>' +
							'<span class="table-th">'+ data[i].actshowproOrderth +'</span>' +
							'<span class="table-th">'+ logName +'</span>' +
							'<span class="table-th">'+ logSeo +'</span>' +
							'<span class="table-th">'+ (data[i].slideIfinto ? 'YES' : 'NO') +'</span>' +
							'<span class="table-th">'+ (data[i].actshowproImgwapurl ?
									'<div class="c-table-img"><img src="'+ encodeUrl(data[i].actshowproImgwapurl) +'" /></div>'
									: '<div class="c-table-icon"><svg class="c-icon"><use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image1"></use></svg></div>') +
							'</span>' +
							'<span class="table-th">'+ (data[i].actshowproImgpcurl ?
									'<div class="c-table-img"><img src="'+ encodeUrl(data[i].actshowproImgpcurl) +'" /></div>'
									: '<div class="c-table-icon"><svg class="c-icon"><use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image1"></use></svg></div>') +
							'</span>' +
							'<span class="table-th"><a class="badge '+ (data[i].actshowproStatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].actshowproStatus ? 'enable' : 'disable') + '</a></span>' +
						'</label>' +
					'</div></div>';
			}
			$('#editModal .modal-body-body').html(htmlStr);
			$('#editModal .spinner').hide();
		}
		// get all activity-product data
		function getAllDisplayAreaData(callback) {
			$.ajax({
				url: "${APP_PATH }/MlbackActShowPro/getMlbackActShowProDownlist",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				success: function (data) {
					if (data.code == 100) {
						callback && callback(data.extend.mlbackActShowProList);
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
		// add to dom for edit-modal eg: activity-product 
		function renderDisplayAreaData(data) {
			var htmlStr = '';
			for (var i = 0, len = data.length; i < len; i += 1) {
				var carouselId = data[i].slideId;
				var logName = '', logSeo = '';
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
				htmlStr += '<div class="page-area-item"><div class="form-check checkbox">' +
						'<input class="form-check-input" id="'+ carouselId +'" type="checkbox"'+ (selectedId.indexOf('' + carouselId) > -1 ? ' checked' : '') +' value="" data-id="'+ carouselId +'">' +
						'<label class="form-check-label" for="'+ carouselId +'">'+
							'<span class="table-th">'+ carouselId +'</span>' +
							'<span class="table-th">'+ data[i].slideName +'</span>' +
							'<span class="table-th">'+ data[i].slideArea +'</span>' +
							'<span class="table-th">'+ data[i].slideFirthNum +'</span>' +
							'<span class="table-th">'+ logName +'</span>' +
							'<span class="table-th">'+ logSeo +'</span>' +
							'<span class="table-th">'+ (data[i].slideIfinto ? 'YES' : 'NO') +'</span>' +
							'<span class="table-th">'+ (data[i].slideWapimgurl ?
									'<div class="c-table-img"><img src="'+ encodeUrl(data[i].slideWapimgurl) +'" /></div>'
									: '<div class="c-table-icon"><svg class="c-icon"><use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image1"></use></svg></div>') +
							'</span>' +
							'<span class="table-th"><a class="badge '+ (data[i].slideWapstatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].slideWapstatus ? 'enable' : 'disable') + '</a></span>' +
							'<span class="table-th">'+ (data[i].slidePcimgurl ?
									'<div class="c-table-img"><img src="'+ encodeUrl(data[i].slidePcimgurl) +'" /></div>'
									: '<div class="c-table-icon"><svg class="c-icon"><use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image1"></use></svg></div>') +
							'</span>' +
							'<span class="table-th"><a class="badge '+ (data[i].slidePcstatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].slidePcstatus ? 'enable' : 'disable') + '</a></span>' +
						'</label>' +
					'</div></div>';
			}
			$('#editModal .modal-body-body').html(htmlStr);
			$('#editModal .spinner').hide();
		}
		function showCreateBlock() {
			$('.c-init').addClass('hide');
			$('.c-create').removeClass('hide');
			if (!hasCollectionList) getAllCollectionData(renderAllValidCollection);
		}
		function showInitBlock() {
			$('.c-init').removeClass('hide');
			$('.c-create').addClass('hide');
		}
		// handle formData
		function generatePageAreaDetails(ids, names) {
			var htmlStr = '';
			if (ids.length && names.length) {
				ids.forEach(function(id, index) {
					htmlStr += '* ' + id + ' —— ' + names[index] + '\n';
				});
			}
			return htmlStr;
		}
		// reset data
		function resetFormData() {
			$('#pageareaId').val('');
			$('#pageareaName').val('');
			$('#pageareaSeo').val('');
			$('#pageareaStatus').prop('checked', false)
			$('#pageareaSort').val('-1');
			$('#pageareaPcstatus').prop('checked', false)
			$('#pageareaPcsort').val('-1');

			$('#pageareaType').val('-1');
			$('.ml-category').addClass('hide');
			$('#pagearea-category').val('-1');
			$('#pageAreaDetailEdit').removeClass('hide');
			
			$('#pageareaTypedetailIdstr').val('');
			$('#pageareaTypedetail').val('');
			$('#pageAreaDetailList').val('');

			$('#pageareaAscription').val('');

			$('#pageareaSupercateid').val('-1');
		}
		// getFormdData
		function getFormData() {
			var data = {};
			data.pageareaId = parseInt($('#pageareaId').val());
			data.pageareaName = $('#pageareaName').val();
			data.pageareaSeo = $('#pageareaSeo').val();
			data.pageareaStatus = $('#pageareaStatus').prop('checked') ? 1 : 0;
			data.pageareaSort = $('#pageareaSort').val();
			data.pageareaPcstatus = $('#pageareaPcstatus').prop('checked') ? 1 : 0;
			data.pageareaPcsort = $('#pageareaPcsort').val();

			data.pageareaType = $('#pageareaType').val();
			data.pageareaTypedetailIdstr = $('#pageareaTypedetailIdstr').val();
			data.pageareaTypedetail = $('#pageareaTypedetail').val();
			data.pageAreaDetailList = $('#pageAreaDetailList').val();

			data.pageareaAscription = $('#pageareaAscription').val();

			data.pageareaSupercateid = $('#pageareaSupercateid').val();
			data.pageareaSupercatename = $('#pageareaSupercateid').find('option:selected').text();

			return data;
		}
		// initFormData
		function initFormData(data) {
			// init
			$('#pageareaId').val(data.pageareaId);
			$('#pageareaName').val(data.pageareaName);
			$('#pageareaSeo').val(data.pageareaSeo);
			$('#pageareaStatus').prop('checked', data.pageareaStatus);
			$('#pageareaSort').val(data.pageareaSort ? data.pageareaSort : '-1');
			$('#pageareaPcstatus').prop('checked', data.pageareaPcstatus);
			$('#pageareaPcsort').val(data.pageareaPcsort ? data.pageareaPcsort : '-1');

			var pageareaType = data.pageareaType;			
			var pageAreaDetailsIdStr = data.pageareaTypedetailIdstr;
			var pageAreaDetails = data.pageareaTypedetail;

			if (pageareaType == 2) {
				$('.ml-category').removeClass('hide')
				$('#pagearea-category').val(pageAreaDetailsIdStr);
				$('#pageAreaDetailEdit').addClass('hide');
			} else {
				$('.ml-category').addClass('hide');
				$('#pageAreaDetailEdit').removeClass('hide');
			}
			$('#pageareaType').val(data.pageareaType);
			$('#pageareaTypedetailIdstr').val(pageAreaDetailsIdStr);
			$('#pageareaTypedetail').val(pageAreaDetails);
			$('#pageAreaDetailList').val(pageAreaDetails && pageAreaDetails && generatePageAreaDetails(pageAreaDetailsIdStr.split(','), pageAreaDetails.split(',')));
			
			$('#pageareaAscription').val(data.pageareaAscription);
			
			$('#pageareaSupercateid').val(data.pageareaSupercateid || '-1');
		}
		// callback get id
		function getPageAreaId() {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/MlbackPageArea/initializaPageArea",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				success: function (data) {
					if (data.code == 100) {
						var pageareaId = data.extend && data.extend.mlbackPageArea && data.extend.mlbackPageArea.pageareaId;
						if (pageareaId) {
							$('#pageareaId').val(pageareaId);
							toastr.success(data.extend.resMsg);
						} else {
							toastr.error('create page-area fail! Please try again.');
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
		function getPageAreasData() {
			$('.c-mask').show();

			var formData = new FormData();
			formData.append('pn', getPageNum());

			$.ajax({
				url: "${APP_PATH}/MlbackPageArea/getMlbackPageAreaByPage",
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
					toastr.error('Failed to get Page Area, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback get one data
		function getOnePageAreaData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackPageArea/getOneMlbackPageAreaDetail",
				type: "post",
				data: JSON.stringify(reqData),
				dataType: 'json',
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend.mlbackPageAreaOne);
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function () {
					toastr.error('Failed to get Page Area, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback get search data
		function getSearchPageareaData(data) {
			$('.c-mask').show();

			var formData = new FormData();
			formData.append('pageareaName', $('#searchPagearea').val());
			formData.append('pageareaSupercateid', ($('#searchSupercate').attr('data-val') || '-1'));
			formData.append('pn', getPageNum());

			$.ajax({
				url: "${APP_PATH}/MlbackPageArea/backSearchByPageArea",
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
					toastr.error('Failed to get Page Area, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback save
		function savePageAreaData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackPageArea/save",
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
		function deletePageAreaData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackPageArea/delete",
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
			function getType(type) {
				var res = '';
				switch(type) {
					case 0: {
						res = '轮播';
						break;
					}
					case 1: {
						res = '活动';
						break;
					}
					case 2: {
						res = '产品类';
						break;
					}
					default: {
						res = '***';
						break;
					}
				}
				return res;
			}
			var htmlStr = '';
			for (var i = 0, len = data.length; i < len; i += 1) {
				htmlStr += '<tr><td>' + data[i].pageareaId + '</td>' +
					'<td>' + data[i].pageareaName + '</td>' +
					'<td>' + data[i].pageareaSeo + '</td>' +
					'<td>' + (getType(data[i].pageareaType)) + '</td>' +
					'<td><a class="badge '+ (data[i].pageareaStatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].pageareaStatus ? 'enable' : 'disable') + '</a></td>' +
					'<td>' + (data[i].pageareaSort == 99 ? '--' : data[i].pageareaSort) + '</td>' +
					'<td><a class="badge '+ (data[i].pageareaPcstatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].pageareaPcstatus ? 'enable' : 'disable') + '</a></td>' +
					'<td>' + (data[i].pageareaPcsort == 99 ? '--' : data[i].pageareaPcsort) + '</td>' +
					'<td>' + data[i].pageareaSupercatename + '</td>' +
					'<td>' + data[i].pageareaAscription + '</td>' +
					'<td>' +
						'<button class="btn btn-primary btn-edit" data-id="' + data[i].pageareaId + '">' +
							'<svg class="c-icon">' +
								'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-pencil"></use>' +
							'</svg>' +
						'</button>' +
						'<button class="btn btn-danger btn-delete" data-id="' + data[i].pageareaId + '">' +
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