<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Whole Sale</title>
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
						<span class="c-option-title">Whole-sale list</span>
						<!-- <button class="btn btn-primary btn-create">Create Whole-sale</button> -->
					</div>
					<div class="ecpp-sync row">
						<!-- <div class="form-group col-md-4">
							<div class="controls">
								<input hidden id="wholesale-create-time" />
								<input hidden id="wholesale-confirm-time" />
								<input class="form-control daterangetimepicker" id="wholesale-time" type="text" />
							</div>
						</div> -->						
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
									<input id="wholesale-name" type="text" placeholder="Search customer-name">						
									<select id="wholesale-status">
										<option value="-1">plz select status...</option>
										<option value="0">uncontacted</option>
										<option value="1">contacted</option>
									</select>
								</div>
								<a class="btn btn-primary input-group-addon btn-save-search" disabled>Save search</a>
							</div>
							<div class="c-table-table table-responsive-sm">
								<table class="table">
									<thead>
										<tr>
											<th>id</th>
											<th>customer-name</th>
											<th>email</th>
											<th>country</th>
											<th>telephone</th>
											<th>whatsapp</th>
											<th>customerType</th>
											<th>message</th>
											<th>time</th>
											<th>customerStatus</th>
											<th>operate-log</th>
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
				<div class="c-view hide">
					<div class="c-option">
						<span class="c-option-title">View Whole-sale</span>
						<div class="group">
							<button class="btn btn-secondary btn-back">Back</button>
							<button class="btn btn-primary btn-save">Save</button>
						</div>
					</div>
					<div class="c-form row">
						<input id="wholesaleId" hidden>
						<!-- left panel -->
						<div class="left-panel col-lg-7 col-md-12">
							<div class="card">
								<div class="card-body">
									<div class="form-group row">
										<label class="col-md-3 col-form-label" for="wholesaleCustomerStatus">Status</label>
										<div class="controls col-md-3">
											<label class="c-switch c-switch-primary">
												<input class="c-switch-input" id="wholesaleCustomerStatus" type="checkbox">
												<span class="c-switch-slider"></span>
											</label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-md-6">
											<label class="col-form-label" for="wholesaleCustomerName">Customer Name</label>
											<div class="controls">
												<input class="form-control" id="wholesaleCustomerName" type="text" disabled />
											</div>
										</div>
										<div class="form-group col-md-6">
											<label class="col-form-label" for="wholesaleCustomerEmail">E-mail</label>
											<div class="controls">
												<input class="form-control" id="wholesaleCustomerEmail" type="text" disabled />
											</div>
										</div>
										<div class="form-group col-md-6">
											<label class="col-form-label" for="wholesaleCustomerCountry">Country</label>
											<div class="controls">
												<input class="form-control" id="wholesaleCustomerCountry" type="text" disabled />
											</div>
										</div>
										<div class="form-group col-md-6">
											<label class="col-form-label" for="wholesaleCustomerTelephone">Telephone</label>
											<div class="controls">
												<input class="form-control" id="wholesaleCustomerTelephone" type="text" disabled />
											</div>
										</div>
										<div class="form-group col-md-6">
											<label class="col-form-label" for="wholesaleCustomerWatsapp">Whatsapp</label>
											<div class="controls">
												<input class="form-control" id="wholesaleCustomerWatsapp" type="text" disabled />
											</div>
										</div>
										<div class="form-group col-md-6">
											<label class="col-form-label" for="wholesaleCustomerTypeName">CustomerType</label>
											<div class="controls">
												<input class="form-control" id="wholesaleCustomerTypeName" type="text" disabled />
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="wholesaleCustomerMessage">Message</label>
										<div class="controls">
											<textarea  rows="5" class="form-control" id="wholesaleCustomerMessage" disabled></textarea>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="wholesaleOperateDeatil">Operate-log</label>
										<div class="controls">
											<textarea  rows="5" class="form-control" id="wholesaleOperateDeatil" placeholder="大客户/问价的/xxx"></textarea>
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
		var hasSuperCateList = false;
		var isCreate = false, oldTime = (new Date()).getTime(), timer = null, storageName = "whole-sale";
		// init
		var date = new Date();
		var ymd = date.getFullYear() + '-' + (date.getMonth() + 1 > 9 ? date.getMonth() + 1 : '0' + (date.getMonth() + 1)) + '-' + (date.getDate() > 9 ? date.getDate() : '0' + date.getDate());
		$('#wholesale-create-time').val(ymd + ' 00:00:00');
		$('#wholesale-confirm-time').val(ymd + ' 23:59:59');
		bindDateRangeEvent(function(startTime, endTime, self) {
			$('#wholesale-create-time').val(startTime);
			$('#wholesale-confirm-time').val(endTime);		
		});
		renderTabItems();
		// pagination a-click
		$(document.body).on('click', '#table-pagination li', function (e) {
			getTabSearchData($('.c-table-tab-item.active'));
		});
		// create wholesale
		$('.btn-create').on('click', function () {
			$('.c-view c-option-title').text('Create Whole-sale');
			showViewBlock();
			getWholesaleId();
			isCreate = true;
		});
		$('.btn-back').on('click', function () {
			$('.c-view c-option-title').text('Whole-sale List');
			showInitBlock();
			resetFormData();
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
			var searchWholesaleVal = {
				status: $('#wholesale-status').val(),
				statustext: $('#wholesale-status').find('option:selected').text(),
				customername: $('#wholesale-name').val()
			};
			// cancel repeat add save-search
			if (checkNewItem(searchWholesaleVal)) return;
			if (parseInt(searchWholesaleVal.status) < 0) searchWholesaleVal.statustext = "";
			if (searchWholesaleVal.status > -1 || searchWholesaleVal.customername) {
				addStorageItem(searchWholesaleVal);
				$('.c-table-tab-tempory').html('');
				createTableTabItem(searchWholesaleVal);
				addTableTabItem(searchWholesaleVal, $('.c-table-tab-item').length);
			}
		});
		// search it
		$('#wholesale-status').on('change', function() {
			$(this).attr('data-val', $(this).val());
			updateSearchData();
		});
		$('#wholesale-name').on('keyup', function() {
			var distanceTime = 1000,
				newTime =  (new Date()).getTime();
			if (newTime - oldTime < 1000) clearTimeout(timer);
			oldTime = newTime;
			timer = setTimeout(function() {
				updateSearchData();
			}, distanceTime);
		});
		// View  whole-sale
		$(document.body).on('click', '.btn-view', function (e) {
			var wholesaleId = parseInt($(this).data('id') || $(this).find('.btn-view').data('id'));
			getOneWholesaleData({
				wholesaleId: wholesaleId
			}, function(resData) {
				$('.c-view c-option-title').text('View Whole-sale');
				initFormData(resData);;
				showViewBlock();
			});
		});
		// save whole-sale
		$('.c-view .btn-save').on('click', function () {
			var reqData = getFormData();
			saveWholeSaleData(reqData, function() {
				showInitBlock();
				updateSearchData();
				$('#wholesaleId').val('');
			});
		});
		// delete whole-sale
		$(document.body).on('click', '.btn-delete', function (e) {
			var wholeSaleId = parseInt($(this).data('id'));
			$('#deleteModal').find('.modal-title').html('Delete whole-sale!');
			$('#deleteModal').modal('show');
			$('#deleteModal .btn-ok').one('click', function () {
				deleteWholeSaleData({
					wholesaleId: wholeSaleId,
				}, function() {
					updateSearchData();
				});
			});
		});
		// handle formData
		// reset data
		function resetFormData() {
			$('#wholesaleId').val('');
			$('#wholesaleCustomerName').val('');
			$('#wholesaleCustomerEmail').val('');
			$('#wholesaleCustomerCountry').val('');
			$('#wholesaleCustomerTelephone').val('');
			$('#wholesaleCustomerWatsapp').val('');
			$('#wholesaleCustomerTypeName').val('');
			$('#wholesaleCustomerMessage').val('');
			$('#wholesaleCustomerStatus').prop('checked', false);
			$('#wholesaleOperateDeatil').val('');			
		}
		// getFormdData
		function getFormData() {
			var data = {};
			data.wholesaleId = parseInt($('#wholesaleId').val());
			data.wholesaleCustomerName = $('#wholesaleCustomerName').val();
			data.wholesaleCustomerEmail = $('#wholesaleCustomerEmail').val();
			data.wholesaleCustomerCountry = $('#wholesaleCustomerCountry').val();
			data.wholesaleCustomerTelephone = $('#wholesaleCustomerTelephone').val();
			data.wholesaleCustomerWatsapp = $('#wholesaleCustomerWatsapp').val();
			data.wholesaleCustomerTypeName = $('#wholesaleCustomerTypeName').val();
			data.wholesaleCustomerMessage = $('#wholesaleCustomerMessage').val();
			data.wholesaleCustomerStatus = $('#wholesaleCustomerStatus').prop('checked') ? 1 : 0;
			data.wholesaleOperateDeatil = $('#wholesaleOperateDeatil').val();			
			return data;
		}
		// initFormData
		function initFormData(data) {
			$('#wholesaleId').val(data.wholesaleId);
			$('#wholesaleCustomerName').val(data.wholesaleCustomerName);
			$('#wholesaleCustomerEmail').val(data.wholesaleCustomerEmail);
			$('#wholesaleCustomerCountry').val(data.wholesaleCustomerCountry);
			$('#wholesaleCustomerTelephone').val(data.wholesaleCustomerTelephone);
			$('#wholesaleCustomerWatsapp').val(data.wholesaleCustomerWatsapp);
			$('#wholesaleCustomerTypeName').val(data.wholesaleCustomerTypeName);
			$('#wholesaleCustomerMessage').val(data.wholesaleCustomerMessage);
			$('#wholesaleCustomerStatus').prop('checked', (''+data.wholesaleCustomerStatus == '0' ? false : true));
			$('#wholesaleOperateDeatil').val(data.wholesaleOperateDeatil);
		}
		// search status change
		function updateSearchData() {
			var searchWholesaleVal = {
				status: $('#wholesale-status').val(),
				statustext: $('#wholesale-status').find('option:selected').text(),
				customername: $('#wholesale-name').val()
			};
			// inital pagination num
			setPageNum(1);
			// check search whole-sale
			if (parseInt(searchWholesaleVal.status) < 0) searchWholesaleVal.statustext = "";

			$('.c-table-tab-item.active').removeClass('active');
			$('.c-table-tab-tempory').html(createTableTabItem(searchWholesaleVal).addClass('active'));
			getTabSearchData($('.c-table-tab-tempory .c-table-tab-item'));
		}
		// tab view/init
		function showViewBlock() {
			$('.c-init').addClass('hide');
			$('.c-view').removeClass('hide');
		}
		function showInitBlock() {
			$('.c-init').removeClass('hide');
			$('.c-view').addClass('hide');
		}
		// get Data for table
		function getTabSearchData($this) {
			var dataVal = $this.data('val');
			if (dataVal && (dataVal.status > - 1 || dataVal.customername)) {
				$('#wholesale-name').val(dataVal.customername || '');
				$('#wholesale-status').val(dataVal.status || '-1');
				console.log(dataVal)
				getSearchWholesalesData();
			} else {
				$('#wholesale-name').val('');
				$('#wholesale-status').val('-1');
				initActiveItemNum();
				getWholesalesData();
			}
		}
		//  callback get all
		function getWholesalesData(val) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/CustomerWholesale/getCustomerWholesaleByPage",
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
					toastr.error('Failed to get Whole-sales, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback get search data
		function getSearchWholesalesData(data) {
			$('.c-mask').show();

			var formData = new FormData();
			formData.append('wholesaleCustomerName', $('#wholesale-name').val());
			formData.append('wholesaleCustomerStatus', $('#wholesale-status').val());
			formData.append('pn', getPageNum());

			$.ajax({
				url: "${APP_PATH}/CustomerWholesale/backSearchByWholesale",
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
					toastr.error('Failed to get Whole-sales, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback save
		function saveWholeSaleData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
		  		url: '${APP_PATH}/CustomerWholesale/save',
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
		// callback get one whole-sale data
		function getOneWholesaleData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/CustomerWholesale/getOneCustomerWholesaleDetail",
				type: "post",
				data: JSON.stringify(reqData),
				dataType: "json",
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend.customerWholesaleOne);
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
		// callback delete
		function deleteWholeSaleData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/CustomerWholesale/delete",
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
				var msg = data[i].wholesaleCustomerMessage;
				htmlStr += '<tr><td>' + data[i].wholesaleId + '</td>' +
					'<td>' + data[i].wholesaleCustomerName + '</td>' +
					'<td>' + data[i].wholesaleCustomerEmail + '</td>' +
					'<td>' + data[i].wholesaleCustomerCountry + '</td>' +
					'<td>' + data[i].wholesaleCustomerTelephone + '</td>' +
					'<td>' + (data[i].wholesaleCustomerWatsapp && (data[i].wholesaleCustomerWatsapp).trim()  ? (data[i].wholesaleCustomerWatsapp).trim() : '') + '</td>' +
					'<td>' + (data[i].wholesaleCustomerTypeName || '--') + '</td>' +
					'<td>' + (msg.length > 10 ? msg.substring(0, 10) + '...' : msg) + '</td>' +
					'<td>' + data[i].wholesaleCreatetime + '</td>' +
					'<td><a class="badge '+ ('' + data[i].wholesaleCustomerStatus == '0' ? 'badge-danger': 'badge-success') +'" href="javascript:;">' + ('' + data[i].wholesaleCustomerStatus == '0' ? 'Uncontacted' : 'Contacted') + '</a></td>' +
					'<td><p style="max-width: 4.5rem;">'+ (data[i].wholesaleOperateDeatil ?  data[i].wholesaleOperateDeatil : '') +'</p></td>' +
					'<td>' +
						'<button class="btn btn-primary btn-view" data-id="' + data[i].wholesaleId + '">' +
							'<svg class="c-icon">' +
								'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-eye"></use>' +
							'</svg>' +
						'</button>' +
						'<button class="btn btn-danger btn-delete" data-id="' + data[i].wholesaleId + '">' +
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