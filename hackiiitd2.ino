#include "FirebaseESP8266.h"
#include <ESP8266WiFi.h>
#include <Servo.h>

#define FIREBASE_HOST "hai-kya-ye.firebaseio.com"
#define FIREBASE_AUTH "Hz6q4tCnLNF4cuGAM8JZiMNelaYWWWO9ReYyMaDg"
#define WIFI_SSID "Redmi"
#define WIFI_PASSWORD "sk271199" 

//Define FirebaseESP8266 data object
FirebaseData firebaseData;

Servo armMimic;
int trainerIn = A0;
int trainVal = 0; 

void setup()
{
  pinMode(A0, INPUT);
  armMimic.attach(D4);
  Serial.begin(115200);
  armMimic.write(0);
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("Connecting to Wi-Fi");
  while (WiFi.status() != WL_CONNECTED)
  {
    Serial.print(".");
    delay(300);
  }
  Serial.println();
  Serial.print("Connected with IP: ");
  Serial.println(WiFi.localIP());
  Serial.println();

  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
 // Firebase.reconnectWiFi(true);
  
  Firebase.setString(firebaseData, "trig", "0");
  Firebase.setInt(firebaseData, "data", 0);
}


void loop() {
  int pos = 0;
  
  Serial.println();
  if (Firebase.getString(firebaseData, "trig")) { //check for "trig" node data

    if (firebaseData.stringData() == "0") {  //if data is 0
          Serial.println("Trig off");
          armMimic.write(0);
    }
    else if(firebaseData.stringData() == "1"){ //if data node is 0
      Serial.println("exercise 1");
       Firebase.setInt(firebaseData, "data", 50);
      for(pos = 0; pos<=120; pos+=1){
        armMimic.write(pos);
        delay(10);
      }
    }
    else if(firebaseData.stringData() == "2"){ //if data node is 2
       Firebase.setInt(firebaseData, "data", 70);
      Serial.println("exercise 2");
      armMimic.write(0);
       
      delay(500);
      armMimic.write(30);
       
      delay(500);
      armMimic.write(60);
       
      delay(500);
      armMimic.write(90);
       
      delay(500);
      armMimic.write(120);
       
      delay(500);
      
    }
    else if(firebaseData.stringData() == "4"){// if data node is 3
      Serial.println("remote");
      armMimic.write(0);
      trainVal = analogRead(trainerIn);
      armMimic.write(map(trainVal,0,1024,0,180)); 
            
    }
    else if(firebaseData.stringData() == "3"){// if data node is 3
      Serial.println("stop");
      armMimic.write(0);
       Firebase.setInt(firebaseData, "data", 47);
      
    }

  } else {
    Serial.println(firebaseData.errorReason());
  }
}
