package master.msc.testData;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import master.msc.enums.AnswerType;
import master.msc.enums.QuestionType;
import master.msc.model.Answer;
import master.msc.model.AnswerDependency;
import master.msc.model.Question;
import master.msc.model.Questionary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestImportCmmQuestionaries {

//    @Autowired
//    AttachmentService attachmentService;

    private final Questionary productionPlanning = new Questionary();
    private final Questionary productionStaerung = new Questionary();
    private final Questionary production = new Questionary();
    private final Questionary hr = new Questionary();
    private final Questionary it = new Questionary();
    private final Questionary logistik = new Questionary();

    @PostConstruct
    public void init() {
        productionPlanning.setName("Produktionsplanung (langfristig)");
        productionStaerung.setName("Produktionssteuerung (kurzfristig)");
        production.setName("Produktion (Fertigung und Montage)");
        hr.setName("HR");
        it.setName("IT");
        logistik.setName("Logistik");
//        getQuestions();
    }

    public Questionary getquestionaryByName(String name) {
        if (name.equals(this.productionPlanning.getName())) {
            return this.productionPlanning;
        } else if (name.equals(this.productionStaerung.getName())) {
            return this.productionStaerung;
        } else if (name.equals(this.production.getName())) {
            return this.production;
        } else if (name.equals(this.hr.getName())) {
            return this.hr;
        } else if (name.equals(this.it.getName())) {
            return this.it;
        } else if (name.equals(this.logistik.getName())) {
            return this.logistik;
        } else {
            return new Questionary();
        }
    }

    public void getQuestions() {
        List<Ques> questions = new ArrayList<>();
        List<UnresolvedAnswerDependency> unresolvedAnswerDependencyList = new ArrayList<>();

        try {
            String rootPath = System.getProperty("user.dir") + File.separator + "src/main/resources";
            String escapedseparator = File.separator + File.separator;
            String fileName = (rootPath + "/questionaires/toParse.xlsx").replaceAll("/", escapedseparator);
            questions = readQuesFromExell(fileName);
            unresolvedAnswerDependencyList = addDependencyToQuestionaries((rootPath + "/questionaires/dependencyToParse.xlsx").replaceAll("/", escapedseparator));
        } catch (IOException | org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
            e.printStackTrace();
        }
        createQuestionaries(questions, unresolvedAnswerDependencyList);
    }

    private List<UnresolvedAnswerDependency> addDependencyToQuestionaries(String fileName) throws IOException,
            InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        return readAnswerDependencyFromExell(fileName);
    }

//    private QuestionImage createImage(String imageName) {
//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("/questionaires/images/" +
//        (imageName.length()==1 ? '0'+imageName : imageName) + ".png");
//        QuestionImage questionImage = (QuestionImage) attachmentService.createDoc(
//                "png",
//                imageName,
//                "QuestionImage",
//                inputStream,
//                false);
//        return questionImage;
//    }

    @Transactional
    void createQuestionaries(List<Ques> questions, List<UnresolvedAnswerDependency> unresolvedAnswerDependencyList) {
        questions.remove(0);
        for (Ques ques : questions) {
            Question question = new Question();
            question.setInnerNr(ques.innerNr);
            question.setName(ques.name);
            question.setContentOfTheQuestion(ques.content);
            question.setAnswerType(AnswerType.ENUM);
            question.setQuestionType(QuestionType.MULTIPLE_SELECTION);
//            QuestionImage questionImage = createImage(Integer.toString(ques.innerNr));
//            questionImage.setQuestion(question);
//            question.setQuestionImage(questionImage);

            for (int i = 0; i < ques.getAnswers().size(); i++) {
                if (!ques.getAnswers().get(i).equals("-")) {
                    Answer answer = new Answer();
                    answer.setAnswerContent(ques.getAnswers().get(i));
                    answer.setMaturityLevel(i);
                    answer.setQuestion(question);
                    question.getAnswers().add(answer);
                }
            }

            assignDependentAnswers(question, unresolvedAnswerDependencyList);

            if (ques.isPoductionPlanningAssigned()) {
                productionPlanning.getQuestions().add(question);
            }
            if (ques.isPoductionStaerungAssigned()) {
                productionStaerung.getQuestions().add(question);
            }
            if (ques.isPoductionAssigned()) {
                production.getQuestions().add(question);
            }
            if (ques.isHrAssigned()) {
                hr.getQuestions().add(question);
            }
            if (ques.isItAssigned()) {
                it.getQuestions().add(question);
            }
            if (ques.isLogistikAssigned()) {
                logistik.getQuestions().add(question);
            }
        }
    }


    private void assignDependentAnswers(Question question, List<UnresolvedAnswerDependency> unresolvedAnswerDependencyList) {
        for (UnresolvedAnswerDependency unresolvedAnswerDependency : unresolvedAnswerDependencyList) {
            if (question.getInnerNr() == unresolvedAnswerDependency.currentQuestionNumber) {
                for (Answer answer : question.getAnswers()) {
                    if (answer.getMaturityLevel() == unresolvedAnswerDependency.answerMaturityLevel) {
                        AnswerDependency answerDependency = new AnswerDependency();
                        answerDependency.setAnswer(answer);
                        answerDependency.setDependentQuestion(question);
                        answerDependency.setMinimumMaturityLevel(unresolvedAnswerDependency.minimumMaturityLevel);
                        answer.addAnswerDependency(answerDependency);
                    }
                }
            }
        }
    }

    public static double asNumeric(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

    private List<Ques> readQuesFromExell(String fileName) throws IOException, InvalidFormatException,
            org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        ExcelImporter quesEcxelImporter = new ExcelImporter<Ques>(fileName) {
            @Override
            protected Ques handleLine(List<String> cellsInRow) {
                Ques ques = returnNewQuesFromListOfCells(cellsInRow);
                if (ques.name != null) {
                    return ques;
                } else {
                    return null;
                }
            }
        };
        return quesEcxelImporter.read();
    }

    private List<UnresolvedAnswerDependency> readAnswerDependencyFromExell(String fileName) throws IOException,
            InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        AnswerDependencyEcxelImporter answerDependencyEcxelImporter = new AnswerDependencyEcxelImporter(fileName);
        List<UnresolvedAnswerDependency> unresolvedAnswerDependencyList = new ArrayList<>();
        List<List<UnresolvedAnswerDependency>> importedList = answerDependencyEcxelImporter.read();
        for (List<UnresolvedAnswerDependency> processedList : importedList) {
            for (UnresolvedAnswerDependency processedItem : processedList) {
                unresolvedAnswerDependencyList.add(processedItem);
            }
        }
        return unresolvedAnswerDependencyList;
    }

    private Ques returnNewQuesFromListOfCells(List<String> helpList) {
        Ques ques = new Ques();
        if (helpList.size() > 0) {

            ques.setInnerNr((int) asNumeric(helpList.get(0)));
            ques.setName(helpList.get(1));
            ques.setContent(helpList.get(2));
            for (int i = 3; i <= 10; i++) {
                if (!helpList.get(i).equals("BLANK")) {
                    ques.getAnswers().add(helpList.get(i));
                }
            }

            if (!helpList.get(helpList.size() - 6).equals("BLANK")) {
                ques.setPoductionPlanningAssigned(true);
            }
            if (!helpList.get(helpList.size() - 5).equals("BLANK")) {
                ques.setPoductionStaerungAssigned(true);
            }
            if (!helpList.get(helpList.size() - 4).equals("BLANK")) {
                ques.setPoductionAssigned(true);
            }
            if (!helpList.get(helpList.size() - 3).equals("BLANK")) {
                ques.setHrAssigned(true);
            }
            if (!helpList.get(helpList.size() - 2).equals("BLANK")) {
                ques.setItAssigned(true);
            }
            if (!helpList.get(helpList.size() - 1).equals("BLANK")) {
                ques.setLogistikAssigned(true);
            }
        }
        return ques;
    }
}