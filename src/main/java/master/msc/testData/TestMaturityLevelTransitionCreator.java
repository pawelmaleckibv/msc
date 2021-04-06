package master.msc.testData;


import master.msc.enums.MaturityLevelTransitionStatus;
import master.msc.model.MaturityLevel;
import master.msc.model.MaturityLevelTransition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestMaturityLevelTransitionCreator extends TestDataCreator<MaturityLevelTransition> {

    @Autowired
    public TestMaturityEvaluationCreator testMaturityEvaluationCreator;

    @Autowired
    public TestMaturityLevelCreator testMaturityLevelCreator;

    @Override
    public MaturityLevelTransition doCreateEntity() {
        MaturityLevelTransition maturityLevelTransition = new MaturityLevelTransition();

        maturityLevelTransition.setCurrentMaturityLevel(new MaturityLevel(1));
        maturityLevelTransition.setStatus(MaturityLevelTransitionStatus.IN_PROGRESS);

        maturityLevelTransition.setCurrentMaturityLevel(testMaturityLevelCreator.createEntity());
        maturityLevelTransition.setTargetMaturityLevel(testMaturityLevelCreator.createEntity());
        return maturityLevelTransition;
    }
}

