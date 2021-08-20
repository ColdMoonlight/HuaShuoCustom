<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Product List</title>
	<jsp:include page="../common/backheader.jsp" flush="true"></jsp:include>
	<link href="${APP_PATH}/static/back/lib/codemirror/codemirror.min.css" rel="stylesheet">
	<link href="${APP_PATH}/static/back/lib/codemirror/blackboard.min.css" rel="stylesheet">
	<link href="${APP_PATH}/static/back/lib/codemirror/monokai.min.css" rel="stylesheet">
	<link href="${APP_PATH}/static/back/lib/summernote/summernote.min.css" rel="stylesheet">
	<link href="${APP_PATH}/static/back/lib/tagsinput/bootstrap-tagsinput.css" rel="stylesheet">
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
						<span class="c-option-title">Products</span>
						<div class="right-box">
							<button class="btn btn-secondary" id="download-google-ad">
								<svg class="c-icon">
									<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-export"></use>
								</svg>
								<span>Export Google-shopping Data</span>
							</button>
							<button class="btn btn-primary btn-create">Create product</button>
							<button class="btn btn-secondary btn-copy">Copy product</button>
						</div>
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
									<input id="searchProduct" type="text" placeholder="Search Products">						
									<select class="supercate-list" id="searchSupercate"></select>
								</div>
								<a class="btn btn-primary input-group-addon btn-save-search">Save search</a>
							</div>
							<div class="c-table-table table-responsive-sm">
								<table class="table">
									<thead>
										<tr>
											<th>id</th>
											<th>image</th>
											<th>name</th>
											<th>basic-price</th>
											<th>discount</th>
											<th>tag</th>
											<th>status</th>
											<th>sale(s)</th>
											<th>reivew(s)</th>
											<th>supercate</th>
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
							<button class="btn btn-primary btn-save">Save Product</button>
						</div>
					</div>
					<div class="c-form row">
						<input id="productId" hidden>
						<!-- left panel  -->
						<div class="left-panel col-lg-7 col-md-12">
							<!-- general -->
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">General</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="productName">Product Name</label>
										<div class="controls">
											<input class="form-control" id="productName" type="text" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="productSeo">Product SEO</label>
										<div class="controls">
											 <input class="form-control" id="productSeo" type="text">
										</div>
									</div>
									<div class="form-group row">
										<label class="col-md-3 col-form-label" for="productStatus">Status</label>
										<div class="controls col-md-3">
											<label class="c-switch c-switch-primary">
												<input class="c-switch-input" id="productStatus" type="checkbox">
												<span class="c-switch-slider"></span>
											</label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="productLable">Label</label>
										<div class="controls">
											<select class="form-control" id="productLable" />
												<option value="0" selected="selected">0</option>
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
												<option value="6">6</option>
												<option value="7">7</option>
												<option value="8">8</option>
												<option value="9">8</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="productDesc">Description</label>
										<div class="controls">
											<textarea id="productDesc"><p>Please Input Product Des.</p></textarea>
										</div>
									</div>
								</div>
							</div>
							<!-- price data  -->
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Price & Discount</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="productOriginalprice">Basic Price</label>
										<div class="controls">
											<input class="form-control" id="productOriginalprice" min="0" type="number" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="productActoffoff">Discount</label>
										<div class="controls">
											<input class="form-control" id="productActoffoff" type="number" min="1" max="99" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="productDiscoutimgShow">Discount Label Picture Status</label>
										<div class="controls">
											<label class="c-switch c-switch-primary">
												<input class="c-switch-input" id="productDiscoutimgShow" type="checkbox">
												<span class="c-switch-slider"></span>
											</label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="productDiscoutimgurl">Discount Label Picture</label>
										<div class="controls">
											<div class="c-upload-img">
												<svg class="c-icon">
													<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image-plus"></use>
												</svg>
												<div class="c-backshow"></div>						
												<input id="productDiscoutimgurl" type="file" accept="image/png, image/jpeg, image/gif" />										
												<!-- spinner -->
												<div class="spinner">
													<div class="spinner-border" role="status" aria-hidden="true"></div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- price sku  -->
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Skus</div>
								</div>
								<div class="card-body">
									<div class="product-options">
										<p class="text-center"> no option, please add one option to generate skus. </p>
									</div>
									<button class="product-option-add btn btn-secondary">Add another option</button>
									<div class="product-skus">
										<div class="product-sku-head">
											<div class="product-sku-th">
												<div class="product-sku-td product-sku-head-name"> name </div>												
												<div class="product-sku-td product-sku-head-picture"> picture </div>
												<div class="product-sku-td product-sku-head-stock"> stock </div>
												<div class="product-sku-td product-sku-head-price"> price </div>
												<div class="product-sku-td product-sku-head-sku"> sku </div>
												<div class="product-sku-td product-sku-head-operate"> operate </div>
											</div>
										</div>
										<div class="product-sku-body">
											<p class="text-center"> no skus ... </p>
										</div>
									</div>
									
									<div class="initial-product-sku hide">
										<h3>Batch Processing</h3>
										<div class="input-group">
											<label class="btn btn-primary input-group-addon" for="initialCount">stock</label>
											<input class="form-control" data-cls="product-sku-stock" id="initialCount" type="text">
											<a class="btn btn-primary input-group-addon">UPDATE</a>
										</div>
										<div class="input-group">
											<label class="btn btn-primary input-group-addon" for="initialPrice">price</label>
											<input class="form-control" data-cls="product-sku-price" id="initialPrice" type="text">
											<a class="btn btn-primary input-group-addon">UPDATE</a>
										</div>
										<div class="input-group">
											<label class="btn btn-primary input-group-addon" for="initialCode">code</label>
											<input class="form-control" data-cls="product-sku-sku" id="initialCode" type="text">
											<a class="btn btn-primary input-group-addon">UPDATE</a>
										</div>
									</div>

									<div class="product-sku-data-operate">
										<button class="btn btn-primary product-sku-add">Add One Product-sku</button>
										<button class="btn btn-primary product-sku-reset">Reset Product Skus</button>
										<button class="btn btn-primary all-product-sku-save">Save All Product Skus</button>
									</div>
								</div>
							</div>
							<!-- media picture -->
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Product Media</div>
								</div>
								<div class="card-body">
									<div class="row">
										<!-- main img  -->
										<div class="col-md-6">
											<h3>Main Picture</h3>
											<div class="c-upload-img">
												<svg class="c-icon">
													<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image-plus"></use>
												</svg>
												<div class="c-backshow"></div>						
												<input id="productImgurl" type="file" accept="image/png, image/jpeg, image/gif" />										
												<!-- spinner -->
												<div class="spinner">
													<div class="spinner-border" role="status" aria-hidden="true"></div>
												</div>
											</div>
										</div>
										<!-- video -->
										<div class="col-md-6">
											<h3>Video</h3>
											<div class="c-upload-img">
												<svg class="c-icon">
													<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-movie"></use>
												</svg>
												<div class="video-preview">preview</div>
												<div class="c-backshow"></div>
												<input id="productVideourl" type="file" accept="video/mp4" />										
												<!-- spinner -->
												<div class="spinner">
													<div class="spinner-border" role="status" aria-hidden="true"></div>
												</div>
											</div>
										</div>
									</div>
									<div class="pictureDetails">
										<h3>Product Details Picture</h3> 
										<i class="text-danger">Upload up to 6 images！</i>
										<button class="btn btn-info" id="custom-pic-order">Custom sort</button>
										<div class="product-img-list">
											<div class="product-img-item c-upload-img">
												<svg class="c-icon">
													<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image-plus"></use>
												</svg>
												<div class="product-img-delete hide">
													<svg class="c-icon">
														<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-trash"></use>
													</svg>
												</div>
												<div class="c-backshow"></div>						
												<input class="productAllImgurl" data-order="1" type="file" accept="image/png, image/jpeg, image/gif" />										
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
							<!-- parent category -->
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Super Category & Parent Category</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="productSupercateid">Super Category</label>
										<div class="controls">
											<select class="form-control supercate-list" id="productSupercateid" /></select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="categoryParentId">Parents Category</label>
										<input type="hidden" id="productCategoryIdsstr" />
										<input type="hidden" id="productCategoryNamesstr" />
										<div class="controls" style="display: flex;">
											<textarea id="productCategoryList" class="form-control" rows="5" disabled></textarea>
											<button id="editProductCategory" class="btn btn-info" style="margin-left: 1rem;">edit</button>
										</div>
									</div>
								</div>
							</div>
							<!-- sell data  -->
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Number of Sale & Reviews</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="productReviewnum">Reviews Number</label>
										<div class="controls">
											 <input class="form-control" id="productReviewnum" type="number" min="0" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="productHavesalenum">Sale Number</label>
										<div class="controls">
											<input class="form-control" id="productHavesalenum" type="number" min="0" />
										</div>
									</div>
								</div>
							</div>
							<!-- seo -->
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Search engine listing preview</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="productMetaTitle">Page title</label>
										<div class="controls">
											<input class="form-control" id="productMetaTitle" type="text" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="productMetaKeywords">Page keywords</label>
										<div class="controls">
											<textarea class="form-control" rows="5" id="productMetaKeywords"></textarea>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="productMetaDesc">Page Description</label>
										<div class="controls">
											<textarea class="form-control" rows="5" id="productMetaDesc"></textarea>
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
	<jsp:include page="../common/editModal.jsp" flush="true"></jsp:include>
	<jsp:include page="../common/videoModal.jsp" flush="true"></jsp:include>
	<jsp:include page="../common/skuModal.jsp" flush="true"></jsp:include>
	<jsp:include page="../common/imgModal.jsp" flush="true"></jsp:include>
	<jsp:include page="../common/googleAdModal.jsp" flush="true"></jsp:include>

	<script src="${APP_PATH}/static/back/lib/tagsinput/bootstrap-tagsinput.min.js"></script>
	<script src="${APP_PATH}/static/back/lib/codemirror/codemirror.js"></script>
	<script src="${APP_PATH}/static/back/lib/codemirror/xml.min.js"></script>
	<script src="${APP_PATH}/static/back/lib/summernote/summernote.min.js"></script>
	<script type="text/javascript" src="${APP_PATH}/static/back/lib/datetimepicker/moment.min.js"></script>
	<script type="text/javascript" src="${APP_PATH}/static/back/lib/datetimepicker/daterangepicker.js"></script>
	<!-- custom script -->
	<script>
		var hasSuperCateList = false;
		var isCreate = false;
		var isSaveSku = false;
		var hasInitSku = false;
		var hasSkuListStatus = false;
		var storageName = 'products';

		bindDateTimepicker();

		// init summernote editor for description
		$('#productDesc').summernote({
			height: 300,
			codemirror: {
				mode: 'text/html',
				htmlMode: true,
				lineNumbers: true,
				theme: 'monokai'
			},
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
		if (!hasSuperCateList) getSuperCategoryData(renderSuperCategory);
	 	$('#searchSupercate').val($('#searchSupercate').data('val') || -1);
		// init
		renderTabItems();
		// save search
		$('.btn-save-search').on('click', function () {
			var searchProductVal = {
				supercate: $('#searchSupercate').find('option:selected').text(),
				supercateId: $('#searchSupercate').val(),
				product: $('#searchProduct').val()
			};
			// cancel repeat add save-search
			if (checkNewItem(searchProductVal)) return;
			if (parseInt(searchProductVal.supercateId) < 0) searchProductVal.supercate = "";
			if (searchProductVal.supercate || searchProductVal.product) {
				$('.c-table-tab-tempory').html('');
				addStorageItem(searchProductVal);
				createTableTabItem(searchProductVal);
				addTableTabItem(searchProductVal, $('.c-table-tab-item').length);
			}
		});
		// search it
		$('#searchSupercate').on('change', function() {
			$(this).attr('data-val', $(this).val());
			updateSearchData();
		});
		var oldTime = (new Date()).getTime(),
			timer = null;
		$('#searchProduct').on('keyup', function() {
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
			var searchProductVal = {
				supercate: $('#searchSupercate').find('option:selected').text(),
				supercateId: $('#searchSupercate').val(),
				product: $('#searchProduct').val()
			};
			// inital pagination num
			setPageNum(1);
			// check searchProduct
			if (parseInt(searchProductVal.supercateId) < 0) searchProductVal.supercate = "";

			$('.c-table-tab-item.active').removeClass('active');
			$('.c-table-tab-tempory').html(createTableTabItem(searchProductVal).addClass('active'));
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
			if (dataVal && (dataVal.product || dataVal.supercate)) {
				$('#searchProduct').val(dataVal.product || '');
				$('#searchSupercate').attr('data-val', dataVal.supercateId || '-1');
				$('#searchSupercate').val(dataVal.supercateId || '-1');
				getSearchProductsData();
			} else {
				$('#searchSupercate').val('-1');
				$('#searchProduct').val('');
				initActiveItemNum();
				getProductsData();
			}
		}
		// tab delete
		$(document.body).on('click', '.delete-table-tab-item', deleteTableTabItem);
		// pagination a-click
		$(document.body).on('click', '#table-pagination li', function (e) {
			getTabSearchData($('.c-table-tab-item.active'));
		});
		// download google-ad data
		$('#download-google-ad').on('click', function() {
			$('#googleAdModal').modal('show');
		});
		$('#googleAdModal .btn-ok').on('click', function() {
			window.location.href = "${APP_PATH}/ExcleDownload/exportProUpdateWithInSomeDay?productMotifytime="+$('#google-ad-time').val();
		});
		// copy product
		$('.btn-copy').on('click', function () {
			$('#editModal').find('.modal-title').html('Copy one product...');
			$('#editModal').modal('show');
			// get parentCategory data
			getAllProductData(renderAllProductData, !0);

			$('#editModal .btn-ok').one('click', function () {
				var productId = $('#editModal').find('input:checked') && $('#editModal').find('input:checked').data('id');
				if (!productId) {
					toastr.warning('没有选择任何产品，无法完成拷贝！！！');
					return false;
				}
				copyOneProductData({
					'productId': productId
				}, function() {
					$('#editModal').modal('hide');
					updateSearchData();
				});
			});
		});
		// create product
		$('.btn-create').on('click', function () {
			$('.c-create .c-option-title').text('Create Product');
			showCreateBlock();
			// init formData
			resetFormData();
			// init option & skus-list			
			addProductOptionsTip();
			addProductSkusTip();
			getProductId();
			isCreate = true;
		});
		// edit product
		$(document.body).on('click', '.btn-edit', function (e) {
			var reqData = $(this).data('val');
			getOneProductData(reqData, function(resData) {
			 	$('.c-create .c-option-title').text('Edit Product');
				showCreateBlock();
				initFormData(resData);
			});
			// initial product option & skus
			getProductOptionsData({
				"productattrnamePid": reqData.productId
			}, function(resData) {
				renderProductOption(resData);
				getProductSkusData({
					"productskuPid": reqData.productId
				}, renderProductSkus)
			});
		});
		// enable batch product-sku
		$('.initial-product-sku input').tagsinput({
			allowDuplicates: true,
			onTagExists: function(item, $tag) {
				toastr.error('Youve already used the value "'+ item + '"');
			},
			trimValue: true
		});
		// delete product
		$(document.body).on('click', '.btn-delete', function (e) {
			var productId = parseInt($(this).data('id'));
			$('#deleteModal').find('.modal-title').html('Delete product!');
			$('#deleteModal').modal('show');
			$('#deleteModal .btn-ok').one('click', function () {
				deleteProductData({
					productId: productId,
				}, function() {
					getProductsData();
				});
			});
		});
		// save product
		$('.c-create .btn-save').on('click', function () {
			if (!hasSkuListStatus && checkSkuChangeStatus()) return;
			if (isSaveSku) {
				toastr.info('Need to save all product Skus!');
				return;
			}
			if (parseInt($('#productSupercateid').val()) < 0) {
				toastr.info('Please Select super-category!');
				$('#productSupercateid').focus();
				$('#productStatus').prop('checked', false);
				return;
			}
			saveProductData(getFormData(), function() {
				// redirect tab-active & then search-data
				if (isCreate) {
					isCreate = false;
					initActiveItemNum();
					$('.c-table-tab-item').removeClass('active').eq(0).addClass('active');
				}

				getTabSearchData($('.c-table-tab-item.active'));
				showInitBlock();
				$('#productId').val('');
				// hide batch product-sku
				$('.initial-product-sku').addClass('hide');
				hasInitSku = false;
			});
		});
		// cancel product save
		$('.c-create .btn-cancel').on('click', function () {
			if (!hasSkuListStatus && checkSkuChangeStatus()) return false;
			if (isCreate) {
				isCreate = false;
				/* initActiveItemNum(); */
				// delete null product
				deleteProductData({
					productId: $('#productId').val(),
				}, function() {
					console.log("cancel create-product");
				});
				// fetch default product
				// getProductsData();
			} else {
				if (isSaveSku && $('.product-sku-item').length) {
					toastr.info('Need to save all product Skus!');
					$('.all-product-sku-save').focus();
					return false;
				}
			}
			showInitBlock();
			// hide batch product-sku
			$('.initial-product-sku').addClass('hide');
			hasInitSku = false;
		});
		$(window).on('beforeunload', function() {
			if (!hasSkuListStatus && checkSkuChangeStatus()) return false;
			if (isCreate) {
				// delete null product
				var productId = $('#productId').val();
				productId && deleteProductData({
					productId: productId,
				});
			} else {
				if (isSaveSku && $('.product-sku-item').length) {
					toastr.info('Need to save all product Skus!');
					$('.all-product-sku-save').focus();
					return false;
				}
			}
			// hide batch product-sku
			$('.initial-product-sku').addClass('hide');
			hasInitSku = false;
		});
		// supercate & productStatus combinewith
		$('#productSupercateid').on('change', function(e) {
			if (parseInt($(this).val()) < 0) {
				$('#productStatus').prop('checked', false);
			}
		});
		// eidt product category
		$('#editProductCategory').on('click', function() {
			$('#editModal').find('.modal-title').html('select category for product');
			$('#editModal').modal('show');
			// get parentCategory data
			getAllCollectionData(renderParentCategory);
			$('#editModal .btn-ok').one('click', function () {
				var checkData = getSelectedCategoryData();
				$('#productCategoryIdsstr').val(checkData.productCategoryIds.join(','));
				$('#productCategoryNamesstr').val(checkData.productCategoryNames.join(','));
				$('#productCategoryList').val(checkData.productCategoryNames.join('\n'));
				$('#editModal').modal('hide');
			});
		});
		function getSelectedCategoryData() {
			var checkedInputs = $('#editModal').find('input:checked');
			var resData = {
					productCategoryIds: [],
					productCategoryNames: []
			};
			for (var i = 0, len = checkedInputs.length; i < len; i+=1) {
				resData.productCategoryIds.push($(checkedInputs[i]).data('id'));
				resData.productCategoryNames.push($(checkedInputs[i]).data('name'));
			}
			return resData;
		}
		// initial product option
		function getProductOptionsData(reqData, callback) {
			// getMlbackProductAttributeNameListByProductId
			$.ajax({
				url: "${APP_PATH }/MlbackProductAttributeName/getMlbackProductAttributeNameListByProductId",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				data: JSON.stringify(reqData),
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend.mbackProductAttributeNameResList);
						toastr.success(data.extend.resMsg);
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
		// add product option
		$('.product-option-add').on('click', function() {
			var sortNum = $('.product-options-item').length ? $('.product-options-item').last().data('sort') + 1 : 1;
			createOptionItem({
				"productattrnameSort": sortNum
			}, true, hasInitSku);
		});
		// save product option
		$(document.body).on('click', '.product-option-save', function() {
			var $this = $(this);
			var parentEl = $this.parents('.product-options-item');
			var optionName = parentEl.find('.product-option-name').val();
			var optionVal = parentEl.find('.product-option-values').val();
			var optionSort = parentEl.data('sort');
			// check option name/value is or not empty
			if (!optionName.trim()) {
				toastr.error('Product-option-name cannot be empty！');
				return;
			}
			if (!optionVal.trim()) {
				toastr.error('Product-option-values cannot be empty！');
				return;
			}

			// save option data & update skus list
			saveProductOption({
				"productattrnameName": optionName,
				"productattrnameId": parentEl.data('id'),
			    "productattrnameSort": optionSort,
			    "productattrnameValues": optionVal,
				"productattrnamePid": $('#productId').val()
			}, function(data) {
				parentEl.data('id', data.productattrnameId);
				if (isCreate) {
					renderProductSkus(getOptionData(), true);
				} else {
					$this.hide();
					isSaveSku = true;
					$('.product-sku-item').each(function(idx, item) {
						var itemText = $(item).data('skuname').split(',');
						itemText.push(optionVal);
						$(item).data('skuname', itemText.join(',')).find('.product-sku-name').text(itemText.join('/'));
					});
				}
			});
		});
		// listen sku-list scroll
		$('.product-sku-body').on('scroll', function(e) {
			var delay = 100;
			var newTime = Date.now();
			if (newTime - oldTime < delay) clearTimeout(timer);
			oldTime = newTime;
			timer = setTimeout(function() {
				$('.product-sku-head .product-sku-th').css('margin-left', '-' +  e.target.scrollLeft + 'px');				
			}, delay);
		});
		// render product skus in sav
		function renderProductSkus(data, flag) {
            var htmlStr = '';
            data = flag ? generateSkus(data) : data;
            if (data.length) {
	            data.forEach(function(item) {
	            	var skuName = item.productskuName ? item.productskuName.replace(/\,/g, '/') : item.join('/');
	            	htmlStr += '<div class="product-sku-item" data-id="'+ (item.productskuId ? item.productskuId : '') +'" data-skuname="'+ (item.productskuName || item.join(',')) +'" data-img="'+ (item.productskuOneImg ? item.productskuOneImg : '') +'">'+
	            		'<div class="product-sku-td product-sku-name">'+ skuName +'</div>' +
	            		'<div class="product-sku-td product-sku-img c-upload-img'+ (item.productskuOneImg ? ' valid active' : '') +'">' +
							'<svg class="c-icon">' +
								'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image-plus"></use>' +
							'</svg>' +
							'<div class="c-backshow">'+ (item.productskuOneImg ? '<img src="'+ encodeUrl(item.productskuOneImg) + '" />' : '') +'</div>' +
							'<input type="file" />' +
							'<div class="spinner">' +
								'<div class="spinner-border" role="status" aria-hidden="true"></div>' +
							'</div>' +
						'</div>' +
	            		'<input type="text" class="product-sku-td product-sku-stock" data-stock="'+ item.productskuStock +'" value="'+ (item.productskuStock ? item.productskuStock : 0) +'"/>' +
	            		'<input type="text" class="product-sku-td product-sku-price" data-price="'+ item.productskuMoney +'" value="'+ (item.productskuMoney ? item.productskuMoney : 0) +'"/>' +
	            		'<input type="text" class="product-sku-td product-sku-sku" data-sku="'+ item.productskuCode +'" value="'+ (item.productskuCode ? item.productskuCode : '') +'"/>' +
	            		'<div class="product-sku-td product-sku-operate">'+ 
		            		'<button class="btn btn-primary product-sku-edit" style="display: '+ (isCreate ? 'none' : 'inherit') +';">' +
			        			'<svg class="c-icon">' +
									'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-pencil"></use>' +
								'</svg>' +
							'</button>' +
	            			'<button class="btn btn-primary product-sku-delete">' +
		            			'<svg class="c-icon">' +
									'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-trash"></use>' +
								'</svg>' +
							'</button>' +
	            		'</div>'
	            	+'</div>';
	            });
	            !isCreate && (hasInitSku = true);
	            $('.initial-product-sku').removeClass('hide');
            } else {
            	htmlStr = '<p class="text-center"> no skus ... </p>';
            }
            
            $('.product-sku-body').html(htmlStr);
		}
		// check sku change or not
		function checkSkuChangeStatus() {
			var flag = false;
			var $productskuitem = $('.product-sku-item');
			for (var i = 0, len = $productskuitem.length; i < len; i += 1) {
				var $item = $($productskuitem[i]);
				if ($item.find('.product-sku-stock').data('stock') != $item.find('.product-sku-stock').val()) {
					flag = true;
					break;
				}
				if ($item.find('.product-sku-price').data('price') != $item.find('.product-sku-price').val()) {
					flag = true;
					break;
				}
				if ($item.find('.product-sku-sku').data('sku') != $item.find('.product-sku-sku').val()) {
					flag = true;
					break;
				}
			}
			
			if (flag) {
				var st = confirm("Are you sure you don't want to save updated SKU list data !");
				if (!st) {
					$('.product-sku-data-operate')[0].scrollIntoView();
					$('.all-product-sku-save').focus();
					return true;					
				} else {
					hasSkuListStatus = true;
					return false;
				}
			}
		}
		// generate skus
		function generateSkus(data) {
			var skuArrs = [];
			(function cb(res, a, n) {  
                if (n == 0) return res.length ? skuArrs.push(res) : skuArrs;
                for (var i= 0, len=a[n-1].length; i < len; i++) {
                    cb(res.concat(a[n-1][i]), a, n - 1);  
                }
            })([], data.reverse(), data.length);
			return skuArrs;
		}
		// get option data
		function getOptionData() {
			var optionVal = [];
			$('.product-option-values').each(function(idx, item) {
				if ($(item).parents('.product-options-item').data('id')) optionVal.push($(item).val().split(','));
			});
			return optionVal;
		}
		// remove product option
		$(document.body).on('click', '.product-option-remove', function() {
			var parentEl = $(this).parents('.product-options-item');
			var optionVal = parentEl.find('.product-option-values').val();
			var optionId = parentEl.data('id');

			if (!optionId) {
				parentEl.remove();
				if (!$('.product-options-item').length) {
					addProductOptionsTip();
					addProductSkusTip();
				}
				return false;
			}
			
			deleteProductOption({
				"productattrnameId": optionId
			}, function() {
				parentEl.remove();
				if (!$('.product-options-item').length) {
					addProductOptionsTip();
					addProductSkusTip();
				}
				if (isCreate) {
					renderProductSkus(getOptionData(), true);					
				} else {
					$('.product-sku-item').each(function(idx, item) {
						var itemText = $(item).data('skuname').split(',');
						itemText.splice(itemText.indexOf(optionVal), 1);
						$(item).data('skuname', itemText.join(',')).find('.product-sku-name').text(itemText.join('/'));
					});
				}
			});
		});
		// delete product option
		function deleteProductOption(reqData, callback) {
			$('.c-mask').show(); 
			$.ajax({
				url: "${APP_PATH }/MlbackProductAttributeName/delete",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				data: JSON.stringify(reqData),
				success: function (data) {
					if (data.code == 100) {
						callback();
						toastr.success(data.extend.resMsg);
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
		// save product option
		function saveProductOption(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/MlbackProductAttributeName/save",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				data: JSON.stringify(reqData),
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend.mlbackProductAttributeName || reqData.productattrnameId);
						toastr.success(data.extend.resMsg);
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
		// edit product sku
		$(document.body).on('click', '.product-sku-edit', function() {
			var parentEl = $(this).parents('.product-sku-item');
			productSkuModal({
				'id': parentEl.data('id'),
				'stock': parentEl.find('.product-sku-stock').val(),
				'price': parentEl.find('.product-sku-price').val(),
				'sku': parentEl.find('.product-sku-sku').val(),
				'name': parentEl.data('skuname').split(','),
				'img': parentEl.data('img'),
			});
		});
		// initial product skus
		$('.initial-product-sku .btn').on('click', function() {
			var $allVal = $(this).siblings('input');
			var skuItemCls = $allVal.data('cls');
			var skuValArr = $allVal.val().split(',');
			var $productSkuItems = $('.' + skuItemCls);

			if (!skuValArr.length) {
				toastr.warnig('Please input data before batch operation !');
				return;
			}
			
			if (skuValArr.length == 1) {
				if (confirm('Are you sure you want to fill all SKUs with the same data?')) {
					$productSkuItems.val(skuValArr[0]);
				}
				return false;
			}
			
			if ($productSkuItems.length > skuValArr.length && skuValArr.length > 1) {
				toastr.error('The batch filled data is less than the number of existing SKUs !');
				return;
			}
			
			if ($productSkuItems.length < skuValArr.length && skuValArr.length > 1) {
				toastr.error('The batch filled data is greater than the number of existing SKUs !');
				return;
			}

			$productSkuItems.each(function(idx, item) {
				$(item).val(skuValArr[idx]);
			});
			toastr.error('The batch filled data successful, please save the data later !');
		});
		// add one product sku
		$('.product-sku-add').on('click', function() {
			if (isCreate) {
				toastr.warning('Create product status, the function is not available !');
			} else {
				productSkuModal();
			}
		});
		// product sku form modal
		function productSkuModal(data) {
			getProductOptionsData({
				"productattrnamePid": $('#productId').val(),
			}, function(resData) {
				// reset skuModal input
				var htmlStr = '';
				resData.forEach(function(item, idx) {
					htmlStr += '<div class="form-group">' +
						'<label class="col-form-label" for="'+ item.productattrnameName +'">'+ item.productattrnameName +'</label>' +
						'<div class="controls">' +
							 '<input class="form-control" id="'+ item.productattrnameName +'" type="text" value="'+ (data ? data.name[idx] : '') +'">' +
						'</div>' +
					'</div>';
				});
				$('#productSkuName').html(htmlStr);
				if (data) {
					$('#productSkuId').val(data.id || '');
					if (data.img) {
						addPicture($("#productSkuImg"), {
							imageUrl: data.img,
							thumImageUrl: data.img
						});
					} else {
						resetPicture($("#productSkuImg"));
					}
					$('#productSkuStock').val(data.stock || 0);
				    $('#productSkuPrice').val(data.price || 0);
				    $('#productSkuSku').val(data.sku || '');
				} else {
					$('#productSkuId').val('');
					resetPicture($("#productSkuImg"));
					$('#productSkuStock').val(0);
				    $('#productSkuPrice').val(0);
				    $('#productSkuSku').val('');
				}
				$('#skuModal').modal('show');
			});
		}
		// save one product sku
		$(document.body).on('click', '#skuModal .btn-save', function() {
			var skuName = [];
			var productId = $('#productId').val();
			$('#productSkuName input').each(function(idx, item) {
				var val = $(item).val().trim();
				if (!val) {
					toastr.warning('Option: '+ $('.product-option-name').eq(idx).text() +' can not be empty!');
				} else {
					skuName.push(val);
				}
			});
			if (skuName.length < $('#productSkuName input').length) return;
			skuName.forEach(function(val, idx) {
				var optionValEl = $('.product-option-values').eq(idx);
				if (optionValEl.val().indexOf(val) < 0) {
					var parentEl = optionValEl.parents('.product-options-item');
					var optionName = parentEl.find('.product-option-name').val();
					var optionVal = parentEl.find('.product-option-values').val().split(',');
					var optionSort = parentEl.data('sort');
					optionVal.push(val);
					saveProductOption({
						"productattrnameName": optionName,
						"productattrnameId": parentEl.data('id'),
					    "productattrnameSort": optionSort,
					    "productattrnameValues": optionVal.join(','),
						"productattrnamePid": productId
					}, function(data) {
						optionValEl.tagsinput('add', val.trim());
					});
				}
			});
			
			saveProductSkuData({
			    "productskuPid": productId,
				"productskuId": $('#productSkuId').val(),
				"productskuName": skuName.join(','),
			    "productskuStock": $('#productSkuStock').val(),
			    "productskuMoney": $('#productSkuPrice').val(),
			    "productskuCode": $('#productSkuSku').val(),
			}, function(data) {
				$('#skuModal').modal('hide');
				getProductSkusData({
					"productskuPid": productId
				}, renderProductSkus)
			});
		});
		// reset product skus
		$('.product-sku-reset').on('click', function() {
			if ($('.product-sku-item').length) {
				toastr.info('Sku data cannot be reset while it exists!');
				return false;
			}
			renderProductSkus(getOptionData(), true);
		});
		// save all product-sku
		$('.all-product-sku-save').on('click', function() {
			function getProductSkus() {
				var skusArr = [];
				$('.product-sku-item').each(function(idx, item) {
					skusArr.push({
						"productskuId": ($(item).data('id') || null),
						"productskuName": $(item).data('skuname'),
					    "productskuStock": $(item).find('.product-sku-stock').val(),
					    "productskuMoney": $(item).find('.product-sku-price').val(),
					    "productskuCode": $(item).find('.product-sku-sku').val(),
					});
				});
				return skusArr;
			}

			var formData = new FormData();
			var productId = $('#productId').val();
			formData.append("productskuPid", productId);
			formData.append("teams", JSON.stringify(getProductSkus()));

			saveProductSkusData(formData, function(data) {
				isSaveSku = false;
				getProductSkusData({
					"productskuPid": productId
				}, renderProductSkus)
			});
		});
		$('#custom-pic-order').on('click', function() {
			getProductAllImgData({
				productId: $('#productId').val()
			}, function(data) {
				var imgsHtml = '';
				if (data.length) {
					data.forEach(function(item, idx) {
						imgsHtml += '<div class="product-imgs-sort-item" data-id="'+ item.productimgId +'"><img src="'+ item.productimgUrl +'"></div>';
					});
					$('#imgModal').data('len', data.length).find('.left-panel').html(imgsHtml).end().find('.right-panel').html('').end().modal('show');					
				} else {
					toastr.warning('没有图片可进行排序！！！');
				}
			});
		})
		$('#imgModal .btn-save').on('click', function() {
			var productImgsArr = [];
			var productImgsItem = $('#imgModal .right-panel .product-imgs-sort-item');
			if (productImgsItem.length < $('#imgModal').data('len')) {
				toastr.error('未完成排序，请继续操作');
				return ;
			}
			productImgsItem.each(function(idx, item) {
				productImgsArr.push({
					"productimgId": ($(item).data('id') || null),
					"productimgSortOrder": (idx + 1),
				});
			});
			var formData = new FormData();
			var productId = $('#productId').val();
			formData.append("productId", productId);
			formData.append("teams", JSON.stringify(productImgsArr));
			saveProductImgsData(formData, function(data) {
				$('#imgModal').modal('hide');
				getProductAllImgData({
					productId: productId
				}, renderProductAllData);
			});
		});
		$(document.body).on('click', '#imgModal .left-panel .product-imgs-sort-item', function() {
			$('#imgModal .right-panel').append($(this));
		});

		$(document.body).on('click', '#imgModal .right-panel .product-imgs-sort-item', function() {
			$('#imgModal .left-panel').append($(this));
		});
		// delete product img
		$(document.body).on('click', '.product-img-delete', function() {
			var productItem = $(this).parent();
			var dataVal = productItem.find('.productAllImgurl').data('val');

			var productImgId = dataVal ? dataVal.imgId : null;
			if (!productImgId) {
				toastr.error('无法删除图片');
				return ;
			}
			$('#deleteModal').find('.modal-title').html('Delete product Img!');
			$('#deleteModal').modal('show');
			$('#deleteModal .btn-ok').one('click', function () {
				deleteProductImgData({
					productimgId: productImgId,
				}, function(data) {
					var imgsLen = $('.product-img-item.valid').length;
					var count = $('.product-img-item').last().find('.productAllImgurl').data('order');
					productItem.remove();
					if (imgsLen >= 6) addUploadBlock(count + 1);
				});
			});
		});
		// callback delete
		function deleteProductImgData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackProductImg/delete",
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
		// delete product sku
		$(document.body).on('click', '.product-sku-delete', function() {
			var parentEl = $(this).parents('.product-sku-item');
			var skuId = parentEl.data('id') || null;
			function deleteSkuItem(el) {
				el.remove();
			}
			if (skuId) {
				deleteProductSkuData({ "productskuId": skuId }, function() {
					deleteSkuItem(parentEl);
					!$('.product-sku-item').length && (addProductSkusTip(), $('.initial-product-sku').addClass('hide'), hasInitSku = false);
				});
			} else {
				deleteSkuItem(parentEl);
				toastr.success('delete product sku success!');
				!$('.product-sku-item').length && (addProductSkusTip(), $('.initial-product-sku').addClass('hide'));
			}
		});
		// get product sku-list
		function getProductSkusData(reqData, callback) {
			$('.c-mask').show(); 
			$.ajax({
				url: "${APP_PATH }/MlbackProductSku/getMlbackProductSkuListByPId",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				data: JSON.stringify(reqData),
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend.mlbackProductSkuResList);
						toastr.success(data.extend.resMsg);
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
		// save product sku-list
		function saveProductSkusData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/MlbackProductSku/productSkuListInsert",
				type: "post",
				dataType: "json",
				processData: false,
				contentType: false,
				cache: false,
				data: reqData,
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend.mlbackProductSkuResList);
						toastr.success(data.extend.resMsg);
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
		function saveProductImgsData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/MlbackProductImg/productImgUpdateSort",
				type: "post",
				dataType: "json",
				processData: false,
				contentType: false,
				cache: false,
				data: reqData,
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend);
						toastr.success(data.extend.resMsg);
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
		// save product sku-data
		function saveProductSkuData(reqData, callback) {
			$('.c-mask').show(); 
			$.ajax({
				url: "${APP_PATH }/MlbackProductSku/save",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				data: JSON.stringify(reqData),
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend.mlbackProductSku);
						toastr.success(data.extend.resMsg);
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
		// delete product sku-data
		function deleteProductSkuData(reqData, callback) {
			$('.c-mask').show(); 
			$.ajax({
				url: "${APP_PATH }/MlbackProductSku/delete",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				data: JSON.stringify(reqData),
				success: function (data) {
					if (data.code == 100) {
						callback && callback();
						toastr.success(data.extend.resMsg);
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
		// render product option
		function renderProductOption(data) {
			if (data.length) {
				$('.product-options').html('');
				data.forEach(function(item) {
					createOptionItem(item);
				});				
			} else {
				addProductOptionsTip();
			}
		}
		// add product-options tip
		function addProductOptionsTip() {
			$('.product-options').html('<p class="text-center"> no option, please add one option to generate skus. </p>');
		}
		function addProductSkusTip() {
			$('.product-sku-body').html('<p class="text-center"> no skus ... </p>');
		}
		// create option item
		function createOptionItem(data, flag, flag2) {
			var productSkuNums = $('.product-sku-item').length;
			$('.product-options .text-center').remove();
			// flag, 0/nul/undefined, create Prodcut/inital product create option; 1, edit-product initial option; 2, edit-product create option 
			var optionItem = $('<div class="product-options-item" data-id="'+ (data.productattrnameId || '') +'" data-sort="'+ (data.productattrnameSort || '') +'">' +
				'<div class="product-option-head">' +
					'<div class="product-option-title">Option '+ ($('.product-options-item').length + 1) +'</div>' +
					'<div style="display: '+ (flag ? 'block': 'none') + '">' +
						'<a href="javascript:;" class="product-option-save" style="margin-right: 1rem;">Save</a>' +
						'<a href="javascript:;" class="product-option-remove">Remove</a>' +
					'</div>' +
				'</div>' +
				'<div class="product-option-body">' +
					'<input class="product-option-name" type="text" value="'+ (data.productattrnameName || "") +'" />' +
					'<input class="product-option-values" type="text" value="'+ (data.productattrnameValues || "") +'" />' +										
				'</div>' +
			'</div>');
			if (!flag2) {
				optionItem.find('.product-option-values')
					.tagsinput({
						onTagExists: function(item, $tag) {
							toastr.error('Youve already used the option "'+ item + '"');
						},
						trimValue: true
					});
			} else {
				optionItem.find('.product-option-values')
					.tagsinput({
						onTagExists: function(item, $tag) {
							toastr.error('Youve already used the option "'+ item + '"');
						},
						maxTags: 1,
						trimValue: true
					});
			}
			optionItem.find('.product-option-values')
				.tagsinput({
					onTagExists: function(item, $tag) {
						toastr.error('Youve already used the option "'+ item + '"');
					},
					trimValue: true
				});
			$('.product-options').append(optionItem);
		}
		// upload main img
		$('#productImgurl').on('change', function(e) {
			var $this = $(this);
			var file = $this[0].files[0];
			var formData = new FormData();

			if (!file) return false;

			$this.parent().find('.spinner').show();
			$this.val('');

			formData.append('type', 'product');
			formData.append('image', file);
			formData.append('productId', parseInt($('#productId').val()));
			formData.append('productSeo', $('#productSeo').val());

			$.ajax({
				url: "${APP_PATH}/ImageUpload/thumImageProduct",
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
							thumImageUrl: data.extend.sqlimageUrl,
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
		// upload discount img
		$('#productDiscoutimgurl').on('change', function(e) {
			var $this = $(this);
			var file = $this[0].files[0];
			var formData = new FormData();

			if (!file) return false;

			$this.parent().find('.spinner').show();
			$this.val('');

			formData.append('type', 'productDiscout');
			formData.append('image', file);
			formData.append('productId', parseInt($('#productId').val()));

			$.ajax({
				url: "${APP_PATH}/ImageUpload/productDiscount",
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
		// upload sku img
		$(document.body).on('change', '.product-sku-img input', function(e) {
			var $this = $(this);
			var file = $this[0].files[0];
			var formData = new FormData();
			var productSkuId = $this.parents('.product-sku-item').data('id');

			if (!productSkuId) {
				toastr.warning('产品sku未保存，保存后，方可上传对应的sku图。。。');
				return false;
			}

			if (!file) return false;

			$this.parent().find('.spinner').show();
			$this.val('');

			formData.append('type', 'proSku');
			formData.append('image', file);
			formData.append('productskuPid', parseInt($('#productId').val()));
			formData.append('productskuId', parseInt(productSkuId));

			$.ajax({
				url: "${APP_PATH}/ImageUpload/uploadProSkuImg",
				type: "post",
				data: formData,
				processData: false,
				contentType: false,
				cache: false,
				dataType: 'json',
				success: function (data) {
					if (data.code == 100) {
						$this.parents('.product-sku-item').data('img', data.extend.sqlimageUrl).find('.product-sku-img').addClass('active').find('.c-backshow').html('<img src="'+ encodeUrl(data.extend.sqlimageUrl) + '" />');
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
		// upload sku img
		$(document.body).on('change', '#productSkuImg', function(e) {
			var $this = $(this);
			var file = $this[0].files[0];
			var formData = new FormData();
			var productSkuId = $('#productSkuId').val();

			if (!productSkuId) {
				toastr.warning('产品sku未保存，保存后，方可上传对应的sku图。。。');
				return false;
			}

			if (!file) return false;

			$this.parent().find('.spinner').show();
			$this.val('');

			formData.append('type', 'proSku');
			formData.append('image', file);
			formData.append('productskuPid', parseInt($('#productId').val()));
			formData.append('productskuId', parseInt(productSkuId));

			$.ajax({
				url: "${APP_PATH}/ImageUpload/uploadProSkuImg",
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
							thumImageUrl: data.extend.sqlimageUrl,
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
		// video preview
		$(document.body).on('click', '.video-preview', function() {
			var videoModal = $('#videoModal');
			var videoEl = videoModal.find('video');
			videoEl[0].src = $('#productVideourl').data('val').videoUrl;
			videoModal.modal('show');
		});
		// upload vieo
		$('#productVideourl').on('change', function(e) {
			var $this = $(this);
			var videoFile = $this[0].files[0];
			var productId = parseInt($('#productId').val());
			var resVideoData = {};

			if (!videoFile) return false;

			$this.parent().find('.spinner').show();
			$this.val('');

			generateVideoPoster(videoFile, function(data) {
				// poster
				var posterFormData = new FormData();            	
				posterFormData.append('type', 'productVideo');
				posterFormData.append('image', data);
				posterFormData.append('productId', productId);

            	$.ajax({
    				url: "${APP_PATH}/ImageUpload/thumProVideoImage",
    				type: "post",
    				data: posterFormData,
    				processData: false,
    				contentType: false,
    				cache: false,
    				dataType: 'json',
    				async: false,
    				success: function (data) {
    					if (data.code == 100) {
    						resVideoData.imageUrl = data.extend.sqlimageUrl;
    						resVideoData.thumImageUrl = data.extend.sqlimageUrl;
    					} else {
    						toastr.error('网络错误， 请稍后重试！');	
    					}
    				},
    				error: function (err) {
    					toastr.error(err);
    				}
    			});
            	// video
    			var videoFormData = new FormData();
    			videoFormData.append('file', videoFile);
    			videoFormData.append('productId', productId);
    			videoFormData.append('productSeo', $('#productSeo').val());
 
    			$.ajax({
    				url: "${APP_PATH}/VideoUpload/uploadProSmallVideo",
    				type: "post",
    				data: videoFormData,
    				processData: false,
    				contentType: false,
    				cache: false,
    				dataType: 'json',
    				success: function (data) {
    					if (data.code == 100) {
    						resVideoData.videoUrl = data.extend.videoUrl;
    						addPicture($this, resVideoData);
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
		});
		function generateVideoPoster(file, callback) {
			var videoEl = document.createElement('video'),
	            mimeType = file.type;
	        videoEl.controls = true;
	        videoEl.width = 200;
	        videoEl.height = 200;
	        videoEl.src = URL.createObjectURL(file);
	        videoEl.currentTime = 1;
	        videoEl.addEventListener('canplay', function(e) {
	            var video = e.target;
	            var canvas = document.createElement('canvas'),
	                ctx = canvas.getContext('2d'),
	                width = video.videoWidth,
	                height = video.videoHeight,
	                videoRatio = width / height,
	                offsetLeft = 0,
	                offsetTop = 0,
	                outputWidth = 500,
	                outputHeight = 500;
	            
	            canvas.width = outputWidth;
	            canvas.height = outputHeight;
	            if (videoRatio > 1) {
	                width = outputWidth;
	                height = parseInt(outputWidth / videoRatio);
	                offsetTop = parseInt((outputHeight - height) / 2);
	            } else if (videoRatio == 1) {
	                width = outputWidth;
	                height = outputHeight;
	            } else {
	                height = outputHeight;
	                width = parseInt(outputHeight * videoRatio);
	                offsetLeft = parseInt((outputWidth - width) / 2);
	            }
	            ctx.drawImage(video, offsetLeft, offsetTop, width, height);
	            canvas.toBlob(function(blob) {
	            	callback(blob);
	            }, 'image/png');
	        }, { once: true });
		}
		// upload details img
		$(document.body).on('change', '.productAllImgurl', function(e) {
			var $this = $(this);
			var file = $this[0].files[0];
			var formData = new FormData();

			if (!file) return false;

			$this.parent().find('.spinner').show();
			$this.val('');

			formData.append('imageAll', file);
			formData.append('productId', parseInt($('#productId').val()));
			formData.append('productimgSortOrder', $this.data('order'));

			$.ajax({
				url: "${APP_PATH}/ImageUpload/thumImageProductAll",
				type: "post",
				data: formData,
				processData: false,
				contentType: false,
				cache: false,
				dataType: 'json',
				success: function (data) {
					if (data.code == 100) {
						var count = $('.product-img-item').last().find('.productAllImgurl').data('order');
						if ($('.product-img-item').length < 6 && !$this.attr('data-val')) {
							addUploadBlock(count + 1);
						}
						addPicture($this, {
							imageUrl: data.extend.sqlimageUrl,
							thumImageUrl: data.extend.sqlimageUrl,
							imgId: (data.extend.mlbackProductImg ? data.extend.mlbackProductImg.productimgId : '')
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
			parentEl.addClass('valid');
			parentEl.addClass('active');
			parentEl.find('.c-backshow').html('<img src="'+ encodeUrl(data.thumImageUrl) + '" />').end().find('.product-img-delete').removeClass('hide');
		}
		function resetPicture(el) {
			var parentEl = el.parent();
			el.attr('data-val', '');
			parentEl.removeClass('active');
			parentEl.find('.c-backshow').html('').end().find('.product-img-delete').addClass('hide');
		}
		function addUploadBlock(idx) {
			var htmlStr = '<div class="product-img-item c-upload-img">' +
				'<svg class="c-icon">' +
					'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image-plus"></use>' +
				'</svg>' +
				'<div class="product-img-delete hide">' +
					'<svg class="c-icon">' +
						'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-trash"></use>' +
					'</svg>' +
				'</div>' +
				'<div class="c-backshow"></div>' +
				'<input class="productAllImgurl" data-order="'+ idx +'" type="file" accept="image/png, image/jpeg, image/gif" />' +
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
			$('#productId').val('');
			$('#productName').val('');
			$('#productStatus').prop('checked', false);
			$('#productLable').val('0');

			$('#productDesc').summernote('reset');

			$('#productOriginalprice').val('0.00');
			$('#productActoffoff').val('0');
			resetPicture($('#productDiscoutimgurl'));
			$('#productDiscoutimgShow').prop('checked', false);
			
			resetPicture($('#productImgurl'));
			resetPicture($('#productVideourl'));

			// reset product-img-list
			$('.product-img-list').html('');
			addUploadBlock(1);

			$('#productSupercateid').val('-1');
			$('#productCategoryIdsstr').val('');
			$('#productCategoryNamesstr').val('');
			$('#productCategoryList').val('No category!');

			$('#productHavesalenum').val('0');
			$('#productReviewnum').val('0');

			$('#productSeo').val('');
			$('#productMetaTitle').val('');
			$('#productMetaKeywords').val('');
			$('#productMetaDesc').val('');
		}
		// getFormdData
		function getFormData() {
			var data = {};
			data.productId = parseInt($('#productId').val());
			data.productName = $('#productName').val();
			data.productStatus = $('#productStatus').prop('checked') ? 1 : 0;
			data.productLable = parseInt($('#productLable').val());
			data.productDesc = $('#productDesc').val();
			
			data.productOriginalprice = $('#productOriginalprice').val();
			data.productActoffoff = $('#productActoffoff').val();

			var discountImageData = $('#productDiscoutimgurl').attr('data-val') && JSON.parse($('#productDiscoutimgurl').attr('data-val'));
			data.productDiscoutimgurl = discountImageData.imageUrl;
			data.productDiscoutimgShow = $('#productDiscoutimgShow').prop('checked') ? 1 : 0;

			var imageData = $('#productImgurl').attr('data-val') && JSON.parse($('#productImgurl').attr('data-val'));
			data.productMainimgurl = imageData.imageUrl || null;
			data.productMainimgsmallurl = imageData.thumImageUrl || null;

			var videoData = $('#productVideourl').attr('data-val') && JSON.parse($('#productVideourl').attr('data-val'));
			data.productVideoImgUrl = videoData.imageUrl || null;
			data.productVideoUrl = videoData.videoUrl || null;

			data.productSupercateid = $('#productSupercateid').val();
			data.productSupercatename = $('#productSupercateid').find('option:checked').text();
			data.productCategoryIdsstr = $('#productCategoryIdsstr').val();
			data.productCategoryNamesstr = $('#productCategoryNamesstr').val();

			data.productHavesalenum = $('#productHavesalenum').val();
			data.productReviewnum = $('#productReviewnum').val();

			data.productSeo = $('#productSeo').val();
			data.productMetaTitle = $('#productMetaTitle').val();
			data.productMetaKeywords = $('#productMetaKeywords').val();
			data.productMetaDesc = $('#productMetaDesc').val();

			return data;
		}
		// initFormData
		function initFormData(data) {
			$('#productId').val(data.productId);
			$('#productName').val(data.productName);
			$('#productStatus').prop('checked', data.productStatus);
			$('#productLable').val(data.productLable);
			$('#productDesc').summernote('code', data.productDesc);

			$('#productOriginalprice').val(data.productOriginalprice || 0.00);
			$('#productActoffoff').val(data.productActoffoff || 0);
			if (data.productDiscoutimgurl) {
				addPicture($('#productDiscoutimgurl'), {
					imageUrl: data.productDiscoutimgurl,
					thumImageUrl: data.productDiscoutimgurl
				});
			} else {
				resetPicture($('#productDiscoutimgurl'));
			}
			$('#productDiscoutimgShow').prop('checked', data.productDiscoutimgShow);

			if (hasSuperCateList) {
				// value
				$('#productSupercateid').val(data.productSupercateid || '-1');
			}
			// attr
			$('#productSupercateid').attr('data-val', data.productSupercateid || '-1');

			$('#productCategoryIdsstr').val(data.productCategoryIdsstr);
			$('#productCategoryNamesstr').val(data.productCategoryNamesstr);
			$('#productCategoryList').val(data.productCategoryNamesstr ? ' * ' + data.productCategoryNamesstr.replace(/\,/g, '\n * ') : 'No category！');

			if (data.productMainimgurl) {
				addPicture($('#productImgurl'), {
					imageUrl: data.productMainimgurl,
					thumImageUrl: data.productMainimgurl
				});
			} else {
				resetPicture($('#productImgurl'));
			}
			
			if (data.productVideoImgUrl && data.productVideoUrl) {
				addPicture($('#productVideourl'), {
					imageUrl: data.productVideoImgUrl,
					thumImageUrl: data.productVideoImgUrl,
					videoUrl: data.productVideoUrl
				});
			} else {
				resetPicture($('#productVideourl'));
			}
			
			getProductAllImgData({
				productId: data.productId
			}, renderProductAllData);

			$('#productHavesalenum').val(data.productHavesalenum);
			$('#productReviewnum').val(data.productReviewnum);			

			$('#productSeo').val(data.productSeo);
			$('#productMetaTitle').val(data.productMetaTitle);
			$('#productMetaKeywords').val(data.productMetaKeywords);
			$('#productMetaDesc').val(data.productMetaDesc);
		}
		// callback get id
		function getProductId() {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/MlbackProduct/initializaProduct",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				success: function (data) {
					if (data.code == 100) {
						var productId = data.extend&& data.extend.mlbackProduct && data.extend.mlbackProduct.productId;
						if (productId) {
							$('#productId').val(data.extend.mlbackProduct.productId);
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
		function getProductsData() {
			$('.c-mask').show();

			var formData = new FormData();
			formData.append('pn', getPageNum());

			$.ajax({
				url: "${APP_PATH }/MlbackProduct/getMlbackProductByPage",
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
					toastr.error('Failed to get Products, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback get one data
		function getOneProductData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/MlbackProduct/getOneMlbackProductDetail",
				type: "post",
				data: JSON.stringify(reqData),
				dataType: 'json',
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend.mlbackProductOne);
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
		function getSearchProductsData(data) {
			$('.c-mask').show();

			var formData = new FormData();
			formData.append('productName', $('#searchProduct').val());
			formData.append('productSupercateid', ($('#searchSupercate').attr('data-val') || '-1'));
			formData.append('pn', getPageNum());

			$.ajax({
				url: "${APP_PATH }/MlbackProduct/backSearchByProduct",
				type: "post",
				data: formData,
				processData: false,
				contentType: false,
				cache: false,
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
		function saveProductData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackProduct/save",
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
		function deleteProductData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackProduct/delete",
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
		// callback get all img data
		function getProductAllImgData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackProductImg/getMlbackProductImgListByProductId",
				type: "post",
				cache: false,
				dataType: "json",
				contentType: 'application/json',
				data: JSON.stringify(reqData),
				success: function (data) {
					if (data.code == 100) {
						toastr.success(data.extend.resMsg);
						callback(data.extend.mbackProductImgResList);
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
				htmlStr += '<tr><td>' + data[i].productId + '</td>' +
					'<td>' +
						((data[i].productMainsmallimgurl || data[i].productMainimgurl) ?
							'<div class="c-table-img"><img src="'+ encodeUrl((data[i].productMainsmallimgurl || data[i].productMainimgurl)) +'" /></div>'
							: '<div class="c-table-icon"><svg class="c-icon"><use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image1"></use></svg></div>') +
					'</td>' +
					'<td>' + data[i].productName + '</td>' +
					'<td>' + data[i].productOriginalprice + '</td>' +
					'<td>' + data[i].productActoffoff + '</td>' +
					'<td>' + data[i].productLable + '</td>' +
					'<td><a class="badge '+ (data[i].productStatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].productStatus ? 'enable' : 'disable') + '</a></td>' +
					'<td>' + data[i].productHavesalenum + '</td>' +
					'<td>' + data[i].productReviewnum + '</td>' +
					'<td>' + (data[i].productSupercatename || "") + '</td>' +
					'<td>' +
						'<button class="btn btn-primary btn-edit" data-val={"productId":'+ data[i].productId + ',"productSupercateid":' +  data[i].productSupercateid + '}>' +
							'<svg class="c-icon">' +
								'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-pencil"></use>' +
							'</svg>' +
						'</button>' +
						'<button class="btn btn-danger btn-delete" data-id="' + data[i].productId + '">' +
							'<svg class="c-icon">' +
								'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-trash"></use>' +
							'</svg>' +
						'</button>' +
					'</td></tr>';
			}
			$('.c-table-table tbody').html(htmlStr);
		}
		// render product all img data
		function renderProductAllData(data) {
			var len = data.length;
			$('.product-img-list').html('');

			if (!len) {
				addUploadBlock(1);
			} else {
				for (var i = 0; i < len; i+=1) {
					addUploadBlock(data[i].productimgSortOrder);
					addPicture($('.product-img-item').last().find('.productAllImgurl'), {
						imageUrl: data[i].productimgUrl,
						thumImageUrl: data[i].productimgUrl,
						imgId: data[i].productimgId
					});
				}
				if (len < 6) addUploadBlock(($('.product-img-item').last().find('.productAllImgurl').data('order') + 1));
			}
		}
		
		// render parentCategoryData
		function renderParentCategory(data) {
			var htmlStr = '';
			var defaultProductCategory = $('#productCategoryIdsstr').val() && $('#productCategoryIdsstr').val().split(',');
			for (var i = 0, len = data.length; i < len; i += 1) {
				var cagtegoryId = data[i].categoryId;
				var categoryName = data[i].categoryDesc;
				var checkedStatus = defaultProductCategory.indexOf(String(cagtegoryId)) > -1 ? "checked" : "";
				htmlStr += '<div class="col-form-label"><div class="form-check checkbox">' +
						'<input class="form-check-input" '+ checkedStatus +' id="'+ cagtegoryId +'" type="checkbox" value="" data-id="'+ cagtegoryId +'" data-name="'+ categoryName +'">' +
						'<label class="form-check-label" for="'+ cagtegoryId +'">No.' + cagtegoryId + ' ' + categoryName +'</label>' +
					'</div></div>';
			}
			$('#editModal .modal-body-body').html(htmlStr);
		}
		
		// copy one product data
		function copyOneProductData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/MlbackProduct/copyProByPid",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				data: JSON.stringify(reqData),
				success: function (data) {
					if (data.code == 100) {
						callback && callback();
						toastr.success(data.extend.resMsg);
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
		
		// render all product data
		function renderAllProductData(data) {
			var htmlStr = '';
			for (var i = 0, len = data.length; i < len; i += 1) {
				var productId = data[i].productId;
				var productName = data[i].productName;
				htmlStr += '<div class="col-form-label"><div class="form-check radio">' +
						'<input class="form-check-input" name="copyProductRadio" id="'+ productId +'" type="radio" value="" data-id="'+ productId +'">' +
						'<label class="form-check-label" for="'+ productId +'">No.' + productId + ' ' + productName +'</label>' +
					'</div></div>';
			}
			$('#editModal .modal-body-body').html(htmlStr);
			$('#editModal .spinner').hide();
		}
	</script>
</body>

</html>