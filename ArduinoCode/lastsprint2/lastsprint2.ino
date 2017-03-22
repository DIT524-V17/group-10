//LIBS
#include <Smartcar.h>


#define led 5
#define SRPC Serial
#define SRBT Serial3

//OBJECTS
Gyroscope gyro;
Car car;
SR04 SonarSensor;


//VARIABLES
char command;
String string = "";
boolean ledon = false;
int speedd = 33;
boolean obsAvoid = false;
const int TRIGGER_PIN = 6; //pins for SonarSensor (5,6)
const int ECHO_PIN = 5;

unsigned int distance;

  //INI
  void setup()
  {
    SRPC.begin(9600);
    SRBT.begin(9600);
    
    pinMode(36, OUTPUT);
    gyro.attach();
    gyro.begin();
    car.begin(gyro);
    SonarSensor.attach(TRIGGER_PIN, ECHO_PIN);
  }

  void loop()
  {  
    if (SRBT.available() > 0) 
    {string = "";}
    while(SRBT.available() > 0)
    {
      command = ((byte)SRBT.read());
      
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
    
    if(string =="TF")
    {
        stopcar();
        ledon = false;
        digitalWrite(36, HIGH);
        SRBT.println(string);
    }
     if(string =="BO")
    {
        movebackward();
        ledon = true;
        digitalWrite(36, LOW);
        SRBT.println(string);
    }
     if(string =="RI")
    {
        right();
        ledon = true;
        digitalWrite(36, LOW);
        SRBT.println(string);
    }
     if(string =="LE")
    {
        left();
        ledon = true;
        digitalWrite(36, LOW);
        SRBT.println(string);
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
      
      obsAvoid = true;   
    }
    if(string == "OF"){
      
      obsAvoid = false;     
    }

    if(obsAvoid){
      distance = SonarSensor.getDistance();
      car.getSpeed();
      if (distance <= 15 && distance >0) {
        car.setSpeed(0);
        car.getSpeed();
      }else{
        if(string == "TO")
        {
           moveforward();
           ledon = true;
           digitalWrite(36, LOW);
        }
      }
    }else{
      if(string == "TO")
      {
        
        moveforward();
        ledon = true;
        digitalWrite(36, LOW);
      }
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
      digitalWrite(36,HIGH);
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
