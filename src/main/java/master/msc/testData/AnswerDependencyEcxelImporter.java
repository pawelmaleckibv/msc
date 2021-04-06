package master.msc.testData;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static master.msc.testData.TestImportCmmQuestionaries.asNumeric;

enum ParserState {
    NOTHING,
    HEADER,
    BODY,
}

class UnresolvedAnswerDependency {
    int answerMaturityLevel;
    int minimumMaturityLevel;
    String dependentQuestion;
    String currentQuestion;
    int currentQuestionNumber;


    UnresolvedAnswerDependency(String currentQuestion,
                               String dependentQuestion,
                               int answerMaturityLevel,
                               int minimumMaturityLevel,
                               int currentQuestionNumber) {
        this.answerMaturityLevel = answerMaturityLevel;
        this.currentQuestion = currentQuestion;
        this.dependentQuestion = dependentQuestion;
        this.minimumMaturityLevel = minimumMaturityLevel;
        this.currentQuestionNumber = currentQuestionNumber;
    }
}

class AnswerDependencyEcxelImporter extends ExcelImporter<List<UnresolvedAnswerDependency>> {

    String currentQuestionName;
    int currentQuestionInnerNumber;

    public AnswerDependencyEcxelImporter(String fileName) {
        super(fileName);
    }

    private ParserState getParserState(List<String> cellsInRow) {
        if (cellsInRow.size() < 3) {
            return ParserState.NOTHING;
        }
        if (cellsInRow.get(0).equals("BLANK") || cellsInRow.get(0).startsWith("The value shows")) {
            return ParserState.NOTHING;
        }
        if (cellsInRow.get(0).startsWith("Frage") && !cellsInRow.get(1).startsWith("Korrelierendes Kriterium")) {
            return ParserState.HEADER;
        } else {
            Pattern pattern = Pattern.compile("[0-9]*.0");
            Matcher matcher = pattern.matcher(cellsInRow.get(0));
            if (matcher.matches()) {
                return ParserState.BODY;
            } else {
                return ParserState.NOTHING;
            }
        }
    }

    private List<UnresolvedAnswerDependency> returnNewAnswerDependencyFromListOfCells(List<String> cellsInRow) {
        ParserState parserState = getParserState(cellsInRow);
        List<UnresolvedAnswerDependency> unresolvedAnswerDependencyList = new ArrayList<UnresolvedAnswerDependency>();
        switch (parserState) {
            case NOTHING:
                break;
            case BODY:
                String dependentQuestionName = cellsInRow.get(1);
                for (int answerMaturityLevel = 0; answerMaturityLevel < 8; answerMaturityLevel++) {
                    if(!cellsInRow.get(2+answerMaturityLevel).equals("BLANK")) {
                        int minimumMaturityLevel = (int) Double.parseDouble(cellsInRow.get(2+answerMaturityLevel));
                        unresolvedAnswerDependencyList.add(
                                new UnresolvedAnswerDependency(currentQuestionName,
                                        dependentQuestionName,
                                        answerMaturityLevel,
                                        minimumMaturityLevel,
                                        currentQuestionInnerNumber)
                        );
                    }

                }
                break;
            case HEADER:
                currentQuestionInnerNumber = (int) asNumeric(cellsInRow.get(0).split("\\s+")[1]);
                currentQuestionName = cellsInRow.get(2);
                break;
            default:
                break;
        }
        int i = 0;
        return unresolvedAnswerDependencyList;
    }

    @Override
    protected List<UnresolvedAnswerDependency> handleLine(List<String> cellsInRow) {
        List<UnresolvedAnswerDependency> answerDependencyList = returnNewAnswerDependencyFromListOfCells(cellsInRow);
        if (answerDependencyList.size() != 0) {
            return answerDependencyList;
        } else {
            return null;
        }
    }
}
