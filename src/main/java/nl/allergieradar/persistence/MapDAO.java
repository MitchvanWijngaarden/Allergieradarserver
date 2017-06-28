package nl.allergieradar.persistence;

import nl.allergieradar.model.Complaint;
import nl.allergieradar.model.Map;
import org.json.JSONObject;

import javax.inject.Singleton;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by vedadpiric on 13-06-17.
 */
@Singleton
public class MapDAO extends DatabaseDAO {

        //private PreparedStatement setAvgComplain;
        private PreparedStatement getAllMap;
        private PreparedStatement addMap;
       // private PreparedStatement getLatLong;
        private List<Map> maps;


        public MapDAO() throws Exception {
            super();
            prepareStatements();
        }

        private void prepareStatements() {

            try {
                //getLatLong = conn.prepareStatement("SELECT latitude ,longitude FROM complaint;");
               // setAvgComplain = conn.prepareStatement("SELECT avg( eyes + nose + lungs)/3.0 FROM complaint;");
                getAllMap = conn.prepareStatement("SELECT * FROM map WHERE DATE_FORMAT(map.date, '%Y-%m-%d') = CURDATE() ;");
                //getAllMap = conn.prepareStatement("SELECT * FROM map ;");
                addMap = conn.prepareStatement("INSERT INTO map (avg_complain, location,date) VALUES (?, ?, ?)");


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    public String getLocation(Complaint complaint){
        String lng=complaint.getLongtitude();
        String lat= complaint.getLatitude();


        System.out.println(lat+","+lng);

        return lat+","+lng;
    }
    public int setAvgComplain(Complaint complaint){

            int avgComplain  = (complaint.getEyes() + complaint.getLungs() + complaint.getNose())/ 3;




        return avgComplain;
    }



    public String setLocation(Complaint complaint) throws IOException {
        String location ="";

        // build a URL
        String s = "https://maps.googleapis.com/maps/api/geocode/json?&latlng="+getLocation(complaint);

        URL url = new URL(s);

        // read from the URL
        Scanner scan = new Scanner(url.openStream());
        String str = new String();
        while (scan.hasNext())
            str += scan.nextLine();
        scan.close();

        // build a JSON object
        JSONObject obj = new JSONObject(str);



            // get the first result
            JSONObject res = obj.getJSONArray("results").getJSONObject(0);
            location = res.getJSONArray("address_components").getJSONObject(3).get("long_name").toString();

        System.out.println(location);
        System.out.println(s);
        return location;
    }

    public List<Map> getAll()
    {
        maps = new ArrayList<>();

        try {
            ResultSet rs = getAllMap.executeQuery();

            while (rs.next()) {
                Map map = new Map();
                map.setId(rs.getInt(1));
                map.setAvg_complaint(rs.getInt(2));
                map.setLocation(rs.getString(3));
                map.setDate(rs.getDate(4));
                maps.add(map);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return maps;
    }
    public void add(Complaint complaint) throws IOException {

        System.out.println("Map add called");
        try {
            addMap.setInt(1, setAvgComplain(complaint));
            addMap.setString(2, setLocation(complaint));
            addMap.setDate(3, complaint.getDate());
            addMap.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
