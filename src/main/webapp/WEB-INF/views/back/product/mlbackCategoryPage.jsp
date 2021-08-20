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
						<span class="c-option-title">Collections</span>
						<button class="btn btn-primary btn-create">Create collection</button>
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
									<input id="searchCollection" type="text" placeholder="Search Collections">						
									<select class="supercate-list" id="searchSupercate"></select>
								</div>
								<a class="btn btn-primary input-group-addon btn-save-search">Save search</a>
							</div>
							<div class="c-table-table table-responsive-sm">
								<table class="table">
									<thead>
										<tr>
											<th>id</th>
											<th>wap-image</th>
											<th>pc-image</th>
											<th>name</th>
											<th>parent-id</th>
											<th>parent-name</th>
											<th>tag</th>
											<th>status</th>
											<th>order</th>
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
						<span class="c-option-title">Edit Collections</span>
						<div class="group">
							<button class="btn btn-secondary btn-cancel">Cancel</button>
							<button class="btn btn-primary btn-save">Save collection</button>
						</div>
					</div>
					<div class="c-form row">
						<input id="categoryId" hidden>
						<!-- left panel  -->
						<div class="left-panel col-lg-7 col-md-12">
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">General</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="categoryName">Collection Name</label>
										<div class="controls">
											<input class="form-control" id="categoryName" type="text" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="categorySeo">Collection SEO</label>
										<div class="controls">
											 <input class="form-control" id="categorySeo" type="text">
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="categorySortOrder">Sort</label>
										<div class="controls">
											<select class="form-control" id="categorySortOrder" />
												<option value="0">Please select category sort-order</option>
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
										<label class="col-md-3 col-form-label" for="categoryStatus">Status</label>
										<div class="controls col-md-3">
											<label class="c-switch c-switch-primary">
												<input class="c-switch-input" id="categoryStatus" type="checkbox">
												<span class="c-switch-slider"></span>
											</label>
										</div>
									</div>
									<div class="form-group hide">
										<label class="col-form-label" for="categoryLable">Label</label>
										<div class="controls">
											<select class="form-control" id="categoryLable" />
												<option value="0" selected="selected">0-无图</option>
												<option value="1">1-hot</option>
												<option value="2">2-new</option>
												<option value="3">3-62% off</option>
												<option value="4">4-65% off</option>
												<option value="5">5-ins</option>
												<option value="6">6-review</option>
												<option value="7">7-video</option>
												<option value="8">8-Best Quality</option>
												<option value="9">8-15A</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="categoryDesc">Description</label>
										<div class="controls">
											<input class="form-control" id="categoryDesc" type="text" disabled />
										</div>
									</div>
								</div>
							</div>
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Collection Image</div>
								</div>
								<div class="card-body">
									<div class="col-md-6">
										<h3>Wap</h3>										
										<div class="form-group row">
											<label class="col-md-3 col-form-label" for="categoryImgstatus">Status</label>
											<div class="controls col-md-3">
												<label class="c-switch c-switch-primary">
													<input class="c-switch-input" id="categoryImgstatus" type="checkbox">
													<span class="c-switch-slider"></span>
												</label>
											</div>
										</div>
										<div class="c-upload-img">
											<svg class="c-icon">
												<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image-plus"></use>
											</svg>
											<%-- <div class="product-img-delete hide">
												<svg class="c-icon">
													<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-trash"></use>
												</svg>
											</div> --%>
											<div class="c-backshow"></div>						
											<input id="categoryImgurl" data-type="categoryImgurl" type="file" accept="image/png, image/jpeg, image/gif" />										
											<!-- spinner -->
											<div class="spinner">
												<div class="spinner-border" role="status" aria-hidden="true"></div>
											</div>
										</div>
										<p>width/height: 750 * 400</p>
									</div>
									<div class="col-md-6">
										<h3>Pc</h3>
										<div class="form-group row">
											<label class="col-md-3 col-form-label" for="categoryImgpcstatus">Status</label>
											<div class="controls col-md-3">
												<label class="c-switch c-switch-primary">
													<input class="c-switch-input" id="categoryImgpcstatus" type="checkbox">
													<span class="c-switch-slider"></span>
												</label>
											</div>
										</div>
										<div class="c-upload-img">
											<svg class="c-icon">
												<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image-plus"></use>
											</svg>
											<%-- <div class="product-img-delete hide">
												<svg class="c-icon">
													<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-trash"></use>
												</svg>
											</div> --%>
											<div class="c-backshow"></div>
											<input id="categoryImgpcurl" data-type="categoryImgpcurl" type="file" accept="image/png, image/jpeg, image/gif" />										
											<!-- spinner -->
											<div class="spinner">
												<div class="spinner-border" role="status" aria-hidden="true"></div>
											</div>
										</div>
										<p>width/height: 1920 * 500</p>
									</div>
								</div>
							</div>
						</div>
						<!-- right panel  -->
						<div class="right-panel col-lg-5 col-md-12">
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Super Category & Parent Category</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="categorySuperCateId">Super Category</label>
										<div class="controls">
											<select class="form-control supercate-list" id="categorySuperCateId" /></select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="categoryParentId">Parent Category</label>
										<div class="controls">
											<select class="form-control collection-list" id="categoryParentId" /></select>
										</div>
									</div>
									<input type="hidden" id="categoryProductIds" />
									<input type="hidden" id="categoryProductNames" />
									<div class="form-group">
										<label class="col-form-label" for="categoryProductList">Products Under Category</label>
										<div class="controls" style="display: flex;">
											<textarea class="form-control" rows="5" id="categoryProductList"></textarea>
											<button class="btn btn-info" id="custom-product-order" style="margin-left: .5rem">Custom sort</button>
										</div>
									</div>
								</div>
							</div>
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Search engine listing preview</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="categoryMetatitle">Page title</label>
										<div class="controls">
											<input class="form-control" id="categoryMetatitle" type="text" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="categoryMetakeywords">Page keywords</label>
										<div class="controls">
											<textarea class="form-control" rows="5" id="categoryMetakeywords"></textarea>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="categoryMetadesc">Page Description</label>
										<div class="controls">
											<textarea class="form-control" rows="5" id="categoryMetadesc"></textarea>
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
		var hasCollectionList = false;
		var isCreate = false;
		var storageName = 'collections';

		if (!hasSuperCateList) getSuperCategoryData(renderSuperCategory);
	 	$('#searchSupercate').val($('#searchSupercate').data('val') || -1);

		// init
		renderTabItems();
		// save search
		$('.btn-save-search').on('click', function () {
			var searchCollectionVal = {
				supercate: $('#searchSupercate').find('option:selected').text(),
				supercateId: $('#searchSupercate').val(),
				collection: $('#searchCollection').val()
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
		$('#searchCollection').on('keyup', function() {
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
				collection: $('#searchCollection').val()
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
				$('#searchCollection').val(dataVal.collection || '');
				$('#searchSupercate').attr('data-val', dataVal.supercateId || '-1');
				$('#searchSupercate').val(dataVal.supercateId || '-1');
				getSearchCollectionsData();
			} else {
				$('#searchSupercate').val('-1');
				$('#searchCollection').val('');
				initActiveItemNum();
				getCollectionsData();
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
			$('.c-create .c-option-title').text('Create Collection');
			showCreateBlock();
			// init formData
			resetFormData();
			getCollectionId();
			isCreate = true;
		});
		// edit collection
		$(document.body).on('click', '.btn-edit', function (e) {
			var categoryId = $(this).data('id');
			getOneCollectionData({
			 categoryId: categoryId
			}, function(resData) {
			 	$('.c-create .c-option-title').text('Edit Collection');
				showCreateBlock();
				initFormData(resData);
			});			
		});
		// delete collection
		$(document.body).on('click', '.btn-delete', function (e) {
			var categoryId = parseInt($(this).data('id'));
			$('#deleteModal').find('.modal-title').html('Delete collection!');
			$('#deleteModal').modal('show');
			$('#deleteModal .btn-ok').one('click', function () {
				deleteCollectionData({
					categoryId: categoryId,
				}, function() {
					getCollectionsData();
					// update parentCategory data
					getAllCollectionData(renderAllCollection);
				});
			});
		});
		$(window).on('beforeunload', function() {
			var categoryId = $('#categoryId').val();
			isCreate && categoryId && deleteCollectionData({
				categoryId: categoryId,
			});
			$('#img-all-handle').addClass('hide');
		});
		// save collection
		$('.c-create .btn-save').on('click', function () {
			saveCollectionData(getFormData(), function() {
				// redirect tab-active & then search-data
				if (isCreate) {
					isCreate = false;
					initActiveItemNum();
					$('.c-table-tab-item').removeClass('active').eq(0).addClass('active');
					// update parentCategory data
					getAllCollectionData(renderAllCollection);
				}

				getTabSearchData($('.c-table-tab-item.active'));
				showInitBlock();
				$('#categoryId').val('');
			});
		});
		// cancel collection save
		$('.c-create .btn-cancel').on('click', function () {
			if (isCreate) {
				isCreate = false;
				/* initActiveItemNum(); */
				// delete null collection
				deleteCollectionData({
					categoryId: $('#categoryId').val(),
				}, function() {
					console.log("cancel create-collection");
				});
				// fetch default collection
				// getCollectionsData();
			}

			showInitBlock();
		});
		// status combinewith supercate
		$('#categoryStatus').on('click', function(e) {
			if (parseInt($('#categorySuperCateId').val()) < 0) {
				toastr.info('Please Select super-category!');
				$('#categorySuperCateId').focus();
				$('#categoryStatus').prop('checked', false);
			}
		});
		// supercate & categoryStatus combinewith
		$('#categorySuperCateId').on('change', function(e) {
			if (parseInt($(this).val()) < 0) {
				$('#categoryStatus').prop('checked', false);
			}
		});
		// custom order
		$('#custom-product-order').on('click', function() {
			var imgsHtml = '';
			var productIds = $('#categoryProductIds').val() && $('#categoryProductIds').val().split(',');
			var productNames = $('#categoryProductNames').val() && $('#categoryProductNames').val().split(',');

			if (productIds.length) {
				productIds.forEach(function(id, idx) {
					imgsHtml += '<div class="product-imgs-sort-item" data-id="'+ id +'" data-name="'+ productNames[idx] +'">'+ productNames[idx] +'</div>';
				});
				$('#img-all-handle').removeClass('hide');
				$('#imgModal').data('len', productIds.length).find('.left-panel').html(imgsHtml).end().find('.right-panel').html('').end().modal('show');					
			} else {
				toastr.warning('没有产品可进行排序！！！');
			}				
			getAllProductData(function(data) {
				var existProducts = [];
				var $items = $('.product-imgs-sort-item');
				data.forEach(function(prod) {
					var pId = '' + prod.productId;
					if (productIds.indexOf(pId) > -1) {
						existProducts.push(pId);
						if (!prod.productStatus) {
							for (var i = 0, len = $items.length; i < len; i += 1) {
								var $item = $items.eq(i);
								if (('' + $item.data('id')) == pId) {
									$item.css('border-color', 'red');
									break;
								}
							}							
						}
					}
				});
				$items.each(function(idx, item) {
					var $item = $(item);
					if (existProducts.indexOf('' + $item.data('id')) < 0) {
						$item.css('border-color', 'blue');
					}
				});
			});
		});
		$(document.body).on('click', '#imgModal .left-panel .product-imgs-sort-item', function() {
			$('#imgModal .right-panel').append($(this));
		});
		$(document.body).on('click', '#imgModal .right-panel .product-imgs-sort-item', function() {
			$('#imgModal .left-panel').append($(this));
		});
		$('#img-all-handle').on('click', function() {
			$('#imgModal .right-panel').append($($('#imgModal .left-panel').html()));
			$('#imgModal .left-panel').html('');
		});
		$('#imgModal .btn-save').on('click', function() {
			var productIdArr = [];
			var productNameArr = [];
			var productImgsItem = $('#imgModal .right-panel .product-imgs-sort-item');
			if (productImgsItem.length < $('#imgModal').data('len')) {
				toastr.error('未完成排序，请继续操作');
				return ;
			}
			productImgsItem.each(function(idx, item) {
				productIdArr.push($(item).data('id'));
				productNameArr.push($(item).data('name'));
			});

			$('#categoryProductIds').val(productIdArr.join(','));
			$('#categoryProductNames').val(productNameArr.join(','));
			
			$('#categoryProductList').val(' * ' + productNameArr.join(',').replace(/\,/g, '\n * ')).attr('data-val', productNameArr.join(','));
			
			$('#imgModal').modal('hide');
		});
		// upload img
		$('#categoryImgurl').on('change', function(e) {
			var $this = $(this);
			var file = $this[0].files[0];
			var formData = new FormData();

			if (!file) return false;
	
			$this.parent().find('.spinner').show();
			$this.val('');

			formData.append('type', 'category');
			formData.append('image', file);
			formData.append('categoryId', parseInt($('#categoryId').val()));
			formData.append('categorySeo', $('#categorySeo').val());

			$.ajax({
				url: "${APP_PATH}/ImageUpload/thumImageCategory",
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
		// upload img
		$('#categoryImgpcurl').on('change', function(e) {
			var $this = $(this);
			var file = $this[0].files[0];
			var formData = new FormData();

			if (!file) return false;
	
			$this.parent().find('.spinner').show();
			$this.val('');

			formData.append('type', 'categorypc');
			formData.append('image', file);
			formData.append('categoryId', parseInt($('#categoryId').val()));
			formData.append('categorySeo', $('#categorySeo').val());

			$.ajax({
				url: "${APP_PATH}/ImageUpload/thumImageCategoryPc",
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
		// delete product img
		$(document.body).on('click', '.product-img-delete', function() {
			var inputFile = $(this).parent().find('input');
			var dataVal = inputFile.data('val');

			$('#deleteModal').find('.modal-title').html('Delete collection Img!');
			$('#deleteModal').modal('show');
			$('#deleteModal .btn-ok').one('click', function () {
				var reqData = {};
				reqData[inputFile.data('type')] = dataVal.imageUrl;
				reqData.categoryId = $('#categoryId').val();
				deletecollectionImgData(reqData, function(data) {
					resetPicture(inputFile);
				});
			});
		});
		// callback delete
		function deletecollectionImgData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackCategory/removeCateImg",
				type: "post",
				cache: false,
				dataType: "json",
				contentType: 'application/json',
				data: JSON.stringify(reqData),
				success: function (data) {
					if (data.code == 100) {
						toastr.success(data.extend.resMsg);
						$('#deleteModal').modal('hide');
						callback && callback();
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
		function addPicture(el, data) {
			var parentEl = el.parent();
			el.attr('data-val', JSON.stringify(data));
			parentEl.addClass('active');
			parentEl.find('.c-backshow').html('<img src="'+ encodeUrl(data.thumImageUrl) + '" />').end().find('.product-img-delete').removeClass('hide');
		}
		function resetPicture(el) {
			var parentEl = el.parent();
			el.attr('data-val', '');
			parentEl.removeClass('active');
			parentEl.find('.c-backshow').html('').end().find('.product-img-delete').addClass('hide');
		}
		function showCreateBlock() {
			$('.c-init').addClass('hide');
			$('.c-create').removeClass('hide');

			if (!hasCollectionList) getAllCollectionData(renderAllCollection);
		}
		function showInitBlock() {
			$('.c-init').removeClass('hide');
			$('.c-create').addClass('hide');
		}
		// handle formData
		// reset data
		function resetFormData() {
			$('#categoryId').val('');
			$('#categoryName').val('');
			$('#categorySortOrder').val('0');
			$('#categoryStatus').prop('checked', false);
			$('#categoryLable').val('0');
			$('#categoryDesc').val('');

			$('#categoryImgstatus').prop('checked', false);
			$('#categoryImgpcstatus').prop('checked', false);

			resetPicture($('#categoryImgurl'));
			resetPicture($('#categoryImgpcurl'));

			$('#categorySuperCateId').val('-1');
			$('#categoryParentId').val('-1');
			$('#categoryProductIds').val('');
			$('#categoryProductNames').val('')
			$('#categoryProductList').val('No product！');

			$('#categorySeo').val('');
			$('#categoryMetatitle').val('');
			$('#categoryMetakeywords').val('');
			$('#categoryMetadesc').val('');
		}
		// getFormdData
		function getFormData() {
			var data = {};
			data.categoryId = parseInt($('#categoryId').val());
			data.categoryName = $('#categoryName').val();
			data.categoryStatus = $('#categoryStatus').prop('checked') ? 1 : 0;
			data.categorySortOrder = $('#categorySortOrder').val();
			data.categoryLable = parseInt($('#categoryLable').val());
			data.categoryDesc = $('#categoryDesc').val();

			data.categoryImgstatus = $('#categoryImgstatus').prop('checked') ? 1 : 0;
			data.categoryImgpcstatus = $('#categoryImgpcstatus').prop('checked') ? 1 : 0;

			var imageData = $('#categoryImgurl').attr('data-val') && JSON.parse($('#categoryImgurl').attr('data-val'));
			var imagePcData = $('#categoryImgpcurl').attr('data-val') && JSON.parse($('#categoryImgpcurl').attr('data-val'));
			data.categoryImgpcurl = imagePcData.imageUrl;
			data.categoryImgurl = imageData.imageUrl;

			data.categorySuperCateId = $('#categorySuperCateId').val();
			data.categorySuperCateName = $('#categorySuperCateId').find('option:selected').text();

			data.categoryParentId = $('#categoryParentId').val();
			data.categoryParentName = $('#categoryParentId').find('option:selected').data('name');

			data.categoryProductIds = $('#categoryProductIds').val();
			data.categoryProductNames = $('#categoryProductNames').val();

			data.categorySeo = $('#categorySeo').val();
			data.categoryMetatitle = $('#categoryMetatitle').val();
			data.categoryMetakeywords = $('#categoryMetakeywords').val();
			data.categoryMetadesc = $('#categoryMetadesc').val();

			return data;
		}
		// initFormData
		function initFormData(data) {
			$('#categoryId').val(data.categoryId);
			$('#categoryName').val(data.categoryName);
			$('#categorySortOrder').val(data.categorySortOrder ? data.categorySortOrder : '0');
			$('#categoryStatus').prop('checked', (data.categorySuperCateId > 0 ? data.categoryStatus : 0));
			$('#categoryLable').val(data.categoryLable);
			$('#categoryDesc').val(data.categoryDesc);

			if (hasSuperCateList && hasCollectionList) {
				// value
				$('#categorySuperCateId').val(data.categorySuperCateId || '-1');
				$('#categoryParentId').val(data.categoryParentId || '-1');
			}
			// attr
			$('#categorySuperCateId').attr('data-val', data.categorySuperCateId || '-1');
			$('#categoryParentId').attr('data-val', data.categoryParentId || '-1');

			$('#categoryProductIds').val(data.categoryProductIds);
			$('#categoryProductNames').val(data.categoryProductNames);
			$('#categoryProductList').val(data.categoryProductNames ? ' * ' + data.categoryProductNames.replace(/\,/g, '\n * '): 'No product！').attr('data-val', data.categoryProductNames);

			$('#categoryImgstatus').prop('checked', data.categoryImgstatus);
			$('#categoryImgpcstatus').prop('checked', data.categoryImgpcstatus);

			if (data.categoryImgurl) {
				addPicture($('#categoryImgurl'), {
					imageUrl: data.categoryImgurl,
					thumImageUrl: data.categoryImgurl
				});				
			} else {
				resetPicture($('#categoryImgurl'));
			}

			if (data.categoryImgpcurl) {
				addPicture($('#categoryImgpcurl'), {
					imageUrl: data.categoryImgpcurl,
					thumImageUrl: data.categoryImgpcurl
				});				
			} else {
				resetPicture($('#categoryImgpcurl'));
			}

			$('#categorySeo').val(data.categorySeo);
			$('#categoryMetatitle').val(data.categoryMetatitle);
			$('#categoryMetakeywords').val(data.categoryMetakeywords);
			$('#categoryMetadesc').val(data.categoryMetadesc);
		}
		// callback get id
		function getCollectionId() {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/MlbackCategory/initializaCategory",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				success: function (data) {
					if (data.code == 100) {
						var categoryId = data.extend&& data.extend.mlbackCategory && data.extend.mlbackCategory.categoryId;
						if (categoryId) {
							$('#categoryId').val(data.extend.mlbackCategory.categoryId);
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
		function getCollectionsData() {
			$('.c-mask').show();

			var formData = new FormData();
			formData.append('pn', getPageNum());

			$.ajax({
				url: "${APP_PATH }/MlbackCategory/getMlbackCategoryByPage",
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
		function getOneCollectionData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/MlbackCategory/getOneMlbackCategoryDetail",
				type: "post",
				data: JSON.stringify(reqData),
				dataType: 'json',
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend.mlbackCategoryOne);
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
		// callback get search data
		function getSearchCollectionsData(data) {
			$('.c-mask').show();

			var formData = new FormData();
			formData.append('categoryName', $('#searchCollection').val());
			formData.append('categorySuperCateId', ($('#searchSupercate').attr('data-val') || '-1'));
			formData.append('pn', getPageNum());

			$.ajax({
				url: "${APP_PATH }/MlbackCategory/backSearchBycategory",
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
		// callback save
		function saveCollectionData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackCategory/save",
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
				url: "${APP_PATH}/MlbackCategory/delete",
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
				htmlStr += '<tr><td>' + data[i].categoryId + '</td>' +
					'<td>' +
						(data[i].categoryImgurl ?
							'<div class="c-table-img"><img src="'+ encodeUrl(data[i].categoryImgurl) +'" /></div>'
							: '<div class="c-table-icon"><svg class="c-icon"><use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image1"></use></svg></div>') +
					'</td>' +
					'<td>' +
						(data[i].categoryImgpcurl ?
							'<div class="c-table-img"><img src="'+ encodeUrl(data[i].categoryImgpcurl) +'" /></div>'
							: '<div class="c-table-icon"><svg class="c-icon"><use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image1"></use></svg></div>') +
					'</td>' +
					'<td>' + data[i].categoryName + '</td>' +
					'<td>' + data[i].categoryParentId + '</td>' +
					'<td>' + data[i].categoryParentName + '</td>' +
					'<td>' + data[i].categoryLable + '</td>' +
					'<td><a class="badge '+ (data[i].categoryStatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].categoryStatus ? 'enable' : 'disable') + '</a></td>' +
					'<td>' + data[i].categorySortOrder + '</td>' +
					'<td>' + data[i].categoryDesc + '</td>' +
					'<td>' +
						'<button class="btn btn-primary btn-edit" data-id="' + data[i].categoryId + '">' +
							'<svg class="c-icon">' +
								'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-pencil"></use>' +
							'</svg>' +
						'</button>' +
						'<button class="btn btn-danger btn-delete" data-id="' + data[i].categoryId + '">' +
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