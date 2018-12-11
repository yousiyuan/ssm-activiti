<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="func"%>

<c:set var="root" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品列表</title>
</head>
<body>
	<div>
		<form action="${root}/manage/list.do" method="post">
			<table cellpadding="0" cellspacing="0" border="0">
				<tbody>
					<tr>
						<th>产品名称</th>
						<td><input type="text" name="productname"
							value="${condition.productname}" /></td>
						<th>供应商</th>
						<td><input type="text" name="supplierid"
							value="${condition.supplierid}" /></td>
						<th>产品类别</th>
						<td><input type="text" name="categoryid"
							value="${condition.categoryid}" /></td>
					</tr>
					<tr>
						<th></th>
						<td></td>
						<th></th>
						<td></td>
						<th></th>
						<td><input type="submit" value="查询" /></td>
					</tr>
					<tr>
						<td colspan="6">
							<a href="${root}/manage/edit.do" target="_self">新增</a></td>
					</tr>
				</tbody>
			</table>
		</form>
		<div id="productlist">
			<table cellpadding="0" cellspacing="0" border="0">
				<thead>
					<tr><td colspan="7">共${totalCount}条结果</td></tr>
					<tr>
						<th>产品编号</th>
						<th>产品名称</th>
						<th>供应商</th>
						<th>产品类别</th>
						<th>单价</th>
						<th>库存</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ResultSet}" var="item">
						<tr>
							<td>${item.productid}</td>
							<td>${item.productname}</td>
							<td>${item.supplierid}</td>
							<td>${item.categoryid}</td>
							<td>${item.unitprice}</td>
							<td>${item.unitsinstock}</td>
							<td><a
								href="${root}/manage/edit.do?productid=${item.productid}">修改</a><a
								href="${root}/manage/delete.do?id=${item.productid}"
								target="_self">删除</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>