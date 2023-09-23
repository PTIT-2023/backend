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
				<h2 class="main-title text-primary" >Danh sách thuỷ sinh vật cảnh</h2>
			</div>
		<jsp:useBean id="pagedListHolder" scope="request"
				type="org.springframework.beans.support.PagedListHolder" />
			<c:url value="gia.htm" var="pagedLink">
				<c:param name="p" value="~" />
			</c:url> 
			</div>
			<table class="table table-striped">
				<thead>
					<tr class="table-primary">
						<th class="text-center">Mã sinh vật cảnh</th>
						<th class="text-center" >Tên</th>
						<th class="text-center" >Loại</th>
						<th class="text-center">Giá</th>
						<th class="text-center">Thêm giá | Xem giá</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="svc" items="${pagedListHolder.pageList}">
						<tr >
							<td class="text-center">${svc.maSVC}</td>
							<td>${svc.ten}</td>
							<td class="text-center">${svc.maLoai.tenLoai}</td>
							<td class="text-center">${svc.getGiaVN()}</td>
							<td class="text-center">
								<a href="../thaydoigia/${svc.maSVC}.htm?linkAdd">
									<button class="btn btn-primary btn-sm"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-circle-fill" viewBox="0 0 16 16">
  <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8.5 4.5a.5.5 0 0 0-1 0v3h-3a.5.5 0 0 0 0 1h3v3a.5.5 0 0 0 1 0v-3h3a.5.5 0 0 0 0-1h-3v-3z"/>
</svg></button>
								</a>|
								<a href="../thaydoigia/${svc.maSVC}.htm?linkDelete">
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
	
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
		    	let btn = document.querySelector('#test');
		        btn.click();
		    } 
		};
</script>

	