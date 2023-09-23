<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="/WEB-INF/views/quanly/header/header.jsp"%>
<div class="main-wrapper">
	<!-- ! Main -->
	<main class="main users chart-page" id="skip-target">
		<div class="container">
			<h2 class="main-title text-primary" >Thêm hình ảnh cho: ${name}</h2>
			<div class="p-3 mb-2 bg-light text-dark">
				<form:form modelAttribute="hinhAnh" action="../hinhanh/themhinhanh.htm">
					<div class = "row">
						<div class="col-md-10">
    						<label class="form-label"><b>Đường dẫn</b></label><label style="color: red;">&nbsp *</label>
    						<form:input path="duongDan" class="form-control" />
    						<form:errors path="duongDan" style="color: red; font-size: 12px;"></form:errors>
 						 </div>
					</div>
					 <br>
					 <div class="col-md-6">
  						<button name="btnSave" class="btn btn-success"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
  <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
  <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
</svg>&nbsp;Xác nhận lưu</button>
  					</div>
				</form:form>
			</div>
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
