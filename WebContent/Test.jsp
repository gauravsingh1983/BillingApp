<%-- 
    Document   : Test
    Created on : Oct 31, 2009, 8:58:33 AM
    Author     : Gaurav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
function printSelection(node){
    for(i=0;i<4;i++){
  var content=node.innerHTML  
var pwin=window.open('','print_content','width=100,height=100');  
pwin.document.open();  
pwin.document.write('<html><body onload="window.print()">'+content+'</body></html>');
  pwin.document.close();   
}
}
</script>
<div id="test1">this is a test1</div>
<div id="test2">this is a test2</div>
<div id="test3">this is a test3</div>
<a href="" onclick="printSelection(document.getElementById('test'));">print</a>