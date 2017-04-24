<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8"/>

    <title>My Profile</title>

    <link href="${contextPath}/resources/css/stylesForPages/myProfilePage.css" rel="stylesheet" type="text/css">
</head>
<body>
    <%@include file="../../resources/header.jsp" %>

    <div id = "wrapper">
        <div id="profileBlock">
            Username: ${username}
            <br>
            Amount Of Playlists: ${amountOfPlaylists}
        </div>
    </div>

    <!-- Make appropriate menu item on header active -->
    <script type="text/javascript">
        document.getElementById("myProfileButton").classList.add("active");
    </script>
</body>
</html>