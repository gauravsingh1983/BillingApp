<%@ page language="java" import="java.sql.*,java.util.*,java.text.*,test.*" contentType="text/html; charset=iso-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="ib" class="test.ItemBean" ></jsp:useBean>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>-------Add Item Details-------</title>
<style type="text/css">
<!--
#tbl tr { padding: 0.5em; }
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
<link href="style.css" rel="stylesheet" type="text/css">
 <script type="text/javascript">
function chkRate(obj){
if(isNaN(obj.value)){
alert("Please enter decimal value");
obj.value="0.0";
}
}
function check2(){
for(i=0;i<10;i++){
if(document.getElementById("itemn"+i).value==""||
    document.getElementById("raten"+i).value==""||
    document.getElementById("vatn"+i).value==""||
    document.getElementById("catn"+i).value==""){
alert("You are leaving some value blank!!");
return false;
}
}
return true;
}        
 function edtenable(id,a)
{
if (document.getElementById(id).checked) {
    document.getElementById("item"+a).disabled = false;
    document.getElementById("rate"+a).disabled = false;
    document.getElementById("cat"+a).disabled = false;
    document.getElementById("vat"+a).disabled = false;
 }else if(!document.getElementById(id).checked){
    document.getElementById("item"+a).disabled = true;
    document.getElementById("rate"+a).disabled = true;
    document.getElementById("cat"+a).disabled = true;
    document.getElementById("vat"+a).disabled = true;
}
}
</script>
</head>
<body>
<%try{
    
	
	String alist = "0";//(String)request.getAttribute("alist");
    String act= request.getParameter("act");
                if(act==null||act.equals("null")||act.equals("")){
                    
                }else if(act.equalsIgnoreCase("add")){
                alist = request.getParameter("listofacnt");
                }
	//out.println(alist);
  //-------------------------------------------------------
       
	
List itemlist = (List)session.getAttribute("itemlist");

%>

<form name="form" method="post" action="additems_admin.jsp?act=add">
  <table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td colspan="2"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr height="27">
                    <td width="5" height="27"background="images/tbl_topleft.jpg" >&nbsp;</td>
                    <td background="images/tbl_topcenter.jpg" class=" style2">Item Add/Update</td>
                    <td width="5" background="images/tbl_topright.jpg">&nbsp;</td>
                  </tr>
      </table></td>
    </tr><br />
    <tr>
      <td width="50%"><span class="style1">Select Number Of Items</span></td>
      <td width="50%"><select name="listofacnt" id="listofacnt">
      <option value="">-</option>
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
        <option value="6">6</option>
        <option value="7">7</option>
        <option value="8">8</option>
        <option value="9">9</option>
        <option value="10">10</option>
      </select>
      <input name="Submit" type="submit" class="style1" id="Submit" value="Go" ></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
  </table>
</form>
<form name="form1" method="post" action="Insertitemdetails?act=update" onsubmit="return check2();">
  <table id="tbl" width="600" border="0" align="center" >
   <tr>
      <td colspan="6" class="style1">
        <div align="left">
          <input type="submit" name="Submit" value="Submit">
         <input type="hidden" name="act" value="update">
          </div></td>
    </tr>
    <tr class="left-column-header">
    <td class="style2"><div align="center">Item Name</div></td>
      <td class="style2"><div align="center">Rate</div></td>
      <td class="style2"><div align="center">Vat</div></td>
      <td class="style2"><div align="center">Category</div></td>
      <td colspan="2" class="style1"><div align="center"></div></td>
    </tr>
    
    <%if(alist==null){}else{
    	int j = Integer.parseInt(alist);
    	for(int i=0; i<j; i++){%>
    <tr class="classy<%=(i % 2)%>">
      <td class="style1"><div align="center">
        <input type="text" id="itemn<%=i%>" name="itemn" size="30"/>
      </div></td>
      <td class="style1"><div align="center"><input type="text" id="raten<%=i%>" name="raten" onchange="chkRate(this)" size="10"/></div></td>
      <td class="style1"><div align="center">
        <input type="text" id="vatn<%=i%>" name="vatn" size="5"/>
      </div></td>
       <td class="style1"><div align="center">
        <input type="text" id="catn<%=i%>" name="catn" size="20"/>
      </div></td>
      <td  class="style1"></td>
      
    
   </tr>
  
<%}} %>
    <% //out.print("gaurav");
            try{
            int sno=0;
       Iterator i= itemlist.iterator();
       while(i.hasNext()){
         
       ib= (ItemBean)i.next();
    %>
    <tr class="classy<%=(sno % 2)%>">
      <td class="style1">
      <div align="center">
        <input id="item<%=sno%>" type="text" name="item" size="30" value="<%=ib.getItem()%>" disabled />
      </div>
      </td>
      <td class="style1"><div align="center">
          <input id="rate<%=sno%>" type="text" name="rate" size="10" value="<%=ib.getRate()%>" onchange="chkRate(this)" disabled/></div></td>
      <td class="style1"><div align="center">
        <input id="vat<%=sno%>" type="text" name="vat" size="5" value="<%=ib.getVat()%>" disabled />
      </div></td>
      <td class="style1"><div align="center">
        <input id="cat<%=sno%>" type="text" name="cat" size="20" value="<%=ib.getCat()%>" disabled />
      </div></td>
      <td class="style1"><input type="checkbox" name="updt" id="updt<%=sno%>" onchange="edtenable(this.id,<%=sno%>)" value="<%=ib.getId()%>" />
      <a href="Insertitemdetails?act=del&itemid=<%=ib.getId()%>&del=Y">X</a>
      </td>
      
     
    </tr>
    
  <%     ++sno;     }
            }catch(Exception e){out.println(e);}%>
    
  
      

  </table>

</form>
<%}catch(Exception e){out.println(e);} %>
</body>
</html>