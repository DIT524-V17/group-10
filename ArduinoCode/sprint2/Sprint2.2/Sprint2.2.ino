#include <Smartcar.h>
#include <SharpIR.h>

#define ir A0
#define model 1080
int maxDistance = 0;
int temp = 0;

SharpIR sharp(ir, 25, 93, model);

// ir: the pin where your sensor is attached
// 25: the number of readings the library will make before calculating a mean distance
// 93: the difference between two consecutive measurements to be taken as valid
// model: an int that determines your sensor:  1080 for GP2Y0A21Y
//                                            20150 for GP2Y0A02Y
//                                            (working distance range according to the datasheets)

Gyroscope gyro;
Car car;
SR04 SonarSensor;
char command;
String string;
boolean ledon = false;
#define led 5
int speedd = 33;
boolean obsAvoid = true;
const int TRIGGER_PIN = 6; //pins for SonarSensor (5,6)
const int ECHO_PIN = 5;
boolean collisionAvoid = true;


  void setup()
  {
    Serial.begin(9600);
    pinMode(LED_BUILTIN, OUTPUT);
    gyro.attach();
    gyro.begin();
    car.begin(gyro);
    SonarSensor.attach(TRIGGER_PIN, ECHO_PIN);
    pinMode (ir, INPUT);
  }

  void loop()
  {  
    
    
    if (Serial.available() > 0) 
    {string = "";}
    
    while(Serial.available() > 0)
    {
      command = ((byte)Serial.read());
      
      if(command == ':')
      {
        break;
      }
      
      else
      {
        string += command;
      }
      
      delay(1);
    }
    
    if(string == "TO")
    {
        moveforward();
        ledon = true;
    }
    
    if(string =="TF")
    {
        stopcar();
        ledon = false;
        Serial.println(string);
    }
     if(string =="BO")
    {
        movebackward();
        ledon = true;
        Serial.println(string);
    }
     if(string =="RI")
    {
        right();
        ledon = true;
        Serial.println(string);
    }
     if(string =="LE")
    {
        left();
        ledon = true;
        Serial.println(string);
    }
     if(string =="S1")
    {
        speedd=33;
    }
    if(string =="S2")
    {
        speedd=66;
    }
    if(string =="S3")
    {
       speedd=100;
    } 
     if(string == "ON"){
      
    boolean obsAvoid = true;   
    }
    if(string == "OF"){
      
    boolean obsAvoid = false;     
    }
    if(obsAvoid == true){
    obstacle();
    }

    
    if(string == "iO"){
    boolean collisionAvoid = true;
    }
    if(string == "iF"){
      boolean collisionAvoid = false;
    }
    if(collisionAvoid == true){
      collisionAvoidance();
    }
 }
 
void moveforward()
   {
      car.setSpeed(speedd);
      delay(10);
    }
 
 void stopcar()
 {
      car.setSpeed(0);
      delay(10);
 }
 void movebackward()
 {     
      car.setSpeed(-(speedd));
      delay(10);
 }
  void right()
 {
      car.rotate(1);
      delay(10);
 }
  void left()
 {
      car.rotate(-1);
      delay(10);
}
void obstacle(){
  unsigned int distance = SonarSensor.getDistance();
  car.getSpeed();
  if (distance <= 15 && distance >0) {
    car.setSpeed(0);
    //drive = false;
    car.getSpeed();
    }
   }
void collisionAvoidance(){
  unsigned long time1=millis();  // takes the time before the loop on the library begins
  
  int dis=sharp.distance();  // this returns the distance to the object you're measuring
  temp= sharp.distance();
  Serial.print("Mean distance: ");  // returns it to the serial monitor
  Serial.println(dis);
  
  unsigned long time2=millis()-time1;  // the following gives you the time taken to get the measurement
  Serial.print("Time taken (ms): ");
  Serial.println(time2);

   if(temp>maxDistance){
    maxDistance= temp;
   }
  if(dis < 15 && dis > 0){
      car.setSpeed(30);
      delay(1);
  }if(dis >= 15 || dis <= 0){
    car.stop();
    delay(1);
  }
  Serial.print(maxDistance);
   }


