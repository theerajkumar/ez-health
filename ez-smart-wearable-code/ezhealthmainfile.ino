
#include <Wire.h>
#include "MAX30100_PulseOximeter.h"
 
#include "Wire.h"
#include "Adafruit_GFX.h"
#include "OakOLED.h"
#define REPORTING_PERIOD_MS 1000
OakOLED oled;
 int as;

PulseOximeter pox;
 
uint32_t tsLastReport = 0;
const int lm35_pin = A1;  /* LM35 O/P pin */
int temp_adc_val;
float temp_val;


 
const unsigned char bitmap [] PROGMEM=
{
0x00, 0x00, 0x00, 0x00, 0x01, 0x80, 0x18, 0x00, 0x0f, 0xe0, 0x7f, 0x00, 0x3f, 0xf9, 0xff, 0xc0,
0x7f, 0xf9, 0xff, 0xc0, 0x7f, 0xff, 0xff, 0xe0, 0x7f, 0xff, 0xff, 0xe0, 0xff, 0xff, 0xff, 0xf0,
0xff, 0xf7, 0xff, 0xf0, 0xff, 0xe7, 0xff, 0xf0, 0xff, 0xe7, 0xff, 0xf0, 0x7f, 0xdb, 0xff, 0xe0,
0x7f, 0x9b, 0xff, 0xe0, 0x00, 0x3b, 0xc0, 0x00, 0x3f, 0xf9, 0x9f, 0xc0, 0x3f, 0xfd, 0xbf, 0xc0,
0x1f, 0xfd, 0xbf, 0x80, 0x0f, 0xfd, 0x7f, 0x00, 0x07, 0xfe, 0x7e, 0x00, 0x03, 0xfe, 0xfc, 0x00,
0x01, 0xff, 0xf8, 0x00, 0x00, 0xff, 0xf0, 0x00, 0x00, 0x7f, 0xe0, 0x00, 0x00, 0x3f, 0xc0, 0x00,
0x00, 0x0f, 0x00, 0x00, 0x00, 0x06, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
};
 
void onBeatDetected()
{
Serial.println("Beat!");
oled.drawBitmap( 80, 20, bitmap, 28, 28, 1);
oled.display();
}
 
void setup()
{
  
Serial.begin(9600);


pinMode(10, INPUT); // Setup for leads off detection LO +
pinMode(11, INPUT); // Setup for leads off detection LO -
 
oled.begin();
oled.clearDisplay();
oled.setTextSize(1);
oled.setTextColor(1);
oled.setCursor(0, 0);
 
oled.println("Initializing pulse oximeter..");
oled.display();
Serial.print("Initializing pulse oximeter..");
 
if (!pox.begin()) {
Serial.println("FAILED");
oled.clearDisplay();
oled.setTextSize(1);
oled.setTextColor(1);
oled.setCursor(0, 0);
oled.println("FAILED");
oled.display();
for(;;);
} else {
oled.clearDisplay();
oled.setTextSize(1);
oled.setTextColor(1);
oled.setCursor(0, 0);
oled.println("SUCCESS");
oled.display();
Serial.println("SUCCESS");
}
pox.setOnBeatDetectedCallback(onBeatDetected);
}
 
void loop()
{

 
pox.update();
 
if (millis() - tsLastReport > REPORTING_PERIOD_MS)
{
Serial.print("Heart BPM:");
Serial.print(pox.getHeartRate());
Serial.print("-----");
Serial.print("Oxygen Percent:");
Serial.print(pox.getSpO2());
Serial.println("\n");
oled.clearDisplay();
oled.setTextSize(1);
oled.setTextColor(1);
oled.setCursor(60,0);
oled.println(pox.getHeartRate());
 
oled.setTextSize(1);
oled.setTextColor(1);
oled.setCursor(0, 0);
oled.println("Heart BPM");
 
oled.setTextSize(1);
oled.setTextColor(1);
oled.setCursor(0,8);
oled.println("Spo2");
 
oled.setTextSize(1);
oled.setTextColor(1);
oled.setCursor(60,8);
oled.println(pox.getSpO2());



oled.setTextSize(1);
oled.setTextColor(1);
oled.setCursor(0,20);
oled.println("Ecg");


  temp_adc_val = analogRead(lm35_pin);  /* Read Temperature */
  temp_val = (temp_adc_val * 4.88); /* Convert adc value to equivalent voltage */
  temp_val = (temp_val/10); /* LM35 gives output of 10mv/°C */
  Serial.print("Temperature = ");
  Serial.print(temp_val);
  Serial.print(" Degree Celsius\n");
//Wait for a bit to keep serial data from saturating


oled.setTextSize(1);
oled.setTextColor(1);
oled.setCursor(0,30);
oled.println("Temp");

oled.setTextSize(1);
oled.setTextColor(1);
oled.setCursor(30,30);
oled.println(temp_val);


tsLastReport = millis();

if((digitalRead(10) == 1)||(digitalRead(11) == 1)){
Serial.println('!');
}
else
{
oled.setTextSize(1);
oled.setTextColor(1);
oled.setCursor(30,20);
as=analogRead(A0);
oled.println(as);
Serial.print("Ecg value: ");
Serial.println(as);
}



delay(1);
oled.display();
}


}
