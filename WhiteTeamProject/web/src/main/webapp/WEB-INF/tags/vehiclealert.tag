<%-- 
    Document   : vehiclealert
    Created on : Dec 23, 2015, 3:13:44 PM
    Author     : Marek
--%>

<%@tag import="java.util.GregorianCalendar"%>
<%@tag import="java.util.Collection"%>
<%@tag import="cz.muni.fi.pa165.project.dto.VehicleDTO"%>
<%@tag import="cz.muni.fi.pa165.project.dto.ServiceCheckDTO"%>
<%@tag description="Checks if vehicle needs service check and shows warning message." pageEncoding="UTF-8"%>

<%@attribute type="VehicleDTO" name="vehicle" required="true"%>
<%@attribute type="Collection<ServiceCheckDTO>" name="serviceChecks" required="true" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set value="<%= new GregorianCalendar(1, 1, 1).getTime() %>" var="last" />
<c:forEach items="${serviceChecks}" var="serviceCheck">
    <c:set value="${serviceCheck.serviceCheckDate}" var="date" />
    <c:if test="${ (date != null) && (date.after(last)) }">
        <c:set value="${date}" var="last" />
    </c:if>
</c:forEach>

<fmt:formatDate value="${last}" pattern="yyyy-MM-dd" var="lastCheck" />
<div class="alert alert-danger" data-bind="visible: needsServiceCheck('${lastCheck}', ${vehicle.serviceCheckInterval})">
    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
    <span>Vehicle needs service check.</span>
</div>