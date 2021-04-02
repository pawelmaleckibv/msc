package master.msc.model;

import master.msc.enums.AnswerType;
import master.msc.enums.QuestionType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question extends MscBaseEntity {

    private Boolean isMandatory;

    private String name;

    private int innerNr;

    @Column(length=1024)
    private String contentOfTheQuestion;
    private AnswerType answerType;

    @Enumerated(EnumType.ORDINAL)
    private QuestionType questionType;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "question")
    private List<Task> tasks = new ArrayList<>();

    private String comment;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY, mappedBy = "question")
    private QuestionImage questionImage;

    public Question() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentOfTheQuestion() {
        return contentOfTheQuestion;
    }

    public void setContentOfTheQuestion(String contentOfTheQuestion) {
        this.contentOfTheQuestion = contentOfTheQuestion;
    }

    public AnswerType getAnswerType() {
        return answerType;
    }

    public void setAnswerType(AnswerType answerType) {
        this.answerType = answerType;
    }

    public Boolean isMandatory() {
        return isMandatory;
    }

    public void setMandatory(Boolean mandatory) {
        isMandatory = mandatory;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public Boolean getMandatory() {
        return isMandatory;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public QuestionImage getQuestionImage() {
        return questionImage;
    }

    public void setQuestionImage(QuestionImage questionImage) {
        this.questionImage = questionImage;
    }

    public int getInnerNr() {
        return innerNr;
    }

    public void setInnerNr(int innerNr) {
        this.innerNr = innerNr;
    }
}
