<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<style>
.lottery-box {
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  z-index: 9999999999;
  padding: 3rem 0;
  background-color: rgba(0, 0, 0, 0.3);
}
.lottery-close {
  position: absolute;
  top: -0.8rem;
  right: -0.8rem;
  z-index: 11;
  width: 2rem;
  height: 2rem;
  background-color: #555;
  color: #fff;
  text-align: center;
  cursor: pointer;
  border-radius: 50%;
}
.lottery-close:hover {
  background-color: #333;
}
.lottery {
  width: 100%;
  max-height: 90vh;
  overflow: auto;
  text-align: center;
}
.lottery::after {
  content: "";
  display: inline-block;
  width: 0;
  height: 90vh;
  vertical-align: middle;
}
.lottery-container {
  position: relative;
  display: inline-block;
  vertical-align: middle;
}
.lottery-oh {
  border-radius: 1rem;
  box-shadow: 0 0 0.5rem #000;
  background-color: #fff;
  overflow: hidden;
}
.lottery-email {
  position: relative;
  width: 100%;
  text-align: center;
  background-size: 100% 100%;
  background-repeat: no-repeat;
}
.lottery-title {
  width: 100%;
  color: #fff;
  text-align: center;
}
.lottery-title > span {
  display: block;
}
.lottery-title > span:nth-of-type(1) {
  background-image: url(${APP_PATH}/static/custom/lottery/lottery-logo.png);
  background-size: contain;
  background-repeat: no-repeat;
  background-position: center;
}
.lottery-email > input {
  display: inline-block;
  background-color: #fff;
  border-style: none;
}
.lottery-join-tip {
  font-weight: 400;
  color: #fff;
}
.lottery-join-tip div {
  display: none;
  opacity: 0;
  visibility: hidden;
}
.lottery-email-tip {
  width: 100%;
  color: #ff0;
  font-style: italic;
}
.lottery-inner-box {
  margin: 0 auto;
  background-color: #fff;
  box-shadow: 0 0 8px #bbb;
}
.lottery-game {
  position: relative;
  background-color: #e1e1e1;
  cursor: pointer;
}
.lottery-game-list {
  position: relative;
  width: 100%;
  height: 100%;
}
.lottery-game-item,
.lottery-startgame {
  position: absolute;
  box-shadow: 0 0 0.25rem #cdcdcd;
  background-size: 100% 100%;
  background-repeat: no-repeat;
}
.lottery-startgame {
  z-index: 9;
}
.lottery-game-list > .lottery-game-item {
  position: absolute;
  overflow: hidden;
  background-color: #fff;
  border: 2px solid transparent;
}
.lottery-game-list > .lottery-game-item.active {
  border-color: red;
  box-shadow: 0 0 6px red;
}
.lottery-result {
  display: inline-block;
  vertical-align: middle;
  height: auto;
  overflow: hidden;
  border-radius: 6px;
  background-color: #fff;
}
.lottery-other {
  width: 100%;
  height: auto;
  margin: 0 auto;
  text-align: center;
  background-size: contain;
  background-repeat: no-repeat;
}
.lottery-result-title > span {
  display: block;
}
.lottery-result-title > span:nth-of-type(1) {
  font-size: 24px;
  font-weight: bolder;
  color: #fff;
}
.lottery-result-title > span:nth-of-type(2) {
  font-size: 14px;
  color: #fff;
}
.lottery-card {
  padding: 2rem 1rem;
  margin: 1rem auto;
  border-radius: 1rem;
  box-shadow: 0 0 0.5rem #dc81e2;
  text-align: center;
  background-color: #fff;
}
.lottery-card .card-item > span {
  display: block;
}
.lottery-card .card-code,
.lottery-card .card-price {
  margin-bottom: 1rem;
}
.lottery-card .card-price > span:nth-of-type(1) {
  font-size: 1.625rem;
  font-weight: bolder;
  color: #f30927;
}
.lottery-card .card-price > span:nth-of-type(2) {
  font-size: 1rem;
  color: #383838;
  margin-top: 8px;
}
.lottery-card .card-code > span {
  display: block;
}
.lottery-card .card-code > span:nth-of-type(1) {
  font-size: 0.5rem;
  color: #383838;
}
.lottery-card .card-code > span:nth-of-type(2) {
  font-size: 1.625rem;
  font-weight: bolder;
  color: #000;
  margin-top: 0.5rem;
}
.lottery-card .card-use {
  font-style: italic;
  color: #fe186e;
}
.card-tip {
  font-size: 0.75rem;
  color: #fe186e;
}
.card-op {
  padding: 0 1rem;
  margin-top: 1rem;
  overflow: auto;
  text-align: center;
}
.card-op > .btn {
  position: relative;
  display: inline-block;
  width: 50%;
  padding: 0.5rem;
  font-size: 1rem;
  line-height: 1.4;
  color: #fff;
  border: 1px solid #ccc;
  border-radius: 0.2rem;
  background-color: #f30927;
}
@media only screen and (max-width: 798px) {
  .lottery-container,
  .lottery-result {
    width: 90%;
    margin: 0 auto;
  }
  .lottery-email {
    padding: 1.25rem 0 1.75rem 0;
    background-image: url(${APP_PATH}/static/custom/lottery/lottery-wap.jpg);
  }
  .lottery-title > span:nth-of-type(1) {
    padding-top: 30px;
  }
  .lottery-title > span:nth-of-type(2) {
    margin-top: 0.25rem;
    font-size: 0.75rem;
    letter-spacing: 0.1rem;
    font-weight: 400;
  }
  .lottery-email > input {
    width: 80%;
    padding: 0.5rem 1rem;
    margin-top: 1rem;
    font-size: 1rem;
    border-radius: 2rem;
  }
  .lottery-join-tip {
    margin-top: 0.75rem;
    margin-bottom: 0.5rem;
    font-size: 0.875rem;
  }
  .lottery-join-tip .wap {
    display: block;
    opacity: 1;
    visibility: visible;
  }
  .lottery-email-tip {
    position: absolute;
    bottom: 0.75rem;
    font-size: 0.75rem;
  }
  .lottery-game-box {
    width: 290px;
    padding: 10px;
    margin: 1rem auto;
  }
  .lottery-inner-box {
    padding: 10px;
    border-radius: 0.75rem;
  }
  .lottery-game {
    width: 250px;
    height: 250px;
    border-radius: 0.75rem;
  }
  .lottery-game-item,
  .lottery-startgame {
    width: 70px;
    height: 70px;
    border-radius: 0.5rem;
  }
  .lottery-startgame {
    top: 90px;
    left: 90px;
    background-image: url(${APP_PATH}/static/custom/lottery/start-game-pc.png);
  }
  .lottery-game-list > .lottery-game-item:nth-of-type(1) {
    top: 10px;
    left: 10px;
  }
  .lottery-game-list > .lottery-game-item:nth-of-type(2) {
    top: 10px;
    left: 90px;
  }
  .lottery-game-list > .lottery-game-item:nth-of-type(3) {
    top: 10px;
    right: 10px;
  }
  .lottery-game-list > .lottery-game-item:nth-of-type(4) {
    top: 90px;
    right: 10px;
  }
  .lottery-game-list > .lottery-game-item:nth-of-type(5) {
    bottom: 10px;
    right: 10px;
  }
  .lottery-game-list > .lottery-game-item:nth-of-type(6) {
    bottom: 10px;
    left: 90px;
  }
  .lottery-game-list > .lottery-game-item:nth-of-type(7) {
    bottom: 10px;
    left: 10px;
  }
  .lottery-game-list > .lottery-game-item:nth-of-type(8) {
    top: 90px;
    left: 10px;
  }
  .lottery-other {
    padding: 30px;
    background-image: url(${APP_PATH}/static/custom/lottery/lottery-success-wap.jpg);
  }
  .card-op > .btn,
  .lottery-card {
    width: 80%;
  }
}
@media only screen and (min-width: 799px) {
  .lottery-container,
  .lottery-result {
    width: 700px;
    margin: 0 auto;
  }
  .lottery-email {
    height: 300px;
    padding-top: 2rem;
    background-image: url(${APP_PATH}/static/custom/lottery/lottery-pc.jpg);
  }
  .lottery-title > span:nth-of-type(1) {
    padding-top: 50px;
  }
  .lottery-title > span:nth-of-type(2) {
    margin-top: 1rem;
    font-size: 1rem;
    letter-spacing: 0.125rem;
    font-weight: 400;
  }
  .lottery-email > input {
    width: 80%;
    padding: 0.75rem 1.25rem;
    margin-top: 1rem;
    font-size: 1rem;
    border-radius: 2rem;
  }
  .lottery-join-tip {
    margin-top: 0.875rem;
    margin-bottom: 0.5rem;
    font-size: 1rem;
  }
  .lottery-join-tip .pc {
    display: block;
    opacity: 1;
    visibility: visible;
  }
  .lottery-email-tip {
    margin-top: 1rem;
    font-size: 0.75rem;
  }
  .lottery-game-box {
    width: 500px;
    padding: 10px;
    margin: 2rem auto;
  }
  .lottery-inner-box {
    padding: 10px;
    border-radius: 1rem;
  }
  .lottery-game {
    width: 460px;
    height: 370px;
    border-radius: 1rem;
  }
  .lottery-game-item,
  .lottery-startgame {
    width: 140px;
    height: 110px;
    border-radius: 1rem;
  }
  .lottery-startgame {
    top: 130px;
    left: 160px;
    background-image: url(${APP_PATH}/static/custom/lottery/start-game-pc.png);
  }
  .lottery-game-list > .lottery-game-item:nth-of-type(1) {
    top: 10px;
    left: 10px;
  }
  .lottery-game-list > .lottery-game-item:nth-of-type(2) {
    top: 10px;
    left: 160px;
  }
  .lottery-game-list > .lottery-game-item:nth-of-type(3) {
    top: 10px;
    right: 10px;
  }
  .lottery-game-list > .lottery-game-item:nth-of-type(4) {
    top: 130px;
    right: 10px;
  }
  .lottery-game-list > .lottery-game-item:nth-of-type(5) {
    bottom: 10px;
    right: 10px;
  }
  .lottery-game-list > .lottery-game-item:nth-of-type(6) {
    bottom: 10px;
    left: 160px;
  }
  .lottery-game-list > .lottery-game-item:nth-of-type(7) {
    bottom: 10px;
    left: 10px;
  }
  .lottery-game-list > .lottery-game-item:nth-of-type(8) {
    top: 130px;
    left: 10px;
  }
  .lottery-other {
    padding: 60px 0;
    background-image: url(${APP_PATH}/static/custom/lottery/lottery-success-pc.jpg);
  }
  .card-op > .btn,
  .lottery-card {
    width: 350px;
  }
}
</style>
<div class="lottery-box hide">
	<div class="lottery">
        <div class="lottery-container">
			<div class="lottery-close icon close"></div>
			<div class="lottery-oh">			
	        	<div class="lottery-email">
		        	<div class="lottery-title">
		        		<span></span>
		        		<span>Your Hair Your Surprise</span>
		        	</div>
		            <input type="text" placeholder="E-mail address">
		            <div class="lottery-join-tip">
		            	<div class="wap">Enter Your Email,Try Your Luck!</div>
		            	<div class="pc">Enter Your Email,Try Your Luck!</div>
		            </div>
		            <div class="lottery-email-tip"></div>
		        </div>
				
		        <div class="lottery-game-box">
		        	<div class="lottery-inner-box">
			            <div class="lottery-game">
			                <div class="lottery-startgame"></div>
			                <div class="lottery-game-list">
			                	<div class="lottery-game-item"></div>
			                	<div class="lottery-game-item"></div>
			                	<div class="lottery-game-item"></div>
			                	<div class="lottery-game-item"></div>
			                	<div class="lottery-game-item"></div>
			                	<div class="lottery-game-item"></div>
			                	<div class="lottery-game-item"></div>
			                	<div class="lottery-game-item"></div>
			                </div>
			            </div>
			         </div>
		        </div>
			</div>
        </div>
    </div>
</div>
<script>
function isValidEmail(email) {
	var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	return reg.test(email);
}

function startGame() {
	if (!isPushEmail) {
		$.ajax({
			url: '${APP_PATH}/MlbackCoupon/getCouponLuckDrawResultAndUserEmail',
			type: 'post',
			data: {
				userEmail: emailEl.val(),
				couponId: String(lotteryData.couponId)
			},
			success: function (data) {
				if (data.code == 100) {
					isPushEmail = true;
				} else {
					lotteryEmailTipEl.text('Lucky draw failed, please try again!');
				}
			},
			error: function() {
				lotteryEmailTipEl.text('Lucky draw failed, please try again!');
			}
		});
	}
	var timer = null,
		speed = 100,
		gameItemEls = $('.lottery-game-item'),
		currentItem = gameItemEls[rollCount % 8];

	if (rollCount >= lotteryIndex + defaultTimes) {
		clearTimeout(timer);
		/* 重置 */
		rollCount = 0;
		$(prevItem).removeClass('active');
		isStartLottery = false;
		lotteryIndex = -1;

		if (isPushEmail) {
			$('.lottery-container').hide();
			renderLotterySuccess();
			lotteryData = null;
			logStatus();
		} else {
			lotteryEmailTipEl.text('Lucky draw failed, please try again!');
		}
	} else {
		speed = rollCount <= defaultTimes ? speed - 5 : speed + 20;

		prevItem && $(prevItem).removeClass('active');
		$(currentItem).addClass('active');
		prevItem = currentItem;
		rollCount += 1;

		timer = setTimeout(startGame, speed);
	}
}

function logStatus() {
	var date = new Date();
	document.cookie = "isHideLottery=true;expires=" + (date.setTime(date.getTime() + 30 * 60 * 1000), date.toGMTString());
}

function renderLotterySuccess() {
	var lotteryResultHtml = '';
	lotteryResultHtml += '<div class="lottery-result">'
		+ '<div class="lottery-other">'
		+ '<div class="lottery-result-title"><span>Congratulations!</span><span>You got a</span></div>'
		+ '<div class="lottery-card">'
		+ '<div class ="card-item card-price"><span>';
	if (parseInt(lotteryData.couponType)) {
		lotteryResultHtml += parseInt(lotteryData.couponPriceoff) + '%';
	} else {
		lotteryResultHtml += '$' + lotteryData.couponPrice;
	}
	lotteryResultHtml += '</span><span>Discount</span></div><div class="card-item card-code"><span>Your Discount Code is:</span><span>' + lotteryData.couponCode + '</span></div>'
		+ '<div class="card-use">'
		+ (lotteryData.couponPriceBaseline ? 'Upon Order $' + lotteryData.couponPriceBaseline : 'No limit!') + '.</div>'
		+ '</div>'
		+ '<div class="card-tip">in order to use this discount add it to the relevant field in checkout!</div>'
		+ '<div class="card-op">'
		+ '<a class="btn" href="javascript: window.location.reload();">Use Discount</a>'
		+ '</div></div></div>';
	lotteryEl.append(lotteryResultHtml);
}

function getLotteryIndex() {
	var couponArr = [],
		couponList = [],
		index = 0,
		lotteryRequest = null;

	$.ajax({
		url: '${APP_PATH}/MlbackCoupon/getMlbackCouponShowByLuckDrawType',
		type: 'post',
		dataType: 'json',
		contentType: 'application/json',
		async: false,
		success: function (data) {
			if (data.code == 100) {
				lotteryRequest = data;
			}
		}
	});

	if (lotteryRequest) {
	    addFixed();
		var $lotterGameItems = $('.lottery-game-item');
		couponList = lotteryRequest.extend.mlbackCouponResList;
		if (couponList.length != 8) {
			return 0;
		}
		if (lotteryCount < 1) {
			couponList.forEach(function(item, idx) {
				couponArr.push(item.couponId);
				$lotterGameItems.eq(idx).css('background-image', 'url('+ item.couponImgUrl +')');
			});
		}
		index = couponArr.indexOf(lotteryRequest.extend.luckDrawDate && lotteryRequest.extend.luckDrawDate.luckDrawCouponId);
		lotteryData = couponList[index];
		return index > -1 ? index + 1 : 0;		
	}
}

function checkUserEmail(email) {
	var isUsed = false;
	$.ajax({
		url: '${APP_PATH}/MlbackCoupon/checkCouponLuckDrawResultAndUserEmail',
		type: 'post',
		dataType: 'json',
		data: {
			userEmail: email,
			couponId: String(lotteryData.couponId)
		},
		async: false,
		success: function (data) {
			if (data.code == 100) {
				isUsed = data.extend && data.extend.emailIsCan ? true : false;
			}
		},
		fail: function () {
			lotteryEmailTipEl.text('The email address validation failed, please try again!');
		}
	});

	return isUsed;
}

function getCookie(name) {
	var arr,
		reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");

	if (arr = document.cookie.match(reg))
		return (arr[2]);
	else
		return null;
}

function isShowLottery() {
	return !isHideLottery && lotteryIndex;
}

var lotteryBoxEl = $('.lottery-box'),
	lotteryEl = $('.lottery'),
	emailEl = $('.lottery-email input'),
	gameStartEl = $('.lottery-startgame'),
	lotteryEmailTipEl = $('.lottery-email-tip'),
	isPushEmail = false,
	lotteryCount = 0,
	defaultTimes = 16,
	rollCount = 0,
	lotteryData = null,
	isStartLottery = false,
	isHideLottery = getCookie('isHideLottery') || false,
	lotteryIndex = isHideLottery ? undefined : getLotteryIndex(),
	prevItem = null;

if (isShowLottery()) lotteryBoxEl.removeClass('hide');

$('.lottery-close').click(function(e){
	lotteryBoxEl.hide();
	$(document.body).css('overflow', 'unset');
    logStatus();
});

lotteryEl.click(function(e){
	if (e.target == this) {
		lotteryBoxEl.hide();
		$(document.body).css('overflow', 'unset');
        logStatus();
	}
});
emailEl.on('change', function () {
	lotteryEmailTipEl.text('');
});
gameStartEl.on('click', function (e) {
	var timer = null;

	if (!isStartLottery) {
		if (isValidEmail(emailEl.val())) {
			isStartLottery = true;
			// 判断是否使用过
			if (!checkUserEmail(emailEl.val())) {
				startGame();
			} else {
				lotteryEmailTipEl.text('This email address has been used!');
				isStartLottery = false;
			}
		} else {
			lotteryEmailTipEl.text('Please enter a valid email address first!');
		}

		if (!gameStartEl.hasClass('active')) {
			gameStartEl.addClass('active');
			timer = setTimeout(function () {
				gameStartEl.removeClass('active');
				clearTimeout(timer);
			}, 300);
		}
	}
});
</script>