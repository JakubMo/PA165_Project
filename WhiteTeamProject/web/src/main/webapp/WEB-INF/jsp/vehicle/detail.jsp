<%-- 
    Document   : detail
    Created on : Dec 15, 2015, 9:19:20 PM
    Author     : Marek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="my"%>

<my:layout title="Vehicle #${vehicle.id}">
    <jsp:attribute name="body">
        <div class="row" style="margin-bottom: 50px">
            <div style="float: right" class="col-md-4">
                <my:imagelink src="/image/vehicle/${vehicle.id}.jpg" />
            </div>
            
            <div class="col-md-5">
                <table class="table">
                    <tr>
                        <th>ID:</th>
                        <td><c:out value="${vehicle.id}" /></td>
                    </tr>
                    <tr>
                        <th>VIN:</th>
                        <td><c:out value="${vehicle.vin}" /></td>
                    </tr>
                    <tr>
                        <th>Brand:</th>
                        <td><c:out value="${vehicle.brand}" /></td>
                    </tr>
                    <tr>
                        <th>Model:</th>
                        <td><c:out value="${vehicle.model}" /></td>
                    </tr>
                    <tr>
                        <th>Type:</th>
                        <td><c:out value="${vehicle.type}" /></td>
                    </tr>
                    <tr>
                        <th>Engine type:</th>
                        <td><c:out value="${vehicle.engineType}" /></td>
                    </tr>
                    <tr>
                        <th>Year of production:</th>
                        <td><c:out value="${vehicle.yearOfProduction}" /></td>
                    </tr>
                    <tr>
                        <th>Mileage:</th>
                        <td><c:out value="${vehicle.mileage}" /></td>
                    </tr>
                    <tr>
                        <th>Max mileage:</th>
                        <td><c:out value="${vehicle.maxMileage}" /></td>
                    </tr>
                    <tr>
                        <th>Service check interval:</th>
                        <td><c:out value="${vehicle.serviceCheckInterval}" /></td>
                    </tr>
                </table>                
                
                <form method="post" action="${pageContext.request.contextPath}/vehicle/delete/${vehicle.id}" 
                      onsubmit="return confirm('Do you really want to delete this vehicle?');">
                    <a href="${pageContext.request.contextPath}/vehicle/list" class="btn btn-default">Back</a>
                    <button type="submit" class="btn btn-danger">Delete</button>
                    
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" >
                </form>
            </div>
        </div>
        
        <h3>History of drives</h3>
        <div class="row" style="margin-bottom: 50px">
            <div class="col-md-6">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Start date</th>
                            <th>End date</th>
                            <th>Length</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${drives}" var="drive">
                            <tr>
                                <td><fmt:formatDate value="${drive.startDate}" pattern="yyyy-MM-dd" /></td>
                                <td><fmt:formatDate value="${drive.endDate}" pattern="yyyy-MM-dd" /></td>
                                <td><c:out value="${drive.km}" /></td>
                                <td><c:out value="${drive.driveStatus}" /></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
                
        <h3>List of service checks</h3>
        <div class="row" style="margin-bottom: 50px">
            <div class="col-md-6">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Date</th>
                            <th>Status</th>
                            <th>Report</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${serviceChecks}" var="sc">
                            <tr>
                                <td><fmt:formatDate value="${sc.serviceCheckDate}" pattern="yyyy-MM-dd" /></td>
                                <td><c:out value="${sc.status}" /></td>
                                <td><c:out value="${sc.report}" /></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </jsp:attribute>
</my:layout>
