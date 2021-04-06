package master.msc.testData;


import master.msc.enums.QuestionType;
import master.msc.model.Answer;
import master.msc.model.Question;
import master.msc.model.Questionary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestImportQuestionaries extends TestDataCreator<Questionary> {
    @Override
    protected Questionary doCreateEntity() {
        return drawQuestionary();
    }

    private Questionary drawQuestionary() {

        List<Pair> files = new ArrayList<>();
//        files.add(new Pair("Demand management maturity", "/questionaires/demand_management_maturity.txt"));
//        files.add(new Pair("Integrated business planning maturity", "/questionaires/integrated_business_planning_aturity.txt"));
//        files.add(new Pair("Product and portfolio optimization", "/questionaires/product_and_portfolio_optimization_maturity.txt"));
//        files.add(new Pair("Supply chain maturity", "/questionaires/supply_chain_maturity.txt"));
        files.add(new Pair("All questions", "/questionaires/supply_chain_maturity.txt"));

        Pair file = files.get(getRandom().nextInt(files.size()));

        return readQuestionary(file.getKey(), file.getValue());
    }

    private Questionary readQuestionary(String questionaryName, String filePath) {
        Questionary questionary = new Questionary(questionaryName);
        List<String> questionaryLines = parseLinesToList(filePath);
        Question question = null;
        Answer answer;
        boolean isQuestion = true;
        Integer questionMaturityLevel = 5;

        for (String line : questionaryLines) {
            if (isQuestion) {
                question = new Question();
                question.setContentOfTheQuestion(line);
                isQuestion = false;
                questionary.getQuestions().add(question);
                questionMaturityLevel = 5;
            } else if (!line.equals("") && question != null) {
                answer = new Answer();
                answer.setAnswerContent(line);
                answer.setQuestion(question);
                answer.setMaturityLevel(questionMaturityLevel < 1 ? 0 : questionMaturityLevel);
                questionMaturityLevel--;
                question.getAnswers().add(answer);
            } else {
                isQuestion = true;
            }
            question.setQuestionType(QuestionType.MULTIPLE_SELECTION);
        }

        return questionary;
    }

    class Pair {
        private String key;
        private String value;
        Pair(String key, String value){
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }

}
