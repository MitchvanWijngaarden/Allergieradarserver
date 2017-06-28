package nl.allergieradar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import nl.allergieradar.View;

/**
 * Created by Danny on 5-6-2017.
 */
public class Answer {

    @JsonView(View.Public.class)
    private int userID;

    @JsonView(View.Public.class)
    private int questionID;

    @JsonView(View.Public.class)
    private String answerText;

    @JsonIgnore
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @JsonIgnore
    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    @JsonIgnore
    public String getAnswerText() { return answerText; }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}
