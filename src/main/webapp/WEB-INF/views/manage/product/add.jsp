<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2023-01-04
  Time: 오후 6:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<p class="subtitle">제품 등록</p>
<form class="ui form" method="post" action="${pageContext.request.contextPath}/manage/product/add" enctype="multipart/form-data" style="width: 50%;">
  <div class="field">
    <input type="text" id="name" name="name" placeholder="제품 이름">
  </div>
  <div class="field">
    <input type="text" id="fullQuantity" name="fullQuantity" placeholder="총 개수">
  </div>
  <div class="field">
    <select class="ui dropdown" name="category">
      <option value="">카테고리</option>
      <c:forEach items="${categoryList}" var="categoryList">
        <option value="${categoryList.code}">${categoryList.name}</option>
      </c:forEach>
    </select>
  </div>
  <div class="field">
    <textarea id="info" name="info" placeholder="상품 정보"></textarea>
  </div>
    <input type="file" id="productImage" name="productImage" multiple="multiple">
  <button class="ui button" type="submit"><i class="save icon"></i></button>
</form>

<button class="ui button" onclick="location.href='${pageContext.request.contextPath}/manage/product/'">뒤로가기</button>
</body>
</html>
