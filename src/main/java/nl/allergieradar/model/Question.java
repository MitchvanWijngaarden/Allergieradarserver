package nl.allergieradar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import nl.allergieradar.View;

import java.util.List;

/**
 * Created by Danny on 14-6-2017.
 */
public class Question {
    @JsonView(View.Public.class)
    private String questiontext;

    @JsonView(View.Public.class)
    private String questionnumber;

    @JsonView(View.Public.class)
    private List<Possibleanswer> possibleanswers;

    @JsonIgnore
    public String getQuestiontext() {
        return questiontext;
    }

    public void setQuestiontext(String questiontext) {
        this.questiontext = questiontext;
    }

    @JsonIgnore
    public String getQuestionnumber() { return questionnumber; }

    public void setQuestionnumber(String questionnumber) {
        this.questionnumber = questionnumber;
    }

    @JsonIgnore
    public List<Possibleanswer> getPossibleanswers() { return possibleanswers; };

    public void setPossibleanswers(List<Possibleanswer> possibleanswers) {
        this.possibleanswers = possibleanswers;
    }

}
