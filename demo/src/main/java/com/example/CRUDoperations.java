package com.example;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


public class CRUDoperations {
    public static final String PERSISTENCE_UNIT_NAME = "PDUconfig";

    private EntityManager ManagerOfEntities = null;

    private void SetEntityManager() {
        if (ManagerOfEntities == null) {
            EntityManagerFactory factory =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            this.ManagerOfEntities = factory.createEntityManager();
        } else if (ManagerOfEntities.isOpen() == false) {
            EntityManagerFactory factory =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            this.ManagerOfEntities = factory.createEntityManager();
        }
    }

    public boolean addTrip(Trip newTrip) {
        SetEntityManager();

        EntityTransaction DBoperation = null;

        boolean result = true;

        try {
            DBoperation = ManagerOfEntities.getTransaction();
            DBoperation.begin();
            ManagerOfEntities.persist(newTrip);
            DBoperation.commit();
        } catch (Exception ex) {
            if (DBoperation != null) {
                result = false;
            }
            //ex.printStackTrace();
            ManagerOfEntities.close();
        }

        return result;
    }

    public Trip getTrip(String id) {
        SetEntityManager();
    	try {
            String query = "SELECT c FROM Trip c WHERE c.SequenceID = :sequenceID";
            TypedQuery<Trip> tq = ManagerOfEntities.createQuery(query, Trip.class);
            tq.setParameter("sequenceID", id);
            
            Trip foundTrip = null;
    		foundTrip = tq.getSingleResult();
            return foundTrip;
    	}
    	catch(NoResultException ex) {
    		//ex.printStackTrace();
            System.out.println("Possible duplication in GetTrip");
            ManagerOfEntities.close();
    	}
        return null;
    }

    public ArrayList<Trip> GetAllTrips() {
        SetEntityManager();

        try {
            String strQuery = "SELECT c FROM Trip c WHERE c.TripId IS NOT NULL";
            List<Trip> foundTrips = null;
            TypedQuery<Trip> tp = ManagerOfEntities.createQuery(strQuery, Trip.class);
            foundTrips = tp.getResultList();

            ArrayList<Trip> ReturnArrayList = new ArrayList<Trip>(foundTrips);

            return ReturnArrayList;
        } catch(NoResultException ex)  { 
            //ex.printStackTrace();
            System.out.println("Possible error in GetAllTrips.");
            ManagerOfEntities.close();
        }
        return null;
    }

    public boolean AlterTrip(Trip newTrip){
        SetEntityManager();

        EntityTransaction et = null;
    	Trip AlteredTrip = null;

        try {
            et = ManagerOfEntities.getTransaction();
            et.begin();

            AlteredTrip = ManagerOfEntities.find(Trip.class, newTrip.getSequenceID());
            AlteredTrip.SetDistance(newTrip.GetDistance());
            AlteredTrip.SetStartTime(newTrip.GetStartTrip());
            AlteredTrip.SetEndTime(newTrip.GetEndTrip());
            AlteredTrip.setCost(newTrip.getCost());

            ManagerOfEntities.persist(AlteredTrip);
            et.commit();
            
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();

        } finally {
            ManagerOfEntities.close();
        }

        
        if (compareTrips(getTrip(newTrip.getSequenceID()), newTrip)) {
            return true;
        } else {
            return false;
        }
        
    }

    public boolean deleteTrip(String id) {
        SetEntityManager();

        EntityTransaction et = null;
        Trip foundTrip = null;
 
        try {
            et = ManagerOfEntities.getTransaction();
            et.begin();
            foundTrip = ManagerOfEntities.find(Trip.class, id);
            ManagerOfEntities.remove(foundTrip);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
            ManagerOfEntities.close();
            return false;
        }
        return true;
    }

    private boolean compareTrips(Trip TripOne, Trip TripTwo) {
        if (TripOne == null || TripTwo == null) {
            return false;
        }

        String TripOneStartTime = String.valueOf(TripOne.GetStartTrip());
        String TripTwoStartTime = String.valueOf(TripTwo.GetStartTrip());

        String TripOneEndTime = String.valueOf(TripOne.GetEndTrip());
        String TripTwoEndTime = String.valueOf(TripTwo.GetEndTrip());

        if (TripOneStartTime.equals(TripTwoStartTime) && TripOneEndTime.equals(TripTwoEndTime)) {
            return true; 
        } else {
            return false;
        }
    }

    public ArrayList<WayPoint> GetAllTripHops(String ID) {
        SetEntityManager();

        try {
            String strQuery = "SELECT c FROM WayPoint c WHERE c.tripID IS '"+ID+"'";
            List<WayPoint> foundTrips = null;
            TypedQuery<WayPoint> tp = ManagerOfEntities.createQuery(strQuery, WayPoint.class);
            foundTrips = tp.getResultList();

            ArrayList<WayPoint> ReturnArrayList = new ArrayList<WayPoint>(foundTrips);

            return ReturnArrayList;
        } catch(NoResultException ex)  { 
            //ex.printStackTrace();
            System.out.println("Possible error in GetAllTrips.");
            ManagerOfEntities.close();
        }
        return null;
    }
}