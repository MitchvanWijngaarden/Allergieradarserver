package nl.allergieradar.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;
import nl.allergieradar.model.User;

/**
 *
 * @author Peter van Vliet
 */
@Singleton
public class UserDAO extends DatabaseDAO {

    private PreparedStatement getUser;
    private PreparedStatement addUser;
    private PreparedStatement getAll;
    private List<User> users;

    public UserDAO() throws Exception {

        super();
        prepareStatements();
        getAll();

//        User user1 = new User();
//        user1.setUserName("First user");
//        user1.setPostcode("1234AB");
//        user1.setStreetnumber("12");
//        user1.setEmailAddress("first@user.com");
//        user1.setPassword("first");
//        user1.setRoles(new String[] { "GUEST", "ADMIN" });
//
//        User user2 = new User();
//        user2.setUserName("Second user");
//        user2.setPostcode("9876ZY");
//        user2.setStreetnumber("98");
//        user2.setEmailAddress("second@user.com");
//        user2.setPassword("second");
//        user2.setRoles(new String[] { "GUEST" });
//
//        users = new ArrayList<>();
//        users.add(user1);
//        users.add(user2);
    }

    private void prepareStatements() {

        try {
            getUser = conn.prepareStatement("SELECT * FROM user WHERE id=?");
            addUser = conn.prepareStatement("INSERT INTO user (username, emailadres, password," +
                    " userid, active) VALUES (?, ?, ?, ?, 1 )");
            getAll = conn.prepareStatement("SELECT * FROM user");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<User> getAll()
    {
        users = new ArrayList<>();

        try {
            ResultSet rs = getAll.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setEmailAddress(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setActive(rs.getBoolean(5));
                users.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
    
    public User get(int id)
    {
        try
        {
            return users.get(id);
        }
        catch(IndexOutOfBoundsException exception)
        {
            return null;
        }
    }
    
    public User getByUserName(String userName)
    {
        Optional<User> result = users.stream()
            .filter(user -> user.getUserName().equals(userName))
            .findAny();
        
        return result.isPresent()
            ? result.get()
            : null;
    }
    
    public void add(User user)
    {
        users.add(user);
    }
    
    public void update(int id, User user)
    {
        users.set(id, user);
    }
    
    public void delete(int id)
    {
        users.remove(id);
    }
}
