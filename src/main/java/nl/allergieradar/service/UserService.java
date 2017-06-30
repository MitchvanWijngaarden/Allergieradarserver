package nl.allergieradar.service;

import java.util.Collection;
import javax.inject.Inject;
import javax.inject.Singleton;

import nl.allergieradar.model.*;
import nl.allergieradar.persistence.UserDAO;

/**
 *
 * @author Peter van Vliet
 */
@Singleton
public class UserService extends BaseService<User>
{
    private final UserDAO dao;
    
    @Inject
    public UserService(UserDAO dao)
    {
        this.dao = dao;
    }
    
    public Collection<User> getAll()
    {
        return dao.getAll();
    }

    public Collection<Question> getQuestion() {return dao.getQuestion(); }

    public Collection<Medicine> getMedicines() {return dao.getMedicines(); }

    public int getUserIdByUsername(String username) {return dao.getUserIdByUsername(username); }

    public User get(int id)
    {
        return requireResult(dao.get(id));
    }

    public void addUserAnswer(UserAnswer useranswer){
        dao.addUserAnswer(useranswer);
    }

    public void addUserMedicine(UserMedicine usermedicine){
        dao.addUserMedicine(usermedicine);
    }
    
    public void add(User user)
    {
        user.setRoles(new String[] { "GUEST" });

        dao.addUser(user);
    }
    
    public void update(User authenticator, int id, User user)
    {
        // Controleren of deze gebruiker wel bestaat
        User oldUser = get(id);
        
        if (!authenticator.hasRole("ADMIN"))
        {
            // Vaststellen dat de geauthenticeerde gebruiker
            // zichzelf aan het aanpassen is
            assertSelf(authenticator, oldUser);
        }
        
        dao.update(id, user);
    }
    
    public void delete(int id)
    {
        // Controleren of deze gebruiker wel bestaat
        User user = get(id);
        
        dao.delete(id);
    }

    public void addAnswer(Answer answer)
    {
        dao.addAnswer(answer);
    }
}
