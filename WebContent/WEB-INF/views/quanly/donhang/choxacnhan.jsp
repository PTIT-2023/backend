<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="/WEB-INF/views/quanly/header/header.jsp"%>
<div class="main-wrapper">
	<!-- ! Main -->
	<main class="main users chart-page" id="skip-target">
	<div class="container">
		<div class="row">
			<div class="col-md-10">
				<h2 class="main-title text-primary" >Danh sách đơn hàng chờ xác nhận</h2>
			</div>
			</div>
			<jsp:useBean id="pagedListHolder" scope="request"
				type="org.springframework.beans.support.PagedListHolder" />
			<c:url value="../nhanvien/index.htm" var="pagedLink">
				<c:param name="p" value="~" />
			</c:url> 
			<table class="table table-striped">
				<thead>
					<tr class="table-primary">
						<th class="text-center">Tên khách hàng</th>
						<th class="text-center" >Ngày đặt</th>
						<th class="text-center" >Chi tiết</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="gh" items="${pagedListHolder.pageList}">
						<tr >
							<td class="text-center">${gh.ten}</td>
							<td class="text-center">${gh.ngayDat}</td>
							<td class="text-center">
								<a href="../donhang/${gh.maGH}.htm?linkChiTiet1">
									<button class="btn btn-primary btn-sm"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eye" viewBox="0 0 16 16">
  <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z"/>
  <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z"/>
</svg></button>
								</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<tg:paging pagedListHolder="${pagedListHolder}"
				pagedLink="${pagedLink}" />
		</div>
	</main>
	
<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary" data-bs-toggle="modal"
		data-bs-target="#staticBackdrop2" id="test2" hidden="true">
		Launch static backdrop modal</button>

	<!-- Modal -->
	<div class="modal fade" id="staticBackdrop2" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="staticBackdropLabel">Thông báo</h4>
				</div>
				<div class="modal-body">${msg2}</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						data-bs-dismiss="modal">&nbsp;&nbsp;&nbsp;OK&nbsp;&nbsp;&nbsp;</button>
				</div>
			</div>
		</div>
	</div>
	
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
				<div class="row" style="width: 100%;">
					<h4 class="modal-title" id="staticBackdropLabel" style="justify-content: center; text-align: center;">HOÁ ĐƠN</h4>
				</div>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
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
				</div><!-- 
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						data-bs-dismiss="modal">&nbsp;&nbsp;&nbsp;Xuất file&nbsp;&nbsp;&nbsp;</button>
				</div> -->
			</div>
		</div>
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
	
<!-- Hidden -->
<p id="hdnUpdate" hidden="true">${msg1}</p>
<p id="hdnUpdate2" hidden="true">${msg2}</p>
<!-- Hidden -->

<%@include file="/WEB-INF/views/quanly/footer/footer.jsp"%>
<script type="text/javascript">
		window.onload = function()
		{
		    var checkAdd = document.getElementById("hdnUpdate");
		    var msg = checkAdd.innerHTML;
		    if(msg != null && msg  != ""){
		    	let btn = document.querySelector('#test');
		        btn.click();
		    } 
		    
		    var checkAdd2 = document.getElementById("hdnUpdate2");
		    var msg2 = checkAdd2.innerHTML;
		    if(msg2 != null && msg2  != ""){
		    	let btn2 = document.querySelector('#test2');
		        btn2.click();
		    } 
		};
</script>


	