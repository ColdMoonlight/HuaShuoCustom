<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>

<div class="c-sidebar" id="sidebar-menu">
	<ul class="c-sidebar-nav">
		<!-- dashboard -->
		<li class="c-sidebar-nav-item">
			<a class="c-sidebar-nav-link" href="${APP_PATH}/BackHome/BackHomePage">
				<svg class="c-sidebar-nav-icon">
					<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-speedometer"></use>
				</svg>
				<span class="c-text">Dashboard</span>
			</a>
		</li>
		<!-- Orders -->
		<li class="c-sidebar-nav-item c-sidebar-nav-dropdown">
			<a class="c-sidebar-nav-link c-sidebar-nav-dropdown-toggle">
				<svg class="c-sidebar-nav-icon">
					<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-cursor"></use>
				</svg>
				<span class="c-text">Orders</span>
			</a>
			<ul class="c-child-menu">
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/MlfrontPayInfo/toMlbackPayInfoList">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">orders</span>
					</a>
				</li>
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/CustomerWholesale/toCustomerWholesalePage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">Wholesale Page</span>
					</a>
				</li>
			</ul>
		</li>
		<!-- product -->
		<li class="c-sidebar-nav-item c-sidebar-nav-dropdown">
			<a class="c-sidebar-nav-link c-sidebar-nav-dropdown-toggle">
				<svg class="c-sidebar-nav-icon">
					<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-cursor"></use>
				</svg>
				<span class="c-text">Products</span>
			</a>
			<ul class="c-child-menu">
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/MlbackSuperCate/toMlbackSuperCatePage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">SuperCate</span>
					</a>
				</li>
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/MlbackCategory/toMlbackCategoryPage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">Collections</span>
					</a>
				</li>
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/MlbackProduct/toMlbackProductPage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">All Products</span>
					</a>
				</li>
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/productTogether/toProductTogetherPage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">Product Together</span>
					</a>
				</li>
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/MlfrontReview/toMlfrontReviewPage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">Reviews</span>
					</a>
				</li>
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/MlbackProfbReview/toMlbackProfbReviewPage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">FB Reviews</span>
					</a>
				</li>
				<%-- <li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/MlfrontProductQa/toMlfrontProductQaPage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">Product QA</span>
					</a>
				</li> --%>
			</ul>
		</li>
		<!-- coupon -->
		<li class="c-sidebar-nav-item c-sidebar-nav-dropdown">
			<a class="c-sidebar-nav-link c-sidebar-nav-dropdown-toggle">
				<svg class="c-sidebar-nav-icon">
					<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-cursor"></use>
				</svg>
				<span class="c-text">Coupon</span>
			</a>
			<ul class="c-child-menu">
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/MlbackCoupon/toMlbackCouponPage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">Coupon list</span>
					</a>
				</li>
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/MlbackCoupon/toCouponAnalysisPage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">Coupon Analysis</span>
					</a>
				</li>
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/MlbackCouponDescTitle/toMlbackCouponDescTitlePage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">Coupon Area Title</span>
					</a>
				</li>
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/MlbackCouponDescDetail/toMlbackCouponDescDetailPage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">Coupon Area Details</span>
					</a>
				</li>
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/MlbackAreafreight/toMlbackAreafreightPage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">shipping_price</span>
					</a>
				</li>
				
				
				
			</ul>
		</li>
		<!-- marketing -->
		<li class="c-sidebar-nav-item c-sidebar-nav-dropdown">
			<a class="c-sidebar-nav-link c-sidebar-nav-dropdown-toggle">
				<svg class="c-sidebar-nav-icon">
					<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-cursor"></use>
				</svg>
				<span class="c-text">Marketing</span>
			</a>
			<ul class="c-child-menu">
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/MlbackCatalog/toMlbackCatalogPage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">CateLog</span>
					</a>
				</li>
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/MlbackSlides/toMlbackSlidePage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">Carousel</span>
					</a>
				</li>
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/MlbackActShowPro/toMlbackActShowProPage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">Activity Product</span>
					</a>
				</li>
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/MlbackShowArea/toMlbackShowAreaPage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">Display Area</span>
					</a>
				</li>
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/MlbackVideoShowArea/toMlbackVideoShowAreaPage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">Video Area</span>
					</a>
				</li>
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/MlbackVideo/toMlbackVideoPage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">Video List</span>
					</a>
				</li>
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/MlbackBlog/toMlbackBlogPage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">Blog</span>
					</a>
				</li>
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/MlbackCountdown/toMlbackCountdownPage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">CountDown Time</span>
					</a>
				</li>
			</ul>
		</li>
		<!-- index-area -->
		<li class="c-sidebar-nav-item">
			<a class="c-sidebar-nav-link" href="${APP_PATH}/MlbackPageArea/MlbackPageAreaPage">
				<svg class="c-sidebar-nav-icon">
					<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-calculator"></use>
				</svg>
				<span class="c-text">Page Area</span>
			</a>
		</li>
		<!-- foot nav -->
		<li class="c-sidebar-nav-item">
			<a class="c-sidebar-nav-link" href="${APP_PATH}/MlbackFootNav/toMlbackFootNavPage">
				<svg class="c-sidebar-nav-icon">
					<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-calculator"></use>
				</svg>
				<span class="c-text">Footer Nav</span>
			</a>
		</li>
		<!-- email -->
		<li class="c-sidebar-nav-item c-sidebar-nav-dropdown">
			<a class="c-sidebar-nav-link c-sidebar-nav-dropdown-toggle">
				<svg class="c-sidebar-nav-icon">
					<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-cursor"></use>
				</svg>
				<span class="c-text">Email</span>
			</a>
			<ul class="c-child-menu">
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/MlbackHtmlEmail/toMlbackHtmlEmailPage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">Email Template</span>
					</a>
				</li>
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/MlbackEmailRichText/toMlbackEmailRichTextPage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">Notification Email</span>
					</a>
				</li>
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/ExcleDownload/DownloadPage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">Email Download</span>
					</a>
				</li>
			</ul>
		</li>
	</ul>
</div>