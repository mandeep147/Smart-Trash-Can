package run;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mysql.jdbc.Statement;


/**
 * Servlet implementation class dbavg
 */
@WebServlet("/dbavg")
public class dbavg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dbavg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	       String json ="";
			PrintWriter out = response.getWriter();
			List <avgdb> lc= new ArrayList<avgdb>();
			
			//place the from and to date
			//String fname = request.getParameter("fname");
			//String mname = request.getParameter("mname");
			
			
			
			Connection con = null;   
			Connection con1=null;
			try {
				//connect to main db 
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				//sensor data sql db
				con= DriverManager.getConnection("jdbc:mysql://us-cdbr-iron-east-03.cleardb.net/ad_dd01ddb80a60ea4","be49496a14c234","dba23021");
				//avgdb data sql db 
				con1= DriverManager.getConnection("jdbc:mysql://us-cdbr-iron-east-03.cleardb.net/ad_3406a67d5e3fe5e","	b0c7c38b3abee5","d2503269 ");
			} catch(Exception e) {
				e.printStackTrace();
			}
			Statement stmt = null;
			//fetch average from main db query
			//add the new rows to the another db using query 1
			String query ="select sensorid,avg(thresholdtime1) as avgt1 from sensordb group by sensorid"; 
          
			try {
				stmt = (Statement) con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					String sensorid = rs.getString("sensorid");
					int avgthreshold1 = rs.getInt("agvt1");
					//int avgthreshold2 = rs.getInt("avgt2");
					
					//need to pass to the temp database say dbavg
					//1. connect to the new db and then pass variables con1 will take the address
					avgdb avginfo = new avgdb(sensorid,avgthreshold1);
				    lc.add(avginfo);
				    
				    
				    //add the values to db
				  String query1 = " INSERT INTO dbavg (sensorid,avgthreshold1)"
							+ " values (?, ?)";

					// create the mysql insert preparedstatement
					PreparedStatement preparedStmt = (PreparedStatement) con1.prepareStatement(query1);
					preparedStmt.setString (1, sensorid);
					preparedStmt.setInt (2, avgthreshold1);
					//preparedStmt.setInt (3, avgthreshold2);
					
					// execute the preparedstatement
					preparedStmt.execute();   
				    				    
				}
				Gson gson = new Gson();
				 json = gson.toJson(lc);
				System.out.println("Json string:"+json);
				
			} catch (SQLException ex ) {
				System.out.println(ex);  
				System.exit(1); 
			} finally {
				System.out.println("finish");
				out.println(json);
				
			}
		
		
	}

}
