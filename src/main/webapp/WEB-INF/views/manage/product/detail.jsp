<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2023-01-12
  Time: 오후 5:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<body>
<p class="subtitle">제품 상세</p>
<form class="ui form" method="post" action="${pageContext.request.contextPath}/product/detail/update" style="width: 50%;">
    <input type="hidden" id="productID" name="productID" value="${getDetailResponse.product.productID}">
    <div class="field">
        <label>제품 이름</label>
        <input type="text" id="name" name="name" value="${getDetailResponse.product.name}">
    </div>
    <div class="field">
        <label>제품 코드</label>
        ${getDetailResponse.product.code}
    </div>
    <div class="field">
        <label>총 수량</label>
        <input type="text" id="fullQuantity" name="fullQuantity" value="${getDetailResponse.product.fullQuantity}">
    </div>
    <div class="field">
        <label>제품 정보</label>
        <input type="text" id="info" name="info" value="${getDetailResponse.product.info}">
    </div>
    <div class="field">
        <label>생성 일시</label>
        <fmt:formatDate pattern="yyyy-MM-dd hh:MM" value="${getDetailResponse.product.registerDate}"/>
    </div>
    <div class="field">
        <label>수정 일시</label>
        <fmt:formatDate pattern="yyyy-MM-dd hh:MM" value="${getDetailResponse.product.updateDate}"/>
    </div>
    <button type="submit" class="ui button"><i class="save icon"></i></button>
</form>
<button class="ui button" onclick="location.href='${pageContext.request.contextPath}/manage/product/'"><i class="list icon"></i></button>
</body>
</html>
