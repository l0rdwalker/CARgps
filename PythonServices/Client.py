import socket
import serial

def SendData(DataToSend):
    s = socket.socket()          
    port = 43470 # Use the same port number here as you did in the server script.
    s.connect(('127.0.0.1', port)) 
    s.send(bytes(DataToSend, 'utf-8'))
    s.close()  

ser = serial.Serial('COM3',9600)

while True:
    Data = ser.readline()
    print(Data)
    Data = str(Data)
    #Data = "-36.989391 148.710746"
    SendData(Data)


