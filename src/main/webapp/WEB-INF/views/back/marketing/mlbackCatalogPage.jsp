<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Marketing</title>
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
						<span class="c-option-title">Marketing</span>
						<button class="btn btn-primary btn-create">Create CateLog</button>
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
									<input id="searchCatalog" type="text" placeholder="Search CateLog">						
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
											<th>parent-id</th>
											<th>parent-name</th>
											<th>tag</th>
											<th>order</th>
											<th>product/subject/colleciton</th>
											<th>seo</th>
											<th>ifInto</th>
											<th>status</th>
											<th>path-desc</th>
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
						<span class="c-option-title">Edit CateLog</span>
						<div class="group">
							<button class="btn btn-secondary btn-cancel">Cancel</button>
							<button class="btn btn-primary btn-save">Save CateLog</button>
						</div>
					</div>
					<div class="c-form row">
						<input id="catalogId" hidden>
						<!-- left panel  -->
						<div class="left-panel col-lg-7 col-md-12">
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">General</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="catalogName">CateLog Name</label>
										<div class="controls">
											<input class="form-control" id="catalogName" type="text" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="catalogFirthNum">Sort</label>
										<div class="controls">
											<select class="form-control" id="catalogFirthNum" />
												<option value="-1">Please select CateLog sort-order</option>
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
										<label class="col-md-3 col-form-label" for="catalogStatus">Status</label>
										<div class="controls col-md-3">
											<label class="c-switch c-switch-primary">
												<input class="c-switch-input" id="catalogStatus" type="checkbox">
												<span class="c-switch-slider"></span>
											</label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="catalogLable">Label</label>
										<div class="controls">
											<select class="form-control" id="catalogLable" />
												<option value="0" selected="selected">0-无图</option>
												<option value="1">1 - hot</option>
												<option value="2">2 - new</option>
												<option value="3">3 - 62% off</option>
												<option value="4">4 - 65% off</option>
												<option value="5">5 - ins</option>
												<option value="6">6 - review</option>
												<option value="7">7 - video</option>
												<option value="8">8 - Best Quality</option>
												<option value="9">9 - 15A</option>
												<option value="10">10 - hot-test</option>
											</select>
										</div>
										<i>useless: 5-ins, 6-reivew, 7-video, 8-beset quality</i>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="catalogDesc">Description</label>
										<div class="controls">
											<input class="form-control" id="catalogDesc" type="text" disabled />
										</div>
									</div>
								</div>
							</div>
							<!-- product or subject -->
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Product & Subject & Collection</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="catalogIfproorcateorpage">Product Or Subject Or Collection</label>
										<div class="controls">
											<select class="form-control" id="catalogIfproorcateorpage" />
												<option value="0" data-class="ml-product">product</option>
												<option value="1" data-class="ml-category">collection</option>
												<option value="2" data-class="ml-subject">subject</option>
											</select>
										</div>
									</div>
									<div>
										<div class="form-group ml-product">
											<label class="col-form-label" for="catalogProid">Product</label>
											<div class="controls">
												<select class="form-control product-list" id="catalogProid" /></select>
											</div>
										</div>
										<div class="form-group ml-category hide">
											<label class="col-form-label" for="catalogCateid">Category</label>
											<div class="controls">
												<select class="form-control collection-list" id="catalogCateid" /></select>
											</div>
										</div>	
										<div class="form-group ml-subject hide">
											<label class="col-form-label" for="catalogPageseoname">Subject</label>
											<div class="controls">
												<select class="form-control" id="catalogPageseoname">
													<jsp:include page="../layout/subject-option.jsp" flush="true"></jsp:include>
												</select>
											</div>
										</div>								
									</div>
									<div class="form-group">
										<label class="col-form-label" for=catalogIfinto>click entry or not</label>
										<div class="controls">
											<select class="form-control" id="catalogIfinto" />
												<option value="0" selected="selected">disable</option>
					  							<option value="1">enalbe</option>
											</select>
										</div>
									</div>	
									
								</div>
							</div>
						</div>
						<!-- right panel  -->
						<div class="right-panel col-lg-5 col-md-12">
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Super Category & Parent CateLog</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="catalogSupercateId">Super Category</label>
										<div class="controls">
											<select class="form-control supercate-list" id="catalogSupercateId" /></select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="catalogParentId">Parent CateLog</label>
										<div class="controls">
											<select class="form-control" id="catalogParentId" /></select>
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
		var hasSuperCateList = false;
		var hasCollectionList = false;
		var hasProductList = false;
		var isCreate = false;
		var storageName = 'marketing';

		$('#catalogIfproorcateorpage').on('change', function() {
			$('.' + $(this).find('option:checked').data('class')).removeClass('hide').siblings().addClass('hide').find('select').val('-1');
		});

		if (!hasSuperCateList) getSuperCategoryData(renderSuperCategory);
	 	$('#searchSupercate').val($('#searchSupercate').data('val') || '-1');

		// init
		renderTabItems();
		// save search
		$('.btn-save-search').on('click', function () {
			var searchCollectionVal = {
				supercate: $('#searchSupercate').find('option:selected').text(),
				supercateId: $('#searchSupercate').val(),
				collection: $('#searchCatalog').val()
			};
			// cancel repeat add save-search
			if (checkNewItem(searchCollectionVal)) return;
			if (parseInt(searchCollectionVal.supercateId) < 0) searchCollectionVal.supercate = "";
			if (searchCollectionVal.supercate || searchCollectionVal.collection) {
				addStorageItem(searchCollectionVal);
				$('.c-table-tab-tempory').html('');
				createTableTabItem(searchCollectionVal);
				addTableTabItem(searchCollectionVal, $('.c-table-tab-item').length);
			}
		});
		// search it
		$('#searchSupercate').on('change', function() {
			$(this).attr('data-val', $(this).val());
			updateSearchData();
		});
		var oldTime = (new Date()).getTime(),
			timer = null;
		$('#searchCatalog').on('keyup', function() {
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
			var searchCollectionVal = {
				supercate: $('#searchSupercate').find('option:selected').text(),
				supercateId: $('#searchSupercate').val(),
				collection: $('#searchCatalog').val()
			};
			// inital pagination num
			setPageNum(1);
			// check searchCollection
			if (parseInt(searchCollectionVal.supercateId) < 0) searchCollectionVal.supercate = "";

			$('.c-table-tab-item.active').removeClass('active');
			$('.c-table-tab-tempory').html(createTableTabItem(searchCollectionVal).addClass('active'));
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
			if (dataVal && (dataVal.collection || dataVal.supercate)) {
				$('#searchCatalog').val(dataVal.collection || '');
				$('#searchSupercate').attr('data-val', dataVal.supercateId || '-1');
				$('#searchSupercate').val(dataVal.supercateId || '-1');
				getSearchMarkegingsData();
			} else {
				$('#searchSupercate').val('-1');
				$('#searchCatalog').val('');
				initActiveItemNum();
				getMarketingsData();
			}
		}
		// tab delete
		$(document.body).on('click', '.delete-table-tab-item', deleteTableTabItem);
		// pagination a-click
		$(document.body).on('click', '#table-pagination li', function (e) {
			getTabSearchData($('.c-table-tab-item.active'));
		});
		// create collection
		$('.btn-create').on('click', function () {
			$('.c-create .c-option-title').text('Create CateLog');
			showCreateBlock();
			// init formData
			resetFormData();
			getMarketingId();
			isCreate = true;
		});
		// edit collection
		$(document.body).on('click', '.btn-edit', function (e) {
			var catalogId = $(this).data('id');
			getOneMarketingData({
			 catalogId: catalogId
			}, function(resData) {
			 	$('.c-create .c-option-title').text('Edit CateLog');
				showCreateBlock();
				initFormData(resData);
			});			
		});
		// delete collection
		$(document.body).on('click', '.btn-delete', function (e) {
			var catalogId = parseInt($(this).data('id'));
			$('#deleteModal').find('.modal-title').html('Delete CateLog!');
			$('#deleteModal').modal('show');
			$('#deleteModal .btn-ok').one('click', function () {
				deleteMarketingData({
					catalogId: catalogId,
				}, function() {
					getMarketingsData();
					// update parentCategory data
					getParentMarketingData(renderParentMarketing);
				});
			});
		});
		$(window).on('beforeunload', function() {
			var catalogId = $('#catalogId').val();
			isCreate && catalogId && deleteMarketingData({
				catalogId: catalogId,
			});
		});
		// save collection
		$('.c-create .btn-save').on('click', function () {
			saveMarketingData(getFormData(), function() {
				// redirect tab-active & then search-data
				if (isCreate) {
					isCreate = false;
					initActiveItemNum();
					$('.c-table-tab-item').removeClass('active').eq(0).addClass('active');
					// update parentCategory data
					getParentMarketingData(renderParentMarketing);
				}

				getTabSearchData($('.c-table-tab-item.active'));
				showInitBlock();
				$('#catalogId').val('');
			});
		});
		// cancel collection save
		$('.c-create .btn-cancel').on('click', function () {
			if (isCreate) {
				isCreate = false;
				/* initActiveItemNum(); */
				// delete null collection
				deleteMarketingData({
					catalogId: $('#catalogId').val(),
				}, function() {
					console.log("cancel create CateLog");
				});
			}

			showInitBlock();
		});
		// status combinewith supercate
		$('#catalogStatus').on('click', function(e) {
			if (parseInt($('#catalogSupercateId').val()) < 0) {
				toastr.info('Please Select super-category!');
				$('#catalogSupercateId').focus();
				$('#catalogStatus').prop('checked', false);
			}
		});
		// supercate & catalogStatus combinewith
		$('#catalogSupercateId').on('change', function(e) {
			if (parseInt($(this).val()) < 0) {
				$('#catalogStatus').prop('checked', false);
			}
		});
		function showCreateBlock() {
			$('.c-init').addClass('hide');
			$('.c-create').removeClass('hide');

			getParentMarketingData(renderParentMarketing);
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
			$('#catalogId').val('');
			$('#catalogName').val('');
			$('#catalogFirthNum').val('-1');
			$('#catalogStatus').prop('checked', false);
			$('#catalogLable').val('0');
			$('#catalogDesc').val('');

			$('#catalogIfproorcateorpage').val('0');
			$('.ml-product').removeClass('hide').siblings().addClass('hide');
			$('#catalogProid').val('-1');
			$('#catalogCateid').val('-1');
			$('#catalogPageseoname').val('-1');
			$('#catalogIfinto').val('0')

			$('#catalogSupercateId').val('-1');
			$('#catalogParentId').val('-1');

		}
		// getFormdData
		function getFormData() {
			var data = {};
			data.catalogId = parseInt($('#catalogId').val());
			data.catalogName = $('#catalogName').val();
			data.catalogStatus = $('#catalogStatus').prop('checked') ? 1 : 0;
			data.catalogFirthNum = $('#catalogFirthNum').val();
			data.catalogLable = parseInt($('#catalogLable').val());
			data.catalogDesc = $('#catalogDesc').val();
			
			var catalogIfproorcateorpage = $('#catalogIfproorcateorpage').val();
			data.catalogIfproorcateorpage = catalogIfproorcateorpage;
			if (catalogIfproorcateorpage == 0) {
				data.catalogProid = $('#catalogProid').val();
				data.catalogProname = $('#catalogProid').find('option:checked').data('name');
				data.catalogSeoname = $('#catalogProid').find('option:checked').data('seo');
				data.catalogCateid = '';
				data.catalogCatename = '';
				data.catalogCateseoname = '';
				data.catalogPageseoname = '';
			} else if (catalogIfproorcateorpage == 1) {
				data.catalogProid = '';
				data.catalogProname = '';
				data.catalogSeoname = '';
				data.catalogCateid = $('#catalogCateid').val();
				data.catalogCatename = $('#catalogCateid').find('option:checked').data('name');
				data.catalogCateseoname = $('#catalogCateid').find('option:checked').data('seo');
				data.catalogPageseoname = '';
			} else if (catalogIfproorcateorpage == 2) {
				data.catalogProid = '';
				data.catalogProname = '';
				data.catalogSeoname = '';
				data.catalogCateid ='';
				data.catalogCatename = '';
				data.catalogCateseoname = '';
				data.catalogPageseoname = $('#catalogPageseoname').val();
			}
			data.catalogIfinto = $('#catalogIfinto').val();

			data.catalogSupercateId = $('#catalogSupercateId').val();
			data.catalogSupercateName = $('#catalogSupercateId').find('option:selected').text();

			data.catalogParentId = $('#catalogParentId').val();
			data.catalogParentName = $('#catalogParentId').find('option:selected').data('name');

			return data;
		}
		// initFormData
		function initFormData(data) {
			// initial
			$('#catalogProid').val('-1');
			$('#catalogCateid').val('-1');
			$('#catalogPageseoname').val('-1');
			// init
			$('#catalogId').val(data.catalogId);
			$('#catalogName').val(data.catalogName);
			$('#catalogFirthNum').val(data.catalogFirthNum ? data.catalogFirthNum : '-1');
			$('#catalogStatus').prop('checked', (data.catalogSupercateId > 0 ? data.catalogStatus : '0'));
			$('#catalogLable').val(data.catalogLable || '0');
			$('#catalogDesc').val(data.catalogDesc);

			var catalogIfproorcateorpage = data.catalogIfproorcateorpage;
			$('#catalogIfproorcateorpage').val(catalogIfproorcateorpage || '0');
			if (catalogIfproorcateorpage == 0) {
				$('#catalogProid').val(data.catalogProid || '-1');
				$('.ml-product').removeClass('hide').siblings().addClass('hide');
			} else if (catalogIfproorcateorpage == 1) {
				$('#catalogCateid').val(data.catalogCateid || '-1');
				$('.ml-category').removeClass('hide').siblings().addClass('hide');
			} else if (catalogIfproorcateorpage == 2) {
				$('#catalogPageseoname').val(data.catalogPageseoname || '-1');
				$('.ml-subject').removeClass('hide').siblings().addClass('hide');
			}
			
			$('#catalogIfinto').val(data.catalogIfinto || '0');
			
			$('#catalogSupercateId').val(data.catalogSupercateId || '-1');
			$('#catalogParentId').val(data.catalogParentId || '-1');

		}
		// callback get id
		function getMarketingId() {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/MlbackCatalog/initializaCatalog",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				success: function (data) {
					if (data.code == 100) {
						var catalogId = data.extend&& data.extend.mlbackCatalog && data.extend.mlbackCatalog.catalogId;
						if (catalogId) {
							$('#catalogId').val(catalogId);
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
		function getMarketingsData() {
			$('.c-mask').show();

			var formData = new FormData();
			formData.append('pn', getPageNum());

			$.ajax({
				url: "${APP_PATH}/MlbackCatalog/getMlbackCatalogByPage",
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
					toastr.error('Failed to get CateLog, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback get one data
		function getOneMarketingData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackCatalog/getOneMlbackCatalogDetail",
				type: "post",
				data: JSON.stringify(reqData),
				dataType: 'json',
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend.MlbackCatalogOne);
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function () {
					toastr.error('Failed to get Marketing, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback get search data
		function getSearchMarkegingsData(data) {
			$('.c-mask').show();

			var formData = new FormData();
			formData.append('catalogName', $('#searchCatalog').val());
			formData.append('catalogSupercateId', ($('#searchSupercate').attr('data-val') || '-1'));
			formData.append('pn', getPageNum());

			$.ajax({
				url: "${APP_PATH}/MlbackCatalog/backSearchByCatalog",
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
					toastr.error('Failed to get Marketing, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback save
		function saveMarketingData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackCatalog/save",
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
		function deleteMarketingData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackCatalog/delete",
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

		// callback parentCategory
		function getParentMarketingData(callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackCatalog/getMlbackCatalogDropdownSelect",
				type: "post",
				contentType: 'application/json',
				async: false,
				success: function (data) {
					if (data.code == 100) {
						toastr.success(data.extend.resMsg);
						callback(data.extend.mlbackCatalogdownList);
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
				if (data[i].catalogIfproorcateorpage == 0) {
					logName = '<b>product</b> ' + data[i].catalogProid;
					logSeo = data[i].catalogSeoname;
				} else if (data[i].catalogIfproorcateorpage == 1) {
					logName = '<b>collection</b> ' + data[i].catalogCateid;
					logSeo = data[i].catalogCateseoname;
				} else if (data[i].catalogIfproorcateorpage == 2) {
					logName = '<b>subject</b> ' + data[i].catalogPageseoname;
					logSeo = data[i].catalogPageseoname;
				}
				htmlStr += '<tr><td>' + data[i].catalogId + '</td>' +
					'<td>' + data[i].catalogName + '</td>' +
					'<td>' + data[i].catalogParentId + '</td>' +
					'<td>' + data[i].catalogParentName + '</td>' +
					'<td>' + data[i].catalogLable + '</td>' +
					'<td>' + data[i].catalogFirthNum + '</td>' +
					'<td>' + (logName || '') + '</td>' +
					'<td>' + (logSeo || '') + '</td>' +
					'<td>' + (data[i].catalogIfinto ? 'YES' : 'NO') + '</td>' +
					'<td><a class="badge '+ (data[i].catalogStatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].catalogStatus ? 'enable' : 'disable') + '</a></td>' +
					'<td>' + data[i].catalogDesc + '</td>' +
					'<td>' +
						'<button class="btn btn-primary btn-edit" data-id="' + data[i].catalogId + '">' +
							'<svg class="c-icon">' +
								'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-pencil"></use>' +
							'</svg>' +
						'</button>' +
						'<button class="btn btn-danger btn-delete" data-id="' + data[i].catalogId + '">' +
							'<svg class="c-icon">' +
								'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-trash"></use>' +
							'</svg>' +
						'</button>' +
					'</td></tr>';
			}
			$('.c-table-table tbody').html(htmlStr);
		}
		// render parentCategoryData
		function renderParentMarketing(data) {
			var htmlStr = '<option value="-1">Please Select parent-category</option>';
			for (var i = 0, len = data.length; i < len; i += 1) {
				htmlStr += '<option value="' + data[i].catalogId + '" data-name="' + data[i].catalogName + '">' + data[i].catalogDesc + '</option>';
			}
			$('#catalogParentId').html(htmlStr);
			hasParentCategory = true;
		}
	</script>
</body>

</html>