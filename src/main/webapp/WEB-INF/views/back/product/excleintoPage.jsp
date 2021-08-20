<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Reviews</title>
	<jsp:include page="../common/backheader.jsp" flush="true"></jsp:include>
</head>

<body>
<jsp:include page="../common/backfooter.jsp" flush="true"></jsp:include>
<!-- 	<button id="excleImport">
	导入产品
	</button> -->
	<div class="row" style="width: 80%; margin: 0 auto;">
		<div class="col-md-6">
			<a href="###">下载模板</a>
			<div class="c-upload-img" id="download" style="margin: 100px auto">
			  <svg class="c-icon">
			  	<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-movie"></use>
			  </svg>
			</div>
		</div>
		<div class="col-md-6">
			<a href="###">上传文档</a>
			<div class="c-upload-img" style="margin: 100px auto">
				<svg class="c-icon">
					<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-movie"></use>
				</svg>
				<div class="c-backshow"></div>	
					<!-- <input type="file" id="file" name="myfile" style="display: none" />
					<input type="text" id="filename" style="display:none"></span>
					<input type="button" onclick="upload()" value="选择文件上传" /> -->
				<input id="excleImport" type="file" name="myfile" />										
				<!-- spinner -->
				<div class="spinner">
					<div class="spinner-border" role="status" aria-hidden="true"></div>
				</div>
			</div>
		</div>
		
	</div>
	
	

	<script type="text/javascript" src="${APP_PATH}/static/common/jquery.min.js"></script>
	<!-- custom script -->
	<script>
	$('#download').on('click', function() {
		window.location.href = "${APP_PATH}/excleImport/exportReviewsImportDemo";
	});
	
	
	$('#excleImport').on('change', function(e) {
		var $this = $(this);
		var excleFile = $this[0].files[0];
		if (!excleFile) return false;
		$this.parent().find('.spinner').show();
		$this.val('');
		var exclFormData = new FormData();
		exclFormData.append('file', excleFile);
		// console.log(exclFormData)
		var file_typename =  excleFile.name.substring(excleFile.name.lastIndexOf('.'));
		if (file_typename === '.xlsx' || file_typename === '.xls') {
			$.ajax({
				url: "${APP_PATH}/excleImport/inportReviews",
				type: "post",
				data: exclFormData,
				processData: false,
				contentType: false,
				cache: false,
				dataType: 'json',
				success: function (data) {
					console.log(data);
				},
				error: function (err) {
					toastr.error(err);
					console.log("出错了")
				},
				complete: function () {
					$this.parent().find('.spinner').hide();
				}
			});
			
		}else{
			console.log("格式不正确")
		}
		
		
		
		
	});

	</script>
</body>

</html>