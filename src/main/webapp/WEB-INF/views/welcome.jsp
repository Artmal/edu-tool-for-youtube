<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Home Page</title>
</head>
<body>
    <c:if test="${not empty listOfPlaylists}">
        <c:forEach items="${listOfPlaylists}" var="element">
            <tr border="1">
                <td>${element.id}</td>
                <td>${element.name}</td>
                <td>${element.channel}</td>
                <td>${element.link}</td>
            </tr>
        </c:forEach>
    </c:if>



    <form action="${contextPath}/welcome" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <p>Add playlist</p>
        <input type="text" name="addPlaylist_link">
        <button type="submit">Add</button>
    </form>
</body>
</html>