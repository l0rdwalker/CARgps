import socket
import json
import mysql.connector
from datetime import datetime
import traceback

mydb = mysql.connector.connect(
  host="192.168.0.92",
  user="Admin",
  password="ilia7eyedWank101!123",
  database="LifeIO"
)

cursor = mydb.cursor()

def TripExistanceCheck(IDtest):
    cursor.execute("SELECT * FROM Trips WHERE TRIPID = %s" %IDtest)
    cursor.fetchall()
    rc = cursor.rowcount

    if (rc == 0):
        TripQury = ("INSERT INTO Trips (tripID, StartTime, EndTime, Distance,Cost) VALUES (%s, null, null, %s, %s)")
        DBdata = (IDtest,0,0)
        cursor.execute(TripQury, DBdata)
        mydb.commit()
        print('No id of ' + str(IDtest) + ' found, adding new entry.')
    else: 
        print('pre-existing entry found')

def AddToTable(Waypoint):
    print(len(str(Waypoint['tripCode'])))
    TripExistanceCheck(Waypoint['tripCode'])
    DBwaypoint = ("INSERT INTO triphops (TripID, LongCRD, LatCRD, TimeStamp) VALUES (%s, %s, %s, SYSDATE())")

    DBdata = (Waypoint['tripCode'],Waypoint['long'],Waypoint['lat'])

    cursor.execute(DBwaypoint, DBdata)
    mydb.commit()

def AllowConnectivity():
    ConnectionCount = 0
    
    s = socket.socket() 
    port = 43470 

    s.bind(('0.0.0.0', port))
    s.listen(10) 

    while True:
        ConnectionCount = ConnectionCount + 1
        print(ConnectionCount)
        c, addr = s.accept()
        data = c.recv(1024).decode("utf-8") 
        try:
            aDict = json.loads(data)
            print(("Long: " + str(aDict['long'])) + (" Lat: " + str(aDict['lat'])) + (" TripCode: " + str(aDict['tripCode'])))
            try:
                AddToTable(aDict)
            except Exception:
                traceback.print_exc()
        except:
            print(data)

AllowConnectivity()
#TripExistanceCheck("544543545")