<%@ page import="edu.utp.dwi.sakilawebapp.model.FilmRating" %>
<%@ page import="java.util.Map" %>
<%@ page import="edu.utp.dwi.sakilawebapp.model.FilmSpecialFeature" %><%--
  Created by IntelliJ IDEA.
  User: Jorge Rodriguez
  Date: 18/05/2022
  Time: 8:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Film</title>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <h2>Film's information</h2>
    <span>Last update: ${film.lastUpdate}</span>
    <hr>

        <table>
            <tr>
                <td>Title</td>
                <td>${film.title}</td>
            </tr>
            <tr>
                <td>Description</td>
                <td>${film.description}</td>
            </tr>
            <tr>
                <td>Release year</td>
                <td>${film.releaseYear}</td>
            </tr>
            <tr>
                <td>Language</td>
                <td>
                    <c:forEach var="language" items="${ languages }">
                        <c:if test="${ film.languageId == language.id }">
                            <span>${ language.name }</span><br>
                        </c:if>
                    </c:forEach>
                </td>
            </tr>

            <tr>
                <td>Original language</td>
                <td>
                    <c:choose>
                        <c:when test="${ film.originalLanguageId > 0 }">
                            <c:forEach var="language" items="${ languages }">
                                <c:if test="${ film.originalLanguageId == language.id }">
                                    <span>${ language.name }</span><br>
                                </c:if>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>(unset)</c:otherwise>
                    </c:choose>
                </td>
            </tr>

            <tr>
                <td>Rental duration</td>
                <td>${film.rentalDuration}</td>
            </tr>
            <tr>
                <td>Rental rate</td>
                <td>${film.rentalRate}</td>
            </tr>
            <tr>
                <td>Length (minutes)</td>
                <td>${film.length}</td>
            </tr>
            <tr>
                <td>Replacement cost</td>
                <td>${film.replacementCost}</td>
            </tr>
            <tr>
                <td>Rating</td>
                <td>
                    <c:forEach var="rating" items="${ FilmRating().getRatings() }">
                        <c:if test="${ film.rating == rating.key }">
                            <span>${ rating.value } (${ rating.key })</span>
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td>Special features</td>
                <td>
                    <c:forEach var="feature" items="${ FilmSpecialFeature().getSpecialFeatures() }">
                        <c:if test="${ film.specialFeatures.contains(feature) }">
                            <span>${ feature }</span><br>
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
        </table>
    <div>
        <br>
        <a href="FormatController?id=${param.id}&format=txt">TXT Export</a>
        &nbsp;|&nbsp;
        <a href="FormatController?id=${param.id}&format=xml">XML Export</a>
    </div>

</body>
</html>
