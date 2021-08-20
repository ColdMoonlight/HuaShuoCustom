<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<!-- split main end -->
</main>
<footer>
	<div class="footer-page-header">
		<div class="container">
			<div class="follow-us">
				<h3 class="follow-us-title wap-follow-us">FOLLOW US</h3>
				<div class="follow-us-title pc-follow-us">
					<img src="${APP_PATH }/static/common/dblogo.png" alt="megalook" title="megalook" style="width:150px">
					<div style="margin-top:6px;">My New Look </div>
				</div>
				<ul class="follow-us-list">
					<!-- <li class="follow-icon instagram" title="megalook instagram"><a href="https://www.instagram.com/colcol_megalookhair/"></a></li> -->
					<li class="follow-icon instagram" title="megalook instagram"><a href="https://www.instagram.com/megalookhair/"></a></li>
					<li class="follow-icon youtube" title="megablook youtube"><a href="https://www.youtube.com/channel/UCbbrYL1KabTMlXFmQhFWtmw?view_as=subscriber"></a></li>
					<li class="follow-icon facebook" title="megalook facebook"><a href="https://www.facebook.com/MegalookHairCompany/"></a></li>
					<li class="follow-icon pinterest" title="megalook pinterest"><a href="https://www.pinterest.com/megalookhair1"></a></li>
					<li class="follow-icon snapchat" title="megalook snapchat"><a href="https://www.snapchat.com/add/megalook-hair"></a></li>
				</ul>
			</div>
			<div class="footer-nav pc-footer"></div>
			<div class="footer-nav wap-footer"></div>	
		</div>
	</div>
	<div class="footer-page-footer">
		<div class="container">
			<p class="copyright">Copyright © 2021 <a href="${APP_PATH}/">MegaLook</a>, All Rights Reserved</p>
			<img src="${APP_PATH }/static/pc/img/paypalcard.jpg" width="266" height="33" alt="paypal card" title="paypal card">
		</div>
	</div>
</footer>
