#include <VMA430_GPS.h>
#include <SoftwareSerial.h>

SoftwareSerial ss(3, 2);
VMA430_GPS gps(&ss);

void setup()
{
  Serial.begin(9600);
  gps.begin(9600);
  gps.setUBXNav();
  pinMode(13, OUTPUT);

}

void loop()
{
  if (gps.getUBX_packet())
  {
    gps.parse_ubx_data();
    if (gps.utc_time.valid)
    {
      digitalWrite(13, HIGH); 
      Serial.print("{\"long\": ");
      Serial.print(gps.location.latitude, 8);
      Serial.print(", \"lat\": ");
      Serial.print(gps.location.longitude, 8);
      Serial.print("}");
      Serial.println();
    } else {
      digitalWrite(13, LOW); 
      delay(300);
    }
  }
}
