<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2022-11-30
  Time: 오후 3:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<p class="subtitle"> 판매 등록 </p>
<form class="ui form" method="post" action="${pageContext.request.contextPath}/sell/register" enctype="multipart/form-data" style="width: 50%;">
    <input type="file" name="sellImage" multiple="multiple">
    <div class="field">
        <input type="text" id="title" name="title" placeholder="글 제목">
    </div>
    <div class="field">
        <input type="text" id="name" name="name" placeholder="제품 이름">
    </div>
    <div class="field">
        <input type="text" id="productCode" name="productCode" placeholder="제품 코드">
    </div>
    <div class="field">
        <select class="ui dropdown" name="category">
            <option value="">카테고리</option>
            <option value="notebook">노트북</option>
            <option value="monitor">모니터</option>
            <option value="etc">주변기기</option>
        </select>
    </div>
    <div class="field">
        <input type="text" id="price" name="price" placeholder="제품 가격">
    </div>
    <div class="field">
        <input type="text" id="content" name="content" placeholder="판매글 내용">
    </div>
    <input type="file" name="sellImage" multiple="multiple">
    <button class="ui button" type="submit"><i class="save icon"></i></button>
</form>
</body>
</html>
