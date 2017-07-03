package nl.allergieradar.service;

import nl.allergieradar.model.Complaint;
import nl.allergieradar.persistence.ComplaintDAO;
import nl.allergieradar.persistence.MapDAO;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by bernd on 16-5-2017.
 */
@Singleton
public class ComplaintService {
    private final ComplaintDAO complaintDAOdao;
    private final MapDAO mapdao;

    @Inject
    public ComplaintService(ComplaintDAO dao, MapDAO mapdao) {
        this.complaintDAOdao = dao;
        this.mapdao = mapdao;
    }

    public Collection<Complaint> getAll()
    {
        return complaintDAOdao.getAll();
    }

    public void add(Complaint complaint) {
        long time = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(time);
        complaint.setDate(date);

        
        complaintDAOdao.add(complaint);
        try {
            mapdao.add(complaint);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
