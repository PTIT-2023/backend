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

    <!-- Start Shop Detail  -->
    <div class="shop-detail-box-main">
        <div class="container">
            <div class="row">
                <div class="col-xl-5 col-lg-5 col-md-6">
                    <div id="carousel-example-1" class="single-product-slider carousel slide" data-ride="carousel">
                        <div style="width: 415px; height: 310px;" class="carousel-inner" role="listbox">
                        <div class="carousel-item active"> <img class="d-block w-100" src="${sp.hinhAnhList.get(0).duongDan}"> </div>
                        	<c:forEach var="ha" items="${sp.hinhAnhList}" >
                        		<c:if test="${ha.duongDan !=  sp.hinhAnhList.get(0).duongDan}">
                        			<div class="carousel-item"> <img class="d-block w-100" src="${ha.duongDan}"> </div>
                        		</c:if>
                        	</c:forEach>
                           <!--  <div class="carousel-item active"> <img class="d-block w-100" src="../resources/khachhang/assets/images/big-img-01.jpg" alt="First slide"> </div>
                            <div class="carousel-item"> <img class="d-block w-100" src="../resources/khachhang/assets/images/big-img-02.jpg" alt="Second slide"> </div> -->
                         
                        </div>
                        <a class="carousel-control-prev" href="#carousel-example-1" role="button" data-slide="prev"> 
						<i class="fa fa-angle-left" aria-hidden="true"></i>
						<span class="sr-only">Previous</span> 
					</a>
                        <a class="carousel-control-next" href="#carousel-example-1" role="button" data-slide="next"> 
						<i class="fa fa-angle-right" aria-hidden="true"></i> 
						<span class="sr-only">Next</span> 
					</a>
                    </div>
                    <c:if test="${sp.soLuongTon > 0}">
							<form:form modelAttribute="giohang" action="../giohang/them.htm" style = "margin-left: 140px;">
								<div hidden="true">
									<form:input path="maSVC"/>
									<%-- <form:select path="maSVC.maSVC" items="${svc}"
									itemValue="maSVC" itemLabel="ten"
									class="form-control"  aria-label=".form-select-lg example">
								</form:select>  --%>
									<form:input path="gia"/>
								</div>
									<label class="control-label" style="margin-left: 30px;">Số lượng</label>
									<form:input path="soLuong" value="1" min="1" max="50" type="number"/>
									<div class="" style="">
										<div class="">
											<button style="background-color: #B0B435; color: #FFFFFF; border: #B0B435; cursor: pointer;"><b style="font-size: 20px;">Thêm vào giỏ hàng</b></button>
											<!-- <a class="btn hvr-hover" data-fancybox-close="" href="../giohang/them.htm">Thêm vào giỏ hàng</a> -->
										</div>
									</div>
								</form:form>
						</c:if>
						<c:if test="${sp.soLuongTon <= 0}">
							<p style="color: red; font-size: 20px; margin-left: 60px;">Hết hàng, không thể thêm vào giỏ hàng!</p>
						</c:if>
                </div>
      
                <div class="col-xl-7 col-lg-7 col-md-6">
                    <div class="single-product-details">
                        <h2>${sp.ten}</h2>
                        <b class="money well" style="font-size: 25px; color: red;">${sp.getGiaVN()}</b>
                        <h3 style="color: #006600;">Lượt mua: ${sp.luotMua}</h3>
                        <h3 style="color: #006600;">Số lượng trong kho: ${sp.soLuongTon}</h3>
						<h4>Mô tả sản phẩm:</h4>
						<p>${sp.moTa}</p>
						<c:if test="${sp.moiTruongSong != null}">
							<h4>Môi trường sống:</h4>
							<p>${sp.moiTruongSong}</p>
						</c:if>
						<c:if test="${sp.nhietDoMoiTruong != null}">
							<h4>Nhiệt độ thích hợp:</h4>
							<p>${sp.nhietDoMoiTruong}</p>
						</c:if>
						<c:if test="${sp.doPH != null}">
							<h4>Độ PH:</h4>
							<p>${sp.doPH}</p>
						</c:if>
						<c:if test="${sp.viTri != null}">
							<h4>Vị trí:</h4>
							<p>${sp.viTri}</p>
						</c:if>
						<c:if test="${sp.hinhThucSinhSan != null}">
							<h4>Hình thức sinh sản:</h4>
							<p>${sp.hinhThucSinhSan}</p>
						</c:if>
						<c:if test="${sp.loaiThucAn != null}">
							<h4>Loại thức ăn:</h4>
							<p>${sp.loaiThucAn}</p>
						</c:if>
						<c:if test="${sp.kichThuocToiDa != null}">
							<h4>Kích thước tối đa:</h4>
							<p>${sp.kichThuocToiDa}</p>
						</c:if>
					<!-- 	<ul>
							<li>
								<div class="form-group quantity-box">
									<label class="control-label">Số lượng</label>
									<input name = "soLuong" class="form-control" value="1" min="1" max="20" type="number">
								</div>
							</li> 
						</ul> -->
						
                    </div>
                </div>
            </div>

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
                                    <p><h5 class="money well">${spbc.getGiaVN()}</h5><p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <!-- End Cart -->

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
					<h2 class="modal-title" id="staticBackdropLabel">Thông báo</h2>
				</div>
				<div class="modal-body">${msg}</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						data-bs-dismiss="modal">&nbsp;&nbsp;&nbsp;OK&nbsp;&nbsp;&nbsp;</button>
				</div>
			</div>
		</div>
	</div>

	<!-- End Instagram Feed  -->

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
    <!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
</body>
<!-- Hidden -->
	<p id="hdnUpdate" hidden="true">${msg}</p>
	<!-- Hidden -->
</html>
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