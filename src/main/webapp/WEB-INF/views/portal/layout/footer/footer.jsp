<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="javax.servlet.*,java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- split main end -->
</main>
<footer>
	<div class="footer-page-header">
		<div class="container">
			<div class="follow-us">
				<h3 class="follow-us-title wap-follow-us">FOLLOW US</h3>
				<div class="follow-us-title pc-follow-us">
					<img src="${APP_PATH }/static/custom/dblogo.png" alt="${ map.website_name }" title="${ map.website_name }" style="width:150px">
					<div style="margin-top:6px;">My New Look </div>
				</div>
				<ul class="follow-us-list">
					<c:if test="${ map.fl_instagram != null }"><li class="follow-icon instagram" title="${ map.website_name } instagram"><a href="${ map.fl_instagram }"></a></li></c:if>
					<c:if test="${ map.fl_youtube != null }"><li class="follow-icon youtube" title="${ map.website_name } youtube"><a href="${ map.fl_youtube }"></a></li></c:if>
					<c:if test="${ map.fl_facebook != null }"><li class="follow-icon facebook" title="${ map.website_name } facebook"><a href="${ map.fl_facebook }"></a></li></c:if>
					<c:if test="${ map.fl_pinterest != null }"><li class="follow-icon pinterest" title="${ map.website_name } pinterest"><a href="${ map.fl_pinterest }"></a></li></c:if>
					<c:if test="${ map.fl_snapchat != null }"><li class="follow-icon snapchat" title="${ map.website_name } snapchat"><a href="${ map.fl_snapchat }"></a></li></c:if>
				</ul>
			</div>
			<div class="footer-nav pc-footer"></div>
			<div class="footer-nav wap-footer"></div>
		</div>
	</div>
	
	<div class="footer-page-footer">
		<div class="container">
			<p class="copyright">Copyright © <% Date dNow = new Date( ); SimpleDateFormat fDate = new SimpleDateFormat("yyyy"); out.print(fDate.format(dNow)); %> <a href="${APP_PATH}/" style="text-transform: capitalize;">${ map.website_name }</a>, All Rights Reserved</p>
			<img src="${APP_PATH }/static/pc/img/paypalcard.jpg" width="266" height="33" alt="paypal card" title="paypal card">
		</div>
	</div>
</footer>
