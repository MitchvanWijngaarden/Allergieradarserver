package nl.allergieradar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import java.security.Principal;

import nl.allergieradar.View;

/**
 * Meer informatie over validatie:
 *  http://hibernate.org/validator/
 * 
 * @author Peter van Vliet
 */
public class User implements Principal {

    @JsonView(View.Public.class)
    private int userID;

    //@NotEmpty
    //@Length(min = 3, max = 100)
    @JsonView(View.Public.class)
    private String username;

    //@NotEmpty
    //@Email
    @JsonView(View.Public.class)
    private String emailadres;

    //@NotEmpty
    //@Length(min = 4, max = 4)
    @JsonView(View.Public.class)
    private int year_of_birth;

    //@NotEmpty
    //@Length(min = 1, max = 1)
    @JsonView(View.Public.class)
    private String gender;

    //@NotEmpty
    //@Length(min = 4, max = 4)
    @JsonView(View.Public.class)
    private int zip_code;
    
    //@NotEmpty
    //@Length(min = 8)
    @JsonView(View.Protected.class)
    private String password;

    @JsonView(View.Public.class)
    private boolean active;

    @JsonView(View.Private.class)
    private String[] roles;

    @JsonIgnore
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    @JsonIgnore
    public String getEmailadres()
    {
        return emailadres;
    }

    public void setEmailadres(String emailadres)
    {
        this.emailadres = emailadres;
    }

    @JsonIgnore
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @JsonIgnore
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @JsonIgnore
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
        return username;
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
        return emailadres.equals(user.getEmailadres());
    }

    @JsonIgnore
    public int getYear_of_birth() {
        return year_of_birth;
    }

    public void setYear_of_birth(int year_of_birth) {
        this.year_of_birth = year_of_birth;
    }

    @JsonIgnore
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonIgnore
    public int getZip_code() {
        return zip_code;
    }

    public void setZip_code(int zip_code) {
        this.zip_code = zip_code;
    }
}
