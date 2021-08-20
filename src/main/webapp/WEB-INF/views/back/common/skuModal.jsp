<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>

<div class="modal fade" id="skuModal" tabindex="-1" role="dialog" aria-labelledby="skuModalLabel"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-center modal-dialog-scrollable" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Add one product sku</h4>
				<button class="close" type="button" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="card">
					<div class="card-title">
						<div class="card-title-name">Options</div>
					</div>
					<div class="card-body">
						<div id="productSkuName"></div>
					</div>
				</div>
				
				<div class="card">
					<div class="card-title">
						<div class="card-title-name">Others</div>
					</div>
					<div class="card-body">	
						<input id="productSkuId" hidden>
						<div class="form-group">
							<label class="col-form-label" for="productSkuImg">Product Sku Picture</label>
							<div class="controls">
								<div class="c-upload-img">
									<svg class="c-icon">
										<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-image-plus"></use>
									</svg>
									<div class="c-backshow"></div>						
									<input id="productSkuImg" type="file" accept="image/png, image/jpeg, image/gif" />										
									<!-- spinner -->
									<div class="spinner">
										<div class="spinner-border" role="status" aria-hidden="true"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-form-label" for="productSkuStock">Product Sku Stock</label>
							<div class="controls">
								 <input class="form-control" id="productSkuStock" type="text">
							</div>
						</div>
						<div class="form-group">
							<label class="col-form-label" for="productSkuPrice">Product Sku Price</label>
							<div class="controls">
								 <input class="form-control" id="productSkuPrice" type="text">
							</div>
						</div>
						<div class="form-group">
							<label class="col-form-label" for="productSkuSku">Product Sku</label>
							<div class="controls">
								 <input class="form-control" id="productSkuSku" type="text">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-secondary btn-cancel" type="button" data-dismiss="modal">Cancel</button>
				<button class="btn btn-danger btn-save" type="button">Save</button>
			</div>
		</div>
	</div>
</div>
<script>
	$('#skuModal .btn-cancel').on('click', function () {
		$('#skuModal .btn-save').off('click');
	});
</script>