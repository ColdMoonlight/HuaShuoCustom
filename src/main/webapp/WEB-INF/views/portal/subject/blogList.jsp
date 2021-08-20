<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<!DOCTYPE html>
<html>
<head>
	<title>Blog List | megalook.com</title>
	<meta name="keywords" content="Lace Front Wigs, Human Hair Extensions,Brazilian Virgin Hair,Affordable Human Hair" />
	<meta name="description" content="Megalook Hair Official Website Sells Best Virgin Hair, Full Lace Wig, Transparent Lace Wig, 360 Lace Wig, Frontal Lace Wig, Closure Lace Wig, Bundles with Closure, Bundles with Frontal, Free Wig Making Service. Hottest Products, Free Gifts, Free & Fast Shipping. No Shedding, No Tangles, Save Big! Lowest Price For U.">
    <!-- google rule -->
	<meta name="robots" content="INDEX,FOLLOW">
    <link rel="alternate" href="https://megalook.com/blog/<%=request.getParameter("blogSeoname") %>.html" hreflang="en-us" />
	<link rel="canonical" href="https://megalook.com/blog/<%=request.getParameter("blogSeoname") %>.html" />
	<!-- socail meta -->
	<meta name="twitter:site" content="@megalookhair">
	<meta name="twitter:card" content="summary">
	<meta name="twitter:title" content="Blog list | megalook.com">
	<meta name="twitter:description" content="Megalook Hair Official Website Sells Best Virgin Hair, Full Lace Wig, Transparent Lace Wig, 360 Lace Wig, Frontal Lace Wig, Closure Lace Wig, Bundles with Closure, Bundles with Frontal, Free Wig Making Service. Hottest Products, Free Gifts, Free & Fast Shipping. No Shedding, No Tangles, Save Big! Lowest Price For U.">
	<meta property="og:type" content="website">
	<meta property="og:title" content="Blog list | megalook.com">
	<meta property="og:url" content="https://megalook.com/blog/<%=request.getParameter("blogSeoname") %>.html">
	<meta property="og:site_name" content="Megalook Hair">
	<meta property="og:image" content="https://www.megalook.com/static/apple/micon1.png" />
	<jsp:include page="../common/processor.jsp" flush="true"></jsp:include>
	<jsp:include page="../common/header.jsp" flush="true"></jsp:include>
	<style>
		@media only screen and (max-width: 1023px) { main { margin:  0; } }
		
		.ml_blog-banner .loaded { background-size: 100% 100%; }
		
		.ml-blog-container { max-width: 1330px; }
		.ml_blog-body { padding-top: 1rem; }
		.ml_blog-img { display: block; }
		.ml_blog-desc {
			display: -moz-box;
		    display: -webkit-box;
		    text-overflow: ellipsis;
		    -webkit-line-clamp: 2;
		    -webkit-box-orient: vertical;
		    overflow: hidden;
    	}
    	.ml_blog-name { text-decoration: none; color: #000; }
    	.ml_blog-more { color: #000; font-weight: 500;  }

		@media only screen and (max-width: 788px) {
			.ml_blog-banner .web { padding-top:53.33%; }
			.ml_blog-body { padding-left: .5rem; padding-right: .5rem; }
			.ml_blog-item { padding: .5rem; border: 1px solid #ccc; border-radius: .25rem; }
			.ml_blog-item+.ml_blog-item { margin-top: .5rem; }
			.ml_blog-img { width: 100%; padding-top: 50%; }
			.ml_blog-name { font-size: 1.2rem; font-weight: 500; }
			.ml_blog-desc { font-size: .875rem; -webkit-line-clamp: 4; }
			.ml_blog-more { font-size: .875rem; }
		}
		
		@media only screen and (min-width: 789px) {
			.ml_blog-banner .pc { padding-top:20.83%; }
			.ml_blog-item { display: flex; padding-top: 1rem; padding-bottom: 1rem; }
			.ml-blog-item+.ml-blog-item { border-top: 1px solid #ccc; }
			.ml_blog-img { width: 50%; padding-top: 25%; flex-shrink: 0; }
			.ml_blog-content { padding-left: 1rem; }
			.ml_blog-name { font-size: 1.5rem; font-weight: 700; }
			.ml_blog-desc { margin: .75rem 0;  min-height: 6rem; font-size: 1rem; line-height:1.5; -webkit-line-clamp: 6; }
			.ml_blog-more { font-size: 1rem;}
		}
	</style>
</head>
<body>
    <jsp:include page="../layout/header/header.jsp" flush="true"></jsp:include>
	<!-- main start -->
		<!-- video banner -->
		<div class="ml_blog-banner">
			<div class="pc lazyload" data-src="${APP_PATH }/static/pc/img/blogpc.jpg"></div>
			<div class="web lazyload" data-src="${APP_PATH }/static/pc/img/blogwap.jpg"></div>	
		</div>
		<div class="ml-blog-container container">
			<div class="ml_blog-body"><div id="init-loading"></div></div>
		</div>
	<!-- main end -->
	<!-- footer nav -->
	<jsp:include page="../layout/footer/footer.jsp" flush="true"></jsp:include>

	<!-- common script -->
	<script src="${APP_PATH}/static/common/jquery.min.js"></script>
	<script>function getCarouselData(area,async,callback){$.ajax({url:"${APP_PATH}/MlbackSlides/getMlbackSlidListByArea",data:JSON.stringify({slideArea:area}),dataType:"json",contentType:"application/json",type:"post",async:async,success:function(data){100==data.code&&callback&&callback(data.extend.mlbackSlideList)}})}function debounce(callback,delay){var timeEnd;delay=delay||100,Date.now()-timeStart>delay&&clearTimeout(timer),timer=setTimeout((function(){callback&&callback()}),delay)}</script>
	<!-- lazy -->
	<script>!function(t,e){"object"==typeof exports?module.exports=e(t):"function"==typeof define&&define.amd?define([],e):t.LazyLoad=e(t)}("undefined"!=typeof global?global:this.window||this.global,function(t){"use strict";function e(t,e){this.settings=o(r,e||{}),this.images=t||document.querySelectorAll(this.settings.selector),this.observer=null,this.init()}"function"==typeof define&&define.amd&&(t=window);const r={src:"data-src",srcset:"data-srcset",selector:".lazyload",root:null,rootMargin:"0px",threshold:0},o=function(){let t={},e=!1,r=0,s=arguments.length;for("[object Boolean]"===Object.prototype.toString.call(arguments[0])&&(e=arguments[0],r++);r<s;r++)!function(r){for(let s in r)Object.prototype.hasOwnProperty.call(r,s)&&(e&&"[object Object]"===Object.prototype.toString.call(r[s])?t[s]=o(!0,t[s],r[s]):t[s]=r[s])}(arguments[r]);return t};if(e.prototype={init:function(){if(!t.IntersectionObserver)return void this.loadImages();let e=this,r={root:this.settings.root,rootMargin:this.settings.rootMargin,threshold:[this.settings.threshold]};this.observer=new IntersectionObserver(function(t){Array.prototype.forEach.call(t,function(t){if(t.isIntersecting){e.observer.unobserve(t.target);let r=t.target.getAttribute(e.settings.src),o=t.target.getAttribute(e.settings.srcset),s=new Image;s.src=r,s.onload=function(e){var s=e.target.width,i=e.target.height;"img"===t.target.tagName.toLowerCase()?(r&&(t.target.src=r),o&&(t.target.srcset=o)):t.target.style.backgroundImage="url("+r+")",t.target.width=s,t.target.height=i,t.target.classList.add("loaded")},s.onerror=function(){}}})},r),Array.prototype.forEach.call(this.images,function(t){e.observer.observe(t)})},loadAndDestroy:function(){this.settings&&(this.loadImages(),this.destroy())},loadImages:function(){if(!this.settings)return;let t=this;Array.prototype.forEach.call(this.images,function(e){let r=e.getAttribute(t.settings.src),o=e.getAttribute(t.settings.srcset);"img"===e.tagName.toLowerCase()?(r&&(e.src=r),o&&(e.srcset=o)):e.style.backgroundImage="url('"+r+"')"})},destroy:function(){this.settings&&(this.observer.disconnect(),this.settings=null)}},t.lazyload=function(t,r){return new e(t,r)},t.jQuery){const r=t.jQuery;r.fn.lazyload=function(t){return(t=t||{}).attribute=t.attribute||"data-src",new e(r.makeArray(this),t),this}}return e});</script>
	<!-- common footer script -->
	<jsp:include page="../common/footer.jsp" flush="true"></jsp:include>
	<!-- header script  -->
	<jsp:include page="../layout/header/header-script.min.jsp" flush="true"></jsp:include>
	<script>
		function getBlogListData(callback) {
			$.ajax({
				url: '${APP_PATH}/MlbackBlog/getMlbackBlogSimpleList',
				type: "post",
				data: JSON.stringify({ "blogStatus": "1" }),
				dataType: "json",
				contentType: 'application/json',
				success: function(data) {
					if (data.code == 100) {
						callback && callback(data.extend.mlbackBlogResList);
					} else {
						sysModalTip();
					}
				},
				error: function() {
					sysModalTip();
				}
			});
		}
		function renderBlogList($el, data) {
			var htmlStr = '';
			data.forEach(function(item, idx) {
				var itemLink = '${APP_PATH}/blog/' + item.blogSeoname + '.html';
				htmlStr += '<div class="ml_blog-item">' +
						'<a class="ml_blog-img lazyload" href="'+ itemLink +'" data-src="'+ item.blogBannerimg +'"></a>' +
						'<div class="ml_blog-content">' +
							'<a class="ml_blog-name" href="'+ itemLink +'">'+ item.blogName +'</a>' +
							'<div class="ml_blog-desc">'+ item.blogMetaDesc +'</div>' +
							'<a class="ml_blog-more" href="'+ itemLink +'">Read More</a>' +
						'</div>' +
					'</div>';
			});
			$el.html(htmlStr);
		}
		new LazyLoad($('main').find('.lazyload'), {
			root: null,
			rootMargin: "10px",
			threshold: 0
		});
		getBlogListData(function(data) {
			var $el = $('.ml_blog-body');
			data.length && renderBlogList($el, data);
			new LazyLoad($el.find('.lazyload'), {
				root: null,
				rootMargin: "10px",
				threshold: 0
			});
		});
	</script>
	<!-- footer script -->
	<jsp:include page="../layout/footer/footer-script.min.jsp" flush="true"></jsp:include>
	<script> addTidio(); </script>
</body>
</html>