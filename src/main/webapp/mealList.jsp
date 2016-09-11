<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Meal list</title>
</head>
<body>
    Whatever<br>
    Some JSP magic lol<br>
    <br><br>

<div>
    <c:forEach items="${meals}" var="meal">
        ${meal}
        <br>
    </c:forEach>
</div>

</body>
</html>
