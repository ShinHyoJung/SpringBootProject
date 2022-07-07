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
            url: "/demo/email",
            success: function (res) {
                console.log(res);
                alert("인증번호가 전송되었습니다.");

                $("#check").click(function () {
                    var crNum = document.getElementById("crNum").value;
                    if (res == crNum) {
                        alert("인증 성공했습니다.");
                    } else {
                        alert("인증 실패했습니다.");
                    }
                });
            }
        })
    }

    function validate() {
        var code = document.getElementById("code").value;
        var postObj = {"code":code};

        $.ajax({
            method:"post",
            data: JSON.stringify(postObj),
            dataType: "json",
            url: "/validate",
            success: function(data) {
                alert(data.message);
            }
        })
    }
</script>
</html>