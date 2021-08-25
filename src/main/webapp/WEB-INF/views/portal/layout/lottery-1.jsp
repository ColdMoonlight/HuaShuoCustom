<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<style>
    .lottery {
        position: fixed;
		z-index: 9999999999;

        display: flex;
        flex-direction: column;
        justify-content: space-between;

        background-color: #fff;
        background-repeat: no-repeat;
        background-size: cover;

        -webkit-transition: all .3s ease-in-out;
        -moz-transition: all .3s ease-in-out;
        transition: all .3s ease-in-out;
    }
    .lottery-logo {
        width: 100%;
        height: 3rem;
        background-image: url('${APP_PATH}/static/custom/lottery/sector-logo.png');
        background-repeat: no-repeat;
        background-size: contain;
        background-position: top center;
    }
    .lottery-close {
    	position: absolute;
    	top: 3rem;
    	right: 1rem;
    	z-index: 9;
    	
    	width: 2rem;
    	height: 2rem;
    	font-size: 1.5rem;
    	color: #fff;
    	background-color: rgba(0, 0, 0, .1);
    	border-radius: .2rem;
    	cursor: pointer;
    }
    .lottery-pc-show,
    .lottery-wap-show {
    	opacity: 0;
    	display: none;
    	visibility: hidden;

        -webkit-transition: all .3s ease-in-out;
        -moz-transition: all .3s ease-in-out;
        transition: all .3s ease-in-out;
    }	    
    .lottery-wap-show {
    	position: absolute;
    	top: 0;
    	right: 0;
    	z-index: 999;
    	width: 150px;
    	height: 150px;
    }
    .lottery-container {
        display: flex;
        flex-grow: 1;

        -webkit-transition: all .3s ease-in-out;
        -moz-transition: all .3s ease-in-out;
        transition: all .3s ease-in-out;
    }
    /* .lottery circle */
    .lottery-circle-container {            
        position: relative;
        display: flex;
        justify-content: center;
    }
    .lottery-start {
        position: absolute;
        top: 50%;
        left: 50%;
        background-size: 100% 100%;
        background-repeat: no-repeat;
        background-position: center;
        cursor: pointer;
    }
    .lottery-circle {
        position: relative;
        overflow: hidden;
        border-radius: 50%;
        background-repeat: no-repeat;
        background-size: 100% 100%;
    }
    .lottery-circle-item {
        position: absolute;
        top: 50%;
        left: 50%;

        display: flex;
        justify-content: center;
        align-items: center;
        background-repeat: no-repeat;
        background-position: bottom 1rem center;
        color: transparent;
    }
    .lottery-circle-item:nth-of-type(1) {
        -webkit-transform: translate(-50%, -50%);
        -moz-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
    }
    .lottery-circle-item:nth-of-type(2) {
        -webkit-transform: translate(-50%, -50%) rotate(45deg);
        -moz-transform: translate(-50%, -50%) rotate(45deg);
        transform: translate(-50%, -50%) rotate(45deg);
    }
    .lottery-circle-item:nth-of-type(3) {
        -webkit-transform: translate(-50%, -50%) rotate(90deg);
        -moz-transform: translate(-50%, -50%) rotate(90deg);
        transform: translate(-50%, -50%) rotate(90deg);
    }
    .lottery-circle-item:nth-of-type(4) {
        -webkit-transform: translate(-50%, -50%) rotate(135deg);
        -moz-transform: translate(-50%, -50%) rotate(135deg);
        transform: translate(-50%, -50%) rotate(135deg);
    }
    .lottery-circle-item:nth-of-type(5) {
        -webkit-transform: translate(-50%, -50%) rotate(180deg);
        -moz-transform: translate(-50%, -50%) rotate(180deg);
        transform: translate(-50%, -50%) rotate(180deg);
    }
    .lottery-circle-item:nth-of-type(6) {
        -webkit-transform: translate(-50%, -50%) rotate(-135deg);
        -moz-transform: translate(-50%, -50%) rotate(-135deg);
        transform: translate(-50%, -50%) rotate(-135deg);
    }
    .lottery-circle-item:nth-of-type(7) {
        -webkit-transform: translate(-50%, -50%) rotate(-90deg);
        -moz-transform: translate(-50%, -50%) rotate(-90deg);
        transform: translate(-50%, -50%) rotate(-90deg);
    }
    .lottery-circle-item:nth-of-type(8) {
        -webkit-transform: translate(-50%, -50%) rotate(-45deg);
        -moz-transform: translate(-50%, -50%) rotate(-45deg);
        transform: translate(-50%, -50%) rotate(-45deg);
    }
    /* lottery-title */
    .lottery-title {
    	padding: 1rem 0;
    	text-align: center;
    	color: #fff;
    }
    .lottery-title-name {
    	font-size: 1.6rem;
    }
    .lottery-title-info {
    	font-size: 1rem;
    }
    /* lottery-email */
    .lottery-email {
    	padding: .5rem 1rem;
    }
    .lottery-email-tip {
    	height: 1.5rem;
    	font-style: italic;
    	font-size: .75rem;
    	line-height: 1.5rem;
    	text-align: center;
    	color: #000;
    }
    .lottery-email-input,
    .lottery-email-check {
    	width: 100%;
    }
    @keyframes shake {
		0%, 100% {
			-webkit-transform: translateX(0);
			-moz-transform: translateX(0);
			transform: translateX(0);
		}
		10%, 30%, 50%, 70%, 90% {
			-webkit-transform: translateX(-0.5rem);
			-moz-transform: translateX(-0.5rem);
			transform: translateX(-0.5rem);
		}
		20%, 40%, 60%, 80% {
			-webkit-transform: translateX(0.5rem);
			-moz-transform: translateX(0.5rem);
			transform: translateX(0.5rem);
		}
	}
	.shake {
		-webkit-animation: shake 1s linear 1;
		-moz-animation: shake 1s linear 1;
		animation: shake 1s linear 1;
	}
	@keyframes pulse {
		from {
			-webkit-transform: scale3d(1, 1, 1);
			-moz-transform: scale3d(1, 1, 1);
			transform: scale3d(1, 1, 1);
		}
		
		50% {
			-webkit-transform: scale3d(.75, .75, .75);
			-moz-transform: scale3d(.75, .75, .75);
			transform: scale3d(.75, .75, .75);
		}
		
		to {
			-webkit-transform: scale3d(1, 1, 1);
			-moz-transform: scale3d(1, 1, 1);
			transform: scale3d(1, 1, 1);
		}
	}
	.pulse {
		-webkit-animation: pulse .5s ease-in-out 2;
		-moz-animation: pulse .5s ease-in-out 2;
		animation: pulse .5s ease-in-out 2;
	}
	/* lottery result */
	.lottery-result {
		position: absolute;
		top: 0;
		right: 0;
		bottom: 0;
		left: 0;
		
		display: flex;
		justify-content: center;
		align-items: center;
		
		background-color: rgba(0, 0, 0, .35);
	}
	.lottery-result-box {
		overflow: hidden;
		width: 80%;
		height: auto;
		border-radius: .5rem;
	}
	.lottery-result-show {
		padding-top: 1rem;
		background-color: #fff;
		font-family: "Arial";
		text-align: center;
	}
	.lottery-result-coupon {
		display: flex;
		justify-content: center;
		align-items: center;
		width: 183px;
		height: 72px;
		margin: 0 auto;
		font-weight: 500;
		font-size: 1.6rem;
    	color: #fff;
		background-image: url('${APP_PATH}/static/custom/lottery/lottery-result-coupon.png');
		background-repeat: no-repeat;
		background-position: center;
	}
	.lottery-result-text-discount {
		font-family: "Helvetica Neue", Verdana, Arial, sans-serif;
		font-size: 2rem;
		font-weight: 400;
		color: #333;
	}
	.lottery-result-text-desc {
		font-size: 1.2rem;
		font-weight: 500;
		color: #333;
	}
	.lottery-result-text-code {
		padding: .25rem;
		font-weight: 600;
		font-size: 2.5rem;
		color: #9416fc;
	}
	.lottery-result-coupon-offset {
		padding: .5rem 0;
		font-size: 1rem;
		font-weight: 700;
		border-top: 1px dotted #333;
		background-color: #ecb9ff;
	}
    @media only screen and (max-width: 869px) {
		.lottery {
			bottom: 0;
			left: 0;
			width: 100vw;
			height: 100vh;
        	background-image: url('${APP_PATH}/static/custom/lottery/sector-bg-wap.jpg');
		}
		.lottery-container {
			flex-direction: column;
			justify-content: center;
		}
       	.lottery-circle {
	        width: 300px;
	        height: 300px;
        	background-image: url('${APP_PATH}/static/custom/lottery/sector-circle-wap.png');
	    }
	    .lottery-start {
			width: 77.6px;
			height: 95px;
			
	        margin-top: -56.2px;
	        margin-left: -38.8px;
        	background-image: url('${APP_PATH}/static/custom/lottery/sector-start-wap.png');
	    }
		.lottery-circle-item {
			width: 90px;
			height: 90px;
        	background-size: 70%;
        }
        .lottery-circle-item:nth-of-type(1) {
	        margin-top: -100px;
	    }
	    .lottery-circle-item:nth-of-type(2) {
	        margin-top: -70.710678118px;
	        margin-left: 70.710678118px;
	    }
	    .lottery-circle-item:nth-of-type(3) {
	        margin-left: 100px;
	    }
	    .lottery-circle-item:nth-of-type(4) {
	        margin-top: 70.710678118px;
	        margin-left: 70.710678118px;
	    }
	    .lottery-circle-item:nth-of-type(5) {
	        margin-top: 100px;
	    }
	    .lottery-circle-item:nth-of-type(6) {
	        margin-top: 70.710678118px;
	        margin-left: -70.710678118px;
	    }
	    .lottery-circle-item:nth-of-type(7) {
	        margin-left: -100px;
	    }
	    .lottery-circle-item:nth-of-type(8) {
	        margin-top: -70.710678118px;
	        margin-left: -70.710678118px;
	    }
	    .lottery.waiting {
	    	width: 300px;
	    	height: 300px;
	    	background: none;
	    	bottom: -150px;
	    	left: -150px;
	    }
	    .lottery.waiting .lottery-logo,
	    .lottery.waiting .lottery-close,
	    .lottery.waiting .lottery-other {
	    	opacity: 0;
	    	display: none;
	    	visibility: hidden;
	    }
	    .lottery.waiting .lottery-wap-show {
	    	opacity: 1;
	    	display: block;
	    	visibility: visible;
	    	cursor: pointer;
	    }
		.lottery.waiting .lottery-wap-show::after {
			content: '';
			position: absolute;
			top: .5rem;
			right: .5rem;
			display: block;
			width: 1rem;
			height: 1rem;
			border-width: 3px;
			border-style: solid;
			border-color: #f17161 #f17161 transparent transparent;
			-webkit-animation: bounce-down 1.5s linear infinite;
			-moz-animation: bounce-down 1.5s linear infinite;
			animation: bounce-down 1.5s linear infinite;
		}
	    @keyframes bounce-down {
			25%, 75% {
				-webkit-transform: translate(-.5rem, .5rem);
				-moz-transform: translate(-.5rem, .5rem);
				transform: translate(-.5rem, .5rem);
			}
			50%, 100% {
				-webkit-transform: translate(0, 0);
				-moz-transform: translate(0, 0);
				transform: translate(0, 0);
			}
		}
		.lottery-result-box {
			width: 80%;
		}
    }
    @media only screen and (min-width: 870px) {
    	.lottery {
    		top: 50%;
    		width: 30px;
    		height: 100px;
        	background-image: url('${APP_PATH}/static/custom/lottery/sector-bg-pc.jpg');
    	}
    	.lottery-pc-show {
    		position: absolute;
    		top: 0;
    		right: 0;

    		display: flex;
    		opacity: 1;
    		visibility: visible;
 
    		justify-content: center;
    		align-items: center;
    		width: 100px;
    		height: 20px;
    		font-weight: 500;
    		font-size: 1rem;
    		color: #fff;
 
    		transform-origin: 50px 50px;
    		transform: rotate(90deg);
    	}
    	.lottery-logo,
    	.lottery.show .lottery-pc-show,
    	.lottery-container {
    		display: none;
    		opacity: 0;
    		visibility: hidden;
    	}
    	.lottery-close {
    		right: 2rem;
    	}
		.lottery-container {
			justify-content: space-between;
			transform: translateX(-200px);
		}
		.lottery-circle-container {
			align-items: center;
			transform: rotate(90deg);
		}
		.lottery-other {
			display: flex;
			flex-direction: column;
			justify-content: center;
			position: relative;
			transform: translateX(50px);
		}
       	.lottery-circle {
	        width: 450px;
	        height: 450px;
        	background-image: url('${APP_PATH}/static/custom/lottery/sector-circle-pc.png');
	    }
	    .lottery-start {
	        width: 110px;
	        height: 134.8px;
			
	        margin-top: -79.8px;
	        margin-left: -55px;
        	background-image: url('${APP_PATH}/static/custom/lottery/sector-start-pc.png');
	    }
		.lottery-circle-item {
			width: 125px;
			height: 125px;
        	background-size: 70%;
        }
        .lottery-circle-item:nth-of-type(1) {
	        margin-top: -150px;
	    }
	    .lottery-circle-item:nth-of-type(2) {
	        margin-top: -106.066017177px;
	        margin-left: 106.066017177px;
	    }
	    .lottery-circle-item:nth-of-type(3) {
	        margin-left: 150px;
	    }
	    .lottery-circle-item:nth-of-type(4) {
	        margin-top: 106.066017177px;
	        margin-left: 106.066017177px;
	    }
	    .lottery-circle-item:nth-of-type(5) {
	        margin-top: 150px;
	    }
	    .lottery-circle-item:nth-of-type(6) {
	        margin-top: 106.066017177px;
	        margin-left: -106.066017177px;
	    }
	    .lottery-circle-item:nth-of-type(7) {
	        margin-left: -150px;
	    }
	    .lottery-circle-item:nth-of-type(8) {
	        margin-top: -106.066017177px;
	        margin-left: -106.066017177px;
	    }
		.lottery-title-info {
			margin-top: .5rem;
		}
		.lottery.show {
			top: 0;
			width: 800px;
			height: 100vh;
			box-shadow: 0 0 2px 2px #aaa;
		}
		.lottery.show .lottery-container,
    	.lottery.show .lottery-logo {
    		display: flex;
    		opacity: 1;
    		visibility: visible;
    	}
		.lottery-result-box {
			width: 60%;
		}
    }
</style>
<div class="lottery waiting hide">
	<div class="lottery-wap-show"></div>
	<div class="lottery-pc-show">Click Here</div>
	<div class="lottery-close icon close"></div>
    <div class="lottery-logo"></div>
    <div class="lottery-container">
        <div class="lottery-circle-container">
            <div class="lottery-circle"></div>
            <div class="lottery-start"></div>
        </div>
        <div class="lottery-other">
	        <div class="lottery-title">
	            <div class="lottery-title-name">Start Your New Look Now!</div>
	            <div class="lottery-title-info">Free Wig/ Big Coupon/ IPad Pro/Vip Discount</div>
	        </div>
	        <div class="lottery-email">
	            <input class="lottery-email-input" placeholder="Enter your email, 100% winning" />
	            <div class="lottery-email-tip"></div>
	        	<div class="btn btn-black lottery-email-check">Try Your Lucky Now &nbsp;>></div>
	        </div>
        </div>
    </div>
</div>
<script>
	function isValidEmail(email) {
		var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		var flag = false;
		if (!email) {
			lotteryEmailInputAnimate();
	    	$('.lottery-email-tip').text('Please enter a valid email address first!');
		}
		if (!reg.test(email)) {
			lotteryEmailInputAnimate();
			$('.lottery-email-tip').text('Please enter a valid email address!');
		} else {
			flag = true;
		}
		return flag;
	}
    function easeInOutCubic (x) {
		return x < 0.5 ?
			4 * x * x * x :
			1 - Math.pow( -2 * x + 2, 3 ) / 2;
    }
    function startLottery(time, duration, n, email, callback) {
        var len = time / duration;
        var sector = 1080 - (n - 1) * 45;
        var i = 0;
        var timer = null;
        var timeStart = Date.now();

        function recursive () {
            ++i;
            $('.lottery-circle').css('transform', 'rotate('+ (easeInOutCubic(i / len) * sector) +'deg)');

            if (i == len) {
                clearTimeout(timer);
                callback && callback();
                return ;
            } else {
                timer = setTimeout(recursive, duration);
            }
        }
        isGaming = true;
        canLottery = false;
        $('.lottery-email-tip').text('');
        registerEmail(email);
        
        timer = setTimeout(recursive, duration);
    }
    function getLotteryData(callback) {
    	$.ajax({
    		url: '${APP_PATH}/MlbackCoupon/getMlbackCouponShowByLuckDrawType',
    		type: 'post',
    		dataType: 'json',
    		contentType: 'application/json',
    		success: function (data) {
    			if (data.code == 100) {
    				callback && callback(data.extend);
    			}
    		}
    	});
    }
    function renderLotteryItems(data) {
    	var html = '';
    	data.mlbackCouponResList.length && data.mlbackCouponResList.forEach(function(item, idx) {
    		if (item.couponId == data.luckDrawDate.luckDrawCouponId) {
    			$('.lottery-start').data('coupon', item).data('index', (idx + 1))
    		}
    		html += '<div class="lottery-circle-item" style="background-image: url('+ item.couponImgUrl +');"></div>';
    	});
    	$('.lottery-circle').html(html);
    	$('.lottery').removeClass('hide');
    }
    function checkInputEmail(email, callback) {
    	payLoading();
    	$.ajax({
    		url: '${APP_PATH}/MlbackCoupon/checkCouponLuckDrawResultAndUserEmail',
    		type: 'post',
    		dataType: 'json',
    		data: {
    			userEmail: email,
    			couponId: $('.lottery-start').data('coupon').couponId
    		},
    		async: false,
    		success: function (data) {
    			if (data.code == 100) {
    				callback && callback(data.extend.emailIsCan ? false : true);
    			}
    		},
    		error: function () {
    			$('.lottery-email-tip').text('The email address validation failed, please try again!');
    		},
    		complete: function() {
    			hidePayLoading();
    		}
    	});
    }
    function registerEmail(email) {
    	$.ajax({
    		url: '${APP_PATH}/MlbackCoupon/getCouponLuckDrawResultAndUserEmail',
    		type: 'post',
    		dataType: 'json',
    		data: {
    			userEmail: email,
    			couponId: $('.lottery-start').data('coupon').couponId
    		},
    		success: function (data) {
    			isUsed = true;
    		}
    	});
    }
    function lotteryEmailInputAnimate() {
    	$('.lottery-email-input').addClass('shake');
    	setTimeout(function() {$('.lottery-email-input').removeClass('shake')}, 1000);
    }
    function lotteryEmailChecktAnimate() {
    	$('.lottery-email-check').addClass('shake');
    	setTimeout(function() {$('.lottery-email-check').removeClass('shake')}, 1000);
    }
    function lotteryStartAnimate() {
    	$('.lottery-start').addClass('pulse');
    	setTimeout(function() {$('.lottery-start').removeClass('pulse')}, 1000);
    }
    function resetLottery() {
    	$('.lottery-email-input').val('');
    	$('.lottery-email-tip').text('');
    	$('.lottery-circle').removeAttr('style');
    	isUsed && getLotteryData(function(data) {
    		data.mlbackCouponResList.length && data.mlbackCouponResList.forEach(function(item, idx) {
        		if (item.couponId == data.luckDrawDate.luckDrawCouponId) {
        			$('.lottery-start').data('coupon', item).data('index', (idx + 1));
        			isUsed = false;
        		}
        	});
    	});
    }
    function lotteryResult() {
    	setTimeout(function() {
	    	var data = $('.lottery-start').data('coupon');
	    	var couponVal = '';
	    	if (data.couponType == '0') {
	    		couponVal = '$' + data.couponPrice;
	    	}
	    	if (data.couponType == '1') {
	    		couponVal = data.couponPriceoff + '%';
	    	}
	    	var html =  '<div class="lottery-result">' +
		    	'<div class="lottery-result-box">' +
		    		'<div class="lottery-result-show">' +
		    			'<div class="lottery-result-coupon">'+ couponVal +' Coupon</div>' +
		    			'<div class="lottery-result-text-discount">Discount</div>' +
		    			'<div class="lottery-result-text-desc">Your Discount Code is :</div>' +
		    			'<div class="lottery-result-text-code">'+ data.couponCode +'</div>' +
		    			'<div class="lottery-result-coupon-offset">Upon Order $'+ data.couponPriceBaseline +'</div>' +
		    		'</div>' +
		    	'</div>' +
		    '</div>';
	    	$('.lottery').append($(html));
	    	isGaming = false;
	    	resetLottery();
    	}, 1000);
    }
    var canLottery = false;
    var isGaming = false;
    var isUsed = false;
    getLotteryData(renderLotteryItems);
    $('.lottery-start').on('click', function() {
        var email = $('.lottery-email-input').val().trim();
        if (!isGaming) {
	        if (!canLottery) {
	        	isValidEmail(email) && lotteryEmailChecktAnimate();
	        } else {
	        	startLottery(5000, 10, parseInt($(this).data('index'), 10), email, lotteryResult);
	        }
        }
    });
    $('.lottery-email-input').on('focus', function() {
    	$('.lottery-email-tip').text('');
    });
    $('.lottery-email-check').on('click', function() {
    	var email = $('.lottery-email-input').val().trim();
    	if (isValidEmail(email)) {
    		checkInputEmail(email, function(flag) {
		    	if (flag) {
		    		canLottery = true;
		        	lotteryStartAnimate();
		    	} else {
		    		$('.lottery-email-input').val('');
		    		lotteryEmailInputAnimate();
		    		$('.lottery-email-tip').text('This email address has been used!');
		    	}    			
    		});
    	}
    });
    $('.lottery-wap-show').on('click', function(e) {
   		$('.lottery').removeClass('waiting').addClass('wap');
   		addFixed();
    });
    function closePcLottery(e) {
    	$('.lottery').removeClass('show').removeClass('pc');
    	resetLottery();
    	$('.lottery-result').length && $('.lottery-result').remove();
    }
    $('.lottery').on('mouseenter', function() {
    	$(this).addClass('show').addClass('pc');
    }).on('mouseleave', closePcLottery);
    $(document.body).on('click', '.lottery.pc .lottery-close', closePcLottery);
    $(document.body).on('click', '.lottery.wap .lottery-close', function(e) {
    	removeFixed();
    	$('.lottery').addClass('waiting').removeClass('wap');
    	resetLottery();
    	$('.lottery-result').length && $('.lottery-result').remove();
    });
</script>