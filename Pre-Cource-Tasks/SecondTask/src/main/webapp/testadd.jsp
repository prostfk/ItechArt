<%--
  Created by IntelliJ IDEA.
  User: prostrmk
  Date: 18.8.18
  Time: 14.14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Testadd</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">

</head>
<body>

<form action="/testAdd" method="post" enctype="multipart/form-data" style="margin: 10%;">
    <div class="form-group">
        <label for="username">Username</label>
        <input id="username" class="form-control" type="text" name="username" placeholder="username" formenctype="multipart/form-data">
    </div>
    <div class="form-group">
        <label for="file-input"></label>
        <input id="file-input" class="form-control-file" type="file" name="file">
    </div>
    <input type="submit" class="btn btn-primary">
</form>

</body>
</html>
