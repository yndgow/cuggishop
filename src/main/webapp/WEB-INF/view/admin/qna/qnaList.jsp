<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file ="/WEB-INF/view/admin/layout/header.jsp" %>
				<div class="list-table-form">
					<h2>문의사항 관리 페이지</h2>
					<div class="top-menu">
						<div>
					    <input type="hidden" id="status" value="${adminPageListDto.status}">
						<select id="type" name="type" >
						    <option value="name" ${adminPageListDto.type eq "name" ? "selected":""}>이름</option>
						</select>
						<input type="text" id="keyword" placeholder="검색" value="${adminPageListDto.keyword}">
						<input type="button" id="search-btn" value="검색하기" onclick="search()">
						</div>
					</div>
					<table class = "list-table">
					    <thead>
							<tr>
								<th>번호</th>
								<th>이름</th>
								<th>제목</th>
								<th>답변처리</th>
								<th>작성일</th>
							</tr>
						</thead>
					    <tbody>
					    	<c:choose>
					    		<c:when test="${adminPageListDto != null}">
					    			<c:forEach var="qna" items="${adminPageListDto.list}">
								        <tr>
								            <td>${qna.id}</td>
								            <td>${qna.name}</td>
								            <td><a href="/admin/qnaDetail/${qna.id}">${qna.title}</a></td>
								            <td>${qna.state}</td>
								            <td>${qna.createdAt}</td>
								        </tr>
							        </c:forEach>
						        </c:when>
					        </c:choose>
					    </tbody>
					</table>
					<a href="?page=1&type=${adminPageListDto.type}&keyword=${adminPageListDto.keyword}">전체보기</a>
					<a href="?page=1&type=${adminPageListDto.type}&keyword=${adminPageListDto.keyword}&status=확인중">확인중</a>
					<a href="?page=1&type=${adminPageListDto.type}&keyword=${adminPageListDto.keyword}&status=답변 완료">답변완료</a>
					<div class="d-flex justify-content-center">
						<ul class="pagination">
							<li class='page-item'>
								<c:choose>
									<c:when test="${adminPageListDto.first}">
										<label>Prev</label>
									</c:when>
									<c:otherwise>	
										<a class="page-link" href="?page=${adminPageListDto.currentPage-1}${empty adminPageListDto.keyword ? "": "&keyword="+= adminPageListDto.keyword}${empty adminPageListDto.type ? "": "&type="+= adminPageListDto.type}${empty adminPageListDto.status ? "": "&status="+= adminPageListDto.status}">Prev</a>
									</c:otherwise> 
								</c:choose> 
							</li>
							<c:forEach var ="num" begin = "${adminPageListDto.startPageNum}" end="${adminPageListDto.lastPageNum}">
								<li class='page-item'><a class='page-link' href="?page=${num}${empty adminPageListDto.keyword ? "": "&keyword="+= adminPageListDto.keyword}${empty adminPageListDto.type ? "": "&type="+= adminPageListDto.type}${empty adminPageListDto.status ? "": "&status="+= adminPageListDto.status}">${num}</a></li>
							</c:forEach>
							<li class='page-item'>
								<c:choose>
									<c:when test="${adminPageListDto.last}">
										<label>Next</label>
									</c:when>
									<c:otherwise>	
										<a class="page-link" href="?page=${adminPageListDto.currentPage+1}${empty adminPageListDto.keyword ? "": "&keyword="+= adminPageListDto.keyword}${empty adminPageListDto.type ? "": "&type="+= adminPageListDto.type}${empty adminPageListDto.status ? "": "&status="+= adminPageListDto.status}">Next</a>
									</c:otherwise> 
								</c:choose>
							</li>
						</ul>
					</div>
				</div>			
<script type="text/javascript">
//delete 확인 자바스크립트
function isDelete(form){
	const response = confirm("정말 삭제하시겠습니까?");
	if(response){
		form.submit();
	}
}

function search(){
	let type=$("#type").val();
	let keyword=$("#keyword").val();
	let status=$("#status").val();
	
//	if(status){
//		location.href="?page=1&type="+type+"&keyword="+keyword+"&status="+status;
//	}else{
		location.href="?page=1&type="+type+"&keyword="+keyword;
//	}
}
</script>
<%@ include file ="/WEB-INF/view/admin/layout/footer.jsp" %> --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file ="/WEB-INF/view/admin/layout/header.jsp" %>
  <div class="main-console ms-4 container">
    <div class="list-table-form table-responsive">
        <div class="h2 mt-3">회원정보 관리 페이지</div>
        <hr>
        <div class="top-menu text-end">
            <div class="d-flex flex-row justify-content-start mb-4">
                <input type="hidden" id="status" value="${adminPageListDto.status}">
                <select id="type" name="type" class="form-select w-auto me-2">
                    <option value="name" ${adminPageListDto.type eq "name" ? "selected":""}>이름</option>
                </select>
                <input type="text" class="form-control w-25 me-2" id="keyword" placeholder="검색" value="${adminPageListDto.keyword}">
                <input type="button" class="btn btn-outline-secondary me-auto" id="search-btn" value="검색하기" onclick="search()">
            </div>
        </div>
        
        <table class="list-table table text-center align-middle table-bordered">
            <thead>
                <tr class="table-light">
                    <th>번호</th>
					<th>이름</th>
					<th>제목</th>
					<th>답변처리</th>
					<th>작성일</th>
                </tr>
            </thead>
                <tbody>
		            <c:forEach var="qna" items="${adminPageListDto.list}">
	                    <tr>
	                        <td>${qna.id}</td>
				            <td>${qna.name}</td>
				            <td><a href="/admin/qnaDetail/${qna.id}">${qna.title}</a></td>
				            <td>${qna.state}</td>
				            <td>${qna.createdAt}</td>
	                    </tr>
        		    </c:forEach>
                </tbody>
        </table>
        <a href="?page=1&type=${adminPageListDto.type}&keyword=${adminPageListDto.keyword}">전체보기</a>
		<a href="?page=1&type=${adminPageListDto.type}&keyword=${adminPageListDto.keyword}&status=확인중">확인중</a>
		<a href="?page=1&type=${adminPageListDto.type}&keyword=${adminPageListDto.keyword}&status=답변 완료">답변완료</a>
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