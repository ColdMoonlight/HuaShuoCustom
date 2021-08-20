<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>xxxxxxxxxxx</title>
	<jsp:include page="../common/backheader.jsp" flush="true"></jsp:include>
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
						<span class="c-option-title">Coupon list</span>
						<button class="btn btn-primary btn-create">Create Coupon</button>
					</div>
					<div class="c-table">
						<div class="c-table-tab">
							<div class="c-table-tab-item" data-idx="0">All</div>
							<div class="c-table-tab-list"></div>
							<div class="c-table-tab-tempory"></div>
						</div>
						<div class="c-table-content">
							<table class="c-table-table table table-responsive-sm">
								<thead>
									<tr>
										<th>id</th>
										<th>Name</th>
										<th>couponCode</th>
										<th>scope</th>
										<th>couponType</th>
										<th>couponPrice</th>
										<th>couponPriceoff</th>
										<th>Draw or no</th>
										<th>Weight</th>
										<th>Product or no</th>
										<th>Productid,SEO</th>
										<th>state</th>
										<th>operate</th>
									</tr>
								</thead>
								<tbody></tbody>
							</table>
							<div id="table-pagination"></div>
						</div>
					</div>
				</div>
				<!-- edit or create -->
				<div class="c-create hide">
					<div class="c-option">
						<span class="c-option-title">Edit Coupon</span>
						<div class="group">
							<button class="btn btn-secondary btn-cancel">Cancel</button>
							<button class="btn btn-primary btn-save">Save Coupon</button>
						</div>
					</div>
					<div class="c-form row">
						<input id="couponId" hidden>
						<!-- left panel -->
						<div class="left-panel col-lg-7 col-md-12">
							<div class="card">
								<div class="card-body">
									<div class="form-group row">
										<label class="col-md-3 col-form-label" for="couponStatus">Status</label>
										<div class="controls col-md-3">
											<label class="c-switch c-switch-primary">
												<input class="c-switch-input" id="couponStatus" type="checkbox">
												<span class="c-switch-slider"></span>
											</label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="couponName">Coupon Name</label>
										<div class="controls">
											<input class="form-control" id="couponName" type="text" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="couponPriceBaseline">Scope of application</label>
										<div class="controls">
											<input class="form-control" id="couponPriceBaseline" type="number" min="0" value="0" />
										</div>
									</div>
									<div class="form-group choose_coup">
										<label class="col-form-label" for="couponType">Coupon type</label>
										<div class="controls">
											<select class="form-control" id="couponType" />
												<option value="0" selected="selected">0-Full decrement</option>
												<option value="1">1-Discount volume</option>
											</select>
										</div>
									</div>
									
									<div class="form-group open_0">
										<label class="col-form-label" for="couponPrice">Preferential strength</label>
										<div class="controls">
											<input class="form-control" id="couponPrice" type="number" min="0" value="0" />
										</div>
									</div>
									<div class="form-group open_1">
										<label class="col-form-label" for="couponPriceoff">Preferential percentage</label>
										<div class="controls">
											<select id="couponPriceoff" name="couponPriceOff" class="form-control">
											   <option value="5" selected="selected">5%</option>
											   <option value="6">6%</option>
											   <option value="7">7%</option>
											   <option value="8">8%</option>
											   <option value="9">9%</option>
											   <option value="10">10%</option>
											   <option value="11">11%</option>
											   <option value="12">12%</option>
											   <option value="13">13%</option>
											   <option value="14">14%</option>
											   <option value="15">15%</option>
											   <option value="16">16%</option>
											   <option value="17">17%</option>
											   <option value="18">18%</option>
											   <option value="19">19%</option>
											   <option value="20">20%</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="couponCode">Discount code:</label>
										<div class="controls">
											<input class="form-control" id="couponCode" type="text" />
										</div>
									</div>
									<div class="form-group choose_draw">
										<label class="col-form-label" for="couponLuckdrawType">Lottery draw</label>
										<div class="controls">
											<select class="form-control" id="couponLuckdrawType" />
												<option value="0" selected="selected">0-Support lottery</option>
												<option value="1">1-Lottery not supported</option>
											</select>
										</div>
									</div>
									<div class="form-group open_draw" style="display: none;">
										<label class="col-form-label" for="couponLuckdrawWeight">Draw weight ratio:</label>
										<div class="controls">
											<select name="couponLuckdrawWeight" id="couponLuckdrawWeight" class="form-control">
											   <option value="0">0%</option>
											   <option value="5">5%</option>
											   <option value="10">10%</option>
											   <option value="15">15%</option>
											   <option value="20">20%</option>
											   <option value="25">25%</option>
											   <option value="30">30%</option>
											   <option value="35">35%</option>
											   <option value="40">40%</option>
											   <option value="45">45%</option>
											   <option value="50">50%</option>
											   <option value="55">55%</option>
											   <option value="60">60%</option>
											   <option value="65">65%</option>
											   <option value="70">70%</option>
											   <option value="75">75%</option>
											   <option value="80">80%</option>
											   <option value="85">85%</option>
											   <option value="90">90%</option>
											   <option value="95">95%</option>
											   <option value="100">100%</option>
											</select>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- right panel -->
						<div class="right-panel col-lg-5 col-md-12">
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Bind product or not</div>
								</div>
								<div class="card-body">
									<div class="form-group choose_product">
										<label class="col-form-label" for="couponProductonlyType">Bind product or not</label>
										<div class="controls">
											<select class="form-control" id="couponProductonlyType" />
											<option value="0" selected="selected">no</option>
											<option value="1">yes</option>
											</select>
										</div>
									</div>
									<div class="form-group open_product" style="display:none">
										<label class="col-form-label" for="couponProductonlyPidstr">Product list </label>
										<div class="controls">
											<select class="form-control product-list" id="couponProductonlyPidstr" />
											</select>
										</div>
									</div>
									<div class="form-group">
										<input id="couponStarttime" hidden type="text" />
										<input id="couponEndtime" hidden type="text" />
										<label for="couponTime" class="control-label">Range Time:</label>
										<div class="controls">
											<input type="text" class="form-control daterangetimepicker" id="couponTime" placeholder="example: 2020-08-01 00:00:00 - 2020-08-01 23:59:59">
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
	<script type="text/javascript" src="${APP_PATH}/static/back/lib/datetimepicker/moment.min.js"></script>
	<script type="text/javascript" src="${APP_PATH}/static/back/lib/datetimepicker/daterangepicker.js"></script>
	
	<!-- custom script -->
	<script>
		var hasProductList = false;
		var isCreate = false;
		// init
		getCollectionsData();
		bindDateRangeEvent(function(startTime, endTime) {
			$('#couponStarttime').val(startTime);
			$('#couponEndtime').val(endTime);
		});
		// create collection
		$('.btn-create').on('click', function () {
			$('.c-create c-option-title').text('Create Collection');
			$(".open_product").hide();
			$(".open_draw").hide();
			$(".open_1").hide();
			showCreateBlock();
			resetFormData();
			getCollectionId();
			isCreate = true;
		});
		// edit collection
		$(document.body).on('click', '.btn-edit', function (e) {
			var couponId = parseInt($(this).data('id'));
			getOneCollectionData({
				couponId: couponId
			}, function(resData) {
				$('.c-create c-option-title').text('Edit Collection');
				showCreateBlock();
				initFormData(resData);
			});
		});
		// delete collection
		$(document.body).on('click', '.btn-delete', function (e) {
			var couponId = parseInt($(this).data('id'));
			$('#deleteModal').find('.modal-title').html('Delete SuperCategory!');
			$('#deleteModal').modal('show');
			$('#deleteModal .btn-ok').one('click', function () {
				deleteCollectionData({
					couponId: couponId,
				}, function() {
					getCollectionsData();
				});
			});
		});
		
		$(window).on('beforeunload', function() {
			var couponId = $('#couponId').val();
			isCreate && couponId && deleteCollectionData({
				couponId: couponId,
			});
		});
		// save collection
		$('.c-create .btn-save').on('click', function () {
			var reqData = getFormData();
			if (reqData.couponStarttime > couponEndtime) {
				toastr.error('The start time must be less than the end time !');
				$('#couponTime').focus();
				return false;
			}
			saveCollectionData(reqData, function() {
				showInitBlock();
				getCollectionsData();
				isCreate = false;
				$('#couponId').val('');
			});
		});
		// cancel collection save
		$('.c-create .btn-cancel').on('click', function () {
			if (isCreate) {
				deleteCollectionData({
					couponId: $('#couponId').val(),
				}, function() {
					showInitBlock();
					isCreate = false;					
				});
			}
			showInitBlock();
		});
		// tab create/init
		function showCreateBlock() {
			$('.c-init').addClass('hide');
			$('.c-create').removeClass('hide');
			if (!hasProductList) getAllProductData(renderAllProduct);
		}
		function showInitBlock() {
			$('.c-init').removeClass('hide');
			$('.c-create').addClass('hide');
		}
		// handle formData
		// reset data
		function resetFormData() {
			$('#couponId').val('');
			$('#couponName').val('');
			$('#couponPrice').val('');
			$('#couponStatus').prop('checked', false);
			$('#couponPriceBaseline').val('');
			$('#couponCode').val('');
			$('#couponPriceoff').val('5');
			$('#couponType').val('0');
			$('#couponLuckdrawType').val('0');
			$('#couponLuckdrawWeight').val('0');
			$('#couponProductonlyType').val('0');
			$('#couponProductonlyPidstr').val('-1');

			var initTime = initDate();
			$('#couponStarttime').val(initTime);
			$('#couponEndtime').val(initTime);
			$('.daterangetimepicker').data('daterangepicker').setStartDate(initTime);
			$('.daterangetimepicker').data('daterangepicker').setEndDate(initTime);
		}
		// getFormdData
		function getFormData() {
			var data = {};
			data.couponId = parseInt($('#couponId').val());
			data.couponName = $('#couponName').val();
			data.couponStatus = $('#couponStatus').prop('checked') ? 1 : 0;
			data.couponPriceBaseline = $('#couponPriceBaseline').val();
			data.couponCode = $('#couponCode').val();
			var couponType = $('#couponType').val();
			data.couponType = couponType;
			if (couponType) {
				data.couponPrice = 0;
				data.couponPriceoff = $('#couponPriceoff').val();
			} else {
				data.couponPrice = $('#couponPrice').val();
				data.couponPriceoff = 0;
			}
			data.couponPrice = $('#couponPrice').val();
			data.couponPriceoff = $('#couponPriceoff').val();
			data.couponLuckdrawType = $('#couponLuckdrawType').val();
            data.couponLuckdrawWeight = $('#couponLuckdrawWeight').val();
			data.couponProductonlyType = $('#couponProductonlyType').val();
			data.couponProductonlyPidstr = $('#couponProductonlyPidstr').val();
			// data.couponProductonlyPidstr = $('#couponProductonlyPidstr').find('option:selected').val();
			data.couponProductonlyPidstr = $('#couponProductonlyPidstr').find('option:selected').val();
			data.couponProductseonamesstronlyPid = $('#couponProductonlyPidstr').find('option:selected').data("seo");
			data.couponProductpronamesstronlyPid = $('#couponProductonlyPidstr').find('option:selected').data("name");
			data.couponStarttime = $('#couponStarttime').val();
			data.couponEndtime = $('#couponEndtime').val();
			return data;
		}
		// initFormData
		function initFormData(data) {
			$('#couponId').val(data.couponId);
			$('#couponName').val(data.couponName);
			$('#couponStatus').prop('checked', data.couponStatus);
			$('#couponPrice').val(data.couponPrice);
			$('#couponPriceoff').val(data.couponPriceoff);
			$('#couponPriceBaseline').val(data.couponPriceBaseline);
			$('#couponCode').val(data.couponCode);
			$('#couponType').val(data.couponType);
			var coupontypezt = data.couponType;
			if(coupontypezt==0){
				$(".open_0").show();
				$(".open_1").hide();
			}else if(coupontypezt==1){
				$(".open_1").show();
				$(".open_0").hide();
			}
			$('#couponLuckdrawType').val(data.couponLuckdrawType);
			var drawtype = data.couponLuckdrawType;
			if(drawtype==0){
				$(".open_draw").hide()
			}else if(drawtype==1){
				$(".open_draw").show()
			}
			$('#couponLuckdrawWeight').val(data.couponLuckdrawWeight);
			$('#couponProductonlyType').val(data.couponProductonlyType);
			var showproduct = data.couponProductonlyType;
			if(showproduct==0){
				$(".open_product").hide();
			}else if(showproduct==1){
				$(".open_product").show();
			}
			$('#couponProductonlyPidstr').val(data.couponProductonlyPidstr || '-1');
			$('#couponProductonlyPidstr').attr('data-val', data.couponProductonlyPidstr || '-1');
			var startTime = data.couponStarttime || initDate();
			var endTime = data.couponEndtime || initDate();
			$("#couponStarttime").val(startTime);
			$("#couponEndtime").val(endTime);
			$('.daterangetimepicker').data('daterangepicker').setStartDate(startTime);
			$('.daterangetimepicker').data('daterangepicker').setEndDate(endTime);
		}
		// callback id
		function getCollectionId() {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/MlbackCoupon/initializaCoupon",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				success: function (data) {
					if (data.code == 100) {
						$('#couponId').val(data.extend.mlbackCoupon.couponId);
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function (err) {
					toastr.error('initial superCate fail：' + err);
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		//  callback get all
		function getCollectionsData(val) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackCoupon/getMlbackCouponByPage",
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
					toastr.error('Failed to get Super-Categeory, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		
		function getOneCollectionData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackCoupon/getOneMlbackCouponDetailById",
				type: "post",
				data: reqData,
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend.mlbackCouponOne);
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function (err) {
					toastr.error('Failed to get Super-Categeory, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		/**************/
		// callback save
		function saveCollectionData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackCoupon/save",
				data: JSON.stringify(reqData),
				dataType: "json",
				contentType: 'application/json',
				type: "post",
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
				url: "${APP_PATH}/MlbackCoupon/delete",
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
				var coupontype =data[i].couponType;
			    var couponLuckdrawtype = data[i].couponLuckdrawType;
			   var couponProductonlytype = data[i].couponProductonlyType;
				htmlStr += '<tr><td>' + data[i].couponId + '</td>' +
					'<td>' + data[i].couponName + '</td>' +
					 '<td>' + data[i].couponCode + '</td>' +
					 '<td>' + data[i].couponPriceBaseline + '</td>' +
					 '<td>'+(coupontype == 1 ? 'Discount' : 'Full')+'</td>' +
					 '<td>' +(coupontype == 0 ? parseFloat(data[i].couponPrice) : '')+ '</td>' +
					 '<td>' +(coupontype == 1 ? parseFloat(data[i].couponPriceoff)+'%' : '')+ '</td>' +
					 '<td>'+(couponLuckdrawtype == 1 ? 'yes' : 'no')+'</td>' +
					 '<td>' + data[i].couponLuckdrawWeight + '%</td>' +
					 '<td>'+(couponProductonlytype == 1 ? 'yes' : 'no')+'</td>' +
					 '<td>'+(couponProductonlytype == 1 ? (data[i].couponProductonlyPidstr+' * '+data[i].couponProductseonamesstronlyPid) : 'All Product')+'</td>' +
					 '<td>' + (data[i].couponStatus ? 'enable' : 'disable') + '</td>' +
					'<td>' +
					'<button class="btn btn-primary btn-edit" data-id="' + data[i].couponId + '">' +
					'<svg class="c-icon">' +
					'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-pencil"></use>' +
					'</svg>' +
					'</button>' +
					'<button class="btn btn-danger btn-delete" data-id="' + data[i].couponId + '">' +
					'<svg class="c-icon">' +
					'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-trash"></use>' +
					'</svg>' +
					'</button>' +
					'</td></tr>';
			}
			$('.c-table-table tbody').html(htmlStr);
		}
		/************************************************************************************/
		$(".choose_coup select").change(function () {
			if ($(this).val() == 0) {
				$(".open_1").hide();
				$(".open_0").show();
			} else if ($(this).val() == 1) {
				$(".open_1").show();
				$(".open_0").hide();
			}
		});
		$(".choose_draw select").change(function () {
			if ($(this).val() == 0) {
				$(".open_draw").hide();
			} else if ($(this).val() == 1) {
				$(".open_draw").show();
			}
		});
		$(".choose_product select").change(function () {
			if ($(this).val() == 0) {
				$(".open_product").hide();
			} else if ($(this).val() == 1) {
				$(".open_product").show();
			}
		});
		/********************************/
		// callback get search data
		function getSearchCollectionsData(data) {
			$('.c-mask').show();
		
			var formData = new FormData();
			formData.append('couponName', $('#searchCollection').val());
			formData.append('pn', getPageNum());
			$.ajax({
				url: "${APP_PATH }/MlbackCoupon/backSearchByCoupon",
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
	</script>
</body>

</html>