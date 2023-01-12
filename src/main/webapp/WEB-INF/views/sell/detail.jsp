<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2022-12-01
  Time: 오후 2:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<body>
${getDetailResponse.sell.title} <br>
<img src="${pageContext.request.contextPath}/static/images/thumbnail/${getDetailResponse.sell.thumbnailImageName}"/><br>
가격 : ${getDetailResponse.sell.price} 원<br>
<button class="ui button">구매</button> <button class="ui button">장바구니</button>
<p class="subtitle"> 상세 정보 </p>
<img src="${pageContext.request.contextPath}/static/images/detail/${getDetailResponse.sell.detailImageName}"/> <br>
${getDetailResponse.sell.content} <br>
작성날짜: <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${getDetailResponse.sell.createDate}"/> <br>
수정날짜: <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${getDetailResponse.sell.updateDate}"/> <br>
<a href="${pageContext.request.contextPath}/sell/update/${getDetailResponse.sell.sellID}">수정</a>
<a href="${pageContext.request.contextPath}/sell/delete/${getDetailResponse.sell.sellID}">삭제</a> <br>
<a href="${pageContext.request.contextPath}/sell/">뒤로가기</a>
</body>
</html>
