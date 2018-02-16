<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>电子书城</title>
<link rel="stylesheet" href="css/main.css" type="text/css" />
</head>


<body class="main">
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />

	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>

				<td><div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="index.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;;订单详细信息
					</div>



					<table cellspacing="0" class="infocontent">
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0">
									<tr>
										<td>

									</tr>
									<tr>
										<td>
											<table cellspacing="1" class="carttable">
												<tr>
													<td width="10%">Number</td>
													<td width="40%">Product Name</td>
													<td width="10%">Price</td>
													<td width="10%">Quality</td>
													<td width="10%">each price</td>

												</tr>
											</table>
											<c:set var="sum" value="0" > </c:set>
											<c:forEach items="${order.orderItems}" var="o" varStatus="vs">
											<table width="100%" border="0" cellspacing="0">
												<tr>
													<td width="10%">${vs.count}</td>
													<td width="40%">${o.p.name}</td>
													<td width="10%">${o.p.price}</td>
													<td width="10%">${o.buynum}</td>
													<td width="10%">${o.buynum*o.p.price}</td>

												</tr>
											</table>
												<c:set var="sum" value="${sum+o.buynum*o.p.price }"> </c:set>
											</c:forEach>
											<table cellspacing="1" class="carttable">
												<tr>
													<td style="text-align:right; padding-right:40px;"><font
														style="color:#FF0000">Total：&nbsp;&nbsp;${sum}&nbsp;&nbsp;dollars</font></td>
												</tr>
											</table>

											<p>
												Address：${order.receiverAddress }&nbsp;&nbsp;&nbsp;&nbsp;<br />
												Reciever：&nbsp;&nbsp;&nbsp;&nbsp;${order.receiverName }&nbsp;&nbsp;&nbsp;&nbsp;<br />
												Telephone：${order.receiverPhone }&nbsp;&nbsp;&nbsp;&nbsp;

											</p>
											<hr>
											<p style="text-align:right">
												<a href="pay.jsp"><img src="images/gif53_029.gif" width="204"
                                                                       height="51" border="0" /> </a>
											</p>
										</td>
									</tr>
								</table>
							</td>


						</tr>


					</table>
				</td>
			</tr>
		</table>
	</div>



	<jsp:include page="foot.jsp" />


</body>
</html>
