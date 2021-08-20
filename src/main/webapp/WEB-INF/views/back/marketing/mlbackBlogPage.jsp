<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Blog</title>
	<jsp:include page="../common/backheader.jsp" flush="true"></jsp:include>
	<link href="${APP_PATH}/static/back/lib/codemirror/codemirror.min.css" rel="stylesheet">
	<link href="${APP_PATH}/static/back/lib/codemirror/blackboard.min.css" rel="stylesheet">
	<link href="${APP_PATH}/static/back/lib/codemirror/monokai.min.css" rel="stylesheet">
	<link href="${APP_PATH}/static/back/lib/summernote/summernote.min.css" rel="stylesheet">
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
						<span class="c-option-title">Blog</span>
						<button class="btn btn-primary btn-create">Create Blog</button>
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
									<input id="searchBlogName" type="text" placeholder="Search Blog by name..." />						
									<select class="supercate-list hide" id="searchSupercate"></select>
								</div>
								<a class="btn btn-primary input-group-addon btn-save-search">Save Blog</a>
							</div>
							<div class="c-table-table table-responsive-sm">
								<table class="table">
									<thead>
										<tr>
											<th>id</th>
											<th>name</th>
											<th>seo</th>
											<th>status</th>
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
						<span class="c-option-title">Edit Blog</span>
						<div class="group">
							<button class="btn btn-secondary btn-cancel">Cancel</button>
							<button class="btn btn-primary btn-save">Save Blog</button>
						</div>
					</div>
					<div class="c-form row">
						<input id="blogId" hidden />
						<!-- left panel  -->
						<div class="left-panel col-lg-7 col-md-12">
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">General</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label class="col-form-label" for="blogName">Name</label>
										<div class="controls">
											<input class="form-control" id="blogName" type="text" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="blogSeoname">SEO</label>
										<div class="controls">
											<input class="form-control" id="blogSeoname" type="text" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-md-3 col-form-label" for="blogStatus">Status</label>
										<div class="controls col-md-3">
											<label class="c-switch c-switch-primary">
												<input class="c-switch-input" id="blogStatus" type="checkbox">
												<span class="c-switch-slider"></span>
											</label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="blogContentrichtext">Rich Text</label>
										<div class="controls">
											<textarea class="form-control" id="blogContentrichtext" rows="8"></textarea>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="blogAuthorCreatetime">Create Time</label>
										<div class="controls">
											<input class="form-control datetimepicker" id="blogAuthorCreatetime" type="text" placeholder="@exmaple 2021-01-01 00:00:59" />
										</div>
									</div>
								</div>
							</div>
							<div class="card">
								<div class="card-title">
									<div class="card-title-name">Image</div>
								</div>
								<div class="card-body">							
									<div class="col-md-6">
										<div class="c-upload-img">
											<svg class="c-icon">
												<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image-plus"></use>
											</svg>
											<div class="c-backshow"></div>						
											<input id="blogBannerimg" type="file" accept="image/png, image/jpeg, image/gif" />										
											<!-- spinner -->
											<div class="spinner">
												<div class="spinner-border" role="status" aria-hidden="true"></div>
											</div>
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
										<label class="col-form-label" for="blogSuperCateid">Super Category</label>
										<div class="controls">
											<select class="form-control supercate-list" id="blogSuperCateid" /></select>
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
										<label class="col-form-label" for="blogMetaTitle">Page title</label>
										<div class="controls">
											<input class="form-control" id="blogMetaTitle" type="text" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="blogMetaKeyword">Page keywords</label>
										<div class="controls">
											<textarea class="form-control" rows="5" id="blogMetaKeyword"></textarea>
										</div>
									</div>
									<div class="form-group">
										<label class="col-form-label" for="blogMetaDesc">Page Description</label>
										<div class="controls">
											<textarea class="form-control" rows="5" id="blogMetaDesc"></textarea>
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

	<script src="${APP_PATH}/static/back/lib/codemirror/codemirror.js"></script>
	<script src="${APP_PATH}/static/back/lib/codemirror/xml.min.js"></script>
	<script src="${APP_PATH}/static/back/lib/summernote/summernote.min.js"></script>

	<script type="text/javascript" src="${APP_PATH}/static/back/lib/datetimepicker/moment.min.js"></script>
	<script type="text/javascript" src="${APP_PATH}/static/back/lib/datetimepicker/daterangepicker.js"></script>

	<!-- custom script -->
	<script>
		var hasSuperCateList = false;
		var hasCollectionList = false;
		var isCreate = false;
		var storageName = 'blog';
		var selectedName = [];
		var selectedId = [];

		$('#blogContentrichtext').summernote({
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
	 	$('#searchSupercate').val($('#searchSupercate').data('val') || '-1');
		// init
		bindDateTimepicker();
		renderTabItems();
		// save search
		$('.btn-save-search').on('click', function () {
			var searchPageareaVal = {
				supercate: $('#searchSupercate').find('option:selected').text(),
				supercateId: $('#searchSupercate').val(),
				pagearea: $('#searchBlogName').val()
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
		$('#searchBlogName').on('keyup', function() {
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
				pagearea: $('#searchBlogName').val()
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
				$('#searchBlogName').val(dataVal.pagearea || '');
				$('#searchSupercate').attr('data-val', dataVal.supercateId || '-1');
				$('#searchSupercate').val(dataVal.supercateId || '-1');
				getSearcBlogData();
			} else {
				$('#searchSupercate').val('-1');
				$('#searchBlogName').val('');
				initActiveItemNum();
				getBlogsData();
			}
		}
		// tab delete
		$(document.body).on('click', '.delete-table-tab-item', deleteTableTabItem);
		// pagination a-click
		$(document.body).on('click', '#table-pagination li', function (e) {
			getTabSearchData($('.c-table-tab-item.active'));
		});
		// create Blog
		$('.btn-create').on('click', function () {
			$('.c-create .c-option-title').text('Create Blog');
			showCreateBlock();
			// init formData
			resetFormData();
			getBlogId();
			isCreate = true;
		});
		// edit Blog
		$(document.body).on('click', '.btn-edit', function (e) {
			var blogId = $(this).data('id');
			getOneBlogData({
				blogId: blogId
			}, function(resData) {
			 	$('.c-create .c-option-title').text('Edit Blog');
				showCreateBlock();
				initFormData(resData);
			});	
		});
		// delete Blog
		$(document.body).on('click', '.btn-delete', function (e) {
			var blogId = parseInt($(this).data('id'));
			$('#deleteModal').find('.modal-title').html('Delete Blog!');
			$('#deleteModal').modal('show');
			$('#deleteModal .btn-ok').one('click', function () {
				deleteBlogData({
					blogId: blogId,
				}, function() {
					getBlogsData();
				});
			});
		});
		$(window).on('beforeunload', function() {
			var blogId = $('#blogId').val();
			isCreate && blogId && deleteBlogData({
				blogId: blogId,
			});
			$('#editModal .select-result').addClass('hide');
		});
		// upload Blog img
		$('#blogBannerimg').on('change', function(e) {
			var $this = $(this);
			var file = $this[0].files[0];
			var formData = new FormData();
			if (!file) return false;
			$this.parent().find('.spinner').show();
			$this.val('');
			formData.append('type', 'blog');
			formData.append('image', file);
			formData.append('blogId', parseInt($('#blogId').val()));
			formData.append('blogSeoname', $('#blogSeoname').val());
			$.ajax({
				url: "${APP_PATH}/ImageUpload/uploadBlogImg",
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
		// save Blog
		$('.c-create .btn-save').on('click', function () {
			saveBlogData(getFormData(), function() {
				// redirect tab-active & then search-data
				if (isCreate) {
					isCreate = false;
					initActiveItemNum();
					$('.c-table-tab-item').removeClass('active').eq(0).addClass('active');
				}
				getTabSearchData($('.c-table-tab-item.active'));
				showInitBlock();
				$('#blogId').val('');
			});
		});
		// cancel Blog save
		$('.c-create .btn-cancel').on('click', function () {
			if (isCreate) {
				isCreate = false;
				// delete null pagearea
				deleteBlogData({
					blogId: $('#blogId').val(),
				}, function() {
					console.log("cancel create Blog");
				});
			}
			showInitBlock();
		});
		
		// status combinewith supercate
		$('#blogStatus').on('change', function(e) {
			if (parseInt($('#blogSuperCateid').val()) < 0) {
				toastr.info('Please Select super-category!');
				$('#blogSuperCateid').focus();
				$('#blogStatus').prop('checked', false);
			}
			if ($('#pageareaType').val() == '-1') {
				toastr.info('Please Select page-area type!');
				$('#pageareaType').focus();
				$('#blogStatus').prop('checked', false);
			}
		});
		// supercate cahnge
		$('#blogSuperCateid').on('change', function() {
			if ($(this).val() == '-1') {
				$('#blogStatus').prop('checked', false);
			}
		});
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
		function generateBlogDetails(ids, names) {
			var htmlStr = '';
			if (ids.length && names.length) {
				ids.forEach(function(id, index) {
					htmlStr += '* ' + id + ' —— ' + names[index] + '\n';
				});
			}
			return htmlStr;
		}
		// callback picture
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
		// reset data
		function resetFormData() {
			$('#blogId').val('');
			$('#blogName').val('');
			$('#blogSeoname').val('');
			$('#blogStatus').prop('checked', false)
			$('#blogContentrichtext').summernote('reset');
			
			$('#blogAuthorCreatetime').val('');
			
			resetPicture($('#blogBannerimg'));
			$('#blogSuperCateid').val('-1');
			
			$('#blogMetaTitle').val('');
			$('#blogMetaKeyword').val('');
			$('#blogMetaDesc').val('');
		}
		// getFormdData
		function getFormData() {
			var data = {};
			data.blogId = parseInt($('#blogId').val());
			data.blogName = $('#blogName').val();
			data.blogSeoname = $('#blogSeoname').val();
			data.blogStatus = $('#blogStatus').prop('checked') ? 1 : 0;
			data.blogContentrichtext = $('#blogContentrichtext').val();
			data.blogAuthorCreatetime = $('#blogAuthorCreatetime').val();
			data.blogSuperCateid = $('#blogSuperCateid').val();
			data.blogSuperCatename = $('#blogSuperCateid').find('option:selected').text();
			data.blogMetaTitle = $('#blogMetaTitle').val();
			data.blogMetaKeyword = $('#blogMetaKeyword').val();
			data.blogMetaDesc = $('#blogMetaDesc').val();
			return data;
		}
		// initFormData
		function initFormData(data) {
			// init
			$('#blogId').val(data.blogId);
			$('#blogName').val(data.blogName);
			$('#blogSeoname').val(data.blogSeoname);
			$('#blogStatus').prop('checked', data.blogStatus);

			$('#blogContentrichtext').summernote('code', data.blogContentrichtext);

			$('#blogAuthorCreatetime').daterangepicker({
				singleDatePicker: true,
				timePicker: true,
				timePicker24Hour: true,
				timePickerSeconds: true,
				showWeekNumbers: true,
				locale: {
					format: format,
				},
				startDate: (data.blogAuthorCreatetime || initDate()),
			});
			
			if (data.blogBannerimg) {
				addPicture($('#blogBannerimg'), {
					imageUrl: data.blogBannerimg,
					thumImageUrl: data.blogBannerimg
				});
			} else {
				resetPicture($('#blogBannerimg'));
			}
			$('#blogMetaTitle').val(data.blogMetaTitle);
			$('#blogMetaKeyword').val(data.blogMetaKeyword);
			$('#blogMetaDesc').val(data.blogMetaDesc);
			
			$('#blogSuperCateid').val(data.blogSuperCateid || '-1');
		}
		// callback get id
		function getBlogId() {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/MlbackBlog/initializaBlog",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				async: false,
				success: function (data) {
					if (data.code == 100) {
						var blogId = data.extend && data.extend.MlbackBlog && data.extend.MlbackBlog.blogId;
						if (blogId) {
							$('#blogId').val(blogId);
							toastr.success(data.extend.resMsg);
						} else {
							toastr.error('create Blog fail! Please try again.');
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
		function getBlogsData() {
			$('.c-mask').show();
			var formData = new FormData();
			formData.append('pn', getPageNum());
			$.ajax({
				url: "${APP_PATH}/MlbackBlog/getMlbackBlogByPage",
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
					toastr.error('Failed to get Blog, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback get one data
		function getOneBlogData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackBlog/getOneMlbackBlogDetail",
				type: "post",
				data: JSON.stringify(reqData),
				dataType: 'json',
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend.MlbackBlogOne);
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function () {
					toastr.error('Failed to get Blog, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback get search data
		function getSearcBlogData(data) {
			$('.c-mask').show();
			var formData = new FormData();
			formData.append('blogName', $('#searchBlogName').val());
			formData.append('blogSuperCateid', ($('#searchSupercate').attr('data-val') || '-1'));
			formData.append('pn', getPageNum());
			$.ajax({
				url: "${APP_PATH}/MlbackBlog/backSearchByBlog",
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
					toastr.error('Failed to get Blog, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback save
		function saveBlogData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackBlog/save",
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
		function deleteBlogData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackBlog/delete",
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
				htmlStr += '<tr><td>' + data[i].blogId + '</td>' +
					'<td>' + data[i].blogName + '</td>' +
					'<td>' + data[i].blogSeoname + '</td>' +
					'<td><a class="badge '+ (data[i].blogStatus ? 'badge-success': 'badge-danger') +'" href="javascript:;">' + (data[i].blogStatus ? 'enable' : 'disable') + '</a></td>' +
					'<td>' + data[i].blogSuperCatename + '</td>' +
					'<td>' +
						'<button class="btn btn-primary btn-edit" data-id="' + data[i].blogId + '">' +
							'<svg class="c-icon">' +
								'<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-pencil"></use>' +
							'</svg>' +
						'</button>' +
						'<button class="btn btn-danger btn-delete" data-id="' + data[i].blogId + '">' +
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