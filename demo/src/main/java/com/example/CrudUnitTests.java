package com.example;

import java.util.ArrayList;

public class CrudUnitTests {

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {
       
        CRUDoperations Database = new CRUDoperations();

        ArrayList<WayPoint> WayPoints = Database.GetAllTripHops("8211627810");

        for (int i = 0; i < WayPoints.size(); i++) {
            System.out.println(WayPoints.get(i).getLatCRD());
        }

        DistanceCalculator TestCalk = new DistanceCalculator();
        ArrayList<Trip> Trips = Database.GetAllTrips();
        TestCalk.GenerateDistance(Database, Trips, 43.0);
        
         /*
        boolean AddToDatabase = false;
        boolean EditItemFromDatabase = false;
        boolean RemoveFromDatabase = false;
        boolean GetAllItemsFromDatabase = false;
        boolean FindTripById = false;
        
        Trip newTrip = new Trip();
        newTrip.SetTripID("8211627811");
        newTrip.setCost(45.32);
        newTrip.SetDistance(453);
        newTrip.SetStartTime(new Date(System.currentTimeMillis()));
        newTrip.SetEndTime(new Date(System.currentTimeMillis()));

        ArrayList<WayPoint> WayPoints = Database.GetAllTripHops("8211627810");


        
        if (Database.addTrip(newTrip)) {
            if (Database.getTrip(newTrip.GetTripId()).GetTripId() == "8211627811") {
                System.out.println(ANSI_GREEN + "Succesfully added and found trip." + ANSI_RESET);
            } else {
                System.out.println(ANSI_GREEN + "Could not add a new trip to the database." + ANSI_RESET);
            }
        }

        if (Database.deleteTrip("8211627811")) {
            if (Database.getTrip(newTrip.GetTripId()) == null) {
                System.out.println(ANSI_GREEN + "Successfully deleated from the database." + ANSI_RESET);
            }
        }

        
        newTrip.SetTripID("8211622211");
        if (Database.AlterTrip(newTrip)) {
            EditItemFromDatabase = true;
        }

        if (Database.getTrip("8211622211").GetTripId() == "8211622211") {
            FindTripById = true;
        }

        if (Database.GetAllTrips() != null) {
            GetAllItemsFromDatabase = true;
        }

        */



    }
}
