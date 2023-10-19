

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<style>
#order-list-title {
text-align: center
}
	table {
        
        border-collapse: collapse;
        text-align: center;
      }
    #list-thead {
    height: 70px;
    }
      
    td {
    	border-top: 1px solid #444444;
     	padding: 10px;
  	}
  	
  	
 	th:first-child{
 	border-top:first-child
 	}
  	
    th:first-child, td:first-child {
    
    	border-left: none;
    	
  	}
</style>
<main>
	<div class="main-column">
	<form action="/order/orderList">
	 <h2 id = "order-list-title">Order</h2>
	 
		<c:choose>
			<c:when test="${orderList != null}">
				<table >
					<thead id = "list-thead">
						<tr  class="thead-dark">
							<th>주문날짜</th>
							<th>이미지</th>
							<th>상품명</th>
							<th>금액</th>
							<th>진행상태</th>
							<th>결제내역</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="order" items="${orderList}">
							<tr>
								<td><a >${order.createdAt}</a>
								</td>								
								<td><a ><img src="${order.image}"></a>
								</td>
								<td><a >${order.productName}</a>
								</td>
								<td><a >${order.price}</a>
								</td>
								<td><a >${order.state}</a>
								</td>
								<td><a href="/order/orderDetail/${order.id}" >상세보기</a>
								</td>						
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			
		</c:choose>
		</form>
	</div>
</main>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
