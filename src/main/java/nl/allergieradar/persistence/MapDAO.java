package nl.allergieradar.persistence;

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

        private PreparedStatement setAvgComplain;
    private PreparedStatement getAllMap;
        private PreparedStatement addMap;
        private List<Map> maps;


        public MapDAO() throws Exception {
            super();
            prepareStatements();
        }

        private void prepareStatements() {

            try {
                setLocation();
                setAvgComplain = conn.prepareStatement("SELECT avg( eyes + nose + lungs)/3.0 FROM complaint;");
                getAllMap = conn.prepareStatement("SELECT * FROM map;");
                addMap = conn.prepareStatement("INSERT INTO map (avg_complain, location,date) VALUES (?, ?, ?)");


            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public int setAvgComplain(){

            int avgComplain  = 0;


        try {
            ResultSet rs = getAllMap.executeQuery();

            while (rs.next()) {

              avgComplain =  rs.getInt(1);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return avgComplain;
    }



    public void setLocation() throws IOException {

        // build a URL
        String s = "https://maps.googleapis.com/maps/api/geocode/json?&latlng=52.152029,4.487915";

        URL url = new URL(s);

        // read from the URL
        Scanner scan = new Scanner(url.openStream());
        String str = new String();
        while (scan.hasNext())
            str += scan.nextLine();
        scan.close();

        // build a JSON object
        JSONObject obj = new JSONObject(str);
        if (! obj.getString("status").equals("OK"))
            return;

        // get the first result
        JSONObject res = obj.getJSONArray("results").getJSONObject(0);
        //System.out.println(res.getString("address_components"));
//        JSONObject loc =
//                res.getJSONObject("long_name").getJSONObject("location");
//        System.out.println("lat: " + loc.getDouble("lat") +
//                ", lng: " + loc.getDouble("lng"));


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
                //map.setDate(rs.getDate(4));
                maps.add(map);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return maps;
    }
    public void add(Map map)
    {

        System.out.println("Complaint add called");
        try {
            addMap.setInt(1, setAvgComplain());
            addMap.setString(2, map.getLocation());
            addMap.setDate(3, map.getDate());
            addMap.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
