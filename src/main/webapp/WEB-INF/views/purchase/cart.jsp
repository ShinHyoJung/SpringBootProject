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
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js"></script>
<body onload="printList()">
<p class="subtitle">장바구니</p>
<form class="ui form" id="form" name="payForm" method="post" action="${pageContext.request.contextPath}/purchase/do" style="width: 50%;">
</form>
<br>
<button class="ui button" type="button" onclick="payCard()" style="margin-top: 30px;"><i class="credit card icon"></i>결제</button>
<script>
    function printList() {
        $.ajax({
            url: '${pageContext.request.contextPath}/purchase/cart',
            method: 'post',
            contentType: 'application/json; charset=utf-8',
            success: function(pageResponse) {
                console.log(pageResponse);
                let stringHTML = '';
                let emptyMsg = '장바구니가 비었습니다.';

                if(pageResponse.cartList == 0) {
                    $('#form').html(emptyMsg);
                } else {
                    stringHTML += '<input type="hidden" id="idx" name="idx" value="'+ pageResponse.member.idx + '">';
                    stringHTML += '<input type="hidden" id="price" name="price" value="' + pageResponse.totalPrice +'">';
                    $.each(pageResponse.cartList, function(i, cartList) {
                        let imgSrc = '${pageContext.request.contextPath}/static/images/cut/' + cartList.thumbnailImageName;
                        stringHTML += '<input type="hidden" id="sellID" name="sellID" value="' + cartList.sellID + '">';
                        stringHTML += '<input type="hidden" name="name" value="' + cartList.name + '">';
                        stringHTML += '<div class="ui items">';
                        stringHTML += '<div class="item">';
                        stringHTML += '<div class="image">';
                        stringHTML += '<img src="' + imgSrc +'"/>';
                        stringHTML += '</div>';
                        stringHTML += '<div class="content">';
                        stringHTML += '<a class="header">' + cartList.name + '</a>';
                        stringHTML += '<div class="description">';
                        stringHTML += '<p>' + cartList.price + "</p>";
                        stringHTML += '<button class="ui button" type="button" onclick="dumbCart(this.id)" value="'+ cartList.sellID +'">삭제</button>';
                        stringHTML += '</div>';
                        stringHTML += '</div>';
                        stringHTML += '</div>';
                        stringHTML += '</div>';
                    });
                    stringHTML += '<p class="subtitle"> 배송정보 </p>';
                    stringHTML += '<div class="field">';
                    stringHTML += '<label>이름</label>';
                    stringHTML += '<input type="text" id="name" value="' + pageResponse.member.name + '">';
                    stringHTML += '</div>';
                    stringHTML += '<div class="field">';
                    stringHTML += '<label>전화번호</label>';
                    stringHTML += '<input type="text" id="mobile" name="mobile" value="' + pageResponse.member.mobile + '">';
                    stringHTML += '</div>';
                    stringHTML += '<div class="field">';
                    stringHTML += '<label>배송지 주소</label>';
                    stringHTML += '<input type="text" id="address" name="address" value="' + pageResponse.member.address + '">';
                    stringHTML += '</div>';

                    stringHTML += '<div class="ui divider">';
                    stringHTML += '<p style="font-size: 20px; margin-top: 30px;" > 계산 금액: ' +  pageResponse.totalPrice + '원 </p><br>';
                //    stringHTML += '<button class="ui button" type="button" onclick="' + payCard() + '" style="margin-top: 30px;">구매</button>';
                    stringHTML += '</div>';

                    $('#form').html(stringHTML);
                }
            }
        });
    }

    function payCard() {
        let price = document.getElementById('price').value;
        let IMP = window.IMP;

        IMP.init('imp31116588');

        IMP.request_pay({
            pg: 'html5_inicis',
            pay_method: 'card',
            merchant_uid : 'merchant_'+new Date().getTime(),
            name : '결제테스트',
            amount : price,
            buyer_email: '${member.mail}',
            buyer_name : '${member.name}',
            buyer_tel : '${member.mobile}',
            buyer_addr : '${member.address}',
        }, function(rsp) {
            if(rsp.success) {
                let form = document.payForm;
                form.submit();
            } else {
                alert("결제에 실패하였습니다.");
            }
        });
    }

    function dumbCart(sellID) {
        let postObj = {
            'sellID':sellID
        };

        $.ajax({
            url: '${pageContext.request.contextPath}/purchase/cart/dump',
            method: 'post',
            dataType: 'json',
            data: JSON.stringify(postObj),
            contentType: 'application/json; charset=utf-8',
            success: function(pageResponse) {
                let stringHTML = pageResponse.totalPrice;
                $('#totalPrice').html(stringHTML);
                printList();
            }
        })
    }
</script>
</body>
</html>
