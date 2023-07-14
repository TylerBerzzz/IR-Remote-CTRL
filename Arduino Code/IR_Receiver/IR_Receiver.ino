//Developer: Tyler Bershad
//Last Modified: 9/25/2016
//Description: This code recieves IR transmissions and decodes the results within the Serial Monitor. The values can be stored for future IR transmission in another script. 

//Acknowledgements: IRremote Library for make all of this possible. THANK YOU!

#include <IRremote.h>
#include <IRremoteInt.h>

int receive_pin= A0;

IRrecv irrecv(receive_pin);

decode_results results;

void setup()
{
  Serial.begin(9600);
  irrecv.enableIRIn(); // Start the receiver
}

void loop() {
  if (irrecv.decode(&results)) {
    Serial.println(results.value, HEX);
    irrecv.resume(); // Receive the next value
  }
}
