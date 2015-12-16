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
                        <td>${vehicle.vin}</td>
                        <td>${vehicle.mileage}</td>
                        <td>${vehicle.yearOfProduction}</td>
                        <td>${vehicle.brand}</td>
                        <td>${vehicle.model}</td>
                        <td>
                            <a href="/vehicle/detail/${vehicle.id}" class="btn btn-primary">Details</a>
                        </td>
                        <td>
                            <form method="post" action="${pageContext.request.contextPath}/vehicle/delete/${vehicle.id}">
                                <button type="submit" class="btn btn-primary">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
</my:layout>
