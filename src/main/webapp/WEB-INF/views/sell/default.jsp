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
</style>
<body onload="printList()">
<p>판매</p>
<a href="${pageContext.request.contextPath}">뒤로가기</a> <br>
<button class="ui button" onclick="location.href='${pageContext.request.contextPath}/sell/register'"><i class="plus icon"></i></button>
<button class="ui button" onclick="location.reload()"><i class="undo icon"></i></button>
<div class="ui three stackable cards" style="margin-top: 10px;">
    <div id="table"></div>
</div>
<div id="pagination" class="ui pagination menu" style="margin-top: 30px;">
</div>
<br>
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

                if(pageResponse.paging.prev) {
                    pageHTML += '<a class="item"> < </a>';
                }

                for(let i = pageResponse.paging.startPage; i <= pageResponse.paging.endPage; i++) {
                    if(currentPage == i) {
                        pageHTML += '<a class="item" onclick="printList(' + i + ')">' + i + '</a>';
                    } else {
                        pageHTML += '<a class="item" onclick="printList(' + i + ')">' + i + '</a>';
                    }
                }

                if(pageResponse.paging.next) {
                    pageHTML += '<a class="item"> > </a>';
                }

                listHTML += '<div class="ui three stackable cards">';
                $.each(pageResponse.sellList, function(i, sellList) {
                    let imgSrc = '${pageContext.request.contextPath}/static/images/thumbnail/' + sellList.thumbnailImageName;
                    let detailSrc = '${pageContext.request.contextPath}/sell/detail/' + sellList.sellID;
                    listHTML += '<div class="ui card">';
                    listHTML += '<div class="image">';
                    listHTML += '<input type="hidden" id="sellID" name="sellID" value="' + sellList.sellID + '"/>';
                    listHTML += '<img src="' + imgSrc +'"/>';
                    listHTML += '</div>';
                    listHTML += '<div class="content">';
                    listHTML += '<a class="header" href="' + detailSrc + '" name="' + sellList.title + '">' + sellList.title + '</a>';
                    listHTML += '<div class="description" style="margin-top: 20px;">';
                    listHTML +=  sellList.price + '원 </div>';
                    listHTML += '</div>';
                    listHTML += '</div>';
                });
                listHTML += '</div>';

                $('#pagination').html(pageHTML);
                $('#table').html(listHTML);
            }
        })
    }
</script>
</html>
