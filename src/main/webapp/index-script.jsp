<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<script>
	// get display area data
	function getMainBodyData(callback) {
		$.ajax({
			url: '${APP_PATH}/MlbackPageArea/portalSelectPageArea',
			type: "post",
			dataType: "json",
			async: false,
			contentType: 'application/json',
			success: function (data) {
				if (data.code == 100) {
					callback && callback(data.extend.pageArealist);
				}
			}
		});
	}
	// render banner product
	function renderCarouselOrBanner($el, data, idx) {
		var htmlStr = '';
		var len = data.length;
		if (len) {
			if (len == 1) {
				htmlStr = '<a class="main-body-item banner" href="'+ (data[0].pageAreaDetailIfinto ? '${APP_PATH}/' + data[0].pageAreaDetaiLinklUrl : 'javascript:;') +'">' +
					'<img class="lazyload" data-src="'+ data[0].pageAreaDetaiImglUrl +'" />' +
				'</a>';
			} else {
				htmlStr += '<div class="main-body-item slide-'+ idx +'"><div class="swiper-container">' +
					'<div class="swiper-btn swiper-button-next"></div>' +
					'<div class="swiper-btn swiper-button-prev"></div>' +
					'<div class="swiper-pagination"></div>' +
					'<div class="swiper-wrapper">';
						data.forEach(function(item) {
							htmlStr += '<div class="swiper-slide">' +
									'<a href="'+ (item.pageAreaDetailIfinto ? '${APP_PATH}/' + item.pageAreaDetaiLinklUrl : 'javascript:;') +'">' +
										'<img class="lazyload swiper-lazy" data-src="'+ item.pageAreaDetaiImglUrl +'" />' +										
									'</a>' +
								'</div>';
						});
				htmlStr += '</div></div></div>';
			}			
			$el.append($(htmlStr));
			if (len > 1) {
				setTimeout(function() {
					new Swiper(('.slide-'+ idx +' .swiper-container'), {
						freeMode: true,
						pagination: {
							el: '.slide-'+ idx +' .swiper-pagination',
							clickable: true
						},
						navigation: {
							nextEl: '.slide-'+ idx +' .swiper-button-next',
							prevEl: '.slide-'+ idx +' .swiper-button-prev',
						},
						speed: 600,
						loop: true,
						autoplay: { delay: 4e3, },					
						lazy: { loadPrevNext: true, },
					});					
				}, 1000);
			}
		}
	}
	// render activity product
	function renderActivityProduct($el, data, idx) {
		var htmlStr = '';
		if (data.length) {
			htmlStr += '<div class="ml-ap ap-'+ idx +'">';
			data.forEach(function(item, index) {
				htmlStr += '<a class="ml-ap-item" href="'+ (data[0].pageAreaDetailIfinto ? '${APP_PATH}/products/' + data[index].pageAreaDetaiLinklUrl : 'javascript:;') +'">' +
					'<img class="lazyload" data-src="'+ data[index].pageAreaDetaiImglUrl +'" />' +
				'</a>';
			});
			htmlStr += '</div>';
		}
		$el.append($(htmlStr));
	}
	// render carsouel product
	function renderCarouselOrProduct($el, data, idx) {
		var htmlStr = '';
		var len = data.length;
		if (len) {
			var moreLink = data[0].pageAreaDetaiLinklUrl ? ('${APP_PATH}/' + data[0].pageAreaDetaiLinklUrl) : 'javascript:;';
			htmlStr += '<div class="ml-product-slide slide-'+ idx +'">' +
				'<div class="swiper-btn swiper-button-black swiper-button-next"></div>' +
				'<div class="swiper-btn swiper-button-black swiper-button-prev"></div>' +
				'<div class="swiper-pagination"></div>' +
				'<div class="swiper-container"><div class="swiper-wrapper">';
					data.forEach(function(data) {
						var item = data.mlbackProduct;
						var productLink = item.productSeo ? '${APP_PATH}/products/' + item.productSeo : 'javascript:;';
						htmlStr += '<div class="swiper-slide product-item" data-productid="'+ item.productId +'">' +
						    '<span class="product-discount-label'+ (item.productDiscoutimgShow ? ' show' : '') +'" style="background-image: url('+ (item.productDiscoutimgurl || '') +');"></span>' +
							'<div class="product-img">' +
								'<a href="'+ productLink +'" title="'+ item.productName +'" class="lazyload" data-src="'+ item.productMainimgurl +'"></a>' +
							'</div>' +
							'<div class="product-desc">' +
								'<div class="product-name"><a href="'+ productLink +'" title="'+ item.productName +'">'+ item.productName +'</a></div>' +
								getProductReivewsData(item.productReviewnum) +
								'<div class="product-price">' + renderProductPriceHtml(item) +
								'</div>' +
							'</div>' +
						'</div>';
					});

					htmlStr += '<div class="swiper-slide product-item product-more-img">' +
							'<a href="'+ moreLink +'" class="lazyload" data-src="${APP_PATH}/static/pc/img/product-more.jpg""><div class="product-img"></div><div class="product-more-desc"></div></a>' +
						'</div>';

			htmlStr += '</div></div></div>';			
			$el.append($(htmlStr));
			setTimeout(function() {
				new Swiper(('.slide-'+ idx +' .swiper-container'), {
					slidesPerView: 'auto',
					spaceBetween: 5,
					freeMode: true,
					pagination: {
						el: '.slide-'+ idx +' .swiper-pagination',
						clickable: true
					},
					navigation: {
						nextEl: '.slide-'+ idx +' .swiper-button-next',
						prevEl: '.slide-'+ idx +' .swiper-button-prev',
					}
				});				
			}, 1000);
		}
	}
	// get display area data
	function getDisplayAreaData(num, callback) {
		$.ajax({
			url: '${APP_PATH}/MlbackShowArea/getMlbackShowAreaOne',
			data: JSON.stringify({"showareaNumth": num}),
			type: "post",
			dataType: "json",
			contentType: 'application/json',
			success: function (data) {
				if (data.code == 100) {
					callback && callback(data.extend.mlbackShowAreaOne);
				}
			}
		});
	}
	// render showArea
	function renderShowArea($el, data) {
		$el.append($('<div class="showarea-banner lazyload wap" data-src="'+ data.showareaImgurl +'"></div>' +
				'<div class="showarea-banner lazyload pc" data-src="'+ data.showareaImgpcurl +'">'));
	}
	// get review data
	function getIntroduceReviewData(callback) {
		$.ajax({
			url: "${APP_PATH}/MlfrontReview/selectReviewListFrom",
			data: JSON.stringify({ "reviewFrom": 3 }),
			type: "post",
			dataType: 'json',
			contentType: 'application/json',
			success: function (data) {
				if (data.code == 100) {
					callback && callback(data.extend);
				}
			}
		});
	}
	// render introduce reivews
	function renderIntroduceReview($el, data) {
		var htmlStr = '';
		var $reviewList = $('<div class="showaera-review-container" />')
		data.mlfrontReviewList.forEach(function(item, idx) {
			htmlStr += '<div class="showarea-review-item shadow-radius lazyload" data-src="'+ (data.mlfrontReviewImgList[idx][0] && data.mlfrontReviewImgList[idx][0].reviewimgUrl) +'" data-id="'+ item.reviewPid +'">' +
				'<div class="showarea-review-mask"><div class="shop-it">SHOP IT</div></div>' +
			'</div>';
		});
		$reviewList.html(htmlStr);
		$el.append($reviewList);
	}
	// render main-body
	function renderMainBody() {
		getMainBodyData(function(data) {
			var $el = $('#main-body');
			var len = data.length;
			if (len) {
				$el.html('');
				data.forEach(function(item, idx) {
					var index = idx + 1;
					if (item[0]) {
						if (String(item[0].pageAreaDetailType) == '0') {
							renderCarouselOrBanner($el, item, index);
						}
						
						if (String(item[0].pageAreaDetailType) == '1') {
							renderActivityProduct($el, item, index)
						}
	
						if (String(item[0].pageAreaDetailType) == '2') {
							renderCarouselOrProduct($el, item, index)
						}
					}
					if (index == 1) {
						$('#main-body').append($('<div id="countdown-area" style="margin: .5rem;"></div>'));
						getCoundownTimeData(1, rednerCountDownAreaOne);						
					}
				});
			}

			new LazyLoad($el.find('.lazyload'), {
				root: null,
				rootMargin: "10px",
				threshold: 0
			});
		});
	}
	var hasChange = false;
	renderMainBody();

	// customer reviews
	getDisplayAreaData(3, function(data) {
		var $el = $('#showAreaThree');
		data && renderShowArea($el, data);
		getIntroduceReviewData(function(data) {
			data.mlfrontReviewList.length && renderIntroduceReview($el, data);
			new LazyLoad($el.find('.lazyload'), {
				root: null,
				rootMargin: "10px",
				threshold: 0
			});
		});
	});
	$(document.body).on('click', '.showarea-review-item', function(e) {
		var productId = $(this).data('id');
		productId && getOneProductData({ "productId": productId }, function(data) {
			var customerVoiceHtml = '<div class="customer-voice">'+
					'<div class="customer-voice-img lazyload" data-src="'+ data.productMainimgurl +'"></div>'+
					'<div class="customer-voice-name">'+ data.productName +'</div>'+
					'<div class="customer-voice-dprice"><span class="name">Regular Price :</span><span class="value">$'+ data.productOriginalprice.toFixed(2) +'</span></div>'+
					'<div class="customer-voice-nprice"><span class="name">Sale Price :</span><span class="value">$'+ (data.productOriginalprice && data.productActoffoff ? accuracyCal(data.productOriginalprice, data.productActoffoff) : 0) +'</span></div>'+
					'<a class="btn btn-pink" href="'+ (data.productSeo ? ('${APP_PATH}/products/' + data.productSeo) : 'jvascrtip:;') +'">Buy Now</a>'+
				'</div>';
			var customerVoiceModal = createModal({
				header: {
					html: '<p>Customer Voice</p>'
				},
				body: {
					html: customerVoiceHtml
				}
			});
			new LazyLoad(customerVoiceModal.find('.lazyload'), {
				root: null,
				rootMargin: "10px",
				threshold: 0
			});
		});
	});
	$(window).on('resize', function () {
		!hasChange && window.innerWidth > 875 && debounce(function() {
			hasChange = true;
			$('#main-body').html('');
			renderMainBody();
		});
	});
</script>