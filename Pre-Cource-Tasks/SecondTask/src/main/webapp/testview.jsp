<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: prostrmk
  Date: 18.8.18
  Time: 14.40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TestView</title>
</head>
<body>

<c:forEach items="${posts}" var="post">

    <div class="card col" style="width: 18rem;">
        <img class="card-img-top" src="${post.pathToPhoto}" alt="Card image cap">
        <div class="card-body">
            <p class="card-text">${post.description}</p>
            <a href="/delete/${post.id}" class="btn btn-danger">Delete</a>
            <a href="/" class="btn btn-primary">Go home</a>
        </div>
    </div>
</c:forEach>

</body>
</html>
