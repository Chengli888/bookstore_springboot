<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


	<title>bookStore列表</title>
	<%--导入css --%>
	<link rel="stylesheet" href="css/main.css" type="text/css" />
</head>

<body class="main">

<jsp:include page="head.jsp" />
<jsp:include page="menu_search.jsp" />

<div id="divpagecontent">
	<table width="100%" border="0" cellspacing="0">
		<tr>

			<td>
				<div style="text-align:right; margin:5px 10px 5px 0px">
					<a href="index">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;计算机&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;图书列表
				</div>

				<%--<table cellspacing="0" class="listcontent">--%>
				<%--<tr>--%>
				<%--<td>--%>
				<%--<h1>商品目录</h1>--%>
				<%--<hr />--%>
				<%--<h1>计算机</h1>&nbsp;&nbsp;&nbsp;&nbsp;共100种商品--%>
				<%--<hr />--%>
				<%--<div style="margin-top:20px; margin-bottom:5px">--%>
				<%--<img src="images/productlist.gif" width="100%" height="38" />--%>
				<%--</div>--%>
				<%--<table cellspacing="0" class="booklist">--%>
				<%--<tr>--%>
				<%--<td>--%>

				<%--<div class="divbookpic">--%>
				<%--<p>--%>
				<%--<a href="product_info.jsp"><img src="bookcover/101.jpg" width="115"--%>
				<%--height="129" border="0" /> </a>--%>
				<%--</p>--%>
				<%--</div>--%>

				<%--<div class="divlisttitle">--%>
				<%--<a href="product_info.jsp">书名:xxx<br />售价:xxx </a>--%>
				<%--</div></td>--%>
				<%--<td>--%>

				<%--<div class="divbookpic">--%>
				<%--<p>--%>
				<%--<a href="product_info.jsp"><img src="bookcover/102.jpg" width="115"--%>
				<%--height="129" border="0" /> </a>--%>
				<%--</p>--%>
				<%--</div>--%>

				<%--<div class="divlisttitle">--%>
				<%--<a href="product_info.jsp">书名:xxx<br />售价:xxx </a>--%>
				<%--</div></td>--%>
				<%--<td>--%>

				<%--<div class="divbookpic">--%>
				<%--<p>--%>
				<%--<a href="product_info.jsp"><img src="bookcover/103.jpg" width="115"--%>
				<%--height="129" border="0" /> </a>--%>
				<%--</p>--%>
				<%--</div>--%>

				<%--<div class="divlisttitle">--%>
				<%--<a href="product_info.jsp">书名:xxx<br />售价:xxx </a>--%>
				<%--</div></td>--%>
				<%--<td>--%>

				<%--<div class="divbookpic">--%>
				<%--<p>--%>
				<%--<a href="product_info.jsp"><img src="bookcover/104.jpg" width="115"--%>
				<%--height="129" border="0" /> </a>--%>
				<%--</p>--%>
				<%--</div>--%>

				<%--<div class="divlisttitle">--%>
				<%--<a href="product_info.jsp">书名:xxx<br />售价:xxx </a>--%>
				<%--</div></td>--%>


				<%--</tr>--%>
				<%--</table>--%>

				<table cellspacing="0" class="listcontent">
					<tr>
						<td>
							<h1>catalog</h1>
							<hr />
							<h1>计算机</h1>&nbsp;&nbsp;&nbsp;&nbsp;共100种商品
							<hr />
							<div style="margin-top:20px; margin-bottom:5px">
								<img src="${pageContext.request.contextPath }/images/productlist.gif" width="100%" height="38" />
							</div>

							<table cellspacing="0" class="booklist">
								<tr>
									<c:forEach items="${pd }" var="b">
										<td>

											<div class="divbookpic">
												<p>
													<a href="#"><img src="" width="115" height="129"
																	 border="0" /> </a>
												</p>
											</div>

											<div class="divlisttitle">
												<a href="${pageContext.request.contextPath }/findProductInfo.do?id=${b.id}">bookname:${b.name }<br />money:${b.price } </a>
											</div>
										</td>
									</c:forEach>
								</tr>
							</table>
















							<div class="pagination">
								<ul>


									<li class="disablepage"><a href="${pageContext.request.contextPath  }/pagelist.do?currentPage=${currentPage==1?1:currentPage-1}">&lt;&lt;上一页</a></li>


									<li>this is ${currentPage }page/total${totalPage==0?1:totalPage}pages</li>

									<li class="nextPage"><a href="${pageContext.request.contextPath  }/pagelist.do?currentPage=${currentPage==totalPage?totalPage:currentPage+1}">&lt;&lt;下一页</a></li>


								</ul>
							</div></td>
						</td>
					</tr>
				</table></td>
		</tr>
	</table>
</div>



<jsp:include page="foot.jsp" />


</body>
</html>
