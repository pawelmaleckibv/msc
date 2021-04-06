package master.msc.testData;

import master.msc.model.MaturityLevel;
import org.springframework.stereotype.Component;

@Component
public class TestMaturityLevelCreator extends TestDataCreator<MaturityLevel> {

    @Override
    public MaturityLevel doCreateEntity(){
        MaturityLevel maturityLevel = new MaturityLevel();
        maturityLevel.setDescription("MaturityLevelDescription_" + getRandom().nextInt(5));
        maturityLevel.setLevel(1);
        return maturityLevel;
    }
}
