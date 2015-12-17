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
        <a href="${pageContext.request.contextPath}/vehicle/new" role="button" class="btn btn-default">New vehicle</a>
        
        <table class="table">
            <thead>
                <tr>
                    <th>VIN</th>
                    <th>Mileage</th>
                    <th>Production year</th>
                    <th>Brand</th>
                    <th>Model</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${vehicles}" var="vehicle">
                    <tr>
                        <td><c:out value="${vehicle.vin}" /></td>
                        <td><c:out value="${vehicle.mileage}" /></td>
                        <td><c:out value="${vehicle.yearOfProduction}" /></td>
                        <td><c:out value="${vehicle.brand}" /></td>
                        <td><c:out value="${vehicle.model}" /></td>
                        <td>
                            <form method="post" action="${pageContext.request.contextPath}/vehicle/delete/${vehicle.id}" 
                                  onsubmit="return confirm('Do you really want to delete this vehicle?');">
                                <a href="${pageContext.request.contextPath}/vehicle/detail/${vehicle.id}" class="btn btn-primary">Details</a>
                                <button type="submit" class="btn btn-danger">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
</my:layout>
