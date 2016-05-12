package trashcancontroller;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.io.*;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;

import com.mysql.jdbc.Connection;



public class trashcancontroller implements MqttCallback{
	MqttClient client;
	String MosquittoBrokerUrl = "tcp://54.213.225.111:2882";
	public static void main(String[] args) {
		new trashcancontroller().demo();
		 	}
	@Override
	public void connectionLost(Throwable cause) {
	    // TODO Auto-generated method stub

	}
	public void demo()
	{
		try {
	    	
	        client = new MqttClient(MosquittoBrokerUrl, "Sending");
	        client.connect();
	        client.setCallback(this);	
	        client.subscribe("/trashcan/alert");
	        System.in.read();
	        client.disconnect();
	        client.close();
	    } catch (MqttException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	@Override
	public void messageArrived(String topic, MqttMessage message)
	        throws Exception {	
		System.out.println(topic);
		System.out.println(message); 
		String s = message.toString();
		String val[] = s.split(" ");
		System.out.println(val[0] + " " + val[1]);
		
		Connection con = null;  
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con= (Connection) DriverManager.getConnection("jdbc:mysql://dbtrigger.cjdsel9fsb9g.us-west-1.rds.amazonaws.com:3306/innodb","sharat","abcdxyz1234");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		String query2= 
				"UPDATE triggerdb SET alert=1 WHERE sensorid = ?";
		PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query2);
		preparedStmt.setString (1, "1");
		preparedStmt.execute();
		}
	
	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
	    // TODO Auto-generated method stub

	}
}
