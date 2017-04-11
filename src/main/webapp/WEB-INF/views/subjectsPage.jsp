<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Subjects Page</title>

    <link href="${contextPath}/resources/css/welcomePage.css" rel="stylesheet" type="text/css">
</head>
<body>
    <%@include file="../../resources/header.jsp" %>

    <c:if test="${not empty listOfSubjects}">
        <table>
            <tr>
                <td>ID</td>
                <td>Title</td>
            </tr>
            <c:forEach items="${listOfSubjects}" var="element">
                <c:set var="count" value="${count + 1}" scope="page"/>
                <tr>
                    <td>${count}</td>
                    <td><a href="/playlist?id=${element.id}">${element.title}</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>