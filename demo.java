package jdbc;
import com.mysql.cj.jdbc.Driver;
import java.sql.*;
import java.util.Scanner;
class operation{
	public void insertValuesIntoTable() throws Exception{
		Connection connection = null;
		Statement statement = null;
		int resultSet = 0;
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		System.out.println("Driver registered succesfully");
		String url = "jdbc:mysql://localhost:3306/school" ;
		String username = "root";
		String password = "root@123";
		Scanner scan = new Scanner(System.in);
		System.out.println("enter id:");
		int id = scan.nextInt();
		System.out.println("enter nams:");
		String nams = scan.next();
		System.out.println("enter age:");
		int age = scan.nextInt();
		System.out.println("enter address:");
		String address = scan.next();
		scan.close();
		try {
			connection = DriverManager.getConnection(url,username,password);
			System.out.println("connection");
			if(connection!=null) {
				statement = connection.createStatement();
				System.out.println("statement");
				if(statement!=null) {
					String sqlSelectQuery =String.format("insert into student values('%d','%s','%d','%s') ",id,nams,age,address);
					resultSet = statement.executeUpdate(sqlSelectQuery);
					
					if(resultSet!=0) {
						System.out.println("SID\tSNAME\tSAGE\tSADDR");
						System.out.println("Inserted no of rows"+resultSet);
						}
						
					}
					
				}
		}
		catch(SQLException e) {
			e.printStackTrace();
				
		}
		catch(Exception sc) {
			sc.printStackTrace();
		}
		finally {
			if(statement!=null) {
				statement.close();
			}
			if(connection!=null) {
				connection.close();
			}
		}
		System.out.println("Closing the connection...");
		
	}
	public void readValuesIntoTable() throws Exception {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		System.out.println("Driver registered succesfully");
		String url = "jdbc:mysql://localhost:3306/school" ;
		String username = "root";
		String password = "root@123";
		try {
			connection = DriverManager.getConnection(url,username,password);
			System.out.println("connection");
			if(connection!=null) {
				statement = connection.createStatement();
				System.out.println("statement");
				if(statement!=null) {
					String sqlSelectQuery ="select * from student";
					resultSet = statement.executeQuery(sqlSelectQuery);
					
					if(resultSet!=null) {
						System.out.println("SID\tSNAME\tSAGE\tSADDR");
						
						while(resultSet.next()){
							Integer id  = resultSet.getInt(1);
							String  name = resultSet.getString(2);
							Integer age = resultSet.getInt(3);
							String add = resultSet.getString(4);
							System.out.println(id+"\t"+name+"\t"+age+'\t'+add);
						}
						
					}
					
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
				
		}
		finally {
			if(resultSet!=null) {
				resultSet.close();
			}
			if(statement!=null) {
				statement.close();
			}
			if(connection!=null) {
				connection.close();
			}
		}
		System.out.println("reading operation completed");
	}
	public void updateValuesIntoTable() throws Exception {
		Connection connection = null;
		Statement statement = null;
		int resultSet = 0;
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		System.out.println("Driver registered succesfully");
		String url = "jdbc:mysql://localhost:3306/school" ;
		String username = "root";
		String password = "root@123";
		try {
			connection = DriverManager.getConnection(url,username,password);
			System.out.println("connection");
			if(connection!=null) {
				statement = connection.createStatement();
				System.out.println("statement");
				if(statement!=null) {
					String sqlSelectQuery = "Update student set age = 15 where nams= 'rupa'";
					resultSet = statement.executeUpdate(sqlSelectQuery);
					if(resultSet!=0) {
						System.out.println("no of rows affected"+resultSet);
						}
						
					}
					
				}
		}
		catch(SQLException e) {
			e.printStackTrace();
				
		}
		catch(Exception sc) {
			sc.printStackTrace();
		}
		finally {
			if(statement!=null) {
				statement.close();
			}
			if(connection!=null) {
				connection.close();
			}
		}
		System.out.println("updation done..");
		
	}
		
	public void deleteValuesIntoTable() throws Exception{
		Connection connection = null;
		Statement statement = null;
		int resultSet = 0;
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		System.out.println("Driver registered succesfully");
		String url = "jdbc:mysql://localhost:3306/school" ;
		String username = "root";
		String password = "root@123";
		try {
			connection = DriverManager.getConnection(url,username,password);
			System.out.println("connection");
			if(connection!=null) {
				statement = connection.createStatement();
				System.out.println("statement");
				if(statement!=null) {
					String sqlSelectQuery = "delete from student where age = 39 ";
					resultSet = statement.executeUpdate(sqlSelectQuery);
					System.out.println("no of rows deleted"+resultSet);
				}
						
			}
					
		}
		
		catch(SQLException e) {
			e.printStackTrace();
				
		}
		catch(Exception sc) {
			sc.printStackTrace();
		}
		finally {
			if(statement!=null) {
				statement.close();
			}
			if(connection!=null) {
				connection.close();
			}
		}
		System.out.println("deletion completed...");
		
	}
	
}
public class demo{
	public static void main(String[] args)throws Exception {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("DO YOU WANT TO PERFORM OPERATION true/false");
			Boolean b = sc.nextBoolean();
			if(b) {
				System.out.println("Select '1':INSERT, '2':READ ,'3':UPDATE,'4':DELETE");
				int num = sc.nextInt();
				operation o = new operation();
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
			}
			else {
					System.out.println("exit");
					break;
			}
		}
	}
}































