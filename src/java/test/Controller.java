/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.io.*;
import java.net.*;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author ugxs318
 */
public class Controller extends HttpServlet {
   
    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        HttpSession sess = request.getSession();
        Service serv = new Service();
        ServiceReport scv = new ServiceReport();
        SqlBean1 sb = new SqlBean1();
        serv.theConnection = (Connection)sess.getAttribute("con");
       sess.removeAttribute("currOrder");
       String user = (String)sess.getAttribute("usrnm");
       String orderid = "";

       int billno =0;

       if(user.equalsIgnoreCase("Raja")){
        orderid = "RJSA"+String.valueOf(System.currentTimeMillis());
        billno = serv.getBillNo();
       }else if(user.equalsIgnoreCase("user1")){
        orderid = "RJSB"+String.valueOf(System.currentTimeMillis());
         scv.theConnection = (Connection)sess.getAttribute("con");
         scv.theConnection1 = sb.makeConnectionAdmin();
         billno = scv.getBillNo();
       }else if(user.equalsIgnoreCase("user2")){
        orderid = "RJSC"+String.valueOf(System.currentTimeMillis());
        scv.theConnection = (Connection)sess.getAttribute("con");
        scv.theConnection1 = sb.makeConnectionAdmin();
        billno = scv.getBillNo();
       }
       
       sess.setAttribute("orderid", orderid);
       sess.setAttribute("billno", billno);

       //sess.setAttribute("curruser", user);
        try {
           List<OrderBean> orders = new ArrayList<OrderBean>();
           Map distVatAmt = new HashMap();

           OrderBean ob ;
           String[] items = request.getParameterValues("inputName");
           String[] rates = request.getParameterValues("inputName1");
           String[] cats = request.getParameterValues("inputName2");
           String[] qtys = request.getParameterValues("inputName3");
           String[] prices = request.getParameterValues("inputName4");
           String[] vat = request.getParameterValues("inputName5");
           int count=1;
           for(int i =0 ; i< items.length; i++){
           ob =new OrderBean();
           ob.setId(count);
           ob.setItem(items[i]);
           ob.setRate(Double.valueOf(rates[i]));
           ob.setCat(cats[i]);
           ob.setQty(Double.valueOf(qtys[i]));
           ob.setPrice(Double.valueOf(prices[i]));
           //added for vat changes
           ob.setPriceExVat(serv.calPEV(Double.valueOf(prices[i]),Double.valueOf(vat[i])));
           ob.setOdate(new java.sql.Date(System.currentTimeMillis()));
           ob.setOid(orderid);
           ob.setVat(Double.valueOf(vat[i]));
           orders.add(ob);
           ++count;
           }
           
          Comparator<OrderBean> BY_CAT = new Comparator<OrderBean>() {
                public int compare(OrderBean ob1, OrderBean ob2) {
                    return ob1.getCat().compareTo(ob2.getCat());
                }
            };
        Collections.sort(orders, BY_CAT);
        distVatAmt = serv.getDistinctVatAmt(orders);
        sess.setAttribute("currOrder", orders);
        sess.setAttribute("distVatAmt", distVatAmt);
        serv.insertAllOrder(orders);
        response.sendRedirect("printOrder.jsp"); 
         
        } catch(Exception ex){
        out.print(ex);
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
