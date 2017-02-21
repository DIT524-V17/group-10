#include <Smartcar.h>

Gyroscope gyro;
Car car;
SR04 SonarSensor;
char command;
String string;
boolean ledon = false;
#define led 5

  void setup()
  {
    Serial.begin(9600);
    pinMode(LED_BUILTIN, OUTPUT);
    gyro.attach();
    gyro.begin();
    car.begin(gyro);
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
        ledOn();
        ledon = true;
    }
    
    if(string =="TF")
    {
        ledOff();
        ledon = false;
        Serial.println(string);
    }
    
    if ((string.toInt()>=0)&&(string.toInt()<=100))
    {
      if (ledon==true)
      {
        //analogWrite(LED_BUILTIN, string.toInt());
        car.setSpeed(string.toInt());
        Serial.println(string);
        delay(10);
      }
    }
 }
 
void ledOn()
   {
      car.setSpeed(30);
      delay(10);
    }
 
 void ledOff()
 {
      car.setSpeed(0);
      delay(10);
 }
 

    
