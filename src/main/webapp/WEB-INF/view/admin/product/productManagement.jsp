<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file ="/WEB-INF/view/admin/layout/header.jsp" %>
  <div class="main-console ms-4 container">
    <div class="list-table-form table-responsive">
        <div class="h2 mt-3">상품 관리 페이지</div>
        <hr>
        <div class="top-menu text-end">
            <div class="d-flex flex-row justify-content-start mb-4">
                <input type="hidden" id="status" value="${adminPageListDto.status}">
                <select id="type" name="type" class="form-select w-auto me-2">
                    <option value="id" ${adminPageListDto.type eq "id" ? "selected":""}>상품아이디</option>
                    <option value="productName" ${adminPageListDto.type eq "productName" ? "selected":""}>상품명</option>
                </select>
                <input type="text" class="form-control w-25 me-2" id="keyword" placeholder="검색" value="${adminPageListDto.keyword}">
                <input type="button" class="btn btn-outline-secondary me-auto" id="search-btn" value="검색하기" onclick="search()">
                <input type="button" class="insertButton btn btn-outline-secondary" onclick="location.href='/admin/product'" value="상품 추가">
            </div>
                
        </div>

        
        <table class="list-table table text-center align-middle table-bordered">
            <thead>
                <tr class="table-light">
                    <th>상품아이디</th>
                    <th>상품명</th>
                    <th>가격</th>
                    <th>수량</th>
                    <th>상세보기 및 수정</th>
                    <th>삭제</th>
                </tr>
            </thead>
            <c:forEach var="product" items="${adminPageListDto.list}">
                <tbody>
                    <tr>
                        <td>${product.id}</td>
                        <td>${product.productName}</td>
                        <td>${product.price}</td>
                        <td>${product.quantity}</td>
                        <td> <input type="button" class="btn btn-outline-secondary" onclick="location.href='/admin/product/${product.id}'" value="수정 및 상세보기"></td>
                        <td> 
                            <form action="/deleteProduct/${product.id}" method="get">
                                <input type="button" class="btn btn-outline-secondary" value="삭제" onclick="isDelete(this.form)">
                            </form>
                        </td>
                    </tr>
                </tbody>
            </c:forEach>
        </table>
        <div class="d-flex justify-content-center">
            <ul class="pagination">
                    <c:choose>
                        <c:when test="${adminPageListDto.first}">
                        </c:when>
                        <c:otherwise>	
                <li class='page-item'>
                            <a class="page-link" href="?page=${adminPageListDto.currentPage-1}${empty adminPageListDto.keyword ? "": "&keyword="+= adminPageListDto.keyword}${empty adminPageListDto.type ? "": "&type="+= adminPageListDto.type}${empty adminPageListDto.status ? "": "&status="+= adminPageListDto.status}">Prev</a>
                </li>
                        </c:otherwise> 
                    </c:choose> 
                <c:forEach var ="num" begin = "${adminPageListDto.startPageNum}" end="${adminPageListDto.lastPageNum}">
                    <li class="page-item  ${page eq num ? 'active' : ''}"><a class="page-link" href="?page=${num}${empty adminPageListDto.keyword ? "": "&keyword="+= adminPageListDto.keyword}${empty adminPageListDto.type ? "": "&type="+= adminPageListDto.type}${empty adminPageListDto.status ? "": "&status="+= adminPageListDto.status}">${num}</a></li>
                </c:forEach>
                    <c:choose>
                        <c:when test="${adminPageListDto.last}">
                        </c:when>
                        <c:otherwise>	
                <li class='page-item'>
                            <a class="page-link" href="?page=${adminPageListDto.currentPage+1}${empty adminPageListDto.keyword ? "": "&keyword="+= adminPageListDto.keyword}${empty adminPageListDto.type ? "": "&type="+= adminPageListDto.type}${empty adminPageListDto.status ? "": "&status="+= adminPageListDto.status}">Next</a>
                </li>
                        </c:otherwise> 
                    </c:choose>
            </ul>
        </div>
    </div>			
</div>
<%@ include file ="/WEB-INF/view/admin/layout/footer.jsp" %>


