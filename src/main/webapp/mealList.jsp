<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
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
            <td>Time</td>
            <td>Meal type</td>
            <td>Calories</td>
        </tr>

        <c:forEach items="${meals}" var="meal">
            <tr class="
                <c:if test="${meal.exceed}">red</c:if>
                <c:if test="${!meal.exceed}">green</c:if>"
            >
                <td>${fn:replace(meal.getDateTime(), 'T', ' ')}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
