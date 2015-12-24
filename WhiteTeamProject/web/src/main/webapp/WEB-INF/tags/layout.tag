<%-- 
    Document   : layout
    Created on : Dec 16, 2015, 3:21:19 PM
    Author     : Marek
--%>

<%@tag description="Layout of the page." pageEncoding="UTF-8"%>

<%@attribute name="title" required="true"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="body" fragment="true"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title><c:out value="${title}"/></title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
        
        <script src="${pageContext.request.contextPath}/js/knockout-3.4.0.js" type="text/javascript"></script>
        <script>
            var viewModel;
            
            function needsServiceCheck(lastCheck, interval) {
                var last = new Date(lastCheck);
                var current = new Date();
                if(last.getDate() > current.getDate()) {
                    return false;
                }

                var diff = 12 * (current.getYear() - last.getYear()) + (current.getMonth() - last.getMonth());
                return diff >= interval;
            }
            
            function logoutFormSubmit() {
                document.getElementById("logoutForm").submit();
            }
        </script>
    </head>
    <body>
        <!-- navigation -->
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}">Home</a>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li><a href="${pageContext.request.contextPath}/drive/list">My Drives</a></li>                                                
                        <li><a href="${pageContext.request.contextPath}/vehicle/list">Vehicles</a></li>
                        <li><a href="${pageContext.request.contextPath}/employee/list">Employees</a></li>
                        <sec:authorize access="hasAuthority('ADMIN')">
                            <li><a href="${pageContext.request.contextPath}/servicecheck/list">Service Checks</a></li>
                            <li ><a href="${pageContext.request.contextPath}/drive/list?admin">All Drives</a></li>
                            <li ><a href="${pageContext.request.contextPath}/drive/list?requested">Requested Drives</a></li>
                        </sec:authorize>
                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Account <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href=<c:out value="${pageContext.request.contextPath}/employee/detail/principal"/>>Profile</a></li>
                                <li role="separator" class="divider"></li>
                                                                
                                <form action="<c:url value="/j_spring_security_logout" />" method="post" id="logoutForm">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                </form>
                                
                                <li><a href="javascript:logoutFormSubmit()">Logout</a></li>
                            </ul>
                        </li>   
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
        
        <!-- content -->
        <div class="container">
            <!-- header -->
            <div class="page-header">
                <h1><c:out value="${title}"/></h1>
            </div>

            <!-- alert messages -->
            <c:if test="${not empty alert_info}">
                <div class="alert alert-info" role="alert"><c:out value="${alert_info}"/></div>
            </c:if>
            <c:if test="${not empty alert_success}">
                <div class="alert alert-success" role="alert"><c:out value="${alert_success}"/></div>
            </c:if>
            <c:if test="${not empty alert_danger}">
                <div class="alert alert-danger" role="alert"><c:out value="${alert_danger}"/></div>
            </c:if>
            <c:if test="${not empty alert_warning}">
                <div class="alert alert-warning" role="alert"><c:out value="${alert_warning}"/></div>
            </c:if>

            <!-- body -->
            <jsp:invoke fragment="body"/>

            <!-- footer -->
            <footer class="footer" style="margin-top: 75px">                
                <p>&copy;&nbsp;<%=java.time.Year.now().toString()%>&nbsp;White Team</p>
            </footer>
        </div>
        
        <!-- javascript -->
        <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
        
        <script type="text/javascript">
            ko.applyBindings(viewModel);
        </script>
    </body>
</html>
