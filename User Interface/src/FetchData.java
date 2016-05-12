//import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FetchData {
private static Connection connection = null;
public static Connection getConnection() {
if (connection != null)
return connection;
else {
try {
	try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    connection= DriverManager.getConnection("jdbc:mysql://gpsdb.cjdsel9fsb9g.us-west-1.rds.amazonaws.com:3306/gpsdb","sharat","abcdxyz123");
	System.out.println("Connection Established");
}catch (ClassNotFoundException e) {
e.printStackTrace();
}catch (SQLException e) {
e.printStackTrace();
}
return connection;
}
}
public static ArrayList<Countries> getAllCountries() {
connection = FetchData.getConnection();
System.out.println(connection);
ArrayList<Countries> countryList = new ArrayList<Countries>();
try {
Statement statement = connection.createStatement();
System.out.println("inside countries");
ResultSet rs = statement.executeQuery("SELECT Latitude,Longitude,Threshold FROM sensor_info");
ResultSetMetaData rsmd = rs.getMetaData();
System.out.println("querying SELECT * FROM XXX");
int columnsNumber = rsmd.getColumnCount();
while (rs.next()) {
    for (int i = 1; i <= columnsNumber; i++) {
        if (i > 1) System.out.print(",  ");
        String columnValue = rs.getString(i);
        System.out.print(columnValue + " " + rsmd.getColumnName(i));
    }
    System.out.println("");
    Countries country=new Countries();
    country.setLongitude(rs.getFloat("Longitude"));
    country.setLatitude(rs.getFloat("Latitude"));
    country.setThreshold(rs.getInt("Threshold"));
    countryList.add(country);

}
/*for(int i=0;i<countryList.size();i++)
	System.out.println("value\n"+countryList.get(i));*/
}catch(SQLException e) {
e.printStackTrace();
}
return countryList;
} 
}
