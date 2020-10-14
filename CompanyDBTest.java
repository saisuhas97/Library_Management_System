/**
 *  NAME: CompanyDBTest.java
 *  AUTHOR: Chris Irwin Davis
 *          chrisirwindavis@utdallas.edu
 *          The Univeristy of Text at Dallas
 *  DATE: 8 JAN 2016
 *
 *  DESCRIPTION: This Java stub code is an example of how to connect to,
 *               query, and manipulate a MySQL database. This example is
 *               designed to work with the COMPANY database from the textbook
 *               "Fundamentals of Databse Design, 7/E" by Elmasri and Navathe.
 */


import java.sql.*;

public class CompanyDBTest {
    static Connection conn = null;
        /**
         * @param args
         */
        public static void main(String[] args) {
			// Initialize variables for fields by data type
			String ssn;
			String firstName;
			String m;
			String lastName;
			String address;
			String dno;
			/* The Java dno variable may also be type int
			 * Note that the SQL data type may be different
			 * than the wrapper language data type!!
			 */
			// int dno; 
			String salary;
			int supers;

			int linect = 0;

			try {
				/* Create a connection to the local MySQL server, with the "company" database selected. */
				//        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "mypassword");
				//
				/* Create a connection to the local MySQL server, with the NO database selected. */
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "swordfish");

				/* Create a SQL statement object and execute the query. */
				Statement stmt = conn.createStatement();
        
				/* Set the current database, if not already set in the getConnection */
				/* Execute a SQL statement */
				stmt.execute("USE Company;");

				/* Execute a SQL query using SQL as a String object */
				/* 
				 * Only the columns listed in the query will be available in the ResultSet object
				 * Note: If 'AS' is used to alias column name, these will be the names in the ResultSet object
				 */
				ResultSet rs = stmt.executeQuery("SELECT Ssn, fname, minit, lname, dno, salary, super_ssn FROM employee;");
				/* Alternatively, 'SELECT *' could be used */
				// ResultSet rs = stmt.executeQuery("SELECT * FROM employee;");

				/* Iterate through the result set using ResultSet class's next() method */
				while (rs.next()) {
					// Keep track of the line/tuple count
					linect++;
					// Populate field variables

					firstName = rs.getString("fname");
					m = rs.getString("minit");
					lastName = rs.getString("lname");
					ssn = rs.getString("Ssn");
					/* Alternative attribute value retrieval by column number */
					// column numbers are the same order as the SELECT statement from executeQuery
					// column numbers are integers that begin with 1
					// ssn = rs.getString(1);
					dno = rs.getString("dno");
					/* Alternative data type retrievals for Dno */
					// dno = rs.getInt("dno");
					// dno = Integer.parseInt(rs.getString("dno"));
					salary = rs.getString("salary");
					supers = rs.getInt("super_ssn");

					// Do something with the data
					System.out.print(linect + ")\t");
					System.out.print(ssn + "\t");
					System.out.print(firstName + "\t");
					System.out.print(lastName + "\t");
					System.out.print(dno + "\t");
					System.out.print(salary + "\t");
					System.out.print(supers + "\t");
					System.out.println();
				} // End while(rs.next())

				// Always close the recordset and connection.
				rs.close();
				conn.close();
				System.out.println("Success!!");
			} 
			catch(SQLException ex) {
				System.out.println("Error in connection: " + ex.getMessage());
			}
		}

	/*
	 *
	 */
	static void newln() {
		System.out.println();
	}
}