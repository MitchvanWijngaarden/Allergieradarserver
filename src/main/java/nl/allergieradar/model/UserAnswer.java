package nl.allergieradar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import nl.allergieradar.View;

/**
 * Created by Danny on 29-6-2017.
 */
public class UserAnswer {

    @JsonView(View.Public.class)
    private String questionnumber;

    @JsonView(View.Public.class)
    private int userID;

    @JsonView(View.Public.class)
    private int answerID;

    @JsonIgnore
    public String getQuestionnumber() {
        return questionnumber;
    }

    public void setQuestionnumber(String questionnumber) {
        this.questionnumber = questionnumber;
    }

    @JsonIgnore
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @JsonIgnore
    public int getAnswerID() {
        return answerID;
    }

    public void setAnswerID(int answerID) {
        this.answerID = answerID;
    }
}
