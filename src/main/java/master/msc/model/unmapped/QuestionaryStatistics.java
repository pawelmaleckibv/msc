package master.msc.model.unmapped;


import master.msc.model.MscBaseEntity;

import java.util.UUID;

public class QuestionaryStatistics extends MscBaseEntity {
    public String questionaryName;
    public Integer numberOfAnsweredQuestionaires;
    public Integer numberOfQuestionaires;
    public Integer averageMaturityLevel;
    public UUID questionaryId;

    public QuestionaryStatistics(String questionaryName, Integer numberOfAnsweredQuestionaires, Integer numberOfQuestionaires, UUID id) {
        this.questionaryName = questionaryName;
        this.numberOfAnsweredQuestionaires = numberOfAnsweredQuestionaires;
        this.numberOfQuestionaires = numberOfQuestionaires;
        this.questionaryId = id;
    }

    public QuestionaryStatistics(){}
}
