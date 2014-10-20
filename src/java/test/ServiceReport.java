/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
/**
 *
 * @author Gaurav
 */
public class ServiceReport extends Service{

   
     public List todayReport() {
       ReportBean rib ;
       List ritems = new ArrayList();
     try{
        query = "SELECT CONVERT(char(10), OrderDate, 103) as orderdate, Item, Category, SUM(Quantity) AS qty, SUM(Price) AS price, SUM(PriceExVat) AS priceExVat " +
                "FROM  OrderTable " +
                "WHERE     (CONVERT(char(10), OrderDate, 103) = CONVERT(char(10), GETDATE(), 103)) " +
                "GROUP BY CONVERT(char(10), OrderDate, 103),Item, Category";
        pstmt = theConnection.prepareStatement(query);
        theResult = pstmt.executeQuery();
        while(theResult.next()){
        rib = new ReportBean();
        rib.setItem( theResult.getString("item"));
        rib.setCategory(theResult.getString("category"));
        rib.setQuantity(theResult.getDouble("qty"));
        rib.setPrice(theResult.getDouble("price"));
        rib.setPriceExVat(theResult.getDouble("priceExVat"));
        rib.setOrderdate(theResult.getString("orderdate"));
        ritems.add(rib);
      }

        pstmt1 = theConnection1.prepareStatement(query);
        theResult1 = pstmt1.executeQuery();
        while(theResult1.next()){
        rib = new ReportBean();
        rib.setItem( theResult1.getString("item"));
        rib.setCategory(theResult1.getString("category"));
        rib.setQuantity(theResult1.getDouble("qty"));
        rib.setPrice(theResult1.getDouble("price"));
        rib.setPriceExVat(theResult1.getDouble("priceExVat"));
        rib.setOrderdate(theResult1.getString("orderdate"));
        ritems.add(rib);
      }

     }
        catch(SQLException ex){ex.getMessage();}
        finally {if(pstmt!=null){try { pstmt.close(); }catch (SQLException e) { e.printStackTrace();}}
        if(pstmt1!=null){try { pstmt1.close(); }catch (SQLException e) { e.printStackTrace();}}
        }
        return ritems;
    }

      public List userWiseReport(String userid) {
       ReportBean rib ;
       List ritems = new ArrayList();
     try{
        query = "SELECT     CONVERT(char(10), OrderDate, 103) as orderdate, Item, Category, SUM(Quantity) AS qty, SUM(Price) AS price, SUM(PriceExVat) AS priceExVat " +
                "FROM       OrderTable " +
                "WHERE     (DATEPART(mm, OrderDate) = DATEPART(mm, GETDATE())) AND (SUBSTRING(OrderID, 1, 4) = '"+ userid + "')" +
                "GROUP BY CONVERT(char(10), OrderDate, 103) ,Item, Category " +
                "ORDER BY CONVERT(char(10), OrderDate, 103) ,Item, Category";
        pstmt = theConnection.prepareStatement(query);
        theResult = pstmt.executeQuery();
        while(theResult.next()){
        rib = new ReportBean();
        rib.setItem( theResult.getString("item"));
        rib.setCategory(theResult.getString("category"));
        rib.setQuantity(theResult.getDouble("qty"));
        rib.setPrice(theResult.getDouble("price"));
        rib.setPriceExVat(theResult.getDouble("priceExVat"));
        rib.setOrderdate(theResult.getString("orderdate"));
        ritems.add(rib);
      }

        pstmt1 = theConnection1.prepareStatement(query);
        theResult1 = pstmt1.executeQuery();
        while(theResult1.next()){
        rib = new ReportBean();
        rib.setItem( theResult1.getString("item"));
        rib.setCategory(theResult1.getString("category"));
        rib.setQuantity(theResult1.getDouble("qty"));
        rib.setPrice(theResult1.getDouble("price"));
        rib.setPriceExVat(theResult1.getDouble("priceExVat"));
        rib.setOrderdate(theResult1.getString("orderdate"));
        ritems.add(rib);
      }
     }
        catch(SQLException ex){ex.getMessage();}
        finally {if(pstmt!=null){try { pstmt.close(); }catch (SQLException e) { e.printStackTrace();}}
        if(pstmt1!=null){try { pstmt1.close(); }catch (SQLException e) { e.printStackTrace();}}
        }
        return ritems;
    }

      public List dayWiseReport() {
       ReportBean rib ;
       List ritems = new ArrayList();
     try{
        query = "SELECT     CONVERT(char(10), OrderDate, 103) as orderdate, Item, Category, SUM(Quantity) AS qty, SUM(Price) AS price, SUM(PriceExVat) AS priceExVat " +
                "FROM       OrderTable " +
                "WHERE     (DATEPART(mm, OrderDate) = DATEPART(mm, GETDATE())) " +
                "GROUP BY CONVERT(char(10), OrderDate, 103) ,Item, Category " +
                "ORDER BY CONVERT(char(10), OrderDate, 103) ,Item, Category";
        pstmt = theConnection.prepareStatement(query);
        theResult = pstmt.executeQuery();
        while(theResult.next()){
        rib = new ReportBean();
        rib.setItem( theResult.getString("item"));
        rib.setCategory(theResult.getString("category"));
        rib.setQuantity(theResult.getDouble("qty"));
        rib.setPrice(theResult.getDouble("price"));
        rib.setPriceExVat(theResult.getDouble("priceExVat"));
        rib.setOrderdate(theResult.getString("orderdate"));
        ritems.add(rib);
      }

        pstmt1 = theConnection1.prepareStatement(query);
        theResult1 = pstmt1.executeQuery();
        while(theResult1.next()){
        rib = new ReportBean();
        rib.setItem( theResult1.getString("item"));
        rib.setCategory(theResult1.getString("category"));
        rib.setQuantity(theResult1.getDouble("qty"));
        rib.setPrice(theResult1.getDouble("price"));
        rib.setPriceExVat(theResult1.getDouble("priceExVat"));
        rib.setOrderdate(theResult1.getString("orderdate"));
        ritems.add(rib);
      }
     }
        catch(SQLException ex){ex.getMessage();}
        finally {if(pstmt!=null){try { pstmt.close(); }catch (SQLException e) { e.printStackTrace();}}
        if(pstmt1!=null){try { pstmt1.close(); }catch (SQLException e) { e.printStackTrace();}}
        }
        return ritems;
    }

       public List dayWiseSumReport() {
       ReportBean rib ;
       List ritems = new ArrayList();
     try{
        query = "SELECT     CONVERT(char(10), OrderDate, 103) as orderdate, SUM(Price) AS price, SUM(PriceExVat) AS priceExVat " +
                "FROM       OrderTable " +
                "WHERE     (DATEPART(mm, OrderDate) = DATEPART(mm, GETDATE())) " +
                "GROUP BY CONVERT(char(10), OrderDate, 103) " +
                "ORDER BY CONVERT(char(10), OrderDate, 103)";
        pstmt = theConnection.prepareStatement(query);
        theResult = pstmt.executeQuery();
        while(theResult.next()){
        rib = new ReportBean();
        rib.setPrice(theResult.getDouble("price"));
        rib.setPriceExVat(theResult.getDouble("priceExVat"));
        rib.setOrderdate(theResult.getString("orderdate"));
        ritems.add(rib);
      }

        pstmt1 = theConnection1.prepareStatement(query);
        theResult1 = pstmt1.executeQuery();
        while(theResult1.next()){
        rib = new ReportBean();
        rib.setPrice(theResult1.getDouble("price"));
        rib.setPriceExVat(theResult1.getDouble("priceExVat"));
        rib.setOrderdate(theResult1.getString("orderdate"));
        ritems.add(rib);
      }
     }
        catch(SQLException ex){ex.getMessage();}
        finally {if(pstmt!=null){try { pstmt.close(); }catch (SQLException e) { e.printStackTrace();}}
        if(pstmt1!=null){try { pstmt1.close(); }catch (SQLException e) { e.printStackTrace();}}
        }
        return ritems;
    }

       //added on 29th jan
        public List dateRangeReport(String startdate, String enddate) {
       ReportBean rib ;
       List ritems = new ArrayList();
       java.sql.Date sdate, edate;
       sdate = stringToDate(startdate);
       edate = stringToDate(enddate);

     try{
        query = "SELECT     CONVERT(char(10), OrderDate, 103) as orderdate, Item, Category, SUM(Quantity) AS qty, SUM(Price) AS price, SUM(PriceExVat) AS priceExVat " +
                "FROM       OrderTable " +
                "WHERE     OrderDate between '" + sdate + "' and '" + edate + "' " +
                "GROUP BY CONVERT(char(10), OrderDate, 103) ,Item, Category " +
                "ORDER BY CONVERT(char(10), OrderDate, 103) ,Item, Category";
        pstmt = theConnection.prepareStatement(query);
        theResult = pstmt.executeQuery();
        while(theResult.next()){
        rib = new ReportBean();
        rib.setItem( theResult.getString("item"));
        rib.setCategory(theResult.getString("category"));
        rib.setQuantity(theResult.getDouble("qty"));
        rib.setPrice(theResult.getDouble("price"));
        rib.setPriceExVat(theResult.getDouble("priceExVat"));
        rib.setOrderdate(theResult.getString("orderdate"));
        ritems.add(rib);
      }

        pstmt1 = theConnection1.prepareStatement(query);
        theResult1 = pstmt1.executeQuery();
        while(theResult1.next()){
        rib = new ReportBean();
        rib.setItem( theResult1.getString("item"));
        rib.setCategory(theResult1.getString("category"));
        rib.setQuantity(theResult1.getDouble("qty"));
        rib.setPrice(theResult1.getDouble("price"));
        rib.setPriceExVat(theResult1.getDouble("priceExVat"));
        rib.setOrderdate(theResult1.getString("orderdate"));
        ritems.add(rib);
        }

     }
        catch(SQLException ex){ex.getMessage();}
        finally {if(pstmt!=null){try { pstmt.close(); }catch (SQLException e) { e.printStackTrace();}}
        if(pstmt1!=null){try { pstmt1.close(); }catch (SQLException e) { e.printStackTrace();}}
        }
        return ritems;
    }

       public int getBillNo() {
        int billno=0;

        try{
        query = "SELECT count(distinct orderid) as billno " +
                "FROM         OrderTable ";
        pstmt = theConnection.prepareStatement(query);
        theResult = pstmt.executeQuery();
        theResult.next();
        billno = theResult.getInt("billno");

        pstmt1 = theConnection1.prepareStatement(query);
        theResult1 = pstmt1.executeQuery();
        theResult1.next();
        billno += theResult1.getInt("billno");

        }
        catch(SQLException ex){ex.getMessage();}
        finally {if(pstmt!=null){try { pstmt.close(); }catch (SQLException e) { e.printStackTrace();}}
        if(pstmt1!=null){try { pstmt1.close(); }catch (SQLException e) { e.printStackTrace();}}
        }
        return billno;
    }
}
