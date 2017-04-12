<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Playlist page</title>

    <link href="${contextPath}/resources/css/stylesForPages/playlistPage.css" rel="stylesheet" type="text/css">
</head>
<body>
    <ul>
        <li><a href="/profile">My Profile</a></li>
        <li><a href="/list-of-subjects">My Subjects</a></li>
        <li><a href="/list-of-playlists">My Playlists</a></li>
    </ul>

    <br>

    <a class="deleteButton" href="/playlist/delete?id=${playlistId}" onclick="return confirm('Are you sure?');">Delete Playlist</a>

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
                        <td><a href="/video?id=${element.id}">${element.title}</a></td>
                        <td>${element.duration}</td>
                        <td><a href="/playlist/change?id=${element.id}">${element.completed}</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</body>
</html>