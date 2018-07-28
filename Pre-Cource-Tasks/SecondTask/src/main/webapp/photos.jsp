<%@ page import="by.prostrmk.model.entity.Post" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: prostrmk
  Date: 26/07/18
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Photo</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">
    <style>
        .row{
            display: flex;
            width: 80%;
            margin: 0;
        }
        .col{
            flex: auto;
        }
    </style>
</head>
<body>
<div class="row">
    <c:forEach items="${posts}" var="post">

        <div class="card col" style="width: 18rem;">
            <img class="card-img-top" src="${post.pathToPhoto}" alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title">${user.username}</h5>
                <p class="card-text">${post.description}</p>
                <a href="/delete/${post.id}" class="btn btn-danger">Delete</a>
                <a href="/" class="btn btn-primary">Go home</a>
            </div>
        </div>
    </c:forEach>

</div>




</body>
</html>
