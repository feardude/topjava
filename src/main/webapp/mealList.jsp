<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

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
                <c:if test="${meal.isExceed()==true}">red</c:if>
                <c:if test="${meal.isExceed()==false}">green</c:if>"
            >
                <td>${meal.dateTime.format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm"))}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
