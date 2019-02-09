package es.uma.khaos.rdf_endpoint.explorer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQLConnection {

	private Connection conn;
	private Statement stmt;
    private ResultSet rs;
    private String dbName;

	public MySQLConnection(String host, String port, String db, String user, String password) {
		conn=null;
		stmt=null;
		rs=null;
		dbName = db;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
        	System.out.println(ex.getMessage());
		}

		try {
		    conn =
		       DriverManager.getConnection("jdbc:mysql://"+host+":"+ port +"/" + db +"?" +
		                                   "user="+user+"&password="+password+ "&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	public String getDBName() {
		return dbName;
	}

	public ArrayList<String> getTableNames(){
		String row = "";
		ArrayList<String> result = new ArrayList<String>();
		String myQuery = "SELECT table_name FROM information_schema.tables  where table_schema='" +
				dbName +"';";
		try {
			stmt = conn.createStatement();
			if (stmt.execute(myQuery)) {
				rs = stmt.getResultSet();
			}
			while ( rs.next() ) {
                int numColumns = rs.getMetaData().getColumnCount();
                for ( int i = 1 ; i <= numColumns ; i++ ) {
                	row += rs.getObject(i) + "\t";
                }
                result.add(row);
                row = "";
            }
		} catch(SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
		}
		return result;
	}

	public ArrayList<String> selectAll(String table) {
		String myquery = "SELECT * FROM " + table;
		ArrayList<String> result = new ArrayList<String>();
		String row = "";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(myquery);

			if (stmt.execute(myquery)) {
				rs = stmt.getResultSet();
			}

			while ( rs.next() ) {
                int numColumns = rs.getMetaData().getColumnCount();
                for ( int i = 1 ; i <= numColumns ; i++ ) {
                	row += rs.getObject(i) + "\t";
                }
                result.add(row);
                row = "";
            }

		}catch(SQLException ex){
			return(result);
		}
		return(result);
	}

	public void insert(String table, String cols, String values) {
		String myquery = "INSERT INTO " + table + "(" + cols + ") VALUES (" + values + ");";
		try {
			stmt = conn.createStatement();
			System.out.println(myquery);
			stmt.executeUpdate(myquery);
			stmt.executeQuery("commit");

		}catch(SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	public void update(String table, String values, int id) {
		String myquery = "UPDATE " + table + " SET " + values + " WHERE id='" + id + "';";
		try {
			stmt = conn.createStatement();
			System.out.println(myquery);
			stmt.executeUpdate(myquery);
			stmt.executeQuery("commit");
		}catch(SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	public void delete(String table, String column, String value) {
		String myquery = "DELETE FROM " + table + " WHERE " + column + " = " + value + ";";
		try {
			stmt = conn.createStatement();
			System.out.println(myquery);
			stmt.executeUpdate(myquery);
			stmt.executeQuery("commit");
		}catch(SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
}
