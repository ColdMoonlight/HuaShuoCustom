<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<!DOCTYPE html>
<html>
<head>
    <title>Best Wholesale Hair | megalook.com</title>
	<meta name="keywords" content="Lace Front Wigs, Human Hair Extensions,Brazilian Virgin Hair,Affordable Human Hair" />
	<meta name="description" content="Megalook Hair Official Website Sells Best Virgin Hair, Full Lace Wig, Transparent Lace Wig, 360 Lace Wig, Frontal Lace Wig, Closure Lace Wig, Bundles with Closure, Bundles with Frontal, Free Wig Making Service. Hottest Products, Free Gifts, Free & Fast Shipping. No Shedding, No Tangles, Save Big! Lowest Price For U.">
    <!-- google rule -->
	<meta name="robots" content="INDEX,FOLLOW">
    <link rel="alternate" href="https://megalook.com/Customer/Wholesale.html" hreflang="en-us" />
	<link rel="canonical" href="https://megalook.com/Customer/Wholesale.html" />
	<!-- socail meta -->
	<meta name="twitter:site" content="@megalookhair">
	<meta name="twitter:card" content="summary">
	<meta name="twitter:title" content="Megalook Hair, Virgin Remy Human Hair Wigs, Lace Front Wigs, Best Wigs | megalook.com">
	<meta name="twitter:description" content="Megalook Hair Official Website Sells Best Virgin Hair, Full Lace Wig, Transparent Lace Wig, 360 Lace Wig, Frontal Lace Wig, Closure Lace Wig, Bundles with Closure, Bundles with Frontal, Free Wig Making Service. Hottest Products, Free Gifts, Free & Fast Shipping. No Shedding, No Tangles, Save Big! Lowest Price For U.">
	<meta property="og:type" content="website">
	<meta property="og:title" content="Megalook Hair, Virgin Remy Human Hair Wigs, Lace Front Wigs, Best Wigs | megalook.com">
	<meta property="og:url" content="https://megalook.com/Customer/Wholesale.html">
	<meta property="og:site_name" content="Megalook Hair">
	<meta property="og:image" content="https://www.megalook.com/static/apple/micon1.png" />
	<jsp:include page="../common/processor.jsp" flush="true"></jsp:include>
	<jsp:include page="../common/header.jsp" flush="true"></jsp:include>
	<style>
		@media only screen and (max-width: 1023px) { main { margin: 0; } }
		@media only screen and (max-width: 1078px) and (min-width: 576px) { main { margin: 0; } }
		@media only screen and (min-width: 576px){
			.ins-banner .pc { padding-top: 38.333333%; }
		}
		/*wholesale*/
		.title_tips{width: 100%;margin: 0 auto; background: #f5f5f5;}
		.title_tips h3{padding: 0; margin: 0; color: #ff1671;font-size: 14px; font-weight:600;width: 100%; line-height: 55px; text-align: center;}
		.Wholesale-body{margin: 15px 0 0;}
		.wholesale-form h2,.wholesale-chat h2{background: #f4f4f4;margin: 20px 0;font-family: calibri;font-size: 20px;padding: 10px 10px 15px;border-radius: 10px;border-left: 1px solid #f92d96;text-transform: unset;  border-right: 1px solid #f92d96; border-bottom: 5px solid #f92d96;	text-align: center;}
		.wholesale-form .form-group{margin:20px 0 0;}
		.wholesale-form .form-group input{border-color: #a9a9a9;}
		.wholesale-form .form-group label{font-weight: bold; display: block; margin: 0 0 10px;}
		.wholesale-form .form-group textarea {width: 100%; height: 100px;border-color: #a9a9a9;}
		.wholesale-form .form-group i{color:#ff6f5e;}
		.wholesale-form .form-group #Submit{background: #f82d98; border:none; color: #fff; line-height: 30px;}
		.wholesale-form{width: 50%;}
		.wholesale-chat{width:50%; margin: 0 0 0 5%;}
		.a-center{text-align: center;}
		.wholesale-chat ul{width: 100%; margin: 30px auto; text-align: left; list-style:none; padding: 0;}
		.wholesale-chat ul li{position: relative; line-height: 50px;font-size: 18px;  padding: 0 0 0 40px; border-bottom: 1px dashed #262626;}
		.wholesale-chat ul li span{ width: 40px; height: 40px; position: absolute; top:50%; margin: -20px 0 0; left: 0;background: url("${APP_PATH }/static/pc/img/wh/ab_07.jpg") no-repeat;}
		.wholesale-chat ul li a{ text-decoration: none; color: #000;}
		.wholesale-chat ul li.live span{background-position: 0 5px;}
		.wholesale-chat ul li.email span{background-position: 0 -66px; }
		.wholesale-chat ul li.whatsapp{ line-height: 25px;}
		.wholesale-chat ul li.whatsapp span{background-position: 0 -32px;top:25px;}
		.wholesale-chat ul li.tel span{background-position: 0 -105px;}
		.wholesale-chat ul li p,.wholesale-chat ul li p a{color: #bd0000;font-weight: bold;}
		.wholesale-container{width: 100%;}
		.wholesale-large{width: 100%;}
		.wholesale-large_left{width: 60%; float: left;}
		.wholesale-large_right{width: 35%; margin: 0 0 0 5%; float: left;}
		.wholesale-large_right img{display:block; margin: 0 auto;}
		.wholesale-large_left span{display:block;line-height:20px;margin-bottom:5px}
		.wholesale-container img{max-width: 100%;}
		.wholesale-type{width: 100%;}
		.wholesale-type_link{text-align: center;}	
		@media only screen and (min-width: 1024px){
		.container1300{max-width: 1300px;}
		.Wholesale-body{display: flex;align-items: flex-start;margin:15px 0 0;}
		.Wholesale_cont{width: 100%; display: flex; align-items: flex-start;}
		.title_tips h3{ font-weight: 700;font-size: 22px;}
		.title_tips {width: 80%; margin: 0 10%;}
		}
		@media only screen and (max-width:1300px) {	
		.wholesale-form{width: 100%; padding: 0 2%;}
		.wholesale-chat{width:100%; padding: 0 2%;margin: 0;}
		.wholesale-type a{width: 48%; margin: 0 1%; display: block; float: left;}
		.wholesale-large_left{width: 100% ; padding: 0 5%; margin: 0;}
		.wholesale-large_right{width:100%;margin:0 0 20px;}
		}
		.wholesale-info { margin: 0; font-size: 12px; color: #ff0000; font-style: italic; }
	</style>
</head>
<body>
    <jsp:include page="../layout/header/header.jsp" flush="true"></jsp:include>
	<!-- main start -->
		<div class="ins-banner">
			<div class="wap lazyload" data-src="${APP_PATH }/static/pc/img/wh/wap.jpg"></div>
			<div class="pc lazyload" data-src="${APP_PATH }/static/pc/img/wh/pc.jpg"></div>
		</div>
		<div class="container container1300">
			<!-- Wholesale-body -->
		   <div class="Wholesale-body clearfix">
			   <div class="title_tips"><h3>Min amount $500 qualifies for special wholesale rate!</h3></div>
		   </div>
		   <div class="Wholesale_cont clearfix">
		   	  <div class="wholesale-form">
				  <h2>Send Inquiry To Become A Megalook Vendor</h2>
				<div class="info_box">
					<div class="form-group required">
						<label for="wholesaleCustomerName" class="form-label">Your name<i>*</i></label>
						<div class="form-input">
							<input type="text" name="wholesaleCustomerName" id="wholesaleCustomerName" class="form-control">
						</div>
					</div>
					<div class="form-group required">
						<label for="wholesaleCustomerEmail" class="form-label">Email<i>*</i></label>
						<div class="form-input">
							<input type="text" name="wholesaleCustomerEmail" id="wholesaleCustomerEmail" class="form-control">
						</div>
					</div>
					<div style="display: flex;">
						<div class="form-group required" style="width: 50%;">
							<label for="wholesaleCustomerTelephone" class="form-label">Telephone<i>*</i></label>
							<div class="form-input">
								<input type="text" name="wholesaleCustomerTelephone" id="wholesaleCustomerTelephone" class="form-control">
							</div>
						</div>
						<div class="form-group" style="width: 50%; margin-left: 1rem;">
							<label for="wholesaleCustomerWatsapp" class="form-label">Watsapp<i></i></label>
							<div class="form-input">
								<input type="text" name="wholesaleCustomerWatsapp" id="wholesaleCustomerWatsapp" class="form-control">
							</div>
						</div>
					</div>
					<div class="form-group required">
						<label for="wholesaleCustomerCountry" class="form-label">Country<i>*</i></label>
						<div class="form-input">
							<input type="text" name="wholesaleCustomerCountry" id="wholesaleCustomerCountry" class="form-control">
						</div>
					</div>
					<div class="form-group required">
						<label for="wholesaleCustomerMessage" class="form-label">Messages<i>*</i></label>
						<div class="form-input">
							<textarea rows="2" cols="" class="form-control" id="wholesaleCustomerMessage" placeholder="Write your detailed requiry,hair types,hair quantity,hair length,hair color..."></textarea>
						</div>
					</div>
					<p class="wholesale-info hide"></p>
				</div>
				<div class="form-group">
					<div class="form-input">
						<input type="button" value="Submit" id="Submit" />
					</div>
				</div>
		   	  </div>
			  <div class="wholesale-chat">
				  <h2> 24-Hours Online Service Waiting For You! </h2>
				  <p class="a-center">
					  <img src="${APP_PATH }/static/pc/img/wh/ab_03.jpg" />
				  </p>
				  <ul>
					  <li class="live">
						  <span></span><a href="javascript:;">Live chat----</a>
					  </li>
					  <li class="whatsapp">
						  <span></span>
						  <p> Whatsapp: +  <a href="tel:86 18903740682">86 18903740682</a> <br /> +  <a href="tel:86 15038903005">86 15038903005</a> + <a href="tel:86 15539762631">86 15539762631</a> </p>
					  </li>
					  <li class="email">
						  <span></span>
						  <a href="mailto:service@megalook.com">Email: service@megalook.com</a>
					  </li>
					  <li class="tel">
						  <span></span>
						<a href=" TEL:(501)7226336"> TEL:(501)7226336</a>
					  </li>
				  </ul>
			  </div>
		   </div>
		   <div class="wholesale-container clearfix">
		           <h3>
		               <img src="${APP_PATH }/static/pc/img/wh/ab_11.jpg">
		           </h3>
		           <div class="clearfix wholesale-type">
		               <a class="wholesale-type_link" href="${APP_PATH }/search/4x4-Closure-Wig.html">
		                   <img src="${APP_PATH }/static/pc/img/wh/a01.jpg">
		               </a>
		               <a class="wholesale-type_link" href="${APP_PATH }/search/13x4-Lace-Frontal-Wig.html">
		                   <img src="${APP_PATH }/static/pc/img/wh/a02.jpg">
		               </a>
					   <a class="wholesale-type_link" href="${APP_PATH }/search/360-Lace-Frontal-Wig.html">
					       <img src="${APP_PATH }/static/pc/img/wh/a03.jpg">
					   </a>
					   <a class="wholesale-type_link" href="${APP_PATH }/search/HAIR-WEAVE-CLOSURE.html">
					       <img src="${APP_PATH }/static/pc/img/wh/a04.jpg">
					   </a>
		           </div>
		           <h3>
		               <img src="${APP_PATH }/static/pc/img/wh/ab_25_01.jpg">
		           </h3>
		           <p><img src="${APP_PATH }/static/pc/img/wh/ab_28_01.jpg"></p>
		           <h3>
		               <img src="${APP_PATH }/static/pc/img/wh/ab_29_01.jpg">
		           </h3>
				  
		           <p> <img src="${APP_PATH }/static/pc/img/wh/ab_30.jpg"></p>
		           <h3>
		               <img src="${APP_PATH }/static/pc/img/wh/ab_32.jpg">
		           </h3>
		           <div class="wholesale-large clearfix">
		               <div class="wholesale-large_left clearfix">
		                 Megalook Hair, as one of the leading manufacturers and brand from China, we are focus on providing reliable and natural 100% virgin human hair.  We source the globe, to provide different region of hair such as Brazilian, Malaysian, Peruvian & Indian hair to distributor business owners & wholesale vendors all around the world.  Our capacity & capability allow for us to not only handle volume of production & order but also maintain the highest quality of virgin human hair.  Therefore, we’re able to pass on the amazing competitive rate for our clientele to grow with us.
		               <br /><br /><br />
					   Since the launch of our company, over the many years, we have carefully and maintained strong & enduring relationship with each of our business partners, vendors and loyal fans. <br /><br /><br />
					   Fans around the world shared their love of Megalook hair through YouTube, Facebook, Instagram and many other social media platforms.  We are so grateful for our fans! <br /><br /><br />
					   To service our customer base to the highest quality possible, we Update our hair many times,Megalook, My New Look!<br /><br />
					   </div>
					   <div class="wholesale-large_right clearfix"><img src="${APP_PATH }/static/pc/img/wh/rttp.jpg" /></div>
		           </div>
		           <p><img src="${APP_PATH }/static/pc/img/wh/dbtp.jpg" /></p>
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
		new LazyLoad($('main').find('.lazyload'), {
			root: null,
			rootMargin: "10px",
			threshold: 0
		});
		
	</script>
	<!-- footer script -->
	<jsp:include page="../layout/footer/footer-script.min.jsp" flush="true"></jsp:include>
	<script> addTidio(); </script>
</body>
</html>