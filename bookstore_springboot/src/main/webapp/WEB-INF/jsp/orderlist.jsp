<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
				<td width="25%"><table width="100%" border="0" cellspacing="0"
						style="margin-top:30px">
						<tr>
							<td class="listtitle">my account</td>
						</tr>
						<tr>
							<td class="listtd"><img src="images/miniicon.gif" width="9"
								height="6" />&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="${pageContext.request.contextPath }/ModifyUserInfo">User information modification</a>
							</td>
						</tr>


						<tr>
							<td class="listtd"><img src="images/miniicon.gif" width="9"
								height="6" />&nbsp;&nbsp;&nbsp;&nbsp; <a href="${pageContext.request.contextPath }/logout.do">Logout</a></td>
						</tr>
















					</table>
				</td>
				<td><div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="${pageContext.request.contextPath }/index">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;<a
							href="${pageContext.request.contextPath }/myAccount.do">&nbsp;My Account</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;Order Tracking
					</div>





					<table cellspacing="0" class="infocontent">
						<tr>
							<td style="padding:20px"><p>welcome bookstore！</p>

								<table width="100%" border="0" cellspacing="0" class="tableopen">
									<tr>
										<td bgcolor="#A3E6DF" class="tableopentd01">order number</td>
										<td bgcolor="#A3D7E6" class="tableopentd01">Recipients</td>
										<td bgcolor="#A3CCE6" class="tableopentd01">Order time</td>
										<td bgcolor="#A3B6E6" class="tableopentd01">status</td>
										<td bgcolor="#A3E2E6" class="tableopentd01">operating</td>
									</tr>


									<c:forEach items="${orders}" var="order" varStatus="vs">
									
									<tr>
										<td class="tableopentd02">${vs.count}</td>

										<td class="tableopentd02">${order.receiverName}</td>
										<td class="tableopentd02">${order.ordertime}</td>
										<td class="tableopentd02">${order.paystate==0?no_pay:have_pay}</td>
										<td class="tableopentd03"><a href="${pageContext.request.contextPath }/findOrderItemsByOrderId?orderid=${order.id}">view</a>&nbsp;&nbsp;
											<a href="${pageContext.request.contextPath }/deleteOrderItemsByOrderId?orderid=${order.id}">delete</a>
										</td>
									</tr>
									</c:forEach>
								</table>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>



	<div id="divfoot">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td rowspan="2" style="width:10%"><img
					src="images/bottomlogo.gif" width="195" height="50"
					style="margin-left:175px" />
				</td>
				<td style="padding-top:5px; padding-left:50px"><a href="#"><font
						color="#747556"><b>CONTACT US</b> </font> </a>
				</td>
			</tr>
			<tr>
				<td style="padding-left:50px"><font color="#CCCCCC"><b>COPYRIGHT
							2017 &copy; eShop All Rights RESERVED.</b> </font>
				</td>
			</tr>
		</table>
	</div>


</body>
</html>
