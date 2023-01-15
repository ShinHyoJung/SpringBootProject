<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2023-01-14
  Time: 오후 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body onload="printList()">
    <p class="subtitle">카테고리 관리</p>
    <button class="ui button" onclick="location.href='${pageContext.request.contextPath}/manage/category/add'"><i class="plus icon"></i></button>
    <button class="ui button" onclick="remove()"><i class="trash alternate icon"></i></button>
    <table id="table" class="ui fixed single line celled table">
    </table>
<script>
    function printList() {
        $.ajax({
            url: '${pageContext.request.contextPath}/manage/category/list',
            method: 'post',
            contentType: 'application/json; charset=utf-8',
            success: function(pageResponse) {
                console.log(pageResponse);

                let listHTML = '';

                listHTML += '<thead>';
                listHTML += '<tr>';
                listHTML += '<th class="one wide" style="width:40px;"></th>';
                listHTML += '<th class="two wide">카테고리 번호</th>';
                listHTML += '<th class="two wide"> 카테고리 이름 </th>';
                listHTML += '<th> 카테고리 코드 </th>';
                listHTML += '</tr>';
                listHTML += '</thead>';
                listHTML += '<tbody>';

                if(pageResponse.categoryList == 0) {
                    listHTML += '<tr>';
                    listHTML += '<td> </td>';
                    listHTML += '<td>카테고리가 없습니다.</td>';
                    listHTML += '<td> </td>';
                    listHTML += '<td> </td>';
                    listHTML += '</tr>';
                } else {
                    $.each(pageResponse.categoryList, function(i, categoryList) {
                        let detailSrc = '${pageContext.request.contextPath}/manage/category/detail/' + categoryList.categoryID;
                        listHTML += '<tr>';
                        listHTML += '<td><input type="checkbox" name="categoryID" value="' + categoryList.categoryID + '"></td>';
                        listHTML += '<td>'+ categoryList.categoryID +'</td>';
                        listHTML += '<td><a href="'+ detailSrc +'">' + categoryList.name + '</a></td>';
                        listHTML += '<td>' + categoryList.code + '</td>';
                        listHTML += '</tr>';
                    });
                }
                listHTML += '</tbody>';

                $('#table').html(listHTML);
            }
        })
    }

    function remove() {
        $('input:checkbox[name=categoryID]').each(function (index) {
            if($(this).is(':checked')==true){
                let postObj = {
                    'categoryID':$(this).val()
                };

                $.ajax({
                    url:'${pageContext.request.contextPath}/manage/category/delete',
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
</script>
</body>
</html>
