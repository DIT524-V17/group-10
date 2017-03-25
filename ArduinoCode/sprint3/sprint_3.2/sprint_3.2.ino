//LIBS
#include <Smartcar.h>


#define led 5
#define SRPC Serial
#define SRBT Serial3

//OBJECTS
Gyroscope gyro;
Car car;
SR04 SonarSensor,BackSonarSensor;


//VARIABLES
char command;
String string = "";
boolean ledon = false;
int speedd = 33;
boolean obsAvoid = false;
boolean obsAvoid2 = false;
const int TRIGGER_PIN = 6; //pins for SonarSensor (5,6)
const int ECHO_PIN = 5;
const int TRIGGER_PIN2 = 35; //pins for BackSonarSensor (35,37)
const int ECHO_PIN2 = 37;

unsigned int distance,distance2;


  //INI
  void setup()
  {
    SRPC.begin(9600);
    SRBT.begin(9600);
    
    pinMode(LED_BUILTIN, OUTPUT);
    gyro.attach();
    gyro.begin();
    car.begin(gyro);
    SonarSensor.attach(TRIGGER_PIN, ECHO_PIN);
    BackSonarSensor.attach(TRIGGER_PIN2, ECHO_PIN2);
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
    }
     if(string =="RI")
    {
        right();
        ledon = true;
        digitalWrite(36, LOW);
    }
     if(string =="LE")
    {
        left();
        ledon = true;
        digitalWrite(36, LOW);
    }
     if(string =="RO")
    {
       leftRotate();
    }
     if(string =="OR")
    {
      rightRotate();
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
      obsAvoid2 = true;  
    }
    if(string == "OF"){
      
      obsAvoid = false;     
      obsAvoid2 = false;
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
    if(obsAvoid2){
      distance2 = BackSonarSensor.getDistance();
      car.getSpeed();
      if (distance2 <= 15 && distance2 >0) {
        car.setSpeed(0);
        car.getSpeed();
      }else{
         if(string =="BO")
        {
        movebackward();
        ledon = true;
        digitalWrite(36, LOW);
        }
      }
    }else{
      if(string == "BO")
      {
         movebackward();
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
 void leftRotate(){
  car.setMotorSpeed(-(speedd), speedd);
  delay(10);
}
void rightRotate(){
  car.setMotorSpeed(speedd, -(speedd));
  delay(10);
}
