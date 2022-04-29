package com.example;

import java.sql.Date;
import java.util.ArrayList;




public final class App {
    private App() {
    }

    public static void main(String[] args) {
        CRUDoperations Database = new CRUDoperations();

        /*
        Trip SearchTrip = Database.getTrip(10);

        if (SearchTrip != null) {
            System.out.println("Found user with id of " + SearchTrip.GetDistance());
        } else {
            System.out.println("Cannot find this user.");
        }


        /*
        Trip NewTrip = TripCreatorAdapter(99,50,new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()), 23.43);

        if (Database.addUser(NewTrip)) {
            System.out.println("User successfully added.");
        } else {
            System.out.println("User was not successfully added.");
        }

        Trip SearchTrip = Database.getTrip(60);

        if (SearchTrip != null) {
            System.out.println("Found user with id of " + SearchTrip.GetTripId());
        } else {
            System.out.println("Cannot find this user.");
        }

        ArrayList<Trip> ListOfAllTrips = Database.GetAllTrips();

        for (Trip find : ListOfAllTrips) {
            System.out.println(find.GetTripId());
        }

        NewTrip.SetDistance(12345678);
        if (Database.AlterTrip(NewTrip)) {
            System.out.println("Successfully altered user with id of 98.");
        } else {
            System.out.println("Could not alter the user.");
        }

        
        if (Database.deleteTrip(NewTrip.GetTripId())) {
            System.out.println("Successfully deleted object from databse.");
        } else {
            System.out.println("Object could not be deleted from database.");
        }
        */
        
    }

    public static Trip TripCreatorAdapter(int ID, int Distance, Date StartDate, Date EndDate, double Cost) {
        Trip NewTrip = new Trip();

        NewTrip.SetTripID(ID);
        NewTrip.SetDistance(Distance);
        NewTrip.SetStartTime(StartDate);
        NewTrip.SetEndTime(EndDate);
        NewTrip.setCost(Cost);

        return NewTrip;
    }
}
