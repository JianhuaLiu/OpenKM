<%@ page language="java"  contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,com.openkm.frontend.client.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
Object s = (Object)session.getAttribute("docList");
List<GWTDocument> list = (List<GWTDocument>)s;
 %>


<table border="1">
		<tr>
			<td><a href="index.jsp"> <font color="#0000FF">BACK </font></td>
		</tr>
	</table>
	<center>
		<h2>File List</h2>
	</center>
<table border="1" align="center">
		<tr>
			<th>Name</th>
			<th>Date</th>
			<th>Download</th>
		</tr>
		<%
			for (GWTDocument item : list)
			{
		%>
		<tr bgcolor="lightblue">
			<td><%=item.getName()%></td>
			<td><%=item.getCreated()%></td>
			<td><a href="Download.DownloadFile?fileName=<%=item.getName()%>">Download</a></td>
		</tr>
		<%
			session.setAttribute(item.getName(), item);
			}
			
		%>
		
	</table>

</body>
</html>