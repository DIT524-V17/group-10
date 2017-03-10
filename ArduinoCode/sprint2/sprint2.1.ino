#include <Smartcar.h>

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


  void setup()
  {
    Serial.begin(9600);
    pinMode(LED_BUILTIN, OUTPUT);
    gyro.attach();
    gyro.begin();
    car.begin(gyro);
    SonarSensor.attach(TRIGGER_PIN, ECHO_PIN);
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


