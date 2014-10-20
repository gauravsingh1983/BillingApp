<%
String usernm = (String)session.getAttribute("usrnm");
String passwd = (String)session.getAttribute("pword");

if(((usernm == null) || (passwd == null)))
{
%>
<jsp:forward page="login.jsp">
    <jsp:param name="usernm" value=""></jsp:param>
    <jsp:param name="passwd" value=""></jsp:param>
</jsp:forward>
<%   
}
%>

