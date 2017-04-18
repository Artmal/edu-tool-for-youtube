<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
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

    <script type="text/javascript">
        document.getElementById("myProfileButton").classList.add("active");
    </script>
</body>
</html>