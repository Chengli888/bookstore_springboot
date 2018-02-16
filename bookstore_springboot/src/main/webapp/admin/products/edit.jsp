<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath}/admin/css/Style.css"
	type="text/css" rel="stylesheet">
<script language="javascript"
	src="${pageContext.request.contextPath}/admin/js/public.js"></script>
<script language="javascript"
	src="${pageContext.request.contextPath}/admin/js/check.js"></script>

</HEAD>
<script type="text/javascript">
	//设置类别的默认值
	function setProductCategory(t) {
		var category = document.getElementById("category");//得到下拉列表

		var ops = category.options;//得到下拉列表中的所有option标签数组
		for ( var i = 0; i < ops.length; i++) {

			if (ops[i].value == t) {//判断哪一个option选项中的value值与t(当前书的类名称)相等
				ops[i].selected = true;//相等则把selected=selected加上
				return;
			}
		}

	};
//	function set1(t) {
//		var category = document.getElementById("category");
//		var ops = category.options;
//		for (var i = 0;i<ops.length;i++){
//		    if(t==ops[i]){
//		        ops[i].selected = true;
//		        return;
//			}
//		}
//    }
</script>
<body onload="setProductCategory('${product.category}')">
	<form id="userAction_save_do" name="Form1"
		action="${pageContext.request.contextPath }/updateproduct.do" method="post">
	
	<input type="hidden" name="id" value="${product.id }"/>
		<table cellSpacing="1" cellPadding="5" width="100%" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
					height="26"><strong><STRONG>Edit the product</STRONG> </strong></td>
			</tr>


			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">product name：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="name" class="bg" value="${product.name }" /></td>
				<td align="center" bgColor="#f5fafe" class="ta_01">Commodity prices：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="price" class="bg"   value="${product.price }"/></td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">Number of Products：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="pnum" class="bg"  value="${product.pnum }" /></td>
				<td align="center" bgColor="#f5fafe" class="ta_01">Product category：</td>
				<td class="ta_01" bgColor="#ffffff"><select name="category"
					id="category">
						<option value="">--Select the category--</option>
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
				<td align="center" bgColor="#f5fafe" class="ta_01">product picture：</td>
				<td class="ta_01" bgColor="#ffffff" colSpan="3"><input
					type="file" name="upload" size="30" value="" /></td>
			</tr>
			<TR>
				<TD class="ta_01" align="center" bgColor="#f5fafe">product description：</TD>
				<TD class="ta_01" bgColor="#ffffff" colSpan="3"><textarea
						name="description" cols="30" rows="3" style="WIDTH: 96%"> ${product.description }</textarea>
				</TD>
			</TR>
			<TR>
				<td align="center" colSpan="4" class="sep1"><img
					src="${pageContext.request.contextPath}/admin/images/shim.gif">
				</td>
			</TR>


			<tr>
				<td class="ta_01" style="WIDTH: 100%" align="center"
					bgColor="#f5fafe" colSpan="4"><input type="submit"
					class="button_ok" value="sure"> <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>



					<input type="reset" value="reset" class="button_cancel"> <FONT
					face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT> <INPUT
					class="button_ok" type="button" onclick="history.go(-1)" value="back" />
					<span id="Label1"> </span></td>
			</tr>
		</table>
	</form>




</body>
</HTML>