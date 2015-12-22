<%--
  User: Mário Kudolani
  Date: 16.12.2015
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="my"%>

<my:layout title="Drives">
    <jsp:attribute name="body">
        <div class="row" style="margin: 5px">
            <a href="${pageContext.request.contextPath}/drive/new" role="button" class="col-lg-1 col-md-2 col-sm-2 col-xs-3 btn btn-primary">New Drive</a>
        </div>
        <div class="row" style="margin-top: 25px">
            <c:forEach items="${drives}" var="drive">
                <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                    <div class="thumbnail">
                        <img src="${pageContext.request.contextPath}/image/vehicle/${drive.vehicle.id}.jpg"
                            style="height: 150px"
                             onerror="this.onerror = null; if (this.src != '${pageContext.request.contextPath}/image/error.png') this.src = '${pageContext.request.contextPath}/image/error.png';"
                            />
                        <div class="caption">
                            <table class="table">
                                <tr>
                                    <th>Brand:</th>
                                    <td><c:out value="${drive.vehicle.brand}"/></td>
                                </tr>
                                <tr>
                                    <th>Model:</th>
                                    <td><c:out value="${drive.vehicle.model}"/></td>
                                </tr>
                                <tr>
                                    <th>Year of Production:</th>
                                    <td><c:out value="${drive.vehicle.yearOfProduction}"/></td>
                                </tr>
                            </table>
                        </div>
                        <div class="caption">
                            <h3>Drive</h3>
                            <table class="table">
                                <tr>
                                    <th>Employee:</th>
                                    <td><c:out value="${drive.employee.firstname}"/> <c:out value="${drive.employee.lastname}"/></td>
                                </tr>
                                <tr>
                                    <th>Star Date:</th>
                                    <td><fmt:formatDate value="${drive.startDate}" pattern="yyyy-MM-dd"/></td>
                                </tr>
                                <tr>
                                    <th>End Date:</th>
                                    <td><fmt:formatDate value="${drive.endDate}" pattern="yyyy-MM-dd"/></td>
                                </tr>
                                <tr>
                                    <th>Km:</th>
                                    <td>
                                        <c:if test="${drive.driveStatus.getValue() == 2}">
                                            <c:out value="${drive.km}"/> km
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Status:</th>
                                    <td><c:out value="${drive.driveStatus}"/></td>
                                </tr>
                            </table>
                            <div style="display: flex; ">
                            <a href="${pageContext.request.contextPath}/drive/${drive.id}" role="button" class="btn btn-primary">Details</a>
                            <c:if test="${drive.driveStatus.getValue() == 1}">
                                <form method="post" action="${pageContext.request.contextPath}/drive/complete/${drive.id}" style="margin-left: 5px">
                                    <button type="submit" class="btn btn-success">Complete</button>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" >
                                </form>
                            </c:if>
                            <c:if test="${drive.driveStatus.getValue() < 2}">
                                <form method="post" action="${pageContext.request.contextPath}/drive/cancel/${drive.id}" style="margin-left: 5px">
                                    <button type="submit" class="btn btn-danger">Cancel</button>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" >
                                </form>
                            </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

    </jsp:attribute>
</my:layout>
