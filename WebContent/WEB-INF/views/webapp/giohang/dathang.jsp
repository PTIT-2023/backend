<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
  <!-- Basic -->

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Site Metas -->
    <title>Shop</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Site Icons -->
    <link rel="shortcut icon" href="../resources/khachhang/assets/images/favicon.ico" type="image/x-icon">
    <link rel="apple-touch-icon" href="../resources/khachhang/assets/images/apple-touch-icon.png">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="../resources/khachhang/assets/css/bootstrap.min.css">
    <!-- Site CSS -->
    <link rel="stylesheet" href="../resources/khachhang/assets/css/style.css">
    <!-- Responsive CSS -->
    <link rel="stylesheet" href="../resources/khachhang/assets/css/responsive.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="../resources/khachhang/assets/css/custom.css">

    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
    <!-- Start Main Top -->
    <div class="main-top">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                    <div class="right-phone-box">
                        <b>Xin chào ${taiKhoan.khachHang.ho} ${taiKhoan.khachHang.ten}</b>
                    </div>
                    <div class="our-link">
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12" >
                <c:if test="${taiKhoan == null }">
                	<div class="login-box" style="background: #B0B435;">
						<a href="../log/index.htm"><b style="color: #FFFFFF; margin-left: 20px;">ĐĂNG NHẬP	</b></a>
					</div>
                </c:if>
                <c:if test="${taiKhoan != null }">
                	<div class="login-box" style="background: #B0B435;">
						<a href="../log/khlogout.htm"><b style="color: #FFFFFF; margin-left: 20px;">ĐĂNG XUẤT</b></a>
					</div>
                </c:if>
                    <div class="text-slid-box">
                        <div id="offer-box" class="carouselTicker">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- End Main Top -->

    <!-- Start Main Top -->
    <header class="main-header">
        <!-- Start Navigation -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light navbar-default bootsnav">
            <div class="container">
                <!-- Start Header Navigation -->
                <div class="navbar-header">
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-menu" aria-controls="navbars-rs-food" aria-expanded="false" aria-label="Toggle navigation">
                    <i class="fa fa-bars"></i>
                </button>
                    <a class="navbar-brand" href="#"><img src="../resources/khachhang/assets/images/logo.png" class="logo" alt=""></a>
                </div>
                <!-- End Header Navigation -->
					<div class="collapse navbar-collapse" id="navbar-menu">
						<ul class="nav navbar-nav ml-auto" data-in="fadeInDown" data-out="fadeOutUp" >
						<form:form modelAttribute="inputSearch" action="../sanpham/timkiem.htm">
							<div class="input-group">
								<div class="form-outline">
									<form:input path="input" type="search" id="form1"
										class="form-control" />
								</div>
								<button class="btn">
									<i class="fas fa-search"></i>
								</button>
							</div>
						</form:form>
					</ul>
				</div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="navbar-menu">
                    <ul class="nav navbar-nav ml-auto" data-in="fadeInDown" data-out="fadeOutUp">
                        <li class="nav-item"><a class="nav-link" href="../webapp/index.htm">Trang chủ</a></li>
                        <li class="nav-item"><a class="nav-link" href="../sanpham/sanpham.htm">Sản phẩm</a></li>
                        <!-- <li class="dropdown">
                            <a href="#" class="nav-link dropdown-toggle arrow" data-toggle="dropdown">SHOP</a>
                            <ul class="dropdown-menu">
								<li><a href="shop.html">Sidebar Shop</a></li>
								<li><a href="shop-detail.html">Shop Detail</a></li>
                                <li><a href="cart.html">Cart</a></li>
                                <li><a href="checkout.html">Checkout</a></li>
                                <li><a href="my-account.html">My Account</a></li>
                                <li><a href="wishlist.html">Wishlist</a></li>
                            </ul>
                        </li>
                        <li class="nav-item"><a class="nav-link" href="gallery.html">Gallery</a></li>
                        <li class="nav-item"><a class="nav-link" href="contact-us.html">Contact Us</a></li> -->
                    </ul>
                </div>
                <!-- /.navbar-collapse -->

                <!-- Start Atribute Navigation -->
                <div class="attr-nav">
                    <ul>
                        <li class="side-menu"><a href="../giohang/index.htm">
						<i class="fa fa-shopping-bag"></i>
							<p>Giỏ hàng của tôi</p>
					</a></li>
						<li class="side-menu"><a href="../donhang2/index.htm">
						<i class="fa fa-shopping-cart"></i>
							<p>Đơn hàng của tôi</p>
					</a></li>
                    </ul>
                </div>
                <!-- End Atribute Navigation -->
            </div>
            <!-- Start Side Menu -->
            <div class="side">
                <a href="#" class="close-side"><i class="fa fa-times"></i></a>
                <li class="cart-box">
                    <ul class="cart-list">
                        <li>
                            <a href="#" class="photo"><img src="../resources/khachhang/assets/images/img-pro-01.jpg" class="cart-thumb" alt="" /></a>
                            <h6><a href="#">Delica omtantur </a></h6>
                            <p>1x - <span class="price">$80.00</span></p>
                        </li>
                        <li>
                            <a href="#" class="photo"><img src="../resources/khachhang/assets/images/img-pro-02.jpg" class="cart-thumb" alt="" /></a>
                            <h6><a href="#">Omnes ocurreret</a></h6>
                            <p>1x - <span class="price">$60.00</span></p>
                        </li>
                        <li>
                            <a href="#" class="photo"><img src="../resources/khachhang/assets/images/img-pro-03.jpg" class="cart-thumb" alt="" /></a>
                            <h6><a href="#">Agam facilisis</a></h6>
                            <p>1x - <span class="price">$40.00</span></p>
                        </li>
                        <li class="total">
                            <a href="#" class="btn btn-default hvr-hover btn-cart">VIEW CART</a>
                            <span class="float-right"><strong>Total</strong>: $180.00</span>
                        </li>
                    </ul>
                </li>
            </div>
            <!-- End Side Menu -->
        </nav>
        <!-- End Navigation -->
    </header>
    <!-- End Main Top -->

    <!-- Start Top Search -->
    <div class="top-search">
        <div class="container">
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-search"></i></span>
                <input type="text" class="form-control" placeholder="Search">
                <span class="input-group-addon close-search"><i class="fa fa-times"></i></span>
            </div>
        </div>
    </div>
    <!-- End Top Search -->

    <!-- End All Title Box -->
<%--  <c:if test="${phieuDatHang == null}">
 	Đặt hàng không thành công!
 </c:if> --%>
<%-- <c:if test="${phieuDatHang != null}"> --%>
<div class="container">
	<table class="table table-striped">
				<thead>
					<tr class="table-primary">
						<th class="text-center">Tên sản phẩm</th>
						<th class="text-center">Số lượng</th>
						<th class="text-center">Đơn giá</th>
						<th class="text-center">Thành tiền</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="sp" items="${dSSP}">
						<tr >
							<td class="text-center">${sp.ten}</td> 
							<td class="text-center">${sp.soLuong}</td> 
							<td class="text-center">${sp.getDonGia()}</td>
							<td class="text-center">${sp.getGiaVN()}</td>
						</tr>
					</c:forEach>
					<td><p3 style = "font-size: 20px;">Tổng tiền:  </p3>  <b style="margin-left: 5px; font-size: 25px;">${tongTien}</b></td>
				</tbody>
			</table>
			
</div>
<div class="container">
			<div class="p-3 mb-2 bg-light text-dark">
				<form:form modelAttribute="phieuDatHang" action="../giohang/dathang.htm" method="POST">
					<div class = "row">
						<div class="col-md-4">
    						<label class="form-label"><b>Tên người nhận:</b></label><label style="color: red;">&nbsp *</label>
    						<form:input path="ten" class="form-control"/>
    						<form:errors path="ten" style="color: red; font-size: 12px;"></form:errors>
  						</div>
  						<div class="col-md-3" hidden="true">
    						<label class="form-label"><b>Ngày đặt hàng:</b></label>
    						<form:input path="ngayDat" class="form-control" />
  						</div>
  						<div class="col-md-3">
    						<label class="form-label"><b>Ngày đặt hàng:</b></label>
    						<form:input path="ngayDat" class="form-control" disabled="true"/>
  						</div>
  						<div class="col-md-3" hidden="true">
    						<label class="form-label"><b>Ngày giao dự kiến::</b></label>
    						<form:input path="ngayGiao" class="form-control"/>
  						</div>
  						<div class="col-md-3">
    						<label class="form-label"><b>Ngày giao dự kiến::</b></label>
    						<form:input path="ngayGiao" class="form-control" disabled="true"/>
  						</div>
  					</div>
						<div class="col-md-13">
    						<label class="form-label"><b>Địa chỉ nhận hàng:</b></label><label style="color: red;">&nbsp *</label>
    						<form:textarea path="diaChiNhan" class="form-control" rows="3"/>
    						<form:errors path="diaChiNhan" style="color: red; font-size: 12px;"></form:errors>
 						 </div>
 						 <div class = "row">
 						 <div class="col-md-4">
    						<label class="form-label"><b>Email nhận hàng: </b></label>
    							<form:input path="emailNhan" class="form-control" />
 						 </div>
    					<div class="col-md-3">
    						<label class="form-label"><b>Số điện thoại</b></label><label style="color: red;">&nbsp *</label>
    						<form:input path="sDTNhan" class="form-control" />
    						<form:errors path="sDTNhan" style="color: red; font-size: 12px;"></form:errors>
 						 </div>
    				</div>
					 <br>
					 <div class="col-md-6">
  						<button name="btnSave" class="btn btn-success">Xác nhận đặt hàng</button>
  					</div>
				</form:form>
			</div>
		</div>


    <a href="#" id="back-to-top" title="Back to top" style="display: none;">&uarr;</a>
</body>