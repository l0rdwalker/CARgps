package com.example;


import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "triphops")
public class WayPoint implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @Column(name = "SequenceID", unique = true)
    private String SequenceID; 

    @Column(name = "tripID", nullable = true)
    private String tripID;

    @Column(name = "LongCRD", nullable = true)
    private String LongCRD;

    @Column(name = "LatCRD", nullable = true)
    private String LatCRD;

    @Column(name = "TimeStamp", nullable = true)
    private Date TimeStamp;

    public String GetSequenceID(){
        return this.SequenceID;
    }

    public String gettripID() {
        return this.tripID;
    }

    //String Long Lat returns//
    public String getLongCRD() {
        return this.LongCRD;
    }

    public String getLatCRD() {
        return this.LatCRD;
    }
    ///////////////////////////

    //Double Long Lat returns//
    public double getDoubleLongCRD() {
        return Double.parseDouble(this.LongCRD);
    }
    
    public double getDoubleLatCRD() {
        return Double.parseDouble(this.LatCRD);
    }
    ///////////////////////////

    public Date getTimeStamp() {
        return this.TimeStamp;
    }




    //SequenceID overloaded set methods//
    public void setSequenceID(int newSequenceID){
        this.SequenceID = String.valueOf(newSequenceID);
    }
    public void setSequenceID(String newSequenceID){
        this.SequenceID = newSequenceID;
    }
    ///////////////////////////////


    //Long AND Lat overloaded set methods//
    public void setLongCRD(String newLongCRD) {
        this.LongCRD = newLongCRD;
    }

    public void setLatCRD(String newLatCRD) {
        this.LatCRD = newLatCRD;
    }    
    ///////////////////////////////


    public void settripID(String newTripID) {
        this.tripID = newTripID;
    }

    public void setTimeStamp(Date newTimeStamp) {
        this.TimeStamp = newTimeStamp;
    }

}