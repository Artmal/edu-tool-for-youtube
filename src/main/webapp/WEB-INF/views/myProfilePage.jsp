<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>My Profile</title>

    <link href="${contextPath}/resources/css/stylesForPages/myProfilePage.css.css" rel="stylesheet" type="text/css">
</head>
<body>
    <%@include file="../../resources/header.jsp" %>

    <div>
        Username: ${username};
        Amount Of Playlists: ${amountOfPlaylists};
    </div>

    <script>
        document.getElementById("myProfileButton").classList.add("active");
    </script>
</body>
</html>