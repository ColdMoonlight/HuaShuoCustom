<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>

<div class="modal fade" id="imgModal" tabindex="-1" role="dialog" aria-labelledby="imgModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-center modal-dialog-scrollable" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Custom order</h4>
				<button class="close" type="button" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="left-panel"></div>
				<div class="right-panel"></div>
			</div>
			<div class="modal-footer" style="position: relative;">
				<button class="btn btn-info hide" id="img-all-handle" style="position: absolute; left: 1rem;">Move All</button>
				<button class="btn btn-secondary btn-cancel" type="button" data-dismiss="modal">Cancel</button>
				<button class="btn btn-danger btn-save" type="button">Save</button>
			</div>
		</div>
	</div>
</div>