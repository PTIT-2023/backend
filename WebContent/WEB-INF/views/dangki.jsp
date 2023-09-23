<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Elegant Dashboard | Dashboard</title>
  <!-- CSS only -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
  <!-- Favicon -->
  <link rel="shortcut icon" href="../resources/nhanvien/assets/img/svg/logo.svg" type="image/x-icon">
  <!-- Custom styles -->
  <link rel="stylesheet" href="../resources/nhanvien/assets/css/style.min.css">
 
</head>

<body>
  <div class="layer"></div>
<!-- ! Body -->
<div class="main-wrapper">
	<!-- ! Main -->
	<main class="main users chart-page" id="skip-target">
		<div class="container">
			<h1 style="color: #000080;">Xin chào!</h1>
			<br>
		<h5 style="color: #000080;">Nhập thông tin để đăng ký tài khoản</h5>
		<br>
		<form:form  modelAttribute="thongTin" action="../khachhang/dangkitaikhoan.htm" style="width: 600px;">
			<div class = "row">
						<div class="col-md-5">
    						<label class="form-label"><b>Họ</b></label>
    							<form:input path="ho" class="form-control"  />
    							<form:errors path="ho" style="color: red; font-size: 12px;"></form:errors>
  						</div>
  						<div class="col-md-5">
    						<label class="form-label"><b>Tên</b></label>
    							<form:input path="ten" class="form-control"  />
    							<form:errors path="ten" style="color: red; font-size: 12px;"></form:errors>
  						</div>
  			</div>
  			<div class = "row">
  				<div class="col-md-3">
  							<label class="form-label"><b>Giới tính</b></label>
							<form:select path="gioiTinh" items="${gioitinh}" class="form-control" aria-label=".form-select-lg example">
							</form:select>
  						</div>
  						<div class="col-md-7">
    						<label class="form-label"><b>Email</b></label>
    							<form:input path="email" class="form-control"  />
    							<form:errors path="email" style="color: red; font-size: 12px;"></form:errors>
  						</div>
  			</div>
  			<div class = "row">
						<div class="col-md-6">
    						<label class="form-label"><b>Mật khẩu</b></label>
    							<form:input path="matKhau" class="form-control"  />
    							<form:errors path="matKhau" style="color: red; font-size: 12px;"></form:errors>
  						</div>
  						<div class="col-md-4">
    						<label class="form-label"><b>Số điện thoại</b></label>
    							<form:input path="sDT" class="form-control"  />
    							<form:errors path="sDT" style="color: red; font-size: 12px;"></form:errors>
  						</div>
  			</div>
  			<div class = "row">
						<div class="col-md-10">
    						<label class="form-label"><b>Địa chỉ</b></label>
    						<form:textarea path="diaChi" class="form-control" rows="4"/>
    						<form:errors path="diaChi" style="color: red; font-size: 12px;"></form:errors>
 						 </div>
  			</div>
  			<div class = "row">
						<div class="col-md-10">
    						<label class="form-label"><b>Mã số thuế (Không bắt buộc)</b></label>
    							<form:input path="maSoThue" class="form-control"  />
    							<form:errors path="maSoThue" style="color: red; font-size: 12px;"></form:errors>
  						</div>
  			</div>
			<h5 style="color: red; padding: 10px;">${msgErr}</h5>
			<button style="width: 500px;" class="primary-default-btn transparent-btn">Đăng ký</button>
		</form:form>
		</div>
	</main>
<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary" data-bs-toggle="modal"
		data-bs-target="#staticBackdrop" id="test" hidden="true">
		Launch static backdrop modal</button>

	<!-- Modal -->
	<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="staticBackdropLabel">Thông báo</h4>
				</div>
				<div class="modal-body">${msg }</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						data-bs-dismiss="modal">&nbsp;&nbsp;&nbsp;OK&nbsp;&nbsp;&nbsp;</button>
				</div>
			</div>
		</div>
	</div>
	<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
	
<!-- Hidden -->
<p id="hdnUpdate" hidden="true">${msg }</p>
<!-- Hidden -->

<script type="text/javascript">
		window.onload = function()
		{
		    var checkAdd = document.getElementById("hdnUpdate");
		    var msg = checkAdd.innerHTML;
		    if(msg != null && msg  != ""){
		    	let btn = document.querySelector('#test');
		        btn.click();
		    } 
		};
</script>
