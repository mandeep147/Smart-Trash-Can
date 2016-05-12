public class Countries {

public Countries(float lat,float lon, int threshold)
{
	this.setLatitude(lat);
	this.setLongitude(lon);
	this.setThreshold(threshold);
}

public Countries() {
}
public int threshold;
public float lat;
public float lon;



public void setLatitude(float lat) {
this.lat = lat;
}
public float getLatitude() {
	return lat;
}

public void setLongitude(float lon) {
this.lon= lon;
}
public float getLongitude() {
return lon;
}
public void setThreshold(int t) {
this.threshold = t;
}
public int getThreshold() {
	return threshold;
}
}