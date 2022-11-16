<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2022-07-08
  Time: 오전 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>쇼핑몰</title>
    <meta charset="UTF-8">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css">
</head>
<body>
    <div class="header">
        <c:import url="include/header.jsp"/>
    </div>
    <div class="container">
        <c:import url="${main}.jsp"/>
    </div>
    <div class="footer">
        <c:import url="include/footer.jsp"/>
    </div>
</body>
</html>
