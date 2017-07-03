package nl.allergieradar.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;

import nl.allergieradar.model.*;

/**
 *
 * @author Peter van Vliet
 */
@Singleton
public class UserDAO extends DatabaseDAO {

    private PreparedStatement getUser;
    private PreparedStatement getUserIdByUsername;
    private PreparedStatement addUser;
    private PreparedStatement getAll;
    private List<User> users;

    private PreparedStatement getAllAnswers;
    private PreparedStatement getAnswersByUser;
    private PreparedStatement addAnswer;

    private PreparedStatement getQuestions;
    private List<Question> questions;

    private PreparedStatement getPossibleanswers;
    private List<Possibleanswer> possibleanswers;

    private PreparedStatement addUserAnswer;

    private List<Answer> answers;
    private UserAnswer useranswer;

    private PreparedStatement getMedicines;
    private List<Medicine> medicines;

    private PreparedStatement addUserMedicine;

    public UserDAO() throws Exception {

        super();
        prepareStatements();
        getAll();

//        User user1 = new User();
//        user1.setUsername("First user");
//        user1.setPostcode("1234AB");
//        user1.setStreetnumber("12");
//        user1.setEmailadres("first@user.com");
//        user1.setPassword("first");
//        user1.setRoles(new String[] { "GUEST", "ADMIN" });
//
//        User user2 = new User();
//        user2.setUsername("Second user");
//        user2.setPostcode("9876ZY");
//        user2.setStreetnumber("98");
//        user2.setEmailadres("second@user.com");
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
            getUserIdByUsername = conn.prepareStatement("SELECT userID FROM user WHERE username = ?");
            addUser = conn.prepareStatement("INSERT INTO user (username, emailadres, year_of_birth," +
                    " gender, zip_code, password, active) VALUES (?, ?, ?, ?, ?, ?, 1 )");
            getAll = conn.prepareStatement("SELECT * FROM user");

            getAllAnswers = conn.prepareStatement("SELECT * FROM answer");
            getAnswersByUser = conn.prepareStatement("SELECT * FROM answer WHERE userID = ?");
            addAnswer = conn.prepareStatement("INSERT INTO answer (userID, questionID, answertext)" +
                    "VALUES (?, ?, ?)");

            getQuestions = conn.prepareStatement("SELECT questiontext, questionnumber FROM question");
            getPossibleanswers = conn.prepareStatement("SELECT answerid, answertext FROM possibleanswer WHERE questionnumber LIKE ? ");

            addUserAnswer = conn.prepareStatement("INSERT INTO useranswer(questionnumber, userID, answerID)" +
                    "VALUES (?, ?, ?)");

            getMedicines = conn.prepareStatement("SELECT medicineID, medicinename, medicinetype FROM medicine");

            addUserMedicine = conn.prepareStatement("INSERT INTO usermedicine(userID, medicineID)" +
                    "VALUES (?, ?)");
            
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
                user.setUsername(rs.getString(2));
                user.setEmailadres(rs.getString(3));
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

    public int getUserIdByUsername(String username)
    {
        try {
            getUserIdByUsername.setString(1, username);
            ResultSet rs = getUserIdByUsername.executeQuery();

            while (rs.next()) {
                System.out.println("username bestaat");
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("username bestaat niet");

        return 0;
    }

    public User getByUserName(String userName)
    {
        Optional<User> result = users.stream()
            .filter(user -> user.getUsername().equals(userName))
            .findAny();

        return result.isPresent()
            ? result.get()
            : null;
    }

    public List<Answer> getAllAnswers()
    {
        answers = new ArrayList<>();

        try {
            ResultSet rs = getAllAnswers.executeQuery();

            while (rs.next()) {
                Answer answer = new Answer();
                answer.setUserID(rs.getInt(1));
                answer.setQuestionID(rs.getInt(2));
                answer.setAnswerText(rs.getString(3));
                answers.add(answer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return answers;
    }

    public List<Question> getQuestion(){

        questions = new ArrayList<>();

        try {
            ResultSet rs = getQuestions.executeQuery();

            while (rs.next()) {
                Question question = new Question();
                question.setQuestiontext(rs.getString(1));
                question.setQuestionnumber(rs.getString(2));

                possibleanswers = getPossibleanswers(question.getQuestionnumber());

                question.setPossibleanswers(possibleanswers);

                questions.add(question);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }

    public List<Possibleanswer> getPossibleanswers(String questionnumber) {
        possibleanswers = new ArrayList<>();

        try {
            getPossibleanswers.setString(1, questionnumber);
            ResultSet rs = getPossibleanswers.executeQuery();

            while(rs.next()){
                Possibleanswer possibleanswer = new Possibleanswer();
                possibleanswer.setAnswerID(rs.getInt(1));
                possibleanswer.setAnswertext(rs.getString(2));
                possibleanswers.add(possibleanswer);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return possibleanswers;
    }

    public List<Medicine> getMedicines(){

        medicines = new ArrayList<>();

        try {
            ResultSet rs = getMedicines.executeQuery();

            while (rs.next()) {
                Medicine medicine = new Medicine();
                medicine.setMedicineID(rs.getInt( 1));
                medicine.setMedicinename(rs.getString(2));
                medicine.setMedicinetype(rs.getString(3));

                medicines.add(medicine);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicines;
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

    public void addAnswer(Answer answer) {
        try {
            addAnswer.setInt(1, answer.getUserID());
            addAnswer.setInt(2, answer.getQuestionID());
            addAnswer.setString(3, answer.getAnswerText());

            addAnswer.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) {
        try {
            System.out.println("addUser called");
            System.out.println(user.getUsername());
            System.out.println(user.getEmailadres());
            System.out.println(user.getYear_of_birth());
            System.out.println(user.getGender());
            System.out.println(user.getZip_code());
            System.out.println(user.getPassword());

            addUser.setString(1, user.getUsername());
            addUser.setString(2, user.getEmailadres());
            addUser.setInt(3, user.getYear_of_birth());
            addUser.setString(4, user.getGender());
            addUser.setInt(5, user.getZip_code());
            addUser.setString(6, user.getPassword());

            addUser.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUserAnswer(UserAnswer useranswer) {
        try {
            addUserAnswer.setString(1, useranswer.getQuestionnumber());
            addUserAnswer.setInt(2, useranswer.getUserID());
            addUserAnswer.setInt(3, useranswer.getAnswerID());

            addUserAnswer.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUserMedicine(UserMedicine usermedicine) {
        try {
            addUserMedicine.setInt(1, usermedicine.getUserID());
            addUserMedicine.setInt(2, usermedicine.getMedicineID());

            addUserMedicine.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
