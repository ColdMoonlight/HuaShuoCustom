<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>

<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel"
	aria-hidden="true">
	<div class="modal-dialog modal-lg modal-dialog-center modal-dialog-scrollable" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title"></h4>
				<button class="close" type="button" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="spinner" style="text-align: center;">
					<div class="spinner-border"></div>
				</div>
				<div class="modal-body-body"></div>
			</div>
			<div class="modal-footer">
				<div class="select-result hide">
					<span class="name">selected: </span>
					<span class="value"></span>
				</div>
				<button class="btn btn-secondary btn-cancel" type="button" data-dismiss="modal">No</button>
				<button class="btn btn-danger btn-ok" type="button">Yes</button>
			</div>
		</div>
	</div>
</div>