<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2022-12-01
  Time: 오후 2:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
${getDetailResponse.title} <br>
<img src="${pageContext.request.contextPath}/static/images/detail/${getDetailResponse.detailImage}"/><br>
${getDetailResponse.price} <br>
${getDetailResponse.content} <br>
작성날짜: <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${getDetailResponse.createDate}"/> <br>
수정날짜: <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${getDetailResponse.updateDate}"/> <br>
<a href="${pageContext.request.contextPath}/sell/delete/${getDetailResponse.sellID}">삭제</a> <br>
<a href="${pageContext.request.contextPath}/sell/">뒤로가기</a>
</body>
</html>
