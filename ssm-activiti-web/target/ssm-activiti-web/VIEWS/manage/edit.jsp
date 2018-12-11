<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="func"%>

<c:set var="root" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品编辑</title>
</head>
<body>
	<form action="${root}/manage/save.do" method="post">
		<table cellpadding="0" cellspacing="0" border="0">
			<tbody>
				<tr>
					<th>商品编号</th>
					<td>
						<label>${item.productid}</label>
						<input type="hidden" name="productid" value="${item.productid}" /></td>
				</tr>
				<tr>
					<th>商品名称</th>
					<td><input type="text" name="productname" value="${item.productname}" /></td>
				</tr>
				<tr>
					<th>供应商</th>
					<td><input type="text" name="supplierid" value="${item.supplierid}" /></td>
				</tr>
				<tr>
					<th>商品类别</th>
					<td><input type="text" name="categoryid" value="${item.categoryid}" /></td>
				</tr>
				<tr>
					<th>数量单位</th>
					<td><input type="text" name="quantityperunit" value="${item.quantityperunit}" /></td>
				</tr>
				<tr>
					<th>单价</th>
					<td><input type="text" name="unitprice" value="${item.unitprice}" /></td>
				</tr>
				<tr>
					<th>单一库存</th>
					<td><input type="text" name="unitsinstock" value="${item.unitsinstock}" /></td>
				</tr>
				<tr>
					<th>单一订单</th>
					<td><input type="text" name="unitsonorder" value="${item.unitsonorder}" /></td>
				</tr>
				<tr>
					<th>商品等级</th>
					<td><input type="text" name="reorderlevel" value="${item.reorderlevel}" /></td>
				</tr>
				<tr>
					<th>是否终止供应</th>
					<td><input type="text" name="discontinued" value="${item.discontinued}" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="保存" /></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>