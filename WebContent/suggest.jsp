<%@page import="java.sql.*,java.util.*,test.*" contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<%
SqlBean1 sb = new SqlBean1();
        Service serv = new Service();
        serv.theConnection = sb.makeConnection();
        String id = request.getParameter("id");
        StringBuffer resposeText = new StringBuffer(""); 
        if(id==null||id==""||id.equalsIgnoreCase("null"))
            {
        resposeText.append("No Suggestions");
        }
        else{
        
        try{
        
       List al = serv.selectItems(id);
       if(al.size()>0){
       Iterator i = al.iterator();
       //int count=0;
        while(i.hasNext()){
         ItemBean ib = (ItemBean)i.next();
       // ++count;
       // if(count==1){
       // resposeText.append(ib.getItem()+"-"+ib.getId());
       // }else{
        resposeText.append(","+ib.getItem()+"-"+ib.getId());
       // }
        }
        }else{
        resposeText.append("No Suggestions");
        }
        }
       
        catch(Exception ex){out.print(ex);}
     }  
out.print(resposeText);
%>