//#include <Servo.h>
#include <Smartcar.h>

Gyroscope gyro;
Car car;
SR04 SonarSensor;

const int TRIGGER_PIN = 6;
const int ECHO_PIN = 5;

bool drive = true;

void setup() {

  gyro.attach();
  gyro.begin();
  SonarSensor.attach(TRIGGER_PIN, ECHO_PIN);
  car.begin(gyro);
  //Serial.begin(9600);
  car.setSpeed(30);
  drive = true;

}

void loop() {

  unsigned int distance = SonarSensor.getDistance();
  car.getSpeed();
  if (distance <= 15 && distance >0) {
    car.setSpeed(0);
    //Serial.println(SonarSensor.getDistance());
   // delay(100);
    drive = false;
    car.getSpeed();

  } if (drive = false) {
      car.getSpeed();
      car.setSpeed(0);
  }
}
