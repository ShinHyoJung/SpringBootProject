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
<form class="ui form" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/manage/product/detail/update" style="width: 50%;">
    <div class="field">
        <label>제품 이미지</label>
        <input type="file" id="thumbnailImage" name="productImage" multiple="multiple" value="${product.thumbnailImageName}" style="width: 30%;" onchange="readURL(this, this.id)">
        <br>
        <input type="hidden" id="productID" name="productID" value="${getDetailResponse.product.productID}">
        <img src="${pageContext.request.contextPath}/static/images/cut/${thumbnailImage.storedName}" id="thumbnailImagePreview" style="width: 200px; height: 200px;">
        <input type="hidden" name="thumbnailImage" value="${thumbnailImage.storedName}">
    </div>
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
<script>
    function readURL(input, imgType) {
        if (input.files && input.files[0]) {
            let reader = new FileReader();
            reader.onload = function(e) {
                if(imgType == 'thumbnailImage') {
                    document.getElementById('thumbnailImagePreview').src = e.target.result;
                } else if(imgType == 'detailImage') {
                    document.getElementById('detailImagePreview').src = e.target.result;
                }
            };
            reader.readAsDataURL(input.files[0]);
        } else {
            document.getElementById('preview').src = "";
        }
    }
</script>
</body>
</html>
