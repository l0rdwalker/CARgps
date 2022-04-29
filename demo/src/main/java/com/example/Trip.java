package com.example;


import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Trips")
public class Trip implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @Column(name = "sequenceID", unique = true)
    private String SequenceID; 

    @Column(name = "TripID", unique = true)
    private String TripId; 

    @Column(name = "StartTime", nullable = true)
    private Date StartTime;

    @Column(name = "EndTime", nullable = true)
    private Date EndTime;

    @Column(name = "Distance", nullable = true)
    private String Distance;

    @Column(name = "Cost", nullable = true)
    private String Cost;

    public String getSequenceID() {
        return this.SequenceID;
    }

    public String getCost() {
        return this.Cost;
    }

    public Double getDoubleCost() {
        return Double.parseDouble(this.Cost);
    }

    public String GetTripId() {
        return this.TripId;
    }

    public Date GetStartTrip() {
        return this.StartTime;
    }

    public Date GetEndTrip() {
        return this.EndTime;
    }

    public String GetDistance() {
        return this.Distance;
    }

    public Double GetDoubleDistance() {
        return Double.parseDouble(this.Distance);
    }

    public void SetStartTime(Date newStartDate) {
        this.StartTime = newStartDate;
    }

    public void SetEndTime(Date newEndDate) {
        this.EndTime = newEndDate;
    }



    //Distance overloaded set methods//
    public void SetDistance(double newDistance) {
        this.Distance = String.valueOf(newDistance);
    }
    public void SetDistance(int newDistance) {
        this.Distance = String.valueOf(newDistance);
    }
    public void SetDistance(String newDistance) {
        this.Distance = newDistance;
    }
    ///////////////////////////////




    //TripID overloaded set methods//
    public void SetTripID(long newTripID) {
        this.TripId = String.valueOf(newTripID);
    }

    public void SetTripID(String newTripID) {
        this.TripId = newTripID;
    }

    public void SetTripID(int newTripID) {
        this.TripId = String.valueOf(newTripID);
    }
    ///////////////////////////////




    //Cost overloaded set methods//
    public void setCost(double newCost) {
        this.Cost = String.valueOf(newCost);
    }
    public void setCost(int newCost) {
        this.Cost = String.valueOf(newCost);
    }
    public void setCost(String newCost) {
        this.Cost = newCost;
    }
    ///////////////////////////////



    public boolean isEmpty(){
        if ((this.StartTime == null) && (this.EndTime == null)) {
            return true;
        }
        return false;
    }
}