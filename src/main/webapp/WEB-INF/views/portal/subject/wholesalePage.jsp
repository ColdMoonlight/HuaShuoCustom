<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common/config-front.jsp"></jsp:include>
    <title>Best Wholesale Hair</title>
	<meta name="keywords" content="Lace Front Wigs, Human Hair Extensions,Brazilian Virgin Hair,Affordable Human Hair" />
	<meta name="description" content="Megalook Hair Official Website Sells Best Virgin Hair, Full Lace Wig, Transparent Lace Wig, 360 Lace Wig, Frontal Lace Wig, Closure Lace Wig, Bundles with Closure, Bundles with Frontal, Free Wig Making Service. Hottest Products, Free Gifts, Free & Fast Shipping. No Shedding, No Tangles, Save Big! Lowest Price For U.">
    <!-- google rule -->
	<meta name="robots" content="INDEX,FOLLOW">
    <link rel="alternate" href="${ map.website_domain }/Customer/Wholesale.html" hreflang="en-us" />
	<link rel="canonical" href="${ map.website_domain }/Customer/Wholesale.html" />
	<!-- socail meta -->
	<meta name="twitter:site" content="@${ map.website_name }">
	<meta name="twitter:card" content="summary">
	<meta name="twitter:title" content="Megalook Hair, Virgin Remy Human Hair Wigs, Lace Front Wigs, Best Wigs | megalook.com">
	<meta name="twitter:description" content="Megalook Hair Official Website Sells Best Virgin Hair, Full Lace Wig, Transparent Lace Wig, 360 Lace Wig, Frontal Lace Wig, Closure Lace Wig, Bundles with Closure, Bundles with Frontal, Free Wig Making Service. Hottest Products, Free Gifts, Free & Fast Shipping. No Shedding, No Tangles, Save Big! Lowest Price For U.">
	<meta property="og:type" content="website">
	<meta property="og:title" content="Megalook Hair, Virgin Remy Human Hair Wigs, Lace Front Wigs, Best Wigs | megalook.com">
	<meta property="og:url" content="${ map.website_domain }/Customer/Wholesale.html">
	<meta property="og:site_name" content="${ map.website_name }">
	<meta property="og:image" content="${ map.website_domain }/static/apple/micon1.png" />
	<jsp:include page="../common/processor.jsp" flush="true"></jsp:include>
	<jsp:include page="../common/header.jsp" flush="true"></jsp:include>
	<jsp:include page="../common/swiper.jsp" flush="true"></jsp:include>
	<style>
		@media only screen and (max-width: 1023px) { main { margin: 0; } }
		@media only screen and (max-width: 1078px) and (min-width: 576px) { main { margin: 0; } }
		/* wholesale */
		.wholesale-factory-item>img, .wholesale-customer-item .wholesale-img>img, .wholesale-video-box .video-img  { background-color: #f5f5f5; }
		.wholesale-carousel-swiper .swiper-slide { width: 100%; height: auto; }
		.wholesale-carousel-swiper .swiper-slide>img { width: 100%; height: auto; }
		.wholesale-carousel-swiper .swiper-pagination-bullets { left: unset!important; width: auto!important; right: 5%; }
		.wholesale-carousel-swiper .swiper-pagination-bullet { width: 16px; height: 16px; margin: 0 8px; }

		.wholesale-title { position: relative; margin-bottom: 20px; text-align: center; line-height: 1.5em; text-transform: uppercase; color: #2e2e2e; }
		.wholesale-title2 { position: relative; margin-top: 0; margin-bottom: 30px; font-size: 30px; }
		.wholesale-title2::after, .wholesale-title::after { content: ""; position: absolute; bottom: -5px; width: 80px; height: 4px; background: #1089ff; }
		.wholesale-title::after { left: 50%; -webkit-transform: translate(-50%); transform: translate(-50%); }
		.wholesale-title2::after { left: 0; }
		.wholesale-p { text-align: center; line-height: 1.8em; padding: 10px 0;}
		.wholesale-item, .wholesale-p { max-width: 1400px; margin: 0 auto;}

		.wholesale-more { display: inline-block; padding: 0 15px; margin-top: 20px; line-height: 40px; font-size: 16px; color: #fff; text-decoration: none; /* background: #09f; */ background: #f82d98; border: solid 1px #dbdbdb; -webkit-transition: linear .5s; transition: linear .5s; }

		.wholesale-factory-item { display: block; text-decoration: none; }
		.wholesale-factory-item>img { width: 100%; height: auto; }
		.wholesale-factory-header { display: flex; align-items: center; height: 110px; padding: 30px; color: #fff; font-size: 20px; /* background: #09f; */ background: #f82d98; }
		.wholesale-factory-header .icon { flex-shrink: 0; width: 52px; height: 51px; margin-right: 10px; background: url(${APP_PATH}/static/pc/img/wholesale/icon.png) 0 0 no-repeat; }
		.wholesale-factory-header .icon.factory { background-position: -10px -10px; }
		.wholesale-factory-header .icon.store { background-position: -72px -10px; }
		.wholesale-factory-header .icon.cost { background-position: -134px -10px; }

		.wholesale-about-us .wholesale-video { width: 100%; background-color: #f5f5f5; }
		.wholesale-about-us .wholesale-right { width: 100%; color: #000; }

		.wholesale-customer-item .wholesale-img { width: 100%; overflow: hidden; background-color: #f5f5f5; cursor: pointer; }
		.wholesale-customer-item .wholesale-img>img { width: 100%; height: auto; min-height: 200px; -webkit-transition: linear .5s; transition: linear .5s; }
		.wholesale-customer-item .wholesale-img:hover>img { -webkit-transform: scale(1.05); transform: scale(1.05); }
		.wholesale-customer-item .wholesale-content { position: relative; bottom: 0; width: 100%; padding: 20px; background: #f7f7f7; }
		.wholesale-customer-item .wholesale-content .text { display: block; line-height: 1.5em; color: #707070; font-weight: 500; text-transform: uppercase; }
		.wholesale-customer-item .wholesale-content .icon { position: absolute; top: -30px; right: 30px; width: 96px; height: 96px; background: url(${APP_PATH}/static/pc/img/wholesale/icon.png) 0 0 no-repeat; }
		.wholesale-customer-item .wholesale-content .icon.store-owner { background-position: -10px -213px; }
		.wholesale-customer-item .wholesale-content .icon.whole-saler { background-position: -116px -213px; }
		.wholesale-customer-item .wholesale-content .icon.hairlist { background-position: -222px -213px; }
		.wholesale-customer-item .wholesale-content .icon.student-sell { background-position: -328px -213px; }

		.wholesale-product-list { display: flex; justify-content: space-between; flex-wrap: wrap; }
		.wholesale-product-item { position: relative; display: block; cursor: pointer; overflow: hidden; }
		.wholesale-product-img { width: 100%; height: 100%; background-color: #f5f5f5; }
		.wholesale-product-content, .wholesale-product-hover-content { position: absolute; left: 0; display: flex; align-items: center; width: 100%; padding: 10px; margin: 0; line-height: 1.6em; color: #fff; text-align: center; font-size: 14px; background: rgba(0,0,0,.5); }
		.wholesale-product-content { bottom: 0; }
		.wholesale-product-hover-content { bottom: -100%; height: 100%; opacity: 0; webkit-transition: linear .3s; transition: linear .3s; }
		.wholesale-product-item:hover .wholesale-product-content{ opacity: 0; }
		.wholesale-product-item:hover .wholesale-product-hover-content{ opacity: 1; bottom: 0; }

		.wholesale-video-swiper { position: relative; }
		.wholesale-video-box { position: relative; border: 2px solid #fff; cursor: pointer; }
		.wholesale-video-box:hover { border-color: #1089ff; }
		.wholesale-video-box .video-play-btn { position: absolute; top: 50%; left: 50%; -webkit-transform: translate(-50%, -50%); transform: translate(-50%, -50%); }
		.wholesale-video-box .video-img { width: 100%; height: 100%; }

		.wholesale-ins-list { display: flex; align-items: center; justify-content: space-between; flex-wrap: wrap; }
		.wholesale-ins-item>img { width: 100%; height: 100%; }

		.wholesale-form {margin: 0 auto; padding: 1rem 0; }
		.wholesale-form .form-group .form-label {padding-right: 10px; vertical-align: top; text-align: right;}
		.wholesale-form .form-group .form-label i {margin-right: .25rem;}
		.wholesale-form .form-radio {display: inline-block; margin-right: 1rem; margin-bottom: .5rem;}
		.wholesale-form .form-radio input {margin-right: .25rem; width: 1rem; height: 1rem;}
		.wholesale-form .form-group textarea {width: 100%; height: 100px; border-color: #a9a9a9;}
		.wholesale-form .form-group i{color:#ff6f5e;}
		.wholesale-form .form-group #Submit{background: #f82d98; border:none; color: #fff; line-height: 30px;}
		.wholesale-info { margin: 0; font-size: 12px; color: #ff0000; font-style: italic; }
		
		@media only screen and (min-width: 568px) {
			.wholesale-form{width: 80%; max-width: 800px;}
			.wholesale-form .form-group {display: flex; margin: 20px 0 0;}
			.wholesale-form .form-group .form-input{display: inline-block;}
			.wholesale-form .form-group .form-label{width: 20%; font-size: 20px;}
			.wholesale-form .form-group .form-input{width: 80%;}
			.wholesale-form .form-group input{padding: .75rem; border: 2px solid #ccc;}
		}
		@media only screen and (max-width: 567px) {
			.wholesale-form{width: 90%;}
			.wholesale-form .form-group+.form-group {margin-top: 16px;}
			.wholesale-form .form-group .form-control{margin-top: .5rem;}
			.wholesale-form .form-group .form-label{width: 35%; font-weight: 600; font-size: 16px;}
			.wholesale-form .form-group input{padding: .5rem; border: 1px solid #ccc;}
			
			.wholesale-carousel-swiper .swiper-slide { min-height: 100px; }
			.wholesale-title { padding: 0 10px; }
			.wholesale-about-us, .wholesale-factory-list, .wholesale-customer-list, .wholesale-product-list { padding: 15px; }
			.wholesale-factory-item+.wholesale-factory-item, .wholesale-customer-item+.wholesale-customer-item { margin-top: 10px; }
			.wholesale-product-item { width: 49%; margin-top: 1.5%; }
			.wholesale-video-swiper .swiper-slide { width: 49%; }
			.wholesale-video-swiper { padding: 0 40px; }
			.wholesale-video-swiper .swiper-btn { width: 30px; height: 30px; }
			.wholesale-about-us .wholesale-video { min-height: 200px; }
		}
		@media only screen and (min-width: 568px) and (max-width: 992px) {
			.wholesale-about-us, .wholesale-factory-list, .wholesale-customer-list, .wholesale-product-list { padding: 20px; }
			.wholesale-about-us .wholesale-right { margin-top: 20px; }
			.wholesale-factory-item+.wholesale-factory-item, .wholesale-customer-item+.wholesale-customer-item { margin-top: 16px; }
			.wholesale-product-item { width: 32%; margin-top: 1.5%; }
			.wholesale-video-swiper .swiper-slide { width: 33%; }
			.wholesale-video-swiper { padding: 0 40px; }
			.wholesale-video-swiper .swiper-button-prev { left: 15px;}
			.wholesale-video-swiper .swiper-button-next { right: 15px;}
			.wholesale-carousel-swiper .swiper-slide { min-height: 150px; }
			.wholesale-ins-list { padding: 0 20px; }
			.wholesale-about-us .wholesale-video { height: 400px; }
		}
		
		@media only screen and (min-width: 993px) {
			.wholesale-carousel-swiper .swiper-slide { min-height: 300px; }
			.wholesale-p { font-size: 18px; }
			.wholesale-about-us { display: flex; align-items: center;}
			.wholesale-about-us .wholesale-right { flex-shrink: 0; width: 560px; padding: 80px 60px; }
			.wholesale-about-us .wholesale-left { width: 100%; height: auto; overflow: hidden; }
			.wholesale-about-us .wholesale-video { width: 100%; height: 465px; }
			.wholesale-factory-list { display: flex; align-items: center; justify-content: space-between; }
			.wholesale-factory-item { width: 31%; text-decoration: none; }
			.wholesale-customer-list { display: flex; justify-content: space-between; flex-wrap: wrap; }
			.wholesale-customer-item { width: 49%; margin-top: 2%; }
			.wholesale-product-item { width: 24%; margin-top: 1.3%; }
			.wholesale-video-swiper, .wholesale-ins-list { margin-top: 20px; }
			.wholesale-video-swiper .swiper-slide { width: 20%; }
			.wholesale-video-swiper { padding: 0 80px; }
			.wholesale-video-swiper .swiper-button-prev { left: 36px; }
			.wholesale-video-swiper .swiper-button-next { right: 36px; }
			.wholesale-customer-item .wholesale-content { min-height: 130px; }
			.wholesale-factory-item>img { min-height: 200px; }
			.wholesale-customer-item .wholesale-content .text { max-width: 80%; font-size: 30px; }
			.wholesale-product-img { min-height: 300px; }
			.wholesale-ins-list { padding: 0 30px; }
			.wholesale-ins-item { width: 19%; }
			.video-play-btn { width: 40px; height: 40px; }
			.wholesale-video-box .video-img { min-height: 150px; }
			.wholesale-title { font-size: 33px; }
			.wholesale-p { font-size: 16px; }
		}
		@media only screen and (max-width: 992px) {
			.wholesale-customer-item .wholesale-content .text { max-width: 60%; font-size: 20px; }
			.wholesale-product-img { min-height: 100px; }
			.wholesale-factory-item>img { min-height: 100px; }
			.wholesale-ins-item { width: 49%; margin-bottom: 1.5%; }
			.wholesale-video-swiper, .wholesale-ins-list { margin-top: 16px; }
			.wholesale-video-box .video-img { min-height: 80px; }
			.wholesale-title { font-size: 20px; }
			.video-play-btn { width: 30px; height: 30px; }
		}
	</style>
</head>
<body>
    <jsp:include page="../layout/header/header.jsp" flush="true"></jsp:include>
	<!-- main start -->
		<div class="wholesale-carousel-swiper swiper-container">
			<div class="swiper-wrapper">
				<div class="swiper-slide"><img src="${APP_PATH}/static/pc/img/wholesale/swiper-1.jpg"></div>
				<div class="swiper-slide"><img src="${APP_PATH}/static/pc/img/wholesale/swiper-2.jpg"></div>
				<!--<div class="swiper-slide"><img src="${APP_PATH}/static/pc/img/wholesale/swiper-3.png"></div>
				<div class="swiper-slide"><img src="${APP_PATH}/static/pc/img/wholesale/swiper-4.jpg"></div> -->
			</div>
			<div class="swiper-pagination"></div>
		</div>	
		<div class="wholesale-box">
			<h2 class="wholesale-title">WHY WE HAVE THE LOWEST WHOLESALE PRICE?</h2>
			<div class="wholesale-body">
				<div class="wholesale-item wholesale-about-us">
					<div class="wholesale-left">
						<video class="wholesale-video" poster="${APP_PATH}/static/pc/img/wholesale/wholesale-poster.jpg" controls>
							<source src="${APP_PATH}/static/pc/img/wholesale/wholesale-video.mp4" type="video/mp4"></source>
						</video>
					</div>
					<div class="wholesale-right ">
						<h3 class="wholesale-title2">About Our Factory</h3>
						<p>MEGALOOK Hair Factory was founded in 2006, which locates in XuChang city, HeNan Province, China. We are a professional manufacturer and exporter specializing in research, production, sales, and service in the field of human hair. Our Service’s theme is Quality is Key,Customer Always comes First. We Are always on the way improving our hair quality,We Want Every Female Becomes More Confident.</p>
						<p>We own 3 factory to supply good hair products all the time. The factory covers an area of 20000 square meters and has more than 700 workers. The production capacity is around 50 tons per month.</p>
						<a href="#" class="wholesale-more">See More</a>
					</div>
				</div>
				<div class="wholesale-item">					
					<div class="wholesale-factory-list">
						<a href="#" class="wholesale-factory-item">
							<div class="wholesale-factory-header factory">
								<sapn class="icon factory"></sapn>
								<span class="text">China's largest hair raw material collection factory</span>
							</div>
							<img src="${APP_PATH}/static/pc/img/wholesale/about-factory-1.jpg">
						</a>
						<a href="#" class="wholesale-factory-item">
							<div class="wholesale-factory-header">
								<sapn class="icon store"></sapn>
								<span class="text">Factory wholesale directly no middlemen</span>
							</div>
							<img src="${APP_PATH}/static/pc/img/wholesale/about-factory-2.jpg">
						</a>
						<a href="#" class="wholesale-factory-item">
							<div class="wholesale-factory-header">
								<sapn class="icon cost"></sapn>
								<span class="text">Own factory, low cost and quality controllable</span>
							</div>
							<img src="${APP_PATH}/static/pc/img/wholesale/about-factory-3.jpg">
						</a>
					</div>
				</div>
			</div>
		</div>
		<div class="wholesale-box">
			<h2 class="wholesale-title">WHAT VALUE DOES IT BRING TO CUSTOMERS?</h2>
			<p class="wholesale-p">28,750 U.S.hair wholesale customers have double profits business</p>
			<div class="wholesale-body">
				<div class="wholesale-item wholesale-customer-list">
					<div class="wholesale-customer-item">
						<div class="wholesale-img">
							<img src="${APP_PATH}/static/pc/img/wholesale/value-1.jpg">
						</div>
						<div class="wholesale-content">
							<span class="text">STORE OWNER SELL HAIR AND OPEN MORE STORES</span>
							<sapn class="icon store-owner"></sapn>
						</div>
					</div>
					<div class="wholesale-customer-item">
						<div class="wholesale-img">
							<img src="${APP_PATH}/static/pc/img/wholesale/value-2.jpg">
						</div>
						<div class="wholesale-content">
							<span class="text">Wholesaler Open Website Get Double Profit</span>
							<sapn class="icon whole-saler"></sapn>
						</div>
					</div>
					<div class="wholesale-customer-item">
						<div class="wholesale-img">
							<img src="${APP_PATH}/static/pc/img/wholesale/value-3.jpg">
						</div>
						<div class="wholesale-content">
							<span class="text">HAIR STYLIST SELL TO LOCAL CUSTOMERS GET MORE PROFITS</span>
							<sapn class="icon hairlist"></sapn>
						</div>
					</div>
					<div class="wholesale-customer-item">
						<div class="wholesale-img">
							<img src="${APP_PATH}/static/pc/img/wholesale/value-4.jpg">
						</div>
						<div class="wholesale-content">
							<span class="text">STUDENT SELL TO FRIENDS GET MORE PROFIT</span>
							<sapn class="icon student-sell"></sapn>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="wholesale-box">
			<h2 class="wholesale-title">OUR PRODUCTS</h2>
			<p class="wholesale-p">Megalook Hair Factory has been sourcing hair raw material professionally for 16 years. We source raw materials from all over the world, such as India, Malaysia, Brazil, Peru, and also China. Our hair products are 100% cuticle aligned virgin human hair that can be bleached 613 blond and easily be permed and styled by yourself.</p>
			<div class="wholesale-body">
				<div class="wholesale-item wholesale-product-list">					
					<a class="wholesale-product-item" href="${APP_PATH}/products/B-13x4-Straight-Transparent-Lace-Wig">
						<img class="wholesale-product-img" src="${APP_PATH}/static/pc/img/wholesale/product-1.jpg">
						<p class="wholesale-product-content">Wholesale 180% Density 13x4 Transparent Lace Frontal Wig</p>
						<p class="wholesale-product-hover-content">Wholesale 180% Density 13x4 Transparent Lace Frontal Wig</p>
					</a>
					<a class="wholesale-product-item" href="${APP_PATH}/products/B-3-Bundles-Straight-Wave-Hair-Weave">
						<img class="wholesale-product-img" src="${APP_PATH}/static/pc/img/wholesale/product-2.jpg">
						<p class="wholesale-product-content">Wholesale 10 A grade human hair 10-40 inch Available</p>
						<p class="wholesale-product-hover-content">Wholesale 10 A grade human hair 10-40 inch Available</p>
					</a>
					<a class="wholesale-product-item" href="${APP_PATH}/products/B-Piano-Color-13x4-Straight-Hair-Wig">
						<img class="wholesale-product-img" src="${APP_PATH}/static/pc/img/wholesale/product-3.jpg">
						<p class="wholesale-product-content">Wholesale Highlight Transparent Lace Frontal Wig</p>
						<p class="wholesale-product-hover-content">Wholesale Highlight Transparent Lace Frontal Wig</p>
					</a>
					<a class="wholesale-product-item" href="${APP_PATH}/collections/Closure-Frontal">
						<img class="wholesale-product-img" src="${APP_PATH}/static/pc/img/wholesale/product-4.jpg">
						<p class="wholesale-product-content">Wholesale Transparent lace Frontal EAR TO EAR 13X4 lace</p>
						<p class="wholesale-product-hover-content">Wholesale Transparent lace Frontal EAR TO EAR 13X4 lace</p>
					</a>
					<a class="wholesale-product-item" href="${APP_PATH}/collections/B-3-Bundle-with-4x4-Closure">
						<img class="wholesale-product-img" src="${APP_PATH}/static/pc/img/wholesale/product-5.jpg">
						<p class="wholesale-product-content">Wholesale Transparent 4x4 5x5 Swiss Lace Closure</p>
						<p class="wholesale-product-hover-content">Wholesale Transparent 4x4 5x5 Swiss Lace Closure</p>
					</a>
					<a class="wholesale-product-item" href="${APP_PATH}/products/B-3-Bundles-613-Straight-Wave-Hair-Weave">
						<img class="wholesale-product-img" src="${APP_PATH}/static/pc/img/wholesale/product-6.jpg">
						<p class="wholesale-product-content">Wholesale 10-32 inch Blond Human Hair Weaves</p>
						<p class="wholesale-product-hover-content">Wholesale 10-32 inch Blond Human Hair Weaves</p>
					</a>
					<a class="wholesale-product-item" href="${APP_PATH}/products/B-3-Bundles-Body-Wave-Hair-Weave">
						<img class="wholesale-product-img" src="${APP_PATH}/static/pc/img/wholesale/product-7.jpg">
						<p class="wholesale-product-content">Wholesale Double Drawn-32 inch Human Hair Weaves</p>
						<p class="wholesale-product-hover-content">Wholesale Double Drawn-32 inch Human Hair Weaves</p>
					</a>
					<a class="wholesale-product-item" href="${APP_PATH}/products/B-13x4-Full-Wave-Lace-Wig">
						<img class="wholesale-product-img" src="${APP_PATH}/static/pc/img/wholesale/product-8.jpg">
						<p class="wholesale-product-content">Wholesale 250% Density Human Hair Wigs can customized length</p>
						<p class="wholesale-product-hover-content">Wholesale 250% Density Human Hair Wigs can customized length</p>
					</a>
				</div>
			</div>
		</div>
		<div class="wholesale-box hide">
			<h2 class="wholesale-title">OUR PRODUCTS CAN BRING YOU MORE LOYAL CUSTOMERS AND BRAND REPUTATION</h2>
			<p class="wholesale-p">Because only the good raw material hair can provide customers premium hair products wholesale online, such as can easy to bleach to 613# or lighter color, can use for more than 5-years.</p>
			<p class="wholesale-p">So that It can bring more profit for our customers. It can get more reputation for our customers. It can help our customers expand their business.</p>
			<div class="wholesale-body wholesale-video-swiper">
				<div class="swiper-btn swiper-button-next"></div>
				<div class="swiper-btn swiper-button-prev"></div>
				<div class="swiper-container">
					<div class="swiper-wrapper">
						<div class="swiper-slide">
							<div class="wholesale-video-box">
								<img src="${APP_PATH}/static/pc/img/wholesale/video-1.webp" class="video-img" />
								<img src="${APP_PATH}/static/pc/img/wholesale/play-video.png" class="video-play-btn" />
							</div>
						</div>
						<div class="swiper-slide">
							<div class="wholesale-video-box">
								<img src="${APP_PATH}/static/pc/img/wholesale/video-2.webp" class="video-img" />
								<img src="${APP_PATH}/static/pc/img/wholesale/play-video.png" class="video-play-btn" />
							</div>
						</div>
						<div class="swiper-slide">
							<div class="wholesale-video-box">
								<img src="${APP_PATH}/static/pc/img/wholesale/video-3.webp" class="video-img" />
								<img src="${APP_PATH}/static/pc/img/wholesale/play-video.png" class="video-play-btn" />
							</div>
						</div>
						<div class="swiper-slide">
							<div class="wholesale-video-box">
								<img src="${APP_PATH}/static/pc/img/wholesale/video-4.webp" class="video-img" />
								<img src="${APP_PATH}/static/pc/img/wholesale/play-video.png" class="video-play-btn" />
							</div>
						</div>
						<div class="swiper-slide">
							<div class="wholesale-video-box">
								<img src="${APP_PATH}/static/pc/img/wholesale/video-5.webp" class="video-img" />
								<img src="${APP_PATH}/static/pc/img/wholesale/play-video.png" class="video-play-btn" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="wholesale-box">
			<h2 class="wholesale-title">INSTAGRAM PAGE</h2>
			<p class="wholesale-p">Subscribe to our official Instagram megalookhair, Discover More Professional Information About Megalook Hair Factory and Our Human Hair Products</p>
			<div class="wholesale-body">
				<div class="wholesale-ins-list">
					<a href="#" class="wholesale-ins-item"><img src="${APP_PATH}/static/pc/img/wholesale/ins-1.jpg" /></a>
					<a href="#" class="wholesale-ins-item"><img src="${APP_PATH}/static/pc/img/wholesale/ins-2.jpg" /></a>
					<a href="#" class="wholesale-ins-item"><img src="${APP_PATH}/static/pc/img/wholesale/ins-3.jpg" /></a>
					<a href="#" class="wholesale-ins-item"><img src="${APP_PATH}/static/pc/img/wholesale/ins-4.jpg" /></a>
					<a href="#" class="wholesale-ins-item"><img src="${APP_PATH}/static/pc/img/wholesale/ins-5.jpg" /></a>
				</div>
			</div>
		</div>
		<div class="wholesale-box">
			<h2 class="wholesale-title">You are one step away to access to our wholesale prices</h2>

			<div class="wholesale-body">
				<div class="wholesale-form">
					<div class="info_box">
						<div class="form-group required">
							<label for="wholesaleCustomerName" class="form-label"><i>*</i>Your name :</label>
							<div class="form-input">
								<input type="text" name="wholesaleCustomerName" id="wholesaleCustomerName" class="form-control">
							</div>
						</div>
						<div class="form-group required">
							<label for="wholesaleCustomerEmail" class="form-label"><i>*</i>Email Address :</label>
							<div class="form-input">
								<input type="text" name="wholesaleCustomerEmail" id="wholesaleCustomerEmail" class="form-control">
							</div>
						</div>
						<div class="form-group required">
							<label for="wholesaleCustomerTelephone" class="form-label"><i>*</i>Telephone :</label>
							<div class="form-input">
								<input type="text" name="wholesaleCustomerTelephone" id="wholesaleCustomerTelephone" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label for="wholesaleCustomerWatsapp" class="form-label">Watsapp :</label>
							<div class="form-input">
								<input type="text" name="wholesaleCustomerWatsapp" id="wholesaleCustomerWatsapp" class="form-control">
							</div>
						</div>
						<div class="form-group required">
							<label for="wholesaleCustomerCountry" class="form-label"><i>*</i>Country :</label>
							<div class="form-input">
								<input type="text" name="wholesaleCustomerCountry" id="wholesaleCustomerCountry" class="form-control">
							</div>
						</div>
						<div class="form-group required">
							<label for="wholesaleCustomerTypeName" class="form-label"><i>*</i>Business Type :</label>
							<input name="wholesaleCustomerTypeName" class="form-control" id="wholesaleCustomerTypeName" hidden>
							<div class="form-input">
								<span class="form-radio">
				                  <input type="radio" name="customerTypeName" value="Wholesale & Distributer" id="wholesaleCustomerTypeName-1" class="form-control">
				                  <label for="wholesaleCustomerTypeName-1">Wholesale & Distributer</label>
				                </span>
				                <span class="form-radio">
				                  <input type="radio" name="customerTypeName" value="Store Owner" id="wholesaleCustomerTypeName-2" class="form-control">
				                  <label for="wholesaleCustomerTypeName-2">Store Owner</label>
				                </span>
				                <span class="form-radio">
				                  <input type="radio" name="customerTypeName" value="Personal Use" id="wholesaleCustomerTypeName-3"  class="form-control">
				                  <label for="wholesaleCustomerTypeName-3">Personal Use</label>
				                </span>
				                <span class="form-radio">
				                  <input type="radio" name="customerTypeName"  value="Stylist" id="wholesaleCustomerTypeName-4" class="form-control">
				                  <label for="wholesaleCustomerTypeName-4">Stylist</label>
				                </span>
				                <span class="form-radio">
				                  <input type="radio" name="customerTypeName"  value="Start Business" id="wholesaleCustomerTypeName-5"  class="form-control">
				                  <label for="wholesaleCustomerTypeName-5">Start Business</label>
				                </span>
				                <span class="form-radio">
				                  <input type="radio" name="customerTypeName"  value="Salon Owner" id="wholesaleCustomerTypeName-6"  class="form-control">
				                  <label for="wholesaleCustomerTypeName-6">Salon Owner</label>
				                </span>
							</div>
						</div>
						<div class="form-group required">
							<label for="wholesaleCustomerMessage" class="form-label"><i>*</i>Comment :</label>
							<div class="form-input">
								<textarea rows="2" cols="" class="form-control" id="wholesaleCustomerMessage" placeholder="Write your detailed requiry,hair types,hair quantity,hair length,hair color..."></textarea>
								<span><span>Tips: We will contact you with 24 hours after we receive your request</span>
							</div>
						</div>
						
						<p class="wholesale-info hide"></p>
					</div>
					<div class="form-group">
						<label class="form-label"></label>
						<div class="form-input">
							<input type="button" value="Submit" id="Submit" />
						</div>
					</div>
			   	  </div>
			</div>
		</div>
	<!-- main end -->
	<!-- footer nav -->
	<jsp:include page="../layout/footer/footer.jsp" flush="true"></jsp:include>

	<!-- common script -->
	<script src="${APP_PATH}/static/common/jquery.min.js"></script>
	<script>function getCarouselData(area,async,callback){$.ajax({url:"${APP_PATH}/MlbackSlides/getMlbackSlidListByArea",data:JSON.stringify({slideArea:area}),dataType:"json",contentType:"application/json",type:"post",async:async,success:function(data){100==data.code&&callback&&callback(data.extend.mlbackSlideList)}})}function debounce(callback,delay){var timeEnd;delay=delay||100,Date.now()-timeStart>delay&&clearTimeout(timer),timer=setTimeout((function(){callback&&callback()}),delay)}</script>
	<!-- lazy -->
	<script>!function(t,e){"object"==typeof exports?module.exports=e(t):"function"==typeof define&&define.amd?define([],e):t.LazyLoad=e(t)}("undefined"!=typeof global?global:this.window||this.global,function(t){"use strict";function e(t,e){this.settings=o(r,e||{}),this.images=t||document.querySelectorAll(this.settings.selector),this.observer=null,this.init()}"function"==typeof define&&define.amd&&(t=window);const r={src:"data-src",srcset:"data-srcset",selector:".lazyload",root:null,rootMargin:"0px",threshold:0},o=function(){let t={},e=!1,r=0,s=arguments.length;for("[object Boolean]"===Object.prototype.toString.call(arguments[0])&&(e=arguments[0],r++);r<s;r++)!function(r){for(let s in r)Object.prototype.hasOwnProperty.call(r,s)&&(e&&"[object Object]"===Object.prototype.toString.call(r[s])?t[s]=o(!0,t[s],r[s]):t[s]=r[s])}(arguments[r]);return t};if(e.prototype={init:function(){if(!t.IntersectionObserver)return void this.loadImages();let e=this,r={root:this.settings.root,rootMargin:this.settings.rootMargin,threshold:[this.settings.threshold]};this.observer=new IntersectionObserver(function(t){Array.prototype.forEach.call(t,function(t){if(t.isIntersecting){e.observer.unobserve(t.target);let r=t.target.getAttribute(e.settings.src),o=t.target.getAttribute(e.settings.srcset),s=new Image;s.src=r,s.onload=function(e){var s=e.target.width,i=e.target.height;"img"===t.target.tagName.toLowerCase()?(r&&(t.target.src=r),o&&(t.target.srcset=o)):t.target.style.backgroundImage="url("+r+")",t.target.width=s,t.target.height=i,t.target.classList.add("loaded")},s.onerror=function(){}}})},r),Array.prototype.forEach.call(this.images,function(t){e.observer.observe(t)})},loadAndDestroy:function(){this.settings&&(this.loadImages(),this.destroy())},loadImages:function(){if(!this.settings)return;let t=this;Array.prototype.forEach.call(this.images,function(e){let r=e.getAttribute(t.settings.src),o=e.getAttribute(t.settings.srcset);"img"===e.tagName.toLowerCase()?(r&&(e.src=r),o&&(e.srcset=o)):e.style.backgroundImage="url('"+r+"')"})},destroy:function(){this.settings&&(this.observer.disconnect(),this.settings=null)}},t.lazyload=function(t,r){return new e(t,r)},t.jQuery){const r=t.jQuery;r.fn.lazyload=function(t){return(t=t||{}).attribute=t.attribute||"data-src",new e(r.makeArray(this),t),this}}return e});</script>
	<!-- swiper script -->
	<script src="${APP_PATH}/static/common/swiper/swiper.min.js"></script>
	<!-- common footer script -->
	<jsp:include page="../common/footer.jsp" flush="true"></jsp:include>
	<!-- header script  -->
	<jsp:include page="../layout/header/header-script.min.jsp" flush="true"></jsp:include>
	<script>
		// //check user input info
		function checkInputAdressInfo() {
			var flag = true;
			for(var idx = 0, len = $('.info_box .form-group.required').length; idx < len; idx += 1) {
				var item = $('.info_box .form-group.required')[idx];
		
				if (!$(item).find('.form-control').val().trim()) {
					mlModalTip('Address information <i style="color: #f00">'+ $(item).find('.form-label').text() +"</i> can't be empty !");
					$(item).find('.form-control').focus();
					flag = false;
					break;
				}
			}
			return flag;
		}
		function getFormData() {
			var data = {};
			data.wholesaleCustomerName = $('#wholesaleCustomerName').val();
			data.wholesaleCustomerEmail = $('#wholesaleCustomerEmail').val();
			data.wholesaleCustomerCountry = $('#wholesaleCustomerCountry').val();
			data.wholesaleCustomerTelephone = $('#wholesaleCustomerTelephone').val();
			data.wholesaleCustomerWatsapp = $('#wholesaleCustomerWatsapp').val();
			data.wholesaleCustomerMessage = $('#wholesaleCustomerMessage').val();
			data.wholesaleCustomerTypeName = $('#wholesaleCustomerTypeName').val();
			return data;
		}
		// save user info
		function orderSaveAddress(reqData, callback) {
			$.ajax({
				url: '${APP_PATH}/CustomerWholesale/save',
				type: 'post',
				dataType: 'json',
				data: JSON.stringify(reqData),
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						callback && callback(data.extend);
						var wholsaleModal = createModal({
							body: {
								html: "<p style='color:red'>Thanks again for your inquiry,our staff will contact you soon!</p>",
							}
						}, function() {
							window.location.reload();
						});
					} else {
						sysModalTip();
					}  
				},
				error: function () {
					sysModalTip();
				}
			});
		}
		var wholesaleCustomerTypeName;
		var carouselSwiper = new Swiper('.wholesale-carousel-swiper.swiper-container', {
			freeMode: true,
			pagination: {
				el: '.wholesale-carousel-swiper .swiper-pagination',
				clickable: true
			},
			speed: 600,
			autoplay: { delay: 4e3, }
		});	
		var videoSwiper = new Swiper('.wholesale-video-swiper .swiper-container', {
			slidesPerView: 'auto',
			spaceBetween: 10,
			freeMode: true,
			navigation: {
				nextEl: '.wholesale-video-swiper .swiper-button-next',
				prevEl: '.wholesale-video-swiper .swiper-button-prev',
			}
		});		
		new LazyLoad($('main').find('.lazyload'), {
			root: null,
			rootMargin: "10px",
			threshold: 0
		});
		$('input[name="customerTypeName"]').on('change', function() {
			$('#wholesaleCustomerTypeName').val($(this).val());
		});
		$(document.body).on('click', '#Submit', function() {
			if (checkInputAdressInfo()) {
				var reqData = getFormData();
				if ((reqData.wholesaleCustomerMessage).length > 2000) {
					$('.wholesale-info').removeClass('hide').text('The maximum message length is 2000 characters...');
					return false;
				}
				orderSaveAddress(reqData);
			}
		});
		$(document.body).on('focus', '#wholesaleCustomerMessage', function() {
			if (!$('#wholesaleCustomerMessage').hasClass('hide')) {
			 	$('.wholesale-info').addClass('hide').text('');
			}
		});
	</script>
	<!-- footer script -->
	<jsp:include page="../layout/footer/footer-script.min.jsp" flush="true"></jsp:include>
	<script> addTidio(); </script>
</body>
</html>