package master.msc.model;

import java.util.ArrayList;
import java.util.List;


public class QuestionaryDto {
    public String questionaryName;
    public List<String> businessUnitName = new ArrayList<>();
    public List<QuestionDto> questionDto = new ArrayList<>();

    public QuestionaryDto(String questionaryName, List<String> businessUnitName, List<QuestionDto> questionDto) {
        this.questionaryName = questionaryName;
        this.businessUnitName = businessUnitName;
        this.questionDto = questionDto;
    }

    public QuestionaryDto() {
    }

    public String getQuestionaryName() {
        return questionaryName;
    }

    public void setQuestionaryName(String questionaryName) {
        this.questionaryName = questionaryName;
    }

    public List<String> getBusinessUnitName() {
        return businessUnitName;
    }

    public void setBusinessUnitName(List<String> businessUnitName) {
        this.businessUnitName = businessUnitName;
    }

    public List<QuestionDto> getQuestionDto() {
        return questionDto;
    }

    public void setQuestionDto(List<QuestionDto> questionDto) {
        this.questionDto = questionDto;
    }
}

