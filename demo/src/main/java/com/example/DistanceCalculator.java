package com.example;

import java.util.ArrayList;

class DistanceCalculator
{

	public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

	public void GenerateDistance(CRUDoperations Database, ArrayList<Trip> Trips, double Cost) {

		Double TotalDistance = 0.0;
		int iPlacement = 0; 

		for (int i = Trips.size() - 1; !(i < 0); i--) {

			if (Trips.get(i).isEmpty()) {
				System.out.println(Trips.get(i).GetTripId() + " is empty");

				ArrayList<WayPoint> TripHops = Database.GetAllTripHops(Trips.get(i).GetTripId());

				double Distance = 0.0;

				for (int HipNum = TripHops.size()-1; !(HipNum < 1); HipNum--) {
					//System.out.println(String.valueOf(TripHops.get(HipNum).GetSequenceID()) + " apart of trip " + Trips.get(i).GetTripId());

					WayPoint WaypointOne = TripHops.get(HipNum);

					//System.out.println(WaypointOne.getLongCRD() + "," + WaypointOne.getLatCRD() + ",#00FF00,marker,'Mumbai'");

					WayPoint WaypointTwo = TripHops.get(HipNum - 1);

					//System.out.println("Compairing " + String.valueOf(WaypointOne.GetSequenceID()) + " to " + String.valueOf(WaypointTwo.GetSequenceID()) + " with a distance of " + String.valueOf(distance_Between_LatLong(WaypointOne.getDoubleLatCRD(), WaypointTwo.getDoubleLatCRD(), WaypointOne.getDoubleLongCRD(), WaypointTwo.getDoubleLongCRD())));

					Distance = Distance + distance_Between_LatLong(WaypointOne.getDoubleLatCRD(),WaypointTwo.getDoubleLatCRD(), WaypointOne.getDoubleLongCRD(), WaypointTwo.getDoubleLongCRD());
					
				}

				TotalDistance = TotalDistance + Distance;

				Trips.get(i).SetDistance(Distance);
				Trips.get(i).SetStartTime(TripHops.get(0).getTimeStamp());
				Trips.get(i).SetEndTime(TripHops.get(TripHops.size() - 1).getTimeStamp());
				
				System.out.println(TripHops.size());

			} else {
				System.out.println(Trips.get(i).GetTripId() + " is not empty");
				iPlacement = i;
				break;
			}
		}

		
		for (int b = iPlacement; !(b >= Trips.size()); b++){
			if (!(Trips.get(b).getDoubleCost() > 0.0)) {
				double Percentage = Trips.get(b).GetDoubleDistance() / TotalDistance;
				double TripCost = Percentage * Cost;

				Trips.get(b).setCost(TripCost);
					
				Database.AlterTrip(Trips.get(b));
			}
		}
	}

	public double distance_Between_LatLong(double lat1, double lat2, double lon1, double lon2) {
		lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
 
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2),2);
             
        double c = 2 * Math.asin(Math.sqrt(a));
 
        double r = 6371;
 
        return(c * r);
    }
}