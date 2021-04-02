package master.msc.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "answers")
public class Answer extends MscBaseEntity {

    @Column(length=1024)
    private String answerContent;
    private Integer maturityLevel;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Question question;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY, mappedBy = "answer")
    private List<QuestionAnswer> questionAnswers = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY, mappedBy = "answer")
    private List<AnswerDependency> answerDependencies = new ArrayList<>();


    public Answer() {
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<QuestionAnswer> getQuestionAnswers() {
        return questionAnswers;
    }

    public void setQuestionAnswers(List<QuestionAnswer> questionAnswers) {
        this.questionAnswers = questionAnswers;
    }

    public Integer getMaturityLevel() {
        return maturityLevel;
    }

    public void setMaturityLevel(Integer maturityLevel) {
        this.maturityLevel = maturityLevel;
    }

    public List<AnswerDependency> getAnswerDependencies() {
        return answerDependencies;
    }

    public void addAnswerDependency(AnswerDependency answerDependency) {
        this.answerDependencies.add(answerDependency);
    }
}
