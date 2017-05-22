package nl.allergieradar.persistence;

import nl.allergieradar.model.Complaint;
import javax.inject.Singleton;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class ComplaintDAO extends DatabaseDAO {
    private PreparedStatement getAllComplaints;
    private PreparedStatement addComplaint;
    private List<Complaint> complaints;

    /**
     * Instantiates a new Database dao.
     *
     * @throws Exception the exception
     */
    public ComplaintDAO() throws Exception {
        super();
        prepareStatements();
    }

    private void prepareStatements() {

        try {
            getAllComplaints = conn.prepareStatement("SELECT * FROM complaint;");
            addComplaint = conn.prepareStatement("INSERT INTO complaint (date, eyes, nose," +
                    " lungs, medicine, latitude, longtitude) VALUES (?, ?, ?, ?, ?, ?, ?)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Complaint> getAll()
    {
        complaints = new ArrayList<>();

        try {
            ResultSet rs = getAllComplaints.executeQuery();

            while (rs.next()) {
                Complaint complaint = new Complaint();
                complaint.setComplaintID(rs.getInt(1));
                complaint.setData(rs.getDate(2));
                complaint.setEyes(rs.getInt(3));
                complaint.setNose(rs.getInt(4));
                complaint.setLungs(rs.getInt(5));
                complaint.setMedicine(rs.getInt(6));
                complaint.setLatitude(rs.getString(7));
                complaint.setLongtitude(rs.getString(8));
                complaints.add(complaint);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return complaints;
    }

    public void add(Complaint complaint)
    {
        complaints.add(complaint);
    }
}
