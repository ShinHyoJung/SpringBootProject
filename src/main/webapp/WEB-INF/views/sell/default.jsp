<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2022-11-30
  Time: 오후 3:24
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body onload="printList()">
<input type="hidden" id="category" value="${category}">
<button class="ui button" onclick="location.href='${pageContext.request.contextPath}/sell/register'"><i class="plus icon"></i></button>
<button class="ui button" onclick="location.reload()"><i class="undo icon"></i></button>
<select class="ui dropdown" id="searchOption">
    <option value="title">글 제목</option>
    <option value="name">제품 이름</option>
</select>
<div class="ui icon input">
    <input type="text" id="keyword" onkeyup="enter()" placeholder="Search...">
    <i class="search icon"></i>
</div>
<div class="ui five cards" id="cards" style="margin-top: 20px;">
</div>
<div id="pagination" class="ui pagination menu" style="margin-top: 30px; margin-left: 500px;">
</div>
<br>
</body>
<script>
    function printList(currentPage) {
        let category = document.getElementById('category').value;
        let searchOption = document.getElementById('searchOption').value;
        let keyword = document.getElementById('keyword').value;

        if(!currentPage) {
            currentPage = 1;
        }

        if(!searchOption) {
            searchOption = 'title';
        }

        let postObj = {
            'currentPage':currentPage,
            'category': category,
            'searchOption':searchOption,
            'keyword':keyword
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

                if(pageResponse.sellList == 0) {
                    $('#pagination').css('display', 'none');
                } else {
                    if(pageResponse.paging.prev) {
                        pageHTML += '<a class="item"> < </a>';
                    }

                    for(let i = pageResponse.paging.startPage; i <= pageResponse.paging.endPage; i++) {
                        if(currentPage == i) {
                            pageHTML += '<a class="active item" onclick="printList(' + i + ')">' + i + '</a>';
                        } else {
                            pageHTML += '<a class="item" onclick="printList(' + i + ')">' + i + '</a>';
                        }
                    }

                    if(pageResponse.paging.next) {
                        pageHTML += '<a class="item"> > </a>';
                    }

                    $.each(pageResponse.sellList, function(i, sellList) {
                        let imgSrc = '${pageContext.request.contextPath}/static/images/cut/' + sellList.thumbnailImageName;
                        let detailSrc = '${pageContext.request.contextPath}/sell/detail/' + sellList.sellID;
                        listHTML += '<div class="card" style="width:15%;">';
                        listHTML += '<input type="hidden" id="sellID" name="sellID" value="' + sellList.sellID + '"/>';
                        listHTML += '<img src="' + imgSrc +'"/>';
                        listHTML += '<div class="extra">';
                        listHTML += '<a class="header" href="' + detailSrc + '" name="' + sellList.title + '">' + sellList.title + '</a>';
                        listHTML += '<div class="description" style="margin-top: 20px;">';
                        listHTML +=  sellList.price + '원 </div>';
                        listHTML += '</div>';
                        listHTML += '</div>';
                    });

                    $('#pagination').html(pageHTML);
                    $('#cards').html(listHTML);
                }
            }
        })
    }

    function enter() {
        if(window.event.keyCode == 13) {
            printList();
        }
    }
</script>
</html>
