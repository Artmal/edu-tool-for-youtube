<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${request.contextPath}"/>
<c:set var="count" value="0" scope="page" />

<html>
<head>
    <title>List of Playlist</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="${contextPath}/resources/css/stylesForPages/pageWithListOfPlaylists.css" rel="stylesheet" type="text/css">
</head>
<body>
    <%@include file="../../resources/header.jsp" %>

    <div>
        <c:if test="${not empty listOfPlaylists}">
            <table>
                <tr>
                    <td>ID</td>
                    <td>Playlist Name</td>
                    <td>Subject</td>
                    <td>Channel</td>
                    <td>Link</td>
                    <td>Progress</td>
                </tr>
                <c:forEach items="${listOfPlaylists}" var="playlist" varStatus="status">
                    <c:set var="count" value="${count + 1}" scope="page"/>
                    <tr>
                        <td>${count}</td>
                        <td><a href="${contextPath}/playlist?id=${playlist.id}">${playlist.name}</a></td>
                        <td>${listOfSubjects[status.index]}</td>
                        <td>${playlist.channel}</td>
                        <td><a href="${playlist.link}">${playlist.link}</a></td>
                        <td>${playlist.amountOfCompletedVideos}/${playlist.amountOfVideos}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <br>

        <div id="addPlaylistSection">
            <div class = "sectionHeader">
                <p class = "ppsd">Add playlist</p>
            </div>

            <form:form action="${contextPath}/list-of-playlists" method="post">
                <input type="text" name="addPlaylist_subject" placeholder="Subject the playlist belongs to">
                <input type="text" name="addPlaylist_link" placeholder="Past playlist link here">
                <button type="submit">Add</button>
            </form:form>
        </div>

    </div>

    <!-- Make appropriate menu item on header active -->
    <script type="text/javascript">
        document.getElementById("myPlaylistsButton").classList.add("active");
    </script>
</body>
</html>