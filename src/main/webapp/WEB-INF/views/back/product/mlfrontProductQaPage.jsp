<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Product QA</title>
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
						<span class="c-option-title">Product QA</span>
						<button class="btn btn-primary btn-create">Create Product QA</button>
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
											<th>product-ids</th>
											<th>product-questions-id</th>
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
						<span class="c-option-title">Edit Product QA</span>
						<div class="group">
							<button class="btn btn-secondary btn-cancel">Cancel</button>
							<button class="btn btn-primary btn-save">Save Product QA</button>
						</div>
					</div>
					<div class="c-form row">
						<input id="productqaId" hidden>
						<!-- left panel  -->
						<div class="left-panel col-lg-7 col-md-12">
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">General</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="productqaName">Product QA Name</label>
										<div class="controls">
											<input class="form-control" id="productqaName" type="text" />
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
									<div class="card-title-name">Product & Question</div>
								</div>
								<div class="card-body">
									<div>
										<div class="form-group ml-product">
											<label class="col-form-label" for="productqaProductIds">Product</label>
											<div class="controls">
												<input class="form-control" id="productqaProductIds" disabled />
												<button id="btn-select-product">select products</button>
											</div>
										</div>
										<div class="form-group ml-category hide">
											<label class="col-form-label" for="productqaQuestionIds">Product Questions</label>
											<div class="controls">
												<input class="form-control" id="productqaQuestionIds" />
												<button id="btn-select-question">select question</button>
											</div>
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
	<jsp:include page="../common/editModal2.jsp" flush="true"></jsp:include>
	<!-- custom script -->
	<script>
		var isCreate = false;
		var isUpdate = false;
		var selectedId = [];
		var flag;

		// init
		getAllBlockData()
		// create collection
		$('.btn-create').on('click', function () {
			$('.c-create .c-option-title').text('Create Product QA');

			// init formData
			resetFormData();
			showCreateBlock();
			isCreate = true;
		});
		// edit block
		$(document.body).on('click', '.btn-edit', function (e) {
			var productqaId = $(this).data('id');
			var formData = new FormData();
			formData.append('productqaId', productqaId);
			getOneBlockData(formData, function(resData) {
				isUpdate = true;
			 	$('.c-create .c-option-title').text('Edit Product QA');
				showCreateBlock();
				initFormData(resData);
			});			
		});
		// delete block
		$(document.body).on('click', '.btn-delete', function (e) {
			var productqaId = parseInt($(this).data('id'));
			$('#deleteModal').find('.modal-title').html('Delete Product QA!');
			$('#deleteModal').modal('show');
			$('#deleteModal .btn-ok').one('click', function () {
				deleteOneBlockData({
					productqaId: productqaId,
				}, function() {
					getAllBlockData();
				});
			});
		});
		// save block
		$('.c-create .btn-save').on('click', function () {
			function resetBlock() {
				// redirect tab-active & then search-data
				if (isCreate) isCreate = false;
				getAllBlockData();
				showInitBlock();
			}

			if (isUpdate) {
				updateOneBlockData(getFormData(), resetBlock);
			}

			if (isCreate) {				
				insertOneBlockData(getFormData(), resetBlock);
			}
		});
		// cancel blocks save
		$('.c-create .btn-cancel').on('click', function () {
			isCreate = false;
			isUpdate = false;

			showInitBlock();
		});
		// select product
		$('#btn-select-product').on('click', function() {
			var productIds = $('#productqaProductIds').val();
			var selectedId = productIds.split(',');

			flag = 'products';

			$('#editModal .select-result').removeClass('hide');
			$('#editModal .select-result .value').text(selectedId);

			getAllProductData(renderAllProduct);

			$('#editModal').find('.modal-title').text('Select product ...');
			$('#editModal').modal('show');
		});
		// select question
		$('#btn-select-question').on('click', function() {
			var questionIds = $('#productqaQuestionIds').val();
			var selectedId = questionIds.split(',');

			flag = 'questions';

			$('#editModal .select-result').removeClass('hide');
			$('#editModal .select-result .value').text(selectedId);

			getAllQuestionData(renderAllQuestion);

			$('#editModal').find('.modal-title').text('Select questions ...');
			$('#editModal').modal('show');
		});
		$('#editModal .btn-ok').on('click', function() {
			if (flag == 'products') {
				$('#productqaProductIds').val(selectedId.join(','));
			}
			if (flag == 'questions') {
				$('#productqaQuestionIds').val(selectedId.join(','));
			}

			$('#editModal').modal('hide');
		});
		$(document.body).on('change', '#editModal .form-check-input', function() {
			var curId = $(this).data('id');
			var curIdx = selectedId.indexOf(curId);
			curIdx > -1 ? selectedId.splice(curIdx, 1) : selectedId.push(curId);

			$('#editModal .select-result .value').text(selectedId.join(','));
		});
		// get all product
		function renderAllProduct(data) {
			var htmlStr = '';
			var productIds = $('#productqaProductIds').val();
			for (var i = 0, len = data.length; i < len; i += 1) {
				var productId = data[i].productId;
				var productName = data[i].productName;
				var productSeo = data[i].productSeo;
				htmlStr += '<div class="page-area-item"><div class="form-check checkbox">' +
						'<input class="form-check-input" id="'+ productId +'" type="checkbox"'+ (productIds.indexOf('' + productId) > -1 ? ' checked' : '') +' value="" data-id="'+ productId +'" data-name="'+ productName +'" data-seo="'+ productSeo +'">' +
						'<label class="form-check-label" for="'+ productId +'">'+
							'<span class="table-th">'+ productId +'</span>' +
							'<span class="table-th"><a class="badge '+ (data[i].productStatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].productStatus ? '已上架' : '未上架') + '</a></span>' +
							'<span class="table-th" style="width: 100%;">'+ productName +'</span>' +
						'</label>' +
					'</div></div>';
			}
			$('#editModal .modal-body-body').html(htmlStr);
			$('#editModal .spinner').hide();
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
			$('#productqaId').val('');
			$('#productqaName').val('');
			$('#productqaProductIds').val('');
			$('#productqaQuestionIds').val('');
		}
		// getFormdData
		function getFormData() {
			var data = {};
			data.productqaId = parseInt($('#productqaId').val());
			data.productqaName = $('#productqaName').val();
			data.productqaProductIds = $('#productqaProductIds').val();
			data.productqaQuestionIds = $('#productqaQuestionIds').val();

			return data;
		}
		// initFormData
		function initFormData(data) {
			// init
			$('#productqaId').val(data.productqaId);
			$('#productqaName').val(data.productqaName);
			$('#productqaProductIds').val('-1');
			$('#productqaQuestionIds').val('-1');
		}
		// callback get all data
		function getAllBlockData() {
			$('.c-mask').show();

			var formData = new FormData();
			formData.append('pn', getPageNum());

			$.ajax({
				url: "${APP_PATH}//MlfrontProductQa/getAllMlfrontProductQa",
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
					toastr.error('Failed to get Product-QA, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback get one data
		function getOneBlockData(formData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlfrontProductQa/getOneMlfrontProductQaDetail",
				type: "post",
				processData: false,
				contentType: false,
				cache: false,
				data: formData,
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend.MlfrontProductQaOne);
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function () {
					toastr.error('Failed to get Product-QA, please refresh the page to get again!');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback insert
		function insertOneBlockData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlfrontProductQa/save",
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
		// callback update
		function updateOneBlockData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlfrontProductQa/update",
				type: "post",
				cache: false,
				dataType: "json",
				contentType: 'application/json',
				data: JSON.stringify(reqData),
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
		// callback delete
		function deleteOneBlockData(reqData, callback) {
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
				htmlStr += '<tr><td>' + data[i].productqaId + '</td>' +
					'<td>' + data[i].productqaName + '</td>' +
					'<td>' + data[i].productqaProductIds + '</td>' +
					'<td>' + data[i].productqaQuestionIds + '</td>' +
					'<td>' +
						'<button class="btn btn-primary btn-edit" data-id="' + data[i].productqaId + '">' +
							'<svg class="c-icon">' +
								'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-pencil"></use>' +
							'</svg>' +
						'</button>' +
						'<button class="btn btn-danger btn-delete" data-id="' + data[i].productqaId + '">' +
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