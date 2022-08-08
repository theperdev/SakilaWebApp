<%--
  Created by IntelliJ IDEA.
  User: Jorge Rodriguez
  Date: 18/05/2022
  Time: 7:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <script src="js/film.js"></script>
    <title>Films Catalog</title>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <h2>Films catalog</h2>
    <% for (char initial: "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) { %>
        <a href="FilmController?action=list&initial=<%=initial%>"><%=initial%></a>
    <% } %>
    <hr>
    <table>
        <tr>
            <th>Film</th>
            <th colspan="3">Actions</th>
        </tr>
    <c:forEach var="film" items="${ films }">
        <tr>
            <td>${ film.title }</td>
            <td><a href="FilmController?action=view&id=${film.id}">View</a></td>
            <td><a href="FilmController?action=edit&id=${film.id}">Edit</a></td>
            <td><a href="#" onclick="confirmDelete('FilmController?action=delete&id=${film.id}&initial=${param.initial}')">Delete</a></td>
        </tr>
    </c:forEach>
    </table>
</body>
</html>
