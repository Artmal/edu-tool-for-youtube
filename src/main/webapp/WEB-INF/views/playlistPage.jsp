<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Playlist page</title>

    <link href="${contextPath}/resources/css/welcomePage.css" rel="stylesheet" type="text/css">
</head>
<body>
<div>
    <c:if test="${not empty listOfVideos}">
        <table>
            <tr>
                <td>ID</td>
                <td>Video Title</td>
                <td>Video Duration</td>
                <td>Completed?</td>
            </tr>
            <c:forEach items="${listOfVideos}" var="element">
                <c:set var="count" value="${count + 1}" scope="page"/>
                <tr>
                    <td>${count}</td>
                    <td>${element.title}</td>
                    <td>${element.duration}</td>
                    <td>${element.completed}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
</body>
</html>