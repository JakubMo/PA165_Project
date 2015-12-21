<%-- 
    Document   : list
    Created on : Dec 15, 2015, 9:18:44 PM
    Author     : Marek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="my"%>

<my:layout title="Vehicles">
    <jsp:attribute name="body">
        <a href="${pageContext.request.contextPath}/vehicle/new" role="button" class="btn btn-primary">New vehicle</a>
        
        <div class="row" style="margin-top: 25px">
            <c:forEach items="${vehicles}" var="vehicle">
                <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12" style="height: 419px">
                    <div class="thumbnail" style="height: 399px">
                        <img src="${pageContext.request.contextPath}/image/vehicle/${vehicle.id}.jpg" style="height: 150px" 
                             onerror="this.onerror = null; if (this.src != '${pageContext.request.contextPath}/image/error.png') this.src = '${pageContext.request.contextPath}/image/error.png';" />
                        <div class="caption">
                            <table class="table">
                                <tr>
                                    <th>Brand:</th>
                                    <td><c:out value="${vehicle.brand}" /></td>
                                </tr>
                                <tr>
                                    <th>Model:</th>
                                    <td><c:out value="${vehicle.model}" /></td>
                                </tr>
                                <tr>
                                    <th>Year of production:</th>
                                    <td><c:out value="${vehicle.yearOfProduction}" /></td>
                                </tr>
                                <tr>
                                    <th>Mileage:</th>
                                    <td><c:out value="${vehicle.mileage}" /></td>
                                </tr>
                            </table>
                            <form method="post" action="${pageContext.request.contextPath}/vehicle/delete/${vehicle.id}" 
                                  onsubmit="return confirm('Do you really want to delete this vehicle?');">
                                <a href="${pageContext.request.contextPath}/vehicle/detail/${vehicle.id}" class="btn btn-primary">Details</a>
                                <button type="submit" class="btn btn-danger">Delete</button>
                                
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" >
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </jsp:attribute>
</my:layout>
