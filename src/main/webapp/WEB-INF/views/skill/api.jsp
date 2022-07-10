<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2022-07-02
  Time: 오후 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>API</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<input type="text" id="idx" placeholder="찾을아이디"/>
<button type="button" onclick="api();">전송</button>

<script>
    function api() {
        var idx = document.getElementById("idx").value;

        var postObj = {"idx":idx};

        $.ajax({
            method:"post",
            dataType: "json",
            data: JSON.stringify(postObj),
            url: "/demo/api",
            contentType: 'application/json; charset=utf-8',
            success: function(data) {
                console.log(data.username);
                alert(data.username);
            }
        })

    }

</script>
</body>
</html>
