<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Polymorphic Toolbox</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="apple-touch-icon" sizes="76x76" href="/images/apple-icon.png">
    <link rel="icon" type="image/png" href="/images/favicon.png">

    <!--     Fonts and icons     -->
    <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,600,700,800" rel="stylesheet" />
    <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
    <!-- Nucleo Icons -->
    <link href="/css/nucleo-icons.css" rel="stylesheet" />
    <!-- CSS Files -->
    <link href="/css/blk-design-system.css?v=1.0.0" rel="stylesheet" />
    <!-- CSS Just for demo purpose, (probably going to take this out) -->
    <link href="/demo/demo.css" rel="stylesheet" />
    <%--    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />--%>
</head>
<body class="index-page">

<!-- Navbar -->
<nav class="navbar navbar-expand-lg fixed-top navbar-transparent " color-on-scroll="100">
    <div class="container">
        <div class="navbar-translate">
            <a class="navbar-brand" href="/home" rel="tooltip" title="" data-placement="bottom" >
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
                        <i class="fa fa-cogs d-lg-none d-xl-none"></i> Administrator
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
                    <a class="nav-link btn btn-default d-none d-lg-block" href="/api/aboutUs" role = "button" onclick="scrollToDownload()">
                        About Us
                    </a>
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
    <img src="/images/dots.png" class = "dots">
    <div class="page-header header-filter">
        <div class="squares square1"></div>
        <div class="squares square2"></div>
        <div class="squares square3"></div>
        <div class="squares square4"></div>
        <div class="squares square5"></div>
        <div class="squares square6"></div>
        <div class="squares square7"></div>
        <div class="container">
            <div class="content-center brand">
                <%--                <h1 class="h1-seo"></h1>--%>
                <h3>Welcome</h3>
                <div class="row justify-content-md-center">
                    <div class="col-lg-3">
                        <img class="img-fluid" src="/images/manage-users.png">
                        <br><br>
                        <p class="text-white mb-3">Invite user to join the Polymorphic Toolbox and experience the wonderful simplicity of our platform. Modify or delete users when necessary.</p>
                        <div class="btn-wrapper mb-3">
                            <p class="category text-success d-inline">Manage Users</p>
                            <a href="/api/users" class="btn btn-success btn-link btn-sm"><i class="tim-icons icon-minimal-right"></i></a>
                        </div>
                        <div class="btn-wrapper">

                        </div>
                    </div>
                    <div class="col-lg-3">
                        <img class="img-fluid" src="/images/manage-servers.png">
                        <br><br>
                        <p class="text-white mb-3">Add new servers by IP and give privillages to the clients so file transfer operations can take place.
                            Modify or delete servers when the infrasture changes.</p>
                        <div class="btn-wrapper mb-3">
                            <p class="category text-success d-inline">Manage Servers</p>
                            <a href="/api/servers" class="btn btn-success btn-link btn-sm"><i class="tim-icons icon-minimal-right"></i></a>
                        </div>
                        <div class="btn-wrapper">

                        </div>
                    </div>
                    <div class="col-lg-3">
                        <img class="img-fluid" src="/images/client-view.jpg">
                        <br><br>
                        <p class="text-white mb-3">Client view allows the Admin of the infrastracture to select and execute operation like a simple user.
                            The Admin can transfer files within the registered servers. Furthermore, back up and retrieve from the cloud storage facility.</p>
                        <div class="btn-wrapper mb-3">
                            <p class="category text-success d-inline">Client Dashboard</p>
                            <a href="/client" class="btn btn-success btn-link btn-sm"><i class="tim-icons icon-minimal-right"></i></a>
                        </div>
                        <div class="btn-wrapper">

                        </div>
                    </div>
                    <%--                    <div class="text-center col-md-12 col-lg-8">--%>
                    <%--                        <a href="/api/admin" class="btn btn-primary btn-round btn-lg" role="button">--%>
                    <%--                            Admin Dashboard--%>
                    <%--                        </a>--%>
                    <%--                    </div>--%>

                </div>
            </div>
        </div>
    </div>

    <!--  End Modal -->
</div>
<footer class="footer">
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <h1 class="title">POLYMORPHIC TOLLBOX</h1>
            </div>
            <div class="col-md-3">
                <ul class="nav">
                    <li class="nav-item">
                        <a href="/api/admin" class="nav-link">
                            Home
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="" class="nav-link">
                            Landing
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="" class="nav-link">
                            Register
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="" class="nav-link">
                            Profile
                        </a>
                    </li>
                </ul>
            </div>
            <div class="col-md-3">
                <ul class="nav">
                    <li class="nav-item">
                        <a href="" class="nav-link">
                            Contact Us
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="" class="nav-link">
                            About Us
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="" class="nav-link">
                            Blog
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="" class="nav-link">
                            License
                        </a>
                    </li>
                </ul>
            </div>
            <div class="col-md-3">
                <h3 class="title">Follow us:</h3>
                <div class="btn-wrapper profile">
                    <a target="_blank" href="" class="btn btn-icon btn-neutral btn-round btn-simple" data-toggle="tooltip" data-original-title="Follow us">
                        <i class="fab fa-twitter"></i>
                    </a>
                    <a target="_blank" href="" class="btn btn-icon btn-neutral btn-round btn-simple" data-toggle="tooltip" data-original-title="Like us">
                        <i class="fab fa-facebook-square"></i>
                    </a>
                    <a target="_blank" href="" class="btn btn-icon btn-neutral  btn-round btn-simple" data-toggle="tooltip" data-original-title="Follow us">
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
<!--  Plugin for Switches -->
<script src="/js/plugins/bootstrap-switch.js"></script>
<!--  Plugin for the Sliders -->
<script src="/js/plugins/nouislider.min.js" type="text/javascript"></script>
<!-- Chart JS -->
<script src="/js/plugins/chartjs.min.js"></script>
<!--  Plugin for the DatePicker-->
<script src="/js/plugins/moment.min.js"></script>
<script src="/js/plugins/bootstrap-datetimepicker.js" type="text/javascript"></script>
<!-- Black Dashboard DEMO methods, (might get rid of this) -->
<script src="/demo/demo.js"></script>
<!-- Control Center for Black UI Kit: parallax effects, scripts for the example pages etc -->
<script src="/js/blk-design-system.min.js?v=1.0.0" type="text/javascript"></script>
<script>
    $(document).ready(function() {
        blackKit.initDatePicker();
        blackKit.initSliders();
    });
    function scrollToDownload() {
        if ($('.section-download').length != 0) {
            $("html, body").animate({
                scrollTop: $('.section-download').offset().top
            }, 1000);
        }
    }
</script>

</body>
</html>

