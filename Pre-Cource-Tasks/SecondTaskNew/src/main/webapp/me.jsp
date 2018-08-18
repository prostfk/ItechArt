<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: prostrmk
  Date: 18.8.18
  Time: 16.00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Me</title>
</head>
<body>

<c:forEach items="${posts}" var="post">
    <div class="card" style="width: 18rem;">
        <img class="card-img-top" src="${post.path}" alt="Card image cap">
        <div class="card-body">
            <h5 class="card-title">${post.title}</h5>
            <a href="#" class="btn btn-primary">Go somewhere</a>
        </div>
    </div>
</c:forEach>

</body>
</html>
