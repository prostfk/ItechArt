<%--
  Created by IntelliJ IDEA.
  User: prostrmk
  Date: 28/07/18
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete</title>
</head>
<body>

<div class="card col" style="width: 18rem;">
    <img class="card-img-top" src="${post.pathToPhoto}" alt="Card image cap">
    <div class="card-body">
        <h5 class="card-title">${user.username}</h5>
        <p class="card-text">${post.description}</p>
        <form action="/delete/${post.id}" method="post">
            <input class="btn btn-danger" type="submit">
        </form>
        <a href="/" class="btn btn-primary">Go home</a>
    </div>
</div>

</body>
</html>
