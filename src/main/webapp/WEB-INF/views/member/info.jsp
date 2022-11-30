<%@ page import="com.project.shop.feature.code.success.SuccessCode" %><%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2022-11-16
  Time: 오후 4:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<form action="${pageContext.request.contextPath}/member/info/update" method="post">
    이름 : <input type="text" id="name" name="name" value = "${getInfoResponse.name}"> <br>
    아이디: ${getInfoResponse.memberID} <br>
    비밀번호: <input type="password" id="password" name="password"> <br>
    생년월일: <input type="text" id="birth" name="birth" value="${getInfoResponse.birth}"> <br>
    휴대폰번호: <input type="text" id="mobile" name="mobile" value="${getInfoResponse.mobile}"> <br>
    이메일: <input type="text" id="mail" name="mail" value="${getInfoResponse.mail}"> <br>
    주소: <input type="text" id="address" name="address" value="${getInfoResponse.address}"> <br>
    생성일시: ${getInfoResponse.createDate} <br>
    변경일시: ${getInfoResponse.updateDate} <br>
    <input type="hidden" id="idx" name="idx" value="${getInfoResponse.idx}">
    <button type="submit">수정하기</button>
</form>
<a href="${pageContext.request.contextPath}">메인으로 가기</a>
<a href="${pageContext.request.contextPath}/member/withdrawal">회원 탈퇴</a>
<script>
    /*
    $(document).ready(function() {
        if(document.getElementById('idx').value == "") {
            location.href= '${pageContext.request.contextPath}';
        }
    })

    function withdrawal() {
        let idx = document.getElementById('idx').value;

        let postObj = {
            'idx':idx
        }

        $.ajax({
            method: 'post',
            url: '${pageContext.request.contextPath}/member/withdrawal',
            dataType: 'json',
            data: JSON.stringify(postObj),
            contentType: 'application/json; charset=utf-8',
            success: function(pageResponse) {

            }
        })
    }
    */
</script>
</body>
</html>
