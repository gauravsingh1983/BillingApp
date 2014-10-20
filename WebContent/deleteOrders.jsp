<%-- 
    Document   : deleteOrders
    Created on : Oct 30, 2009, 8:31:27 PM
    Author     : Gaurav
--%>

<%@page contentType="text/html" import="java.sql.Connection, test.*" pageEncoding="UTF-8"%>
<%
String userid = (String)session.getAttribute("userid");
Connection con = (Connection)session.getAttribute("con");
Service scv = new Service();
scv.theConnection = con;

if(scv.deleteOrder(userid)>1){
out.println("SUCCESSFUL!! Please close this window");
}else{
   out.println("There are no item for such operation!!"); 
}
%>
<body onload="window.close();">
<div><input type="button" value="close" onclick="javascript:window.close()"></div>
</body>