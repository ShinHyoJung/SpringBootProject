<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2023-01-12
  Time: 오후 5:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
제품 상세
<form class="ui form" method="post" action="${pageContext.request.contextPath}/product/update">
    <div class="field">
        <label>제품 이름</label>
        <input type="text" id="name" value="${getDetailResponse.product.name}">
    </div>
    <div class="field">
        <label>제품 코드</label>
        ${getDetailResponse.product.code}
    </div>
    <div class="field">
        <label>제품 정보</label>
        <input type="text" id="info" value="${getDetailResponse.product.info}">
    </div>
    <button type="submit" class="ui button"><i class="save icon"></i></button>
</form>
</body>
</html>
