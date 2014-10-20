<%
response.setContentType("application/xml");	
out.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
%>
<%@page import="java.sql.*,java.util.*,test.*" contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<%

        Service serv = new Service();
        serv.theConnection = (Connection)session.getAttribute("con");
        StringBuffer resposeText = new StringBuffer(""); 
        String id = request.getParameter("id");
       if(id==null||id==""||id.equalsIgnoreCase("null"))
            {
       // resposeText.append("No Suggestions");
        }
        else{
        
        try{
        
       ItemBean ib = serv.selectItem(id);
       resposeText.append("<items>");
       resposeText.append("<id>"+ib.getId()+"</id>");
       resposeText.append("<item>"+ib.getItem()+"</item>");
       resposeText.append("<rate>"+ib.getRate()+"</rate>");
       //added for vat changes
       resposeText.append("<vat>"+ib.getVat()+"</vat>");
       resposeText.append("<cat>"+ib.getCat()+"</cat>");
       resposeText.append("</items>");
        }
        catch(Exception ex){out.print(ex);}
    }   
out.print(resposeText);
%>