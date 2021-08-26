<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<script>
	function initFooterData(data) {
		for (var key in footerData) {
			var blockData = data[footerData[key]["data-key"]];
			for (var key2 in blockData) {
				if (blockData[key2].footnavIfincome && blockData[key2].footnavSeo) {
					var footnavUrl;
					if (blockData[key2].footnavClickType == '1') {
						footnavUrl = "${APP_PATH}/" + blockData[key2].footnavClickUrl;
					} else {
						footnavUrl = "${APP_PATH}/pages/" + blockData[key2].footnavSeo;
					}
					footerData[key].children.push({
						"name": blockData[key2].footnavName,
						"icon": "",
						"link": footnavUrl,
					});
				} else {
					footerData[key].children.push({
						"name": blockData[key2].footnavName,
						"icon": "",
						"link": "javascritp:;"
					});
				}
			}
		}
	}

	function renderFooterNav(parent, data) {
		var htmlStr = '';
		for (var i = 0, len = data.length; i < len; i += 1) {
			htmlStr += '<div class="footer-nav-item ' + (data[i].name).toLowerCase() + '">' +
				'<h4 class="footer-nav-title">' + (data[i].name.replace(/\_/g, ' ')) +
				'<i class="icon ' + data[i].icon + '"></i>' +
				'</h4>';
			var children = data[i].children;
			var len2 = (children && data[i].children.length) || 0;
			if (len2) {
				htmlStr += '<ul class="footer-nav-body footer-nav-submenu">';
				for (j = 0; j < len2; j += 1) {
					htmlStr += '<li class="footer-nav-submenu-item"><a href="' + children[j].link + '">' + children[j].name + '</a></li>';
				}
				htmlStr += '</ul>';
			}

			htmlStr += '</div>';
		}

		parent.html(htmlStr);
	}

	function addWapFooterNavEvent() {
		$('.client_service_support').find('.icon').addClass('sub').removeClass('plus').parents('.footer-nav-item').find('.footer-nav-body').slideDown();
		$(document.body).on('click', '.footer-nav-title', function () {
			$('.footer-nav-body').slideUp().parents('.footer-nav').find('.icon').removeClass('sub').addClass('plus');
			$(this).find('.icon').addClass('sub').removeClass('plus').parents('.footer-nav-item').find('.footer-nav-body').slideDown();
		});
	}

	function backTop() {
		function toTop() {
			if (window.pageYOffset > window.innerHeight) {
				var $backTop = $('.back-top');
				if (!$backTop.length) {
					$backTop = $('<div id="back-top" class="back-top" title="back top"><span class="icon backtop"></span></div>');
					$(document.body).append($backTop);
				}
				setTimeout(function() {
					$backTop.addClass('active');
				}, 100);
			} else {
				$('.back-top').hasClass('active') & $('.back-top').removeClass('active');
			}
		}
		$(window).on('scroll', function() {
			var timeEnd = Date.now();
			if (timeEnd - timeStart < 100) clearTimeout(timer);
			timeStart = timeEnd;
			timer = setTimeout(toTop, 100);
		});
		$(document.body).on('click', '#back-top', function() {
			$('html').animate({scrollTop: 0}, 500); 
		});
	}
	// get footer nav data
	function getFooterNavData(callback) {
		$.ajax({
			url: '${APP_PATH}/MlbackFootNav/getMlfrontFootNavAllSimple',
			type: 'post',
			async: false,
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
	var footerData = [{
		"name": "Megalook_Hair",
		"icon": "plus",
		"link": "javascript:;",
		"children": [],
		"data-key": "mlbackFootNavOneList"
	}, {
		"name": "Buyer_Service",
		"icon": "plus",
		"link": "javascript:;",
		"children": [],
		"data-key": "mlbackFootNavTwoList"
	}, {
		"name": "Activity",
		"icon": "plus",
		"link": "javascript:;",
		"children": [],
		"data-key": "mlbackFootNavThreeList"
	}, {
		"name": "Client_Service_Support",
		"icon": "plus",
		"link": "javascript:;",
		"children": [],
		"data-key": "mlbackFootNavFourList"
	}];
	getFooterNavData(function(data) {
		if (data.isNav == 0) {
			mlModalTip('<p>Failed to get to the relevant data !</p>');
		}

		if (data.isNav == 1) {
			initFooterData(data);
			renderFooterNav($('.footer-nav'), footerData);
			addWapFooterNavEvent();
		}
	});
	backTop();
</script>