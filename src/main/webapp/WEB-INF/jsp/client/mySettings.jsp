<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="apple-touch-icon" sizes="76x76" href="/images/apple-icon.png">
    <link rel="icon" type="image/png" href="/images/favicon.png">
    <title>
        Polymorphic Toolbox
    </title>
    <!--     Fonts and icons     -->
    <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,600,700,800" rel="stylesheet" />
    <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
    <!-- Nucleo Icons -->
    <link href="/css/nucleo-icons.css" rel="stylesheet" />
    <!-- CSS Files -->
    <link href="/css/blk-design-system.css?v=1.0.0" rel="stylesheet" />
    <%--    <!-- CSS Just for demo purpose, don't include it in your project -->--%>
    <%--    <link href="/demo/demo.css" rel="stylesheet" />--%>
</head>

<body class="register-page">

<!-- Navbar -->
<nav class="navbar navbar-expand-lg fixed-top navbar-transparent " color-on-scroll="100">
    <div class="container">
        <div class="navbar-translate">
            <a class="navbar-brand" href="/client" rel="tooltip" title="" data-placement="bottom" >
                <span>POLYMORPHIC TOOLBOX</span>
            </a>
            <button class="navbar-toggler navbar-toggler" type="button" data-toggle="collapse" data-target="#navigation" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-bar bar1"></span>
                <span class="navbar-toggler-bar bar2"></span>
                <span class="navbar-toggler-bar bar3"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse justify-content-end" id="navigation">
            <div class="navbar-collapse-header">
                <div class="row">
                    <div class="col-6 collapse-brand">
                        <a>
                            POLYMORPHIC TOOLBOX
                        </a>
                    </div>
                    <div class="col-6 collapse-close text-right">
                        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navigation" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">
                            <i class="tim-icons icon-simple-remove"></i>
                        </button>
                    </div>
                </div>
            </div>
            <ul class="navbar-nav">
                <li class="nav-item p-0">
                    <a class="nav-link" rel="tooltip" title="Follow us on Twitter" data-placement="bottom" href="" target="_blank">
                        <i class="fab fa-twitter"></i>
                        <p class="d-lg-none d-xl-none">Twitter</p>
                    </a>
                </li>
                <li class="nav-item p-0">
                    <a class="nav-link" rel="tooltip" title="Like us on Facebook" data-placement="bottom" href="" target="_blank">
                        <i class="fab fa-facebook-square"></i>
                        <p class="d-lg-none d-xl-none">Facebook</p>
                    </a>
                </li>
                <li class="nav-item p-0">
                    <a class="nav-link" rel="tooltip" title="Follow us on Instagram" data-placement="bottom" href="" target="_blank">
                        <i class="fab fa-instagram"></i>
                        <p class="d-lg-none d-xl-none">Instagram</p>
                    </a>
                </li>
                <li class="dropdown nav-item">
                    <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown">
                        <i class="fa fa-cogs d-lg-none d-xl-none"></i> Getting started
                    </a>
                    <div class="dropdown-menu dropdown-with-icons">
                        <a href="" class="dropdown-item">
                            <i class="tim-icons icon-paper"></i> Documentation
                        </a>
                        <a href="" class="dropdown-item">
                            <i class="tim-icons icon-bullet-list-67"></i>Register Page
                        </a>
                        <a href="" class="dropdown-item">
                            <i class="tim-icons icon-image-02"></i>Landing Page
                        </a>
                        <a href="" class="dropdown-item">
                            <i class="tim-icons icon-single-02"></i>Profile Page
                        </a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link btn btn-default d-none d-lg-block" href="/logout" role = "button" onclick="scrollToDownload()">
                        Logout
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<!-- End Navbar -->


<div class="wrapper">
    <div class="page-header">
        <div class="page-header-image"></div>
        <div class="content">
            <div class="container">
                <div class="row">
                    <div class="col-lg-5 col-md-6 offset-lg-0 offset-md-3">
                        <div id="square7" class="square square-7"></div>
                        <div id="square8" class="square square-8"></div>
                        <div class="card card-register">
                            <div class="card-header">
                                <img class="card-img" src="/images/square1.png" alt="Card image">
                                <h3 class="card-title">Update</h3>
                            </div>
                            <%--                            <form:form class = "form" method = "POST" action = "/api/users/save">--%>
                            <div class="card-body">
                                <h3>My Information</h3>
                                    <h4>User: ${email}</h4>
                                <form:form method = "POST" action = "/client/mySettings" autocomplete="off">
                                    <div class="card-body">

                                        <div class="input-group">
                                            <div class="input-group-prepend">
                                                <div class="input-group-text">
                                                    <i class="tim-icons icon-lock-circle"></i>
                                                </div>
                                            </div>
                                            <form:input path = "firstName" type="text" class="form-control" placeholder="First Name" autocomplete="false"/>
                                        </div>
                                        <div class="input-group">
                                            <div class="input-group-prepend">
                                                <div class="input-group-text">
                                                    <i class="tim-icons icon-lock-circle"></i>
                                                </div>
                                            </div>
                                            <form:input path = "lastName" type="text" class="form-control" placeholder="Last Name" autocomplete="false"/>
                                        </div>
                                        <div class="input-group">
                                            <div class="input-group-prepend">
                                                <div class="input-group-text">
                                                    <i class="tim-icons icon-lock-circle"></i>
                                                </div>
                                            </div>
                                            <form:input path = "password" type="password" class="form-control" placeholder="Password" autocomplete="false"/>
                                        </div>

                                        <div class="input-group">
                                            <div>
                                                <form:input path = "role" type="hidden" class="form-control" value="${userDto.role}" autocomplete="off"/>
                                            </div>
                                            <div>
                                                <form:input path = "email" type="hidden" class="form-control" value="${email}" autocomplete="off"/> Hint: Password has to be at least 8 characters
                                            </div>
                                        </div>
                                    </div>

                                    <div class="card-footer">
                                        <input type = "submit" value="Update" class="btn btn-info btn-round btn-lg">
                                    </div>
                                </form:form>
                            </div>
                        </div>

                    </div>
                </div>
                    <div class="register-bg"></div>
                    <div id="square1" class="square square-1"></div>
                    <div id="square2" class="square square-2"></div>
                    <div id="square3" class="square square-3"></div>
                    <div id="square4" class="square square-4"></div>
                    <div id="square5" class="square square-5"></div>
                    <div id="square6" class="square square-6"></div>
                </div>
            </div>
        </div>
        <footer class="footer">
            <div class="container">
                <div class="row">
                    <div class="col-md-3">
                        <h1 class="title">PolyMorphic Toolbox</h1>
                    </div>
                    <div class="col-md-3">
                        <ul class="nav">
                            <li class="nav-item">
                                <a href="../index.html" class="nav-link">
                                    Home
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="../examples/landing-page.html" class="nav-link">
                                    Landing
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="../examples/register-page.html" class="nav-link">
                                    Register
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="../examples/profile-page.html" class="nav-link">
                                    Profile
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div class="col-md-3">
                        <ul class="nav">
                            <li class="nav-item">
                                <a href="https://creative-tim.com/contact-us" class="nav-link">
                                    Contact Us
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="https://creative-tim.com/about-us" class="nav-link">
                                    About Us
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="https://creative-tim.com/blog" class="nav-link">
                                    Blog
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="https://opensource.org/licenses/MIT" class="nav-link">
                                    License
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div class="col-md-3">
                        <h3 class="title">Follow us:</h3>
                        <div class="btn-wrapper profile">
                            <a target="_blank" href="https://twitter.com/creativetim" class="btn btn-icon btn-neutral btn-round btn-simple" data-toggle="tooltip" data-original-title="Follow us">
                                <i class="fab fa-twitter"></i>
                            </a>
                            <a target="_blank" href="https://www.facebook.com/creativetim" class="btn btn-icon btn-neutral btn-round btn-simple" data-toggle="tooltip" data-original-title="Like us">
                                <i class="fab fa-facebook-square"></i>
                            </a>
                            <a target="_blank" href="https://dribbble.com/creativetim" class="btn btn-icon btn-neutral  btn-round btn-simple" data-toggle="tooltip" data-original-title="Follow us">
                                <i class="fab fa-dribbble"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
    </div>
    <!--   Core JS Files   -->
    <script src="/js/core/jquery.min.js" type="text/javascript"></script>
    <script src="/js/core/popper.min.js" type="text/javascript"></script>
    <script src="/js/core/bootstrap.min.js" type="text/javascript"></script>
    <script src="/js/plugins/perfect-scrollbar.jquery.min.js"></script>
    <!--  Plugin for Switches, full documentation here: http://www.jque.re/plugins/version3/bootstrap.switch/ -->
    <script src="/js/plugins/bootstrap-switch.js"></script>
    <!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
    <script src="/js/plugins/nouislider.min.js" type="text/javascript"></script>
    <!-- Chart JS -->
    <script src="/js/plugins/chartjs.min.js"></script>
    <!--  Plugin for the DatePicker, full documentation here: https://github.com/uxsolutions/bootstrap-datepicker -->
    <script src="/js/plugins/moment.min.js"></script>
    <script src="/js/plugins/bootstrap-datetimepicker.js" type="text/javascript"></script>
    <%--<!-- Black Dashboard DEMO methods, don't include it in your project! -->--%>
    <%--<script src="/demo/demo.js"></script>--%>
    <!-- Control Center for Black UI Kit: parallax effects, scripts for the example pages etc -->
    <script src="/js/blk-design-system.min.js?v=1.0.0" type="text/javascript"></script>
</body>

</html>