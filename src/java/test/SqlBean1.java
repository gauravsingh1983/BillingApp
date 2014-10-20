package test;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.logging.Level;

public class SqlBean1 {
	
	private String drivernm = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String dataurl = "jdbc:microsoft:sqlserver://"+getCompName()+"\\SQLINST2:1433;databasename=Sweets";
        private String dataurlAdmin = "jdbc:microsoft:sqlserver://"+getCompName()+"\\SQLINST1:1433;databasename=Sweets";
	
	//private String dataurl1 = "jdbc:microsoft:sqlserver://10.144.2.130:1433;databasename=SWEETS";
	
	private String dataurl1 = "jdbc:sqlserver://laptop08;instanceName=MSSQLSERVER1;databasename=MSODLoadingOSSTest";
	//private String	dataurl1		= "jdbc:sqlserver://10.144.2.128:1433;databasename=SWEETS";
	Connection con = null;
	//Connection con1 = null;
//private String drivernm = "sun.jdbc.odbc.JdbcOdbcDriver";
	//private String dataurl = "jdbc:odbc:swt";
	
	public SqlBean1() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Connection makeConnection(){
	try{
		Class.forName(drivernm);
		con = DriverManager.getConnection(dataurl1,"sa","Fundw0rk51");
		
		}catch(Exception e){
		System.out.println(e);
		
		}
		return con;
	}

        public Connection makeConnectionAdmin(){
	try{
		Class.forName(drivernm);
		con = DriverManager.getConnection(dataurl1,"sa","Fundw0rk5");

		}catch(Exception e){
		System.out.println(e);

		}
		return con;
	}
	/*public Connection makeConnection2() throws ClassNotFoundException, SQLException
	{
		Class.forName(drivernm);
		con1 = DriverManager.getConnection(dataurl1,"cwtreportuser","t@lcwt123");
		return con1;
	}*/
	public void close()throws SQLException{
		con.close();
		//con1.close();
	}


         private static String getCompName()
     {
        String computername=null;
        try {
            computername = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            System.out.println("Computer name not found "+ex.getMessage());
        }
        return computername;
     }
         
        
       
         public static void main(String[] args)
		{
        	 SqlBean1 sqlBean1 = new SqlBean1();
        	 
        	 System.out.println("TEst"+ getCompName());
        	 
        	 System.out.println("Test Connection : " + sqlBean1.makeConnection());
        	 
		}


}