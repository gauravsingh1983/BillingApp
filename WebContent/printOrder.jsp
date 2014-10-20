<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
response.setHeader("Pragma","no-cache");
response.setDateHeader("Expires",0);
response.setHeader("Cache-Control","no-cache");
%>
<%@page import="java.sql.*,java.util.*,test.*,java.text.DecimalFormat,java.text.SimpleDateFormat" contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<jsp:include page="sessionCheck.jsp"></jsp:include>
<%
List printItem = (List)session.getAttribute("currOrder");
Map distVatAmt = (Map)session.getAttribute("distVatAmt");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>---Print Order---</title>
<script type="text/javascript">
function printClose(){
//window.print();
//window.location.href="index.jsp";
}

</script>
<style type="text/css">
<!--
.style1 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-weight: bold;
}

.styleFont {
	font-family: "Times New Roman", Times, serif;
	font-size:6;
}
.style3 {
    font-size: small
}
.pbstl{page-break-after: always;}
-->
</style>
</head>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
String orderid = (String)session.getAttribute("orderid");
 DecimalFormat dc = new DecimalFormat("0.00");
 DecimalFormat dc1 = new DecimalFormat("0.000");
 Double total=0.0;
 Double totalExVat=0.0;
 Double salestax =0.0;
 
//String billno = orderid.substring(7, orderid.length()-3); 
 int billno = (Integer)session.getAttribute("billno"); 
 %>
<body onload="printClose()">
 <table width="250"  border="0" align="center" cellpadding="2" cellspacing="4"> 
        <%
try{
Iterator i = printItem.iterator();
OrderBean ob;
String a ="sweets";
int sno=1;

  while(i.hasNext()){
       ob = (OrderBean)i.next();
       if(!ob.getCat().equalsIgnoreCase(a)){
        %>
        <%if(sno>1){%><p style="page-break-before: always;"/><%}%>
        <table width="250"  border="0" align="center" cellpadding="2" cellspacing="4">
         <tr>
          <td colspan="5" scope="col"><div align="left">Estimate No - <%=billno%></div></td>
          </tr>
        <tr>
          <th scope="col">Sno</th>
          <th scope="col">ItemName</th>
          <th scope="col">Qty</th>
          <th scope="col">Rate</th>
          <th scope="col">Amount</th>
        </tr>
        <tr class="style3">
          <td><%=sno%></td>
          <td><%=ob.getItem()%></td>
          <td><%=dc1.format(ob.getQty())%></td>
          <td><%=dc.format(ob.getRate())%></td>
          <td>Rs <%=dc.format(ob.getPrice())%></td>
        </tr>
        </table>     
        <%}else{%>
        <table width="250"  border="0" align="center" cellpadding="2" cellspacing="4">
         
        <tr class="style3">
          <td width="25"><%=sno%></td>
          <td width="86"><%=ob.getItem()%></td>
          <td width="27"><%=dc1.format(ob.getQty())%></td>
          <td width="32"><%=dc.format(ob.getRate())%></td>
          <td width="54">Rs <%=dc.format(ob.getPrice())%></td>
        </tr>
    </table>
        <%}
a = ob.getCat();
++sno;
}}catch(Exception ex){out.println(ex);}
%>
</table>
<table width="250" height="276" border="0" cellpadding="2" cellspacing="4">
  <tr>
      <td  height="72" colspan="3"><div align="right"><font size="2px">Ph :65423324</font></div>
    <div align="center" class="style1">NEW RAJA SWEETS</div>
<div align="center"><font size="2px">Vardhman Airport Plaza, Sector-6, Dwarka.</font></div></td>
  </tr>
  <tr >
      <td colspan="3"><div align="left"><font size="2px">Estimate No - <%=billno%></font></div>
          <div align="right"><font size="2px">Date: <%=sdf.format(new java.util.Date())%></font></div>
      <table width="250"  border="0" align="center" cellpadding="2" cellspacing="4">
        <tr>
          <th scope="col">Sno</th>
          <th scope="col">ItemName</th>
          <th scope="col">Qty</th>
          <th scope="col">Rate</th>
          <th scope="col">Amount</th>
        </tr>
        <%
try{
Iterator i = printItem.iterator();
OrderBean ob;
int sno=1;

  while(i.hasNext()){
       ob = (OrderBean)i.next();   
        %>
        <tr class="style3">
          <td><%=sno%></td>
          <td><%=ob.getItem()%></td>
          <td><%=dc1.format(ob.getQty())%></td>
          <td><%=dc.format(ob.getRate())%></td>
          <td>Rs <%=dc.format(ob.getPrice())%></td>
        </tr>
        <%
 total +=ob.getPrice();
 totalExVat += ob.getPriceExVat();
++sno;
}}catch(Exception ex){out.println(ex);}
%>
      </table></td>
  </tr>
 
   <tr class="style3">
    <td colspan="2"><b>Total Amount (Incl. VAT)</b></td>
    <td><div align="right"><b>Rs :<%=dc.format(total+salestax)%></b></div></td>
  </tr>
  <tr class="style3">
    <td colspan="2">Total VAT</td>
    <td><div align="right">Rs :<%=dc.format(total-totalExVat)%></div></td>
  </tr>
  <%
  Double vatAmt=0.0;
  Double vatRate=0.0;
  Set s = distVatAmt.keySet();
  Iterator i = s.iterator();
  while(i.hasNext()){
   vatRate = (Double)i.next();
   vatAmt = (Double)distVatAmt.get(vatRate);
  %>
  <tr class="style3">
    <td colspan="2">VAT @<%=vatRate%>%</td>
    <td><div align="right">Rs :<%=dc.format(vatAmt)%></div></td>
  </tr>
  <%}%>
  <tr>
    <td colspan="3"><div align="center">!! THANKS VISIT AGAIN !!</div></td>
  </tr>
  <p style="page-break-after: always;"/>

</table>
</body>
</html>
