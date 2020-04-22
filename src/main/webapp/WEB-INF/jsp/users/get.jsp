<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" class="com.temple.polymorphic.toolbox.dto.UserDto"/>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="apple-touch-icon" sizes="76x76" href="/images/apple-icon.png">
    <link rel="icon" type="image/png" href="/images/favicon.png">
    <title>
        Blk• Design System by Creative Tim
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



<body class="index-page">
<!-- Navbar -->
<nav class="navbar navbar-expand-lg fixed-top navbar-transparent " color-on-scroll="100">
    <div class="container">
        <div class="navbar-translate">
            <a class="navbar-brand" href="/api/admin" rel="tooltip"  data-placement="bottom" >
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
<%--                <li class="nav-item p-0">--%>
<%--                    <a class="nav-link" rel="tooltip" title="Follow us on Twitter" data-placement="bottom" href="" target="_blank">--%>
<%--                        <i class="fab fa-twitter"></i>--%>
<%--                        <p class="d-lg-none d-xl-none">Twitter</p>--%>
<%--                    </a>--%>
<%--                </li>--%>
<%--                <li class="nav-item p-0">--%>
<%--                    <a class="nav-link" rel="tooltip" title="Like us on Facebook" data-placement="bottom" href="" target="_blank">--%>
<%--                        <i class="fab fa-facebook-square"></i>--%>
<%--                        <p class="d-lg-none d-xl-none">Facebook</p>--%>
<%--                    </a>--%>
<%--                </li>--%>
<%--                <li class="nav-item p-0">--%>
<%--                    <a class="nav-link" rel="tooltip" title="Follow us on Instagram" data-placement="bottom" href="" target="_blank">--%>
<%--                        <i class="fab fa-instagram"></i>--%>
<%--                        <p class="d-lg-none d-xl-none">Instagram</p>--%>
<%--                    </a>--%>
<%--                </li>--%>
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
                <h1 class="h1-seo">View All Users</h1>
                <div th:case="*">
                    <table class="table table-striped table-responsive-md">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email</th>
                            <th>Role</th>
                            <th>Edit</th>
                            <th>Permissions</th>
                            <th>Delete</th>
                        </tr>
                        </thead>
                        <c:forEach items="${list}" var="user">
                            <tr>
                                <td>${user.id}</td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>${user.email}</td>
                                <td>${user.role}</td>
                                <td><form:form name="edit" method="GET" action="/api/users/update/${user.email}"><input type="submit" value="Edit"></form:form></td>
                                <td><form:form name="permissions" method="GET" action="/api/users/permissions/get/${user.email}"><input type="submit" value="Permissions"></form:form></td>
                                <td><form:form name="delete" method="GET" action="/api/users/delete/${user.email}"><input type="submit" value="Delete"></form:form></td>
                            </tr>
                        </c:forEach>
                        </tr>
                    </table>
                </div>
                <tr>
                    <td>Return to :</td>
                    <td><a href="/api/users/">Manage Users</a></td>
                </tr>

            </div>
            </div>
        </div>


    </div>

<div class="section section-download" id="#download-section" data-background-color="black">
    <img src="assets/img/path1.png" class="path">
    <div class="container">
        <div class="row justify-content-md-center">
            <div class="text-center col-md-12 col-lg-8">
                <h2 class="title">Do you love this Bootstrap 4 Design System?</h2>
                <h4 class="description">Cause if you do, it can be yours for FREE. Hit the button below to navigate to Creative Tim where you can find the design system in HTML format. Start a new project or give an old Bootstrap project a new look!</h4>
            </div>
            <div class="text-center col-md-12 col-lg-8">
                <a href="https://www.creative-tim.com/product/blk-design-system" class="btn btn-primary btn-round btn-lg" role="button">
                    Download HTML
                </a>
                <!-- <a href="https://www.invisionapp.com/now" target="_blank" class="btn btn-primary btn-lg btn-simple btn-round" role="button">
                          Download PSD/Sketch
                      </a> -->
            </div>
        </div>
        <br>
        <br>
        <br>
        <!-- <div class="row text-center mt-5">
              <div class="col-md-8 ml-auto mr-auto">
                  <h2>Want more?</h2>
                  <h5 class="description">We've just launched <a href="http://demos.creative-tim.com/black-kit-pro/presentation.html" target="_blank">BLK• Design System PRO</a>. It has a huge number of components, sections and example pages. Start Your Development With A Badass Bootstrap 4 Design System.</h4>
              </div>
              <div class="col-md-12">
                  <a href="http://demos.creative-tim.com/black-kit-pro/presentation.html" class="btn btn-neutral btn-round btn-lg" target="_blank">
                      <i class="tim-icons icon-cloud-upload-94"></i> Upgrade to PRO
                  </a>
              </div>
          </div> -->
        <br>
        <br>
        <div class="row row-grid align-items-center my-md">
            <div class="col-lg-6">
                <h3 class="text-primary font-weight-light mb-2">Thank you for supporting us!</h3>
                <h4 class="mb-0 font-weight-light">Let's get in touch on any of these platforms.</h4>
            </div>
            <div class="col-lg-6 text-lg-center btn-wrapper">
                <a id="twitter" class="btn btn-icon btn-twitter btn-round btn-lg" data-toggle="tooltip" data-original-title="Tweet!">
                    <i class="fab fa-twitter"></i>
                </a>
                <a id="facebook" class="btn btn-icon btn-facebook btn-round btn-lg" data-toggle="tooltip" data-original-title="Share!">
                    <i class="fab fa-facebook-square"></i>
                </a>
                <a target="_blank" href="https://github.com/creativetimofficial" class="btn btn-icon btn-github btn-round btn-lg" data-toggle="tooltip" data-original-title="Star on Github">
                    <i class="fab fa-github"></i>
                </a>
            </div>
        </div>
    </div>
</div>

    <!--  End Modal -->

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
<!--  Plugin for the Sliders-->
<script src="/js/plugins/nouislider.min.js" type="text/javascript"></script>
<!-- Chart JS -->
<script src="/js/plugins/chartjs.min.js"></script>
<!--  Plugin for the DatePicker-->
<script src="/js/plugins/moment.min.js"></script>
<script src="/js/plugins/bootstrap-datetimepicker.js" type="text/javascript"></script>
<!-- Black Dashboard DEMO methods, (take this out) -->
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




