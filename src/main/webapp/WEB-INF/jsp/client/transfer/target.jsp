<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="tran" class="com.temple.polymorphic.toolbox.dto.TransferOperation"/>
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
                    <a class="nav-link btn btn-default d-none d-lg-block" href="javascript:void(0)" onclick="scrollToDownload()">
                        <i class="tim-icons icon-cloud-download-93"></i> Download
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
                <h1 class="h1-seo">File Directory For Server</h1>
                <div th:case="*">
                    <form:form method = "POST" action = "/client/transfer/scp" autocomplete="false">
                        <div class="container my-2">
                            <div th:case="*">
                                <table class="table table-striped table-responsive-md">
                                    <thead>
                                    <tr>
                                        <th>File Paths</th>
                                    </tr>
                                    </thead>
                                    <c:forEach items="${directory}" var="path">
                                        <tr>
                                            <td>${path}</td>
                                        </tr>
                                    </c:forEach>
                                    </tr>
                                </table>
                            </div>
                            <tr>
                                    <%--<td><form:label path = "email">Email</form:label></td>--%>
                                <td><form:input path = "email" type="hidden" value="${email}" autocomplete="false" /></td>
                            </tr>
                            <tr>
                                    <%--<td><form:label path = "srcServerId">Source Server</form:label></td>--%>
                                <td><form:input path = "srcServerId" type="hidden" value="${srcServerId}" autocomplete="false"/></td>
                            </tr>
                            <tr>
                                <td><form:input path = "filePath" type="hidden" value="${filePath}" autocomplete="false"/></td>
                            </tr>
                            <tr>
                                <td><form:input path = "dstServerId" type="hidden" value="${dstServerId}" autocomplete="false" /></td>
                            </tr>
                            <tr>
                                <td><form:input path = "targetPath" autocomplete="false" placeholder="Target Folder Path"/></td>
                            </tr>
                            <br><br>
                            <tr>
                                <td colspan = "2">
                                    <input type = "submit" value = "Set"/>
                                </td>
                            </tr>
                        </div>
                    </form:form>
                </div>
                <tr>
                    <td>Return to :</td>
                    <td><a href="/client">Client Dashboard</a></td>
                </tr>

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