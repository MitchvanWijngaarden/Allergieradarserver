package nl.allergieradar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import nl.allergieradar.View;

import java.sql.Date;

/**
 * Created by vedadpiric on 13-06-17.
 */
public class Map {

    @JsonView(View.Public.class)
    private int id;

    @JsonView(View.Public.class)
    private int avg_complaint;

    @JsonView(View.Public.class)
    private String location;



    @JsonView(View.Public.class)
    private java.sql.Date date;

    @JsonIgnore
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @JsonIgnore
    public int getAvg_complaint() {
        return avg_complaint;
    }

    public void setAvg_complaint(int avg_complaint) {
        this.avg_complaint = avg_complaint;
    }
    @JsonIgnore
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public Date getDate() {
        return date;
    }
    @JsonIgnore
    public void setDate(Date date) {
        this.date = date;
    }

}
