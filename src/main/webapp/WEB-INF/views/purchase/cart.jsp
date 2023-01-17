<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2023-01-17
  Time: 오전 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body onload="printList()">
<p class="subtitle">장바구니</p>

<form class="ui form" id="form" method="post" action="${pageContext.request.contextPath}/purchase/do" style="width: 50%;">
    <input type="hidden" id="idx" name="idx" value="${member.idx}">
    <input type="hidden" id="price" name="price" value="${totalPrice}">
    <div id="list"></div>
 <!--   <c:forEach items="${cartList}" var="cartList">
        <input type="hidden" id="sellID" name="sellID" value="${cartList.sellID}">
        <input type="hidden" id="name" name="name" value="${cartList.name}">
        <div class="ui items">
            <div class="item">
                <div class="image">
                    <img src="${pageContext.request.contextPath}/static/images/cut/${cartList.thumbnailImageName}">
                </div>
                <div class="content">
                    <a class="header">${cartList.name}</a>
                    <div class="description">
                        <p> ${cartList.price}원</p>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach> -->
    <p class="subtitle">배송 정보</p>
    <div class="field">
        <label>이름</label>
        <input type="text" value="${member.name}">
    </div>
    <div class="field">
        <label>전화번호</label>
        <input type="text" value="${member.mobile}">
    </div>
    <div class="field">
        <label>배송지 주소</label>
        <input type="text" name="address" value="${member.address}">
    </div>
    <div class="ui divider">
        계산 금액 <span id="totalPrice"></span>
    </div>
    <button class="ui button" type="submit" style="margin-top: 30px;">구매</button>
</form>
<script>
    function printList() {
        $.ajax({
            url: '${pageContext.request.contextPath}/purchase/cart',
            method: 'post',
            contentType: 'application/json; charset=utf-8',
            success: function(pageResponse) {
                console.log(pageResponse);
                let listHTML = '';
                $.each(pageResponse.cartList, function(i, cartList) {
                    let imgSrc = '${pageContext.request.contextPath}/static/images/cut/' + cartList.thumbnailImageName;
                    listHTML += '<input type="hidden" id="sellID" name="sellID" value="' + cartList.sellID + '">';
                    listHTML += '<input type="hidden" id="name" name="name" value="' + cartList.name + '">';
                    listHTML += '<div class="ui items">';
                    listHTML += '<div class="item">';
                    listHTML += '<div class="image">';
                    listHTML += '<img src="' + imgSrc +'"/>';
                    listHTML += '</div>';
                    listHTML += '<div class="content">';
                    listHTML += '<a class="header">' + cartList.name + '</a>';
                    listHTML += '<div class="description">';
                    listHTML += '<p>' + cartList.price + "</p>";
                    listHTML += '</div>';
                    listHTML += '</div>';
                    listHTML += '</div>';
                    listHTML += '</div>';
                })

                $('#list').html(listHTML);


            }
        })
    }
</script>
</body>
</html>
