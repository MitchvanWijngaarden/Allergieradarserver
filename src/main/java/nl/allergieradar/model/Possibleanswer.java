package nl.allergieradar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import nl.allergieradar.View;

/**
 * Created by Danny on 15-6-2017.
 */
public class Possibleanswer {
    @JsonView(View.Public.class)
    private int answerID;

    @JsonView(View.Public.class)
    private String answertext;

    @JsonIgnore
    public String getAnswertext() { return answertext; }

    public void setAnswertext(String answertext) {
        this.answertext = answertext;
    }

    @JsonIgnore
    public int getAnswerID() {
        return answerID;
    }

    public void setAnswerID(int answerID) {
        this.answerID = answerID;
    }
}
