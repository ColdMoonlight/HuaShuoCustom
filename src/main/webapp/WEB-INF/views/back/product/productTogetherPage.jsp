<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Product Together</title>
	<jsp:include page="../common/backheader.jsp" flush="true"></jsp:include>
	<style>
		.product-operate {
			text-align: right;
		}
		.product-list {
			padding: 1rem;
			margin-top: 1rem;
			border: 1px solid #7d50af;
		}
		.product-item {
			display: flex;
			padding: .5rem;
			border: 1px solid #ccc;
			border-radius: .5rem;
		}
		.product-item+.product-item {
			margin-top: 1rem;
		}
		.product-img {
			display: block;
			width: 100px;
			height: 100px;
			margin-right: 1rem;
		}
		.product-name {
			font-size: 1.2rem;
			color: #333;
			cursor: pointer;
		}
	</style>
</head>
<body class="c-app">
	<jsp:include page="../layout/backheader.jsp" flush="true"></jsp:include>
	<jsp:include page="../layout/backsidebar.jsp" flush="true"></jsp:include>
	<div class="c-wrapper">
		<div class="c-body">
			<div class="c-main">
				<div class="c-init">
					<div class="c-option">
						<span class="c-option-title">Product-Together list</span>
						<button class="btn btn-primary btn-create">Create Product-Together</button>
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
									<input id="search-name" type="text" placeholder="Search proudct-together name...">						
									<select class="supercate-list" id="search-supercate"></select>
								</div>
								<a class="btn btn-primary input-group-addon btn-save-search">Save search</a>
							</div>
							<div class="c-table-table table-responsive-sm">
								<table class="table">
									<thead>
										<tr>
											<th>id</th>
											<th>name</th>
											<th>product-seo</th>
											<th>belong-seo</th>
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
						<span class="c-option-title">Edit Product-Together</span>
						<div class="group">
							<button class="btn btn-secondary btn-back">Back</button>
							<button class="btn btn-primary btn-save">Save</button>
						</div>
					</div>
					<div class="c-form row">
						<input id="producttogetherId" hidden>
						<!-- left panel -->
						<div class="left-panel col-lg-6 col-md-12">
							<div class="card">
								<div class="card-body">
									<div class="form-group row">
										<label class="col-md-3 col-form-label" for="producttogetherStatus">Status</label>
										<div class="controls col-md-3">
											<label class="c-switch c-switch-primary">
												<input class="c-switch-input" id="producttogetherStatus" type="checkbox">
												<span class="c-switch-slider"></span>
											</label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="producttogetherName">Name</label>
										<div class="controls">
											<input class="form-control" id="producttogetherName" type="text" />
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- right panel  -->
						<div class="right-panel col-lg-6 col-md-12">
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Super Category</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="producttogetherSupercateId">Super Category</label>
										<div class="controls">
											<select class="form-control supercate-list" id="producttogetherSupercateId" /></select>
										</div>
									</div>
								</div>
							</div>
						</div>						
					</div>
					
					<div class="row">
						<div class="col-lg-6 col-md-12">
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Select product mix</div>
								</div>
								<div class="card-body">
									<input id="producttogetherProsidStr" hidden />
									<input id="producttogetherProsnameStr" hidden />
									<input id="producttogetherProsseoStr" hidden />
									<input id="producttogetherProsimgurlStr" hidden />
									<div class="product-operate">
										<button class="btn btn-primary" id="product-together-edit">edit it</button>
									</div>
									<div class="product-list product-list-1"></div>
								</div>
							</div>
						</div>
						
						<div class="col-lg-6 col-md-12">
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Belonging products</div>
								</div>
								<div class="card-body">
									<input id="producttogetherBelongProIdStr" hidden />
									<input id="producttogetherBelongProNameStr" hidden />
									<input id="producttogetherBelongProSeoNameStr" hidden />
									<div class="product-operate">
										<button class="btn btn-primary" id="product-belong-edit">edit it</button>
									</div>
									<div class="product-list product-list-2"></div>
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
	<jsp:include page="../common/editModal2.jsp" flush="true"></jsp:include>

	<!-- custom script -->
	<script>
		var hasSuperCateList = false;
		var isCreate = false, oldTime = (new Date()).getTime(), timer = null, storageName = "product-together";
		var selectedName = [], selectedId = [], selectedSeo = [], selectedImg = [];
		var belongName = [], belongId = [], belongSeo = [], belongImg = [];
		var productTogetherNames = {};
		var flag;

		if (!hasSuperCateList) getSuperCategoryData(renderSuperCategory);
		$('#search-supercate').val($('#search-supercate').data('val') || -1);
		getAllProductTogetherNames();
		// init
		renderTabItems();
		// pagination a-click
		$(document.body).on('click', '#table-pagination li', function (e) {
			getTabSearchData($('.c-table-tab-item.active'));
		});
		// create proudct-together
		$('.btn-create').on('click', function () {
			$('.c-create .c-option-title').text('Create Product-Together');
			resetFormData();
			showViewBlock();
			getProductTogetherId();
			isCreate = true;
		});
		$('.btn-back').on('click', function () {
			if (isCreate) {
				isCreate = false;
				deleteProductTogetherData({
					producttogetherId: $('#producttogetherId').val(),
				}, function() {
					console.log("cancel create product-together...");
				});
			}
			$('.c-create .c-option-title').text('Product-Together List');
			showInitBlock();
			resetFormData();
		});
		$(window).on('beforeunload', function() {
			var producttogetherId = $('#producttogetherId').val();
			isCreate && producttogetherId && deleteProductTogetherData({
				producttogetherId: producttogetherId,
			});
			$('#editModal .select-result').addClass('hide');
		});
		// tab-item click
		$(document.body).on('click', '.c-table-tab-item', function (e) {
			$('.c-table-tab-item').removeClass('active');
			$(this).addClass('active');
			// inital pagination num
			setPageNum(1);
			setActiveItemNum($(this).data('idx'));
			getTabSearchData($(this));
		});
		// tab delete
		$(document.body).on('click', '.delete-table-tab-item', deleteTableTabItem);
		// save search
		$('.btn-save-search').on('click', function () {
			var searchVal = {
				supercate: $('#search-supercate').find('option:selected').text(),
				supercateid: $('#search-supercate').val(),
				name: $('#search-name').val()
			};
			// cancel repeat add save-search
			if (checkNewItem(searchVal)) return;

			if (parseInt(searchVal.supercateid) < 0) searchVal.supercate = "";

			if (searchVal.supercate > -1 || searchVal.name) {
				addStorageItem(searchVal);
				$('.c-table-tab-tempory').html('');
				createTableTabItem(searchVal);
				addTableTabItem(searchVal, $('.c-table-tab-item').length);
			}
		});
		// search it
		$('#search-supercate').on('change', function() {
			$(this).attr('data-val', $(this).val());
			updateSearchData();
		});
		$('#search-name').on('keyup', function() {
			var distanceTime = 1000,
				newTime =  (new Date()).getTime();
			if (newTime - oldTime < 1000) clearTimeout(timer);
			oldTime = newTime;
			timer = setTimeout(function() {
				updateSearchData();
			}, distanceTime);
		});
		// edit product-together
		$(document.body).on('click', '.btn-edit', function (e) {
			var producttogetherId = parseInt($(this).data('id') || $(this).find('.btn-edit').data('id'));
			isCreate = false;
			getOneProductTogetherData({
				producttogetherId: producttogetherId
			}, function(resData) {
				$('.c-create .c-option-title').text('Edit Product-Together');
				initFormData(resData);;
				showViewBlock();
			});
		});
		// save product-together
		$('.c-create .btn-save').on('click', function () {
			var reqData = getFormData();
			saveProductTogetherData(reqData, function() {
				showInitBlock();
				getProductTogethersData();
				$('#producttogetherId').val('');
			});
		});
		// delete product-together
		$(document.body).on('click', '.btn-delete', function (e) {
			var producttogetherId = parseInt($(this).data('id'));
			$('#deleteModal').find('.modal-title').html('Delete product-together!');
			$('#deleteModal').modal('show');
			$('#deleteModal .btn-ok').one('click', function () {
				deleteProductTogetherData({
					producttogetherId: producttogetherId,
				}, function() {
					getProductTogethersData();
				});
			});
		});
		// status combinewith supercate
		$('#producttogetherStatus').on('click', function(e) {
			if (parseInt($('#producttogetherSupercateId').val()) < 0) {
				toastr.info('Please Select super-category!');
				$('#producttogetherSupercateId').focus();
				$('#producttogetherStatus').prop('checked', false);
			}
		});
		// slelect content
		$('#product-together-edit').on('click', function() {
			var $selectResult = $('#editModal .select-result .value');

			flag = 'mix';

			$('#editModal .select-result').removeClass('hide');

			if (selectedId.length) {
				$selectResult.text(selectedId.join(','));
			} else {
				$selectResult.text('');
			}

			getAllProductData(renderAllProduct2);

			$('#editModal').find('.modal-title').text('Select product ...');
			$('#editModal').modal('show');
		});
		$('#product-belong-edit').on('click', function() {
			var $selectResult = $('#editModal .select-result .value');
			
			flag = 'belong';
			
			$('#editModal .select-result').removeClass('hide');

			if (belongId.length) {
				$selectResult.text(belongId.join(','));
			} else {
				$selectResult.text('');
			}

			getAllProductData(renderAllProduct2);

			$('#editModal').find('.modal-title').text('Belong product ...');
			$('#editModal').modal('show');
		});
		$('#editModal .btn-ok').on('click', function() {
			if (flag == 'mix') {
				if (selectedId.length && selectedName.length && (selectedId.length == selectedName.length)) {
					$('#producttogetherProsidStr').val(selectedId.join(','));
					$('#producttogetherProsnameStr').val(selectedName.join(','));
					$('#producttogetherProsseoStr').val(selectedSeo.join(','));
					$('#producttogetherProsimgurlStr').val(selectedImg.join(','));
					// render product list
					renderSelectedProduct();
				} else {
					selectedId = $('#producttogetherProsidStr').val() ? $('#producttogetherProsidStr').val().split(',') : [];
					selectedName = $('#producttogetherProsnameStr').val() ? $('#producttogetherProsnameStr').val().split(',') : [];
					selectedSeo = $('#producttogetherProsseoStr').val() ? $('#producttogetherProsseoStr').val().split(',') : [];
					selectedImg = $('#producttogetherProsimgurlStr').val() ? $('#producttogetherProsimgurlStr').val().split(',') : [];
					console.log('数据错误！！！');
				}				
			}

			if (flag == 'belong') {
				if (belongId.length && belongName.length && (belongId.length == belongName.length)) {
					$('#producttogetherBelongProIdStr').val(belongId.join(','));
					$('#producttogetherBelongProNameStr').val(belongName.join(','));
					$('#producttogetherBelongProSeoNameStr').val(belongSeo.join(','));
					// render product list
					renderBelongProduct();
				} else {
					belongId = $('#producttogetherBelongProIdStr').val() ? $('#producttogetherBelongProIdStr').val().split(',') : [];
					belongSeo = $('#producttogetherBelongProNameStr').val() ? $('#producttogetherBelongProNameStr').val().split(',') : [];
					belongSeo = $('#producttogetherBelongProSeoNameStr').val() ? $('#producttogetherBelongProSeoNameStr').val().split(',') : [];
					console.log('数据错误！！！');
				}
			}

			$('#editModal').modal('hide');
		});
		$(document.body).on('click', '#editModal .form-check-input', function() {
			var $this = $(this);
			var id = '' + $this.data('id');
			var name = $this.data('name');
			var seo = $this.data('seo');
			if (flag == 'mix') {
				var img = $this.data('img');
				if ($this.prop('checked')) {
					selectedId.push(id);
					selectedName.push(name);
					selectedSeo.push(seo);
					selectedImg.push(img);
				} else {
					var idx = selectedId.indexOf(id);
					if (idx > -1) {
						selectedId.splice(idx, 1);
						selectedName.splice(idx, 1);
						selectedSeo.splice(idx, 1);
						selectedImg.splice(idx, 1);
					}
				}
				$('#editModal .select-result .value').text(selectedId.join(', '));
			}
			
			if (flag == 'belong') {
				if ($this.prop('checked')) {
					belongId.push(id);
					belongName.push(name);
					belongSeo.push(seo);
				} else {
					var idx = belongId.indexOf(id);
					if (idx > -1) {
						belongId.splice(idx, 1);
						belongName.splice(idx, 1);
						belongSeo.splice(idx, 1);
					}
				}
				$('#editModal .select-result .value').text(belongId.join(', '));
			}
		});
		$('#editModal .btn-ok').on('click', function() {
			if (flag == 'mix') {
				if (selectedId.length && selectedName.length && (selectedId.length == selectedName.length)) {
					$('#producttogetherProsidStr').val(selectedId.join(','));
					$('#producttogetherProsnameStr').val(selectedName.join(','));
					$('#producttogetherProsseoStr').val(selectedSeo.join(','));
					$('#producttogetherProsimgurlStr').val(selectedImg.join(','));
					// render product list
					renderSelectedProduct();
				} else {
					selectedId = $('#producttogetherProsidStr').val() ? $('#producttogetherProsidStr').val().split(',') : [];
					selectedName = $('#producttogetherProsnameStr').val() ? $('#producttogetherProsnameStr').val().split(',') : [];
					selectedSeo = $('#producttogetherProsseoStr').val() ? $('#producttogetherProsseoStr').val().split(',') : [];
					selectedImg = $('#producttogetherProsimgurlStr').val() ? $('#producttogetherProsimgurlStr').val().split(',') : [];
					console.log('数据错误！！！');
				}				
			}

			if (flag == 'belong') {
				if (belongId.length && belongName.length && (belongId.length == belongName.length)) {
					$('#producttogetherBelongProIdStr').val(belongId.join(','));
					$('#producttogetherBelongProNameStr').val(belongName.join(','));
					$('#producttogetherBelongProSeoNameStr').val(belongSeo.join(','));
					// render product list
					renderBelongProduct();
				} else {
					belongId = $('#producttogetherBelongProIdStr').val() ? $('#producttogetherBelongProIdStr').val().split(',') : [];
					belongName = $('#producttogetherBelongProNameStr').val() ? $('#producttogetherBelongProNameStr').val().split(',') : [];
					belongSeo = $('#producttogetherBelongProSeoNameStr').val() ? $('#producttogetherBelongProSeoNameStr').val().split(',') : [];
					console.log('数据错误！！！');
				}				
			}
			$('#editModal').modal('hide');
		});
		// render selectedProduct
		function renderSelectedProduct() {
			if (selectedId.length) {
				var htmlStr = '';
				selectedId.forEach(function(item, idx) {
					var link = '${APP_PATH}/products/' + selectedSeo[idx];
					htmlStr += '<div class="product-item">' +
						'<a href="'+ link +'" target="blank"><img class="product-img" src="'+ selectedImg[idx] + '" /></a>' +
						'<a class="product-name" href="'+ link +'" target="blank">' + selectedName[idx] + '</a>' +
					'</div>';
				});
			} else {
				htmlStr = '<p class="text-align: center; font-style: italic;">Empty here...</p>';
			}
			$('.product-list-1').html(htmlStr);
		}

		function renderBelongProduct() {
			if (belongId.length) {
				var htmlStr = '';
				belongId.forEach(function(item, idx) {
					var link = '${APP_PATH}/products/' + belongSeo[idx];
					htmlStr += '<div class="product-item">' +
						'<a class="product-name" href="'+ link +'" target="blank">' + belongName[idx] + '</a>' +
					'</div>';
				});
			} else {
				htmlStr = '<p class="text-align: center; font-style: italic;">Empty here...</p>';
			}
			$('.product-list-2').html(htmlStr);
		}
		// get all product
		function renderAllProduct2(data) {
			function getGroup(id) {
				var txt = '';
				switch(id) {
					case 999:
						txt = '--';
						break;
					case 0:
						txt = '随机组合';
						break;
					default:
						txt = '第' + id + '组合';
						if (Object.keys(productTogetherNames).length) txt += '——' + productTogetherNames[id];
				}
				return txt;
			}
			var htmlStr = '';
			for (var i = 0, len = data.length; i < len; i += 1) {
				var productId = data[i].productId;
				var productName = data[i].productName;
				var productSeo = data[i].productSeo;
				var productImg = data[i].productMainimgurl;
				var arrId = [];
				if (flag == 'mix') {
					arrId = selectedId;
				}
				if (flag == 'belong') {
					arrId = belongId;
				}
				htmlStr += '<div class="page-area-item"><div class="form-check checkbox">' +
						'<input class="form-check-input" id="'+ productId +'" type="checkbox"'+ (arrId.indexOf('' + productId) > -1 ? ' checked' : '') +' value="" data-id="'+ productId +'" data-name="'+ productName +'" data-seo="'+ productSeo +'" data-img="'+ productImg +'">' +
						'<label class="form-check-label" for="'+ productId +'">'+
							'<span class="table-th">'+ productId +'</span>' +
							'<span class="table-th"><a class="badge '+ (data[i].productStatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].productStatus ? '已上架' : '未上架') + '</a></span>' +
							/* '<span class="table-th">'+ (productImg ?
									'<div class="c-table-img"><img src="'+ encodeUrl(productImg) +'" /></div>'
									: '<div class="c-table-icon"><svg class="c-icon"><use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image1"></use></svg></div>') +
							'</span>' + */
							'<span class="table-th" style="width: 100%;">'+ productName +'</span>' +
							(flag == 'belong' ? '<span class="table-th">'+ getGroup(data[i].productNeedProTogetherId) +'</span>' : '') +
						'</label>' +
					'</div></div>';
			}
			$('#editModal .modal-body-body').html(htmlStr);
			$('#editModal .spinner').hide();
		}
		// get all product-together-name
		function getAllProductTogetherNames() {
			// productTogether/lownLoadProTogether
			$.ajax({
				url: "${APP_PATH}/productTogether/lownLoadProTogether",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						var resData = data.extend.mlbackProductTogetherResList;
						resData && resData.forEach(function(item, idx) {
							productTogetherNames[item.producttogetherId] = item.producttogetherName
						});
					} else {}
				}
			});
		}
		// handle formData
		// reset data
		function resetFormData() {
			$('#producttogetherId').val('');
			$('#producttogetherName').val('');
			$('#producttogetherStatus').prop('checked', false);

			$('#producttogetherProsidStr').val('');
			$('#producttogetherProsnameStr').val('');
			$('#producttogetherProsseoStr').val('');
			$('#producttogetherProsimgurlStr').val('');

			selectedId = [];
			selectedName = [];
			selectedSeo = [];
			selectedImg = [];
			renderSelectedProduct();

			$('#producttogetherBelongProIdStr').val('');
			$('#producttogetherBelongProNameStr').val('');
			$('#producttogetherBelongProSeoNameStr').val('');

			belongId = [];
			belongName = [];
			belongSeo = [];
			renderBelongProduct();

			$('#producttogetherSupercateId').val('-1');
		}
		// getFormdData
		function getFormData() {
			var data = {};
			data.producttogetherId = parseInt($('#producttogetherId').val());
			data.producttogetherName = $('#producttogetherName').val();
			data.producttogetherStatus = $('#producttogetherStatus').prop('checked') ? 1 : 0;

			data.producttogetherProsidStr = $('#producttogetherProsidStr').val();
			data.producttogetherProsnameStr = $('#producttogetherProsnameStr').val();
			data.producttogetherProsseoStr = $('#producttogetherProsseoStr').val();
			data.producttogetherProsimgurlStr = $('#producttogetherProsimgurlStr').val();

			data.producttogetherBelongProIdStr = $('#producttogetherBelongProIdStr').val();
			data.producttogetherBelongProNameStr = $('#producttogetherBelongProNameStr').val();
			data.producttogetherBelongProSeoNameStr = $('#producttogetherBelongProSeoNameStr').val();

			data.producttogetherSupercateId = $('#producttogetherSupercateId').val();
			data.producttogetherSupercateName = $('#producttogetherSupercateId').find('option:checked').text();
			return data;
		}
		// initFormData
		function initFormData(data) {
			$('#producttogetherId').val(data.producttogetherId);
			$('#producttogetherName').val(data.producttogetherName || '');
			$('#producttogetherStatus').prop('checked', (''+data.producttogetherStatus == '0' ? false : true));

			$('#producttogetherProsidStr').val(data.producttogetherProsidStr || '');
			$('#producttogetherProsnameStr').val(data.producttogetherProsnameStr || '');
			$('#producttogetherProsseoStr').val(data.producttogetherProsseoStr || '');
			$('#producttogetherProsimgurlStr').val(data.producttogetherProsimgurlStr || '');

			if (data.producttogetherProsidStr && data.producttogetherProsnameStr && data.producttogetherProsseoStr && data.producttogetherProsimgurlStr) {
				selectedId = data.producttogetherProsidStr.split(',');
				selectedName = data.producttogetherProsnameStr.split(',');
				selectedSeo = data.producttogetherProsseoStr.split(',');
				selectedImg = data.producttogetherProsimgurlStr.split(',');
			}
			// render product list
			renderSelectedProduct();

			$('#producttogetherBelongProIdStr').val(data.producttogetherBelongProIdStr || '');
			$('#producttogetherBelongProNameStr').val(data.producttogetherBelongProNameStr || '');
			$('#producttogetherBelongProSeoNameStr').val(data.producttogetherBelongProSeoNameStr || '');

			if (data.producttogetherBelongProIdStr && data.producttogetherBelongProNameStr && data.producttogetherBelongProSeoNameStr) {
				belongId = data.producttogetherBelongProIdStr.split(',');
				belongName = data.producttogetherBelongProNameStr.split(',');
				belongSeo = data.producttogetherBelongProSeoNameStr.split(',');
			}
			// render product list
			renderBelongProduct();

			$('#producttogetherSupercateId').val(data.producttogetherSupercateId || '-1');
		}
		// search status change
		function updateSearchData() {
			var searchVal = {
				supercate: $('#search-supercate').find('option:selected').text(),
				supercateid: $('#search-supercate').val(),
				name: $('#search-name').val()
			};
			if (parseInt(searchVal.supercateid) < 0) searchVal.supercate = "";
			// inital pagination num
			setPageNum(1);
			// check search product-together
			$('.c-table-tab-item.active').removeClass('active');
			$('.c-table-tab-tempory').html(createTableTabItem(searchVal).addClass('active'));
			getTabSearchData($('.c-table-tab-tempory .c-table-tab-item'));
		}
		// tab view/init
		function showViewBlock() {
			$('.c-init').addClass('hide');
			$('.c-create').removeClass('hide');
		}
		function showInitBlock() {
			$('.c-init').removeClass('hide');
			$('.c-create').addClass('hide');
		}
		// get Data for table
		function getTabSearchData($this) {
			var dataVal = $this.data('val');
			if (dataVal && (dataVal.supercate || dataVal.name)) {
				$('#search-name').val(dataVal.name || '');
				$('#search-supercate').attr('data-val', dataVal.supercateid || '-1');
				$('#search-supercate').val(dataVal.supercateid || '-1');
				getSearchProductTogetherData();
			} else {
				$('#search-name').val('');
				$('#search-supercate').val('-1');
				initActiveItemNum();
				getProductTogethersData();
			}
		}
		//  callback get all
		function getProductTogethersData() {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/productTogether/getProductTogetherByPage",
				type: "post",
				data: "pn=" + getPageNum(),
				success: function (data) {
					if (data.code == 100) {
						renderTable(data.extend.pageInfo.list);
						renderTablePagination(data.extend.pageInfo);
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function (err) {
					toastr.error('Failed to get Product-Togethers, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback get search data
		function getSearchProductTogetherData(data) {
			$('.c-mask').show();

			var formData = new FormData();
			formData.append('producttogetherName', $('#search-name').val());
			formData.append('producttogetherSupercateId', ($('#search-supercate').attr('data-val') || '-1'));
			formData.append('pn', getPageNum());

			$.ajax({
				url: "${APP_PATH}/productTogether/backSearchByProductTogether",
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
					toastr.error('Failed to get Product-Togethers data, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback initial id
		function getProductTogetherId(callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/productTogether/initializaProductTogether",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				success: function (data) {
					if (data.code == 100) {
						var producttogetherId = data.extend&& data.extend.mlbackProductTogether && data.extend.mlbackProductTogether.producttogetherId;
						if (producttogetherId) {
							$('#producttogetherId').val(data.extend.mlbackProductTogether.producttogetherId);
							toastr.success(data.extend.resMsg);
						} else {
							toastr.error('create Product-together fail! Please try again.');
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
		// callback save
		function saveProductTogetherData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
		  		url: '${APP_PATH}/productTogether/save',
		  		type: 'post',
		  		dataType: 'json',
		  		data: JSON.stringify(reqData),
		  		contentType: 'application/json',
		  		success: function (data) {
					if (data.code == 100) {
						toastr.success(data.extend.resMsg);
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
		// callback get one product-together data
		function getOneProductTogetherData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/productTogether/getOneMlbackProductTogetherDetail",
				type: "post",
				data: JSON.stringify(reqData),
				dataType: "json",
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend.mlbackProductTogetherOne);
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function (err) {
					toastr.error('Failed to get product-together data, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback delete
		function deleteProductTogetherData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/productTogether/delete",
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
		// init table-list
		function renderTable(data) {
			var htmlStr = '';
			for (var i = 0, len = data.length; i < len; i += 1) {
				htmlStr += '<tr><td>' + data[i].producttogetherId + '</td>' +
					'<td>' + (data[i].producttogetherName || '') + '</td>' +
					'<td>' + (data[i].producttogetherProsseoStr && data[i].producttogetherProsseoStr.replace(/\,/g, '<br\/>') || '--') + '</td>' +
					'<td>' + (data[i].producttogetherBelongProSeoNameStr && data[i].producttogetherBelongProSeoNameStr.replace(/\,/g, '<br\/>') || '--') + '</td>' +
					'<td><a class="badge '+ ('' + data[i].producttogetherStatus == '0' ? 'badge-danger': 'badge-success') +'" href="javascript:;">' + ('' + data[i].producttogetherStatus == '0' ? 'unabled' : 'enabled') + '</a></td>' +
					'<td>' +
						'<button class="btn btn-primary btn-edit" data-id="' + data[i].producttogetherId + '">' +
							'<svg class="c-icon">' +
								'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-pencil"></use>' +
							'</svg>' +
						'</button>' +
						'<button class="btn btn-danger btn-delete" data-id="' + data[i].producttogetherId + '">' +
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