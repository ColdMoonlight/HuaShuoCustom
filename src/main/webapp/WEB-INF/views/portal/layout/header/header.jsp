<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<header>
	<div class="pc-header">
		<div class="ml-ad"></div>
		<div class="ml-search">
			<div class="container">
				<a class="logo" href="javascript:goToIndex();">
					<img src="${APP_PATH }/static/custom/dblogo.png" alt="${ map.website_name }" title="${ map.website_name }">
				</a>
				<div class="search-box">
					<div class="search-inputgroup">
						<input type="text" class="search-input" placeholder="Search Products..." />
						<button class="btn btn-search" type="submit"><i class="icon search"></i></button>
					</div>
				</div>
				<div class="right-box">
					<a class="track-order" href="javascript:goToSearchTrack();" title="search your tracking info...">Track Order</a>
					<div class="login" title="login/register">
						<i class="icon person"></i>
					</div>				
				</div>
			</div>
		</div>
		<div class="pc-nav">
			<div class="container">
				<ul class="ml-nav"></ul>
			</div>
		</div>
		<a href="${APP_PATH}/MlbackCart/toCartList" class="icon cart"><span class="num">0</span></a>
	</div>
	<div class="wap-header">
		<div class="ml-ad"></div>
		<div class="wap-search-track">
			<a class="wap-track-order" id="search-product">
				<span class="icon search"></span>
				<span class="wap-track-order-text">Search Product...</span>
			</a>
		</div>
		<div class="wap-navbar">
			<span id="menu" class="icon menu"></span>
			<span class="icon person" title="login/register"></span>
			<a href="TEL:(501)7226336" class="icon phone"></a>
			<a class="logo" href="javascript:goToIndex();">
				<img src="${APP_PATH}/static/custom/dblogo.png" alt="${ map.website_name } title="${ map.website_name }">
			</a>
			<span class="icon iphone-unshare"></span>
			<span class="icon share iphone-share hide" id="iphone-share"></span>
			<a href="${APP_PATH}/MlbackCart/toCartList" class="icon cart"><span class="num">0</span></a>
			<a href="javascript:goToSearchTrack();" class="icon shipping"></a>
			<div class="search-box">
				<div class="title">What are you Looking for?</div>
				<div class="search-inputgroup">
					<input type="text" class="search-input" placeholder="Search Products..." />
					<button class="btn btn-search" type="submit">Search</button>
				</div>
			</div>
		</div>
	</div>
</header>
<!-- wap menu-list -->
<div class="wap-nav-box">
	<div class="wap-nav-header">
		<i class="icon close"></i>
	</div>
	<div class="wap-nav-body">
		<ul class="wap-nav ml-nav"></ul>
	</div>
	<div class="wap-nav-footer"></div>
</div>
<!-- search result -->
<div class="search-result-box">
	<span class="icon close"></span>
	<ul class="search-result"></ul>
</div>
<!-- split main start -->
<main>
