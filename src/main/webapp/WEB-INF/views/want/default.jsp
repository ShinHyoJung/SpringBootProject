<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2023-01-23
  Time: 오후 7:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body onload="printList();">
<p class="subtitle">찜한 내역</p>
<div class="ui items" id="list">
</div>
<div class="ui pagination menu" id="pagination" style="display:none; margin-top: 20px; margin-left: 300px;">
</div>
<script>
    function printList(currentPage) {
        if(!currentPage) {
            currentPage = 1;
        }

        let postObj = {
            'currentPage':currentPage
        }

        $.ajax({
            url: '${pageContext.request.contextPath}/want/list',
            method: 'post',
            dataType: 'json',
            data: JSON.stringify(postObj),
            contentType: 'application/json; charset=utf-8',
            success: function(pageResponse) {
                console.log(pageResponse);
                let pageHTML = '';
                let listHTML = '';

                if(pageResponse.code == 'SUCCESS') {
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

                    $.each(pageResponse.wantList, function(i, wantList) {
                        let imgSrc = '${pageContext.request.contextPath}/static/images/cut/' + wantList.thumbnailImageName;
                        listHTML += '<div class="item">';
                        listHTML += '<div class="image">';
                        listHTML += '<img src="' + imgSrc + '"/>';
                        listHTML += '</div>';
                        listHTML += '<div class="content">';
                        listHTML += '<a class="header" href="${pageContext.request.contextPath}/sell/detail/'+ wantList.sellID +'">' + wantList.name + '</a>';
                        listHTML += '<div class="description">';
                        listHTML += '<p>' + wantList.price + '원</p>';
                        listHTML += '<button class="ui button" type="button" id="' + wantList.sellID + '" onclick="remove(this.id);" value="' + wantList.wantID + '">삭제</button>';
                    });

                    $('#pagination').css('display', '');
                    $('#list').html(listHTML);
                    $('#pagination').html(pageHTML);
                } else {
                    alert(pageResponse.message);
                }
            }
        });
    }

    function remove(sellID) {
        let postObj = {
            'sellID':sellID
        }

        $.ajax({
            url:'${pageContext.request.contextPath}/want/delete',
            method: 'post',
            dataType: 'json',
            data: JSON.stringify(postObj),
            contentType: 'application/json; charset=utf-8',
            success: function(pageResponse) {
                alert(pageResponse.message);
                location.reload();
            }
        })
    }
</script>
</body>
</html>
