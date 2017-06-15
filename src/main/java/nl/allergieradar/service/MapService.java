package nl.allergieradar.service;

import nl.allergieradar.model.Map;
import nl.allergieradar.persistence.MapDAO;

import javax.inject.Inject;
import java.util.Collection;

/**
 * Created by bernd on 16-5-2017.
 */
public class MapService {
    private final MapDAO dao;


    @Inject
    public MapService(MapDAO dao) {
        this.dao = dao;
    }

    public Collection<Map> getAll()
    {
        return dao.getAll();
    }

    public void add(Map map) {



        dao.add(map);

    }
}
