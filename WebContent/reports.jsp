<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
response.setHeader("Pragma","no-cache");
response.setDateHeader("Expires",0);
response.setHeader("Cache-Control","no-cache");
%>
<%@page import="java.sql.*,java.util.*,test.*,java.text.DecimalFormat" contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<script language="JavaScript" src="calendar_us.js"></script>
<link rel="stylesheet" href="calendar.css"/>
<script language="JavaScript">

function check()
{
    if(document.form1.repcat.value==6){
        if((document.form1.sdate.value==null)||(document.form1.sdate.value=="")
        ||(document.form1.edate.value==null)||(document.form1.edate.value==""))
	{
		alert("Enter missing value!!");
		document.form1.sdate.focus();
		return false;
	}
    }
	return true;
}
function disdata(argmnt)
{
if(argmnt=="6")
{
document.getElementById("range").style.visibility="visible";
}
else
{
document.getElementById("range").style.visibility="hidden";
}
}
</script>
<jsp:include page="sessionCheck.jsp"></jsp:include>
<%
String utype =(String)session.getAttribute("user");
Service scv ;
if(utype.equalsIgnoreCase("SA")){
SqlBean1 sb = new SqlBean1();
scv = new ServiceReport();
scv.theConnection = (Connection)session.getAttribute("con");
scv.theConnection1 = sb.makeConnectionAdmin();
}else{
scv = new Service();
scv.theConnection = (Connection)session.getAttribute("con");
}
List reportItem ;
int repcat=1;
String rcat = request.getParameter("repcat");
String startdate=null;
String enddate=null;
if(rcat==null||rcat==""||rcat.equalsIgnoreCase("null")){
 repcat=1;   
}
else{
    repcat = Integer.parseInt(rcat);
    if(repcat==6){
       startdate = request.getParameter("sdate");
       enddate = request.getParameter("edate");
    }
}

switch(repcat){
case 1:   reportItem = scv.todayReport();break;
case 2:   reportItem = scv.dayWiseSumReport();break;
case 3:   reportItem = scv.userWiseReport("RJSA");break;
case 4:   reportItem = scv.userWiseReport("RJSB");break;
case 5:   reportItem = scv.userWiseReport("RJSC");break;
case 6:   reportItem = scv.dateRangeReport(startdate, enddate);break;
default:    reportItem = scv.dayWiseReport();
}
session.setAttribute("reportList", reportItem);
session.setAttribute("repcat", repcat);

//out.println("gaurav"+repcat);
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>---Reports---</title>
      <link href="style.css" rel="stylesheet" type="text/css">
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
<body onLoad='disdata(document.getElementById("repcat").value)'>
    <table id="tblreport" border="0" cellspacing="4" align="center">

      <tr>
        <td><div align="left" class=" style2">View Reports</div></td>
      </tr>
      <tr>
        <td><div align="left" class=" style1">Reports By:
        <form name="form1" action="reports.jsp" method="post" onsubmit="return check();">
            <select name="repcat" id="repcat" onchange="disdata(this.value)">
              <option value="">-</option>
              <option value="1">todays report</option>
              <option value="2">day wise report</option>
              <option value="3">Raja</option>
              <option value="4">user1</option>
              <option value="5">user2</option>
              <option value="6">date range report</option>
                </select>
             <input type="submit" value="Get Report" />
             <div id="range">
             <input type="text" id="sdate" name="sdate" value="" readonly />
                     <script language="JavaScript">new tcal ({'formname': 'form1','controlname': 'sdate'});</script>
             <input type="text" id="edate" name="edate" value="" readonly />
                     <script language="JavaScript">new tcal ({'formname': 'form1','controlname': 'edate'});</script>
             </div>
            </form>
        </div></td>
      </tr>
      <tr>
          <td><a href="reports_xls.jsp" onclick="">Export to XLS</a></td>
      </tr>
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
