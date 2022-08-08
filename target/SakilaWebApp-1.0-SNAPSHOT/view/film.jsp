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
    <h2>${ film.id > 0 ? "Edit film" : "New film" }</h2>
    <c:if test="${ film.id > 0 }">
        <span>Last update: ${film.lastUpdate}</span>
    </c:if>
    <hr>
    <form action="FilmController?action=${film.id > 0 ? "update" : "save"}&id=${film.id}" method="post">
        <table>
            <tr>
                <td>Title</td>
                <td><input type="text" name="txtTitle" value="${film.title}"></td>
            </tr>
            <tr>
                <td>Description</td>
                <td><textarea name="txtDescription" cols="50" rows="5">${film.description}</textarea></td>
            </tr>
            <tr>
                <td>Release year</td>
                <td><input type="number" min="2000" max="2022" name="txtReleaseYear" value="${film.releaseYear}"></td>
            </tr>
            <tr>
                <td>Language</td>
                <td>
                    <select name="selLanguage">
                    <c:forEach var="language" items="${ languages }">
                        <option value="${ language.id }" ${ film.languageId == language.id ? "selected" : "" }>
                                ${ language.name }
                        </option>
                    </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Original language</td>
                <td>
                    <select name="selOriginalLanguage">
                        <option value="0">(no selected)</option>
                        <c:forEach var="language" items="${ languages }">
                            <option value="${ language.id }" ${ film.originalLanguageId == language.id ? "selected" : "" }>
                                    ${ language.name }
                            </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Rental duration</td>
                <td><input type="number" min="1" max="10" name="txtRentalDuration" value="${film.rentalDuration}"></td>
            </tr>
            <tr>
                <td>Rental rate</td>
                <td>
                    <input type="number" min="0" max="10" step="0.01" name="txtRentalRate" value="${film.rentalRate}">
                </td>
            </tr>
            <tr>
                <td>Length (minutes)</td>
                <td><input type="number" min="1" max="480" name="txtLength" value="${film.length}"></td>
            </tr>
            <tr>
                <td>Replacement cost</td>
                <td>
                    <input type="number" min="0" max="100" step="0.01"
                           name="txtReplacementCost" value="${film.replacementCost}">
                </td>
            </tr>
            <tr>
                <td>Rating</td>
                <td>
                    <select name="selRating">
                    <c:forEach var="rating" items="${ FilmRating().getRatings() }">
                        <option value="${ rating.key }" ${ film.rating == rating.key ? "selected" : "" }>
                                ${ rating.value } (${ rating.key })
                        </option>
                    </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Special features</td>
                <td>
                    <select name="selFeatures" multiple>
                        <c:forEach var="feature" items="${ FilmSpecialFeature().getSpecialFeatures() }">
                            <option ${ film.specialFeatures.contains(feature) ? "selected" : "" }>${ feature }</option>
                        </c:forEach>:
                    </select>
                </td>
            </tr>
            <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
            <tr>
                <td>
                </td>
                <td><input type="submit" value="${ film.id > 0 ? "Update" : "Save" }"></td>
            </tr>
        </table>
    </form>
</body>
</html>
