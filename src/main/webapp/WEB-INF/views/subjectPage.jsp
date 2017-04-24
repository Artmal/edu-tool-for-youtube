<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${request.contextPath}"/>

<html>
<head>
    <title>Subjects Page</title>

    <link href="${contextPath}/resources/css/stylesForPages/subjectPage.css" rel="stylesheet" type="text/css">
</head>
<body>
    <ul>
        <li><a href="${contextPath}/profile">My Profile</a></li>
        <li><a href="${contextPath}/list-of-subjects">My Subjects</a></li>
        <li><a href="${contextPath}/list-of-playlists">My Playlists</a></li>
    </ul>

        <br>
        <p1>${subject_title}</p1>
    
        <c:if test="${not empty listOfPlaylists}">
        <table>
            <tr>
                <td>ID</td>
                <td>Playlist Name</td>
                <td>Channel</td>
                <td>Link</td>
                <td>Progress</td>
            </tr>
            <c:forEach items="${listOfPlaylists}" var="video">
                <c:set var="count" value="${count + 1}" scope="page"/>
                <tr>
                    <td>${count}</td>
                    <td><a href="${contextPath}/playlist?id=${video.id}">${video.name}</a></td>
                    <td>${video.channel}</td>
                    <td><a href="${video.link}">${video.link}</a></td>
                    <td>${video.amountOfCompletedVideos}/${video.amountOfVideos}</td>
                </tr>
            </c:forEach>
        </table>
        </c:if>
</body>
</html>