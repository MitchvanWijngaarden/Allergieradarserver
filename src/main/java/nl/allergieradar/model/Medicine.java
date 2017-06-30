package nl.allergieradar.model;

import com.fasterxml.jackson.annotation.JsonView;
import nl.allergieradar.View;

/**
 * Created by Danny on 30-6-2017.
 */
public class Medicine {

    @JsonView(View.Public.class)
    private int medicineID;

    @JsonView(View.Public.class)
    private String medicinename;

    @JsonView(View.Public.class)
    private String medicinetype;

    public String getMedicinename() {
        return medicinename;
    }

    public void setMedicinename(String medicinename) {
        this.medicinename = medicinename;
    }

    public String getMedicinetype() {
        return medicinetype;
    }

    public void setMedicinetype(String medicinetype) {
        this.medicinetype = medicinetype;
    }

    public int getMedicineID() {
        return medicineID;
    }

    public void setMedicineID(int medicineID) {
        this.medicineID = medicineID;
    }
}
