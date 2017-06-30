package nl.allergieradar.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Singleton;
import io.dropwizard.auth.Auth;
import java.util.Collection;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import nl.allergieradar.View;
import nl.allergieradar.model.*;
import nl.allergieradar.service.UserService;

/**
 * Meer informatie over resources:
 *  https://jersey.java.net/documentation/latest/user-guide.html#jaxrs-resources
 * 
 * @author Peter van Vliet
 */
@Singleton
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource
{
    private final UserService service;
    
    @Inject
    public UserResource(UserService service)
    {
        this.service = service;
    }
    
    @GET
    @JsonView(View.Public.class)
    @PermitAll
    public Collection<User> retrieveAll() { return service.getAll(); }

    @GET
    @Path("/questions")
    @JsonView(View.Public.class)
    public Collection<Question> retrieveQuestion() {
        return service.getQuestion();
    }

    @GET
    @Path("/medicines")
    @JsonView(View.Public.class)
    public Collection<Medicine> retrieveMedicines() {return service.getMedicines(); }
    
    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
    @RolesAllowed("GUEST")
    public User retrieve(@PathParam("id") int id)
    {
        return service.get(id);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    public void create(User user)
    {
        service.add(user);
    }

    @POST
    @Path("/useranswer")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    public void createUserAnswer(UserAnswer useranswer)
    {
        service.addUserAnswer(useranswer);
    }

    @POST
    @Path("/usermedicine")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    public void createUserMedicine(UserMedicine usermedicine)
    {
        service.addUserMedicine(usermedicine);
    }

    @GET
    @Path("/userid/{username}")
    @JsonView(View.Public.class)
    public int retrieve(@PathParam("username") String username) { return service.getUserIdByUsername(username) ;}

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    @RolesAllowed("GUEST")
    public void update(@PathParam("id") int id, @Auth User authenticator, User user)
    {
        service.update(authenticator, id, user);
    }
    
    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public void delete(@PathParam("id") int id)
    {
        service.delete(id);
    }
    
    @GET
    @Path("/me")
    @JsonView(View.Private.class)
    public User authenticate(@Auth User authenticator)
    {
        return authenticator;
    }
}
