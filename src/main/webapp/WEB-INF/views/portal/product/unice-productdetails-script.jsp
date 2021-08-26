<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<script>
	function addHeaderInfo(productData) {
		var productNameStr = productData.productName,
			productMetaDescStr = productData.productMetaDesc,
			productSeoStr = productData.productSeo,
			urlStr = '${ map.website_domain }/products/' + productSeoStr,
			imageStr = productData.productMainimgurl,
			amountStr =  accuracyCal(productData.productOriginalprice, productData.productActoffoff),
			productIdStr = productData.productId;
		var meta = $('<meta property = "og:title" content ="' + productNameStr + '">' +
			'<meta property = "og:description" content = "' + productMetaDescStr + '" >' +
			'<meta property = "og:url" content = "' + urlStr + '" >' +
			'<meta property = "og:image" content = "' + imageStr + '" >' +
			'<meta property = "product:brand" content = "MegaLook" >' +
			'<meta property = "product:availability" content = "in stock" >' +
			'<meta property = "product:condition" content = "new" > ' +
			'<meta property = "product:price:amount" content = "' + amountStr + '" >' +
			'<meta property = "product:price:currency" content = "USD" >' +
			'<meta property = "product:retailer_item_id" content = "' + productIdStr + '" >' +
			'');
		$(document.head).append(meta);
	}
</script>
<script>!function(a){a.fly=function(b,c){var d={version:"1.0.1",autoPlay:!0,vertex_Rtop:20,speed:1.2,start:{},end:{},onEnd:a.noop},e=this,f=a(b);e.init=function(a){this.setOptions(a),!!this.settings.autoPlay&&this.play()},e.setOptions=function(b){this.settings=a.extend(!0,{},d,b);var c=this.settings,e=c.start,g=c.end;f.css({marginTop:"0px",marginLeft:"0px",position:"fixed"}).appendTo("body"),null!=g.width&&null!=g.height&&a.extend(!0,e,{width:f.width(),height:f.height()});var h=Math.min(e.top,g.top)-Math.abs(e.left-g.left)/3;h<c.vertex_Rtop&&(h=Math.min(c.vertex_Rtop,Math.min(e.top,g.top)));var i=Math.sqrt(Math.pow(e.top-g.top,2)+Math.pow(e.left-g.left,2)),j=Math.ceil(Math.min(Math.max(Math.log(i)/.05-75,30),100)/c.speed),k=e.top==h?0:-Math.sqrt((g.top-h)/(e.top-h)),l=(k*e.left-g.left)/(k-1),m=g.left==l?0:(g.top-h)/Math.pow(g.left-l,2);a.extend(!0,c,{count:-1,steps:j,vertex_left:l,vertex_top:h,curvature:m})},e.play=function(){this.move()},e.move=function(){var b=this.settings,c=b.start,d=b.count,e=b.steps,g=b.end,h=c.left+(g.left-c.left)*d/e,i=0==b.curvature?c.top+(g.top-c.top)*d/e:b.curvature*Math.pow(h-b.vertex_left,2)+b.vertex_top;if(null!=g.width&&null!=g.height){var j=e/2,k=g.width-(g.width-c.width)*Math.cos(j>d?0:(d-j)/(e-j)*Math.PI/2),l=g.height-(g.height-c.height)*Math.cos(j>d?0:(d-j)/(e-j)*Math.PI/2);f.css({width:k+"px",height:l+"px","font-size":Math.min(k,l)+"px"})}f.css({left:h+"px",top:i+"px"}),b.count++;var m=window.requestAnimationFrame(a.proxy(this.move,this));d==e&&(window.cancelAnimationFrame(m),b.onEnd.apply(this))},e.destroy=function(){f.remove()},e.init(c)},a.fn.fly=function(b){return this.each(function(){void 0==a(this).data("fly")&&a(this).data("fly",new a.fly(this,b))})}}(jQuery);</script>
<script>
	// imagezoom
	(function($) {
	    $.fn.imagezoom = function(options) {
	        var settings = {
	            xzoom: 300,
	            yzoom: 300,
	            scale: 3
	        };
	        var self = this;
	        if (options) {
	            $.extend(settings, options);
	        }
	        $(this).bind("mouseover",  function(ev) {
	            var imageLeft = $(this).offset().left;
	            var imageTop = $(this).offset().top;
	            var imageWidth = $(this).get(0).offsetWidth;
	            var imageHeight = $(this).get(0).offsetHeight;
	            var boxLeft = $(this).parent().offset().left;
	            var boxTop = $(this).parent().offset().top;
	            var boxWidth = $(this).parent().width();
	            var boxHeight = $(this).parent().height();
	            var imageUrl = $(this).attr("rel");
	
	            if ($("div.zoomDiv").get().length == 0) {
	                $(document.body).append("<div class='zoomDiv'><img class='bigimg' src='" + imageUrl + "'/></div>");
	            }
	
	            $(this).css('cursor', 'crosshair');
	
	            $("div.zoomDiv").css({ top: boxTop, left: (boxLeft + boxWidth) });
	            $("div.zoomDiv").width(settings.xzoom);
	            $("div.zoomDiv").height(settings.yzoom);
	            $("div.zoomDiv .bigimg").width(settings.xzoom * settings.scale);
	            $("div.zoomDiv .bigimg").height(settings.yzoom * settings.scale);
	            $("div.zoomDiv").show();
	
	            $(document.body).mousemove(function(e) {
	                var mouse = {
	                		x: e.pageX,
	                		y: e.pageY
	                	};
	                if (mouse.x < imageLeft || (mouse.x > imageLeft + imageWidth) || mouse.y < imageTop || (mouse.y > imageTop + imageHeight)) {
	                    mouseOutImage();
	                } else {                	
	                	var bigWidth = $(".bigimg").get(0).offsetWidth;
	                	var bigHeight = $(".bigimg").get(0).offsetHeight;
	                	var scalex = boxWidth / (bigWidth - settings.xzoom);
	                	var scaley = boxHeight / (bigHeight - settings.yzoom);
	                	var xposs = mouse.x - imageLeft;
	                	var yposs = mouse.y - imageTop;
	                	
	                	$("div.zoomDiv").get(0).scrollLeft = xposs / scalex;
	                	$("div.zoomDiv").get(0).scrollTop = yposs / scaley;
	                }
	            });
	        });
	        function mouseOutImage() {
	            $(document.body).unbind("mousemove");
	            $("div.zoomDiv").remove();
	        }
	    }
	})(jQuery);
	// fly bubble
	function generateFlyBubble(event, callback) {
		$('<img class="ml-flyer" src="'+ mainUrl +'" />').fly({
		    start:{
		    	left: event.pageX - window.pageXOffset,
				top: event.pageY - window.pageYOffset
		    },
		    end:{
		        left: window.innerWidth - 30,
		        top: 150,
		        width: 0,
		        height: 0,
		    },
		    onEnd: function() {
		    	this.destroy();
		    	callback();
		    }
		});
	}
	// update product details attr-data
       function updateProductData(selectedKeys) {
       	var productQty = mapSet[selectedKeys.join(',')];
           var productSku = mapItems[selectedKeys.join(',')] || {};
           if (!Object.keys(mapSet).length || !Object.keys(mapItems).length) {
           	mlModalTip("I'm sorry that the product cannot be purchased for the time being. If necessary, please contact customer service !");
           	return;
           }
           /* if (parseInt($('.product-qty .product-num').val()) > productQty.count) {
           	$('.product-qty .product-num').val(productQty.count);
           	mlModalTip('Under the current options can buy at most <i style="font-weight: blold">'+ productQty.count +'</i> product !');
           } */
          	$('.product-qty .product-num').data('count', productQty.count).data('productsku', productSku);
          	var productSkuMoney = productSku.productskuMoney
          	productSkuMoney ? updateProductPrice(productSkuMoney) : updateProductPrice();
       }
	// update product price
       function updateProductPrice(price) {
       	var data = $('.product-details').data('product');
		function getSkuPrice() {
			return price ? (parseFloat(price) ? parseFloat(price) : 0) : 0
		}
      		$('.product-define-price').text('$'+ (data.productPrice + getSkuPrice()).toFixed(2));
      		$('.product-now-price').text('$'+ accuracyCal((data.productPrice + getSkuPrice()), data.productDiscount));
       }
	// get product data
	function getProductData () {
		var productData = {};
		var productDetails = $('.product-details').data('product') || {};
		var productSKu = $('.product-qty .product-num').data('productsku');

		if (!productSKu || !Object.keys(productSKu).length) return false;

		productData["cartitemProductId"] = productDetails.proudctId;
		productData["cartitemProductSeoName"] = productDetails.productSeo;
		productData["cartitemProductName"] = productDetails.productName;
		productData["cartitemProductOriginalprice"] = productDetails.productPrice;
		productData["cartitemProductMainimgurl"] = productDetails.productMainImgUrl;
		productData["cartitemProductActoff"] = productDetails.productDiscount;

		productData["cartitemProductskuIdstr"] = optionIdArr.join(',');
		productData["cartitemProductskuIdnamestr"] = Object.keys(optionObj).join(',');

		productData["cartitemProductskuId"] = productSKu.productskuId;
		productData["cartitemProductskuName"] = productSKu.productskuName;
		productData["cartitemProductskuCode"] = productSKu.productskuCode;
		productData["cartitemProductskuMoneystr"] = productSKu.productskuMoney;
		productData["cartitemProductNumber"] =  parseInt($('.product-qty .product-num').val());
		
		return productData;
	}
	// to add cart
	function addToCart(reqData, callback) {
		$.ajax({
			url: '${APP_PATH}/MlbackCart/toAddToCart',
			data: JSON.stringify(reqData),
			type: "post",
			dataType: 'json',
			contentType: 'application/json',
			success: function (data) {
				if (data.code == 100) {
					callback && callback();
				} else {
					mlModalTip('Failed to add products to cart !</p>');
				}
			},
			error: function (err) {
				sysModalTip();
			}
		});
	}
	// to buy now
	function toBuyNow(reqData, callback) {
		$.ajax({
			url: '${APP_PATH}/MlbackCart/toBuyNow',
			data: JSON.stringify(reqData),
			type: "post",
			dataType: 'json',
			contentType: 'application/json',
			success: function (data) {
				if (data.code == 100) {
					callback & callback();
				} else {
					mlModalTip("I'm sorry, temporarily unable to settlement, please try again later !");
				}
			},
			error: function(err) {
				sysModalTip();
			}
		});
	}
	// render product media
	function renderProductMedia(data) {
		function stopVideo () {
			$('#ml-video')[0] && $('#ml-video')[0].pause();
		}
		var htmlStr = $('.product-slide .swiper-wrapper').html();
		var htmlVideoThumb = '';
		var htmlVideo = '';
		var htmlSkuImgs = '';
		if (data.video) {
			htmlVideoThumb = '<div class="swiper-slide"><div class=" lazyload" data-src="' + data.video.posterUrl + '"></div></div>';
			
			htmlVideo = '<div class="swiper-slide">' +
				'<video id="ml-video" controls preload="none" poster="' + data.video.posterUrl + '">' +
			    	'<source src="'+ data.video.videoUrl +'" type="video/mp4" />' +
			  	'</video>' +
			'</div>';
		}
		data.skuImgs && data.skuImgs.length && data.skuImgs.forEach(function(item) {
			htmlSkuImgs += '<div class="swiper-slide" data-sku="'+ item.sku +'"><div class="lazyload img" data-src="' + item.url + '" rel="' + item.url + '"></div></div>'
		});
		$('.product-thumb-slide .swiper-wrapper').html(htmlVideoThumb + htmlStr + htmlSkuImgs);
		$('.product-slide .swiper-wrapper').html(htmlVideo + htmlStr + htmlSkuImgs);
		
		$('#ml-video').on('play', function() {
			pintrk('track', 'watchvideo');
		});

		// lazyload
		new LazyLoad($('.product-media .lazyload'), {
			root: null,
			rootMargin: "10px",
			threshold: 0
		});
		// media video
			// media imgs
		var swiperThumb = new Swiper('.product-thumb-slide', {
			direction: 'vertical',
			slidesPerView: 6,
			spaceBetween: 5,
			freeMode: true,
			navigation: {
				nextEl: '.product-thumb-slide .swiper-button-next',
				prevEl: '.product-thumb-slide .swiper-button-prev',
			},
			on: {
				slideChangeTransitionStart: function() {
					stopVideo(); // swiper slide start, stop video play
				},
			},
		});
		var swiperMain = new Swiper('.product-slide', {
			pagination: {
				el: '.product-slide .swiper-pagination',
				clickable: true
			},
			navigation: {
				nextEl: '.product-slide .swiper-button-next',
				prevEl: '.product-slide .swiper-button-prev',
			},
			on: {
				slideChangeTransitionStart: function() {
					stopVideo(); // swiper slide start, stop video play
				},
			},
			thumbs: { swiper: swiperThumb },
		});
		imageZoomEvent();
	}
	// render product detials
	function renderProductDetails(data) {
		$('.product-details').data('product', {
			'proudctId': data.productId,
			'productName': data.productName,
			'productDiscount': data.productActoffoff,
			'productMainImgUrl': data.productMainimgurl,
			'productPrice': data.productOriginalprice,
			'productSeo': data.productSeo,
		});
		$('.product-name').text(data.productName);
		$('.product-price').html('<div class="name">Total Price: </div><div class="product-define-price">$'+ (data.productOriginalprice).toFixed(2) +'</div><div class="product-now-price">$'+ accuracyCal(data.productOriginalprice, data.productActoffoff) +'</div>');
		$('.product-description').html(data.productDesc);
	}
	/* create review swiper */
	function createReviewSwiper(imgs, activeNum) {
		var slideImgs = imgs.reduce(function(acc, img) {acc += '<div class="swiper-slide"><img class="lazyload" src="'+ img +'" /></div>'; return acc;}, '');
		if (!reviewSwiper) {
			var $reviewSwiper = $('<div class="review-swiper-box">' +
					'<div id="review-swiper" class="swiper-container">' +
					    '<div class="swiper-wrapper">'+ slideImgs +'</div>' +
					    '<div class="swiper-pagination"></div>' +
					    '<div class="swiper-button-next"></div>' +
					    '<div class="swiper-button-prev"></div>' +
				    '</div>' +
				    '<div class="review-swiper-close"><span class="icon close"></span></div>' +
				'<div>');
			$(document.body).append($reviewSwiper);
			addFixed();
			reviewSwiper = new Swiper('#review-swiper', {
				pagination: {
			    	el: '.swiper-pagination',
			    	type: 'fraction',
				},
			    navigation: {
			    	nextEl: '.swiper-button-next',
			    	prevEl: '.swiper-button-prev',
				},
			});

			reviewSwiper.slideTo(activeNum, 0, false);
		} else {
			$('.review-swiper-box .swiper-wrapper').html(slideImgs);

			reviewSwiper.updateSlides();
			reviewSwiper.slideTo(activeNum, 0, false);
			
			$('.review-swiper-box').show();
			addFixed();
		}
	}
	/* details of main */
	function getProductDetails(callback) {
		$.ajax({
			url: '${APP_PATH}/MlbackProduct/getOneMlbackProductDetail',
			data: JSON.stringify({ "productId": productId }),
			dataType: 'json',
			contentType: 'application/json',
			type: "post",
			async: false,
			success: function (data) {
				if (data.code == 100) {
					callback(data.extend.mlbackProductOne);
				}
			}
		});			
	}
	// callback to cart or checkout
	function selectCartOrCheckout(data) {
		var cartCheckoutHtml = '<div class="cart-checkout-product">' +
				'<div class="lazyload" data-src="'+ data.cartitemProductMainimgurl +'"></div>' +
				'<div class="cart-checkout-data">' +
					'<div class="cart-checkout-productname">'+ data.cartitemProductName +'</div>' +
					'<div class="cart-checkout-productprice">' +
						'<div class="name">Price: </div>' +
						'<div class="value">$'+ accuracyCal((data.cartitemProductOriginalprice + parseFloat(data.cartitemProductskuMoneystr)), data.cartitemProductActoff) +'</div>' +
					'</div>' +
					'<div class="btn-group">' +
						'<a class="btn" href="javascript:goToCartList();">View Cart</a>' +
						'<a class="btn btn-pink buy-now">Checkout</a>' +
					'</div>' +
				'</div>' +
			'</div>';
		var cartOrCheckoutModal = createModal({
			header: {
				html: '<p>Product successfully added to your cart!</p>'
			},
   			body: {
   				html: cartCheckoutHtml,
   			},
   			footer: {
   				isShow: true,
   				html: 'Free Gift For Every Order!'
   			},
   			autoClose: false
   		});
		new LazyLoad(cartOrCheckoutModal.find('.lazyload'), {
			root: null,
			rootMargin: "10px",
			threshold: 0
		});
		cartOrCheckoutModal.addClass('cart-checkout');
		
		setTimeout(function() {
			removeModal(cartOrCheckoutModal);
		}, 5000);
	}
	// to pay instance
	/* function toPayInstance(reqData) {
		$.ajax({
			url: '${APP_PATH}/ProPay/toBuyNowPay',					
			data: JSON.stringify(reqData),
			type: "post",
			dataType: 'json',
			contentType: 'application/json',
			success: function (data) {
				if (data.code == 100) {
					// create payment id
					$.ajax({
						url: '${APP_PATH}/MlfrontOrder/proDetailOrderToPayInfo',
						data: JSON.stringify({
							"orderId": data.extend.OrderIdBuyNowPay,
							"orderPayPlate": 1, //选择的付款方式,int类型   paypal传0，后来再有信用卡传1
							"orderProNumStr": reqData.cartitemProductNumber
						}),
						type: 'post',
						dataType: 'json',
						contentType: 'application/json',
						success: function (data) {
							if (data.code == 100) {								
								goTopayInstance();					
							} else {
								sysModalTip();
							}
						},
						error: function(err) {
							sysModalTip();
						}
					});
				} else {
					sysModalTip();
				}
			},
			error: function (data) {
				sysModalTip();
			}
		});
	} */
	// get review cal data
	function getReviewCalData(callback) {
		$.ajax({
			url: '${APP_PATH}/MlfrontReview/getMlfrontReviewCount',
			data: JSON.stringify({ "reviewPid": productId }),
			type: "post",
			dataType: 'json',
			contentType: 'application/json',
			success: function (data) {
				if (data.code == 100) {
					callback && callback(data.extend);
				} else {
					refreshPageModal();
				}
			},
			error: function(err) {
				refreshPageModal();
			}
		});
	}
	// get review list data
	function getReviewListData() {
		$.ajax({
			url: "${APP_PATH}/MlfrontReview/getMlfrontReviewByProductIdAndPage",
			data: JSON.stringify({ "reviewPid": productId, "reviewUid": getPageNum() }),
			type: "post",
			dataType: 'json',
			contentType: 'application/json',
			async: false,
			success: function (data) {
				if (data.code == 100) {
					var pageInfo = data.extend.pageInfo;
					if (!pageInfo.list.length) {
						$('.product-review-body').html('<p class="text-error">here are no comments yet, you can just click the <i>Write a Reveiw</i> button to write a comment !</p>');
					} else {
						renderProductReviewList(pageInfo.list, data.extend.imgUrlStrListst);
						renderTablePagination(pageInfo);				
					}
				} else {
					refreshPageModal();
				}
			},
			error: function(err) {
				refreshPageModal();					
			}
		});
	}
	// render product review count
	function renderProudctReviewCal (data) {
		var reviewNum = data.allReviewNum,
			reviewStarList = data.StartNumList,
			total = reviewStarList ? reviewStarList.reduce(function(acc, item) {acc += item.startCount * item.startNum; return acc;}, 0) : 0,
			avgStar = Math.ceil(total / reviewNum);
			htmlStr = '';
		for (var i = 4, percent = 0; i >= 0; i-=1) {
			percent = (data.allReviewNum ? reviewStarList[i].startCount * 100 / data.allReviewNum : 0);
			htmlStr += '<div class="product-review-star-item">' +
     	 		'<div class="stars">'+ (i+1) +' star</div>' +
     	 		'<div class="progress">' +
     	 			'<div class="progress-inner" style="width: ' + percent + '%"></div>' +
     	 		'</div>' +
     	 		'<div class="data">'+ percent.toFixed(2)  +'%</div>' +
     	 	'</div>';
		}
		// avg
		$('.product-review-avgstar .icon').each(function(idx, item) {
			if (idx < avgStar) $(item).removeClass('.star').addClass('star2');
		});
		// percent
		$('.product-review-star-list').html(htmlStr);
	}
	// redner pruduct review list
	function renderProductReviewList(reviewList, imgList) {
		var htmlStr = '';
		for (var i = 0, len = reviewList.length; i < len; i++) {
			htmlStr += '<div class="product-review-item">' +
					'<div class="product-review-item-title">' +
						'<div class="product-reivewer-img lazyload" data-src="' + reviewList[i].reviewUimgurl + '"></div>' +
						'<div class="product-review-item-data">' +
							'<div class="product-review-stars">';
							for (var j = 0; j < 5; j++) {
								if (j < reviewList[i].reviewProstarnum) {
									htmlStr += '<span class="icon star2"></span>';
								} else {
									htmlStr += '<span class="icon star"></span>';
								}
							}
					htmlStr += '</div>' +
							'<div class="product-review-author">' + reviewList[i].reviewUname + '</div>' +
						'</div>' +
					'<div class="product-review-date">' + (reviewList[i].reviewCreatetime) + '</div>' +
				'</div>' +
				'<div class="proudct-review-item-text">'+ reviewList[i].reviewDetailstr + '</div>' +
				'<div class="product-review-item-imgs">';
					var imgLen = imgList[i].length <= 6 ? imgList[i].length : 6;
					for (var k = 0; k < imgLen; k++) {
						htmlStr += '<div class="product-review-imgs-item lazyload" data-src="' + imgList[i][k] + '"></div>';
					}
					htmlStr += '</div></div>';
		}
		$('.product-review-list').html(htmlStr);
		
		new LazyLoad($('.product-review-list .lazyload'), {
			root: null,
			rootMargin: "10px",
			threshold: 0
		})
	}
	// callback delete revie id
	function deleteReviewId() {
		reviewId && $.ajax({
			url: "${APP_PATH }/MlfrontReview/delete",
			type: "post",
			dataType: "json",
			contentType: 'application/json',
			data: JSON.stringify({
				"reviewId": reviewId
			}),
			success: function (data) {}
		});
	}
	// callback get review id
	function getReviewId() {
		deleteReviewId();
		$.ajax({
			url: "${APP_PATH }/MlfrontReview/initializaReview",
			type: "post",
			dataType: "json",
			contentType: 'application/json',
			async: false,
			success: function (data) {
				if (data.code == 100) {
					reviewId = data.extend&& data.extend.mlfrontReview && data.extend.mlfrontReview.reviewId;
				} else {
					sysModalTip();
				}
			},
			error: function (err) {
				sysModalTip();
			}
		});
	}
	// callback save review data
	function saveReviewData(reqData, callback) {
		$.ajax({
			url: "${APP_PATH}/MlfrontReview/save",
			type: "post",
			data: JSON.stringify(reqData),
			dataType: "json",
			contentType: 'application/json',
			async: false,
			success: function (result) {
				if (result.code == 100) {
					mlModalTip('Successful operation. New comment information needs to be reviewed before it can be displayed !');
					reviewId = null;
					callback && callback();
				} else {
					mlModalTip('Operation Failed !');
				}
			},
			error: function() {
				mlModalTip('Operation Failed !');					
			}
		});
	}
	// varients
	var mediaData = {}, productData = {}, mainUrl, reviewSwiper, reviewModal, reviewId, hasReivewData = false;
	// initial
	getProductDetails(function(data) {
		mainUrl = data.productMainimgurl;
		// media
		if (data.productVideoUrl && data.productVideoImgUrl) {
			mediaData.video = {
				videoUrl: data.productVideoUrl,
				posterUrl: data.productVideoImgUrl,
				isVideo: true
			};
		}
		// details
		productSeo = data.productSeo;
		productName = data.proudctName;
		renderProductDetails(data);
		// coupon area list			
		getCouponAreaData(renderCouponAreaData);
		// option
		getProductOption(function(data) {
			renderProductOptions(data);
		});
		// skus
		getProductSkus(function(data) {
			function getProductSkuImgs(data) {
				var productSkuImgs = [];
				data.forEach(function(item) {
					item.productskuOneImg && productSkuImgs.push({
						'sku': item.productskuName,
						'url': item.productskuOneImg 
					});
				})
				return productSkuImgs;
			}
			data.length && buildResult(data), mediaData.skuImgs = getProductSkuImgs(data);
		});
		renderProductMedia(mediaData);
		$('.product-option-item .radio').on('click', function() {
			function getSwiperSlideItem(sku) {
				var swiperIdx = -1;
				$('.product-thumb-slide .swiper-slide').each(function(idx, item) {
					if($(item).data('sku') == sku) swiperIdx = idx;
				});
				return swiperIdx;
			}
			setTimeout(function() {
				var $selectedRadio = $('.product-option-item .radio.active');
				if ($selectedRadio.length && ($selectedRadio.length == $('.product-option-item').length)) {
					var skuNameArr = [];
					var swiperSlideIdx = null;
					$selectedRadio.each(function(idx, item) {
						skuNameArr.push($(item).data('text'));
					});
					swiperSlideIdx = getSwiperSlideItem(skuNameArr.join(','));
					swiperSlideIdx > -1 && $('.product-slide .swiper-pagination-bullet').eq(swiperSlideIdx).click();
				}				
			}, 500);
		})
		// countdown time
		getCoundownTimeData(2, rednerCountDownAreaOne);
		addHeaderInfo(data);
		var fbpid = data.productId;
		var fbprice = accuracyCal(data.productOriginalprice, data.productActoffoff);
		fbq('track', 'PageView', {
			content_ids: fbpid,
			contents: [{ "id": fbpid, "quantity": 999, "item_price": fbprice }],
			content_type: 'product',
			value: fbprice,
			currency: 'USD'
		});
		fbq('track', 'ViewContent', {
			content_ids: fbpid,
			contents: [{ "id": fbpid, "quantity": 999, "item_price": fbprice }],
			content_type: 'product',
			value: fbprice,
			currency: 'USD'
		});

		pintrk('track', 'pagevisit', {
			em: '${ map.pin_email}',
			value: fbprice * $('.product-qty .product-num').val(),
			order_quantity: $('.product-qty .product-num').val(),
			currency: 'USD',
			product_ids: ["" + fbpid],
			line_items: [
				{
					product_name: $('.product-name').html(),
					product_id: fbpid,
					product_price: fbprice,
					product_quantity: $('.product-qty .product-num').val()
				}
			]
		});
	});
	// event
	$(window).on('resize', imageZoomEvent);
	// image zoom resize
	function imageZoomEvent() {
		if (window.innerWidth < 1300) {
			$('.product-slide.product-zoom').find('.img').off('mouseover');
		} else {
			$('.product-slide.product-zoom').find('.img').imagezoom();
		}
	}
	// product share
	$('.share-click').on('click', function() {
		if ($(this)[0].title == 'share on pinterest') {
			window.open($(this).data('url') + encodeURIComponent(window.location.href) + '&channel=pinterest&media=' + encodeURIComponent($('.product-slide').find('.img').eq(0).data('src')) + '&description=' + $('#ml-des').attr('content'));
		} else
			window.open($(this).data('url') + encodeURIComponent(window.location.href));
	});
	// add product
	$('#product-num-add').on('click', function() {
		productAdd($(this));
	});
	// sub product
	$('#product-num-sub').on('click', function() {
		productSub($(this));
	});
	// product-body product-tab
	$('.product-tab-item').on('click', function() {
		var $this = $(this);
		var tabName = $this.data('name');
		if (!$this.hasClass('active')) $('.product-tab-container[data-name="'+ tabName +'"]').addClass('active').siblings().removeClass('active');
		if (tabName == 'review' && !hasReivewData) {
			// review count
			getReviewCalData(function(data) {
				$('.product-review-total span').text(data.allReviewNum);
				renderProudctReviewCal(data);
			});
			// review list
			getReviewListData();
			// change review state
			hasReivewData = true;
			$('#loader-box').hide();
		}
	});
	// pagination a-click
	$(document.body).on('click', '#table-pagination li', function (e) {
		getReviewListData();
	});
	// add to cart
	$('#add-to-cart').on('click', function(evt) {
		debounce(function() {
			if (isCorrectProduct()) {
				// check product sku is error or not
				var reqData = getProductData();
				if (!reqData) {
					removeModal(reviewModal);
					cannotBuyProductModal();
					return false;
				}

				addToCart(reqData, function() {
					if (window.innerWidth > 1023) {
						generateFlyBubble(evt, updateProructNumberInCart);							
					} else {
						updateProructNumberInCart();
						selectCartOrCheckout(reqData);
					}
				});

				fbq('track', 'AddToCart', {
					content_ids: reqData.cartitemProductId,
					content_type: 'product',
					value: reqData.cartitemProductOriginalprice,
					currency: 'USD'
				});
				pintrk('track', 'addtocart', {
					em: '${ map.pin_email}',
					value: reqData.cartitemProductOriginalprice * $('.product-qty .product-num').val(),
					order_quantity: $('.product-qty .product-num').val(),
					currency: 'USD',
					product_ids: ["" + reqData.cartitemProductId],
					line_items: [
						{
							product_name: $('.product-name').html(),
							product_id: reqData.cartitemProductId,
							product_price: reqData.cartitemProductOriginalprice,
							product_quantity: $('.product-qty .product-num').val()
						}
					]
				});
			}
		}, 300);
	});
	// buy now
	$(document.body).on('click', '.buy-now', function() {
		var reqData = getProductData();
		isCorrectProduct() && reqData && (fbq('track', 'InitiateCheckout', {
			content_ids: reqData.cartitemProductId,
			content_type: 'product',
			value: accuracyCal((reqData.cartitemProductOriginalprice + parseFloat(reqData.cartitemProductskuMoneystr)), reqData.cartitemProductActoff),
			currency: "USD"
		}), pintrk('track', 'checkout', {
			em: '${ map.pin_email}',
			value: accuracyCal((reqData.cartitemProductOriginalprice + parseFloat(reqData.cartitemProductskuMoneystr)), reqData.cartitemProductActoff) * $('.product-qty .product-num').val(),
			order_quantity: $('.product-qty .product-num').val(),
			currency: 'USD',
			product_ids: ["" + reqData.cartitemProductId],
			line_items: [
				{
					product_name: $('.product-name').html(),
					product_id: reqData.cartitemProductId,
					product_price: reqData.cartitemProductOriginalprice,
					product_quantity: $('.product-qty .product-num').val()
				}
			]
		}), toBuyNow(reqData, goToCheckout));
	});
	/* $('.paypal-button.paypal-now').on('click', function() {
		var reqData = getProductData();
		isCorrectProduct() && reqData && (payLoading(), fbq('track', 'AddPaymentInfo', {
			content_ids: reqData.cartitemProductId,
			content_type: 'product',
			value: accuracyCal((reqData.cartitemProductOriginalprice + parseFloat(reqData.cartitemProductskuMoneystr)) * reqData.cartitemProductNumber, reqData.cartitemProductActoff),
			currency: 'USD'
		}), toPayInstance(reqData));
	}); */
	// open reiview swiper
	$(document.body).on('click', '.product-review-imgs-item', function() {
		var activeImg = $(this).data('src');
		var activeNum = 0;
		var imgs = [];
		$(this).parent().find('.lazyload').each(function(idx, item) {
			var img = $(item).data('src');
			if (img == activeImg) activeNum = idx;
			imgs.push(img);
		});
		createReviewSwiper(imgs, activeNum);
	});
	// clsoe reivew swiper		
	$(document.body).on('click', '.review-swiper-close', function() {
		$('.review-swiper-box').hide();
		removeFixed();
	});
	// write review
	$('#write-review').on('click', function(e) {
		var writeReviewHtml = '<div class="write-review-content">' +
			'<div class="input-group">' +
				'<label for="write-review-name">User Name</label>' +
				'<input type="text" id="write-review-name" placeholder="Enter your name (public)">' +
			'</div>' +
			'<div class="input-group">' +
				'<label class="name">Star Rating</label>' +
				'<div class="write-review-star">' +
					'<span class="icon star" data-id="1"></span>' +
					'<span class="icon star" data-id="2"></span>' +
					'<span class="icon star" data-id="3"></span>' +
					'<span class="icon star" data-id="4"></span>' +
					'<span class="icon star" data-id="5"></span>' +
				'</div>' +
			'</div>' +
			'<div class="input-group">' +
				'<label for="write-review-details">Review Text</label>' +
				'<textarea id="write-review-details" placeholder="Write your comments here" rows="5"></textarea>' +
			'</div>' +
			'<div class="input-group">' +
				'<label>Picture Zone (optional)</label>' +
				'<div class="write-review-img-box">' +
					'<div class="ml-upload">' +
						'<input class="upload-review-picture" type="file">' +
						'<i class="icon plus"></i>' +
						'<div class="spinner">' +
							'<div class="spinner-border" role="status" aria-hidden="true"></div>' +
						'</div>'+
					'</div>' +
					'<div class="write-review-imglist hide"></div>' +
				'</div>' +
			'</div>' +
			'<div class="input-group">' +
				'<button class="btn btn-pink write-review-publish">publish</button>' +
			'</div>' +
		'</div>';
		getReviewId();
		reviewModal = createModal({
			header: {
				html: '<p>Write A Review...</p>'
			},
   			body: {
   				html: writeReviewHtml,
   			}
   		});
		reviewModal.addClass('review-modal');
	});
	// select star
	$(document.body).on('click', '.write-review-star .icon', function (e) {
		$('.write-review-star').data('star', $(this).data('id'));
		$(this).removeClass('star').addClass('star2').prevAll('.icon').removeClass('star').addClass('star2');
		$(this).nextAll('.icon').removeClass('star2').addClass('star');
	});
	// upload review picture
	$(document.body).on('change', '.upload-review-picture', function(e) {
		var $this = $(this);
		var file = $this[0].files[0];
		var formData = new FormData();
		var reviewImgLen = $('.write-review-imglist-item').length || 0;
		var reviewImgOrder = reviewImgLen + 1;

		if (!file) return false;

		$this.parent().find('.spinner').show();
		$this.val('');

		formData.append('image', file);
		formData.append('type', 'reviewDetail');
		formData.append('productSeo', productSeo);
		formData.append('reviewId', reviewId);
		formData.append('reviewimgSortOrder', reviewImgOrder);

		if (reviewImgLen >= 6) {
			mlModalTip('Upload up to 6 pictures !');
			return false;
		}

		$.ajax({
			url: "${APP_PATH}/ImageUpload/thumImageReviewAll",
			type: "post",
			data: formData,
			processData: false,
			contentType: false,
			cache: false,
			dataType: 'json',
			success: function (data) {
				if (data.code == 100) {
					$('.write-review-imglist').hasClass('hide') && $('.write-review-imglist').removeClass('hide');
					$('.write-review-imglist').append($('<div class="write-review-imglist-item"><img src="'+ data.extend.sqlimageUrl +'"></div>'));
				} else {
					mlModalTip('Failed to upload comment picture !');
				}
			},
			error: function (err) {
				mlModalTip('Failed to upload comment picture !');
			},
			complete: function () {
				$this.parent().find('.spinner').hide();
			}
		});
	});
	// publish a review
	$(document.body).on('click', '.write-review-publish', function(e) {
		var username = $('#write-review-name').val(),
			starNum = $('.write-review-star').data('star'),
			details = $('#write-review-details').val();

		if (!username || !username.trim()) {
			mlModalTip('Please enter user name !');
			return;
		}

		if (!starNum) {
			mlModalTip('Please Rating Star !');
			return;
		}

		if (!details || !details.trim()) {
			mlModalTip('Comment content cannot be empty !');
			return;
		}

		saveReviewData({
			reviewId: reviewId,
			reviewUname: username,
			reviewPid: productId,
			reviewPseoname: productSeo,
			reviewPname: productName,
			reviewDetailstr: details,
			reviewProstarnum: starNum,
			reviewFrom: 1,
		}, function() {
			removeModal(reviewModal);
		});
	});
	// get introduct product
	getProductSlideArea(function(data) {
		var $el = $('.product-footer');
		data.length && (renderIntroduceProductSlide($el, data),
		new LazyLoad($el.find('.lazyload'), {
			root: null,
			rootMargin: "10px",
			threshold: 0
		}));
	});
	// delte unused reiview
	$(window).on('beforeunload', function() {
		deleteReviewId();
		setPageNum(1);
	});
</script>
<script>
function renderFbReviews(data) {
	var $fbReviewBox;
	var reviewList = data && data.match(/<iframe\s*.*?><\/iframe>/g) || [];
	var len = reviewList.length;
	var htmlStr = '<div style="height: 36px; line-height: 36px; text-align: center;; font-size: 16px; -webkit-overflow-scrolling: touch; background-color: #558dfd; color: #fff;">Real-time Feedback on Facebook</div>';
	if (len) {
		htmlStr += reviewList.splice(0, 5).join('');
		if (len > 5) {
			$fbReviewBox = $('<div class="fb-reviews-box hide"  style="position: fixed; top: 0; left: 0; z-index: 9999999999; width: 100%; height: 100%; background-color: rgba(0, 0, 0, .5);"></div');
			var fbReviewBoxHtml = '<div class="fb-container" style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); width: 80%; max-width: 500px; background-color: #fff;">' +
					'<div class="fb-review-head" style="text-align: center;">' +
						'<div style="height: 36px; line-height: 36px; font-size: 16px; background-color: #558dfd; color: #fff;">Real-time Feedback on Facebook</div>' +
						'<span class="icon close" style="position: absolute; top: 0; right: 0; width: 32px; height: 32px; color: #fff; font-size: 18px; cursor: pointer;"></span>' +
						'<div style="height: 30px; line-height: 30px; background: #fff; color: #636363; font-size: 16px;">To see how they experience it !</div>' +
					'</div>' +
					'<div class="fb-review-body" style="height: 412px; overflow-y: auto">'+ reviewList.join('') +'</div>' +
				'</div';
			htmlStr += '<div class="fb-review-more" style="text-align: center; color: #4080ff; cursor: pointer;">More Feedbacks &gt;</span>';
			$fbReviewBox.html(fbReviewBoxHtml);
		}
		$('.product-fb-reviews').html(htmlStr);
		$(document.body).append($fbReviewBox);
	}
	// event-open
	$(document.body).on('click', '.fb-review-more', function() {
		addFixed();
		$('.fb-reviews-box').removeClass('hide');
	});
	// event-close
	$('.fb-reviews-box').on('click', function(e) {
		if (e.target == this) removeFixed(), $('.fb-reviews-box').addClass('hide');
	});
	$('.fb-reviews-box .close').on('click', function(e) {
		removeFixed();
		$('.fb-reviews-box').addClass('hide');
	});
}

function getfbReviewsData(callback) {
	$.ajax({
		url: "${APP_PATH}/MlbackProfbReview/getProfbreviewAreaDetailListByPid",
		type: "post",
		data: JSON.stringify({"profbreviewAreaPid": productId}),
		dataType: "json",
		contentType: 'application/json',
		success: function (data) {
			if (data.code == 100) {
				renderFbReviews(data.extend.mlbackProfbreviewAreaOne.profbreviewAreaDesc);
			}
		},
		error: function (err) {}
	});	
}
getfbReviewsData(renderFbReviews);
</script>