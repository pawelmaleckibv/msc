package master.msc.testData;

import java.util.ArrayList;
import java.util.List;

public class Ques {

    int innerNr;
    String name;
    String content;
    List<String> answers = new ArrayList<>();
    boolean poductionPlanningAssigned = false;
    boolean poductionStaerungAssigned = false;
    boolean poductionAssigned = false;
    boolean hrAssigned = false;
    boolean itAssigned = false;
    boolean logistikAssigned = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public boolean isPoductionPlanningAssigned() {
        return poductionPlanningAssigned;
    }

    public void setPoductionPlanningAssigned(boolean poductionPlanningAssigned) {
        this.poductionPlanningAssigned = poductionPlanningAssigned;
    }

    public boolean isPoductionStaerungAssigned() {
        return poductionStaerungAssigned;
    }

    public void setPoductionStaerungAssigned(boolean poductionStaerungAssigned) {
        this.poductionStaerungAssigned = poductionStaerungAssigned;
    }

    public boolean isPoductionAssigned() {
        return poductionAssigned;
    }

    public void setPoductionAssigned(boolean poductionAssigned) {
        this.poductionAssigned = poductionAssigned;
    }

    public boolean isHrAssigned() {
        return hrAssigned;
    }

    public void setHrAssigned(boolean hrAssigned) {
        this.hrAssigned = hrAssigned;
    }

    public boolean isItAssigned() {
        return itAssigned;
    }

    public void setItAssigned(boolean itAssigned) {
        this.itAssigned = itAssigned;
    }

    public boolean isLogistikAssigned() {
        return logistikAssigned;
    }

    public void setLogistikAssigned(boolean logistikAssigned) {
        this.logistikAssigned = logistikAssigned;
    }

    public int getInnerNr() {
        return innerNr;
    }

    public void setInnerNr(int innerNr) {
        this.innerNr = innerNr;
    }
}
