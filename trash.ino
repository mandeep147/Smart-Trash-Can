// This #include statement was automatically added by the Particle IDE.
#include "MQTT/MQTT.h"

//#include "HC_SR04/HC_SR04.h"
void callback(char * t, byte * payload, unsigned int length);

double distance = 0;
double duration=0;
int critical = 0;
int counter=0;
int percentage,heightTank, deviation;

int trigPin = D4;
int echoPin = D6;
String t1;
String t2=0;

byte ip[] = {54,213,225,111};
MQTT client(ip, 2882, callback);
// connection = mysql.createConnection();
void callback(char * t, byte * payload, unsigned int length)
{
    
}
void setup() 
{
    client.connect("fromSpark");
   
   Particle.variable("critical", critical);  
   pinMode(D6, INPUT);
   pinMode(D4, OUTPUT);
   digitalWrite(D4, LOW);
  // t1=Time.timeStr();
 
 //deviation=25;
}

void loop() 
{
 char payLoad[255];   
 digitalWrite(D4, LOW); 
delay(1);

 digitalWrite(D4, HIGH);
 delayMicroseconds(10); 
 
 digitalWrite(D4, LOW);
 //delayMicroseconds(10); 
 duration = pulseIn(D6, HIGH);
 
 
 //Calculate the distance (in cm) based on the speed of sound.
 distance = duration/58.2;
 Serial.println(distance);
//Serial.println(Time.timeStr());

    //delay(100);
    
    if(distance < 10 && counter > 5){
       counter=0; 
        sprintf(payLoad,"%s %d","01",(int) distance);
        //t2=Time.timeStr()-t1;
        Serial.println("hello");
        critical =1;
    
    
    if(client.isConnected())
    {
         Serial.println("hello");
        client.publish("/trashcan/alert",payLoad);
    }
    }
    counter++;
    //Serial.println(t2);
    delay(1000);
}