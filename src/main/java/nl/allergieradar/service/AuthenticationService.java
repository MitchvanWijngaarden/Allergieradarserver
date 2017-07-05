/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.allergieradar.service;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.Authorizer;
import io.dropwizard.auth.basic.BasicCredentials;
import nl.allergieradar.BCrypt;
import nl.allergieradar.model.User;
import nl.allergieradar.persistence.UserDAO;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

/**
 *
 * @author Peter van Vliet
 */
@Singleton
public class AuthenticationService implements Authenticator<BasicCredentials, User>, Authorizer<User>
{
    private final UserDAO userDAO;
    
    @Inject
    public AuthenticationService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException
    {
        User user = userDAO.getByUserName(credentials.getUsername());
        
        if (user != null && BCrypt.checkpw(credentials.getPassword(),user.getPassword()))
        {
            return Optional.of(user);
        }
        
        return Optional.empty();
    }


    @Override
    public boolean authorize(User user, String roleName)
    {
        return user.hasRole(roleName);
    }
}
