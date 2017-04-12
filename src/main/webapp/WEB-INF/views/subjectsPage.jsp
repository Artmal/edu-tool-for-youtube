<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Subjects Page</title>

    <link href="${contextPath}/resources/css/stylesForPages/subjectPage.css" rel="stylesheet" type="text/css">
</head>
<body>
    <ul>
        <li><a href="/profile">My Profile</a></li>
        <li><a class="active"  href="/list-of-subjects">My Subjects</a></li>
        <li><a href="/list-of-playlists">My Playlists</a></li>
    </ul>

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
                    <td><a href="/subject?subject_id=${element.id}">${element.title}</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>