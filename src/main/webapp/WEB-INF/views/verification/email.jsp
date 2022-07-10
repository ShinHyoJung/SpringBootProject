<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2022-07-08
  Time: 오전 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>이메일</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<input type="text" id="email" placeholder="이메일"/>
<button type="button" onclick="code();">인증번호 받기</button>
<input type="text" id="code" placeholder="내용 입력"/>
<button type="button" id="check" onclick="validate();">인증 확인</button>
</body>

<script>
    function code() {
        var email = document.getElementById("email").value;

        $.ajax({
            method:"post",
            dataType: "json",
            data: {"email":email},
            url: "/developer/email"
        })
        alert("인증번호가 전송되었습니다.");
    }

    function validate() {
        var code = document.getElementById("code").value;
        var postObj = {"code":code};
        $.ajax({
            method:"post",
            url: "/developer/validate",
            dataType: "json",
            data: JSON.stringify(postObj),
            contentType: "application/json; charset=UTF-8",
            success: function(ValidateCodeResponse) {
                alert(ValidateCodeResponse.message);
                if(ValidateCodeResponse.status = "SUCCESS") {
                    location.href="/developer/home";
                }
            }
        })
    }
</script>
</html>