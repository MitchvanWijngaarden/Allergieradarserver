package nl.allergieradar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import nl.allergieradar.View;
import java.sql.Date;

/**
 * Created by bernd on 16-5-2017.
 */
public class Complaint {

    @JsonView(View.Public.class)
    private int complaintID;

    @JsonView(View.Public.class)
    private Date date;

    @JsonView(View.Public.class)
    private int eyes;

    @JsonView(View.Public.class)
    private int nose;

    @JsonView(View.Public.class)
    private int lungs;

    @JsonView(View.Public.class)
    private int medicine;

    @JsonView(View.Public.class)
    private String latitude;

    @JsonView(View.Public.class)
    private String longtitude;


    @JsonIgnore
    public int getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(int complaintID) {
        this.complaintID = complaintID;
    }

    @JsonIgnore
    public Date getData() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @JsonIgnore
    public int getEyes() {
        return eyes;
    }

    public void setEyes(int eyes) {
        this.eyes = eyes;
    }

    @JsonIgnore
    public int getNose() {
        return nose;
    }

    public void setNose(int nose) {
        this.nose = nose;
    }

    @JsonIgnore
    public int getLungs() {
        return lungs;
    }

    public void setLungs(int lungs) {
        this.lungs = lungs;
    }

    @JsonIgnore
    public int getMedicine() {
        return medicine;
    }

    public void setMedicine(int medicine) {
        this.medicine = medicine;
    }

    @JsonIgnore
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @JsonIgnore
    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }
}
