<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form" %> 
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Mật khẩu</title>
<!-- Favicon -->
<link rel="shortcut icon" href="../resources/nhanvien/assets/img/svg/logo.svg" type="image/x-icon">
<!-- Custom styles -->
<link rel="stylesheet" href="../resources/nhanvien/assets/css/style.min.css">
</head>
<body>
	<div class="layer"></div>
	<main class="page-center">
	<article class="sign-up">
		<h1 class="sign-up__title">Bạn quên mật khẩu?</h1>
		<p class="sign-up__subtitle">Nhập email để lấy mật khẩu mới!</p>
		<form:form class="sign-up-form form" modelAttribute="email" action="../log/datlai.htm">
			<label class="form-label-wrapper">
				<p class="form-label">Email</p> 
				<form:input class="form-input" path="email" placeholder="Nhập Email!"/>
			</label> 
			<h5 style="color: red; padding: 10px;">${msgErr}</h5>
			<button class="form-btn primary-default-btn transparent-btn">Lấy mật khẩu mới</button>
		</form:form>
	</article>
	</main>
	<!-- Chart library -->
	<script src="../resources/nhanvien/assets/plugins/chart.min.js"></script>
	<!-- Icons library -->
	<script src="../resources/nhanvien/assets/plugins/feather.min.js"></script>
	<!-- Custom scripts -->
	<script src="../resources/nhanvien/assets/js/script.js"></script>
</body>
</html>