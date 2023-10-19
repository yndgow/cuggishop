<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file ="/WEB-INF/view/admin/layout/header.jsp" %>

				<form class="one-table-form" method="post" action="insertOfflineStore">
					<h2>지점 등록 페이지</h2>
					<table class = "one-table">
					    <tbody>
					        <tr>
					            <td>지점 이름</td>
					            <td><input type="text" id="store-name" name="storeName" placeholder="지점 이름을 입력하세요."></td>
					        </tr>
					        <tr>
					            <td>지점 번호</td>
					            <td><input type="text" id="store-number" name="storeNumber" placeholder="지점 번호를 입력하세요."></td>
					        </tr>
					        <tr>
					            <td>지점 주소</td>
					            <td><input type="text" id="store-address" name="storeAddress" placeholder="지점 주소를 입력하세요." readonly> <input type="button" onclick="openZipSearch()" value="주소입력" ></td>
					        </tr>
					        <tr>
					            <td>지점 상세주소</td>
					            <td><input type="text" id="store-address-detail" name="storeAddressDetail" placeholder="지점 상세주소를 입력하세요."></td>
					        </tr>
					    </tbody>
					</table>
					<input type="submit" value="지점 등록">
				</form>
		


<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>	
<script>
function openZipSearch() {
    new daum.Postcode({
    	oncomplete: function(data) {     
		var addr = ''; 
		if (data.userSelectedType === 'R') { 
			addr = data.roadAddress;
		} else {
			addr = data.jibunAddress;
		}

		//$("#zip_code").val(data.zonecode);
		$("#store-address").val(addr);
		$("#store-address-detail").val("");
		$("#store-address-detail").focus();
        }
    }).open();
}
</script>
<%@ include file ="/WEB-INF/view/admin/layout/footer.jsp" %>