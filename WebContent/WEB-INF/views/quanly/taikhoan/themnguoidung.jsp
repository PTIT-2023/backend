<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@include file="/WEB-INF/views/quanly/header/header.jsp"%>
<div class="main-wrapper">
<!-- ! Main -->
	<main class="main users chart-page" id="skip-target">
	<div class="container">
	<div class="p-3 mb-2 bg-light text-dark">
			<c:if test="${loaiTrang == 'taoNhanVien'}">
				<h2 class="main-title text-primary">Tạo nhân viên cho tài khoản: ${taiKhoan}</h2>
				<form:form modelAttribute="nhanVien" action="../nhanvien/them.htm">
					<div class = "row">
						<div class="col-md-3">
    						<label class="form-label"><b>Mã nhân viên</b></label><label style="color: red;">&nbsp *</label>
    							<form:input path="maNV" class="form-control"  />
    							<form:errors path="maNV" style="color: red; font-size: 12px;"></form:errors>
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
							<form:select path="gioiTinh" items="${gioitinh}" class="form-control" aria-label=".form-select-lg example">
							</form:select>
  						</div>
 						 <div class="col-md-3">
 						 	<label class="form-label"><b>Số điện thoại</b></label><label style="color: red;">&nbsp *</label>
    							<form:input path="sDT" class="form-control" />
    							<form:errors path="sDT" style="color: red; font-size: 12px;"></form:errors>
 						 </div>
					</div>
 					<div class = "row">
 					 	<div class="col-md-12">
    						<label class="form-label"><b>Địa chỉ</b></label><label style="color: red;">&nbsp *</label>
    						<form:textarea path="diaChi" class="form-control" rows="3"/>
    						<form:errors path="diaChi" style="color: red; font-size: 12px;"></form:errors>
 						 </div>
 					 </div>
					 <br>
					 <div class="col-md-6">
  						<button name="btnAdd" class="btn btn-success"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-plus" viewBox="0 0 16 16">
  <path d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H1s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C9.516 10.68 8.289 10 6 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
  <path fill-rule="evenodd" d="M13.5 5a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z"/>
</svg>&nbsp;Tạo nhân viên</button>
  					</div>
			</form:form>
			</c:if>
			<c:if test="${loaiTrang == 'taoKhachHang'}">
				<h2 class="main-title text-primary">Tạo khách hàng cho tài khoản: ${taiKhoan}</h2>
				<form:form modelAttribute="khachHang" action="../khachhang/them.htm">
					<div class = "row">
						<div class="col-md-3">
    						<label class="form-label"><b>Mã khách hàng</b></label><label style="color: red;">&nbsp *</label>
    							<form:input path="maKH" class="form-control"  />
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
							<form:select path="gioiTinh" items="${gioitinh}" class="form-control" aria-label=".form-select-lg example">
							</form:select>
  						</div>
 						 <div class="col-md-3">
 						 	<label class="form-label"><b>Số điện thoại</b></label><label style="color: red;">&nbsp *</label>
    							<form:input path="sDT" class="form-control" />
    							<form:errors path="sDT" style="color: red; font-size: 12px;"></form:errors>
 						 </div>
 						 <div class="col-md-3">
 						 	<label class="form-label"><b>Mã số thuế</b></label>
    							<form:input path="maSoThue" class="form-control" />
    							<form:errors path="maSoThue" style="color: red; font-size: 12px;"></form:errors>
 						 </div>
					</div>
 					<div class = "row">
 					 	<div class="col-md-12">
    						<label class="form-label"><b>Địa chỉ</b></label><label style="color: red;">&nbsp *</label>
    						<form:textarea path="diaChi" class="form-control" rows="3"/>
    						<form:errors path="diaChi" style="color: red; font-size: 12px;"></form:errors>
 						 </div>
 					 </div>
					 <br>
					 <div class="col-md-6">
  						<button name="btnAdd" class="btn btn-success"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-plus" viewBox="0 0 16 16">
  <path d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H1s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C9.516 10.68 8.289 10 6 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
  <path fill-rule="evenodd" d="M13.5 5a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z"/>
</svg>&nbsp;Tạo khách hàng</button>
  					</div>
			</form:form>
			</c:if>
			</div>
		</div>
	</main>
<!-- Hidden -->
<p id="hdnAdd" hidden="true">${msg }</p>
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
	    var checkAdd = document.getElementById("hdnAdd");
	    var msg = checkAdd.innerHTML;
	    if(msg != null && msg  != ""){
	    	alert(msg);
	    } 
	    
	};
</script>