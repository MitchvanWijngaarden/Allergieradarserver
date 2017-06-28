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
            addComplaint = conn.prepareStatement("INSERT INTO complaint (eyes, nose," +
                    " lungs, medicine, latitude, longitude) VALUES (?, ?, ?, ?, ?, ?)");


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
                complaint.setDate(rs.getDate(2));
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

        System.out.println("Complaint add called");
        try {
            addComplaint.setInt(1, complaint.getEyes());
            addComplaint.setInt(2, complaint.getNose());
            addComplaint.setInt(3, complaint.getLungs());
            addComplaint.setInt(4, complaint.getMedicine());
            addComplaint.setString(5, complaint.getLatitude());
            addComplaint.setString(6, complaint.getLongtitude());

            addComplaint.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
