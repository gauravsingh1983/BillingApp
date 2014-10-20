<%-- 
    Document   : admin_settings
    Created on : Oct 16, 2009, 8:31:46 PM
    Author     : Gaurav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="sessionCheck.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<style type="text/css">
<!--
.style1 {color: #003A95}
.style4 {
	color: #284b91;
	font-weight: bold;
}
-->
</style>
<script language="JavaScript">

function check()
{  
    
    if((document.nefrm.user.value==""))
	{
		alert("Please select a user!!");
		document.nefrm.user.focus();
		return false;
	}
	if((document.nefrm.opwd.value==null)||(document.nefrm.opwd.value==""))
	{
		alert("Enter Old Password!!");
		document.nefrm.opwd.focus();
		return false;
	}
	if((document.nefrm.npwd.value==null)||(document.nefrm.npwd.value==""))
	{
		alert("Enter New Password!!");
		document.nefrm.npwd.focus();
		return false;
	}
        if((document.nefrm.cnpwd.value==null)||(document.nefrm.cnpwd.value==""))
	{
		alert("Confirm New Password!!");
		document.nefrm.cnpwd.focus();
		return false;
	}
      
	return true;
}
</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Setting</title>

</head>
<%
String emsg="";
emsg = (String)request.getAttribute("emsg");

%>
<body>
 
                <form id="form1" name="nefrm" method="post" action="ChangePwd" onsubmit="return check();">
                     <div id="msg"><font color="RED"><%=emsg!=null?emsg:""%></font></div>  
                <table width="99%" height="177" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td height="12" valign="top" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                          <p>&nbsp;</p></td>
                        </tr>
                      <tr>
                        <td valign="top" ><table width="69%" align="center" cellpadding="0" cellspacing="0">
                            <tr>
                            <td  >Select User</td>
                            <td colspan="2"><select name="user" id="user">
              <option value="">-</option>
              <option value="Raja">Raja</option>
              <option value="user1">user1</option>
              <option value="user2">user2</option>
                </select></td>
                          </tr>
                          <tr>
                            <td width="50%"><label>                            
                              Old Password<br />
                                
                            </label></td>
                            <td colspan="2" >
                              
                                <input type="password" name="opwd" value="" />
                               
                            </td>
                            </tr>
                          <tr>
                            <td width="50%" >Password</td>
                            <td colspan="2" >
                                <input type="password" name="npwd" /></td>
                          </tr>
                          <tr>
                            <td >Re type your Password</td>
                            <td colspan="2"><label>
                              <input type="password" name="cnpwd" />
                            </label></td>
                          </tr>
                          <tr>
                            <td></td>
                            <td width="13%">&nbsp;</td>
                            <td width="37%"><input type="submit" name="Submit" value="Submit" /></td>
                          </tr>
                          
                        </table>
                        </form>

</body>
</html>
