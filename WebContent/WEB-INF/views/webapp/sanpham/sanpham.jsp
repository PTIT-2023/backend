<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
   <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <html lang="en">
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
                        <li class="nav-item active"><a class="nav-link" href="../sanpham/sanpham.htm">Sản phẩm</a></li>
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

    <!-- Start Gallery  -->
    <div class="products-box">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="title-all text-center">
                        <h1>Sản phẩm</h1>
                        <p>------------------------------------------------------------------------------------------</p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="special-menu text-center">
                        <div class="button-group filter-button-group">
                            <button class="active" data-filter="*">Tất cả</button>
							<button data-filter=".podded-vegetables">Động vật</button>
							<button data-filter=".root-and-tuberous">Thực vật</button>
                        </div>
                    </div>
                </div>
            </div>

		<jsp:useBean id="pagedListHolder" scope="request"
				type="org.springframework.beans.support.PagedListHolder" />
			<c:url value="../sanpham/sanpham.htm" var="pagedLink">
				<c:param name="p" value="~" />
			</c:url> 
            <div class="row special-list">
            <c:if test="${pagedListHolder.pageList.size() > 0}">
            <c:forEach var="sp" items="${pagedListHolder.pageList}">
            <c:if test="${sp.getMaLoai().getMaLoai() == 'DV'}">
           	 	<div class="col-lg-3 col-md-6 special-grid podded-vegetables">
           	 </c:if>
           	 <c:if test="${sp.getMaLoai().getMaLoai() == 'TV'}">
           	 	<div class="col-lg-3 col-md-6 special-grid root-and-tuberous">
           	 </c:if>
           	 	<div class="products-single fix">
                        <div style="width: 255px; height: 140px;" class="box-img-hover">
                            <img src="${sp.getHinhAnhList().get(0).getDuongDan()}" class="img-fluid" alt="Image">
                            <div class="mask-icon">
                                <ul>
                                    <li><a href="../sanpham/${sp.maSVC}.htm?linkInfor" data-toggle="tooltip" data-placement="right" title="Xem chi tiết"><i class="fas fa-eye"></i></a></li>
                                </ul>
                            </div>
                        </div>    
                        <p style="font-size: 20px;">${sp.getTen()}</p>     
                        <b style="font-size: 25px; color: red;">${sp.getGiaVN()}</b>           
                    </div>
                    </div>
            <%-- <c:if test="${sp.getMaLoai().getMaLoai() == 'TV'}">
            	<div class="col-lg-3 col-md-6 special-grid root-and-tuberous">
            	<div class="products-single fix">
                        <div style="width: 255px; height: 140px;" class="box-img-hover">
                            <img src="${sp.getHinhAnhList().get(0).getDuongDan()}" class="img-fluid" alt="Image">
                            <div class="mask-icon">
                                <ul>
                                    <li><a href="../sanpham/${sp.maSVC}.htm?linkInfor" data-toggle="tooltip" data-placement="right" title="Xem chi tiết"><i class="fas fa-eye"></i></a></li>
                                </ul>
                            </div>
                        </div>    
                        <p style="font-size: 20px;">${sp.getTen()}</p>     
                        <b style="font-size: 25px; color: red;">${sp.getGiaVN()}</b>
                    </div>
                    </div>
            </c:if> --%>
            </c:forEach>
            </c:if>
             </div>
             <tg:paging pagedListHolder="${pagedListHolder}"
				pagedLink="${pagedLink}" />
            </div> 
        </div>
    </div>
     
    <!-- End Gallery  -->

         <div class="shop-detail-box-main">
        <div class="container">
	<div class="row my-5">
                <div class="col-lg-12">
                    <div class="title-all text-center">
                        <h1>Sản phẩm bán chạy</h1>
                    </div>
                    <div class="featured-products-box owl-carousel owl-theme">
                    <c:forEach var="spbc" items="${dSSPBC }">
                    	<div class="item">
                            <div class="products-single fix">
                                <div class="box-img-hover" style="width: 250px; height: 150px;">
                                    <img src="${spbc.hinhAnhList.get(0).duongDan}" class="img-fluid" alt="Image">
                                    <div class="mask-icon">
                                        <ul>
                                            <li><a href="../sanpham/${spbc.maSVC}.htm?linkInfor" data-toggle="tooltip" data-placement="right" title="Xem chi tiết"><i class="fas fa-eye"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="why-text">
                                    <h2>${spbc.ten}</h2>
                                    <h3 style="color: red;">Lượt mua: ${spbc.luotMua}</h3>
                                    <p><h5>${spbc.getGiaVN()}</h5><p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    </div>
                </div>
            </div>
            </div>
            </div>

    <!-- Start Instagram Feed  -->
    <div class="instagram-box">
        <div class="main-instagram owl-carousel owl-theme">
        <c:forEach var="spnn" items="${dSSPNN }">
        	<div class="item" style="width: 250px; height: 150px;">
                <div class="ins-inner-box">
                    <img src="${spnn.hinhAnhList.get(0).duongDan}" alt="" />
                    <div class="hov-in">
                        <a href="../sanpham/${spnn.maSVC}.htm?linkInfor"><i ></i></a>
                    </div>
                </div>
            </div>
        </c:forEach>
        </div>
    </div>

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
</body>

</html>