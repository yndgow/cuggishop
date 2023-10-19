<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>NICEPAY CANCEL REQUEST(UTF-8)</title>
<meta charset="utf-8">
<style>
	html,body {height: 100%;}
	form {overflow: hidden;}
</style>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
$("#cancelForm").ready(()=>{
		$("#cancelForm").submit();
});
</script>
</head>
<body> 

<form id="cancelForm" name="cancelForm" method="post" target="_self" action="/admin/cancelPaymentResult/${orderListResponseDto.id}">
	<table>
		<tr>
			<th>원거래 ID</th>
			<td><input type="text" name="TID" readolny value="${paymentResponseDto.tid}" /></td>
		</tr>
		<tr>
			<th>취소 금액</th>
			<td><input type="text" name="CancelAmt" value="${orderListResponseDto.price}" /></td>
		</tr>
		<tr>
			<th>부분취소 여부</th>
			<td>
				<input type="radio" name="PartialCancelCode" value="0" checked="checked"/> 전체취소
				<input type="radio" name="PartialCancelCode" value="1"/> 부분취소
			</td>
		</tr>
	</table>
	<a href="#" onClick="reqCancel();">요 청</a>				
</form>	

</body>

</html>
