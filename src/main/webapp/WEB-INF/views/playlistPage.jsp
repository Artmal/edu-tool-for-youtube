<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${request.contextPath}"/>

<html>
<head>
    <meta charset="utf-8"/>

    <title>Playlist page</title>

    <link href="${contextPath}/resources/css/stylesForPages/playlistPage.css" rel="stylesheet" type="text/css">
</head>
<body>
    <ul>
        <li><a href="${contextPath}/profile">My Profile</a></li>
        <li><a href="${contextPath}/list-of-subjects">My Subjects</a></li>
        <li><a href="${contextPath}/list-of-playlists">My Playlists</a></li>
    </ul>

    <div>
        <c:if test="${not empty listOfVideos}">
            <table>
                <tr>
                    <td>ID</td>
                    <td>Video Title</td>
                    <td>Video Duration</td>
                    <td>Understanding level</td>
                    <td>Completed?</td>
                </tr>
                <c:forEach items="${listOfVideos}" var="video">
                    <c:set var="count" value="${count + 1}" scope="page"/>
                    <tr>
                        <td>${count}</td>
                        <td><a href="${contextPath}/video?id=${video.id}">${video.title}</a></td>
                        <td>${video.duration}</td>
                        <td>
                            <c:forEach var="i" begin="1" end="${video.levelOfUnderstanding}">
                                &#10037;
                            </c:forEach>
                        </td>
                        <td><a href="${contextPath}/playlist/changeVideoCompleteness?video_id=${video.id}">${video.completed}</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <a class="deleteButton" href="${contextPath}/playlist/deletePlaylist?playlist_id=${playlistId}" onclick="return confirm('Are you sure?');">Delete Playlist</a>
    </div>
</body>
</html>