package nl.allergieradar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import java.security.Principal;
import nl.allergieradar.View;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Meer informatie over validatie:
 *  http://hibernate.org/validator/
 * 
 * @author Peter van Vliet
 */
public class User implements Principal {


    @NotEmpty
    @JsonView(View.Public.class)
    private int userID;

    @NotEmpty
    @Length(min = 3, max = 100)
    @JsonView(View.Public.class)
    private String userName;

    @NotEmpty
    @Email
    @JsonView(View.Public.class)
    private String emailAddress;
    
    @NotEmpty
    @Length(min = 8)
    @JsonView(View.Protected.class)
    private String password;

    @JsonView(View.Public.class)
    private boolean active;

    @JsonView(View.Private.class)
    private String[] roles;

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }



    @Override
    @JsonIgnore
    public String getName()
    {
        return userName;
    }

    public String[] getRoles()
    {
        return roles;
    }

    public void setRoles(String[] roles)
    {
        this.roles = roles;
    }
    
    public boolean hasRole(String roleName)
    {
        if (roles != null)
        {
            for(String role : roles)
            {
                if(roleName.equals(role))
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean equals(User user)
    {
        return emailAddress.equals(user.getEmailAddress());
    }
}
