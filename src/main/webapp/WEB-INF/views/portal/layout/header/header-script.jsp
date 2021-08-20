<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<script>
	// get one product data
	function getOneProductData(reqData, callback) {
		$.ajax({
			url: '${APP_PATH}/MlbackProduct/getOneProductSimple',
			data: JSON.stringify(reqData),
			type: "post",
			dataType: 'json',
			contentType: 'application/json',
			success: function(data) {
				if (data.code == 100) {
					callback && callback(data.extend.mlbackProductOne)
				} else {
					sysModalTip();
				}
			},
			error: function() {
				sysModalTip();
			}
		});
	}
	function matchYoutubeUrl(url) {
	    return url.split("watch?v=")[1];
   	}
	// render video data
	function renderVideoData(data, videoLink) {
		var videoRecommendHtml = '<div class="video-recommend">'+
				'<iframe frameborder="0" allowfullscreen="1" allow="autoplay; encrypted-media" title="YouTube video player" width="100%" height="300" src="'+ videoLink +'"></iframe>' +
				'<div class="video-recommend-product">'+
					'<div class="video-recommend-img lazyload" data-src="'+ data.productMainimgurl +'"></div>'+
					'<div class="video-recommend-data">' +
						'<div class="video-recommend-name">'+ data.productName +'</div>'+
						'<div class="video-recommend-dprice"><span class="">Regular Price :</span><span class="value">$'+ data.productOriginalprice.toFixed(2) +'</span></div>'+
						'<div class="video-recommend-nprice"><span class="name">Sale Price :</span><span class="value">$'+ (data.productOriginalprice && data.productActoffoff ? accuracyCal(data.productOriginalprice, data.productActoffoff) : 0.00) +'</span></div>'+
						'<a class="btn btn-pink" href="'+ (data.productSeo ? ('${APP_PATH}/' + data.productSeo +'.html') : 'jvascrtip:;') +'">Buy Now</a>'+
					'</div>'+
				'</div>' +
			'</div>';
		var videoRecommendModal = createModal({
			header: {
				html: '<p>Hot Video Recommend...</p>'
			},
			body: {
				html: videoRecommendHtml
			}
		});
		new LazyLoad(videoRecommendModal.find('.lazyload'), {
			root: null,
			rootMargin: "10px",
			threshold: 0
		});
	}
	// get Label Class
	function getLabelClass(id) {
		if (id == 1) {
			return "label-hot";
		} else if (id == 2) {
			return "label-new";
		} else if (id == 3) {
			return "label-sale";
		} else if (id == 4) {
			return "label-doubledrawn";
		} else if (id == 9) {
			return "label-15a";
		} else if (id == 10) {
			return "label-hot-test";
		}
		return "";
	}
	// render nav
	function renderMainCategory(parentEl, data1, data2) {
		var menuHhtml = "";
		for (var i = 0; i < data1.length; i++) {
			var labelClass = getLabelClass(data1[i].catalogLable);
			var secondNavSeo;
			// product
			if (data1[i].catalogIfproorcateorpage == 0) {				
				secondNavSeo = data1[i].catalogSeoname;
			}
			// collection
			if (data1[i].catalogIfproorcateorpage == 1) {
				secondNavSeo = 'search/' + data1[i].catalogCateseoname;
			}
			// subject
			if (data1[i].catalogIfproorcateorpage == 2) {
				secondNavSeo = data1[i].catalogPageseoname;
			}
			menuHhtml += '<li class="menu-item label ' + labelClass + '">';
			menuHhtml += '<a href="' + (data1[i].catalogIfinto ? '${APP_PATH}/' + secondNavSeo + '.html' : 'javascript:;') + '">' + data1[i].catalogName + '</a>';
			if (data2 && data2.length > 0 && data2[i] && data2[i].length > 0) {
				var subMenuHtml = '',
					isWrap = true,
					subMenuLen = data2[i].length;
				if (subMenuLen) menuHhtml += '<div class="operate wap-op-one"><i class="icon plus"></i></div>';

				for (var j = 0; j < subMenuLen; j += 1) {
					if (data2[i][j] && data2[i][j].length > 0) {
						subMenuHtml += '<dl class="sub-menu-item">';
						var thirdMenuLen = data2[i][j].length;
						for (var k = 0; k < thirdMenuLen; k += 1) {
							var labelClass = getLabelClass(data2[i][j][k].catalogLable);
							var thirdNavSeo;

							// product
							if (data2[i][j][k].catalogIfproorcateorpage == 0) {			
								thirdNavSeo = data2[i][j][k].catalogSeoname;
							}
							// collection
							if (data2[i][j][k].catalogIfproorcateorpage == 1) {
								thirdNavSeo = 'search/'+ data2[i][j][k].catalogCateseoname;
							}
							// subject
							if (data2[i][j][k].catalogIfproorcateorpage == 2) {
								thirdNavSeo = data2[i][j][k].catalogPageseoname;
							}
						
							if (k == 0) {
								subMenuHtml += '<dt>' +
									'<a href="'+ (data2[i][j][k].catalogIfinto ? '${APP_PATH}/' + thirdNavSeo + '.html' : 'javascript:;') +'">' + data2[i][j][k].catalogName + '</a>' +
									(thirdMenuLen > 1 ? '<div class="operate wap-op-two"><i class="icon plus"></i></div>' : '') + '</dt>';
							} else {
								isWrap = false;
								subMenuHtml += '<dd><a href="'+ (data2[i][j][k].catalogIfinto ? '${APP_PATH}/' + thirdNavSeo + '.html' : 'javascript:;') +'">' + data2[i][j][k].catalogName + '</a></dd>';
							}
						}
						subMenuHtml += '</dl>';
					}
				}

				menuHhtml += '<div class="sub-menu-container"><div class="sub-menu' + (isWrap ? " nowrap" : "") + '">' + subMenuHtml + '</div></div>';
			}
			menuHhtml += '</li>';
		}
		parentEl.html(menuHhtml);
	}
	// listner event throttle
	function addNavEvent() {
		var timer = null;
		$('.pc-nav .menu-item').on('mouseenter', function (e) {
			var $this = $(this);
			timer = setTimeout(function () {
				enterEvent($this, e);
			}, 300);
		}).on('mouseleave', function (e) {
			var $this = $(this);
			clearTimeout(timer);
			$this.find('.sub-menu-container').removeClass('active');
			$this.find('a').removeClass('active');
		});
	}
	function addWapNavEvent() {
		$('#menu').on('click', function () {
			$('.wap-nav-box').addClass('active');
			addFixed();
			$('.lottery').length && $('.lottery').addClass('hide');
		});
		$('.wap-nav-box').on('click', function (e) {
			if (this == e.target) {
				$('.wap-nav-box').removeClass('active');
				removeFixed();
			}
		});
		$('.wap-nav-box .close').on('click', function () {
			$('.wap-nav-box').removeClass('active');
			removeFixed();
			$('.lottery').length && $('.lottery').removeClass('hide');
		});
		// dropdwon menu
		$('.wap-op-one').click(function (e) {
			e.stopPropagation();
			var $this = $(this);
			var str = $this.next('.sub-menu-container').css('display');
			if (str == 'none') {
				$this.next('.sub-menu-container').show(200);
				$this.find('.icon').addClass('sub').removeClass('plus');
			} else {
				$this.next('.sub-menu-container').hide(200);
				$this.find('.icon').removeClass('sub').addClass('plus');
			}
			$this.parent('.menu-item')
				.siblings('.menu-item')
				.find('.wap-op-one .sub').removeClass('sub').addClass('plus')
				.parent().next('.sub-menu-container').hide(200)
				.find('dd').hide()
				.parent().find('.wap-op-two .sub').removeClass('sub').addClass('plus');
		});
		$('.wap-op-two').click(function (e) {
			e.stopPropagation();
			var $this = $(this);
			if ($this.find('.icon').hasClass('sub')) {
				$this.find('.icon').removeClass('sub').addClass('plus').parents('dl').find('dd').hide(200);
			} else {
				$('.wap-op-two .sub').removeClass("sub").addClass('plus').parents('dl').find('dd').hide(200);
				$this.find('.icon').addClass('sub').removeClass('plus').parents("dl").find('dd').show(200);
			}
		});
	}
	// mouse enter event
	function enterEvent($this, e) {
		var targetEl = $this.find('.sub-menu-container'),
			outerWidth = 0,
			clientRect = $this[0].getBoundingClientRect(),
			navulClientRect = $('.pc-nav .ml-nav')[0].getBoundingClientRect(),
			offsetWidth = window.innerWidth > navulClientRect.width ? navulClientRect.width : window.innerWidth,
			offsetLeft = clientRect.left - navulClientRect.left,
			offsetBottom = clientRect.bottom - navulClientRect.top;
		targetEl.addClass('active');
		outerWidth = targetEl.outerWidth();
		if (outerWidth >= offsetWidth) {
			targetEl.css({
				'width': offsetWidth + 'px',
				'left': '0' + 'px',
				'top': offsetBottom + 'px'
			});
		} else {
			if (offsetLeft < (outerWidth - clientRect.width) / 2 && (offsetLeft + clientRect.width / 2 < offsetWidth / 2)) {
				targetEl.css({
					'left': '0px',
					'top': offsetBottom + 'px'
				});
			} else if (offsetLeft + clientRect.width + (outerWidth - clientRect.width) / 2 >= offsetWidth && (offsetLeft + clientRect.width / 2 > offsetWidth / 2)) {
				targetEl.css({
					'left': 'unset',
					'right': '0' + 'px',
					'top': offsetBottom + 'px'
				});
			} else {
				var defaultLeft = offsetLeft - (outerWidth - clientRect.width) / 2;
				targetEl.css({
					'left': defaultLeft + 'px',
					'top': offsetBottom + 'px'
				});
			}
		}
		$this.find('a').addClass('active');
	}
	// ajax fetch nav data
	function getNavMenuData(async, callback) {
		$.ajax({
			url: '${APP_PATH}/MlbackCatalog/getCatalogSuperMenu',
			method: 'post',
			async: async,
			success: function (data) {
				if (data.code == 100) {
					callback && callback(data.extend);
				} else {
					mlModalTip('Failed to get to the relevant data !');
				}
			},
			error: function(err) {
				sysModalTip();
			}
		});
	}
	// ajax fetch productNum in the cart
	function updateProructNumberInCart() {
		$.ajax({
			url: '${APP_PATH}/MlbackCart/getCartProductNumber',
			method: 'post',
			success: function (data) {
				if (data.code == 100) {
					$('.cart .num').html(data.extend.number || 0);
				}
			}
		});
	}
	// render introduce search
	function renderIntroduceSearch() {
		var data = ["Transparent Lace", "Balayage", "Undetectable", "Headband Wig", "Ombre", "#4 Color", "Highlight", "Curly Wig", "Closure Wig", "360 Lace", "Piano Color", "Glueless Wig", "Bob Wig", "Ginger", "613 Blonde", "Short Cut", "New Arrival", "U part", "Water Wave", "Bundles", "Double Drawn Hair", "Thick From Top To End"];
		var searchRsultHtml = '';
		data.forEach(function(item, idx) {
			searchRsultHtml += '<li class="search-result-item" data-name="'+ item +'">'+ item +'</li>';
		});
		$('.search-result').html(searchRsultHtml);
	}
	// check user login or not
	function checkUserLoginOrNot() {
		var flag = false;
		$.ajax({
			url: "${APP_PATH}/MlfrontUser/ifLogin",
			type: 'post',
			async: false,
			success: function (data) {
				if (data.code == 100) {
			 		flag = data.extend.ifLogin ? true : false;					
				}
			}
		});
		return flag;
	}
	// update user login status fn
	function updateUserLoginStatus() {
		var loginStatus = checkUserLoginOrNot();
	 	var $pserson = $('.icon.person');
		if (loginStatus && !$pserson.hasClass('active')) $pserson.addClass('active');
		
	 	if (!loginStatus && $pserson.hasClass('active')) $person.removeClass('active');
	}
	// login register fn
	function registerFn(reqData, callback) {
		payLoading();
		$.ajax({
			url: "${APP_PATH }/MlfrontUser/register",
			type: 'post',
			data: JSON.stringify(reqData),
			dataType: 'json',
			contentType: 'application/json',
			success: function (data) {
				if (data.extend.registerYes) {
					mlModalTip('Registered successfully !');
					 callback && callback();
				} else {
					mlModalTip(data.extend.resMsg);
				}
			},
			error: function() {
				sysModalTip();
			},
			complete: function() {
				hidePayLoading();
			}
		});
	}
	
	function forgetFn(reqData, callback) {
		payLoading();
		$.ajax({
			url: "${APP_PATH}/MlfrontUser/reSetPwd",
			type: 'post',
			data: JSON.stringify(reqData),
			dataType: 'json',
			contentType: 'application/json',
			success: function (data) {
				if (data.code == 100) {
					mlModalTip('Password change successfully !');
					 callback && callback();
				} else {
					mlModalTip('Password change failed !');
				}
			},
			error: function() {
				sysModalTip();
			},
			complete: function() {
				hidePayLoading();
			}
		});
	}

	function loginFn(reqData, callback) {
		payLoading();
		$.ajax({
			url: "${APP_PATH }/MlfrontUser/login",
			type: 'post',
			data: JSON.stringify(reqData),
			dataType: 'json',
			contentType: 'application/json',
			success: function (data) {
				if (data.extend.loginYes) {
					mlModalTip('Login successfully !');
					callback && callback();
				} else {
					mlModalTip(data.extend.resMsg);
				}
			},
			error: function() {
				sysModalTip();
			},
			complete: function() {
				hidePayLoading();
			}
		});
	}
	// login-register box	
	function loginRegisterBox () {		
		var loginRegisterHtml = '<div class="left-box">' +
				'<div class="tab">' +
					'<div class="tab-item active" data-id="signin">Sign In</div>' +
					'<div class="tab-item" data-id="register">Register</div>' +
				'</div>' +
				'<div class="tab-pane signin active">' +
					'<form id="signin">' +
						'<div class="input-group">' +
							'<label for="suserEmail">E-mail</label>' +
							'<input type="email" id="suserEmail" name="userEmail" placeholder="eamil" required>' +
						'</div>' +
						'<div class="input-group">' +
							'<label for="suserPassword">Password</label>' +
							'<input type="password" id="suserPassword" name="userPassword" placeholder="password at least six figures">' +
						'</div>' +
					'</form>' +
					'<div class="btn-group">' +
						'<a href="javascript:;" class="btn btn-black sigin">SIGN IN</a>' +
					'</div>' +
					'<p class="forget-password"><a id="forget-password">Forget PassWord?</a></p>' +
				'</div>' +
				'<div class="tab-pane register">' +
					'<form id="register">' +
						'<div class="input-group">' +
							'<label for="ruserEmail">E-mail</label>' +
							'<input type="email" id="ruserEmail" name="userEmail" placeholder="please input eamil" required>' +
						'</div>' +
						'<div class="input-group">' +
							'<label for="ruserPassword">Password</label>' +
							'<input type="password" id="ruserPassword" name="userPassword" placeholder="password at least six figures">' +
						'</div>' +
						'<div class="input-group">' +
							'<label for="rConfirmPassword">ConfirmPassword</label>' +
							'<input type="password" id="rConfirmPassword" name="ConfirmPassword" placeholder="ConfirmPassword">' +
						'</div>' +
					'</form>' +
					'<div class="ml-user-policy"><input name="userPolicy" type="checkbox" checked /> I accept&nbsp;<a class="user-policy-item" data-title="Terms of service" href="${APP_PATH}/Policy/TermsConditionsPage.html">Terms of Usage</a>&nbsp;and&nbsp;<a class="user-policy-item" data-title="Privacy Policy" href="${APP_PATH}/Policy/PrivacyPolicyPage.html">Privacy Policy</a>&nbsp;of Megalook.com</div>' +
					'<div class="btn-group">' +
						'<a href="javascript:;" class="btn btn-black register"> REGISTER </a>' +
						'<a href="javascript:goToIndex();" class="btn btn-pink">Go Home</a>' +
					'</div>' +
				'</div>' +
			'</div>' +
			'<div class="right-box">' +
				'<form id="forget-password">' +
					'<div class="input-group">' +
						'<label for="fuserEmail">E-mail</label>' +
						'<input type="email" id="fuserEmail" name="userEmail" placeholder="please input eamil" required>' +
					'</div>' +
					'<div class="input-group">' +
						'<label for="fuserPassword">Password</label>' +
						'<input type="password" id="fuserPassword" name="userPassword" placeholder="password at least six figures">' +
					'</div>' +
					'<div class="input-group">' +
						'<label for="fConfirmPassword">ConfirmPassword</label>' +
						'<input type="password" id="fConfirmPassword" name="ConfirmPassword" placeholder="ConfirmPassword">' +
					'</div>' +
				'</form>' +
				'<div class="btn-group">' +
					'<a href="javascript:;" class="btn btn-black forget-password"> Confirm </a>' +
				'</div>'
			'</div>';
		loginRegisterModal = createModal({
			header: {
				html: '<p>MegaLook</p>'
			},
			body: {
				html: loginRegisterHtml
			}
		});
		loginRegisterModal.addClass('login-register');
	}
	/* render header ad */
	function renderHeaderAd(data) {
		var adSeo, adLink;
		if (data.slideIfproorcateorpage == 0) {				
			adSeo = data.slideSeoname;
		}
		// collection
		if (data.slideIfproorcateorpage == 1) {
			adSeo = 'search/' + data.slideCateseoname;
		}
		// subject
		if (data.slideIfproorcateorpage == 2) {
			adSeo = data.slidePageseoname;
		}
		adLink = data.slideIfinto ? '${APP_PATH}/' + adSeo + '.html' : 'javascript:;';
		data.slideWapstatus && $('.wap-header .ml-ad').html('<a class="lazyload" style="background-size: cover;" href="'+ adLink +'" data-src="'+ data.slideWapimgurl +'"></a>');
		data.slidePcstatus && $('.pc-header .ml-ad').html('<a class="lazyload" href="'+ adLink +'" data-src="'+ data.slidePcimgurl +'"></a>');
		// lazyload
		new LazyLoad($('.ml-ad .lazyload'), {
			root: null,
			rootMargin: "10px",
			threshold: 0
		});
	}
	// go to search product
	function goToSearchProduct(searchName) {
		window.location.href = '${APP_PATH}/MlbackProduct/toSearchPage?searchProductName=' + searchName.replace('#','');
	}
	// check input search value
	function checkSearchInput(value) {
		if (!value.trim()) {
			mlModalTip('The search content cannot be empty !');
			return false;
		}
		return true;
	}
	// refresh page or not
	function refreshPage() {
		var currentUrl = window.location.href;
		(currentUrl == '${APP_PATH}/MlbackCart/toCheakOut') && (window.location.href = currentUrl);
	}
	// iphone share
	function initIphoneShare() {
		var isIOS = navigator.userAgent && navigator.userAgent.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/);
		isIOS ? ($('.iphone-share').removeClass('hide'),$('.iphone-unshare').addClass('hide')) : ($('.iphone-share').addClass('hide'),$('.iphone-unshare').removeClass('hide'));
	}

	// initial ml-ad
	getCarouselData(5, true, function(data) {
		var hData = data[0]
		hData && renderHeaderAd(hData);
		// main padding-top
		$('main').css({ 'paddingTop': $('header').height() });
	});
	// initial header nav
	getNavMenuData((window.innerWidth > 1024 ? false : true), function (data) {
		renderMainCategory($('.ml-nav'), data.CatalogFirstList, data.MlbackCatalogSuperList);
		// init iphone share
		initIphoneShare();
		// main padding-top
		$('main').css({ 'paddingTop': $('header').height() });
		// pc event
		addNavEvent();
		// wap event;
		addWapNavEvent();
	});
	// initial cart num
	updateProructNumberInCart();
	// initial login status
	updateUserLoginStatus();
	// listener search event
	function showSearchBox() {
		addFixed();
		$('.search-result-box').addClass('active').slideDown(300);
		if (window.innerWidth < 1023) {
			$('.wap-navbar .search-box').show();
			$('.search-result-box').css('top', $('.wap-navbar .search-box').outerHeight());
		} else {
			$('.search-result-box').css('top', $('.pc-header').outerHeight() - $('.pc-nav').height());
		}
		renderIntroduceSearch();
	}
	$('.pc-header .search-input, #search-product').on('click', function() {
		setTimeout(function() {
			$('#.search-input').focus();
		});
		showSearchBox();
	});
	// close search-result box
	$('.search-result-box').on('click', function (e) {
		if (e.target == this) {
			removeFixed();
			if (window.innerWidth < 1023) {
				$('.wap-navbar .search-box').slideUp(300);
			}
			$(this).removeClass('active').slideUp(300);				
		}
	});
	$('.search-result-box .close').on('click', function () {
		removeFixed();
		if (window.innerWidth < 1023) {
			$('.wap-navbar .search-box').slideUp();
		}
		$('.search-result-box').removeClass('active').slideUp();
	});
	// search product
	$('.btn-search').on('click', function() {
		var searchName = $(this).parent().find('.search-input').val();
		checkSearchInput(searchName) && goToSearchProduct(searchName);
	});
	$('.search-input').on('keyup', function(e) {
		if (e.keyCode == 13) {
			var searchName = $('.pc-header .search-input').val() || $('.wap-header .search-input').val();
			checkSearchInput(searchName) && goToSearchProduct(searchName);
		}
	});
	$(document.body).on('click', '.search-result-item', function() {
		var searchName = '' + $(this).data('name');
		checkSearchInput(searchName) && goToSearchProduct(searchName);
	});
	var startY = 0,
		loginRegisterModal = null;
	$(window).on('scroll', function () {
		var currentY = window.pageYOffset;
		var isPc = window.innerWidth > 1024;
		if (currentY >= startY && currentY > 60 && isPc) {
			!$('.ml-search').hasClass('down') && ($('.ml-search').addClass('hide down').removeClass('up'), $('main').css({ 'paddingTop': $('header').height() }));
		}

		if (currentY < startY && currentY < 60 && isPc) {
			!$('.ml-search').hasClass('up') && ($('.ml-search').removeClass('down').removeClass('hide').addClass('up'), $('main').css({ 'paddingTop': $('header').height() }));
		}
		startY = currentY;
	});
	$(window).on('resize', function () {
		debounce(function() {
			$('main').css({ 'paddingTop': $('header').height() });
			if ($('.search-result-box').hasClass('active')) {
				showSearchBox();
			}
			// reset iphone share
			initIphoneShare();
		}, 100);
	});
	// iphone share
	$('#iphone-share .share').on('click', function() {
		var $iphoneAdvice = $('.iphone-advice');
		if (!$iphoneAdvice.length) {
			$iphoneAdvice = $('<div class="iphone-advice"><img src="${APP_PATH}/static/pc/img/iphone-advice.gif" /><span class="icon close"></span></div>');
			$(document.body).append($iphoneAdvice);
			addFixed();
		}
		$iphoneAdvice.show();
	});
	$(document.body).on('click', '.iphone-advice .close', function() {
		$('.iphone-advice').hide();
		removeFixed();
	});
	// login-register
	$('.icon.person').on('click', function() {
		if ($(this).hasClass('active')) {
			goToUserCenter();
			return ;
		} else {
			loginRegisterBox();
		}
	});
	// login-register tab event
	$(document.body).on('click', '.login-register .tab-item', function() {
		if (!$(this).hasClass('active')) {
			$(this).addClass('active').siblings().removeClass('active');
			$('.tab-pane.'+ $(this).data('id')).addClass('active').siblings().removeClass('active');
		}
	});
	// tab forget-password box
	$(document.body).on('click', '#forget-password', function() {
		$('.left-box').hide();
		$('.right-box').show();
	});
	// login event
	$(document.body).on('click', '.btn.sigin', function () {
		var email = $('#signin input[name=userEmail]').val();
		var password = $('#signin input[name=userPassword]').val();

		if (!email || !emailPattern.test(email)) {
			mlModalTip('It is illegal to enter an email address !');
			return ;
		}

		if (password.length < 6) {
			mlModalTip('The password entered is invalid !');
			return ;
		}

		loginFn({
			'userEmail': email,
			'userPassword': password
		}, function () {
			removeModal(loginRegisterModal);
			updateProructNumberInCart();
			$('.icon.person').addClass('active');
			refreshPage();
		});
	});
	// register event
	$(document.body).on('click', '.btn.register', function () {
		var email = $('#register input[name=userEmail]').val();
		var password = $('#register input[name=userPassword]').val();
		var password2 = $('#register input[name=ConfirmPassword]').val();

		if (!email || !emailPattern.test(email)) {
			mlModalTip('It is illegal to enter an email address !');
			return ;
		}

		if (password.length < 6) {
			mlModalTip('The password entered is invalid !');
			return ;
		}

		if (!password2.length) {
			mlModalTip('Please enter a confirmation password !');
			return ;
		}

		if (password2.length < 6) {
			mlModalTip('The confirm password entered is invalid !');
			return ;				
		}

		if (password != password2) {
			mlModalTip('Twice the password is double, please re-enter the password !');
			$('#register input[name=ConfirmPassword]').val('');
			return ;				
		}

		registerFn({
			'userEmail': email,
			'userPassword': password
		}, function() {
			removeModal(loginRegisterModal);
			updateProructNumberInCart();
			$('.icon.person').addClass('active');
			refreshPage();
			setTimeout(function() {
				mlModalTip('The new account has been automatically logged in !');
			}, 2000);
			fbq('track', 'CompleteRegistration');
			pintrk('track', 'signup');
			snaptr('track', 'SIGN_UP');
		});
	});
	// forget password event
	$(document.body).on('click', '.btn.forget-password', function () {
		var email = $('#forget-password input[name=userEmail]').val();
		var password = $('#forget-password input[name=userPassword]').val();
		var password2 = $('#forget-password input[name=ConfirmPassword]').val();

		if (!email || !emailPattern.test(email)) {
			mlModalTip('It is illegal to enter an email address !');
			return ;
		}

		if (password.length < 6) {
			mlModalTip('The password entered is invalid !');
			return ;
		}

		if (!password2.length) {
			mlModalTip('Please enter a confirmation password !');
			return ;
		}

		if (password2.length < 6) {
			mlModalTip('The confirm password entered is invalid !');
			return ;				
		}

		if (password != password2) {
			mlModalTip('Twice the password is double, please re-enter the password !');
			$('#register input[name=ConfirmPassword]').val('');
			return ;				
		}

		forgetFn({
			'userEmail': email,
			'userPassword': password
		}, function() {
			$('.left-box').show();
			$('.right-box').hide();
		});
	});
</script>
<script>
$(document.body).on('click', '.user-policy-item', function(e) {
	var $this = $(this), policyModalHtml = '<iframe src="'+ e.target.href +'"><iframe>', policyModal;
	e.preventDefault();
	policyModal = createModal({
		header: {
			html: '<p>'+ $(this).data('title') +'</p>'
		},
		body: {
			html: policyModalHtml,
		}
	});
	policyModal.addClass('policy-modal');
});
</script>