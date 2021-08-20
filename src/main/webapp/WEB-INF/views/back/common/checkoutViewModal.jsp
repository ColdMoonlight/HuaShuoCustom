<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>

<div class="modal fade" id="checkoutViewModal" tabindex="-1" role="dialog" aria-labelledby="checkoutViewModalLabel"
	aria-hidden="true">
	<div class="modal-dialog modal-lg modal-dialog-center modal-dialog-scrollable" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Checkout View</h4>
				<button class="close" type="button" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="order-info">
					<h4>Order Info</h4>
					<div class="checkout-view-box">
						<div class="order-list">
							<p>no product for order...</p>
						</div>
						<div class="pay-list">
							<div class="pay-item">
								<div class="pay-item-title">Product Total</div>
								<div class="pay-prototal">
									<div class="name"></div>
									<div class="value">$0.00</div>
								</div>
							</div>
							<div class="pay-item">
								<div class="pay-item-title">Discount</div>
								<div class="pay-discount">
									<div class="name"></div>
									<div class="value">-$0.00</div>
								</div>
							</div>
							<div class="pay-item">
								<div class="pay-item-title">Shipping</div>
								<div class="pay-shipping">
									<div class="name"></div>
									<div class="value">$0.00</div>
								</div>
							</div>
							<div class="pay-item">
								<div class="pay-item-title">Total</div>
								<div class="pay-total">
									<div class="name"></div>
									<div class="value">$0.00</div>
								</div>
							</div>									
						</div>
						<div class="pay-final sum-item">
							<div class="name">Paid by customer</div>
							<div class="value">$0.00</div>
						</div>
					</div>
				</div>
				<div class="shippping-address">
					<h4>Shipping Address</h4>
					<div class="shipping-list">
						<div class="shipping-item checkout-view-shipping firstname">
							<div class="name">First Name</div>
							<div class="value"></div>
						</div>
						<div class="shipping-item checkout-view-shipping lastname">
							<div class="name">Last Name</div>
							<div class="value"></div>
						</div>
						<div class="shipping-item checkout-view-shipping address">
							<div class="name">Address Details</div>
							<div class="value"></div>
						</div>
						<div class="shipping-item checkout-view-shipping city">
							<div class="name">city</div>
							<div class="value"></div>
						</div>
						<div class="shipping-item checkout-view-shipping province">
							<div class="name">Province/State</div>
							<div class="value"></div>
						</div>
						<div class="shipping-item checkout-view-shipping postcode">
							<div class="name">Post Code</div>
							<div class="value"></div>
						</div>
						<div class="shipping-item checkout-view-shipping country">
							<div class="name">Country</div>
							<div class="value"></div>
						</div>
						<div class="shipping-item checkout-view-shipping phone">
							<div class="name">Phone</div>
							<div class="value"></div>
						</div>
						<div class="shipping-item checkout-view-shipping email">
							<div class="name">E-mail</div>
							<div class="value"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>