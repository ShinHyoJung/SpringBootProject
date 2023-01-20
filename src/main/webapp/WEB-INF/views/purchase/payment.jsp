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
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<body>
<p class="subtitle">주문</p>
<form class="ui form" method="post" name="payForm" action="${pageContext.request.contextPath}/purchase/do" style="width: 50%;">
    <input type="hidden" id="idx" name="idx" value="${member.idx}">
    <input type="hidden" id="sellID" name="sellID" value="${sell.sellID}">
    <input type="hidden" id="name" name="name" value="${sell.name}">
    <input type="hidden" id="price" name="price" value="">
    <input type="hidden" id="impUid" name="impUid" value="">
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
        <label>우편 번호</label> <button class="ui button" type="button" onclick="searchZipCode();">우편번호 찾기</button>
        <input type="text" name="zipCode" value="${member.zipCode}" style="margin-top: 10px;">
    </div>
    <div class="field">
        <label>주소</label>
        <input type="text" name="address" value="${member.address}">
    </div>
    <div class="field">
        <label>상세 주소</label>
        <input type="text" name="detailAddress" value="${member.detailAddress}">
    </div>
    <div class="ui divider">
        ${sell.price} X
        <div class="ui right labeled input" style="width:10%;">
            <input type="text" id="quantity" name="quantity" value="${quantity}" placeholder="수량">
            <div class="ui basic label">개</div>
        </div>${quantity} = 계산 금액 <span id="calculatedPrice"></span> 원
            <button class="ui button" type="button" onclick="modifyQuantity()">변경</button>
    </div>
    <button class="ui button" type="button" onclick="payCard();" style="margin-top: 30px;"><i class="credit card icon"></i>결제</button>
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
                document.getElementById('impUid').value = rsp.imp_uid;
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
