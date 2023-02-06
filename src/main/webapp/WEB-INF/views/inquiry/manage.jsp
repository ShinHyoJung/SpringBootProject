<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2023-02-06
  Time: 오후 9:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body onload="printList();">
<p class="subtitle">상담 문의 관리</p>
<table class="ui basic table" id="table" style="width: 70%;">
</table>
<div class="ui pagination menu" id="pagination" style="margin-top: 300px; margin-left: 300px;">
<script>
    function printList(currentPage) {
        if(!currentPage) {
            currentPage = 1;
        }

        let postObj = {
            'currentPage':currentPage
        };

        $.ajax({
            url: '${pageContext.request.contextPath}/inquiry/manage/list',
            method: 'post',
            dataType: 'json',
            data: JSON.stringify(postObj),
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
                        pageHTML += '<a class="active item" onclick="printList(' + i + ')">' + i + '</a>';
                    } else {
                        pageHTML += '<a class="item" onclick="printList(' + i + ')">' + i + '</a>';
                    }
                }

                if(pageResponse.paging.next) {
                    pageHTML += '<a class="item"> > </a>';
                }

                if(pageResponse.inquiryList) {
                    listHTML += '<thead>';
                    listHTML += '<tr>';
                    listHTML += '<th>글 번호</th>';
                    listHTML += '<th> 제목 </th>';
                    listHTML += '<th> 작성자 </th>';
                    listHTML += '<th> 작성날짜 </th>';
                    listHTML += '<th> 수정날짜 </th>';
                    listHTML += '</tr>';
                    listHTML += '</thead>';
                }

                $.each(pageResponse.inquiryList, function (i, inquiryList) {
                    let inquiryUrl = '${pageContext.request.contextPath}/inquiry/read/' + inquiryList.inquiryID;
                    listHTML += '<tbody>';
                    listHTML += '<tr>';
                    listHTML += '<td>'+ inquiryList.inquiryID +'</td>';
                    listHTML += '<td><a href="'+ inquiryUrl + '">' + inquiryList.title + '</a></td>';
                    listHTML += '<td>' + inquiryList.writer + '</td>';
                    listHTML += '<td>' + inquiryList.createDate + '</td>';
                    listHTML += '<td>' + inquiryList.updateDate + '</td>';
                    listHTML += '</tbody>';
                });

                $('#pagination').html(pageHTML);
                $('#table').html(listHTML);
            }
        });
    }
</script>
</body>
</html>
