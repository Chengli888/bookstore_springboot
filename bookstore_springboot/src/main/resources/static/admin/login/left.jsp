<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Menu</title>
<link href="${pageContext.request.contextPath}/admin/css/left.css" rel="stylesheet" type="text/css">
</head>
<body>
<table width="100" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="12"></td>
  </tr>
</table>
<table width="100%" border="0">
  <tr>
    <td>
<div class="dtree">

	<a href="javascript: d.openAll();">Expand all</a> | <a href="javascript: d.closeAll();">Close all</a>
	<link rel="StyleSheet" href="${pageContext.request.contextPath}/admin/css/dtree.css" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/dtree.js"></script>
	<script type="text/javascript">
		<!--
		d = new dTree('d');
		d.add(0,-1,'System menu tree');
		d.add(1,0,'Commodity management','${pageContext.request.contextPath}/welcome','','mainFrame');
		d.add(2,0,'Order Management','admin/login/welcome.jsp','','mainFrame');
		
		
		//子目录添加
		d.add(11,1,'Product view','${pageContext.request.contextPath}/booklistservlet','','mainFrame');
		
		d.add(12,1,'Sales list','${pageContext.request.contextPath}/admin/products/download.jsp','','mainFrame')
		
		d.add(21,2,'Order View','${pageContext.request.contextPath}/admin/orders/list.jsp','','mainFrame');
	
		
		document.write(d);
		//-->
	</script>
</div>	</td>
  </tr>
</table>
</body>
</html>
