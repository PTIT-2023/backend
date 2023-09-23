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
			<c:if test="${loaiTrang == 'them'}">
				<h2 class="main-title text-primary">Thêm tài khoản</h2>
				<form:form modelAttribute="taiKhoan" action="../taikhoan/edittaikhoan.htm">
					<div class = "row">
						<div class="col-md-4">
    						<label class="form-label"><b>Mã tài khoản</b></label><label style="color: red;">&nbsp *</label>
    							<form:input path="maTK" class="form-control"  />
    							<form:errors path="maTK" style="color: red; font-size: 12px;"></form:errors>
  						</div>
  						<div class="col-md-4">
    						<label class="form-label"><b>Email</b></label><label style="color: red;">&nbsp *</label>
    							<form:input path="email" class="form-control" />
    							<form:errors path="email" style="color: red; font-size: 12px;"></form:errors>
 						 </div>
					</div>
 					<div class = "row">
 					 	<div class="col-md-4">
    						<label class="form-label"><b>Mật khẩu</b></label><label style="color: red;">&nbsp *</label>
    						<form:input path="matKhau" class="form-control"/>
    						<form:errors path="matKhau" style="color: red; font-size: 12px;"></form:errors>
 						 </div>
 					 	<div class="col-md-4">
 					 		<label class="form-label"><b>Quyền</b></label><label style="color: red;">&nbsp *</label>
								<form:select path="maQuyen.maQuyen" items="${quyen}"
									itemValue="maQuyen" itemLabel="tenQuyen"
									class="form-control"  aria-label=".form-select-lg example">
								</form:select>
					 	</div>
 					 </div>
							<div class = "row">
							<div class="col-md-4">
							<label class="form-label"><b>Trạng thái</b></label><label style="color: red;">&nbsp *</label>
								<form:select path="maTT.maTT" items="${trangthai}"
								itemValue="maTT" itemLabel="tenTT"
									class="form-control"  aria-label=".form-select-lg example">
								</form:select>
								</div>
							</div>
					 <br>
					 <div class="col-md-6">
  						<button name="btnAdd" class="btn btn-success"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-plus" viewBox="0 0 16 16">
  <path d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H1s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C9.516 10.68 8.289 10 6 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
  <path fill-rule="evenodd" d="M13.5 5a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z"/>
</svg>&nbsp;Thêm</button>
  					</div>
			</form:form>
			</c:if>
			<c:if test="${loaiTrang == 'sua'}">
			<h2 class="main-title text-primary">Sửa tài khoản</h2>
				<form:form modelAttribute="taiKhoan" action="../taikhoan/edittaikhoan.htm">
					<div class = "row">
						<div class="col-md-4">
    						<label class="form-label"><b>Mã tài khoản</b></label><label style="color: red;">&nbsp *</label>
    							<form:input path="maTK" class="form-control" disabled="true"/>
    							<form:errors path="maTK" style="color: red; font-size: 12px;"></form:errors>
  						</div>
  						<div class="col-md-4" hidden="true">
    						<label class="form-label"><b>Mã tài khoản</b></label><label style="color: red;">&nbsp *</label>
    							<form:input path="maTK" class="form-control" />
    							<form:errors path="maTK" style="color: red; font-size: 12px;"></form:errors>
  						</div>
  						<div class="col-md-4">
    						<label class="form-label"><b>Email</b></label><label style="color: red;">&nbsp *</label>
    							<form:input path="email" class="form-control" disabled="true"/>
    							<form:errors path="email" style="color: red; font-size: 12px;"></form:errors>
 						 </div>
 						 <div class="col-md-4" hidden="true">
    						<label class="form-label"><b>Email</b></label><label style="color: red;">&nbsp *</label>
    							<form:input path="email" class="form-control" />
    							<form:errors path="email" style="color: red; font-size: 12px;"></form:errors>
 						 </div>
					</div>
 					<div class = "row">
 					 	<div class="col-md-4">
    						<label class="form-label"><b>Mật khẩu</b></label><label style="color: red;">&nbsp *</label>
    						<form:input path="matKhau" class="form-control"/>
    						<form:errors path="matKhau" style="color: red; font-size: 12px;"></form:errors>
 						 </div>
 					 	<div class="col-md-4">
 					 		<label class="form-label"><b>Quyền</b></label><label style="color: red;">&nbsp *</label>
								<form:select path="maQuyen.maQuyen" items="${quyen}"
									itemValue="maQuyen" itemLabel="tenQuyen"
									class="form-control"  aria-label=".form-select-lg example" disabled="true">
								</form:select>
					 	</div>
					 	<div class="col-md-4" hidden="true">
 					 		<label class="form-label"><b>Quyền</b></label><label style="color: red;">&nbsp *</label>
								<form:select path="maQuyen.maQuyen" items="${quyen}"
									itemValue="maQuyen" itemLabel="tenQuyen"
									class="form-control"  aria-label=".form-select-lg example" >
								</form:select>
					 	</div>
 					 </div>
							<div class = "row">
							<div class="col-md-4">
							<label class="form-label"><b>Trạng thái</b></label><label style="color: red;">&nbsp *</label>
								<form:select path="maTT.maTT" items="${trangthai}"
								itemValue="maTT" itemLabel="tenTT"
									class="form-control"  aria-label=".form-select-lg example">
								</form:select>
								</div>
							</div>
					 <br>
					 <div class="col-md-6">
  						<button name="btnEdit" class="btn btn-success"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
  <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
  <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
</svg>&nbsp;Lưu thay đổi</button>
  					</div>
			</form:form>
			</c:if>
			</div>
		<div class="row">
			<div class="col-md-6">
				<h2 class="main-title text-primary" >Danh sách tài khoản</h2>
			</div>
			</div>
			<div>
			<%-- <jsp:useBean id="pagedListHolder" scope="request"
				type="org.springframework.beans.support.PagedListHolder" /> --%>
			<c:url value="../taikhoan/index.htm" var="pagedLink">
				<c:param name="p" value="~" />
			</c:url>  
			<table class="table table-striped">
				<colgroup>
					<col style="width: 70px;">
					<col style="width: 120px;">
					<col style="width: 100px;">
					<col style="width: 100px;">
					<col style="width: 100px;">
					<col style="width: 120px;">
				</colgroup>
				<thead>
					<tr class="table-primary">
						<th class="text-center">Mã tài khoản</th>
						<th class="text-center" >Email</th>
						<th class="text-center">Quyền</th>
						<th class="text-center">Trạng thái</th>
						<th class="text-center">Chỉnh sửa</th>
						<th class="text-center">Tạo người dùng</th>
					</tr>
				</thead>
				<tbody>
					<%-- <c:forEach var="tk" items="${dSTaiKhoan}"> --%>
					<c:forEach var="tk" items="${pagedListHolder.pageList}">
						<tr >
							<td class="text-center">${tk.maTK}</td>
							<td class="text-center" colspan="1" >${tk.email}</td>
							<td class="text-center" colspan="1">${tk.maQuyen.tenQuyen}</td>
							<c:if test="${tk.maTT.maTT == 'TT1'}">
								<td class="text-center" colspan="1" ><p class="text-success">${tk.maTT.tenTT}</p></td>
							</c:if>
							<c:if test="${tk.maTT.maTT == 'TT2'}">
								<td class="text-center" colspan="1" ><p class="text-danger">${tk.maTT.tenTT}</p></td>
							</c:if>
							<td class="text-center">
								<a href="../taikhoan/${tk.maTK}.htm?linkEdit">
									<button class="btn btn-primary btn-sm"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
  <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
  <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
</svg></button>
								</a>
							</td>
							<td class="text-center">
							<c:if test="${tk.nhanVien == null && tk.maQuyen.maQuyen == 'NV'}">
									<a href="../nhanvien/${tk.maTK}.htm?linkAddNhanVien">
										<u class="text-primary">Tạo nhân viên</u>
									</a>
							</c:if>
							<c:if test="${ tk.maQuyen.maQuyen == 'KH' && tk.khachHang == null}">
									<a href="../khachhang/${tk.maTK}.htm?linkAddKhachHang">
										<u  class="text-primary">Tạo khách hàng</u>
									</a>
							</c:if>
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