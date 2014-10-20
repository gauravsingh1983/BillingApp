<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
response.setHeader("Pragma","no-cache");
response.setDateHeader("Expires",0);
response.setHeader("Cache-Control","no-cache");
%>
<%@page import="java.sql.*,java.util.*,test.*,java.text.DecimalFormat" contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<jsp:include page="sessionCheck.jsp"></jsp:include>
<%
Service scv = new Service();
List reportItem = (List)session.getAttribute("reportList");
Integer repcat = (Integer)session.getAttribute("repcat");
response.setContentType("application/vnd.ms-excel");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>---Reports---</title>
        <style type="text/css">
<!--
#tblreport tr { padding: 0.5em; }
body {
	background-color: #FFFFFF;
}
.style2 {
    font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 20px;
	color: #333333;
	font-weight: bold;
}
-->
</style>
    </head>
<body>
    <table id="tblreport" border="0" cellspacing="4" align="center">

      
      <tr>
        <td><%if(repcat==2){%>
            <table border="0" align="center">
          <thead>
            <tr class="left-column-header">
              <th>Sno</th>
              <th>OrderDate</th>
              <th>Amount</th>
              <th>Amount(VAT Excl.)</th>
            </tr>
          </thead>
          <tbody>
            <%
   DecimalFormat dc = new DecimalFormat("0.00");
  Double total=0.0;
  Double totalExVat=0.0;
  //Double totalVat=0.0;
try{

Iterator i = reportItem.iterator();
ReportBean rb;
int sno = 0;

  while(i.hasNext()){
      ++sno;

       rb = (ReportBean)i.next();
        %>
            <tr class="classy<%=(sno % 2)%>">
              <td><%=sno%></td>
              <td><%=rb.getOrderdate()%></td>

              <td><%=dc.format(rb.getPrice())%></td>
              <td><%=dc.format(rb.getPriceExVat())%></td>
            </tr>
            <%
        total +=rb.getPrice();
        totalExVat += rb.getPriceExVat();
}
}catch(Exception ex){out.println(ex);}
%>
 <tr class="classy0">
     <td colspan="2" ><div align="right">Total  </div></td>
     <td colspan="1" ><div align="justify"><%=dc.format(total)%></div></td>
     <td colspan="1" ><div align="justify"><%=dc.format(totalExVat)%></div></td>
 </tr>
      <tr class="classy0">
     <td colspan="5" ><div align="right">Total VAT </div></td>
     <td colspan="2" ><div align="right"><%=dc.format(total-totalExVat)%></div></td>
            </tr>
          </tbody>
        </table>
        <%}else{%>
        <table border="0" align="center">
          <thead>
            <tr class="left-column-header">
              <th>Sno</th>
              <th>OrderDate</th>
              <th>Item</th>
              <th>Category</th>
              <th>Qty</th>
              <th>Amount</th>
              <th>Amount(VAT Excl.)</th>
              <th>VAT Amount</th>
            </tr>
          </thead>
          <tbody>
            <%
   DecimalFormat dc = new DecimalFormat("0.00");
  Double total=0.0;
  Double totalExVat=0.0;
  Double totalVat=0.0;
try{

Iterator i = reportItem.iterator();
ReportBean rb;
int sno = 0;

  while(i.hasNext()){
      ++sno;

       rb = (ReportBean)i.next();
        %>
            <tr class="classy<%=(sno % 2)%>">
              <td><%=sno%></td>
              <td><%=rb.getOrderdate()%></td>
              <td><%=rb.getItem()%></td>
              <td><%=rb.getCategory()%></td>
              <td><%=dc.format(rb.getQuantity())%></td>
              <td><%=dc.format(rb.getPrice())%></td>
              <td><%=dc.format(rb.getPriceExVat())%></td>
              <td><%=dc.format(rb.getPrice()-rb.getPriceExVat())%></td>
            </tr>
            <%
        total +=rb.getPrice();
        totalExVat += rb.getPriceExVat();
        totalVat += (rb.getPrice()-rb.getPriceExVat());
}
}catch(Exception ex){out.println(ex);}
%>
 <tr class="classy0">
     <td colspan="5" ><div align="right">Total  </div></td>
     <td colspan="1" ><div align="justify"><%=dc.format(total)%></div></td>
     <td colspan="1" ><div align="justify"><%=dc.format(totalExVat)%></div></td>
     <td colspan="1" ><div align="justify"><%=dc.format(totalVat)%></div></td>
            </tr>
 <tr class="classy0">
     <td colspan="5" ><div align="right">Total VAT </div></td>
     <td colspan="2" ><div align="right"><%=dc.format(total-totalExVat)%></div></td>
     <td colspan="1" ><div align="right"><%=dc.format(totalVat)%></div></td>
            </tr>
          </tbody>
        </table>
        <%}%>
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table>
<h2>&nbsp;</h2>
 <!--a href="javascript:history.back()">back</a-->
</body>
</html>
