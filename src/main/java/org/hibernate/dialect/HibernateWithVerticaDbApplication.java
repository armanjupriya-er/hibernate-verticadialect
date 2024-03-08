package org.hibernate.dialect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;


public class HibernateWithVerticaDbApplication {

	 public static void main(String[] args) {   
//	    	String createSchemaSQL = "CREATE SCHEMA IF NOT EXISTS vertica_db";
	    	System.out.println("hello");
	    	Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
//	    	 Connection connection = DriverManager.getConnection(url, username, password);
//	    	Statement statement = connection.createStatement()
	    	System.out.println("in 14");
	    	SessionFactory sessionFactory = configuration.buildSessionFactory();
	    	Session session = sessionFactory.openSession();
	    	session.beginTransaction();
	    	String sql="INSERT INTO users_info(id, email, name, phoneNumber) VALUES (1, 'saif Ahmed', 'bila.doe@example.com', '1234567890')";
	    	String createSchema = "CREATE SCHEMA newschema1";
	    	String alterQuery ="ALTER TABLE users_info ADD Address VARCHAR";
	    	String deallocate="DEALLOCATE ALL";
	    	String createTableAs= "CREATE TABLE TestTable AS SELECT name, email FROM users_info";
	    	String updateQuery = "UPDATE users_info SET name = 'Alfred Schmidt' WHERE id = 1";
	    	String rollback="DELETE from users_info where email = 'FareedAhmed'";
	    	String truncateTable="TRUNCATE TABLE users_info";
	    	String droptable="DROP TABLE Premium_Customer";
	    	String dropSchema="DROP SCHEMA newschema1";
	    	String delete="DELETE FROM users_info WHERE id=1"; 
	    	String sqlQuery = "SELECT CURDATEANDTIME() AS currentDateTime";

//	    	String descQuery="desc users_info";
	    	String url ="jdbc:vertica://159.65.145.184:5433/dbadmin";
	    	String user="dbadmin";
	    	String password= "1234";
	    			
//	    	try (Connection connection = DriverManager.getConnection(url, user, password)) {
//	            // Create statement
//	            Statement statement = connection.createStatement();
//
//	            // Execute the INSERT statement
//     int rowsAffected = statement.executeUpdate(alterQuery);
//
//
//	            // Check if the insertion was successful
//        if (rowsAffected > 0) {
//             System.out.println("Insertion successful. " + rowsAffected + " row(s) affected.");
//         } else {
//             System.out.println("Insertion failed.");
//	            }
	    		 Query<Object> query = session.createQuery("SELECT sysdate() AS currentTimestamp");
	    		

	    	        // Execute the query and retrieve the result
	    	        Object result = query.uniqueResult();
	    	        
	    	        // Commit transaction
	    	        session.getTransaction().commit();
	    	        
	    	        // Close session
	    	        session.close();

	    	        // Print the result
	    	        System.out.println("Current Timestamp: " + result);
	    	        double number = -10.5;

	    	        // Calculate the absolute value of the number
	    	        double absValue = Math.abs(number);

	    	        // Print the absolute value
	    	        System.out.println("Absolute value of " + number + " is: " + absValue);
	    	    
//	 
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
	    	System.out.println("DONE");
	    
	}

	    	
	       
	    

	    private static void queryInventory(Session session) {
	        Query<Object[]> query = session.createQuery("SELECT a.class_desc, a.item_desc, SUM(b.units_received) " +
	                "FROM Item a, InventoryOrders b " +
	                "WHERE a.item_nbr = b.item_id " +
	                "GROUP BY a.class_desc, a.item_desc " +
	                "ORDER BY a.class_desc, a.item_desc", Object[].class);
	        
	       

	        // Iterate through query results using Iterator
	        query.stream().forEach(row -> {
	            String classDesc = (String) row[0];
	            String itemDesc = (String) row[1];
	            Long sumUnitsReceived = (Long) row[2]; // Assuming unitsReceived is of type Long

	            System.out.println("Class Desc: " + classDesc);
	            System.out.println("Item Desc: " + itemDesc);
	            System.out.println("Sum Units Received: " + sumUnitsReceived);
	        });

	        // Close the session
	        session.close();
	    }

	    
	}
