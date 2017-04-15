<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>List of Playlist</title>

    <link href="${contextPath}/resources/css/stylesForPages/videoPage.css" rel="stylesheet" type="text/css">

    <script src="http://js.nicedit.com/nicEdit-latest.js" type="text/javascript"></script>
    <script type="text/javascript">bkLib.onDomLoaded(nicEditors.allTextAreas);</script>
    <script>
        "use strict";
        function r(f){/in/.test(document.readyState)?setTimeout('r('+f+')',9):f()}
        r(function(){
            if(!document.getElementsByClassName) {
                // IE8 support
                var getElementsByClassName = function(node, classname) {
                    var a = [];
                    var re = new RegExp('(^| )'+classname+'( |$)');
                    var els = node.getElementsByTagName("*");
                    for(var i=0,j=els.length; i<j; i++)
                        if(re.test(els[i].className))a.push(els[i]);
                    return a;
                }
                var videos = getElementsByClassName(document.body,"youtube");
            }
            else {
                var videos = document.getElementsByClassName("youtube");
            }

            var nb_videos = videos.length;
            for (var i=0; i<nb_videos; i++) {
                // Based on the YouTube ID, we can easily find the thumbnail image
                videos[i].style.backgroundImage = 'url(http://i.ytimg.com/vi/' + videos[i].id + '/sddefault.jpg)';

                // Overlay the Play icon to make it look like a video player
                var play = document.createElement("div");
                play.setAttribute("class","play");
                videos[i].appendChild(play);

                videos[i].onclick = function() {
                    // Create an iFrame with autoplay set to true
                    var iframe = document.createElement("iframe");
                    var iframe_url = "https://www.youtube.com/embed/" + this.id + "?autoplay=1&autohide=1";
                    if (this.getAttribute("data-params")) iframe_url+='&'+this.getAttribute("data-params");
                    iframe.setAttribute("src",iframe_url);
                    iframe.setAttribute("frameborder",'0');

                    // The height and width of the iFrame should be the same as parent
                    iframe.style.width  = this.style.width;
                    iframe.style.height = this.style.height;

                    // Replace the YouTube thumbnail with YouTube Player
                    this.parentNode.replaceChild(iframe, this);
                }
            }
        });
    </script>

</head>
<body>
    <div id = "pageWrapper">
        <ul>
            <li><a href="${contextPath}/profile">My Profile</a></li>
            <li><a href="${contextPath}/list-of-subjects">My Subjects</a></li>
            <li><a href="${contextPath}/list-of-playlists">My Playlists</a></li>
        </ul>
        <br>

        <div id="understandingLevelSection">
            <div class="sectionHeader">
                <p class = "ppsd">Understanding level</p>
            </div>

                <form:form action="/video/changeUnderstandingLevel?video_id=${video_id}">
                    <input type="radio" name="level_of_understanding" value="1"/>
                    <input type="radio" name="level_of_understanding" value="2"/>
                    <input type="radio" name="level_of_understanding" value="3"/>
                    <input type="radio" name="level_of_understanding" value="4"/>
                    <input type="radio" name="level_of_understanding" value="5"/>
                    <button type="submit">Submit</button>
                </form:form>
            <br>
        </div>

        <br>

        <div id = "wrapperForVideoAndNoteForm">
            <div class = "sectionHeader">
                <p class = "ppsd">Video and Note Section</p>
            </div>

            <div id="videoSection">
                <iframe style = "margin-left: 5px" style = "border: 1px solid black" width="750" height="350" src="https://www.youtube.com/embed/${videoCode}" frameborder="0" allowfullscreen></iframe>
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
                <c:forEach items="${video_notes}" var="element">
                    <p1>${element.date} (<a href="${contextPath}/deleteNote?note_id=${element.id}&video_id=${video_id}">Delete</a>)</p1>
                    <div class = "noteDiv">
                            ${element.note}
                    </div>
                    <br>
                </c:forEach>
            </c:if>
        </div>
    </div>
</body>
</html>