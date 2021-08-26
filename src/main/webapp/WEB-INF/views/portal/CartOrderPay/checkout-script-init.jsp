<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<script>
	// get order list
	function getProductOrderList(callback) {
		$.ajax({
			url: '${APP_PATH}/MlfrontOrder/getNowOrderDetail',
			type: 'get',
			async: false,
			cache: false,
			success: function (data) {
				if (data.code == 100) {
					callback && callback(data.extend);
				}
			}
		});
	}
	// get province data
	function getProvinceData(reqData, callback) {
		$.ajax({
			url: '${APP_PATH}/MlfrontAddress/getAreafreightMoney',
			data: JSON.stringify(reqData),
			async: false,
			type: 'post',
			dataType: 'json',
			contentType: 'application/json',
			async: false,
			success: function (data) {
				if (data.code == 100) {
					callback && callback(data.extend);
				}					
			}
		});
	}
	// get user info
	function getUserInfo(callback) {
		$.ajax({
			url: '${APP_PATH}/MlfrontAddress/getOneMlfrontAddressDetailByUinfo',
			type: 'post',
			dataType: 'json',
			async: false,
			success: function (data) {
				callback && callback(data.extend);
			}
		});
	}
	// update order product number
	function updateOrderNum(el, num, callback) {
		var targetData = el.parents('.order-item').data('orderitem') || null,
			reqData = {
				"orderitemId": parseInt(targetData.orderitemId),
				"orderitemPskuNumber": num
			};
		$.ajax({
			url: '${APP_PATH}/MlfrontOrder/updateOrderItemNum',
			data: JSON.stringify(reqData),
			type: "post",
			dataType: 'json',
			contentType: 'application/json',
			success: function (data) {
				if (data.code == 100) {
					var orderProductNumArr = $('.order-list').data('itemnumarr').split(',');
					var orderCartItemIdArr = $('.order-list').data('itemidarr').split(',')
					orderCartItemIdArr.forEach(function(item, idx) {
						if ('' + item == '' + reqData.orderitemId) {
							orderProductNumArr[idx] = num;
						}
					});
					$('.order-list').data('itemnumarr', orderProductNumArr.join(','));
					updateProructNumberInCart();
					targetData.orderitemPskuNumber = num;
					el.parents('.order-item').data('orderitem', targetData);
					callback && callback();
					updateProductNumSuccessModal();
				} else {
					el.find('.product-num').val(targetData.orderitemPskuNumber);
					updateProductNumFailModal();
				}
			},
			error: function () {
				el.find('.product-num').val(targetData.cartitemProductNumber);
				updateProductNumFailModal();
			}
		});
	}
	// delete order product
	function deleteOrderProduct(el, callback) {
		var targetData = el.data('orderitem') || null,
			orderitemId = parseInt(targetData.orderitemId),
			reqData = {
				"orderitemId": orderitemId
			};
		function resetOrderListData() {
			var itemIdArr = $('.order-list').data('itemidarr').split(',');
			itemIdArr = itemIdArr.filter(function(id) {
				return id != orderitemId;
			});
			$('.order-list').data('itemidarr', itemIdArr.join(','));
		}
		$.ajax({
			url: '${APP_PATH}/MlfrontOrder/delOrderItem',
			data: JSON.stringify(reqData),
			type: "post",
			dataType: 'json',
			contentType: 'application/json',
			success: function (data) {
				el.remove();
				if (!$('.order-item').length) goToCartList();
				callback && callback(orderitemId);
				resetOrderListData();
				deleteProductSuccessModal();
			},
			error: function () {
				el.find('.product-num').val(targetData.cartitemProductNumber);
				deleteProductFailModal();
			}
		});
	}
	// save user address
	function orderSaveAddress(reqData, callback) {
		$.ajax({
			url: '${APP_PATH}/MlfrontAddress/save',
			type: 'post',
			dataType: 'json',
			data: JSON.stringify(reqData),
			contentType: 'application/json',
			success: function (data) {
				if (data.code == 100) {
					callback && callback(data.extend.mlfrontAddress);
				} else {
					sysModalTip();
				}
			},
			error: function () {
				sysModalTip();
			}
		});
	}
	// order pay
	function orderPay(reqData, callback) {
		$.ajax({
			url: '${APP_PATH}/MlfrontOrder/orderToPayInfo',
			data: JSON.stringify(reqData),
			type: 'post',
			dataType: 'json',
			contentType: 'application/json',
			success: function (data) {
				if (data.code == 100) {
					callback && callback(data.extend);
				} else {
					sysModalTip();
				}
			},
			error: function(err) {
				sysModalTip();
			}
		});
	}
	// country combine with province
	function countryCombineWithProvince() {
		getProvinceData({
			"addressCountry": $('#addressCountry').find('option:checked').data('value')
		}, function(data) {
			var money = data.areafreightMoney,
				provinceList = data.mlPaypalStateprovinceList;
			$('.order-address-shipping').data('shipping', money).find('.value').text('$' + money);
			if (provinceList && provinceList.length > 0) {
				renderProvince(provinceList);
				$('#addressProvince').data('status', false);
				hasProvince = true;
				$("#addressProvince").parents('.form-group').removeClass('hide');
				$("#addressCountry").parents('.form-group').css("width", "");
			} else {
				$('#addressProvince').val('').data('status', true);
				hasProvince = false;
				$("#addressProvince").parents('.form-group').addClass('hide');
				$("#addressCountry").parents('.form-group').css("width", "100%");
			}
		});
	}
	// get current time
	/* function getTime() {
		var date = new Date(),
			year = date.getFullYear(),
			month = date.getMonth() + 1,
			day = date.getDate(),
			hours = date.getHours(),
			minutes = date.getMinutes(),
			seconds = date.getSeconds();
		return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + (seconds < 10 ? '0' + seconds : seconds);
	} */
	// cal orderList data
	function calOrderList () {
		var couponData = $('.order-coupon-group').data('coupon');
		var resData = {
				prototal: 0,
				subtotal: 0,
				shipping: $('.order-address-shipping').data('shipping') || 0,
				coupon: 0,
			};
		var productIdArr = $('.order-list').data('productidarr').split(',');
		var couponPidArr = [];
		var userEmail = $('#addressEmail').val().trim();
		$('.order-item').each(function(idx, item) {
			var $data = $(item).data('orderitem');
			var orderitemPrice = parseFloat((parseFloat(accuracyCal((($data.orderitemProductOriginalprice || 0) + parseFloat($data.orderitemPskuMoneystr || 0)), ($data.orderitemProductAccoff || 100))) * $data.orderitemPskuNumber).toFixed(2));
			resData.prototal += orderitemPrice;
		});
		// coupon code
		if (couponData && !couponData.mlbackCouponOne) {
			resData.coupon = 0;
			$('.order-coupon-tip').html('<p>'+ couponData.resMsg +'</p>');
		}
		if (couponData && couponData.mlbackCouponOne) {
			function checkCouponBaseline(price) {
				if (!couponData.mlbackCouponOne.couponPriceBaseline || (couponData.mlbackCouponOne.couponPriceBaseline && price >= parseFloat(couponData.mlbackCouponOne.couponPriceBaseline))) {
					return true;
				} else {
					resData.coupon = 0;
					$('.order-coupon-tip').html('<p>The minimum usage price of this coupon is + <i style="color: #f00">$'+ couponData.mlbackCouponOne.couponPriceBaseline +'<i></p>');
					return false;
				}
			}
			function checkCouponType(needProductPrice) {
				// 满减
				if ('' + couponData.mlbackCouponOne.couponType == '0') {
					resData.coupon = parseFloat(couponData.mlbackCouponOne.couponPrice);
				}
				// 折扣
				if ('' + couponData.mlbackCouponOne.couponType == '1') {
					resData.coupon = parseFloat(accuracyCal(needProductPrice, (couponData.mlbackCouponOne.couponPriceoff || 100)));
				}
				$('.order-coupon-tip').html('<p><i style="color: #f00">'+ couponData.mlbackCouponOne.couponCode +'</i> used successful ! </p>');
			}
			function getAllProductItemPrice(flag) {
				var prices = 0;
				$('.order-item').each(function(idx, item) {
					var $data = $(item).data('orderitem');
					var orderItemPrice = parseFloat((parseFloat(accuracyCal((($data.orderitemProductOriginalprice || 0) + parseFloat($data.orderitemPskuMoneystr || 0)), ($data.orderitemProductAccoff || 100))) * $data.orderitemPskuNumber).toFixed(2));
					if (flag == 'all') {
						prices += orderItemPrice;
					}
					if (flag == 'products') {
						couponPidArr.indexOf('' + $data.orderitemPid) > -1 && (prices += orderItemPrice);
					}
					if (flag == 'collection') {
						couponPidArr.indexOf('' + $data.orderitemPid) > -1 && (prices += orderItemPrice);
					}
					if (flag == 'except') {
						couponPidArr.indexOf('' + $data.orderitemPid) < 0 && (prices += orderItemPrice);
					}
				});
				return prices;
			}
			function checkCouponRange(flag) {
				var needProductPrice = getAllProductItemPrice(flag);
				if (needProductPrice) {
					checkCouponBaseline(needProductPrice) && checkCouponType(needProductPrice);
				} else {
					$('.order-coupon-tip').html('<p><i style="color: #f00">This coupon does not apply to the currently purchased product! </p>');
				}
			}
			function checkCoupon() {
				// all product
				if ('' + couponData.mlbackCouponOne.couponProductonlyType == '0' || !couponData.mlbackCouponOne.couponProductonlyType) {
					checkCouponRange('all');
				}
				// specific product
				if ('' + couponData.mlbackCouponOne.couponProductonlyType == '1') {
					couponPidArr = couponData.mlbackCouponOne.couponProductonlyPidstr && couponData.mlbackCouponOne.couponProductonlyPidstr.split(',');
					checkCouponRange('products');
				}
				// specific collection
				if ('' + couponData.mlbackCouponOne.couponProductonlyType == '2') {
					couponPidArr = couponData.mlbackCouponOne.couponProsFromApplyCateidstr && couponData.mlbackCouponOne.couponProsFromApplyCateidstr.split(',');
					checkCouponRange('collection');
				}
				// except product
				if ('' + couponData.mlbackCouponOne.couponProductonlyType == '3') {
					couponPidArr = couponData.mlbackCouponOne.couponAllExceptPidstr && couponData.mlbackCouponOne.couponAllExceptPidstr.split(',');
					checkCouponRange('except');
				}
			}
			if ('' + couponData.mlbackCouponOne.couponCodeUniqueEmailIF == '1') {
				if (userEmail) {
					if (couponData.mlbackCouponOne.couponCodeUniqueEmail && couponData.mlbackCouponOne.couponCodeUniqueEmail.indexOf(userEmail) > -1) {
						checkCoupon();
					} else {
						resData.coupon = 0;
						$('.order-coupon-tip').html('<p>This coupon is only applicable to designated users!</p>');
					}
				} else {
					mlModalTip('This is an exclusive coupon, you need to complete the address information!');
					$('.order-coupon-tip').html('');
					$('html').animate({scrollTop: 0}, 500);
				}
			} else {
				checkCoupon();
			}
		}
		resData.subtotal = resData.prototal + resData.shipping - resData.coupon;
		if (resData.coupon <= 0) $('.order-coupon-group').data('coupon', '');
		showFreeGift(resData.prototal);
		
		return resData;
	}
	// reset order  cal
	function resetOrderCal() {
		var calOrder = calOrderList();
		$('.order-cal-prototal').text('$' + (calOrder.prototal).toFixed(2));
		$('.order-cal-shipping').text('$'+ (calOrder.shipping).toFixed(2));
		$('.order-cal-coupon').text('-$'+ (calOrder.coupon).toFixed(2));
		$('.order-cal-subtotal').text('$'+ (calOrder.subtotal).toFixed(2)).data('price', (calOrder.subtotal).toFixed(2));
	}
	// render country
	function renderCountry() {
		var htmlStr = '',
			countryList = [{name:"United States",nickname:"US"},{name:"United Kingdom",nickname:"GB"},{name:"Canada",nickname:"CA"},{name:"Nigeria",nickname:"NG"},{name:"Australia",nickname:"AU"},{name:"South Africa",nickname:"ZA"},{name:"Germany",nickname:"DE"},{name:"Kenya",nickname:"KE"},{name:"Spain",nickname:"ES"},{name:"United Arab Emirates",nickname:"AE"},{name:"France",nickname:"FR"},{name:"--------------------",nickname:null},{name:"Afghanistan",nickname:"AF"},{name:"Åland Islands",nickname:"AX"},{name:"Albania",nickname:"AL"},{name:"Algeria",nickname:"DZ"},{name:"American Samoa",nickname:"AS"},{name:"Andorra",nickname:"AD"},{name:"Angola",nickname:"AO"},{name:"Anguilla",nickname:"AI"},{name:"Antarctica",nickname:"AQ"},{name:"Antigua and Barbuda",nickname:"AG"},{name:"Argentina",nickname:"AR"},{name:"Armenia",nickname:"AM"},{name:"Aruba",nickname:"AW"},{name:"Australia",nickname:"AU"},{name:"Austria",nickname:"AT"},{name:"Azerbaijan",nickname:"AZ"},{name:"Bahamas",nickname:"BS"},{name:"Bahrain",nickname:"BH"},{name:"Bangladesh",nickname:"BD"},{name:"Barbados",nickname:"BB"},{name:"Belarus",nickname:"BY"},{name:"Belgium",nickname:"BE"},{name:"Belize",nickname:"BZ"},{name:"Benin",nickname:"BJ"},{name:"Bermuda",nickname:"BM"},{name:"Bhutan",nickname:"BT"},{name:"Bolivia",nickname:"BO"},{name:"Bosnia and Herzegovina",nickname:"BA"},{name:"Botswana",nickname:"BW"},{name:"Bouvet Island",nickname:"BV"},{name:"Brazil",nickname:"BR"},{name:"British Indian Ocean Territory",nickname:"IO"},{name:"British Virgin Islands",nickname:"VG"},{name:"Brunei",nickname:"BN"},{name:"Bulgaria",nickname:"BG"},{name:"Burkina Faso",nickname:"BF"},{name:"Burundi",nickname:"BI"},{name:"Cambodia",nickname:"KH"},{name:"Canada",nickname:"CA"},{name:"Cameroon",nickname:"CM"},{name:"Cape Verde",nickname:"CV"},{name:"Cayman Islands",nickname:"KY"},{name:"Central African Republic",nickname:"CF"},{name:"Chad",nickname:"TD"},{name:"Chile",nickname:"CL"},{name:"China",nickname:"CN"},{name:"Christmas Island",nickname:"CX"},{name:"Cocos (Keeling) Islands",nickname:"CC"},{name:"Colombia",nickname:"CO"},{name:"Comoros",nickname:"KM"},{name:"Congo - Brazzaville",nickname:"CG"},{name:"Congo - Kinshasa",nickname:"CD"},{name:"Cook Islands",nickname:"CK"},{name:"Costa Rica",nickname:"CR"},{name:"Côte d’Ivoire",nickname:"CI"},{name:"Croatia",nickname:"HR"},{name:"Cuba",nickname:"CU"},{name:"Cyprus",nickname:"CY"},{name:"Czech Republic",nickname:"CZ"},{name:"Denmark",nickname:"DK"},{name:"Djibouti",nickname:"DJ"},{name:"Dominica",nickname:"DM"},{name:"Dominican Republic",nickname:"DO"},{name:"Ecuador",nickname:"EC"},{name:"Egypt",nickname:"EG"},{name:"El Salvador",nickname:"SV"},{name:"Equatorial Guinea",nickname:"GQ"},{name:"Eritrea",nickname:"ER"},{name:"Estonia",nickname:"EE"},{name:"Ethiopia",nickname:"ET"},{name:"Falkland Islands",nickname:"FK"},{name:"Faroe Islands",nickname:"FO"},{name:"France",nickname:"FR"},{name:"Fiji",nickname:"FJ"},{name:"Finland",nickname:"FI"},/* {name:"French Guiana",nickname:"GF"},{name:"French Polynesia",nickname:"PF"},{name:"French Southern Territories",nickname:"TF"}, */{name:"Gabon",nickname:"GA"},{name:"Gambia",nickname:"GM"},{name:"Georgia",nickname:"GE"},{name:"Germany",nickname:"DE"},{name:"Ghana",nickname:"GH"},{name:"Gibraltar",nickname:"GI"},{name:"Greece",nickname:"GR"},{name:"Greenland",nickname:"GL"},{name:"Grenada",nickname:"GD"},{name:"Guadeloupe",nickname:"GP"},{name:"Guam",nickname:"GU"},{name:"Guatemala",nickname:"GT"},{name:"Guernsey",nickname:"GG"},{name:"Guinea",nickname:"GN"},{name:"Guinea-Bissau",nickname:"GW"},{name:"Guyana",nickname:"GY"},{name:"Haiti",nickname:"HT"},{name:"Heard &amp; McDonald Islands",nickname:"HM"},{name:"Honduras",nickname:"HN"},{name:"Hong Kong SAR China",nickname:"HK"},{name:"Hungary",nickname:"HU"},{name:"Iceland",nickname:"IS"},{name:"India",nickname:"IN"},{name:"Indonesia",nickname:"ID"},{name:"Iran",nickname:"IR"},{name:"Iraq",nickname:"IQ"},{name:"Ireland",nickname:"IE"},{name:"Isle of Man",nickname:"IM"},{name:"Israel",nickname:"IL"},{name:"Italy",nickname:"IT"},{name:"Jamaica",nickname:"JM"},{name:"Japan",nickname:"JP"},{name:"Jersey",nickname:"JE"},{name:"Jordan",nickname:"JO"},{name:"Kazakhstan",nickname:"KZ"},{name:"Kenya",nickname:"KE"},{name:"Kiribati",nickname:"KI"},{name:"Kuwait",nickname:"KW"},{name:"Kyrgyzstan",nickname:"KG"},{name:"Laos",nickname:"LA"},{name:"Latvia",nickname:"LV"},{name:"Lebanon",nickname:"LB"},{name:"Lesotho",nickname:"LS"},{name:"Liberia",nickname:"LR"},{name:"Libya",nickname:"LY"},{name:"Liechtenstein",nickname:"LI"},{name:"Lithuania",nickname:"LT"},{name:"Luxembourg",nickname:"LU"},{name:"Macau SAR China",nickname:"MO"},{name:"Macedonia",nickname:"MK"},{name:"Madagascar",nickname:"MG"},{name:"Malawi",nickname:"MW"},{name:"Malaysia",nickname:"MY"},{name:"Maldives",nickname:"MV"},{name:"Mali",nickname:"ML"},{name:"Malta",nickname:"MT"},{name:"Marshall Islands",nickname:"MH"},{name:"Martinique",nickname:"MQ"},{name:"Mauritania",nickname:"MR"},{name:"Mauritius",nickname:"MU"},{name:"Mayotte",nickname:"YT"},{name:"Mexico",nickname:"MX"},{name:"Micronesia",nickname:"FM"},{name:"Moldova",nickname:"MD"},{name:"Monaco",nickname:"MC"},{name:"Mongolia",nickname:"MN"},{name:"Montenegro",nickname:"ME"},{name:"Montserrat",nickname:"MS"},{name:"Morocco",nickname:"MA"},{name:"Mozambique",nickname:"MZ"},{name:"Myanmar (Burma)",nickname:"MM"},{name:"Namibia",nickname:"NA"},{name:"Nauru",nickname:"NR"},{name:"Nepal",nickname:"NP"},{name:"Netherlands",nickname:"NL"},{name:"Netherlands Antilles",nickname:"AN"},{name:"New Caledonia",nickname:"NC"},{name:"New Zealand",nickname:"NZ"},{name:"Nicaragua",nickname:"NI"},{name:"Niger",nickname:"NE"},{name:"Nigeria",nickname:"NG"},{name:"Niue",nickname:"NU"},{name:"Norfolk Island",nickname:"NF"},{name:"Northern Mariana Islands",nickname:"MP"},{name:"North Korea",nickname:"KP"},{name:"Norway",nickname:"NO"},{name:"Oman",nickname:"OM"},{name:"Pakistan",nickname:"PK"},{name:"Palau",nickname:"PW"},{name:"Palestinian Territories",nickname:"PS"},{name:"Panama",nickname:"PA"},{name:"Papua New Guinea",nickname:"PG"},{name:"Paraguay",nickname:"PY"},{name:"Peru",nickname:"PE"},{name:"Philippines",nickname:"PH"},{name:"Pitcairn Islands",nickname:"PN"},{name:"Poland",nickname:"PL"},{name:"Portugal",nickname:"PT"},{name:"Puerto Rico",nickname:"PR"},{name:"Qatar",nickname:"QA"},{name:"Réunion",nickname:"RE"},{name:"Romania",nickname:"RO"},{name:"Russia",nickname:"RU"},{name:"Rwanda",nickname:"RW"},{name:"Saint Barthélemy",nickname:"BL"},{name:"Saint Helena",nickname:"SH"},{name:"Saint Kitts and Nevis",nickname:"KN"},{name:"Saint Lucia",nickname:"LC"},{name:"Saint Martin",nickname:"MF"},{name:"Saint Pierre and Miquelon",nickname:"PM"},{name:"Samoa",nickname:"WS"},{name:"San Marino",nickname:"SM"},{name:"São Tomé and Príncipe",nickname:"ST"},{name:"Saudi Arabia",nickname:"SA"},{name:"Senegal",nickname:"SN"},{name:"Serbia",nickname:"RS"},{name:"Seychelles",nickname:"SC"},{name:"Sierra Leone",nickname:"SL"},{name:"Singapore",nickname:"SG"},{name:"Slovakia",nickname:"SK"},{name:"Slovenia",nickname:"SI"},{name:"Solomon Islands",nickname:"SB"},{name:"Somalia",nickname:"SO"},{name:"South Africa",nickname:"ZA"},{name:"South Georgia &amp; South Sandwich Islands",nickname:"GS"},{name:"South Korea",nickname:"KR"},{name:"Spain",nickname:"ES"},{name:"Sri Lanka",nickname:"LK"},{name:"St. Vincent &amp; Grenadines",nickname:"VC"},{name:"Sudan",nickname:"SD"},{name:"Suriname",nickname:"SR"},{name:"Svalbard and Jan Mayen",nickname:"SJ"},{name:"Swaziland",nickname:"SZ"},{name:"Sweden",nickname:"SE"},{name:"Switzerland",nickname:"CH"},{name:"Syria",nickname:"SY"},{name:"Taiwan",nickname:"TW"},{name:"Tajikistan",nickname:"TJ"},{name:"Tanzania",nickname:"TZ"},{name:"Thailand",nickname:"TH"},{name:"Timor-Leste",nickname:"TL"},{name:"Togo",nickname:"TG"},{name:"Tokelau",nickname:"TK"},{name:"Tonga",nickname:"TO"},{name:"Trinidad and Tobago",nickname:"TT"},{name:"Tunisia",nickname:"TN"},{name:"Turkey",nickname:"TR"},{name:"Turkmenistan",nickname:"TM"},{name:"Turks and Caicos Islands",nickname:"TC"},{name:"Tuvalu",nickname:"TV"},{name:"Uganda",nickname:"UG"},{name:"Ukraine",nickname:"UA"},{name:"Uruguay",nickname:"UY"},{name:"United Arab Emirates",nickname:"AE"},{name:"United Kingdom",nickname:"GB"},{name:"United States",nickname:"US"},{name:"U.S. Outlying Islands",nickname:"UM"},{name:"U.S. Virgin Islands",nickname:"VI"},{name:"Uzbekistan",nickname:"UZ"},{name:"Vanuatu",nickname:"VU"},{name:"Vatican City",nickname:"VA"},{name:"Venezuela",nickname:"VE"},{name:"Vietnam",nickname:"VN"},{name:"Wallis and Futuna",nickname:"WF"},{name:"Western Sahara",nickname:"EH"},{name:"Yemen",nickname:"YE"},{name:"Zambia",nickname:"ZM"},{name:"Zimbabwe",nickname:"ZW"}];
		for (var i = 0, len = countryList.length; i < len; i++) {
			var  disablednull=countryList[i].nickname;
			if(disablednull==null){
				htmlStr += '<option disabled=="disabled" value="' + countryList[i].name + '">' + countryList[i].name + '</option>';
			}else{
				htmlStr += '<option data-value="' + countryList[i].nickname + '" value="' + countryList[i].name + '">' + countryList[i].name + '</option>';
			}
		}
		$('#addressCountry').html(htmlStr);
	}
	// redner province
	function renderProvince(data) {
		var htmlStr = '<option value="state">State/Province</option>',
			defaultValue = $('#addressProvince').data('value');
		for (var i = 0, len = data.length; i < len; i += 1) {
			htmlStr += '<option value="' + data[i].stateprovinceName + '"'+ (defaultValue == data[i].stateprovinceName ? "selected" : "") +'>' + data[i].stateprovinceName + '</option>';
		}
		$('#addressProvince').html(htmlStr);
	}
	// renderd order list
	function renderOrderList(data) {
		var $orderList = $('<div class="order-list" />'),
			orderProductIdArr = [],
			orderItemIdArr = [],
			orderProductNumArr = [];
		data.forEach(function(item, idx) {
			var orderSkuList = '',
				optionArr = item.orderitemPskuIdnamestr.split(','),
				skuNameArr = item.orderitemPskuName.split(',');
			
			optionArr.forEach(function(item, idx) {
				orderSkuList += '<div class="order-sku-list-item">' +
					'<span class="name">'+ item +' :</span>' +
					'<span class="value">'+ skuNameArr[idx] +'</span>' +
				'</div>';
			});
			orderProductIdArr.push(item.orderitemPid);
			orderItemIdArr.push(item.orderitemId);
			orderProductNumArr.push(item.orderitemPskuNumber);
			var productLink = item.orderitemPseo ? ('${APP_PATH}/products/'+ item.orderitemPseo) : 'javascript:;';
			$orderList.append($('<div class="order-item">' +
				'<a href="'+ productLink +'" class="order-img lazyload" data-src="'+ item.orderitemProductMainimgurl +'"></a>' +
				'<div class="order-product">' +
					'<a class="order-product-name" href="'+ productLink +'">'+ item.orderitemPname +'</a>' +
					'<div class="order-sku-list">'+ orderSkuList +'</div>' +
					'<div class="order-product-num">' +
						'<div class="order-product-price">' +
							'<span class="product-define-price">$'+ ((item.orderitemProductOriginalprice + parseFloat(item.orderitemPskuMoneystr)) || 0).toFixed(2) +'</span>' +
							'<span class="product-now-price">$'+ (item.orderitemProductOriginalprice && item.orderitemProductAccoff ? accuracyCal((item.orderitemProductOriginalprice + parseFloat(item.orderitemPskuMoneystr)), item.orderitemProductAccoff) : 0.00) +'</span>' +
						'</div>' +
						'<span class="icon delete product-delete">' + '</span>' +
						'<div class="product-qty">' +
							'<span class="group-addon product-sub"><i class="icon sub"></i></span>' +
							'<input type="text" disabled class="product-num" value="' + (item.orderitemPskuNumber) + '" />' +
							'<span class="group-addon product-add"><i class="icon plus"></i></span>' +
						'</div>' +
					'</div>' +
				'</div>' +
			'</div>').data('orderitem', item));
		});
		$orderList.data('itemidarr', orderItemIdArr.join(',')).data('productidarr', orderProductIdArr.join(',')).data('itemnumarr', orderProductNumArr.join(','))
		var $cartReviewBox = $('<div class="cart-box">'+
				'<div class="cart-box-title"><span class="order-sort">2</span>Checkout Review</div>'+
				'<div class="cart-box-body"></div>'+
			'</div>');
		$cartReviewBox.find('.cart-box-body').append($orderList);
		$('.checkout-order-list').append($cartReviewBox);
		new LazyLoad($orderList.find('.lazyload'), {
			root: null,
			rootMargin: "10px",
			threshold: 0
		})
	}
	// render order couopons
	function renderOrderCoupons() {
		var $cartCouponBox = $('<div class="cart-box">'+
				'<div class="cart-box-title"><span class="order-sort">3</span>DISCOUNT CODES</div>'+
				'<div class="cart-box-body">'+
					'<div class="product-coupons hide"></div>'+
					'<div class="order-coupons">'+
						'<div class="order-coupon-list"></div>'+
						'<div class="order-coupon-group">'+
							'<input type="text" placeholder="Please enter code" />'+
							'<button id="order-check-coupon" class="btn btn-pink">APPLY</button>'+
						'</div>'+
						'<div class="order-coupon-tip"></div>'+
					'</div>'+
				'</div>'+
			'</div>');
		$('.checkout-coupons').html('').append($cartCouponBox);
		// coupon area list			
		getCouponAreaData(renderCouponAreaData);
	}
	// render order payment method
	function renderOrderPaymentMethod() {
		var $cartPaymentBox = $('<div class="cart-box">'+
				'<div class="cart-box-title"><span class="order-sort">4</span>PAYMENT METHOD</div>'+
				'<div class="cart-box-body">'+
					'<div class="order-payment" data-pay="0">' +
						'<div class="order-payment-item">'+
							'<div class="order-pay-inner custom-check">' +
								'<input type="radio" name="payment" id="payment-paypal" checked value="0">' +
								'<label for="payment-paypal">' +
									'<img src="${APP_PATH}/static/pc/img/paypal-1.png">' +
									'<span>Pay with PayPal account</span>' +
								'</label>' +
							'</div>' +
							'<div class="paypal-box">' +
						    	'<img src="${APP_PATH}/static/pc/img/paypal-3.svg">' +
						    	'<div class="paypal-des">After clicking “Complete order”, you will be redirected to PayPal to complete your purchase securely.</div>' +
						    '</div>' +
						'</div>' +
						'<div class="order-payment-item">'+
							'<div class="order-pay-inner custom-check">' +
								'<input type="radio" name="payment" id="payment-paypalcard" value="1">' +
								'<label for="payment-paypalcard">' +
									'<img src="${APP_PATH}/static/pc/img/paypal-2-2.png">' +
									'<sapn style="white-space: nowrap;">Pay with Paypal Credit card</span>' +
								'</label>' +
							'</div>'+
							'<div class="paypal-box hide">' +
						    	'<img src="${APP_PATH}/static/pc/img/paypal-3.svg">' +
						    	'<div class="paypal-des">After clicking “complete order”, you will be redirected to PayPal to complete your purchase safely with several credit cards supported by PayPal.</div>' +
						    '</div>' +
						'</div>' +
						'<div class="card-element-box mask hide">' +
					    	'<div class="spinner"></div>' +
					    	'<div id="card-element"></div>' +
					    	'<p id="card-error" role="alert"></p>' +
					    '</div>' +
					'</div>'+
				'</div>'+
			'</div>');
		$('.checkout-pay-method').append($cartPaymentBox);
	}
	// render order buyer message
	function renderOrderBuyerMsg() {
		var $cartBuyerMsgBox = $('<div class="cart-box">'+
				'<div class="cart-box-title"><span class="order-sort">5</span>ADDITIONAL INFORMATION</div>'+
				'<div class="cart-box-body">'+
					'<div class="order-buyer-msg"><textarea rows="2" placeholder="Any Request,Notes Here."></textarea></div>'+
				'</div>'+
			'</div>');
		$('.checkout-buy-msg').append($cartBuyerMsgBox);
	}
	// render order cal
	function rednerOrderCal() {
		var calOrder = calOrderList();
		var $cartCalBox = $('<div class="cart-box">'+
				// '<div class="cart-box-title"></div>'+
				'<div class="cart-box-body">'+
					'<div class="order-cal">' +
						'<div class="order-cal-item"><span class="name">prototal</span><span class="value order-cal-prototal">$'+ (calOrder.prototal).toFixed(2) +'</span></div>' +
						'<div class="order-cal-item"><span class="name">coupon</span><span class="value order-cal-coupon">-$'+ (calOrder.coupon).toFixed(2) +'</span></div>' +
						'<div class="order-cal-item"><span class="name">shipping</span><span class="value order-cal-shipping">$'+ (calOrder.shipping).toFixed(2) +'</span></div>' +
						'<div class="order-cal-item"><span class="name">subtotal</span><span class="value order-cal-subtotal" data-price="'+ (calOrder.subtotal).toFixed(2) +'">$'+ (calOrder.subtotal).toFixed(2) +'</span></div>' +
						'<div class="order-cal-btn">' +
							'<a href="javascript:;" id="pay-now" class="btn btn-pink">Complete Order</a>' +
							'<button id="payment-form" class="btn btn-pink hide" disabled><div class="spinner hide"></div><span class="btn-text">Pay Securely Now</span></button>' +
						'</div>' +
					'</div>'+
				'</div>'+
			'</div>');
		$('.checkout-order-cal').append($cartCalBox);
	}
	// check user input address info
	function checkInputAdressInfo() {
		var flag = true;
		for(var idx = 0, len = $('.address-box .form-group').length; idx < len; idx += 1) {
			var item = $('.address-box .form-group')[idx];
			if ($(item).find('.form-control').data('status')) continue;
			if (!$(item).find('.form-control').val() || $(item).find('.form-control').val() == 'state') {
				mlModalTip('Address information <i style="color: #f00">'+ $(item).find('.form-label').text() +"</i> can't be empty !");
				$(item).find('.form-control').focus();
				flag = false;
				break;
			}
		}
		return flag;
	}
	// check couopon code
	function checkCouponCode(reqData, callback) {
		$.ajax({
			url: '${APP_PATH}/MlbackCoupon/getOneMlbackCouponDetailByCode',
			data: JSON.stringify(reqData),
			type: 'post',
			dataType: 'json',
			contentType: 'application/json',
			success: function (data) {
				if (data.code == 100) {
					callback && callback(data.extend);
				} else {
					sysModalTip();
				}
			},
			error: function(err) {
				sysModalTip();
			}
		});
	}
	// get order address
	function getOrderAddress() {
		var addressData = {};
		$('.address-box .form-control').each(function(idx, item) {
			if (item.name == 'addressCountry') addressData['addressCountryCode'] = $(item).find('option:checked').data('value');
			addressData[item.name] = (item.value || '').trim();
		});
		return addressData;
	}
	// init order address
	function initOrderAddress(data) {
		$('.address-box .form-control').each(function(idx, item) {
			if (item.name == 'addressProvince') $(item).data('value', data[item.name]);
			$(item).val(data[item.name]);
		});
		isBackFill = true;
	}
	// get order pay info
	function getOrderPayInfo() {
		var couponData = $('.order-coupon-group').data('coupon') || {};
		return {
			"orderId": $('.order-list').data('orderid') || null,
			"orderOrderitemidstr": $('.order-list').data('itemidarr'),
			"orderCouponId": (couponData.mlbackCouponOne && couponData.mlbackCouponOne.couponId) || '',
			"orderCouponCode": (couponData.mlbackCouponOne && couponData.mlbackCouponOne.couponCode) || '',
			"orderPayPlate": $('input[name="payment"]:checked').val(),
			"orderProNumStr": $('.order-list').data('itemnumarr'),
			"orderBuyMess": $('.order-buyer-msg textarea').val(),
			"addressinfoId": $('#addressId').val(),
			"orderCreatetime" : $('.order-list').data('productidarr'),
			"orderMotifytime": checkoutviewId,
		}
	}
	// initial order for checkout 
	function initialOrder() {
		// 1
		renderCountry();
		// paypal address check
		var $paypalTip = $('.paypal-error-tip');
		if ('${sessionScope.PaypalErrorName}' == "VALIDATION_ERROR") {
			$paypalTip.hasClass('hide') && $paypalTip.removeClass('hide');
		} else {
			!$paypalTip.hasClass('hide') && $paypalTip.addClass('hide');
		}
		$('#addressEmail, #addressCountry, #addressProvince, #addressCity, #addressPost').on('foucs', function() {
			!$paypalTip.hasClass('hide') && $paypalTip.addClass('hide');
		});
		// userinfo initial
		getUserInfo(function(data) {
			var addressData = data.mlfrontAddressOne;
			/* if (data.usertype) { */
				addressData && initOrderAddress(addressData);
				$('.order-address-shipping').data('shipping', data.areafreightMoney).find('.value', '$' + data.areafreightMoney);
			/* } */
			countryCombineWithProvince();			
		});
		renderOrderPaymentMethod(); // 2
		renderOrderBuyerMsg(); // 3
		renderOrderCoupons(); // 4
		// 5
		getProductOrderList(function(data) {
			var orderListData = data.mlfrontOrderItemList;
			if (!orderListData.length) {
				mlModalTip("Don't have to pay for goods, please to add items in the shopping cart, and then to pay !");
				goToCartList();
				return ;
			}
			renderOrderList(orderListData);
			renderFreeGift();
			$('.order-list').data('orderid', data.orderId);
		});
		rednerOrderCal(); // 6
		isBackFill && collectShippingAddress && collectShippingAddress();
	}
	var hasProvince = true;
	// initial
	setTimeout(initialOrder, 0);
	// event
	$("#addressCountry").on('change', function() {
		countryCombineWithProvince();
		resetOrderCal();
	});
	// add product
	$(document.body).on('click', '.product-add', function() {
		productAdd($(this), function(el, num) {
			updateOrderNum(el, num, resetOrderCal);
		});
	});
	// sub product
	$(document.body).on('click', '.product-sub', function() {
		productSub($(this), function(el, num) {
			updateOrderNum(el, num, resetOrderCal);
		});
	});
	// delete product
	$(document.body).on('click', '.product-delete', function() {
		deleteOrderProduct($(this).parents('.order-item'), function(id) {
			var productIdArr = $('.order-list').data('productidarr').split(',');
			var orderProductNumArr = $('.order-list').data('itemnumarr').split(',');
			var orderCartItemIdArr = $('.order-list').data('itemidarr').split(',')
			orderCartItemIdArr.forEach(function(item, idx) {
				if (item == id) {
					productIdArr.splice(idx, 1);
					orderCartItemIdArr.splice(idx, 1);
					orderProductNumArr.splice(idx, 1);
				}
			});
			$('.order-list').data('productidarr', productIdArr.join(','));
			$('.order-list').data('itemidarr', orderCartItemIdArr.join(','));
			$('.order-list').data('itemnumarr', orderProductNumArr.join(','));
			// reset order cal
			resetOrderCal();
		});
	});
	// check input coupon-code
	$(document.body).on('click', '#order-check-coupon', function() {
		var couponCode = $('.order-coupon-group input').val().trim();
		if (couponCode) {
			checkCouponCode({
				"couponCreatetime": $('.order-list').data('productidarr'),
				"couponCode": couponCode
			}, function(data) {					
				$('.order-coupon-group').data('coupon', data);
				resetOrderCal();
			});	
		} else {
			mlModalTip('Please enter a valid coupon code !');
		}
	});
	// pay event
	$(document.body).on('click', '#pay-now', function() {
		if (checkInputAdressInfo()) {
			orderSaveAddress(getOrderAddress(), function(data) {
				$('#addressId').val(data.addressId);
				var productIdArr = $('.order-list').data('productidarr') ? $('.order-list').data('productidarr').split(',') : [];
				var orderMoney = $('.order-cal-subtotal').data('price');
				fbq('track', 'AddPaymentInfo', {
					content_ids: productIdArr,
					content_type: 'product',
					value: orderMoney,
					currency: 'USD'
				});
				payLoading();
				orderPay(getOrderPayInfo(), goToPay);
			});
		}
	});
	$('#addressEmail').on('change', function() {
		$('.order-coupons').find('input').val('');
		$('.order-coupons').find('.order-coupon-tip').html('');
		$('.order-coupon-group').data('coupon', '');
		setTimeout(resetOrderCal);
	});
</script>
<script>
function goToPay() {	
	$.ajax({
		url: '${APP_PATH}/paypal/mpay?paypalPlatFrom=' + $('input[name="payment"]:checked').val(),
		type: 'post',
		dataType: 'json',
		contentType: 'application/json',
		success: function (data) {
			if (data.code == 100) {
				if ('' + data.extend.ifPaypalCheckSuccess == '0') {
					window.location.reload();
				}
				
				if ('' + data.extend.ifPaypalCheckSuccess == '1') {
					window.location.href = data.extend.redirectUrl;
				}
				
				if ('' + data.extend.ifPaypalCheckSuccess == '2') {
					hidePayLoading();
					$('html').animate({scrollTop: 0}, 500);
					setTimeout(function(msg) {
						var payModalTip = createModal({
							body: {
								html: '<p>'+ msg +'</p>',
							}
						});
					}, 600, data.extend.errorDetail);
				}
			} else {
				sysModalTip();
			}
		},
		error: function () {
			sysModalTip();
		}
    });
}
</script>
<script>
$('.website-policy-item').on('click', function(e) {
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
$(document.body).on('change', 'input[type="radio"][name="payment"]', function() {
	$('.paypal-box').addClass('hide');
	$(this).parents('.order-payment-item').find('.paypal-box').removeClass('hide');
});
</script>
