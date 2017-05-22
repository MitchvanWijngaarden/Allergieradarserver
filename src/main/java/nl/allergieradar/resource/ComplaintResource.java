package nl.allergieradar.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Singleton;
import nl.allergieradar.View;
import nl.allergieradar.model.Complaint;
import nl.allergieradar.service.ComplaintService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Singleton
@Path("/complaints")
@Produces(MediaType.APPLICATION_JSON)
public class ComplaintResource {
    private final ComplaintService service;

    @Inject
    public ComplaintResource(ComplaintService service) {
        this.service = service;
    }

    @GET
    @JsonView(View.Public.class)
    @PermitAll
    public Collection<Complaint> retrieveAll()
    {
        return service.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    public void create(Complaint complaint)
    {
        service.add(complaint);
    }



}
