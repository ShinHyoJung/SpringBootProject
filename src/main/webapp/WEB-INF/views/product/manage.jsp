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
재고관리
<br>
<a href="${pageContext.request.contextPath}/product/add">재고 추가</a>

<table id="table">

</table>
<nav id="pagination">
</nav>
<script>
    function printList(currentPage) {
        if(!currentPage) {
            currentPage = 1;
        }

        let postObj = {
            'currentPage':currentPage
        };

        $.ajax({
            url: '${pageContext.request.contextPath}/product/manage/list',
            method: 'post',
            dataType: 'json',
            data: JSON.stringify(postObj),
            contentType: 'application/json; charset=utf-8',
            success: function(pageResponse) {
                console.log(pageResponse);

                let pageHTML = '';
                let listHTML = '';

                pageHTML += '<ul class="pagination">';
                if (pageResponse.paging.prev) {
                    pageHTML += '<li class="pagination-previous"> < </li>';
                }

                for (let i = pageResponse.paging.startPage; i <= pageResponse.paging.endPage; i++) {
                    if (currentPage == i) {
                        pageHTML += '<li class="current"><button onclick="printList(' + i + ')">' + i + '</button></li>';
                    } else {
                        pageHTML += '<li><button onclick="printList(' + i + ')">' + i + '</button></li>';
                    }
                }

                if (pageResponse.paging.next) {
                    pageHTML += '<li class="pagination-next"> > </li>';
                }

                pageHTML += '</ul>';
                pageHTML += '</li>';

                if(pageResponse.productList) {
                    listHTML += '<thead>';
                    listHTML += '<tr>';
                    listHTML += '<th>제품 번호</th>';
                    listHTML += '<th> 제품 이미지 </th>';
                    listHTML += '<th> 제품 이름 </th>';
                    listHTML += '<th> 전체 수량 </th>';
                    listHTML += '<th> 팔린 개수 </th>';
                    listHTML += '<th> 남은 수량 </th>';
                    listHTML += '</tr>';
                    listHTML += '</thead>';
                }

                $.each(pageResponse.productList, function(i, productList) {
                    let imgSrc = '${pageContext.request.contextPath}/static/images/thumbnail/' + productList.thumbnailImageName;
                    listHTML += '<tbody>';
                    listHTML += '<tr>';
                    listHTML += '<td>'+ productList.productID +'</td>';
                    listHTML += '<td height="150"><img src="'+ imgSrc + '"/></td>';
                    listHTML += '<td>' + productList.name + '</td>';
                    listHTML += '<td>' + productList.fullQuantity + '</td>';
                    listHTML += '<td>' + productList.soldQuantity + '</td>';
                    listHTML += '<td>' + productList.leftQuantity + '</td>';
                    listHTML += '</tbody>';
                });

                $('#pagination').html(pageHTML);
                $('#table').html(listHTML);
            }
        })
    }
</script>
</body>
</html>
