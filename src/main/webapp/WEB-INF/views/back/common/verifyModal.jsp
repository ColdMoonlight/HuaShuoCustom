<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>

<div class="modal fade" id="verifyModal" tabindex="-1" role="dialog" aria-labelledby="verifyModalLabel"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-center modal-dialog-scrollable" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Verify...</h4>
				<button class="close" type="button" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="modal-body-body">
					<!-- <div class="form-group">
						<label class="col-form-label" for="username">Name</label>
						<div class="controls">
							<input class="form-control" id="username" type="text" placeholder="Please enter your name..." />
						</div>
					</div> -->
					<div class="form-group">
						<label class="col-form-label" for="password">Code</label>
						<div class="controls">
							 <input class="form-control" id="password" type="text" placeholder="Please enter your download-code..." />
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-secondary btn-cancel" type="button" data-dismiss="modal">Cancel</button>
				<button class="btn btn-danger btn-ok" type="button">Confirm</button>
			</div>
		</div>
	</div>
</div>