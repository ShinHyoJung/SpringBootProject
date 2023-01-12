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
<button class="ui button" onclick="location.href='${pageContext.request.contextPath}/product/add'"><i class="plus icon"></i></button>
<button class="ui button" onclick="location.reload()"><i class="undo icon"></i></button>
<button class="ui button" onclick="remove()"><i class="trash alternate icon"></i></button>
<button class="ui button" onclick="save()"><i class="save icon"></i></button>
<table id="table" class="ui fixed single line celled table">
</table>
<div id="pagination" class="ui pagination menu" style="margin-left: 500px;">
</div>
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

                if (pageResponse.paging.prev) {
                    pageHTML += '<a class="item"> < </a>';
                }

                for (let i = pageResponse.paging.startPage; i <= pageResponse.paging.endPage; i++) {
                    if (currentPage == i) {
                        pageHTML += '<a class="item active" onclick="printList(' + i + ')">' + i + '</a>';
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
                listHTML += '<th class="two wide"> 제품 이미지 </th>';
                listHTML += '<th> 제품 이름 </th>';
                listHTML += '<th> 제품 코드 </th>';
                listHTML += '<th class="one wide"> 전체 수량 </th>';
                listHTML += '<th class="one wide"> 팔린 개수 </th>';
                listHTML += '<th class="one wide"> 남은 수량 </th>';
                listHTML += '</tr>';
                listHTML += '</thead>';
                listHTML += '<tbody>';

                if(pageResponse.productList == 0) {
                    listHTML += '<tr>';
                    listHTML += '<td></td>';
                    listHTML += '<td>  </td>';
                    listHTML += '<td> </td>';
                    listHTML += '<td>재고 목록이 없습니다. </td>';
                    listHTML += '<td> </td>';
                    listHTML += '<td> </td>';
                    listHTML += '<td> </td>';
                    listHTML += '<td> </td>';
                    listHTML += '</tr>';
                } else {
                    $.each(pageResponse.productList, function(i, productList) {
                        let imgSrc = '${pageContext.request.contextPath}/static/images/thumbnail/' + productList.thumbnailImageName;
                        let detailSrc = '${pageContext.request.contextPath}/product/detail/' + productList.productID;
                        listHTML += '<tr>';
                        listHTML += '<td><input type="checkbox" name="productID" value="' + productList.productID + '"></td>';
                        listHTML += '<td>'+ productList.productID +'</td>';
                        listHTML += '<td height="100"><img src="'+ imgSrc + '"/></td>';
                        listHTML += '<td><a href="'+ detailSrc + '">' + productList.name + '</a></td>';
                        listHTML += '<td>' + productList.code + '</td>';
                        listHTML += '<td>' + productList.fullQuantity + '</td>';
                        listHTML += '<td><input type="text" style="width:60px;" value="' + productList.soldQuantity + '"/></td>';
                        listHTML += '<td><input type="text" style="width:60px;" value="' + productList.leftQuantity + '"/></td>';
                        listHTML += '</tr>';
                    });
                }
                listHTML += '</tbody>';

                $('#pagination').html(pageHTML);
                $('#table').html(listHTML);
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
                    url:'${pageContext.request.contextPath}/product/delete',
                    method:'post',
                    dataType: 'json',
                    data: JSON.stringify(postObj),
                    contentType: 'application/json; charset=utf-8',
                    success: function(pageResponse) {
                        console.log(pageResponse);
                        if(pageResponse.code == '성공') {
                            location.reload();
                        }
                    }
                })
            }
        })
    }

    function save() {

    }
</script>
</body>
</html>
