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
  <title>Hoá đơn</title>
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
			<div class="row" style="width: 100%;">
					<h1 class="modal-title" id="staticBackdropLabel" style="justify-content: center; text-align: center;">HOÁ ĐƠN</h1>
				</div>
			<div class="modal-body">
					Mã hoá đơn: ${hoaDon.maHD} <br>
					Ngày lập: ${ngayTao}<br>
					Tên khách hàng: ${gioHang.maKH.ho} ${gioHang.maKH.ten}<br>
					Nhân viên lập: ${nhanVien.ho} ${nhanVien.ten}<br>
					<table class="table">
						<thead>
							<tr class="table-secondary">
								<th scope="col" class="text-center">Tên sản phẩm</th>
								<th scope="col" class="text-center">Số lượng</th>
								<th scope="col" class="text-center">Đơn giá</th>
								<th scope="col" class="text-center">Thành tiền</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="sp" items="${dSSP}">
								<tr>
									<td class="text-center">${sp.ten}</td>
									<td class="text-center">${sp.soLuong}</td>
									<td class="text-center">${sp.getDonGia()}</td>
									<td class="text-center">${sp.getGiaVN()}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<b>Tổng tiền: ${tongTien}</b><br>
					<b>Tiền ghi bằng chữ: ${tienChu}</b>
				</div>
			</div>
	</main>
