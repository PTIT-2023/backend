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
        <a href="../donhang2/index.htm"><button type="button" class="btn btn-success">Chờ xác nhận</button></a>
        <a href="../donhang2/daxacnhan.htm"><button type="button" class="btn btn-secondary">Đã xác nhận</button></a>
        <a href="../donhang2/danggiao.htm"><button type="button" class="btn btn-secondary">Đang giao</button></a>
        <a href="../donhang2/dagiao.htm"><button type="button" class="btn btn-secondary">Đã nhận</button></a>
        <a href="../donhang2/dahuy.htm"><button type="button" class="btn btn-secondary">Đã huỷ</button></a>
            <div class="row">
                <div class="col-lg-12">
                    <div class="table-main table-responsive">
                    <c:if test="${pagedListHolder.pageList.size() == 0 or pagedListHolder.pageList.size() == null}">
                    	<h2>Không có đơn hàng nào chờ xác nhận!</h2>
                    </c:if>
                    <c:if test="${pagedListHolder.pageList.size() != 0 and pagedListHolder.pageList.size() != null}">
							<jsp:useBean id="pagedListHolder" scope="request"
								type="org.springframework.beans.support.PagedListHolder" />
							<c:url value="../donhang2/index.htm" var="pagedLink">
								<c:param name="p" value="~" />
							</c:url>
							<table class="table">
                        	<colgroup>
                        		<col>
                        		<col>
                        		<col style="width: 30px;">
                        	</colgroup>
                            <thead>
                                <tr>
                                    <th>Tên người nhận</th>
                                    <th>Ngày đặt</th>
                                    <th>Chức năng</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach var="gh" items="${pagedListHolder.pageList}">
                                <tr>
                                    <td>${gh.ten}</td>
							<td>${gh.ngayDat}</td>
							<td>
								<a href="../donhang2/${gh.maGH}.htm?linkChiTiet">
									<u class="text-primary">Chi tiết</u>
								</a>
								&nbsp;
								<a href="#" type="button" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
									<u class="text-danger">Huỷ đơn</u>
								</a>
							</td>
                                </tr>
										<!-- Modal -->
										<div class="modal fade " id="staticBackdrop"
											data-bs-backdrop="static" data-bs-keyboard="false"
											tabindex="-1" aria-labelledby="staticBackdropLabel"
											aria-hidden="true">
											<div class="modal-dialog modal-dialog-centered">
												<div class="modal-content">
													<div class="modal-header">
														<h2 class="modal-title" id="staticBackdropLabel">Xác
															nhận huỷ</h2>
													</div>
													<div class="modal-body">Bạn có thật sự muốn huỷ đơn
														hàng này?</div>
													<div class="modal-footer">
														<a href="../donhang2/${gh.maGH}.htm?linkHuy"><button
																type="button" class="btn btn-primary">Xác nhận</button></a>
														<button type="button" class="btn btn-secondary"
															data-bs-dismiss="modal">Thoát</button>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
                            </tbody>
                        </table>
                        <tg:paging pagedListHolder="${pagedListHolder}"
						pagedLink="${pagedLink}" />
                     </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- End Wishlist -->

	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary" data-bs-toggle="modal"
		data-bs-target="#staticBackdrop3" id="test" hidden="true">
		Launch static backdrop modal</button>

	<!-- Modal -->
	<div class="modal fade" id="staticBackdrop3" data-bs-backdrop="static"
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

	<!-- Hidden -->
	<p id="hdnUpdate" hidden="true">${msg }</p>

	 <a href="#" id="back-to-top" title="Back to top" style="display: none;">&uarr;</a>

    <!-- ALL JS FILES -->
    <script src="../resources/khachhang/assets/js/jquery-3.2.1.min.js"></script>
    <script src="../resources/khachhang/assets/js/popper.min.js"></script>
    <script src="../resources/khachhang/assets/js/bootstrap.min.js"></script>
    <!-- ALL PLUGINS -->
    <script src="../resources/khachhang/assets/js/jquery.superslides.min.js"></script>
    <script src="../resources/khachhang/assets/js/bootstrap-select.js"></script>
    <script src="../resources/khachhang/assets/js/inewsticker.js"></script>
    <script src="../resources/khachhang/assets/js/bootsnav.js."></script>
    <script src="../resources/khachhang/assets/js/images-loded.min.js"></script>
    <script src="../resources/khachhang/assets/js/isotope.min.js"></script>
    <script src="../resources/khachhang/assets/js/owl.carousel.min.js"></script>
    <script src="../resources/khachhang/assets/js/baguetteBox.min.js"></script>
    <script src="../resources/khachhang/assets/js/form-validator.min.js"></script>
    <script src="../resources/khachhang/assets/js/contact-form-script.js"></script>
    <script src="../resources/khachhang/assets/js/custom.js"></script>
    <script src="https://cdn.baotrongit.com/Money-Format-Plugin/money_format.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
    
</body>
<script type="text/javascript">
		$('.money').simpleMoneyFormat();
		
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