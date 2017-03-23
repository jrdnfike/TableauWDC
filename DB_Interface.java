package WDC;

import java.sql.*;

/**
 * A class to interface with a local MySQL database
 * @author Jordan Fike
 */
public class DB_Interface {

    private String dbURL;
    private String dbUsername;
    private String dbPassword;

    //Store database connection info
    public DB_Interface()
    {
        dbURL = "jdbc:mysql://localhost:3306/my_database";
        dbUsername = "my_database_username";
        dbPassword = "my_database_password";
        
        //Register the JDBC driver
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } 
        catch (Exception ex) {
            
            System.out.println(ex.getMessage());
            
        }
    }
    
    //Run the given query using the database connection
    public String db_query(String query)
    {
        try
        {
            //Connect to the database
            Connection my_database_conn = DriverManager.getConnection(
                    dbURL, dbUsername, dbPassword);
            
            System.out.println("Executing DB query");
            
            //Initialize statement executor
            Statement to_execute = my_database_conn.createStatement();
            
            //Run the given query
            ResultSet query_result = to_execute.executeQuery(query);
            
            //Convert the result to a JSON format
            String JSON_result = ResultSetConverter.convert(query_result);
            
            //Close database connection objects
            to_execute.close();
            my_database_conn.close();
            
            return JSON_result;
        }
        
        catch (SQLException error)
        {
            System.out.println("Error encountered");
            System.out.println(error.getMessage());
            
            return null;
        }
        
    }
    
}
