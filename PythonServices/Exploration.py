import socket
import json
import mysql.connector
from datetime import datetime

mydb = mysql.connector.connect(
  host="192.168.0.92",
  user="Admin",
  password="ilia7eyedWank101!123",
  database="LifeIO"
)

cur = mydb.cursor()

IDtest = 10


def TripExistanceCheck(IDtest):
    cur.execute("SELECT * FROM Trips WHERE TRIPID = %s" %IDtest)
    cur.fetchall()
    rc = cur.rowcount

    if (rc == 0):
        TripQury = ("INSERT INTO Trips (TripID, StartTime, EndTime, Distance,Cost) VALUES (%s, null, null, %s, %s)")
        DBdata = (IDtest,0,0)
        cur.execute(TripQury, DBdata)
        mydb.commit()
        print('No id of ' + str(IDtest) + ' found, adding new entry.')
    else: 
        print('pre-existing entry found')

TripExistanceCheck(IDtest)