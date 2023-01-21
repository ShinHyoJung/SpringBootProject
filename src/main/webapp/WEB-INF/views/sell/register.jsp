<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <input type="file" name="sellImage" multiple="multiple">
    <input type="hidden" id="name" name="name">
    <div class="field">
        <input type="text" id="title" name="title" placeholder="글 제목">
    </div>
    <div class="field">
        <select class="ui dropdown" name="category" id="category" onchange="selectCategory(this.value);">
            <option value="">카테고리</option>
            <c:forEach items="${categoryList}" var="categoryList">
                <option id="${categoryList.code}" value="${categoryList.name}">${categoryList.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="field" >
        <select class="ui dropdown" id="productList" name="productID">
            <option value="" >제품</option>
        </select>
    </div>
    <div class="field">
        <input type="text" id="price" name="price" placeholder="제품 가격">
    </div>
    <div class="field">
        <textarea id="content" name="content" placeholder="판매글 내용"></textarea>
    </div>
    <input type="file" name="sellImage" multiple="multiple">
    <button class="ui button" type="submit"><i class="save icon"></i></button>
</form>

<script>
    let selectCategory = function(value) {
        printProductList(value);
    }

    $("select[name=productID]").change(function() {
        document.getElementById('name').value = $('select[name = productID] option:selected').attr('id');
    });

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
