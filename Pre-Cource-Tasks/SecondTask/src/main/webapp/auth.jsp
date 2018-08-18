<%--
  Created by IntelliJ IDEA.
  User: prostrmk
  Date: 26/07/18
  Time: 23:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Auth</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">
</head>
<body>

<form method="post" action="/auth" style="margin: 10%;">
    <div class="form-group">
        <label for="username">Username</label>
        <input id="username" class="form-control" placeholder="username" type="text" name="username">
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input id="password" class="form-control" type="password" placeholder="password" name="password">
    </div>
    <input type="submit" class="btn btn-primary">
    <a href="/registration" class="btn btn-success">Registration</a>
</form>

</body>
</html>
