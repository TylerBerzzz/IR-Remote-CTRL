# IR-Remote

## What is Infrared (IR) Transmission?
IR transmission refers to the communication of data or signals using infrared radiation, which is an electromagnetic wave with a wavelength longer than that of visible light. It is commonly used in various applications such as remote controls, data transfer between devices, and proximity sensors. 

## When to use IR Communication?
IR communication is often more power-efficient, making it suitable for battery-powered device operation when conserving power is of interest. It typically requires a direct line of sight between the transmitter and receiver, meaning there should be no obstacles obstructing the path of the infrared signal. The circuit simplicity and inexpensive cost of components also makes it a strong choice in system design selection. 

## What is in this repo?

### 1) Android Studio App
In 2017, I developed an application for my Samsung Galaxy S4 that utalized the in-built IR blaster (another name for IR Transmitter) to control systems. Back in 2017, it seemed like samsung was experimenting to see if in-built phone IR-Transmission technology would lead to positive business outcomes; although,in 2023 it seems like that trend has not caught on. Within the **Android Studio** folder, you will find the _Simplest-Samsung-Remote_ and _ledstrip-remote_ folders. These are 2 apps that represent different proof-of-concept for both controlling a TV and a LED Strip.  

![Simplest-Remote](https://github.com/TylerBerzzz/IR-Remote/assets/30520534/6e617480-27d9-4b4a-a4d9-6095e0641597)

![LED-Strip-Remote1](https://github.com/TylerBerzzz/IR-Remote/assets/30520534/d4b0687a-982e-4d1e-9368-f7846c90449b)


### 2) Arduino Microcontroller Code
I used an Arduino equipped with an IR receiver and transmitter to capture and interpret IR signals for various commands. By utilizing the IR receiver, I could read commands from a TV remote and then validate the functionality by transmitting those commands back to the TV using the microcontroller, effectively controlling it. 


## My Applications of IR Communication in Projects:
