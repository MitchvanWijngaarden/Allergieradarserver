package nl.allergieradar.resource;


import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Singleton;
import nl.allergieradar.View;
import nl.allergieradar.model.Complaint;
import nl.allergieradar.model.Map;
import nl.allergieradar.service.MapService;
import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by vedadpiric on 14-06-17.
 */

@Singleton
@Path("/map")
@Produces(MediaType.APPLICATION_JSON)
public class MapResource {
    private final MapService service;

    @Inject
    public MapResource(MapService service) {
        this.service = service;
    }

    @GET
    @JsonView(View.Public.class)

    public Collection<Map> retrieveAll()
    {
        return service.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    @PermitAll
    public void create(Complaint complaint) throws IOException {
        try {
            service.add(complaint);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
