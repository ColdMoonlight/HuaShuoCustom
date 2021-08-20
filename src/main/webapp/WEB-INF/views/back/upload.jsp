<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form class="form-horizontal" id="upload" method="post" action="${APP_PATH}/VideoUpload/upload" enctype="multipart/form-data">
            <div class="form-group" align="center">
                <div class="col-md-4 col-sm-4  col-xs-4 col-lg-4">
                   文件上传： <input type="file" class="form-control" name="file" id="file">
                   上传地址：<select name="shipin">
                    <option value="/video/">video视频文件</option>
                    <option value="/file/">file表格文件</option>
                    <option value="/image/">image照片</option>
                   </select>
                <br>
                   <input type="submit" value="上传">
                </div>
            </div>
 
          </form>
</body>


</html>