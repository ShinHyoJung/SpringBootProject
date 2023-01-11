<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2022-11-30
  Time: 오후 3:24
  To change this template use File | Settings | File Templates.
--%>
<html>
<style>
    .grid {
        display: grid;
        grid-template-columns: 300px 300px 300px;
        grid-template-rows: 250px 250px;
    }

    .item {

    }
</style>
<body onload="printList()">
<p>판매</p>
<div class="grid-container">
    <div id="table" class="grid-x grid-margin-x small-up-2 medium-up-6">
    </div>
</div>
<nav id="pagination" aria-label="Pagination">
</nav>
<a href="${pageContext.request.contextPath}">뒤로가기</a> <br>
<a href="${pageContext.request.contextPath}/sell/register">판매 등록</a>
</body>
<script>
    function printList(currentPage) {
        if(!currentPage) {
            currentPage = 1;
        }

        let postObj = {
            'currentPage':currentPage
        };

        $.ajax({
            url: '${pageContext.request.contextPath}/sell/list',
            method: 'post',
            dataType: 'json',
            data:JSON.stringify(postObj),
            contentType: 'application/json; charset=utf-8',
            success: function(pageResponse) {
                console.log(pageResponse);
                let pageHTML= '';
                let listHTML = '';

                pageHTML += '<ul class="pagination">';
                if(pageResponse.paging.prev) {
                    pageHTML += '<li class="pagination-previous"> < </li>';
                }

                for(let i = pageResponse.paging.startPage; i <= pageResponse.paging.endPage; i++) {
                    if(currentPage == i) {
                        pageHTML += '<li class="current"><button onclick="printList(' + i + ')">' + i + '</button></li>';
                    } else {
                        pageHTML += '<li><button onclick="printList(' + i + ')">' + i + '</button></li>';
                    }
                }

                if(pageResponse.paging.next) {
                    pageHTML += '<li class="pagination-next"> > </li>';
                }

                pageHTML += '</ul>';
                pageHTML += '</li>';

                $.each(pageResponse.sellList, function(i, sellList) {
                    let imgSrc = '${pageContext.request.contextPath}/static/images/thumbnail/' + sellList.thumbnailImageName;
                    let detailSrc = '${pageContext.request.contextPath}/sell/detail/' + sellList.sellID;
                    listHTML += '<div class="cell">';
                    listHTML += '<div class="card">';
                    listHTML += '<input type="hidden" id="sellID" name="sellID" value="' + sellList.sellID + '"/>';
                    listHTML += '<img src="' + imgSrc +'"/>';
                    listHTML += '<div class="card-section"/>';
                    listHTML += '<a href="' + detailSrc + '" name="' + sellList.title + '">' + sellList.title + '</a>';
                    listHTML += '<p>' + sellList.price + '원 </p>';
                    listHTML += '</div>';
                    listHTML += '</div>';
                });

                $('#pagination').html(pageHTML);
                $('#table').html(listHTML);
            }
        })
    }
</script>
</html>
