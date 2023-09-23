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
				<h2 class="main-title text-primary" >Danh sách giá của ${name}</h2>
			</div>
			</div>
			<table class="table table-striped">
				<thead>
					<tr class="table-primary">
						<th class="text-center">Giá</th>
						<th class="text-center" >Ngày áp dụng</th>
						<th class="text-center" >Nhân viên cập nhật</th>
						<th class="text-center" >Xoá giá</th>
					</tr>
				</thead>
				<tbody>
				<c:if test="${dSGia.size()>=0}">
					<c:forEach var="gia" items="${dSGia}">
						<tr >
							<td class="text-center">${gia.getGiaVN()}</td>
							<td class="text-center">${gia.ngayApDung}</td>
							<td class="text-center">${gia.maNV.ho} ${gia.maNV.ten}</td>
							<td class="text-center">
								<a href="../thaydoigia/${gia.iD}.htm?Delete">
									<button class="btn btn-danger btn-sm"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3-fill" viewBox="0 0 16 16">
  <path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5Zm-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5ZM4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5Z"/>
</svg></button>
								</a>
							</td>
						</tr>
					</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</main>
	<!-- Hidden -->
	<p id="hdnUpdate" hidden="true">${msg }</p>
	<!-- Hidden -->
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

	