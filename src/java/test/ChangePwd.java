/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.io.*;
import java.net.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Gaurav
 */
public class ChangePwd extends HttpServlet {
   
    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession sess = request.getSession();
        Service scv = new Service();
        Connection con = (Connection)sess.getAttribute("con");
        scv.theConnection = con;
        RequestDispatcher rd = request.getRequestDispatcher("pwdsettings.jsp");
        String errmsg="";
        try {
            String usrnm =request.getParameter("user");
            String pword = scv.getPwd(usrnm);
            String opwd = request.getParameter("opwd");
            String npwd = request.getParameter("npwd");
            String cnpwd = request.getParameter("cnpwd");
            
            if(npwd.equals(cnpwd)&&opwd.equals(pword)){
            String uqry = "UPDATE LOGIN SET PASSWORD = ? WHERE USERNAME = ?";
		PreparedStatement ups = con.prepareStatement(uqry);
		ups.setString(1, npwd);
		ups.setString(2, usrnm);
                //ups.executeUpdate();
		
            if(ups.executeUpdate()==1){
                errmsg="Password has been changed successfully!!Please logout and re-login";
            }
                
            }else{
                errmsg="Password not matched!! Please retype password.";
                
            }
         
            request.setAttribute("emsg", errmsg);
            rd.forward(request, response);
            
        } catch(SQLException ex ){ex.getMessage();}finally { 
            out.close();
        }
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
