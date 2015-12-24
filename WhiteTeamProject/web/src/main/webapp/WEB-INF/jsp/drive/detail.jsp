<%--
  User: Mário Kudoláni
  Date: 22.12.2015
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="my"%>

<my:layout title="Drives">
    <jsp:attribute name="body">
        <a href="${pageContext.request.contextPath}/drive/list" role="button" class="btn btn-default">Back</a>

        <div class="row" style="margin-top: 25px">
            <div style="float: right" class="col-md-3">
                <my:imagelink src="http://cdn.flaticon.com/png/256/2087.png" />
            </div>

            <div class="col-md-5">
                <table class="table">
                    <tr>
                        <th>Vehicle VIN</th>
                        <td><c:out value="${drive.vehicle.vin}"/></td>
                    </tr>
                    <tr>
                        <th>Start Date</th>
                        <td><fmt:formatDate value="${drive.startDate}" pattern="yyyy-MM-dd"/></td>
                    </tr>
                    <tr>
                        <th>End Date</th>
                        <td><fmt:formatDate value="${drive.endDate}" pattern="yyyy-MM-dd"/></td>
                    </tr>
                    <tr>
                        <th>Km</th>
                        <td>
                            <c:if test="${drive.driveStatus.getValue() == 2}">
                                <c:out value="${drive.km}"/> km
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <th>Status</th>
                        <td><c:out value="${drive.driveStatus}"/></td>
                    </tr>
                </table>
            </div>

        </div>
    </jsp:attribute>
</my:layout>