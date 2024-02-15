package com.hibernatewithvertica.hibernateWithVerticaDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;


public class HibernateWithVerticaDbApplication {

    public static void main(String[] args) {   
//    	String createSchemaSQL = "CREATE SCHEMA IF NOT EXISTS vertica_db";
    	System.out.println("hello");
    	Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
//    	 Connection connection = DriverManager.getConnection(url, username, password);
//    	Statement statement = connection.createStatement()
    	System.out.println("in 14");
    	SessionFactory sessionFactory = configuration.buildSessionFactory();
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	queryInventory(session);
    	System.out.println("done");
    	
       
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
