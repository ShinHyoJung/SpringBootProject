<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2022-12-05
  Time: 오후 3:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<p class="subtitle"> 판매글 수정 </p>
<form class="ui form" action="${pageContext.request.contextPath}/sell/update" method="post" enctype="multipart/form-data" style="width: 50%;">
    <div class="field">
        <label>썸네일 이미지</label>
        <input type="file" id="thumbnailImage" name="sellImage" multiple="multiple" value="${sell.thumbnailImageName}" style="width: 30%;" onchange="readURL(this, this.id)">
        <img id="thumbnailImagePreview" style="width: 200px; height: 200px;">
    </div>
    <div class="field">
        <label>제목 이미지</label>
        <input type="file" id="titleImage" name="sellImage" multiple="multiple" value="${sell.titleImageName}" style="width: 30%;" onchange="readURL(this, this.id)">
        <img id="titleImagePreview" style="width: 300px; height: 300px;">
    </div>
    <input type="hidden" id="name" name="name" value="">
    <input type="hidden" id="sellID" name="sellID" value="${sell.sellID}">
    <div class="ui field">
        <label>제목</label>
        <input type="text" id="title" name="title" value="${sell.title}">
    </div>
    <div class="ui field">
        <select class="ui dropdown" id="category" name="category" onchange="selectCategory(this.value);">
            <option value="${sell.category}">카테고리 선택</option>
            <c:forEach items="${categoryList}" var="categoryList">
                <option id="${categoryList.code}" value="${categoryList.name}">${categoryList.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="field">
        <select class="ui dropdown" id="productList" name="productID">
            <option value="">재고 선택</option>
        </select>
    </div>
    <div class="ui field">
        <label>가격</label>
        <input type="text" id="price" name="price" value="${sell.price}">
    </div>
    <div class="ui field">
        <label>상세 내용</label>
        <textarea id="content" name="content">${sell.content}</textarea>
    </div>
    <div class="field">
        <label>상세 이미지</label>
        <input type="file" id="detailImage" name="sellImage" multiple="multiple" value="${sell.detailImageName}" style="width: 30%;" onchange="readURL(this, this.id)">
        <img id="detailImagePreview" style="width: 500px; height: 500px;">
    </div>
    <button class="ui button" type="submit" style="margin-top: 10px;"><i class="save icon"></i></button>
</form>
<script>
    $(document).ready(function() {
        document.getElementById('category').options[0].selected = true;
        printProductList($('#category option:eq(0)').attr('value'));
        document.getElementById('productList').options[0].selected = true;
        document.getElementById('name').value = $("#productList option:selected").attr('id');
    });

    let selectCategory = function(value) {
        printProductList(value);
        document.getElementById('productList').options[0].selected = true;
        document.getElementById('name').value = $("#productList option:selected").attr('id');
    }

    $("select[name=productID]").change(function() {
        document.getElementById('name').value = $('select[name = productID] option:selected').attr('id');
    });

    function readURL(input, imgType) {
        if (input.files && input.files[0]) {
            let reader = new FileReader();
            reader.onload = function(e) {
                if(imgType == 'thumbnailImage') {
                    document.getElementById('thumbnailImagePreview').src = e.target.result;
                } else if(imgType == 'titleImage') {
                    document.getElementById('titleImagePreview').src = e.target.result;
                } else if(imgType == 'detailImage') {
                    document.getElementById('detailImagePreview').src = e.target.result;
                }
            };
            reader.readAsDataURL(input.files[0]);
        } else {
            document.getElementById('preview').src = "";
        }
    }

    function printProductList(category) {
        let postObj = {
            'category':category
        }

        $.ajax({
            url: '${pageContext.request.contextPath}/sell/search/product',
            method: 'post',
            dataType: 'json',
            data: JSON.stringify(postObj),
            contentType: 'application/json; charset=utf-8',
            success: function(pageResponse) {
                console.log(pageResponse);
                let listHTML = '';

                if(pageResponse.productList == 0) {
                    listHTML += '카테고리에 해당되는 상품이 없습니다.';
                } else {
                    $.each(pageResponse.productList, function(i, productList) {
                        listHTML += '<option id = "' + productList.name + '" value="' + productList.productID + '">' + productList.name + '</option>';
                    });
                }

                $('#productList').html(listHTML);
            }
        });
    }
</script>
</body>
</html>
