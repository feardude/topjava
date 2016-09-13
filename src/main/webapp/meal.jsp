<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal</title>
</head>
<body>
<a href="meals?action=listMeal">Meal list</a>
<br><br>

<form method="POST" action="meals" name="addOrUpdateMeal">
    Meal ID: <input type="text" readonly="readonly" name="id"
                    value="<c:out value="${meal.id}" />" />
    <br><br>
    Date: <input type="datetime-local" name="dateTime"
                 value="<c:out value="${meal.dateTime}" />" />
    <br><br>
    Description: <input type="text" name="description"
                        value="<c:out value="${meal.description}" />" />
    <br><br>
    Calories: <input type="number" name="calories"
                     value="<c:out value="${meal.calories}" />" />
    <br><br>
    <input type="submit" value="Submit" />
</form>

</body>
</html>
