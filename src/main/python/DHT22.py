import Adafruit_DHT
import requests
from time import sleep
sensor = Adafruit_DHT.DHT22
# DHT22 sensor connected to GPIO12.
pin = 12
print("[press ctrl+c to end the script]")
try: # Main program loop
    while True:
        humidity, temperature = Adafruit_DHT.read_retry(sensor,pin)
        sleep(2.5)
        if humidity is not None and temperature is not None:
            print("Temp={0:0.1f}*C Humidity={1:0.1f}%".format(temperature, humidity))
            param = {'temperature':(float(temperature)),'humidity':(float(humidity))}
            print(param)
            r = requests.post('http://192.168.0.42:8080/dht22/store', data=param)
        else:
            print("Failed to get reading. Try again!")

# Scavenging work after the end of the program
except KeyboardInterrupt:
    print("Script end!")