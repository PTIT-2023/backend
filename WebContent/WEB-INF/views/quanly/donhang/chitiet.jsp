<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/views/quanly/header/header.jsp"%>
<div class="main-wrapper">
	<!-- ! Main -->
	<main class="main users chart-page" id="skip-target">
		<div class="container">
			<h2 class="main-title text-primary" >Thông tin của đơn hàng ${name}</h2>
			<div class="p-3 mb-2 bg-light text-dark">
					<div class = "row">
						<div class="col-md-4">
    						<label class="form-label"><b>Mã đơn hàng:</b></label>
    						<label>${gioHang.maGH}</label>
  						</div>
  						<div class="col-md-3">
    						<label class="form-label"><b>Tên khách hàng:</b></label>
    						<label>${gioHang.ten}</label>
  						</div>
					</div>
					<div class = "row">
						<div class="col-md-12">
    					<label class="form-label"><b>Địa chỉ nhận:</b></label>
    					<label>${gioHang.diaChiNhan}</label>
  					</div>
					</div>
					<div class = "row">
						<div class="col-md-3">
    						<label class="form-label"><b>Ngày đặt:</b></label>
    						<label>${gioHang.ngayDat}</label>
  						</div>
  						<div class="col-md-3">
    						<label class="form-label"><b>Ngày giao dự kiến:</b></label>
    						<label>${gioHang.ngayGiao}</label>
  						</div>
					</div>
					<div class = "row">
						<div class="col-md-3">
    						<label class="form-label"><b>Số điện thoại:</b></label>
    						<label>${gioHang.sDTNhan}</label>
  						</div>
  						<c:if test="${gioHang.emailNhan != null}">
  							<div class="col-md-3">
    						<label class="form-label"><b>Email:</b></label>
    						<label>${gioHang.emailNhan}</label>
  						</div>
  						</c:if>
					</div>
					<div class = "row">
						<div class="col-md-6">
    						<label class="form-label"><b>Hình thức thanh toán:</b></label>
    						<label>Thanh toán khi nhận hàng</label>
  						</div>
					</div>
			</div>
  			<div class="p-3 mb-2 bg-light text-dark">
  				<table class="table">
  					<thead>
					<tr class="table-secondary">
						<th class="text-center">Hình ảnh</th>
						<th class="text-center">Tên sản phẩm</th>
						<th class="text-center" >Số  lượng</th>
						<th class="text-center" >Đơn giá</th>
						<th class="text-center" >Thành tiền</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="sp" items="${dSSP}">
                                <tr>
                                	<td class="text-center"><img style = "heigh: 60px; width: 80px;" src="${sp.duongDan}" alt="Hình ảnh sản phẩm" /></td>
                                    <td class="text-center">${sp.ten} </td>
                                    <td class="text-center"><p>${sp.soLuong}</p></td>
                                    <td class="text-center"><p>${sp.getDonGia()}</p></td>
                                    <td class="text-center"><h6>${sp.getGiaVN()}</h6></td>
                                </tr>
                    </c:forEach>
				</tbody>
  				</table>
  				<p3>Tổng tiền:  </p3>  <b class="money well" style="margin-left: 5px; font-size: 20px; color: black;">${tongTien}</b> VND
  			</div>
  			<div class="col-md-6">
				<c:if test="${loaiTrang == 'choXacNhan'}">
					<a href="../donhang/${gioHang.maGH}.htm?linkXacNhan"><button class="btn btn-success"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-circle" viewBox="0 0 16 16">
  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
  <path d="M10.97 4.97a.235.235 0 0 0-.02.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-1.071-1.05z"/>
</svg>&nbsp;Xác nhận</button></a>
					<a  href="#" type="button" data-bs-toggle="modal" data-bs-target="#staticBackdrop"><button class="btn btn-danger"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-circle" viewBox="0 0 16 16">
  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
  <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
</svg>&nbsp;Huỷ đơn</button></a>
				</c:if>
				<c:if test="${loaiTrang == 'daXacNhan'}">
					<a href="../donhang/${gioHang.maGH}.htm?linkGiao"><button class="btn btn-success"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-circle" viewBox="0 0 16 16">
  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
  <path d="M10.97 4.97a.235.235 0 0 0-.02.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-1.071-1.05z"/>
</svg>&nbsp;Xác nhận giao</button></a>
				</c:if>
  			</div>
		</div>
	</main>
	<!-- Modal -->
	<div class="modal fade " id="staticBackdrop" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h2 class="modal-title" id="staticBackdropLabel">Xác nhận huỷ</h2>
				</div>
				<div class="modal-body">Bạn có thật sự muốn huỷ đơn hàng này?</div>
				<div class="modal-footer">
					<a href="../donhang/${gioHang.maGH}.htm?linkHuy"><button
							type="button" class="btn btn-primary">Xác nhận</button></a>
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Thoát</button>
				</div>
			</div>
		</div>
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>

	<%@include file="/WEB-INF/views/quanly/footer/footer.jsp"%>
