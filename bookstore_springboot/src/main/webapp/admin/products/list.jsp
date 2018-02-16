<%@ page import="java.util.List" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/admin/css/Style.css"
	rel="stylesheet" type="text/css" />
<script language="javascript"
	src="${pageContext.request.contextPath}/admin/js/public.js"></script>
<script type="text/javascript">
	function addProduct() {
		window.location.href = "${pageContext.request.contextPath}/admin/products/add.jsp";
	}
	function delBook(id) {
		if(confirm("Are you sure to delete it")){
		    location.href = "${pageContext.request.contextPath}/deleteproduct.do?id="+id;
		}

    }
    function checkall(){
       var flag = document.getElementById("ckAll").checked;
       var ids = document.getElementsByName("ids");
       for(var i=0;i<=ids.length;i++){
           ids[i].checked = flag;
		}

    }
    function delAllproducts() {
		var ids = document.getElementsByName("ids");
		var str = "";
		for(var i=0;i<ids.length;i++){
		    if(ids[i].checked){
		        str+="ids="+ids[i].value+"&"
			}
		}
		str = str.substring(0,str.length-1);
		location.href = "${pageContext.request.contextPath}/deleteproducts.do?"+str;
    }
</script>
</HEAD>
<body>
	<br>
	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/searchProducts.do"
		method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>check
						Inquiry conditions</strong>
					</td>
				</tr>
				<tr>
					<td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									Product Number</td>
								<td class="ta_01" bgColor="#ffffff"><input type="text"
									name="id" size="15" value="" id="Form1_userName" class="bg" />
								</td>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									category：</td>
								<td class="ta_01" bgColor="#ffffff"><select name="category"
									id="category">
										<option value="" selected="selected">--Select the category--</option>
									<option value="literature">literature</option>
									<option value="life">life</option>
									<option value="computer">computer</option>
									<option value="English">English</option>
									<option value="management">management</option>
									<option value="motivational">motivational</option>
									<option value="society">society</option>
									<option value="academic">academic</option>
									<option value="children">children</option>
									<option value="art">art</option>
									<option value="original edition">original edition</option>
									<option value="science">scinence</option>
									<option value="examination">examination</option>
									<option value="home life">home life</option>
								</select></td>
							</tr>

							<tr>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									product name：</td>
								<td class="ta_01" bgColor="#ffffff"><input type="text"
									name="name" size="15" value="" id="Form1_userName" class="bg" />
								</td>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									Price range ($)：</td>
								<td class="ta_01" bgColor="#ffffff"><input type="text"
									name="minprice" size="10" value="" />- <input type="text"
									name="maxprice" size="10" value="" /></td>
							</tr>

							<tr>
								<td width="100" height="22" align="center" bgColor="#f5fafe"
									class="ta_01"></td>
								<td class="ta_01" bgColor="#ffffff"><font face="宋体"
									color="red"> &nbsp;</font>
								</td>
								<td align="right" bgColor="#ffffff" class="ta_01"><br>
									<br></td>
								<td align="right" bgColor="#ffffff" class="ta_01">
									<button type="submit" id="search" name="search"
										value="start search" class="button_view">
										start search</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
									type="reset" name="reset" value="reset"
									class="button_view" />
								</td>
							</tr>
						</table>
					</td>

				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>Product List</strong>
					</TD>
				</tr>
				<tr>
					<td class="ta_01" align="right">
						<button type="button" id="" name="add" value="deleteAll"
								class="button_add" onclick="delAllproducts()">deleteAll
						</button>
						<button type="button" id="add" name="add" value="add product"
							class="button_add" onclick="addProduct()">add product
						</button>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								<td align="center" width="10%"><input type="checkbox" id="ckAll" onclick="checkall()"/>全选／全不选</td>
								<td align="center" width="24%">Product Number</td>
								<td align="center" width="18%">product name</td>
								<td align="center" width="9%">Commodity prices</td>
								<td align="center" width="9%">Number of Products</td>
								<td width="8%" align="center">Product category</td>
								<td width="8%" align="center">edit</td>
								<td width="8%" align="center">delete</td>
							</tr>

			<c:forEach items="${products}" var="p">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10"><input type="checkbox" name="ids" value="${p.id}"/></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="23">${p.id}</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="18%">${p.name}</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%">${p.price}</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%">${p.pnum}</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">${p.category}</td>
									<td align="center" style="HEIGHT: 22px" width="7%"><a
										href="${pageContext.request.contextPath}/edit.do?id=${p.id }">
											<img
											src="${pageContext.request.contextPath}/admin/images/i_edit.gif"
											border="0" style="CURSOR: hand"> </a>
									</td>

									<td align="center" style="HEIGHT: 22px" width="7%"><a
										href="javascript:delBook('${p.id}')">
											<img
											src="${pageContext.request.contextPath}/admin/images/i_del.gif"
											width="16" height="16" border="0" style="CURSOR: hand">
									</a>
									</td>
								</tr>
					</c:forEach>	
						</table>
					</td>
				</tr>
			</TBODY>
		</table>
	</form>
</body>
</HTML>

