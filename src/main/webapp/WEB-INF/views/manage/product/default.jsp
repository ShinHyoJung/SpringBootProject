<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2023-01-04
  Time: 오후 5:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body onload="printList()">
<p class="subtitle" style="margin-top: 10px;">재고관리</p>
<br>
<button class="ui button" onclick="location.href='${pageContext.request.contextPath}/manage/product/add'"><i class="plus icon"></i></button>
<button class="ui button" onclick="location.reload()"><i class="undo icon"></i></button>
<button class="ui button" onclick="remove()"><i class="trash alternate icon"></i></button>
<select class="ui dropdown" id="searchOption">
    <option value="name">제품 명</option>
    <option value="code">제품 코드</option>
    <option value="category">카테고리</option>
</select>
<div class="ui icon input">
    <input type="text" id="keyword" onkeyup="printList();" placeholder="Search...">
    <i class="search icon"></i>
</div>
<table id="table" class="ui fixed single line celled table" style="width: 90%;">
</table>
<div id="pagination" class="ui pagination menu" style="margin-left: 500px;">
</div>
<script>
    function printList(currentPage) {
        let searchOption = document.getElementById('searchOption').value;
        let keyword = document.getElementById('keyword').value;

        if(!currentPage) {
            currentPage = 1;
        }

        if(!searchOption) {
            searchOption = 'name';
        }

        let postObj = {
            'currentPage':currentPage,
            'searchOption':searchOption,
            'keyword':keyword
        };

        $.ajax({
            url: '${pageContext.request.contextPath}/manage/product/list',
            method: 'post',
            dataType: 'json',
            data: JSON.stringify(postObj),
            contentType: 'application/json; charset=utf-8',
            success: function(pageResponse) {
                console.log(pageResponse);
                let emptyMsg = '재고가 없습니다.';
                let pageHTML = '';
                let listHTML = '';

                if(pageResponse.productList == 0) {
                    $('#table').html(emptyMsg);
                    $('#pagination').css('display', 'none');
                } else {
                    if (pageResponse.paging.prev) {
                        pageHTML += '<a class="item"> < </a>';
                    }

                    for (let i = pageResponse.paging.startPage; i <= pageResponse.paging.endPage; i++) {
                        if (currentPage == i) {
                            pageHTML += '<a class="active item" onclick="printList(' + i + ')">' + i + '</a>';
                        } else {
                            pageHTML += '<a class="item" onclick="printList(' + i + ')">' + i + '</a>';
                        }
                    }

                    if (pageResponse.paging.next) {
                        pageHTML += '<a class="item"> > </a>';
                    }

                    listHTML += '<thead>';
                    listHTML += '<tr>';
                    listHTML += '<th class="one wide" style="width:40px;"></th>';
                    listHTML += '<th class="one wide">제품 번호</th>';
                    listHTML += '<th class="two wide">카테고리</th>';
                    listHTML += '<th class="two wide"> 제품 이미지 </th>';
                    listHTML += '<th> 제품 이름 </th>';
                    listHTML += '<th> 제품 코드 </th>';
                    listHTML += '<th class="one wide"> 전체 수량 </th>';
                    listHTML += '<th class="one wide"> 팔린 개수 </th>';
                    listHTML += '<th class="one wide"> 남은 수량 </th>';
                    listHTML += '</tr>';
                    listHTML += '</thead>';
                    listHTML += '<tbody>';

                    $.each(pageResponse.productList, function(i, productList) {
                        let imgSrc = '${pageContext.request.contextPath}/static/images/cut/' + productList.thumbnailImageName;
                        let detailSrc = '${pageContext.request.contextPath}/manage/product/detail/' + productList.productID;
                        listHTML += '<tr>';
                        listHTML += '<td><input type="checkbox" name="productID" value="' + productList.productID + '"></td>';
                        listHTML += '<td>'+ productList.productID +'</td>';
                        listHTML += '<td>' + productList.category + '</td>';
                        listHTML += '<td height="100"><img src="'+ imgSrc + '"/></td>';
                        listHTML += '<td><a href="'+ detailSrc + '">' + productList.name + '</a></td>';
                        listHTML += '<td>' + productList.code + '</td>';
                        listHTML += '<td>' + productList.fullQuantity + '</td>';
                        listHTML += '<td>' + productList.soldQuantity + '</td>';
                        listHTML += '<td>' + productList.leftQuantity + '</td>';
                        listHTML += '</tr>';
                    });
                    listHTML += '</tbody>';

                    $('#pagination').html(pageHTML);
                    $('#table').html(listHTML);
                }
            }
        })
    }

    function remove() {
        $('input:checkbox[name=productID]').each(function (index) {
            if($(this).is(':checked')==true){
                let postObj = {
                    'productID':$(this).val()
                };

                $.ajax({
                    url:'${pageContext.request.contextPath}/manage/product/delete',
                    method:'post',
                    dataType: 'json',
                    data: JSON.stringify(postObj),
                    contentType: 'application/json; charset=utf-8',
                    success: function(pageResponse) {
                        alert(pageResponse.message);
                        if(pageResponse.code == 'SUCCESS') {
                            location.reload();
                        }
                    }
                })
            }
        })
    }

    function save() {

    }

    function enter() {
        if(window.event.keyCode == 13) {
            printList();
        }
    }
</script>
</body>
</html>
