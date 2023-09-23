<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
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



    <!-- Start Wishlist  -->
    <div class="wishlist-box-main">
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
						<th class="text-center" >Tiếp tục mua</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="sp" items="${dSSP}">
                                <tr>
                                	<td class="text-center"><img style = "heigh: 60px; width: 80px;" src="${sp.duongDan}" alt="Hình ảnh sản phẩm" /></td>
                                    <td class="text-center">${sp.ten} </td>
                                    <td class="text-center"><p>${sp.soLuong}</p></td>
                                    <td class="text-center"><p>${sp.getDonGia()}</p></td>
                                    <td class="text-center"><h6 class="money well">${sp.getGiaVN()}</h6></td>
                                    <td class="text-center">
                                    	<a href="../sanpham/${sp.maSVC}.htm?linkInfor">
									<button class="btn btn-primary btn-sm"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart-fill" viewBox="0 0 16 16">
  <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
</svg></button>
									</a>
                                    </td>
                                </tr>
                    </c:forEach>
				</tbody>
  				</table>
  				<p3>Tổng tiền:  </p3>  <b class="money well" style="margin-left: 5px; font-size: 20px; color: black;">${tongTien}</b>
  			</div>
  			<div class="col-md-6">
				<c:if test="${loaiTrang == 'choXacNhan'}">
					<button class="btn btn-danger"  type="button" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Huỷ đơn</button>
				</c:if>
  			</div>
        </div>
    </div>
    <!-- End Wishlist -->
    


<!-- Modal -->
<div class="modal fade " id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h2 class="modal-title" id="staticBackdropLabel" >Xác nhận huỷ</h2>
      </div>
      <div class="modal-body">
        Bạn có thật sự muốn huỷ đơn hàng đang chờ xác nhận này?
      </div>
      <div class="modal-footer">
        <a href="../donhang2/${gioHang.maGH}.htm?linkHuy"><button type="button" class="btn btn-primary">Xác nhận</button></a>
         <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Thoát</button>
      </div>
    </div>
  </div>
</div>

    <!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
</body>