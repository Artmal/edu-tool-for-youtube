<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${request.contextPath}"/>

<html>
<head>
    <meta charset="utf-8"/>

    <title>List of Playlist</title>

    <link href="${contextPath}/resources/css/stylesForPages/videoPage/videoPage.css" rel="stylesheet" type="text/css">

    <script src="http://js.nicedit.com/nicEdit-latest.js" type="text/javascript"></script>
    <script type="text/javascript">bkLib.onDomLoaded(nicEditors.allTextAreas);</script>

</head>
<body>
    <div id = "pageWrapper">
        <%@ include file="/resources/header.jsp" %>

        <div id="understandingLevelSection">
            <div class="sectionHeader">
                <p class = "ppsd">Understanding level</p>
            </div>

            <form:form action="/video/changeUnderstandingLevel?video_id=${video_id}">
                    <span class="starRating">

                        <input id="rating5" type="radio" name="level_of_understanding" value="5"
                               <c:if test = "${level_of_understanding == 5}">checked="checked"</c:if>
                        />
                        <label for="rating5">1</label>

                        <input id="rating4" type="radio" name="level_of_understanding" value="4"
                               <c:if test = "${level_of_understanding == 4}">checked="checked"</c:if>/>
                        <label for="rating4">2</label>

                        <input id="rating3" type="radio" name="level_of_understanding" value="3"
                               <c:if test = "${level_of_understanding == 3}">checked="checked"</c:if>/>
                        <label for="rating3">3</label>

                        <input id="rating2" type="radio" name="level_of_understanding" value="2"
                               <c:if test = "${level_of_understanding == 2}">checked="checked"</c:if> />
                        <label for="rating2">4</label>

                        <input id="rating1" type="radio" name="level_of_understanding" value="1"
                             <c:if test = "${level_of_understanding == 1}">checked="checked"</c:if>/>
                        <label for="rating1">5</label>
                    </span>
                <button type="submit">Submit</button>
            </form:form>
            <br>
        </div>

        <br>

        <div id = "wrapperForVideoAndNoteForm">
            <div class = "sectionHeader">
                <p class = "ppsd">Video and Note Section</p>
            </div>

            <div id="videoSection" style="padding-left: 5px">
                <iframe style = "border: 1px solid black" width="750" height="350" src="https://www.youtube.com/embed/${videoCode}" frameborder="0" allowfullscreen></iframe>
                <br>
                <button type="button" href="${contextPath}/setVideoAsCompleted?id=${video_id}">Completed</button>
            </div>

            <div id="addNoteSection">
                    <form:form action="${contextPath}/addNote?video_id=${video_id}" method="post">
                        <textarea name="note"></textarea>
                        <button type="submit">Submit Note</button>
                    </form:form>
            </div>
        </div>

        <br>

        <div id = "notesSection">
            <div class = "sectionHeader">
                <p class = "ppsd">Notes</p>
            </div>
            <c:if test="${not empty video_notes}">
                <c:forEach items="${video_notes}" var="video">
                    <p1>${video.date} (<a href="${contextPath}/deleteNote?note_id=${video.id}&video_id=${video_id}">Delete</a>)</p1>
                    <div class = "noteDiv">
                            ${video.note}
                    </div>
                    <br>
                </c:forEach>
            </c:if>
        </div>
    </div>
</body>
</html>