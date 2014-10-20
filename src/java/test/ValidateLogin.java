/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.io.*;
import java.net.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Gaurav
 */
public class ValidateLogin extends HttpServlet {
   
    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession sess;
        //RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
		
		//out.println(user);
		//out.println(pass);
		
		if((user.equals(""))||(user==null))
		{
			response.sendRedirect("login.jsp");
		}
        try {
            String DbUserName = "";
		String DbPassword = "";
		String usertype = "";
                String userid = "";

            SqlBean1 sb = new SqlBean1();
            
             Connection con = null;
             
            if(user.equalsIgnoreCase("Raja")){
              con =  sb.makeConnectionAdmin();
            }else{
                con =sb.makeConnection();
            }
		
		PreparedStatement pstmt;
		String Query = "SELECT * FROM LOGIN WHERE USERNAME = ?";
                pstmt = con.prepareStatement(Query);
                pstmt.setString(1, user);
		ResultSet rs = pstmt.executeQuery();
                if(rs.next())
		  {
		   	DbUserName=rs.getString("UserName");
		   	DbPassword=rs.getString("Password");
		 	usertype = rs.getString("UserType");
                        if(pass.equals(DbPassword)){
                           sess = request.getSession(true);
                           sess.setAttribute("user", usertype);
                           sess.setAttribute("pword", DbPassword);
                           sess.setAttribute("usrnm", DbUserName);
                           sess.setAttribute("con", con);
                            if(DbUserName.equalsIgnoreCase("Raja")){
                            userid = "RJSA"; 
                           }else if(DbUserName.equalsIgnoreCase("user1")){
                            userid = "RJSB";
                           }else if(DbUserName.equalsIgnoreCase("user2")){
                            userid = "RJSC";
                           }
                           sess.setAttribute("userid", userid);
                          response.sendRedirect("index.jsp");
                        } else{
                        response.sendRedirect("login.jsp");
                        }
                }else{
                    response.sendRedirect("login.jsp");
                }
             //con.close();   
        } catch(Exception ex) { 
            out.println(ex);
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
