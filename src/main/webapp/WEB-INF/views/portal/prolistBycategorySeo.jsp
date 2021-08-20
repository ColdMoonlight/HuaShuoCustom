<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!--[if IE 8]><html class="no-js lt-ie9" lang="en" xmlns:og="http://ogp.me/ns#" xmlns:fb="http://www.facebook.com/2008/fbml"> <![endif]-->
<!--[if IE 9 ]><html class="ie9 no-js" xmlns:og="http://ogp.me/ns#" xmlns:fb="http://www.facebook.com/2008/fbml"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html class="no-js" xmlns:og="http://ogp.me/ns#" xmlns:fb="http://www.facebook.com/2008/fbml"> <!--<![endif]-->
<head>
<c:choose>
<c:when test="${sessionScope.categoryMetaTitle==null}"><title>Product List</title></c:when>
<c:otherwise><title>${sessionScope.categoryMetaTitle}</title></c:otherwise>
</c:choose>
<meta name="keyword" content="${sessionScope.categoryMetaKeyWords}">
<meta name="description" content="${sessionScope.categoryMetaDesc}">
<!-- google rule -->
<meta name="robots" content="INDEX,FOLLOW">
<link rel="alternate" href="https://www.megalook.com/search/<%=request.getParameter("categorySeo") %>.html" hreflang="en-us" />
<link rel="canonical" href="https://www.megalook.com/search/<%=request.getParameter("categorySeo") %>.html" />
<!-- socail meta -->
<meta name="twitter:site" content="@megalookhair">
<meta name="twitter:card" content="summary">
<meta name="twitter:title" content="${ sessionScope.categoryMetaTitle }">
<meta name="twitter:description" content="${sessionScope.categoryMetaDesc}">
<meta property="og:type" content="website">
<meta property="og:title" content="${ sessionScope.categoryMetaTitle }">
<meta property="og:url" content="https://www.megalook.com/search/<%=request.getParameter("categorySeo") %>.html">
<meta property="og:site_name" content="Megalook Hair">
<meta property="og:image" content="https://www.megalook.com/static/apple/micon1.png" />
	<jsp:include page="common/processor.jsp" flush="true"></jsp:include>
	<script> var categorySeo = '${sessionScope.categorySeo}'; </script>
	<jsp:include page="common/header.jsp" flush="true"></jsp:include>
	<jsp:include page="common/swiper.jsp" flush="true"></jsp:include>
	<style>@media only screen and (max-width: 1023px) { main { margin: 0; } main .container { margin: 1rem; } } @media only screen and (min-width: 1024px) { main .container { margin-top: 1rem; } }</style>
</head>
<body>
    <jsp:include page="layout/header/header.jsp" flush="true"></jsp:include>
	<!-- main start -->
		<div class="search-product-banner"></div>
		<div class="container">
			<div class="category-condition">
				<span class="category-condition-name">style</span>
				<select class="category-select"></select>
			</div>
			<div class="product-list"><div id="init-loading"></div></div>
			<div class="customer-introduce">
	            <div class="customer-introduce-bg" style="background-image: url('${APP_PATH}/static/pc/img/send-us-email.jpg')">
	            	<div class="customer-introduce-info">
		                <div class="customer-introduce-text1">Tired of Looking Suitable Hair, Or Still Didn't find the wig/style you wanted?</div>
		                <div class="customer-introduce-text1">Or you have great idea/picture about the Hair /style you wanted.</div>
		                <div class="customer-introduce-text2">Just Email us!</div>
		                <div class="customer-introduce-text3">We will try our best meet your demand ! </div>
	              	</div>
	            </div>
	            <a class="customer-introduce-btn" href="mailto:team@megalookemail.com?subject=Product%20%20Inquiry%20---New%20Idea%20For%20The%20Hair&amp;body=Hi,%20Megalook%20Team,%20i%20Browser%20your%20website%20and%20didn't%20find%20the%20exact%20hair%20i%20want,%20here%20is%20some%20information%20about%20the%20hair%20i%20want%20,pls%20check%20and%20reply,%20thanks%20!">Send Us Your Idea &gt;</a>
	       	</div>
		</div>
	<!-- main end -->
	<!-- footer nav -->
	<jsp:include page="layout/footer/footer.jsp" flush="true"></jsp:include>

	<!-- common script -->
	<script src="${APP_PATH}/static/common/jquery.min.js"></script>
	<script>function getCarouselData(area,async,callback){$.ajax({url:"${APP_PATH}/MlbackSlides/getMlbackSlidListByArea",data:JSON.stringify({slideArea:area}),dataType:"json",contentType:"application/json",type:"post",async:async,success:function(data){100==data.code&&callback&&callback(data.extend.mlbackSlideList)}})}function debounce(callback,delay){var timeEnd;delay=delay||100,Date.now()-timeStart>delay&&clearTimeout(timer),timer=setTimeout((function(){callback&&callback()}),delay)}</script>
	<!-- lazy -->
	<script>!function(t,e){"object"==typeof exports?module.exports=e(t):"function"==typeof define&&define.amd?define([],e):t.LazyLoad=e(t)}("undefined"!=typeof global?global:this.window||this.global,function(t){"use strict";function e(t,e){this.settings=o(r,e||{}),this.images=t||document.querySelectorAll(this.settings.selector),this.observer=null,this.init()}"function"==typeof define&&define.amd&&(t=window);const r={src:"data-src",srcset:"data-srcset",selector:".lazyload",root:null,rootMargin:"0px",threshold:0},o=function(){let t={},e=!1,r=0,s=arguments.length;for("[object Boolean]"===Object.prototype.toString.call(arguments[0])&&(e=arguments[0],r++);r<s;r++)!function(r){for(let s in r)Object.prototype.hasOwnProperty.call(r,s)&&(e&&"[object Object]"===Object.prototype.toString.call(r[s])?t[s]=o(!0,t[s],r[s]):t[s]=r[s])}(arguments[r]);return t};if(e.prototype={init:function(){if(!t.IntersectionObserver)return void this.loadImages();let e=this,r={root:this.settings.root,rootMargin:this.settings.rootMargin,threshold:[this.settings.threshold]};this.observer=new IntersectionObserver(function(t){Array.prototype.forEach.call(t,function(t){if(t.isIntersecting){e.observer.unobserve(t.target);let r=t.target.getAttribute(e.settings.src),o=t.target.getAttribute(e.settings.srcset),s=new Image;s.src=r,s.onload=function(e){var s=e.target.width,i=e.target.height;"img"===t.target.tagName.toLowerCase()?(r&&(t.target.src=r),o&&(t.target.srcset=o)):t.target.style.backgroundImage="url("+r+")",t.target.width=s,t.target.height=i,t.target.classList.add("loaded")},s.onerror=function(){}}})},r),Array.prototype.forEach.call(this.images,function(t){e.observer.observe(t)})},loadAndDestroy:function(){this.settings&&(this.loadImages(),this.destroy())},loadImages:function(){if(!this.settings)return;let t=this;Array.prototype.forEach.call(this.images,function(e){let r=e.getAttribute(t.settings.src),o=e.getAttribute(t.settings.srcset);"img"===e.tagName.toLowerCase()?(r&&(e.src=r),o&&(e.srcset=o)):e.style.backgroundImage="url('"+r+"')"})},destroy:function(){this.settings&&(this.observer.disconnect(),this.settings=null)}},t.lazyload=function(t,r){return new e(t,r)},t.jQuery){const r=t.jQuery;r.fn.lazyload=function(t){return(t=t||{}).attribute=t.attribute||"data-src",new e(r.makeArray(this),t),this}}return e});</script>
	<!-- swiper script -->
	<script src="${APP_PATH}/static/common/swiper/swiper.min.js"></script>
	<!-- common footer script -->
	<jsp:include page="common/footer.jsp" flush="true"></jsp:include>
	<!-- header script  -->
	<jsp:include page="layout/header/header-script.min.jsp" flush="true"></jsp:include>
	<script>
		function getProductListByCategorySeo(categorySeo, hasCategory) {
			$.ajax({
				url: "${APP_PATH}/MlbackCategory/searchBycategorySeo",
				type: "post",
				data: JSON.stringify({"categorySeo": categorySeo}),
				dataType: 'json',
				contentType: 'application/json',
				async: false,
				success: function (data) {
					if (data.code == 100) {
						renderCategorySeo(data.extend, hasCategory);
					} else {
						refreshPageModal();
					}
				},
				error: function () {
					refreshPageModal();
				}
			});
		}
		function renderCategorySeo(data, hasCategory) {
			renderSearchBanner({
				imgStatus: data.cateImgStatus,
				imgUrl: data.cateImg
			});
			if (!hasCategory) renderCategorySelect(data.mlbackCategorydownList || []);
			renderProductList(descPrdouct(data.mlbackProductResList || []));
		}
		function renderProductList(data) {
			var htmlStr = '';
			if(data.length) {
				data.forEach(function(item) {
					var productLink = '${APP_PATH}/'+ item.productSeo +'.html';
					htmlStr += '<div class="product-item-box"><div class="product-item" data-productid="'+ item.productId +'">' +
					    '<span class="product-discount-label'+ (item.productDiscoutimgShow ? ' show' : '') +'" style="background-image: url('+ (item.productDiscoutimgurl || '') +');"></span>' +
						'<div class="product-img">' +
							'<a href="'+ productLink +'" title="'+ item.productName +'" class="lazyload" data-src="'+ item.productMainimgurl +'"></a>' +
						'</div>' +
						'<div class="product-desc">' +
							'<div class="product-name"><a href="'+ productLink +'" title="'+ item.productName +'">'+ item.productName +'</a></div>' +
							getProductReivewsData(item.productReviewnum) +
							'<div class="product-price">' + renderProductPriceHtml(item) + '</div>' +
						'</div>' +
					'</div></div>';					
				});
			} else {
				htmlStr += '<p class="data-info">Relevant product classification products have been removed from the shelves!</p>';
			}
			
			$('.product-list').html(htmlStr);
			// lazyload
			new LazyLoad($('.product-list').find('.lazyload'), {
				root: null,
				rootMargin: "10px",
				threshold: 0
			});
		}
		function renderCategorySelect(data) {
			var htmlStr = '';
			data.length && data.forEach(function(item) {
				htmlStr += '<option value="'+ item.categorySeo +'" '+ (categorySeo == item.categorySeo ? 'selected' : '') +'>'+ item.categoryName +'</option>';
			});
			$('.category-select').html(htmlStr);
		}
		function renderSearchBanner(data) {
			var $searchBanner = $('.search-product-banner');
			if (data.imgStatus && data.imgUrl) {
				$searchBanner.attr('data-src')
				var htmlStr = '<div class="wap lazyload" data-src="'+ data.imgUrl +'"></div>' +
					'<div class="pc lazyload" data-src="'+ data.imgUrl +'"></div>'
					$searchBanner.html(htmlStr);
			} else {
				$searchBanner.addClass('hide');
			}
			// lazyload
			new LazyLoad($searchBanner.find('.lazyload'), {
				root: null,
				rootMargin: "10px",
				threshold: 0
			});
		}
		getProductListByCategorySeo(categorySeo, false);
		$(document.body).on('change', '.category-select', function() {
			getProductListByCategorySeo($(this).val(), true);
		});

		// get introduct product
		getProductSlideArea(function(data) {
			var $el = $('<div id="introduce-product" />');
			$('main .container').append($el);
			data.length && (renderIntroduceProductSlide($el, data),
			new LazyLoad($el.find('.lazyload'), {
				root: null,
				rootMargin: "10px",
				threshold: 0
			}));
		});
	</script>
	<!-- footer script -->
	<jsp:include page="layout/footer/footer-script.min.jsp" flush="true"></jsp:include>
	<!-- lottery -->
	<jsp:include page="layout/lottery.jsp" flush="true"></jsp:include>
	<script> addTidio(); </script>
</body>

</html>