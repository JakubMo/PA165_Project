<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="my"%>

<my:layout title="Home">
    <jsp:attribute name="body">
        <h1>Welcome ${principal.firstname} ${principal.lastname}!</h1>
            <h4>You are logged with ${principal.role} rights.</h4>
    </jsp:attribute>
</my:layout>
