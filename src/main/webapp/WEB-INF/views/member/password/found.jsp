<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2022-11-16
  Time: 오후 4:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<p class="subtitle">비밀번호 변경</p>
<form class="ui form" style="width: 30%;">
    <div class="field">
        <label>새 비밀번호 입력</label>
        <input type="password" id="password" name="password" placeholder="변경할 비밀번호 입력">
    </div>
    <div class="field">
        <label>비밀번호 확인</label>
        <input type="password" id="passwordConfirm" placeholder="비밀번호 확인">
    </div>
    <button class="ui button" type="button" onclick="change()">변경하기</button>
</form>
<input type="hidden" id="idx" value="${idx}">
<script>
    let pwdVal = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]).{10,20}$/;
    let blankVal = /^.*( ).*$/;

    function change() {
        let password = document.getElementById('password').value.trim();
        let passwordConfirm = document.getElementById('passwordConfirm').value.trim();
        let idx = document.getElementById('idx').value;

        if(!password) {
            alert("비밀번호를 입력해주세요.");
            return false;
        }

        if(!pwdVal.test(password) || blankVal.test(password)) {
            alert("비밀번호는 공백없이 영문대소문자와 숫자, 특수문자 포함하여 10~20자리로 입력해주세요.");
            return false;
        }

        if(!passwordConfirm) {
            alert("비밀번호를 한번 더 입력해주세요.");
            return false;
        }

        if(password != passwordConfirm) {
            alert("비밀번호가 서로 다릅니다. 다시 입력해주세요.");
            return false;
        }

        let postObj = {
            'password': password,
            'idx':idx
        };

        $.ajax({
            url: '${pageContext.request.contextPath}/member/password/change',
            method: 'post',
            dataType: 'json',
            data: JSON.stringify(postObj),
            contentType: 'application/json; charset=utf-8',
            success: function(pageResponse) {
                alert(pageResponse.message);

                if(pageResponse.code == 'SUCCESS') {
                    location.href= '${pageContext.request.contextPath}/member/login';
                }
            }
        })
    }
</script>
</body>
</html>
