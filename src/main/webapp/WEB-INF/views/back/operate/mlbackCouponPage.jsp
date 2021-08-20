<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>coupon list</title>
	<jsp:include page="../common/backheader.jsp" flush="true"></jsp:include>
	<link rel="stylesheet" href="${APP_PATH}/static/back/lib/datetimepicker/daterangepicker.css">
	<style>
		.collection-list, .product-list, .un-product-list {
			padding: .5rem;
			margin-top: .75rem;
			border: 1px solid #ccc;
			border-radius: .25rem;
		}
		.product-item, .collection-item {
			display: block;
		}
		.product-item+.product-item, .collection-item+.collection-item {
			margin-top: .25rem;
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
							<div class="input-group c-search">
								<svg class="c-icon">
									<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-magnifying-glass"></use>
								</svg>
								<div class="form-control">
									<input id="coupon-name" type="text" placeholder="Search coupon-name">
								</div>
								<a class="btn btn-primary input-group-addon btn-save-search" disabled>Save search</a>
							</div>
							<div class="c-table-table table-responsive-sm">
								<table class="table">
									<thead>
										<tr>
											<th>id</th>
											<th>Des</th>
											<th>Type</th>
											<th>Draw or no</th>
											<th>Draw order</th>
											<th>customer</th>
											<th>use</th>
											<th>status</th>
											<th>state</th>
											<th>date</th>
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
										<label class="col-form-label" for="couponCode">Discount code:</label>
										<div class="controls">
											<input class="form-control" id="couponCode" type="text" />
										</div>
									</div>
								</div>								
							</div>

							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Types</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<div class="controls">
											<select id="couponType" name="couponType" class="form-control">
											   <option value="0">Fixed amount</option>
											   <option value="1">Percentage</option>
											 </select>
										</div>
									</div>
									<div class="form-group type-item type-1">
										<label class="col-form-label" for="couponPrice">Discount Value(0)</label>
										<div class="controls">
											<input class="form-control" id="couponPrice" type="number" min="0" value="0" />
										</div>
									</div>
									<div class="form-group type-item type-2">
										<label class="col-form-label" for="couponPriceoff">Discount Value(1)</label>
										<div class="controls">
											<select id="couponPriceoff" name="couponPriceOff" class="form-control">
											   <option value="5">5%</option>
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
											   <option value="21">21%</option>
											   <option value="22">22%</option>
											   <option value="23">23%</option>
											   <option value="24">24%</option>
											   <option value="25">25%</option>
											   <option value="26">26%</option>
											   <option value="27">27%</option>
											   <option value="28">28%</option>
											   <option value="29">29%</option>
											   <option value="30">30%</option>
											   <option value="50">50%</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="couponPriceBaseline">Scope of application</label>
										<div class="controls">
											<input class="form-control" id="couponPriceBaseline" type="number" min="0" value="0" />
										</div>
									</div>
								</div>
							</div>

							<div class="card">
								<div class="card-title">
									<div class="card-title-name">APPLIES TO</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<div class="controls">
											<select id="couponProductonlyType" name="couponProductonlyType" class="form-control">
											   <option value="0">All products</option>
											   <option value="1">Specific products</option>
											   <option value="2">Specific collections</option>
											   <option value="3">except products</option>
											 </select>
										</div>
									</div>
									<div class="hide apply-box apply-1">
										<input id="couponProductonlyPidstr" hidden />
										<input id="couponProductseonamesstronlyPid" hidden />
										<input id="couponProductpronamesstronlyPid" hidden />
										<div class="form-group">
											<label class="col-form-label">Product list(specific)</label>
											<button class="btn btn btn-primary" style="float: right;" id="select-product">Select Product</button>
											<div class="controls product-list"></div>
										</div>
									</div>
									<div class="hide apply-box apply-2">
										<input id="couponApplyCateidstr" hidden />
										<input id="couponApplyCateSeostr" hidden />
										<div class="form-group">
											<label class="col-form-label">Collection list(specific)</label>
											<button class="btn btn btn-primary" style="float: right;" id="select-collection">Select Collection</button>
											<div class="controls collection-list"></div>
										</div>
									</div>
									<div class="hide apply-box apply-3">
										<input id="couponAllExceptPidstr" hidden />
										<input id="couponAllExceptPseostr" hidden />
										<div class="form-group">
											<label class="col-form-label">Product list(except)</label>
											<button class="btn btn btn-primary" style="float: right;" id="select-un-product">Select product</button>
											<div class="controls un-product-list"></div>
										</div>
									</div>
								</div>
							</div>

							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Customer eligibility</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<div class="controls">
											<select id="couponCodeUniqueEmailIF" name="couponCodeUniqueEmailIF" class="form-control">
											   <option value="0">Everyone</option>
											   <option value="1">Specific groups of customers</option>
											 </select>
										</div>
									</div>
									<div class="form-group type-item customer-1">
										<label class="col-form-label" for="couponCodeUniqueEmail">Customer Email groups</label>
										<div class="controls">										
											<textarea class="form-control" rows="3" id="couponCodeUniqueEmail" placeholder="eg:name@qq.com"></textarea>
										</div>
									</div>
								</div>
							</div>

							<!-- lottery -->
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Lottery</div>
								</div>
								<div class="card-body">
									<div class="form-group choose_draw">
										<label class="col-form-label" for="couponLuckdrawType">Lottery draw</label>
										<div class="controls">
											<select class="form-control" id="couponLuckdrawType" />
												<option value="0">0-Lottery not supported</option>
												<option value="1">1-Support lottery</option>
											</select>
										</div>
									</div>
									<div class="form-group choose_draw">
										<label class="col-form-label" for="couponLuckdrawSort">Lottery Order</label>
										<div class="controls">
											<select name="couponLuckdrawSort" id="couponLuckdrawSort" class="form-control">
											   <option value="0">无排序</option>
											   <option value="1">1</option>
											   <option value="2">2</option>
											   <option value="3">3</option>
											   <option value="4">4</option>
											   <option value="5">5</option>
											   <option value="6">6</option>
											   <option value="7">7</option>
											   <option value="8">8</option>
											</select>
										</div>
									</div>
									<div class="form-group open_draw">
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
									<!-- image -->
									<div>
										<h4>Image</h4>
										<div class="c-upload-img">
											<svg class="c-icon">
												<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image-plus"></use>
											</svg>
											<div class="c-backshow"></div>						
											<input id="couponImgUrl" type="file" accept="image/png, image/jpeg, image/gif" />										
											<!-- spinner -->
											<div class="spinner">
												<div class="spinner-border" role="status" aria-hidden="true"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- right panel -->
						<div class="right-panel col-lg-5 col-md-12">
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Coupon Use-times</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label for="couponAlltimes" class="control-label">All Times:</label>
										<div class="controls">
											<input type="number" class="form-control" id="couponAlltimes">
										</div>
									</div>
									<div class="form-group">
										<label for="couponTimes" class="control-label">Used Times:</label>
										<div class="controls">
											<input type="text" class="form-control" id="couponTimes" disabled>
										</div>
									</div>
								</div>
							</div>
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Active dates</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label for="couponStarttime" class="control-label">Start Time:</label>
										<div class="controls">
											<input type="text" class="form-control" id="couponStarttime" placeholder="example: 2020-08-01 23:59:59">
										</div>
									</div>
									<div class="form-group">
										<label for="couponEndtime" class="control-label">End Time:</label>
										<div class="controls">
											<input type="text" class="form-control" id="couponEndtime" placeholder="example: 2020-08-01 23:59:59">
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

	<script type="text/javascript" src="${APP_PATH}/static/back/lib/datetimepicker/moment.min.js"></script>
	<script type="text/javascript" src="${APP_PATH}/static/back/lib/datetimepicker/daterangepicker.js"></script>

	<!-- custom script -->
	<script>
		var isCreate = false, oldTime = (new Date()).getTime(), timer = null, storageName = "coupons", flag;
		var pId = [], pName = [], pSeo = [];
		var cId = [], cSeo = [];
		var suId = [], suSeo = [];
		// init
		renderTabItems();
		getCouponsData();
		// pagination a-click
		$(document.body).on('click', '#table-pagination li', function (e) {
			getTabSearchData($('.c-table-tab-item.active'));
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
		$(document.body).on('click', '.delete-table-tab-item', deleteTableTabItem);// save search
		$('.btn-save-search').on('click', function () {
			var searchCouponVal = {
				coupon: $('#coupon-name').val()
			};
			// cancel repeat add save-search
			if (checkNewItem(searchCouponVal)) return;
			if (searchCouponVal.coupon) {
				addStorageItem(searchCouponVal);
				$('.c-table-tab-tempory').html('');
				createTableTabItem(searchCouponVal);
				addTableTabItem(searchCouponVal, $('.c-table-tab-item').length);
			}
		});
		// search
		$('#coupon-name').on('keyup', function() {
			var distanceTime = 1000,
				newTime =  (new Date()).getTime();
			if (newTime - oldTime < 1000) clearTimeout(timer);
			oldTime = newTime;
			timer = setTimeout(function() {
				updateSearchData();
			}, distanceTime);
		});
		// create
		$('.btn-create').on('click', function () {
			$('.c-create c-option-title').text('Create Coupon');

			showCreateBlock();
			getCouponId();
			isCreate = true;
		});
		// Edit
		$(document.body).on('click', '.btn-edit', function (e) {
			var couponId = parseInt($(this).data('id'));
			getOneCouponData({
				couponId: couponId
			}, function(resData) {
				$('.c-create c-option-title').text('Edit Coupon');
				showCreateBlock();
				initFormData(resData);
			});
		});
		// delete
		$(document.body).on('click', '.btn-delete', function (e) {
			var couponId = parseInt($(this).data('id'));
			$('#deleteModal').find('.modal-title').html('Delete coupon!');
			$('#deleteModal').modal('show');
			$('#deleteModal .btn-ok').one('click', function () {
				deleteCouponData({
					couponId: couponId,
				}, function() {
					getCouponsData();
				});
			});
		});
		
		$(window).on('beforeunload', function() {
			var couponId = $('#couponId').val();
			isCreate && couponId && deleteCouponData({
				couponId: couponId,
			});
		});
		// save
		$('.c-create .btn-save').on('click', function () {
			var reqData = getFormData();
			if (reqData.couponStarttime > couponEndtime) {
				toastr.error('The start time must be less than the end time !');
				$('#couponTime').focus();
				return false;
			}
			saveCouponData(reqData, function() {
				showInitBlock();
				getCouponsData();
				isCreate = false;
				$('#couponId').val('');
			});
		});
		// cancel save
		$('.c-create .btn-cancel').on('click', function () {
			if (isCreate) {
				deleteCouponData({
					couponId: $('#couponId').val(),
				}, function() {
					showInitBlock();
					isCreate = false;					
				});
			}
			showInitBlock();
		});// upload img
		$('#couponImgUrl').on('change', function(e) {
			var $this = $(this);
			var file = $this[0].files[0];
			var formData = new FormData();

			if (!file) return false;

			$this.parent().find('.spinner').show();
			$this.val('');

			formData.append('type', 'coupon');
			formData.append('image', file);
			formData.append('couponId', parseInt($('#couponId').val()));
			$this.val('');

			$.ajax({
				url: "${APP_PATH}/ImageUpload/uploadCouponImg",
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
					$('.c-upload-img .spinner').hide();
				}
			});
		});
		// search status change
		function updateSearchData() {
			var searchCouponVal = {
				coupon: $('#coupon-name').val()
			};
			// inital pagination num
			setPageNum(1);

			$('.c-table-tab-item.active').removeClass('active');
			$('.c-table-tab-tempory').html(createTableTabItem(searchCouponVal).addClass('active'));
			getTabSearchData($('.c-table-tab-tempory .c-table-tab-item'));
		}
		// get Data for table
		function getTabSearchData($this) {
			var dataVal = $this.data('val');
			if (dataVal && dataVal.coupon) {
				$('#coupon-name').val(dataVal.coupon || '');
				getSearchCouponsData();
			} else {
				$('#coupon-name').val('');
				initActiveItemNum();
				getCouponsData();
			}
		}
		function addPicture(el, data) {
			var parentEl = el.parent();
			el.attr('data-val', JSON.stringify(data));
			parentEl.addClass('active');
			parentEl.find('.c-backshow').html('<img src="'+ encodeUrl(data.thumImageUrl) + '" />');
		}
		function resetPicture(el) {
			var parentEl = el.parent();
			el.attr('data-val', '');
			parentEl.removeClass('active');
			parentEl.find('.c-backshow').html('');
		}
		// tab create/init
		function showCreateBlock() {
			$('.c-init').addClass('hide');
			$('.c-create').removeClass('hide');
			resetFormData();
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
			$('#couponPriceBaseline').val('0');
			$('#couponCode').val('');
			$('#couponPriceoff').val('5');

			$('#couponType').val('0');
			$('.type-item').addClass('hide');
			$('.type-1').removeClass('hide');

			$('#couponProductonlyType').val('0');
			$('.apply-box').addClass('hide');

			$('#couponProductonlyPidstr').val('');
			$('#couponProductseonamesstronlyPid').val('');
			$('#couponProductpronamesstronlyPid').val('');
			$('#couponApplyCateidstr').val('');
			$('#couponApplyCateSeostr').val('');
			$('.product-list, .collection-list, .un-product-list').html('<p class="text-align: center; font-style: italic;">Empty here...</p>');
			pId = [];
			pName = [];
			pSeo = [];
			cId = [];
			cSeo = [];
			suId = [];
			suSeo = [];

			$('#couponCodeUniqueEmailIF').val('0');
			$('#couponCodeUniqueEmail').val('');
			$('.customer-1').addClass('hide');

			$('#couponLuckdrawType').val('0');
			$('#couponLuckdrawWeight').val('0');
			$('#couponLuckdrawSort').val('0');
			
			resetPicture($('#couponImgUrl'));
			
			$('#couponTimes').val('0');
			$('#couponAlltimes').val('99');

			$('#couponStarttime').daterangepicker({
				singleDatePicker: true,
				timePicker: true,
				timePicker24Hour: true,
				timePickerSeconds: true,
				showWeekNumbers: true,
				locale: {
					format: format,
				},
				startDate: initDate(),
			});
			$('#couponEndtime').daterangepicker({
				singleDatePicker: true,
				timePicker: true,
				timePicker24Hour: true,
				timePickerSeconds: true,
				showWeekNumbers: true,
				locale: {
					format: format,
				},
				startDate: '2099-12-31',
			});
		}
		// getFormdData
		function getFormData() {
			var data = {}, imageData, couponType;

			data.couponId = parseInt($('#couponId').val());
			data.couponName = $('#couponName').val();
			data.couponStatus = $('#couponStatus').prop('checked') ? 1 : 0;
			data.couponPriceBaseline = $('#couponPriceBaseline').val() || 0;
			data.couponCode = $('#couponCode').val();

			couponType = $('#couponType').val();
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

			data.couponProductonlyType = $('#couponProductonlyType').val();
			data.couponProductonlyPidstr = $('#couponProductonlyPidstr').val();
			data.couponProductseonamesstronlyPid = $('#couponProductseonamesstronlyPid').val();
			data.couponProductpronamesstronlyPid = $('#couponProductpronamesstronlyPid').val();
			if (data.couponProductonlyPidstr && data.couponProductpronamesstronlyPid && data.couponProductseonamesstronlyPid) {
				pId = data.couponProductonlyPidstr.split(',');
				pName = data.couponProductpronamesstronlyPid.split(',');
				pSeo = data.couponProductseonamesstronlyPid.split(',');
			}
			renderProductList();

			data.couponApplyCateidstr = $('#couponApplyCateidstr').val();
			data.couponApplyCateSeostr = $('#couponApplyCateSeostr').val();
			if (data.couponApplyCateidstr && data.couponApplyCateSeostr) {
				cId = data.couponApplyCateidstr.split(',');
				cSeo = data.couponApplyCateSeostr.split(',');
			}
			renderCollectionList();

			data.couponAllExceptPidstr = $('#couponAllExceptPidstr').val();
			data.couponAllExceptPseostr = $('#couponAllExceptPseostr').val();
			if (data.couponAllExceptPidstr && data.couponAllExceptPseostr) {
				suId = data.couponAllExceptPidstr.split(',');
				suSeo = data.couponAllExceptPseostr.split(',');
			}			
			renderProductList2();
			
			data.couponCodeUniqueEmailIF = $('#couponCodeUniqueEmailIF').val();
			data.couponCodeUniqueEmail = $('#couponCodeUniqueEmail').val();

			data.couponLuckdrawType = $('#couponLuckdrawType').val();
            data.couponLuckdrawWeight = $('#couponLuckdrawWeight').val();
            data.couponLuckdrawSort = $('#couponLuckdrawSort').val();
            imageData = $('#couponImgUrl').attr('data-val') && JSON.parse($('#couponImgUrl').attr('data-val'));
			data.couponImgUrl = imageData.imageUrl;

			data.couponTimes = $('#couponTimes').val();
			var allTimes = parseInt($('#couponAlltimes').val());
			if (allTimes) {
				data.couponAlltimes = allTimes;
			}

			data.couponStarttime = $('#couponStarttime').val();
			data.couponEndtime = $('#couponEndtime').val();
			return data;
		}
		// initFormData
		function initFormData(data) {
			$('#couponId').val(data.couponId);
			$('#couponName').val(data.couponName);
			$('#couponStatus').prop('checked', data.couponStatus);
			$('#couponPriceBaseline').val(data.couponPriceBaseline || '0');
			$('#couponCode').val(data.couponCode);

			var couponType = data.couponType;
			$('#couponType').val(couponType || '0');
			$('.type-item').addClass('hide');
			if (couponType == "0") {
				$(".type-1").removeClass('hide');
				$('#couponPrice').val(data.couponPrice);
			} else if (couponType == "1") {
				$(".type-2").removeClass('hide');
				$('#couponPriceoff').val(data.couponPriceoff);
			}

			var onlyType = data.couponProductonlyType;
			$('#couponProductonlyType').val(onlyType || '0');
			$('.apply-box').addClass('hide');
			if (onlyType == "1") {
				$(".apply-1").removeClass('hide');
				$('#couponProductonlyPidstr').val(data.couponProductonlyPidstr || '');
				$('#couponProductseonamesstronlyPid').val(data.couponProductseonamesstronlyPid || '');
				$('#couponProductpronamesstronlyPid').val(data.couponProductpronamesstronlyPid || '');
				if (data.couponProductseonamesstronlyPid &&  data.couponProductpronamesstronlyPid) {
					pId = data.couponProductonlyPidstr.split(',');
					pName = data.couponProductpronamesstronlyPid.split(',');
					pSeo = data.couponProductseonamesstronlyPid.split(',')
				}
				renderProductList();
			} else if (onlyType == "2") {
				$(".apply-2").removeClass('hide');
				$('#couponApplyCateidstr').val(data.couponApplyCateidstr || '');
				$('#couponApplyCateSeostr').val(data.couponApplyCateSeostr || '');
				if (data.couponApplyCateidstr && data.couponApplyCateSeostr) {
					cId = data.couponApplyCateidstr.split(',');
					cSeo = data.couponApplyCateSeostr.split(',');
				}
				renderCollectionList();
			} else if (onlyType == "3") {
				$(".apply-3").removeClass('hide');
				$('#couponAllExceptPidstr').val(data.couponAllExceptPidstr || '');
				$('#couponAllExceptPseostr').val(data.couponAllExceptPseostr || '');
				if (data.couponAllExceptPidstr && data.couponAllExceptPseostr) {
					suId = data.couponAllExceptPidstr.split(',');
					suSeo = data.couponAllExceptPseostr.split(',');
				}
				renderProductList2();
			}

			var isUniqueCustomer = data.couponCodeUniqueEmailIF;
			$('#couponCodeUniqueEmailIF').val(isUniqueCustomer);
			if (isUniqueCustomer) {
				$('#couponCodeUniqueEmail').val(data.couponCodeUniqueEmail);
				$('.customer-1').removeClass('hide');
			}

			$('#couponLuckdrawType').val(data.couponLuckdrawType);
			$('#couponLuckdrawWeight').val(data.couponLuckdrawWeight);
			$('#couponLuckdrawSort').val(data.couponLuckdrawSort);
			if (data.couponImgUrl) {
				addPicture($('#couponImgUrl'), {
					imageUrl: data.couponImgUrl,
					thumImageUrl: data.couponImgUrl
				});
			} else {
				resetPicture($('#couponImgUrl'));
			}

			$('#couponTimes').val(parseInt(data.couponTimes || '0'));
			$('#couponAlltimes').val(parseInt(data.couponAlltimes || '0'));

			$('#couponStarttime').daterangepicker({
				singleDatePicker: true,
				timePicker: true,
				timePicker24Hour: true,
				timePickerSeconds: true,
				showWeekNumbers: true,
				locale: {
					format: format,
				},
				startDate: (data.couponStarttime || initDate()),
			});
			$('#couponEndtime').daterangepicker({
				singleDatePicker: true,
				timePicker: true,
				timePicker24Hour: true,
				timePickerSeconds: true,
				showWeekNumbers: true,
				locale: {
					format: format,
				},
				startDate: (data.couponEndtime || '2099-12-31'),
			});
		}

		// slelect content
		$('#select-product').on('click', function() {
			var $selectResult = $('#editModal .select-result .value');

			flag = 'sp';
			$('#editModal .select-result').removeClass('hide');

			if (pId.length) {
				$selectResult.text(pId.join(','));
			} else {
				$selectResult.text('');
			}

			getAllProductData(renderAllProduct2);

			$('#editModal').find('.modal-title').text('Select product ...');
			$('#editModal').modal('show');
		});
		$('#select-collection').on('click', function() {
			var $selectResult = $('#editModal .select-result .value');

			flag = 'sc';
			$('#editModal .select-result').removeClass('hide');

			if (cId.length) {
				$selectResult.text(cId.join(','));
			} else {
				$selectResult.text('');
			}

			getAllCollectionData(renderAllCollection2);

			$('#editModal').find('.modal-title').text('Select collection ...');
			$('#editModal').modal('show');
		});
		$('#select-un-product').on('click', function() {
			var $selectResult = $('#editModal .select-result .value');

			flag = 'sup';
			$('#editModal .select-result').removeClass('hide');

			if (suId.length) {
				$selectResult.text(suId.join(','));
			} else {
				$selectResult.text('');
			}

			getAllProductData(renderAllProduct3);

			$('#editModal').find('.modal-title').text('Select collection ...');
			$('#editModal').modal('show');
		});
		$('#editModal .btn-ok').on('click', function() {
			if (flag == 'sp') {
				if (pId.length && pName.length && (pId.length == pName.length)) {
					$('#couponProductonlyPidstr').val(pId.join(','));
					$('#couponProductpronamesstronlyPid').val(pName.join(','));
					$('#couponProductseonamesstronlyPid').val(pSeo.join(','));
					// render product list
					renderProductList();
				} else {
					pId = $('#couponProductonlyPidstr').val() ? $('#couponProductonlyPidstr').val().split(',') : [];
					pName = $('#couponProductpronamesstronlyPid').val() ? $('#couponProductpronamesstronlyPid').val().split(',') : [];
					pSeo = $('#couponProductseonamesstronlyPid').val() ? $('#couponProductseonamesstronlyPid').val().split(',') : [];
					console.log('数据错误！！！');
				}
			}

			if (flag == 'sc') {
				if (cId.length && cSeo.length && (cId.length == cSeo.length)) {
					$('#couponApplyCateidstr').val(cId.join(','));
					$('#couponApplyCateSeostr').val(cSeo.join(','));
					// render product list
					renderCollectionList();
				} else {
					cId = $('#couponApplyCateidstr').val() ? $('#couponApplyCateidstr').val().split(',') : [];
					cSeo = $('#couponApplyCateSeostr').val() ? $('#couponApplyCateSeostr').val().split(',') : [];
					console.log('数据错误！！！');
				}
			}
			
			if (flag = 'sup') {
				if (suId.length && suSeo.length && (suId.length == suSeo.length)) {
					$('#couponAllExceptPidstr').val(suId.join(','));
					$('#couponAllExceptPseostr').val(suSeo.join(','));
					// render product list
					renderProductList2();
				} else {
					suId = $('#couponAllExceptPidstr').val() ? $('#couponAllExceptPidstr').val().split(',') : [];
					suSeo = $('#couponAllExceptPseostr').val() ? $('#couponAllExceptPseostr').val().split(',') : [];
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
			if (flag == 'sp') {
				if ($this.prop('checked')) {
					pId.push(id);
					pName.push(name);
					pSeo.push(seo);
				} else {
					var idx = pId.indexOf(id);
					var namex = pName.indexOf(name);
					var seox = pSeo.indexOf(seo);
					idx > -1  && pId.splice(idx, 1);
					namex > -1  && pName.splice(namex, 1);
					seox > -1  && pSeo.splice(seox, 1);
				}
				$('#editModal .select-result .value').text(pId.join(','));
			}

			if (flag == 'sc') {
				if ($this.prop('checked')) {
					cId.push(id);
					cSeo.push(seo);
				} else {
					var idx = cId.indexOf(id);
					var seox = cSeo.indexOf(seo);
					idx > -1  && cId.splice(idx, 1);
					seox > -1  && cSeo.splice(seox, 1);
				}
				$('#editModal .select-result .value').text(cId.join(', '));
			}
			
			if (flag == 'sup') {
				if ($this.prop('checked')) {
					suId.push(id);
					suSeo.push(seo);
				} else {
					var idx = suId.indexOf(id);
					var seox = suSeo.indexOf(seo);
					idx > -1  && suId.splice(idx, 1);
					seox > -1  && suSeo.splice(seox, 1);
				}
				$('#editModal .select-result .value').text(suId.join(', '));
			}
		});
		$('#editModal .btn-ok').on('click', function() {
			if (flag == 'sp') {
				if (pId.length && pName.length && (pId.length == pName.length)) {
					$('#couponProductonlyPidstr').val(pId.join(','));
					$('#couponProductpronamesstronlyPid').val(pName.join(','));
					$('#couponProductseonamesstronlyPid').val(pSeo.join(','));
					// render product list
					renderCollectionList();
				} else {
					pId = $('#couponProductonlyPidstr').val() ? $('#couponProductonlyPidstr').val().split(',') : [];
					pName = $('#couponProductpronamesstronlyPid').val() ? $('#couponProductpronamesstronlyPid').val().split(',') : [];
					pSeo = $('#couponProductseonamesstronlyPid').val() ? $('#couponProductseonamesstronlyPid').val().split(',') : [];
					console.log('数据错误！！！');
				}
			}

			if (flag == 'sc') {
				if (cId.length && cSeo.length && (cId.length == cSeo.length)) {
					$('#couponApplyCateidstr').val(cId.join(','));
					$('#producttogethercProSeoNameStr').val(cSeo.join(','));
					// render product list
					renderCollectionList();
				} else {
					cId = $('#couponApplyCateidstr').val() ? $('#couponApplyCateidstr').val().split(',') : [];
					cSeo = $('#couponApplyCateSeostr').val() ? $('#couponApplyCateSeostr').val().split(',') : [];
					console.log('数据错误！！！');
				}				
			}
			$('#editModal').modal('hide');
		});
		// render product-list
		function renderProductList() {
			var htmlStr = '';
			if (pName.length) {
				pName.forEach(function(item, idx) {
					htmlStr += '<a class="product-item" href="${APP_PATH}/' + pSeo[idx] + '.html" target="_blank">' + item + '</a>';
				});
			} else {
				htmlStr = '<p class="text-align: center; font-style: italic;">Empty here...</p>';
			}
			$('.product-list').html(htmlStr);
		}
		// render product-list
		function renderProductList2() {
			var htmlStr = '';
			if (suSeo.length) {
				suSeo.forEach(function(item, idx) {
					htmlStr += '<a class="product-item" href="${APP_PATH}/' + item + '.html" target="_blank">' + item + '</a>';
				});
			} else {
				htmlStr = '<p class="text-align: center; font-style: italic;">Empty here...</p>';
			}
			$('.un-product-list').html(htmlStr);
		}
		// render collection-list
		function renderCollectionList() {
			var htmlStr = '';
			if (cSeo.length) {
				cSeo.forEach(function(item, idx) {
					htmlStr += '<a class="collection-item" href="${APP_PATH}/search/' + item + '.html" target="_blank">' + item + '</a>';
				});
			} else {
				htmlStr = '<p class="text-align: center; font-style: italic;">Empty here...</p>';
			}
			$('.collection-list').html(htmlStr);
		}
		// render all product
		function renderAllProduct2(data) {
			var htmlStr = '';
			for (var i = 0, len = data.length; i < len; i += 1) {
				var id = data[i].productId;
				var name = data[i].productName;
				var seo = data[i].productSeo;
				htmlStr += '<div class="page-area-item"><div class="form-check checkbox">' +
						'<input class="form-check-input" id="'+ id +'" type="checkbox"'+ (pId.indexOf('' + id) > -1 ? ' checked' : '') +' value="" data-id="'+ id +'" data-name="'+ name +'" data-seo="'+ seo +'">' +
						'<label class="form-check-label" for="'+ id +'">'+
							'<span class="table-th">'+ id +'</span>' +
							'<span class="table-th"><a class="badge '+ (data[i].productStatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].productStatus ? '已上架' : '未上架') + '</a></span>' +
							'<span class="table-th" style="width: 100%;">'+ name +'</span>' +
						'</label>' +
					'</div></div>';
			}
			$('#editModal .modal-body-body').html(htmlStr);
			$('#editModal .spinner').hide();
		}
		function renderAllProduct3(data) {
			var htmlStr = '';
			for (var i = 0, len = data.length; i < len; i += 1) {
				var id = data[i].productId;
				var name = data[i].productName;
				var seo = data[i].productSeo;
				htmlStr += '<div class="page-area-item"><div class="form-check checkbox">' +
						'<input class="form-check-input" id="'+ id +'" type="checkbox"'+ (suId.indexOf('' + id) > -1 ? ' checked' : '') +' value="" data-id="'+ id +'" data-name="'+ name +'" data-seo="'+ seo +'">' +
						'<label class="form-check-label" for="'+ id +'">'+
							'<span class="table-th">'+ id +'</span>' +
							'<span class="table-th"><a class="badge '+ (data[i].productStatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].productStatus ? '已上架' : '未上架') + '</a></span>' +
							'<span class="table-th" style="width: 100%;">'+ name +'</span>' +
						'</label>' +
					'</div></div>';
			}
			$('#editModal .modal-body-body').html(htmlStr);
			$('#editModal .spinner').hide();
		}
		// render all collection
		function renderAllCollection2(data, arrId) {
			var htmlStr = '';
			for (var i = 0, len = data.length; i < len; i += 1) {
				var id = data[i].categoryId;
				var name = data[i].categoryName;
				var seo = data[i].categorySeo;
				htmlStr += '<div class="page-area-item"><div class="form-check checkbox">' +
					'<input class="form-check-input" id="'+ id +'" type="checkbox"'+ (cId.indexOf('' + id) > -1 ? ' checked' : '') +' value="" data-id="'+ id +'" data-name="'+ name +'" data-seo="'+ seo +'">' +
					'<label class="form-check-label" for="'+ id +'">'+
						'<span class="table-th">'+ id +'</span>' +
						'<span class="table-th"><a class="badge '+ (data[i].categoryStatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].categoryStatus ? '已上架' : '未上架') + '</a></span>' +
						'<span class="table-th" style="width: 100%;">'+ data[i].categoryDesc +'</span>' +
					'</label>' +
				'</div></div>';
			}
			$('#editModal .modal-body-body').html(htmlStr);
			$('#editModal .spinner').hide();
		}
		// callback id
		function getCouponId() {
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
		function getCouponsData(val) {
			$('.c-mask').show();
			$.ajax({
				//url: "${APP_PATH}/MlbackCoupon/getMlbackCouponByPage",
				url: "${APP_PATH}/MlbackCoupon/getMlbackCouponByPageOrderByStatus",
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
		// callback get one
		function getOneCouponData(reqData, callback) {
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
		// callback get search data
		function getSearchCouponsData(data) {
			$('.c-mask').show();

			var formData = new FormData();
			formData.append('couponName', $('#coupon-name').val());
			formData.append('pn', getPageNum());

			$.ajax({
				url: "${APP_PATH}/MlbackCoupon/backSearchByCouponCode",
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
					toastr.error('Failed to get coupon-list, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback save
		function saveCouponData(reqData, callback) {
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
		function deleteCouponData(reqData, callback) {
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
			function generateCouponHtml(data) {
				if (!data.couponProductonlyType || data.couponProductonlyType == '0') {
					return '<td>0 - All product</td>';
				}

				if (data.couponProductonlyType == '1') {
					return '<td>1 - Specific product</td>';
				}

				if (data.couponProductonlyType == '2') {
					return '<td>2 - Specific collection</td>';
				}

				if (data.couponProductonlyType == '3') {
					return '<td>3 - Except product</td>';
				}
			}
			var htmlStr = '';
			for (var i = 0, len = data.length; i < len; i += 1) {
				var coupontype = data[i].couponType;
			    var couponLuckdrawtype = data[i].couponLuckdrawType;
			    var specificCustomer = data[i].couponCodeUniqueEmailIF;
			    var currentTime = initDate();
			    console.log(!data[i].couponEndtime)
				htmlStr += '<tr><td>' + data[i].couponId + '</td>' +
					'<td>' +
						'<div class="td-code" style="font-weight: bold">' + data[i].couponCode + '</div>' +
						'<div class="td-des">' +
							(coupontype == '1' ? data[i].couponPriceoff + '%' || '--' : '$' + data[i].couponPrice || '--') + ' off Promotion' +
							(data[i].couponPriceBaseline ? ' • Minimum purchase of $' + data[i].couponPriceBaseline : ' • --') +
						'</div>' +
					'</td>' +
					generateCouponHtml(data[i]) +
					'<td>'+
						'<div>' + (couponLuckdrawtype == 1 ? 'support' : 'un-support') + '</div>' +
						(couponLuckdrawtype ? '<div>' + data[i].couponLuckdrawWeight  + '%</div>' : '--') +
					'</td>' +
					'<td>' + (data[i].couponLuckdrawSort ? data[i].couponLuckdrawSort : '--') + '</td>' +
					'<td>' + (specificCustomer ? 'partial-customer' : 'everyone') + '</td>' +
					'<td>' + ((data[i].couponTimes || '0') + '/' + (data[i].couponAlltimes || '--')) + '</td>' +
					'<td><a class="badge '+ (data[i].couponStatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].couponStatus ? 'enable' : 'disable') + '</a></td>' +
					'<td><a class="badge badge-gray" href="javascript:;">' + (data[i].couponEndtime && data[i].couponStarttime ? (data[i].couponEndtime >= currentTime && data[i].couponStarttime <= currentTime ? 'Active' : 'Expired') : '--')  + '</a></td>' +
					'<td>'+ (data[i].couponStarttime || '--') + '<br>' + (data[i].couponEndtime || '--') +'</td>' +
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
		// change
		$('#couponType').on('change', function () {
			$('.type-item').addClass('hide');
			if ($(this).val() == "0") {
				$(".type-1").removeClass('hide');
			} else if ($(this).val() == "1") {
				$(".type-2").removeClass('hide');
			}
		});
		$('#couponProductonlyType').on('change', function () {
			$(".apply-box").addClass('hide');
			if ($(this).val() == "0") {
				return ;
			} else if ($(this).val() == "1") {
				$(".apply-1").removeClass('hide');
			} else if ($(this).val() == "2") {
				$(".apply-2").removeClass('hide');
			} else if ($(this).val() == "3") {
				$(".apply-3").removeClass('hide');
			}
		});
		$('#couponCodeUniqueEmailIF').on('change', function() {
			if ($(this).val() == '0') {
				$('.customer-1').addClass('hide');
			} else if ($(this).val() == '1') {
				$('.customer-1').removeClass('hide');
			}
		});
		// callback get search data
		function getSearchCouponsData(data) {
			$('.c-mask').show();

			var formData = new FormData();
			formData.append('couponName', $('#coupon-name').val());
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
					toastr.error('Failed to get Coupon, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
	</script>
</body>

</html>