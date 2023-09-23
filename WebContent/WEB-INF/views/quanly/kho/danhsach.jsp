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
	<c:if test="${loaiTrang == 'DV'}">
		<div class="container">
			<div class="row">
				<div class="col-md-10">
					<h2 class="main-title text-primary">Kho động vật</h2>
				</div>
			</div>
	</c:if>
	<c:if test="${loaiTrang == 'TV'}">
		<div class="container">
			<div class="row">
				<div class="col-md-10">
					<h2 class="main-title text-primary">Kho thực vật</h2>
				</div>
			</div>
	</c:if>
			<div class="p-3 mb-2 bg-light text-dark">
				<jsp:useBean id="pagedListHolder" scope="request"
				type="org.springframework.beans.support.PagedListHolder" />
			<c:url value="" var="pagedLink">
				<c:param name="p" value="~" />
			</c:url> 
			<table class="table table-striped">
				<thead>
					<tr class="table-primary">
						<th class="text-center">Mã SVC</th>
						<th class="text-center" >Tên SVC</th>
						<th class="text-center" >Số lượng tồn</th>
						<th class="text-center" >chỉnh sửa</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="svc" items="${pagedListHolder.pageList}">
						<tr >
							<td class="text-center">${svc.maSVC}</td>
							<td class="text-center">${svc.ten}</td>
							<td class="text-center">${svc.soLuongTon}</td>
							<td class="text-center">
								<a href="../kho/${svc.maSVC}.htm?linkEdit">
									<button class="btn btn-primary btn-sm"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
  <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
  <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
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
		</div>
	
	</main>
<%@include file="/WEB-INF/views/quanly/footer/footer.jsp"%>