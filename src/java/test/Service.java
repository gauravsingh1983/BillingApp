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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ugxs318
 */
public class Service {

    
    String query=null;
    PreparedStatement pstmt=null;
    ResultSet theResult=null;
    public Connection theConnection;

    public Connection theConnection1;
    PreparedStatement pstmt1=null;
    ResultSet theResult1=null;

    DecimalFormat dc = new DecimalFormat("0.00");
    
     public int insertOrderTable(OrderBean ob)throws SQLException {
        
        query="insert into ordertable(item,quantity,price,priceexvat," +
                "rate,category,orderdate,orderid)" +
                "values(?,?,?,?,?,?,?,?)";
       
            pstmt=theConnection.prepareStatement(query);
            //pstmt.setInt(1, ob.getId());
            pstmt.setString(1, ob.getItem());
            pstmt.setDouble(2, ob.getQty());
            pstmt.setDouble(3, ob.getPrice());
            pstmt.setDouble(4, ob.getPriceExVat());
            pstmt.setDouble(5, ob.getRate());
            pstmt.setString(6, ob.getCat());
            pstmt.setDate(7, ob.getOdate());
            pstmt.setString(8, ob.getOid());
            return pstmt.executeUpdate();
            
    }
     
     public void insertAllOrder(List<?> orders) throws SQLException{
     Iterator<?> i = orders.iterator();
     while(i.hasNext()){
     OrderBean ob = (OrderBean)i.next();
     if(ob.getCat().trim().equalsIgnoreCase("A")||ob.getCat().trim().equalsIgnoreCase("B")||ob.getCat().trim().equalsIgnoreCase("C")||ob.getCat().trim().equalsIgnoreCase("D"))
     {}else{insertOrderTable(ob);}
     }
     }
     
    public Double getRate(int id){
        double rate=0;
     try{ 
        query = "select * from itemtable where id=? ";
        pstmt = theConnection.prepareStatement(query);
        pstmt.setInt(1,id);
        theResult = pstmt.executeQuery();
        theResult.next();
        rate = theResult.getInt("rate");
      }
        catch(SQLException ex){ex.getMessage();}
        finally {if(pstmt!=null){try { pstmt.close(); }catch (SQLException e) { e.printStackTrace();}}}
        return rate;
    }
    
     public ItemBean selectItem(int id){
        ItemBean ib = new ItemBean();
     try{ 
        query = "select * from itemtable where id=? ";
        pstmt = theConnection.prepareStatement(query);
        pstmt.setInt(1,id);
        theResult = pstmt.executeQuery();
        theResult.next();
        ib.setId( theResult.getInt("id"));
        ib.setItem(theResult.getString("item"));
        ib.setRate(theResult.getDouble("rate"));
        //added for vat changes
        ib.setVat(theResult.getDouble("vat"));
        ib.setCat(theResult.getString("category"));
      }
        catch(SQLException ex){ex.getMessage();}
        finally {if(pstmt!=null){try { pstmt.close(); }catch (SQLException e) { e.printStackTrace();}}}
        return ib;
    }
     
     public ItemBean selectItem(String itemid){
       ItemBean ib = new ItemBean();
     try{ 
        query = "select * from itemtable where id=? ";
        pstmt = theConnection.prepareStatement(query);
        pstmt.setString(1,itemid);
        theResult = pstmt.executeQuery();
        theResult.next();
        ib.setId( theResult.getInt("id"));
        ib.setItem(theResult.getString("item"));
        ib.setRate(theResult.getDouble("rate"));
        //added for vat changes
        ib.setVat(theResult.getDouble("vat"));
        ib.setCat(theResult.getString("category"));
      }
        catch(SQLException ex){ex.getMessage();}
        finally {if(pstmt!=null){try { pstmt.close(); }catch (SQLException e) { e.printStackTrace();}}}
        return ib;
    }
    
     public List<ItemBean> selectItems(String input){
       ItemBean ib ;
       List<ItemBean> items = new ArrayList<ItemBean>();
     try{ 
        query = "select * from itemtable where item like '"+ input + "%'";
        pstmt = theConnection.prepareStatement(query);
        theResult = pstmt.executeQuery();
        while(theResult.next()){
        ib = new ItemBean();    
        ib.setId( theResult.getInt("id"));
        ib.setItem(theResult.getString("item"));
        ib.setRate(theResult.getDouble("rate"));
        //added for vat changes
        ib.setVat(theResult.getDouble("vat"));
        ib.setCat(theResult.getString("category"));
        items.add(ib);
      }}
        catch(SQLException ex){ex.getMessage();}
        finally {if(pstmt!=null){try { pstmt.close(); }catch (SQLException e) { e.printStackTrace();}}}
        return items;
    }
   
      public List<ItemBean> selectAllItems(){
       ItemBean ib ;
       List<ItemBean> items = new ArrayList<ItemBean>();
     try{ 
        query = "select * from itemtable order by item";
        pstmt = theConnection.prepareStatement(query);
        theResult = pstmt.executeQuery();
        while(theResult.next()){
        ib = new ItemBean();    
        ib.setId( theResult.getInt("id"));
        ib.setItem(theResult.getString("item"));
        ib.setRate(theResult.getDouble("rate"));
        //added for vat changes
        ib.setVat(theResult.getDouble("vat"));
        ib.setCat(theResult.getString("category"));
        items.add(ib);
      }}
        catch(SQLException ex){ex.getMessage();}
        finally {if(pstmt!=null){try { pstmt.close(); }catch (SQLException e) { e.printStackTrace();}}}
        return items;
    }
    
     public int insertItemTable(ItemBean ib)throws SQLException {
        query="insert into itemtable(item,rate,vat,category)" +
                "values(?,?,?,?)";
            pstmt=theConnection.prepareStatement(query);
            pstmt.setString(1, ib.getItem());
            pstmt.setDouble(2, ib.getRate());
            //added for vat changes.
            pstmt.setDouble(3, ib.getVat());
            pstmt.setString(4, ib.getCat());
            return pstmt.executeUpdate();
            
    } 
     
      public int updateItemTable(ItemBean ib,int itemid)throws SQLException {
        query="update itemtable set item=?, rate=?,vat=?, category=? " +
                "where id= ?";
            pstmt=theConnection.prepareStatement(query);
            pstmt.setString(1, ib.getItem());
            pstmt.setDouble(2, ib.getRate());
            //added for vat changes.
            pstmt.setDouble(3, ib.getVat());
            pstmt.setString(4, ib.getCat());
            pstmt.setInt(5, itemid);
            return pstmt.executeUpdate();
            
    } 
      
     public int deleteItem(int itemid)throws SQLException{
       query="delete from itemtable where id=?";
           pstmt=theConnection.prepareStatement(query);
           pstmt.setInt(1, itemid);
          return pstmt.executeUpdate();
    }
    
     public int deleteOrder(String userid)throws SQLException{
       query="DELETE FROM OrderTable WHERE NOT (SUBSTRING(OrderID, 1, 4) = '"+userid+"')";
           pstmt=theConnection.prepareStatement(query);
          return pstmt.executeUpdate();
    } 
     
      public List<ReportBean> todayReport() {
       ReportBean rib ;
       List<ReportBean> ritems = new ArrayList<ReportBean>();
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
      }}
        catch(SQLException ex){ex.getMessage();}
        finally {if(pstmt!=null){try { pstmt.close(); }catch (SQLException e) { e.printStackTrace();}}}
        return ritems;
    }
      
      public List<ReportBean> userWiseReport(String userid) {
       ReportBean rib ;
       List<ReportBean> ritems = new ArrayList<ReportBean>();
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
      }}
        catch(SQLException ex){ex.getMessage();}
        finally {if(pstmt!=null){try { pstmt.close(); }catch (SQLException e) { e.printStackTrace();}}}
        return ritems;
    }
      
      public List<ReportBean> dayWiseReport() {
       ReportBean rib ;
       List<ReportBean> ritems = new ArrayList<ReportBean>();
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
      }}
        catch(SQLException ex){ex.getMessage();}
        finally {if(pstmt!=null){try { pstmt.close(); }catch (SQLException e) { e.printStackTrace();}}}
        return ritems;
    }
     
       public List<ReportBean> dayWiseSumReport() {
       ReportBean rib ;
       List<ReportBean> ritems = new ArrayList<ReportBean>();
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
      }}
        catch(SQLException ex){ex.getMessage();}
        finally {if(pstmt!=null){try { pstmt.close(); }catch (SQLException e) { e.printStackTrace();}}}
        return ritems;
    }

       //added on 29th jan
        public List<ReportBean> dateRangeReport(String startdate, String enddate) {
       ReportBean rib ;
       List<ReportBean> ritems = new ArrayList<ReportBean>();
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
      }}
        catch(SQLException ex){ex.getMessage();}
        finally {if(pstmt!=null){try { pstmt.close(); }catch (SQLException e) { e.printStackTrace();}}}
        return ritems;
    }
      
    public String getPwd(String user){
        String pwd="";
     try{ 
        query = "select password from login where username=? ";
        pstmt = theConnection.prepareStatement(query);
        pstmt.setString(1,user);
        theResult = pstmt.executeQuery();
        theResult.next();
        pwd = theResult.getString("password");
      }
        catch(SQLException ex){ex.getMessage();}
        finally {if(pstmt!=null){try { pstmt.close(); }catch (SQLException e) { e.printStackTrace();}}}
        return pwd;
    }
    
    public int getBillNobyDate() {
        int billno=0;
    
        try{
        query = "SELECT count(distinct orderid) as billno " +
                "FROM         OrderTable " +
                "WHERE     (DATEPART(dd, OrderDate) = DATEPART(dd, GETDATE())) AND (DATEPART(mm, OrderDate) = DATEPART(mm, GETDATE())) " +
                "AND (DATEPART(yyyy, OrderDate)= DATEPART(yyyy, GETDATE()))";
        pstmt = theConnection.prepareStatement(query);
        theResult = pstmt.executeQuery();
        theResult.next();
        billno = theResult.getInt("billno");
        }catch(SQLException ex){ex.getMessage();}
        finally {if(pstmt!=null){try { pstmt.close(); }catch (SQLException e) { e.printStackTrace();}}}
        return billno;
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
        }catch(SQLException ex){ex.getMessage();}
        finally {if(pstmt!=null){try { pstmt.close(); }catch (SQLException e) { e.printStackTrace();}}}
        return billno;
    }


     public Double calPEV(Double rate, Double vat){
        Double pev = 0.0;
        pev=Round(((rate*100)/(100 + vat)));

        return pev;
    }

   public static Double Round(Double amt) {
        return (double)Math.round(amt*100)/100;
    }


    public java.sql.Date stringToDate(String anydate){
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       java.util.Date dt=null;
       try {
        dt = (java.util.Date)sdf.parse(anydate);
       } catch (Exception e) { }
       return new java.sql.Date(dt.getTime());
   }


    public Map<Double, Double> getDistinctVatAmt(List<OrderBean> orderList){
        Map<Double, Double> distVatAmt = new HashMap<Double, Double>();
        Double vatAmt = 0.0;
        //double vatRate = 0.0;
        Iterator<OrderBean> i = orderList.iterator();
        while(i.hasNext()){
            OrderBean ob = (OrderBean)i.next();
            if(!distVatAmt.containsKey(ob.getVat())){
                vatAmt = ob.getPrice()-ob.getPriceExVat();
                distVatAmt.put(ob.getVat(), vatAmt);
            } else {
               vatAmt = (Double)distVatAmt.get(ob.getVat());
               vatAmt += ob.getPrice()-ob.getPriceExVat();
               distVatAmt.put(ob.getVat(), vatAmt);
            }
        }
        return distVatAmt;
    }
}
