import RPi.GPIO as GPIO
import time
import sys
import paho.mqtt.client as mqtt

GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)

# SOGLIA = 10 # cm

# LED  = 25
TRIG = 17
ECHO = 27

# GPIO.setup(LED, GPIO.OUT)
GPIO.setup(TRIG,GPIO.OUT)   # il trigger lo emettiamo noi e quindi è un output
GPIO.setup(ECHO,GPIO.IN)    # il segnale di ECHO è un input in quanto gestito automaticamente dal sensore (noi dobbiamo leggere quando torna basso)

GPIO.output(TRIG, False)   # TRIG parte LOW
print ('Waiting a few seconds for the sensor to settle')
time.sleep(2)

# --- MQTT Setup ---
MQTT_BROKER = "192.168.1.189"
MQTT_PORT = 1883
MQTT_TOPIC = "sensor/sonar"

client = mqtt.Client()
client.connect(MQTT_BROKER, MQTT_PORT, 60)


def loop():
    while True:
        GPIO.output(TRIG, True)    # invia impulsoTRIG
        time.sleep(0.00001)        # dura 10 us
        GPIO.output(TRIG, False)

        # attendi che ECHO parta e memorizza tempo
        while GPIO.input(ECHO)==0:
            pulse_start = time.time() # tempo in secondi
    
        # register the last timestamp at which the receiver detects the echo signal
        while GPIO.input(ECHO)==1:
            pulse_end = time.time()

        pulse_duration = pulse_end - pulse_start
        # velocità del suono ~= 340m/s -> distanza = v*t -> il nostro tempo misura un roundtrip -> distanza = v*t/2  
        distance = pulse_duration * 17165 # espressa in cm 
        distance = round(distance, 1)
    
        # if distance <= SOGLIA:
        #     GPIO.output(LED, GPIO.HIGH)
        # else:
        #     GPIO.output(LED, GPIO.LOW)

        # print(f"Distance: {distance} cm")
        # sys.stdout.flush()   # Importante! (immagino che se no l'os bufferizzi)
        client.publish(MQTT_TOPIC, str(distance))  
        time.sleep(0.25) 

   
if __name__ == '__main__':
    try:
        loop()
    except KeyboardInterrupt:  # Press ctrl-c to end the program.
        print("\nEnding program")
