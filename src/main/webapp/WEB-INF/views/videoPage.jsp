<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>List of Playlist</title>

    <link href="${contextPath}/resources/css/videoPage.css" rel="stylesheet" type="text/css">

    <script src="http://js.nicedit.com/nicEdit-latest.js" type="text/javascript"></script>
    <script type="text/javascript">bkLib.onDomLoaded(nicEditors.allTextAreas);</script>

</head>
<body>
    <%@include file="../../resources/header.jsp" %>
    <br>

    <div class="wrapper">
        <div class="left_block">
            <iframe style = "border: 1px solid black" width="560" height="315" src="https://www.youtube.com/embed/${videoCode}" frameborder="0" allowfullscreen></iframe>
            <br><br>
            <a href="/setVideoAsCompleted?id=${video_id}" class="completeButton">Completed</a>
        </div>

        <div class="right_block">
            <form:form action="/addNote?video_id=${video_id}" method="post">
                <textarea name="note"></textarea>
                <button type="submit">Submit Notes</button>
            </form:form>
        </div>
    </div>

    <br>

    <div class="wrapper">
    <c:if test="${not empty video_notes}">
        <c:forEach items="${video_notes}" var="element">
            <p1>${element.date}</p1>
            <div style="border: 1px solid black; padding: 2px">
                ${element.note}
            </div>
            <br>
        </c:forEach>
    </c:if>
    </div>
</body>
</html>