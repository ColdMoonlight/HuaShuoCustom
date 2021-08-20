<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<script>
	function collectShippingAddress() {
		var checkoutView = {};
    	var productSeoArr = [], productNumArr = [], skuCodeArr = [], productPriceArr = [], skuIdNameArr = [], skuNameArr = [];

		checkoutView['checkoutviewUserfirstname'] = $('#addressUserfirstname').val();
		checkoutView['checkoutviewUserlastname'] = $('#addressUserlastname').val();
		checkoutView['checkoutviewEmail'] = $('#addressEmail').val();
		checkoutView['checkoutviewTelephone'] = $('#addressTelephone').val();
		checkoutView['checkoutviewAddressdetail'] = $('#addressDetail').val();
		checkoutView['checkoutviewCity'] = $('#addressCity').val();
		checkoutView['checkoutviewPost'] = $('#addressPost').val();

		checkoutView['checkoutviewCountry'] = $('#addressCountry').val();
		checkoutView['checkoutviewCountryCode'] = $('#addressCountry').find('option:selected').data('value');
		
		if ($('.address-province').hasClass('hide')) {
			checkoutView['checkoutviewProvince'] = '';
		} else {
			checkoutView['checkoutviewProvince'] = $('#addressProvince').val();
		}

		checkoutView['checkoutviewProvincecode'] = '';
		
		checkoutView['checkoutviewOrderId'] = $('.order-list').data('orderid') || '';
		
		$('.order-item').each(function(idx, item) {
			var oData = $(item).data('orderitem');
			if (oData) {
				productSeoArr.push(oData.orderitemPseo || '');
				productNumArr.push(oData.orderitemPskuNumber || '');
				skuCodeArr.push(oData.orderitemPskuCode || '');
				productPriceArr.push(parseFloat(accuracyCal(((oData.orderitemProductOriginalprice || 0) + parseFloat(oData.orderitemPskuMoneystr || 0)), (oData.orderitemProductAccoff || 100))) || '');
				skuIdNameArr.push(oData.orderitemPskuIdnamestr || '');
				skuNameArr.push(oData.orderitemPskuName || '');
			} else {
				productSeoArr.push('');
				productNumArr.push('');
				skuCodeArr.push('');
				productPriceArr.push('');
				skuIdNameArr.push('');
				skuNameArr.push('');
			}
		});

		checkoutView['checkoutviewOrderProseoStr'] = productSeoArr.join(',');
		checkoutView['checkoutviewOrderPronumStr'] = productNumArr.join(',');
		checkoutView['checkoutviewOrderOrderitempskucode'] = skuCodeArr.join(',');
		checkoutView['checkoutviewOrderitempskumoneystr'] = productPriceArr.join(',');
		checkoutView['checkoutviewOrderitempskuidnamestr'] = skuIdNameArr.join('.');
		checkoutView['checkoutviewOrderitempskuname'] = skuNameArr.join('.');

		$.ajax({
			url: '${APP_PATH}/MlfrontCheckoutView/save',
			data: JSON.stringify(checkoutView),
			type: 'post',
			dataType: 'json',
			contentType: 'application/json',
			success: function (data) {
				if (data.code == 100) {
					checkoutviewId = data.extend.mlfrontCheckoutView && data.extend.mlfrontCheckoutView.checkoutviewId;
				}
			}
		});
	}
	$('#addressCountry').on('change', function() {
		$('.address-province').hasClass('hide') && collectShippingAddress();
	});
	$('#addressProvince').on('change', function() {
		 collectShippingAddress();
	});
</script>