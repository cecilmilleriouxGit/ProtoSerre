# DHT22 Spring-boot Raspberry

Spring Boot software to run on a Raspberry Pi 3A+ to run DHT22.py chip and 4B+ te run maven server to read from a DHT-22 temperature and humidity sensor

This repository is an Open Source project.

This project uses a trial version of CanvasJs for the graphical interface.  
Make sure you get the license for commercial use.
[https://canvasjs.com/license/](https://canvasjs.com/license/)

To use this code you need a Raspberry (two in my case) and the DHT22 chip.

DHT22 pin => Raspberry Pi pin

 "+"  => [PIN2] (5v)
 
"out" => [PIN32] GPIO12

"-" => [PIN30] GND

You can see the mapping with the command: 

    pinout

On the raspberry on which you plug the DHT22 chip

The library we are going to use is called "Adafruit_DHT". To install it we first
need to make sure Rasbian is up to date. 
Start your Raspberry Pi, open terminal and run these commands:
First command is for making the system up to date: 

`sudo apt-get update && sudo apt-get upgrade -y`

Second command is for installing python3-pip app. 
We will use pip app to install library:

 `sudo apt-get install python3-pip`
 
Third command is for installing other apps that pip app uses during installation:

    sudo python3 -m pip install --upgrade pip setuptools wheel

And after this is completed, we can install library that we need. Run this
command in terminal: 

    sudo pip3 install Adafruit_DHT

On the raspberry which runs the server, you must also create a MySQL database with the command:

`CREATE DATABASE Protoserre;`

To start go edit the file: 

    nano ProtoSerre/src/main/resources/application.properties.exemple

add your MySQL account :

    spring.datasource.username = *****
    spring.datasource.password = *****

and save this file with the name:

     application.properties

Then go to your router to assign a static ip to the raspberry which runs the spring boot server.

> 192.168.0.33

(in my project I used a raspberry pi 3A + to run the python script so the chip is on the pins of the raspi 3A + and a raspberry 4B + to run the Spring Boot server so I assigned this ip on the raspberry 4B +, make sure you are connected on the same network)

To launch the server (raspberry 4B +) use the command (at the root of the project):

    mvn spring-boot: run

To launch the python script (raspberry 3A +) use the command (in the src/main/python/):

    python3 DHT22.py

The python script takes the measurements and transmits them to the server every hour.

You can access the graphical interface:

http://localhost:8080 ( on the raspberry 4B+ )

http://192.168.0.33:8080/ ( on your other device )


### License

ProtoSerre is open source software [licensed as MIT](https://github.com/cecilmillerioux/ProtoSerre/blob/master/LICENSE).
