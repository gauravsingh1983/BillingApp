/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.io.*;
import java.net.*;

import java.sql.Connection;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Gaurav
 */
public class Insertitemdetails extends HttpServlet {
   
    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Service cda = new Service();
       HttpSession sess = request.getSession();
        cda.theConnection = (Connection)sess.getAttribute("con");
        
        
        try{
		String act= request.getParameter("act");
                if(act==null||act.equals("null")||act.equals("")){
                out.print("gaurav"+act);
                sess.removeAttribute("itemlist");
                List itemlist= cda.selectAllItems();
                out.print("gaurav"+itemlist.size());
                sess.setAttribute("itemlist", itemlist);
                response.sendRedirect("additems_admin.jsp");
		
                }
                else{
                    
                 /* if(act.equalsIgnoreCase("add")){
                      
                String alist = request.getParameter("listofacnt");
                if(alist==null||alist.equals("")){
                   response.sendRedirect("additems_admin.jsp");
                }else{
                         
                         request.setAttribute("alist", alist);
                          RequestDispatcher rd = request.getRequestDispatcher("addmultipleaccount_admin.jsp");
                        rd.forward(request, response);
                }
            } */
                    
             if(act.equalsIgnoreCase("del")){
                    int itemid=0;
                    String del="";
                    try{del=request.getParameter("del");
       
                    if(del==null||del.equals("")){del="";} else {
                 if(request.getParameter("del").equalsIgnoreCase("Y"))
                     {
                         String id=request.getParameter("itemid");
                         itemid= Integer.parseInt(id);
                         if(cda.deleteItem(itemid)==1){
                         response.sendRedirect("Insertitemdetails");
                         }
                     }
                    }}catch(Exception e){}
                
               } 
                  
                 if(act.equalsIgnoreCase("update")){
                   int row=0;
                   
                   String[] updt = request.getParameterValues("updt");
                   if(updt!=null){
                   String[] item = request.getParameterValues("item");
                   String[] rate = request.getParameterValues("rate");
                   //added for vat changes
                   String[] vat = request.getParameterValues("vat");
                   String[] cat = request.getParameterValues("cat");
                   if(updt.length>0){
                    for(int i=0; i<updt.length; i++)
			{
                                ItemBean ib= new ItemBean();
				int cid = Integer.parseInt(updt[i]);
                                ib.setItem(item[i]);
                                ib.setRate(Double.parseDouble(rate[i]));
                                ib.setVat(Double.parseDouble(vat[i]));
                                ib.setCat(cat[i]);
				row=cda.updateItemTable(ib, cid);
			}
                   }}
                   String[] itemn = request.getParameterValues("itemn");
                   String[] raten = request.getParameterValues("raten");
                   //added for vat changes
                   String[] vatn = request.getParameterValues("vatn");
                   String[] catn = request.getParameterValues("catn");
                   if(itemn==null||raten==null||catn==null){}else{
                    for(int i=0; i<itemn.length; i++)
			{
                                ItemBean ib= new ItemBean();
				ib.setItem(itemn[i]);
                                ib.setRate(Double.parseDouble(raten[i]));
                                ib.setVat(Double.parseDouble(vatn[i]));
                                ib.setCat(catn[i]);
				row=cda.insertItemTable(ib);
			}
                   }
                 if(row>=1){
                response.sendRedirect("Insertitemdetails");
              }
                 }
                
                
                }
            
			out.println("Data has been successfully updated, please close the window.");
		}//catch(SQLException e){out.println(e);}
             catch(Exception e){out.println(e);}
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
    * Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
    * Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
    * Returns a short description of the servlet.
    */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
