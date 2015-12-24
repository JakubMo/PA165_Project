<%-- 
    Document   : detail
    Created on : Dec 15, 2015, 9:19:20 PM
    Author     : Marek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="my"%>

<my:layout title="Vehicle #${vehicle.id}">
    <jsp:attribute name="body">
        <script type="text/javascript">
            var viewModel = {
                maxMileageText: ko.observable(true),
                maxMileageInput: ko.observable(false),
                serviceCheckIntervalText: ko.observable(true),
                serviceCheckIntervalInput: ko.observable(false)
            };
            
            function showMaxMileage(value) {
                viewModel.maxMileageText(value);
                viewModel.maxMileageInput(!value);
            }
            
            function showServiceCheckInterval(value) {
                viewModel.serviceCheckIntervalText(value);
                viewModel.serviceCheckIntervalInput(!value);
            }
        </script>
        
        <div class="row" style="margin-bottom: 50px">
            <div style="float: right" class="col-md-4">
                <my:imagelink src="/image/vehicle/${vehicle.id}.jpg" />
            </div>
            
            <div class="col-md-5">
                <my:vehiclealert vehicle="${vehicle}" serviceChecks="${serviceChecks}" />
                
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
                        <td><c:out value="${vehicle.mileage} km" /></td>
                    </tr>
                    <tr>
                        <th>Max mileage:</th>
                        <td>
                            <span data-bind="visible: maxMileageText">
                                <c:out value="${vehicle.maxMileage} km" />
                                <sec:authorize access="hasAuthority('ADMIN')">
                                    <button class="btn-default" style="float: right" onclick="showMaxMileage(false); showServiceCheckInterval(true);">Edit</button>
                                </sec:authorize>
                            </span>
                            <span data-bind="visible: maxMileageInput">
                                <sec:authorize access="hasAuthority('ADMIN')">
                                    <form method="post" action="${pageContext.request.contextPath}/vehicle/update/${vehicle.id}">
                                        <input name="maxMileage" value="${vehicle.maxMileage}" type="number" min="100000" required style="width: 80px" />
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                                        <button type="button" class="btn-default" style="float: right" onclick="showMaxMileage(true);">Cancel</button>
                                        <button type="submit" class="btn-primary" style="float: right" onsubmit="showMaxMileage(true);">Update</button>
                                    </form>
                                </sec:authorize>
                            </span>
                        </td>
                    </tr>
                    <tr>
                        <th>Service check interval:</th>
                        <td>
                            <span data-bind="visible: serviceCheckIntervalText">
                                <c:out value="${vehicle.serviceCheckInterval} months" />
                                <sec:authorize access="hasAuthority('ADMIN')">
                                    <button class="btn-default" style="float: right" onclick="showServiceCheckInterval(false); showMaxMileage(true);">Edit</button>
                                </sec:authorize>
                            </span>
                            <span data-bind="visible: serviceCheckIntervalInput">
                                <sec:authorize access="hasAuthority('ADMIN')">
                                    <form method="post" action="${pageContext.request.contextPath}/vehicle/update/${vehicle.id}">
                                        <input name="serviceCheckInterval" value="${vehicle.serviceCheckInterval}" type="number" min="1" max="12" required style="width: 80px" />
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                                        <button type="button" class="btn-default" style="float: right" onclick="showServiceCheckInterval(true);">Cancel</button>
                                        <button type="submit" class="btn-primary" style="float: right" onsubmit="showServiceCheckInterval(true);">Update</button>
                                    </form>
                                </sec:authorize>
                            </span>
                        </td>
                    </tr>
                </table>                
                
                <form method="post" action="${pageContext.request.contextPath}/vehicle/delete/${vehicle.id}" 
                      onsubmit="return confirm('Do you really want to delete this vehicle?');">
                    <a href="${pageContext.request.contextPath}/vehicle/list" class="btn btn-default">Back</a>
                    <sec:authorize access="hasAuthority('ADMIN')">
                        <button type="submit" class="btn btn-danger">Delete</button>
                    
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" >
                    </sec:authorize>
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

        <sec:authorize access="hasAuthority('ADMIN')">
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
        </sec:authorize>
    </jsp:attribute>
</my:layout>
