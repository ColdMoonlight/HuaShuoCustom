<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<!DOCTYPE html>
<!--[if IE 8]><html class="no-js lt-ie9" lang="en" xmlns:og="http://ogp.me/ns#" xmlns:fb="http://www.facebook.com/2008/fbml"> <![endif]-->
<!--[if IE 9 ]><html class="ie9 no-js" xmlns:og="http://ogp.me/ns#" xmlns:fb="http://www.facebook.com/2008/fbml"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html class="no-js" xmlns:og="http://ogp.me/ns#" xmlns:fb="http://www.facebook.com/2008/fbml"> <!--<![endif]-->
<head>
	<title>${ map.checkout_title }</title>
	<meta name="keywords" content="${ map.checkout_keywords }" />
	<meta name="description" content="${ map.checkout_description }">
	<jsp:include page="../common/processor.jsp" flush="true"></jsp:include>
	<!-- google rule -->
	<meta name="robots" content="INDEX,FOLLOW">
    <link rel="alternate" href="${ map.website_domain }/MlbackCart/toCheakOut" hreflang="en-us" />
	<link rel="canonical" href="${ map.website_domain }/MlbackCart/toCheakOut" />
	<!-- socail media -->
	<meta property="og:type" content="website">
	<meta property="og:title" content="${ map.checkout_title }">
	<meta property="og:url" content="${ map.website_domain }/MlbackCart/toCheakOut">
	<meta property="og:site_name" content="${ map.website_name }">
	<meta property="og:image" content="${ map.website_domain }/static/apple/micon1.png" />
	<meta name="twitter:site" content="@${ map.website_name }">
	<meta name="twitter:card" content="summary_large_image">
	<jsp:include page="../common/header.jsp" flush="true"></jsp:include>
	<style>main { margin: 0; }</style>
	<style>.spinner,.spinner:after,.spinner:before{border-radius:50%}.spinner{position:relative;margin-right:.75rem;color:#fff;width:20px;height:20px;box-shadow:inset 0 0 0 2px;-webkit-transform:translateZ(0);-ms-transform:translateZ(0);transform:translateZ(0)}.spinner:after,.spinner:before{position:absolute;content:"";background:#5469d4}.spinner:before{width:10.4px;height:20.4px;border-radius:20.4px 0 0 20.4px;top:-.2px;left:-.2px;-webkit-transform-origin:10.4px 10.2px;transform-origin:10.4px 10.2px;-webkit-animation:loading 2s infinite ease 1.5s;animation:loading 2s infinite ease 1.5s}.spinner:after{width:10.4px;height:10.2px;border-radius:0 10.2px 10.2px 0;top:-.1px;left:10.2px;-webkit-transform-origin:0 10.2px;transform-origin:0 10.2px;-webkit-animation:loading 2s infinite ease;animation:loading 2s infinite ease}@-webkit-keyframes loading{0%{-webkit-transform:rotate(0);transform:rotate(0)}100%{-webkit-transform:rotate(360deg);transform:rotate(360deg)}}@keyframes loading{0%{-webkit-transform:rotate(0);transform:rotate(0)}100%{-webkit-transform:rotate(360deg);transform:rotate(360deg)}}.card-element-box{position:relative;padding:1rem;margin-top:.5rem;background-color:#f5f5f5}.card-element-box .spinner:after,.card-element-box .spinner:before{background-color:#f5f5f5}#card-element{width:100%;height:2.75rem;padding:.75rem;border-radius:.25rem .25rem 0 0;border:1px solid rgba(50,50,93,.1);background-color:#fff}#card-error{margin:.25rem 0 0 0;font-style:italic;font-size:.75rem;text-align:left;color:#ff1671}.card-element-box.mask #card-element{background-color:#f5f5f5;border-color:#f5f5f5}.card-element-box.mask .spinner{display:block;color:#333}#payment-form{display:flex;align-items:center;justify-content:center}#payment-form .spinner:after,#payment-form .spinner:before{background-color:#ccc}#payment-form[disabled]{background-color:#ccc;border-color:#ccc}.card-element-box .spinner{position:absolute;top:50%;left:50%;display:none;transform:translate(-50%,-50%)}.order-payment-item~.order-payment-item{margin-top:.5rem;}.form-input{position:relative}.address-box .form-no-label::after{position:absolute;top:.7rem;left:.4rem;content:'*';display:inline-block;font-size:.875rem;color:red;vertical-align:middle}</style>
	<style>.btn-yellow{background-color:#ffc439}.complete-order{display:flex;align-items:center;justify-content:center}.complete-order-2{color:#fff}.paypal-btn{display:inline-block;background-position:center;background-repeat:no-repeat;background-size:cover}.paypal-btn-1,.paypal-btn-3{background-image:url(data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTAxcHgiIGhlaWdodD0iMzIiIHZpZXdCb3g9IjAgMCAxMDEgMzIiIHByZXNlcnZlQXNwZWN0UmF0aW89InhNaW5ZTWluIG1lZXQiIHhtbG5zPSJodHRwOiYjeDJGOyYjeDJGO3d3dy53My5vcmcmI3gyRjsyMDAwJiN4MkY7c3ZnIj48cGF0aCBmaWxsPSIjMDAzMDg3IiBkPSJNIDEyLjIzNyAyLjggTCA0LjQzNyAyLjggQyAzLjkzNyAyLjggMy40MzcgMy4yIDMuMzM3IDMuNyBMIDAuMjM3IDIzLjcgQyAwLjEzNyAyNC4xIDAuNDM3IDI0LjQgMC44MzcgMjQuNCBMIDQuNTM3IDI0LjQgQyA1LjAzNyAyNC40IDUuNTM3IDI0IDUuNjM3IDIzLjUgTCA2LjQzNyAxOC4xIEMgNi41MzcgMTcuNiA2LjkzNyAxNy4yIDcuNTM3IDE3LjIgTCAxMC4wMzcgMTcuMiBDIDE1LjEzNyAxNy4yIDE4LjEzNyAxNC43IDE4LjkzNyA5LjggQyAxOS4yMzcgNy43IDE4LjkzNyA2IDE3LjkzNyA0LjggQyAxNi44MzcgMy41IDE0LjgzNyAyLjggMTIuMjM3IDIuOCBaIE0gMTMuMTM3IDEwLjEgQyAxMi43MzcgMTIuOSAxMC41MzcgMTIuOSA4LjUzNyAxMi45IEwgNy4zMzcgMTIuOSBMIDguMTM3IDcuNyBDIDguMTM3IDcuNCA4LjQzNyA3LjIgOC43MzcgNy4yIEwgOS4yMzcgNy4yIEMgMTAuNjM3IDcuMiAxMS45MzcgNy4yIDEyLjYzNyA4IEMgMTMuMTM3IDguNCAxMy4zMzcgOS4xIDEzLjEzNyAxMC4xIFoiPjwvcGF0aD48cGF0aCBmaWxsPSIjMDAzMDg3IiBkPSJNIDM1LjQzNyAxMCBMIDMxLjczNyAxMCBDIDMxLjQzNyAxMCAzMS4xMzcgMTAuMiAzMS4xMzcgMTAuNSBMIDMwLjkzNyAxMS41IEwgMzAuNjM3IDExLjEgQyAyOS44MzcgOS45IDI4LjAzNyA5LjUgMjYuMjM3IDkuNSBDIDIyLjEzNyA5LjUgMTguNjM3IDEyLjYgMTcuOTM3IDE3IEMgMTcuNTM3IDE5LjIgMTguMDM3IDIxLjMgMTkuMzM3IDIyLjcgQyAyMC40MzcgMjQgMjIuMTM3IDI0LjYgMjQuMDM3IDI0LjYgQyAyNy4zMzcgMjQuNiAyOS4yMzcgMjIuNSAyOS4yMzcgMjIuNSBMIDI5LjAzNyAyMy41IEMgMjguOTM3IDIzLjkgMjkuMjM3IDI0LjMgMjkuNjM3IDI0LjMgTCAzMy4wMzcgMjQuMyBDIDMzLjUzNyAyNC4zIDM0LjAzNyAyMy45IDM0LjEzNyAyMy40IEwgMzYuMTM3IDEwLjYgQyAzNi4yMzcgMTAuNCAzNS44MzcgMTAgMzUuNDM3IDEwIFogTSAzMC4zMzcgMTcuMiBDIDI5LjkzNyAxOS4zIDI4LjMzNyAyMC44IDI2LjEzNyAyMC44IEMgMjUuMDM3IDIwLjggMjQuMjM3IDIwLjUgMjMuNjM3IDE5LjggQyAyMy4wMzcgMTkuMSAyMi44MzcgMTguMiAyMy4wMzcgMTcuMiBDIDIzLjMzNyAxNS4xIDI1LjEzNyAxMy42IDI3LjIzNyAxMy42IEMgMjguMzM3IDEzLjYgMjkuMTM3IDE0IDI5LjczNyAxNC42IEMgMzAuMjM3IDE1LjMgMzAuNDM3IDE2LjIgMzAuMzM3IDE3LjIgWiI+PC9wYXRoPjxwYXRoIGZpbGw9IiMwMDMwODciIGQ9Ik0gNTUuMzM3IDEwIEwgNTEuNjM3IDEwIEMgNTEuMjM3IDEwIDUwLjkzNyAxMC4yIDUwLjczNyAxMC41IEwgNDUuNTM3IDE4LjEgTCA0My4zMzcgMTAuOCBDIDQzLjIzNyAxMC4zIDQyLjczNyAxMCA0Mi4zMzcgMTAgTCAzOC42MzcgMTAgQyAzOC4yMzcgMTAgMzcuODM3IDEwLjQgMzguMDM3IDEwLjkgTCA0Mi4xMzcgMjMgTCAzOC4yMzcgMjguNCBDIDM3LjkzNyAyOC44IDM4LjIzNyAyOS40IDM4LjczNyAyOS40IEwgNDIuNDM3IDI5LjQgQyA0Mi44MzcgMjkuNCA0My4xMzcgMjkuMiA0My4zMzcgMjguOSBMIDU1LjgzNyAxMC45IEMgNTYuMTM3IDEwLjYgNTUuODM3IDEwIDU1LjMzNyAxMCBaIj48L3BhdGg+PHBhdGggZmlsbD0iIzAwOWNkZSIgZD0iTSA2Ny43MzcgMi44IEwgNTkuOTM3IDIuOCBDIDU5LjQzNyAyLjggNTguOTM3IDMuMiA1OC44MzcgMy43IEwgNTUuNzM3IDIzLjYgQyA1NS42MzcgMjQgNTUuOTM3IDI0LjMgNTYuMzM3IDI0LjMgTCA2MC4zMzcgMjQuMyBDIDYwLjczNyAyNC4zIDYxLjAzNyAyNCA2MS4wMzcgMjMuNyBMIDYxLjkzNyAxOCBDIDYyLjAzNyAxNy41IDYyLjQzNyAxNy4xIDYzLjAzNyAxNy4xIEwgNjUuNTM3IDE3LjEgQyA3MC42MzcgMTcuMSA3My42MzcgMTQuNiA3NC40MzcgOS43IEMgNzQuNzM3IDcuNiA3NC40MzcgNS45IDczLjQzNyA0LjcgQyA3Mi4yMzcgMy41IDcwLjMzNyAyLjggNjcuNzM3IDIuOCBaIE0gNjguNjM3IDEwLjEgQyA2OC4yMzcgMTIuOSA2Ni4wMzcgMTIuOSA2NC4wMzcgMTIuOSBMIDYyLjgzNyAxMi45IEwgNjMuNjM3IDcuNyBDIDYzLjYzNyA3LjQgNjMuOTM3IDcuMiA2NC4yMzcgNy4yIEwgNjQuNzM3IDcuMiBDIDY2LjEzNyA3LjIgNjcuNDM3IDcuMiA2OC4xMzcgOCBDIDY4LjYzNyA4LjQgNjguNzM3IDkuMSA2OC42MzcgMTAuMSBaIj48L3BhdGg+PHBhdGggZmlsbD0iIzAwOWNkZSIgZD0iTSA5MC45MzcgMTAgTCA4Ny4yMzcgMTAgQyA4Ni45MzcgMTAgODYuNjM3IDEwLjIgODYuNjM3IDEwLjUgTCA4Ni40MzcgMTEuNSBMIDg2LjEzNyAxMS4xIEMgODUuMzM3IDkuOSA4My41MzcgOS41IDgxLjczNyA5LjUgQyA3Ny42MzcgOS41IDc0LjEzNyAxMi42IDczLjQzNyAxNyBDIDczLjAzNyAxOS4yIDczLjUzNyAyMS4zIDc0LjgzNyAyMi43IEMgNzUuOTM3IDI0IDc3LjYzNyAyNC42IDc5LjUzNyAyNC42IEMgODIuODM3IDI0LjYgODQuNzM3IDIyLjUgODQuNzM3IDIyLjUgTCA4NC41MzcgMjMuNSBDIDg0LjQzNyAyMy45IDg0LjczNyAyNC4zIDg1LjEzNyAyNC4zIEwgODguNTM3IDI0LjMgQyA4OS4wMzcgMjQuMyA4OS41MzcgMjMuOSA4OS42MzcgMjMuNCBMIDkxLjYzNyAxMC42IEMgOTEuNjM3IDEwLjQgOTEuMzM3IDEwIDkwLjkzNyAxMCBaIE0gODUuNzM3IDE3LjIgQyA4NS4zMzcgMTkuMyA4My43MzcgMjAuOCA4MS41MzcgMjAuOCBDIDgwLjQzNyAyMC44IDc5LjYzNyAyMC41IDc5LjAzNyAxOS44IEMgNzguNDM3IDE5LjEgNzguMjM3IDE4LjIgNzguNDM3IDE3LjIgQyA3OC43MzcgMTUuMSA4MC41MzcgMTMuNiA4Mi42MzcgMTMuNiBDIDgzLjczNyAxMy42IDg0LjUzNyAxNCA4NS4xMzcgMTQuNiBDIDg1LjczNyAxNS4zIDg1LjkzNyAxNi4yIDg1LjczNyAxNy4yIFoiPjwvcGF0aD48cGF0aCBmaWxsPSIjMDA5Y2RlIiBkPSJNIDk1LjMzNyAzLjMgTCA5Mi4xMzcgMjMuNiBDIDkyLjAzNyAyNCA5Mi4zMzcgMjQuMyA5Mi43MzcgMjQuMyBMIDk1LjkzNyAyNC4zIEMgOTYuNDM3IDI0LjMgOTYuOTM3IDIzLjkgOTcuMDM3IDIzLjQgTCAxMDAuMjM3IDMuNSBDIDEwMC4zMzcgMy4xIDEwMC4wMzcgMi44IDk5LjYzNyAyLjggTCA5Ni4wMzcgMi44IEMgOTUuNjM3IDIuOCA5NS40MzcgMyA5NS4zMzcgMy4zIFoiPjwvcGF0aD48L3N2Zz4=)}.paypal-btn-2{margin-right:.5rem;background-image:url(data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjRweCIgaGVpZ2h0PSIxOHB4IiB2aWV3Qm94PSIwIDAgMjQgMTgiIHhtbG5zPSJodHRwOiYjeDJGOyYjeDJGO3d3dy53My5vcmcmI3gyRjsyMDAwJiN4MkY7c3ZnIj48ZyBzdHJva2U9Im5vbmUiIHN0cm9rZS13aWR0aD0iMSIgZmlsbD0ibm9uZSIgZmlsbC1ydWxlPSJldmVub2RkIj48ZyB0cmFuc2Zvcm09InRyYW5zbGF0ZSgtMy4wMDAwMDAsIC02LjAwMDAwMCkiIGZpbGw9IiNmZmZmZmYiIGZpbGwtcnVsZT0ibm9uemVybyI+PHBhdGggZD0iTTguMjc1MjEzMzgsMTIuNTEyMjY1MyBDNy45MzAwMzU0MiwxMi41MTIyNjUzIDcuNjUwMjEzMzgsMTIuMjMyNDQzMiA3LjY1MDIxMzM4LDExLjg4NzI2NTMgQzcuNjUwMjEzMzgsMTEuNTQyMDg3MyA3LjkzMDAzNTQyLDExLjI2MjI2NTMgOC4yNzUyMTMzOCwxMS4yNjIyNjUzIEwyNC43ODc5MDQyLDExLjI2MjI2NTMgQzI1LjU5NTU5MzksMTEuMjYyMjY1MyAyNi4yNSwxMS45MTc1OTA1IDI2LjI1LDEyLjcyNTUzNjggTDI2LjI1LDIyLjI4NjcyODQgQzI2LjI1LDIzLjA5NDY3NDggMjUuNTk1NTkzOSwyMy43NSAyNC43ODc5MDQyLDIzLjc1IEw1LjIxMjMxMzAyLDIzLjc1IEM0LjQwNDYyMzI1LDIzLjc1IDMuNzUsMjMuMDk0Njc0OCAzLjc1LDIyLjI4NjczOTcgTDMuNzUsNy43MTMyNzE1MiBDMy43NSw2LjkwNTMyNTE4IDQuNDA0NDA2MDgsNi4yNSA1LjIxMjI3MjEyLDYuMjUgTDI0Ljc4ODA2NjQsNi4yNTU1MjE2MyBDMjUuNTk1NjA3OSw2LjI1NTczMTQ3IDI2LjI1LDYuOTEwOTk1MDcgMjYuMjUsNy43MTg3MDM2MiBMMjYuMjUsOS4yMzU3NzE2MSBDMjYuMjUsOS41ODA5NDk1OCAyNS45NzAyNjc1LDkuODYwODExNjggMjUuNjI1MDg5NSw5Ljg2MDg2MTEyIEMyNS4yNzk5MTE1LDkuODYwOTEwNTUgMjUuMDAwMDQ5NCw5LjU4MTEyODYgMjUsOS4yMzU5NTA2MyBMMjQuOTk5NzgyNyw3LjcxODc5MzEzIEMyNC45OTk3ODI3LDcuNjAwODMxODkgMjQuOTA0NjYxMSw3LjUwNTU1MTk3IDI0Ljc4NzcyNzgsNy41MDU1MjE1OCBMNS4yMTIwOTU4Myw3LjQ5OTk5OTk4IEM1LjA5NTE1NTA2LDcuNDk5OTk5OTggNSw3LjU5NTI4ODY4IDUsNy43MTMyNjAyOCBMNS4wMDAyMTcxOCwyMi4yODY3Mjg0IEM1LjAwMDIxNzE4LDIyLjQwNDcxMTMgNS4wOTUzNzIyMywyMi41IDUuMjEyMzEzMDIsMjIuNSBMMjQuNzg3OTA0MiwyMi41IEMyNC45MDQ4NDUsMjIuNSAyNSwyMi40MDQ3MTEzIDI1LDIyLjI4NjcyODQgTDI1LDEyLjcyNTUzNjggQzI1LDEyLjYwNzU1NCAyNC45MDQ4NDQ5LDEyLjUxMjI2NTMgMjQuNzg3OTA0MiwxMi41MTIyNjUzIEw4LjI3NTIxMzM4LDEyLjUxMjI2NTMgWiIgaWQ9IlN0cm9rZS0xIj48L3BhdGg+PC9nPjwvZz48L3N2Zz4=)}.paypal-btn-3{width:50px;height:14px;margin-left:.3rem}.paypal-logon{display:flex;justify-content:center;align-items:center;margin-top:1rem}.paypal-power-by{line-height:14px;font-size:14px;font-weight:500;color:#7b8388;font-style:italic}@media only screen and (max-width:575px){.complete-order-2{margin-top:.6rem;font-size:16px}.paypal-btn-1{width:61px;height:19px}.paypal-btn-2{width:26px;height:19px}}@media only screen and (min-width:1024px){.complete-order-2{margin-top:.6rem;font-size:16px}.paypal-btn-1{width:61px;height:19px}.paypal-btn-2{width:26px;height:19px}}@media only screen and (min-width:576px) and (max-width:1023px){.complete-order-2{margin-top:1rem;font-size:20px}.paypal-btn-1{width:76px;height:24px}.paypal-btn-2{width:32px;height:24px}}</style>
	<script>var checkoutviewId='',isBackFill=false;</script>
</head>

<body>
	<jsp:include page="../layout/header/header.jsp" flush="true"></jsp:include> 
	<!-- main start -->
		<div class="container">
			<div class="order-header">
				<div class="ml-breadcrumb">
					<div class="ml-breadcrumb-item">Cart</div>
					<div class="ml-breadcrumb-item">•&nbsp;•&nbsp;•</div>
					<div class="ml-breadcrumb-item active">Checkout</div>
					<div class="ml-breadcrumb-item">•&nbsp;•&nbsp;•</div>
					<div class="ml-breadcrumb-item">Payment</div>
				</div>
			</div>
			<div class="order-body">
				<div class="order-left">
					<div class="cart-box">
						<div class="cart-box-title"><span class="order-sort">1</span>SHIPPING ADDRESS</div>
						<div class="cart-box-body">
							<div class="address-box">
								<!-- address id -->
								<input type="hidden" id="addressId" class="form-control" name="addressId">
								<!-- first name -->
								<div class="form-group">
									<div class="form-input form-no-label">
										<label for="addressUserfirstname" class="form-label hide">First Name</label>
										<input type="text" name="addressUserfirstname" id="addressUserfirstname" class="form-control" placeholder="First Name">
									</div>
								</div>
								<!-- last name -->
								<div class="form-group">
									<label for="addressUserlastname" class="form-label hide">Last Name</label>
									<div class="form-input form-no-label">
										<input type="text" name="addressUserlastname" id="addressUserlastname" class="form-control" placeholder="Last Name">
									</div>
								</div>
								<!-- email address -->
								<div class="form-group">
									<label for="addressEmail" class="form-label hide">Email Adress</label>
									<div class="form-input form-no-label">
										<input type="text" name="addressEmail" id="addressEmail" class="form-control" placeholder="Email">
									</div>
								</div>
								<!-- telephone -->
								<div class="form-group">
									<label for="addressTelephone" class="form-label hide">Telephone</label>
									<div class="form-input form-no-label">
										<input type="text" name="addressTelephone" id="addressTelephone" class="form-control" placeholder="Telephone">
									</div>
								</div>
								<!-- address -->
								<div class="form-group" style="width: 100%">
									<label for="addressDetail" class="form-label hide">Address</label>
									<div class="form-input form-no-label">
										<input type="text" name="addressDetail" id="addressDetail" class="form-control" placeholder="Address(Apartment, suite, etc)">
									</div>
								</div>
								<!-- city -->
								<div class="form-group">
									<label for="addressCity" class="form-label hide">City</label>
									<div class="form-input form-no-label">
										<input type="text" name="addressCity" id="addressCity" class="form-control" placeholder="City">
									</div>
								</div>
								<!-- Zip/Postal code -->
								<div class="form-group">
									<label for="addressPost" class="form-label hide">Zip/Postal code</label>
									<div class="form-input form-no-label">
										<input type="text" name="addressPost" id="addressPost" class="form-control" placeholder="Zip/Postal code">
									</div>
								</div>
								<!-- country -->
								<div class="form-group">
									<label for="addressCountry" class="form-label hide">Country</label>
									<div class="form-input">
										<select name="addressCountry" id="addressCountry" class="form-control" value="US"></select>
									</div>
								</div>
								<!-- stateprovinceName -->
								<div class="form-group address-province">
									<label for="addressProvince" class="form-label hide">State/Province</label>
									<div class="form-input">
										<select name="addressProvince" id="addressProvince" class="form-control"></select>
									</div>
								</div>
								<div class="ml-user-policy"><input name="userPolicy" type="checkbox" checked /> I accept&nbsp;<a class="user-policy-item" data-title="Terms of service" href="${APP_PATH}/Policy/TermsConditionsPage.html">Terms of Usage</a>&nbsp;and&nbsp;<a class="user-policy-item" data-title="Privacy Policy" href="${APP_PATH}/Policy/PrivacyPolicyPage.html">Privacy Policy</a>&nbsp;of Megalook.com</div>
							</div>
							<div class="paypal-error-tip hide">
								<span class="icon tip"></span>
								<div class="text">A match of the shipping Address E-mail, Country, State and Postal Code failed.</div>
							</div>
							<div class="order-address-shipping">
								<div class="name">SHIPPING COST:</div>
								<div class="value">$0</div>
							</div>
						</div>
					</div>
					<div class="checkout-order-list"></div>
				</div>

				<div class="order-right">
					<div class="checkout-coupons"><div id="init-loading"></div></div>
					<div class="checkout-pay-method"></div>
					<div class="checkout-buy-msg"></div>
					<div class="checkout-order-cal"></div>
				</div>
			</div>
			<div class="website-policy" style="margin-bottom: 1rem;">
				<a class="website-policy-item" href="${APP_PATH}/Policy/ReturnPolicyPage.html" data-title="Refund policy">Refund policy</a>
				<a class="website-policy-item" href="${APP_PATH}/Policy/ShippingPolicyPage.html" data-title="Shipping policy">Shipping policy</a>
				<a class="website-policy-item" href="${APP_PATH}/Policy/PrivacyPolicyPage.html" data-title="Privacy policy">Privacy policy</a>
				<a class="website-policy-item" href="${APP_PATH}/Policy/TermsConditionsPage.html" data-title="Terms of service">Terms of service</a>
			</div>
		</div>
	</main>
	<!-- main end -->

	<!-- common script -->
	<script src="${APP_PATH}/static/common/jquery.min.js"></script>
	<script>function getCarouselData(area,async,callback){$.ajax({url:"${APP_PATH}/MlbackSlides/getMlbackSlidListByArea",data:JSON.stringify({slideArea:area}),dataType:"json",contentType:"application/json",type:"post",async:async,success:function(data){100==data.code&&callback&&callback(data.extend.mlbackSlideList)}})}function debounce(callback,delay){var timeEnd;delay=delay||100,Date.now()-timeStart>delay&&clearTimeout(timer),timer=setTimeout((function(){callback&&callback()}),delay)}</script>
	<!-- lazy -->
	<script>!function(t,e){"object"==typeof exports?module.exports=e(t):"function"==typeof define&&define.amd?define([],e):t.LazyLoad=e(t)}("undefined"!=typeof global?global:this.window||this.global,function(t){"use strict";function e(t,e){this.settings=o(r,e||{}),this.images=t||document.querySelectorAll(this.settings.selector),this.observer=null,this.init()}"function"==typeof define&&define.amd&&(t=window);const r={src:"data-src",srcset:"data-srcset",selector:".lazyload",root:null,rootMargin:"0px",threshold:0},o=function(){let t={},e=!1,r=0,s=arguments.length;for("[object Boolean]"===Object.prototype.toString.call(arguments[0])&&(e=arguments[0],r++);r<s;r++)!function(r){for(let s in r)Object.prototype.hasOwnProperty.call(r,s)&&(e&&"[object Object]"===Object.prototype.toString.call(r[s])?t[s]=o(!0,t[s],r[s]):t[s]=r[s])}(arguments[r]);return t};if(e.prototype={init:function(){if(!t.IntersectionObserver)return void this.loadImages();let e=this,r={root:this.settings.root,rootMargin:this.settings.rootMargin,threshold:[this.settings.threshold]};this.observer=new IntersectionObserver(function(t){Array.prototype.forEach.call(t,function(t){if(t.isIntersecting){e.observer.unobserve(t.target);let r=t.target.getAttribute(e.settings.src),o=t.target.getAttribute(e.settings.srcset),s=new Image;s.src=r,s.onload=function(e){var s=e.target.width,i=e.target.height;"img"===t.target.tagName.toLowerCase()?(r&&(t.target.src=r),o&&(t.target.srcset=o)):t.target.style.backgroundImage="url("+r+")",t.target.width=s,t.target.height=i,t.target.classList.add("loaded")},s.onerror=function(){}}})},r),Array.prototype.forEach.call(this.images,function(t){e.observer.observe(t)})},loadAndDestroy:function(){this.settings&&(this.loadImages(),this.destroy())},loadImages:function(){if(!this.settings)return;let t=this;Array.prototype.forEach.call(this.images,function(e){let r=e.getAttribute(t.settings.src),o=e.getAttribute(t.settings.srcset);"img"===e.tagName.toLowerCase()?(r&&(e.src=r),o&&(e.srcset=o)):e.style.backgroundImage="url('"+r+"')"})},destroy:function(){this.settings&&(this.observer.disconnect(),this.settings=null)}},t.lazyload=function(t,r){return new e(t,r)},t.jQuery){const r=t.jQuery;r.fn.lazyload=function(t){return(t=t||{}).attribute=t.attribute||"data-src",new e(r.makeArray(this),t),this}}return e});</script>
	<!-- common footer script -->
	<jsp:include page="../common/footer.jsp" flush="true"></jsp:include>
	<!-- header script  -->
	<jsp:include page="../layout/header/header-script.min.jsp" flush="true"></jsp:include>
	<jsp:include page="./add-free-gift.jsp" flush="true"></jsp:include>
	<!-- checkout script -->
	<jsp:include page="./checkout-script.min.jsp" flush="true"></jsp:include>
	<jsp:include page="collect-shipping-address.jsp" flush="true"></jsp:include>
	<script> addTidio(); </script>
</body>
</html>