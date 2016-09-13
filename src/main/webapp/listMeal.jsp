<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <link href="<c:url value="/css/main.css"/>" rel="stylesheet">
    <title>Meal list</title>
</head>
<body>
    <h1>Meal list</h1>
    <br>
<div>
    <table>
        <tr>
            <th>Time</th>
            <th>Description</th>
            <th>Calories</th>
            <th colspan="2"></th>
        </tr>

        <c:forEach items="${meals}" var="meal">
            <tr class="
                <c:if test="${meal.exceed}">red</c:if>
                <c:if test="${!meal.exceed}">green</c:if>"
            >
                <td>${fn:replace(meal.dateTime, 'T', ' ')}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=<c:out value="${meal.id}" />">Update</a></td>
                <td><a href="meals?action=delete&id=<c:out value="${meal.id}" />">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <p><a href="meals?action=add">Add meal</a></p>
</div>

</body>
</html>
