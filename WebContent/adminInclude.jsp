<%-- 
    Document   : adminInclude
    Created on : Oct 11, 2009, 10:39:35 AM
    Author     : Gaurav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
  <p>
 <input type="button" name="print" value="today report" size="10" onclick="window.open('reports.jsp','location','top=150,left=230,toolbars=no,maximize=yes,resize=yes,width=700,height=450,location=no,scrollbars=yes');"/>  
<input type="button" name="additm" value="add items"  size="10" onclick="window.open('Insertitemdetails','location','top=150,left=230,toolbars=no,maximize=yes,resize=yes,width=700,height=450,location=no,scrollbars=yes');"/>
<input type="button" name="delordr" value="test"  size="10" onclick="window.open('deleteOrders.jsp','location','top=150,left=230,toolbars=no,maximize=yes,resize=yes,width=700,height=450,location=no,scrollbars=yes');"/>  
<input type="button" name="delordr" value="Change Pwd"  size="10" onclick="window.open('pwdsettings.jsp','location','top=150,left=230,toolbars=no,maximize=yes,resize=yes,width=700,height=450,location=no,scrollbars=yes');"/>  
<!--input type="button" name="b" value="add item2" onclick="window.open('reports.jsp')"/>  
<input type="button" name="b" value="add item2" onclick="window.open('reports.jsp')"/-->  
    </body>
</html>
