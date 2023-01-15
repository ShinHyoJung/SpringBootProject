<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2023-01-16
  Time: 오전 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<body>
<p class="subtitle">주문 내역</p>
<div class="ui relaxed divided list">
  <c:forEach items="${purchaseList}" var="purchaseList">
    <div class="item">
      <i class="large github middle aligned icon"></i>
      <div class="content">
        <a class="header">${purchaseList.name}</a>
        <div class="description">${purchaseList.price}</div>
        <div class="description">${purchaseList.deliveryStatus}</div>
        <div class="description"> <fmt:formatDate pattern="yyyy-MM-dd hh:MM" value="${purchaseList.purchaseDate}"/></div>
      </div>
    </div>
  </c:forEach>
</div>
</body>
</html>
