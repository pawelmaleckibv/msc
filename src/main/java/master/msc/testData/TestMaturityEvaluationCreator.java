package master.msc.testData;


import master.msc.enums.MaturityEvaluationStatus;
import master.msc.model.MaturityEvaluation;
import org.springframework.stereotype.Component;

@Component
public class TestMaturityEvaluationCreator extends TestDataCreator<MaturityEvaluation> {


    @Override
    public MaturityEvaluation doCreateEntity() {
        MaturityEvaluation maturityEvaluation = new MaturityEvaluation();

        maturityEvaluation.setStatus(MaturityEvaluationStatus.COLECTING_ANSWERS);

        return maturityEvaluation;
    }


}

