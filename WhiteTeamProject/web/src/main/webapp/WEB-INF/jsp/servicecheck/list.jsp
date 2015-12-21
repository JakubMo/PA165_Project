<%--
  Created by IntelliJ IDEA.
  User: tborcin
  Date: 12/17/15
  Time: 10:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<my:layout title="Service checks">
<jsp:attribute name="body">
<a href="${pageContext.request.contextPath}/servicecheck/new" role="button" class="btn btn-primary">New Service
	Check</a>
	<div class="row" style="margin-top: 25px">

		<c:forEach items="${services}" var="serviceCheck">
			<div class="col-lg-4 col-md-5 col-sm-8 col-xs-14">
				<div class="thumbnail">
					<img src="${pageContext.request.contextPath}/image/vehicle/${serviceCheck.vehicle.id}.jpg"
							 style="height: 150px"
							 onerror="this.onerror = null; if (this.src != '${pageContext.request.contextPath}/image/error.png') this.src = '${pageContext.request.contextPath}/image/error.png';"/>

					<div class="caption">
						<table class="table">
							<tr>
								<th>Brand:</th>
								<td><c:out value="${serviceCheck.vehicle.brand}"/></td>
							</tr>
							<tr>
								<th>Model:</th>
								<td><c:out value="${serviceCheck.vehicle.model}"/></td>
							</tr>
							<tr>
								<th>Year of production:</th>
								<td><c:out value="${serviceCheck.vehicle.yearOfProduction}"/></td>
							</tr>
							<tr>
								<th>Mileage:</th>
								<td><c:out value="${serviceCheck.vehicle.mileage}"/></td>
							</tr>
						</table>
					</div>
					<div class="caption">
						<h3>Service check</h3>
						<table class="table">
							<tr>
								<th>Status:</th>
								<td><c:out value="${serviceCheck.status}"/></td>
							</tr>
							<tr>
								<th>Check date:</th>
								<td><c:out value="${serviceCheck.serviceCheckDate}"/></td>
							</tr>
							<tr>
								<th>Check employee:</th>
								<td><c:out value="${serviceCheck.serviceEmployee}"/></td>
							</tr>
							<tr>
								<th>Report:</th>
								<td><c:out value="${serviceCheck.report}"/></td>
							</tr>
						</table>
						<form method="post" action="${pageContext.request.contextPath}/servicecheck/delete/${serviceCheck.id}"
									onsubmit="return confirm('Do you want to delete this service check?');">
							<a href="${pageContext.request.contextPath}/servicecheck/detail/${serviceCheck.id}"
								 class="btn btn-primary">Details</a>
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
