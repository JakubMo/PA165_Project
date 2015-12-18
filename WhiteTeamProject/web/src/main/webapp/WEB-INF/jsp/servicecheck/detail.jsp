<%--
  Created by IntelliJ IDEA.
  User: tborcin
  Date: 12/17/15
  Time: 1:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="my" %>

<my:layout title="Service Check #${serviceCheck.id}">
    <jsp:attribute name="body">
        <div class="row">
					<div style="float: right" class="col-md-3">
						<my:imagelink src="http://www.crossfitpulse.com/wp-content/uploads/2012/12/question-mark.jpg"/>
					</div>

					<div class="col-md-5">
						<table class="table">
							<tr>
								<th>Vehicle VIN:</th>
								<td><c:out value="${serviceCheck.vehicle.vin}"/></td>
							</tr>
							<tr>
								<th>Status:</th>
								<td><c:out value="${serviceCheck.status}"/></td>
							</tr>
							<tr>
								<th>Date:</th>
								<td><c:out value="${serviceCheck.serviceCheckDate}"/></td>
							</tr>
							<tr>
								<th>Service employee:</th>
								<td><c:out value="${serviceCheck.serviceEmployee}"/></td>
							</tr>
							<tr>
								<th>Report:</th>
								<td><c:out value="${serviceCheck.report}"/></td>
							</tr>
						</table>
						<form method="post" action="${pageContext.request.contextPath}/servicecheck/delete/${serviceCheck.id}"
									onsubmit="return confirm('Do you want to delete this service check?');">
							<a href="${pageContext.request.contextPath}/servicecheck/list" class="btn btn-default">Back</a>
							<button type="submit" class="btn btn-danger">Delete</button>
						</form>
					</div>
				</div>
    </jsp:attribute>
</my:layout>