<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/views/quanly/header/header.jsp"%>
<div class="main-wrapper">
	<!-- ! Main -->
	<main class="main users chart-page" id="skip-target">
		<div class="container">
			<h2 class="main-title text-primary" >Chỉnh sửa khách hàng</h2>
			<div class="p-3 mb-2 bg-light text-dark">
				<form:form modelAttribute="khachHang" action="../khachhang/sua.htm">
					<div class = "row">
						<div class="col-md-3">
    						<label class="form-label"><b>Mã khách hàng</b></label><label style="color: red;">&nbsp *</label>
    						<form:input path="maKH" class="form-control" disabled="true"/>
    						<form:errors path="maKH" style="color: red; font-size: 12px;"></form:errors>
  						</div>
  						<div class="col-md-3" hidden="true">
    						<label class="form-label"><b>Mã khách hàng</b></label><label style="color: red;">&nbsp *</label>
    						<form:input path="maKH" class="form-control" />
    						<form:errors path="maKH" style="color: red; font-size: 12px;"></form:errors>
  						</div>
  						<div class="col-md-3">
    						<label class="form-label"><b>Họ</b></label><label style="color: red;">&nbsp *</label>
    						<form:input path="ho" class="form-control" />
    						<form:errors path="ho" style="color: red; font-size: 12px;"></form:errors>
 						 </div>
 						 <div class="col-md-3">
    						<label class="form-label"><b>Tên</b></label><label style="color: red;">&nbsp *</label>
    						<form:input path="ten" class="form-control" />
    						<form:errors path="ten" style="color: red; font-size: 12px;"></form:errors>
 						 </div>
					</div>
					<div class = "row">
						<div class="col-md-3">
    						<label class="form-label"><b>Giới tính</b></label><label style="color: red;">&nbsp *</label>
    						<form:select path="gioiTinh" items="${gioitinh}"
									class="form-control"  aria-label=".form-select-lg example">
							</form:select>
 						 </div>
 						 <div class="col-md-3">
    						<label class="form-label"><b>Mã số thuế</b></label><label style="color: red;">&nbsp *</label>
      						<form:input path="maSoThue" class="form-control" />
    						<form:errors path="maSoThue" style="color: red; font-size: 12px;"></form:errors>
 						 </div>
 						 <div class="col-md-3">
    						<label class="form-label"><b>Số điện thoại</b></label><label style="color: red;">&nbsp *</label>
    						<form:input path="sDT" class="form-control" />
    						<form:errors path="sDT" style="color: red; font-size: 12px;"></form:errors>
 						 </div>
					</div>
					<div class = "row">
						<div class="col-md-13">
    						<label class="form-label"><b>Địa chỉ</b></label><label style="color: red;">&nbsp *</label>
    						<form:textarea path="diaChi" class="form-control" rows="3"/>
    						<form:errors path="diaChi" style="color: red; font-size: 12px;"></form:errors>
 						 </div>
					</div>
					 <br>
					 <div class="col-md-6">
  						<button name="btnSave" class="btn btn-success"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-circle" viewBox="0 0 16 16">
  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
  <path d="M10.97 4.97a.235.235 0 0 0-.02.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-1.071-1.05z"/>
</svg>&nbsp;Xác nhận lưu</button>
  					</div>
				</form:form>
			</div>
		</div>
	</main>
	<!-- Hidden -->
	<p id="hdnUpdate" hidden="true">${msg }</p>
	<!-- Hidden -->
	<!--  -->
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
		<script type="text/javascript">
        	$(function() {
            	$('#datepicker').datepicker();
        	});
    </script> 
	<!--  -->
<%@include file="/WEB-INF/views/quanly/footer/footer.jsp"%>
<script type="text/javascript">
	window.onload = function()
	{
	    var checkAdd = document.getElementById("hdnUpdate");
	    var msg = checkAdd.innerHTML;
	    if(msg != null && msg  != ""){
	    	alert(msg);
	    } 
	    
	};
</script>
