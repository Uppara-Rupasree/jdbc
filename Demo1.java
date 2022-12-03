package jdbc;
import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.xdevapi.PreparableStatement;

import java.sql.*;
import java.util.Scanner;

class connect{
	public Connection getConnect()throws Exception{
		Connection c = null;
		String url = "jdbc:mysql://localhost:3306/school";
		String un = "root";
		String ps = "root@123";
		c = DriverManager.getConnection(url,un,ps);
		return c;
	}
	public void closable(Connection c ,Statement s,ResultSet r,Scanner su) throws SQLException{
		if(c!=null) {
			c.close();
		}
		if(s!=null) {
			s.close();
		}
		if(r!=null) {
			r.close();
		}
		if(su!=null){
			su.close();
		}
	}
	
	public void closable(Connection c, PreparedStatement ps, Scanner su) throws SQLException {
		// TODO Auto-generated method stub
		if(c!=null) {
			c.close();
		}
		if(ps!=null) {
			ps.close();
		}
		if(su!=null){
			su.close();
		}
		
	}
}

class opt{
	
	public void	readValuesIntoTable() throws Exception{
		Scanner sr = new Scanner(System.in);
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet r = null;
		connect ct = new connect();
		c = ct.getConnect();
		String q = "select * from student";
		try {
			if(c!=null) {
				ps = c.prepareStatement(q);
				if(ps!=null){
					r = ps.executeQuery(q);
					if(r!=null) {
						while(r.next()) {
							Integer id  = r.getInt(1);
							String  name = r.getString(2);
							Integer age = r.getInt(3);
							String add = r.getString(4);
							System.out.println(id+"\t"+name+"\t"+age+'\t'+add);
						}
						
					}
					
				}
			}
		}
		catch(Exception ep){
			ep.printStackTrace();
		}
		finally {
			ct.closable(c,ps,r,sr);
		}
	}
	
	
	
	public void  insertValuesIntoTable() throws Exception{
		Scanner si	= new Scanner(System.in);
		int id 			= si.nextInt();
		String name 	= si.next();
		int age 		= si.nextInt();
		String adrs 	= si.next();
		Connection c 	= null;
		PreparedStatement ps = null;
		int r 			= 0;
		connect ct 		= new connect();
		c = ct.getConnect();
		String q = "insert into student values(?,?,?,?)";
		try {
			if(c!=null) {
				ps =  c.prepareStatement(q);
				ps.setInt(1,id);
				ps.setString(2, name);
				ps.setInt(3,age);
				ps.setString(4,adrs);
				
				if(ps!=null) {
					r = ps.executeUpdate();
					if(r!=0){
						System.out.println("insertion completed"+r);
					}
					else {
						System.out.println("failed to insert data into table");
					}
					
				}
			}
		}
		catch(Exception ep){
			ep.printStackTrace();
		}
		finally {
			ct.closable(c,ps,si);
		}
	}
	
	
	
	public void updateValuesIntoTable() throws Exception{
		Scanner su = new Scanner(System.in);
		int wa	=su.nextInt();
		int sa 	= su.nextInt();
		Connection c = null;
		PreparedStatement ps= null;
		int r = 0;
		connect ct = new connect();
		String q = "update student set age = ? where age = ?";
		c = ct.getConnect();
		try {
			if(c!=null) {
				ps = c.prepareStatement(q);
				ps.setInt(1,wa);
				ps.setInt(2, sa);
				if(ps!=null){
					r = ps.executeUpdate();
					if(r!=0) {
						System.out.println("no.of.rows updated"+r);
					}
					else {
						System.out.println("value not found");
					}
				}
						
			}
		}
		catch(Exception ep){
			ep.printStackTrace();
		}
		finally {
			ct.closable(c,ps,su);
		}
	}
	
	public void deleteValuesIntoTable() throws Exception{
		Scanner sd = new Scanner(System.in);
		String sn = sd.next();
		Connection c = null;
		PreparedStatement ps= null;
		int	r =0;
		connect ct = new connect();
		c = ct.getConnect();
		String q = "delete from student where name = ? ";
		
		try {
			if(c!=null) {
				ps = c.prepareStatement(q);
				ps.setString(1,sn);
				if(ps!=null){
					r = ps.executeUpdate();
					if(r!=0) {
						System.out.println("no .of.deleted"+r);
					}
					else {
						System.out.println("Value not found");
					}
					
					
				}
						
			}
					
		}
		catch(Exception ep){
			ep.printStackTrace();
		}
		finally {
			ct.closable(c,ps,sd);
		}
	}
}


public class Demo1 {
	public static void main(String[] args)throws Exception {
		System.out.println("Select '1':INSERT, '2':READ ,'3':UPDATE,'4':DELETE");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		opt o = new opt();
		switch(num) {
			case 1:
							/*call READ METHOD*/
							o.insertValuesIntoTable();
							break;
			case 2:
							/*call READ METHOD*/
							o.readValuesIntoTable();
							break;
			case 3:
							/*call update method*/
							o.updateValuesIntoTable();
							break;
			case 4:
							/*call delete method*/
							o.deleteValuesIntoTable();
							break;
			default:
							System.out.println("provide crt input");			
		}			
	sc.close();
	}
}	



