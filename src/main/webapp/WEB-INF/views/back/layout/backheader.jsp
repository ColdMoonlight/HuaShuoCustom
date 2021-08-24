<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<jsp:include page="../../portal/common/config-front.jsp" flush="true"></jsp:include>
<div class="c-header">
	<div class="c-menu">
		<svg class="c-icon">
			<use xlink:href="${APP_PATH }/static/back/img/svg/free.svg#cil-list"></use>
		</svg>
	</div>
    <a class="c-logo" href="${APP_PATH}/BackHome/BackHomePage" title="${ map.website_name }" style="text-transform: capitalize;">${ map.website_name }</a>
    <div class="c-header-nav">
		<div class="c-avatar" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
			<img class="c-avatar-img" alt="user@email.com">
		</div>
		<div class="dropdown-menu">
			<a class="dropdown-item">
				<svg class="c-icon">
					<use xlink:href="${APP_PATH }/static/back/img/svg/free.svg#cil-lock-locked"></use>
				</svg>
				<span>Lock Account</span>
			</a>
			<a class="dropdown-item" href="${APP_PATH }/MlbackAdmin/exitIndex">
				<svg class="c-icon">
					<use xlink:href="${APP_PATH }/static/back/img/svg/free.svg#cil-account-logout"></use>
				</svg>
				<span>Logout</span>
			</a>
		</div>
    </div>
</div>