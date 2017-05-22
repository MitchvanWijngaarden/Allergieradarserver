package nl.allergieradar.service;

import nl.allergieradar.model.Complaint;
import nl.allergieradar.persistence.ComplaintDAO;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collection;

/**
 * Created by bernd on 16-5-2017.
 */
@Singleton
public class ComplaintService {
    private final ComplaintDAO dao;

    @Inject
    public ComplaintService(ComplaintDAO dao) {
        this.dao = dao;
    }

    public Collection<Complaint> getAll()
    {
        return dao.getAll();
    }

    public void add(Complaint complaint) { dao.add(complaint); }


}
