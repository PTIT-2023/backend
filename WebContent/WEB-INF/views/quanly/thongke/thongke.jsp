<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="/WEB-INF/views/quanly/header/header.jsp"%>
<div class="main-wrapper">
	<!-- ! Main -->
	<main class="main users chart-page" id="skip-target">
	<div class="container">
		<div class="row">
			<div class="col-md-10">
				<h2 class="main-title text-primary" >Thống kê doanh thu theo tháng</h2>
			</div>
			</div>
			<div class="p-3 mb-2 bg-light text-dark">
				<form:form modelAttribute="thongKe" action="../thongke/laysolieu.htm">
					<div class = "row">
					<div class="col-md-3">
    					<label class="form-label"><b>Ngày bắt đầu</b></label>
      						<div class="input-group date" id="datepicker">
        						<form:input path="ngayBD" type="text" class="form-control" />
        						<span class="input-group-append">
        					    	<span class="input-group-text bg-light d-block">
            							<i class="fa fa-calendar"></i>
          							</span>
       							 </span>
      						</div>  
    					</div>
    					<div class="col-md-3">
    					<label class="form-label"><b>Ngày kết thúc</b></label>
      						<div class="input-group date" id="datepicker2">
        						<form:input path="ngayKT" type="text" class="form-control" />
        						<span class="input-group-append">
        					    	<span class="input-group-text bg-light d-block">
            							<i class="fa fa-calendar"></i>
          							</span>
       							 </span>
      						</div>  
        						<%-- <form:errors path="ngayApDung" style="color: red; font-size: 12px;"></form:errors> --%>
    					</div>
    				</div>
					 <br>
					 <div class="col-md-6">
  						<button name="btnSave" class="btn btn-success"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
  <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
  <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
</svg>&nbsp;Thống kê</button>
  					</div>
				</form:form>
			</div>
			<br>
		 	 <c:if test="${kq != null && kq != ''}">
				<c:if test="${listKQ.size() > 0}">
					<div class="p-3 mb-2 bg-light text-dark">
					<h1 style="justify-content: center; text-align: center;">THỐNG KÊ</h1>
					<div class = "row">
						<label class="form-label"><b>Ngày bắt đầu: ${nbd}</b></label>
					</div>
					<div class = "row">
						<label class="form-label"><b>Ngày kết thúc: ${nkt}</b></label>
					</div>
					<table class="table">
						<thead>
							<tr class="table-secondary">
								<th class="text-center">Thời gian (Tháng-Năm)</th>
								<th class="text-center">Doanh thu</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="i" items="${listKQ}">
								<tr>
									<td class="text-center">${i.ngayTaoTN}</td>
									<td class="text-center"><p>${i.getGiaVN()}</p></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<a href="../thongke/exportCSV.htm"><button name="btnSave" class="btn btn-success"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-bar-right" viewBox="0 0 16 16">
  <path fill-rule="evenodd" d="M6 8a.5.5 0 0 0 .5.5h5.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L12.293 7.5H6.5A.5.5 0 0 0 6 8zm-2.5 7a.5.5 0 0 1-.5-.5v-13a.5.5 0 0 1 1 0v13a.5.5 0 0 1-.5.5z"/>
</svg>&nbsp;Xuất file Excel</button></a>
					<label class="form-label"><b style="margin-left: 800px; font-size: 20px;">Tổng cộng: ${tong}</b></label>
				</div>
				</c:if>
				<c:if test="${listKQ.size() <= 0}">
					<div class="p-3 mb-2 bg-light text-dark">
						<div class = "row">
						<label class="form-label"><b>Ngày bắt đầu: ${nbd}</b></label>
					</div>
					<div class = "row">
						<label class="form-label"><b>Ngày kết thúc: ${nkt}</b></label>
					</div>
						Không có dữ liệu trong khoảng thời gian đã chọn!
					</div>
			</c:if>
			</c:if>
		</div>
	</main>
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
            	$('#datepicker2').datepicker();
        	});
    </script>
<%@include file="/WEB-INF/views/quanly/footer/footer.jsp"%>