//Developed by: Tyler Bershad
//Last Modified: 3/5/2017
//Description: This is the driving code behind the Hacknado Project. The IR communication controls fan and light properties within the system. 

#include <IRremote.h>
int receiver = 3;
int LED = 6;
int fan = 5;

int initialflow = 20;
int inc = 5;
int flow = 10;

/*-----( Declare objects )-----*/
IRrecv irrecv(receiver);           // create instance of 'irrecv'
decode_results results;            // create instance of 'decode_results'


void setup() {
  pinMode(receiver, OUTPUT);
  Serial.begin(9600);
  irrecv.enableIRIn(); // Start the receiver


}
void loop()
{
  if (irrecv.decode(&results)) {
    translateIR();
    analogWrite(fan, flow);
    if (flow > 255) {
      flow = 255;
    }
    if (flow < 0) {
      flow = 0;
    }
    irrecv.resume(); // Receive the next value
  }


}
void translateIR() // takes action based on IR code received

{

  switch (results.value)

  {

    case 0xF7C03F:
      Serial.println(" Power            "); // LED Power 
      //- Code to turn the LED ON/OFF
      analogWrite(LED, 255);
      flow = initialflow;
      break;

    case 0xF740BF:
      Serial.println(" Off             "); // Power Off
      digitalWrite(LED, LOW);
      flow = 0;
      break;

    case 0xF700FF:
      Serial.println(" Fan Up            "); // Brightness Up
      flow = flow + inc;
      Serial.println(flow);
      break;

    case 0xF7807F:
      Serial.println(" Fan Down           "); //Brightness Down
      Serial.println(flow);
      flow = flow - inc;
      break;

    default:
      Serial.println(" Not Configured   ");

  }
  delay(250); //wait 250 ms

}
//END translateIR
