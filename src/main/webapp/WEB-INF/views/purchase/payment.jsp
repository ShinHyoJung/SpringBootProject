<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2023-01-15
  Time: 오후 8:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js"></script>
<body>
<p class="subtitle">결제</p>

<form class="ui form" method="post" name="payForm" action="${pageContext.request.contextPath}/purchase/do" style="width: 50%;">
    <input type="hidden" id="idx" name="idx" value="${member.idx}">
    <input type="hidden" id="sellID" name="sellID" value="${sell.sellID}">
    <input type="hidden" id="name" name="name" value="${sell.name}">
    <input type="hidden" id="price" name="price" value="">
    <div class="ui items">
        <div class="item">
            <div class="image">
                <img src="${pageContext.request.contextPath}/static/images/cut/${sell.thumbnailImageName}">
            </div>
            <div class="content">
                <a class="header">${sell.name}</a>
                <div class="description">
                    <p>${sell.price} 원</p>
                </div>
            </div>
        </div>
    </div>
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
        ${sell.price} X
        <div class="ui right labeled input" style="width:10%;">
            <input type="text" id="quantity" name="quantity" value="${quantity}" placeholder="수량">
            <div class="ui basic label">개</div>
        </div>${quantity} = 계산 금액 <span id="calculatedPrice"></span> 원
            <button class="ui button" type="button" onclick="modifyQuantity()">변경</button>
    </div>
    <button class="ui button" type="button" onclick="payCard();">카드 결제</button>
</form>
<script>
    $(document).ready(function () {
        let result = calculate();
        $('#calculatedPrice').html(result);

        document.getElementById('price').value = result;
    });

    function calculate() {
        let price = '${sell.price}';
        let quantity = document.getElementById('quantity').value;

        let result = price * quantity;

        return result;
    }

    function modifyQuantity() {
        let result = calculate();
        $('#calculatedPrice').html(result);
        document.getElementById('price').value = result;
    }

    function payCard() {
        let price = document.getElementById('price').value;
        var IMP = window.IMP;
        IMP.init('imp31116588');

        IMP.request_pay({
            pg: 'html5_inicis',
            pay_method: 'card',
            merchant_uid : 'merchant_'+new Date().getTime(),
            name : '결제테스트',
            amount : price,
            buyer_email : '${member.mail}',
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


</script>
</body>
</html>
