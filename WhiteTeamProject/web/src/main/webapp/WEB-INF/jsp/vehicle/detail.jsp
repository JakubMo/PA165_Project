<%-- 
    Document   : detail
    Created on : Dec 15, 2015, 9:19:20 PM
    Author     : Marek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="my"%>

<my:layout title="Vehicle #${vehicle.id}">
    <jsp:attribute name="body">
        <div class="row">
            <div style="float: right" class="col-md-3">
                <my:imagelink src="http://www.crossfitpulse.com/wp-content/uploads/2012/12/question-mark.jpg" />
            </div>
            
            <div class="col-md-5"
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
                </table>
            </div>
        </div>
    </jsp:attribute>
</my:layout>
