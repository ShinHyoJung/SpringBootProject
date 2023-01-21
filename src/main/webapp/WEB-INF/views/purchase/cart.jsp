<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2023-01-17
  Time: 오전 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<body onload="printList()">
<p class="subtitle">장바구니</p>
<form class="ui form" id="form" name="payForm" method="post" action="${pageContext.request.contextPath}/purchase/do" style="width: 50%;">
</form>
<br>
<script>
    function printList() {
        $.ajax({
            url: '${pageContext.request.contextPath}/purchase/cart',
            method: 'post',
            contentType: 'application/json; charset=utf-8',
            success: function(pageResponse) {
                console.log(pageResponse);
                let stringHTML = '';

                if(pageResponse.code == 'SUCCESS') {
                    stringHTML += '<input type="hidden" id="idx" name="idx" value="'+ pageResponse.member.idx + '">';
                    stringHTML += '<input type="hidden" id="price" name="price" value="' + pageResponse.totalPrice +'">';
                    stringHTML += '<input type="hidden" id="impUid" name="impUid" value="">';
                    $.each(pageResponse.cartList, function(i, cartList) {
                        let imgSrc = '${pageContext.request.contextPath}/static/images/cut/' + cartList.thumbnailImageName;
                        stringHTML += '<input type="hidden" id="quantity" name="quantity" value="' + cartList.quantity +'">';
                        stringHTML += '<input type="hidden" id="sellID" name="sellID" value="' + cartList.sellID + '">';
                        stringHTML += '<input type="hidden" id="productID" name="productID" value="' + cartList.productID + '">';
                        stringHTML += '<input type="hidden" name="name" value="' + cartList.name + '">';
                        stringHTML += '<div class="ui items">';
                        stringHTML += '<div class="item">';
                        stringHTML += '<div class="image">';
                        stringHTML += '<img src="' + imgSrc +'"/>';
                        stringHTML += '</div>';
                        stringHTML += '<div class="content">';
                        stringHTML += '<a class="header">' + cartList.name + '</a>';
                        stringHTML += '<div class="description">';
                        stringHTML += '<p>' + cartList.price + '원</p>';
                        stringHTML += '<p>수량: ' + cartList.quantity +'</p>';
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
                    stringHTML += '<input type="text" id="mobile" name="mobile" value="' + pageResponse.member.phone + '">';
                    stringHTML += '</div>';
                    stringHTML += '<div class="field">';
                    stringHTML += '<label>우편 번호</label> <button class="ui button" type="button" onclick="searchZipCode();">우편번호 찾기</button>';
                    stringHTML += '<input type="text" id="zipCode" name="zipCode" value="' + pageResponse.member.zipCode + '" style="margin-top: 20px;">';
                    stringHTML += '</div>';
                    stringHTML += '<div class="field">';
                    stringHTML += '<label>주소</label>';
                    stringHTML += '<input type="text" id="address" name="address" value="' + pageResponse.member.address + '">';
                    stringHTML += '</div>';
                    stringHTML += '<div class="field">';
                    stringHTML += '<label>상세 주소</label>';
                    stringHTML += '<input type="text" id="detailAddress" name="detailAddress" value="' + pageResponse.member.detailAddress + '">';
                    stringHTML += '</div>';
                    stringHTML += '<div class="ui divider">';
                    stringHTML += '<p style="font-size: 20px; margin-top: 30px;" > 계산 금액: ' +  pageResponse.totalPrice + '원 </p><br>';
                    stringHTML += '<button class="ui button" type="button" id="payBtn" onclick="payCard()" style="margin-top: 10px;"><i class="credit card icon"></i>결제</button>';
                    stringHTML += '</div>';

                    $('#form').html(stringHTML);
                } else {
                    alert(pageResponse.message);
                }
            }
        });
    }

    function searchZipCode() {
        new daum.Postcode({
            oncomplete: function(data) {
                let addr = '';
                let extraAddr = '';

                if(data.roadAddress !== '') {
                    addr = data.roadAddress;
                } else if(data.jibunAddress !== '') {
                    addr = data.jibunAddress;
                }

                if(data.userSelectType === 'R') {
                    if(data.bname !== ''&&/[동][로][가]$/g.test(data.bname)) {
                        extraAddr += data.bname;
                    }

                    if(data.buildingName !== ''&& data.apartment === 'Y') {
                        extraAddr += (extraAddr !== ''? ',' + data.buildingName : data.buildingName);
                    }

                    if(extraAddr !== '') {
                        extraAddr = '(' + extraAddr + ')';
                    }
                    document.getElementById('address').value = extraAddr;
                } else {
                    document.getElementById('address').value = '';
                }

                document.getElementById('zipCode').value = data.zonecode;
                document.getElementById('address').value = addr;

                document.getElementById('address').focus();
            }
        }).open();
    }

    function payCard() {
        let price = document.getElementById('price').value;
        let IMP = window.IMP;

        IMP.init('imp31116588');

        IMP.request_pay({
            pg: 'html5_inicis',
            pay_method: 'card',
            merchant_uid : 'merchant_' + new Date().getTime(),
            name : '결제테스트',
            amount : price,
            buyer_email: '${member.email}',
            buyer_name : '${member.name}',
            buyer_tel : '${member.phone}',
            buyer_addr : '${member.address}',
        }, function(rsp) {
            if(rsp.success) {
                document.getElementById('impUid').value = rsp.imp_uid;
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
                alert(pageResponse.message);
                let stringHTML = pageResponse.totalPrice;
                $('#totalPrice').html(stringHTML);
                printList();
            }
        })
    }
</script>
</body>
</html>
