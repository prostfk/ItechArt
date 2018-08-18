<%--
  Created by IntelliJ IDEA.
  User: prostrmk
  Date: 9.8.18
  Time: 19.25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">

</head>
<body>


<form method="post" action="/registration" style="margin: 10%;">
    <div class="form-group">
        <label for="username">Username</label>
        <input id="username" class="form-control" placeholder="username" type="text" name="username">
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input id="password" class="form-control" type="password" placeholder="password" name="password">
    </div>
    <input type="submit" class="btn btn-primary">
</form>

</body>
</html>
