package run;

public class avgdb {
 

	String sensorid;

	int avgthreshold1 ;
	//int avgthreshold2;

	public String getSensorid() {
		return sensorid;
	}

	public void setSensorid(String sensorid) {
		this.sensorid = sensorid;
	}

	public int getAvgthreshold1() {
		return avgthreshold1;
	}

	public void setAvgthreshold1(int avgthreshold1) {
		this.avgthreshold1 = avgthreshold1;
	}

	/*public int getavgthreshold2() {
		return avgthreshold2;
	}

	public void setAvgthreshold2(int avgthreshold2) {
		this.avgthreshold2 = avgthreshold2;
	}

	*/
	
	public avgdb(String sensorid,int avgthreshold1){
		this.sensorid=sensorid;
		this.avgthreshold1=avgthreshold1;
		//this.avgthreshold2=avgthreshold2;
		

		}

	
	
}
