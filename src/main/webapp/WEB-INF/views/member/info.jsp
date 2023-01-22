<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2022-11-16
  Time: 오후 4:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<body>
<p class="subtitle">내 정보</p>
<form class="ui form" name="infoForm" action="${pageContext.request.contextPath}/member/info/update" method="post" style="width: 30%;">
    <div class="field">
        <label> 이름  </label>
            <input type="text" id="name" name="name" value = "${getInfoResponse.name}">
    </div>
    <div class="field">
        <label> 아이디  </label>
        ${getInfoResponse.loginID}
    </div>
    <div class="field">
        <label> 비밀번호 </label>
            <input type="password" id="password" name="password">
    </div>
    <div class="field">
        <label>  생년월일 </label>
            <input type="text" id="birth" name="birth" value="${getInfoResponse.birth}">
    </div>
    <div class="field">
        <label> 휴대폰번호 </label>
            <input type="text" id="phone" name="phone" value="${getInfoResponse.phone}">
    </div>
    <div class="field">
        <label> 이메일 </label>
            <input type="text" id="email" name="email" value="${getInfoResponse.email}">
    </div>
    <div class="field">
        <label> 우편번호 </label> <button class="ui button" type="button" onclick="searchZipCode();">우편번호 찾기</button>
            <input type="text" id="zipCode" name="zipCode" value="${getInfoResponse.zipCode}" style="margin-top: 10px;">
    </div>
    <div class="field">
        <label> 주소 </label>
        <input type="text" id="address" name="address" value="${getInfoResponse.address}">
    </div>
    <div class="field">
        <label> 상세 주소 </label>
        <input type="text" id="detailAddress" name="detailAddress" value="${getInfoResponse.detailAddress}">
    </div>
    <div class="field">
        <label> 생성일시 </label>
        <fmt:formatDate pattern="yyyy-MM-dd hh:MM" value="${getInfoResponse.createDate}"/>
    </div>
    <div class="field">
        <label> 변경일시 </label>
        <fmt:formatDate pattern="yyyy-MM-dd hh:MM" value="${getInfoResponse.updateDate}"/>
    </div>
        <input type="hidden" id="idx" name="idx" value="${getInfoResponse.idx}">
        <button class="ui button" type="button" onclick="updateInfo();"><i class="save icon"></i></button>
</form>
<button class="ui button" onclick="location.href='${pageContext.request.contextPath}/member/info/download?idx=${idx}'">
    <i class="download icon"></i>엑셀파일 다운로드</button>
<button class="ui button" onclick="location.href='${pageContext.request.contextPath}'">메인으로 가기</button>
<button class="ui button" onclick="location.href='${pageContext.request.contextPath}/member/withdrawal'">회원 탈퇴</button>
<script>
    let pwdVal = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]).{10,20}$/;
    let emailVal = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    let phoneVal = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;

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

    function updateInfo() {
        let password = document.getElementById('password').value.trim();
        let name = document.getElementById('name').value.trim();
        let birth = document.getElementById('birth').value.trim();
        let phone = document.getElementById('phone').value.trim();
        let email = document.getElementById('email').value.trim();
        let zipCode = document.getElementById('zipCode').value.trim();
        let address = document.getElementById('address').value.trim();
        let detailAddress = document.getElementById('detailAddress').value.trim();

        if(!password) {
            alert("비밀번호를 입력해주세요.");
            return false;
        }

        if(!pwdVal.test(password)) {
            alert("비밀번호는 영문대소문자와 숫자, 특수문자 포함하여 10~20자리로 입력해주세요.");
            return false;
        }

        if(!name) {
            alert("이름을 입력해주세요.");
            return false;
        }

        if(!birth) {
            alert("생년월일 8자리를 입력해주세요.");
            return false;
        }

        if(!phone) {
            alert("휴대폰 번호를 입력해주세요.");
            return false;
        }

        if(!email) {
            alert("이메일을 입력해주세요.");
            return false;
        }

        if(!phoneVal.test(phone)) {
            alert("휴대폰 번호 형식이 맞지 않습니다. 다시 입력해주세요.");
            return false;
        }

        if(!emailVal.test(email)) {
            alert("이메일 형식이 맞지 않습니다. 다시 입력해주세요.");
            return false;
        }

        if(!zipCode) {
            alert("우편번호를 입력해주세요.");
            return false;
        }

        if(!address) {
            alert("주소를 입력해주세요.");
            return false;
        }

        if(!detailAddress) {
            alert("상세 주소를 입력해주세요.");
            return false;
        }

        let form = document.infoForm;
        form.submit();
    }
</script>
</body>
</html>
