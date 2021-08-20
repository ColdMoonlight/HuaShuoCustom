<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>

<div class="modal fade" id="randTimeModal" tabindex="-1" role="dialog" aria-labelledby="randTimeModalLabel"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-center modal-dialog-scrollable" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Generate random time...</h4>
				<button class="close" type="button" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="modal-body">
				<div  style="width: 80%; max-width: 600px; margin: 0 auto;">
					<div class="row">
						<div class="form-group">
							<label class="col-form-label" for="download-time-year">Date(year):</label>
							<div class="controls">
								<select class="form-control" id="download-time-year">
									<option value="-1">请选择年份...</option>
									<option value="2020">2020</option>
									<option value="2021">2021</option>
									<option value="2022">2022</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-form-label" for="download-time-month">Date(month): </label>
							<div class="controls">
								<select class="form-control" id="download-time-month">
									<option value="-1">请选择月份...</option>
									<option value="1">一月</option>
									<option value="2">二月</option>
									<option value="3">三月</option>
									<option value="4">四月</option>
									<option value="5">五月</option>
									<option value="6">六月</option>
									<option value="7">七月</option>
									<option value="8">八月</option>
									<option value="9">九月</option>
									<option value="10">十月</option>
									<option value="11">十一月</option>
									<option value="12">十二月</option>
								</select>
							</div>
						</div>
					</div>
					<div class="row" style="display: flex;">
						<div class="form-group startday" style="width: 50%;">
							<label class="col-form-label" for="download-startday">startday: </label>
							<div class="controls">
								<input class="form-control" id="download-startday" placeholder="请输时间..." />
							</div>
						</div>
						<div class="form-group endday" style="width: 50%; margin-left: 10px;">
							<label class="col-form-label" for="download-endday">endday: </label>
							<div class="controls">
								<input class="form-control" id="download-endday" placeholder="请输入时间..." />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group">
							<label class="col-form-label" for="download-time-number">review number: </label>
							<div class="controls">
								<input class="form-control" id="download-time-number" placeholder="请输入数目..." />
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-secondary btn-cancel" type="button" data-dismiss="modal">Cancel</button>
				<button class="btn btn-danger" id="download-time-template" type="button">Start</button>
				<button class="btn btn-danger" id="download-time-custm" type="button">Start</button>
				
			</div>
			
			
		</div>
	</div>
</div>