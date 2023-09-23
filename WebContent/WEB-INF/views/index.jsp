<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form" %> 
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Đăng nhập</title>
<!-- Favicon -->
<link rel="shortcut icon" href="../resources/nhanvien/assets/img/svg/logo.svg" type="image/x-icon">
<!-- Custom styles -->
<link rel="stylesheet" href="../resources/nhanvien/assets/css/style.min.css">
</head>
<body>
	<div class="layer"></div>
	<main class="page-center">
	<article class="sign-up">
		<h1 class="sign-up__title">Xin chào!</h1>
		<p class="sign-up__subtitle">Đăng nhập bằng Email và mật khẩu để tiếp tục</p>
		<form:form class="sign-up-form form" modelAttribute="taiKhoan" action="../log/login.htm">
			<label class="form-label-wrapper">
				<p class="form-label">Email</p> 
				<form:input class="form-input" path="email" placeholder="Nhập Email!"/>
			</label> 
			<label class="form-label-wrapper">
				<p class="form-label">Mật khẩu</p> 
				<form:input class="form-input" type="password" path="matKhau" placeholder="Nhập mật khẩu!"/>
			</label> 
			<h5 style="color: red; padding: 10px;">${msgErr}</h5>
			<button class="form-btn primary-default-btn transparent-btn">Đăng nhập</button>
			<br>
			<a class="link-info forget-link" href="../log/quenmatkhau.htm">Quên mật khẩu</a>
			&nbsp
			<a class="link-info forget-link" href="../khachhang/dangki.htm">Đăng ký tài khoản mới</a>
		</form:form>
	</article>
	</main>
	<!-- Hidden -->
	<p id="hdnUpdate" hidden="true">${msg }</p>
	<!-- Chart library -->
	<script src="../resources/nhanvien/assets/plugins/chart.min.js"></script>
	<!-- Icons library -->
	<script src="../resources/nhanvien/assets/plugins/feather.min.js"></script>
	<!-- Custom scripts -->
	<script src="../resources/nhanvien/assets/js/script.js"></script>
</body>
</html>
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
