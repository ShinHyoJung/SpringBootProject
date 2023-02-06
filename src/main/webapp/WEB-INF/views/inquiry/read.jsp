<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2022-11-30
  Time: 오후 5:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<body>
<table class="ui basic table" style="width: 50%;">
    <tr>
    <td>제목: ${getReadResponse.title}</td>
    </tr>
    <tr>
    <td>작성자: ${getReadResponse.writer}</td>
    </tr>
    <tr style="height:500px;">
        <td >${getReadResponse.content}</td>
    </tr>
    <tr>
        <td>작성날짜: <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${getReadResponse.createDate}"/></td>
    </tr>
    <tr>
        <td>수정날짜: <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${getReadResponse.updateDate}"/></td>
    </tr>
</table>
<br>
<c:if test="${getReadResponse.idx eq idx}">
    <a class="ui button" href="${pageContext.request.contextPath}/inquiry/update/${getReadResponse.inquiryID}"><i class="alternate pencil icon"></i></a>
    <a class="ui button" href="${pageContext.request.contextPath}/inquiry/delete/${getReadResponse.inquiryID}"><i class="trash alternate icon"></i></a>
</c:if>
<sec:authorize access="hasAnyRole('ROLE_USER')">
    <a class="ui button" href="${pageContext.request.contextPath}/inquiry/"><i class="list icon"></i></a>
</sec:authorize>
<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
    <c:choose>
        <c:when test="${empty answer}">
            <form class="ui form" method="post" action="${pageContext.request.contextPath}/inquiry/manage/answer/write" style="width: 40%;">
                <input type="hidden" id="idx" name="idx" value="${idx}">
                <input type="hidden" id="inquiryID" name="inquiryID" value="${getReadResponse.inquiryID}">
                <textarea id="content" name="content"> </textarea>
                <button class="ui button" type="submit">등록</button>
            </form>
        </c:when>
        <c:otherwise>
            <table id="answer" class="ui basic table" style="width: 50%;">
                <tr>
                    <td>작성자: ${answer.writer} </td>
                </tr>
                <tr >
                    <td style="height: 500px;">내용: ${answer.content} </td>
                </tr>
                <input type="hidden" id="answerID" value="${answer.answerID}">
            </table>
        </c:otherwise>
    </c:choose>
    <button class="ui button" onclick="updateAnswer();"><i class="alternate pencil icon"></i></button>
    <button class="ui button" onclick="removeAnswer();"><i class="trash alternate icon"></i></button>
    <a class="ui button" href="${pageContext.request.contextPath}/inquiry/manage"><i class="list icon"></i></a>
</sec:authorize>
<script>
    function updateAnswer() {

    }

    function removeAnswer() {

    }
</script>
</body>
</html>
