import java.sql.*;  
 
public class JdbcSelectTest {   
   public static void main(String[] args) {
      try (
         // Allocate a database 'Connection' object
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/employees?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "root", "");   // For MySQL only

 
         // Allocate a 'Statement' object in the Connection
         Statement stmt = conn.createStatement();
      ) {
         // Execute a SQL SELECT query. The query result is returned in a 'ResultSet' object.
         String strSelect = "SELECT * FROM employees";
         System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging
 
         ResultSet rset = stmt.executeQuery(strSelect);
 
         // Process the ResultSet by scrolling the cursor forward via next().
         //  For each row, retrieve the contents of the cells with getXxx(columnName).
         System.out.println("The records selected are:");
         int rowCount = 0;
         while(rset.next()) {   // Move the cursor to the next row, return false if no more row
            String name = rset.getString("first_name");
            String lname = rset.getString("last_name");
            int    id   = rset.getInt("emp_no");
            System.out.println(id + ", " + name  + ", " + lname);
            ++rowCount;
         }
         System.out.println("Total number of records = " + rowCount);
 
      } catch(SQLException ex) {
         ex.printStackTrace();
      }  // Close conn and stmt - Done automatically by try-with-resources (JDK 7)
   }
}
