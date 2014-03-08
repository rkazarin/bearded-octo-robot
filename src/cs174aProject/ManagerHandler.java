package cs174aProject;
import java.sql.*;
import java.util.Scanner;


public class ManagerHandler extends UserHandler {

	
	public ManagerHandler(String dbDescription, String dbUser, String dbPassword){
		super(true, false, dbDescription, dbUser, dbPassword);	}
	
	public int changeItemPrice(int stock_id, float new_price)
	{
		StringBuilder myQuery = new StringBuilder(150);
		myQuery.append("UPDATE emart_catalog SET prices = ");
		myQuery.append(new_price);
		myQuery.append(" WHERE stock_num = ");
		myQuery.append(stock_id);
		//myQuery.append(";");
		return myDB.executeUpdate(myQuery.toString());
		
	}
	
	public boolean checkManagerLogin()
	{
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter manager username: ");
		String username = scan.nextLine();
		System.out.print("Enter manager password: ");
		String password = scan.nextLine();
		
		String correctPassword = "";
		String isManager = "";
		
		try{
			Statement stmt = myDB.db_conn.createStatement();
			StringBuilder myQuery = new StringBuilder(150);
			myQuery.append("SELECT C.password, C.manager");
			myQuery.append(" FROM EMART_CUSTOMERS C");
			myQuery.append(" WHERE C.customer_ID = " + "\'" + username + "\'");
			
			ResultSet rs = stmt.executeQuery(myQuery.toString());
			
			while(rs.next())
			{
				correctPassword  = rs.getString("PASSWORD");
				System.out.println(correctPassword);
				isManager = rs.getString("MANAGER");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		if(password.equals(correctPassword) && isManager.equals("TRUE"))
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	
	public boolean processInput()
	{
		return true;
		
	}	
}