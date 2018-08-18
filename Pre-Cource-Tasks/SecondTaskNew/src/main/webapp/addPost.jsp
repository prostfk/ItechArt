<%--
  Created by IntelliJ IDEA.
  User: prostrmk
  Date: 18.8.18
  Time: 16.18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add post</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">

</head>
<body>

<form action="/addPost" method="post" enctype="multipart/form-data" style="margin: 10%;">
    <div class="form-group">
        <label for="title">Title</label>
        <input id="title" class="form-control" type="text" name="title" placeholder="title" formenctype="multipart/form-data">
    </div>
    <div class="form-group">
        <label for="file-input"></label>
        <input id="file-input" class="form-control-file" type="file" name="file">
    </div>
    <input type="submit" class="btn btn-primary">
</form>
<div style="margin: 1% 10% 10% 10%">
    <a href="/me">
        <button class="btn btn-info">Go to photos</button>
    </a>
</div>


</body>
</html>
