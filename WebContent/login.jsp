<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Login Page</title>
<style type="text/css">
<!--
.style11 {	font-size: 10px;
	color: #333333;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-weight: bold;
}
.style13 {font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px; color: #333333; }
.style6 {	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 10px;
	color: #666666;
}
.style7 {	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-weight: bold;
	font-size: 14px;
	color: #333333;
}
-->
</style>
</head>

<body>
<form action="ValidateLogin" method="post">
<table width="185" height="124" border="0" cellpadding="0" cellspacing="0" align="center">
  <tr>
    <td width="15">&nbsp;</td>
    <td width="64"><span class="style7">Sign In</span></td>
    <td width="106">&nbsp;</td>
  </tr>
  <tr>
    <td height="25">&nbsp;</td>
    <td width="64" height="25"><span class="style6">Username</span></td>
    <td width="106" height="25"><input name="username" type="text" class="style13" size="16" /></td>
  </tr>
  <tr>
    <td height="25">&nbsp;</td>
    <td width="64" height="25"><span class="style6">Password</span></td>
    <td width="106" height="25"><input name="password" type="password" class="style13" size="16" /></td>
  </tr>
  <tr>
    <td height="25">&nbsp;</td>
    <td width="64" height="25">&nbsp;</td>
    <td width="106" height="25"><label>
      <input name="Submit" type="submit" class="style6" value="Login" />
    </label></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2"><span class="style11"><a href="#">Forgot   Password?</a></span></td>
  </tr>
</table>
</form>
</body>
</html>
