<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>

<div class="modal fade" id="googleAdModal" tabindex="-1" role="dialog" aria-labelledby="googleAdModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg modal-dialog-center modal-dialog-scrollable" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Google Shopping-Data download</h4>
				<button class="close" type="button" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="modal-body">
				<input class="form-control datetimepicker" id="google-ad-time" type="text" />
			</div>
			<div class="modal-footer">
				<button class="btn btn-secondary btn-cancel" type="button" data-dismiss="modal">Cancel</button>
				<button class="btn btn-danger btn-ok" type="button">download</button>
			</div>
		</div>
	</div>
</div>
