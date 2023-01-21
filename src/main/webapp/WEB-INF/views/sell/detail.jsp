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
<button class="ui button" onclick="location.href='${pageContext.request.contextPath}/sell/update/${getDetailResponse.sell.sellID}'"><i class="alternate pencil icon"></i></button>
<button class="ui button" onclick="location.href='${pageContext.request.contextPath}/sell/delete/${getDetailResponse.sell.sellID}'"><i class="trash alternate icon"></i></button>
<br>
<p style="margin-top: 20px; font-size: 30px;">${getDetailResponse.sell.title}</p> <br>
<form method="post" action="${pageContext.request.contextPath}/purchase/pay" name="purchaseForm">
    <input type="hidden" id="sellID" name="sellID" value="${getDetailResponse.sell.sellID}">
    <input type="hidden" id="productID" name="productID" value="${getDetailResponse.sell.productID}">
    <input type="hidden" id="name" name="name" value="${getDetailResponse.sell.name}">
    <input type="hidden" id="price" name="price" value="${getDetailResponse.sell.price}">
<div class="ui items">
    <div class="item">
        <a class="ui small sellImage">
            <img src="${pageContext.request.contextPath}/static/images/cut/${getDetailResponse.sell.titleImageName}"/>
        </a>
        <div class="content" style="margin-left: 20px;">
            <a class="header">${getDetailResponse.sell.name}</a>
            <div class="ui right labeled input">
                <input type="text" id="quantity" name="quantity" value="1" placeholder="수량">
                <div class="ui basic label">
                    개
                </div>
            </div>
            <div class="description">
                <p>가격 : ${getDetailResponse.sell.price} 원</p>
            </div>
        </div>
    </div>
</div>
    <button class="ui button" type="button" onclick="purchase()">구매</button>
    <button class="ui button" type="button" onclick="addCart()"><i class="cart arrow down icon"></i></button>
</form>
<div class="ui divider"></div>
<p class="subtitle"> 상세 정보 </p>
<img src="${pageContext.request.contextPath}/static/images/${getDetailResponse.sell.detailImageName}"/> <br>
<br>
${getDetailResponse.sell.content} <br>
<br>
작성날짜: <fmt:formatDate pattern="yyyy-MM-dd hh:MM" value="${getDetailResponse.sell.createDate}"/> <br>
수정날짜: <fmt:formatDate pattern="yyyy-MM-dd hh:MM" value="${getDetailResponse.sell.updateDate}"/> <br>
<button class="ui button" style="margin-top: 20px;" onclick="location.href='${pageContext.request.contextPath}/sell/'"><i class="list icon"></i></button>
<script>
    function purchase() {
        let form = document.purchaseForm;
        form.submit();
    }

    function addCart() {
        let sellID = document.getElementById('sellID').value;
        let name = document.getElementById('name').value;
        let price = document.getElementById('price').value;
        let quantity = document.getElementById('quantity').value;

        let postObj = {
            'sellID':sellID,
            'name': name,
            'price':price,
            'quantity':quantity
        };

        $.ajax({
            url: '${pageContext.request.contextPath}/purchase/cart/add',
            method: 'post',
            dataType: 'json',
            data: JSON.stringify(postObj),
            contentType: 'application/json; charset=utf-8',
            success: function(pageResponse) {
                alert(pageResponse.message);
            }
        })
    }
</script>
</body>
</html>
