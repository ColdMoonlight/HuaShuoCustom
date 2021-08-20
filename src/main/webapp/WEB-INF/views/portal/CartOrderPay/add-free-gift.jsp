<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<script>
	function renderFreeGift() {
		var htmlStr = '<div class="free-gift-list hide">' +
			'<div style="text-align: center; padding: 1rem 0; border-top: 1px solid #ccc;">Buy More, get More Free Gift.</div>' +
			'<div class="free-gift-item free-1">' +
				'<div class="free-gift-img lazyload" data-src="${APP_PATH}/static/pc/img/free-gift/free-gift-1.jpg"></div>' +
				'<div class="free-gift-name">Megalook Free Gift Silk Headband High Quality 1pc</div>' +
				'<div class="free-gift-desc"><div>Free Gift</div><div style="color: #ff1671;">$0.00</div></div>' +
			'</div>' +
			'<div class="free-gift-item free-1">' +
				'<div class="free-gift-img lazyload" data-src="${APP_PATH}/static/pc/img/free-gift/free-gift-2.jpg"></div>' +
				'<div class="free-gift-name">Megalook Free Gift Net Cap High Quality Stretch Stocking Caps Wig Mesh Wig Cap</div>' +
				'<div class="free-gift-desc"><div>Free Gift</div><div style="color: #ff1671;">$0.00</div></div>' +
			'</div>' +
			'<div class="free-gift-item free-2 hide">' +
				'<div class="free-gift-img lazyload" data-src="${APP_PATH}/static/pc/img/free-gift/free-gift-3.jpg"></div>' +
				'<div class="free-gift-name">Megalook Free Gift 1PC Edge Brush Help U make baby hair (Color Send Randomly)</div>' +
				'<div class="free-gift-desc"><div>Free Gift</div><div style="color: #ff1671;">$0.00</div></div>' +
			'</div>' +
		'</div>';

		$('.cart-list, .order-list, .payment-order .payment-product').append($(htmlStr));
		
		new LazyLoad($('.free-gift-img'), {
			root: null,
			rootMargin: "10px",
			threshold: 0
		});
	}
	
	function showFreeGift(price) {
		$('.free-gift-list').removeClass('hide');
		if (price < 100) {
			$('.free-gift-list').find('.free-gift-item.free-2').addClass('hide');
		} else {
			$('.free-gift-list').find('.free-gift-item.free-2').removeClass('hide');
		}
	}
</script>