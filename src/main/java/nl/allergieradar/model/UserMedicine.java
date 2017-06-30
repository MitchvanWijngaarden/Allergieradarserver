package nl.allergieradar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import nl.allergieradar.View;

/**
 * Created by Danny on 30-6-2017.
 */
public class UserMedicine {

    @JsonView(View.Public.class)
    private int userID;

    @JsonView(View.Public.class)
    private int medicineID;

    @JsonIgnore
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @JsonIgnore
    public int getMedicineID() {
        return medicineID;
    }

    public void setMedicineID(int medicineID) {
        this.medicineID = medicineID;
    }
}
